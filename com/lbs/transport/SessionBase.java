/*    */ package com.lbs.transport;
/*    */ 
/*    */ import com.lbs.rmi.JLbsHttpInvoker;
/*    */ import java.io.Serializable;
/*    */ import javax.crypto.Cipher;
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
/*    */ public class SessionBase
/*    */   implements ISessionBase, Serializable
/*    */ {
/*    */   protected String m_SessionCode;
/*    */   protected Object m_LocalCryptoParam;
/*    */   protected Object m_RemoteCryptoParam;
/*    */   protected Object m_SessionData;
/*    */   private Cipher m_DecryptionCipher;
/*    */   private Cipher m_EncryptionCipher;
/*    */   
/*    */   public SessionBase(LoginResult loginRes, Object localCryptoParam) {
/* 29 */     if (loginRes != null) {
/*    */       
/* 31 */       this.m_SessionCode = loginRes.SessionCode;
/* 32 */       this.m_LocalCryptoParam = localCryptoParam;
/* 33 */       this.m_RemoteCryptoParam = loginRes.RemoteCryptoData;
/* 34 */       this.m_SessionData = loginRes.RemoteObject;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public SessionBase(String sessionCode, Object localCryptoParam, Object remoteCryptoParam, Object sessionData) {
/* 40 */     this.m_SessionCode = sessionCode;
/* 41 */     this.m_LocalCryptoParam = localCryptoParam;
/* 42 */     this.m_RemoteCryptoParam = remoteCryptoParam;
/* 43 */     this.m_SessionData = sessionData;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSessionCode() {
/* 51 */     return this.m_SessionCode;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getRemoteCryptoParam() {
/* 59 */     return this.m_RemoteCryptoParam;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getLocalCryptoParam() {
/* 67 */     return this.m_LocalCryptoParam;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getSessionData() {
/* 75 */     return this.m_SessionData;
/*    */   }
/*    */ 
/*    */   
/*    */   public Cipher getDecryptionCipher() {
/* 80 */     Cipher cipher = JLbsHttpInvoker.getDecryptionCipher(this.m_DecryptionCipher, (byte[])getLocalCryptoParam());
/* 81 */     if (JLbsHttpInvoker.CACHE_CIPHERS) {
/* 82 */       this.m_DecryptionCipher = cipher;
/*    */     }
/* 84 */     return cipher;
/*    */   }
/*    */ 
/*    */   
/*    */   public Cipher getEncryptionCipher() {
/* 89 */     Cipher cipher = JLbsHttpInvoker.getEncryptionCipher(this.m_EncryptionCipher, (byte[])getRemoteCryptoParam());
/* 90 */     if (JLbsHttpInvoker.CACHE_CIPHERS) {
/* 91 */       this.m_EncryptionCipher = cipher;
/*    */     }
/* 93 */     return cipher;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\SessionBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */