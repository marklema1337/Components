/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ArrayList;
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
/*     */ public class LbsClassGeneratorBase
/*     */ {
/*     */   protected StringBuilder m_Buffer;
/*     */   protected PrintWriter m_Writer;
/*     */   protected String m_PackageName;
/*     */   protected String m_ClassName;
/*  26 */   protected ArrayList m_Imports = new ArrayList();
/*     */   
/*  28 */   protected int m_Level = 0;
/*     */ 
/*     */   
/*     */   protected void initializeWriter(String targetFolder, String packageName, String className) throws Exception {
/*  32 */     File f = new File(targetFolder);
/*  33 */     if (!f.exists()) {
/*  34 */       throw new Exception("Target folder '" + targetFolder + "' does not exist!");
/*     */     }
/*  36 */     this.m_PackageName = packageName;
/*  37 */     this.m_ClassName = className;
/*     */     
/*  39 */     File folder = f;
/*  40 */     if (!JLbsStringUtil.isEmpty(this.m_PackageName)) {
/*     */       
/*  42 */       String[] parts = packageName.split("\\.");
/*  43 */       for (int i = 0; i < parts.length; i++) {
/*     */         
/*  45 */         folder = new File(folder, parts[i]);
/*  46 */         if (!folder.exists())
/*  47 */           folder.mkdir(); 
/*     */       } 
/*     */     } 
/*  50 */     File file = new File(folder, String.valueOf(className) + ".java");
/*  51 */     if (!file.exists()) {
/*  52 */       file.createNewFile();
/*     */     }
/*  54 */     this.m_Writer = new PrintWriter(new FileOutputStream(file));
/*  55 */     this.m_Buffer = new StringBuilder();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void classDeclaration(String superClass, String[] interfaces) {
/*  60 */     classDeclaration(superClass, interfaces, false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void classDeclaration(String superClass, String[] interfaces, boolean isInterface) {
/*  65 */     String interfaceStr = "";
/*  66 */     StringBuilder interfaceStr1 = new StringBuilder();
/*  67 */     if (interfaces != null) {
/*     */       
/*  69 */       for (int i = 0; i < interfaces.length; i++) {
/*     */         
/*  71 */         if (i > 0)
/*  72 */           interfaceStr1.append(", "); 
/*  73 */         interfaceStr1.append(importClass(interfaces[i]));
/*     */       } 
/*  75 */       interfaceStr = interfaceStr1.toString();
/*     */     } 
/*  77 */     if (!JLbsStringUtil.isEmpty(interfaceStr)) {
/*  78 */       interfaceStr = " implements " + interfaceStr;
/*     */     }
/*  80 */     String extendStr = "";
/*  81 */     if (!JLbsStringUtil.isEmpty(superClass)) {
/*  82 */       extendStr = " extends " + superClass;
/*     */     }
/*  84 */     println("public " + (isInterface ? 
/*  85 */         "interface" : 
/*  86 */         "class") + " " + this.m_ClassName + extendStr + interfaceStr);
/*  87 */     startBlock();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void finish() {
/*  92 */     endBlock();
/*  93 */     if (!JLbsStringUtil.isEmpty(this.m_PackageName))
/*  94 */       this.m_Writer.println("package " + this.m_PackageName + ";"); 
/*  95 */     this.m_Writer.println();
/*  96 */     for (int i = 0; i < this.m_Imports.size(); i++)
/*     */     {
/*  98 */       this.m_Writer.println("import " + this.m_Imports.get(i) + ";");
/*     */     }
/* 100 */     this.m_Writer.println();
/* 101 */     int idx = 0;
/* 102 */     int startIdx = 0;
/* 103 */     while (idx < this.m_Buffer.length() - 1) {
/*     */       
/* 105 */       startIdx = idx;
/* 106 */       idx = this.m_Buffer.indexOf("\\\n", idx);
/* 107 */       if (idx > 0) {
/*     */         
/* 109 */         this.m_Writer.println(this.m_Buffer.substring(startIdx, idx));
/* 110 */         idx += "\\\n".length();
/*     */         
/*     */         continue;
/*     */       } 
/* 114 */       this.m_Writer.println(this.m_Buffer.substring(startIdx));
/*     */       
/*     */       break;
/*     */     } 
/* 118 */     this.m_Writer.flush();
/* 119 */     this.m_Writer.close();
/* 120 */     this.m_Buffer = null;
/* 121 */     this.m_Writer = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void startBlock() {
/* 126 */     println("{");
/* 127 */     indent();
/*     */   }
/*     */ 
/*     */   
/*     */   public void endBlock() {
/* 132 */     unindent();
/* 133 */     println("}");
/*     */   }
/*     */ 
/*     */   
/*     */   public String importClass(Class clazz) {
/* 138 */     if (clazz.isArray())
/* 139 */       return String.valueOf(importClass(clazz.getComponentType())) + "[]"; 
/* 140 */     return importClass(clazz.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String importClass(String className) {
/* 146 */     if (!this.m_Imports.contains(className) && className.indexOf('.') > 0) {
/* 147 */       this.m_Imports.add(className);
/*     */     }
/* 149 */     int idx = className.lastIndexOf('.');
/* 150 */     if (idx > 0)
/* 151 */       return className.substring(idx + 1); 
/* 152 */     return className;
/*     */   }
/*     */ 
/*     */   
/*     */   public void indent() {
/* 157 */     this.m_Level++;
/*     */   }
/*     */ 
/*     */   
/*     */   public void unindent() {
/* 162 */     if (this.m_Level > 0) {
/* 163 */       this.m_Level--;
/*     */     }
/*     */   }
/*     */   
/*     */   public void println(String line) {
/* 168 */     for (int i = 0; i < this.m_Level; i++)
/*     */     {
/* 170 */       this.m_Buffer.append("\t");
/*     */     }
/* 172 */     this.m_Buffer.append(line);
/* 173 */     this.m_Buffer.append("\\\n");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLevelStr() {
/* 178 */     StringBuilder s = new StringBuilder();
/* 179 */     for (int i = 0; i < this.m_Level; i++)
/*     */     {
/* 181 */       s.append("\t");
/*     */     }
/* 183 */     return s.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList getImports() {
/* 189 */     return this.m_Imports;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImports(ArrayList m_Imports) {
/* 194 */     this.m_Imports = m_Imports;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\LbsClassGeneratorBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */