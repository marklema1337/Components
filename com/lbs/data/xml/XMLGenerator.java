/*     */ package com.lbs.data.xml;
/*     */ 
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import com.lbs.util.XMLUtil;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Calendar;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMLGenerator
/*     */ {
/*     */   public static final String DEFAULT_DTD_PATH = "../LbsData.Server/com/lbs/data/";
/*     */   protected PrintWriter m_Writer;
/*     */   protected String m_DTDPath;
/*     */   protected boolean m_PrintDocType = true;
/*     */   
/*     */   public XMLGenerator() {
/*  43 */     this("../LbsData.Server/com/lbs/data/");
/*     */   }
/*     */ 
/*     */   
/*     */   public XMLGenerator(String dtdPath) {
/*  48 */     this.m_DTDPath = dtdPath;
/*  49 */     if (this.m_DTDPath == null) {
/*  50 */       this.m_DTDPath = "../LbsData.Server/com/lbs/data/";
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeHeader(String dtdName, String rootAttribute) {
/*  55 */     this.m_Writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
/*  56 */     if (this.m_PrintDocType) {
/*     */       
/*  58 */       String dtdFile = JLbsFileUtil.appendPath(this.m_DTDPath, dtdName + ".dtd");
/*  59 */       this.m_Writer.println("<!DOCTYPE " + rootAttribute + " SYSTEM \"" + dtdFile + "\" >");
/*     */     } 
/*  61 */     this.m_Writer.println();
/*  62 */     this.m_Writer.println("<" + rootAttribute + ">");
/*     */   }
/*     */ 
/*     */   
/*     */   public void startFile(String file) throws IOException {
/*  67 */     File out = new File(file);
/*     */     
/*  69 */     String encoding = "UTF-8";
/*     */     
/*  71 */     OutputStreamWriter writer = null;
/*  72 */     if (!JLbsStringUtil.isEmpty(encoding)) {
/*  73 */       writer = new OutputStreamWriter(new FileOutputStream(out), encoding);
/*     */     } else {
/*  75 */       writer = new FileWriter(out);
/*     */     } 
/*  77 */     this.m_Writer = new PrintWriter(writer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeFile() {
/*     */     try {
/*  84 */       if (this.m_Writer != null) {
/*  85 */         this.m_Writer.close();
/*     */       }
/*  87 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  92 */   protected static char[] SPECIAL_CHARS = new char[] { '&', '<', '>', '"', '\'' };
/*  93 */   protected static String[] SPECIAL_SUBST = new String[] { "&amp;", "&lt;", "&gt;", "&quot;", "&apos;" };
/*     */ 
/*     */   
/*     */   protected String convertChar(char c) {
/*  97 */     for (int i = 0; i < SPECIAL_CHARS.length; i++) {
/*     */       
/*  99 */       if (c == SPECIAL_CHARS[i]) {
/* 100 */         return SPECIAL_SUBST[i];
/*     */       }
/*     */     } 
/* 103 */     return c + "";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void printAttribute(String name, String value) {
/* 108 */     if (value == null) {
/*     */       
/* 110 */       this.m_Writer.print(name + "=\"\" ");
/*     */       
/*     */       return;
/*     */     } 
/* 114 */     value = XMLUtil.getAttribute(value);
/* 115 */     this.m_Writer.print(name + "=\"" + value + "\" ");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void printAttribute(String name, Object value) {
/* 120 */     if (value instanceof Calendar) {
/* 121 */       value = StringUtil.toCanonicalString((Calendar)value);
/*     */     }
/* 123 */     if (value == null)
/* 124 */       value = ""; 
/* 125 */     this.m_Writer.print(name + "=\"" + value + "\" ");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void printAttribute(String name, boolean value) {
/* 130 */     this.m_Writer.print(name + "=\"" + value + "\" ");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void printAttribute(String name, int value) {
/* 135 */     this.m_Writer.print(name + "=\"" + value + "\" ");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrintDocType(boolean printDocType) {
/* 140 */     this.m_PrintDocType = printDocType;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\xml\XMLGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */