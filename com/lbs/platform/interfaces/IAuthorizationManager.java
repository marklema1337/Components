package com.lbs.platform.interfaces;

import com.lbs.data.objects.BasicBusinessObject;
import com.lbs.util.JLbsStringList;
import java.util.Hashtable;

public interface IAuthorizationManager {
  String getDefaultAuthCode(JLbsStringList paramJLbsStringList);
  
  JLbsStringList getAuthCodesWithRight(JLbsStringList paramJLbsStringList, int paramInt);
  
  boolean hasRight(JLbsStringList paramJLbsStringList, int paramInt1, int paramInt2, String paramString);
  
  boolean hasRight(BasicBusinessObject paramBasicBusinessObject, int paramInt, JLbsStringList paramJLbsStringList1, JLbsStringList paramJLbsStringList2);
  
  boolean hasRight(BasicBusinessObject paramBasicBusinessObject, int paramInt, JLbsStringList paramJLbsStringList1, JLbsStringList paramJLbsStringList2, Hashtable<String, String> paramHashtable);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\interfaces\IAuthorizationManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */