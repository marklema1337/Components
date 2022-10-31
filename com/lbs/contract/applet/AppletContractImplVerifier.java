/*    */ package com.lbs.contract.applet;
/*    */ 
/*    */ import com.lbs.contract.ContractException;
/*    */ import com.lbs.contract.ContractImplementation;
/*    */ import com.lbs.contract.IContractImplVerifier;
/*    */ import com.lbs.util.StringUtil;
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
/*    */ 
/*    */ 
/*    */ public class AppletContractImplVerifier
/*    */   implements IContractImplVerifier, IAppletContractConstants
/*    */ {
/*    */   public void verifyContractImplementation(ContractImplementation implementation) throws ContractException {
/* 24 */     Hashtable<String, String> properties = implementation.getProperties();
/* 25 */     String className = properties.get("ClassName");
/* 26 */     if (StringUtil.isEmpty(className)) {
/* 27 */       throw new ContractException("'ClassName' implementation property is mandatory for 'applet' contract implementations : " + 
/* 28 */           implementation.getIdentifier() + 
/* 29 */           "!");
/*    */     }
/*    */     
/*    */     try {
/* 33 */       Class<?> c = Class.forName(className);
/* 34 */       Object instance = c.newInstance();
/* 35 */       if (!(instance instanceof IContractExecutor))
/* 36 */         throw new ContractException("'applet' contract implementations should implement '" + 
/* 37 */             IContractExecutor.class.getName() + "' interface!" + " Contract implementation class named '" + className + 
/* 38 */             "' is not implementing this interface!"); 
/* 39 */       IContractExecutor executor = (IContractExecutor)instance;
/* 40 */       executor.checkImplementationProps(properties);
/*    */     }
/* 42 */     catch (Exception e) {
/*    */       
/* 44 */       throw new ContractException("Exception occurred in contract implementation verification '" + implementation + "'", e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\applet\AppletContractImplVerifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */