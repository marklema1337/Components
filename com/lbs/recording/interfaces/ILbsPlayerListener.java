package com.lbs.recording.interfaces;

public interface ILbsPlayerListener {
  void statusChanged(int paramInt1, int paramInt2, Object paramObject);
  
  void statusChanged(LbsPlayerMessage paramLbsPlayerMessage);
  
  void recordErrorMessage(String paramString);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\interfaces\ILbsPlayerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */