/*    */ package com.lbs.contract;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Enumeration;
/*    */ import java.util.Hashtable;
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
/*    */ public class ContractImplementationTmp
/*    */   extends ContractImplementation
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 20 */   private ArrayList<ContractImplInclude> m_Includes = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public ArrayList<ContractImplInclude> getIncludes() {
/* 24 */     return this.m_Includes;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object clone() throws CloneNotSupportedException {
/* 30 */     ContractImplementationTmp clone = (ContractImplementationTmp)super.clone();
/* 31 */     clone.m_Includes = new ArrayList<>();
/* 32 */     for (ContractImplInclude include : this.m_Includes)
/*    */     {
/* 34 */       clone.m_Includes.add((ContractImplInclude)include.clone());
/*    */     }
/* 36 */     return clone;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ContractImplementation processForTemplates() throws ContractException {
/*    */     try {
/* 44 */       ContractImplementation implementation = new ContractImplementation((ContractImplementation)super.clone());
/* 45 */       for (ContractImplInclude include : this.m_Includes) {
/*    */         
/* 47 */         ContractImplTemplate template = ContractImplTemplate.findAndProcessForTemplate(include, "contract implementation '" + 
/* 48 */             getIdentifier() + "'");
/* 49 */         Hashtable<String, String> templateProps = template.getProperties();
/* 50 */         Enumeration<String> keys = templateProps.keys();
/* 51 */         while (keys.hasMoreElements()) {
/*    */           
/* 53 */           String key = keys.nextElement();
/* 54 */           String value = templateProps.get(key);
/* 55 */           if (!implementation.getProperties().containsKey(key))
/* 56 */             implementation.getProperties().put(key, value); 
/*    */         } 
/*    */       } 
/* 59 */       return implementation;
/*    */     }
/* 61 */     catch (CloneNotSupportedException e) {
/*    */       
/* 63 */       throw new ContractException(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void fillAllIncludes(ArrayList<ContractImplInclude> all) {
/* 70 */     if (this.m_Includes != null)
/* 71 */       all.addAll(this.m_Includes); 
/* 72 */     super.fillAllIncludes(all);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractImplementationTmp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */