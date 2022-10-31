/*    */ package com.lbs.image;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Image;
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.Action;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JRootPane;
/*    */ import javax.swing.KeyStroke;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsImagePreviewDlg
/*    */   extends JDialog
/*    */   implements Action
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public JLbsImagePreviewDlg(String caption, Image img) {
/* 23 */     setTitle(caption);
/* 24 */     setDefaultCloseOperation(2);
/* 25 */     JPanel contPanel = new JPanel();
/* 26 */     contPanel.setLayout(new BorderLayout());
/* 27 */     JLbsImagePane pane = new JLbsImagePane();
/* 28 */     pane.setImage(img);
/* 29 */     pane.setPreferredSize(new Dimension(400, 300));
/* 30 */     contPanel.add(pane, "Center");
/* 31 */     pane.revalidate();
/* 32 */     setContentPane(contPanel);
/*    */     
/* 34 */     addShortcut(KeyStroke.getKeyStroke(27, 0, true), "CloseAction", this);
/* 35 */     pack();
/* 36 */     centerScreen();
/*    */   }
/*    */ 
/*    */   
/*    */   public void centerScreen() {
/* 41 */     Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 42 */     int x = (scrSize.width - getWidth()) / 2;
/* 43 */     int y = (scrSize.height - getHeight()) / 2;
/* 44 */     setLocation(x, y);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void addShortcut(KeyStroke ks, String action, Action listener) {
/* 49 */     JRootPane rootPane = getRootPane();
/* 50 */     rootPane.getInputMap().put(ks, action);
/* 51 */     rootPane.getActionMap().put(action, listener);
/* 52 */     rootPane.registerKeyboardAction(listener, ks, 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void actionPerformed(ActionEvent e) {
/* 57 */     dispose();
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getValue(String key) {
/* 62 */     return null;
/*    */   }
/*    */   
/*    */   public void putValue(String key, Object value) {}
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\image\JLbsImagePreviewDlg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */