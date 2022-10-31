/*    */ package com.lbs.data.objects;
/*    */ 
/*    */ import com.lbs.platform.interfaces.ILbsGrammarGenerator;
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
/*    */ public class ChildGrammarGenerator
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_PropertyName;
/*    */   private ILbsGrammarGenerator m_Child;
/*    */   
/*    */   public ChildGrammarGenerator(String propertyName, ILbsGrammarGenerator child) {
/* 26 */     this.m_PropertyName = propertyName;
/* 27 */     this.m_Child = child;
/*    */   }
/*    */ 
/*    */   
/*    */   public ILbsGrammarGenerator getChild() {
/* 32 */     return this.m_Child;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPropertyName() {
/* 37 */     return this.m_PropertyName;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\ChildGrammarGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */