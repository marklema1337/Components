package com.lbs.controls;

import com.lbs.control.interfaces.ILbsPanel;
import com.lbs.controls.pivottable.PivotTableInfo;
import java.io.InputStream;
import java.io.OutputStream;

public interface ILbsPivotGridPanel extends ILbsPanel {
  JLbsPanel getPivotPanel(PivotTableInfo paramPivotTableInfo);
  
  void setLayout(InputStream paramInputStream);
  
  void setReportPrinter(ILbsPivotReportPrinter paramILbsPivotReportPrinter);
  
  void refreshData(PivotTableInfo paramPivotTableInfo);
  
  void refreshReportData(PivotTableInfo paramPivotTableInfo);
  
  OutputStream getLayoutData();
  
  JLbsMenuButton getOptionsMenu();
  
  JLbsImageButton getAdvancedButton();
  
  void loadLayout(InputStream paramInputStream);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\ILbsPivotGridPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */