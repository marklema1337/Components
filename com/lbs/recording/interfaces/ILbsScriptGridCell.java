package com.lbs.recording.interfaces;

public interface ILbsScriptGridCell {
  void select() throws InterruptedException;
  
  void setValue(Object paramObject) throws InterruptedException;
  
  void verifyValue(Object paramObject) throws InterruptedException;
  
  void valueVerify(Object paramObject) throws InterruptedException;
  
  Object getValue() throws InterruptedException;
  
  String getValueAsString() throws InterruptedException;
  
  void lookup() throws InterruptedException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\interfaces\ILbsScriptGridCell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */