/*     */ package com.lbs.client;
/*     */ 
/*     */ import com.lbs.util.JLbsDialog;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.UIHelperUtil;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.WindowEvent;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPasswordField;
/*     */ import javax.swing.border.EmptyBorder;
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
/*     */ public class LbsPasswordRequestDialog
/*     */   extends JLbsDialog
/*     */   implements ActionListener
/*     */ {
/*     */   JLabel messageLabel;
/*     */   JLabel infoLabel;
/*     */   JLabel passwordLabel;
/*     */   JPasswordField passwordField;
/*     */   private JButton btnCancel;
/*     */   private JButton btnOk;
/*     */   private boolean okPressed = false;
/*     */   
/*     */   public LbsPasswordRequestDialog(String title, String message, String info, String passwordLabelText, String okButtonCaption, String cancelButtonCaption, boolean passwordEnabled) {
/*  45 */     setTitle(title);
/*     */     
/*  47 */     this.messageLabel = new JLabel();
/*  48 */     this.messageLabel.setText(message);
/*  49 */     this.infoLabel = new JLabel();
/*  50 */     this.infoLabel.setText(info);
/*  51 */     this.passwordLabel = new JLabel(passwordLabelText);
/*  52 */     this.passwordField = new JPasswordField();
/*  53 */     this.passwordField.setEditable(passwordEnabled);
/*  54 */     this.passwordField.setEnabled(passwordEnabled);
/*  55 */     this.passwordField.setMaximumSize(new Dimension(100, 24));
/*  56 */     this.messageLabel.setBorder(new EmptyBorder(0, 0, 0, 6));
/*  57 */     JPanel passwordPanel = new JPanel();
/*  58 */     passwordPanel.setLayout(new BorderLayout(5, 9));
/*  59 */     passwordPanel.add(this.messageLabel, "North");
/*  60 */     passwordPanel.add(this.passwordLabel, "West");
/*  61 */     passwordPanel.add(this.passwordField, "Center");
/*  62 */     passwordPanel.add(this.infoLabel, "South");
/*  63 */     passwordPanel.grabFocus();
/*     */     
/*  65 */     this.btnOk = new JButton();
/*  66 */     this.btnOk.setActionCommand("ok");
/*  67 */     this.btnOk.addActionListener(this);
/*  68 */     UIHelperUtil.setCaption(this.btnOk, okButtonCaption);
/*     */     
/*  70 */     this.btnCancel = new JButton();
/*  71 */     this.btnCancel.setActionCommand("close");
/*  72 */     this.btnCancel.addActionListener(this);
/*  73 */     UIHelperUtil.setCaption(this.btnCancel, cancelButtonCaption);
/*     */     
/*  75 */     JPanel btnPanel = new JPanel();
/*  76 */     btnPanel.setLayout(new FlowLayout(2));
/*  77 */     btnPanel.add(this.btnOk);
/*  78 */     btnPanel.add(this.btnCancel);
/*  79 */     Dimension dim = getMaxDimension(this.btnOk.getPreferredSize(), this.btnCancel.getPreferredSize());
/*  80 */     if (dim != null) {
/*     */       
/*  82 */       this.btnOk.setPreferredSize(dim);
/*  83 */       this.btnCancel.setPreferredSize(dim);
/*     */     } 
/*  85 */     JPanel contPanel = new JPanel();
/*  86 */     contPanel.setLayout(new BorderLayout());
/*  87 */     contPanel.add(passwordPanel, "Center");
/*  88 */     contPanel.add(btnPanel, "South");
/*  89 */     contPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
/*     */     
/*  91 */     setContentPane(contPanel);
/*     */     
/*  93 */     this.passwordField.addKeyListener(new KeyListener()
/*     */         {
/*     */           public void keyTyped(KeyEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void keyPressed(KeyEvent e) {
/* 103 */             if (e.getKeyChar() == '\n')
/*     */             {
/* 105 */               LbsPasswordRequestDialog.this.btnOk.doClick();
/*     */             }
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public void keyReleased(KeyEvent e) {}
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void windowClosing(WindowEvent arg0) {
/* 118 */     super.windowClosing(arg0);
/* 119 */     actionPerformed(new ActionEvent(this, 0, "close"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Dimension getMaxDimension(Dimension preferredSize, Dimension preferredSize2) {
/* 129 */     if (preferredSize == null || preferredSize2 == null) {
/* 130 */       return null;
/*     */     }
/* 132 */     return new Dimension(Math.max(preferredSize.width, preferredSize2.width), Math.max(preferredSize.height, preferredSize2.height));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showDialog() {
/* 139 */     pack();
/* 140 */     centerScreen();
/* 141 */     setModal(true);
/* 142 */     setResizable(false);
/* 143 */     addEscapeCloseShortcut();
/* 144 */     this.btnOk.requestFocus();
/* 145 */     show();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOkPressed() {
/* 150 */     return this.okPressed;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPassword() {
/* 155 */     if (this.passwordField != null)
/* 156 */       return new String(this.passwordField.getPassword()); 
/* 157 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 162 */     if (JLbsStringUtil.areEqual("close", e.getActionCommand())) {
/*     */       
/* 164 */       this.okPressed = false;
/* 165 */       dispose();
/*     */     } 
/* 167 */     if (JLbsStringUtil.areEqual("ok", e.getActionCommand())) {
/*     */       
/* 169 */       this.okPressed = true;
/* 170 */       dispose();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 176 */     LbsPasswordRequestDialog dialog = new LbsPasswordRequestDialog("Test", "Your session has expired. Please log in again.", "test", "Password:", "Ok", "Cancel", true);
/*     */     
/* 178 */     dialog.showDialog();
/* 179 */     System.exit(0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\client\LbsPasswordRequestDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */