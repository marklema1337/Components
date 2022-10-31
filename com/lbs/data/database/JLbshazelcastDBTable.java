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
/*    */ public class JLbshazelcastDBTable
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String firmNr;
/*    */   private String period;
/*    */   private DBTable table;
/*    */   
/*    */   public String getFirmNr() {
/* 23 */     return this.firmNr;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFirmNr(String firmNr) {
/* 28 */     this.firmNr = firmNr;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPeriod() {
/* 33 */     return this.period;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPeriod(String period) {
/* 38 */     this.period = period;
/*    */   }
/*    */ 
/*    */   
/*    */   public DBTable getTable() {
/* 43 */     return this.table;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTable(DBTable table) {
/* 48 */     this.table = table;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\JLbshazelcastDBTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */