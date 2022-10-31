package com.lbs.controls.datedit;

public interface ILbsFixedDateSelectionListener {
  public static final int FIXDATE_NONE = 0;
  
  public static final int FIXDATE_YESTERDAY = 1;
  
  public static final int FIXDATE_TODAY = 2;
  
  public static final int FIXDATE_TOMORROW = 3;
  
  public static final int FIXDATE_FRST_DAY_THISWEEK = 5;
  
  public static final int FIXDATE_LST_DAY_THISWEEK = 6;
  
  public static final int FIXDATE_FRST_DAY_THISMONTH = 8;
  
  public static final int FIXDATE_LST_DAY_THISMONTH = 9;
  
  public static final int FIXDATE_FRST_DAY_LASTMONTH = 11;
  
  public static final int FIXDATE_LST_DAY_LASTMONTH = 12;
  
  public static final int FIXDATE_FRST_DAY_NEXTMONTH = 14;
  
  public static final int FIXDATE_LST_DAY_NEXTMONTH = 15;
  
  void fixedDateSelected(int paramInt);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\datedit\ILbsFixedDateSelectionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */