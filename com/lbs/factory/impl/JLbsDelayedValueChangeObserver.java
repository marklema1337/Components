/*    */ package com.lbs.factory.impl;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsTextEdit;
/*    */ import com.lbs.controls.maskededit.JLbsTextEdit;
/*    */ import com.lbs.factory.interfaces.ILbsDelayedValueChangeObserver;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.KeyAdapter;
/*    */ import java.awt.event.KeyEvent;
/*    */ import java.util.function.Consumer;
/*    */ import javax.swing.Timer;
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
/*    */ public class JLbsDelayedValueChangeObserver
/*    */   implements ILbsDelayedValueChangeObserver
/*    */ {
/*    */   private Timer timer;
/*    */   
/*    */   public void register(ILbsTextEdit textedit, int delay, Consumer<ILbsTextEdit> eventConsumer) {
/* 29 */     if (!(textedit instanceof JLbsTextEdit)) {
/* 30 */       throw new IllegalArgumentException(String.valueOf(JLbsTextEdit.class.getName()) + " expected.");
/*    */     }
/* 32 */     this.timer = new Timer(delay, e -> {
/*    */           this.timer.stop();
/*    */           
/*    */           paramConsumer.accept(paramILbsTextEdit);
/*    */         });
/* 37 */     textedit.addKeyListener(new KeyAdapter()
/*    */         {
/*    */           
/*    */           public void keyReleased(KeyEvent e)
/*    */           {
/* 42 */             if (JLbsDelayedValueChangeObserver.this.timer.isRunning()) {
/* 43 */               JLbsDelayedValueChangeObserver.this.timer.restart();
/*    */             } else {
/*    */               
/* 46 */               JLbsDelayedValueChangeObserver.this.timer.start();
/*    */             } 
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\factory\impl\JLbsDelayedValueChangeObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */