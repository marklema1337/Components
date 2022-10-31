/*    */ package com.lbs.xui;
/*    */ 
/*    */ import com.lbs.remoteclient.IClientContext;
/*    */ import com.lbs.transport.RemoteMethodResponse;
/*    */ import java.util.HashMap;
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
/*    */ public class ClientXUICustServices
/*    */ {
/*    */   public static HashMap<String, BOLinkPair[]> collectExtensionData(IClientContext clientContext, String guid, String objName) {
/*    */     try {
/* 23 */       RemoteMethodResponse response = clientContext.callRemoteMethod("SCS", "collectExtensionData", new Object[] { guid, 
/* 24 */             objName });
/*    */       
/* 26 */       if (response != null && response.Result instanceof HashMap) {
/* 27 */         return (HashMap<String, BOLinkPair[]>)response.Result;
/*    */       }
/* 29 */     } catch (Exception e) {
/*    */       
/* 31 */       e.printStackTrace();
/*    */     } 
/* 33 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\xui\ClientXUICustServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */