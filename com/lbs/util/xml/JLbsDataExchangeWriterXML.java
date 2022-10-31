/*     */ package com.lbs.util.xml;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.util.ILbsDataExchangeWriter;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.IOException;
/*     */ import java.util.Locale;
/*     */ import java.util.NoSuchElementException;
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
/*     */ public class JLbsDataExchangeWriterXML
/*     */   implements ILbsDataExchangeWriter
/*     */ {
/*     */   private XMLWriter m_Writer;
/*     */   
/*     */   public XMLWriter getWriter() {
/*  26 */     return this.m_Writer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWriter(XMLWriter writer) {
/*  31 */     this.m_Writer = writer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeObjectHeader(String name, String state, String type, String customization, String packageName, String nameSpace, String className, boolean isCustom) throws IllegalStateException, IllegalArgumentException, IOException {
/*  38 */     if (this.m_Writer != null) {
/*     */       
/*  40 */       this.m_Writer.startTag(name);
/*  41 */       this.m_Writer.attribute("object-state", state);
/*  42 */       this.m_Writer.attribute("object-type", type);
/*  43 */       if (isCustom)
/*  44 */         this.m_Writer.attribute("cust-guid", customization); 
/*  45 */       this.m_Writer.attribute("package", packageName);
/*  46 */       if (!StringUtil.isEmpty(nameSpace)) {
/*  47 */         this.m_Writer.attribute("xmlns:" + nameSpace, className);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeParameterHeader(String tag, String name, String packageName) throws IllegalStateException, IllegalArgumentException, IOException {
/*  54 */     if (this.m_Writer != null) {
/*     */       
/*  56 */       this.m_Writer.startTag(tag);
/*  57 */       this.m_Writer.attribute("package", packageName);
/*  58 */       if (!StringUtil.isEmpty(name)) {
/*  59 */         this.m_Writer.attribute("xmlns:" + name.toLowerCase(Locale.UK), packageName + "." + name);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeObjectProperty(String name, String value) {}
/*     */ 
/*     */   
/*     */   public void writePropertyStartTag(String nameSpace, String string) {
/*     */     try {
/*  71 */       this.m_Writer.startTag(string);
/*     */     }
/*  73 */     catch (IllegalStateException e) {
/*     */       
/*  75 */       e.printStackTrace();
/*     */     }
/*  77 */     catch (IllegalArgumentException e) {
/*     */       
/*  79 */       e.printStackTrace();
/*     */     }
/*  81 */     catch (IOException e) {
/*     */       
/*  83 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePropertyEndTag(String string) {
/*     */     try {
/*  91 */       this.m_Writer.endTag(string);
/*     */     }
/*  93 */     catch (IllegalStateException e) {
/*     */       
/*  95 */       e.printStackTrace();
/*     */     }
/*  97 */     catch (NoSuchElementException e) {
/*     */       
/*  99 */       e.printStackTrace();
/*     */     }
/* 101 */     catch (IOException e) {
/*     */       
/* 103 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public LineBreak getLineBreak() {
/* 109 */     return this.m_Writer.getLineBreak();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void pcdata(String string) {
/*     */     try {
/* 116 */       this.m_Writer.pcdata(string);
/*     */     }
/* 118 */     catch (IllegalStateException e) {
/*     */       
/* 120 */       e.printStackTrace();
/*     */     }
/* 122 */     catch (IllegalArgumentException e) {
/*     */       
/* 124 */       e.printStackTrace();
/*     */     }
/* 126 */     catch (InvalidXMLException e) {
/*     */       
/* 128 */       e.printStackTrace();
/*     */     }
/* 130 */     catch (IOException e) {
/*     */       
/* 132 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndentation(String string) {
/* 138 */     this.m_Writer.setIndentation(string);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIndentation() {
/* 143 */     return this.m_Writer.getIndentation();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeComment(String comment) {
/*     */     try {
/* 150 */       this.m_Writer.comment(comment);
/*     */     }
/* 152 */     catch (IllegalStateException e) {
/*     */       
/* 154 */       e.printStackTrace();
/*     */     }
/* 156 */     catch (IllegalArgumentException e) {
/*     */       
/* 158 */       e.printStackTrace();
/*     */     }
/* 160 */     catch (InvalidXMLException e) {
/*     */       
/* 162 */       e.printStackTrace();
/*     */     }
/* 164 */     catch (IOException e) {
/*     */       
/* 166 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePropertyStartTag(String string) {
/*     */     try {
/* 174 */       this.m_Writer.startTag(string);
/*     */     }
/* 176 */     catch (IllegalStateException e) {
/*     */       
/* 178 */       e.printStackTrace();
/*     */     }
/* 180 */     catch (IllegalArgumentException e) {
/*     */       
/* 182 */       e.printStackTrace();
/*     */     }
/* 184 */     catch (IOException e) {
/*     */       
/* 186 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLineBreak(LineBreak lineBreak) {
/* 192 */     this.m_Writer.setLineBreak(lineBreak);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeLinkedObjectStartTag(String string) {
/*     */     try {
/* 199 */       this.m_Writer.startTag(string);
/*     */     }
/* 201 */     catch (IllegalStateException e) {
/*     */       
/* 203 */       e.printStackTrace();
/*     */     }
/* 205 */     catch (IllegalArgumentException e) {
/*     */       
/* 207 */       e.printStackTrace();
/*     */     }
/* 209 */     catch (IOException e) {
/*     */       
/* 211 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeLinkedObjects() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeLinkedObjectEndTag(String string) {
/*     */     try {
/* 223 */       this.m_Writer.endTag(string);
/*     */     }
/* 225 */     catch (IllegalStateException e) {
/*     */       
/* 227 */       e.printStackTrace();
/*     */     }
/* 229 */     catch (NoSuchElementException e) {
/*     */       
/* 231 */       e.printStackTrace();
/*     */     }
/* 233 */     catch (IOException e) {
/*     */       
/* 235 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeCollectionsStartTag(String string) {
/*     */     try {
/* 243 */       this.m_Writer.startTag(string);
/*     */     }
/* 245 */     catch (IllegalStateException e) {
/*     */       
/* 247 */       e.printStackTrace();
/*     */     }
/* 249 */     catch (IllegalArgumentException e) {
/*     */       
/* 251 */       e.printStackTrace();
/*     */     }
/* 253 */     catch (IOException e) {
/*     */       
/* 255 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeCollections() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeCollectionsEndTag(String string) {
/*     */     try {
/* 267 */       this.m_Writer.endTag(string);
/*     */     }
/* 269 */     catch (IllegalStateException e) {
/*     */       
/* 271 */       e.printStackTrace();
/*     */     }
/* 273 */     catch (NoSuchElementException e) {
/*     */       
/* 275 */       e.printStackTrace();
/*     */     }
/* 277 */     catch (IOException e) {
/*     */       
/* 279 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeExtensions() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePropertyResolverStartTag(String string) {
/*     */     try {
/* 291 */       this.m_Writer.startTag(string);
/*     */     }
/* 293 */     catch (IllegalStateException e) {
/*     */       
/* 295 */       e.printStackTrace();
/*     */     }
/* 297 */     catch (IllegalArgumentException e) {
/*     */       
/* 299 */       e.printStackTrace();
/*     */     }
/* 301 */     catch (IOException e) {
/*     */       
/* 303 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeResolvers() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePropertyResolverEndTag(String string) {
/*     */     try {
/* 315 */       this.m_Writer.endTag(string);
/*     */     }
/* 317 */     catch (IllegalStateException e) {
/*     */       
/* 319 */       e.printStackTrace();
/*     */     }
/* 321 */     catch (NoSuchElementException e) {
/*     */       
/* 323 */       e.printStackTrace();
/*     */     }
/* 325 */     catch (IOException e) {
/*     */       
/* 327 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeObjectFooter(String string) {
/*     */     try {
/* 335 */       this.m_Writer.endTag(string);
/*     */     }
/* 337 */     catch (IllegalStateException e) {
/*     */       
/* 339 */       e.printStackTrace();
/*     */     }
/* 341 */     catch (NoSuchElementException e) {
/*     */       
/* 343 */       e.printStackTrace();
/*     */     }
/* 345 */     catch (IOException e) {
/*     */       
/* 347 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeParameterFooter(String string) {
/*     */     try {
/* 355 */       this.m_Writer.endTag(string);
/*     */     }
/* 357 */     catch (IllegalStateException e) {
/*     */       
/* 359 */       e.printStackTrace();
/*     */     }
/* 361 */     catch (NoSuchElementException e) {
/*     */       
/* 363 */       e.printStackTrace();
/*     */     }
/* 365 */     catch (IOException e) {
/*     */       
/* 367 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeExtensionsStartTag(String string) {
/*     */     try {
/* 375 */       this.m_Writer.startTag(string);
/*     */     }
/* 377 */     catch (IllegalStateException e) {
/*     */       
/* 379 */       e.printStackTrace();
/*     */     }
/* 381 */     catch (IllegalArgumentException e) {
/*     */       
/* 383 */       e.printStackTrace();
/*     */     }
/* 385 */     catch (IOException e) {
/*     */       
/* 387 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeExtensionsEndTag(String string) {
/*     */     try {
/* 395 */       this.m_Writer.endTag(string);
/*     */     }
/* 397 */     catch (IllegalStateException e) {
/*     */       
/* 399 */       e.printStackTrace();
/*     */     }
/* 401 */     catch (IllegalArgumentException e) {
/*     */       
/* 403 */       e.printStackTrace();
/*     */     }
/* 405 */     catch (IOException e) {
/*     */       
/* 407 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endTag() {
/*     */     try {
/* 415 */       this.m_Writer.endTag();
/*     */     }
/* 417 */     catch (IllegalStateException e) {
/*     */       
/* 419 */       e.printStackTrace();
/*     */     }
/* 421 */     catch (IOException e) {
/*     */       
/* 423 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endDocument() {
/*     */     try {
/* 431 */       this.m_Writer.endDocument();
/*     */     }
/* 433 */     catch (IllegalStateException e) {
/*     */       
/* 435 */       e.printStackTrace();
/*     */     }
/* 437 */     catch (IOException e) {
/*     */       
/* 439 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeFile() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeParameterStartTag(String string) {
/*     */     try {
/* 451 */       this.m_Writer.startTag(string);
/*     */     }
/* 453 */     catch (Exception e) {
/*     */       
/* 455 */       LbsConsole.getLogger(getClass()).error(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeParameterEndTag(String string) {
/*     */     try {
/* 462 */       this.m_Writer.endTag(string);
/*     */     }
/* 464 */     catch (Exception e) {
/*     */       
/* 466 */       LbsConsole.getLogger(getClass()).error(e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\xml\JLbsDataExchangeWriterXML.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */