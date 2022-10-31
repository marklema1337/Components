/*     */ package com.lbs.laf;
/*     */ 
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import com.lbs.util.JLbsClientFS;
/*     */ import com.lbs.util.JLbsIniProperties;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.UIManager;
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
/*     */ public class LAFPluginManager
/*     */ {
/*  26 */   public static String SUBSTANCE_PLUGIN_NAME = "substance-4.3.jar";
/*  27 */   public static int SUBSTANCE_PLUGIN_SIZE = 1833326;
/*  28 */   public static String SUBSTANCE_PLUGIN_MENU = "Download Substance";
/*     */ 
/*     */   
/*     */   static {
/*  32 */     UIManager.put("substancelaf.noExtraElements", new Boolean(true));
/*     */   }
/*     */ 
/*     */   
/*     */   public static JMenuItem getMenuItem(IClientContext context, JMenu parentMenu) {
/*  37 */     JMenuItem result = new JMenuItem(SUBSTANCE_PLUGIN_MENU);
/*  38 */     result.addActionListener(new LAFDownloadListener(context, parentMenu, result));
/*  39 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSubstanceInstalled(IClientContext context) {
/*     */     try {
/*  46 */       Class.forName("org.jvnet.substance.SubstanceLookAndFeel");
/*  47 */       return true;
/*     */     }
/*  49 */     catch (ClassNotFoundException classNotFoundException) {
/*     */ 
/*     */ 
/*     */       
/*  53 */       if (PluginManager.existsPlugin(context, SUBSTANCE_PLUGIN_NAME, SUBSTANCE_PLUGIN_SIZE)) {
/*     */         
/*     */         try {
/*     */           
/*  57 */           PluginManager.loadPlugin(context, SUBSTANCE_PLUGIN_NAME, SUBSTANCE_PLUGIN_SIZE, 
/*  58 */               null);
/*  59 */           return true;
/*     */         }
/*  61 */         catch (Exception e) {
/*     */           
/*  63 */           return false;
/*     */         } 
/*     */       }
/*     */       
/*  67 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void installLAF(IClientContext context, String lafClassName) {
/*  72 */     boolean isSubstance = lafClassName.startsWith("org.jvnet.substance");
/*  73 */     if (isSubstance) {
/*     */       
/*  75 */       if (isSubstanceInstalled(context)) {
/*  76 */         LAFChanger.changeTheme(context, lafClassName);
/*     */       }
/*     */     } else {
/*     */       
/*  80 */       LAFChanger.changeTheme(context, lafClassName);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void installPreferenceLAF(IClientContext context) {
/*     */     try {
/*  88 */       byte[] lafSettings = context.loadLocalFile("Settings/LAF.ini");
/*     */       
/*  90 */       if (lafSettings != null)
/*     */       {
/*  92 */         JLbsIniProperties lafProps = new JLbsIniProperties();
/*  93 */         lafProps.load(lafSettings);
/*  94 */         String lafClassName = lafProps.getProperty("ClassName");
/*  95 */         installLAF(context, lafClassName);
/*     */       }
/*     */     
/*  98 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void saveLAFPreferences(IClientContext context) {
/* 106 */     JLbsIniProperties lafProps = new JLbsIniProperties();
/* 107 */     String lafClassName = UIManager.getLookAndFeel().getClass().getName();
/* 108 */     lafProps.setProperty("ClassName", lafClassName);
/*     */ 
/*     */     
/*     */     try {
/* 112 */       byte[] lafSettings = lafProps.toByteArray();
/* 113 */       if (lafSettings != null)
/*     */       {
/* 115 */         JLbsClientFS.makeDirectory("Settings");
/* 116 */         context.saveLocalFile("Settings/LAF.ini", lafSettings, true, false);
/*     */       }
/*     */     
/* 119 */     } catch (Exception exception) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\LAFPluginManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */