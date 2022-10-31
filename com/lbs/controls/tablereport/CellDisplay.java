/*    */ package com.lbs.controls.tablereport;
/*    */ 
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
/*    */ 
/*    */ public class CellDisplay
/*    */ {
/* 16 */   private Color background = Color.white;
/* 17 */   private Color foreground = Color.black;
/*    */   
/*    */   public CellDisplay(Color back, Color fore) {
/* 20 */     this.background = back;
/* 21 */     this.foreground = fore;
/*    */   }
/*    */ 
/*    */   
/*    */   public Color getBackground() {
/* 26 */     return this.background;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBackground(Color background) {
/* 31 */     this.background = background;
/*    */   }
/*    */ 
/*    */   
/*    */   public Color getForeground() {
/* 36 */     return this.foreground;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setForeground(Color foreground) {
/* 41 */     this.foreground = foreground;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\CellDisplay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */