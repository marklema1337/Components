package com.lbs.factory.interfaces;

import com.lbs.control.interfaces.ILbsTextEdit;
import java.util.function.Consumer;

public interface ILbsDelayedValueChangeObserver {
  void register(ILbsTextEdit paramILbsTextEdit, int paramInt, Consumer<ILbsTextEdit> paramConsumer);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\factory\interfaces\ILbsDelayedValueChangeObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */