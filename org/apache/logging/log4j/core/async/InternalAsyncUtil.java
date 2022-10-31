/*    */ package org.apache.logging.log4j.core.async;
/*    */ 
/*    */ import org.apache.logging.log4j.core.util.Constants;
/*    */ import org.apache.logging.log4j.message.AsynchronouslyFormattable;
/*    */ import org.apache.logging.log4j.message.Message;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InternalAsyncUtil
/*    */ {
/*    */   public static Message makeMessageImmutable(Message msg) {
/* 40 */     if (msg != null && !canFormatMessageInBackground(msg)) {
/* 41 */       msg.getFormattedMessage();
/*    */     }
/* 43 */     return msg;
/*    */   }
/*    */   
/*    */   private static boolean canFormatMessageInBackground(Message message) {
/* 47 */     return (Constants.FORMAT_MESSAGES_IN_BACKGROUND || message
/* 48 */       .getClass().isAnnotationPresent((Class)AsynchronouslyFormattable.class));
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\async\InternalAsyncUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */