/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.xml.sax.SAXException;
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
/*     */ public class JLbsClientXMLUtil
/*     */ {
/*     */   public static JLbsStringList getModeList(JLbsStringList allModes, String xml) {
/*  31 */     Document doc = parseXML(xml);
/*  32 */     if (doc != null) {
/*     */       
/*  34 */       JLbsStringList list = new JLbsStringList();
/*  35 */       Element customization = doc.getDocumentElement();
/*  36 */       NodeList nodeList = customization.getElementsByTagName("Mode");
/*  37 */       if (nodeList != null) {
/*     */ 
/*     */         
/*  40 */         for (int i = 0; i < nodeList.getLength(); i++) {
/*     */           
/*  42 */           Node mode = nodeList.item(i);
/*  43 */           if (mode instanceof Element) {
/*     */             
/*  45 */             Element elementMode = (Element)mode;
/*  46 */             int id = JLbsConvertUtil.str2IntDef(elementMode.getAttribute("Id"), -1);
/*  47 */             int modeNr = JLbsConvertUtil.str2IntDef(elementMode.getAttribute("Mode-Nr"), -1);
/*  48 */             if (id != -1 && modeNr != -1)
/*     */             {
/*  50 */               if (allModes.getValueAtTag(id) != null)
/*  51 */                 list.add(allModes.getValueAtTag(id), modeNr);  } 
/*     */           } 
/*     */         } 
/*  54 */         return list;
/*     */       } 
/*     */     } 
/*  57 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getModuleIdByModeID(String xml, int modeId) {
/*  62 */     Document doc = parseXML(xml);
/*  63 */     if (doc != null) {
/*     */       
/*  65 */       Element customization = doc.getDocumentElement();
/*  66 */       NodeList nodeList = customization.getElementsByTagName("Mode");
/*  67 */       if (nodeList != null && nodeList.getLength() > 0) {
/*     */ 
/*     */         
/*  70 */         for (int i = 0; i < nodeList.getLength(); i++) {
/*     */           
/*  72 */           Node mode = nodeList.item(i);
/*  73 */           if (mode instanceof Element) {
/*     */             
/*  75 */             Element elementMode = (Element)mode;
/*  76 */             int j = JLbsConvertUtil.str2IntDef(elementMode.getAttribute("Id"), -1);
/*  77 */             int k = JLbsConvertUtil.str2IntDef(elementMode.getAttribute("Mode-Nr"), -1);
/*  78 */             int m = JLbsConvertUtil.str2IntDef(elementMode.getAttribute("Module-Id"), -1);
/*  79 */             if (modeId == k) {
/*  80 */               return String.valueOf(j) + "_" + m;
/*     */             }
/*     */           } 
/*     */         } 
/*  84 */         return "";
/*     */       } 
/*     */ 
/*     */       
/*  88 */       NamedNodeMap attributes = customization.getAttributes();
/*  89 */       int modeNr = -1;
/*  90 */       int moduleId = -1;
/*  91 */       int id = JLbsConvertUtil.str2IntDef(attributes.getNamedItem("Id").getNodeValue(), -1);
/*  92 */       if (attributes.getNamedItem("Mode-Nr") != null)
/*  93 */         modeNr = JLbsConvertUtil.str2IntDef(attributes.getNamedItem("Mode-Nr").getNodeValue(), -1); 
/*  94 */       if (attributes.getNamedItem("Module-Id") != null)
/*  95 */         moduleId = JLbsConvertUtil.str2IntDef(attributes.getNamedItem("Module-Id").getNodeValue(), -1); 
/*  96 */       return String.valueOf(id) + "_" + moduleId;
/*     */     } 
/*     */     
/*  99 */     return "";
/*     */   }
/*     */   
/*     */   public static JLbsStringList getModuleAndModeList(String xml) {
/* 103 */     Document doc = parseXML(xml);
/* 104 */     if (doc != null) {
/*     */       
/* 106 */       JLbsStringList list = new JLbsStringList();
/* 107 */       Element customization = doc.getDocumentElement();
/* 108 */       NodeList nodeList = customization.getElementsByTagName("Mode");
/* 109 */       if (nodeList != null) {
/*     */ 
/*     */         
/* 112 */         for (int i = 0; i < nodeList.getLength(); i++) {
/*     */           
/* 114 */           Node mode = nodeList.item(i);
/* 115 */           if (mode instanceof Element) {
/*     */             
/* 117 */             Element elementMode = (Element)mode;
/* 118 */             int id = JLbsConvertUtil.str2IntDef(elementMode.getAttribute("Id"), -1);
/* 119 */             int modeNr = JLbsConvertUtil.str2IntDef(elementMode.getAttribute("Mode-Nr"), -1);
/* 120 */             int moduleId = JLbsConvertUtil.str2IntDef(elementMode.getAttribute("Module-Id"), -1);
/* 121 */             if (id != -1 && modeNr != -1)
/*     */             {
/* 123 */               list.add(String.valueOf(moduleId) + "," + modeNr); } 
/*     */           } 
/*     */         } 
/* 126 */         return list;
/*     */       } 
/*     */     } 
/* 129 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Document parseXML(String xml) {
/* 134 */     File xmlFile = null;
/*     */     
/*     */     try {
/* 137 */       String encoding = findXMLEncoding(xml);
/* 138 */       xmlFile = File.createTempFile("parse", ".xml");
/* 139 */       FileOutputStream fout = new FileOutputStream(xmlFile);
/* 140 */       fout.write(xml.getBytes(encoding));
/* 141 */       fout.flush();
/* 142 */       fout.close();
/*     */       
/* 144 */       DocumentBuilderFactory domFactory = getDomFactory();
/* 145 */       DocumentBuilder domBuilder = domFactory.newDocumentBuilder();
/* 146 */       Document doc = domBuilder.parse(xmlFile.getAbsoluteFile());
/*     */       
/* 148 */       return doc;
/*     */     }
/* 150 */     catch (IOException e) {
/*     */       
/* 152 */       e.printStackTrace();
/*     */     }
/* 154 */     catch (SAXException e) {
/*     */       
/* 156 */       e.printStackTrace();
/*     */     }
/* 158 */     catch (ParserConfigurationException e) {
/*     */       
/* 160 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/* 164 */       if (xmlFile != null)
/*     */       {
/* 166 */         xmlFile.delete();
/*     */       }
/*     */     } 
/* 169 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static DocumentBuilderFactory getDomFactory() {
/*     */     try {
/* 176 */       Class<?> xercesInternalClass = Class.forName("com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
/* 177 */       return (DocumentBuilderFactory)xercesInternalClass.newInstance();
/*     */     }
/* 179 */     catch (Exception e) {
/*     */ 
/*     */       
/*     */       try {
/* 183 */         Class<?> xercesClass = Class.forName("org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
/* 184 */         return (DocumentBuilderFactory)xercesClass.newInstance();
/*     */       }
/* 186 */       catch (Exception e2) {
/*     */ 
/*     */         
/*     */         try {
/* 190 */           Class<?> thermoClass = Class.forName("com.hp.hpl.thermopylae.DocumentBuilderFactoryImpl");
/* 191 */           return (DocumentBuilderFactory)thermoClass.newInstance();
/*     */         }
/* 193 */         catch (Exception exception) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 199 */           return null;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   } public static Document loadXMLDocument(String fileName, boolean validating) throws Exception {
/* 204 */     DocumentBuilderFactory factory = getDomFactory();
/* 205 */     if (factory == null)
/* 206 */       return null; 
/* 207 */     Document document = null;
/* 208 */     factory.setValidating(validating);
/* 209 */     factory.setExpandEntityReferences(validating);
/* 210 */     DocumentBuilder parser = factory.newDocumentBuilder();
/* 211 */     document = parser.parse(fileName);
/* 212 */     return document;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String findXMLEncoding(String xml) {
/* 217 */     String encoding = "";
/* 218 */     String search = "encoding=";
/* 219 */     int idx = xml.indexOf(search);
/* 220 */     if (idx >= 0) {
/*     */       
/* 222 */       idx += search.length() + 1;
/* 223 */       int endIdx = xml.indexOf('"', idx);
/* 224 */       if (endIdx >= 0 && endIdx > idx)
/* 225 */         encoding = xml.substring(idx, endIdx); 
/*     */     } 
/* 227 */     if (JLbsStringUtil.isEmpty(encoding))
/* 228 */       encoding = "UTF-8"; 
/* 229 */     return encoding;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsClientXMLUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */