/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsSplitPane;
/*     */ import java.awt.AWTKeyStroke;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.util.Set;
/*     */ import javax.swing.JSplitPane;
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
/*     */ public class JLbsSplitPane
/*     */   extends JSplitPane
/*     */   implements ILbsSplitPane
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private boolean painted = true;
/*     */   private double dividerProportionalLocation;
/*     */   
/*     */   public boolean isPainted() {
/*  29 */     return this.painted;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPainted(boolean painted) {
/*  34 */     this.painted = painted;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsSplitPane(int newOrientation, Component newLeftComponent, Component newRightComponent) {
/*  39 */     super(newOrientation, newLeftComponent, newRightComponent);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsSplitPane(int newOrientation) {
/*  44 */     super(newOrientation);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsSplitPane() {
/*  49 */     super(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g) {
/*  54 */     super.paint(g);
/*     */     
/*  56 */     if (!this.painted) {
/*     */       
/*  58 */       this.painted = true;
/*  59 */       setDividerLocation(this.dividerProportionalLocation);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addImpl(Component comp, Object constraints, int index) {
/*  65 */     super.addImpl(comp, constraints, index);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/*  70 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/*  75 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/*  86 */     return getParent();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/*  91 */     super.updateUI();
/*  92 */     setFocusTraversalKeys(0, (Set<? extends AWTKeyStroke>)null);
/*  93 */     setFocusTraversalKeys(1, (Set<? extends AWTKeyStroke>)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDividerProportionalLocation() {
/*  98 */     return this.dividerProportionalLocation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDividerProportionalLocation(double dividerProportionalLocation) {
/* 103 */     this.dividerProportionalLocation = dividerProportionalLocation;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRightComponent(ILbsComponent comp) {
/* 109 */     if (comp instanceof Component) {
/* 110 */       setRightComponent((Component)comp);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeftComponent(ILbsComponent comp) {
/* 117 */     if (comp instanceof Component) {
/* 118 */       setLeftComponent((Component)comp);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getRightComp() {
/* 125 */     if (getRightComponent() instanceof ILbsComponent) {
/* 126 */       return (ILbsComponent)getRightComponent();
/*     */     }
/* 128 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getLeftComp() {
/* 134 */     if (getRightComponent() instanceof ILbsComponent) {
/* 135 */       return (ILbsComponent)getRightComponent();
/*     */     }
/* 137 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsSplitPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */