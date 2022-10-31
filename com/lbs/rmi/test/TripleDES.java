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
/*     */ public class TripleDES
/*     */ {
/*     */   public static void main(String[] args) {
/*     */     try {
/*     */       try {
/*  53 */         Cipher.getInstance("DESede");
/*     */       }
/*  55 */       catch (Exception e) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  61 */         System.err.println("Installing SunJCE provider.");
/*  62 */         Provider sunjce = new SunJCE();
/*  63 */         Security.addProvider(sunjce);
/*     */       } 
/*     */ 
/*     */       
/*  67 */       File keyfile = new File(args[1]);
/*     */ 
/*     */       
/*  70 */       if (args[0].equals("-g"))
/*     */       {
/*  72 */         System.out.print("Generating key. This may take some time...");
/*  73 */         System.out.flush();
/*  74 */         SecretKey key = generateKey();
/*  75 */         writeKey(key, keyfile);
/*  76 */         System.out.println("done.");
/*  77 */         System.out.println("Secret key written to " + args[1] + ". Protect that file carefully!");
/*     */       }
/*  79 */       else if (args[0].equals("-e"))
/*     */       {
/*  81 */         SecretKey key = readKey(keyfile);
/*  82 */         encrypt(key, System.in, System.out);
/*     */       }
/*  84 */       else if (args[0].equals("-d"))
/*     */       {
/*  86 */         SecretKey key = readKey(keyfile);
/*  87 */         decrypt(key, System.in, System.out);
/*     */       }
/*     */     
/*  90 */     } catch (Exception e) {
/*     */       
/*  92 */       System.err.println(e);
/*  93 */       System.err.println("Usage: java " + TripleDES.class.getName() + " -d|-e|-g <keyfile>");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SecretKey generateKey() throws NoSuchAlgorithmException {
/* 101 */     KeyGenerator keygen = KeyGenerator.getInstance("DESede");
/*     */     
/* 103 */     return keygen.generateKey();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeKey(SecretKey key, File f) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
/* 110 */     SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
/* 111 */     DESedeKeySpec keyspec = (DESedeKeySpec)keyfactory.getKeySpec(key, DESedeKeySpec.class);
/* 112 */     byte[] rawkey = keyspec.getKey();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     FileOutputStream out = null;
/*     */     try {
/* 119 */       out = new FileOutputStream(f);
/* 120 */       out.write(rawkey);
/*     */     }
/* 122 */     catch (IOException e) {
/* 123 */       throw e;
/*     */     } finally {
/*     */       
/* 126 */       if (out != null) {
/* 127 */         out.close();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SecretKey readKey(File f) throws IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
/* 136 */     DataInputStream in = new DataInputStream(new FileInputStream(f));
/* 137 */     byte[] rawkey = new byte[(int)f.length()];
/* 138 */     in.readFully(rawkey);
/* 139 */     in.close();
/*     */ 
/*     */     
/* 142 */     DESedeKeySpec keyspec = new DESedeKeySpec(rawkey);
/* 143 */     SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
/* 144 */     SecretKey key = keyfactory.generateSecret(keyspec);
/* 145 */     return key;
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
/* 157 */     Cipher cipher = Cipher.getInstance("DESede");
/* 158 */     cipher.init(1, key);
/*     */ 
/*     */     
/* 161 */     CipherOutputStream cos = new CipherOutputStream(out, cipher);
/*     */ 
/*     */     
/* 164 */     byte[] buffer = new byte[2048];
/*     */     int bytesRead;
/* 166 */     while ((bytesRead = in.read(buffer)) != -1)
/*     */     {
/* 168 */       cos.write(buffer, 0, bytesRead);
/*     */     }
/* 170 */     cos.close();
/*     */ 
/*     */     
/* 173 */     Arrays.fill(buffer, (byte)0);
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
/* 186 */     Cipher cipher = Cipher.getInstance("DESede");
/* 187 */     cipher.init(2, key);
/*     */ 
/*     */     
/* 190 */     byte[] buffer = new byte[2048];
/*     */     int bytesRead;
/* 192 */     while ((bytesRead = in.read(buffer)) != -1)
/*     */     {
/* 194 */       out.write(cipher.update(buffer, 0, bytesRead));
/*     */     }
/*     */ 
/*     */     
/* 198 */     out.write(cipher.doFinal());
/* 199 */     out.flush();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\rmi\test\TripleDES.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */