/*     */ package com.lbs.transport;
/*     */ 
/*     */ import com.lbs.channel.ChannelListener;
/*     */ import com.lbs.channel.IChannel;
/*     */ import com.lbs.channel.IChannelListener;
/*     */ import com.lbs.channel.IChannelProvider;
/*     */ import com.lbs.channel.ICryptor;
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.util.ArrayUtil;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransportClient
/*     */   extends ChannelListener
/*     */ {
/*  22 */   private static final LbsConsole ms_Logger = LbsConsole.getLogger("Transport.TransClient");
/*     */   
/*     */   protected IChannelProvider m_ChannelProv;
/*     */   
/*     */   protected ISessionBase m_Session;
/*     */   protected ICryptor m_LocalCryptor;
/*     */   protected ICryptor m_RemoteCryptor;
/*     */   protected byte[] m_ReturnData;
/*     */   protected TransportClientRecorder recorder;
/*     */   
/*     */   public TransportClient(IChannelProvider channelProv, ISessionBase session, ICryptor localCryptor, ICryptor remoteCryptor) {
/*  33 */     this.m_ChannelProv = channelProv;
/*  34 */     this.m_Session = session;
/*  35 */     this.m_LocalCryptor = localCryptor;
/*  36 */     this.m_RemoteCryptor = remoteCryptor;
/*     */   }
/*     */ 
/*     */   
/*     */   public TransportClientRecorder getRecorder() {
/*  41 */     return this.recorder;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRecorder(TransportClientRecorder recorder) {
/*  46 */     this.recorder = recorder;
/*     */   }
/*     */ 
/*     */   
/*     */   public TransportClient duplicate() {
/*  51 */     TransportClient cand = new TransportClient(this.m_ChannelProv, this.m_Session, this.m_LocalCryptor, this.m_RemoteCryptor);
/*  52 */     cand.setRecorder(this.recorder);
/*  53 */     return cand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] postData(String sUri, byte[] sendPrefix, byte[] sendData, boolean bPublicInfo) {
/*  60 */     this.m_ReturnData = null;
/*     */     
/*     */     try {
/*  63 */       byte[] orgData = (this.recorder != null) ? 
/*  64 */         sendData : 
/*  65 */         null;
/*  66 */       byte[] encData = (this.m_RemoteCryptor != null && sendData != null) ? 
/*  67 */         this.m_RemoteCryptor.encrypt(sendData, 0, sendData.length) : 
/*  68 */         sendData;
/*  69 */       IChannel channel = this.m_ChannelProv.createChannel();
/*  70 */       if (channel != null) {
/*     */         
/*  72 */         installChannelListeners(channel);
/*  73 */         this.m_ReturnData = null;
/*  74 */         synchronized (channel) {
/*     */           
/*  76 */           if (channel.open(sUri, this.m_Session))
/*     */           {
/*  78 */             ByteArrayOutputStream stream = null;
/*  79 */             if (this.m_Session != null) {
/*     */               
/*  81 */               stream = new ByteArrayOutputStream();
/*  82 */               String s = this.m_Session.getSessionCode();
/*  83 */               if (s != null && s.length() > 0) {
/*     */                 
/*  85 */                 String sessionStr = String.valueOf(s) + "\n";
/*  86 */                 byte[] bytes = sessionStr.getBytes();
/*  87 */                 stream.write(bytes);
/*     */               } 
/*     */             } 
/*  90 */             if (sendPrefix != null && sendPrefix.length > 0) {
/*     */               
/*  92 */               if (stream == null)
/*  93 */                 stream = new ByteArrayOutputStream(); 
/*  94 */               stream.write(sendPrefix);
/*     */             } 
/*  96 */             if (stream == null) {
/*  97 */               channel.sendData(encData, 0, encData.length, true);
/*     */             } else {
/*     */               
/* 100 */               stream.write(encData);
/* 101 */               byte[] streamData = stream.toByteArray();
/* 102 */               stream.close();
/* 103 */               channel.sendData(streamData, 0, streamData.length, true);
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/* 108 */             if (this.m_ReturnData != null && this.m_ReturnData.length > 0) {
/*     */               
/* 110 */               if (plainData(this.m_ReturnData))
/* 111 */                 return ArrayUtil.subArray(this.m_ReturnData, 4, this.m_ReturnData.length - 4); 
/* 112 */               if (this.m_LocalCryptor != null)
/* 113 */                 return this.m_LocalCryptor.decrypt(this.m_ReturnData, 0, this.m_ReturnData.length); 
/*     */             } 
/* 115 */             if (this.recorder != null)
/* 116 */               this.recorder.dataSent(sUri, sendPrefix, orgData, bPublicInfo, this.m_ReturnData); 
/* 117 */             processTransportedData(this.m_ReturnData);
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 122 */     } catch (Exception ex) {
/*     */       
/* 124 */       ms_Logger.error("postData:" + ex, ex);
/*     */     } 
/* 126 */     return this.m_ReturnData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized byte[] postData(String sUri, byte[] sendPrefix, byte[] sendData, boolean bPublicInfo, boolean isClass) {
/* 132 */     this.m_ReturnData = null;
/*     */     
/*     */     try {
/* 135 */       byte[] encData = (this.m_RemoteCryptor != null && sendData != null) ? 
/* 136 */         this.m_RemoteCryptor.encrypt(sendData, 0, sendData.length) : 
/* 137 */         sendData;
/* 138 */       IChannel channel = this.m_ChannelProv.createChannel();
/* 139 */       if (channel != null)
/*     */       {
/* 141 */         installChannelListeners(channel);
/* 142 */         this.m_ReturnData = null;
/* 143 */         if (channel.open(sUri, this.m_Session))
/*     */         {
/* 145 */           ByteArrayOutputStream stream = null;
/* 146 */           if (this.m_Session != null) {
/*     */             
/* 148 */             stream = new ByteArrayOutputStream();
/* 149 */             String s = this.m_Session.getSessionCode();
/* 150 */             if (s != null && s.length() > 0) {
/*     */               
/* 152 */               String sessionStr = String.valueOf(s) + "\n";
/* 153 */               byte[] bytes = sessionStr.getBytes();
/* 154 */               stream.write(bytes);
/*     */             } 
/*     */           } 
/* 157 */           if (sendPrefix != null && sendPrefix.length > 0) {
/*     */             
/* 159 */             if (stream == null)
/* 160 */               stream = new ByteArrayOutputStream(); 
/* 161 */             stream.write(sendPrefix);
/*     */           } 
/* 163 */           if (stream == null) {
/* 164 */             channel.sendData(encData, 0, encData.length, true);
/*     */           } else {
/*     */             
/* 167 */             stream.write(encData);
/* 168 */             byte[] streamData = stream.toByteArray();
/* 169 */             stream.close();
/* 170 */             channel.sendData(streamData, 0, streamData.length, true);
/*     */ 
/*     */ 
/*     */           
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 188 */     catch (Exception ex) {
/*     */       
/* 190 */       ms_Logger.error("postData:" + ex, ex);
/*     */     } 
/* 192 */     return this.m_ReturnData;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized byte[] getData(String sUri) {
/* 197 */     this.m_ReturnData = null;
/*     */     
/*     */     try {
/* 200 */       IChannel channel = this.m_ChannelProv.createChannel();
/* 201 */       if (channel != null) {
/*     */         
/* 203 */         channel.setListener((IChannelListener)this);
/* 204 */         this.m_ReturnData = null;
/* 205 */         if (channel.open(sUri, this.m_Session)) {
/* 206 */           channel.getData();
/*     */         }
/*     */       } 
/* 209 */     } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */     
/* 213 */     byte[] returnData = this.m_ReturnData;
/* 214 */     this.m_ReturnData = null;
/* 215 */     return returnData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean channelDataRead(IChannel channel, byte[] data) {
/* 226 */     this.m_ReturnData = data;
/* 227 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processTransportedData(byte[] data) {}
/*     */ 
/*     */   
/*     */   protected void installChannelListeners(IChannel channel) {
/* 236 */     channel.setListener((IChannelListener)this);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean plainData(byte[] data) {
/* 241 */     if (data != null && data.length > 4) {
/*     */       
/* 243 */       byte[] signature = TransportUtil.PLAINSIGNATURE;
/* 244 */       return (data[0] == signature[0] && data[1] == signature[1] && data[2] == signature[2] && data[3] == signature[3]);
/*     */     } 
/* 246 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\TransportClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */