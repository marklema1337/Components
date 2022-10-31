/*    */ package com.lbs.mi.defs;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public class ModuleJarDefinition
/*    */   implements Serializable
/*    */ {
/*    */   private String m_JarName;
/*    */   private boolean m_SignVerify;
/*    */   private boolean m_SilentLoad;
/*    */   
/*    */   public ModuleJarDefinition(String jarName, boolean signVerify, boolean silentLoad) {
/* 23 */     this.m_JarName = jarName;
/* 24 */     this.m_SignVerify = signVerify;
/* 25 */     this.m_SilentLoad = silentLoad;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getJarName() {
/* 33 */     return this.m_JarName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setJarName(String jarName) {
/* 41 */     this.m_JarName = jarName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isSignVerify() {
/* 49 */     return this.m_SignVerify;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSignVerify(boolean signVerify) {
/* 57 */     this.m_SignVerify = signVerify;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isSilentLoad() {
/* 65 */     return this.m_SilentLoad;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSilentLoad(boolean silentLoad) {
/* 73 */     this.m_SilentLoad = silentLoad;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mi\defs\ModuleJarDefinition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */