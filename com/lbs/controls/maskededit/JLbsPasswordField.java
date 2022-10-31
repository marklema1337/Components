/*     */ package com.lbs.controls.maskededit;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsPasswordField;
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import javax.swing.JPasswordField;
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
/*     */ public class JLbsPasswordField
/*     */   extends JPasswordField
/*     */   implements FocusListener, KeyListener, ILbsPasswordField
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String m_CoverText = "***********";
/*  28 */   private String m_Password = "";
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsPasswordField() {
/*  33 */     addFocusListener(this);
/*  34 */     addKeyListener(this);
/*  35 */     super.setText("***********");
/*     */   }
/*     */ 
/*     */   
/*     */   public void focusGained(FocusEvent arg0) {
/*  40 */     super.setText(this.m_Password);
/*     */   }
/*     */ 
/*     */   
/*     */   public void focusLost(FocusEvent arg0) {
/*  45 */     this.m_Password = new String(super.getPassword());
/*  46 */     super.setText("***********");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyPressed(KeyEvent e) {}
/*     */ 
/*     */   
/*     */   public void keyReleased(KeyEvent e) {
/*  55 */     this.m_Password = new String(super.getPassword());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyTyped(KeyEvent e) {}
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/*  64 */     this.m_Password = text;
/*  65 */     String display = hasFocus() ? 
/*  66 */       this.m_Password : 
/*  67 */       "***********";
/*  68 */     super.setText(display);
/*     */   }
/*     */ 
/*     */   
/*     */   public char[] getPassword() {
/*  73 */     return this.m_Password.toCharArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPasswordText() {
/*  78 */     return this.m_Password;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPassword(String password) {
/*  83 */     this.m_Password = password;
/*     */   }
/*     */   
/*     */   public String getResourceIdentifier() {
/*  87 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/*  92 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 102 */     return getParent();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResetTime(boolean resetTime) {}
/*     */ 
/*     */   
/*     */   public boolean isResetTime() {
/* 111 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\maskededit\JLbsPasswordField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */