/*     */ package com.lbs.crypto;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.security.MessageDigest;
/*     */ import java.util.Random;
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
/*     */ public class JLbsEnigma
/*     */ {
/*  25 */   protected JLbsDESContext[] m_ContextEncrypt = null;
/*  26 */   protected JLbsDESContext[] m_ContextDecrypt = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsEnigma() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsEnigma(String password) throws Exception {
/*  43 */     setPassword(password);
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
/*     */   public void setPassword(String password) throws Exception {
/*  56 */     if (password == null || password.length() == 0) {
/*  57 */       setPassword(new byte[] { 17, 9, 19, 75, -70, -4, 44, 78, 22, 59, -18 });
/*     */     } else {
/*  59 */       setPassword(password.getBytes());
/*     */     } 
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
/*     */   public void setPassword(byte[] password) throws Exception {
/*  72 */     if (password == null || password.length == 0) {
/*  73 */       throw new Exception("JLbsEnigma: The password is not valid !");
/*     */     }
/*  75 */     MessageDigest md5 = MessageDigest.getInstance("MD5");
/*  76 */     byte[] md5PassData = md5.digest(password);
/*  77 */     md5.reset();
/*  78 */     byte[] md5md5PassData = md5.digest(md5PassData);
/*  79 */     byte[][] arrPassData = { new byte[8], new byte[8], new byte[8] };
/*  80 */     System.arraycopy(md5PassData, 0, arrPassData[0], 0, 8);
/*  81 */     System.arraycopy(md5PassData, 8, arrPassData[1], 0, 8);
/*  82 */     System.arraycopy(md5md5PassData, 0, arrPassData[2], 0, 8);
/*     */     
/*  84 */     this.m_ContextEncrypt = new JLbsDESContext[3];
/*  85 */     this.m_ContextDecrypt = new JLbsDESContext[3];
/*  86 */     for (int i = 0; i < 3; i++) {
/*     */       
/*  88 */       JLbsDESKey keys = new JLbsDESKey(arrPassData[i]);
/*  89 */       this.m_ContextEncrypt[i] = new JLbsDESContext(keys, true);
/*  90 */       this.m_ContextDecrypt[i] = new JLbsDESContext(keys, false);
/*     */     } 
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
/*     */   public int EncryptInBuffer(byte[] data, int dataLength) throws Exception {
/* 107 */     return internalProcessBuffer(data, dataLength, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] Encrypt(byte[] data) throws Exception {
/* 118 */     return Encrypt(data, 0, data.length);
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
/*     */   public byte[] Encrypt(byte[] data, int index, int length) throws Exception {
/* 132 */     byte[] newData = new byte[length + 8];
/* 133 */     System.arraycopy(data, index, newData, 0, length);
/* 134 */     int iRetLength = internalProcessBuffer(newData, length, true);
/* 135 */     if (iRetLength > 0) {
/*     */       
/* 137 */       if (iRetLength == length + 8)
/* 138 */         return newData; 
/* 139 */       byte[] result = new byte[iRetLength];
/* 140 */       System.arraycopy(newData, 0, result, 0, iRetLength);
/* 141 */       return result;
/*     */     } 
/* 143 */     return null;
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
/*     */   public int DecryptInBuffer(byte[] data, int dataLength) throws Exception {
/* 156 */     return internalProcessBuffer(data, dataLength, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] Decrypt(byte[] data) throws Exception {
/* 167 */     return Decrypt(data, 0, data.length);
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
/*     */   public byte[] Decrypt(byte[] data, int index, int length) throws Exception {
/* 181 */     byte[] newData = new byte[length];
/* 182 */     System.arraycopy(data, index, newData, 0, length);
/* 183 */     int iRetLength = internalProcessBuffer(newData, length, false);
/* 184 */     if (iRetLength > 0) {
/*     */       
/* 186 */       if (iRetLength == length + 8)
/* 187 */         return newData; 
/* 188 */       byte[] result = new byte[iRetLength];
/* 189 */       System.arraycopy(newData, 0, result, 0, iRetLength);
/* 190 */       return result;
/*     */     } 
/* 192 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public File decryptToTemp(InputStream is, int chunkSize) throws Exception {
/* 197 */     File result = null;
/* 198 */     File file = File.createTempFile("dcr", null);
/* 199 */     FileOutputStream os = null;
/*     */     
/*     */     try {
/* 202 */       os = new FileOutputStream(file);
/* 203 */       byte[] buffer = new byte[chunkSize];
/*     */       int read;
/* 205 */       while ((read = is.read(buffer, 0, buffer.length)) > 0) {
/*     */         
/* 207 */         byte[] processed = Decrypt(buffer, 0, read);
/* 208 */         os.write(processed, 0, processed.length);
/*     */       } 
/* 210 */       result = file;
/*     */     }
/*     */     finally {
/*     */       
/* 214 */       if (os != null)
/* 215 */         os.close(); 
/* 216 */       if (file != null && result == null && !file.delete())
/* 217 */         file.deleteOnExit(); 
/*     */     } 
/* 219 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int internalProcessBuffer(byte[] data, int dataLength, boolean bEncrypt) throws Exception {
/* 226 */     if (data == null || dataLength <= 0)
/* 227 */       return 0; 
/* 228 */     if (this.m_ContextEncrypt == null || this.m_ContextDecrypt == null)
/* 229 */       throw new Exception("JLbsEnigma: Password is not set !"); 
/* 230 */     int iBlockCount = dataLength >>> 3;
/* 231 */     int iBlockRemain = dataLength & 0x7;
/* 232 */     if (iBlockRemain > 0 && !bEncrypt)
/* 233 */       throw new Exception("JLbsEnigma: Invalid decrypt data size !"); 
/* 234 */     int iLocalBufferSize = iBlockCount << 3;
/* 235 */     if (bEncrypt)
/* 236 */       iLocalBufferSize += 8; 
/* 237 */     if (data.length < iLocalBufferSize) {
/* 238 */       throw new Exception("JLbsEnigma: Enigma requires more data buffer than the given one!");
/*     */     }
/* 240 */     JLbsDES des = new JLbsDES();
/* 241 */     if (bEncrypt) {
/*     */       
/* 243 */       int j = iLocalBufferSize - 8;
/* 244 */       Random random = new Random(System.currentTimeMillis()); int k;
/* 245 */       for (k = iBlockRemain; k < 7; k++)
/* 246 */         data[j + k] = (byte)random.nextInt(); 
/* 247 */       data[j + 7] = (byte)iBlockRemain;
/* 248 */       j = 0;
/* 249 */       for (k = 0; k <= iBlockCount; k++) {
/*     */         
/* 251 */         des.Encrypt(this.m_ContextEncrypt[0], data, j);
/* 252 */         des.Encrypt(this.m_ContextDecrypt[1], data, j);
/* 253 */         des.Encrypt(this.m_ContextEncrypt[2], data, j);
/* 254 */         j += 8;
/*     */       } 
/* 256 */       return iLocalBufferSize;
/*     */     } 
/*     */ 
/*     */     
/* 260 */     int iBufferIndex = 0;
/* 261 */     for (int i = 0; i < iBlockCount; i++) {
/*     */       
/* 263 */       des.Encrypt(this.m_ContextDecrypt[2], data, iBufferIndex);
/* 264 */       des.Encrypt(this.m_ContextEncrypt[1], data, iBufferIndex);
/* 265 */       des.Encrypt(this.m_ContextDecrypt[0], data, iBufferIndex);
/* 266 */       iBufferIndex += 8;
/*     */     } 
/* 268 */     int iLastBlockLen = data[iLocalBufferSize - 1] & 0x7;
/* 269 */     return iLocalBufferSize - 8 - iLastBlockLen;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 275 */     if (args.length != 3) {
/*     */       
/* 277 */       System.out.println("Invalid arguments");
/* 278 */       System.out.println("1. Argument is type. 1=Encrype, 2=Decrype");
/* 279 */       System.out.println("2. Argument is password.");
/* 280 */       System.out.println("3. Argument is value.");
/*     */       return;
/*     */     } 
/* 283 */     String type = args[0];
/* 284 */     String pass = args[1];
/* 285 */     String value = args[2];
/*     */ 
/*     */     
/*     */     try {
/* 289 */       JLbsEnigma cryptor = new JLbsEnigma();
/* 290 */       cryptor.setPassword(pass);
/* 291 */       if (type.equals("1"))
/*     */       {
/* 293 */         byte[] val = cryptor.Encrypt(value.getBytes("UTF-8"));
/* 294 */         System.out.println(JLbsBase64.encode(val));
/*     */       }
/* 296 */       else if (type.equals("2"))
/*     */       {
/* 298 */         byte[] val = JLbsBase64.decode(value);
/* 299 */         val = cryptor.Decrypt(val);
/* 300 */         System.out.println(new String(val, "UTF-8"));
/*     */       }
/*     */     
/* 303 */     } catch (Exception e) {
/*     */       
/* 305 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\crypto\JLbsEnigma.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */