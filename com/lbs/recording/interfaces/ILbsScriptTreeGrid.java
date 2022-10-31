package com.lbs.recording.interfaces;

public interface ILbsScriptTreeGrid extends ILbsScriptGrid {
  void leftClickNode(int paramInt) throws InterruptedException;
  
  void rightClickNode(int paramInt) throws InterruptedException;
  
  void leftClickNode(int paramInt1, int paramInt2) throws InterruptedException;
  
  void rightClickNode(int paramInt1, int paramInt2) throws InterruptedException;
  
  void leftClickNode(int paramInt1, int paramInt2, int paramInt3) throws InterruptedException;
  
  void leftClickNode(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws InterruptedException;
  
  void rightClickNode(int paramInt1, int paramInt2, int paramInt3) throws InterruptedException;
  
  void rightClickNode(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws InterruptedException;
  
  void expandNode(int paramInt) throws InterruptedException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\interfaces\ILbsScriptTreeGrid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */