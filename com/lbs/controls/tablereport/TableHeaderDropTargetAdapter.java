/*    */ package com.lbs.controls.tablereport;
/*    */ 
/*    */ import java.awt.datatransfer.Transferable;
/*    */ import java.awt.dnd.DropTargetAdapter;
/*    */ import java.awt.dnd.DropTargetDropEvent;
/*    */ import java.beans.PropertyChangeEvent;
/*    */ import java.beans.PropertyChangeListener;
/*    */ 
/*    */ public class TableHeaderDropTargetAdapter extends DropTargetAdapter {
/*    */   private PropertyChangeListener listener;
/*    */   
/*    */   public TableHeaderDropTargetAdapter(PropertyChangeListener listener) {
/* 13 */     this.listener = listener;
/*    */   }
/*    */   
/*    */   public void drop(DropTargetDropEvent dtde) {
/*    */     try {
/* 18 */       Transferable t = dtde.getTransferable();
/* 19 */       TableReportHeaderInfo info = (TableReportHeaderInfo)t.getTransferData(TableReportConstants.headerInfoFlavor);
/* 20 */       PropertyChangeEvent evt = new PropertyChangeEvent(dtde.getSource(), "group.restored.visible", null, info);
/* 21 */       this.listener.propertyChange(evt);
/*    */     }
/* 23 */     catch (Exception e) {
/* 24 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\TableHeaderDropTargetAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */