/*     */ package com.lbs.controls.numericedit;
/*     */ 
/*     */ import com.lbs.controls.JLbsPanel;
/*     */ import com.lbs.controls.maskededit.JLbsTextEdit;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.UIHelperUtil;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JPanel;
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
/*     */ public class JLbsCalculatorPanel
/*     */   extends JLbsPanel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected ILbsCultureInfo m_CultureInfo;
/*     */   protected ILbsCalculatorPanelListener m_CalculatorListener;
/*  49 */   protected Rectangle m_rectCalcGrid = null;
/*     */   
/*  51 */   protected JButton m_LeftP = null;
/*     */   
/*  53 */   protected JButton m_RightP = null;
/*     */   
/*     */   protected JButton m_Back;
/*     */   
/*  57 */   protected JButton m_Add = null;
/*     */   
/*  59 */   protected JButton m_Subtract = null;
/*     */   
/*     */   protected JButton m_Multiply;
/*     */   
/*     */   protected JButton m_Divide;
/*     */   
/*     */   protected JPanel m_ExpPanel;
/*     */   
/*     */   protected JPanel m_BtnPanel;
/*     */   
/*     */   protected JLbsTextEdit m_Edit;
/*     */   
/*  71 */   private String m_Expression = "";
/*     */   
/*  73 */   private String m_ExpChar = "";
/*     */   
/*     */   private static final int RESID = -20098;
/*     */ 
/*     */   
/*     */   public JLbsCalculatorPanel() {
/*  79 */     setBackground(Color.white);
/*  80 */     enableEvents(28L);
/*  81 */     addMouseListener(new JLbsCalculatorPanelMouseAdapter());
/*  82 */     addKeyListener(new JLbsCalculatorPanelKeyAdapter());
/*  83 */     setLayout(new BorderLayout());
/*  84 */     createExpressionPanel();
/*  85 */     createButtonPanel();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(boolean form) {
/*  91 */     int desktopYDelta = 0;
/*  92 */     if (JLbsConstants.DESKTOP_MODE)
/*  93 */       desktopYDelta = 20; 
/*  94 */     if (form) {
/*  95 */       return new Dimension(300, 120 + desktopYDelta);
/*     */     }
/*  97 */     return new Dimension(300, 100 + desktopYDelta);
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 102 */     return getPreferredSize(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCultureInfo(ILbsCultureInfo cultureInfo) {
/* 107 */     if (this.m_CultureInfo != cultureInfo) {
/*     */       
/* 109 */       this.m_CultureInfo = cultureInfo;
/* 110 */       invalidate();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void doKeyNavigation(KeyEvent e) {
/*     */     DecimalFormatSymbols symbols;
/* 116 */     if (e.isConsumed()) {
/*     */       return;
/*     */     }
/* 119 */     doZeroExpression();
/*     */     
/* 121 */     switch (e.getKeyCode()) {
/*     */       
/*     */       case 10:
/* 124 */         fireExpressionSelected();
/* 125 */         e.consume();
/*     */         return;
/*     */       case 27:
/* 128 */         fireEscapeSelected();
/* 129 */         e.consume();
/*     */         return;
/*     */       case 48:
/*     */       case 96:
/* 133 */         this.m_ExpChar = "0";
/* 134 */         expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 135 */         e.consume();
/*     */         return;
/*     */       case 49:
/*     */       case 97:
/* 139 */         this.m_ExpChar = "1";
/* 140 */         expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 141 */         e.consume();
/*     */         return;
/*     */       case 50:
/*     */       case 98:
/* 145 */         this.m_ExpChar = "2";
/* 146 */         expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 147 */         e.consume();
/*     */         return;
/*     */       case 51:
/*     */       case 99:
/* 151 */         this.m_ExpChar = "3";
/* 152 */         expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 153 */         e.consume();
/*     */         return;
/*     */       case 100:
/* 156 */         this.m_ExpChar = "4";
/* 157 */         expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 158 */         e.consume();
/*     */         return;
/*     */       case 52:
/* 161 */         if (e.isShiftDown()) {
/*     */           
/* 163 */           this.m_ExpChar = "+";
/* 164 */           expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 165 */           e.consume();
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 170 */           this.m_ExpChar = "4";
/* 171 */           expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 172 */           e.consume();
/*     */         } 
/*     */         return;
/*     */       case 53:
/*     */       case 101:
/* 177 */         this.m_ExpChar = "5";
/* 178 */         expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 179 */         e.consume();
/*     */         return;
/*     */       case 54:
/*     */       case 102:
/* 183 */         this.m_ExpChar = "6";
/* 184 */         expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 185 */         e.consume();
/*     */         return;
/*     */       case 103:
/* 188 */         this.m_ExpChar = "7";
/* 189 */         expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 190 */         e.consume();
/*     */         return;
/*     */       case 55:
/* 193 */         if (e.isShiftDown()) {
/*     */           
/* 195 */           this.m_ExpChar = "/";
/* 196 */           expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 197 */           e.consume();
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 202 */           this.m_ExpChar = "7";
/* 203 */           expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 204 */           e.consume();
/*     */         } 
/*     */         return;
/*     */       case 104:
/* 208 */         this.m_ExpChar = "8";
/* 209 */         expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 210 */         e.consume();
/*     */         return;
/*     */       case 56:
/* 213 */         if (e.isShiftDown()) {
/*     */           
/* 215 */           this.m_ExpChar = "(";
/* 216 */           expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 217 */           e.consume();
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 222 */           this.m_ExpChar = "8";
/* 223 */           expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 224 */           e.consume();
/*     */         } 
/*     */         return;
/*     */       case 105:
/* 228 */         this.m_ExpChar = "9";
/* 229 */         expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 230 */         e.consume();
/*     */         return;
/*     */       case 57:
/* 233 */         if (e.isShiftDown()) {
/*     */           
/* 235 */           this.m_ExpChar = ")";
/* 236 */           expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 237 */           e.consume();
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 242 */           this.m_ExpChar = "9";
/* 243 */           expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 244 */           e.consume();
/*     */         } 
/*     */         return;
/*     */       case 44:
/*     */       case 110:
/* 249 */         symbols = (new DecimalFormat()).getDecimalFormatSymbols();
/* 250 */         this.m_ExpChar = symbols.getDecimalSeparator();
/* 251 */         expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 252 */         e.consume();
/*     */         return;
/*     */       case 107:
/* 255 */         this.m_ExpChar = "+";
/* 256 */         expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 257 */         e.consume();
/*     */         return;
/*     */       case 45:
/*     */       case 109:
/* 261 */         this.m_ExpChar = "-";
/* 262 */         expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 263 */         e.consume();
/*     */         return;
/*     */       case 106:
/*     */       case 151:
/* 267 */         this.m_ExpChar = "*";
/* 268 */         expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 269 */         e.consume();
/*     */         return;
/*     */       case 111:
/* 272 */         this.m_ExpChar = "/";
/* 273 */         expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 274 */         e.consume();
/*     */         return;
/*     */       case 519:
/* 277 */         this.m_ExpChar = "(";
/* 278 */         expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 279 */         e.consume();
/*     */         return;
/*     */       case 522:
/* 282 */         this.m_ExpChar = ")";
/* 283 */         expressionSelectionChanged((Object)null, this.m_ExpChar);
/* 284 */         e.consume();
/*     */         return;
/*     */       case 8:
/* 287 */         setBack();
/* 288 */         e.consume();
/*     */         return;
/*     */     } 
/* 291 */     e.consume();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void doZeroExpression() {
/* 298 */     if (this.m_Edit.getText().equals("0"))
/* 299 */       this.m_Edit.setText(""); 
/* 300 */     if (this.m_Expression.equals("0")) {
/* 301 */       this.m_Expression = "";
/*     */     }
/*     */   }
/*     */   
/*     */   public void setCalculatorListener(ILbsCalculatorPanelListener listener) {
/* 306 */     this.m_CalculatorListener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusable() {
/* 311 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void expressionSelectionChanged(Object sender, String expression) {
/* 316 */     DecimalFormatSymbols symbols = (new DecimalFormat()).getDecimalFormatSymbols();
/* 317 */     char c = symbols.getDecimalSeparator();
/* 318 */     String exp = this.m_Edit.getText();
/* 319 */     String lastExp = getLastExpression(exp);
/* 320 */     if (expression.equals(c))
/*     */     {
/* 322 */       if (!JLbsStringUtil.isEmpty(exp) && (
/* 323 */         exp.charAt(exp.length() - 1) == '+' || exp.charAt(exp.length() - 1) == '-' || 
/* 324 */         exp.charAt(exp.length() - 1) == '*' || exp.charAt(exp.length() - 1) == '/' || 
/* 325 */         exp.charAt(exp.length() - 1) == '(' || exp.charAt(exp.length() - 1) == ')'))
/* 326 */       { expression = "0" + expression; }
/* 327 */       else { if (lastExp.indexOf(c) >= 0)
/*     */           return; 
/* 329 */         if (JLbsStringUtil.isEmpty(lastExp))
/* 330 */           expression = "0" + expression;  }
/*     */        } 
/* 332 */     this.m_Expression = String.valueOf(this.m_Expression) + expression;
/* 333 */     this.m_Edit.setText(this.m_Expression);
/*     */   }
/*     */ 
/*     */   
/*     */   private String getLastExpression(String expression) {
/* 338 */     String[] strTok = JLbsStringUtil.tokenize(expression, "+-/*()");
/*     */     
/* 340 */     int length = strTok.length;
/* 341 */     if (length > 0)
/*     */     {
/* 343 */       return strTok[length - 1];
/*     */     }
/*     */     
/* 346 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fireExpressionSelectionChanged() {
/* 353 */     if (this.m_CalculatorListener != null) {
/* 354 */       this.m_CalculatorListener.expressionSelectionChanged(this, this.m_ExpChar);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void fireExpressionSelected() {
/* 359 */     if (this.m_CalculatorListener != null) {
/* 360 */       this.m_CalculatorListener.expressionSelected(this, this.m_Expression);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void fireEscapeSelected() {
/* 365 */     if (this.m_CalculatorListener != null) {
/* 366 */       this.m_CalculatorListener.escapeSelected(this);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/* 371 */     super.paintComponent(g);
/*     */   }
/*     */ 
/*     */   
/*     */   private void createExpressionPanel() {
/* 376 */     this.m_ExpPanel = new JPanel();
/* 377 */     this.m_ExpPanel.setLayout(new FlowLayout(1, 0, 2));
/* 378 */     createExpressionEdit();
/* 379 */     add(this.m_ExpPanel, "Center");
/*     */   }
/*     */ 
/*     */   
/*     */   private void createExpressionEdit() {
/* 384 */     this.m_Edit = new JLbsTextEdit();
/* 385 */     this.m_Edit.setFocusable(false);
/* 386 */     this.m_Edit.setEditable(false);
/* 387 */     this.m_Edit.setPreferredSize(new Dimension(270, 20));
/* 388 */     this.m_ExpPanel.add((Component)this.m_Edit);
/*     */   }
/*     */ 
/*     */   
/*     */   private void createButtonPanel() {
/* 393 */     this.m_BtnPanel = new JPanel();
/* 394 */     this.m_BtnPanel.setLayout(new FlowLayout(1, 0, 2));
/* 395 */     createLeftPButton();
/* 396 */     createRightPButton();
/* 397 */     createAddButton();
/* 398 */     createSubtractButton();
/* 399 */     createMultiplyButton();
/* 400 */     createDivideButton();
/* 401 */     createBackButton();
/* 402 */     add(this.m_BtnPanel, "South");
/*     */   }
/*     */ 
/*     */   
/*     */   private void createLeftPButton() {
/* 407 */     this.m_LeftP = new JButton();
/* 408 */     this.m_LeftP.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 412 */             JLbsCalculatorPanel.this.setLeftP();
/* 413 */             JLbsCalculatorPanel.this.expressionSelectionChanged(JLbsCalculatorPanel.this.m_LeftP, JLbsCalculatorPanel.this.m_ExpChar);
/* 414 */             JLbsCalculatorPanel.this.requestFocus();
/*     */           }
/*     */         });
/* 417 */     UIHelperUtil.setCaption(this.m_LeftP, "(");
/* 418 */     this.m_LeftP.setToolTipText(JLbsLocalizer.getStringDef("UN", -20098, 2, "Left Paranthesis"));
/* 419 */     this.m_BtnPanel.add(this.m_LeftP);
/*     */   }
/*     */ 
/*     */   
/*     */   private void createRightPButton() {
/* 424 */     this.m_RightP = new JButton();
/* 425 */     this.m_RightP.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 429 */             JLbsCalculatorPanel.this.setRightP();
/* 430 */             JLbsCalculatorPanel.this.expressionSelectionChanged(JLbsCalculatorPanel.this.m_RightP, JLbsCalculatorPanel.this.m_ExpChar);
/* 431 */             JLbsCalculatorPanel.this.requestFocus();
/*     */           }
/*     */         });
/* 434 */     UIHelperUtil.setCaption(this.m_RightP, ")");
/* 435 */     this.m_RightP.setToolTipText(JLbsLocalizer.getStringDef("UN", -20098, 3, "Right Paranthesis"));
/* 436 */     this.m_BtnPanel.add(this.m_RightP);
/*     */   }
/*     */ 
/*     */   
/*     */   private void createBackButton() {
/* 441 */     this.m_Back = new JButton();
/* 442 */     this.m_Back.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 446 */             JLbsCalculatorPanel.this.setBack();
/* 447 */             JLbsCalculatorPanel.this.requestFocus();
/*     */           }
/*     */         });
/* 450 */     UIHelperUtil.setCaption(this.m_Back, JLbsLocalizer.getStringDef("UN", -20098, 4, "Back"));
/* 451 */     this.m_Back.setToolTipText(JLbsLocalizer.getStringDef("UN", -20098, 4, "Backspace"));
/* 452 */     this.m_BtnPanel.add(this.m_Back);
/*     */   }
/*     */ 
/*     */   
/*     */   private void createAddButton() {
/* 457 */     this.m_Add = new JButton();
/* 458 */     this.m_Add.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 462 */             JLbsCalculatorPanel.this.setAddition();
/* 463 */             JLbsCalculatorPanel.this.expressionSelectionChanged(JLbsCalculatorPanel.this.m_Add, JLbsCalculatorPanel.this.m_ExpChar);
/* 464 */             JLbsCalculatorPanel.this.requestFocus();
/*     */           }
/*     */         });
/* 467 */     UIHelperUtil.setCaption(this.m_Add, "+");
/* 468 */     this.m_Add.setToolTipText(JLbsLocalizer.getStringDef("UN", -20098, 5, "Addition"));
/* 469 */     this.m_BtnPanel.add(this.m_Add);
/*     */   }
/*     */ 
/*     */   
/*     */   private void createSubtractButton() {
/* 474 */     this.m_Subtract = new JButton();
/* 475 */     this.m_Subtract.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 479 */             JLbsCalculatorPanel.this.setSubtraction();
/* 480 */             JLbsCalculatorPanel.this.expressionSelectionChanged(JLbsCalculatorPanel.this.m_Subtract, JLbsCalculatorPanel.this.m_ExpChar);
/* 481 */             JLbsCalculatorPanel.this.requestFocus();
/*     */           }
/*     */         });
/* 484 */     UIHelperUtil.setCaption(this.m_Subtract, "-");
/* 485 */     this.m_Subtract.setToolTipText(JLbsLocalizer.getStringDef("UN", -20098, 6, "Subtraction"));
/* 486 */     this.m_BtnPanel.add(this.m_Subtract);
/*     */   }
/*     */ 
/*     */   
/*     */   private void createMultiplyButton() {
/* 491 */     this.m_Multiply = new JButton();
/* 492 */     this.m_Multiply.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 496 */             JLbsCalculatorPanel.this.setMultiplication();
/* 497 */             JLbsCalculatorPanel.this.expressionSelectionChanged(JLbsCalculatorPanel.this.m_Multiply, JLbsCalculatorPanel.this.m_ExpChar);
/* 498 */             JLbsCalculatorPanel.this.requestFocus();
/*     */           }
/*     */         });
/* 501 */     UIHelperUtil.setCaption(this.m_Multiply, "*");
/* 502 */     this.m_Multiply.setToolTipText(JLbsLocalizer.getStringDef("UN", -20098, 7, "Multiplication"));
/* 503 */     this.m_BtnPanel.add(this.m_Multiply);
/*     */   }
/*     */ 
/*     */   
/*     */   private void createDivideButton() {
/* 508 */     this.m_Divide = new JButton();
/* 509 */     this.m_Divide.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 513 */             JLbsCalculatorPanel.this.setDivision();
/* 514 */             JLbsCalculatorPanel.this.expressionSelectionChanged(JLbsCalculatorPanel.this.m_Divide, JLbsCalculatorPanel.this.m_ExpChar);
/* 515 */             JLbsCalculatorPanel.this.requestFocus();
/*     */           }
/*     */         });
/* 518 */     UIHelperUtil.setCaption(this.m_Divide, "/");
/* 519 */     this.m_Divide.setToolTipText(JLbsLocalizer.getStringDef("UN", -20098, 8, "Division"));
/* 520 */     this.m_BtnPanel.add(this.m_Divide);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLeftP() {
/* 525 */     this.m_ExpChar = "(";
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRightP() {
/* 530 */     this.m_ExpChar = ")";
/*     */   }
/*     */ 
/*     */   
/*     */   private void setAddition() {
/* 535 */     this.m_ExpChar = "+";
/*     */   }
/*     */ 
/*     */   
/*     */   private void setSubtraction() {
/* 540 */     this.m_ExpChar = "-";
/*     */   }
/*     */ 
/*     */   
/*     */   private void setMultiplication() {
/* 545 */     this.m_ExpChar = "*";
/*     */   }
/*     */ 
/*     */   
/*     */   private void setDivision() {
/* 550 */     this.m_ExpChar = "/";
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOriginalText(String originalText) {
/* 555 */     if (!JLbsStringUtil.isEmpty(originalText)) {
/*     */       
/* 557 */       originalText = JLbsStringUtil.remove(originalText, (new DecimalFormat()).getDecimalFormatSymbols().getGroupingSeparator());
/* 558 */       this.m_Edit.setText(originalText);
/* 559 */       this.m_Expression = originalText;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setBack() {
/* 565 */     if (this.m_Expression != null) {
/*     */       
/* 567 */       int size = this.m_Expression.length();
/* 568 */       if (size > 0) {
/*     */         
/* 570 */         this.m_Expression = this.m_Expression.substring(0, size - 1);
/* 571 */         this.m_Edit.setText(this.m_Expression);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\numericedit\JLbsCalculatorPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */