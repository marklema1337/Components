/*     */ package com.lbs.laf.common;
/*     */ 
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.awt.Color;
/*     */ import java.awt.Image;
/*     */ import java.io.File;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.UIDefaults;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ColorUIResource;
/*     */ import javax.swing.plaf.FontUIResource;
/*     */ import javax.swing.plaf.metal.DefaultMetalTheme;
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
/*     */ public class DefaultSkinnableTheme
/*     */   extends DefaultMetalTheme
/*     */ {
/*     */   public static boolean CAN_LOAD_THEME_FROM_FS = false;
/*  31 */   public ColorUIResource m_LightBackground = new ColorUIResource(252, 252, 254);
/*  32 */   public ColorUIResource m_TabbedPaneBorderColor = new ColorUIResource(145, 155, 156);
/*  33 */   public ColorUIResource m_DarkControl = new ColorUIResource(161, 161, 148);
/*     */ 
/*     */   
/*     */   public static SkinImage getSkinImage(Class cls, String fileName, int nrImages, int roundSize) {
/*  37 */     return new SkinImage(getImagePath(cls, fileName), nrImages, roundSize);
/*     */   }
/*     */ 
/*     */   
/*     */   public static SkinImage getSkinImage(Class cls, String fileName, int nrImages, int ux, int uy, int rx, int ry) {
/*  42 */     return new SkinImage(getImagePath(cls, fileName), nrImages, ux, uy, rx, ry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ImageIcon getImageIcon(Class cls, String fileName) {
/*  47 */     Image img = SkinImageLoader.getImage(getImagePath(cls, fileName));
/*  48 */     if (img != null)
/*  49 */       return new ImageIcon(img); 
/*  50 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getImagePath(Class cls, String fileName) {
/*  55 */     if (CAN_LOAD_THEME_FROM_FS) {
/*     */       
/*  57 */       String filePath = "c:\\artworks_j-platform\\" + fileName;
/*  58 */       File file = new File(filePath);
/*  59 */       if (file.exists())
/*  60 */         return "file:///c://artworks_j-platform//" + fileName; 
/*     */     } 
/*  62 */     return getFilePath(cls, "images", fileName);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFilePath(Class cls, String fileName) {
/*  67 */     return getFilePath(cls, null, fileName);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFilePath(Class cls, String relDir, String fileName) {
/*  72 */     String className = cls.getName();
/*  73 */     int iDot = className.lastIndexOf(".");
/*  74 */     if (iDot > 0) {
/*     */       
/*  76 */       String path = "/" + className.substring(0, iDot);
/*  77 */       path = String.valueOf(path.replace('.', '/')) + '/';
/*  78 */       if (relDir != null && relDir.length() > 0)
/*  79 */         path = String.valueOf(path) + relDir + "/" + fileName; 
/*  80 */       return path;
/*     */     } 
/*  82 */     return fileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   private static final ColorUIResource primary1 = new ColorUIResource(0, 0, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   private static final ColorUIResource primary2 = new ColorUIResource(213, 211, 209);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   private static final ColorUIResource primary3 = new ColorUIResource(213, 211, 209);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   private static final ColorUIResource secondary1 = new ColorUIResource(167, 165, 163);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   private static final ColorUIResource secondary2 = new ColorUIResource(167, 165, 163);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   private static final ColorUIResource secondary3 = new ColorUIResource(236, 233, 216);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   private static final ColorUIResource secondary4 = new ColorUIResource(190, 188, 186);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   private static final Color gradientReflection = new Color(255, 255, 255, 86);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   private static final Color gradientShadow = new Color(188, 186, 184, 100);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   private static final Color gradientTranslucentReflection = new Color(gradientReflection.getRGB() & 0xFFFFFF, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   private static final Color gradientTranslucentShadow = new Color(gradientShadow.getRGB() & 0xFFFFFF, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getGradientReflection() {
/* 170 */     return gradientReflection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getGradientShadow() {
/* 181 */     return gradientShadow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getGradientTranslucentReflection() {
/* 192 */     return gradientTranslucentReflection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getGradientTranslucentShadow() {
/* 203 */     return gradientTranslucentShadow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FontUIResource getControlTextFont() {
/* 213 */     return new FontUIResource(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FontUIResource getMenuTextFont() {
/* 223 */     return new FontUIResource(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FontUIResource getSystemTextFont() {
/* 233 */     return new FontUIResource(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FontUIResource getUserTextFont() {
/* 243 */     return new FontUIResource(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FontUIResource getWindowTitleFont() {
/* 253 */     return new FontUIResource(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE);
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
/*     */   public void addCustomEntriesToTable(UIDefaults table) {
/* 265 */     super.addCustomEntriesToTable(table);
/* 266 */     FontUIResource plainFont = new FontUIResource(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE);
/* 267 */     UIManager.getDefaults().put("PasswordField.font", plainFont);
/* 268 */     UIManager.getDefaults().put("TextArea.font", plainFont);
/* 269 */     UIManager.getDefaults().put("TextPane.font", plainFont);
/* 270 */     UIManager.getDefaults().put("EditorPane.font", plainFont);
/* 271 */     UIManager.getDefaults().put("InternalFrame.font", plainFont);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorUIResource getMenuSelectedBackground() {
/* 282 */     return new ColorUIResource(new Color(200, 200, 255));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorUIResource getSeparatorForeground() {
/* 292 */     return new ColorUIResource(Color.white);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 302 */     return "LBS Default Theme";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ColorUIResource getPrimary1() {
/* 312 */     return primary1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ColorUIResource getPrimary2() {
/* 322 */     return primary2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ColorUIResource getPrimary3() {
/* 332 */     return primary3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ColorUIResource getSecondary1() {
/* 342 */     return secondary1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ColorUIResource getSecondary2() {
/* 352 */     return secondary2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ColorUIResource getSecondary3() {
/* 362 */     return secondary3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorUIResource getPressedBackground() {
/* 373 */     return secondary4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorUIResource getLigthBackground() {
/* 381 */     return this.m_LightBackground;
/*     */   }
/*     */ 
/*     */   
/*     */   public ColorUIResource getDarkControl() {
/* 386 */     return this.m_DarkControl;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFilePath(String fileName) {
/* 391 */     return getFilePath(getClass(), fileName);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\DefaultSkinnableTheme.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */