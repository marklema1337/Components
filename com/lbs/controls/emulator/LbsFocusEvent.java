/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsComponent;
/*    */ import java.awt.Component;
/*    */ import java.awt.event.FocusEvent;
/*    */ import javax.swing.JPanel;
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
/*    */ 
/*    */ public class LbsFocusEvent
/*    */   extends FocusEvent
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private ILbsComponent m_Source;
/*    */   private ILbsComponent m_Opposite;
/*    */   
/*    */   public LbsFocusEvent(ILbsComponent source, int id, boolean temporary, ILbsComponent opposite) {
/* 28 */     super(getDummySource(), id, temporary, (Component)null);
/* 29 */     this.m_Source = source;
/* 30 */     this.m_Opposite = opposite;
/*    */   }
/*    */ 
/*    */   
/*    */   public LbsFocusEvent(ILbsComponent source, int id, boolean temporary) {
/* 35 */     super(getDummySource(), id, temporary);
/* 36 */     this.m_Source = source;
/*    */   }
/*    */ 
/*    */   
/*    */   public LbsFocusEvent(ILbsComponent source, int id) {
/* 41 */     super(getDummySource(), id);
/* 42 */     this.m_Source = source;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getSource() {
/* 47 */     return this.m_Source;
/*    */   }
/*    */ 
/*    */   
/*    */   public ILbsComponent getOpposite() {
/* 52 */     return this.m_Opposite;
/*    */   }
/*    */ 
/*    */   
/*    */   private static Component getDummySource() {
/* 57 */     return new JPanel();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSource(Object newSource) {
/* 62 */     super.setSource(newSource);
/* 63 */     if (newSource instanceof ILbsComponent)
/* 64 */       this.m_Source = (ILbsComponent)newSource; 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsFocusEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */