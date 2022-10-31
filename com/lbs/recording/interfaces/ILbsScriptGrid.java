package com.lbs.recording.interfaces;

public interface ILbsScriptGrid {
  int findColumnIndex(int paramInt);
  
  void selectRowByValue(String paramString) throws InterruptedException;
  
  void selectRowByValue(int paramInt) throws InterruptedException;
  
  void selectRowByKey(String paramString) throws InterruptedException;
  
  void selectRowBySecondaryKey(String paramString, String[] paramArrayOfString) throws Exception;
  
  void selectRowBySecondaryKey(String paramString, String[] paramArrayOfString, boolean paramBoolean1, boolean paramBoolean2) throws Exception;
  
  void selectRowByKey(int paramInt) throws InterruptedException;
  
  void selectRowByValue(String paramString, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void selectRowByValue(int paramInt, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void selectRowByKey(String paramString, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void selectRowByKey(int paramInt, boolean paramBoolean1, boolean paramBoolean2) throws InterruptedException;
  
  void selectCell(int paramInt1, int paramInt2) throws InterruptedException;
  
  void doubleClick() throws InterruptedException;
  
  void doubleClick(int paramInt1, int paramInt2) throws InterruptedException;
  
  void editCell(int paramInt1, int paramInt2, Object paramObject) throws InterruptedException;
  
  void verifyCellValue(int paramInt1, int paramInt2, Object paramObject) throws InterruptedException;
  
  void cellValueVerify(int paramInt1, int paramInt2, Object paramObject) throws InterruptedException;
  
  void insertRow(int paramInt, boolean paramBoolean) throws InterruptedException;
  
  void appendRow(boolean paramBoolean) throws InterruptedException;
  
  void deleteRow(int paramInt) throws InterruptedException;
  
  void sortItems(int paramInt) throws InterruptedException;
  
  void showSearchRow() throws InterruptedException;
  
  void hideSearchRow() throws InterruptedException;
  
  void applyHeaderFilters() throws InterruptedException;
  
  void scrollPageUp() throws InterruptedException;
  
  void scrollPageDown() throws InterruptedException;
  
  void scrollLineUp() throws InterruptedException;
  
  void scrollLineDown() throws InterruptedException;
  
  void scrollTop() throws InterruptedException;
  
  void scrollBottom() throws InterruptedException;
  
  void lookup(int paramInt1, int paramInt2) throws InterruptedException;
  
  void doubleClickLastEditedRow() throws InterruptedException;
  
  void updateLastEditedRow() throws InterruptedException;
  
  Object getValueAtCell(int paramInt1, int paramInt2) throws InterruptedException;
  
  String getValueAtCellAsString(int paramInt1, int paramInt2) throws InterruptedException;
  
  ILbsScriptGridCell getCell(int paramInt1, int paramInt2) throws InterruptedException;
  
  String getQueryName() throws InterruptedException;
  
  String getOrderName() throws InterruptedException;
  
  String getLookupTerm() throws InterruptedException;
  
  Object getQueryParams() throws InterruptedException;
  
  int getRowCount() throws InterruptedException;
  
  void checkColumnExistance(int paramInt) throws InterruptedException;
  
  void checkColumnExistance(int paramInt1, int paramInt2) throws InterruptedException;
  
  void checkColumnHeaderCaptions(int paramInt) throws InterruptedException;
  
  void checkColumnHeaderCaptions(int paramInt1, int paramInt2) throws InterruptedException;
  
  void checkColumnOrders(int paramInt) throws InterruptedException;
  
  void checkColumnOrders(int paramInt1, int paramInt2) throws InterruptedException;
  
  void checkColumnPropertyFields(int paramInt) throws InterruptedException;
  
  void checkColumnPropertyFields(int paramInt1, int paramInt2) throws InterruptedException;
  
  void checkColumnWidths(int paramInt) throws InterruptedException;
  
  void checkColumnWidths(int paramInt1, int paramInt2) throws InterruptedException;
  
  void checkColumnMandatories(int paramInt) throws InterruptedException;
  
  void checkColumnMandatories(int paramInt1, int paramInt2) throws InterruptedException;
  
  void checkColumnHasLookups(int paramInt) throws InterruptedException;
  
  void checkColumnHasLookups(int paramInt1, int paramInt2) throws InterruptedException;
  
  void checkColumnEditTypes(int paramInt) throws InterruptedException;
  
  void checkColumnEditTypes(int paramInt1, int paramInt2) throws InterruptedException;
  
  void checkData(int paramInt) throws InterruptedException;
  
  void checkData(int paramInt1, int paramInt2) throws InterruptedException;
  
  void checkRowStates(int paramInt) throws InterruptedException;
  
  void checkRowStates(int paramInt1, int paramInt2) throws InterruptedException;
  
  void selectRowByRowIndex(int paramInt) throws InterruptedException;
  
  int getPrimaryKey(int paramInt);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\interfaces\ILbsScriptGrid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */