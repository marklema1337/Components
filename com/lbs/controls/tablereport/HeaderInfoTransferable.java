/*    */ package com.lbs.controls.tablereport;
/*    */ 
/*    */ import java.awt.datatransfer.DataFlavor;
/*    */ import java.awt.datatransfer.Transferable;
/*    */ import java.awt.datatransfer.UnsupportedFlavorException;
/*    */ import java.io.IOException;
/*    */ import java.util.Arrays;
/*    */ 
/*    */ public class HeaderInfoTransferable
/*    */   implements Transferable {
/*    */   private TableReportHeaderInfo info;
/* 12 */   private static final DataFlavor[] flavors = new DataFlavor[] { TableReportConstants.headerInfoFlavor };
/*    */   
/*    */   public HeaderInfoTransferable(TableReportHeaderInfo info) {
/* 15 */     this.info = info;
/*    */   }
/*    */   
/*    */   public DataFlavor[] getTransferDataFlavors() {
/* 19 */     return flavors;
/*    */   }
/*    */   
/*    */   public boolean isDataFlavorSupported(DataFlavor flavor) {
/* 23 */     return Arrays.<DataFlavor>asList(flavors).contains(flavor);
/*    */   }
/*    */   
/*    */   public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
/* 27 */     if (!isDataFlavorSupported(flavor)) {
/* 28 */       return null;
/*    */     }
/*    */     
/* 31 */     return this.info;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\HeaderInfoTransferable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */