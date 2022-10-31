package com.lbs.message;

import com.lbs.globalization.ILbsCultureInfo;
import com.lbs.util.JLbsStringList;

public interface ILbsMessageDialogImpl {
  JLbsMessageDialogResult showLbsMessageDialog(JLbsMessage paramJLbsMessage, String paramString1, JLbsStringList paramJLbsStringList, String paramString2, ILbsCultureInfo paramILbsCultureInfo, int paramInt, Object paramObject);
  
  void showMultiLineMessages(JLbsMessage paramJLbsMessage, String[] paramArrayOfString1, String[] paramArrayOfString2);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\ILbsMessageDialogImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */