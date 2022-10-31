package com.lbs.controls.datedit;

import com.lbs.control.interfaces.ILbsDateEdit;
import javax.swing.JFormattedTextField;

public interface ILbsInternalDateEdit extends ILbsDateEdit {
  void setDateFormatter(JFormattedTextField.AbstractFormatter paramAbstractFormatter);
  
  boolean superVerifyContent();
  
  void setOriginalText(String paramString);
  
  void setValueChangedByParent(boolean paramBoolean);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\datedit\ILbsInternalDateEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */