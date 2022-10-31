/*     */ package com.lbs.controls.tablereport;
/*     */ 
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.HashMap;
/*     */ import java.util.Vector;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ import org.jdesktop.swingx.JXTable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AggregateTable
/*     */   extends JXTable
/*     */ {
/*     */   private HashMap<Integer, Vector<AggregateValue>> aggregateValues;
/*     */   
/*     */   public void reset(Integer columnIndex) {
/*  27 */     Vector<AggregateValue> columnValues = this.aggregateValues.get(columnIndex);
/*  28 */     if (columnValues != null) {
/*  29 */       columnValues.clear();
/*     */     }
/*     */     
/*  32 */     fillAggregateValues();
/*     */   }
/*     */   
/*     */   public void addAggregateValue(AggregateValue aggregateValue, int columnIndex) {
/*  36 */     if (this.aggregateValues.get(Integer.valueOf(columnIndex)) == null) {
/*  37 */       Vector<AggregateValue> vector = new Vector<>();
/*  38 */       this.aggregateValues.put(Integer.valueOf(columnIndex), vector);
/*     */     } 
/*     */     
/*  41 */     Vector<AggregateValue> columnValues = this.aggregateValues.get(Integer.valueOf(columnIndex));
/*     */     
/*  43 */     if (!columnValues.remove(aggregateValue)) {
/*  44 */       columnValues.add(aggregateValue);
/*     */     }
/*     */   }
/*     */   
/*     */   public void fillAggregateValues() {
/*  49 */     DefaultTableModel model = (DefaultTableModel)getModel();
/*  50 */     model.setRowCount(0);
/*     */     
/*  52 */     int rowCount = getMaxRowCount();
/*     */     
/*  54 */     for (int row = 0; row < rowCount; row++) {
/*  55 */       Vector<String> vector = new Vector<>();
/*     */       
/*  57 */       for (int column = 0; column < getModel().getColumnCount(); column++) {
/*  58 */         Vector<AggregateValue> columnValues = this.aggregateValues.get(Integer.valueOf(column));
/*  59 */         String text = "";
/*     */         
/*  61 */         if (columnValues != null && 
/*  62 */           columnValues.size() > row) {
/*  63 */           text = ((AggregateValue)columnValues.get(row)).toString();
/*     */         }
/*     */         
/*  66 */         vector.add(text);
/*     */       } 
/*  68 */       model.addRow(vector);
/*     */     } 
/*     */   }
/*     */   
/*     */   private int getMaxRowCount() {
/*  73 */     int rowCount = 0;
/*  74 */     for (Integer key : this.aggregateValues.keySet()) {
/*  75 */       if (rowCount < ((Vector)this.aggregateValues.get(key)).size()) {
/*  76 */         rowCount = ((Vector)this.aggregateValues.get(key)).size();
/*     */       }
/*     */     } 
/*     */     
/*  80 */     return rowCount;
/*     */   }
/*     */   
/*     */   public AggregateTable(DefaultTableModel model, ILbsCultureInfo cultureInfo) {
/*  84 */     super(model);
/*  85 */     initGUI();
/*     */     
/*  87 */     AggregateValue.setDecimalFormat(new DecimalFormat(cultureInfo.getDefaultNumberFormat()));
/*  88 */     this.aggregateValues = new HashMap<>();
/*     */   }
/*     */   
/*     */   private void initGUI() {
/*  92 */     setShowGrid(false, false);
/*  93 */     setSortable(false);
/*     */     
/*  95 */     setRowSelectionAllowed(false);
/*  96 */     setColumnSelectionAllowed(false);
/*  97 */     setCellSelectionEnabled(true);
/*     */     
/*  99 */     setSelectionMode(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<Integer, Vector<AggregateValue>> getAggregateValues() {
/* 104 */     return this.aggregateValues;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\AggregateTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */