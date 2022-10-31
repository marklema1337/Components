package com.lbs.control.interfaces;

import java.awt.event.MouseListener;

public interface ILbsLinkLabel extends ILbsLabel {
  void setClickListener(MouseListener paramMouseListener);
  
  MouseListener getClickListener();
  
  void updatePreferredSize();
  
  void updateSize();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsLinkLabel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */