/*     */ package com.lbs.objectstore;
/*     */ 
/*     */ import com.lbs.data.objects.CustomBusinessObject;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
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
/*     */ public class FileObjectStoreTest
/*     */ {
/*     */   public static CustomBusinessObject[] createTestObjects() {
/*  26 */     Random r = new Random(System.currentTimeMillis());
/*  27 */     CustomBusinessObject[] result = new CustomBusinessObject[100];
/*  28 */     for (int i = 0; i < result.length; i++) {
/*     */       
/*  30 */       CustomBusinessObject bo = new CustomBusinessObject();
/*  31 */       bo.set("name", "Test" + i);
/*  32 */       bo.set("id", r.nextInt());
/*  33 */       result[i] = bo;
/*     */     } 
/*  35 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void createObjectStore(File file, CustomBusinessObject[] objects, boolean append) throws IOException {
/*  40 */     FileObjectStore store = new FileObjectStore(file, append ? 3 : 2);
/*  41 */     for (int i = 0; i < objects.length; i++)
/*  42 */       store.writeObject((Serializable)objects[i], i); 
/*  43 */     store.close();
/*     */   }
/*     */ 
/*     */   
/*     */   public static CustomBusinessObject[] loadObjectStore(File file) throws IOException, ClassNotFoundException {
/*  48 */     ArrayList<Object> result = new ArrayList();
/*  49 */     FileObjectStore store = new FileObjectStore(file, 1);
/*  50 */     for (int i = 0; i < store.size(); i++) {
/*     */       
/*  52 */       Object obj = store.readObject(i);
/*  53 */       if (obj instanceof CustomBusinessObject)
/*  54 */         result.add(obj); 
/*     */     } 
/*  56 */     store.close();
/*  57 */     return result.<CustomBusinessObject>toArray(new CustomBusinessObject[result.size()]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/*  64 */       String tempDirectory = System.getProperty("java.io.tmpdir");
/*  65 */       CustomBusinessObject[] objects = createTestObjects();
/*  66 */       File file = new File(tempDirectory, "test.str");
/*     */       
/*  68 */       createObjectStore(file, objects, false);
/*  69 */       CustomBusinessObject[] loadedObjects = loadObjectStore(file);
/*     */       
/*  71 */       if (loadedObjects.length != objects.length) {
/*     */         
/*  73 */         System.err.println("Object read failed for create, needs debug!");
/*     */         
/*     */         return;
/*     */       } 
/*  77 */       CustomBusinessObject[] appendedObjects = createTestObjects();
/*  78 */       createObjectStore(file, appendedObjects, true);
/*  79 */       loadedObjects = loadObjectStore(file);
/*  80 */       if (loadedObjects.length != objects.length + appendedObjects.length) {
/*     */         
/*  82 */         System.err.println("Object read failed for append, needs debug!");
/*     */         
/*     */         return;
/*     */       } 
/*  86 */       FileObjectStore store = new FileObjectStore(file, 3);
/*  87 */       for (int i = objects.length - 1; i >= 0; i--)
/*     */       {
/*  89 */         store.deleteObject(i);
/*     */       }
/*  91 */       store.close();
/*  92 */       loadedObjects = loadObjectStore(file);
/*  93 */       if (loadedObjects.length != appendedObjects.length) {
/*     */         
/*  95 */         System.err.println("Object delete failed for append, needs debug!");
/*     */         return;
/*     */       } 
/*  98 */       file.delete();
/*     */     }
/* 100 */     catch (Exception e) {
/*     */       
/* 102 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\objectstore\FileObjectStoreTest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */