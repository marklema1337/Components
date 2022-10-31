package com.lbs.control.interfaces;

import com.lbs.controls.ILbsFocusProvider;
import com.lbs.controls.groupbox.ILbsButtonGroupListener;
import com.lbs.util.JLbsStringList;
import info.clearthought.layout.TableLayoutConstraints;

public interface ILbsMultiColGroupBox extends ILbsGroupBox, ILbsFocusProvider {
  public static final int EMPTYTAG = 0;
  
  int getSelectedItemMask();
  
  void setSelectedItemMask(int paramInt);
  
  void setSelectedItemMaskWithSameTag(int paramInt);
  
  int[] getSelectedItemArray();
  
  int[] getSelectedItemIndexes();
  
  JLbsStringList getItems();
  
  void setItems(JLbsStringList paramJLbsStringList);
  
  void clearItems();
  
  void addItems(JLbsStringList paramJLbsStringList);
  
  void setSelectedItemArray(int[] paramArrayOfint);
  
  boolean setControlEnabled(int paramInt, boolean paramBoolean);
  
  ILbsComponent getControlByTag(int paramInt);
  
  void setColumnCount(int paramInt);
  
  int getColumnCount();
  
  void addItem(Object paramObject);
  
  void addItem(Object paramObject, int paramInt);
  
  void addItem(Object paramObject, int paramInt, TableLayoutConstraints paramTableLayoutConstraints);
  
  void addItem(Object paramObject, TableLayoutConstraints paramTableLayoutConstraints);
  
  void setButtonGroupListener(ILbsButtonGroupListener paramILbsButtonGroupListener);
  
  ILbsButtonGroupListener getButtonGroupListener();
  
  void setSelectedControl(ILbsComponent paramILbsComponent, boolean paramBoolean);
  
  JLbsStringList getVisibleItems();
  
  void setLayout();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsMultiColGroupBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */