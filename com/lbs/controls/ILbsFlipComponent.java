package com.lbs.controls;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeListener;

public interface ILbsFlipComponent {
  void paintFlipComponent(Graphics paramGraphics);
  
  ComponentOrientation getComponentOrientation();
  
  int getWidth();
  
  Color getBackground();
  
  int getHeight();
  
  void addPropertyChangeListener(String paramString, PropertyChangeListener paramPropertyChangeListener);
  
  void addComponentListener(ComponentListener paramComponentListener);
  
  boolean isOpaque();
  
  boolean hasSearchFilter();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\ILbsFlipComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */