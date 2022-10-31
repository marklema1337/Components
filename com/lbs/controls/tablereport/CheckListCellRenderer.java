/*    */ package com.lbs.controls.tablereport;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import javax.swing.JCheckBox;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JList;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.ListCellRenderer;
/*    */ import javax.swing.border.Border;
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
/*    */ public class CheckListCellRenderer
/*    */   implements ListCellRenderer
/*    */ {
/*    */   private JCheckBox check;
/*    */   private JLabel label;
/*    */   
/*    */   public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
/* 29 */     JPanel renderer = createDefaultCellRenderer();
/* 30 */     FilterListCellValue flcv = (FilterListCellValue)value;
/* 31 */     this.check.setSelected(flcv.isIncluded());
/* 32 */     this.label.setText(TableReportInfo.getFormatter().format(flcv.getValue()));
/*    */     
/* 34 */     if (isSelected || cellHasFocus) {
/* 35 */       this.check.setBackground(Color.blue.brighter());
/* 36 */       this.label.setBackground(Color.blue.brighter());
/* 37 */       this.label.setForeground(Color.white);
/*    */     } else {
/*    */       
/* 40 */       this.check.setBackground(Color.white);
/* 41 */       this.label.setBackground(Color.white);
/* 42 */       this.label.setForeground(Color.black);
/*    */     } 
/*    */     
/* 45 */     return renderer;
/*    */   }
/*    */   
/*    */   private JPanel createDefaultCellRenderer() {
/* 49 */     JPanel panel = new JPanel();
/* 50 */     panel.setOpaque(false);
/* 51 */     panel.setBorder((Border)null);
/* 52 */     panel.setLayout(new BorderLayout());
/*    */     
/* 54 */     this.check = new JCheckBox();
/* 55 */     this.check.setBackground(Color.white);
/* 56 */     this.check.setOpaque(true);
/*    */     
/* 58 */     this.label = new JLabel();
/* 59 */     this.label.setOpaque(true);
/* 60 */     this.label.setBackground(Color.white);
/*    */     
/* 62 */     panel.add(this.check, "West");
/* 63 */     panel.add(this.label, "Center");
/*    */     
/* 65 */     return panel;
/*    */   }
/*    */ 
/*    */   
/*    */   public JCheckBox getCheck() {
/* 70 */     return this.check;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\CheckListCellRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */