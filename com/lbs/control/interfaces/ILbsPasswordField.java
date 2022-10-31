package com.lbs.control.interfaces;

public interface ILbsPasswordField extends ILbsTextField, ILbsActionTarget {
  String getPasswordText();
  
  char[] getPassword();
  
  char getEchoChar();
  
  void setEchoChar(char paramChar);
  
  void setPassword(String paramString);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsPasswordField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */