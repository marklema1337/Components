/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsInputVerifier;
/*     */ import com.lbs.control.interfaces.ILbsMaskedEdit;
/*     */ import com.lbs.controls.maskededit.ITextLimitChangeListener;
/*     */ import com.lbs.controls.maskededit.JLbsCharMap;
/*     */ import java.awt.AWTEventMulticaster;
/*     */ import java.awt.Color;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import javax.swing.JFormattedTextField;
/*     */ import javax.swing.text.MaskFormatter;
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
/*     */ public class LbsMaskedEditEmulator
/*     */   extends LbsTextComponentEmulator
/*     */   implements ILbsMaskedEdit
/*     */ {
/*     */   private boolean m_EnableFocusEvent = true;
/*     */   private String m_CharFilter;
/*     */   private JLbsCharMap m_CharMap;
/*     */   private int m_TextLimit;
/*  34 */   private FocusListener m_FocusListener = null;
/*     */   
/*     */   private ITextLimitChangeListener m_Listener;
/*     */   
/*     */   private Object m_Value;
/*     */   
/*     */   protected JFormattedTextField.AbstractFormatter m_Formatter;
/*  41 */   private static final ILbsInputVerifier ms_Verifier = new LbsMaskedEditVerifier();
/*     */   
/*     */   private ILbsInputVerifier m_Verifier;
/*     */   
/*     */   private boolean m_ValueChangedByParent;
/*     */   
/*     */   private boolean m_clipMode;
/*     */ 
/*     */   
/*     */   public ILbsInputVerifier getLbsInputVerifier() {
/*  51 */     return ms_Verifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLbsInputVerifier(ILbsInputVerifier verifier) {
/*  56 */     this.m_Verifier = verifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void enableFocusEvent(boolean enable) {
/*  61 */     this.m_EnableFocusEvent = enable;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCharFilter() {
/*  66 */     return this.m_CharFilter;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsCharMap getCharMap() {
/*  71 */     if (this.m_CharMap == null)
/*  72 */       this.m_CharMap = new JLbsCharMap(); 
/*  73 */     return this.m_CharMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getNormalBkColor() {
/*  78 */     return Color.gray;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTextLimit() {
/*  83 */     return this.m_TextLimit;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getValue() {
/*  88 */     return this.m_Value;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCharFilter(String string) {
/*  93 */     this.m_CharFilter = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCharMap(JLbsCharMap charMap) {
/*  98 */     this.m_CharMap = charMap;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMask(String mask) {
/*     */     try {
/* 105 */       MaskFormatter formatter = new MaskFormatter(mask);
/* 106 */       formatter.setPlaceholderCharacter('_');
/* 107 */       this.m_Formatter = formatter;
/*     */     }
/* 109 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setTextLimit(int limit) {
/* 116 */     this.m_TextLimit = limit;
/* 117 */     if (this.m_Listener != null)
/* 118 */       this.m_Listener.textLimitChanged(this); 
/* 119 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTextLimitChangeListener(ITextLimitChangeListener textLimitChangeListener) {
/* 124 */     this.m_Listener = textLimitChangeListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(Object value) {
/* 129 */     this.m_Value = value;
/*     */     
/*     */     try {
/* 132 */       String sValue = null;
/* 133 */       if (this.m_Formatter != null) {
/* 134 */         sValue = this.m_Formatter.valueToString(value);
/*     */       
/*     */       }
/* 137 */       else if (value == null) {
/* 138 */         sValue = "";
/*     */       } else {
/* 140 */         sValue = value.toString();
/*     */       } 
/* 142 */       setText(sValue);
/*     */     }
/* 144 */     catch (Exception e) {
/*     */       
/* 146 */       setText("");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String s) {
/* 152 */     if (s != null && s.length() > 0 && s.charAt(0) == '\000') {
/* 153 */       super.setText((String)null);
/*     */     } else {
/* 155 */       super.setText(s);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean verifyContent() {
/* 160 */     if (this.m_Verifier != null) {
/* 161 */       return this.m_Verifier.verify((ILbsComponent)this);
/*     */     }
/* 163 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addLbsFocusListener(FocusListener listener) {
/* 168 */     this.m_FocusListener = AWTEventMulticaster.add(this.m_FocusListener, listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeLbsFocusListener(FocusListener listener) {
/* 173 */     this.m_FocusListener = AWTEventMulticaster.remove(this.m_FocusListener, listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnableFocusEvent() {
/* 178 */     return this.m_EnableFocusEvent;
/*     */   }
/*     */   public LbsMaskedEditEmulator() {
/* 181 */     this.m_ValueChangedByParent = false;
/* 182 */     this.m_clipMode = false;
/*     */     super.setLbsInputVerifier(ms_Verifier);
/*     */   }
/*     */   protected boolean isValueChangedByParent() {
/* 186 */     return this.m_ValueChangedByParent;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValueChangedByParent(boolean valueChangedByParent) {
/* 191 */     this.m_ValueChangedByParent = valueChangedByParent;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fireLbsFocusEvent(FocusEvent event, int id) {
/* 196 */     if (this.m_FocusListener != null)
/*     */     {
/* 198 */       if (id == 1004) {
/* 199 */         this.m_FocusListener.focusGained(event);
/* 200 */       } else if (id == 1005) {
/* 201 */         this.m_FocusListener.focusLost(event);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected void processFocusEvent(FocusEvent e) {
/* 207 */     super.processFocusEvent(e);
/* 208 */     if (this.m_EnableFocusEvent)
/* 209 */       fireLbsFocusEvent(e, e.getID()); 
/* 210 */     if (e.getID() == 1004) {
/*     */ 
/*     */       
/* 213 */       if (!isValueChangedByParent())
/* 214 */         this.m_OriginalText = getText(); 
/* 215 */       setValueChangedByParent(false);
/*     */       
/* 217 */       if (!this.m_EnableFocusEvent)
/* 218 */         this.m_EnableFocusEvent = true; 
/* 219 */       if (isEditable()) {
/* 220 */         selectAll();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClipMode(boolean clipMode) {
/* 227 */     this.m_clipMode = clipMode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMask() {
/* 233 */     if (this.m_Formatter != null && this.m_Formatter instanceof MaskFormatter)
/*     */     {
/* 235 */       return ((MaskFormatter)this.m_Formatter).getMask();
/*     */     }
/* 237 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JFormattedTextField.AbstractFormatter getFormatter() {
/* 243 */     return this.m_Formatter;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isClipMode() {
/* 249 */     return this.m_clipMode;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsMaskedEditEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */