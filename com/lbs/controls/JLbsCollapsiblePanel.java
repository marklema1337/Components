/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.controls.groupbox.JLbsGroupBorder;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.border.Border;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsCollapsiblePanel
/*     */   extends JLbsPanel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected HeaderPanel m_HeaderPanel;
/*     */   
/*     */   public JLbsCollapsiblePanel(JPanel content, String title) {
/*  45 */     setLayout(new GridBagLayout());
/*  46 */     setBorder((Border)new JLbsGroupBorder());
/*  47 */     GridBagConstraints gbc = new GridBagConstraints();
/*  48 */     gbc.insets = new Insets(1, 3, 0, 3);
/*  49 */     gbc.weightx = 1.0D;
/*  50 */     gbc.fill = 2;
/*  51 */     gbc.gridwidth = 0;
/*  52 */     this.m_HeaderPanel = new HeaderPanel(title, content);
/*  53 */     add(this.m_HeaderPanel, gbc);
/*  54 */     add(content, gbc);
/*  55 */     content.setVisible(false);
/*  56 */     JLabel padding = new JLabel();
/*  57 */     gbc.weighty = 1.0D;
/*  58 */     add(padding, gbc);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTitle(String text) {
/*  63 */     if (this.m_HeaderPanel != null) {
/*     */       
/*  65 */       this.m_HeaderPanel.setText(text);
/*  66 */       validate();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeaderWidth(int width) {
/*  72 */     if (this.m_HeaderPanel != null) {
/*     */       
/*  74 */       this.m_HeaderPanel.setPreferredSize(new Dimension(width, 20));
/*  75 */       validate();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*  81 */     GridBagConstraints gbc = new GridBagConstraints();
/*  82 */     gbc.insets = new Insets(2, 1, 2, 1);
/*  83 */     gbc.weightx = 1.0D;
/*  84 */     gbc.weighty = 1.0D;
/*  85 */     JPanel p1 = new JPanel(new GridBagLayout());
/*  86 */     gbc.gridwidth = -1;
/*  87 */     p1.add(new JButton("button 1"), gbc);
/*  88 */     gbc.gridwidth = 0;
/*  89 */     p1.add(new JButton("button 2"), gbc);
/*  90 */     gbc.gridwidth = -1;
/*  91 */     p1.add(new JButton("button 3"), gbc);
/*  92 */     gbc.gridwidth = 0;
/*  93 */     p1.add(new JButton("button 4"), gbc);
/*     */     
/*  95 */     JPanel p2 = new JPanel(new GridBagLayout());
/*  96 */     gbc.gridwidth = -1;
/*  97 */     p2.add(new JButton("button 11"), gbc);
/*  98 */     gbc.gridwidth = 0;
/*  99 */     p2.add(new JButton("button 22"), gbc);
/* 100 */     gbc.gridwidth = -1;
/* 101 */     p2.add(new JButton("button 32"), gbc);
/* 102 */     gbc.gridwidth = 0;
/* 103 */     p2.add(new JButton("button 42"), gbc);
/*     */     
/* 105 */     JFrame f = new JFrame();
/* 106 */     f.setDefaultCloseOperation(3);
/*     */     
/* 108 */     JLbsCollapsiblePanel pnl = new JLbsCollapsiblePanel(p1, "Deneme");
/* 109 */     JLbsCollapsiblePanel pnl2 = new JLbsCollapsiblePanel(p2, "Deneme 2");
/* 110 */     JPanel scrollPane = new JPanel();
/* 111 */     scrollPane.add(pnl);
/* 112 */     scrollPane.add(pnl2);
/* 113 */     f.getContentPane().add(scrollPane);
/* 114 */     f.setSize(360, 500);
/* 115 */     f.setLocation(200, 100);
/* 116 */     f.setVisible(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsCollapsiblePanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */