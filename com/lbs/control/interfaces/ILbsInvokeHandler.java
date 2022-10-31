package com.lbs.control.interfaces;

import com.lbs.xui.RunnableWithReturn;

public interface ILbsInvokeHandler {
  void invokeLater(Runnable paramRunnable);
  
  ILbsTimer getTimer();
  
  Object runWithProgressImpl(String paramString, RunnableWithReturn paramRunnableWithReturn) throws Exception;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsInvokeHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */