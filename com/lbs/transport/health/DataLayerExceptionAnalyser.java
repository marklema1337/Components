/*    */ package com.lbs.transport.health;
/*    */ 
/*    */ import com.lbs.data.factory.DBError;
/*    */ import com.lbs.data.factory.DBErrors;
/*    */ import com.lbs.data.factory.FactoryException;
/*    */ import com.lbs.util.StringUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataLayerExceptionAnalyser
/*    */   implements TransportExceptionAnalyser
/*    */ {
/*    */   private ArrayList<Pattern> m_Patterns;
/*    */   
/*    */   public DataLayerExceptionAnalyser() {
/* 19 */     this.m_Patterns = new ArrayList<>();
/*    */     
/* 21 */     this.m_Patterns.add(Pattern.compile(".*SQLSTATE : 08.*", 32));
/*    */     
/* 23 */     this.m_Patterns.add(Pattern.compile(".*CannotAcquireResourceException.*", 32));
/*    */     
/* 25 */     this.m_Patterns.add(Pattern.compile(".*jdbc.*CommunicationsException.*", 32));
/*    */     
/* 27 */     this.m_Patterns.add(Pattern.compile(".*ORA-17008.*", 32));
/*    */     
/* 29 */     this.m_Patterns.add(Pattern.compile(".*ORA-17002.*", 32));
/*    */     
/* 31 */     this.m_Patterns.add(Pattern.compile(".*ORA-17016.*", 32));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isTransportBlocker(Throwable t) {
/* 37 */     if (t instanceof FactoryException) {
/*    */       
/* 39 */       FactoryException e = (FactoryException)t;
/* 40 */       DBErrors errors = e.getErrors();
/* 41 */       if (errors != null)
/*    */       {
/* 43 */         return analyseDBErrors(errors);
/*    */       }
/*    */     } 
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean analyseDBErrors(DBErrors errors) {
/* 51 */     for (DBError error : errors) {
/*    */       
/* 53 */       String message = error.getMessage();
/* 54 */       if (!StringUtil.isEmpty(message))
/*    */       {
/* 56 */         for (Pattern pattern : this.m_Patterns) {
/*    */           
/* 58 */           Matcher matcher = pattern.matcher(message);
/* 59 */           if (matcher.matches())
/* 60 */             return true; 
/*    */         } 
/*    */       }
/*    */     } 
/* 64 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\health\DataLayerExceptionAnalyser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */