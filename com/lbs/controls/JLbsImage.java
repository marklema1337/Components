/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsImage;
/*     */ import com.lbs.image.JLbsImagePane;
/*     */ import com.lbs.image.MouseEventRedirector;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.util.Hashtable;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JLabel;
/*     */ 
/*     */ 
/*     */ public class JLbsImage
/*     */   extends JLbsLabel
/*     */   implements ILbsImage
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JLabel m_Label;
/*     */   private JLbsImagePane m_Pane;
/*     */   private Hashtable m_Redirectors;
/*     */   private boolean m_IsRestricted;
/*     */   private boolean m_IsStretched = true;
/*     */   private String m_HiddenValue;
/*     */   
/*     */   public JLbsImage() {
/*  29 */     this(true);
/*  30 */     this.m_IsRestricted = false;
/*  31 */     if (JLbsConstants.DESKTOP_MODE) {
/*  32 */       setBackground(new Color(232, 245, 236));
/*     */     }
/*     */   }
/*     */   
/*     */   public JLbsImage(boolean streched) {
/*  37 */     setHorizontalAlignment(0);
/*  38 */     setStretched(streched);
/*  39 */     if (JLbsConstants.DESKTOP_MODE) {
/*  40 */       setBackground(new Color(232, 245, 236));
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized boolean isFocusable() {
/*  45 */     return (this.m_Pane != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void setText(String text) {
/*  50 */     if (this.m_Label != null) {
/*  51 */       this.m_Label.setText(text);
/*     */     } else {
/*  53 */       super.setText(text);
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void setIcon(Icon icon) {
/*  58 */     if (this.m_Pane != null) {
/*  59 */       this.m_Pane.setIcon(icon);
/*     */     } else {
/*     */       
/*  62 */       super.setIcon(icon);
/*  63 */       super.setText("");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void addMouseListener(MouseListener l) {
/*  69 */     super.addMouseListener(l);
/*  70 */     if (l != null && this.m_Pane != null) {
/*     */       
/*  72 */       MouseEventRedirector redirector = new MouseEventRedirector(this, l);
/*  73 */       this.m_Redirectors.put(l, redirector);
/*  74 */       this.m_Label.addMouseListener((MouseListener)redirector);
/*  75 */       this.m_Pane.addMouseListener((MouseListener)redirector);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void removeMouseListener(MouseListener l) {
/*  81 */     super.removeMouseListener(l);
/*  82 */     if (l != null && this.m_Pane != null) {
/*     */       
/*  84 */       MouseEventRedirector redirector = (MouseEventRedirector)this.m_Redirectors.get(l);
/*  85 */       if (redirector != null) {
/*     */         
/*  87 */         this.m_Redirectors.remove(l);
/*  88 */         this.m_Label.removeMouseListener((MouseListener)redirector);
/*  89 */         this.m_Pane.removeMouseListener((MouseListener)redirector);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRestricted() {
/*  96 */     return this.m_IsRestricted;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRestricted(boolean isRestricted) {
/* 101 */     this.m_IsRestricted = isRestricted;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 106 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 111 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 121 */     return getParent();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void setStretched(boolean stretched) {
/* 126 */     this.m_IsStretched = stretched;
/* 127 */     if (!this.m_IsStretched) {
/*     */       
/* 129 */       setLayout(new BorderLayout());
/* 130 */       this.m_Label = new JLabel();
/* 131 */       add(this.m_Label, "North");
/* 132 */       this.m_Pane = new JLbsImagePane();
/* 133 */       add((Component)this.m_Pane, "Center");
/* 134 */       this.m_Redirectors = new Hashtable<>();
/*     */     }
/*     */     else {
/*     */       
/* 138 */       if (this.m_Label != null) {
/*     */         
/* 140 */         remove(this.m_Label);
/* 141 */         this.m_Label = null;
/*     */       } 
/* 143 */       if (this.m_Pane != null) {
/*     */         
/* 145 */         remove((Component)this.m_Pane);
/* 146 */         this.m_Pane = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStretched() {
/* 153 */     return this.m_IsStretched;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHiddenValue() {
/* 158 */     return this.m_HiddenValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHiddenValue(String hiddenValue) {
/* 163 */     this.m_HiddenValue = hiddenValue;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsImage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */