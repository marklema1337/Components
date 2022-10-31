/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.io.StringReader;
/*     */ import java.util.HashMap;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.InputSource;
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
/*     */ public class XMLParserUtil
/*     */ {
/*     */   public static boolean XML_NODE_INFO = false;
/*  22 */   public static HashMap m_HashMap = new HashMap<>();
/*     */   public static boolean USER_DATA_NOT_SUPPORTED = true;
/*  24 */   public static String PROPERY_NODE_INFO = "XMLInfo";
/*     */ 
/*     */   
/*     */   public static Document loadXMLDocument(String fileName) throws Exception {
/*  28 */     return loadXMLDocument(fileName, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Document loadXMLDocument(String fileName, boolean validating) throws Exception {
/*  33 */     if (!XML_NODE_INFO) {
/*  34 */       return DOMUtil.loadXmlDocument(fileName, validating);
/*     */     }
/*  36 */     ILineNumberedParser parser = null;
/*     */     
/*  38 */     m_HashMap.clear();
/*     */     
/*     */     try {
/*  41 */       parser = new XercesLineNumberedParserNative(fileName);
/*     */     }
/*  43 */     catch (Exception e) {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/*  48 */         parser = new XercesLineNumberedParser(fileName);
/*     */       }
/*  50 */       catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  56 */     if (parser != null) {
/*     */       
/*  58 */       parser.parse(fileName);
/*  59 */       return parser.getDocument();
/*     */     } 
/*  61 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Document loadXMLDocument(String fileName, String xmlText) throws Exception {
/*  66 */     if (!XML_NODE_INFO) {
/*  67 */       return DOMUtil.loadXmlDocument(xmlText);
/*     */     }
/*  69 */     ILineNumberedParser parser = null;
/*     */     
/*  71 */     m_HashMap.clear();
/*     */     
/*     */     try {
/*  74 */       parser = new XercesLineNumberedParserNative(fileName);
/*     */     }
/*  76 */     catch (Exception e) {
/*     */ 
/*     */       
/*     */       try {
/*  80 */         parser = new XercesLineNumberedParser(fileName);
/*     */       }
/*  82 */       catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     if (parser != null) {
/*     */       
/*  90 */       InputSource inputSource = new InputSource(new StringReader(xmlText));
/*  91 */       parser.parse(inputSource);
/*  92 */       return parser.getDocument();
/*     */     } 
/*     */     
/*  95 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void finishXMLDocument(String fileName, Document document) {
/* 100 */     m_HashMap.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public static XMLNodeInfo getNodeInfo(Node node) {
/* 105 */     if (!XML_NODE_INFO) {
/* 106 */       return null;
/*     */     }
/* 108 */     if (USER_DATA_NOT_SUPPORTED) {
/*     */       
/* 110 */       XMLNodeInfo info = (XMLNodeInfo)m_HashMap.get(node);
/* 111 */       while (node != null && info == null) {
/*     */         
/* 113 */         node = node.getParentNode();
/* 114 */         info = (XMLNodeInfo)m_HashMap.get(node);
/*     */       } 
/*     */       
/* 117 */       return info;
/*     */     } 
/*     */ 
/*     */     
/* 121 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void putNodeInfo(Node node, XMLNodeInfo info) {
/* 126 */     if (!XML_NODE_INFO) {
/*     */       return;
/*     */     }
/* 129 */     if (node != null) {
/*     */       
/*     */       try {
/*     */         
/* 133 */         if (USER_DATA_NOT_SUPPORTED) {
/* 134 */           m_HashMap.put(node, info);
/*     */         
/*     */         }
/*     */       }
/* 138 */       catch (Exception e) {
/*     */         
/* 140 */         USER_DATA_NOT_SUPPORTED = true;
/* 141 */         m_HashMap.put(node, info);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void checkNodeInfo(Node node) {
/* 148 */     XMLNodeInfo info = null;
/*     */     
/* 150 */     Node x = node;
/* 151 */     while (info == null && x != null) {
/*     */       
/* 153 */       info = getNodeInfo(x);
/* 154 */       x = x.getParentNode();
/*     */     } 
/*     */     
/* 157 */     if (info == null) {
/*     */       
/* 159 */       System.err.println("No node info for node " + node);
/*     */       
/* 161 */       x = node;
/* 162 */       while (info == null && x != null) {
/*     */         
/* 164 */         info = getNodeInfo(x);
/* 165 */         x = x.getParentNode();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\XMLParserUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */