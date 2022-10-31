/*    */ package com.lbs.util;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XSLTUtil
/*    */ {
/*    */   public static void transformXML(String inputXML, String inputXSL, String outputXML, ClassLoader loader) throws Exception {
/* 18 */     if (loader == null) {
/* 19 */       loader = ClassLoader.getSystemClassLoader();
/*    */     }
/* 21 */     ClassLoader orgClassLoader = Thread.currentThread().getContextClassLoader();
/*    */     
/*    */     try {
/* 24 */       Thread.currentThread().setContextClassLoader(loader);
/* 25 */       Class<?> implClazz = Class.forName("com.lbs.util.XSLTUtilImpl");
/* 26 */       if (implClazz != null) {
/*    */         
/* 28 */         Object instance = implClazz.newInstance();
/* 29 */         if (instance != null)
/*    */         {
/* 31 */           Method method = implClazz.getDeclaredMethod("transformXML", new Class[] { String.class, String.class, String.class });
/*    */           
/* 33 */           if (method != null)
/*    */           {
/* 35 */             method.invoke(instance, new Object[] { inputXML, inputXSL, outputXML });
/*    */           }
/*    */         }
/*    */       
/*    */       } 
/*    */     } finally {
/*    */       
/* 42 */       if (orgClassLoader != null) {
/* 43 */         Thread.currentThread().setContextClassLoader(orgClassLoader);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void transformXML(String inputXML, String inputXSL, String outputXML) throws Exception {
/* 49 */     transformXML(inputXML, inputXSL, outputXML, ClassLoader.getSystemClassLoader());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/*    */     try {
/* 56 */       transformXML(JLbsFileUtil.appendPath("E:\\Temp\\xsl", "test.xml"), JLbsFileUtil.appendPath("E:\\Temp\\xsl", "test.xsl"), 
/* 57 */           JLbsFileUtil.appendPath("E:\\Temp\\xsl", "out.xml"));
/*    */     
/*    */     }
/* 60 */     catch (Exception e) {
/*    */       
/* 62 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\XSLTUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */