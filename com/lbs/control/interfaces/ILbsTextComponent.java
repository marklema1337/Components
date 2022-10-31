package com.lbs.control.interfaces;

import java.awt.Color;
import javax.swing.text.BadLocationException;

public interface ILbsTextComponent extends ILbsComponent {
  String getText();
  
  void setText(String paramString);
  
  int getCaretPosition();
  
  void setCaretPosition(int paramInt);
  
  void setEditable(boolean paramBoolean);
  
  boolean isEditable();
  
  void replaceSelection(String paramString);
  
  Color getDisabledTextColor();
  
  void setDisabledTextColor(Color paramColor);
  
  String getSelectedText();
  
  Color getSelectedTextColor();
  
  void setSelectedTextColor(Color paramColor);
  
  Color getSelectionColor();
  
  void setSelectionColor(Color paramColor);
  
  int getSelectionEnd();
  
  void setSelectionEnd(int paramInt);
  
  int getSelectionStart();
  
  void setSelectionStart(int paramInt);
  
  String getText(int paramInt1, int paramInt2) throws BadLocationException;
  
  void select(int paramInt1, int paramInt2);
  
  void selectAll();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsTextComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */