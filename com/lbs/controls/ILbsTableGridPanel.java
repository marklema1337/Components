package com.lbs.controls;

import com.lbs.control.interfaces.ILbsPanel;
import com.lbs.controls.tablereport.TableReportInfo;
import java.io.InputStream;
import java.io.OutputStream;

public interface ILbsTableGridPanel extends ILbsPanel {
  JLbsPanel getTableGridPanel(TableReportInfo paramTableReportInfo);
  
  void setLayout(InputStream paramInputStream);
  
  void refreshData(TableReportInfo paramTableReportInfo);
  
  OutputStream getLayoutData();
  
  JLbsMenuButton getOptionsMenu();
  
  void loadLayout(InputStream paramInputStream);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\ILbsTableGridPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */