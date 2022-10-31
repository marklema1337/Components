/*    */ package com.lbs.controls.emulator;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsMaskedEdit;
/*    */ import com.lbs.control.interfaces.ILbsPrefixPanel;
/*    */ import java.awt.Color;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsPrefixPanelEmulator
/*    */   extends LbsPanelEmulator
/*    */   implements ILbsPrefixPanel
/*    */ {
/*    */   private ILbsMaskedEdit m_InnerComponent;
/*    */   private String m_Prefix;
/* 21 */   private int m_PrefixOffset = 0;
/*    */ 
/*    */   
/*    */   public LbsPrefixPanelEmulator() {
/* 25 */     this(new LbsMaskedEditEmulator());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public LbsPrefixPanelEmulator(ILbsMaskedEdit innerComponent) {
/* 31 */     this.m_InnerComponent = innerComponent;
/*    */   }
/*    */ 
/*    */   
/*    */   public ILbsMaskedEdit getFillControl() {
/* 36 */     return this.m_InnerComponent;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPrefix() {
/* 41 */     return this.m_Prefix;
/*    */   }
/*    */ 
/*    */   
/*    */   public Color getPrefixColor() {
/* 46 */     return Color.white;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPrefixOfset() {
/* 51 */     return this.m_PrefixOffset;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPrefix(String prefix) {
/* 56 */     this.m_Prefix = prefix;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPrefixColor(Color color) {}
/*    */ 
/*    */   
/*    */   public void setPrefixOfset(int i) {
/* 65 */     this.m_PrefixOffset = i;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFocusManager(LbsFocusManager focusManager) {
/* 70 */     super.setFocusManager(focusManager);
/* 71 */     ((LbsComponentEmulator)getFillControl()).setFocusManager(focusManager);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsPrefixPanelEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */