package com.lbs.message;

import com.lbs.util.JLbsStringList;

public interface ILbsMessageListener {
  String processTitleBeforeShow(String paramString1, String paramString2);
  
  String processMainMessageBeforeShow(String paramString1, String paramString2, boolean paramBoolean);
  
  JLbsStringList processDetailMessagesBeforeShow(String paramString, JLbsStringList paramJLbsStringList);
  
  String[] processTitlesBeforeShow(String paramString, String[] paramArrayOfString);
  
  String[] processMainMessagesBeforeShow(String paramString, String[] paramArrayOfString);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\ILbsMessageListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */