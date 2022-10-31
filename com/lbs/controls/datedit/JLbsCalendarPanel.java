/*     */ package com.lbs.controls.datedit;
/*     */ 
/*     */ import com.ghasemkiani.util.DateFields;
/*     */ import com.ghasemkiani.util.SimplePersianCalendar;
/*     */ import com.lbs.controls.ILbsFlipComponent;
/*     */ import com.lbs.controls.JLbsControlHelper;
/*     */ import com.lbs.controls.JLbsFlipTransform;
/*     */ import com.lbs.controls.JLbsPanel;
/*     */ import com.lbs.globalization.ILbsCultureCalendarHandler;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsDateUtil;
/*     */ import com.lbs.util.UIHelperUtil;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.SystemColor;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.Popup;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsCalendarPanel
/*     */   extends JLbsPanel
/*     */   implements ILbsFlipComponent
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  61 */   public static int HEADERHEIGHT = 24;
/*  62 */   public static int MONTHITEMBASE = 100;
/*  63 */   public static int YEARITEMBASE = 200;
/*  64 */   public static int DAYPRINTOFFSET = 1;
/*     */   
/*  66 */   protected int m_iDay = 17;
/*  67 */   protected int m_iMonth = 8;
/*  68 */   protected int m_iYear = 1975;
/*  69 */   protected Rectangle m_DateGridRect = null;
/*     */   
/*     */   protected ILbsCultureInfo m_CultureInfo;
/*     */   protected ILbsCalendarPanelListener m_CalListener;
/*     */   protected boolean m_InDialog = false;
/*     */   protected boolean m_ShowWeeks;
/*     */   protected JButton m_Today;
/*     */   public boolean m_PersianCalendar = false;
/*  77 */   protected int m_CalendarType = 0;
/*     */ 
/*     */   
/*  80 */   protected static final Color TODAYCOLOR = new Color(10, 36, 106);
/*     */   
/*     */   protected Color m_HeaderTextColor;
/*     */   protected Color m_TextColor;
/*     */   protected Color m_LineColor;
/*     */   protected JLbsFlipTransform m_FlipTransform;
/*  86 */   private Popup m_Popup = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsCalendarPanel() {
/*  94 */     enableEvents(28L);
/*  95 */     addMouseListener(new JLbsCalendarPanelMouseAdapter());
/*  96 */     addKeyListener(new JLbsCalendarPanelKeyAdapter());
/*  97 */     createTodayButton();
/*     */     
/*  99 */     setOpaque(true);
/*     */     
/* 101 */     this.m_FlipTransform = new JLbsFlipTransform(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public Popup getPopup() {
/* 106 */     return this.m_Popup;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPopup(Popup popup) {
/* 111 */     this.m_Popup = popup;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static Font getHeaderFont() {
/* 116 */     return new Font(JLbsConstants.APP_FONT, 1, 10);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static Font getNormalFont() {
/* 121 */     return new Font(JLbsConstants.APP_FONT, 0, 9);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static Font getWeekFont() {
/* 126 */     return new Font(JLbsConstants.APP_FONT, 0, 9);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getMonthName(int iMonth) {
/* 131 */     if (this.m_CultureInfo != null)
/* 132 */       return this.m_CultureInfo.getMonthFullName(iMonth % 12 + 1); 
/* 133 */     if (iMonth >= 0 && iMonth < 12)
/* 134 */       return JLbsDateUtil.monthNames[iMonth]; 
/* 135 */     return JLbsDateUtil.monthNames[11];
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getMonthName(int iMonth, int calendarType) {
/* 140 */     if (this.m_CultureInfo != null)
/* 141 */       return this.m_CultureInfo.getMonthFullName(iMonth % 12 + 1, this.m_CalendarType); 
/* 142 */     if (iMonth >= 0 && iMonth < 12)
/* 143 */       return JLbsDateUtil.monthNames[iMonth]; 
/* 144 */     return JLbsDateUtil.monthNames[11];
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getDayHeader(int iDay) {
/* 149 */     iDay = (iDay + DAYPRINTOFFSET) % 7;
/* 150 */     String name = (this.m_CultureInfo != null) ? 
/* 151 */       this.m_CultureInfo.getDayShortName(iDay + 1) : 
/* 152 */       JLbsDateUtil.dayNames[iDay];
/* 153 */     return name;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getDayHeader(int iDay, int calendarType) {
/* 158 */     iDay = (iDay + DAYPRINTOFFSET) % 7;
/* 159 */     String name = (this.m_CultureInfo != null) ? 
/* 160 */       this.m_CultureInfo.getDayShortName(iDay + 1, calendarType) : 
/* 161 */       JLbsDateUtil.dayNames[iDay];
/* 162 */     return name;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 167 */     if (this.m_Today != null)
/* 168 */       return new Dimension(166, 195); 
/* 169 */     return new Dimension(166, 175);
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(boolean isFAIR) {
/* 174 */     if (!isFAIR) {
/* 175 */       return getPreferredSize();
/*     */     }
/*     */     
/* 178 */     if (this.m_Today != null)
/* 179 */       return new Dimension(250, 195); 
/* 180 */     return new Dimension(250, 175);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCultureInfo(ILbsCultureInfo cultureInfo) {
/* 189 */     if (this.m_CultureInfo != cultureInfo) {
/*     */       
/* 191 */       this.m_CultureInfo = cultureInfo;
/* 192 */       if (this.m_CultureInfo instanceof ILbsCultureCalendarHandler) {
/*     */         
/* 194 */         this.m_PersianCalendar = (this.m_CultureInfo.getCalendarType() == 1);
/* 195 */         this.m_CalendarType = this.m_CultureInfo.getCalendarType();
/*     */       } 
/* 197 */       invalidate();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDay() {
/* 207 */     return this.m_iDay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setDay(int iDay) {
/* 218 */     if (iDay <= 0 || this.m_iDay == iDay || validDay(iDay))
/* 219 */       return false; 
/* 220 */     fireDateSelectionChanged();
/* 221 */     this.m_iDay = iDay;
/* 222 */     repaint();
/* 223 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean validDay(int iDay) {
/* 228 */     return (iDay > getNumOfDaysInMonth(this.m_iMonth, this.m_iYear));
/*     */   }
/*     */ 
/*     */   
/*     */   private int getNumOfDaysInMonth(int month, int year) {
/* 233 */     return (this.m_CultureInfo != null) ? 
/* 234 */       this.m_CultureInfo.getNumberOfDaysInMonth(month, year) : 
/* 235 */       JLbsDateUtil.getNumDays(month, year);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setToday() {
/* 243 */     if (this.m_CultureInfo == null) {
/*     */       
/*     */       try {
/*     */         
/* 247 */         Class<?> cls = Class.forName("com.lbs.globalization.JLbsDefaultCultureInfo");
/* 248 */         this.m_CultureInfo = (ILbsCultureInfo)cls.newInstance();
/*     */       }
/* 250 */       catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */     
/* 254 */     Calendar calendar = this.m_CultureInfo.getCalendarInstance();
/* 255 */     setDate(calendar);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void incrementDays(int iOffset) {
/* 266 */     if (iOffset == 0)
/*     */       return; 
/* 268 */     int iNewDay = this.m_iDay + iOffset;
/* 269 */     if (iNewDay <= 0) {
/*     */       
/* 271 */       while (iNewDay <= 0) {
/*     */         
/* 273 */         if (this.m_iMonth <= 0) {
/*     */           
/* 275 */           this.m_iYear--;
/* 276 */           this.m_iMonth = 11;
/*     */         } else {
/*     */           
/* 279 */           this.m_iMonth--;
/* 280 */         }  int iMonthDays = getNumOfDaysInMonth(this.m_iMonth, this.m_iYear);
/* 281 */         iNewDay += iMonthDays;
/*     */       } 
/* 283 */       this.m_iDay = iNewDay;
/*     */     }
/*     */     else {
/*     */       
/* 287 */       int iCurrMonthDays = getNumOfDaysInMonth(this.m_iMonth, this.m_iYear);
/* 288 */       while (iNewDay > iCurrMonthDays) {
/*     */         
/* 290 */         iNewDay -= iCurrMonthDays;
/* 291 */         if (this.m_iMonth >= 11) {
/*     */           
/* 293 */           this.m_iYear++;
/* 294 */           this.m_iMonth = 0;
/*     */         } else {
/*     */           
/* 297 */           this.m_iMonth++;
/* 298 */         }  iCurrMonthDays = getNumOfDaysInMonth(this.m_iMonth, this.m_iYear);
/*     */       } 
/* 300 */       this.m_iDay = iNewDay;
/*     */     } 
/* 302 */     fireDateSelectionChanged();
/* 303 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMonth() {
/* 312 */     return this.m_iMonth;
/*     */   }
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
/*     */   public boolean setMonth(int iMonth) {
/* 325 */     if (this.m_iMonth == iMonth)
/* 326 */       return false; 
/* 327 */     if (iMonth < 0 || iMonth >= 12) {
/*     */       
/* 329 */       int iMonthCount = this.m_iYear * 12 + iMonth;
/* 330 */       this.m_iYear = iMonthCount / 12;
/* 331 */       this.m_iMonth = iMonthCount % 12;
/*     */     } else {
/*     */       
/* 334 */       this.m_iMonth = iMonth;
/*     */     } 
/* 336 */     fireDateSelectionChanged();
/* 337 */     refreshPopup();
/* 338 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void refreshPopup() {
/* 343 */     if (getPopup() != null) {
/*     */       
/* 345 */       getPopup().show();
/* 346 */       repaint();
/*     */     } else {
/*     */       
/* 349 */       repaint();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getYear() {
/* 358 */     return this.m_iYear;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setYear(int iYear) {
/* 368 */     if (iYear < 0 || this.m_iYear == iYear)
/* 369 */       return false; 
/* 370 */     this.m_iYear = iYear;
/* 371 */     fireDateSelectionChanged();
/* 372 */     refreshPopup();
/* 373 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setDate(Date date) {
/* 383 */     if (this.m_CultureInfo == null) {
/*     */       
/*     */       try {
/*     */         
/* 387 */         Class<?> cls = Class.forName("com.lbs.globalization.JLbsDefaultCultureInfo");
/* 388 */         this.m_CultureInfo = (ILbsCultureInfo)cls.newInstance();
/*     */       }
/* 390 */       catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */     
/* 394 */     Calendar calendar = this.m_CultureInfo.getCalendarInstance();
/* 395 */     calendar.setTime(date);
/* 396 */     return setDate(calendar);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setDate(Calendar calendar) {
/*     */     try {
/*     */       int iDay;
/*     */       int iMonth;
/*     */       int iYear;
/*     */       SimplePersianCalendar persianCalendar;
/*     */       DateFields dateFields;
/* 409 */       switch (this.m_CalendarType) {
/*     */         
/*     */         case 1:
/* 412 */           persianCalendar = (SimplePersianCalendar)((ILbsCultureCalendarHandler)this.m_CultureInfo)
/* 413 */             .beforeCalendarPanelShow(calendar);
/* 414 */           dateFields = persianCalendar.getDateFields();
/* 415 */           iDay = dateFields.getDay();
/* 416 */           iMonth = dateFields.getMonth();
/* 417 */           iYear = dateFields.getYear();
/*     */           break;
/*     */         default:
/* 420 */           iDay = calendar.get(5);
/* 421 */           iMonth = calendar.get(2);
/* 422 */           iYear = calendar.get(1);
/*     */           break;
/*     */       } 
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
/* 440 */       if (this.m_iDay != iDay || this.m_iMonth != iMonth || this.m_iYear != iYear)
/*     */       {
/* 442 */         this.m_iDay = iDay;
/* 443 */         this.m_iMonth = iMonth;
/* 444 */         this.m_iYear = iYear;
/* 445 */         fireDateSelectionChanged();
/* 446 */         repaint();
/* 447 */         return true;
/*     */       }
/*     */     
/* 450 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 453 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCalendarListener(ILbsCalendarPanelListener listener) {
/* 463 */     this.m_CalListener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusable() {
/* 468 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fireDateSelectionChanged() {
/* 474 */     if (this.m_CalListener != null) {
/*     */       DateFields dateFields; SimplePersianCalendar persianCalendar; GregorianCalendar calendar;
/* 476 */       int year = this.m_iYear;
/* 477 */       int month = this.m_iMonth;
/* 478 */       int day = this.m_iDay;
/*     */       
/* 480 */       switch (this.m_CalendarType) {
/*     */         
/*     */         case 1:
/* 483 */           dateFields = new DateFields(this.m_iYear, this.m_iMonth, this.m_iDay);
/* 484 */           persianCalendar = new SimplePersianCalendar();
/* 485 */           persianCalendar.setDateFields(dateFields);
/* 486 */           calendar = (GregorianCalendar)((ILbsCultureCalendarHandler)this.m_CultureInfo)
/* 487 */             .beforeCalendarPanelSetCalendar((Calendar)persianCalendar);
/* 488 */           year = calendar.get(1);
/* 489 */           month = calendar.get(2);
/* 490 */           day = calendar.get(5);
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/* 495 */       this.m_CalListener.dateSelectionChanged(this, day, month, year);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fireDateSelected() {
/* 501 */     if (this.m_CalListener != null) {
/*     */       DateFields dateFields; SimplePersianCalendar persianCalendar; GregorianCalendar calendar;
/* 503 */       int year = this.m_iYear;
/* 504 */       int month = this.m_iMonth;
/* 505 */       int day = this.m_iDay;
/*     */       
/* 507 */       switch (this.m_CalendarType) {
/*     */         
/*     */         case 1:
/* 510 */           dateFields = new DateFields(this.m_iYear, this.m_iMonth, this.m_iDay);
/* 511 */           persianCalendar = new SimplePersianCalendar();
/* 512 */           persianCalendar.setDateFields(dateFields);
/* 513 */           calendar = (GregorianCalendar)((ILbsCultureCalendarHandler)this.m_CultureInfo)
/* 514 */             .beforeCalendarPanelSetCalendar((Calendar)persianCalendar);
/* 515 */           year = calendar.get(1);
/* 516 */           month = calendar.get(2);
/* 517 */           day = calendar.get(5);
/*     */           break;
/*     */       } 
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
/* 534 */       this.m_CalListener.dateSelected(this, day, month, year);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getDayOfWeekInternal(int y, int m, int d) {
/* 540 */     switch (this.m_CalendarType) {
/*     */       
/*     */       case 1:
/* 543 */         return ((ILbsCultureCalendarHandler)this.m_CultureInfo).getDayOfWeek(y, m, d);
/*     */     } 
/* 545 */     return (JLbsDateUtil.getDayOfWeek(this.m_iYear, this.m_iMonth, 1) + 7 - DAYPRINTOFFSET) % 7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getWeekIndexInternal(int day, int month, int year) {
/* 556 */     switch (this.m_CalendarType) {
/*     */       
/*     */       case 1:
/* 559 */         return ((ILbsCultureCalendarHandler)this.m_CultureInfo).getWeekIndex(day, month, year);
/*     */     } 
/* 561 */     return JLbsDateUtil.getWeekIndex(day, month, year);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getTodayIndex() {
/*     */     SimplePersianCalendar persianCalendar;
/*     */     DateFields dateFields;
/* 574 */     Calendar cal = this.m_CultureInfo.getCalendarInstance();
/*     */     
/* 576 */     switch (this.m_CalendarType)
/*     */     
/*     */     { case 1:
/* 579 */         persianCalendar = (SimplePersianCalendar)((ILbsCultureCalendarHandler)this.m_CultureInfo).beforeCalendarPanelShow(cal);
/* 580 */         dateFields = persianCalendar.getDateFields();
/* 581 */         todayIndex = (dateFields.getMonth() == this.m_iMonth && dateFields.getYear() == this.m_iYear) ? 
/* 582 */           dateFields.getDay() : 
/* 583 */           -1;
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
/* 606 */         return todayIndex; }  int todayIndex = (cal.get(2) == this.m_iMonth && cal.get(1) == this.m_iYear) ? cal.get(5) : -1; return todayIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/* 611 */     this.m_FlipTransform.paintComponent(g);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintFlipComponent(Graphics g) {
/* 617 */     Rectangle rectClient = JLbsControlHelper.getClientRect((JComponent)this);
/* 618 */     Color bkcol = UIManager.getColor("Button.background");
/* 619 */     g.setColor((bkcol != null) ? 
/* 620 */         bkcol : 
/* 621 */         SystemColor.control);
/* 622 */     JLbsControlHelper.fillHeaderRectangle(g, rectClient.x + 2, rectClient.y + 2, rectClient.x + rectClient.width - 2, 
/* 623 */         rectClient.y + HEADERHEIGHT, false);
/*     */ 
/*     */     
/* 626 */     g.setColor(this.m_LineColor);
/* 627 */     if (this.m_InDialog) {
/*     */       
/* 629 */       g.drawLine(rectClient.x + rectClient.width - 2, rectClient.y, rectClient.x, rectClient.y);
/* 630 */       g.drawLine(rectClient.x, rectClient.y + 1, rectClient.x, rectClient.y + rectClient.height - 2);
/*     */     } 
/* 632 */     g.drawLine(rectClient.x + rectClient.width - 2, rectClient.y + HEADERHEIGHT, rectClient.x, rectClient.y + HEADERHEIGHT);
/* 633 */     g.drawLine(rectClient.x + rectClient.width - 2, rectClient.y + 1, rectClient.x + rectClient.width - 2, rectClient.y + 
/* 634 */         HEADERHEIGHT - 1);
/* 635 */     int iMonthTextX = rectClient.x + rectClient.width * 9 / 16;
/* 636 */     g.drawLine(iMonthTextX, rectClient.y + 4, iMonthTextX, rectClient.y + HEADERHEIGHT - 4);
/*     */ 
/*     */ 
/*     */     
/* 640 */     g.setColor(this.m_LineColor);
/* 641 */     if (!this.m_InDialog) {
/*     */       
/* 643 */       g.drawLine(rectClient.x + rectClient.width - 2, rectClient.y, rectClient.x, rectClient.y);
/* 644 */       g.drawLine(rectClient.x, rectClient.y + 1, rectClient.x, rectClient.y + rectClient.height - 2);
/*     */     } 
/* 646 */     g.drawLine(rectClient.x + rectClient.width - 1, rectClient.y, rectClient.x + rectClient.width - 1, rectClient.y + 
/* 647 */         rectClient.height - 1);
/* 648 */     g.drawLine(rectClient.x, rectClient.y + rectClient.height - 1, rectClient.x + rectClient.width - 1, rectClient.y + 
/* 649 */         rectClient.height - 1);
/* 650 */     g.drawLine(rectClient.x + 1, rectClient.y + 1, rectClient.x + rectClient.width - 3, rectClient.y + 1);
/* 651 */     g.drawLine(rectClient.x + 1, rectClient.y + 2, rectClient.x + 1, rectClient.y + HEADERHEIGHT - 1);
/* 652 */     g.drawLine(iMonthTextX + 1, rectClient.y + 4, iMonthTextX + 1, rectClient.y + HEADERHEIGHT - 4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 659 */     g.setColor(Color.black);
/*     */     
/* 661 */     int iX = rectClient.x + 6;
/* 662 */     int iY = rectClient.y + HEADERHEIGHT / 2 - 5;
/* 663 */     int[] px1 = { iX, iX + 5, iX + 5 };
/* 664 */     int[] py1 = { iY + 5, iY, iY + 10 };
/* 665 */     g.fillPolygon(px1, py1, 3);
/* 666 */     iX = iMonthTextX - 9;
/* 667 */     int[] px2 = { iX, iX + 5, iX };
/* 668 */     int[] py2 = { iY, iY + 5, iY + 10 };
/* 669 */     g.fillPolygon(px2, py2, 3);
/*     */     
/* 671 */     iX = iMonthTextX + 6;
/* 672 */     int[] px3 = { iX, iX + 5, iX + 5 };
/* 673 */     int[] py3 = { iY + 5, iY, iY + 10 };
/* 674 */     g.fillPolygon(px3, py3, 3);
/*     */     
/* 676 */     iX = rectClient.x + rectClient.width - 14;
/* 677 */     int[] px4 = { iX, iX + 5, iX };
/* 678 */     int[] py4 = { iY, iY + 5, iY + 10 };
/* 679 */     g.fillPolygon(px4, py4, 3);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 684 */     Rectangle rectText = new Rectangle(rectClient.x + 12, rectClient.y + 4, iMonthTextX - 22, HEADERHEIGHT - 4);
/* 685 */     JLbsFlipTransform.drawString((Graphics2D)g, getMonthName(this.m_iMonth, this.m_CalendarType), this.m_HeaderTextColor, getNormalFont(), 
/* 686 */         rectText, 0, 0, true);
/*     */     
/* 688 */     rectText = new Rectangle(iMonthTextX + 2, rectClient.y + 4, rectClient.x + rectClient.width - iMonthTextX - 4, 
/* 689 */         HEADERHEIGHT - 4);
/* 690 */     JLbsFlipTransform.drawString((Graphics2D)g, Integer.toString(this.m_iYear), this.m_HeaderTextColor, getNormalFont(), rectText, 
/* 691 */         0, 0, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 696 */     rectText = new Rectangle(rectClient.x + 2, rectClient.y + HEADERHEIGHT + 2, rectClient.width - 4, rectClient.height - 
/* 697 */         HEADERHEIGHT - 4);
/*     */     
/* 699 */     if (this.m_ShowWeeks) {
/*     */       
/* 701 */       rectText.width -= 20;
/* 702 */       rectText.x += 20;
/*     */     } 
/* 704 */     int iCellWidth = rectText.width / 7;
/* 705 */     if (this.m_Today != null) {
/*     */       
/* 707 */       rectText.height -= (this.m_Today.getPreferredSize()).height + 2;
/*     */       
/* 709 */       g.setColor(this.m_LineColor);
/* 710 */       int y = rectText.y + rectText.height - 1;
/* 711 */       g.drawLine(rectClient.x, y, rectClient.x + rectClient.width, y);
/*     */     } 
/*     */     
/* 714 */     int iCellHeight = rectText.height / 7;
/*     */ 
/*     */ 
/*     */     
/* 718 */     Rectangle rectCell = new Rectangle(rectText.x, rectText.y, iCellWidth, iCellHeight);
/*     */ 
/*     */     
/* 721 */     for (int i = 0; i < 7; i++) {
/*     */       
/* 723 */       switch (this.m_CalendarType) {
/*     */         
/*     */         case 1:
/* 726 */           JLbsFlipTransform.drawString((Graphics2D)g, getDayHeader(i + 5, this.m_CalendarType), this.m_HeaderTextColor, 
/* 727 */               getHeaderFont(), rectCell, 0, 0, false);
/*     */           break;
/*     */         default:
/* 730 */           JLbsFlipTransform.drawString((Graphics2D)g, getDayHeader(i, this.m_CalendarType), this.m_HeaderTextColor, 
/* 731 */               getHeaderFont(), rectCell, 0, 0, false); break;
/* 732 */       }  rectCell.x += 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 741 */         iCellWidth;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 747 */     g.setFont(getNormalFont());
/* 748 */     rectCell.y += iCellHeight;
/* 749 */     this.m_DateGridRect = new Rectangle(rectText.x, rectCell.y, rectText.width, rectText.height - iCellHeight);
/*     */     
/* 751 */     int iDayStart = getDayOfWeekInternal(this.m_iYear, this.m_iMonth, 1);
/* 752 */     int iDayCount = getNumOfDaysInMonth(this.m_iMonth, this.m_iYear);
/*     */     
/* 754 */     int iCurrDay = iDayStart;
/* 755 */     rectText.x += iCellWidth * iDayStart;
/*     */     
/* 757 */     int todayIndex = 0;
/* 758 */     todayIndex = getTodayIndex();
/* 759 */     g.setColor(this.m_TextColor);
/* 760 */     int lastWeek = -1;
/*     */     
/* 762 */     for (int j = 1; j <= iDayCount; j++) {
/*     */       
/* 764 */       if (j == this.m_iDay) {
/*     */         
/* 766 */         g.setColor(Color.gray);
/* 767 */         g.fillRect(rectCell.x + 2, rectCell.y + 1, rectCell.width - 4, rectCell.height - 2);
/* 768 */         g.setColor(Color.white);
/* 769 */         JLbsFlipTransform.drawString((Graphics2D)g, Integer.toString(j), Color.white, getNormalFont(), rectCell, 
/* 770 */             0, 0, true);
/*     */         
/* 772 */         g.setColor(this.m_TextColor);
/*     */       }
/*     */       else {
/*     */         
/* 776 */         JLbsFlipTransform.drawString((Graphics2D)g, Integer.toString(j), this.m_TextColor, getNormalFont(), rectCell, 
/* 777 */             0, 0, true);
/*     */       } 
/*     */       
/* 780 */       if (todayIndex == j) {
/*     */         
/* 782 */         g.setColor(TODAYCOLOR);
/* 783 */         g.drawRect(rectCell.x + 2, rectCell.y + 1, rectCell.width - 5, rectCell.height - 3);
/* 784 */         g.setColor(this.m_TextColor);
/*     */       } 
/*     */       
/* 787 */       if (this.m_ShowWeeks && lastWeek == -1) {
/*     */         
/* 789 */         lastWeek = getWeekIndexInternal(j, this.m_iMonth, this.m_iYear);
/* 790 */         g.setColor(Color.GRAY);
/* 791 */         g.setFont(getWeekFont());
/* 792 */         Rectangle rectWeek = new Rectangle(rectClient.x + 3, rectCell.y, 16, iCellHeight);
/* 793 */         JLbsFlipTransform.drawString((Graphics2D)g, Integer.toString(lastWeek), Color.GRAY, getWeekFont(), rectWeek, 
/* 794 */             0, 0, true);
/*     */         
/* 796 */         g.setColor(this.m_TextColor);
/* 797 */         g.setFont(getNormalFont());
/*     */       } 
/* 799 */       if (iCurrDay >= 6) {
/*     */         
/* 801 */         rectCell.x = rectText.x;
/* 802 */         rectCell.y += iCellHeight;
/* 803 */         iCurrDay = 0;
/* 804 */         lastWeek = -1;
/*     */       }
/*     */       else {
/*     */         
/* 808 */         rectCell.x += iCellWidth;
/* 809 */         iCurrDay++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean dateGridRectangleContainsPoint(int x, int y) {
/* 817 */     if (!this.m_ShowWeeks)
/* 818 */       return this.m_DateGridRect.contains(x, y); 
/* 819 */     if (this.m_FlipTransform.isFlipped()) {
/*     */       
/* 821 */       Rectangle rect = (Rectangle)this.m_DateGridRect.clone();
/* 822 */       rect.x -= 20;
/* 823 */       rect.width += 20;
/* 824 */       return rect.contains(x, y);
/*     */     } 
/*     */     
/* 827 */     return this.m_DateGridRect.contains(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean setDateAt(int x, int y) {
/* 832 */     if (this.m_DateGridRect == null || this.m_DateGridRect.isEmpty() || !dateGridRectangleContainsPoint(x, y))
/* 833 */       return false; 
/* 834 */     int iCellWidth = this.m_DateGridRect.width / 7;
/* 835 */     int iCellHeight = this.m_DateGridRect.height / 6;
/* 836 */     int iDateStart = getDayOfWeekInternal(this.m_iYear, this.m_iMonth, 1);
/*     */ 
/*     */     
/* 839 */     x = this.m_FlipTransform.transposeX(x);
/* 840 */     int iDateOffset = (x - this.m_DateGridRect.x) / iCellWidth + (y - this.m_DateGridRect.y) / iCellHeight * 7 - iDateStart + 1;
/*     */     
/* 842 */     if (iDateOffset <= 0)
/* 843 */       return false; 
/* 844 */     int iMonthDays = getNumOfDaysInMonth(this.m_iMonth, this.m_iYear);
/* 845 */     if (iMonthDays < iDateOffset)
/* 846 */       return false; 
/* 847 */     setDay(iDateOffset);
/* 848 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void createTodayButton() {
/* 853 */     this.m_Today = new JButton();
/* 854 */     this.m_Today.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 858 */             JLbsCalendarPanel.this.setToday();
/* 859 */             JLbsCalendarPanel.this.fireDateSelected();
/*     */           }
/*     */         });
/* 862 */     UIHelperUtil.setCaption(this.m_Today, JLbsLocalizer.getStringDef("UNRP", -20099, 3, "&Today"));
/* 863 */     setLayout(new BorderLayout());
/* 864 */     JPanel btnPanel = new JPanel();
/* 865 */     btnPanel.setLayout(new FlowLayout(1, 0, 2));
/* 866 */     btnPanel.add(this.m_Today);
/* 867 */     add(btnPanel, "South");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processMouseEvent(MouseEvent e) {
/* 872 */     super.processMouseEvent(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInDialog() {
/* 877 */     return this.m_InDialog;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInDialog(boolean b) {
/* 882 */     this.m_InDialog = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowWeeks() {
/* 887 */     return this.m_ShowWeeks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShowWeeks(boolean b) {
/* 892 */     this.m_ShowWeeks = b;
/* 893 */     repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doKeyNavigation(KeyEvent e) {
/* 898 */     if (e.isConsumed()) {
/*     */       return;
/*     */     }
/* 901 */     switch (e.getKeyCode()) {
/*     */       
/*     */       case 27:
/* 904 */         this.m_CalListener.doCloseControl();
/* 905 */         e.consume();
/*     */         break;
/*     */       case 10:
/*     */       case 32:
/* 909 */         fireDateSelected();
/* 910 */         e.consume();
/*     */         break;
/*     */       case 40:
/* 913 */         incrementDays(7);
/* 914 */         e.consume();
/*     */         break;
/*     */       case 38:
/* 917 */         incrementDays(-7);
/* 918 */         e.consume();
/*     */         break;
/*     */       case 37:
/* 921 */         incrementDays(-1);
/* 922 */         e.consume();
/*     */         break;
/*     */       case 39:
/* 925 */         incrementDays(1);
/* 926 */         e.consume();
/*     */         break;
/*     */       case 34:
/* 929 */         if ((e.getModifiers() & 0x2) == 2) {
/* 930 */           setYear(getYear() + 1);
/*     */         } else {
/* 932 */           setMonth(getMonth() + 1);
/* 933 */         }  e.consume();
/*     */         break;
/*     */       case 33:
/* 936 */         if ((e.getModifiers() & 0x2) == 2) {
/* 937 */           setYear(getYear() - 1);
/*     */         } else {
/* 939 */           setMonth(getMonth() - 1);
/* 940 */         }  e.consume();
/*     */         break;
/*     */       case 84:
/* 943 */         if ((e.getModifiers() & 0x8) == 8) {
/*     */           
/* 945 */           setToday();
/* 946 */           fireDateSelected();
/* 947 */           e.consume();
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 955 */     super.updateUI();
/*     */ 
/*     */     
/* 958 */     setBackground(UIManager.getColor("Table.background"));
/*     */     
/* 960 */     this.m_HeaderTextColor = UIManager.getColor("TableHeader.foreground");
/* 961 */     this.m_TextColor = UIManager.getColor("Table.foreground");
/* 962 */     this.m_LineColor = UIManager.getColor("Table.gridColor");
/* 963 */     if (this.m_LineColor == null) {
/* 964 */       this.m_LineColor = Color.gray;
/*     */     }
/*     */   }
/*     */   
/*     */   public int getCalendarType() {
/* 969 */     return this.m_CalendarType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCalendarType(int calendarType) {
/* 974 */     this.m_CalendarType = calendarType;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasSearchFilter() {
/* 979 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\datedit\JLbsCalendarPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */