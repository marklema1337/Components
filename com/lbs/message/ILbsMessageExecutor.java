package com.lbs.message;

import com.lbs.globalization.ILbsCultureInfo;
import com.lbs.util.JLbsStringList;
import java.util.ArrayList;

public interface ILbsMessageExecutor {
  JLbsMessageDialogResult onMessageShow(JLbsMessage paramJLbsMessage, String paramString1, JLbsStringList paramJLbsStringList, String paramString2, ILbsCultureInfo paramILbsCultureInfo, int paramInt, Object paramObject);
  
  void onMultiMessageShow(JLbsMessage paramJLbsMessage);
  
  boolean onPaneMessage(int paramInt1, int paramInt2, String paramString, int paramInt3, int paramInt4);
  
  boolean onPaneMessage(int paramInt1, int paramInt2, String paramString, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
  
  boolean onPaneMessages(ArrayList paramArrayList);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\ILbsMessageExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */