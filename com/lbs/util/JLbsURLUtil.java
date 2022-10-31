/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Vector;
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
/*     */ public class JLbsURLUtil
/*     */ {
/*  19 */   private static String[] m_BrowserCommands = null;
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  24 */     m_BrowserCommands = defaultCommands();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] defaultCommands() {
/*  29 */     String[] exec = null;
/*  30 */     if (System.getProperty("os.name").startsWith("Windows")) {
/*     */       
/*  32 */       exec = new String[] { "rundll32 url.dll,FileProtocolHandler {0}" };
/*     */     }
/*  34 */     else if (System.getProperty("os.name").startsWith("Mac")) {
/*     */       
/*  36 */       Vector<String> browsers = new Vector();
/*     */       
/*     */       try {
/*  39 */         Process p = Runtime.getRuntime().exec("which open");
/*  40 */         if (p.waitFor() == 0)
/*     */         {
/*  42 */           browsers.add("open {0}");
/*     */         }
/*     */       }
/*  45 */       catch (IOException iOException) {
/*     */ 
/*     */       
/*  48 */       } catch (InterruptedException interruptedException) {}
/*     */ 
/*     */       
/*  51 */       if (browsers.size() == 0)
/*     */       {
/*  53 */         exec = null;
/*     */       }
/*     */       else
/*     */       {
/*  57 */         exec = browsers.<String>toArray(new String[0]);
/*     */       }
/*     */     
/*  60 */     } else if (System.getProperty("os.name").startsWith("SunOS")) {
/*     */       
/*  62 */       exec = new String[] { "/usr/dt/bin/sdtwebclient {0}" };
/*     */     }
/*     */     else {
/*     */       
/*  66 */       Vector<String> browsers = new Vector();
/*     */       
/*     */       try {
/*  69 */         Process p = Runtime.getRuntime().exec("which firebird");
/*  70 */         if (p.waitFor() == 0)
/*     */         {
/*  72 */           browsers.add("firebird -remote openURL({0})");
/*  73 */           browsers.add("firebird {0}");
/*     */         }
/*     */       
/*  76 */       } catch (IOException iOException) {
/*     */ 
/*     */       
/*  79 */       } catch (InterruptedException interruptedException) {}
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/*  84 */         Process p = Runtime.getRuntime().exec("which mozilla");
/*  85 */         if (p.waitFor() == 0)
/*     */         {
/*  87 */           browsers.add("mozilla -remote openURL({0})");
/*  88 */           browsers.add("mozilla {0}");
/*     */         }
/*     */       
/*  91 */       } catch (IOException iOException) {
/*     */ 
/*     */       
/*  94 */       } catch (InterruptedException interruptedException) {}
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/*  99 */         Process p = Runtime.getRuntime().exec("which opera");
/* 100 */         if (p.waitFor() == 0)
/*     */         {
/* 102 */           browsers.add("opera -remote openURL({0})");
/* 103 */           browsers.add("opera {0}");
/*     */         }
/*     */       
/* 106 */       } catch (IOException iOException) {
/*     */ 
/*     */       
/* 109 */       } catch (InterruptedException interruptedException) {}
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 114 */         Process p = Runtime.getRuntime().exec("which galeon");
/* 115 */         if (p.waitFor() == 0)
/*     */         {
/* 117 */           browsers.add("galeon {0}");
/*     */         }
/*     */       }
/* 120 */       catch (IOException iOException) {
/*     */ 
/*     */       
/* 123 */       } catch (InterruptedException interruptedException) {}
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 128 */         Process p = Runtime.getRuntime().exec("which konqueror");
/* 129 */         if (p.waitFor() == 0)
/*     */         {
/* 131 */           browsers.add("konqueror {0}");
/*     */         }
/*     */       }
/* 134 */       catch (IOException iOException) {
/*     */ 
/*     */       
/* 137 */       } catch (InterruptedException interruptedException) {}
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 142 */         Process p = Runtime.getRuntime().exec("which netscape");
/* 143 */         if (p.waitFor() == 0)
/*     */         {
/* 145 */           browsers.add("netscape -remote openURL({0})");
/* 146 */           browsers.add("netscape {0}");
/*     */         }
/*     */       
/* 149 */       } catch (IOException iOException) {
/*     */ 
/*     */       
/* 152 */       } catch (InterruptedException interruptedException) {}
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 157 */         Process p = Runtime.getRuntime().exec("which xterm");
/* 158 */         if (p.waitFor() == 0)
/*     */         {
/* 160 */           p = Runtime.getRuntime().exec("which lynx");
/* 161 */           if (p.waitFor() == 0)
/*     */           {
/* 163 */             browsers.add("xterm -e lynx {0}");
/*     */           }
/*     */         }
/*     */       
/* 167 */       } catch (IOException iOException) {
/*     */ 
/*     */       
/* 170 */       } catch (InterruptedException interruptedException) {}
/*     */ 
/*     */       
/* 173 */       if (browsers.size() == 0) {
/*     */         
/* 175 */         exec = null;
/*     */       }
/*     */       else {
/*     */         
/* 179 */         exec = browsers.<String>toArray(new String[0]);
/*     */       } 
/*     */     } 
/* 182 */     return exec;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/* 189 */       displayURL("http://localhost:9081/LogoERP/Profiler/birim setleri listesi_meltemg_2007_11_23_17_58_58.pdf");
/*     */     }
/* 191 */     catch (IOException e) {
/*     */       
/* 193 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void displayURL(String url) throws IOException {
/* 199 */     boolean found = false;
/* 200 */     if (m_BrowserCommands == null) {
/*     */       
/* 202 */       System.err.println("No browser installed");
/*     */       return;
/*     */     } 
/* 205 */     for (int i = 0; i < m_BrowserCommands.length && !found; i++) {
/*     */ 
/*     */       
/*     */       try {
/* 209 */         url = MessageFormat.format(m_BrowserCommands[i], (Object[])new String[] { url });
/* 210 */         Process p = Runtime.getRuntime().exec(url);
/* 211 */         for (int j = 0; j < 2; j++) {
/*     */ 
/*     */           
/*     */           try {
/* 215 */             Thread.currentThread(); Thread.sleep(1000L);
/*     */           }
/* 217 */           catch (InterruptedException interruptedException) {}
/*     */         } 
/*     */ 
/*     */         
/* 221 */         if (p.exitValue() == 0)
/*     */         {
/* 223 */           found = true;
/*     */         }
/*     */       }
/* 226 */       catch (IOException x) {
/*     */         
/* 228 */         System.err.println("warning " + x.getMessage());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsURLUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */