/*    */ package com.lbs.transport;
/*    */ 
/*    */ import com.lbs.interfaces.ILbsDisposeableClassLoader;
/*    */ import com.lbs.invoke.JLbsClassLoader;
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
/*    */ public class EntityXUIDefClassLoader
/*    */   extends JLbsClassLoader
/*    */   implements ILbsDisposeableClassLoader
/*    */ {
/*    */   public Class<?> loadEntityXUIClass() throws ClassNotFoundException {
/* 20 */     return loadClass("com.lbs.client.EntityXUIDefinition");
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\EntityXUIDefClassLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */