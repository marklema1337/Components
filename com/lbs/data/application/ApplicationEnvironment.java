/*    */ package com.lbs.data.application;
/*    */ 
/*    */ import com.lbs.data.factory.IObjectFactory;
/*    */ import com.lbs.data.factory.NamedVariables;
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
/*    */ public class ApplicationEnvironment
/*    */   implements IApplicationEnvironment
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 19 */   private NamedVariables m_Values = new NamedVariables();
/*    */   
/*    */   private ApplicationVariables m_Variables;
/*    */ 
/*    */   
/*    */   public ApplicationEnvironment() {}
/*    */ 
/*    */   
/*    */   public ApplicationEnvironment(ApplicationVariables vars, NamedVariables vals) {
/* 28 */     this.m_Values = vals;
/* 29 */     this.m_Variables = vars;
/*    */   }
/*    */ 
/*    */   
/*    */   public ApplicationVariables getDefinitions(IObjectFactory factory) {
/* 34 */     if (this.m_Variables == null)
/*    */     {
/* 36 */       this.m_Variables = factory.getApplicationVariables();
/*    */     }
/* 38 */     return this.m_Variables;
/*    */   }
/*    */ 
/*    */   
/*    */   public NamedVariables getValues() {
/* 43 */     return this.m_Values;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getIntVariable(String varName) {
/* 48 */     Object value = this.m_Values.getObject(varName);
/* 49 */     if (value == null)
/* 50 */       return 0; 
/* 51 */     if (value instanceof Integer)
/*    */     {
/* 53 */       return ((Integer)value).intValue();
/*    */     }
/* 55 */     if (value instanceof String)
/*    */     {
/* 57 */       return Integer.parseInt((String)value);
/*    */     }
/* 59 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getStringVariable(String varName) {
/* 64 */     Object value = this.m_Values.getObject(varName);
/* 65 */     if (value == null)
/* 66 */       return null; 
/* 67 */     return String.valueOf(value);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\application\ApplicationEnvironment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */