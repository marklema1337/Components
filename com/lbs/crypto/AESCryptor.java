/*     */ package com.lbs.crypto;
/*     */ 
/*     */ import com.lbs.transport.CryptorException;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.security.MessageDigest;
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
/*     */ public class AESCryptor
/*     */   extends SymetricCryptorBase
/*     */ {
/*     */   private Object m_Key;
/*     */   
/*     */   public AESCryptor() {}
/*     */   
/*     */   public AESCryptor(Object privateKey) {
/*  32 */     this();
/*  33 */     setParam(0, privateKey);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] decrypt(byte[] data, int index, int length) throws CryptorException {
/*  40 */     if (this.m_Key == null) {
/*  41 */       throw new CryptorException("The encryption key is not set!");
/*     */     }
/*     */     try {
/*  44 */       int blockCount = length / 16;
/*  45 */       if (blockCount <= 0)
/*  46 */         return null; 
/*  47 */       if (length % 16 != 0)
/*  48 */         throw new CryptorException("The data is not aligned properly!"); 
/*  49 */       ByteArrayInputStream stream = new ByteArrayInputStream(data, index, length);
/*  50 */       ByteArrayOutputStream outStream = new ByteArrayOutputStream(length);
/*  51 */       byte[] buffer = new byte[16];
/*  52 */       buffer[15] = 0;
/*  53 */       stream.read(buffer, 0, buffer.length);
/*     */       
/*  55 */       for (int i = 1; i < blockCount; i++) {
/*     */         
/*  57 */         byte[] arrayOfByte = JLbsAES.blockDecrypt(buffer, 0, this.m_Key);
/*  58 */         outStream.write(arrayOfByte);
/*  59 */         stream.read(buffer, 0, buffer.length);
/*     */       } 
/*  61 */       byte[] block = JLbsAES.blockDecrypt(buffer, 0, this.m_Key);
/*  62 */       int len = block[15];
/*  63 */       if (len > 0)
/*  64 */         outStream.write(block, 0, len); 
/*  65 */       return outStream.toByteArray();
/*     */     }
/*  67 */     catch (Exception e) {
/*     */       
/*  69 */       throw new CryptorException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] encrypt(byte[] data, int index, int length) throws CryptorException {
/*  77 */     if (this.m_Key == null) {
/*  78 */       throw new CryptorException("The encryption key is not set!");
/*     */     }
/*     */     try {
/*  81 */       ByteArrayInputStream stream = new ByteArrayInputStream(data, index, length);
/*  82 */       ByteArrayOutputStream outStream = new ByteArrayOutputStream(length + 32);
/*  83 */       byte[] buffer = new byte[16];
/*  84 */       int read = stream.read(buffer, 0, buffer.length);
/*  85 */       while (read == 16) {
/*     */         
/*  87 */         byte[] arrayOfByte = JLbsAES.blockEncrypt(buffer, 0, this.m_Key);
/*  88 */         outStream.write(arrayOfByte);
/*  89 */         read = stream.read(buffer, 0, buffer.length);
/*     */       } 
/*  91 */       buffer[15] = (byte)read;
/*  92 */       byte[] block = JLbsAES.blockEncrypt(buffer, 0, this.m_Key);
/*  93 */       outStream.write(block);
/*  94 */       return outStream.toByteArray();
/*     */     }
/*  96 */     catch (Exception e) {
/*     */       
/*  98 */       throw new CryptorException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setPassword(byte[] pass) {
/* 105 */     if (pass != null && pass.length > 0) {
/*     */       
/*     */       try {
/* 108 */         MessageDigest md5 = MessageDigest.getInstance("MD5");
/* 109 */         byte[] md5PassData = md5.digest(pass);
/* 110 */         md5.reset();
/* 111 */         byte[] md5md5PassData = md5.digest(md5PassData);
/* 112 */         byte[] key = new byte[24];
/* 113 */         System.arraycopy(md5PassData, 0, key, 0, 16);
/* 114 */         System.arraycopy(md5md5PassData, 0, key, 16, key.length - 16);
/* 115 */         this.m_Key = JLbsAES.makeKey(key);
/*     */         
/*     */         return;
/* 118 */       } catch (Exception exception) {}
/*     */     }
/*     */     
/* 121 */     this.m_Key = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setPassword(String pass) {
/* 127 */     (new byte[6])[0] = 17; (new byte[6])[1] = 9; (new byte[6])[2] = 75; (new byte[6])[3] = 29; (new byte[6])[4] = 20; (new byte[6])[5] = 4; setPassword((pass != null) ? pass.getBytes() : new byte[6]);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\crypto\AESCryptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */