package com.lbs.platform.iterator;

import java.util.List;

public interface ILbsIterator<T> {
  public static final int EVENT_FIRST = 0;
  
  public static final int EVENT_INITIALIZE = 1;
  
  public static final int EVENT_LAST = 2;
  
  public static final int EVENT_NEXT = 3;
  
  public static final int EVENT_PREVIOUS = 4;
  
  void initialize(IteratorParamBase<T> paramIteratorParamBase) throws Exception;
  
  T first() throws Exception;
  
  T next() throws Exception;
  
  T previous() throws Exception;
  
  T last() throws Exception;
  
  boolean eof();
  
  List<T> getChunk();
  
  int chunkSize();
  
  boolean isBidirectional();
  
  void iterate(ILbsIterationProcessor<T> paramILbsIterationProcessor, boolean paramBoolean) throws Exception;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\iterator\ILbsIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */