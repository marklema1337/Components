/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.invoke.RttiUtil;
/*     */ import com.lbs.recording.interfaces.ILbsPlayerListener;
/*     */ import com.lbs.recording.interfaces.LbsPlayerMessage;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Window;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Locale;
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
/*     */ public class JLbsOpenWindowListing
/*     */ {
/*     */   public static final String PROP_FILE_NAME = "File_Name";
/*     */   
/*     */   public static int getUniqueIDForWindowName(String windowName) {
/*  34 */     synchronized ((JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows) {
/*     */ 
/*     */ 
/*     */       
/*  38 */       int iSize = (JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows.size();
/*  39 */       for (int i = 0; i < iSize; i++) {
/*     */         
/*  41 */         WeakReference ref = (JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows.get(i);
/*  42 */         Object form = ref.get();
/*     */         
/*  44 */         if (form instanceof JLbsFrame) {
/*     */           
/*  46 */           String formName = (String)((JLbsFrame)form).getFormProp("File_Name");
/*     */           
/*  48 */           if (!JLbsStringUtil.isEmpty(formName) && formName.equalsIgnoreCase(windowName)) {
/*  49 */             return ((JLbsFrame)form).getUniqueIdentifier();
/*     */           }
/*  51 */         } else if (form instanceof JLbsDialog) {
/*     */           
/*  53 */           String formName = (String)((JLbsDialog)form).getFormProp("File_Name");
/*     */           
/*  55 */           if (!JLbsStringUtil.isEmpty(formName) && formName.equalsIgnoreCase(windowName)) {
/*  56 */             return ((JLbsDialog)form).getUniqueIdentifier();
/*     */           }
/*  58 */         } else if (form instanceof JLbsInternalFrame) {
/*     */           
/*  60 */           String formName = (String)((JLbsInternalFrame)form).getFormProp("File_Name");
/*     */           
/*  62 */           if (!JLbsStringUtil.isEmpty(formName) && formName.equalsIgnoreCase(windowName)) {
/*  63 */             return ((JLbsInternalFrame)form).getUniqueIdentifier();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  69 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getWindowNameForUniqueID(int uniqueIdentifier) {
/*  74 */     synchronized ((JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows) {
/*     */ 
/*     */ 
/*     */       
/*  78 */       int iSize = (JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows.size();
/*  79 */       for (int i = 0; i < iSize; i++) {
/*     */         
/*  81 */         WeakReference ref = (JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows.get(i);
/*  82 */         Object form = ref.get();
/*     */         
/*  84 */         if (form instanceof JLbsFrame && uniqueIdentifier == ((JLbsFrame)form).getUniqueIdentifier()) {
/*  85 */           return (String)((JLbsFrame)form).getFormProp("File_Name");
/*     */         }
/*  87 */         if (form instanceof JLbsDialog && uniqueIdentifier == ((JLbsDialog)form).getUniqueIdentifier()) {
/*  88 */           return (String)((JLbsDialog)form).getFormProp("File_Name");
/*     */         }
/*  90 */         if (form instanceof JLbsInternalFrame && uniqueIdentifier == ((JLbsInternalFrame)form).getUniqueIdentifier()) {
/*  91 */           return (String)((JLbsInternalFrame)form).getFormProp("File_Name");
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  96 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ArrayList getOpenDialogs() {
/* 101 */     ArrayList<Object> list = new ArrayList();
/* 102 */     synchronized ((JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows) {
/*     */ 
/*     */ 
/*     */       
/* 106 */       int iSize = (JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows.size();
/* 107 */       for (int i = 0; i < iSize; i++) {
/*     */         
/* 109 */         WeakReference ref = (JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows.get(i);
/* 110 */         Object form = ref.get();
/* 111 */         if (form != null)
/* 112 */           list.add(form); 
/*     */       } 
/*     */     } 
/* 115 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ArrayList getOpenDialogs(String key) {
/* 120 */     ArrayList<WeakReference> list = null;
/* 121 */     synchronized ((JLbsOpenWindowListingFieldHolder.getInstance()).ms_KeyOpenWindows) {
/*     */       
/* 123 */       list = (ArrayList)(JLbsOpenWindowListingFieldHolder.getInstance()).ms_KeyOpenWindows.get(key);
/*     */     } 
/* 125 */     ArrayList<Object> innerList = new ArrayList();
/* 126 */     if (list != null)
/*     */     {
/* 128 */       synchronized (list) {
/*     */ 
/*     */ 
/*     */         
/* 132 */         int iSize = list.size();
/* 133 */         for (int i = 0; i < iSize; i++) {
/*     */           
/* 135 */           WeakReference ref = list.get(i);
/* 136 */           Object form = ref.get();
/* 137 */           if (form != null)
/* 138 */             innerList.add(form); 
/*     */         } 
/*     */       } 
/*     */     }
/* 142 */     return innerList;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void removeWindow(Object comp) {
/* 147 */     if (comp != null)
/* 148 */       synchronized ((JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows) {
/*     */         
/* 150 */         WeakReference ref = null;
/* 151 */         for (int i = 0; i < (JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows.size(); i++) {
/*     */           
/* 153 */           WeakReference item = (JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows.get(i);
/* 154 */           if (item.get() == comp) {
/*     */             
/* 156 */             ref = item;
/*     */             break;
/*     */           } 
/*     */         } 
/* 160 */         if (ref != null) {
/* 161 */           unregisterWindow(ref);
/*     */         }
/*     */       }  
/*     */   }
/*     */   
/*     */   public static void removeWindow(String key, Object comp) {
/* 167 */     if (comp != null) {
/*     */       
/* 169 */       ArrayList<WeakReference> list = (ArrayList)(JLbsOpenWindowListingFieldHolder.getInstance()).ms_KeyOpenWindows.get(key);
/* 170 */       if (list == null)
/*     */         return; 
/* 172 */       WeakReference ref = null;
/* 173 */       synchronized (list) {
/*     */         
/* 175 */         for (int i = 0; i < list.size(); i++) {
/*     */           
/* 177 */           WeakReference item = list.get(i);
/* 178 */           if (item.get() == comp) {
/*     */             
/* 180 */             ref = item;
/*     */             break;
/*     */           } 
/*     */         } 
/* 184 */         if (ref != null) {
/* 185 */           list.remove(ref);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void registerWindow(WeakReference ref) {
/* 192 */     if (ref != null) {
/* 193 */       synchronized ((JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows) {
/*     */         
/* 195 */         (JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows.add(ref);
/* 196 */         checkActivationList(true, ref);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static void registerWindow(String key, WeakReference ref) {
/* 202 */     if (ref != null) {
/*     */       
/* 204 */       ArrayList<WeakReference> list = (ArrayList)(JLbsOpenWindowListingFieldHolder.getInstance()).ms_KeyOpenWindows.get(key);
/* 205 */       if (list == null)
/*     */       {
/* 207 */         synchronized ((JLbsOpenWindowListingFieldHolder.getInstance()).ms_KeyOpenWindows) {
/*     */           
/* 209 */           list = new ArrayList();
/* 210 */           (JLbsOpenWindowListingFieldHolder.getInstance()).ms_KeyOpenWindows.put(key, list);
/*     */         } 
/*     */       }
/* 213 */       synchronized (list) {
/*     */         
/* 215 */         list.add(ref);
/*     */       } 
/*     */     } 
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
/*     */   private static void checkActivationList(boolean b, WeakReference ref) {
/* 229 */     if (ref == null || (JLbsOpenWindowListingFieldHolder.getInstance()).m_PlayerListener == null)
/*     */       return; 
/* 231 */     Object form = ref.get();
/* 232 */     boolean repeatSent = false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {  }
/*     */     finally
/* 287 */     { if (b && !repeatSent && form instanceof com.lbs.recording.interfaces.ILbsMessageDlgRecordingEvents)
/*     */       
/* 289 */       { LbsPlayerMessage msg = new LbsPlayerMessage();
/* 290 */         msg.Status = 7;
/* 291 */         msg.Data = form;
/* 292 */         (JLbsOpenWindowListingFieldHolder.getInstance()).m_PlayerListener.statusChanged(msg); }  }  if (b && !repeatSent && form instanceof com.lbs.recording.interfaces.ILbsMessageDlgRecordingEvents) { LbsPlayerMessage msg = new LbsPlayerMessage(); msg.Status = 7; msg.Data = form; (JLbsOpenWindowListingFieldHolder.getInstance()).m_PlayerListener.statusChanged(msg); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void sendRepeatMessage(String key, Object obj) {
/* 303 */     LbsPlayerMessage msg = new LbsPlayerMessage();
/* 304 */     msg.ActionID = ((Integer)obj).intValue();
/* 305 */     msg.Status = 3;
/* 306 */     msg.Data = key;
/* 307 */     (JLbsOpenWindowListingFieldHolder.getInstance()).m_PlayerListener.statusChanged(msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean checkAndRemove(String key, String formName) {
/* 318 */     String curKey = formName;
/* 319 */     Object obj = (JLbsOpenWindowListingFieldHolder.getInstance()).ms_ActivationList.get(formName);
/* 320 */     if (obj == null) {
/*     */       
/* 322 */       String formPrefix = getFormNamePrefix(formName);
/* 323 */       Enumeration<String> keys = (JLbsOpenWindowListingFieldHolder.getInstance()).ms_ActivationList.keys();
/* 324 */       while (keys.hasMoreElements()) {
/*     */         
/* 326 */         curKey = keys.nextElement();
/* 327 */         if (areSameFormName(curKey, formPrefix, true)) {
/*     */           
/* 329 */           obj = (JLbsOpenWindowListingFieldHolder.getInstance()).ms_ActivationList.get(curKey);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 335 */     if (obj != null && obj instanceof Integer) {
/*     */       
/* 337 */       synchronized ((JLbsOpenWindowListingFieldHolder.getInstance()).ms_ActivationList) {
/*     */         
/* 339 */         (JLbsOpenWindowListingFieldHolder.getInstance()).ms_ActivationList.remove(curKey);
/* 340 */         (JLbsOpenWindowListingFieldHolder.getInstance()).ms_AlternateNames.remove(key);
/*     */       } 
/* 342 */       sendRepeatMessage(formName, obj);
/* 343 */       return true;
/*     */     } 
/* 345 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void unregisterWindow(WeakReference ref) {
/* 350 */     if (ref != null) {
/* 351 */       synchronized ((JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows) {
/*     */         
/* 353 */         (JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows.remove(ref);
/* 354 */         checkActivationList(false, ref);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static void closeAll() {
/* 360 */     synchronized ((JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows) {
/*     */ 
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 366 */         int iSize = (JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows.size();
/* 367 */         for (int i = iSize - 1; i >= 0; i--) {
/*     */           
/* 369 */           WeakReference ref = (JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows.get(i);
/* 370 */           Object form = ref.get();
/* 371 */           checkActivationList(false, ref);
/* 372 */           if (form instanceof Window) {
/*     */             
/* 374 */             Window frm = (Window)form;
/* 375 */             if (frm instanceof ILbsLogoutListener)
/* 376 */               ((ILbsLogoutListener)frm).windowClosingOnLogout(); 
/* 377 */             frm.dispose();
/*     */           }
/* 379 */           else if (form instanceof JLbsInternalFrame) {
/*     */             
/* 381 */             JLbsInternalFrame frm = (JLbsInternalFrame)form;
/* 382 */             if (frm != null) {
/*     */               
/* 384 */               if (frm instanceof ILbsLogoutListener)
/* 385 */                 frm.windowClosingOnLogout(); 
/* 386 */               if (!frm.isValid() && !frm.isVisible()) {
/* 387 */                 frm.dispose();
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/* 392 */       } catch (Exception e) {
/*     */         
/* 394 */         e.printStackTrace();
/*     */       } 
/* 396 */       (JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void closeAll(String key) {
/* 402 */     ArrayList<WeakReference> list = null;
/* 403 */     synchronized ((JLbsOpenWindowListingFieldHolder.getInstance()).ms_KeyOpenWindows) {
/*     */       
/* 405 */       list = (ArrayList)(JLbsOpenWindowListingFieldHolder.getInstance()).ms_KeyOpenWindows.get(key);
/* 406 */       (JLbsOpenWindowListingFieldHolder.getInstance()).ms_KeyOpenWindows.remove(key);
/*     */     } 
/* 408 */     if (list != null) {
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */         
/* 414 */         int iSize = list.size();
/* 415 */         for (int i = iSize - 1; i >= 0; i--)
/*     */         {
/* 417 */           WeakReference ref = list.get(i);
/* 418 */           Object form = ref.get();
/* 419 */           checkActivationList(false, ref);
/* 420 */           if (form instanceof Window)
/*     */           {
/* 422 */             Window frm = (Window)form;
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 427 */             if (frm instanceof ILbsLogoutListener)
/* 428 */               ((ILbsLogoutListener)frm).windowClosingOnLogout(); 
/* 429 */             frm.dispose();
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 435 */       catch (Exception e) {
/*     */         
/* 437 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isAlreadyOpened(int uid) {
/* 444 */     synchronized ((JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows) {
/*     */ 
/*     */ 
/*     */       
/* 448 */       int iSize = (JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows.size();
/* 449 */       for (int i = 0; i < iSize; i++) {
/*     */         
/* 451 */         WeakReference ref = (JLbsOpenWindowListingFieldHolder.getInstance()).ms_OpenWindows.get(i);
/* 452 */         Object form = ref.get();
/* 453 */         if (form instanceof ILbsComponentBase) {
/*     */           
/* 455 */           ILbsComponentBase lbsForm = (ILbsComponentBase)form;
/* 456 */           if (lbsForm.getUniqueIdentifier() == uid)
/* 457 */             return true; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 461 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void add2ActivationList(int uid, int alternateUid, int actionID) {
/* 466 */     String key = (new StringBuilder(String.valueOf(uid))).toString();
/* 467 */     if (isAlreadyOpened(uid) || isAlreadyOpened(alternateUid)) {
/* 468 */       sendRepeatMessage(key, Integer.valueOf(actionID));
/*     */     
/*     */     }
/* 471 */     else if (!(JLbsOpenWindowListingFieldHolder.getInstance()).ms_ActivationList.containsKey(key)) {
/*     */       
/* 473 */       (JLbsOpenWindowListingFieldHolder.getInstance()).ms_ActivationList.put(key, Integer.valueOf(actionID));
/* 474 */       (JLbsOpenWindowListingFieldHolder.getInstance()).ms_AlternateNames.put(key, (new StringBuilder(String.valueOf(alternateUid))).toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void add2ActivationList(String formName, String alternateFormName, int actionID) {
/* 481 */     int uid = getUniqueIDForWindowName(formName);
/* 482 */     int alternateUid = getUniqueIDForWindowName(alternateFormName);
/* 483 */     String key = (new StringBuilder(String.valueOf(uid))).toString();
/*     */     
/* 485 */     if (uid != -1 && (isAlreadyOpened(uid) || isAlreadyOpened(alternateUid))) {
/* 486 */       sendRepeatMessage(key, Integer.valueOf(actionID));
/*     */     
/*     */     }
/* 489 */     else if (!(JLbsOpenWindowListingFieldHolder.getInstance()).ms_ActivationList.containsKey(formName)) {
/*     */       
/* 491 */       (JLbsOpenWindowListingFieldHolder.getInstance()).ms_ActivationList.put(formName, Integer.valueOf(actionID));
/* 492 */       (JLbsOpenWindowListingFieldHolder.getInstance()).ms_AlternateNames.put(formName, alternateFormName);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setPlayerListener(ILbsPlayerListener playerListener) {
/* 499 */     (JLbsOpenWindowListingFieldHolder.getInstance()).m_PlayerListener = playerListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getOpenWindowNames() {
/* 504 */     String openWindowNames = "";
/* 505 */     StringBuilder buff = new StringBuilder();
/*     */ 
/*     */     
/*     */     try {
/* 509 */       ArrayList<WeakReference<Window>> openDialogs = getOpenDialogs();
/* 510 */       for (int i = 0; i < openDialogs.size(); i++) {
/*     */         
/* 512 */         Window window = ((WeakReference<Window>)openDialogs.get(i)).get();
/* 513 */         if (window != null) {
/*     */ 
/*     */           
/* 516 */           if (RttiUtil.isSubClassOf(window.getClass(), "com.lbs.xui.JLbsXUIPane")) {
/*     */             
/* 518 */             Method method = window.getClass().getMethod("getFormName", null);
/* 519 */             String formName = (String)method.invoke(window, null);
/*     */             
/* 521 */             buff.append("(" + formName + ")");
/*     */           } 
/*     */           
/* 524 */           if (window instanceof Dialog) {
/* 525 */             buff.append(((Dialog)window).getTitle());
/*     */           }
/* 527 */           else if (window instanceof Frame) {
/* 528 */             buff.append(((Frame)window).getTitle());
/*     */           } else {
/*     */             
/* 531 */             buff.append(window.getName());
/*     */           } 
/*     */           
/* 534 */           if (window.isActive()) {
/* 535 */             buff.append("(Active)");
/*     */           }
/* 537 */           if (i < openDialogs.size() - 1)
/* 538 */             buff.append("| "); 
/*     */         } 
/*     */       } 
/* 541 */       openWindowNames = buff.toString();
/*     */     
/*     */     }
/* 544 */     catch (Exception t) {
/*     */       
/* 546 */       openWindowNames = String.valueOf(openWindowNames) + "\n" + t.getMessage();
/*     */     } 
/* 548 */     return openWindowNames;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Container getWindowByFormName(String formName, boolean startFromLastlyOpenedWnd, boolean isDialog) {
/* 554 */     return getWindowByFormName(formName, startFromLastlyOpenedWnd, isDialog, false);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean areSameFormName(String wndName, String formName, boolean isPrefix) {
/* 559 */     if (formName.startsWith("{") && formName.contains("_CST_")) {
/* 560 */       formName = formName.replaceAll("_CST_", "/");
/*     */     }
/* 562 */     if (!isPrefix) {
/* 563 */       return JLbsStringUtil.areEqualNoCase(wndName, formName);
/*     */     }
/* 565 */     if (JLbsStringUtil.areEqualNoCase(wndName, formName))
/* 566 */       return true; 
/* 567 */     wndName = wndName.toLowerCase(Locale.ENGLISH);
/* 568 */     formName = formName.toLowerCase(Locale.ENGLISH);
/* 569 */     if (wndName.startsWith(formName) && formName.length() < wndName.length()) {
/*     */       
/* 571 */       char c = wndName.charAt(formName.length());
/* 572 */       if (c == '%' || c == '.')
/* 573 */         return true; 
/*     */     } 
/* 575 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFormNamePrefix(String formName) {
/* 580 */     String result = "";
/* 581 */     if (JLbsStringUtil.isEmpty(formName))
/* 582 */       return result; 
/* 583 */     int x = formName.indexOf("%");
/* 584 */     if (x < 0)
/* 585 */       x = formName.lastIndexOf('.'); 
/* 586 */     if (x > 0)
/* 587 */       result = formName.substring(0, x); 
/* 588 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Container getWindowByFormName(String formName, boolean startFromLastlyOpenedWnd, boolean isDialog, boolean isPrefix) {
/* 594 */     ArrayList formList = getOpenDialogs();
/* 595 */     if (formList != null && formList.size() > 0) {
/*     */       
/* 597 */       Window wnd = null;
/* 598 */       JLbsFrame frame = null;
/* 599 */       JLbsDialog dlg = null;
/* 600 */       JLbsInternalFrame iFrame = null;
/* 601 */       for (int idx = 0; idx < formList.size(); idx++) {
/*     */         
/* 603 */         int wndIdx = idx;
/* 604 */         if (startFromLastlyOpenedWnd) {
/* 605 */           wndIdx = formList.size() - idx + 1;
/*     */         }
/* 607 */         Object container = formList.get(wndIdx);
/* 608 */         if (container instanceof Window) {
/*     */           
/* 610 */           wnd = (Window)container;
/* 611 */           if (wnd instanceof JLbsDialog) {
/*     */             
/* 613 */             dlg = (JLbsDialog)wnd;
/* 614 */             if (areSameFormName(dlg.getFormName(), formName, isPrefix)) {
/* 615 */               return dlg;
/*     */             }
/* 617 */           } else if (!isDialog && wnd instanceof JLbsFrame) {
/*     */             
/* 619 */             frame = (JLbsFrame)wnd;
/* 620 */             if (areSameFormName(frame.getFormName(), formName, isPrefix)) {
/* 621 */               return frame;
/*     */             }
/*     */           } 
/* 624 */         } else if (container instanceof JLbsInternalFrame) {
/*     */           
/* 626 */           if (!isDialog) {
/*     */             
/* 628 */             iFrame = (JLbsInternalFrame)container;
/* 629 */             if (areSameFormName(iFrame.getFormName(), formName, isPrefix)) {
/* 630 */               return iFrame;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 636 */     return null;
/*     */   }
/*     */   
/*     */   public static class JLbsOpenWindowListingFieldHolder
/*     */   {
/* 641 */     private ArrayList ms_OpenWindows = new ArrayList();
/*     */     private ILbsPlayerListener m_PlayerListener;
/* 643 */     private Hashtable ms_ActivationList = new Hashtable<>();
/* 644 */     private Hashtable<String, String> ms_AlternateNames = new Hashtable<>();
/* 645 */     private HashMap<String, ArrayList> ms_KeyOpenWindows = new HashMap<>();
/*     */ 
/*     */     
/*     */     private static JLbsOpenWindowListingFieldHolder getInstance() {
/* 649 */       return LbsClassInstanceProvider.<JLbsOpenWindowListingFieldHolder>getInstanceByClass(JLbsOpenWindowListingFieldHolder.class);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsOpenWindowListing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */