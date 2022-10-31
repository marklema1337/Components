/*    */ package com.lbs.mi.defs;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SearchEnableForm
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private ArrayList<String> m_Query;
/*    */   
/*    */   public SearchEnableForm() {
/* 15 */     this.m_Query = new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public SearchEnableForm(SearchEnableForm def) {
/* 20 */     this.m_Query = def.getQueries();
/*    */   }
/*    */ 
/*    */   
/*    */   public void addQueries(String alias) {
/* 25 */     getQueries().add(alias);
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<String> getQueries() {
/* 30 */     return this.m_Query;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mi\defs\SearchEnableForm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */