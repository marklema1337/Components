package com.lbs.controls;

import com.lbs.control.interfaces.ILbsInvokeHandler;
import com.lbs.control.interfaces.ILbsPanel;
import com.lbs.control.interfaces.ILbsPopupMenu;
import com.lbs.controls.menu.JLbsPopupMenuItem;
import com.lbs.controls.pivottable.PivotTableInfo;
import com.lbs.controls.pivottable.PivotViewPreferences;
import com.lbs.controls.tablereport.TableReportInfo;
import com.lbs.controls.tablereport.TableReportPanel;
import com.lbs.controls.tablereport.TableReportPreferences;
import com.lbs.globalization.ILbsCultureInfo;
import com.lbs.mi.defs.FilterLookupEntry;
import com.lbs.platform.interfaces.ICacheManager;
import com.lbs.recording.interfaces.ILbsEventRecorder;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public interface ILbsComponentHelper {
  int getUniqueIdentifier(ILbsComponentBase paramILbsComponentBase);
  
  String getResourceIdentifier(ILbsComponentBase paramILbsComponentBase);
  
  int getTag(ILbsComponentBase paramILbsComponentBase);
  
  int getParentTag(ILbsComponentBase paramILbsComponentBase);
  
  int getType(ILbsComponentBase paramILbsComponentBase);
  
  int getInvokerType(ILbsComponentBase paramILbsComponentBase);
  
  int getAncestorTag(ILbsComponentBase paramILbsComponentBase);
  
  String getTitle(ILbsComponentBase paramILbsComponentBase);
  
  String getTitleForColumn(ILbsComponentBase paramILbsComponentBase, int paramInt1, int paramInt2);
  
  int getAncestorIdentifier(ILbsComponentBase paramILbsComponentBase);
  
  Object getValue(ILbsComponentBase paramILbsComponentBase);
  
  Object getValue(ILbsComponentBase paramILbsComponentBase, int paramInt1, int paramInt2);
  
  String getText(ILbsComponentBase paramILbsComponentBase);
  
  String getPossibleValues(ILbsComponentBase paramILbsComponentBase);
  
  Object getCurrentContainer();
  
  ICacheManager getCacheManager();
  
  void processKeyEvent(KeyEvent paramKeyEvent);
  
  void setCurrentContainer(Object paramObject);
  
  void setPlayerListener(Object paramObject);
  
  Object getPlayerListener();
  
  Object getClassInstance(Object paramObject, String paramString);
  
  boolean canRecordEvent(Object paramObject);
  
  boolean doGridOperations(ILbsGridComponent paramILbsGridComponent, Object paramObject);
  
  boolean doFilterGridOperations(ILbsGridComponent paramILbsGridComponent, Object paramObject);
  
  boolean canCustomizeColumns(ILbsComponentBase paramILbsComponentBase);
  
  boolean resetToDefaultValues(ILbsGridComponent paramILbsGridComponent, Object paramObject);
  
  boolean isSuperUser(Object paramObject);
  
  void clearPreferences(Object paramObject1, Object paramObject2);
  
  void savePreferences(Object paramObject1, Object paramObject2);
  
  void saveGeneralPreferences(Object paramObject1, Object paramObject2);
  
  void saveGeneralPreferencesForAllFirms(Object paramObject1, Object paramObject2);
  
  boolean isQueryGrid(ILbsComponentBase paramILbsComponentBase);
  
  boolean isFilterGrid(ILbsComponentBase paramILbsComponentBase);
  
  boolean isTreeGrid(ILbsComponentBase paramILbsComponentBase);
  
  boolean isEditGrid(ILbsComponentBase paramILbsComponentBase);
  
  boolean isQueryTreeGrid(ILbsComponentBase paramILbsComponentBase);
  
  boolean isQuerySelectionGrid(ILbsComponentBase paramILbsComponentBase);
  
  void showRecordCount(Object paramObject1, Object paramObject2);
  
  void showSelectedRecordCount(Object paramObject1, Object paramObject2);
  
  boolean hasRecord(Object paramObject);
  
  boolean allowtoEditMenu(Object paramObject);
  
  void showRecordInfo(Object paramObject1, Object paramObject2);
  
  boolean hasRecordInfo(Object paramObject);
  
  void showReportRecordInfo(Object paramObject1, Object paramObject2);
  
  void showListReport(Object paramObject1, Object paramObject2);
  
  void showGroupedListReport(Object paramObject1, Object paramObject2);
  
  void doSelectAll(Object paramObject);
  
  void doDeselectAll(Object paramObject);
  
  void doInvertSelection(Object paramObject);
  
  void doSelectWithCount(Object paramObject, int paramInt);
  
  int getGridSelectedRowForInpEditor(ILbsComponentBase paramILbsComponentBase);
  
  int getGridSelectedColumnForInpEditor(ILbsComponentBase paramILbsComponentBase);
  
  int getInpEditorOwnerTag(ILbsComponentBase paramILbsComponentBase);
  
  int getGridTag(ILbsComponentBase paramILbsComponentBase);
  
  void dbRefresh(Object paramObject1, Object paramObject2, Object paramObject3);
  
  boolean canSwitchPeriod(Object paramObject1, Object paramObject2, Object paramObject3);
  
  void switchPeriod(Object paramObject1, Object paramObject2, Object paramObject3);
  
  void defineShortcut(Object paramObject1, Object paramObject2);
  
  void revisionHistory(Object paramObject1, Object paramObject2);
  
  void createBookmarkURL(Object paramObject1, Object paramObject2);
  
  String getFilterTypeText(int paramInt);
  
  boolean isObjectGrid(ILbsGridComponent paramILbsGridComponent);
  
  int getUserCanCustomize(Object paramObject);
  
  int getUserCanNotSeeReportFiltersProperties(Object paramObject);
  
  void customize(Object paramObject1, Object paramObject2, Object paramObject3);
  
  void rowColoring(Object paramObject1, Object paramObject2, Object paramObject3);
  
  boolean verifyCalendarValue(int paramInt, Object paramObject);
  
  boolean verifyCalendarValue(int paramInt, Object paramObject, ILbsCultureInfo paramILbsCultureInfo, boolean paramBoolean);
  
  void resetScreenshotCounter();
  
  void setScreenshotFileDir(String paramString);
  
  void setScreenshotFileName(String paramString);
  
  void setScreenshotFileExtension(String paramString);
  
  void enableScreenshots();
  
  void disableScreenshots();
  
  boolean isLockEnabled(Object paramObject1, Object paramObject2);
  
  void lockRecord(Object paramObject1, Object paramObject2);
  
  void unlockRecord(Object paramObject1, Object paramObject2);
  
  boolean isLocked(Object paramObject1, Object paramObject2);
  
  void ensurePopupMenuExist(Object paramObject);
  
  boolean hasXUIPane(Object paramObject);
  
  boolean hasRevisionHistory(Object paramObject);
  
  ILbsInvokeHandler getInvokeHandler(Object paramObject);
  
  Object getPropertyValue(Object paramObject, String paramString) throws Exception;
  
  boolean hasBookmarkSupport(Object paramObject);
  
  void openExportWizard(Object paramObject1, Object paramObject2);
  
  void openImportWizard(Object paramObject1, Object paramObject2);
  
  void ExportBrwToExcel(Object paramObject1, Object paramObject2);
  
  void openTransferToFirmWizard(Object paramObject1, Object paramObject2);
  
  void openTransferToFirmWizard(Object paramObject1, Object paramObject2, Object paramObject3);
  
  boolean canAppendTemplateMenu();
  
  void showLdapBrowser(Object paramObject1, boolean paramBoolean, Object paramObject2);
  
  void showAzureUserBrowser(Object paramObject1, Object paramObject2);
  
  void showAzureUserFilter(Object paramObject1, Object paramObject2);
  
  TableReportInfo createTableReportInfo(TableReportInfo paramTableReportInfo);
  
  TableReportInfo createTableReportInfo(Object paramObject, ILbsComponentBase paramILbsComponentBase);
  
  PivotTableInfo createPivotTableInfo(Object paramObject, ILbsComponentBase paramILbsComponentBase, boolean paramBoolean, PivotExtraColumn[] paramArrayOfPivotExtraColumn);
  
  PivotTableInfo createPivotTableInfo(Object paramObject, String paramString);
  
  PivotTableInfo createPivotTableInfo(PivotTableInfo paramPivotTableInfo);
  
  PivotTableInfo createPivotTableInfo(Object paramObject, PivotViewPreferences paramPivotViewPreferences, String paramString);
  
  PivotViewPreferences getPivotViewPreferences(Object paramObject, String paramString);
  
  void savePivotViewPreferences(Object paramObject1, Object paramObject2, PivotViewPreferences paramPivotViewPreferences) throws Exception;
  
  void saveTableReportPreferences(Object paramObject, TableReportPreferences paramTableReportPreferences) throws Exception;
  
  void exportPivotToExcel(Object paramObject, DefaultTableModel paramDefaultTableModel1, DefaultTableModel paramDefaultTableModel2, DefaultTableModel paramDefaultTableModel3, File paramFile);
  
  void exportTableReportToExcel(Object paramObject, TableReportPanel paramTableReportPanel, File paramFile);
  
  void loadJFreeChartLib(Object paramObject);
  
  void rowColoring(TableReportInfo paramTableReportInfo, boolean paramBoolean);
  
  boolean hasPolicyLimit(Object paramObject, int paramInt);
  
  void invalidDataGridSelection();
  
  Object createAbstractClassInstance(Class<?> paramClass);
  
  void treeGridExpand(ILbsGridComponent paramILbsGridComponent);
  
  void treeGridCollapse(ILbsGridComponent paramILbsGridComponent);
  
  int getColumnIndex(ILbsComponentBase paramILbsComponentBase, int paramInt);
  
  boolean createPivotTableTab(Object paramObject, ILbsComponentBase paramILbsComponentBase, boolean paramBoolean);
  
  void takeScreenShot(Container paramContainer);
  
  boolean canTakeScreenShot();
  
  ILogger getLogger(String paramString);
  
  boolean isUserRestricted(Object paramObject);
  
  Object getXUIPane(ILbsComponentBase paramILbsComponentBase);
  
  String getBaseFormName(Object paramObject, boolean paramBoolean);
  
  int getPopupItemUsagesCount(Object paramObject, String paramString);
  
  void setPopupItemUsagesStr(Object paramObject, String paramString, int paramInt);
  
  String getParentPopupTagConstant();
  
  String getFormName(Object paramObject);
  
  void addPopupItemToFavorites(Object paramObject, JLbsPopupMenuItem paramJLbsPopupMenuItem);
  
  void insertRowToListGrid(Object paramObject1, Object paramObject2);
  
  void appendRowToListGrid(Object paramObject1, Object paramObject2);
  
  void deleteRowToListGrid(Object paramObject1, Object paramObject2);
  
  ArrayList<Integer> getDataExportOptionsRights(int paramInt1, int paramInt2);
  
  Map getAppIdList();
  
  FilterLookupEntry getAppId(Integer paramInteger);
  
  ILbsEventRecorder getEventRecorder();
  
  boolean setChildrenEnabled(ILbsPanel paramILbsPanel, boolean paramBoolean);
  
  boolean isToggleAction(ILbsComponentBase paramILbsComponentBase);
  
  void doToggleAction(Object paramObject1, Object paramObject2);
  
  void doDeselectToggleAction(Object paramObject1, Object paramObject2);
  
  boolean hasBookmarkURLRight(Object paramObject);
  
  void showWorkflowStatus(Object paramObject1, Object paramObject2);
  
  boolean isQueryGridMultiSelected(ILbsComponentBase paramILbsComponentBase);
  
  void doDisableButton(Object paramObject, boolean paramBoolean);
  
  boolean isQueryMultiSelectionGrid(ILbsComponentBase paramILbsComponentBase);
  
  boolean getPopupItemVisibility(ILbsPopupMenu paramILbsPopupMenu, int paramInt);
  
  boolean canAddAppendMenuItem(Object paramObject);
  
  boolean canAddInsertMenuItem(Object paramObject);
  
  boolean canAddDeleteMenuItem(Object paramObject);
  
  void pasteContent(Object paramObject, String paramString);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\ILbsComponentHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */