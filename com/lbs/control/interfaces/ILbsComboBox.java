package com.lbs.control.interfaces;

import com.lbs.controls.ILbsComboFilterListener;
import com.lbs.controls.JLbsComboBoxItem;
import com.lbs.util.JLbsStringList;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public interface ILbsComboBox extends ILbsComponent, ILbsActionTarget {
  public static final int EMPTYTAG = 0;
  
  void addItem(Object paramObject);
  
  void addItem(Object paramObject, int paramInt);
  
  void addItem(Object paramObject, int paramInt, ImageIcon paramImageIcon);
  
  void addItems(JLbsStringList paramJLbsStringList);
  
  void setItems(JLbsStringList paramJLbsStringList);
  
  void clearItems();
  
  void doFilterItems();
  
  int findItemIndex(Object paramObject);
  
  int findItemIndex(String paramString);
  
  Object[] getItemlist();
  
  boolean setSelectedItemTag(int paramInt);
  
  int getSelectedItemTag();
  
  Object getSelectedItemValue();
  
  int getItemCount();
  
  Object getItemAt(int paramInt);
  
  Object getItemTagValue(int paramInt);
  
  int getSelectedIndex();
  
  void setSelectedIndex(int paramInt);
  
  String getItemTagString(int paramInt);
  
  JLbsComboBoxItem getItemTagObject(int paramInt);
  
  int getTagOfItem(Object paramObject);
  
  boolean hasItemTag(int paramInt);
  
  boolean setSelectedItem(String paramString);
  
  Object getSelectedItem();
  
  void setSelectedItem(Object paramObject);
  
  void setFilterListener(ILbsComboFilterListener paramILbsComboFilterListener);
  
  ILbsComboFilterListener getFilterListener();
  
  int setSelectedItemValue(Object paramObject);
  
  ArrayList getItems();
  
  String getItemsSList();
  
  void addItemListener(ItemListener paramItemListener);
  
  void removeItemListener(ItemListener paramItemListener);
  
  void setMaximumRowCount(int paramInt);
  
  int getMaximumRowCount();
  
  boolean isEditable();
  
  void setEditable(boolean paramBoolean);
  
  void setEditableForAutoComplete(boolean paramBoolean);
  
  void removeAllItems();
  
  void removeItemAt(int paramInt);
  
  String getActionCommand();
  
  void setActionCommand(String paramString);
  
  boolean isPopupVisible();
  
  void setPopupVisible(boolean paramBoolean);
  
  int getCurrentCaretPosition();
  
  void setCurrentCaretPosition(int paramInt);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsComboBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */