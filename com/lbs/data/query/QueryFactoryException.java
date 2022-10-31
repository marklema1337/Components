/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import com.lbs.data.factory.DBErrors;
/*    */ import com.lbs.data.factory.FactoryException;
/*    */ import com.lbs.localization.LbsMessage;
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
/*    */ public class QueryFactoryException
/*    */   extends FactoryException
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public QueryFactoryException(int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/* 28 */     super(listID, stringTag, defaultTemplateMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryFactoryException(int listID, int stringTag, String defaultMessage) {
/* 33 */     super(listID, stringTag, defaultMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryFactoryException(Throwable cause, int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/* 38 */     super(cause, listID, stringTag, defaultTemplateMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryFactoryException(Throwable cause, int listID, int stringTag, String defaultMessage) {
/* 43 */     super(cause, listID, stringTag, defaultMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryFactoryException(int id, int listID, int stringTag, LbsMessage message) {
/* 48 */     super(id, listID, stringTag, message);
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryFactoryException(int id, int listID, int stringTag, String message) {
/* 53 */     super(id, listID, stringTag, message);
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryFactoryException(Exception e) {
/* 58 */     super(e);
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryFactoryException(DBErrors errors) {
/* 63 */     super(errors);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryFactoryException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */