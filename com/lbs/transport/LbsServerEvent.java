/*     */ package com.lbs.transport;
/*     */ 
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
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
/*     */ public class LbsServerEvent
/*     */   implements Serializable, Externalizable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int m_EventType;
/*     */   private int m_EventSeverity;
/*     */   private boolean m_Consumed;
/*     */   private Object m_EventData;
/*     */   private boolean m_CanDispatch;
/*  27 */   private int m_EventID = 0;
/*  28 */   private int m_UserNr = -1;
/*  29 */   private int m_OperationID = 0;
/*  30 */   private int m_DomainId = 0;
/*  31 */   private int m_Status = 1;
/*     */   
/*     */   private Long m_SpecificTime;
/*     */   
/*     */   private Object m_Consumers;
/*     */   
/*     */   public static final int EVT_MESSAGE = 0;
/*     */   
/*     */   public static final int EVT_ERROR = 1;
/*     */   
/*     */   public static final int EVT_OPERATION = 2;
/*     */   
/*     */   public static final int EVT_NOTIFICATION = 3;
/*     */   
/*     */   public static final int TIME_DURATION = 2;
/*     */   
/*     */   public static final int TIME_EXACT = 1;
/*     */   
/*     */   public static final int SEVERITY_LOW = 0;
/*     */   
/*     */   public static final int SEVERITY_MEDIUM = 1;
/*     */   public static final int SEVERITY_HIGH = 2;
/*     */   public static final int SEVERITY_FATAL = 3;
/*     */   public static final int STATUS_PASSIVE = 0;
/*     */   public static final int STATUS_ACTIVE = 1;
/*     */   
/*     */   public boolean isConsumed() {
/*  58 */     return this.m_Consumed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConsumed(boolean consumed) {
/*  63 */     this.m_Consumed = consumed;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getEventData() {
/*  68 */     return this.m_EventData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEventData(Object eventData) {
/*  73 */     this.m_EventData = eventData;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEventSeverity() {
/*  78 */     return this.m_EventSeverity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEventSeverity(int eventSeverity) {
/*  83 */     this.m_EventSeverity = eventSeverity;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEventType() {
/*  88 */     return this.m_EventType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEventType(int eventType) {
/*  93 */     this.m_EventType = eventType;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canDispatch() {
/*  98 */     return this.m_CanDispatch;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCanDispatch(boolean canDispatch) {
/* 103 */     this.m_CanDispatch = canDispatch;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEventID() {
/* 108 */     return this.m_EventID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEventID(int eventID) {
/* 113 */     this.m_EventID = eventID;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUserNr() {
/* 118 */     return this.m_UserNr;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUserNr(int userNr) {
/* 123 */     this.m_UserNr = userNr;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOperationID() {
/* 128 */     return this.m_OperationID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperationID(int operationID) {
/* 133 */     this.m_OperationID = operationID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDomainId(int domainId) {
/* 138 */     this.m_DomainId = domainId;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDomainId() {
/* 143 */     return this.m_DomainId;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStatus() {
/* 148 */     return this.m_Status;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatus(int status) {
/* 153 */     this.m_Status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getSpecificTime() {
/* 158 */     return this.m_SpecificTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSpecificTime(Long specificTime) {
/* 163 */     this.m_SpecificTime = specificTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getConsumers() {
/* 168 */     return this.m_Consumers;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConsumers(Object consumers) {
/* 173 */     this.m_Consumers = consumers;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 178 */     this.m_EventType = in.readInt();
/* 179 */     this.m_EventSeverity = in.readInt();
/* 180 */     this.m_Consumed = in.readBoolean();
/* 181 */     this.m_EventData = in.readObject();
/* 182 */     this.m_CanDispatch = in.readBoolean();
/* 183 */     this.m_EventID = in.readInt();
/* 184 */     this.m_UserNr = in.readInt();
/* 185 */     this.m_OperationID = in.readInt();
/* 186 */     this.m_DomainId = in.readInt();
/* 187 */     this.m_SpecificTime = (Long)in.readObject();
/* 188 */     this.m_Consumers = in.readObject();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 193 */     out.writeInt(this.m_EventType);
/* 194 */     out.writeInt(this.m_EventSeverity);
/* 195 */     out.writeBoolean(this.m_Consumed);
/* 196 */     out.writeObject(this.m_EventData);
/* 197 */     out.writeBoolean(this.m_CanDispatch);
/* 198 */     out.writeInt(this.m_EventID);
/* 199 */     out.writeInt(this.m_UserNr);
/* 200 */     out.writeInt(this.m_OperationID);
/* 201 */     out.writeInt(this.m_DomainId);
/* 202 */     out.writeObject(this.m_SpecificTime);
/* 203 */     out.writeObject(this.m_Consumers);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\LbsServerEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */