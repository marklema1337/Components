/*    */ package com.lbs.controls.tablereport;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Polygon;
/*    */ import javax.swing.BorderFactory;
/*    */ import javax.swing.JLabel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TableHeaderLabel
/*    */   extends JLabel
/*    */ {
/*    */   private TableReportHeaderInfo headerInfo;
/*    */   
/*    */   private class DirectionLabel
/*    */     extends JLabel
/*    */   {
/*    */     private static final int VM = 5;
/*    */     private static final int HM = 5;
/*    */     
/*    */     private DirectionLabel() {}
/*    */     
/*    */     public void toggle() {
/* 31 */       if (TableHeaderLabel.this.headerInfo.getSortDirection() == TableReportConstants.SortDirection.ASC) {
/* 32 */         setSortDirection(TableReportConstants.SortDirection.DESC);
/*    */       } else {
/* 34 */         setSortDirection(TableReportConstants.SortDirection.ASC);
/*    */       } 
/*    */     }
/*    */     
/*    */     public void paintComponent(Graphics g) {
/* 39 */       super.paintComponent(g);
/*    */       
/* 41 */       if (TableHeaderLabel.this.headerInfo.getSortDirection() != TableReportConstants.SortDirection.UNDEFINED) {
/* 42 */         Polygon p = createDirectionPolygon();
/*    */         
/* 44 */         g.setColor(Color.black);
/* 45 */         g.fillPolygon(p);
/*    */       } 
/*    */     }
/*    */ 
/*    */     
/*    */     private Polygon createDirectionPolygon() {
/* 51 */       int xs[], ys[], w = (getWidth() - 10) / 2;
/* 52 */       int h = (getHeight() - 10) / 2;
/*    */       
/* 54 */       Polygon p = null;
/*    */ 
/*    */       
/* 57 */       if (TableHeaderLabel.this.headerInfo.getSortDirection() == TableReportConstants.SortDirection.ASC) {
/* 58 */         xs = new int[] { 5, 2 * w + 5, w + 5 };
/* 59 */         ys = new int[] { 2 * h + 5, 2 * h + 5, 5 };
/*    */       } else {
/*    */         
/* 62 */         xs = new int[] { 5, 2 * w + 5, w + 5 };
/* 63 */         ys = new int[] { 5, 5, 2 * h + 5 };
/*    */       } 
/*    */ 
/*    */       
/* 67 */       p = new Polygon(xs, ys, 3);
/*    */       
/* 69 */       return p;
/*    */     }
/*    */     
/*    */     public void setSortDirection(TableReportConstants.SortDirection sortDirection) {
/* 73 */       TableHeaderLabel.this.headerInfo.setSortDirection(sortDirection);
/* 74 */       repaint();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public TableHeaderLabel(TableReportHeaderInfo headerInfo) {
/* 80 */     this.headerInfo = headerInfo;
/*    */     
/* 82 */     initGUI();
/*    */   }
/*    */   
/*    */   private void initGUI() {
/* 86 */     setLayout(new BorderLayout());
/* 87 */     setBorder(BorderFactory.createLineBorder(Color.lightGray));
/* 88 */     DirectionLabel dirLabel = new DirectionLabel(null);
/* 89 */     dirLabel.setSize(20, 20);
/* 90 */     dirLabel.setPreferredSize(dirLabel.getSize());
/* 91 */     add(dirLabel, "East");
/* 92 */     setText(this.headerInfo.getTitle());
/* 93 */     setHorizontalAlignment(0);
/* 94 */     setVerticalAlignment(0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\TableHeaderLabel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */