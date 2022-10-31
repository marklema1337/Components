package com.lbs.controls.interfaces.orgchart;

import com.lbs.control.interfaces.ILbsPanel;
import com.lbs.control.interfaces.ILbsSplitPane;
import com.lbs.platform.interfaces.IApplicationContext;

public interface ILbsOrgChartDesigner extends ILbsOrgChartDesignerExtra {
  void setContext(IApplicationContext paramIApplicationContext);
  
  void init();
  
  void setViewMode(boolean paramBoolean);
  
  void focusToRoot();
  
  boolean hasOrgChart();
  
  void refreshOrgChart(boolean paramBoolean);
  
  void updateOrgChartView();
  
  void addContentTo(ILbsSplitPane paramILbsSplitPane, ILbsPanel paramILbsPanel1, ILbsPanel paramILbsPanel2);
  
  void setOperator(ILbsOrgChartDesignerOperator paramILbsOrgChartDesignerOperator);
  
  ILbsOrgChartDesignerOperator getOperator();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\interfaces\orgchart\ILbsOrgChartDesigner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */