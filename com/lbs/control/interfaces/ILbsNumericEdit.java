package com.lbs.control.interfaces;

import com.lbs.controls.numericedit.ILbsNumberListener;
import com.lbs.controls.numericedit.JLbsRealNumberFormatter;
import com.lbs.globalization.ILbsCultureInfo;
import com.lbs.globalization.JLbsCurrenciesBase;
import java.text.ParseException;

public interface ILbsNumericEdit extends ILbsMaskedEdit {
  public static final int NONE = 0;
  
  public static final int NUMBER = 1;
  
  public static final int REAL = 2;
  
  public static final String INVALID = "Invalid";
  
  void setNumber(Number paramNumber);
  
  Number getNumber() throws ParseException;
  
  Number getBigDecimalValue();
  
  void setNumberFormatter(int paramInt);
  
  void setNumberFormatter(int paramInt1, int paramInt2);
  
  void setNumberFormatter(int paramInt1, int paramInt2, int paramInt3, JLbsCurrenciesBase paramJLbsCurrenciesBase, boolean paramBoolean);
  
  void setNumberFormatter(int paramInt1, int paramInt2, int paramInt3, JLbsCurrenciesBase paramJLbsCurrenciesBase, boolean paramBoolean1, boolean paramBoolean2);
  
  void setSignAllowed(boolean paramBoolean);
  
  JLbsRealNumberFormatter getNumberFormatter();
  
  void setNumberFormatter(JLbsRealNumberFormatter paramJLbsRealNumberFormatter);
  
  boolean getForceDecimals();
  
  void setForceDecimals(boolean paramBoolean);
  
  void setCultureInfo(ILbsCultureInfo paramILbsCultureInfo);
  
  void setCurrencyBase(JLbsCurrenciesBase paramJLbsCurrenciesBase);
  
  ILbsCultureInfo getCultureInfo();
  
  void clearNumberListener();
  
  boolean getDisplayZero();
  
  void setDisplayZero(boolean paramBoolean);
  
  void getDisplayZero(boolean paramBoolean);
  
  double getDoubleValue();
  
  long getLongValue();
  
  boolean getFormatAsYouType();
  
  void setFormatAsYouType(boolean paramBoolean);
  
  ILbsNumberListener getNumberListener();
  
  void setNumberListener(ILbsNumberListener paramILbsNumberListener);
  
  String getPostfix();
  
  void setPostfix(String paramString);
  
  String getPrefix();
  
  void setPrefix(String paramString);
  
  boolean isSignAllowed();
  
  void setFilter(int paramInt1, String paramString, int paramInt2);
  
  void setFormatSpecifierParams();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsNumericEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */