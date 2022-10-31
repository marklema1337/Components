/*    */ package com.lbs.controller.dto;
/*    */ 
/*    */ import com.google.gwt.core.shared.GwtIncompatible;
/*    */ import com.lbs.gwt.library.shared.facet.PropertyConstraints;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Model
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected boolean m_Modified = false;
/* 16 */   protected HashMap<String, PropertyConstraints> m_Constraints = new HashMap<>();
/*    */   
/* 18 */   private ArrayList<String> m_SetOrder = new ArrayList<>();
/*    */ 
/*    */   
/*    */   @GwtIncompatible
/*    */   public abstract ArrayList<String> getGroupProperties(int paramInt);
/*    */   
/*    */   @GwtIncompatible
/*    */   public abstract ArrayList<Integer> getPropertyGroupIds(String paramString);
/*    */   
/*    */   @GwtIncompatible
/*    */   public abstract Integer getPropertyUniqueUIId(String paramString);
/*    */   
/*    */   @GwtIncompatible
/*    */   public abstract String getPropertyNameByUniqueUIId(int paramInt);
/*    */   
/*    */   @GwtIncompatible
/*    */   public abstract LinkVerifierDescription getLinkVerifierDescription(String paramString);
/*    */   
/*    */   public boolean isModified() {
/* 37 */     return this.m_Modified;
/*    */   }
/*    */ 
/*    */   
/*    */   protected <T> ArrayList<T> mergeLists(ArrayList<T> list1, ArrayList<T> list2) {
/* 42 */     if (list1 == null)
/* 43 */       return list2; 
/* 44 */     if (list2 == null)
/* 45 */       return list1; 
/* 46 */     list1.addAll(list2);
/* 47 */     return list1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void recordPropertySet(String propertyName) {
/* 52 */     this.m_SetOrder.remove(propertyName);
/* 53 */     this.m_SetOrder.add(propertyName);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPropertyConstraint(String propertyName, PropertyConstraints constraints) {
/* 58 */     this.m_Constraints.put(propertyName, constraints);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyConstraints getPropertyConstraint(String propertyName) {
/* 63 */     return this.m_Constraints.get(propertyName);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPrimaryKey() {
/* 68 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<String> getPropertiesSetInOrder() {
/* 73 */     return this.m_SetOrder;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\dto\Model.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */