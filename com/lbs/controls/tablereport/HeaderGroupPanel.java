/*     */ package com.lbs.controls.tablereport;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.datatransfer.Transferable;
/*     */ import java.awt.dnd.DropTargetDragEvent;
/*     */ import java.awt.dnd.DropTargetDropEvent;
/*     */ import java.awt.dnd.DropTargetEvent;
/*     */ import java.awt.dnd.DropTargetListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class HeaderGroupPanel
/*     */   extends JPanel
/*     */   implements DropTargetListener {
/*     */   private void clearDummyLabels() {
/*     */     byte b;
/*     */     int i;
/*     */     Component[] arrayOfComponent;
/*  22 */     for (i = (arrayOfComponent = getComponents()).length, b = 0; b < i; ) { Component c = arrayOfComponent[b];
/*  23 */       if (!(c instanceof GroupHeaderLabel)) {
/*  24 */         remove(c);
/*     */       }
/*     */       b++; }
/*     */     
/*  28 */     revalidate();
/*  29 */     updateUI();
/*     */   }
/*     */   
/*     */   public void remove(TableReportHeaderInfo headerInfo) {
/*  33 */     GroupHeaderLabel label = getGroupHeaderLabel(headerInfo);
/*  34 */     if (label != null) {
/*  35 */       remove(label);
/*  36 */       revalidate();
/*  37 */       updateUI();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setSelected(TableReportHeaderInfo headerInfo, boolean selected) {
/*  42 */     GroupHeaderLabel label = getGroupHeaderLabel(headerInfo);
/*  43 */     label.setSelected(selected); } public GroupHeaderLabel getGroupHeaderLabel(TableReportHeaderInfo hinfo) {
/*     */     byte b;
/*     */     int i;
/*     */     Component[] arrayOfComponent;
/*  47 */     for (i = (arrayOfComponent = getComponents()).length, b = 0; b < i; ) { Component c = arrayOfComponent[b];
/*  48 */       if (c instanceof GroupHeaderLabel) {
/*  49 */         TableReportHeaderInfo headerInfo = ((GroupHeaderLabel)c).getHeaderInfo();
/*  50 */         if (hinfo.equals(headerInfo)) {
/*  51 */           return (GroupHeaderLabel)c;
/*     */         }
/*     */       } 
/*     */       b++; }
/*     */     
/*  56 */     return null;
/*     */   }
/*     */   
/*     */   public void clearFilter(TableReportHeaderInfo headerInfo) {
/*  60 */     GroupHeaderLabel label = getGroupHeaderLabel(headerInfo);
/*  61 */     if (label != null) {
/*  62 */       label.clearFilter();
/*     */     }
/*     */   }
/*     */   
/*     */   public void restoreFilter(TableReportHeaderInfo headerInfo) {
/*  67 */     GroupHeaderLabel label = getGroupHeaderLabel(headerInfo);
/*  68 */     if (label == null) {
/*     */       return;
/*     */     }
/*     */     
/*  72 */     label.restoreFilter();
/*     */   }
/*     */   
/*     */   public boolean contains(TableReportHeaderInfo headerInfo) {
/*  76 */     GroupHeaderLabel label = getGroupHeaderLabel(headerInfo);
/*  77 */     return (label != null);
/*     */   }
/*     */   
/*     */   public void dragEnter(DropTargetDragEvent dtde) {
/*  81 */     JPanel panel = (JPanel)dtde.getDropTargetContext().getComponent();
/*  82 */     TableReportHeaderInfo headerInfo = null;
/*     */     
/*     */     try {
/*  85 */       headerInfo = (TableReportHeaderInfo)dtde.getTransferable().getTransferData(TableReportConstants.headerInfoFlavor);
/*  86 */     } catch (Exception e) {
/*  87 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  90 */     GroupHeaderLabel label = new GroupHeaderLabel(headerInfo);
/*  91 */     Dimension dim = label.getPreferredSize();
/*     */     
/*  93 */     JLabel dummyLabel = new JLabel("");
/*  94 */     dummyLabel.setSize(dim);
/*  95 */     dummyLabel.setPreferredSize(dummyLabel.getSize());
/*  96 */     dummyLabel.setOpaque(false);
/*  97 */     dummyLabel.setBorder(BorderFactory.createLineBorder(Color.green, 2));
/*  98 */     panel.add(dummyLabel);
/*  99 */     panel.revalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void dragOver(DropTargetDragEvent dtde) {}
/*     */ 
/*     */   
/*     */   public void dropActionChanged(DropTargetDragEvent dtde) {}
/*     */   
/*     */   public void dragExit(DropTargetEvent dte) {
/* 109 */     clearDummyLabels();
/*     */   }
/*     */   
/*     */   public void drop(DropTargetDropEvent dtde) {
/*     */     try {
/* 114 */       Transferable t = dtde.getTransferable();
/* 115 */       TableReportHeaderInfo info = (TableReportHeaderInfo)t.getTransferData(TableReportConstants.headerInfoFlavor);
/* 116 */       clearDummyLabels();
/* 117 */       firePropertyChange("drop", (Object)null, info);
/*     */     }
/* 119 */     catch (Exception e) {
/* 120 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\HeaderGroupPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */