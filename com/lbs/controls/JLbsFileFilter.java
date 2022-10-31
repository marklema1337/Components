/*     */ package com.lbs.controls;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import javax.swing.filechooser.FileFilter;
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
/*     */ public class JLbsFileFilter
/*     */   extends FileFilter
/*     */ {
/*  22 */   public static String TYPE_UNKNOWN = "Type Unknown";
/*  23 */   public static String HIDDEN_FILE = "Hidden File";
/*     */   
/*  25 */   private Hashtable filters = null;
/*  26 */   private String description = null;
/*  27 */   private String fullDescription = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean useExtensionsInDescription = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsFileFilter() {
/*  38 */     this.filters = new Hashtable<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsFileFilter(String extension) {
/*  49 */     this(extension, (String)null);
/*     */   }
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
/*     */   public JLbsFileFilter(String extension, String description) {
/*  63 */     this();
/*  64 */     if (extension != null)
/*  65 */       addExtension(extension); 
/*  66 */     if (description != null) {
/*  67 */       setDescription(description);
/*     */     }
/*     */   }
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
/*     */   public JLbsFileFilter(String[] filters) {
/*  81 */     this(filters, (String)null);
/*     */   }
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
/*     */   public JLbsFileFilter(String[] filters, String description) {
/*  94 */     this();
/*  95 */     for (int i = 0; i < filters.length; i++)
/*     */     {
/*     */       
/*  98 */       addExtension(filters[i]);
/*     */     }
/* 100 */     if (description != null) {
/* 101 */       setDescription(description);
/*     */     }
/*     */   }
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
/*     */   public boolean accept(File f) {
/* 115 */     if (f != null) {
/*     */       
/* 117 */       if (f.isDirectory())
/*     */       {
/* 119 */         return true;
/*     */       }
/* 121 */       String extension = getExtension(f);
/* 122 */       if (extension != null && this.filters.get(getExtension(f)) != null)
/*     */       {
/* 124 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 128 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExtension(File f) {
/* 139 */     if (f != null) {
/*     */       
/* 141 */       String filename = f.getName();
/* 142 */       int i = filename.lastIndexOf('.');
/* 143 */       if (i > 0 && i < filename.length() - 1)
/*     */       {
/* 145 */         return filename.substring(i + 1).toLowerCase();
/*     */       }
/*     */     } 
/*     */     
/* 149 */     return null;
/*     */   }
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
/*     */   public void addExtension(String extension) {
/* 166 */     if (this.filters == null)
/*     */     {
/* 168 */       this.filters = new Hashtable<>(5);
/*     */     }
/* 170 */     this.filters.put(extension.toLowerCase(), this);
/* 171 */     this.fullDescription = null;
/*     */   }
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
/*     */   public String getDescription() {
/* 185 */     if (this.fullDescription == null)
/*     */     {
/* 187 */       if (this.description == null || isExtensionListInDescription()) {
/*     */         
/* 189 */         this.fullDescription = (this.description == null) ? 
/* 190 */           "(" : (
/* 191 */           String.valueOf(this.description) + " (");
/*     */         
/* 193 */         Enumeration<String> extensions = this.filters.keys();
/* 194 */         if (extensions != null) {
/*     */           
/* 196 */           this.fullDescription = String.valueOf(this.fullDescription) + "." + (String)extensions.nextElement();
/* 197 */           while (extensions.hasMoreElements())
/*     */           {
/* 199 */             this.fullDescription = String.valueOf(this.fullDescription) + ", ." + (String)extensions.nextElement();
/*     */           }
/*     */         } 
/* 202 */         this.fullDescription = String.valueOf(this.fullDescription) + ")";
/*     */       }
/*     */       else {
/*     */         
/* 206 */         this.fullDescription = this.description;
/*     */       } 
/*     */     }
/* 209 */     return this.fullDescription;
/*     */   }
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
/*     */   public void setDescription(String description) {
/* 222 */     this.description = description;
/* 223 */     this.fullDescription = null;
/*     */   }
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
/*     */   public void setExtensionListInDescription(boolean b) {
/* 239 */     this.useExtensionsInDescription = b;
/* 240 */     this.fullDescription = null;
/*     */   }
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
/*     */   public boolean isExtensionListInDescription() {
/* 256 */     return this.useExtensionsInDescription;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsFileFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */