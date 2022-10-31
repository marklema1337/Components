/*     */ package com.lbs.transport;
/*     */ 
/*     */ import com.lbs.channel.IChannel;
/*     */ import com.lbs.channel.IChannelDataReader;
/*     */ import com.lbs.channel.IChannelListener;
/*     */ import com.lbs.channel.IChannelProvider;
/*     */ import com.lbs.channel.ICryptor;
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.http.HttpChannelProvider;
/*     */ import com.lbs.invoke.RttiUtil;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MultiBufferTransportClient
/*     */   extends ObjectTransportClient
/*     */   implements IChannelDataReader
/*     */ {
/*  25 */   public static final byte[] SEPARATOR = new byte[] { 75, 9, 17, 1 };
/*     */   protected byte[] m_ReadBuffer;
/*     */   private IMultiBufferTransportClientReader m_ChunkReader;
/*  28 */   private int m_PackageLength = 0;
/*  29 */   private int m_RetryCount = 0;
/*     */   
/*     */   private ByteArrayOutputStreamEx m_BufferStream;
/*     */ 
/*     */   
/*     */   public MultiBufferTransportClient(IChannelProvider channelProv, ISessionBase session, ICryptor localCryptor, ICryptor remoteCryptor) {
/*  35 */     super(channelProv, session, localCryptor, remoteCryptor);
/*  36 */     this.m_BufferStream = new ByteArrayOutputStreamEx(8192);
/*     */   }
/*     */ 
/*     */   
/*     */   public TransportClient duplicateEx() {
/*  41 */     TransportClient cand = new MultiBufferTransportClient((IChannelProvider)new HttpChannelProvider(), this.m_Session, this.m_LocalCryptor, this.m_RemoteCryptor);
/*  42 */     cand.setRecorder(this.recorder);
/*  43 */     return cand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean channelDataRead(IChannel channel, byte[] data) {
/*  52 */     this.m_ReturnData = data;
/*  53 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int processReadBuffer(byte[] data, int start, int count) {
/*  58 */     int buffLen = (this.m_ReadBuffer != null) ? 
/*  59 */       this.m_ReadBuffer.length : 
/*  60 */       0;
/*  61 */     int seq = 0;
/*  62 */     int index = 0;
/*  63 */     while (seq < 4 && index < buffLen)
/*  64 */       seq = updateSeq(this.m_ReadBuffer[index++], seq); 
/*  65 */     int index2 = start;
/*  66 */     while (seq < 4 && index2 < count)
/*  67 */       seq = updateSeq(data[index2++], seq); 
/*  68 */     if (seq == 4)
/*     */       
/*     */       try {
/*  71 */         int totalBytes = index + index2 - start;
/*  72 */         ByteArrayOutputStream stream = new ByteArrayOutputStream(totalBytes);
/*  73 */         if (index > 0) {
/*     */           
/*  75 */           stream.write(this.m_ReadBuffer, 0, Math.min(totalBytes - 4, buffLen));
/*  76 */           if (index2 <= 0)
/*  77 */             if (index < this.m_ReadBuffer.length) {
/*  78 */               this.m_ReadBuffer = RttiUtil.copyByteArray(this.m_ReadBuffer, index, this.m_ReadBuffer.length - index);
/*     */             } else {
/*  80 */               this.m_ReadBuffer = null;
/*     */             }  
/*  82 */         }  if (index2 - 4 > start)
/*  83 */           stream.write(data, start, index2 - start - 4); 
/*  84 */         byte[] chunkData = stream.toByteArray();
/*  85 */         if (index2 < count)
/*  86 */           this.m_ReadBuffer = RttiUtil.copyByteArray(data, index2, count - index2); 
/*  87 */         return processChunk(chunkData) ? 
/*  88 */           1 : 
/*  89 */           0;
/*     */       }
/*  91 */       catch (Exception e) {
/*     */         
/*  93 */         return 0;
/*     */       }  
/*  95 */     if (count > 0)
/*     */       
/*     */       try {
/*  98 */         ByteArrayOutputStream stream = new ByteArrayOutputStream(buffLen + count);
/*  99 */         if (buffLen > 0)
/* 100 */           stream.write(this.m_ReadBuffer); 
/* 101 */         stream.write(data, start, count);
/* 102 */         this.m_ReadBuffer = stream.toByteArray();
/*     */       }
/* 104 */       catch (Exception e) {
/*     */         
/* 106 */         e.printStackTrace();
/*     */       }  
/* 108 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean processChunk(byte[] data) {
/* 113 */     if (data == null) {
/* 114 */       return true;
/*     */     }
/*     */     try {
/* 117 */       if (this.m_LocalCryptor != null)
/* 118 */         data = this.m_LocalCryptor.decrypt(data, 0, data.length); 
/* 119 */       if (this.m_Compressor != null && (this.m_CompressType & 0x2) == 2) {
/* 120 */         data = this.m_Compressor.uncompress(data, 0, data.length);
/*     */       }
/* 122 */     } catch (Exception e) {
/*     */       
/* 124 */       e.printStackTrace();
/*     */     } 
/* 126 */     return chunkDataRead(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readChannelData(IChannelListener listener, InputStream stream, int length) {
/* 132 */     if (JLbsConstants.BUFFERED_REPORT && this.m_ChunkReader instanceof ICommandProcessor) {
/*     */       
/* 134 */       readCommands(stream, (ICommandProcessor)this.m_ChunkReader);
/*     */     } else {
/*     */       
/* 137 */       readAsCommand(stream);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readCommands(InputStream stream, ICommandProcessor processor) {
/* 147 */     byte[] intBuffer = new byte[4];
/* 148 */     int test = -1;
/* 149 */     int available = 0;
/* 150 */     int dataLength = 0;
/* 151 */     int remain = 0;
/* 152 */     while (this.m_RetryCount < 1000) {
/*     */ 
/*     */       
/*     */       try {
/* 156 */         if (dataLength == 0) {
/*     */           
/* 158 */           int i = 0;
/* 159 */           while (i < 4) {
/*     */             
/* 161 */             test = stream.read();
/* 162 */             if (test > -1) {
/*     */               
/* 164 */               intBuffer[i] = (byte)test;
/* 165 */               i++;
/*     */             } else {
/*     */               
/* 168 */               Thread.sleep(20L);
/* 169 */             }  if (i == 4) {
/*     */               
/* 171 */               dataLength = TransportUtil.byteArrayToInt(intBuffer);
/* 172 */               remain = dataLength;
/* 173 */               if (remain <= 0)
/*     */                 break; 
/* 175 */               available = 0;
/* 176 */               test = -1;
/*     */             } 
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/* 182 */         remain = dataLength - this.m_BufferStream.size();
/* 183 */         if (remain > 0)
/* 184 */           test = stream.read(); 
/* 185 */         if (test > -1 || remain <= 0) {
/*     */           
/* 187 */           if (remain > 0)
/* 188 */             this.m_BufferStream.write(test); 
/* 189 */           available += stream.available();
/* 190 */           remain = dataLength - this.m_BufferStream.size();
/* 191 */           if (available > 0 || remain <= 0)
/*     */           {
/* 193 */             if (remain <= 0) {
/*     */               
/* 195 */               byte[] data = this.m_BufferStream.toByteArray();
/* 196 */               Object obj = TransportUtil.deserializeObject(data);
/* 197 */               if (obj instanceof ArrayList && processor.processCommands((ArrayList)obj)) {
/*     */                 
/* 199 */                 dataLength = 0;
/* 200 */                 remain = 0;
/* 201 */                 this.m_RetryCount = 0;
/* 202 */                 this.m_BufferStream.reset();
/*     */               }
/*     */               else {
/*     */                 
/* 206 */                 stream.close();
/*     */ 
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } else {
/* 212 */               int bufLen = Math.min(available, remain);
/* 213 */               byte[] readBuffer = new byte[bufLen];
/* 214 */               int read = stream.read(readBuffer, 0, bufLen);
/* 215 */               this.m_BufferStream.write(readBuffer, 0, read);
/* 216 */               if (read > JLbsConstants.BUFFER_SIZE)
/* 217 */                 this.m_RetryCount = 0; 
/* 218 */               available -= read;
/*     */             } 
/*     */           }
/*     */         } 
/* 222 */         if (available > 0) {
/*     */           
/* 224 */           this.m_RetryCount = 0;
/*     */           
/*     */           continue;
/*     */         } 
/* 228 */         if (this.m_RetryCount > 10) {
/*     */           int waitTime;
/*     */           
/* 231 */           if (this.m_RetryCount < 20) {
/* 232 */             waitTime = 5;
/* 233 */           } else if (this.m_RetryCount < 40) {
/* 234 */             waitTime = 20;
/* 235 */           } else if (this.m_RetryCount < 50) {
/* 236 */             waitTime = 50;
/* 237 */           } else if (this.m_RetryCount < 100) {
/* 238 */             waitTime = 200;
/*     */           } else {
/* 240 */             waitTime = 800;
/* 241 */           }  Thread.sleep(waitTime);
/*     */         } 
/* 243 */         this.m_RetryCount++;
/*     */ 
/*     */       
/*     */       }
/* 247 */       catch (Exception e) {
/*     */         
/* 249 */         this.m_RetryCount = 1000;
/* 250 */         LbsConsole.getLogger(getClass()).debug("Report readChannelData", e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void readAsCommand(InputStream stream) {
/* 257 */     byte[] readBuffer = new byte[4096];
/* 258 */     int available = 0;
/* 259 */     while (this.m_RetryCount < 1000) {
/*     */ 
/*     */       
/*     */       try {
/* 263 */         int test = stream.read();
/* 264 */         if (test > -1) {
/*     */           
/* 266 */           this.m_BufferStream.write(test);
/* 267 */           available += stream.available();
/* 268 */           if (available > 0) {
/*     */             
/* 270 */             int read = stream.read(readBuffer, 0, Math.min(available, readBuffer.length));
/* 271 */             int resp = processReadBuffer(readBuffer, read);
/* 272 */             available -= read;
/* 273 */             if (resp == 0) {
/*     */               
/* 275 */               stream.close();
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/* 280 */         if (available > 0) {
/*     */           
/* 282 */           this.m_RetryCount = 0;
/*     */           
/*     */           continue;
/*     */         } 
/* 286 */         if (this.m_RetryCount > 10) {
/*     */           int waitTime;
/*     */ 
/*     */           
/* 290 */           if (this.m_RetryCount < 20) {
/* 291 */             waitTime = 5;
/* 292 */           } else if (this.m_RetryCount < 40) {
/* 293 */             waitTime = 50;
/* 294 */           } else if (this.m_RetryCount < 50) {
/* 295 */             waitTime = 200;
/* 296 */           } else if (this.m_RetryCount < 100) {
/* 297 */             waitTime = 500;
/*     */           } else {
/* 299 */             waitTime = 2000;
/* 300 */           }  Thread.sleep(waitTime);
/*     */         } 
/* 302 */         this.m_RetryCount++;
/*     */       
/*     */       }
/* 305 */       catch (Exception e) {
/*     */         
/* 307 */         this.m_RetryCount = 1000;
/* 308 */         LbsConsole.getLogger(getClass()).debug("Report readChannelData", e);
/* 309 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int processReadBuffer(byte[] readBuffer, int length) {
/* 316 */     byte[] buffer = null;
/* 317 */     if (readBuffer != null && length > 0) {
/*     */       
/*     */       try {
/*     */         
/* 321 */         this.m_BufferStream.write(readBuffer, 0, length);
/*     */       }
/* 323 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 328 */     boolean commandProcessed = true;
/* 329 */     while (commandProcessed) {
/*     */       
/* 331 */       commandProcessed = false;
/* 332 */       if (this.m_PackageLength <= 0 && this.m_BufferStream.size() > 4)
/* 333 */         this.m_PackageLength = this.m_BufferStream.peekInt(); 
/* 334 */       if (this.m_PackageLength > 0) {
/*     */ 
/*     */         
/* 337 */         buffer = this.m_BufferStream.readBuffer(this.m_PackageLength);
/* 338 */         if (buffer != null) {
/*     */           
/* 340 */           this.m_PackageLength = 0;
/* 341 */           commandProcessed = true;
/* 342 */           boolean res = processChunk(buffer);
/* 343 */           if (!res) {
/* 344 */             return 0;
/*     */           }
/*     */           continue;
/*     */         } 
/*     */         break;
/*     */       } 
/*     */     } 
/* 351 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void resetRetryCount() {
/* 356 */     this.m_RetryCount = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void reInitialize() {
/* 361 */     this.m_ReadBuffer = null;
/* 362 */     this.m_PackageLength = 0;
/* 363 */     this.m_RetryCount = 0;
/* 364 */     this.m_BufferStream = new ByteArrayOutputStreamEx(8192);
/*     */   }
/*     */ 
/*     */   
/*     */   private int updateSeq(byte data, int seq) {
/* 369 */     switch (data) {
/*     */       
/*     */       case 75:
/* 372 */         if (seq++ == 0)
/* 373 */           return seq; 
/*     */         break;
/*     */       case 9:
/* 376 */         if (seq++ == 1)
/* 377 */           return seq; 
/*     */         break;
/*     */       case 17:
/* 380 */         if (seq++ == 2)
/* 381 */           return seq; 
/*     */         break;
/*     */       case 1:
/* 384 */         if (seq++ == 3)
/* 385 */           return seq; 
/*     */         break;
/*     */     } 
/* 388 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void installChannelListeners(IChannel channel) {
/* 394 */     super.installChannelListeners(channel);
/* 395 */     channel.setChannelReadListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean chunkDataRead(byte[] data) {
/* 400 */     if (this.m_ChunkReader != null)
/* 401 */       return this.m_ChunkReader.chunkDataRead(data); 
/* 402 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public IMultiBufferTransportClientReader getChunkReader() {
/* 407 */     return this.m_ChunkReader;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChunkReader(IMultiBufferTransportClientReader reader) {
/* 412 */     this.m_ChunkReader = reader;
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeChannel(boolean bForce) {
/* 417 */     if (this.m_ChannelProv != null)
/* 418 */       this.m_ChannelProv.closeChannel(bForce); 
/*     */   }
/*     */   
/*     */   class ByteArrayOutputStreamEx
/*     */     extends ByteArrayOutputStream
/*     */   {
/*     */     public ByteArrayOutputStreamEx(int size) {
/* 425 */       super(size);
/*     */     }
/*     */ 
/*     */     
/*     */     public byte[] readBuffer(int packageLength) {
/* 430 */       if (this.count >= packageLength + 4) {
/*     */         
/* 432 */         byte[] result = new byte[packageLength];
/* 433 */         System.arraycopy(this.buf, 4, result, 0, packageLength);
/* 434 */         int remaining = this.count - packageLength + 4;
/* 435 */         System.arraycopy(this.buf, packageLength + 4, this.buf, 0, remaining);
/* 436 */         this.count = remaining;
/* 437 */         return result;
/*     */       } 
/* 439 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public int peekInt() {
/* 444 */       return TransportUtil.byteArrayToInt(this.buf);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\MultiBufferTransportClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */