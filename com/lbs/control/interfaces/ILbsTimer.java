package com.lbs.control.interfaces;

import java.awt.event.ActionListener;

public interface ILbsTimer {
  void schedule(int paramInt, ActionListener paramActionListener);
  
  void stop();
  
  void start();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsTimer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */