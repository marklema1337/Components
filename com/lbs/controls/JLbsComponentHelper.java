/*      */ package com.lbs.controls;
/*      */ 
/*      */ import com.lbs.control.interfaces.ILbsComponent;
/*      */ import com.lbs.control.interfaces.ILbsInvokeHandler;
/*      */ import com.lbs.control.interfaces.ILbsInvokeHandlerOwner;
/*      */ import com.lbs.control.interfaces.ILbsPanel;
/*      */ import com.lbs.control.interfaces.ILbsPopupMenu;
/*      */ import com.lbs.controls.emulator.LbsComponentEmulator;
/*      */ import com.lbs.controls.emulator.LbsFocusManager;
/*      */ import com.lbs.controls.menu.IXUIFormTemplateManager;
/*      */ import com.lbs.controls.menu.JLbsPopupMenuItem;
/*      */ import com.lbs.controls.pivottable.PivotTableInfo;
/*      */ import com.lbs.controls.pivottable.PivotViewPreferences;
/*      */ import com.lbs.controls.tablereport.TableReportInfo;
/*      */ import com.lbs.controls.tablereport.TableReportPanel;
/*      */ import com.lbs.controls.tablereport.TableReportPreferences;
/*      */ import com.lbs.crypto.JLbsCryptoUtil;
/*      */ import com.lbs.globalization.ILbsCultureInfo;
/*      */ import com.lbs.interfaces.ILbsExpressionEvaluator;
/*      */ import com.lbs.mi.defs.FilterLookupEntry;
/*      */ import com.lbs.platform.interfaces.ICacheManager;
/*      */ import com.lbs.recording.interfaces.ILbsEventRecorder;
/*      */ import com.lbs.recording.interfaces.ILbsPlayerListener;
/*      */ import com.lbs.recording.interfaces.ILbsTestPlayerWrapper;
/*      */ import com.lbs.recording.interfaces.LbsPlayerMessage;
/*      */ import com.lbs.util.ClientContextHolder;
/*      */ import com.lbs.util.JLbsClientFS;
/*      */ import com.lbs.util.JLbsConstants;
/*      */ import com.lbs.util.LbsClassInstanceProvider;
/*      */ import java.awt.Container;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.EOFException;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Map;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.table.DefaultTableModel;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JLbsComponentHelper
/*      */ {
/*      */   private static ILbsEventRecorder ms_EventRecorder;
/*      */   private static ILbsTestPlayerWrapper ms_TestPlayerWrapper;
/*      */   private static ILbsExpressionEvaluator ms_ExpressionEvaluator;
/*      */   private static boolean ms_CanRecordEvent = true;
/*      */   private static boolean ms_PlayingTest = false;
/*      */   
/*      */   private static byte[] serializeObjectPlain(Object obj) throws IOException {
/*   79 */     byte[] data = null;
/*   80 */     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/*   81 */     ObjectOutputStream objStream = new ObjectOutputStream(outStream);
/*      */     
/*      */     try {
/*   84 */       objStream.writeObject(obj);
/*   85 */       data = outStream.toByteArray();
/*      */     }
/*   87 */     catch (EOFException eOFException) {
/*      */ 
/*      */     
/*      */     } finally {
/*      */       
/*   92 */       objStream.close();
/*   93 */       outStream.close();
/*      */     } 
/*   95 */     return data;
/*      */   }
/*      */ 
/*      */   
/*      */   private static Object deserializeObjectPlain(byte[] data) throws IOException, ClassNotFoundException {
/*  100 */     ByteArrayInputStream inStream = new ByteArrayInputStream(data);
/*  101 */     DataInputStream objStream = new DataInputStream(inStream);
/*  102 */     Object object = null;
/*      */     
/*      */     try {
/*  105 */       ObjectInputStream localStream = new ObjectInputStream(objStream);
/*  106 */       object = localStream.readObject();
/*      */     }
/*  108 */     catch (EOFException eOFException) {
/*      */ 
/*      */     
/*      */     } finally {
/*      */       
/*  113 */       objStream.close();
/*  114 */       inStream.close();
/*      */     } 
/*  116 */     return object;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void saveLFSObject(Object obj, String key) {
/*  121 */     if (obj == null || key == null || getCurrentContext() == null) {
/*      */       return;
/*      */     }
/*  124 */     String fileName = getLFSFileName(key);
/*      */     
/*      */     try {
/*  127 */       byte[] data = serializeObjectPlain(obj);
/*  128 */       JLbsClientFS.saveFile(fileName, data, true, false, true);
/*      */     }
/*  130 */     catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String getLFSFileName(String key) {
/*  138 */     if (key == null) {
/*  139 */       return "";
/*      */     }
/*      */     try {
/*  142 */       key = JLbsCryptoUtil.createHashString(key.getBytes("utf-8"));
/*  143 */       return JLbsClientFS.appendPath(JLbsClientFS.getCachePath(), String.valueOf(key) + "." + "lfs");
/*      */     }
/*  145 */     catch (Exception exception) {
/*      */ 
/*      */       
/*  148 */       return "";
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Object loadLFSObject(String key) {
/*  153 */     if (key == null || key.length() == 0 || getCurrentContext() == null) {
/*  154 */       return null;
/*      */     }
/*  156 */     String fileName = getLFSFileName(key);
/*      */     
/*      */     try {
/*  159 */       if (JLbsClientFS.fileExists(fileName))
/*      */       {
/*  161 */         byte[] data = JLbsClientFS.loadFile(fileName, true);
/*  162 */         if (data != null)
/*      */         {
/*  164 */           return deserializeObjectPlain(data);
/*      */         }
/*      */       }
/*      */     
/*      */     }
/*  169 */     catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */     
/*  173 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Map<Integer, FilterLookupEntry> getMapFilterLookupEnrties() {
/*  178 */     return (JLbsComponentHelperFieldHolder.getInstance()).ms_MapFilterLookupEnrties;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setMapFilterLookupEnrties(Map<Integer, FilterLookupEntry> mapFilterLookupEnrties) {
/*  183 */     (JLbsComponentHelperFieldHolder.getInstance()).ms_MapFilterLookupEnrties = mapFilterLookupEnrties;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Map getAppIdList() {
/*  188 */     ILbsComponentHelper handler = getHandler();
/*  189 */     if (handler != null)
/*  190 */       return handler.getAppIdList(); 
/*  191 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean setChildrenEnabled(ILbsPanel panel, boolean enabled) {
/*  196 */     ILbsComponentHelper handler = getHandler();
/*  197 */     if (handler != null)
/*  198 */       return handler.setChildrenEnabled(panel, enabled); 
/*  199 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static FilterLookupEntry getAppId(Integer appId) {
/*  204 */     ILbsComponentHelper handler = getHandler();
/*  205 */     if (handler != null)
/*  206 */       return handler.getAppId(appId); 
/*  207 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void processDataGridForSecondaryKeySelect(Object grid) {
/*  212 */     if (ms_EventRecorder != null) {
/*  213 */       ms_EventRecorder.processDataGridForSecondaryKeySelect(grid);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void openExportWizard(Object source, Object context) {
/*  218 */     ILbsComponentHelper handler = getHandler();
/*  219 */     if (handler != null) {
/*  220 */       handler.openExportWizard(source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void openImportWizard(Object source, Object context) {
/*  225 */     ILbsComponentHelper handler = getHandler();
/*  226 */     if (handler != null) {
/*  227 */       handler.openImportWizard(source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void ExportBrwToExcel(Object source, Object context) {
/*  232 */     ILbsComponentHelper handler = getHandler();
/*  233 */     if (handler != null) {
/*  234 */       handler.ExportBrwToExcel(source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void openTransferToFirmWizard(Object source, Object context) {
/*  239 */     ILbsComponentHelper handler = getHandler();
/*  240 */     if (handler != null) {
/*  241 */       handler.openTransferToFirmWizard(source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void openTransferToFirmWizard(Object pane, Object source, Object context) {
/*  246 */     ILbsComponentHelper handler = getHandler();
/*  247 */     if (handler != null) {
/*  248 */       handler.openTransferToFirmWizard(pane, source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static boolean verifyCalendarValue(int year, Object messageDialogImpl) {
/*  253 */     ILbsComponentHelper handler = getHandler();
/*  254 */     if (handler != null)
/*  255 */       return handler.verifyCalendarValue(year, messageDialogImpl); 
/*  256 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean verifyCalendarValue(int year, Object messageDialogImpl, ILbsCultureInfo info, boolean noCompany) {
/*  262 */     ILbsComponentHelper handler = getHandler();
/*  263 */     if (handler != null)
/*  264 */       return handler.verifyCalendarValue(year, messageDialogImpl, info, noCompany); 
/*  265 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isPlayingTest() {
/*  270 */     return ms_PlayingTest;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setPlayingTest(boolean playingTest) {
/*  275 */     ms_PlayingTest = playingTest;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableRecording() {
/*  280 */     ms_CanRecordEvent = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableRecording() {
/*  285 */     ms_CanRecordEvent = false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isEnabledRecording() {
/*  290 */     return ms_CanRecordEvent;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getUniqueIdentifier(ILbsComponentBase component) {
/*  295 */     ILbsComponentHelper handler = getHandler();
/*  296 */     if (handler == null) {
/*  297 */       return -1;
/*      */     }
/*  299 */     return handler.getUniqueIdentifier(component);
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getResourceIdentifier(ILbsComponentBase component) {
/*  304 */     ILbsComponentHelper handler = getHandler();
/*  305 */     if (handler == null) {
/*  306 */       return "";
/*      */     }
/*  308 */     return handler.getResourceIdentifier(component);
/*      */   }
/*      */ 
/*      */   
/*      */   public static ILbsComponentHelper getHandler() {
/*  313 */     return (JLbsComponentHelperFieldHolder.getInstance()).ms_Handler;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setHandler(ILbsComponentHelper handler) {
/*  318 */     (JLbsComponentHelperFieldHolder.getInstance()).ms_Handler = handler;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getTag(ILbsComponentBase component) {
/*  323 */     ILbsComponentHelper handler = getHandler();
/*  324 */     if (handler == null) {
/*  325 */       return -1;
/*      */     }
/*  327 */     return handler.getTag(component);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getParentTag(ILbsComponentBase component) {
/*  332 */     ILbsComponentHelper handler = getHandler();
/*  333 */     if (handler == null) {
/*  334 */       return -1;
/*      */     }
/*  336 */     return handler.getParentTag(component);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getAncestorTag(ILbsComponentBase component) {
/*  341 */     ILbsComponentHelper handler = getHandler();
/*  342 */     if (handler == null) {
/*  343 */       return -1;
/*      */     }
/*  345 */     return handler.getTag(component);
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getTitleForColumn(ILbsComponentBase component, int row, int column) {
/*  350 */     ILbsComponentHelper handler = getHandler();
/*  351 */     if (handler == null) {
/*  352 */       return "Unknown";
/*      */     }
/*  354 */     return handler.getTitleForColumn(component, row, column);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getTitle(ILbsComponentBase component) {
/*  360 */     ILbsComponentHelper handler = getHandler();
/*  361 */     if (handler == null) {
/*  362 */       return "Unknown";
/*      */     }
/*  364 */     return handler.getTitle(component);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getAncestorIdentifier(ILbsComponentBase component) {
/*  369 */     ILbsComponentHelper handler = getHandler();
/*  370 */     if (handler == null) {
/*  371 */       return -1;
/*      */     }
/*  373 */     return handler.getAncestorIdentifier(component);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object getValue(ILbsComponentBase component) {
/*  378 */     ILbsComponentHelper handler = getHandler();
/*  379 */     if (handler == null) {
/*  380 */       return null;
/*      */     }
/*  382 */     return handler.getValue(component);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object getValueAtCell(ILbsComponentBase component, int row, int column) {
/*  387 */     ILbsComponentHelper handler = getHandler();
/*  388 */     if (handler == null) {
/*  389 */       return null;
/*      */     }
/*  391 */     return handler.getValue(component, row, column);
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getText(ILbsComponentBase component) {
/*  396 */     ILbsComponentHelper handler = getHandler();
/*  397 */     if (handler == null) {
/*  398 */       return "";
/*      */     }
/*  400 */     return handler.getText(component);
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getPossibleValues(ILbsComponentBase component) {
/*  405 */     ILbsComponentHelper handler = getHandler();
/*  406 */     if (handler == null) {
/*  407 */       return null;
/*      */     }
/*  409 */     return handler.getPossibleValues(component);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getInvokerType(ILbsComponentBase component) {
/*  414 */     ILbsComponentHelper handler = getHandler();
/*  415 */     if (handler == null) {
/*  416 */       return -1;
/*      */     }
/*  418 */     return handler.getInvokerType(component);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getType(ILbsComponentBase component) {
/*  423 */     ILbsComponentHelper handler = getHandler();
/*  424 */     if (handler == null) {
/*  425 */       return -1;
/*      */     }
/*  427 */     return handler.getType(component);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ILbsEventRecorder getEventRecorder() {
/*  435 */     if (ms_EventRecorder != null) {
/*  436 */       return ms_EventRecorder;
/*      */     }
/*  438 */     ILbsComponentHelper handler = getHandler();
/*  439 */     return (handler != null) ? 
/*  440 */       handler.getEventRecorder() : 
/*  441 */       null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setTestPlayerWrapper(ILbsTestPlayerWrapper testPlayerWrapper) {
/*  450 */     ms_TestPlayerWrapper = testPlayerWrapper;
/*      */   }
/*      */ 
/*      */   
/*      */   public static ILbsTestPlayerWrapper getTestPlayerWrapper() {
/*  455 */     return ms_TestPlayerWrapper;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setEventRecorder(ILbsEventRecorder eventRecorder) {
/*  460 */     ms_EventRecorder = eventRecorder;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void processKeyEvent(KeyEvent evt) {
/*  465 */     ILbsComponentHelper handler = getHandler();
/*  466 */     if (handler == null) {
/*      */       return;
/*      */     }
/*  469 */     handler.processKeyEvent(evt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object getCurrentContainer() {
/*  477 */     synchronized (JLbsComponentHelper.class) {
/*      */       
/*  479 */       if ((JLbsComponentHelperFieldHolder.getInstance()).ms_CurrentContainer != null)
/*  480 */         return (JLbsComponentHelperFieldHolder.getInstance()).ms_CurrentContainer.get(); 
/*  481 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setCurrentContainer(Object currentContainer) {
/*  491 */     synchronized (JLbsComponentHelper.class) {
/*      */       
/*  493 */       (JLbsComponentHelperFieldHolder.getInstance()).ms_CurrentContainer = null;
/*  494 */       (JLbsComponentHelperFieldHolder.getInstance()).ms_CurrentContainer = new WeakReference(currentContainer);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object getMainForm() {
/*  503 */     return (JLbsComponentHelperFieldHolder.getInstance()).ms_MainForm;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setMainForm(Object mainForm) {
/*  512 */     (JLbsComponentHelperFieldHolder.getInstance()).ms_MainForm = mainForm;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object getClassInstance(Object context, String className) {
/*  517 */     ILbsComponentHelper handler = getHandler();
/*  518 */     if (handler != null) {
/*  519 */       return handler.getClassInstance(context, className);
/*      */     }
/*  521 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object getClassInstance(String className) {
/*  526 */     ILbsComponentHelper handler = getHandler();
/*  527 */     if (handler != null) {
/*  528 */       return handler.getClassInstance(null, className);
/*      */     }
/*  530 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void statusChanged(int status, int actionID, Object data) {
/*  535 */     ILbsComponentHelper handler = getHandler();
/*  536 */     if (handler != null && handler.getPlayerListener() != null) {
/*  537 */       ((ILbsPlayerListener)handler.getPlayerListener()).statusChanged(status, actionID, data);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void statusChanged(LbsPlayerMessage msg) {
/*  542 */     ILbsComponentHelper handler = getHandler();
/*  543 */     if (handler != null && handler.getPlayerListener() != null) {
/*  544 */       ((ILbsPlayerListener)handler.getPlayerListener()).statusChanged(msg);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void recordErrorMessage(String message) {
/*  549 */     ILbsComponentHelper handler = getHandler();
/*  550 */     if (handler != null && handler.getPlayerListener() != null) {
/*  551 */       ((ILbsPlayerListener)handler.getPlayerListener()).recordErrorMessage(message);
/*      */     }
/*      */   }
/*      */   
/*      */   public static boolean canRecordEvent(Object eventObj) {
/*  556 */     ILbsComponentHelper handler = getHandler();
/*  557 */     boolean ok = (handler != null) ? 
/*  558 */       handler.canRecordEvent(eventObj) : true;
/*      */     
/*  560 */     return (ms_CanRecordEvent && ok);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean canRecordSpecialEvent() {
/*  565 */     ILbsComponentHelper handler = getHandler();
/*  566 */     return (handler != null) ? 
/*  567 */       handler.canRecordEvent(null) : true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void doGridOperations(ILbsGridComponent grid, Object context) {
/*  573 */     ILbsComponentHelper handler = getHandler();
/*  574 */     if (handler != null) {
/*  575 */       handler.doGridOperations(grid, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void doFilterGridOperations(ILbsGridComponent grid, Object context) {
/*  580 */     ILbsComponentHelper handler = getHandler();
/*  581 */     if (handler != null) {
/*  582 */       handler.doFilterGridOperations(grid, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static IXUIFormTemplateManager getFormTemplateManager() {
/*  587 */     return (JLbsComponentHelperFieldHolder.getInstance()).ms_FormTemplateManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setFormTemplateManager(IXUIFormTemplateManager formTemplateManager) {
/*  592 */     (JLbsComponentHelperFieldHolder.getInstance()).ms_FormTemplateManager = formTemplateManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object getFormPropertyRecorder() {
/*  597 */     return (JLbsComponentHelperFieldHolder.getInstance()).ms_FormPropertyRecorder;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setFormPropertyRecorder(Object formPropertyRecorder) {
/*  602 */     (JLbsComponentHelperFieldHolder.getInstance()).ms_FormPropertyRecorder = formPropertyRecorder;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean canCustomizeColumns(ILbsComponentBase comp) {
/*  607 */     ILbsComponentHelper handler = getHandler();
/*  608 */     return (handler != null) ? 
/*  609 */       handler.canCustomizeColumns(comp) : false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void resetToDefaultValues(ILbsGridComponent grid, Object context) {
/*  615 */     ILbsComponentHelper handler = getHandler();
/*  616 */     if (handler != null) {
/*  617 */       handler.resetToDefaultValues(grid, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static boolean isSuperUser(Object context) {
/*  622 */     ILbsComponentHelper handler = getHandler();
/*  623 */     if (handler != null)
/*  624 */       return handler.isSuperUser(context); 
/*  625 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getUserCanNotSeeReportFiltersProperties(Object context) {
/*  630 */     ILbsComponentHelper handler = getHandler();
/*  631 */     if (handler != null)
/*  632 */       return handler.getUserCanNotSeeReportFiltersProperties(context); 
/*  633 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getUserCanCustomize(Object context) {
/*  638 */     ILbsComponentHelper handler = getHandler();
/*  639 */     if (handler != null)
/*  640 */       return handler.getUserCanCustomize(context); 
/*  641 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void clearPreferences(Object source, Object context) {
/*  646 */     ILbsComponentHelper handler = getHandler();
/*  647 */     if (handler != null) {
/*  648 */       handler.clearPreferences(source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void savePreferences(Object source, Object context) {
/*  653 */     ILbsComponentHelper handler = getHandler();
/*  654 */     if (handler != null) {
/*  655 */       handler.savePreferences(source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void saveGeneralPreferences(Object source, Object context) {
/*  660 */     ILbsComponentHelper handler = getHandler();
/*  661 */     if (handler != null) {
/*  662 */       handler.saveGeneralPreferences(source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void saveGeneralPreferencesForAllFirms(Object source, Object context) {
/*  667 */     ILbsComponentHelper handler = getHandler();
/*  668 */     if (handler != null) {
/*  669 */       handler.saveGeneralPreferencesForAllFirms(source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static boolean isQueryGrid(ILbsComponentBase component) {
/*  674 */     ILbsComponentHelper handler = getHandler();
/*  675 */     if (handler != null)
/*  676 */       return handler.isQueryGrid(component); 
/*  677 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isEditGrid(ILbsComponentBase component) {
/*  682 */     ILbsComponentHelper handler = getHandler();
/*  683 */     if (handler != null)
/*  684 */       return handler.isEditGrid(component); 
/*  685 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isFilterGrid(ILbsComponentBase component) {
/*  690 */     ILbsComponentHelper handler = getHandler();
/*  691 */     if (handler != null)
/*  692 */       return handler.isFilterGrid(component); 
/*  693 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isTreeGrid(ILbsComponentBase component) {
/*  698 */     ILbsComponentHelper handler = getHandler();
/*  699 */     if (handler != null)
/*  700 */       return handler.isTreeGrid(component); 
/*  701 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isQueryTreeGrid(ILbsComponentBase component) {
/*  706 */     ILbsComponentHelper handler = getHandler();
/*  707 */     if (handler != null)
/*  708 */       return handler.isQueryTreeGrid(component); 
/*  709 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isQuerySelectionGrid(ILbsComponentBase component) {
/*  714 */     ILbsComponentHelper handler = getHandler();
/*  715 */     if (handler != null)
/*  716 */       return handler.isQuerySelectionGrid(component); 
/*  717 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isQueryMultiSelectionGrid(ILbsComponentBase component) {
/*  722 */     ILbsComponentHelper handler = getHandler();
/*  723 */     if (handler != null)
/*  724 */       return handler.isQueryMultiSelectionGrid(component); 
/*  725 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void showRecordCount(Object source, Object context) {
/*  734 */     ILbsComponentHelper handler = getHandler();
/*  735 */     if (handler != null) {
/*  736 */       handler.showRecordCount(source, context);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void showSelectedRecordCount(Object source, Object context) {
/*  742 */     ILbsComponentHelper handler = getHandler();
/*  743 */     if (handler != null) {
/*  744 */       handler.showSelectedRecordCount(source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static boolean hasRecord(Object source) {
/*  749 */     ILbsComponentHelper handler = getHandler();
/*  750 */     if (handler != null)
/*  751 */       return handler.hasRecord(source); 
/*  752 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean allowtoEditMenu(Object source) {
/*  757 */     ILbsComponentHelper handler = getHandler();
/*  758 */     if (handler != null)
/*  759 */       return handler.allowtoEditMenu(source); 
/*  760 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean getPopupItemVisibility(ILbsPopupMenu existingMenu, int tag) {
/*  765 */     ILbsComponentHelper handler = getHandler();
/*  766 */     if (handler != null)
/*  767 */       return handler.getPopupItemVisibility(existingMenu, tag); 
/*  768 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void showRecordInfo(Object source, Object context) {
/*  773 */     ILbsComponentHelper handler = getHandler();
/*  774 */     if (handler != null) {
/*  775 */       handler.showRecordInfo(source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static boolean hasRecordInfo(Object source) {
/*  780 */     ILbsComponentHelper handler = getHandler();
/*  781 */     if (handler != null)
/*  782 */       return handler.hasRecordInfo(source); 
/*  783 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean canAddAppendMenuItem(Object source) {
/*  788 */     ILbsComponentHelper handler = getHandler();
/*  789 */     if (handler != null)
/*  790 */       return handler.canAddAppendMenuItem(source); 
/*  791 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean canAddInsertMenuItem(Object source) {
/*  796 */     ILbsComponentHelper handler = getHandler();
/*  797 */     if (handler != null)
/*  798 */       return handler.canAddInsertMenuItem(source); 
/*  799 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean canAddDeleteMenuItem(Object source) {
/*  804 */     ILbsComponentHelper handler = getHandler();
/*  805 */     if (handler != null)
/*  806 */       return handler.canAddDeleteMenuItem(source); 
/*  807 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void showReportRecordInfo(Object obj, Object context) {
/*  812 */     ILbsComponentHelper handler = getHandler();
/*  813 */     if (handler != null) {
/*  814 */       handler.showReportRecordInfo(obj, context);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void doSelectAll(Object source) {
/*  823 */     ILbsComponentHelper handler = getHandler();
/*  824 */     if (handler != null) {
/*  825 */       handler.doSelectAll(source);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void doDeselectAll(Object source) {
/*  834 */     ILbsComponentHelper handler = getHandler();
/*  835 */     if (handler != null) {
/*  836 */       handler.doDeselectAll(source);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void doInvertSelection(Object source) {
/*  845 */     ILbsComponentHelper handler = getHandler();
/*  846 */     if (handler != null) {
/*  847 */       handler.doInvertSelection(source);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void doSelectWithCount(Object source, int count) {
/*  852 */     ILbsComponentHelper handler = getHandler();
/*  853 */     if (handler != null) {
/*  854 */       handler.doSelectWithCount(source, count);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ILbsExpressionEvaluator getExpressionEvaluator() {
/*  862 */     return ms_ExpressionEvaluator;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setExpressionEvaluator(ILbsExpressionEvaluator expressionEvaluator) {
/*  871 */     synchronized (JLbsComponentHelper.class) {
/*      */       
/*  873 */       ms_ExpressionEvaluator = expressionEvaluator;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getGridSelectedColumnForInpEditor(ILbsComponentBase component) {
/*  879 */     ILbsComponentHelper handler = getHandler();
/*  880 */     if (handler == null) {
/*  881 */       return -1;
/*      */     }
/*  883 */     return handler.getGridSelectedColumnForInpEditor(component);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getGridSelectedRowForInpEditor(ILbsComponentBase component) {
/*  888 */     ILbsComponentHelper handler = getHandler();
/*  889 */     if (handler == null) {
/*  890 */       return -1;
/*      */     }
/*  892 */     return handler.getGridSelectedRowForInpEditor(component);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getInpEditorOwnerTag(ILbsComponentBase component) {
/*  897 */     ILbsComponentHelper handler = getHandler();
/*  898 */     if (handler == null) {
/*  899 */       return -1;
/*      */     }
/*  901 */     return handler.getInpEditorOwnerTag(component);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getGridTag(ILbsComponentBase component) {
/*  906 */     ILbsComponentHelper handler = getHandler();
/*  907 */     if (handler == null) {
/*  908 */       return -1;
/*      */     }
/*  910 */     return handler.getGridTag(component);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void showListReport(Object source, Object context) {
/*  920 */     ILbsComponentHelper handler = getHandler();
/*  921 */     if (handler != null) {
/*  922 */       handler.showListReport(source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void showGroupedListReport(Object source, Object context) {
/*  927 */     ILbsComponentHelper handler = getHandler();
/*  928 */     if (handler != null) {
/*  929 */       handler.showGroupedListReport(source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static boolean isLockEnabled(Object source, Object context) {
/*  934 */     ILbsComponentHelper handler = getHandler();
/*  935 */     if (handler != null)
/*  936 */       return handler.isLockEnabled(source, context); 
/*  937 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isLockedRecord(Object source, Object context) {
/*  942 */     ILbsComponentHelper handler = getHandler();
/*  943 */     if (handler != null)
/*  944 */       return handler.isLocked(source, context); 
/*  945 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void lockRecord(Object source, Object context) {
/*  950 */     ILbsComponentHelper handler = getHandler();
/*  951 */     if (handler != null) {
/*  952 */       handler.lockRecord(source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void unlockRecord(Object source, Object context) {
/*  957 */     ILbsComponentHelper handler = getHandler();
/*  958 */     if (handler != null) {
/*  959 */       handler.unlockRecord(source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void dbRefresh(Object source, Object context, Object orjComp) {
/*  964 */     ILbsComponentHelper handler = getHandler();
/*  965 */     if (handler != null) {
/*  966 */       handler.dbRefresh(source, context, orjComp);
/*      */     }
/*      */   }
/*      */   
/*      */   public static boolean canSwitchPeriod(Object source, Object context, Object orjComp) {
/*  971 */     ILbsComponentHelper handler = getHandler();
/*  972 */     if (handler != null)
/*  973 */       return handler.canSwitchPeriod(source, context, orjComp); 
/*  974 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void switchPeriod(Object source, Object context, Object orjComp) {
/*  979 */     ILbsComponentHelper handler = getHandler();
/*  980 */     if (handler != null) {
/*  981 */       handler.switchPeriod(source, context, orjComp);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void defineShortcut(Object source, Object context) {
/*  986 */     ILbsComponentHelper handler = getHandler();
/*  987 */     if (handler != null) {
/*  988 */       handler.defineShortcut(source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void revisionHistory(Object source, Object context) {
/*  993 */     ILbsComponentHelper handler = getHandler();
/*  994 */     if (handler != null)
/*      */     {
/*  996 */       handler.revisionHistory(source, context);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void createBookmarkURL(Object source, Object context) {
/* 1003 */     ILbsComponentHelper handler = getHandler();
/* 1004 */     if (handler != null) {
/* 1005 */       handler.createBookmarkURL(source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static String getFilterTypeText(int type) {
/* 1010 */     ILbsComponentHelper handler = getHandler();
/* 1011 */     if (handler == null) {
/* 1012 */       return null;
/*      */     }
/* 1014 */     return handler.getFilterTypeText(type);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isObjectGrid(ILbsGridComponent component) {
/* 1019 */     ILbsComponentHelper handler = getHandler();
/* 1020 */     if (handler != null)
/* 1021 */       return handler.isObjectGrid(component); 
/* 1022 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void customize(Object root, Object source, Object context) {
/* 1027 */     ILbsComponentHelper handler = getHandler();
/* 1028 */     if (handler != null) {
/* 1029 */       handler.customize(root, source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void rowColoring(TableReportInfo reportInfo, boolean visual) {
/* 1034 */     ILbsComponentHelper handler = getHandler();
/* 1035 */     if (handler != null)
/*      */     {
/* 1037 */       handler.rowColoring(reportInfo, visual);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void rowColoring(Object sourceComp, Object context, ILbsComponentBase orjComp) {
/* 1043 */     ILbsComponentHelper handler = getHandler();
/* 1044 */     if (handler != null) {
/* 1045 */       handler.rowColoring(sourceComp, context, orjComp);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void setXUIShortcutEnforcer(Object xUIShortcutEnforcer) {
/* 1050 */     (JLbsComponentHelperFieldHolder.getInstance()).ms_XUIShortcutEnforcer = xUIShortcutEnforcer;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object getXUIShortcutEnforcer() {
/* 1055 */     return (JLbsComponentHelperFieldHolder.getInstance()).ms_XUIShortcutEnforcer;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void ensurePopupMenuExist(Object source) {
/* 1060 */     ILbsComponentHelper handler = getHandler();
/* 1061 */     if (handler != null) {
/* 1062 */       handler.ensurePopupMenuExist(source);
/*      */     }
/*      */   }
/*      */   
/*      */   public static boolean hasXUIPane(Object source) {
/* 1067 */     ILbsComponentHelper handler = getHandler();
/* 1068 */     if (handler != null)
/* 1069 */       return handler.hasXUIPane(source); 
/* 1070 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean hasRevisionHistory(Object source) {
/* 1075 */     ILbsComponentHelper handler = getHandler();
/* 1076 */     if (handler != null)
/* 1077 */       return handler.hasRevisionHistory(source); 
/* 1078 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean canAppendTemplateMenu() {
/* 1083 */     ILbsComponentHelper handler = getHandler();
/* 1084 */     if (handler != null)
/* 1085 */       return handler.canAppendTemplateMenu(); 
/* 1086 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean hasBookmarkSupport(Object source) {
/* 1091 */     ILbsComponentHelper handler = getHandler();
/* 1092 */     if (handler != null)
/* 1093 */       return handler.hasBookmarkSupport(source); 
/* 1094 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean hasBookmarkURLRight(Object source) {
/* 1099 */     ILbsComponentHelper handler = getHandler();
/* 1100 */     if (handler != null)
/* 1101 */       return handler.hasBookmarkURLRight(source); 
/* 1102 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public static ILbsComponent getComponent(JComponent comp) {
/* 1107 */     if (comp instanceof ILbsComponent)
/* 1108 */       return (ILbsComponent)comp; 
/* 1109 */     return new JLbsComponentWrapper(comp);
/*      */   }
/*      */ 
/*      */   
/*      */   public static ILbsInvokeHandler getInvokeHandler(Object source) {
/* 1114 */     if (source instanceof LbsComponentEmulator) {
/*      */       
/* 1116 */       LbsComponentEmulator comp = (LbsComponentEmulator)source;
/* 1117 */       LbsFocusManager focusManager = comp.getFocusManager();
/* 1118 */       if (focusManager != null) {
/* 1119 */         return focusManager.getInvokeHandler();
/*      */       }
/*      */     } 
/* 1122 */     if (source instanceof ILbsInvokeHandlerOwner) {
/* 1123 */       return ((ILbsInvokeHandlerOwner)source).getInvokeHandler();
/*      */     }
/* 1125 */     ILbsComponentHelper handler = getHandler();
/* 1126 */     if (handler != null) {
/* 1127 */       return handler.getInvokeHandler(source);
/*      */     }
/* 1129 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object getPropertyValue(Object obj, String propertyName) throws Exception {
/* 1134 */     ILbsComponentHelper handler = getHandler();
/* 1135 */     if (handler != null)
/* 1136 */       return handler.getPropertyValue(obj, propertyName); 
/* 1137 */     throw new Exception("Cannot get property value: Handler is null!");
/*      */   }
/*      */ 
/*      */   
/*      */   public static void loadJFreeChartLib(Object context) {
/* 1142 */     ILbsComponentHelper handler = getHandler();
/* 1143 */     if (handler != null) {
/* 1144 */       handler.loadJFreeChartLib(context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static TableReportInfo createTableReportInfo(TableReportInfo reportInfo) {
/* 1149 */     ILbsComponentHelper handler = getHandler();
/* 1150 */     if (handler != null)
/* 1151 */       return handler.createTableReportInfo(reportInfo); 
/* 1152 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static PivotTableInfo createPivotTableInfo(PivotTableInfo reportInfo) {
/* 1157 */     ILbsComponentHelper handler = getHandler();
/* 1158 */     if (handler != null)
/* 1159 */       return handler.createPivotTableInfo(reportInfo); 
/* 1160 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static TableReportInfo createTableReportInfo(Object context, ILbsComponentBase orjComp) {
/* 1165 */     ILbsComponentHelper handler = getHandler();
/* 1166 */     if (handler != null)
/* 1167 */       return handler.createTableReportInfo(context, orjComp); 
/* 1168 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static PivotTableInfo createPivotTableInfo(Object context, ILbsComponentBase orjComp, boolean allRows, PivotExtraColumn[] extraColumns) {
/* 1174 */     ILbsComponentHelper handler = getHandler();
/* 1175 */     if (handler != null)
/* 1176 */       return handler.createPivotTableInfo(context, orjComp, allRows, extraColumns); 
/* 1177 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static PivotTableInfo createPivotTableInfo(Object context, String queryName) {
/* 1182 */     return createPivotTableInfo(context, null, queryName);
/*      */   }
/*      */ 
/*      */   
/*      */   public static PivotTableInfo createPivotTableInfo(Object context, PivotViewPreferences pivotViewPreferences, String queryName) {
/* 1187 */     ILbsComponentHelper handler = getHandler();
/* 1188 */     if (handler != null)
/* 1189 */       return handler.createPivotTableInfo(context, pivotViewPreferences, queryName); 
/* 1190 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean createPivotTableTab(Object context, ILbsComponentBase orjComp, boolean allRows) {
/* 1195 */     ILbsComponentHelper handler = getHandler();
/* 1196 */     if (handler != null)
/* 1197 */       return handler.createPivotTableTab(context, orjComp, allRows); 
/* 1198 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void saveTableReportPreferences(Object context, TableReportPreferences reportPreferences) throws Exception {
/* 1203 */     ILbsComponentHelper handler = getHandler();
/* 1204 */     if (handler != null) {
/* 1205 */       handler.saveTableReportPreferences(context, reportPreferences);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void savePivotViewPreferences(Object context, Object reportWrapper, PivotViewPreferences viewPrefs) throws Exception {
/* 1211 */     ILbsComponentHelper handler = getHandler();
/* 1212 */     if (handler != null) {
/* 1213 */       handler.savePivotViewPreferences(context, reportWrapper, viewPrefs);
/*      */     }
/*      */   }
/*      */   
/*      */   public static PivotViewPreferences getPivotViewPreferences(Object context, String queryName) {
/* 1218 */     ILbsComponentHelper handler = getHandler();
/* 1219 */     if (handler != null)
/* 1220 */       return handler.getPivotViewPreferences(context, queryName); 
/* 1221 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void exportPivotToExcel(Object context, DefaultTableModel rowHeaderModel, DefaultTableModel columnHeaderModel, DefaultTableModel model, File file) {
/* 1227 */     ILbsComponentHelper handler = getHandler();
/* 1228 */     if (handler != null) {
/* 1229 */       handler.exportPivotToExcel(context, rowHeaderModel, columnHeaderModel, model, file);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void exportTableReportToExcel(Object context, TableReportPanel reportPanel, File file) {
/* 1234 */     ILbsComponentHelper handler = getHandler();
/* 1235 */     if (handler != null) {
/* 1236 */       handler.exportTableReportToExcel(context, reportPanel, file);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void showLdapBrowser(Object source, boolean importOp, Object context) {
/* 1241 */     ILbsComponentHelper handler = getHandler();
/* 1242 */     if (handler != null) {
/* 1243 */       handler.showLdapBrowser(source, importOp, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void showAzureUserBrowser(Object source, Object context) {
/* 1248 */     ILbsComponentHelper handler = getHandler();
/* 1249 */     if (handler != null) {
/* 1250 */       handler.showAzureUserBrowser(source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void showAzureUserFilter(Object source, Object context) {
/* 1255 */     ILbsComponentHelper handler = getHandler();
/* 1256 */     if (handler != null) {
/* 1257 */       handler.showAzureUserFilter(source, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static boolean hasPolicyLimit(Object queryGrid, int actMask) {
/* 1262 */     ILbsComponentHelper handler = getHandler();
/* 1263 */     if (handler != null)
/* 1264 */       return handler.hasPolicyLimit(queryGrid, actMask); 
/* 1265 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public static ICacheManager getCacheManager() {
/* 1270 */     ILbsComponentHelper handler = getHandler();
/* 1271 */     if (handler != null)
/* 1272 */       return handler.getCacheManager(); 
/* 1273 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void invalidDataGridSelection() {
/* 1278 */     ILbsComponentHelper handler = getHandler();
/* 1279 */     if (handler != null) {
/* 1280 */       handler.invalidDataGridSelection();
/*      */     }
/*      */   }
/*      */   
/*      */   public static Object createAbstractClassInstance(Class<?> c) {
/* 1285 */     ILbsComponentHelper handler = getHandler();
/* 1286 */     if (handler != null)
/* 1287 */       return handler.createAbstractClassInstance(c); 
/* 1288 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void treeGridExpand(ILbsGridComponent source) {
/* 1293 */     ILbsComponentHelper handler = getHandler();
/* 1294 */     if (handler != null) {
/* 1295 */       handler.treeGridExpand(source);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void treeGridCollapse(ILbsGridComponent source) {
/* 1300 */     ILbsComponentHelper handler = getHandler();
/* 1301 */     if (handler != null) {
/* 1302 */       handler.treeGridCollapse(source);
/*      */     }
/*      */   }
/*      */   
/*      */   public static int getColumnIndex(ILbsComponentBase comp, int colID) {
/* 1307 */     ILbsComponentHelper handler = getHandler();
/* 1308 */     if (handler == null) {
/* 1309 */       return -1;
/*      */     }
/* 1311 */     return handler.getColumnIndex(comp, colID);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void takeScreenShot(Container window) {
/* 1316 */     ILbsComponentHelper handler = getHandler();
/* 1317 */     if (handler == null)
/*      */       return; 
/* 1319 */     handler.takeScreenShot(window);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean canTakeScreenShot() {
/* 1324 */     ILbsComponentHelper handler = getHandler();
/* 1325 */     if (handler == null)
/* 1326 */       return false; 
/* 1327 */     return handler.canTakeScreenShot();
/*      */   }
/*      */ 
/*      */   
/*      */   public static ILbsTestPlayerWrapper createTestPlayerWrapper() throws Exception {
/* 1332 */     Class<?> tpwClass = Class.forName("com.lbs.test.scripting.JLbsTestPlayerWrapper");
/* 1333 */     ILbsTestPlayerWrapper tpw = (ILbsTestPlayerWrapper)tpwClass.newInstance();
/* 1334 */     return tpw;
/*      */   }
/*      */ 
/*      */   
/*      */   public static ILogger getLogger(String loggerName) {
/* 1339 */     ILbsComponentHelper handler = getHandler();
/* 1340 */     if (handler == null)
/* 1341 */       return new ILogger()
/*      */         {
/*      */ 
/*      */           
/*      */           public void warn(Throwable t)
/*      */           {
/* 1347 */             if (JLbsConstants.DEBUG) {
/* 1348 */               t.printStackTrace();
/*      */             }
/*      */           }
/*      */ 
/*      */           
/*      */           public void warn(Object message, Throwable t) {
/* 1354 */             if (JLbsConstants.DEBUG) {
/*      */               
/* 1356 */               System.out.println(message);
/* 1357 */               t.printStackTrace();
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public void warn(Object message) {
/* 1364 */             if (JLbsConstants.DEBUG) {
/* 1365 */               System.out.println(message);
/*      */             }
/*      */           }
/*      */ 
/*      */           
/*      */           public void trace(Throwable t) {
/* 1371 */             if (JLbsConstants.DEBUG) {
/* 1372 */               t.printStackTrace();
/*      */             }
/*      */           }
/*      */ 
/*      */           
/*      */           public void trace(Object message, Throwable t) {
/* 1378 */             if (JLbsConstants.DEBUG) {
/*      */               
/* 1380 */               System.out.println(message);
/* 1381 */               t.printStackTrace();
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public void trace(Object message) {
/* 1388 */             if (JLbsConstants.DEBUG) {
/* 1389 */               System.out.println(message);
/*      */             }
/*      */           }
/*      */ 
/*      */           
/*      */           public void info(Throwable t) {
/* 1395 */             if (JLbsConstants.DEBUG) {
/* 1396 */               t.printStackTrace();
/*      */             }
/*      */           }
/*      */ 
/*      */           
/*      */           public void info(Object message, Throwable t) {
/* 1402 */             if (JLbsConstants.DEBUG) {
/*      */               
/* 1404 */               System.out.println(message);
/* 1405 */               t.printStackTrace();
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public void info(Object message) {
/* 1412 */             if (JLbsConstants.DEBUG) {
/* 1413 */               System.out.println(message);
/*      */             }
/*      */           }
/*      */ 
/*      */           
/*      */           public void fatal(Throwable t) {
/* 1419 */             t.printStackTrace();
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public void fatal(Object message, Throwable t) {
/* 1425 */             System.out.println(message);
/* 1426 */             t.printStackTrace();
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public void fatal(Object message) {
/* 1432 */             System.out.println(message);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public void error(Throwable t) {
/* 1438 */             t.printStackTrace();
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public void error(Object message, Throwable t) {
/* 1444 */             System.out.println(message);
/* 1445 */             t.printStackTrace();
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public void error(Object message) {
/* 1451 */             System.out.println(message);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public void debug(Throwable t) {
/* 1457 */             if (JLbsConstants.DEBUG) {
/* 1458 */               t.printStackTrace();
/*      */             }
/*      */           }
/*      */ 
/*      */           
/*      */           public void debug(Object message, Throwable t) {
/* 1464 */             if (JLbsConstants.DEBUG) {
/*      */               
/* 1466 */               System.out.println(message);
/* 1467 */               t.printStackTrace();
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public void debug(Object message) {
/* 1474 */             if (JLbsConstants.DEBUG) {
/* 1475 */               System.out.println(message);
/*      */             }
/*      */           }
/*      */         }; 
/* 1479 */     return handler.getLogger(loggerName);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setCurrentContext(Object context) {
/* 1484 */     ClientContextHolder.getInstance().setClientContext(context);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object getCurrentContext() {
/* 1489 */     return ClientContextHolder.getInstance().getClientContext();
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isUserRestricted() {
/* 1494 */     ILbsComponentHelper handler = getHandler();
/* 1495 */     if (handler == null)
/* 1496 */       return false; 
/* 1497 */     if (getCurrentContext() == null)
/* 1498 */       return false; 
/* 1499 */     return handler.isUserRestricted(getCurrentContext());
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object getXUIPane(ILbsComponentBase comp) {
/* 1504 */     ILbsComponentHelper handler = getHandler();
/* 1505 */     if (handler == null || comp == null)
/* 1506 */       return null; 
/* 1507 */     return handler.getXUIPane(comp);
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getBaseFormName(Object xuiPane, boolean bLowerCase) {
/* 1512 */     ILbsComponentHelper handler = getHandler();
/* 1513 */     if (handler == null)
/* 1514 */       return null; 
/* 1515 */     return handler.getBaseFormName(xuiPane, bLowerCase);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getPopupItemUsagesCount(Object source, String keyStr) {
/* 1520 */     ILbsComponentHelper handler = getHandler();
/* 1521 */     if (handler == null)
/* 1522 */       return 0; 
/* 1523 */     return handler.getPopupItemUsagesCount(source, keyStr);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setPopupItemUsagesStr(Object source, String keyStr, int keyValue) {
/* 1528 */     ILbsComponentHelper handler = getHandler();
/* 1529 */     if (handler != null && !JLbsConstants.EVENT_RECORDING && !JLbsConstants.TEST_PLAYING) {
/* 1530 */       handler.setPopupItemUsagesStr(source, keyStr, keyValue);
/*      */     }
/*      */   }
/*      */   
/*      */   public static String getParentPopupTagConstant() {
/* 1535 */     ILbsComponentHelper handler = getHandler();
/* 1536 */     if (handler == null)
/* 1537 */       return ""; 
/* 1538 */     return handler.getParentPopupTagConstant();
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getFormName(Object source) {
/* 1543 */     ILbsComponentHelper handler = getHandler();
/* 1544 */     if (handler == null)
/* 1545 */       return null; 
/* 1546 */     return handler.getFormName(source);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void addPopupItemToFavorites(Object source, JLbsPopupMenuItem popupMenuItem) {
/* 1551 */     ILbsComponentHelper handler = getHandler();
/* 1552 */     if (handler != null && !JLbsConstants.EVENT_RECORDING && !JLbsConstants.TEST_PLAYING) {
/* 1553 */       handler.addPopupItemToFavorites(source, popupMenuItem);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void insertRowToListGrid(Object sourceComp, Object m_Context) {
/* 1558 */     ILbsComponentHelper handler = getHandler();
/* 1559 */     if (handler != null) {
/* 1560 */       handler.insertRowToListGrid(sourceComp, m_Context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void appendRowToListGrid(Object sourceComp, Object m_Context) {
/* 1565 */     ILbsComponentHelper handler = getHandler();
/* 1566 */     if (handler != null) {
/* 1567 */       handler.appendRowToListGrid(sourceComp, m_Context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void deleteRowToListGrid(Object sourceComp, Object m_Context) {
/* 1572 */     ILbsComponentHelper handler = getHandler();
/* 1573 */     if (handler != null) {
/* 1574 */       handler.deleteRowToListGrid(sourceComp, m_Context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static ArrayList<Integer> getDataExportOptionsRights(int minTag, int maxTag) {
/* 1579 */     ILbsComponentHelper handler = getHandler();
/* 1580 */     if (handler == null)
/* 1581 */       return null; 
/* 1582 */     return handler.getDataExportOptionsRights(minTag, maxTag);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setHazelcastConfigPath(String configPath) {
/* 1587 */     (JLbsComponentHelperFieldHolder.getInstance()).ms_HazelcastConfigPath = String.valueOf(configPath) + File.separator + "hazelcast.xml";
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getHazelcastConfigPath() {
/* 1592 */     return (JLbsComponentHelperFieldHolder.getInstance()).ms_HazelcastConfigPath;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isWFReport() {
/* 1597 */     Boolean value = (JLbsComponentHelperFieldHolder.getInstance()).ms_WFReport.get();
/* 1598 */     if (value != null)
/* 1599 */       return value.booleanValue(); 
/* 1600 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setWFReport(boolean wfReport) {
/* 1605 */     (JLbsComponentHelperFieldHolder.getInstance()).ms_WFReport.set(Boolean.valueOf(wfReport));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isToggleAction(ILbsComponentBase component) {
/* 1610 */     ILbsComponentHelper handler = getHandler();
/* 1611 */     if (handler != null)
/* 1612 */       return handler.isToggleAction(component); 
/* 1613 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void doToggleAction(Object source, Object context) {
/* 1618 */     ILbsComponentHelper handler = getHandler();
/* 1619 */     if (handler != null) {
/* 1620 */       handler.doToggleAction(source, context);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void doDeselectToggleAction(Object sourceComp, Object context) {
/* 1626 */     ILbsComponentHelper handler = getHandler();
/* 1627 */     if (handler != null) {
/* 1628 */       handler.doDeselectToggleAction(sourceComp, context);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void showWorkflowStatus(Object sourceComp, Object context) {
/* 1634 */     ILbsComponentHelper handler = getHandler();
/* 1635 */     if (handler != null) {
/* 1636 */       handler.showWorkflowStatus(sourceComp, context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static boolean isQueryGridMultiSelected(ILbsComponentBase component) {
/* 1641 */     ILbsComponentHelper handler = getHandler();
/* 1642 */     if (handler != null) {
/* 1643 */       return handler.isQueryGridMultiSelected(component);
/*      */     }
/* 1645 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void doDisableButton(Object source, boolean enabled) {
/* 1650 */     ILbsComponentHelper handler = getHandler();
/* 1651 */     if (handler != null) {
/* 1652 */       handler.doDisableButton(source, enabled);
/*      */     }
/*      */   }
/*      */   
/*      */   public static class JLbsComponentHelperFieldHolder
/*      */   {
/*      */     private IXUIFormTemplateManager ms_FormTemplateManager;
/*      */     private Object ms_FormPropertyRecorder;
/*      */     private Object ms_MainForm;
/*      */     private WeakReference<Object> ms_CurrentContainer;
/*      */     
/*      */     public JLbsComponentHelperFieldHolder() {
/* 1664 */       this.ms_WFReport = new ThreadLocal<>();
/*      */     }
/*      */     private Object ms_XUIShortcutEnforcer; private String ms_HazelcastConfigPath; private Map<Integer, FilterLookupEntry> ms_MapFilterLookupEnrties; private ThreadLocal<Boolean> ms_WFReport; private ILbsComponentHelper ms_Handler;
/*      */     
/*      */     private static JLbsComponentHelperFieldHolder getInstance() {
/* 1669 */       return (JLbsComponentHelperFieldHolder)LbsClassInstanceProvider.getInstanceByClass(JLbsComponentHelperFieldHolder.class);
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsComponentHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */