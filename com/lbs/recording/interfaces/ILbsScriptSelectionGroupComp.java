package com.lbs.recording.interfaces;

import com.lbs.util.JLbsStringList;

public interface ILbsScriptSelectionGroupComp {
  void selectByTag(int paramInt, boolean paramBoolean) throws InterruptedException;
  
  void selectByTag(int paramInt, String paramString) throws InterruptedException;
  
  JLbsStringList getItems() throws InterruptedException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\interfaces\ILbsScriptSelectionGroupComp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */