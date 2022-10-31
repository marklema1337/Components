/*    */ package com.lbs.util;
/*    */ 
/*    */ import com.lbs.interfaces.ILbsCommentOwner;
/*    */ import java.io.Serializable;
/*    */ import java.util.Vector;
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
/*    */ public class JLbsCommentOwner
/*    */   implements ILbsCommentOwner, Serializable, Cloneable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 21 */   private Vector<String> m_Comments = new Vector<>();
/*    */ 
/*    */   
/*    */   public void addComment(String comment) {
/* 25 */     this.m_Comments.add(comment);
/*    */   }
/*    */ 
/*    */   
/*    */   public Vector<String> getComments() {
/* 30 */     return this.m_Comments;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasComments() {
/* 35 */     return (this.m_Comments.size() > 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object clone() throws CloneNotSupportedException {
/* 41 */     return super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsCommentOwner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */