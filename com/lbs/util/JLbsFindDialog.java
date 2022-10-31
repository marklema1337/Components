/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsFindDialog;
/*     */ import com.lbs.controls.JLbsButton;
/*     */ import com.lbs.controls.JLbsLabel;
/*     */ import com.lbs.controls.JLbsPanel;
/*     */ import com.lbs.controls.maskededit.JLbsTextEdit;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
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
/*     */ public class JLbsFindDialog
/*     */   extends JLbsDialog
/*     */   implements ILbsFindDialog
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final int DEFAULT_HEIGHT = 150;
/*     */   private static final int DEFAULT_WIDTH = 350;
/*     */   private ILbsFindDialogListener m_Listener;
/*     */   private ILocalizationServices m_LocalService;
/*  44 */   private int m_LastIdx = -1;
/*  45 */   private String m_LastSearch = null;
/*     */ 
/*     */   
/*     */   public JLbsFindDialog(Component parent, ILbsFindDialogListener listener, ILocalizationServices localizationService) {
/*  49 */     this(parent, findParentWindow(parent), listener, localizationService);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsFindDialog(Component parent, Window parentWindow, ILbsFindDialogListener listener, ILocalizationServices localizationService) {
/*  55 */     super(parentWindow);
/*  56 */     this.m_Listener = listener;
/*  57 */     this.m_LocalService = localizationService;
/*     */     
/*  59 */     JLbsLabel label = new JLbsLabel(String.valueOf(getResource(1, "Find")) + ":");
/*  60 */     final JLbsTextEdit textInput = new JLbsTextEdit(100);
/*  61 */     final JLbsButton nextButton = new JLbsButton();
/*  62 */     nextButton.setText(getResource(2, "Next"));
/*  63 */     final JLbsButton prevButton = new JLbsButton();
/*  64 */     prevButton.setText(getResource(3, "Previous"));
/*  65 */     final JLbsLabel statusLabel = new JLbsLabel();
/*     */ 
/*     */     
/*  68 */     textInput.addKeyListener(new KeyAdapter()
/*     */         {
/*     */           
/*     */           public void keyPressed(KeyEvent e)
/*     */           {
/*  73 */             switch (e.getKeyCode()) {
/*     */               
/*     */               case 27:
/*  76 */                 JLbsFindDialog.this.dispose();
/*  77 */                 e.consume();
/*     */                 break;
/*     */               case 10:
/*  80 */                 JLbsFindDialog.this.findNext(textInput, nextButton, statusLabel);
/*  81 */                 e.consume();
/*     */                 break;
/*     */             } 
/*     */ 
/*     */           
/*     */           }
/*     */         });
/*  88 */     if (this.m_Listener != null) {
/*     */ 
/*     */       
/*  91 */       nextButton.addActionListener(new ActionListener()
/*     */           {
/*     */             public void actionPerformed(ActionEvent e)
/*     */             {
/*  95 */               JLbsFindDialog.this.findNext(textInput, nextButton, statusLabel);
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 100 */       prevButton.addActionListener(new ActionListener()
/*     */           {
/*     */             public void actionPerformed(ActionEvent e)
/*     */             {
/* 104 */               JLbsFindDialog.this.findPrev(textInput, prevButton, statusLabel);
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/* 110 */     JLbsPanel inputPanel = new JLbsPanel();
/* 111 */     inputPanel.setLayout(new GridBagLayout());
/* 112 */     GridBagConstraints cons = new GridBagConstraints();
/* 113 */     cons.anchor = 23;
/* 114 */     inputPanel.add((Component)label, cons);
/* 115 */     cons = new GridBagConstraints();
/* 116 */     cons.gridx = -1;
/* 117 */     cons.anchor = 23;
/* 118 */     cons.weightx = 1.0D;
/* 119 */     cons.fill = 2;
/* 120 */     cons.gridwidth = 0;
/* 121 */     inputPanel.add((Component)textInput, cons);
/* 122 */     cons = new GridBagConstraints();
/* 123 */     cons.gridy = -1;
/* 124 */     cons.anchor = 23;
/* 125 */     cons.weightx = 1.0D;
/* 126 */     cons.fill = 2;
/* 127 */     cons.gridwidth = 0;
/* 128 */     inputPanel.add((Component)statusLabel, cons);
/*     */     
/* 130 */     JLbsPanel buttonPanel = new JLbsPanel();
/* 131 */     buttonPanel.setLayout(new GridBagLayout());
/* 132 */     cons = new GridBagConstraints();
/* 133 */     cons.anchor = 26;
/* 134 */     buttonPanel.add((Component)prevButton, cons);
/* 135 */     cons = new GridBagConstraints();
/* 136 */     cons.anchor = 26;
/* 137 */     cons.gridx = -1;
/* 138 */     buttonPanel.add((Component)nextButton, cons);
/*     */     
/* 140 */     getContentPane().setLayout(new BorderLayout());
/* 141 */     getContentPane().add((Component)inputPanel, "Center");
/* 142 */     getContentPane().add((Component)buttonPanel, "South");
/*     */     
/* 144 */     setSize(350, 150);
/*     */     
/* 146 */     if (parent != null) {
/*     */       
/* 148 */       Rectangle bounds = parent.getBounds();
/* 149 */       int x = bounds.x + bounds.width / 2;
/* 150 */       int y = bounds.y + bounds.height / 2;
/* 151 */       setLocation(x, y);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 156 */       centerScreen();
/*     */     } 
/* 158 */     setTitle(getResource(1, "Find"));
/* 159 */     textInput.requestFocus();
/* 160 */     setModal(false);
/* 161 */     validate();
/*     */   }
/*     */ 
/*     */   
/*     */   private String getResource(int tag, String defMess) {
/* 166 */     String m = (this.m_LocalService == null) ? 
/* 167 */       null : 
/* 168 */       this.m_LocalService.getItem(-1005, tag);
/* 169 */     if (JLbsStringUtil.isEmpty(m))
/* 170 */       m = defMess; 
/* 171 */     return m;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void findNext(JLbsTextEdit textInput, JLbsButton nextButton, JLbsLabel statusLabel) {
/* 176 */     String text = textInput.getText();
/* 177 */     if (!JLbsStringUtil.equals(this.m_LastSearch, text)) {
/*     */       
/* 179 */       this.m_LastSearch = text;
/* 180 */       this.m_LastIdx = -1;
/* 181 */       statusLabel.setText("");
/* 182 */       validate();
/*     */     } 
/* 184 */     int newIdx = this.m_Listener.findNext(this.m_LastSearch, this.m_LastIdx + 1);
/* 185 */     if (newIdx < 0) {
/*     */       
/* 187 */       if (this.m_LastIdx >= 0) {
/*     */         
/* 189 */         UIManager.put("OptionPane.yesButtonText", this.m_LocalService.getItem(801, 0));
/* 190 */         UIManager.put("OptionPane.noButtonText", this.m_LocalService.getItem(801, 1));
/* 191 */         int result = JOptionPane.showConfirmDialog((Component)nextButton, getResource(5, ""), getTitle(), 0);
/* 192 */         if (result == 0)
/*     */         {
/* 194 */           this.m_LastIdx = -1;
/* 195 */           findNext(textInput, nextButton, statusLabel);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 200 */         statusLabel.setText(getResource(4, "Cannot be found..."));
/*     */       } 
/*     */     } else {
/*     */       
/* 204 */       this.m_LastIdx = newIdx;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void findPrev(JLbsTextEdit textInput, JLbsButton prevButton, JLbsLabel statusLabel) {
/* 209 */     String text = textInput.getText();
/* 210 */     if (!JLbsStringUtil.equals(this.m_LastSearch, text)) {
/*     */       
/* 212 */       this.m_LastSearch = text;
/* 213 */       this.m_LastIdx = this.m_Listener.getTotalIdx() + 1;
/* 214 */       statusLabel.setText("");
/* 215 */       validate();
/*     */     } 
/* 217 */     int newIdx = this.m_Listener.findPrevious(this.m_LastSearch, this.m_LastIdx - 1);
/* 218 */     if (newIdx < 0) {
/*     */       
/* 220 */       if (this.m_LastIdx >= 0) {
/*     */         
/* 222 */         int result = JOptionPane.showConfirmDialog((Component)prevButton, getResource(5, ""), getTitle(), 0);
/* 223 */         if (result == 0)
/*     */         {
/* 225 */           this.m_LastIdx = this.m_Listener.getTotalIdx() + 1;
/* 226 */           findPrev(textInput, prevButton, statusLabel);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 231 */         statusLabel.setText(getResource(4, "Cannot be found..."));
/*     */       } 
/*     */     } else {
/*     */       
/* 235 */       this.m_LastIdx = newIdx;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Window findParentWindow(Component parent) {
/* 240 */     Component root = SwingUtilities.getRoot(parent);
/* 241 */     return (root instanceof Window) ? 
/* 242 */       (Window)root : 
/* 243 */       null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsFindDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */