/*    */ package com.lbs.util;
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
/*    */ 
/*    */ public class JLbsTransportMode
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public static transient boolean ENCRYPTION_ON = true;
/*    */   public static transient boolean COMPRESSION_ON = true;
/*    */   public static transient boolean ROBOTMODE = false;
/*    */   private boolean m_EncryptionOn = true;
/*    */   private boolean m_CompressionOn = true;
/*    */   private boolean m_RobotMode = false;
/*    */   
/*    */   public JLbsTransportMode(boolean encryptionOn, boolean compressionOn, boolean robotMode) {
/* 28 */     this.m_EncryptionOn = encryptionOn;
/* 29 */     this.m_CompressionOn = compressionOn;
/* 30 */     this.m_RobotMode = robotMode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyTransportMode() {
/* 35 */     ENCRYPTION_ON = this.m_EncryptionOn;
/* 36 */     COMPRESSION_ON = this.m_CompressionOn;
/* 37 */     ROBOTMODE = this.m_RobotMode;
/* 38 */     if (JLbsConstants.DEBUG && JLbsConstants.LOGLEVEL >= 5)
/* 39 */       System.out.println("Encryption On: " + ENCRYPTION_ON + " Compression On: " + COMPRESSION_ON + " RobotMode: " + 
/* 40 */           ROBOTMODE); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsTransportMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */