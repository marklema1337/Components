package com.lbs.recording.interfaces;

import com.lbs.controls.ILbsComponentBase;
import java.awt.event.ItemEvent;

public interface ILbsSelectionRecordingEvents extends ILbsFocusRecordingEvents {
  void recordSetItemSelected(ILbsComponentBase paramILbsComponentBase, boolean paramBoolean);
  
  void recordFireItemStateChanged(ItemEvent paramItemEvent);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\interfaces\ILbsSelectionRecordingEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */