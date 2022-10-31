/*     */ package com.lbs.localization.db.synchronizer;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
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
/*     */ public class JLbsLocalizationDBSynchronizerHelper
/*     */ {
/*     */   private byte[] getEncryptedData(String parameter) throws Exception {
/*  28 */     byte[] encParam = serializeObject(parameter);
/*  29 */     return encParam;
/*     */   }
/*     */ 
/*     */   
/*     */   private void printByteArray(byte[] array) {
/*  34 */     StringBuilder buffer = new StringBuilder();
/*  35 */     buffer.append("{");
/*  36 */     for (int i = 0; i < array.length; i++) {
/*     */       
/*  38 */       buffer.append(array[i]);
/*  39 */       if (i < array.length - 1)
/*  40 */         buffer.append(","); 
/*     */     } 
/*  42 */     buffer.append("}");
/*  43 */     System.out.println(buffer);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDecryptedData(byte[] parameter) throws Exception {
/*  48 */     Object decParam = deserializeObject(parameter);
/*  49 */     if (decParam instanceof String) {
/*  50 */       return (String)decParam;
/*     */     }
/*  52 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object deserializeObject(byte[] data) throws IOException, ClassNotFoundException {
/*  57 */     ByteArrayInputStream inStream = new ByteArrayInputStream(data);
/*  58 */     DataInputStream objStream = new DataInputStream(inStream);
/*  59 */     Object object = null;
/*     */     
/*     */     try {
/*  62 */       ObjectInputStream localStream = new ObjectInputStream(objStream);
/*  63 */       object = localStream.readObject();
/*     */     }
/*  65 */     catch (EOFException eOFException) {
/*     */ 
/*     */     
/*     */     } finally {
/*     */       
/*  70 */       objStream.close();
/*  71 */       inStream.close();
/*     */     } 
/*  73 */     return object;
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] serializeObject(Object obj) throws IOException {
/*  78 */     byte[] data = null;
/*  79 */     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/*  80 */     ObjectOutputStream objStream = new ObjectOutputStream(outStream);
/*     */     
/*     */     try {
/*  83 */       objStream.writeObject(obj);
/*  84 */       data = outStream.toByteArray();
/*     */     }
/*  86 */     catch (EOFException eOFException) {
/*     */ 
/*     */     
/*     */     } finally {
/*     */       
/*  91 */       objStream.close();
/*  92 */       outStream.close();
/*     */     } 
/*  94 */     return data;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/* 101 */       JLbsLocalizationDBSynchronizerHelper jLbsLocalizationDBSynchronizerHelper = new JLbsLocalizationDBSynchronizerHelper();
/*     */     }
/* 103 */     catch (Exception e) {
/*     */       
/* 105 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\db\synchronizer\JLbsLocalizationDBSynchronizerHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */