/*    */ package com.lbs.controls.tablereport;
/*    */ 
/*    */ import java.awt.datatransfer.Transferable;
/*    */ import java.awt.dnd.DragGestureEvent;
/*    */ import java.awt.dnd.DragGestureListener;
/*    */ import java.awt.dnd.DragSource;
/*    */ import java.awt.dnd.DragSourceAdapter;
/*    */ import javax.swing.table.JTableHeader;
/*    */ import javax.swing.table.TableColumn;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HeaderDragSource
/*    */   extends DragSourceAdapter
/*    */   implements DragGestureListener
/*    */ {
/*    */   private JTableHeader header;
/*    */   
/*    */   public HeaderDragSource(JTableHeader header) {
/* 21 */     this.header = header;
/*    */     
/* 23 */     DragSource dragSource = DragSource.getDefaultDragSource();
/* 24 */     dragSource.createDefaultDragGestureRecognizer(header, 3, this);
/*    */   }
/*    */   
/*    */   private TableReportHeaderInfo createHeaderInfo() {
/* 28 */     TableColumn tc = this.header.getDraggedColumn();
/* 29 */     TableReportHeaderInfo info = null;
/*    */     
/* 31 */     if (tc == null) {
/* 32 */       return null;
/*    */     }
/*    */     
/* 35 */     if (tc.getModelIndex() > 0) {
/* 36 */       info = new TableReportHeaderInfo(Integer.valueOf(tc.getModelIndex()), TableReportConstants.SortDirection.ASC);
/* 37 */       info.setTitle((String)tc.getHeaderValue());
/*    */     } 
/*    */     
/* 40 */     return info;
/*    */   }
/*    */   
/*    */   public TableReportHeaderInfo createHeaderInfo(Integer modelIndex) {
/* 44 */     TableReportHeaderInfo headerInfo = new TableReportHeaderInfo(modelIndex, TableReportConstants.SortDirection.ASC);
/*    */     
/* 46 */     int viewIndex = this.header.getTable().convertColumnIndexToView(modelIndex.intValue());
/* 47 */     String headerName = this.header.getColumnModel().getColumn(viewIndex).getHeaderValue().toString();
/* 48 */     headerInfo.setTitle(headerName);
/*    */     
/* 50 */     return headerInfo;
/*    */   }
/*    */   
/*    */   public void dragGestureRecognized(DragGestureEvent dge) {
/* 54 */     TableReportHeaderInfo info = createHeaderInfo();
/* 55 */     if (info == null) {
/*    */       return;
/*    */     }
/*    */     
/* 59 */     Transferable transferable = new HeaderInfoTransferable(info);
/*    */     try {
/* 61 */       dge.startDrag(DragSource.DefaultCopyDrop, transferable, this);
/* 62 */     } catch (Exception e) {
/* 63 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\HeaderDragSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */