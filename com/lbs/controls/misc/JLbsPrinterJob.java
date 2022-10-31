/*    */ package com.lbs.controls.misc;
/*    */ 
/*    */ import com.lbs.interfaces.ILbsPrinterJob;
/*    */ import java.awt.print.Printable;
/*    */ import java.awt.print.PrinterAbortException;
/*    */ import java.awt.print.PrinterJob;
/*    */ import java.io.File;
/*    */ import javax.print.PrintService;
/*    */ import javax.print.attribute.PrintRequestAttributeSet;
/*    */ import org.apache.pdfbox.pdmodel.PDDocument;
/*    */ import org.apache.pdfbox.printing.PDFPrintable;
/*    */ import org.apache.pdfbox.printing.Scaling;
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
/*    */ 
/*    */ public class JLbsPrinterJob
/*    */   implements ILbsPrinterJob
/*    */ {
/* 27 */   private final PrinterJob printerJob = PrinterJob.getPrinterJob();
/*    */ 
/*    */   
/*    */   private boolean aborted;
/*    */ 
/*    */   
/*    */   public boolean printDialog(Object attributes) {
/* 34 */     return this.printerJob.printDialog((PrintRequestAttributeSet)attributes);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void print(Object attributes, String filePath) {
/*    */     try {
/* 42 */       PrintService service = this.printerJob.getPrintService();
/* 43 */       PDDocument document = PDDocument.load(new File(filePath));
/* 44 */       PDFPrintable printable = new PDFPrintable(document, Scaling.SCALE_TO_FIT);
/* 45 */       this.printerJob.setPrintable((Printable)printable);
/* 46 */       this.printerJob.setPrintService(service);
/* 47 */       this.printerJob.print((PrintRequestAttributeSet)attributes);
/* 48 */       document.close();
/*    */     }
/* 50 */     catch (PrinterAbortException e) {
/*    */       
/* 52 */       this.aborted = true;
/* 53 */       throw new RuntimeException(e);
/*    */     }
/* 55 */     catch (Exception e) {
/*    */       
/* 57 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isPrintAborted() {
/* 64 */     return this.aborted;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\misc\JLbsPrinterJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */