package com.lbs.recording.interfaces;

public interface ILbsScriptFilterGrid extends ILbsScriptGrid {
  void clickNode(int paramInt) throws InterruptedException;
  
  void clickNode(int paramInt1, int paramInt2) throws InterruptedException;
  
  void toggleEntry(int paramInt1, int paramInt2, int paramInt3) throws InterruptedException;
  
  void toggleSubGroup(int paramInt) throws InterruptedException;
  
  void doubleClick(int paramInt1, int paramInt2) throws InterruptedException;
  
  void checkFilterDescriptions(int paramInt);
  
  void checkFilterDescriptions(int paramInt1, int paramInt2);
  
  void checkFilterDescriptionsByFilterID(int paramInt, int[] paramArrayOfint);
  
  void checkFilterDescriptionsByFilterID(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void checkFilterExistance(int paramInt);
  
  void checkFilterExistance(int paramInt1, int paramInt2);
  
  void checkFilterOrders(int paramInt);
  
  void checkFilterOrders(int paramInt1, int paramInt2);
  
  void checkFilterItemListValues(int paramInt);
  
  void checkFilterItemListValues(int paramInt1, int paramInt2);
  
  void checkSelectedFilterItemListValues(int paramInt);
  
  void checkSelectedFilterItemListValues(int paramInt1, int paramInt2);
  
  void expandGroupNode(int paramInt) throws InterruptedException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\interfaces\ILbsScriptFilterGrid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */