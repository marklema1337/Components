package com.lbs.control.interfaces;

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;
import javax.swing.ListModel;

public interface ILbsListBox extends ILbsComponent {
  int getSelectionMode();
  
  void setSelectionMode(int paramInt);
  
  void setSelectedIndex(int paramInt);
  
  void setModel(ListModel paramListModel);
  
  Object getSelectedValue();
  
  void setSelectedValue(Object paramObject, boolean paramBoolean);
  
  void setLayoutOrientation(int paramInt);
  
  int getLayoutOrientation();
  
  int getSelectedIndex();
  
  ListModel getModel();
  
  void ensureIndexIsVisible(int paramInt);
  
  int addItem(String paramString);
  
  int addItem(String paramString, int paramInt);
  
  boolean clearItems();
  
  boolean getShowTooltips();
  
  void setSelection(int paramInt);
  
  void setShowTooltips(boolean paramBoolean);
  
  void addSelectionInterval(int paramInt1, int paramInt2);
  
  void clearSelection();
  
  int getAnchorSelectionIndex();
  
  boolean getDragEnabled();
  
  void setDragEnabled(boolean paramBoolean);
  
  int getFirstVisibleIndex();
  
  int getFixedCellHeight();
  
  void setFixedCellHeight(int paramInt);
  
  int getFixedCellWidth();
  
  void setFixedCellWidth(int paramInt);
  
  int getLastVisibleIndex();
  
  int getLeadSelectionIndex();
  
  int getMaxSelectionIndex();
  
  int getMinSelectionIndex();
  
  int[] getSelectedIndices();
  
  void setSelectedIndices(int[] paramArrayOfint);
  
  Object[] getSelectedValues();
  
  Color getSelectionBackground();
  
  void setSelectionBackground(Color paramColor);
  
  Color getSelectionForeground();
  
  void setSelectionForeground(Color paramColor);
  
  boolean getValueIsAdjusting();
  
  void setValueIsAdjusting(boolean paramBoolean);
  
  int getVisibleRowCount();
  
  void setVisibleRowCount(int paramInt);
  
  Point indexToLocation(int paramInt);
  
  boolean isSelectedIndex(int paramInt);
  
  boolean isSelectionEmpty();
  
  int locationToIndex(Point paramPoint);
  
  void setListData(Object[] paramArrayOfObject);
  
  void setListData(Vector paramVector);
  
  void setSelectionInterval(int paramInt1, int paramInt2);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsListBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */