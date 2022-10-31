package com.lbs.client;

import java.util.Calendar;
import java.util.List;

public interface ILbsCalendarService {
  String saveAppointment(JLbsAppointment paramJLbsAppointment);
  
  boolean deleteAppointment(String paramString);
  
  String sendMeeting(JLbsMeeting paramJLbsMeeting);
  
  boolean cancelMeeting(String paramString);
  
  JLbsAppointment getAppointment(String paramString);
  
  JLbsMeeting getMeeting(String paramString);
  
  List<JLbsAppointment> getCalendarItems(Calendar paramCalendar1, Calendar paramCalendar2, String paramString);
  
  void close();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\client\ILbsCalendarService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */