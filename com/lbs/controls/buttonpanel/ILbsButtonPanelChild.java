package com.lbs.controls.buttonpanel;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

public interface ILbsButtonPanelChild {
  void PaintButton(Component paramComponent, Graphics paramGraphics, Rectangle paramRectangle);
  
  boolean PaintButtonHover(Component paramComponent, Graphics paramGraphics, Rectangle paramRectangle);
  
  boolean PaintButtonPressed(Component paramComponent, Graphics paramGraphics, Rectangle paramRectangle);
  
  int getWidth();
  
  void updateUI();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\buttonpanel\ILbsButtonPanelChild.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */