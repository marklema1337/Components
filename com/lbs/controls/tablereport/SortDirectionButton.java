/*    */ package com.lbs.controls.tablereport;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Polygon;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.border.Border;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SortDirectionButton
/*    */   extends JButton
/*    */ {
/*    */   TableReportHeaderInfo info;
/*    */   private static final int VM = 5;
/*    */   private static final int HM = 5;
/*    */   
/* 20 */   private transient ActionListener listener = new ActionListener() {
/*    */       public void actionPerformed(ActionEvent e) {
/* 22 */         SortDirectionButton.this.toggle();
/* 23 */         SortDirectionButton.this.firePropertyChange("sort.changed", (Object)null, SortDirectionButton.this.info);
/*    */       }
/*    */     };
/*    */ 
/*    */   
/*    */   public SortDirectionButton(TableReportHeaderInfo info) {
/* 29 */     this.info = info;
/* 30 */     addActionListener(this.listener);
/* 31 */     setBorder((Border)null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void paintComponent(Graphics g) {
/* 37 */     Polygon p = createDirectionPolygon();
/*    */     
/* 39 */     g.setColor(Color.black);
/* 40 */     g.fillPolygon(p);
/*    */   }
/*    */ 
/*    */   
/*    */   private Polygon createDirectionPolygon() {
/* 45 */     int xs[], ys[], w = (getWidth() - 10) / 2;
/* 46 */     int h = (getHeight() - 10) / 2;
/*    */     
/* 48 */     Polygon p = null;
/*    */ 
/*    */     
/* 51 */     if (this.info.getSortDirection() == TableReportConstants.SortDirection.ASC) {
/* 52 */       xs = new int[] { 5, 2 * w + 5, w + 5 };
/* 53 */       ys = new int[] { 2 * h + 5, 2 * h + 5, 5 };
/*    */     } else {
/*    */       
/* 56 */       xs = new int[] { 5, 2 * w + 5, w + 5 };
/* 57 */       ys = new int[] { 5, 5, 2 * h + 5 };
/*    */     } 
/*    */ 
/*    */     
/* 61 */     p = new Polygon(xs, ys, 3);
/*    */     
/* 63 */     return p;
/*    */   }
/*    */   
/*    */   public void setSortDirection(TableReportConstants.SortDirection sortDirection) {
/* 67 */     this.info.setSortDirection(sortDirection);
/* 68 */     repaint();
/*    */   }
/*    */   
/*    */   public void toggle() {
/* 72 */     if (this.info.getSortDirection() == TableReportConstants.SortDirection.ASC) {
/* 73 */       this.info.setSortDirection(TableReportConstants.SortDirection.DESC);
/*    */     } else {
/* 75 */       this.info.setSortDirection(TableReportConstants.SortDirection.ASC);
/*    */     } 
/*    */     
/* 78 */     repaint();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\SortDirectionButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */