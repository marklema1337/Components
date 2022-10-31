package com.lbs.control.interfaces;

import com.lbs.controls.ILbsTabbedPaneListener;
import com.lbs.controls.wizard.xui.ILbsXUIWizardPane;
import javax.swing.Icon;
import javax.swing.event.ChangeListener;

public interface ILbsTabbedPane extends ILbsComponent, ILbsXUIWizardPane {
  int getSelectedIndex();
  
  void setSelectedIndex(int paramInt);
  
  int getTabCount();
  
  void addChangeListener(ChangeListener paramChangeListener);
  
  void removeChangeListener(ChangeListener paramChangeListener);
  
  String getTitleAt(int paramInt);
  
  void setTitleAt(int paramInt, String paramString);
  
  void remove(int paramInt);
  
  int getSelectedPageTag();
  
  ILbsTabPage getSelectedPage();
  
  ILbsTabbedPaneListener getTabbedPaneListener();
  
  void setTabbedPaneListener(ILbsTabbedPaneListener paramILbsTabbedPaneListener);
  
  int getTabLayoutPolicy();
  
  void setTabLayoutPolicy(int paramInt);
  
  boolean isEnabledAt(int paramInt);
  
  void removeAll();
  
  void removeTabAt(int paramInt);
  
  void setEnabledAt(int paramInt, boolean paramBoolean);
  
  void addTabPage(String paramString, ILbsComponent paramILbsComponent);
  
  void remove(ILbsComponent paramILbsComponent);
  
  void addTab(String paramString, ILbsTabPage paramILbsTabPage);
  
  void insertTab(String paramString1, Icon paramIcon, Object paramObject, String paramString2, int paramInt);
  
  Object getComponentAt(int paramInt);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsTabbedPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */