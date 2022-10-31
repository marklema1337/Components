/*    */ package com.lbs.controls.groupbox;
/*    */ 
/*    */ import com.lbs.laf.common.borders.GroupBorder;
/*    */ import com.lbs.laf.mac.ShadowedRoundBorder;
/*    */ import java.awt.Color;
/*    */ import javax.swing.border.AbstractBorder;
/*    */ 
/*    */ public class JLbsRoundGroupBorder
/*    */   extends GroupBorder
/*    */ {
/*    */   public JLbsRoundGroupBorder() {
/* 12 */     super((AbstractBorder)new ShadowedRoundBorder(new Color(10526880), 8, 8));
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\groupbox\JLbsRoundGroupBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */