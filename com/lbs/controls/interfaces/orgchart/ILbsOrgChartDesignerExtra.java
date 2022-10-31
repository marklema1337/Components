package com.lbs.controls.interfaces.orgchart;

import javax.swing.tree.TreeModel;

public interface ILbsOrgChartDesignerExtra {
  boolean hasVacantPosition();
  
  boolean isEditing();
  
  void save();
  
  TreeModel getModel();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\interfaces\orgchart\ILbsOrgChartDesignerExtra.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */