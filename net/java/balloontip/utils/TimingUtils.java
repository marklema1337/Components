/*    */ package net.java.balloontip.utils;
/*    */ 
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.Timer;
/*    */ import net.java.balloontip.BalloonTip;
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
/*    */ 
/*    */ public final class TimingUtils
/*    */ {
/*    */   public static void showTimedBalloon(final BalloonTip balloon, int time) {
/* 37 */     showTimedBalloon(balloon, time, new ActionListener() {
/*    */           public void actionPerformed(ActionEvent e) {
/* 39 */             balloon.closeBalloon();
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void showTimedBalloon(BalloonTip balloon, int time, ActionListener onTimeout) {
/* 51 */     balloon.setVisible(true);
/* 52 */     Timer timer = new Timer(time, onTimeout);
/* 53 */     timer.setRepeats(false);
/* 54 */     timer.start();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\net\java\balloonti\\utils\TimingUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */