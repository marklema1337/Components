package com.lbs.recording.interfaces;

import com.lbs.controls.JLbsButton;
import com.lbs.controls.JLbsCheckBox;
import com.lbs.controls.JLbsComboBox;
import com.lbs.controls.JLbsComboEdit;
import com.lbs.controls.JLbsMenuButton;
import com.lbs.controls.JLbsRadioButton;
import com.lbs.controls.JLbsTabbedPane;
import com.lbs.controls.datedit.JLbsDateEdit;
import com.lbs.controls.datedit.JLbsDateEditWithCalendar;
import com.lbs.controls.datedit.JLbsTimeEdit;
import com.lbs.controls.maskededit.JLbsMaskedEdit;
import com.lbs.controls.maskededit.JLbsTextEdit;
import com.lbs.controls.menu.JLbsPopupMenu;
import com.lbs.controls.numericedit.JLbsNumericEdit;
import com.lbs.recording.JLbsRecordItem;
import com.lbs.util.JLbsStringList;
import java.io.File;
import java.util.Hashtable;
import javax.swing.JComponent;

public interface ILbsTestPlayerWrapper {
  void setText(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void setCurrentItemContainer(Object paramObject);
  
  void setNumber(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void setDate(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void setTime(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void open(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void close(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void keyPressed(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void mouseClicked(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void setValue(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void lookup(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void setSelected(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void setComboIndex(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void setTabbedPaneIndex(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void setPageIndex(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void selectComponent(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void deselectComponent(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void selectPopUpMenuItem(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void performVerification(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void selectMainMenuItem(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void doubleClickGridCell(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void doubleClickGridCell(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void setComboItemObj(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void setComboItemStr(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void setComboItemTag(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void doubleClickLabel(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void setEditorPaneText(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void setSyntaxEditFormula(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void gridEditLastEditedRow(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void gridShowSearchRow(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void gridApplyHeaderFilters(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void gridScrollPageUp(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void gridScrollPageDown(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void gridScrollLineUp(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void gridScrollLineDown(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void gridScrollTop(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void gridScrollBottom(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void gridHideSearchRow(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void gridDoubleClickLastEditedRow(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void popUpSelectAll(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void popUpDeselectAll(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void popUpInvertSelection(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void selectGridRow(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void doubleClickFilterGridCell(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void showPopUpMenu(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void keyPressed(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void clickGridCell(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void expandGridNode(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void clickButton(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void activateContainer(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void showMessageDialog(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void confirm(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void clickMenuButtonItem(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void clickMenuButton(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void editGridCell(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void verifyGridCellValue(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void gridCellValueVerify(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void insertGridRow(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void appendGridRow(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void deleteGridRow(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void lookupGrid(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void sortGridData(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void selectGrid(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void selectGridCell(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void toggleFilterGridEntry(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void toggleFilterGridSubGroup(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void closeContainer(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void resizeContainer(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void performReportRunDialogAction(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void performFilterSaveDialogAction(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void performFilterSaveRequestFocus(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void performFilterSaveVerifyContent(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void performMsgDialogAction(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void keyPressedMsgDialog(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void checkMsgDialogContent(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  ILbsScriptComponent getScriptComponentByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptComponent getScriptComponentByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt, boolean paramBoolean);
  
  ILbsScriptPopUpMenu getScriptPopUpMenuByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptSyntaxEdit getScriptSyntaxEditByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptButton getScriptButtonByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptMenuButton getScriptMenuButtonByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptGrid getScriptGridByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptTreeGrid getScriptTreeGridByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptFilterGrid getScriptFilterGridByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptComboEdit getScriptComboEditByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptComboBox getScriptComboBoxByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptTabbedPane getScriptTabbedPaneByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptSelectionComp getScriptCheckBoxByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptSelectionGroupComp getScriptCheckBoxGroupByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptSelectionComp getScriptRadioButtonByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptSelectionGroupComp getScriptRadioButtonGroupByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptDateEdit getScriptDateEditByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptNumEditWithCalc getScriptNumEditWithCalcByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptTextArea getScriptTextAreaByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptTextEdit getScriptTextEditByTag(ILbsScriptContainer paramILbsScriptContainer, int paramInt);
  
  ILbsScriptDataPool getCurrentDataPool();
  
  int getCurrentDataPoolID();
  
  ILbsScriptContainer getMainWorkPane();
  
  ILbsScriptTreeGrid getMainMenuTreeGrid();
  
  ILbsScriptTreeGrid getUserMenuTreeGrid();
  
  JComponent getComponentByTag(int paramInt);
  
  Object getControlByTag(int paramInt);
  
  JLbsMaskedEdit getMaskedEditByTag(int paramInt);
  
  JLbsTextEdit getTextEditByTag(int paramInt);
  
  JLbsDateEdit getDateEditByTag(int paramInt);
  
  JLbsNumericEdit getNumericEditByTag(int paramInt);
  
  JLbsTimeEdit getTimeEditByTag(int paramInt);
  
  JLbsTabbedPane getTabbedPaneByTag(int paramInt);
  
  JLbsPopupMenu getPopupMenuByTag(int paramInt);
  
  JLbsButton getButtonByTag(int paramInt);
  
  JLbsComboBox getComboBoxByTag(int paramInt);
  
  JLbsComboEdit getComboEditByTag(int paramInt);
  
  JLbsCheckBox getCheckBoxByTag(int paramInt);
  
  JLbsRadioButton getRadioButtonByTag(int paramInt);
  
  JLbsDateEditWithCalendar getDateEditWithCalendarByTag(int paramInt);
  
  JLbsMenuButton getMenuButtonByTag(int paramInt);
  
  void addExternalReportItem(String paramString);
  
  Hashtable generateFormContentSnapshot();
  
  Hashtable generateFormContentSnapshot(String paramString);
  
  Object getData();
  
  Object getEventHandler();
  
  void loadPaneMessages(ILbsScriptPaneMessageList paramILbsScriptPaneMessageList) throws InterruptedException;
  
  Object getValueFromDataPool(int paramInt);
  
  String getStringValueFromDataPool(int paramInt);
  
  int getIntValueFromDataPool(int paramInt);
  
  double getDoubleValueFromDataPool(int paramInt);
  
  JLbsStringList getStringListValueFromDataPool(int paramInt);
  
  Object getValueFromDataPool(int paramInt1, int paramInt2);
  
  String getStringValueFromDataPool(int paramInt1, int paramInt2);
  
  int getIntValueFromDataPool(int paramInt1, int paramInt2);
  
  double getDoubleValueFromDataPool(int paramInt1, int paramInt2);
  
  JLbsStringList getStringListValueFromDataPool(int paramInt1, int paramInt2);
  
  String getCurrentScriptFilePath();
  
  String getCurrentReportingDir();
  
  void addTestErrorItem(ILbsTestErrorItem paramILbsTestErrorItem);
  
  void addTestErrorItem(ILbsTestErrorItem paramILbsTestErrorItem, int paramInt1, int paramInt2, String paramString, boolean paramBoolean1, boolean paramBoolean2);
  
  void stopPlaying();
  
  String getCurrentContainerTitle();
  
  Object getOpenContainer();
  
  boolean isWindowOpen(String paramString);
  
  void stopAndFinalize();
  
  JLbsRecordItem getCurrentItem();
  
  int getActionType(String paramString);
  
  ILbsTestErrorItem createMessageDialogErrorItem(String paramString1, String paramString2);
  
  int getMessageDialogErrorType();
  
  int getTestFailureStatusType();
  
  void setTestStatus(int paramInt);
  
  void setTestStatus(int paramInt1, int paramInt2, int paramInt3);
  
  void executeLocalScript(String paramString) throws Exception;
  
  void executeScript(String paramString) throws Exception;
  
  void finishUnitTest();
  
  Object getReplacementValueFromDataPool(Object paramObject);
  
  void setGlobalVariable(String paramString, Object paramObject);
  
  void setGlobalVariable(String paramString, int paramInt) throws Exception;
  
  void setGlobalVariable(String paramString, int paramInt1, int paramInt2, int paramInt3) throws Exception;
  
  Object getGlobalVariable(String paramString);
  
  void setPackageVariable(String paramString, Object paramObject);
  
  void setPackageVariable(String paramString, int paramInt) throws Exception;
  
  void setPackageVariable(String paramString, int paramInt1, int paramInt2, int paramInt3) throws Exception;
  
  Object getPackageVariable(String paramString);
  
  void setLocalVariable(String paramString, Object paramObject);
  
  void setLocalVariable(String paramString, int paramInt) throws Exception;
  
  void setLocalVariable(String paramString, int paramInt1, int paramInt2, int paramInt3) throws Exception;
  
  Object getLocalVariable(String paramString);
  
  void executeReport(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3, String paramString4, boolean paramBoolean) throws InterruptedException;
  
  void executePDFReport(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3, String paramString4, boolean paramBoolean) throws InterruptedException;
  
  Object getContext();
  
  boolean isFormSnapshotOverwriteEnabled();
  
  boolean isReportSnapshotOverwriteEnabled();
  
  boolean isReportFiltersSnapshotOverwriteEnabled();
  
  void handleException(Exception paramException);
  
  void resetTimer();
  
  int getCurrentValidationButton();
  
  void setCurrentValidationButton(int paramInt);
  
  void expandFilterGroupNode(int paramInt1, int paramInt2, int paramInt3, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, String paramString) throws InterruptedException;
  
  void setCurrentReportContextParameters(Object paramObject);
  
  Object getCurrentReportContextParameters();
  
  ILbsTestScriptUtil getTestScriptUtil();
  
  void setTestScriptUtil(ILbsTestScriptUtil paramILbsTestScriptUtil);
  
  void executeScript(File paramFile, Object paramObject);
  
  void log(String paramString);
  
  void changeCurrentDataPool(String paramString);
  
  boolean exists(String paramString, String[] paramArrayOfString);
  
  int getStartedBatchID();
  
  int getBatchStatus(int paramInt);
  
  int waitBatch(int paramInt);
  
  String getMainMenuDescByNode(int paramInt);
  
  void setChannelStatisticDebug(boolean paramBoolean);
  
  void saveChannelStatisticsToExcel(String paramString);
  
  boolean getChannelListenerState();
  
  Object getChannelStatistics();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\interfaces\ILbsTestPlayerWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */