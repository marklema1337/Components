package com.lbs.smtp;

import com.lbs.mail.JLbsMailMessage;

public interface ILbsMailWorker {
  void sendMailDirect(JLbsSMTPConnectionInfo paramJLbsSMTPConnectionInfo, JLbsMailMessage paramJLbsMailMessage) throws Throwable;
  
  void sendMailDirect(JLbsSMTPConnectionInfo paramJLbsSMTPConnectionInfo, JLbsMailMessage paramJLbsMailMessage, boolean paramBoolean) throws Throwable;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\smtp\ILbsMailWorker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */