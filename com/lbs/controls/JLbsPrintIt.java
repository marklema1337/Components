/*     */ package com.lbs.controls;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.print.PageFormat;
/*     */ import java.awt.print.Printable;
/*     */ import java.awt.print.PrinterException;
/*     */ import java.awt.print.PrinterJob;
/*     */ import javax.swing.JTextPane;
/*     */ import javax.swing.text.Document;
/*     */ import javax.swing.text.View;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsPrintIt
/*     */   implements Printable
/*     */ {
/*  26 */   int m_currentPage = -1;
/*  27 */   double m_pageEndY = 0.0D;
/*  28 */   double m_pageStartY = 0.0D;
/*     */   
/*     */   boolean m_scaleWidthToFit = true;
/*     */   JTextPane m_printPane;
/*     */   PageFormat m_pFormat;
/*     */   PrinterJob m_pJob;
/*     */   
/*     */   public JLbsPrintIt() {
/*  36 */     this.m_pFormat = new PageFormat();
/*  37 */     this.m_pJob = PrinterJob.getPrinterJob();
/*     */   }
/*     */ 
/*     */   
/*     */   public Document getDocument() {
/*  42 */     if (this.m_printPane != null)
/*     */     {
/*  44 */       return this.m_printPane.getDocument();
/*     */     }
/*     */ 
/*     */     
/*  48 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getScaleWidthToFit() {
/*  54 */     return this.m_scaleWidthToFit;
/*     */   }
/*     */ 
/*     */   
/*     */   public void pageDialog() {
/*  59 */     this.m_pFormat = this.m_pJob.pageDialog(this.m_pFormat);
/*     */   }
/*     */ 
/*     */   
/*     */   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
/*  64 */     double scale = 1.0D;
/*     */     
/*  66 */     Graphics2D graphics2D = (Graphics2D)graphics;
/*     */     
/*  68 */     this.m_printPane.setSize((int)pageFormat.getImageableWidth(), 2147483647);
/*  69 */     this.m_printPane.validate();
/*  70 */     View rootView = this.m_printPane.getUI().getRootView(this.m_printPane);
/*  71 */     if (this.m_scaleWidthToFit && this.m_printPane.getMinimumSize().getWidth() > pageFormat.getImageableWidth()) {
/*     */       
/*  73 */       scale = pageFormat.getImageableWidth() / this.m_printPane.getMinimumSize().getWidth();
/*  74 */       graphics2D.scale(scale, scale);
/*     */     } 
/*  76 */     graphics2D.setClip((int)(pageFormat.getImageableX() / scale), (int)(pageFormat.getImageableY() / scale), 
/*  77 */         (int)(pageFormat.getImageableWidth() / scale), (int)(pageFormat.getImageableHeight() / scale));
/*  78 */     if (pageIndex > this.m_currentPage) {
/*     */       
/*  80 */       this.m_currentPage = pageIndex;
/*  81 */       this.m_pageStartY += this.m_pageEndY;
/*  82 */       this.m_pageEndY = graphics2D.getClipBounds().getHeight();
/*     */     } 
/*  84 */     graphics2D.translate(graphics2D.getClipBounds().getX(), graphics2D.getClipBounds().getY());
/*  85 */     Rectangle allocation = new Rectangle(0, (int)-this.m_pageStartY, (int)this.m_printPane.getMinimumSize().getWidth(), 
/*  86 */         (int)this.m_printPane.getPreferredSize().getHeight());
/*  87 */     if (printView(graphics2D, allocation, rootView))
/*     */     {
/*  89 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*  93 */     this.m_pageStartY = 0.0D;
/*  94 */     this.m_pageEndY = 0.0D;
/*  95 */     this.m_currentPage = -1;
/*  96 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void print(JTextPane pane) {
/* 102 */     setDocument(pane);
/* 103 */     printDialog();
/*     */   }
/*     */ 
/*     */   
/*     */   private void printDialog() {
/* 108 */     if (this.m_pJob.printDialog()) {
/*     */       
/* 110 */       this.m_pJob.setPrintable(this, this.m_pFormat);
/*     */       
/*     */       try {
/* 113 */         this.m_pJob.print();
/*     */       }
/* 115 */       catch (PrinterException printerException) {
/*     */         
/* 117 */         this.m_pageStartY = 0.0D;
/* 118 */         this.m_pageEndY = 0.0D;
/* 119 */         this.m_currentPage = -1;
/* 120 */         System.out.println("Error Printing Document");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean printView(Graphics2D graphics2D, Shape allocation, View view) {
/* 127 */     boolean pageExists = false;
/* 128 */     Rectangle clipRectangle = graphics2D.getClipBounds();
/*     */ 
/*     */     
/* 131 */     if (view.getViewCount() > 0) {
/*     */       
/* 133 */       for (int i = 0; i < view.getViewCount(); i++)
/*     */       {
/* 135 */         Shape childAllocation = view.getChildAllocation(i, allocation);
/* 136 */         if (childAllocation != null)
/*     */         {
/* 138 */           View childView = view.getView(i);
/* 139 */           if (printView(graphics2D, childAllocation, childView))
/*     */           {
/* 141 */             pageExists = true;
/*     */           }
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 148 */     else if (allocation.getBounds().getMaxY() >= clipRectangle.getY()) {
/*     */       
/* 150 */       pageExists = true;
/* 151 */       if (allocation.getBounds().getHeight() > clipRectangle.getHeight() && allocation.intersects(clipRectangle)) {
/*     */         
/* 153 */         view.paint(graphics2D, allocation);
/*     */ 
/*     */       
/*     */       }
/* 157 */       else if (allocation.getBounds().getY() >= clipRectangle.getY()) {
/*     */         
/* 159 */         if (allocation.getBounds().getMaxY() <= clipRectangle.getMaxY()) {
/*     */           
/* 161 */           view.paint(graphics2D, allocation);
/*     */ 
/*     */         
/*     */         }
/* 165 */         else if (allocation.getBounds().getY() < this.m_pageEndY) {
/*     */           
/* 167 */           this.m_pageEndY = allocation.getBounds().getY();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 174 */     return pageExists;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setContentType(String type) {
/* 179 */     this.m_printPane.setContentType(type);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setDocument(JTextPane pane) {
/* 184 */     this.m_printPane = new JTextPane();
/* 185 */     setDocument(pane.getContentType(), pane.getDocument());
/*     */   }
/*     */ 
/*     */   
/*     */   private void setDocument(String type, Document document) {
/* 190 */     setContentType(type);
/* 191 */     this.m_printPane.setDocument(document);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setScaleWidthToFit(boolean scaleWidth) {
/* 196 */     this.m_scaleWidthToFit = scaleWidth;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsPrintIt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */