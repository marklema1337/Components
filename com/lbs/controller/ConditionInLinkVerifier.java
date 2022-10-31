/*    */ package com.lbs.controller;
/*    */ 
/*    */ import java.util.Hashtable;
/*    */ 
/*    */ 
/*    */ public class ConditionInLinkVerifier
/*    */ {
/*    */   private String m_Name;
/*  9 */   private Hashtable<String, String> m_Inputs = new Hashtable<>();
/*    */ 
/*    */   
/*    */   public String getName() {
/* 13 */     return this.m_Name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 18 */     this.m_Name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public Hashtable<String, String> getInputs() {
/* 23 */     return this.m_Inputs;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addInput(String name, String value) {
/* 28 */     this.m_Inputs.put(name, value);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getInputValue(String name) {
/* 33 */     return this.m_Inputs.get(name);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 39 */     if (obj instanceof ConditionInLinkVerifier) {
/*    */       
/* 41 */       ConditionInLinkVerifier cond = (ConditionInLinkVerifier)obj;
/* 42 */       return this.m_Name.equals(cond.m_Name);
/*    */     } 
/* 44 */     return super.equals(obj);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 50 */     return this.m_Name.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\ConditionInLinkVerifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */