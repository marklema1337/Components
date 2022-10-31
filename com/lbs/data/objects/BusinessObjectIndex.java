/*    */ package com.lbs.data.objects;
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
/*    */ public class BusinessObjectIndex
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 17 */   private String m_Name = null;
/* 18 */   private String[] m_Segments = null;
/*    */ 
/*    */   
/*    */   public BusinessObjectIndex(String name, String[] segments) {
/* 22 */     this.m_Name = name;
/* 23 */     this.m_Segments = segments;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 31 */     return this.m_Name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String[] getSegments() {
/* 40 */     return this.m_Segments;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\BusinessObjectIndex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */