/*    */ package com.lbs.controls.maskededit;
/*    */ 
/*    */ import com.lbs.control.interfaces.ILbsTextEdit;
/*    */ import com.lbs.controls.ILbsComponentBase;
/*    */ import com.lbs.controls.JLbsComponentHelper;
/*    */ import com.lbs.controls.JLbsEventRecorderHelper;
/*    */ import com.lbs.recording.interfaces.ILbsTextEditRecordingEvents;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.KeyEvent;
/*    */ import java.awt.event.MouseEvent;
/*    */ import javax.swing.SwingUtilities;
/*    */ import javax.swing.text.Document;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsTextEdit
/*    */   extends JLbsMaskedEdit
/*    */   implements ILbsTextEditRecordingEvents, ILbsTextEdit
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public static final int EDITATTRNORMAL = 0;
/*    */   public static final int EDITATTRUPPERCASE = 1;
/*    */   public static final int EDITATTRLOWERCASE = 2;
/*    */   
/*    */   public JLbsTextEdit() {}
/*    */   
/*    */   public JLbsTextEdit(int iMaxLength) {
/* 48 */     setTextLimit(iMaxLength);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEditStyle() {
/* 56 */     Document doc = getDocument();
/* 57 */     if (doc instanceof JLbsLimitedTextDocument)
/* 58 */       return ((JLbsLimitedTextDocument)doc).getEditStyle(); 
/* 59 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setEditStyle(int iType) {
/* 67 */     Document doc = getDocument();
/* 68 */     if (doc instanceof JLbsLimitedTextDocument) {
/* 69 */       ((JLbsLimitedTextDocument)doc).setEditStyle(iType);
/*    */     }
/*    */   }
/*    */   
/*    */   protected Document createDefaultModel() {
/* 74 */     return new JLbsLimitedTextDocument(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setText(String s) {
/* 79 */     super.setText(s);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void doSetText(String s, int actionID) {
/* 85 */     final String mS = s;
/* 86 */     final int fActionID = actionID;
/* 87 */     SwingUtilities.invokeLater(new Runnable()
/*    */         {
/*    */           public void run()
/*    */           {
/* 91 */             JLbsTextEdit.this.setText(mS);
/* 92 */             JLbsComponentHelper.statusChanged(4, fActionID, null);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public void recordSetText(String s) {
/* 99 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "SET_TEXT");
/*    */   }
/*    */   
/*    */   public void recordActionPerformed(ActionEvent evt) {}
/*    */   
/*    */   public void recordKeyPressed(KeyEvent evt) {}
/*    */   
/*    */   public void recordMouseClicked(MouseEvent evt) {}
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\maskededit\JLbsTextEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */