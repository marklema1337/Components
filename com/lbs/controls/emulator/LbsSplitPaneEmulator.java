/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsSplitPane;
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
/*     */ public class LbsSplitPaneEmulator
/*     */   extends LbsPanelEmulator
/*     */   implements ILbsSplitPane
/*     */ {
/*     */   private int m_DividerLocation;
/*     */   private int m_DividerSize;
/*     */   private int m_LastDividerLocation;
/*     */   private int m_Orientation;
/*     */   private double m_ResizeWeight;
/*     */   
/*     */   public LbsSplitPaneEmulator(int newOrientation) {
/*  27 */     setOrientation(newOrientation);
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsSplitPaneEmulator() {
/*  32 */     this(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLeftComponent(LbsComponentEmulator leftComponent) {
/*  37 */     add(leftComponent, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRightComponent(LbsComponentEmulator rightComponent) {
/*  42 */     add(rightComponent, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDividerLocation() {
/*  47 */     return this.m_DividerLocation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDividerSize() {
/*  52 */     return this.m_DividerSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLastDividerLocation() {
/*  57 */     return this.m_LastDividerLocation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaximumDividerLocation() {
/*  62 */     return this.m_DividerLocation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinimumDividerLocation() {
/*  67 */     return this.m_DividerLocation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOrientation() {
/*  72 */     return this.m_Orientation;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getResizeWeight() {
/*  77 */     return this.m_ResizeWeight;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isContinuousLayout() {
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOneTouchExpandable() {
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetToPreferredSizes() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContinuousLayout(boolean newContinuousLayout) {}
/*     */ 
/*     */   
/*     */   public void setDividerLocation(int location) {
/* 100 */     this.m_DividerLocation = location;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDividerLocation(double location) {
/* 105 */     this.m_DividerLocation = (int)location;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDividerSize(int size) {
/* 110 */     this.m_DividerSize = size;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLastDividerLocation(int newLastLocation) {
/* 115 */     this.m_LastDividerLocation = newLastLocation;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOneTouchExpandable(boolean newValue) {}
/*     */ 
/*     */   
/*     */   public void setOrientation(int orientation) {
/* 124 */     this.m_Orientation = orientation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setResizeWeight(double value) {
/* 129 */     this.m_ResizeWeight = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRightComponent(ILbsComponent comp) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeftComponent(ILbsComponent comp) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getRightComp() {
/* 150 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getLeftComp() {
/* 157 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsSplitPaneEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */