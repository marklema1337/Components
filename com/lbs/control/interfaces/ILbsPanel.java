package com.lbs.control.interfaces;

import java.awt.Image;
import java.io.FileNotFoundException;

public interface ILbsPanel extends ILbsComponent {
  void setHeader(String paramString, IImageSupplier paramIImageSupplier);
  
  boolean isCollapsed();
  
  void collapse();
  
  void expand();
  
  public static interface IImageSupplier {
    Image getImage(String param1String) throws FileNotFoundException;
  }
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */