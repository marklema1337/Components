/*     */ package com.lbs.controls.datedit;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsDateEdit;
/*     */ import com.lbs.control.interfaces.ILbsDateEditWithCalendar;
/*     */ import com.lbs.control.interfaces.ILbsExtraPaneControl;
/*     */ import com.lbs.control.interfaces.ILbsMaskedEdit;
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.ILbsLocalizable;
/*     */ import com.lbs.controls.JLbsComboEdit;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.controls.JLbsControlHelper;
/*     */ import com.lbs.controls.JLbsEventRecorderHelper;
/*     */ import com.lbs.controls.JLbsSwingUtilities;
/*     */ import com.lbs.controls.LbsComboEditImplementor;
/*     */ import com.lbs.controls.buttonpanel.ILbsButtonPanelChild;
/*     */ import com.lbs.controls.buttonpanel.JLbsButtonPanelSimpleButton;
/*     */ import com.lbs.controls.menu.JLbsPopupMenu;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.recording.interfaces.ILbsDateEditWithCalendarRecordingEvents;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.KeyboardFocusManager;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.TimeZone;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.Popup;
/*     */ import javax.swing.PopupFactory;
/*     */ import javax.swing.border.LineBorder;
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
/*     */ public class JLbsDateEditWithCalendar
/*     */   extends JLbsComboEdit
/*     */   implements ActionListener, ILbsLocalizable, ILbsDateEditWithCalendarRecordingEvents, ILbsDateEditWithCalendar
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final int RESID = -20099;
/*  68 */   public String m_PopupTitle = "Select A Date";
/*  69 */   public String m_ExtPopupItems = "Yesterday|Today|Tomorrow||First Day of This Week|Last Day of This Week||First Day of This Month|Last Day of This Month||First Day of Last Week|Last Day of LastWeek||First Day of Next Week|Last Day of Next Week||15th of This Month||7 Days After Today|15 Days After Today||7 Days Before Today|15 Days Before Today";
/*     */   
/*     */   private JPanel m_PopupPanel;
/*     */   
/*     */   private Popup m_Popup;
/*     */   
/*     */   private JPanel m_PopupDlg;
/*     */   private transient KeyListener m_PopupKeyListener;
/*     */   private transient FocusListener m_PopupFocusListener;
/*     */   
/*     */   public boolean localize() {
/*  80 */     this.m_PopupTitle = JLbsLocalizer.getStringDef("UNRP", -20099, 1, this.m_PopupTitle);
/*  81 */     this.m_ExtPopupItems = JLbsLocalizer.getStringDef("UNRP", -20099, 2, this.m_ExtPopupItems);
/*  82 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsDateEditWithCalendar() {
/*  87 */     this(new JLbsDateEdit());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsDateEditWithCalendar(JLbsDateEdit edit) {
/*  97 */     super(edit, 2);
/*  98 */     localize();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected LbsComboEditImplementor createImplementor(ILbsMaskedEdit edit) {
/* 104 */     return new LbsDateEditWithCalendarImplementor(this, (ILbsInternalDateEdit)edit);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addExtMenuButton() {
/* 109 */     if (this.m_Buttons == null || this.m_Buttons.size() < 2) {
/* 110 */       addButton((ILbsButtonPanelChild)new JLbsButtonPanelSimpleButton(24, 0), 100);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPopup(int index, int id) {
/* 117 */     if (!isEnabled())
/*     */       return; 
/* 119 */     super.doPopup(index, id);
/* 120 */     if (index == 0 || id == 0) {
/*     */       
/* 122 */       Container root = JLbsControlHelper.getRootContainer((Component)this);
/* 123 */       if (root instanceof JDialog && ((JDialog)root).isModal()) {
/* 124 */         popupCalendar(true);
/*     */       } else {
/* 126 */         popupCalendar(false);
/*     */       } 
/* 128 */     } else if (id == 100) {
/* 129 */       popupExtensionMenu();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected JLbsCalendarPanel createCalendarPanel() {
/* 134 */     JLbsCalendarPanel calendar = new JLbsCalendarPanel();
/* 135 */     calendar.setCultureInfo(((JLbsDateEdit)this.m_Edit).getCultureInfo());
/* 136 */     Date date = ((JLbsDateEdit)this.m_Edit).getDate();
/* 137 */     if (date == null) {
/* 138 */       calendar.setToday();
/*     */     } else {
/* 140 */       calendar.setDate(date);
/* 141 */     }  return calendar;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void popupCalendar(boolean modal) {
/* 147 */     Rectangle rectScreen = JLbsControlHelper.getRootCoordinates((Component)this);
/* 148 */     JLbsPopupMenu.setDefaultLightWeightPopupEnabled(true);
/* 149 */     Dialog owner = null;
/* 150 */     if (modal)
/* 151 */       owner = (Dialog)JLbsControlHelper.getRootContainer((Component)this); 
/* 152 */     JDialog dialog = new JDialog(owner);
/* 153 */     dialog.setTitle(this.m_PopupTitle);
/* 154 */     dialog.setResizable(false);
/* 155 */     dialog.setLocation(rectScreen.x - 1, rectScreen.y + rectScreen.height);
/* 156 */     JLbsCalendarPanel calendar = new JLbsCalendarPanel();
/* 157 */     calendar.setShowWeeks(isShowWeeks());
/* 158 */     ILbsCultureInfo cultInfo = ((JLbsDateEdit)this.m_Edit).getCultureInfo();
/* 159 */     dialog.setSize(calendar.getPreferredSize());
/* 160 */     calendar.setCultureInfo(cultInfo);
/* 161 */     calendar.applyComponentOrientation(getComponentOrientation());
/* 162 */     calendar.addKeyListener(new JLbsDateEditWithCalendarPanelKeyAdapter(dialog));
/* 163 */     calendar.setCalendarListener(new JLbsDateEditWithCalendarPanelListener(dialog, (JLbsDateEdit)this.m_Edit));
/* 164 */     Date date = ((JLbsDateEdit)this.m_Edit).getDate();
/* 165 */     if (date == null) {
/* 166 */       calendar.setToday();
/*     */     } else {
/* 168 */       calendar.setDate(date);
/* 169 */     }  dialog.setContentPane((Container)calendar);
/*     */     
/* 171 */     dialog.addWindowListener(new JLbsDateEditWithCalendarDialogAdapter());
/* 172 */     dialog.show();
/* 173 */     this.m_PopupDlg = (JPanel)calendar;
/*     */     
/* 175 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 179 */             JLbsDateEditWithCalendar.this.m_PopupDlg.invalidate();
/* 180 */             JLbsDateEditWithCalendar.this.m_PopupDlg.validate();
/* 181 */             JLbsDateEditWithCalendar.this.m_PopupDlg.repaint();
/* 182 */             JLbsDateEditWithCalendar.this.m_PopupDlg.grabFocus();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void popupModalCalendar(boolean inDialog) {
/* 192 */     doHidePopup(this.m_Popup, true);
/* 193 */     JLbsPopupMenu.setDefaultLightWeightPopupEnabled(!inDialog);
/* 194 */     JLbsCalendarPanel calendar = createCalendarPanel();
/* 195 */     this.m_PopupPanel = (JPanel)calendar;
/* 196 */     calendar.setInDialog(inDialog);
/* 197 */     calendar.setBorder(new LineBorder(Color.BLACK));
/* 198 */     Point p = this.m_Edit.getLocationOnScreen();
/* 199 */     Dimension size = null;
/* 200 */     size = calendar.getPreferredSize();
/* 201 */     size.width += 20;
/* 202 */     p.y += this.m_Edit.getHeight();
/* 203 */     calendar.setPreferredSize(size);
/* 204 */     Popup popup = PopupFactory.getSharedInstance().getPopup(getRootPane(), (Component)calendar, p.x, p.y);
/* 205 */     calendar.setCalendarListener(new JLbsDateEditWithCalendarPanelListener(popup, (JLbsDateEdit)this.m_Edit));
/* 206 */     this.m_PopupFocusListener = new JLbsDateEditFocusListener(popup, (JPanel)calendar);
/* 207 */     this.m_PopupPanel.addFocusListener(this.m_PopupFocusListener);
/* 208 */     this.m_Edit.addFocusListener(this.m_PopupFocusListener);
/* 209 */     this.m_PopupKeyListener = new JLbsDateEditWithCalendarKeyListener(popup);
/* 210 */     this.m_Edit.addKeyListener(this.m_PopupKeyListener);
/* 211 */     popup.show();
/*     */     
/* 213 */     this.m_Popup = popup;
/* 214 */     calendar.setPopup(this.m_Popup);
/* 215 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 219 */             if (JLbsDateEditWithCalendar.this.m_PopupPanel != null) {
/*     */               
/* 221 */               JLbsDateEditWithCalendar.this.m_PopupPanel.requestFocus();
/* 222 */               JLbsDateEditWithCalendar.this.m_PopupPanel.grabFocus();
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doCloseCalendar(JDialog dlg) {
/* 231 */     dlg.hide();
/* 232 */     dlg.dispose();
/* 233 */     this.m_Edit.grabFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doClosePopupMenu(JPopupMenu menu) {
/* 238 */     menu.setVisible(false);
/* 239 */     this.m_Edit.grabFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   private void doHidePopup(Popup popup, boolean initialize) {
/* 244 */     if (this.m_PopupPanel != null) {
/*     */       
/* 246 */       popup.hide();
/* 247 */       this.m_PopupPanel = null;
/* 248 */       if (!initialize) {
/*     */         
/* 250 */         this.m_Edit.removeKeyListener(this.m_PopupKeyListener);
/* 251 */         this.m_Edit.removeFocusListener(this.m_PopupFocusListener);
/* 252 */         KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
/* 253 */         this.m_Edit.grabFocus();
/*     */       } 
/* 255 */       popup = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void popupExtensionMenu() {
/* 261 */     doHidePopup(this.m_Popup, true);
/* 262 */     if (this.m_ExtPopupItems == null || this.m_ExtPopupItems.length() == 0)
/*     */       return; 
/* 264 */     JLbsPopupMenu menu = new JLbsPopupMenu();
/* 265 */     menu.setBorder(new LineBorder(Color.gray, 1));
/* 266 */     menu.setActionListener(this);
/* 267 */     menu.addItems(new JLbsStringList(this.m_ExtPopupItems));
/* 268 */     menu.setAlignmentX(1.0F);
/* 269 */     menu.show((Component)this, getWidth() - 1, getHeight());
/*     */ 
/*     */     
/* 272 */     KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void requestFocus() {
/* 278 */     super.requestFocus();
/* 279 */     ((LbsDateEditWithCalendarImplementor)this.m_Implementor).requestFocus();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFocusOwner() {
/* 285 */     return super.isFocusOwner();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void focusLost(FocusEvent e) {
/* 291 */     super.focusLost(e);
/* 292 */     if (e.getSource() == this.m_Edit && !(e.getOppositeComponent() instanceof JLbsCalendarPanel)) {
/*     */       
/* 294 */       Calendar c = ((JLbsDateEdit)this.m_Edit).getCalendarValue();
/* 295 */       if (c != null) {
/*     */         
/* 297 */         int month = c.get(2) + 1;
/* 298 */         String dateStr = String.valueOf(c.get(5)) + "/" + month + "/" + c.get(1);
/* 299 */         recordSetDate(dateStr);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void focusGained(FocusEvent e) {
/* 307 */     super.focusGained(e);
/* 308 */     recordRequestFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRequestFocus(int actionID) {
/* 313 */     final int mActionID = actionID;
/* 314 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 318 */             JLbsDateEditWithCalendar.this.requestFocus();
/* 319 */             JLbsComponentHelper.statusChanged(4, mActionID, null);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void doSetDate(String date, int actionID) {
/* 326 */     String mDate = date;
/* 327 */     final int fActionID = actionID;
/* 328 */     String[] s = null;
/* 329 */     GregorianCalendar calendar = null;
/* 330 */     if (mDate != null)
/* 331 */       s = mDate.split("/"); 
/* 332 */     if (s != null && s.length == 3) {
/* 333 */       calendar = new GregorianCalendar(Integer.parseInt(s[2]), Integer.parseInt(s[1]) - 1, Integer.parseInt(s[0]));
/*     */     } else {
/* 335 */       calendar = null;
/* 336 */     }  final GregorianCalendar mCalendar = calendar;
/* 337 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 341 */             JLbsDateEditWithCalendar.this.setDate(mCalendar);
/* 342 */             JLbsComponentHelper.statusChanged(4, fActionID, null);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDate(GregorianCalendar cal) {
/* 349 */     ((LbsDateEditWithCalendarImplementor)this.m_Implementor).setDate(cal);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDate(Calendar calendar) {
/* 354 */     ((LbsDateEditWithCalendarImplementor)this.m_Implementor).setDate(calendar);
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getCalendar() {
/* 359 */     return ((LbsDateEditWithCalendarImplementor)this.m_Implementor).getCalendar();
/*     */   }
/*     */ 
/*     */   
/*     */   public static Calendar getCalculatedDate(int index) {
/* 364 */     return LbsDateEditWithCalendarImplementor.getCalculatedDate(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Calendar getCalculatedDate(int index, TimeZone timeZone) {
/* 369 */     return LbsDateEditWithCalendarImplementor.getCalculatedDate(index, timeZone);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCalculatedDate(int index) {
/* 374 */     ((LbsDateEditWithCalendarImplementor)this.m_Implementor).setCalculatedDate(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 379 */     ((LbsDateEditWithCalendarImplementor)this.m_Implementor).actionPerformed(e);
/*     */   }
/*     */ 
/*     */   
/*     */   class JLbsDateEditWithCalendarDialogAdapter
/*     */     extends WindowAdapter
/*     */   {
/*     */     public void windowDeactivated(WindowEvent e) {
/* 387 */       Object obj = e.getSource();
/* 388 */       if (obj instanceof JDialog) {
/* 389 */         JLbsDateEditWithCalendar.this.doCloseCalendar((JDialog)obj);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   class JLbsDateEditWithCalendarKeyListener
/*     */     implements KeyListener
/*     */   {
/*     */     private Popup m_Popup;
/*     */     
/*     */     public JLbsDateEditWithCalendarKeyListener(Popup popup) {
/* 400 */       this.m_Popup = popup;
/*     */     }
/*     */ 
/*     */     
/*     */     public void keyPressed(KeyEvent e) {
/* 405 */       if (e.getModifiers() == 0 && JLbsDateEditWithCalendar.this.m_PopupPanel instanceof JLbsCalendarPanel) {
/*     */         
/* 407 */         JLbsCalendarPanel calPanel = (JLbsCalendarPanel)JLbsDateEditWithCalendar.this.m_PopupPanel;
/* 408 */         calPanel.doKeyNavigation(e);
/*     */       } 
/* 410 */       if (e.isConsumed() || e.isAltDown())
/*     */         return; 
/* 412 */       JLbsDateEditWithCalendar.this.doHidePopup(this.m_Popup, false);
/* 413 */       e.consume();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void keyReleased(KeyEvent e) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void keyTyped(KeyEvent e) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   class JLbsDateEditFocusListener
/*     */     implements FocusListener
/*     */   {
/*     */     private Popup m_Popup;
/*     */     
/*     */     private JPanel m_Panel;
/*     */ 
/*     */     
/*     */     public JLbsDateEditFocusListener(Popup popup, JPanel panel) {
/* 436 */       this.m_Popup = popup;
/* 437 */       this.m_Panel = panel;
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean isCompParent(Component parent, Container comp) {
/* 442 */       if (parent != null)
/* 443 */         while (comp != null) {
/*     */           
/* 445 */           if (parent == comp)
/* 446 */             return true; 
/* 447 */           comp = comp.getParent();
/*     */         }  
/* 449 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     private void hidePopup() {
/* 454 */       if (this.m_Popup != null) {
/*     */         
/* 456 */         this.m_Popup.hide();
/* 457 */         this.m_Popup = null;
/* 458 */         this.m_Panel = null;
/* 459 */         JLbsDateEditWithCalendar.this.m_PopupPanel = null;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void focusGained(FocusEvent e) {}
/*     */ 
/*     */     
/*     */     public void focusLost(FocusEvent e) {
/* 469 */       Component newFocus = e.getOppositeComponent();
/* 470 */       if (this.m_Panel == null || (!this.m_Panel.isFocusOwner() && !this.m_Panel.isAncestorOf(newFocus)))
/*     */       {
/* 472 */         if (isCompParent(newFocus, this.m_Panel)) {
/* 473 */           this.m_Panel.grabFocus();
/*     */         } else {
/* 475 */           hidePopup();
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   class JLbsDateEditWithCalendarPanelKeyAdapter extends KeyAdapter {
/*     */     protected JDialog m_Dialog;
/*     */     
/*     */     public JLbsDateEditWithCalendarPanelKeyAdapter(JDialog dialog) {
/* 485 */       this.m_Dialog = dialog;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void keyPressed(KeyEvent keEvt) {
/* 491 */       if (this.m_Dialog == null)
/*     */         return; 
/* 493 */       switch (keEvt.getKeyCode()) {
/*     */       
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   class JLbsDateEditWithCalendarPanelListener
/*     */     implements ILbsCalendarPanelListener
/*     */   {
/*     */     protected Object m_Window;
/*     */     
/*     */     protected JLbsDateEdit m_Edit;
/*     */     
/*     */     public JLbsDateEditWithCalendarPanelListener(Object window, JLbsDateEdit edit) {
/* 508 */       this.m_Window = window;
/* 509 */       this.m_Edit = edit;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void dateSelectionChanged(Object sender, int iDay, int iMonth, int iYear) {}
/*     */ 
/*     */     
/*     */     public void dateSelected(Object sender, int iDay, int iMonth, int iYear) {
/* 518 */       if (sender instanceof JLbsCalendarPanel) {
/*     */         
/* 520 */         if (this.m_Edit != null) {
/*     */           
/* 522 */           this.m_Edit.setValueChangedByParent(true);
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
/* 533 */           this.m_Edit.setDate((new GregorianCalendar(iYear, iMonth, iDay)).getTime());
/*     */         } 
/* 535 */         if (JLbsDateEditWithCalendar.this.getFixedDateSelectionHandler() != null)
/* 536 */           JLbsDateEditWithCalendar.this.getFixedDateSelectionHandler().fixedDateSelected(0); 
/* 537 */         doClose();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void doCloseControl() {
/* 543 */       doClose();
/*     */     }
/*     */ 
/*     */     
/*     */     private void doClose() {
/* 548 */       if (this.m_Edit != null)
/*     */       {
/* 550 */         if (this.m_Edit.getFocusListener() != null && this.m_Edit.getFocusListener() instanceof ILbsExtraPaneControl)
/*     */         {
/* 552 */           ((ILbsExtraPaneControl)this.m_Edit.getFocusListener()).setExtraControls();
/*     */         }
/*     */       }
/* 555 */       if (this.m_Window instanceof JDialog) {
/* 556 */         JLbsDateEditWithCalendar.this.doCloseCalendar((JDialog)this.m_Window);
/* 557 */       } else if (this.m_Window instanceof JPopupMenu) {
/* 558 */         JLbsDateEditWithCalendar.this.doClosePopupMenu((JPopupMenu)this.m_Window);
/* 559 */       } else if (this.m_Window instanceof Popup) {
/* 560 */         JLbsDateEditWithCalendar.this.doHidePopup((Popup)this.m_Window, false);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public ILbsFixedDateSelectionListener getFixedDateSelectionHandler() {
/* 566 */     return ((LbsDateEditWithCalendarImplementor)this.m_Implementor).getFixedDateSelectionHandler();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFixedDateSelectionHandler(ILbsFixedDateSelectionListener listener) {
/* 571 */     ((LbsDateEditWithCalendarImplementor)this.m_Implementor).setFixedDateSelectionHandler(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowWeeks() {
/* 576 */     return ((LbsDateEditWithCalendarImplementor)this.m_Implementor).isShowWeeks();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShowWeeks(boolean b) {
/* 581 */     ((LbsDateEditWithCalendarImplementor)this.m_Implementor).setShowWeeks(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordSetDate(String date) {
/* 586 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "SET_DATE", date);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordRequestFocus() {
/* 592 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "REQUEST_FOCUS");
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetModified(Calendar newValue) {
/* 597 */     ((LbsDateEditWithCalendarImplementor)this.m_Implementor).resetModified(newValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar getCalendarValue() {
/* 602 */     return ((LbsDateEditWithCalendarImplementor)this.m_Implementor).getCalendarValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCultureInfo(ILbsCultureInfo info) {
/* 607 */     ((JLbsDateEdit)this.m_Edit).setCultureInfo(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsDateEdit getDateEdit() {
/* 612 */     return (JLbsDateEdit)this.m_Edit;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\datedit\JLbsDateEditWithCalendar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */