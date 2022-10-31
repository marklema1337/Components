package com.lbs.recording.interfaces;

public interface ILbsScriptComponent {
  void select() throws InterruptedException;
  
  void deselect() throws InterruptedException;
  
  void select(int paramInt) throws InterruptedException;
  
  void setValue(String paramString) throws InterruptedException;
  
  Object getValue() throws InterruptedException;
  
  String getValueAsString() throws InterruptedException;
  
  String getTitle() throws InterruptedException;
  
  int getType() throws InterruptedException;
  
  void keyPressed(int paramInt) throws InterruptedException;
  
  void keyPressed(int paramInt1, int paramInt2) throws InterruptedException;
  
  boolean isMandatory() throws InterruptedException;
  
  void checkTitle(int paramInt) throws InterruptedException;
  
  void checkTitle(int paramInt1, int paramInt2) throws InterruptedException;
  
  void checkValue(int paramInt) throws InterruptedException;
  
  void checkValue(int paramInt1, int paramInt2) throws InterruptedException;
  
  void checkSize(int paramInt) throws InterruptedException;
  
  void checkSize(int paramInt1, int paramInt2) throws InterruptedException;
  
  void checkLocation(int paramInt) throws InterruptedException;
  
  void checkLocation(int paramInt1, int paramInt2) throws InterruptedException;
  
  void checkVisibility(int paramInt) throws InterruptedException;
  
  void checkVisibility(int paramInt1, int paramInt2) throws InterruptedException;
  
  void checkEnablementStatus(int paramInt) throws InterruptedException;
  
  void checkEnablementStatus(int paramInt1, int paramInt2) throws InterruptedException;
  
  void checkMandatoryStatus(int paramInt) throws InterruptedException;
  
  void checkMandatoryStatus(int paramInt1, int paramInt2) throws InterruptedException;
  
  void checkItemListValues(int paramInt) throws InterruptedException;
  
  void checkItemListValues(int paramInt1, int paramInt2) throws InterruptedException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\interfaces\ILbsScriptComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */