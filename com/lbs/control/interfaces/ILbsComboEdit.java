package com.lbs.control.interfaces;

import com.lbs.controls.ILbsFocusProvider;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public interface ILbsComboEdit extends ILbsPanel, ILbsFocusProvider {
  public static final int EXTRA_BUTTON_ID = 1;
  
  ILbsMaskedEdit getEditControl();
  
  void addExtraButton(int paramInt);
  
  int getEmptyBlockWidth();
  
  void setEmptyBlockWidth(int paramInt);
  
  void setActionListener(ActionListener paramActionListener);
  
  void setExtraButtonListener(ActionListener paramActionListener);
  
  void doButtonAction(int paramInt1, int paramInt2);
  
  void setFirstLookupButtonVisible(boolean paramBoolean);
  
  void setMultilangButton(JButton paramJButton);
  
  void setIconToMultilangButton(ImageIcon paramImageIcon);
  
  Object getMultilangButton();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsComboEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */