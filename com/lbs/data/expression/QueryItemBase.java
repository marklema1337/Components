/*    */ package com.lbs.data.expression;
/*    */ 
/*    */ import com.lbs.util.ObjectUtil;
/*    */ import java.io.Serializable;
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
/*    */ public abstract class QueryItemBase
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected QueryItemBase m_Parent;
/*    */   protected Object m_Tag;
/*    */   
/*    */   public QueryItemBase(QueryItemBase parent) {
/* 26 */     this.m_Parent = parent;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public QueryItemBase getParent() {
/* 34 */     return this.m_Parent;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setParent(QueryItemBase parent) {
/* 39 */     this.m_Parent = parent;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getTag() {
/* 47 */     return this.m_Tag;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setTag(Object tag) {
/* 56 */     this.m_Tag = tag;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void copy(QueryItemBase dst, IQueryItemCopy copyHandler) {
/* 61 */     if (this.m_Tag != null)
/* 62 */       dst.m_Tag = ObjectUtil.createCopy(this.m_Tag); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\expression\QueryItemBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */