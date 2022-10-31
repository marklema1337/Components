/*      */ package com.lbs.yearlycalendar;
/*      */ 
/*      */ import com.lbs.controls.JLbsControlHelper;
/*      */ import com.lbs.globalization.ILbsCultureInfo;
/*      */ import com.lbs.util.JLbsConstants;
/*      */ import com.lbs.util.JLbsDateUtil;
/*      */ import java.awt.Color;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.GridLayout;
/*      */ import java.awt.Image;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.SystemColor;
/*      */ import java.awt.Toolkit;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.GregorianCalendar;
/*      */ import javax.swing.JDialog;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.ToolTipManager;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.border.LineBorder;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JLbsMonthCalendarControl
/*      */   extends JPanel
/*      */ {
/*   50 */   private Dimension m_CellSize = new Dimension(0, 0);
/*   51 */   private Dimension m_DayCellSize = new Dimension(0, 0);
/*   52 */   private int m_RowH = 0;
/*   53 */   private Graphics m_Graphics = null;
/*   54 */   private String[] m_ShortDayNames = new String[7];
/*      */   private Calendar m_Date;
/*   56 */   private int m_Month = 8;
/*   57 */   private int m_Year = 1976;
/*   58 */   private int m_MaxDays = 0;
/*   59 */   private int m_PrevMaxDays = 0;
/*   60 */   private int m_DayOfWeek = 1;
/*   61 */   private int m_WeekendDays = 96;
/*      */   private boolean m_DisplayMonthName = true;
/*      */   private boolean m_DisplayYear = true;
/*      */   private boolean m_DisplayGridLines = false;
/*      */   private boolean m_DisplayTrailingDays = false;
/*      */   private boolean m_DisplayLeadingDays = false;
/*      */   private boolean m_Initiated = false;
/*      */   private boolean m_Internal = false;
/*   69 */   private Calendar m_Today = new GregorianCalendar();
/*      */   
/*   71 */   private static final Color SPECIALDATECOLOR = Color.BLUE;
/*   72 */   private static final Color SPECIALWEEKENDCOLOR = Color.RED;
/*   73 */   private static final Color WEEKENDCOLOR = Color.RED;
/*   74 */   private static final Color MONTHCOLOR = Color.BLACK;
/*   75 */   private static final Color DAYNAMECOLOR = Color.BLUE;
/*      */   
/*   77 */   private JLbsCalendarSpecialDates m_SpecialDates = null;
/*      */   private JLbsCalendarCellRects m_CellRects;
/*   79 */   private Calendar m_LastDate = null;
/*      */   
/*   81 */   private int m_MaxRows = 8;
/*   82 */   private int m_UpdateCount = 0;
/*   83 */   private String m_Hint = "";
/*      */   
/*      */   private boolean m_DisplayNextMonthButton = true;
/*      */   private boolean m_DisplayPrevMonthButton = true;
/*   87 */   public static Image ms_NextMonthButton = getResourceImage("Next.png");
/*   88 */   public static Image ms_PrevMonthButton = getResourceImage("Previous.png");
/*   89 */   private Rectangle m_NextMonthButtonR = new Rectangle(0, 0, 0, 0);
/*      */   
/*   91 */   private Rectangle m_PrevMonthButtonR = new Rectangle(0, 0, 0, 0);
/*   92 */   private Point m_MouseDownPoint = new Point(0, 0);
/*      */   private boolean m_Selecting = false;
/*      */   private boolean m_MultipleSelection = true;
/*   95 */   private ArrayList m_ListenerList = null;
/*      */   
/*      */   private boolean m_Active = false;
/*      */   
/*      */   protected ILbsCultureInfo m_CultureInfo;
/*      */   
/*      */   private static Font getHeaderFont() {
/*  102 */     return new Font(JLbsConstants.APP_FONT, 1, JLbsConstants.APP_FONT_SIZE - 1);
/*      */   }
/*      */ 
/*      */   
/*      */   private static Font getSpecialDateFont() {
/*  107 */     return new Font(JLbsConstants.APP_FONT, 1, JLbsConstants.APP_FONT_SIZE - 2);
/*      */   }
/*      */ 
/*      */   
/*      */   private static Font getWeekFont() {
/*  112 */     return new Font(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE - 2);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public JLbsMonthCalendarControl() {
/*  118 */     this.m_SpecialDates = new JLbsCalendarSpecialDates(this);
/*  119 */     setBackground(Color.white);
/*  120 */     this.m_CellRects = new JLbsCalendarCellRects(this);
/*  121 */     this.m_ListenerList = new ArrayList();
/*  122 */     this.m_Internal = true;
/*  123 */     setDate(new GregorianCalendar());
/*  124 */     this.m_Internal = false;
/*  125 */     enableEvents(131128L);
/*  126 */     addMouseListener(new JLbsMonthCalendarMouseAdapter());
/*  127 */     addMouseMotionListener(new JLbsMonthCalendarMouseAdapter());
/*  128 */     addKeyListener(new JLbsMonthCalendarKeyAdapter());
/*  129 */     addFocusListener(new JLbsMonthCalendarMouseAdapter());
/*  130 */     initializeShortDayNames();
/*  131 */     setDoubleBuffered(true);
/*  132 */     ToolTipManager.sharedInstance().registerComponent(this);
/*  133 */     setPreferredSize(new Dimension(196, 280));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setInternalFlag(boolean value) {
/*  138 */     this.m_Internal = value;
/*      */   }
/*      */   
/*      */   public int dayOfWeekToCalendarDay(int ADay) {
/*  142 */     switch (ADay) {
/*      */       
/*      */       case 2:
/*  145 */         return 1;
/*      */       case 3:
/*  147 */         return 2;
/*      */       case 4:
/*  149 */         return 4;
/*      */       case 5:
/*  151 */         return 8;
/*      */       case 6:
/*  153 */         return 16;
/*      */       case 7:
/*  155 */         return 32;
/*      */       case 1:
/*  157 */         return 64;
/*      */     } 
/*  159 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int calendarDaysToDayOfWeek(int ADay) {
/*  166 */     switch (ADay) {
/*      */       
/*      */       case 1:
/*  169 */         return 2;
/*      */       case 2:
/*  171 */         return 3;
/*      */       case 4:
/*  173 */         return 4;
/*      */       case 8:
/*  175 */         return 5;
/*      */       case 16:
/*  177 */         return 6;
/*      */       case 32:
/*  179 */         return 7;
/*      */       case 64:
/*  181 */         return 1;
/*      */     } 
/*  183 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void initializeShortDayNames() {
/*  189 */     for (int iDay = 0; iDay < this.m_ShortDayNames.length; iDay++)
/*  190 */       this.m_ShortDayNames[iDay] = (this.m_CultureInfo != null) ? this.m_CultureInfo.getDayShortName(iDay + 1) : JLbsDateUtil.dayNames[iDay]; 
/*      */   }
/*      */   
/*      */   public void setCultureInfo(ILbsCultureInfo cultureInfo) {
/*  194 */     if (this.m_CultureInfo != cultureInfo) {
/*      */       
/*  196 */       this.m_CultureInfo = cultureInfo;
/*  197 */       repaint();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void navigateDays(int value, boolean addToSelection) {
/*  202 */     int aDay = this.m_LastDate.get(5) + value;
/*  203 */     if (aDay > 0 && aDay <= this.m_MaxDays)
/*      */     {
/*  205 */       if (!this.m_MultipleSelection) {
/*      */         
/*  207 */         Calendar currentDate = JLbsCalendarGlobal.incrementDays(value, this.m_Date);
/*  208 */         setDate(currentDate);
/*      */       }
/*      */       else {
/*      */         
/*  212 */         JLbsCalendarCellRect aCell = getCellRects().getCellRect(this.m_LastDate);
/*  213 */         if (aCell != null)
/*  214 */           aCell.repaintCellRect(); 
/*  215 */         if (addToSelection)
/*  216 */           selectDate(this.m_LastDate); 
/*  217 */         this.m_LastDate = JLbsCalendarGlobal.incrementDays(value, this.m_LastDate);
/*  218 */         if (addToSelection)
/*  219 */           selectDate(this.m_LastDate); 
/*  220 */         aCell = getCellRects().getCellRect(this.m_LastDate);
/*  221 */         if (aCell != null) {
/*  222 */           aCell.repaintCellRect();
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void jumpToPrevYear() {
/*  230 */     setDate(new GregorianCalendar(this.m_Year - 1, this.m_Month, 1));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void jumpToNextYear() {
/*  236 */     setDate(new GregorianCalendar(this.m_Year + 1, this.m_Month, 1));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void jumpToNextMonth() {
/*  242 */     if (this.m_Month < 12) {
/*  243 */       setDate(new GregorianCalendar(this.m_Year, this.m_Month + 1, 1));
/*      */     } else {
/*  245 */       setDate(new GregorianCalendar(this.m_Year + 1, 1, 1));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void jumpToPrevMonth() {
/*  250 */     if (this.m_Month > 0) {
/*  251 */       setDate(new GregorianCalendar(this.m_Year, this.m_Month - 1, 1));
/*      */     } else {
/*  253 */       setDate(new GregorianCalendar(this.m_Year - 1, 11, 1));
/*      */     } 
/*      */   }
/*      */   public void addCalendarListener(ILbsCalendarEventListener listener) {
/*  257 */     if (this.m_ListenerList.indexOf(listener) == -1)
/*  258 */       this.m_ListenerList.add(listener); 
/*      */   }
/*      */   
/*      */   public void removeCalendarListener(ILbsCalendarEventListener listener) {
/*  262 */     int anIdx = this.m_ListenerList.indexOf(listener);
/*  263 */     if (anIdx != -1) {
/*  264 */       this.m_ListenerList.remove(anIdx);
/*      */     }
/*      */   }
/*      */   
/*      */   public void addSpecialDate(Calendar aDate, String aDesc) {
/*  269 */     if (this.m_SpecialDates.addSpecialDate(aDate, aDesc) != null)
/*  270 */       RefreshCalendar(); 
/*      */   }
/*      */   
/*      */   public void editSpecialDate(Calendar aDate, String aDesc) {
/*  274 */     if (this.m_SpecialDates.editSpecialDate(aDate, aDesc) != null)
/*  275 */       RefreshCalendar(); 
/*      */   }
/*      */   
/*      */   public void addSpecialDateRange(JLbsCalenderSpecialDate[] aSpecialDateList) {
/*  279 */     for (int anIdx = 0; anIdx < aSpecialDateList.length; anIdx++) {
/*      */       
/*  281 */       if (this.m_SpecialDates.getIndex(aSpecialDateList[anIdx].getDate()) == -1)
/*  282 */         this.m_SpecialDates.addSpecialDate(aSpecialDateList[anIdx]); 
/*      */     } 
/*  284 */     RefreshCalendar();
/*      */   }
/*      */   
/*      */   public void editSpecialDateRange(JLbsCalenderSpecialDate[] aSpecialDateList) {
/*  288 */     for (int anIdx = 0; anIdx < aSpecialDateList.length; anIdx++) {
/*      */       
/*  290 */       if (this.m_SpecialDates.getIndex(aSpecialDateList[anIdx].getDate()) == -1)
/*  291 */         this.m_SpecialDates.editSpecialDate(aSpecialDateList[anIdx]); 
/*      */     } 
/*  293 */     RefreshCalendar();
/*      */   }
/*      */ 
/*      */   
/*      */   public void addSpecialDateInterval(Calendar aStartDate, Calendar anEndDate, String aDescription) {
/*  298 */     Calendar aDate = aStartDate;
/*  299 */     while (JLbsDateUtil.dateCompare(aDate, anEndDate) == -1) {
/*      */       
/*  301 */       this.m_SpecialDates.addSpecialDate(aDate, aDescription);
/*  302 */       aDate = JLbsCalendarGlobal.incrementDays(1, aDate);
/*      */     } 
/*  304 */     RefreshCalendar();
/*      */   }
/*      */   
/*      */   public void editSpecialDateInterval(Calendar aStartDate, Calendar anEndDate, String aDescription) {
/*  308 */     Calendar aDate = aStartDate;
/*  309 */     while (JLbsDateUtil.dateCompare(aDate, anEndDate) == -1) {
/*      */       
/*  311 */       this.m_SpecialDates.editSpecialDate(aDate, aDescription);
/*  312 */       aDate = JLbsCalendarGlobal.incrementDays(1, aDate);
/*      */     } 
/*  314 */     RefreshCalendar();
/*      */   }
/*      */   
/*      */   public void addSpecialDateInterval(Calendar aStartDate, int aCount, String aDescription) {
/*  318 */     Calendar aDate = aStartDate;
/*  319 */     for (int anIdx = 0; anIdx < aCount; anIdx++) {
/*      */       
/*  321 */       this.m_SpecialDates.addSpecialDate(aDate, aDescription);
/*  322 */       aDate = JLbsCalendarGlobal.incrementDays(1, aDate);
/*      */     } 
/*  324 */     RefreshCalendar();
/*      */   }
/*      */   
/*      */   public void editSpecialDateInterval(Calendar aStartDate, int aCount, String aDescription) {
/*  328 */     Calendar aDate = aStartDate;
/*  329 */     for (int anIdx = 0; anIdx < aCount; anIdx++) {
/*      */       
/*  331 */       this.m_SpecialDates.editSpecialDate(aDate, aDescription);
/*  332 */       aDate = JLbsCalendarGlobal.incrementDays(1, aDate);
/*      */     } 
/*  334 */     RefreshCalendar();
/*      */   }
/*      */   
/*      */   public void removeSpecialDate(Calendar aDate) {
/*  338 */     if (this.m_SpecialDates.removeSpecialDate(aDate))
/*  339 */       RefreshCalendar(); 
/*      */   }
/*      */   
/*      */   public void removeSpecialDateRange(JLbsCalenderSpecialDate[] aSpecialDateList) {
/*  343 */     for (int anIdx = 0; anIdx < aSpecialDateList.length; anIdx++)
/*  344 */       this.m_SpecialDates.removeSpecialDate(aSpecialDateList[anIdx].getDate()); 
/*  345 */     RefreshCalendar();
/*      */   }
/*      */   
/*      */   public void removeSpecialDateInterval(Calendar aStartDate, Calendar anEndDate) {
/*  349 */     Calendar aDate = aStartDate;
/*  350 */     while (JLbsDateUtil.dateCompare(aDate, anEndDate) == -1) {
/*      */       
/*  352 */       this.m_SpecialDates.removeSpecialDate(aDate);
/*  353 */       aDate = JLbsCalendarGlobal.incrementDays(1, aDate);
/*      */     } 
/*  355 */     RefreshCalendar();
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeSpecialDateInterval(Calendar aStartDate, int aCount) {
/*  360 */     Calendar aDate = aStartDate;
/*  361 */     for (int anIdx = 0; anIdx < aCount; anIdx++) {
/*      */       
/*  363 */       this.m_SpecialDates.removeSpecialDate(aDate);
/*  364 */       aDate = JLbsCalendarGlobal.incrementDays(1, aDate);
/*      */     } 
/*  366 */     RefreshCalendar();
/*      */   }
/*      */   
/*      */   public void removeAllSpecialDates() {
/*  370 */     this.m_SpecialDates.clear();
/*  371 */     RefreshCalendar();
/*      */   }
/*      */   
/*      */   public void removeAllSelections() {
/*  375 */     this.m_CellRects.clearSelection();
/*  376 */     RefreshCalendar();
/*      */   }
/*      */   
/*      */   public void BeginUpdate() {
/*  380 */     this.m_UpdateCount++;
/*      */   }
/*      */   
/*      */   public void EndUpdate() {
/*  384 */     if (this.m_UpdateCount > 0)
/*  385 */       this.m_UpdateCount--; 
/*  386 */     if (this.m_UpdateCount == 0) {
/*  387 */       invalidate();
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean IsOptionSet(int aDay) {
/*  392 */     return ((this.m_WeekendDays & dayOfWeekToCalendarDay(aDay)) != 0);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void fireSelectionChanged(JLbsSelectionList lstSelection) {
/*  397 */     int anIdx = 0;
/*  398 */     ILbsCalendarEventListener aListener = null;
/*  399 */     while (anIdx < this.m_ListenerList.size()) {
/*      */       
/*  401 */       aListener = this.m_ListenerList.get(anIdx);
/*  402 */       if (aListener != null)
/*  403 */         aListener.onSelectionChanged(this, lstSelection); 
/*  404 */       anIdx++;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fireSelectionCleared() {
/*  411 */     int anIdx = 0;
/*  412 */     ILbsCalendarEventListener aListener = null;
/*  413 */     while (anIdx < this.m_ListenerList.size()) {
/*      */       
/*  415 */       aListener = this.m_ListenerList.get(anIdx);
/*  416 */       if (aListener != null)
/*  417 */         aListener.onSelectionCleared(this); 
/*  418 */       anIdx++;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void fireDateChangeEvent(Object sender, Calendar oldDate, Calendar newDate) {
/*  423 */     int anIdx = 0;
/*  424 */     ILbsCalendarEventListener aListener = null;
/*  425 */     while (anIdx < this.m_ListenerList.size()) {
/*      */       
/*  427 */       aListener = this.m_ListenerList.get(anIdx);
/*  428 */       if (aListener != null)
/*  429 */         aListener.onDateChanged(this, oldDate, newDate); 
/*  430 */       anIdx++;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setMouseDownPoint(Point aPoint) {
/*  436 */     this.m_MouseDownPoint = aPoint;
/*      */   }
/*      */   
/*      */   protected Point getMouseDownPoint() {
/*  440 */     return this.m_MouseDownPoint;
/*      */   }
/*      */   
/*      */   protected void setSelecting(boolean value) {
/*  444 */     if (this.m_MultipleSelection)
/*  445 */       this.m_Selecting = value; 
/*      */   }
/*      */   
/*      */   protected boolean getSelecting() {
/*  449 */     if (!this.m_MultipleSelection)
/*  450 */       return false; 
/*  451 */     return this.m_Selecting;
/*      */   }
/*      */   
/*      */   protected void setMultipleSelection(boolean value) {
/*  455 */     this.m_MultipleSelection = value;
/*      */   }
/*      */   
/*      */   protected boolean getMultipleSelection() {
/*  459 */     return this.m_MultipleSelection;
/*      */   }
/*      */   
/*      */   private static Image getResourceImage(String resName) {
/*  463 */     URL url = JLbsMonthCalendarControl.class.getResource(resName);
/*  464 */     if (url == null) {
/*  465 */       return null;
/*      */     }
/*  467 */     Image image = Toolkit.getDefaultToolkit().createImage(url);
/*      */     
/*  469 */     image.getWidth(null);
/*  470 */     image.getHeight(null);
/*      */     
/*  472 */     return image;
/*      */   }
/*      */ 
/*      */   
/*      */   private Dimension GetMaxDaySize(Font aFont) {
/*  477 */     int wMax = 0;
/*  478 */     int hMax = 0;
/*  479 */     int w = 0;
/*  480 */     int h = 0;
/*  481 */     String aName = "";
/*  482 */     FontMetrics aMetrics = this.m_Graphics.getFontMetrics(aFont);
/*  483 */     for (int iDay = 0; iDay < this.m_ShortDayNames.length; iDay++) {
/*      */       
/*  485 */       aName = (this.m_CultureInfo != null) ? this.m_CultureInfo.getDayShortName(iDay + 1) : JLbsDateUtil.dayNames[iDay];
/*  486 */       this.m_ShortDayNames[iDay] = aName;
/*  487 */       w = aMetrics.stringWidth(aName) + 8;
/*  488 */       h = aMetrics.getHeight() + 8;
/*  489 */       if (w > wMax)
/*  490 */         wMax = w; 
/*  491 */       if (h > hMax)
/*  492 */         hMax = h; 
/*      */     } 
/*  494 */     return new Dimension(w, h);
/*      */   }
/*      */   
/*      */   private Dimension GetMaxNumSize(Font aFont) {
/*  498 */     int wMax = 0;
/*  499 */     int hMax = 0;
/*  500 */     int w = 0;
/*  501 */     int h = 0;
/*  502 */     FontMetrics aMetrics = this.m_Graphics.getFontMetrics(aFont);
/*  503 */     for (int AnIndex = 1; AnIndex < 32; AnIndex++) {
/*      */       
/*  505 */       w = aMetrics.stringWidth(Integer.toString(AnIndex)) + 4;
/*  506 */       h = aMetrics.getHeight() + 4;
/*  507 */       if (w > wMax)
/*  508 */         wMax = w; 
/*  509 */       if (h > hMax)
/*  510 */         hMax = h; 
/*      */     } 
/*  512 */     return new Dimension(w, h);
/*      */   }
/*      */ 
/*      */   
/*      */   private void CheckCellSize() {
/*  517 */     if (this.m_CellSize.getWidth() == 0.0D || this.m_CellSize.getHeight() == 0.0D) {
/*      */       
/*  519 */       Dimension aDefaultSize = GetMaxDaySize(getFont());
/*  520 */       Dimension aDayNamesFontSize = GetMaxDaySize(getHeaderFont());
/*      */       
/*  522 */       this.m_DayCellSize = new Dimension((int)Math.max(aDayNamesFontSize.getWidth(), aDefaultSize.getWidth()), (int)Math.max(aDayNamesFontSize.getHeight(), aDefaultSize.getHeight()));
/*  523 */       aDefaultSize = GetMaxNumSize(getFont());
/*  524 */       this.m_RowH = (int)Math.max(aDefaultSize.getHeight(), this.m_DayCellSize.getHeight());
/*  525 */       this.m_CellSize = new Dimension((int)aDefaultSize.getWidth(), this.m_RowH);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void LocateButtons() {
/*  530 */     if (ms_NextMonthButton != null) {
/*      */       
/*  532 */       Rectangle aRect = getMonthNameRect();
/*  533 */       int iLeftX = (int)(aRect.getX() + aRect.getWidth() - ms_NextMonthButton.getWidth(null) - 4.0D);
/*  534 */       int iTopY = (int)(aRect.getHeight() - ms_NextMonthButton.getHeight(null)) / 2;
/*  535 */       this.m_NextMonthButtonR = new Rectangle(iLeftX, iTopY, ms_NextMonthButton.getWidth(null), ms_NextMonthButton.getHeight(null));
/*  536 */       this.m_PrevMonthButtonR = new Rectangle(4, iTopY, ms_PrevMonthButton.getWidth(null), ms_PrevMonthButton.getHeight(null));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private Dimension GetTextSize(String aText, Font aFont) {
/*  542 */     FontMetrics aMetric = this.m_Graphics.getFontMetrics(aFont);
/*  543 */     int w = aMetric.stringWidth(aText);
/*  544 */     int h = aMetric.getHeight();
/*  545 */     return new Dimension(w, h);
/*      */   }
/*      */   
/*      */   private int GetTextHeight(Font aFont) {
/*  549 */     return this.m_Graphics.getFontMetrics(aFont).getHeight();
/*      */   }
/*      */   
/*      */   private int GetTextWidth(String aText, Font aFont) {
/*  553 */     FontMetrics aMetric = this.m_Graphics.getFontMetrics(aFont);
/*  554 */     return aMetric.stringWidth(aText);
/*      */   }
/*      */ 
/*      */   
/*      */   private void InitializeCalendarControl() {
/*  559 */     this.m_Graphics = getGraphics();
/*  560 */     if (this.m_Graphics != null && !this.m_Initiated) {
/*      */       
/*  562 */       this.m_Initiated = true;
/*  563 */       CheckCellSize();
/*  564 */       CalculateCellRects();
/*  565 */       requestFocus();
/*      */     } 
/*      */   }
/*      */   
/*      */   private Rectangle getCellRectF(int ACol, int ARow) {
/*  570 */     double x = (ACol == 0) ? 0.0D : (this.m_CellSize.getWidth() * (ACol - 1) + this.m_DayCellSize.getWidth() + ((ACol > 0 && getDisplayGridLines()) ? (ACol - 1) : false));
/*  571 */     double y = (this.m_RowH * ARow + ((ARow > 0 && getDisplayGridLines()) ? (ARow - 1) : 0));
/*  572 */     double h = (this.m_RowH + (getDisplayGridLines() ? 1 : 0));
/*  573 */     double w = ((ACol == 0) ? this.m_DayCellSize.getWidth() : this.m_CellSize.getWidth()) + (getDisplayGridLines() ? true : false);
/*  574 */     return new Rectangle((int)x, (int)y, (int)w, (int)h);
/*      */   }
/*      */   
/*      */   private Rectangle getMonthNameRect() {
/*  578 */     int iWidth = (getCellRect(0, 0)).width + (getCellRect(1, 1)).width * 6;
/*  579 */     return new Rectangle(0, 0, iWidth, (int)this.m_CellSize.getHeight());
/*      */   }
/*      */   
/*      */   private int FindStartCell() {
/*  583 */     return this.m_DayOfWeek;
/*      */   }
/*      */   
/*      */   private boolean isWeekend(int ADayOfWeek) {
/*  587 */     if (ADayOfWeek > 7)
/*  588 */       return false; 
/*  589 */     return IsOptionSet(ADayOfWeek);
/*      */   }
/*      */   
/*      */   private boolean isSpecialDate(Calendar ADate) {
/*  593 */     return (this.m_SpecialDates.getIndex(ADate) != -1);
/*      */   }
/*      */   
/*      */   private void PaintGridLines(Graphics AGraphics) {
/*  597 */     if (getDisplayGridLines()) {
/*      */       
/*  599 */       double x = this.m_DayCellSize.getWidth();
/*  600 */       double y = (getDisplayMonthName() ? this.m_RowH : false);
/*  601 */       Color bkcol = UIManager.getColor("Button.background");
/*  602 */       AGraphics.setColor((bkcol != null) ? bkcol : SystemColor.control);
/*      */       
/*  604 */       int iHeight = ((int)this.m_CellSize.getHeight() + 1) * 7;
/*  605 */       for (int AnIndex = 0; AnIndex <= 6; AnIndex++) {
/*      */         
/*  607 */         AGraphics.drawLine((int)x, (int)y, (int)x, (int)(y + iHeight));
/*  608 */         x += this.m_CellSize.getWidth() + 1.0D;
/*      */       } 
/*  610 */       int iWidth = (int)(this.m_DayCellSize.getWidth() + (this.m_CellSize.getWidth() + 1.0D) * 6.0D);
/*  611 */       for (int i = 0; i < this.m_MaxRows; i++) {
/*      */         
/*  613 */         AGraphics.drawLine(0, (int)y, iWidth, (int)y);
/*  614 */         y += (this.m_RowH + 1);
/*      */       } 
/*  616 */       LineBorder border = new LineBorder(SystemColor.BLUE, 1);
/*  617 */       border.paintBorder(null, AGraphics, 0, 0, iWidth + 2, iHeight + (getDisplayMonthName() ? this.m_RowH : 0) + 2);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void PaintDayNames(Graphics AGraphics) {
/*  622 */     int ARow = 0;
/*  623 */     String ADayName = "";
/*  624 */     AGraphics.setFont(getHeaderFont());
/*      */     
/*  626 */     for (int anIdx = 0; anIdx < this.m_ShortDayNames.length; anIdx++) {
/*      */       
/*  628 */       ADayName = this.m_ShortDayNames[anIdx];
/*  629 */       if (isWeekend(anIdx + 1)) {
/*  630 */         AGraphics.setColor(WEEKENDCOLOR);
/*      */       } else {
/*  632 */         AGraphics.setColor(DAYNAMECOLOR);
/*      */       } 
/*  634 */       JLbsControlHelper.drawStringVCentered(null, AGraphics, getCellRect(0, ARow), ADayName, 0);
/*  635 */       ARow++;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void fillDateOfCellRects() {
/*  640 */     int AStartRow = FindStartCell();
/*  641 */     if (AStartRow != -1) {
/*      */       
/*  643 */       int ADayIndex = 7 + AStartRow;
/*  644 */       CalculateCellRects();
/*  645 */       for (int AnIndex = 1; AnIndex <= this.m_MaxDays; AnIndex++) {
/*      */         
/*  647 */         this.m_CellRects.getCellRect(ADayIndex).setDate(new GregorianCalendar(this.m_Year, this.m_Month, AnIndex));
/*  648 */         ADayIndex++;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void PaintDayNumbers(Graphics AGraphics) {
/*  655 */     int AStartRow = FindStartCell();
/*  656 */     if (AStartRow != -1) {
/*      */       
/*  658 */       int ADayIndex = 7 + AStartRow;
/*  659 */       boolean ASpecialDate = false;
/*      */ 
/*      */       
/*  662 */       Color ActiveColor = null;
/*  663 */       Font ActiveFont = null;
/*  664 */       JLbsCalendarCellRects ACellRects = getCellRects(); int AnIndex;
/*  665 */       for (AnIndex = 1; AnIndex <= this.m_MaxDays; AnIndex++) {
/*      */         
/*  667 */         JLbsCalendarCellRect ACellRect = ACellRects.getCellRect(ADayIndex);
/*  668 */         ACellRect.setDate(new GregorianCalendar(this.m_Year, this.m_Month, AnIndex));
/*  669 */         ASpecialDate = isSpecialDate(ACellRect.getDate());
/*  670 */         if (isWeekend(ACellRect.getDate().get(7))) {
/*      */           
/*  672 */           if (ASpecialDate)
/*      */           {
/*  674 */             ActiveColor = SPECIALWEEKENDCOLOR;
/*  675 */             ActiveFont = getSpecialDateFont();
/*      */           }
/*      */           else
/*      */           {
/*  679 */             ActiveColor = WEEKENDCOLOR;
/*  680 */             ActiveFont = getWeekFont();
/*      */           
/*      */           }
/*      */         
/*      */         }
/*  685 */         else if (ASpecialDate) {
/*      */           
/*  687 */           ActiveColor = SPECIALDATECOLOR;
/*  688 */           ActiveFont = getSpecialDateFont();
/*      */         }
/*      */         else {
/*      */           
/*  692 */           ActiveColor = Color.BLACK;
/*  693 */           ActiveFont = getWeekFont();
/*      */         } 
/*      */         
/*  696 */         if (ACellRect.getSelected()) {
/*      */           
/*  698 */           Rectangle rect = ACellRect.getRect();
/*  699 */           AGraphics.setColor(new Color(244, 223, 140));
/*  700 */           AGraphics.fillRect(rect.x + 1, rect.y + 1, rect.width - 1, rect.height - 1);
/*  701 */           ActiveColor = SystemColor.infoText;
/*      */         } 
/*      */         
/*  704 */         if (ASpecialDate) {
/*      */           
/*  706 */           Rectangle rect = ACellRect.getRect();
/*  707 */           AGraphics.setColor(new Color(220, 242, 198));
/*  708 */           AGraphics.fillRect(rect.x + 1, rect.y + 1, rect.width - 1, rect.height - 1);
/*      */         } 
/*      */         
/*  711 */         if (ACellRect.getDate() != null && JLbsDateUtil.dateCompare(ACellRect.getDate(), this.m_Today) == 0) {
/*      */           
/*  713 */           Rectangle rect = ACellRect.getRect();
/*  714 */           AGraphics.setColor(Color.RED);
/*  715 */           AGraphics.drawRect(rect.x + 1, rect.y + 1, rect.width - 1, rect.height - 1);
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  720 */         else if (this.m_LastDate != null && JLbsDateUtil.dateCompare(ACellRect.getDate(), this.m_LastDate) == 0 && this.m_Active) {
/*      */           
/*  722 */           Rectangle rect = ACellRect.getRect();
/*  723 */           JLbsControlHelper.drawFocusRect(AGraphics, rect, Color.DARK_GRAY);
/*      */         } 
/*      */         
/*  726 */         AGraphics.setColor(ActiveColor);
/*  727 */         AGraphics.setFont(ActiveFont);
/*  728 */         JLbsControlHelper.drawStringVCentered(null, AGraphics, ACellRect.getRect(), Integer.toString(AnIndex), 0);
/*  729 */         ADayIndex++;
/*      */       } 
/*  731 */       if (getDisplayTrailingDays() && ADayIndex < this.m_CellRects.size()) {
/*      */         
/*  733 */         AnIndex = 1;
/*  734 */         AGraphics.setColor(Color.GRAY);
/*  735 */         AGraphics.setFont(getWeekFont());
/*  736 */         ACellRects = getCellRects();
/*      */         
/*  738 */         while (ADayIndex < this.m_CellRects.size()) {
/*      */ 
/*      */           
/*  741 */           JLbsControlHelper.drawStringVCentered(null, AGraphics, ACellRects.getCellRect(ADayIndex).getRect(), Integer.toString(AnIndex), 0);
/*  742 */           AnIndex++;
/*  743 */           ADayIndex++;
/*      */         } 
/*      */       } 
/*  746 */       if (getDisplayLeadingDays() && AStartRow > 0) {
/*      */         
/*  748 */         AGraphics.setColor(Color.GRAY);
/*  749 */         AGraphics.setFont(getWeekFont());
/*  750 */         int ADay = this.m_PrevMaxDays;
/*  751 */         for (int i = AStartRow - 1; i >= 0; i--) {
/*      */           
/*  753 */           JLbsControlHelper.drawStringVCentered(null, AGraphics, getCellRect(1, i), Integer.toString(ADay), 0);
/*  754 */           ADay--;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void PaintMonthName(Graphics AGraphics) {
/*  762 */     if (getDisplayMonthName()) {
/*      */       
/*  764 */       LocateButtons();
/*  765 */       Rectangle ARect = getMonthNameRect();
/*  766 */       JLbsControlHelper.drawHeaderRectangle(AGraphics, ARect);
/*  767 */       if (getDisplayNextMonthButton())
/*  768 */         AGraphics.drawImage(ms_NextMonthButton, this.m_NextMonthButtonR.x, this.m_NextMonthButtonR.y, 11, 9, null); 
/*  769 */       if (getDisplayPrevMonthButton())
/*  770 */         AGraphics.drawImage(ms_PrevMonthButton, this.m_PrevMonthButtonR.x, this.m_PrevMonthButtonR.y, 11, 9, null); 
/*  771 */       StringBuilder ABuffer = new StringBuilder();
/*  772 */       ABuffer.append(JLbsDateUtil.monthNames[this.m_Month]);
/*  773 */       ABuffer.append("-");
/*  774 */       ABuffer.append(getDisplayYear() ? Integer.toString(this.m_Year) : "");
/*  775 */       AGraphics.setColor(MONTHCOLOR);
/*  776 */       AGraphics.setFont(getHeaderFont());
/*  777 */       JLbsControlHelper.drawStringVCentered(null, AGraphics, ARect, ABuffer.toString(), 0);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void CalculateCellRects() {
/*  782 */     this.m_CellRects.clear();
/*  783 */     int AStartRow = getDisplayMonthName() ? 1 : 0;
/*  784 */     for (int ACol = 0; ACol < 7; ACol++) {
/*  785 */       for (int ARow = AStartRow; ARow < this.m_MaxRows; ARow++)
/*  786 */         this.m_CellRects.add(getCellRectF(ACol, ARow)); 
/*      */     } 
/*      */   }
/*      */   private Rectangle getCellRect(int ACol, int ARow) {
/*  790 */     JLbsCalendarCellRect ACellRect = this.m_CellRects.getCellRect(ACol * 7 + ARow);
/*  791 */     return ACellRect.getRect();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void RefreshCalendar() {
/*  800 */     repaint();
/*      */   }
/*      */   
/*      */   public Rectangle getNextMonthButtonR() {
/*  804 */     return this.m_NextMonthButtonR;
/*      */   }
/*      */   
/*      */   public Rectangle getPrevMonthButtonR() {
/*  808 */     return this.m_PrevMonthButtonR;
/*      */   }
/*      */   
/*      */   public String[] getShortDayNames() {
/*  812 */     return this.m_ShortDayNames;
/*      */   }
/*      */ 
/*      */   
/*      */   public Calendar getToday() {
/*  817 */     return this.m_Today;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMonth() {
/*  831 */     return this.m_Month;
/*      */   }
/*      */   
/*      */   public int getYear() {
/*  835 */     return this.m_Year;
/*      */   }
/*      */   
/*      */   public Calendar getDate() {
/*  839 */     return this.m_Date;
/*      */   }
/*      */   
/*      */   public void setDate(Calendar value, boolean selectDate) {
/*  843 */     boolean internalFlagOld = this.m_Internal;
/*  844 */     this.m_Internal = !selectDate;
/*  845 */     setDate(value);
/*  846 */     this.m_Internal = internalFlagOld;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDate(Calendar value) {
/*  851 */     if (JLbsCalendarGlobal.dateCompare(this.m_Date, value) != 0) {
/*      */       
/*  853 */       if (!this.m_Internal)
/*  854 */         fireDateChangeEvent(this, this.m_Date, value); 
/*  855 */       this.m_Date = value;
/*  856 */       this.m_Month = this.m_Date.get(2);
/*  857 */       this.m_Year = this.m_Date.get(1);
/*  858 */       this.m_SpecialDates.setYear(this.m_Year);
/*  859 */       this.m_MaxDays = JLbsDateUtil.getNumDays(this.m_Month, this.m_Year);
/*  860 */       this.m_PrevMaxDays = (this.m_Month == 1) ? 31 : JLbsDateUtil.getNumDays(this.m_Month - 1, this.m_Year);
/*  861 */       this.m_DayOfWeek = JLbsDateUtil.getDayOfWeek(this.m_Year, this.m_Month, 1);
/*  862 */       fillDateOfCellRects();
/*  863 */       this.m_CellRects.clearSelection();
/*  864 */       if (!this.m_Internal)
/*  865 */         selectDate(value); 
/*  866 */       RefreshCalendar();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void selectDate(Calendar aDate) {
/*  871 */     if (this.m_CellRects.getCellRect(aDate) != null) {
/*  872 */       this.m_CellRects.getCellRect(aDate).setSelected(true);
/*      */     }
/*      */   }
/*      */   
/*      */   public void revertDateSelection(Calendar aDate) {
/*  877 */     if (this.m_CellRects.getCellRect(aDate) != null) {
/*      */       
/*  879 */       boolean aSelected = this.m_CellRects.getCellRect(aDate).getSelected();
/*  880 */       this.m_CellRects.getCellRect(aDate).setSelected(!aSelected);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLastDate(Calendar value) {
/*  886 */     this.m_LastDate = value;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getDisplayMonthName() {
/*  891 */     return this.m_DisplayMonthName;
/*      */   }
/*      */   
/*      */   public void setDisplayMonthName(boolean value) {
/*  895 */     if (this.m_DisplayMonthName != value) {
/*      */       
/*  897 */       this.m_DisplayMonthName = value;
/*  898 */       this.m_MaxRows = value ? 8 : 7;
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getWeekendDays() {
/*  903 */     return this.m_WeekendDays;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setWeekendDays(int value) {
/*  908 */     if (this.m_WeekendDays != value) {
/*      */       
/*  910 */       this.m_WeekendDays = value;
/*  911 */       RefreshCalendar();
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean getDisplayYear() {
/*  916 */     return this.m_DisplayYear;
/*      */   }
/*      */   
/*      */   public void setDisplayYear(boolean value) {
/*  920 */     this.m_DisplayYear = value;
/*  921 */     if (getDisplayMonthName()) {
/*  922 */       invalidate();
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean getDisplayGridLines() {
/*  927 */     return this.m_DisplayGridLines;
/*      */   }
/*      */   
/*      */   public void setDisplayLines(boolean value) {
/*  931 */     if (this.m_DisplayGridLines != value)
/*      */     {
/*  933 */       this.m_DisplayGridLines = value;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getDisplayTrailingDays() {
/*  939 */     return this.m_DisplayTrailingDays;
/*      */   }
/*      */   
/*      */   public void setDisplayTrailingDays(boolean value) {
/*  943 */     if (this.m_DisplayTrailingDays != value) {
/*      */       
/*  945 */       this.m_DisplayTrailingDays = value;
/*  946 */       RefreshCalendar();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getDisplayLeadingDays() {
/*  952 */     return this.m_DisplayLeadingDays;
/*      */   }
/*      */   
/*      */   public void setDisplayLeadingDays(boolean value) {
/*  956 */     if (this.m_DisplayLeadingDays != value) {
/*      */       
/*  958 */       this.m_DisplayLeadingDays = value;
/*  959 */       RefreshCalendar();
/*      */     } 
/*      */   }
/*      */   
/*      */   public JLbsCalendarSpecialDates getSpecialDates() {
/*  964 */     return this.m_SpecialDates;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSpecialDates(JLbsCalendarSpecialDates value) {
/*  969 */     this.m_SpecialDates = value;
/*      */   }
/*      */   
/*      */   public JLbsCalendarCellRects getCellRects() {
/*  973 */     return this.m_CellRects;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getHint() {
/*  978 */     return this.m_Hint;
/*      */   }
/*      */   
/*      */   public void setHint(String value) {
/*  982 */     if (this.m_Hint != value) {
/*      */       
/*  984 */       this.m_Hint = value;
/*  985 */       setToolTipText(this.m_Hint);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getDisplayNextMonthButton() {
/*  991 */     return this.m_DisplayNextMonthButton;
/*      */   }
/*      */   
/*      */   public void setDisplayNextMonthButton(boolean value) {
/*  995 */     if (this.m_DisplayNextMonthButton != value) {
/*      */       
/*  997 */       this.m_DisplayNextMonthButton = value;
/*  998 */       if (this.m_DisplayMonthName)
/*  999 */         RefreshCalendar(); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean getDisplayPrevMonthButton() {
/* 1004 */     return this.m_DisplayPrevMonthButton;
/*      */   }
/*      */   
/*      */   public void setDisplayPrevMonthButton(boolean value) {
/* 1008 */     if (this.m_DisplayPrevMonthButton != value) {
/*      */       
/* 1010 */       this.m_DisplayPrevMonthButton = value;
/* 1011 */       if (this.m_DisplayMonthName) {
/* 1012 */         RefreshCalendar();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setActive(boolean value) {
/* 1018 */     if (this.m_Active != value) {
/*      */       
/* 1020 */       this.m_Active = value;
/* 1021 */       RefreshCalendar();
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean getActive() {
/* 1026 */     return this.m_Active;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Dimension getPreferredSize() {
/* 1033 */     int w = (int)(this.m_DayCellSize.getWidth() + 6.0D * this.m_CellSize.getWidth() + (getDisplayGridLines() ? 6 : false));
/* 1034 */     int h = (int)(this.m_MaxRows * this.m_CellSize.getHeight() + (getDisplayGridLines() ? (this.m_MaxRows - 1) : false));
/* 1035 */     if (w == 0)
/* 1036 */       w = 120; 
/* 1037 */     if (h == 0)
/* 1038 */       h = 230; 
/* 1039 */     return new Dimension(w, h);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void paintComponent(Graphics g) {
/* 1044 */     super.paintComponent(g);
/* 1045 */     if (this.m_UpdateCount == 0) {
/*      */       
/* 1047 */       InitializeCalendarControl();
/* 1048 */       PaintGridLines(g);
/* 1049 */       PaintDayNames(g);
/* 1050 */       PaintDayNumbers(g);
/* 1051 */       PaintMonthName(g);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void main(String[] args) {
/* 1057 */     JDialog dlg = new JDialog();
/* 1058 */     dlg.setDefaultCloseOperation(2);
/* 1059 */     dlg.setModal(true);
/*      */     
/* 1061 */     JPanel contPanel = new JPanel();
/*      */     
/* 1063 */     contPanel.setLayout(new GridLayout());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1068 */     JLbsMonthCalendarControl aCal = new JLbsMonthCalendarControl();
/*      */     
/* 1070 */     aCal.addSpecialDate(new GregorianCalendar(2005, 0, 4), "4 OCAK TATİL");
/* 1071 */     aCal.addSpecialDate(new GregorianCalendar(2005, 0, 14), "14 OCAK TATİL 14 OCAK TATİL 14 OCAK TATİL 14 OCAK TATİL 14 OCAK TATİL 14 OCAK TATİL 14 OCAK TATİL 14 OCAK TATİL");
/* 1072 */     aCal.setMultipleSelection(false);
/*      */     
/* 1074 */     contPanel.add(aCal);
/*      */     
/* 1076 */     dlg.setContentPane(contPanel);
/* 1077 */     dlg.setSize(400, 400);
/* 1078 */     dlg.setLocation(200, 200);
/* 1079 */     dlg.show();
/* 1080 */     System.exit(0);
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\yearlycalendar\JLbsMonthCalendarControl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */