/*    */ package com.lbs.controls;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Rectangle;
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
/*    */ public class JLbsHorizontalRuler
/*    */   extends JLbsPanel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 19 */   private int m_rulerX = 0;
/* 20 */   private int m_rulerY = 0;
/*    */   
/*    */   private JLbsRichTextEditor_Old m_editor;
/*    */ 
/*    */   
/*    */   public JLbsHorizontalRuler(JLbsRichTextEditor_Old editor, int x, int y) {
/* 26 */     this.m_editor = editor;
/* 27 */     this.m_rulerX = x;
/* 28 */     this.m_rulerY = y;
/*    */   }
/*    */ 
/*    */   
/*    */   public void paint(Graphics graphics) {
/* 33 */     super.paint(graphics);
/* 34 */     if (graphics == null)
/*    */       return; 
/* 36 */     JLbsControlHelper x = new JLbsControlHelper();
/* 37 */     x.paintHorizontalRuler(graphics, new Rectangle(this.m_rulerX, this.m_rulerY, getWidth() - 2, 20), false, this.m_editor.getScrollX());
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsHorizontalRuler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */