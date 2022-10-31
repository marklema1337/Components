/*    */ package com.lbs.controls.tablereport;
/*    */ 
/*    */ import java.awt.datatransfer.Transferable;
/*    */ import java.awt.dnd.DropTargetDragEvent;
/*    */ import java.awt.dnd.DropTargetDropEvent;
/*    */ import java.awt.dnd.DropTargetEvent;
/*    */ import java.awt.dnd.DropTargetListener;
/*    */ import javax.swing.JLabel;
/*    */ 
/*    */ public class HeaderLabel
/*    */   extends JLabel
/*    */   implements DropTargetListener {
/*    */   public HeaderLabel(String text) {
/* 14 */     super(text);
/*    */   }
/*    */ 
/*    */   
/*    */   public void dragEnter(DropTargetDragEvent dtde) {}
/*    */ 
/*    */   
/*    */   public void dragOver(DropTargetDragEvent dtde) {}
/*    */ 
/*    */   
/*    */   public void dropActionChanged(DropTargetDragEvent dtde) {}
/*    */ 
/*    */   
/*    */   public void dragExit(DropTargetEvent dte) {}
/*    */   
/*    */   public void drop(DropTargetDropEvent dtde) {
/*    */     try {
/* 31 */       Transferable t = dtde.getTransferable();
/* 32 */       TableReportHeaderInfo headerInfo = (TableReportHeaderInfo)t.getTransferData(TableReportConstants.headerInfoFlavor);
/* 33 */       firePropertyChange("make.column.invisible", (Object)null, headerInfo);
/* 34 */       firePropertyChange("filter.removed", (Object)null, headerInfo);
/*    */     }
/* 36 */     catch (Exception e) {
/* 37 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\HeaderLabel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */