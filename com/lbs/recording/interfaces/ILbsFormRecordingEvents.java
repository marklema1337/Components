package com.lbs.recording.interfaces;

import java.awt.event.WindowEvent;

public interface ILbsFormRecordingEvents extends ILbsRecordingEvents {
  void recordWindowActivated(WindowEvent paramWindowEvent);
  
  void recordWindowClosed(WindowEvent paramWindowEvent);
  
  void recordWindowClosing(WindowEvent paramWindowEvent);
  
  void recordWindowResized();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\interfaces\ILbsFormRecordingEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */