/*     */ package com.lbs.data.objects;
/*     */ 
/*     */ import com.lbs.invoke.MethodInvoker;
/*     */ import com.lbs.reflection.ReflectiveInvocation;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Method;
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
/*     */ public class BusinessObjectEvent
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int MEMBER_CHANGED = 1;
/*     */   public static final int METHOD_INVOKED = 2;
/*     */   public static final int LIST_CREATED = 3;
/*     */   public static final int LIST_ITEM_ADDED = 4;
/*     */   public static final int LIST_ITEM_REMOVED = 5;
/*     */   public static final int LIST_ITEM_DELETED = 6;
/*     */   private transient Object m_Source;
/*     */   private String m_MemberName;
/*     */   private String m_MethodName;
/*     */   private Object m_Value;
/*     */   private Object m_OldValue;
/*     */   private int m_Reason;
/*  35 */   private int m_ItemIndex = -1;
/*     */ 
/*     */   
/*     */   public BusinessObjectEvent(Object source, String memberName, Object value, Object oldValue, int reason) {
/*  39 */     this.m_Source = source;
/*  40 */     this.m_MemberName = memberName;
/*  41 */     this.m_Value = value;
/*  42 */     this.m_OldValue = oldValue;
/*  43 */     this.m_Reason = reason;
/*     */   }
/*     */ 
/*     */   
/*     */   public BusinessObjectEvent(BasicBusinessObject source, String memberName, Object value, Object oldValue) {
/*  48 */     this(source, memberName, value, oldValue, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public BusinessObjectEvent(Object source, String memberName, String methodName, Object[] params) {
/*  53 */     this(source, memberName, params, null, 2);
/*  54 */     this.m_MethodName = methodName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMemberName() {
/*  59 */     return this.m_MemberName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMethodName() {
/*  64 */     return this.m_MethodName;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getReason() {
/*  69 */     return this.m_Reason;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getSource() {
/*  74 */     return this.m_Source;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getValue() {
/*  79 */     return this.m_Value;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getOldValue() {
/*  84 */     return this.m_OldValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] getMethodParams() {
/*  89 */     if (this.m_Value instanceof Object[]) {
/*  90 */       return (Object[])this.m_Value;
/*     */     }
/*  92 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMemberName(String memberName) {
/*  97 */     this.m_MemberName = memberName;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getItemIndex() {
/* 102 */     return this.m_ItemIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemIndex(int itemIndex) {
/* 107 */     this.m_ItemIndex = itemIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void invoke() throws Exception {
/* 112 */     MethodInvoker.invokeMethod(getMethodName(), this.m_Source, getMethodParams());
/*     */   }
/*     */ 
/*     */   
/*     */   public void invoke(Object obj) throws Exception {
/* 117 */     Object[] args = getMethodParams();
/* 118 */     Method method = ReflectiveInvocation.findMethod(obj, getMethodName(), args);
/*     */     
/* 120 */     if (method != null)
/* 121 */       method.invoke(obj, args); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\BusinessObjectEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */