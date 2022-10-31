package com.lbs.control.interfaces;

import com.lbs.util.JLbsStringList;

public interface ILbsEventQueueListener {
  JLbsStringList getPopupMenuItems(Object paramObject, ILbsComponent paramILbsComponent);
  
  void popupActionPerformed(Object paramObject, ILbsComponent paramILbsComponent, int paramInt);
  
  boolean isPopupActionEnabled(Object paramObject, ILbsComponent paramILbsComponent, int paramInt);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsEventQueueListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */