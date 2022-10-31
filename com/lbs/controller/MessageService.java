package com.lbs.controller;

import com.lbs.message.JLbsMessageDialogResult;
import com.lbs.platform.interfaces.IApplicationContext;

public interface MessageService {
  JLbsMessageDialogResult handleMessage(IApplicationContext paramIApplicationContext, String paramString1, String paramString2);
  
  JLbsMessageDialogResult handleMessage(IApplicationContext paramIApplicationContext, String paramString1, String paramString2, String[] paramArrayOfString);
  
  boolean confirmed(IApplicationContext paramIApplicationContext, String paramString1, String paramString2);
  
  boolean confirmed(IApplicationContext paramIApplicationContext, String paramString1, String paramString2, String[] paramArrayOfString);
  
  void addValidationMessage(ValidationMessage paramValidationMessage);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\MessageService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */