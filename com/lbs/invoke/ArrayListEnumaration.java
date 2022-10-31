/*    */ package com.lbs.invoke;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Enumeration;
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
/*    */ 
/*    */ 
/*    */ class ArrayListEnumaration
/*    */   implements Enumeration
/*    */ {
/*    */   private ArrayList m_List;
/*    */   private int m_Index;
/*    */   
/*    */   public ArrayListEnumaration(ArrayList list) {
/* 74 */     this.m_List = list;
/* 75 */     this.m_Index = 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasMoreElements() {
/* 83 */     return (this.m_List != null && this.m_List.size() > this.m_Index);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object nextElement() {
/* 91 */     if (this.m_List != null && this.m_List.size() > this.m_Index)
/* 92 */       return this.m_List.get(this.m_Index++); 
/* 93 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\invoke\ArrayListEnumaration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */