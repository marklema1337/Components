package com.lbs.controls.numericedit;

import java.text.ParseException;

public interface ILbsNumberFormatter {
  Number parseNumber(String paramString) throws ParseException;
  
  String formatNumber(Number paramNumber);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\numericedit\ILbsNumberFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */