/*    */ package com.lbs.message;
/*    */ 
/*    */ import com.lbs.localization.ILocalizationServices;
/*    */ import com.lbs.util.LbsContClassGeneratorBase;
/*    */ import java.util.ArrayList;
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
/*    */ public class LbsMessageConstantsClassGenerator
/*    */   extends LbsContClassGeneratorBase
/*    */ {
/*    */   private ILocalizationServices m_LocalService;
/*    */   
/*    */   public void generateMessageConstantsClass(ILocalizationServices localService, String targetFolder, String packageName, String className, String module) throws Exception {
/* 24 */     this.m_LocalService = localService;
/*    */     
/* 26 */     initializeWriter(targetFolder, packageName, className);
/* 27 */     classDeclaration(null, null, true);
/*    */     
/* 29 */     generateConstants(module);
/*    */     
/* 31 */     finish();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void generateConstants(String module) {
/* 37 */     ArrayList<String> generated = new ArrayList<>();
/* 38 */     String[] constants = this.m_LocalService.getMessageConstantIds(module);
/* 39 */     if (constants != null)
/*    */     {
/* 41 */       for (int i = 0; i < constants.length; i++) {
/*    */         
/* 43 */         if (!generated.contains(constants[i])) {
/*    */           
/* 45 */           println("public static final String " + constants[i] + " = \"" + constants[i] + "\";");
/* 46 */           generated.add(constants[i]);
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\message\LbsMessageConstantsClassGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */