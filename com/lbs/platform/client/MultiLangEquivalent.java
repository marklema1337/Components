/*    */ package com.lbs.platform.client;
/*    */ 
/*    */ import com.lbs.globalization.ILbsCultureInfo;
/*    */ import com.lbs.globalization.JLbsCultureInfoBase;
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
/*    */ 
/*    */ 
/*    */ public class MultiLangEquivalent
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_Language;
/*    */   private String m_Equivalent;
/*    */   private String m_LanguageName;
/*    */   
/*    */   public String getEquivalent() {
/* 30 */     return this.m_Equivalent;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLanguage() {
/* 38 */     return this.m_Language;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setEquivalent(String equiv) {
/* 44 */     this.m_Equivalent = equiv;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLanguage(String lang) {
/* 49 */     this.m_Language = lang;
/*    */     
/* 51 */     ILbsCultureInfo culture = JLbsCultureInfoBase.createInstance(lang);
/* 52 */     if (culture.getLocale() != null) {
/* 53 */       this.m_LanguageName = culture.getLocale().getDisplayLanguage();
/*    */     } else {
/* 55 */       this.m_LanguageName = lang;
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getLanguageName() {
/* 60 */     return this.m_LanguageName;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\client\MultiLangEquivalent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */