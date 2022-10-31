/*    */ package com.lbs.laf.mac;
/*    */ 
/*    */ import com.lbs.laf.common.SkinnedButtonUI;
/*    */ import com.lbs.laf.common.SkinnedCheckBoxIcon;
/*    */ import com.lbs.util.JLbsConstants;
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Rectangle;
/*    */ import javax.swing.Icon;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JRadioButton;
/*    */ import javax.swing.plaf.ComponentUI;
/*    */ import javax.swing.plaf.metal.MetalRadioButtonUI;
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
/*    */ 
/*    */ public class RadioButtonUI
/*    */   extends MetalRadioButtonUI
/*    */ {
/* 31 */   private static final RadioButtonUI ms_MetouiaRadioButtonUI = new RadioButtonUI();
/* 32 */   private static final SkinnedCheckBoxIcon ms_SkinnedIcon = new SkinnedCheckBoxIcon(DefaultTheme.getSkinImage(CheckBoxUI.class, 
/* 33 */         JLbsConstants.DESKTOP_MODE ? 
/* 34 */         "radiobuttondesktop.png" : 
/* 35 */         "radiobutton.png", 8, 0));
/*    */ 
/*    */   
/*    */   public static ComponentUI createUI(JComponent c) {
/* 39 */     if (c instanceof JRadioButton) {
/*    */       
/* 41 */       JRadioButton jb = (JRadioButton)c;
/* 42 */       jb.setRolloverEnabled(true);
/*    */     } 
/* 44 */     return ms_MetouiaRadioButtonUI;
/*    */   }
/*    */ 
/*    */   
/*    */   public void installUI(JComponent arg0) {
/* 49 */     super.installUI(arg0);
/* 50 */     this.icon = (Icon)ms_SkinnedIcon;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintFocus(Graphics g, Rectangle t, Dimension arg2) {
/* 55 */     Graphics2D g2d = (Graphics2D)g;
/* 56 */     g2d.setColor(Color.black);
/* 57 */     g2d.setStroke(SkinnedButtonUI.ms_FocusStroke);
/* 58 */     g2d.drawLine(t.x - 1, t.y - 1, t.x - 1 + t.width + 1, t.y - 1);
/* 59 */     g2d.drawLine(t.x - 1, t.y - 1 + t.height + 1, t.x - 1 + t.width + 1, t.y - 1 + t.height + 1);
/* 60 */     g2d.drawLine(t.x - 1, t.y - 1, t.x - 1, t.y - 1 + t.height + 1);
/* 61 */     g2d.drawLine(t.x - 1 + t.width + 1, t.y - 1, t.x - 1 + t.width + 1, t.y - 1 + t.height + 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\mac\RadioButtonUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */