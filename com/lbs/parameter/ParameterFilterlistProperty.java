/*    */ package com.lbs.parameter;
/*    */ 
/*    */ import java.util.ArrayList;
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
/*    */ public class ParameterFilterlistProperty
/*    */   extends ParameterProperty
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 18 */   private ArrayList<ParameterFilterGroup> m_FilterGroups = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public ArrayList<ParameterFilterGroup> getFilterGroups() {
/* 22 */     return this.m_FilterGroups;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTypeString() {
/* 28 */     return "JLbsFilterList";
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFilterGroups(ArrayList<ParameterFilterGroup> filters) {
/* 33 */     this.m_FilterGroups = filters;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasFilters() {
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void prepareImports(ArrayList<String> imports) {
/* 45 */     super.prepareImports(imports);
/* 46 */     ParameterSchema.addImport(imports, "com.lbs.filter.JLbsFilterList");
/* 47 */     for (ParameterFilterGroup filterGrp : this.m_FilterGroups)
/*    */     {
/* 49 */       filterGrp.prepareImports(imports);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean needsXmlChildren() {
/* 56 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\parameter\ParameterFilterlistProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */