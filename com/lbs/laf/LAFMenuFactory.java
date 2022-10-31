/*     */ package com.lbs.laf;
/*     */ 
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JRootPane;
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
/*     */ public class LAFMenuFactory
/*     */ {
/*     */   public static JMenu getLookAndFeelMenu(IClientContext context, JRootPane rootPane) {
/*  24 */     JMenu lafMenu = new JMenu("Look & feel");
/*     */     
/*  26 */     JMenu coreLafMenus = new JMenu("Core LAFs");
/*  27 */     lafMenu.add(coreLafMenus);
/*  28 */     coreLafMenus.add(LAFChanger.getMenuItem(context, "System", UIManager.getSystemLookAndFeelClassName()));
/*  29 */     coreLafMenus.add(LAFChanger.getMenuItem(context, "Metal", "javax.swing.plaf.metal.MetalLookAndFeel"));
/*  30 */     coreLafMenus.add(LAFChanger.getMenuItem(context, "Windows", "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"));
/*  31 */     coreLafMenus.add(LAFChanger.getMenuItem(context, "Windows Classic", 
/*  32 */           "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"));
/*  33 */     coreLafMenus.add(LAFChanger.getMenuItem(context, "Motif", "com.sun.java.swing.plaf.motif.MotifLookAndFeel"));
/*     */     
/*  35 */     lafMenu.addSeparator();
/*     */     
/*  37 */     JMenu lbsLafMenus = new JMenu("Logo LAFs");
/*  38 */     lafMenu.add(lbsLafMenus);
/*  39 */     lbsLafMenus.add(LAFChanger.getMenuItem(context, "Aqua Blue", "com.lbs.laf.mac.LookAndFeel"));
/*  40 */     lafMenu.addSeparator();
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
/* 136 */     JMenu localeMenus = new JMenu("Change locale");
/* 137 */     lafMenu.add(localeMenus);
/*     */     
/* 139 */     JMenuItem localeTurkish = new JMenuItem("Turkish Locale", getIcon("flag_turkey"));
/* 140 */     localeTurkish.addActionListener(new LAFLocaleChangeListener("tr", "TR", rootPane));
/*     */     
/* 142 */     JMenuItem localeArabic = new JMenuItem("Jordan (Arabic) Locale", getIcon("flag_saudi_arabia"));
/* 143 */     localeArabic.addActionListener(new LAFLocaleChangeListener("ar", "JO", rootPane));
/*     */     
/* 145 */     JMenuItem localeEnglish = new JMenuItem("English Locale", getIcon("flag_united_states"));
/* 146 */     localeEnglish.addActionListener(new LAFLocaleChangeListener("en", "US", rootPane));
/*     */     
/* 148 */     JMenuItem localeGerman = new JMenuItem("German Locale", getIcon("flag_germany"));
/* 149 */     localeGerman.addActionListener(new LAFLocaleChangeListener("de", "DE", rootPane));
/*     */     
/* 151 */     JMenuItem localeRussian = new JMenuItem("Russian Locale", getIcon("flag_russia"));
/* 152 */     localeRussian.addActionListener(new LAFLocaleChangeListener("ru", "RU", rootPane));
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
/* 226 */     localeMenus.add(localeTurkish);
/* 227 */     localeMenus.add(localeArabic);
/* 228 */     localeMenus.add(localeEnglish);
/* 229 */     localeMenus.add(localeGerman);
/* 230 */     localeMenus.add(localeRussian);
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 256 */     return lafMenu;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void createSubstanceMenu(IClientContext context, JMenu substanceMenus) {
/* 261 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Substance", "org.jvnet.substance.SubstanceLookAndFeel"));
/* 262 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Default", "org.jvnet.substance.SubstanceDefaultLookAndFeel"));
/* 263 */     substanceMenus.addSeparator();
/* 264 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Business", "org.jvnet.substance.skin.SubstanceBusinessLookAndFeel"));
/* 265 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Business Black Steel", 
/* 266 */           "org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel"));
/* 267 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Business Blue Steel", 
/* 268 */           "org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel"));
/* 269 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Creme", "org.jvnet.substance.skin.SubstanceCremeLookAndFeel"));
/* 270 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Moderate", "org.jvnet.substance.skin.SubstanceModerateLookAndFeel"));
/* 271 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Nebula", "org.jvnet.substance.skin.SubstanceNebulaLookAndFeel"));
/* 272 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Nebula Brick Wall", 
/* 273 */           "org.jvnet.substance.skin.SubstanceNebulaBrickWallLookAndFeel"));
/* 274 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Office Silver 2007", 
/* 275 */           "org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel"));
/* 276 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Sahara", "org.jvnet.substance.skin.SubstanceSaharaLookAndFeel"));
/* 277 */     substanceMenus.addSeparator();
/* 278 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Field of Wheat", 
/* 279 */           "org.jvnet.substance.skin.SubstanceFieldOfWheatLookAndFeel"));
/* 280 */     substanceMenus.add(
/* 281 */         LAFChanger.getMenuItem(context, "Green Magic", "org.jvnet.substance.skin.SubstanceGreenMagicLookAndFeel"));
/* 282 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Mango", "org.jvnet.substance.skin.SubstanceMangoLookAndFeel"));
/* 283 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Office Blue 2007", 
/* 284 */           "org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel"));
/* 285 */     substanceMenus.addSeparator();
/* 286 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Challenger Deep", 
/* 287 */           "org.jvnet.substance.skin.SubstanceChallengerDeepLookAndFeel"));
/* 288 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Emerald Dusk", 
/* 289 */           "org.jvnet.substance.skin.SubstanceEmeraldDuskLookAndFeel"));
/* 290 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Magma", "org.jvnet.substance.skin.SubstanceMagmaLookAndFeel"));
/* 291 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Raven", "org.jvnet.substance.skin.SubstanceRavenLookAndFeel"));
/* 292 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Raven Graphite", 
/* 293 */           "org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel"));
/* 294 */     substanceMenus.add(LAFChanger.getMenuItem(context, "Raven Graphite Glass", 
/* 295 */           "org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel"));
/*     */   }
/*     */ 
/*     */   
/*     */   protected static Icon getIcon(String name) {
/* 300 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\LAFMenuFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */