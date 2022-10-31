/*    */ package com.lbs.controls.tablereport;
/*    */ 
/*    */ import java.awt.datatransfer.Transferable;
/*    */ import java.awt.dnd.DragGestureEvent;
/*    */ import java.awt.dnd.DragGestureListener;
/*    */ import java.awt.dnd.DragSource;
/*    */ import java.awt.dnd.DragSourceAdapter;
/*    */ 
/*    */ public class GroupLabelDragSource
/*    */   extends DragSourceAdapter implements DragGestureListener {
/*    */   private GroupHeaderLabel label;
/*    */   
/*    */   public GroupLabelDragSource(GroupHeaderLabel label) {
/* 14 */     this.label = label;
/*    */     
/* 16 */     DragSource dragSource = DragSource.getDefaultDragSource();
/* 17 */     dragSource.createDefaultDragGestureRecognizer(label, 3, this);
/*    */   }
/*    */   
/*    */   public void dragGestureRecognized(DragGestureEvent dge) {
/* 21 */     TableReportHeaderInfo info = this.label.getHeaderInfo();
/*    */     
/* 23 */     if (info == null) {
/*    */       return;
/*    */     }
/*    */     
/* 27 */     Transferable transferable = new HeaderInfoTransferable(info);
/*    */     try {
/* 29 */       dge.startDrag(DragSource.DefaultCopyDrop, transferable, this);
/* 30 */     } catch (Exception e) {
/* 31 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\GroupLabelDragSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */