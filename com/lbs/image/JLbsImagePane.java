/*    */ package com.lbs.image;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Image;
/*    */ import java.awt.event.MouseListener;
/*    */ import javax.swing.Icon;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JScrollPane;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsImagePane
/*    */   extends JScrollPane
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private JLbsImagePreview m_ImageCtrl;
/*    */   
/*    */   public JLbsImagePane() {
/* 20 */     super(20, 30);
/* 21 */     this.m_ImageCtrl = new JLbsImagePreview();
/* 22 */     JPanel panel = new JPanel();
/* 23 */     panel.setLayout(new CenterLayout());
/* 24 */     panel.add(this.m_ImageCtrl);
/* 25 */     getViewport().add(panel);
/* 26 */     setPreferredSize(new Dimension(100, 100));
/*    */   }
/*    */ 
/*    */   
/*    */   public Image getImage() {
/* 31 */     return (this.m_ImageCtrl != null) ? this.m_ImageCtrl.getImage() : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setImage(Image img) {
/* 36 */     if (this.m_ImageCtrl != null) {
/*    */       
/* 38 */       this.m_ImageCtrl.setImage(img);
/* 39 */       revalidate();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIcon(Icon icon) {
/* 45 */     if (icon instanceof ImageIcon) {
/* 46 */       setImage(((ImageIcon)icon).getImage());
/* 47 */     } else if (icon == null) {
/* 48 */       setImage((Image)null);
/*    */     } 
/*    */   }
/*    */   
/*    */   public synchronized void addMouseListener(MouseListener l) {
/* 53 */     super.addMouseListener(l);
/* 54 */     this.m_ImageCtrl.addMouseListener(l);
/* 55 */     getViewport().addMouseListener(l);
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized void removeMouseListener(MouseListener l) {
/* 60 */     super.removeMouseListener(l);
/* 61 */     this.m_ImageCtrl.removeMouseListener(l);
/* 62 */     getViewport().removeMouseListener(l);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\image\JLbsImagePane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */