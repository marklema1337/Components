package com.lbs.recording.interfaces;

import javax.swing.JComponent;

public interface ILbsScriptContainer {
  ILbsScriptComponent getScriptComponentByTag(int paramInt);
  
  ILbsScriptComponent getScriptComponentByTag(int paramInt, boolean paramBoolean);
  
  ILbsScriptPopUpMenu getScriptPopUpMenuByTag(int paramInt);
  
  ILbsScriptButton getScriptButtonByTag(int paramInt);
  
  ILbsScriptSyntaxEdit getScriptSyntaxEditByTag(int paramInt);
  
  ILbsScriptMenuButton getScriptMenuButtonByTag(int paramInt);
  
  ILbsScriptGrid getScriptGridByTag(int paramInt);
  
  ILbsScriptTreeGrid getScriptTreeGridByTag(int paramInt);
  
  ILbsScriptFilterGrid getScriptFilterGridByTag(int paramInt);
  
  ILbsScriptComboEdit getScriptComboEditByTag(int paramInt);
  
  ILbsScriptComboBox getScriptComboBoxByTag(int paramInt);
  
  ILbsScriptTabbedPane getScriptTabbedPaneByTag(int paramInt);
  
  ILbsScriptSelectionComp getScriptCheckBoxByTag(int paramInt);
  
  ILbsScriptSelectionGroupComp getScriptCheckBoxGroupByTag(int paramInt);
  
  ILbsScriptSelectionComp getScriptRadioButtonByTag(int paramInt);
  
  ILbsScriptSelectionGroupComp getScriptRadioButtonGroupByTag(int paramInt);
  
  ILbsScriptDateEdit getScriptDateEditByTag(int paramInt);
  
  ILbsScriptNumEditWithCalc getScriptNumEditWithCalcByTag(int paramInt);
  
  ILbsScriptTextArea getScriptTextAreaByTag(int paramInt);
  
  ILbsScriptTextEdit getScriptTextEditByTag(int paramInt);
  
  void checkColumnExistance(int paramInt);
  
  void checkColumnExistance(int paramInt1, int paramInt2);
  
  void checkColumnExistanceByGridTag(int paramInt, int[] paramArrayOfint);
  
  void checkColumnExistanceByGridTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkColumnHeaderCaptions(int paramInt);
  
  void checkColumnHeaderCaptions(int paramInt1, int paramInt2);
  
  void checkColumnHeaderCaptionsByGridTag(int paramInt, int[] paramArrayOfint);
  
  void checkColumnHeaderCaptionsByGridTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkColumnOrders(int paramInt);
  
  void checkColumnOrders(int paramInt1, int paramInt2);
  
  void checkColumnOrdersByGridTag(int paramInt, int[] paramArrayOfint);
  
  void checkColumnOrdersByGridTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkColumnPropertyFields(int paramInt);
  
  void checkColumnPropertyFields(int paramInt1, int paramInt2);
  
  void checkColumnPropertyFieldsByGridTag(int paramInt, int[] paramArrayOfint);
  
  void checkColumnPropertyFieldsByGridTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkColumnWidths(int paramInt);
  
  void checkColumnWidths(int paramInt1, int paramInt2);
  
  void checkColumnMandatories(int paramInt);
  
  void checkColumnMandatories(int paramInt1, int paramInt2);
  
  void checkColumnHasLookups(int paramInt);
  
  void checkColumnHasLookups(int paramInt1, int paramInt2);
  
  void checkColumnEditTypes(int paramInt);
  
  void checkColumnEditTypes(int paramInt1, int paramInt2);
  
  void checkColumnWidthsByGridTag(int paramInt, int[] paramArrayOfint);
  
  void checkColumnWidthsByGridTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkColumnMandatoriesByGridTag(int paramInt, int[] paramArrayOfint);
  
  void checkColumnMandatoriesByGridTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkColumnHasLookupsByGridTag(int paramInt, int[] paramArrayOfint);
  
  void checkColumnHasLookupsByGridTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkColumnEditTypeGridTag(int paramInt, int[] paramArrayOfint);
  
  void checkColumnEditTypeGridTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkControlItemListValues(int paramInt);
  
  void checkControlItemListValues(int paramInt1, int paramInt2);
  
  void checkControlItemListValuesByTag(int paramInt, int[] paramArrayOfint);
  
  void checkControlItemListValuesByTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkControlItemListValuesByType(int paramInt, int[] paramArrayOfint);
  
  void checkControlItemListValuesByType(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkControlTitles(int paramInt);
  
  void checkControlTitles(int paramInt1, int paramInt2);
  
  void checkControlTitlesByTag(int paramInt, int[] paramArrayOfint);
  
  void checkControlTitlesByTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkControlTitlesByType(int paramInt, int[] paramArrayOfint);
  
  void checkControlTitlesByType(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkControlValues(int paramInt);
  
  void checkControlValues(int paramInt1, int paramInt2);
  
  void checkControlValuesByTag(int paramInt, int[] paramArrayOfint);
  
  void checkControlValuesByTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkControlValuesByType(int paramInt, int[] paramArrayOfint);
  
  void checkControlValuesByType(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkControlLocations(int paramInt);
  
  void checkControlLocations(int paramInt1, int paramInt2);
  
  void checkControlLocationsByTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkControlLocationsByTag(int paramInt, int[] paramArrayOfint);
  
  void checkControlMandatoryStatus(int paramInt);
  
  void checkControlMandatoryStatus(int paramInt1, int paramInt2);
  
  void checkControlMandatoryStatusByTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkControlMandatoryStatusByTag(int paramInt, int[] paramArrayOfint);
  
  void checkControlSizes(int paramInt);
  
  void checkControlSizes(int paramInt1, int paramInt2);
  
  void checkControlSizesByTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkControlSizesByTag(int paramInt, int[] paramArrayOfint);
  
  void checkControlVisibilities(int paramInt);
  
  void checkControlVisibilities(int paramInt1, int paramInt2);
  
  void checkControlVisibilitiesByTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkControlVisibilitiesByTag(int paramInt, int[] paramArrayOfint);
  
  void checkFilterDescriptions(int paramInt);
  
  void checkFilterDescriptions(int paramInt1, int paramInt2);
  
  void checkFilterDescriptionsByFilterID(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkFilterDescriptionsByFilterID(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfint);
  
  void checkFilterDescriptionsByGridTag(int paramInt, int[] paramArrayOfint);
  
  void checkFilterDescriptionsByGridTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkFilterExistance(int paramInt);
  
  void checkFilterExistance(int paramInt1, int paramInt2);
  
  void checkFilterExistanceByGridTag(int paramInt, int[] paramArrayOfint);
  
  void checkFilterExistanceByGridTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkFilterOrders(int paramInt);
  
  void checkFilterOrders(int paramInt1, int paramInt2);
  
  void checkFilterOrdersByGridTag(int paramInt, int[] paramArrayOfint);
  
  void checkFilterOrdersByGridTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkFormTitle(int paramInt);
  
  void checkFormTitle(int paramInt1, int paramInt2);
  
  void checkMessageListContent(int paramInt);
  
  void checkMessageListContent(int paramInt1, int paramInt2);
  
  void checkGridData(int paramInt);
  
  void checkGridData(int paramInt1, int paramInt2);
  
  void checkGridDataByGridTag(int paramInt, int[] paramArrayOfint);
  
  void checkGridDataByGridTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkControlEnablementStatus(int paramInt);
  
  void checkControlEnablementStatus(int paramInt1, int paramInt2);
  
  void checkControlEnablementStatusByTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkControlEnablementStatusByTag(int paramInt, int[] paramArrayOfint);
  
  void checkFilterItemListValues(int paramInt);
  
  void checkFilterItemListValues(int paramInt1, int paramInt2);
  
  void checkFilterItemListValuesByGridTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkFilterItemListValuesByGridTag(int paramInt, int[] paramArrayOfint);
  
  void checkSelectedFilterItemListValues(int paramInt);
  
  void checkSelectedFilterItemListValues(int paramInt1, int paramInt2);
  
  void checkSelectedFilterItemListValuesByGridTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkSelectedFilterItemListValuesByGridTag(int paramInt, int[] paramArrayOfint);
  
  void checkRowStates(int paramInt);
  
  void checkRowStates(int paramInt1, int paramInt2);
  
  void checkRowStatesByGridTag(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkRowStatesByGridTag(int paramInt, int[] paramArrayOfint);
  
  void compareWithCurrentDateByTag(int paramInt, int[] paramArrayOfint);
  
  void compareWithPeriodStartDateByTag(int paramInt, int[] paramArrayOfint);
  
  void resize(int paramInt1, int paramInt2) throws InterruptedException;
  
  void checkControlExistance(int paramInt);
  
  void checkControlExistance(int paramInt1, int paramInt2);
  
  JComponent getComponentByTag(int paramInt);
  
  JComponent _getComponentByTag(int paramInt);
  
  Object _getControlByTag(int paramInt);
  
  Object _getXUIPane();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\interfaces\ILbsScriptContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */