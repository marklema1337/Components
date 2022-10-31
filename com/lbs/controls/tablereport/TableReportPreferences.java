/*    */ package com.lbs.controls.tablereport;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Vector;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TableReportPreferences
/*    */   extends ViewPreferences
/*    */   implements Serializable
/*    */ {
/*    */   private HashMap<Integer, Vector<FilterListCellValue>> exclusionMap;
/*    */   private Vector<Integer> columnOrder;
/*    */   private HashMap<Integer, TableReportHeaderInfo> headerInfos;
/*    */   private ArrayList<JLbsRowColorPrefObject> colorPrefs;
/*    */   
/*    */   public HashMap<Integer, Vector<FilterListCellValue>> getExclusionMap() {
/* 26 */     return this.exclusionMap;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setExclusionMap(HashMap<Integer, Vector<FilterListCellValue>> exclusionMap) {
/* 31 */     this.exclusionMap = exclusionMap;
/*    */   }
/*    */ 
/*    */   
/*    */   public Vector<Integer> getColumnOrder() {
/* 36 */     return this.columnOrder;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setColumnOrder(Vector<Integer> columnOrder) {
/* 41 */     this.columnOrder = columnOrder;
/*    */   }
/*    */ 
/*    */   
/*    */   public HashMap<Integer, TableReportHeaderInfo> getHeaderInfos() {
/* 46 */     return this.headerInfos;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHeaderInfos(HashMap<Integer, TableReportHeaderInfo> headerInfos) {
/* 51 */     this.headerInfos = headerInfos;
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<JLbsRowColorPrefObject> getColorPrefs() {
/* 56 */     return this.colorPrefs;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setColorPrefs(ArrayList<JLbsRowColorPrefObject> colorPrefs) {
/* 61 */     this.colorPrefs = colorPrefs;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\TableReportPreferences.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */