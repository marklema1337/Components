/*     */ package com.lbs.remoteclient;
/*     */ 
/*     */ import com.lbs.globalization.JLbsCultureInfoBase;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsDialog;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.UIHelperUtil;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
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
/*     */ public class JLbsRememberPassEditor
/*     */   extends JLbsDialog
/*     */   implements ActionListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private IClientContext m_Context;
/*     */   private JLbsCultureInfoBase m_SelectedCulture;
/*     */   private JTextField m_EmailAddress;
/*     */   private JButton m_BtnOk;
/*     */   private JButton m_BtnCancel;
/*     */   private String[] resources;
/*     */   private String m_UserName;
/*     */   
/*     */   public JLbsRememberPassEditor(IClientContext context, JLbsCultureInfoBase culture, String userName) {
/*  48 */     this.m_Context = context;
/*  49 */     this.m_SelectedCulture = culture;
/*  50 */     this.m_UserName = userName;
/*  51 */     init();
/*     */   }
/*     */ 
/*     */   
/*     */   private void init() {
/*  56 */     setTitle(getCultureResStr(112));
/*  57 */     setModal(true);
/*     */     
/*  59 */     getContentPane().setLayout(new BorderLayout(0, 0));
/*  60 */     JPanel panel = new JPanel();
/*  61 */     getContentPane().add(panel, "Center");
/*  62 */     GridBagLayout gbl_panel = new GridBagLayout();
/*  63 */     gbl_panel.columnWidths = new int[] { 110, 163 };
/*  64 */     gbl_panel.rowHeights = new int[] { 32 };
/*  65 */     gbl_panel.columnWeights = new double[] { 0.0D, 0.0D, Double.MIN_VALUE };
/*  66 */     gbl_panel.rowWeights = new double[] { 0.0D, Double.MIN_VALUE };
/*  67 */     panel.setLayout(gbl_panel);
/*     */     
/*  69 */     JLabel lblNewLabel = new JLabel(getCultureResStr(113));
/*  70 */     GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
/*  71 */     gbc_lblNewLabel.anchor = 17;
/*  72 */     gbc_lblNewLabel.insets = new Insets(5, 5, 0, 5);
/*  73 */     gbc_lblNewLabel.gridx = 0;
/*  74 */     gbc_lblNewLabel.gridy = 0;
/*  75 */     panel.add(lblNewLabel, gbc_lblNewLabel);
/*     */     
/*  77 */     this.m_EmailAddress = new JTextField();
/*  78 */     this.m_EmailAddress.setFont(new Font(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE));
/*  79 */     this.m_EmailAddress.setPreferredSize(new Dimension(163, 22));
/*  80 */     this.m_EmailAddress.setSize(163, 22);
/*  81 */     GridBagConstraints gbc_textField = new GridBagConstraints();
/*  82 */     gbc_textField.insets = new Insets(5, 5, 0, 5);
/*  83 */     gbc_textField.fill = 2;
/*  84 */     gbc_textField.gridx = 1;
/*  85 */     gbc_textField.gridy = 0;
/*  86 */     panel.add(this.m_EmailAddress, gbc_textField);
/*  87 */     this.m_EmailAddress.setColumns(22);
/*     */     
/*  89 */     JPanel panel_1 = new JPanel();
/*  90 */     FlowLayout flowLayout = (FlowLayout)panel_1.getLayout();
/*  91 */     flowLayout.setAlignment(2);
/*  92 */     getContentPane().add(panel_1, "South");
/*     */     
/*  94 */     this.m_BtnOk = new JButton();
/*     */     
/*  96 */     UIHelperUtil.setCaption(this.m_BtnOk, getCultureResStr(107));
/*  97 */     this.m_BtnOk.addActionListener(this);
/*  98 */     panel_1.add(this.m_BtnOk);
/*     */     
/* 100 */     this.m_BtnCancel = new JButton();
/*     */     
/* 102 */     UIHelperUtil.setCaption(this.m_BtnCancel, getCultureResStr(108));
/* 103 */     this.m_BtnCancel.addActionListener(this);
/* 104 */     panel_1.add(this.m_BtnCancel);
/*     */     
/* 106 */     getRootPane().setDefaultButton(this.m_BtnCancel);
/* 107 */     setResizable(false);
/* 108 */     pack();
/* 109 */     centerScreen();
/* 110 */     addEscapeCloseShortcut();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getCultureResStr(int cultureResID) {
/* 115 */     return this.m_SelectedCulture.getCultureResStr(cultureResID);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 120 */     JLbsRememberPassEditor e = new JLbsRememberPassEditor(null, null, null);
/* 121 */     e.show();
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 126 */     Object source = e.getSource();
/* 127 */     if (source == this.m_BtnCancel) {
/*     */       
/* 129 */       dispose();
/*     */     }
/* 131 */     else if (source == this.m_BtnOk) {
/*     */       
/* 133 */       if (!JLbsStringUtil.validateEmail(this.m_EmailAddress.getText())) {
/*     */         
/* 135 */         this.m_Context.showMessage(getCultureResStr(1000), getCultureResStr(114));
/*     */         
/*     */         return;
/*     */       } 
/*     */       try {
/* 140 */         Object returnObj = this.m_Context.getPublicObject("RememberPassword", new String[] { this.m_EmailAddress.getText(), "TRTR", this.m_UserName }, true);
/* 141 */         if (returnObj instanceof Boolean) {
/*     */           
/* 143 */           if (((Boolean)returnObj).booleanValue()) {
/* 144 */             this.m_Context.showMessage(getCultureResStr(118), getCultureResStr(115));
/*     */           } else {
/* 146 */             this.m_Context.showMessage(getCultureResStr(1000), getCultureResStr(116));
/*     */           } 
/*     */         } else {
/* 149 */           this.m_Context.showMessage(getCultureResStr(1000), getCultureResStr(117));
/*     */         } 
/* 151 */       } catch (Exception e1) {
/*     */         
/* 153 */         e1.printStackTrace();
/* 154 */         this.m_Context.showMessage(getCultureResStr(1000), getCultureResStr(117));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\JLbsRememberPassEditor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */