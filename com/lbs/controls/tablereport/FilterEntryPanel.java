/*     */ package com.lbs.controls.tablereport;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FilterEntryPanel
/*     */   extends JPanel
/*     */ {
/*     */   private TableReportHeaderInfo headerInfo;
/*     */   private JLabel label;
/*     */   private JCheckBox chkIncluded;
/*     */   private JButton btnCancel;
/*     */   
/*     */   public FilterEntryPanel(TableReportHeaderInfo headerInfo) {
/*  36 */     this.headerInfo = headerInfo;
/*  37 */     setOpaque(false);
/*  38 */     initGUI();
/*     */   }
/*     */ 
/*     */   
/*     */   private void initGUI() {
/*  43 */     setLayout(new BoxLayout(this, 0));
/*     */     
/*  45 */     this.btnCancel = new JButton("X");
/*  46 */     this.btnCancel.setForeground(Color.red);
/*  47 */     this.btnCancel.setSize(22, 22);
/*  48 */     this.btnCancel.setPreferredSize(this.btnCancel.getPreferredSize());
/*  49 */     this.btnCancel.setFont(this.btnCancel.getFont().deriveFont(11.0F));
/*  50 */     this.btnCancel.setFont(this.btnCancel.getFont().deriveFont(1));
/*  51 */     this.btnCancel.setHorizontalAlignment(0);
/*  52 */     this.btnCancel.setHorizontalAlignment(0);
/*  53 */     this.btnCancel.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  57 */             FilterEntryPanel.this.firePropertyChange("filter.removed", (Object)null, FilterEntryPanel.this.headerInfo);
/*     */           }
/*     */         });
/*     */     
/*  61 */     this.chkIncluded = new JCheckBox();
/*  62 */     this.chkIncluded.setOpaque(false);
/*  63 */     this.chkIncluded.setPreferredSize(new Dimension(20, 20));
/*  64 */     this.chkIncluded.setSelected(true);
/*  65 */     this.chkIncluded.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  69 */             if (FilterEntryPanel.this.chkIncluded.isSelected()) {
/*  70 */               FilterEntryPanel.this.firePropertyChange("filter.enabled", (Object)null, FilterEntryPanel.this.headerInfo);
/*     */             } else {
/*  72 */               FilterEntryPanel.this.firePropertyChange("filter.disabled", (Object)null, FilterEntryPanel.this.headerInfo);
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/*  77 */     this.label = new JLabel(this.headerInfo.getFilterString());
/*  78 */     this.label.setFont(this.label.getFont().deriveFont(1));
/*  79 */     this.label.setOpaque(false);
/*     */     
/*  81 */     add(this.btnCancel);
/*  82 */     add(this.chkIncluded);
/*  83 */     add(this.label);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateFilterString() {
/*  88 */     this.label.setText(this.headerInfo.getFilterString());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIncluded() {
/*  93 */     return this.chkIncluded.isSelected();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIncluded(boolean included) {
/*  98 */     this.chkIncluded.setSelected(included);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeaderInfo(TableReportHeaderInfo headerInfo) {
/* 103 */     this.headerInfo = headerInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public TableReportHeaderInfo getHeaderInfo() {
/* 108 */     return this.headerInfo;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\FilterEntryPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */