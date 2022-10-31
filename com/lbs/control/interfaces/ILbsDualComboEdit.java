package com.lbs.control.interfaces;

import com.lbs.controls.ILbsFocusProvider;
import java.awt.event.ActionListener;

public interface ILbsDualComboEdit extends ILbsPanel, ILbsFocusProvider {
  void setText(String paramString1, String paramString2);
  
  void setToolTipFormat(String paramString);
  
  String[] getText();
  
  String getEditorText();
  
  void setEditorText(String paramString);
  
  ILbsMaskedEdit getEditControl();
  
  void setActionListener(ActionListener paramActionListener);
  
  int getEmptyBlockWidth();
  
  void setEmptyBlockWidth(int paramInt);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsDualComboEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */