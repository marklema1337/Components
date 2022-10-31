package com.lbs.transport;

import com.lbs.util.JLbsStringList;
import java.util.Calendar;

public interface IUserDetailInfo {
  boolean isSuperUser();
  
  void setSuperUser(boolean paramBoolean);
  
  boolean isSubSuperUser();
  
  void setSubSuperUser(boolean paramBoolean);
  
  JLbsStringList getDBRights();
  
  void setDBRights(JLbsStringList paramJLbsStringList);
  
  JLbsStringList getRepRights();
  
  void setRepRights(JLbsStringList paramJLbsStringList);
  
  int getUserNr();
  
  String getEmail();
  
  String getUserName();
  
  int[] getGroupIDs();
  
  int canCustomize();
  
  int canNotSeeReportFiltersProperties();
  
  int getDomainId();
  
  String getClientIp();
  
  boolean hasSpecialRight(int paramInt);
  
  String getFullName();
  
  int getFirmNr();
  
  String getTenantId();
  
  String getFirmTitle();
  
  int getPeriodNr();
  
  Calendar getDateForLocalCalendar(Calendar paramCalendar);
  
  int getUserLogicalRef();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\IUserDetailInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */