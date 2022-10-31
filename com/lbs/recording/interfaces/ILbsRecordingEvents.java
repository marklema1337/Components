package com.lbs.recording.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface ILbsRecordingEvents {
  void recordKeyPressed(KeyEvent paramKeyEvent);
  
  void recordMouseClicked(MouseEvent paramMouseEvent);
  
  void recordActionPerformed(ActionEvent paramActionEvent);
  
  void recordRequestFocus();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\interfaces\ILbsRecordingEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */