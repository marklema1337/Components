/*    */ package com.lbs.transport;
/*    */ 
/*    */ import com.lbs.profiler.helper.RemoteMethodRequestDetails;
/*    */ import com.lbs.util.ExternalizationUtil;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInput;
/*    */ import java.io.ObjectOutput;
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
/*    */ public class RemoteMethodRequest
/*    */   extends RemoteRequest
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 6332195483559860242L;
/*    */   public String MethodName;
/*    */   public boolean[] ReturnParams;
/*    */   public String StackInfo;
/*    */   public String OpenWindowNames;
/*    */   public boolean newInstance;
/*    */   
/*    */   private void setMethodCallDetails() {
/* 47 */     RemoteMethodRequestDetails requestDetails = new RemoteMethodRequestDetails();
/* 48 */     requestDetails.methodName = this.MethodName;
/* 49 */     requestDetails.setParameters(this.Parameters);
/* 50 */     requestDetails.returnParams = this.ReturnParams;
/* 51 */     requestDetails.sessionCode = this.SessionCode;
/* 52 */     requestDetails.stackInfo = this.StackInfo;
/* 53 */     requestDetails.targetServlet = this.TargetServlet;
/* 54 */     requestDetails.openWindowNames = this.OpenWindowNames;
/* 55 */     requestDetails.newInstance = this.newInstance;
/* 56 */     RemoteMethodRequestDetails.setMethodCallDetails(requestDetails);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeExternal(ObjectOutput out) throws IOException {
/* 61 */     super.writeExternal(out);
/*    */     
/* 63 */     out.writeObject(this.MethodName);
/* 64 */     ExternalizationUtil.writeBooleanArray(this.ReturnParams, out);
/* 65 */     out.writeObject(this.StackInfo);
/* 66 */     out.writeObject(this.OpenWindowNames);
/* 67 */     out.writeBoolean(this.newInstance);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 72 */     super.readExternal(in);
/*    */     
/* 74 */     this.MethodName = (String)in.readObject();
/* 75 */     this.ReturnParams = ExternalizationUtil.readBooleanArray(in);
/* 76 */     this.StackInfo = (String)in.readObject();
/* 77 */     this.OpenWindowNames = (String)in.readObject();
/* 78 */     this.newInstance = in.readBoolean();
/*    */     
/* 80 */     setMethodCallDetails();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\RemoteMethodRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */