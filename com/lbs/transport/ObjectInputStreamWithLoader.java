/*    */ package com.lbs.transport;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectStreamClass;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ObjectInputStreamWithLoader
/*    */   extends ObjectInputStream
/*    */ {
/*    */   private ClassLoader m_ClsLoader;
/*    */   
/*    */   public ObjectInputStreamWithLoader(InputStream in, ClassLoader clsLoader) throws IOException {
/* 21 */     super(in);
/* 22 */     this.m_ClsLoader = clsLoader;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Class resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
/* 28 */     if (this.m_ClsLoader != null) {
/*    */       
/*    */       try {
/* 31 */         if (this.m_ClsLoader instanceof EntityXUIDefClassLoader && desc.getName().indexOf("EntityXUIDefinition") > -1)
/* 32 */           return ((EntityXUIDefClassLoader)this.m_ClsLoader).loadEntityXUIClass(); 
/* 33 */         return super.resolveClass(desc);
/*    */       }
/* 35 */       catch (ClassNotFoundException e) {
/*    */         
/* 37 */         String className = desc.getName();
/* 38 */         return this.m_ClsLoader.loadClass(className);
/*    */       } 
/*    */     }
/* 41 */     return super.resolveClass(desc);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\ObjectInputStreamWithLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */