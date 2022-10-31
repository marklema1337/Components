/*    */ package com.lbs.transport;
/*    */ 
/*    */ import com.lbs.util.ExternalizationUtil;
/*    */ import java.io.Externalizable;
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
/*    */ public class RemoteRequest
/*    */   implements Serializable, Externalizable
/*    */ {
/*    */   public String SessionCode;
/*    */   public String TargetServlet;
/*    */   public Object[] Parameters;
/*    */   
/*    */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 27 */     this.SessionCode = (String)in.readObject();
/* 28 */     this.TargetServlet = (String)in.readObject();
/*    */     
/* 30 */     this.Parameters = ExternalizationUtil.readArray(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeExternal(ObjectOutput out) throws IOException {
/* 35 */     out.writeObject(this.SessionCode);
/* 36 */     out.writeObject(this.TargetServlet);
/*    */     
/* 38 */     ExternalizationUtil.writeExternal(this.Parameters, out);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\RemoteRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */