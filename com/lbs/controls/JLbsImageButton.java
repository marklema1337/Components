/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsImageButton;
/*     */ import com.lbs.util.ILbsCaptionTag;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.border.BevelBorder;
/*     */ import javax.swing.border.Border;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsImageButton
/*     */   extends JLbsButton
/*     */   implements MouseListener, ILbsImageButton, ILbsCaptionTag
/*     */ {
/*     */   public static boolean CUSTOM_BORDERS = true;
/*     */   private static final long serialVersionUID = 1L;
/*  31 */   public static Border m_normal = new BevelBorder(0);
/*  32 */   public static Border m_pressed = new BevelBorder(1);
/*     */   
/*     */   private boolean m_Fixed = false;
/*     */   
/*     */   private Icon m_NormalIcon;
/*     */   
/*     */   private Icon m_HighlightIcon;
/*     */   
/*     */   private int m_Tag;
/*     */ 
/*     */   
/*     */   public JLbsImageButton() {
/*  44 */     setContentAreaFilled(false);
/*     */ 
/*     */ 
/*     */     
/*  48 */     if (CUSTOM_BORDERS) {
/*     */       
/*  50 */       setBorder(m_normal);
/*  51 */       setBorderPainted(false);
/*     */     } 
/*     */     
/*  54 */     reinitialize();
/*     */     
/*  56 */     addMouseListener(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reinitialize() {
/*  63 */     putClientProperty("JToolBar.isToolbarButton", Boolean.TRUE);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseClicked(MouseEvent evt) {}
/*     */ 
/*     */   
/*     */   public void mousePressed(MouseEvent evt) {
/*  72 */     if (CUSTOM_BORDERS) {
/*  73 */       setBorder(m_pressed);
/*     */     } else {
/*  75 */       getModel().setPressed(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void mouseReleased(MouseEvent evt) {
/*  80 */     if (CUSTOM_BORDERS) {
/*  81 */       setBorder(m_normal);
/*     */     } else {
/*  83 */       getModel().setPressed(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void mouseEntered(MouseEvent evt) {
/*  88 */     if (!isEnabled()) {
/*     */       return;
/*     */     }
/*  91 */     if (CUSTOM_BORDERS) {
/*     */       
/*  93 */       setBorderPainted(true);
/*  94 */       if (JLbsConstants.DESKTOP_MODE && this.m_HighlightIcon != null) {
/*  95 */         setSuperIcon(this.m_HighlightIcon);
/*     */       }
/*     */     } else {
/*  98 */       getModel().setSelected(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void mouseExited(MouseEvent evt) {
/* 103 */     if (CUSTOM_BORDERS) {
/*     */       
/* 105 */       setBorderPainted(false);
/* 106 */       if (JLbsConstants.DESKTOP_MODE) {
/* 107 */         setSuperIcon(this.m_NormalIcon);
/*     */       }
/*     */     } else {
/* 110 */       getModel().setSelected(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIcon(Icon arg0) {
/* 118 */     this.m_NormalIcon = arg0;
/* 119 */     super.setIcon(arg0);
/* 120 */     if (arg0 != null && !this.m_Fixed) {
/*     */       
/* 122 */       Dimension size = getPreferredSize();
/* 123 */       Dimension newSize = new Dimension(size.width, size.height);
/* 124 */       setPreferredSize(newSize);
/* 125 */       this.m_Fixed = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTag() {
/* 131 */     return this.m_Tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTag(int tag) {
/* 136 */     this.m_Tag = tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHighlightIcon(Icon arg0) {
/* 141 */     this.m_HighlightIcon = arg0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSuperIcon(Icon icon) {
/* 146 */     super.setIcon(icon);
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getNormalIcon() {
/* 151 */     return this.m_NormalIcon;
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getHighlightIcon() {
/* 156 */     return this.m_HighlightIcon;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsImageButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */