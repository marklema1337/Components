/*    */ package com.lbs.controls.tablereport;
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
/*    */ public class ViewPreferences
/*    */   implements Serializable
/*    */ {
/*    */   private Integer logicalRef;
/*    */   private Integer userNr;
/*    */   private String queryName;
/*    */   
/*    */   public Integer getUserNr() {
/* 21 */     return this.userNr;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setUserNr(Integer userNr) {
/* 26 */     this.userNr = userNr;
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getLogicalRef() {
/* 31 */     return this.logicalRef;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLogicalRef(Integer logicalRef) {
/* 36 */     this.logicalRef = logicalRef;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getQueryName() {
/* 41 */     return this.queryName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setQueryName(String queryName) {
/* 46 */     this.queryName = queryName;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\ViewPreferences.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */