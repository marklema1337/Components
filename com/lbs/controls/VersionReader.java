/*     */ package com.lbs.controls;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.StringWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Properties;
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
/*     */ public class VersionReader
/*     */ {
/*     */   private static int VERSION_MAJOR;
/*     */   private static int VERSION_MINOR;
/*     */   private static int VERSION_RELEASE;
/*     */   private static int VERSION_BUGFIX;
/*     */   
/*     */   public static void main(String[] args) {
/*  33 */     if (args.length >= 3) {
/*     */       
/*  35 */       readVersion(args[0], args[1]);
/*  36 */       if (args[2].equals("1"))
/*  37 */       { System.exit(VERSION_MAJOR); }
/*  38 */       else if (args[2].equals("2"))
/*  39 */       { System.exit(VERSION_MINOR); }
/*  40 */       else if (args[2].equals("3"))
/*  41 */       { System.exit(VERSION_RELEASE); }
/*  42 */       else if (args[2].equals("4"))
/*  43 */       { System.exit(VERSION_BUGFIX); }
/*  44 */       else { System.exit(0); }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void readVersion(String appPath, String fileName) {
/*  50 */     fileName = appPath + File.separator + fileName;
/*     */     
/*  52 */     Properties props = new Properties();
/*     */ 
/*     */     
/*     */     try {
/*  56 */       String propsStr = getFileContents(fileName, false);
/*  57 */       ByteArrayInputStream in = new ByteArrayInputStream(propsStr.getBytes());
/*  58 */       props.load(in);
/*     */       
/*  60 */       int prop = getIntProp(props, "Version.Major");
/*  61 */       if (prop != Integer.MIN_VALUE) {
/*  62 */         VERSION_MAJOR = prop;
/*     */       }
/*  64 */       prop = getIntProp(props, "Version.Minor");
/*  65 */       if (prop != Integer.MIN_VALUE) {
/*  66 */         VERSION_MINOR = prop;
/*     */       }
/*  68 */       prop = getIntProp(props, "Version.Release");
/*  69 */       if (prop != Integer.MIN_VALUE) {
/*  70 */         VERSION_RELEASE = prop;
/*     */       }
/*  72 */       prop = getIntProp(props, "Version.Bugfix");
/*  73 */       if (prop != Integer.MIN_VALUE) {
/*  74 */         VERSION_BUGFIX = prop;
/*     */       }
/*     */     }
/*  77 */     catch (Exception e) {
/*     */       
/*  79 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected static int getIntProp(Properties props, String propName) {
/*  85 */     String prop = props.getProperty(propName);
/*  86 */     if (prop != null && !prop.equals("")) {
/*     */       
/*     */       try {
/*     */         
/*  90 */         return Integer.parseInt(prop);
/*     */       }
/*  92 */       catch (NumberFormatException numberFormatException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     return Integer.MIN_VALUE;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFileContents(String src, boolean removeDocType) throws IOException {
/* 103 */     return getFileContents(src, removeDocType, (String[])null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFileContents(String src, boolean removeDocType, String[] docTypeLine) throws IOException {
/* 108 */     File srcFile = new File(src);
/*     */     
/* 110 */     if (!srcFile.exists()) {
/* 111 */       return "";
/*     */     }
/* 113 */     return getContents(srcFile, removeDocType, docTypeLine);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFileContents(BufferedReader br, boolean removeDocType) {
/* 118 */     return getFileContents(br, removeDocType, (String[])null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getFileContents(BufferedReader br, boolean removeDocType, String[] docTypeLine) {
/*     */     try {
/* 125 */       StringWriter writer = new StringWriter();
/*     */       
/* 127 */       while (br.ready()) {
/*     */         
/* 129 */         String line = br.readLine();
/* 130 */         if (line == null) {
/*     */           break;
/*     */         }
/* 133 */         if (!removeDocType) {
/*     */           
/* 135 */           writer.write(line);
/* 136 */           writer.write("\n");
/*     */           
/*     */           continue;
/*     */         } 
/* 140 */         boolean docType = (line.indexOf("DOCTYPE") >= 0);
/*     */         
/* 142 */         if (!docType) {
/*     */           
/* 144 */           writer.write(line);
/* 145 */           writer.write("\n");
/*     */         } 
/*     */         
/* 148 */         if (docType && docTypeLine != null && docTypeLine.length >= 1)
/*     */         {
/* 150 */           docTypeLine[0] = line;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 155 */       String s = writer.getBuffer().toString();
/* 156 */       writer.close();
/* 157 */       return s;
/*     */     }
/* 159 */     catch (IOException e) {
/*     */       
/* 161 */       e.printStackTrace();
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 167 */         br.close();
/*     */       }
/* 169 */       catch (IOException iOException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 174 */     return "";
/*     */   }
/*     */   public static String getContents(File file, boolean removeDocType, String[] docTypeLine) throws IOException {
/*     */     byte[] buf;
/*     */     int l;
/* 179 */     InputStream in = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 185 */       in = new FileInputStream(file);
/* 186 */       buf = new byte[10];
/* 187 */       l = in.read(buf);
/*     */     }
/*     */     finally {
/*     */       
/* 191 */       if (in != null) {
/* 192 */         in.close();
/*     */       }
/*     */     } 
/* 195 */     return getFileContents(createBufferedReader(new FileInputStream(file), buf, l), removeDocType, docTypeLine);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedReader createBufferedReader(InputStream in, byte[] buf, int l) throws IOException, UnsupportedEncodingException {
/* 202 */     String encoding = "utf-8";
/* 203 */     if (l > 1)
/*     */     {
/* 205 */       if (buf[0] == -1 && buf[1] == -2) {
/* 206 */         encoding = "unicode";
/* 207 */       } else if (buf[0] == -2 && buf[1] == -1) {
/* 208 */         encoding = "unicode";
/* 209 */       } else if (buf[0] == -17 && buf[1] == -69 && buf.length > 2 && buf[2] == -65) {
/* 210 */         in.skip(3L);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 216 */     InputStreamReader reader = new InputStreamReader(in, encoding);
/*     */     
/* 218 */     BufferedReader br = new BufferedReader(reader);
/* 219 */     return br;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\VersionReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */