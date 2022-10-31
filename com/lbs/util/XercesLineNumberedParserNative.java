/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.sun.org.apache.xerces.internal.parsers.DOMParser;
/*     */ import com.sun.org.apache.xerces.internal.xni.Augmentations;
/*     */ import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
/*     */ import com.sun.org.apache.xerces.internal.xni.QName;
/*     */ import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
/*     */ import com.sun.org.apache.xerces.internal.xni.XMLLocator;
/*     */ import com.sun.org.apache.xerces.internal.xni.XNIException;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXNotRecognizedException;
/*     */ import org.xml.sax.SAXNotSupportedException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XercesLineNumberedParserNative
/*     */   extends DOMParser
/*     */   implements ILineNumberedParser
/*     */ {
/*     */   private XMLLocator m_Locator;
/*     */   private String m_FileName;
/*     */   
/*     */   public XercesLineNumberedParserNative(String fileName) {
/*  32 */     this.m_FileName = fileName;
/*     */     
/*     */     try {
/*  35 */       setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);
/*  36 */       setErrorHandler((ErrorHandler)null);
/*     */     }
/*  38 */     catch (SAXNotRecognizedException e) {
/*     */       
/*  40 */       e.printStackTrace();
/*     */     }
/*  42 */     catch (SAXNotSupportedException e) {
/*     */       
/*  44 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void startElement(QName elementQName, XMLAttributes attrList, Augmentations augs) throws XNIException {
/*  50 */     super.startElement(elementQName, attrList, augs);
/*     */     
/*  52 */     Node node = null;
/*     */     
/*     */     try {
/*  55 */       node = (Node)getProperty("http://apache.org/xml/properties/dom/current-element-node");
/*     */     }
/*  57 */     catch (SAXException e) {
/*     */       
/*  59 */       e.printStackTrace();
/*     */     } 
/*  61 */     putNodeInfo(node);
/*     */   }
/*     */ 
/*     */   
/*     */   private void putNodeInfo(Node node) {
/*  66 */     if (!XMLParserUtil.XML_NODE_INFO) {
/*     */       return;
/*     */     }
/*  69 */     if (node != null) {
/*     */       
/*  71 */       XMLNodeInfo info = new XMLNodeInfo(this.m_FileName, this.m_Locator.getLineNumber());
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/*  76 */         info.setColumnNumber(this.m_Locator.getColumnNumber());
/*     */       }
/*  78 */       catch (Exception e) {
/*     */         
/*  80 */         LbsConsole.getLogger(getClass()).error(e, e);
/*     */       } 
/*     */       
/*  83 */       XMLParserUtil.putNodeInfo(node, info);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDocument(XMLLocator locator, String encoding, NamespaceContext namespaceContext, Augmentations augs) throws XNIException {
/*  91 */     super.startDocument(locator, encoding, namespaceContext, augs);
/*  92 */     this.m_Locator = locator;
/*  93 */     Node node = null;
/*     */     
/*     */     try {
/*  96 */       node = (Node)getProperty("http://apache.org/xml/properties/dom/current-element-node");
/*     */     }
/*  98 */     catch (SAXException e) {
/*     */       
/* 100 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 103 */     putNodeInfo(node);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\XercesLineNumberedParserNative.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */