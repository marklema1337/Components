/*    */ package com.lbs.control.interfaces;
/*    */ 
/*    */ import com.lbs.controls.ILbsFocusProvider;
/*    */ import com.lbs.controls.maskededit.ITextLimitChangeListener;
/*    */ import com.lbs.controls.maskededit.JLbsCharMap;
/*    */ import java.awt.Color;
/*    */ import java.io.File;
/*    */ import javax.swing.JFormattedTextField;
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ILbsMaskedEdit
/*    */   extends ILbsTextField, ILbsActionTarget, ILbsFocusProvider
/*    */ {
/*    */   void setValue(Object paramObject);
/*    */   
/*    */   Object getValue();
/*    */   
/*    */   boolean setTextLimit(int paramInt);
/*    */   
/*    */   int getTextLimit();
/*    */   
/*    */   void setMask(String paramString);
/*    */   
/*    */   void setTextLimitChangeListener(ITextLimitChangeListener paramITextLimitChangeListener);
/*    */   
/*    */   JLbsCharMap getCharMap();
/*    */   
/*    */   void setCharMap(JLbsCharMap paramJLbsCharMap);
/*    */   
/*    */   void setCharFilter(String paramString);
/*    */   
/*    */   String getCharFilter();
/*    */   
/*    */   Color getNormalBkColor();
/*    */   
/*    */   String getOriginalText();
/*    */   
/*    */   boolean verifyContent();
/*    */   
/*    */   void enableFocusEvent(boolean paramBoolean);
/*    */   
/*    */   boolean isEnableFocusEvent();
/*    */   
/*    */   void setClipMode(boolean paramBoolean);
/*    */   
/*    */   String getMask();
/*    */   
/*    */   JFormattedTextField.AbstractFormatter getFormatter();
/*    */   
/*    */   boolean isClipMode();
/*    */   
/*    */   default boolean hasFileDialog() {
/* 54 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   default void setHasFileDialog(boolean hasFileDialog) {}
/*    */ 
/*    */   
/*    */   default File[] getSelectedFiles() {
/* 63 */     return new File[0];
/*    */   }
/*    */   
/*    */   default void setSelectedFiles(File[] selectedFiles) {}
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsMaskedEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */