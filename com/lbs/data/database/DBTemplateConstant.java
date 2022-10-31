/*    */ package com.lbs.data.database;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DBTemplateConstant
/*    */   implements Cloneable, Serializable
/*    */ {
/*    */   public static final int TYPE_INT = 0;
/*    */   public static final int TYPE_STRING = 1;
/*    */   public static final int TYPE_BOOLEAN = 2;
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_Name;
/*    */   private int m_Type;
/*    */   private Object m_Value;
/*    */   
/*    */   public DBTemplateConstant() {}
/*    */   
/*    */   public DBTemplateConstant(String name, int type, String value) {
/* 33 */     this.m_Name = name;
/* 34 */     this.m_Type = type;
/* 35 */     this.m_Value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 40 */     return this.m_Name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 45 */     this.m_Name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getType() {
/* 50 */     return this.m_Type;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setType(int type) {
/* 55 */     this.m_Type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getValue() {
/* 60 */     return this.m_Value;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValue(Object value) {
/* 65 */     this.m_Value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object clone() throws CloneNotSupportedException {
/* 70 */     return super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\DBTemplateConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */