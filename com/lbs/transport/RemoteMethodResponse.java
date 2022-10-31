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
/*    */ public class RemoteMethodResponse
/*    */   implements Serializable, Externalizable
/*    */ {
/*    */   public Object Result;
/*    */   public LbsServerEventList Events;
/*    */   public Object[] ReturnParameters;
/*    */   
/*    */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 27 */     this.Result = in.readObject();
/* 28 */     this.Events = LbsServerEventList.readExternal(in);
/* 29 */     this.ReturnParameters = ExternalizationUtil.readArray(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeExternal(ObjectOutput out) throws IOException {
/* 34 */     out.writeObject(this.Result);
/* 35 */     LbsServerEventList.writeExternal(this.Events, out);
/* 36 */     ExternalizationUtil.writeExternal(this.ReturnParameters, out);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\RemoteMethodResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */