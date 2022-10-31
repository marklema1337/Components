/*    */ package com.lbs.localization;
/*    */ 
/*    */ import com.lbs.util.JLbsClientXMLUtil;
/*    */ import java.util.Hashtable;
/*    */ import org.w3c.dom.Document;
/*    */ import org.w3c.dom.Element;
/*    */ import org.w3c.dom.NodeList;
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
/*    */ public class LbsPreferredReplaceLangs
/*    */ {
/*    */   public static Hashtable<String, String[]> ms_PrefLangMaps;
/*    */   
/*    */   public static void load(String filePath) throws Exception {
/* 26 */     Document document = loadXMLDocument(filePath, true);
/* 27 */     if (document != null) {
/*    */       
/* 29 */       Element root = document.getDocumentElement();
/* 30 */       NodeList languages = root.getElementsByTagName("preferred-languages");
/* 31 */       if (languages != null) {
/*    */         
/* 33 */         for (int i = 0; i < languages.getLength(); i++) {
/*    */           
/* 35 */           Element node = (Element)languages.item(i);
/* 36 */           NodeList nodeImpls = node.getElementsByTagName("language");
/* 37 */           if (nodeImpls != null)
/*    */           {
/* 39 */             for (int j = 0; j < nodeImpls.getLength(); j++) {
/*    */               
/* 41 */               String languageName = null;
/* 42 */               String[] preferredLanguages = new String[2];
/* 43 */               Element nodeImpl = (Element)nodeImpls.item(j);
/* 44 */               languageName = JLbsLocalizationUtil.ms_LangMaps.get(nodeImpl.getAttribute("name"));
/* 45 */               NodeList preferredLangList = nodeImpl.getElementsByTagName("replace-lang");
/* 46 */               if (preferredLangList != null)
/*    */               {
/* 48 */                 for (int k = 0; k < preferredLangList.getLength(); k++) {
/*    */                   
/* 50 */                   Element preferredLang = (Element)preferredLangList.item(k);
/* 51 */                   if (k < 2)
/* 52 */                     preferredLanguages[k] = JLbsLocalizationUtil.ms_LangMaps.get(preferredLang.getAttribute("name")); 
/*    */                 } 
/*    */               }
/* 55 */               if (languageName != null) {
/*    */                 
/* 57 */                 if (ms_PrefLangMaps == null) {
/* 58 */                   ms_PrefLangMaps = (Hashtable)new Hashtable<>();
/*    */                 }
/* 60 */                 ms_PrefLangMaps.put(languageName, preferredLanguages);
/*    */               } 
/*    */             } 
/*    */           }
/*    */         } 
/*    */         
/* 66 */         if (ms_PrefLangMaps != null && ms_PrefLangMaps.get("") == null)
/*    */         {
/* 68 */           ms_PrefLangMaps.put("", new String[] { "RE_ENGLISHUS", "RE_TURKISHTR" });
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static Document loadXMLDocument(String fileName, boolean validating) throws Exception {
/* 76 */     return JLbsClientXMLUtil.loadXMLDocument(fileName, validating);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\LbsPreferredReplaceLangs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */