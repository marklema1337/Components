/*    */ package com.lbs.transport;
/*    */ 
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
/*    */ public class TransportWatchedData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String sUri;
/*    */   private byte[] sendPrefix;
/*    */   private byte[] sendData;
/*    */   private byte[] returnData;
/*    */   private boolean bPublicInfo;
/*    */   private RemoteMethodRequest RMIRequest;
/*    */   private RemoteMethodResponse RMIResponse;
/*    */   
/*    */   public String getsUri() {
/* 28 */     return this.sUri;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setsUri(String sUri) {
/* 33 */     this.sUri = sUri;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getSendPrefix() {
/* 38 */     return this.sendPrefix;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSendPrefix(byte[] sendPrefix) {
/* 43 */     this.sendPrefix = sendPrefix;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getSendData() {
/* 48 */     return this.sendData;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSendData(byte[] sendData) {
/* 53 */     this.sendData = sendData;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isbPublicInfo() {
/* 58 */     return this.bPublicInfo;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setbPublicInfo(boolean bPublicInfo) {
/* 63 */     this.bPublicInfo = bPublicInfo;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getReturnData() {
/* 68 */     return this.returnData;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setReturnData(byte[] returnData) {
/* 73 */     this.returnData = returnData;
/*    */   }
/*    */ 
/*    */   
/*    */   public RemoteMethodRequest getRMIRequest() {
/* 78 */     return this.RMIRequest;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRMIRequest(RemoteMethodRequest rMIRequest) {
/* 83 */     this.RMIRequest = rMIRequest;
/*    */   }
/*    */ 
/*    */   
/*    */   public RemoteMethodResponse getRMIResponse() {
/* 88 */     return this.RMIResponse;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRMIResponse(RemoteMethodResponse rMIResponse) {
/* 93 */     this.RMIResponse = rMIResponse;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\TransportWatchedData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */