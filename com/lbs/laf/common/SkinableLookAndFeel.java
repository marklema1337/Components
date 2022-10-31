/*     */ package com.lbs.laf.common;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.UIDefaults;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.metal.MetalLookAndFeel;
/*     */ import javax.swing.plaf.metal.MetalTheme;
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
/*     */ public class SkinableLookAndFeel
/*     */   extends MetalLookAndFeel
/*     */ {
/*  26 */   private static Hashtable ms_InstalledLAFs = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public static ImageIcon loadIcon(String file, Object invoker) {
/*     */     try {
/*  32 */       Image img = SkinImageLoader.getImage(file);
/*  33 */       if (img != null)
/*     */       {
/*  35 */         ImageIcon icon = new ImageIcon(img);
/*  36 */         return icon;
/*     */       }
/*     */     
/*  39 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/*  42 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Hashtable getCurrentUIProperties() {
/*  47 */     Hashtable<Object, Object> result = new Hashtable<>();
/*  48 */     UIDefaults uiDefaults = UIManager.getLookAndFeelDefaults();
/*  49 */     Enumeration keys = uiDefaults.keys();
/*  50 */     while (keys.hasMoreElements()) {
/*     */       
/*  52 */       Object k = keys.nextElement();
/*  53 */       if (k != null) {
/*     */         
/*  55 */         Object v = uiDefaults.get(k);
/*  56 */         if (v != null)
/*  57 */           result.put(k, v); 
/*     */       } 
/*     */     } 
/*  60 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setCurrentUIProperties(Hashtable table) {
/*  65 */     UIDefaults uiDefaults = UIManager.getLookAndFeelDefaults();
/*  66 */     Enumeration keys = table.keys();
/*  67 */     while (keys.hasMoreElements()) {
/*     */       
/*  69 */       Object k = keys.nextElement();
/*  70 */       uiDefaults.put(k, table.get(k));
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
/*     */   
/*     */   private static synchronized boolean setInstalled(SkinableLookAndFeel laf, String clsName) {
/*     */     Class<?> cls;
/*  86 */     if (ms_InstalledLAFs != null && laf != null && (cls = laf.getClass()) != null && 
/*  87 */       !ms_InstalledLAFs.containsKey(cls) && clsName != null) {
/*     */ 
/*     */       
/*  90 */       ms_InstalledLAFs.put(clsName, laf);
/*  91 */       ms_InstalledLAFs.put(cls, laf);
/*  92 */       UIManager.installLookAndFeel(new UIManager.LookAndFeelInfo(clsName, cls.getName()));
/*     */     } 
/*  94 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean m_bThemeSet = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SkinableLookAndFeel() {
/* 111 */     setInstalled(this, getID());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getID() {
/* 116 */     String clsName = getClass().getName();
/* 117 */     String[] tokens = clsName.split("\\.");
/* 118 */     if (tokens.length > 1)
/* 119 */       return tokens[tokens.length - 1]; 
/* 120 */     return clsName;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isSupportedLookAndFeel() {
/* 125 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNativeLookAndFeel() {
/* 130 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 136 */     return "LbsCustom/" + getID();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 141 */     return String.valueOf(getName()) + " : A custom look and feel.";
/*     */   }
/*     */ 
/*     */   
/*     */   protected MetalTheme getDefaultTheme() {
/* 146 */     return new DefaultSkinnableTheme();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initSystemColorDefaults(UIDefaults table) {
/* 151 */     super.initSystemColorDefaults(table);
/* 152 */     table.put("textHighlight", getTextHighlightColor());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initComponentDefaults(UIDefaults table) {
/* 157 */     super.initComponentDefaults(table);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initClassDefaults(UIDefaults table) {
/* 162 */     super.initClassDefaults(table);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void createDefaultTheme() {
/* 167 */     if (!this.m_bThemeSet) {
/*     */       
/* 169 */       MetalTheme theme = getDefaultTheme();
/* 170 */       if (theme != null) {
/* 171 */         setCurrentThemeInternal(theme);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void setCurrentThemeInternal(MetalTheme theme) {
/* 177 */     MetalLookAndFeel.setCurrentTheme(theme);
/* 178 */     this.m_bThemeSet = true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void putTable(UIDefaults table, String key, Class cls) {
/* 183 */     if (table != null && key != null && cls != null)
/* 184 */       table.put(key, cls.getName()); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinableLookAndFeel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */