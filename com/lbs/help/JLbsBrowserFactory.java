/*    */ package com.lbs.help;
/*    */ 
/*    */ import com.lbs.util.JLbsNameValueMap;
/*    */ import com.lbs.util.JLbsStringUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsBrowserFactory
/*    */ {
/* 16 */   private static JLbsNameValueMap ms_List = new JLbsNameValueMap();
/*    */ 
/*    */   
/*    */   public static void registerBrowser(String name, JLbsWebBrowser browser) {
/* 20 */     if (!JLbsStringUtil.isEmpty(name)) {
/* 21 */       ms_List.setValue(name, browser);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void deregisterBrowser(String name, JLbsWebBrowser browser) {
/* 26 */     if (browser != null && getRegisteredBrowser(name) != browser)
/* 27 */       return;  ms_List.setValue(name, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public static JLbsWebBrowser getRegisteredBrowser(String name) {
/* 32 */     Object o = ms_List.getValue(name);
/* 33 */     if (o instanceof JLbsWebBrowser)
/* 34 */       return (JLbsWebBrowser)o; 
/* 35 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public static JLbsWebBrowser getBrowser(String name) {
/* 40 */     Object o = ms_List.getValue(name);
/* 41 */     if (o instanceof JLbsWebBrowser)
/* 42 */       return (JLbsWebBrowser)o; 
/* 43 */     return new JLbsWebBrowser(name);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static JLbsWebBrowser openURL(String URL, String targetBrowser) {
/*    */     try {
/* 50 */       JLbsWebBrowser browser = getBrowser(targetBrowser);
/* 51 */       browser.setPage(URL);
/* 52 */       if (browser.getState() == 1)
/* 53 */         browser.setState(0); 
/* 54 */       browser.show();
/* 55 */       return browser;
/*    */     }
/* 57 */     catch (Exception e) {
/*    */       
/* 59 */       System.out.println(e);
/*    */       
/* 61 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\help\JLbsBrowserFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */