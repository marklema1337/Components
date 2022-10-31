/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class QueryGlobalOrder
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected String m_Name;
/* 23 */   protected ArrayList<String> m_TableAliases = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 28 */     return this.m_Name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setName(String string) {
/* 33 */     this.m_Name = string;
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<String> getTableAliases() {
/* 38 */     return this.m_TableAliases;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTableAliases(ArrayList<String> tableAliases) {
/* 43 */     this.m_TableAliases = tableAliases;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryGlobalOrder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */