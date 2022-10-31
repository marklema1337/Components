/*    */ package com.lbs.console;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ public abstract class AppenderProps
/*    */   implements Serializable, Cloneable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_Name;
/*    */   
/*    */   public AppenderProps(String name, int domainID) {
/* 21 */     if (name == null || name.length() == 0)
/* 22 */       throw new IllegalArgumentException("name parameter cannot be empty"); 
/* 23 */     this.m_Name = name;
/* 24 */     this.m_DomainId = domainID;
/*    */   }
/*    */ 
/*    */   
/* 28 */   private int m_DomainId = -1;
/*    */ 
/*    */   
/*    */   public String getName() {
/* 32 */     return this.m_Name;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDomainId() {
/* 37 */     return this.m_DomainId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String[] apply() throws Exception {
/* 45 */     synchronized (LbsAppenderFactory.class) {
/*    */       
/* 47 */       ILbsConsole[] attachedLoggers = LbsConsoleHelper.getAttachedLoggers(getName());
/* 48 */       if (attachedLoggers.length > 0) {
/*    */         
/* 50 */         ILbsAppender oldInstance = attachedLoggers[0].getAppender(getName());
/* 51 */         LbsAppenderFactory.destroyAppender(oldInstance);
/* 52 */         ILbsAppender newInstance = createAppender();
/*    */         
/* 54 */         for (int j = 0; j < attachedLoggers.length; j++)
/* 55 */           attachedLoggers[j].addAppender(newInstance); 
/*    */       } 
/* 57 */       String[] modifiedLoggerNames = new String[attachedLoggers.length];
/* 58 */       for (int i = 0; i < attachedLoggers.length; i++)
/*    */       {
/* 60 */         modifiedLoggerNames[i] = attachedLoggers[i].getName();
/*    */       }
/* 62 */       return modifiedLoggerNames;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 68 */     return this.m_Name;
/*    */   }
/*    */   
/*    */   public abstract Object clone();
/*    */   
/*    */   protected abstract ILbsAppender createAppender() throws IOException;
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\AppenderProps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */