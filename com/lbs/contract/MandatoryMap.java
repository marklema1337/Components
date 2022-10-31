/*    */ package com.lbs.contract;
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
/*    */ public class MandatoryMap
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private boolean m_MarkAllNonMandatory = false;
/* 20 */   private Hashtable<String, Boolean> m_MandatoryPropMap = new Hashtable<>();
/*    */ 
/*    */   
/*    */   public void setMarkAllNonMandatory(boolean markAllNonMandatory) {
/* 24 */     this.m_MarkAllNonMandatory = markAllNonMandatory;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isMarkAllNonMandatory() {
/* 29 */     return this.m_MarkAllNonMandatory;
/*    */   }
/*    */ 
/*    */   
/*    */   public Hashtable<String, Boolean> getMandatoryPropMap() {
/* 34 */     return this.m_MandatoryPropMap;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addMandatoryPropMap(String propertyName, boolean mandatory) {
/* 39 */     this.m_MandatoryPropMap.put(propertyName, Boolean.valueOf(mandatory));
/*    */   }
/*    */ 
/*    */   
/*    */   public Boolean getMandatoryForProperty(String propertyName) {
/* 44 */     return this.m_MandatoryPropMap.get(propertyName);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\MandatoryMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */