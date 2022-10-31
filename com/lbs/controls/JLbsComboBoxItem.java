/*    */ package com.lbs.controls;
/*    */ 
/*    */ import com.lbs.util.JLbsStringUtil;
/*    */ import javax.swing.ImageIcon;
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
/*    */ public class JLbsComboBoxItem
/*    */ {
/*    */   private Object m_Value;
/*    */   private int m_iTag;
/*    */   private ImageIcon m_icon;
/*    */   public static final String EMPTYVALUE = "#";
/*    */   
/*    */   public JLbsComboBoxItem(Object value, int iTag) {
/* 24 */     this(value, iTag, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsComboBoxItem(Object value, int iTag, ImageIcon icon) {
/* 29 */     this.m_Value = value;
/* 30 */     if (this.m_Value instanceof String && JLbsStringUtil.areEqual((String)this.m_Value, "#"))
/* 31 */       this.m_Value = ""; 
/* 32 */     this.m_iTag = iTag;
/* 33 */     this.m_icon = icon;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 39 */     if (this.m_Value instanceof JLbsPropertyVariable) {
/*    */       
/* 41 */       JLbsPropertyVariable propVar = (JLbsPropertyVariable)this.m_Value;
/* 42 */       if (!JLbsStringUtil.isEmpty(propVar.getDescription())) {
/* 43 */         return propVar.getDescription();
/*    */       }
/* 45 */       return propVar.getName();
/*    */     } 
/*    */     
/* 48 */     return (this.m_Value != null) ? 
/* 49 */       this.m_Value.toString() : 
/* 50 */       "";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTag() {
/* 55 */     return this.m_iTag;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getValue() {
/* 60 */     return this.m_Value;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIcon(ImageIcon icon) {
/* 65 */     this.m_icon = icon;
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageIcon getIcon() {
/* 70 */     return this.m_icon;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toEditString() {
/* 76 */     StringBuffer buffer = new StringBuffer();
/* 77 */     if (this.m_Value instanceof JLbsPropertyVariable) {
/* 78 */       buffer.append(((JLbsPropertyVariable)this.m_Value).getName());
/* 79 */     } else if (this.m_Value != null) {
/* 80 */       buffer.append(this.m_Value.toString());
/* 81 */     }  buffer.append('~');
/* 82 */     buffer.append(this.m_iTag);
/* 83 */     return buffer.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsComboBoxItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */