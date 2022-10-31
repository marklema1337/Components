/*     */ package com.lbs.localization;
/*     */ 
/*     */ import com.lbs.util.JLbsClientXMLUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsLocalizationConfig
/*     */ {
/*     */   public static Hashtable<String, LbsLocalizationLanguage> ms_LocalizationLanguages;
/*     */   
/*     */   public static void load(String filePath) throws Exception {
/*  27 */     Document document = loadXMLDocument(filePath, true);
/*  28 */     if (document != null) {
/*     */       
/*  30 */       Element root = document.getDocumentElement();
/*  31 */       NodeList languages = root.getElementsByTagName("localization-languages");
/*  32 */       if (languages != null) {
/*     */         
/*  34 */         for (int i = 0; i < languages.getLength(); i++) {
/*     */           
/*  36 */           Element node = (Element)languages.item(i);
/*  37 */           NodeList nodeImpls = node.getElementsByTagName("localization-language");
/*  38 */           if (nodeImpls != null)
/*     */           {
/*  40 */             for (int j = 0; j < nodeImpls.getLength(); j++) {
/*     */               
/*  42 */               LbsLocalizationLanguage language = new LbsLocalizationLanguage();
/*  43 */               ArrayList<String> preferredLanguages = new ArrayList<>();
/*  44 */               Element nodeImpl = (Element)nodeImpls.item(j);
/*     */               
/*  46 */               language.setName(nodeImpl.getAttribute("name"));
/*  47 */               language.setDescription(nodeImpl.getAttribute("description"));
/*  48 */               language.setDatabaseTableName(nodeImpl.getAttribute("database-table-name"));
/*     */               
/*  50 */               NodeList prefLangs = nodeImpl.getElementsByTagName("preferred-languages");
/*  51 */               if (prefLangs != null && prefLangs.getLength() > 0) {
/*     */                 
/*  53 */                 Element plNodeImpl = (Element)prefLangs.item(0);
/*  54 */                 NodeList preferredLangList = plNodeImpl.getElementsByTagName("replace-lang");
/*  55 */                 if (preferredLangList != null) {
/*     */                   
/*  57 */                   for (int k = 0; k < preferredLangList.getLength(); k++) {
/*     */                     
/*  59 */                     Element preferredLang = (Element)preferredLangList.item(k);
/*  60 */                     if (k < 2)
/*  61 */                       preferredLanguages.add(preferredLang.getAttribute("name")); 
/*     */                   } 
/*  63 */                   if (preferredLangList.getLength() == 0)
/*     */                   {
/*  65 */                     preferredLanguages.add("ENUS");
/*  66 */                     preferredLanguages.add("TRTR");
/*     */                   }
/*     */                 
/*     */                 } else {
/*     */                   
/*  71 */                   preferredLanguages.add("ENUS");
/*  72 */                   preferredLanguages.add("TRTR");
/*     */                 }
/*     */               
/*     */               } else {
/*     */                 
/*  77 */                 preferredLanguages.add("ENUS");
/*  78 */                 preferredLanguages.add("TRTR");
/*     */               } 
/*  80 */               language.setPreferredLanguages(preferredLanguages);
/*  81 */               if (ms_LocalizationLanguages == null)
/*  82 */                 ms_LocalizationLanguages = new Hashtable<>(); 
/*  83 */               ms_LocalizationLanguages.put(language.getName(), language);
/*     */             } 
/*     */           }
/*     */         } 
/*     */         
/*  88 */         if (ms_LocalizationLanguages != null && ms_LocalizationLanguages.get("") == null) {
/*     */           
/*  90 */           LbsLocalizationLanguage defaultConfig = new LbsLocalizationLanguage();
/*  91 */           defaultConfig.setName("");
/*  92 */           defaultConfig.setDatabaseTableName("");
/*  93 */           ArrayList<String> defaultPrefLangs = new ArrayList<>();
/*  94 */           defaultPrefLangs.add("ENUS");
/*  95 */           defaultPrefLangs.add("TRTR");
/*  96 */           defaultConfig.setPreferredLanguages(defaultPrefLangs);
/*  97 */           ms_LocalizationLanguages.put("", defaultConfig);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static Document loadXMLDocument(String fileName, boolean validating) throws Exception {
/* 105 */     return JLbsClientXMLUtil.loadXMLDocument(fileName, validating);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/* 112 */       load("D:\\projects\\UNITY_GIT_SET2\\jaf/LogoERP/WebContent/resources/Database\\..\\..\\Config\\System\\LbsLocalizationConfig.xml");
/*     */     }
/* 114 */     catch (Exception exception) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\LbsLocalizationConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */