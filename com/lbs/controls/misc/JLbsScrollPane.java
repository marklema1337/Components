/*     */ package com.lbs.controls.misc;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsScrollPane;
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import java.awt.Component;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JViewport;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsScrollPane
/*     */   extends JScrollPane
/*     */   implements ILbsScrollPane
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public JLbsScrollPane() {}
/*     */   
/*     */   public JLbsScrollPane(Component view) {
/*  29 */     super(view);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsScrollPane(int vPolicy, int hPolicy) {
/*  34 */     super(vPolicy, hPolicy);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsScrollPane(Component view, int vPolicy, int hPolicy) {
/*  39 */     super(view, vPolicy, hPolicy);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsComponent getInnerComponent() {
/*  44 */     if (getComponentCount() > 0)
/*  45 */       return JLbsComponentHelper.getComponent((JComponent)getComponent(0)); 
/*  46 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public final Component getViewportView() {
/*  51 */     return (getViewport() == null) ? 
/*  52 */       null : 
/*  53 */       getViewport().getView();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRowHeaderView(Component value) {
/*  61 */     if (getRowHeader() == null) {
/*     */       
/*  63 */       JViewport v = createViewport();
/*     */       
/*  65 */       if (v instanceof JLbsViewport) {
/*  66 */         ((JLbsViewport)v).setHorizontallyFixed(true);
/*     */       }
/*  68 */       setRowHeader(v);
/*     */     } 
/*     */     
/*  71 */     super.setRowHeaderView(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumnHeaderView(Component value) {
/*  76 */     if (getColumnHeader() == null) {
/*     */       
/*  78 */       JViewport v = createViewport();
/*     */       
/*  80 */       if (v instanceof JLbsViewport) {
/*  81 */         ((JLbsViewport)v).setVerticallyFixed(true);
/*     */       }
/*  83 */       setColumnHeader(v);
/*     */     } 
/*     */     
/*  86 */     super.setColumnHeaderView(value);
/*     */   }
/*     */ 
/*     */   
/*     */   protected JViewport createViewport() {
/*  91 */     return new JLbsViewport();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/*  96 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 101 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 111 */     return getParent();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\misc\JLbsScrollPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */