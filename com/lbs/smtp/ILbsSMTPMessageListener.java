package com.lbs.smtp;

public interface ILbsSMTPMessageListener {
  public static final int MAIL_SINGLE = 0;
  
  public static final int MAIL_MULTI = 1;
  
  void setMailType(int paramInt);
  
  void messageSent(JLbsSMTPQueueMessage paramJLbsSMTPQueueMessage);
  
  void messageFailed(JLbsSMTPQueueMessage paramJLbsSMTPQueueMessage, String paramString);
  
  void reportFailed(long paramLong, String paramString);
  
  void reportEnd(long paramLong, boolean paramBoolean);
  
  void reportSaved(long paramLong);
  
  void afterInitialize();
  
  void setUserNr(int paramInt);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\smtp\ILbsSMTPMessageListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */