/*     */ package com.lbs.rmi.test;
/*     */ 
/*     */ import com.sun.crypto.provider.SunJCE;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.Provider;
/*     */ import java.security.Security;
/*     */ import java.security.spec.InvalidKeySpecException;
/*     */ import java.util.Arrays;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.CipherOutputStream;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.KeyGenerator;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.SecretKeyFactory;
/*     */ import javax.crypto.spec.DESedeKeySpec;
/*     */ import javax.crypto.spec.SecretKeySpec;
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
/*     */ public class AES
/*     */ {
/*     */   public static void main(String[] args) {
/*     */     try {
/*  50 */       testAES();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/*  56 */         Cipher.getInstance("AES");
/*     */       }
/*  58 */       catch (Exception e) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  64 */         System.err.println("Installing SunJCE provider.");
/*  65 */         Provider sunjce = new SunJCE();
/*  66 */         Security.addProvider(sunjce);
/*     */       } 
/*     */ 
/*     */       
/*  70 */       File keyfile = new File(args[1]);
/*     */ 
/*     */       
/*  73 */       if (args[0].equals("-g"))
/*     */       {
/*  75 */         System.out.print("Generating key. This may take some time...");
/*  76 */         System.out.flush();
/*  77 */         SecretKey key = generateKey();
/*  78 */         writeKey(key, keyfile);
/*  79 */         System.out.println("done.");
/*  80 */         System.out.println("Secret key written to " + args[1] + ". Protect that file carefully!");
/*     */       }
/*  82 */       else if (args[0].equals("-e"))
/*     */       {
/*  84 */         SecretKey key = readKey(keyfile);
/*  85 */         encrypt(key, System.in, System.out);
/*     */       }
/*  87 */       else if (args[0].equals("-d"))
/*     */       {
/*  89 */         SecretKey key = readKey(keyfile);
/*  90 */         decrypt(key, System.in, System.out);
/*     */       }
/*     */     
/*  93 */     } catch (Exception e) {
/*     */       
/*  95 */       System.err.println(e);
/*  96 */       System.err.println("Usage: java " + AES.class.getName() + " -d|-e|-g <keyfile>");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SecretKey generateKey() throws NoSuchAlgorithmException {
/* 104 */     KeyGenerator keygen = KeyGenerator.getInstance("AES");
/*     */     
/* 106 */     return keygen.generateKey();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeKey(SecretKey key, File f) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
/* 114 */     System.out.println("\n" + key.getAlgorithm() + " - " + Arrays.toString(key.getEncoded()));
/*     */     
/* 116 */     SecretKeySpec keyspec = new SecretKeySpec(key.getEncoded(), "AES");
/* 117 */     byte[] rawkey = keyspec.getEncoded();
/*     */ 
/*     */     
/* 120 */     System.out.println("\n" + keyspec.getAlgorithm() + " - " + Arrays.toString(keyspec.getEncoded()));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     FileOutputStream out = null;
/*     */     try {
/* 127 */       out = new FileOutputStream(f);
/* 128 */       out.write(rawkey);
/*     */     }
/* 130 */     catch (IOException e) {
/* 131 */       throw e;
/*     */     } finally {
/*     */       
/* 134 */       if (out != null) {
/* 135 */         out.close();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SecretKey readKey(File f) throws IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
/* 144 */     DataInputStream in = new DataInputStream(new FileInputStream(f));
/* 145 */     byte[] rawkey = new byte[(int)f.length()];
/* 146 */     in.readFully(rawkey);
/* 147 */     in.close();
/*     */ 
/*     */     
/* 150 */     DESedeKeySpec keyspec = new DESedeKeySpec(rawkey);
/* 151 */     SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("AES");
/* 152 */     SecretKey key = keyfactory.generateSecret(keyspec);
/* 153 */     return key;
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
/*     */   public static void encrypt(SecretKey key, InputStream in, OutputStream out) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IOException {
/* 165 */     Cipher cipher = Cipher.getInstance("AES");
/* 166 */     cipher.init(1, key);
/*     */ 
/*     */     
/* 169 */     CipherOutputStream cos = new CipherOutputStream(out, cipher);
/*     */ 
/*     */     
/* 172 */     byte[] buffer = new byte[2048];
/*     */     int bytesRead;
/* 174 */     while ((bytesRead = in.read(buffer)) != -1)
/*     */     {
/* 176 */       cos.write(buffer, 0, bytesRead);
/*     */     }
/* 178 */     cos.close();
/*     */ 
/*     */     
/* 181 */     Arrays.fill(buffer, (byte)0);
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
/*     */   public static void decrypt(SecretKey key, InputStream in, OutputStream out) throws NoSuchAlgorithmException, InvalidKeyException, IOException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException {
/* 194 */     Cipher cipher = Cipher.getInstance("AES");
/* 195 */     cipher.init(2, key);
/*     */ 
/*     */     
/* 198 */     byte[] buffer = new byte[2048];
/*     */     int bytesRead;
/* 200 */     while ((bytesRead = in.read(buffer)) != -1)
/*     */     {
/* 202 */       out.write(cipher.update(buffer, 0, bytesRead));
/*     */     }
/*     */ 
/*     */     
/* 206 */     out.write(cipher.doFinal());
/* 207 */     out.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void testAES() throws Exception {
/* 213 */     Cipher c = Cipher.getInstance("AES");
/*     */ 
/*     */ 
/*     */     
/* 217 */     KeyGenerator kgen = KeyGenerator.getInstance("AES");
/*     */ 
/*     */ 
/*     */     
/* 221 */     SecretKey skey = kgen.generateKey();
/* 222 */     byte[] raw = skey.getEncoded();
/* 223 */     SecretKeySpec kspec = 
/* 224 */       new SecretKeySpec(raw, "AES");
/*     */ 
/*     */ 
/*     */     
/* 228 */     c.init(1, kspec);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\rmi\test\AES.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */