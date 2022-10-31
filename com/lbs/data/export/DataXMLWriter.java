/*     */ package com.lbs.data.export;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.platform.interfaces.IDataWriter;
/*     */ import com.lbs.util.StringUtil;
/*     */ import com.lbs.util.xml.InvalidXMLException;
/*     */ import com.lbs.util.xml.XMLWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataXMLWriter
/*     */   implements IDataWriter
/*     */ {
/*     */   private XMLWriter m_Writer;
/*     */   
/*     */   public DataXMLWriter(XMLWriter writer) {
/*  21 */     this.m_Writer = writer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeObjectStart(String objectName, String customization, String nameSpace, String className) throws IllegalStateException, IOException {
/*  27 */     this.m_Writer.startTag(objectName);
/*  28 */     if (!StringUtil.isEmpty(customization))
/*  29 */       this.m_Writer.attribute("cust-guid", customization); 
/*  30 */     if (!StringUtil.isEmpty(nameSpace)) {
/*  31 */       this.m_Writer.attribute("xmlns:" + nameSpace, className);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeObjectEnd() throws IllegalStateException, IOException {
/*  37 */     this.m_Writer.endTag();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeProperty(String propertyName, String propertyValue, Hashtable<String, String> propertyAttributes) throws IllegalStateException, IllegalArgumentException, IOException {
/*  44 */     this.m_Writer.startTag(propertyName);
/*  45 */     this.m_Writer.attribute("value", propertyValue);
/*  46 */     if (propertyAttributes != null) {
/*     */       
/*  48 */       Enumeration<String> keys = propertyAttributes.keys();
/*  49 */       while (keys.hasMoreElements()) {
/*     */         
/*  51 */         String key = keys.nextElement();
/*  52 */         String value = propertyAttributes.get(key);
/*  53 */         this.m_Writer.attribute(key, value);
/*     */       } 
/*     */     } 
/*  56 */     this.m_Writer.endTag();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeInnerObjectStart(String propertyName) throws IllegalStateException, IllegalArgumentException, IOException {
/*  62 */     this.m_Writer.startTag(propertyName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeInnerObjectEnd(String propertyName) throws IllegalStateException, NoSuchElementException, IOException {
/*  68 */     this.m_Writer.endTag(propertyName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeComment(String comment) throws IllegalStateException, IllegalArgumentException, InvalidXMLException, IOException {
/*  75 */     this.m_Writer.comment(comment);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void pcdata(String xml) throws IllegalStateException, IllegalArgumentException, InvalidXMLException, IOException {
/*  81 */     this.m_Writer.pcdata(xml);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeParameterStartTag(String string) {
/*     */     try {
/*  88 */       this.m_Writer.startTag(string);
/*     */     }
/*  90 */     catch (Exception e) {
/*     */       
/*  92 */       LbsConsole.getLogger(getClass()).error(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endTag() throws IllegalStateException, IOException {
/*  99 */     this.m_Writer.endTag();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endDocument() throws IllegalStateException, IOException {
/* 105 */     this.m_Writer.endDocument();
/*     */   }
/*     */   
/*     */   public void closeFile() {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\DataXMLWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */