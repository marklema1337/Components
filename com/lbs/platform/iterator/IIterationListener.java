package com.lbs.platform.iterator;

public interface IIterationListener<T> {
  void onInitialize(IterationEvent<T> paramIterationEvent);
  
  void onStartup(IterationEvent<T> paramIterationEvent);
  
  void onFirst(IterationEvent<T> paramIterationEvent);
  
  void onNext(IterationEvent<T> paramIterationEvent);
  
  void onPrevious(IterationEvent<T> paramIterationEvent);
  
  void onLast(IterationEvent<T> paramIterationEvent);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\iterator\IIterationListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */