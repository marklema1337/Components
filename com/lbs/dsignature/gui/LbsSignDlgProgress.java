/*     */ package com.lbs.dsignature.gui;
/*     */ 
/*     */ import com.lbs.util.JLbsDialog;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.LineBorder;
/*     */ 
/*     */ 
/*     */ public class LbsSignDlgProgress
/*     */   extends JLbsDialog
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  22 */   public final JLabel m_LblMesaj = new JLabel();
/*  23 */   public final JLabel m_LblMesaj2 = new JLabel();
/*     */   
/*     */   private JPanel m_Panel;
/*     */ 
/*     */   
/*     */   public LbsSignDlgProgress(String aIslemMesaji, Component aComponent) {
/*  29 */     init(aIslemMesaji);
/*  30 */     setLocationRelativeTo(aComponent);
/*     */     
/*     */     try {
/*  33 */       UIManager.setLookAndFeel("com.lbs.laf.mac.LookAndFeel");
/*  34 */       SwingUtilities.updateComponentTreeUI((Component)this);
/*     */     }
/*  36 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LbsSignDlgProgress(String aIslemMesaji, Dialog owner) {
/*  43 */     super(owner);
/*  44 */     init(aIslemMesaji);
/*  45 */     setLocationRelativeTo(getParent());
/*     */     
/*     */     try {
/*  48 */       UIManager.setLookAndFeel("com.lbs.laf.mac.LookAndFeel");
/*  49 */       SwingUtilities.updateComponentTreeUI((Component)this);
/*     */     }
/*  51 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void init(String aIslemMesaji) {
/*  58 */     setResizable(false);
/*  59 */     setLayout(new BorderLayout());
/*  60 */     setModal(true);
/*  61 */     setUndecorated(true);
/*  62 */     setSize(450, 150);
/*     */     
/*  64 */     this.m_Panel = new JPanel();
/*  65 */     this.m_Panel.setLayout(new GridBagLayout());
/*  66 */     this.m_Panel.setBorder(new LineBorder(Color.blue, 1, true));
/*  67 */     this.m_LblMesaj.setText(aIslemMesaji);
/*  68 */     this.m_Panel.add(this.m_LblMesaj2, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, 
/*  69 */           new Insets(0, 0, 0, 0), 0, 0));
/*  70 */     this.m_Panel.add(this.m_LblMesaj, new GridBagConstraints(0, 1, 1, 1, 1.0D, 0.0D, 10, 0, 
/*  71 */           new Insets(0, 0, 0, 0), 0, 0));
/*  72 */     add(this.m_Panel, "Center");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void threadBaslat() {
/*  81 */     Thread mBeklemeThreadi = new Thread(new Runnable()
/*     */         {
/*     */ 
/*     */           
/*     */           public void run()
/*     */           {
/*  87 */             LbsSignDlgProgress.this.setVisible(true);
/*     */           }
/*     */         });
/*  90 */     mBeklemeThreadi.start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void threadBitir() {
/*  98 */     Thread mSonlanmaThreadi = new Thread(new Runnable()
/*     */         {
/*     */ 
/*     */           
/*     */           public void run()
/*     */           {
/* 104 */             LbsSignDlgProgress.this.dispose();
/*     */           }
/*     */         });
/* 107 */     mSonlanmaThreadi.start();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\dsignature\gui\LbsSignDlgProgress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */