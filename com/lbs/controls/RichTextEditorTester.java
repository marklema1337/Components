/*    */ package com.lbs.controls;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JFrame;
/*    */ 
/*    */ 
/*    */ public class RichTextEditorTester
/*    */ {
/* 12 */   static final JLbsRichTextEditor myEditor = new JLbsRichTextEditor();
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 17 */     JFrame myFrame = new JFrame();
/* 18 */     myFrame.setDefaultCloseOperation(3);
/* 19 */     myFrame.setSize(800, 600);
/* 20 */     myFrame.getContentPane().setLayout(new BorderLayout());
/* 21 */     myFrame.getContentPane().add(myEditor, "Center");
/*    */     
/* 23 */     JButton btn = new JButton();
/* 24 */     btn.addActionListener(new ActionListener()
/*    */         {
/*    */           
/*    */           public void actionPerformed(ActionEvent e)
/*    */           {
/* 29 */             System.out.println(RichTextEditorTester.myEditor.getText());
/*    */           }
/*    */         });
/*    */     
/* 33 */     myFrame.getContentPane().add(btn, "South");
/* 34 */     myFrame.setVisible(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\RichTextEditorTester.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */