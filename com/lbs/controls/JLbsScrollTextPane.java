/*    */ package com.lbs.controls;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Dimension;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JTextPane;
/*    */ import javax.swing.JViewport;
/*    */ import javax.swing.plaf.TextUI;
/*    */ import javax.swing.undo.UndoManager;
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
/*    */ public class JLbsScrollTextPane
/*    */   extends JLbsPanel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private JLbsTextPane m_textPane;
/*    */   private JScrollPane m_scrollPane;
/*    */   private boolean wordWrap = false;
/* 28 */   UndoManager manager = new UndoManager();
/*    */ 
/*    */   
/*    */   public JLbsScrollTextPane() {
/* 32 */     setLayout(new BorderLayout());
/*    */     
/* 34 */     this.m_textPane = new JLbsTextPane()
/*    */       {
/*    */         private static final long serialVersionUID = 1L;
/*    */ 
/*    */         
/*    */         public void setSize(Dimension d) {
/* 40 */           if (!JLbsScrollTextPane.this.wordWrap)
/*    */           {
/* 42 */             if (d.width < (getParent().getSize()).width)
/* 43 */               d.width = (getParent().getSize()).width; 
/*    */           }
/* 45 */           super.setSize(d);
/*    */         }
/*    */ 
/*    */         
/*    */         public boolean getScrollableTracksViewportWidth() {
/* 50 */           if (JLbsScrollTextPane.this.wordWrap) {
/*    */             
/* 52 */             JViewport port = (JViewport)getParent();
/* 53 */             TextUI ui = getUI();
/* 54 */             int w = port.getWidth();
/* 55 */             Dimension min = ui.getMinimumSize(this);
/* 56 */             Dimension max = ui.getMaximumSize(this);
/* 57 */             if (w >= min.width && w <= max.width)
/* 58 */               return true; 
/*    */           } 
/* 60 */           return false;
/*    */         }
/*    */       };
/* 63 */     this.m_scrollPane = new JScrollPane(this.m_textPane);
/* 64 */     add(this.m_scrollPane, "Center");
/*    */   }
/*    */ 
/*    */   
/*    */   public JScrollPane getScrollPane() {
/* 69 */     return this.m_scrollPane;
/*    */   }
/*    */ 
/*    */   
/*    */   public JTextPane getTextPane() {
/* 74 */     return this.m_textPane;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isWordWrap() {
/* 79 */     return this.wordWrap;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setWordWrap(boolean wordWrap) {
/* 84 */     this.wordWrap = wordWrap;
/* 85 */     this.m_textPane.updateUI();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsScrollTextPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */