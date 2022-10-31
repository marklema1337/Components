/*    */ package com.lbs.globalization;
/*    */ 
/*    */ import com.lbs.util.JLbsConstants;
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
/*    */ public class JLbsCultureConstants
/*    */   implements ILbsCultureConstants
/*    */ {
/* 18 */   public static int[] SUPPORTED_CULTURES = new int[0];
/*    */ 
/*    */   
/*    */   public static JLbsCultureInfoBase[] prepareCultureObjects() {
/* 22 */     ArrayList<JLbsCultureInfoBase> list = new ArrayList<>();
/*    */ 
/*    */     
/* 25 */     for (int i = 0; i < SUPPORTED_CULTURES.length; i++) {
/*    */       
/* 27 */       JLbsCultureInfoBase culture = null;
/* 28 */       int langID = SUPPORTED_CULTURES[i];
/*    */       
/* 30 */       String langPrefix = ILbsCultureConstants.LANGUAGEPREFIXES[langID];
/* 31 */       if (langID == 31)
/*    */       {
/* 33 */         if (JLbsConstants.DEBUG_RIGHT_TO_LEFT) {
/* 34 */           langPrefix = "TRRL";
/*    */         } else {
/*    */           continue;
/*    */         } 
/*    */       }
/*    */       
/*    */       try {
/* 41 */         String className = "com.lbs.globalization.JLbsCultureInfo" + langPrefix;
/* 42 */         culture = (JLbsCultureInfoBase)Class.forName(className).newInstance();
/*    */       }
/* 44 */       catch (Exception exception) {}
/*    */ 
/*    */ 
/*    */       
/* 48 */       if (culture != null)
/* 49 */         list.add(culture); 
/*    */       continue;
/*    */     } 
/* 52 */     return list.<JLbsCultureInfoBase>toArray(new JLbsCultureInfoBase[list.size()]);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCultureConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */