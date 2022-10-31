/*     */ package com.lbs.util;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObjectCopyEvent
/*     */ {
/*     */   protected boolean m_Consumed;
/*     */   protected ObjectCopyEnvironment m_Environment;
/*     */   protected Object m_DestinationObject;
/*     */   protected Object m_SourceObject;
/*     */   protected Object m_DestinationOwner;
/*     */   protected Object m_SourceOwner;
/*     */   protected String m_MemberName;
/*     */   
/*     */   public ObjectCopyEvent(ObjectCopyEnvironment env, Object srcOwner, Object dstOwner, Object srcObject, Object dstObject, String memberName) {
/*  29 */     this.m_Consumed = false;
/*     */     
/*  31 */     this.m_Environment = env;
/*  32 */     this.m_DestinationObject = dstObject;
/*  33 */     this.m_SourceObject = srcObject;
/*  34 */     this.m_DestinationOwner = dstOwner;
/*  35 */     this.m_SourceOwner = srcOwner;
/*  36 */     this.m_MemberName = memberName;
/*     */   }
/*     */ 
/*     */   
/*     */   public ObjectCopyEvent(ObjectCopyEvent e) {
/*  41 */     this.m_Consumed = false;
/*     */     
/*  43 */     this.m_Environment = e.m_Environment;
/*  44 */     this.m_DestinationObject = e.m_DestinationObject;
/*  45 */     this.m_SourceObject = e.m_SourceObject;
/*  46 */     this.m_DestinationOwner = e.m_DestinationOwner;
/*  47 */     this.m_SourceOwner = e.m_SourceOwner;
/*  48 */     this.m_MemberName = e.m_MemberName;
/*     */   }
/*     */ 
/*     */   
/*     */   public ObjectCopyEvent(ObjectCopyEvent e, Object dstObject) {
/*  53 */     this(e);
/*  54 */     this.m_DestinationObject = dstObject;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConsumed() {
/*  59 */     return this.m_Consumed;
/*     */   }
/*     */ 
/*     */   
/*     */   public ObjectCopyEnvironment getEnvironment() {
/*  64 */     return this.m_Environment;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getDestinationObject() {
/*  69 */     return this.m_DestinationObject;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConsumed(boolean consumed) {
/*  74 */     this.m_Consumed = consumed;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMemberName() {
/*  79 */     return this.m_MemberName;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getSourceOwner() {
/*  84 */     return this.m_SourceOwner;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  89 */     StringBuilder sb = new StringBuilder();
/*     */     
/*  91 */     sb.append("[");
/*  92 */     sb.append("src object=" + this.m_SourceObject);
/*  93 */     sb.append(", dst object=" + this.m_DestinationObject);
/*  94 */     sb.append(", src owner=" + this.m_SourceOwner);
/*  95 */     sb.append(", dst owner=" + this.m_DestinationOwner);
/*  96 */     sb.append(", memberName=" + this.m_MemberName);
/*     */     
/*  98 */     if (this.m_DestinationObject != null)
/*  99 */       sb.append(", exists=" + this.m_Environment.contains(this.m_DestinationObject)); 
/* 100 */     sb.append("]");
/*     */     
/* 102 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getDestinationOwner() {
/* 107 */     return this.m_DestinationOwner;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getSourceObject() {
/* 112 */     return this.m_SourceObject;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDestinationObject(Object object) {
/* 117 */     this.m_DestinationObject = object;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ObjectCopyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */