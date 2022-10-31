/*     */ package com.lbs.util.xml;
/*     */ 
/*     */ import com.lbs.util.ILbsDataExchangeWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
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
/*     */ public class JLbsDataExchangeWriterJson
/*     */   implements ILbsDataExchangeWriter
/*     */ {
/*  20 */   public HashMap<String, String> m_PropertyResourceMap = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeObjectHeader(String startTag, String state, String type, String customization, String packageName, String nameSpace, String className, boolean isCustom) throws IllegalStateException, IllegalArgumentException, IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeObjectProperty(String name, String value) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePropertyStartTag(String string) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePropertyEndTag(String string) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineBreak(LineBreak lineBreak) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeLinkedObjectStartTag(String string) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeLinkedObjects() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeLinkedObjectEndTag(String string) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeCollectionsStartTag(String string) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeCollections() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeCollectionsEndTag(String string) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeExtensionsStartTag(String string) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeExtensions() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeExtensionsEndTag(String string) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePropertyResolverStartTag(String string) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeResolvers() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePropertyResolverEndTag(String string) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeParameterStartTag(String string) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeParameterEndTag(String string) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeObjectFooter(String string) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LineBreak getLineBreak() {
/* 147 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pcdata(String string) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIndentation(String string) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIndentation() {
/* 165 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeComment(String comment) {
/* 171 */     if (comment != null) {
/*     */       
/* 173 */       String[] comments = comment.split("-");
/* 174 */       if (comments.length == 3)
/*     */       {
/* 176 */         this.m_PropertyResourceMap.put(comments[0], comments[1] + "-" + comments[2]);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endTag() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endDocument() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeFile() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeParameterHeader(String tag, String parameter, String string) throws IllegalStateException, IllegalArgumentException, IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeParameterFooter(String string) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HashMap<String, String> getPropertyResourceMap() {
/* 214 */     return this.m_PropertyResourceMap;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\xml\JLbsDataExchangeWriterJson.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */