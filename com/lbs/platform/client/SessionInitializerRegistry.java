/*    */ package com.lbs.platform.client;
/*    */ 
/*    */ import com.lbs.globalization.JLbsInitializationData;
/*    */ import com.lbs.interfaces.ILbsSessionInitializer;
/*    */ import com.lbs.interfaces.IVariableHolder;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Vector;
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
/*    */ 
/*    */ 
/*    */ public class SessionInitializerRegistry
/*    */ {
/*    */   public static final int RUNTIME_SERVER = 1;
/*    */   public static final int RUNTIME_CLIENT = 2;
/*    */   public static final int RUNTIME_BOTH = 3;
/* 27 */   private List<ILbsSessionInitializer> m_ClientInitializers = new Vector<>();
/* 28 */   private List<ILbsSessionInitializer> m_ServerInitializers = new Vector<>();
/*    */   
/* 30 */   private static SessionInitializerRegistry ms_Instance = new SessionInitializerRegistry();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static SessionInitializerRegistry getInstance() {
/* 38 */     return ms_Instance;
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerInitializer(ILbsSessionInitializer initializer, int runtime) {
/* 43 */     switch (runtime) {
/*    */       
/*    */       case 2:
/* 46 */         this.m_ClientInitializers.add(initializer);
/*    */         break;
/*    */       case 1:
/* 49 */         this.m_ServerInitializers.add(initializer);
/*    */         break;
/*    */       case 3:
/* 52 */         this.m_ClientInitializers.add(initializer);
/* 53 */         this.m_ServerInitializers.add(initializer);
/*    */         break;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void initializeSession(IVariableHolder varHolder, JLbsInitializationData initData, int runtime) {
/* 61 */     Iterator<ILbsSessionInitializer> iterator = (runtime == 2) ? 
/* 62 */       this.m_ClientInitializers.iterator() : 
/* 63 */       this.m_ServerInitializers.iterator(); while (iterator.hasNext()) {
/*    */ 
/*    */       
/*    */       try {
/* 67 */         ILbsSessionInitializer initializer = iterator.next();
/* 68 */         initializer.initializeSession(varHolder, initData);
/*    */       }
/* 70 */       catch (Throwable e) {
/*    */         
/* 72 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List<ILbsSessionInitializer> getClientInitializers() {
/* 79 */     return this.m_ClientInitializers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\client\SessionInitializerRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */