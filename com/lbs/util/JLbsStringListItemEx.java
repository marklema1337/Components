/*    */ package com.lbs.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsStringListItemEx
/*    */   extends JLbsStringListItem
/*    */ {
/*    */   private static final long serialVersionUID = 6085037306246963131L;
/*    */   protected Object m_Object;
/*    */   
/*    */   public JLbsStringListItemEx(String s) {
/* 18 */     super(s);
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsStringListItemEx(String s, int iTag) {
/* 23 */     super(s, iTag);
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsStringListItemEx(String s, int iTag, Object obj) {
/* 28 */     super(s, iTag);
/* 29 */     this.m_Object = obj;
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsStringListItemEx(JLbsStringListItem item) {
/* 34 */     super(item);
/* 35 */     if (item instanceof JLbsStringListItemEx) {
/* 36 */       this.m_Object = ((JLbsStringListItemEx)item).m_Object;
/*    */     }
/*    */   }
/*    */   
/*    */   public Object getObject() {
/* 41 */     return this.m_Object;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setObject(Object object) {
/* 46 */     this.m_Object = object;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getFlag() {
/* 51 */     return (this.m_Object instanceof Boolean && ((Boolean)this.m_Object).booleanValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFlag(boolean b) {
/* 56 */     this.m_Object = new Boolean(b);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void calculateDelta(JLbsStringListItem obj, ArrayList<String> delta, String prefix) {
/* 62 */     super.calculateDelta(obj, delta, prefix);
/* 63 */     if (obj instanceof JLbsStringListItemEx)
/*    */     {
/* 65 */       if (this.m_Object == null) {
/*    */         
/* 67 */         if (((JLbsStringListItemEx)obj).m_Object != null) {
/* 68 */           delta.add("StringListItemEx object is null for source : " + getDeltaIdentifier(prefix));
/*    */         
/*    */         }
/*    */       }
/* 72 */       else if (((JLbsStringListItemEx)obj).m_Object == null) {
/* 73 */         delta.add("StringListItemEx object is null for dest : " + obj.getDeltaIdentifier(prefix));
/* 74 */       } else if (!this.m_Object.equals(((JLbsStringListItemEx)obj).m_Object)) {
/* 75 */         delta.add("StringListItemEx objects are different : source : " + getDeltaIdentifier(prefix) + " " + this.m_Object + 
/* 76 */             " , dest : " + obj.getDeltaIdentifier(prefix) + " " + ((JLbsStringListItemEx)obj).m_Object);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsStringListItemEx.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */