/*    */ package com.lbs.mi.defs;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Hashtable;
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
/*    */ public class ModuleContractInput
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_Alias;
/* 20 */   private Hashtable<String, Object> m_Properties = new Hashtable<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public ModuleContractInput(String alias) {
/* 25 */     this.m_Alias = alias;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAlias() {
/* 30 */     return this.m_Alias;
/*    */   }
/*    */ 
/*    */   
/*    */   public Hashtable<String, Object> getProperties() {
/* 35 */     return this.m_Properties;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mi\defs\ModuleContractInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */