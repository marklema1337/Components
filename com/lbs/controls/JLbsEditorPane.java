/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsEditorPane;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Toolkit;
/*     */ import javax.swing.JEditorPane;
/*     */ import javax.swing.JViewport;
/*     */ import javax.swing.text.AttributeSet;
/*     */ import javax.swing.text.BadLocationException;
/*     */ import javax.swing.text.Document;
/*     */ import javax.swing.text.PlainDocument;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsEditorPane
/*     */   extends JEditorPane
/*     */   implements ILbsEditorPane
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  31 */   protected String m_OriginalText = null;
/*     */   
/*     */   private int m_MaxLenght;
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/*  37 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/*  42 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String t) {
/*  47 */     super.setText(t);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doSetText(int actionID, String str) {
/*  52 */     final int mActionID = actionID;
/*  53 */     final String mStr = str;
/*  54 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           
/*     */           public void run()
/*     */           {
/*     */             try {
/*  60 */               JLbsEditorPane.this.setText(mStr);
/*  61 */               JLbsComponentHelper.statusChanged(4, mActionID, "Set Text succedded.");
/*     */             }
/*  63 */             catch (Exception e) {
/*     */               
/*  65 */               JLbsComponentHelper.statusChanged(1, mActionID, "Could not set " + mStr);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestFocus() {
/*  73 */     this.m_OriginalText = getText();
/*  74 */     super.requestFocus();
/*  75 */     recordRequestFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordRequestFocus() {
/*  80 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "REQUEST_FOCUS");
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRequestFocus(int actionID) {
/*  85 */     final int mActionID = actionID;
/*  86 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/*  90 */             JLbsEditorPane.this.requestFocus();
/*  91 */             JLbsComponentHelper.statusChanged(4, mActionID, null);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAll() {
/* 105 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 109 */             JLbsEditorPane.this.internalRemoveAll();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private void internalRemoveAll() {
/* 116 */     super.removeAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 121 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOriginalText() {
/* 129 */     return this.m_OriginalText;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 134 */     return getPage();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResetTime(boolean resetTime) {}
/*     */ 
/*     */   
/*     */   public boolean isResetTime() {
/* 143 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPreferredSize(Dimension preferredSize) {
/* 149 */     super.setPreferredSize(preferredSize);
/* 150 */     Container parent = getUnwrappedParent(this);
/* 151 */     if (parent instanceof JViewport) {
/*     */       
/* 153 */       JViewport port = (JViewport)parent;
/* 154 */       port.setSize(preferredSize);
/* 155 */       port.setPreferredSize(preferredSize);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Container getUnwrappedParent(Component component) {
/* 163 */     Container parent = component.getParent();
/* 164 */     while (parent instanceof javax.swing.Scrollable)
/* 165 */       parent = parent.getParent(); 
/* 166 */     return parent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextLimit(int limit) {
/* 172 */     this.m_MaxLenght = limit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDocument(Document doc) {
/* 179 */     if (doc instanceof PlainDocument) {
/*     */       
/* 181 */       super.setDocument(new PlainDocument()
/*     */           {
/*     */             
/*     */             public void insertString(int offset, String str, AttributeSet a) throws BadLocationException
/*     */             {
/* 186 */               int maxLenght = JLbsEditorPane.this.getTextLimit();
/* 187 */               if (maxLenght > 0) {
/*     */                 
/* 189 */                 int iSelfLength = getLength();
/* 190 */                 if (iSelfLength + str.length() > maxLenght) {
/* 191 */                   super.insertString(offset, str.substring(0, maxLenght - iSelfLength), a);
/* 192 */                 } else if (getLength() + str.length() <= maxLenght) {
/* 193 */                   super.insertString(offset, str, a);
/*     */                 } else {
/* 195 */                   Toolkit.getDefaultToolkit().beep();
/*     */                 } 
/*     */               } else {
/* 198 */                 super.insertString(offset, str, a);
/*     */               } 
/*     */             }
/*     */           });
/*     */     } else {
/* 203 */       super.setDocument(doc);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTextLimit() {
/* 209 */     return this.m_MaxLenght;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsEditorPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */