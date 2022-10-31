/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.SwingUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsMessageBox
/*     */   extends JLbsDialog
/*     */   implements ActionListener
/*     */ {
/*  27 */   private static int emptySpace = 5;
/*  28 */   private static int buttonHeight = 25;
/*  29 */   private static int buttonWidth = 100;
/*  30 */   private static int mainPaneHeight = 300;
/*  31 */   private static int mainPaneWidth = 400;
/*  32 */   private static int secondaryPaneHeight = mainPaneHeight - buttonHeight - emptySpace * 3;
/*  33 */   private static int secondaryPaneWidth = mainPaneWidth - emptySpace * 2;
/*     */ 
/*     */   
/*     */   boolean frameSizeAdjusted = false;
/*     */   
/*  38 */   JScrollPane scrollPane = new JScrollPane();
/*  39 */   JTextArea textArea = new JTextArea();
/*  40 */   JButton okButton = new JButton("OK");
/*     */ 
/*     */   
/*     */   public JLbsMessageBox(Frame parent) {
/*  44 */     super(parent);
/*     */     
/*  46 */     getContentPane().setLayout((LayoutManager)null);
/*  47 */     setSize(mainPaneWidth, mainPaneHeight);
/*  48 */     setVisible(false);
/*  49 */     setModal(true);
/*  50 */     getContentPane().add(this.scrollPane);
/*  51 */     this.scrollPane.setBounds(emptySpace, emptySpace, secondaryPaneWidth, secondaryPaneHeight);
/*  52 */     this.scrollPane.getViewport().add(this.textArea);
/*  53 */     this.textArea.setWrapStyleWord(true);
/*  54 */     this.textArea.setEditable(false);
/*  55 */     this.textArea.setBounds(2, 2, secondaryPaneWidth - 4, secondaryPaneHeight - 4);
/*  56 */     this.textArea.setFont(new Font(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE));
/*  57 */     getContentPane().add(this.okButton);
/*  58 */     this.okButton.setBounds((mainPaneWidth - buttonWidth) / 2, mainPaneHeight - buttonHeight - emptySpace, buttonWidth, buttonHeight);
/*  59 */     this.okButton.addActionListener(this);
/*     */     
/*  61 */     centerScreen();
/*  62 */     this.scrollPane.revalidate();
/*  63 */     SwingUtilities.updateComponentTreeUI(this);
/*  64 */     setResizable(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsMessageBox() {
/*  69 */     this((Frame)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsMessageBox(String sMessage) {
/*  74 */     this("Message", sMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsMessageBox(String sTitle, String sMessage) {
/*  79 */     this();
/*  80 */     setTitle(sTitle);
/*  81 */     this.textArea.setText(sMessage);
/*  82 */     this.textArea.setFont(new Font(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE));
/*     */   }
/*     */   
/*     */   public JLbsMessageBox(String sTitle, String sMessage, int txtFontSize) {
/*  86 */     this();
/*  87 */     setTitle(sTitle);
/*  88 */     setSize(150, 100);
/*  89 */     centerScreen();
/*  90 */     this.textArea.setText("  " + sMessage);
/*  91 */     this.textArea.setFont(new Font(JLbsConstants.APP_FONT, 0, txtFontSize));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean b) {
/*  96 */     if (b)
/*  97 */       setLocation(50, 50); 
/*  98 */     super.setVisible(b);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addNotify() {
/* 104 */     Dimension size = getSize();
/*     */     
/* 106 */     super.addNotify();
/*     */     
/* 108 */     if (this.frameSizeAdjusted)
/*     */       return; 
/* 110 */     this.frameSizeAdjusted = true;
/*     */ 
/*     */     
/* 113 */     Insets insets = getInsets();
/* 114 */     setSize(insets.left + insets.right + size.width, insets.top + insets.bottom + size.height);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent event) {
/* 122 */     Object object = event.getSource();
/* 123 */     if (object == this.okButton) {
/* 124 */       dispose();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setText(String sMessage) {
/* 129 */     this.textArea.setText(sMessage);
/* 130 */     this.textArea.setFont(new Font(JLbsConstants.APP_FONT, getFont().getStyle(), JLbsConstants.APP_FONT_SIZE));
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsMessageBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */