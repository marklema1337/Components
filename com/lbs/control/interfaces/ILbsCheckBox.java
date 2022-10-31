package com.lbs.control.interfaces;

import javax.swing.event.ChangeListener;

public interface ILbsCheckBox extends ILbsAbstractButton {
  default void addChangeListener(ChangeListener changeListener) {}
  
  default void removeChangeListener(ChangeListener changeListener) {}
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsCheckBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */