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
/*    */ public class JLbsVerticalRuler
/*    */   extends JLbsPanel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 19 */   private int m_rulerX = 0;
/* 20 */   private int m_rulerY = 0;
/*    */   
/*    */   private JLbsRichTextEditor_Old m_editor;
/*    */ 
/*    */   
/*    */   public JLbsVerticalRuler(JLbsRichTextEditor_Old editor, int x, int y) {
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
/* 37 */     x.paintVerticalRuler(graphics, new Rectangle(this.m_rulerX, this.m_rulerY, 20, getHeight() - 2), false, this.m_editor.getScrollY());
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsVerticalRuler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */