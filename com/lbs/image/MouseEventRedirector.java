/*    */ package com.lbs.image;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
/*    */ import javax.swing.JComponent;
/*    */ 
/*    */ 
/*    */ public class MouseEventRedirector
/*    */   implements MouseListener
/*    */ {
/*    */   private JComponent m_Target;
/*    */   private MouseListener m_Listener;
/*    */   
/*    */   public MouseEventRedirector(JComponent comp, MouseListener l) {
/* 16 */     this.m_Target = comp;
/* 17 */     this.m_Listener = l;
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseClicked(MouseEvent e) {
/* 22 */     MouseEvent m = retargetEvent(e);
/* 23 */     this.m_Listener.mouseClicked(m);
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseEntered(MouseEvent e) {
/* 28 */     MouseEvent m = retargetEvent(e);
/* 29 */     this.m_Listener.mouseEntered(m);
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseExited(MouseEvent e) {
/* 34 */     MouseEvent m = retargetEvent(e);
/* 35 */     this.m_Listener.mouseExited(m);
/*    */   }
/*    */ 
/*    */   
/*    */   public void mousePressed(MouseEvent e) {
/* 40 */     MouseEvent m = retargetEvent(e);
/* 41 */     this.m_Listener.mousePressed(m);
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseReleased(MouseEvent e) {
/* 46 */     MouseEvent m = retargetEvent(e);
/* 47 */     this.m_Listener.mouseReleased(m);
/*    */   }
/*    */ 
/*    */   
/*    */   private MouseEvent retargetEvent(MouseEvent e) {
/* 52 */     Component heavySource = (Component)e.getSource();
/* 53 */     if (heavySource == this.m_Target) return e; 
/* 54 */     int x = e.getX();
/* 55 */     int y = e.getY();
/* 56 */     Component target = heavySource;
/* 57 */     while (target != this.m_Target) {
/*    */       
/* 59 */       x -= target.getX();
/* 60 */       y -= target.getY();
/* 61 */       target = target.getParent();
/*    */     } 
/* 63 */     if (target == null || target != this.m_Target || target == heavySource) {
/* 64 */       return e;
/*    */     }
/* 66 */     MouseEvent redirected = new MouseEvent(target, e.getID(), e.getWhen(), e.getModifiers(), x, y, e.getClickCount(), e.isPopupTrigger());
/* 67 */     return redirected;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\image\MouseEventRedirector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */