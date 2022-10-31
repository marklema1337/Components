/*     */ package com.lbs.controls.datedit;
/*     */ 
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.util.Calendar;
/*     */ import javax.swing.JComponent;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsTimeEditImplementor
/*     */ {
/*     */   private ILbsInternalTimeEdit m_Component;
/*  31 */   private char m_chTimeSeparator = ':';
/*  32 */   private char m_chPlaceHolder = '0';
/*  33 */   private int m_iTimeFormat = 0;
/*     */   private boolean m_ShowSeconds = true;
/*     */   private boolean m_ShowMiliseconds = false;
/*     */   private boolean m_bInPMSlice = false;
/*  37 */   private String m_szPostfix = null;
/*     */   
/*     */   private Calendar m_CalendarValue;
/*     */   private String m_OldValue;
/*     */   
/*     */   public Calendar getCalendarValue() {
/*  43 */     return this.m_CalendarValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCalendarValue(Calendar calendarValue) {
/*  48 */     this.m_CalendarValue = calendarValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LbsTimeEditImplementor(ILbsInternalTimeEdit component, int iTimeFormat, boolean bShowSeconds, boolean bShowMilis, String szPostfix) {
/*  54 */     this.m_Component = component;
/*  55 */     this.m_szPostfix = szPostfix;
/*  56 */     this.m_ShowSeconds = bShowSeconds;
/*  57 */     this.m_ShowMiliseconds = (bShowSeconds && bShowMilis);
/*     */   }
/*     */ 
/*     */   
/*     */   public void init(int iTimeFormat) {
/*  62 */     this.m_Component.setTimeFormat(iTimeFormat);
/*     */     
/*  64 */     JLbsTimeEditInputAdapter adapter = new JLbsTimeEditInputAdapter(this);
/*  65 */     this.m_Component.addKeyListener(adapter);
/*  66 */     if (this.m_Component instanceof JComponent) {
/*  67 */       ((JComponent)this.m_Component).addInputMethodListener(adapter);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setTimeFormat(int iTimeFormat) {
/*     */     try {
/*  74 */       switch (iTimeFormat) {
/*     */         
/*     */         case 1:
/*     */         case 2:
/*  78 */           this.m_iTimeFormat = iTimeFormat;
/*     */           break;
/*     */         default:
/*  81 */           this.m_iTimeFormat = 0; break;
/*     */       } 
/*  83 */       String szTimeFormat = "##:##";
/*  84 */       if (this.m_ShowSeconds)
/*  85 */         szTimeFormat = String.valueOf(szTimeFormat) + ":##"; 
/*  86 */       if (this.m_ShowMiliseconds)
/*  87 */         szTimeFormat = String.valueOf(szTimeFormat) + ":##"; 
/*  88 */       if (this.m_chTimeSeparator != ':')
/*  89 */         szTimeFormat = szTimeFormat.replace(':', this.m_chTimeSeparator); 
/*  90 */       MaskFormatter formatter = new MaskFormatter(szTimeFormat);
/*  91 */       formatter.setPlaceholderCharacter(this.m_chPlaceHolder);
/*  92 */       this.m_Component.setTimeFormatter(formatter);
/*  93 */       return true;
/*     */     }
/*  95 */     catch (Exception ex) {
/*     */       
/*  97 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPostfix() {
/* 103 */     return this.m_szPostfix;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPostfix(String szPostfix) {
/* 108 */     this.m_szPostfix = szPostfix;
/* 109 */     this.m_Component.repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public char getTimeSeparator() {
/* 114 */     return this.m_chTimeSeparator;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setTimeSeparator(char chSeparator) {
/* 119 */     if (this.m_chTimeSeparator != chSeparator) {
/*     */       
/* 121 */       char chSaveSeparator = this.m_chTimeSeparator;
/* 122 */       this.m_chTimeSeparator = chSeparator;
/* 123 */       if (this.m_Component.setTimeFormat(this.m_iTimeFormat))
/* 124 */         return true; 
/* 125 */       this.m_chTimeSeparator = chSaveSeparator;
/* 126 */       this.m_Component.setTimeFormat(this.m_iTimeFormat);
/*     */     } 
/* 128 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public char getMaskPlaceHolder() {
/* 133 */     return this.m_chPlaceHolder;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaskPlaceHolder(char chPlaceHolder) {
/* 138 */     if (this.m_chPlaceHolder != chPlaceHolder) {
/*     */       
/* 140 */       this.m_chPlaceHolder = chPlaceHolder;
/* 141 */       this.m_Component.setTimeFormat(this.m_iTimeFormat);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsTimeDuration getTime() {
/* 147 */     String szText = this.m_Component.getText();
/* 148 */     int iHour = getSectionValue(szText, 0, 2);
/* 149 */     if (this.m_iTimeFormat == 1 && iHour < 12 && this.m_bInPMSlice)
/* 150 */       iHour += 12; 
/* 151 */     int iMinute = getSectionValue(szText, 3, 2);
/* 152 */     int iSecond = getSectionValue(szText, 6, 2);
/* 153 */     int iMilis = getSectionValue(szText, 9, 2);
/* 154 */     return new JLbsTimeDuration(iHour, iMinute, iSecond, iMilis);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTime(Calendar time) {
/* 159 */     if (time == null)
/*     */       return; 
/* 161 */     this.m_Component.setTime(new JLbsTimeDuration(time));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTime(JLbsTimeDuration duration) {
/* 166 */     if (duration == null)
/*     */       return; 
/* 168 */     StringBuilder buffer = new StringBuilder();
/* 169 */     buffer.append(getSectionStr((this.m_iTimeFormat == 1) ? (
/* 170 */           duration.getHour() % 12) : 
/* 171 */           duration.getHour()));
/* 172 */     buffer.append(this.m_chTimeSeparator);
/* 173 */     buffer.append(getSectionStr(duration.getMinute()));
/* 174 */     if (this.m_ShowSeconds) {
/*     */       
/* 176 */       buffer.append(this.m_chTimeSeparator);
/* 177 */       buffer.append(getSectionStr(duration.getSecond()));
/*     */     } 
/* 179 */     if (this.m_ShowMiliseconds) {
/*     */       
/* 181 */       buffer.append(this.m_chTimeSeparator);
/* 182 */       buffer.append(getSectionStr(duration.getMilisecond()));
/*     */     } 
/* 184 */     this.m_bInPMSlice = (duration.getHour() >= 12);
/* 185 */     this.m_Component.setText(buffer.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getShowSeconds() {
/* 190 */     return this.m_ShowSeconds;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShowSeconds(boolean b) {
/* 195 */     if (this.m_ShowSeconds != b) {
/*     */       
/* 197 */       this.m_ShowSeconds = b;
/* 198 */       this.m_Component.setTimeFormat(this.m_iTimeFormat);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTimeFormatEx(int dispFormat, boolean duration) {
/* 204 */     switch (dispFormat) {
/*     */       
/*     */       case 22:
/* 207 */         this.m_iTimeFormat = 0;
/* 208 */         this.m_ShowSeconds = false;
/* 209 */         this.m_ShowMiliseconds = false;
/*     */         break;
/*     */       case 21:
/*     */       case 23:
/* 213 */         this.m_iTimeFormat = 0;
/* 214 */         this.m_ShowSeconds = true;
/* 215 */         this.m_ShowMiliseconds = false;
/*     */         break;
/*     */       case 24:
/* 218 */         this.m_iTimeFormat = 0;
/* 219 */         this.m_ShowSeconds = true;
/* 220 */         this.m_ShowMiliseconds = true;
/*     */         break;
/*     */       case 25:
/* 223 */         this.m_iTimeFormat = 1;
/* 224 */         this.m_ShowSeconds = false;
/* 225 */         this.m_ShowMiliseconds = false;
/*     */         break;
/*     */       case 26:
/* 228 */         this.m_iTimeFormat = 1;
/* 229 */         this.m_ShowSeconds = true;
/* 230 */         this.m_ShowMiliseconds = false;
/*     */         break;
/*     */     } 
/* 233 */     if (duration)
/* 234 */       this.m_iTimeFormat = 2; 
/* 235 */     this.m_Component.setTimeFormat(this.m_iTimeFormat);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getSectionStr(int iVal) {
/* 240 */     iVal = Math.max(0, Math.min(99, iVal));
/* 241 */     if (iVal < 10)
/* 242 */       return "0" + Integer.toString(iVal); 
/* 243 */     return Integer.toString(iVal);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSectionValue(String szSource, int iOffs, int iLen) {
/* 248 */     if (szSource != null) {
/*     */       
/* 250 */       int iSrcLen = szSource.length();
/* 251 */       if (iOffs < iSrcLen) {
/*     */         
/* 253 */         int iSectionlen = Math.min(iSrcLen, iOffs + iLen);
/*     */         
/*     */         try {
/* 256 */           String szSection = szSource.substring(iOffs, iSectionlen);
/* 257 */           return Integer.parseInt(szSection);
/*     */         }
/* 259 */         catch (Exception exception) {}
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 264 */     return 0;
/*     */   }
/*     */   
/*     */   public void processKeyEvent(KeyEvent e) {
/*     */     char ch;
/* 269 */     switch (e.getID()) {
/*     */       
/*     */       case 400:
/* 272 */         ch = e.getKeyChar();
/* 273 */         if (Character.isDigit(ch) && this.m_Component.getSelectionStart() == 0 && 
/* 274 */           this.m_Component.getSelectionEnd() == this.m_Component.getText().length()) {
/*     */           
/* 276 */           this.m_Component.setText("");
/* 277 */           this.m_Component.setCaretPosition(0);
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isCharAcceptable(char ch) {
/* 285 */     int iPos = this.m_Component.getCaretPosition();
/*     */     
/* 287 */     int start = this.m_Component.getSelectionStart();
/* 288 */     int end = this.m_Component.getSelectionEnd();
/*     */     
/* 290 */     if (start != end && iPos == end) {
/* 291 */       iPos = start;
/*     */     }
/* 293 */     return isCharAcceptable(ch, iPos);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isCharAcceptable(char ch, int iCaretPos) {
/* 298 */     if (ch == '\b')
/* 299 */       return true; 
/* 300 */     if (Character.isDigit(ch)) {
/*     */       
/* 302 */       String szText = this.m_Component.getText();
/* 303 */       return isCharacterValidAtPos(szText, ch, iCaretPos);
/*     */     } 
/* 305 */     if (this.m_iTimeFormat == 1)
/*     */     {
/* 307 */       switch (ch) {
/*     */         
/*     */         case 'A':
/*     */         case 'a':
/* 311 */           if (this.m_bInPMSlice) {
/*     */             
/* 313 */             this.m_bInPMSlice = false;
/* 314 */             this.m_Component.repaint();
/*     */           } 
/*     */           break;
/*     */         case 'P':
/*     */         case 'p':
/* 319 */           if (!this.m_bInPMSlice) {
/*     */             
/* 321 */             this.m_bInPMSlice = true;
/* 322 */             this.m_Component.repaint();
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     }
/* 327 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isCharacterValidAtPos(String szText, char ch, int iPos) {
/* 332 */     if (Character.isDigit(ch) && szText != null && iPos < szText.length())
/*     */     {
/* 334 */       switch (iPos) {
/*     */         
/*     */         case 0:
/* 337 */           switch (this.m_iTimeFormat) {
/*     */             
/*     */             case 1:
/* 340 */               return (ch <= '1' && szText.charAt(1) < '2');
/*     */             case 2:
/* 342 */               return true;
/*     */           } 
/* 344 */           return !(ch >= '2' && (ch != '2' || szText.charAt(1) > '3'));
/*     */         
/*     */         case 1:
/* 347 */           switch (this.m_iTimeFormat) {
/*     */             
/*     */             case 1:
/* 350 */               return !(szText.charAt(0) != '0' && ch >= '2');
/*     */             case 2:
/* 352 */               return true;
/*     */           } 
/* 354 */           return !(szText.charAt(0) >= '2' && ch > '3');
/*     */         
/*     */         case 3:
/*     */         case 6:
/* 358 */           return (ch < '6');
/*     */         case 4:
/*     */         case 7:
/* 361 */           return true;
/*     */         case 9:
/*     */         case 10:
/* 364 */           return true;
/*     */       } 
/*     */     }
/* 367 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getFormattedTextExtras() {
/* 372 */     String szText = (this.m_iTimeFormat == 1) ? (
/* 373 */       this.m_bInPMSlice ? 
/* 374 */       "PM " : 
/* 375 */       "AM ") : 
/* 376 */       "";
/* 377 */     if (this.m_szPostfix != null)
/* 378 */       szText = String.valueOf(szText) + this.m_szPostfix; 
/* 379 */     return szText;
/*     */   }
/*     */ 
/*     */   
/*     */   public void focusGained(FocusEvent e) {
/* 384 */     this.m_OldValue = this.m_Component.getText();
/*     */   }
/*     */ 
/*     */   
/*     */   public void focusLost(FocusEvent e) {
/* 389 */     if (e.isTemporary())
/*     */       return; 
/* 391 */     String timeText = this.m_Component.getText();
/* 392 */     if (timeText == null) {
/*     */       return;
/*     */     }
/* 395 */     boolean ok = true;
/* 396 */     for (int i = 0; i < timeText.length(); i++) {
/*     */       
/* 398 */       char chr = timeText.charAt(i);
/*     */       
/* 400 */       if (Character.isDigit(chr)) {
/*     */         
/* 402 */         ok = isCharAcceptable(chr, i);
/* 403 */         if (!ok) {
/*     */           
/* 405 */           this.m_Component.setText(this.m_OldValue);
/* 406 */           this.m_Component.requestFocus();
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValueModified() {
/* 415 */     String szText = this.m_Component.getText();
/* 416 */     if (this.m_OldValue != null) {
/* 417 */       return !this.m_OldValue.equals(szText);
/*     */     }
/* 419 */     return !JLbsStringUtil.isEmpty(szText);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\datedit\LbsTimeEditImplementor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */