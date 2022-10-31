/*     */ package com.lbs.transport;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.rmi.JLbsHttpInvoker;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import java.security.SecureRandom;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransportUtil
/*     */ {
/*     */   public static final int STREAMSIGNATURE = -1161908224;
/*  34 */   public static final byte[] PLAINSIGNATURE = new byte[] { -17 };
/*     */ 
/*     */   
/*     */   public static final int MAX_BUFFER_SIZE = 2048;
/*     */ 
/*     */   
/*     */   public static boolean ms_SpringBoot = false;
/*     */ 
/*     */   
/*  43 */   private static SecureRandom ms_Random = new SecureRandom(); static {
/*  44 */     ms_Random.setSeed(ms_Random.generateSeed(16));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] intToByteArray(int value) {
/*  53 */     byte[] buffer = new byte[4];
/*     */     
/*  55 */     buffer[0] = (byte)value;
/*  56 */     buffer[1] = (byte)(value >> 8);
/*  57 */     buffer[2] = (byte)(value >> 16);
/*  58 */     buffer[3] = (byte)(value >> 24);
/*  59 */     return buffer;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int byteArrayToInt(byte[] buffer) {
/*  64 */     return (buffer[3] & 0xFF) << 24 | (buffer[2] & 0xFF) << 16 | (buffer[1] & 0xFF) << 8 | buffer[0] & 0xFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] readStream(InputStream stream, int iBuffSize) {
/*  69 */     if (stream == null) {
/*  70 */       return null;
/*     */     }
/*     */     try {
/*  73 */       int iBufferLen = Math.min((iBuffSize > 0) ? 
/*  74 */           iBuffSize : 
/*  75 */           2048, 2048);
/*  76 */       byte[] dataBuffer = new byte[iBufferLen];
/*  77 */       ByteArrayOutputStream byteStream = new ByteArrayOutputStream(iBufferLen);
/*  78 */       int retryCount = 0;
/*  79 */       for (int iReadSize = 0; iReadSize > 0 || retryCount < 5; ) {
/*     */         
/*  81 */         iReadSize = stream.read(dataBuffer, 0, iBufferLen);
/*  82 */         if (iReadSize > 0) {
/*     */           
/*  84 */           byteStream.write(dataBuffer, 0, iReadSize);
/*  85 */           if (iReadSize >= iBufferLen) {
/*  86 */             retryCount = 0;
/*     */           }
/*     */           continue;
/*     */         } 
/*  90 */         retryCount++;
/*  91 */         if (retryCount > 2 && !(stream instanceof java.util.zip.GZIPInputStream)) {
/*  92 */           Thread.sleep(1L);
/*     */         }
/*     */       } 
/*  95 */       byte[] readData = byteStream.toByteArray();
/*  96 */       byteStream.close();
/*  97 */       return readData;
/*     */     }
/*  99 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 102 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static byte[] serializeObjectPlain(Object obj) throws IOException {
/* 107 */     byte[] data = null;
/* 108 */     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/* 109 */     ObjectOutputStream objStream = new ObjectOutputStream(outStream);
/*     */     
/*     */     try {
/* 112 */       objStream.writeObject(obj);
/* 113 */       data = outStream.toByteArray();
/*     */     }
/* 115 */     catch (EOFException eOFException) {
/*     */ 
/*     */     
/*     */     } finally {
/*     */       
/* 120 */       objStream.close();
/* 121 */       outStream.close();
/*     */     } 
/* 123 */     return data;
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] serializeObject(Object obj) throws Exception {
/* 128 */     return serializeObject(obj, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] serializeObject(Object obj, ClassLoader clsLoader) throws Exception {
/* 133 */     Object[] objects = new Object[1];
/* 134 */     objects[0] = obj;
/* 135 */     return serializeObjects(objects, clsLoader);
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] serializeObjects(Object[] objects) throws Exception {
/* 140 */     return serializeObjects(objects, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] serializeObjects(Object[] objects, ClassLoader clsLoader) throws Exception {
/* 145 */     ArrayList<Object> streamList = new ArrayList();
/* 146 */     byte[] result = null;
/*     */     
/*     */     try {
/* 149 */       for (int i = 0; i < objects.length; i++) {
/*     */         
/* 151 */         Object obj = objects[i];
/* 152 */         if (obj != null) {
/*     */           
/* 154 */           if (obj instanceof byte[]) {
/* 155 */             streamList.add(obj);
/*     */           } else {
/*     */             
/* 158 */             ByteArrayOutputStream stream = new ByteArrayOutputStream();
/* 159 */             ObjectOutputStream outStream = new ObjectOutputStream(stream);
/* 160 */             outStream.writeObject(obj);
/* 161 */             streamList.add(stream);
/*     */           } 
/*     */         } else {
/*     */           
/* 165 */           streamList.add(null);
/*     */         } 
/* 167 */       }  ByteArrayOutputStream sumStream = new ByteArrayOutputStream();
/* 168 */       DataOutputStream sumOutStream = new DataOutputStream(sumStream);
/* 169 */       sumOutStream.writeInt(-1161908224);
/* 170 */       for (Iterator it = streamList.iterator(); it.hasNext(); ) {
/*     */         
/* 172 */         Object o = it.next();
/* 173 */         if (o != null) {
/*     */           
/* 175 */           if (o instanceof byte[]) {
/*     */             
/* 177 */             byte[] byteArray = (byte[])o;
/* 178 */             sumOutStream.writeInt(0x40000000 | byteArray.length);
/* 179 */             sumOutStream.write(byteArray);
/*     */           }
/*     */           else {
/*     */             
/* 183 */             ByteArrayOutputStream objStream = (ByteArrayOutputStream)o;
/* 184 */             byte[] byteArray = objStream.toByteArray();
/* 185 */             sumOutStream.writeInt(byteArray.length);
/* 186 */             sumOutStream.write(byteArray);
/* 187 */             objStream.close();
/*     */           } 
/*     */         } else {
/*     */           
/* 191 */           sumOutStream.writeInt(0);
/* 192 */         }  result = sumStream.toByteArray();
/* 193 */         sumStream.close();
/* 194 */         sumOutStream.close();
/*     */       } 
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 201 */         for (Iterator it = streamList.iterator(); it.hasNext(); ) {
/*     */           
/* 203 */           Object o = it.next();
/* 204 */           if (o != null) {
/* 205 */             ((ByteArrayOutputStream)o).close();
/*     */           }
/*     */         } 
/* 208 */       } catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */     
/* 212 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object deserializeCompressedObjectPlain(byte[] data) throws Exception {
/*     */     try {
/* 219 */       StdCompressor comp = new StdCompressor();
/* 220 */       data = comp.uncompress(data, 0, data.length);
/*     */     }
/* 222 */     catch (Exception exception) {}
/*     */ 
/*     */     
/* 225 */     return deserializeObjectPlain(null, data);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object deserializeObject(byte[] data) throws Exception {
/* 230 */     return deserializeObject(null, data);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object deserializeObject(ClassLoader clsLoader, byte[] data) throws Exception {
/* 235 */     Object[] objects = deserializeObjects(clsLoader, data);
/* 236 */     if (objects != null && objects.length == 1)
/* 237 */       return objects[0]; 
/* 238 */     return objects;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object[] deserializeObjects(byte[] data) throws Exception {
/* 243 */     return deserializeObjects(null, data);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object[] deserializeObjects(ClassLoader clsLoader, byte[] data) throws Exception {
/* 248 */     if (data == null)
/* 249 */       return null; 
/* 250 */     ArrayList<Object> list = new ArrayList();
/* 251 */     ByteArrayInputStream inStream = new ByteArrayInputStream(data);
/* 252 */     DataInputStream objStream = new DataInputStream(inStream);
/*     */     
/*     */     try {
/* 255 */       int iSignature = objStream.readInt();
/* 256 */       if (iSignature != -1161908224 || data.length < 8)
/* 257 */         throw new Exception("The given data has a different signature!");  while (true) {
/*     */         byte[] objData, object; ByteArrayInputStream localByteStream; ObjectInputStreamWithLoader localStream;
/*     */         Object object1;
/* 260 */         int iReadLength = objStream.readInt();
/* 261 */         if (iReadLength == 0) {
/* 262 */           list.add(null);
/*     */           continue;
/*     */         } 
/* 265 */         int iDataLength = iReadLength & 0x3FFFFFFF;
/* 266 */         switch (iReadLength >> 30) {
/*     */ 
/*     */           
/*     */           case 0:
/* 270 */             objData = new byte[iDataLength];
/* 271 */             objStream.read(objData, 0, objData.length);
/* 272 */             localByteStream = new ByteArrayInputStream(objData);
/* 273 */             localStream = new ObjectInputStreamWithLoader(localByteStream, clsLoader);
/* 274 */             object1 = localStream.readObject();
/* 275 */             list.add(object1);
/* 276 */             localStream.close();
/* 277 */             localByteStream.close();
/*     */             continue;
/*     */ 
/*     */           
/*     */           case 1:
/* 282 */             object = new byte[iDataLength];
/* 283 */             objStream.read(object, 0, object.length);
/* 284 */             list.add(object); continue;
/*     */         } 
/*     */         break;
/*     */       } 
/* 288 */       throw new Exception("The given data has a invalid object signature!");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 294 */     catch (EOFException eOFException) {
/*     */ 
/*     */       
/* 297 */       objStream.close();
/* 298 */       return (list.size() == 0) ? 
/* 299 */         null : 
/* 300 */         list.toArray();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object deserializeObjectPlain(byte[] data) throws IOException, ClassNotFoundException {
/* 305 */     return deserializeObjectPlain(null, data);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object deserializeObjectPlain(ClassLoader clsLoader, byte[] data) throws IOException, ClassNotFoundException {
/* 310 */     ByteArrayInputStream inStream = new ByteArrayInputStream(data);
/* 311 */     DataInputStream objStream = new DataInputStream(inStream);
/* 312 */     Object object = null;
/*     */     
/*     */     try {
/* 315 */       ObjectInputStreamWithLoader localStream = new ObjectInputStreamWithLoader(objStream, clsLoader);
/* 316 */       object = localStream.readObject();
/*     */     }
/* 318 */     catch (EOFException eOFException) {
/*     */ 
/*     */     
/*     */     } finally {
/*     */       
/* 323 */       objStream.close();
/* 324 */       inStream.close();
/*     */     } 
/* 326 */     return object;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] getRandomKey(int iLength) {
/* 332 */     return JLbsHttpInvoker.getRandomKey();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dumpBinary(Writer writer, byte[] data) {
/*     */     try {
/* 372 */       if (data == null) {
/* 373 */         writer.write("null");
/* 374 */       } else if (data.length == 0) {
/* 375 */         writer.write("(empty)");
/*     */       } else {
/*     */         
/* 378 */         for (int i = 0; i < data.length; i++)
/*     */         {
/* 380 */           byte b = data[i];
/* 381 */           String s = Integer.toHexString(b);
/* 382 */           while (s.length() < 2)
/* 383 */             s = "0" + s; 
/* 384 */           writer.write(s);
/*     */         }
/*     */       
/*     */       } 
/* 388 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dumpBinary(OutputStream stream, byte[] data) {
/*     */     try {
/* 397 */       if (data == null) {
/* 398 */         stream.write("null".getBytes());
/* 399 */       } else if (data.length == 0) {
/* 400 */         stream.write("(empty)".getBytes());
/*     */       } else {
/*     */         
/* 403 */         for (int i = 0; i < data.length; i++)
/*     */         {
/* 405 */           byte b = data[i];
/* 406 */           String s = Integer.toHexString(b);
/* 407 */           while (s.length() < 2)
/* 408 */             s = "0" + s; 
/* 409 */           stream.write(s.getBytes());
/*     */         }
/*     */       
/*     */       } 
/* 413 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dumpString(OutputStream stream, byte[] data) {
/*     */     try {
/* 422 */       if (data == null) {
/* 423 */         stream.write("null".getBytes());
/* 424 */       } else if (data.length == 0) {
/* 425 */         stream.write("(empty)".getBytes());
/*     */       } else {
/*     */         
/* 428 */         String s = new String(data);
/* 429 */         stream.write(s.getBytes());
/*     */       }
/*     */     
/* 432 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String dumpToString(byte[] data) {
/*     */     try {
/* 441 */       if (data == null)
/* 442 */         return "null"; 
/* 443 */       if (data.length == 0) {
/* 444 */         return "(empty)";
/*     */       }
/*     */       
/* 447 */       return new String(data);
/*     */     
/*     */     }
/* 450 */     catch (Exception e) {
/*     */       
/* 452 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object deserializeRemoteObject(byte[] data) {
/* 458 */     return deserializeRemoteObject(data, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object deserializeRemoteObject(byte[] data, ClassLoader clsLoader) {
/*     */     try {
/* 465 */       Object o = deserializeObject(clsLoader, data);
/* 466 */       if (o instanceof RemoteResponse) {
/*     */         
/* 468 */         RemoteResponse remResp = (RemoteResponse)o;
/* 469 */         if (remResp.ErrorCode == 0)
/* 470 */           return remResp.Result; 
/*     */       } 
/* 472 */       return o;
/*     */     }
/* 474 */     catch (Exception e) {
/*     */       
/* 476 */       LbsConsole.getLogger("Transport.TransUtil").error("Deserialize Exception:" + e, e);
/*     */ 
/*     */       
/* 479 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] uncompress(byte[] data) {
/*     */     try {
/* 486 */       if (data != null)
/*     */       {
/* 488 */         StdCompressor comp = new StdCompressor();
/* 489 */         return comp.uncompress(data, 0, data.length);
/*     */       }
/*     */     
/* 492 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 495 */     return data;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkFormDataUseful(String fileName, byte[] data) {
/*     */     try {
/* 502 */       if (data != null && !JLbsStringUtil.isEmpty(fileName) && fileName.endsWith(".jfm") && data.length < 100 && 
/* 503 */         deserializeObjectPlain(data) == null) {
/* 504 */         return false;
/*     */       }
/* 506 */     } catch (Exception e) {
/*     */       
/* 508 */       return false;
/*     */     } 
/* 510 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] compress(byte[] data) {
/*     */     try {
/* 517 */       if (data != null)
/*     */       {
/* 519 */         StdCompressor comp = new StdCompressor();
/* 520 */         return comp.compress(data, 0, data.length);
/*     */       }
/*     */     
/* 523 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 526 */     return data;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object cloneObject(Object o) {
/* 531 */     return cloneObject(null, o);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object cloneObject(ClassLoader clsLoader, Object o) {
/* 536 */     if (o != null) {
/*     */       
/*     */       try {
/* 539 */         return deserializeObjectPlain(clsLoader, serializeObjectPlain(o));
/*     */       }
/* 541 */       catch (Exception e) {
/*     */         
/* 543 */         LbsConsole.getLogger("Transport.TransUtil").error("cloneObject: " + e, e);
/*     */       } 
/*     */     }
/*     */     
/* 547 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object[] cloneObjects(Object[] objects) {
/* 552 */     return cloneObjects(null, objects);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object[] cloneObjects(ClassLoader clsLoader, Object[] objects) {
/* 557 */     Object[] cloneObjects = null;
/* 558 */     if (objects != null) {
/*     */       
/* 560 */       cloneObjects = new Object[objects.length];
/* 561 */       for (int i = 0; i < objects.length; i++)
/*     */       {
/* 563 */         cloneObjects[i] = cloneObject(clsLoader, objects[i]);
/*     */       }
/*     */     } 
/* 566 */     return cloneObjects;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\TransportUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */