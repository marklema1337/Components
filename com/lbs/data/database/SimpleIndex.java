/*    */ package com.lbs.data.database;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SimpleIndex
/*    */   implements Cloneable
/*    */ {
/* 14 */   private String m_Suffix = "0";
/*    */   private String m_Name;
/*    */   private String m_PhysicalName;
/* 17 */   private int m_Type = 3;
/*    */ 
/*    */   
/*    */   public String getSuffix() {
/* 21 */     return this.m_Suffix;
/*    */   }
/*    */   
/*    */   public void setSuffix(String suffix) {
/* 25 */     this.m_Suffix = suffix;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 29 */     return this.m_Name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 33 */     this.m_Name = name;
/*    */   }
/*    */   
/*    */   public String getPhysicalName() {
/* 37 */     return this.m_PhysicalName;
/*    */   }
/*    */   
/*    */   public void setPhysicalName(String physicalName) {
/* 41 */     this.m_PhysicalName = physicalName;
/*    */   }
/*    */   
/*    */   public int getType() {
/* 45 */     return this.m_Type;
/*    */   }
/*    */   
/*    */   public void setType(int type) {
/* 49 */     this.m_Type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object clone() throws CloneNotSupportedException {
/* 54 */     return super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\SimpleIndex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */