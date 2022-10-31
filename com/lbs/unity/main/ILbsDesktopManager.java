package com.lbs.unity.main;

import java.awt.Frame;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;

public interface ILbsDesktopManager extends WindowListener {
  JComponent getAllDesktop();
  
  JDesktopPane getActiveDesktop();
  
  void shortcutActionPerformed(String paramString, JComponent paramJComponent);
  
  Frame getMainFrame();
  
  void loadFromXml(String paramString, int paramInt);
  
  void loadFromFile(File paramFile);
  
  void showSlide();
  
  void closeSlide();
  
  void slideForward();
  
  void slideBackward();
  
  ArrayList<?> getDesktops();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\unity\main\ILbsDesktopManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */