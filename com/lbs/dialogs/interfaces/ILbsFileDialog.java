package com.lbs.dialogs.interfaces;

import java.awt.Component;
import java.io.File;
import javax.swing.filechooser.FileFilter;

public interface ILbsFileDialog {
  void setDialogTitle(String paramString);
  
  void setDialogType(int paramInt);
  
  void setMultiSelectionEnabled(boolean paramBoolean);
  
  void setFileSelectionMode(int paramInt);
  
  void setApproveButtonText(String paramString);
  
  void setApproveButtonMnemonic(char paramChar);
  
  void setSelectedFile(File paramFile);
  
  void setSelectedFiles(File[] paramArrayOfFile);
  
  File getSelectedFile();
  
  File[] getSelectedFiles();
  
  void setCurrentDirectory(File paramFile);
  
  File getCurrentDirectory();
  
  void setFileFilter(FileFilter paramFileFilter);
  
  void setAcceptAllFileFilterUsed(boolean paramBoolean);
  
  void addChoosableFileFilter(FileFilter paramFileFilter);
  
  int showDialog(Component paramComponent, String paramString);
  
  int showOpenDialog(Component paramComponent);
  
  int showSaveDialog(Component paramComponent);
  
  default void beforeDownload(File selectedFile) {}
  
  default void downloadFile(File selectedFile) {}
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\dialogs\interfaces\ILbsFileDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */