/*    */ package com.lbs.contract;
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
/*    */ public class ContractImplTemplate
/*    */   extends ContractImplGroup
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public void initialize() throws ContractException {
/* 20 */     processForTemplates();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ContractImplTemplate findAndProcessForTemplate(ContractImplInclude include, String description) throws ContractException {
/*    */     try {
/* 28 */       ContractImplTemplate template = ContractSchemaRegistry.getApplicationContainer().findTemplate(include.getTemplateName());
/* 29 */       if (template == null)
/* 30 */         throw new ContractException("Cannot find template named '" + include.getTemplateName() + "' in " + 
/* 31 */             description); 
/* 32 */       template = (ContractImplTemplate)template.clone();
/* 33 */       template.processVariableSubstitutions(include.getVarSubstitutions());
/* 34 */       return template;
/*    */     }
/* 36 */     catch (CloneNotSupportedException e) {
/*    */       
/* 38 */       throw new ContractException(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractImplTemplate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */