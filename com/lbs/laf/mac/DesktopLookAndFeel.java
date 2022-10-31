/*     */ package com.lbs.laf.mac;
/*     */ 
/*     */ import com.lbs.controls.groupbox.JLbsRoundGroupBorder;
/*     */ import com.lbs.laf.common.DefaultSkinnableTheme;
/*     */ import com.lbs.laf.common.SkinImage;
/*     */ import com.lbs.laf.common.SkinableLookAndFeel;
/*     */ import com.lbs.laf.common.SkinnedBorder;
/*     */ import com.lbs.laf.common.borders.FrameRoundedCornerBorder;
/*     */ import com.lbs.laf.common.borders.RoundedCornerBorder;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Toolkit;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.UIDefaults;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.border.CompoundBorder;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.border.LineBorder;
/*     */ import javax.swing.plaf.ColorUIResource;
/*     */ import javax.swing.plaf.basic.BasicBorders;
/*     */ import javax.swing.plaf.metal.MetalTheme;
/*     */ import javax.swing.text.StyleContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DesktopLookAndFeel
/*     */   extends SkinableLookAndFeel
/*     */ {
/*  37 */   private static final Color BACK_GROUND = new Color(245, 245, 245);
/*  38 */   private static final Color BACK_GROUND_DESKTOP = new Color(232, 245, 236);
/*  39 */   private static final Color DEFAULT_TOOLBARTEXT_COLOR = new Color(0, 0, 0);
/*     */   private static final long serialVersionUID = 1L;
/*  41 */   private static Border ms_TextBorder1 = (Border)new SkinnedBorder(new Insets(2, 5, 2, 5), new SkinImage(getImagePath("textbox.png"), 3, 
/*  42 */         3));
/*  43 */   private static Border ms_TextBorder = new EmptyBorder(new Insets(2, 5, 2, 5));
/*     */   
/*     */   private Color m_ControlColor;
/*  46 */   private static final Double SIZE_DIFF = Double.valueOf(0.04D);
/*     */   
/*     */   private static final int DEFAULT_DPI = 96;
/*     */   private static final int MAX_APP_FONT_SIZE = 17;
/*     */   
/*     */   public DesktopLookAndFeel() {
/*  52 */     this.m_ControlColor = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public DesktopLookAndFeel(Color cl) {
/*  57 */     this.m_ControlColor = cl;
/*     */   }
/*     */ 
/*     */   
/*     */   protected MetalTheme getDefaultTheme() {
/*  62 */     DefaultTheme theme = new DefaultTheme();
/*  63 */     if (this.m_ControlColor != null)
/*  64 */       theme.secondary3 = new ColorUIResource(this.m_ControlColor); 
/*  65 */     return (MetalTheme)theme;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getImagePath(String fileName) {
/*  70 */     return DefaultSkinnableTheme.getImagePath(DesktopLookAndFeel.class, fileName);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initComponentDefaults(UIDefaults table) {
/*  75 */     super.initComponentDefaults(table);
/*  76 */     prepareAppFontSize();
/*     */ 
/*     */     
/*  79 */     table.put("Button.border", new EmptyBorder(8, 8, 8, 8));
/*  80 */     table.put("Button.font", new Font(JLbsConstants.APP_FONT, 1, JLbsConstants.APP_SIZE_DEFAULT - 1));
/*  81 */     table.put("Button.foreground", Color.white);
/*     */     
/*  83 */     table.put("Button.disabledText", Color.white);
/*     */     
/*  85 */     table.put("ToggleButton.border", new BasicBorders.MarginBorder());
/*  86 */     Border border = null;
/*     */     
/*  88 */     table.put("TextField.border", new RoundedCornerBorder());
/*  89 */     table.put("FormattedTextField.border", new RoundedCornerBorder());
/*  90 */     table.put("TextField.selectionForeground", Color.white);
/*  91 */     table.put("TextField.selectionBackground", new Color(50, 50, 80));
/*  92 */     table.put("TextField.background", Color.white);
/*  93 */     table.put("TextField.disabledBackground", new Color(236, 233, 216));
/*     */     
/*  95 */     table.put("PasswordField.border", ms_TextBorder);
/*  96 */     table.put("PasswordField.selectionForeground", Color.white);
/*  97 */     table.put("PasswordField.selectionBackground", new Color(50, 50, 80));
/*  98 */     table.put("PasswordField.background", Color.white);
/*  99 */     table.put("PasswordField.disabledBackground", new Color(236, 233, 216));
/*     */     
/* 101 */     table.put("FormattedTextField.disabledBackground", new Color(236, 233, 216));
/* 102 */     table.put("FormattedTextField.selectionForeground", Color.white);
/* 103 */     table.put("FormattedTextField.selectionBackground", new Color(50, 50, 80));
/* 104 */     table.put("FormattedTextField.background", Color.white);
/* 105 */     table.put("TextField.inactiveBackground", Color.blue);
/*     */     
/* 107 */     table.put("ComboEdit.border", ms_TextBorder);
/* 108 */     table.put("ShadowedBorder", new SkinnedBorder(new Insets(0, 0, 3, 0), new SkinImage(getImagePath("shadowedborder.png"), 2, 
/* 109 */             3)));
/*     */     
/* 111 */     table.put("ComboBox.border", BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0));
/* 112 */     table.put("ComboBox.foreground", Color.black);
/* 113 */     table.put("ComboBox.background", Color.white);
/* 114 */     table.put("ComboBox.selectionForeground", new Color(169, 169, 169));
/* 115 */     table.put("ComboBox.selectionBackground", new Color(50, 50, 80));
/* 116 */     table.put("ComboBox.disabledBackground", Color.white);
/*     */     
/* 118 */     DefaultTheme theme = (DefaultTheme)getDefaultTheme();
/* 119 */     table.put("ToolTip.font", theme.getControlTextFont());
/* 120 */     table.put("ToolTip.border", new CompoundBorder(new LineBorder(Color.black, 1), new EmptyBorder(2, 2, 2, 2)));
/*     */     
/* 122 */     table.put("Panel.roundFrame", new JLbsRoundGroupBorder());
/* 123 */     table.put("Panel.CaptionColor", new Color(17763));
/* 124 */     table.put("Panel.CaptionColorDisabled", new Color(10991103));
/*     */     
/* 126 */     border = new LineBorder(Color.LIGHT_GRAY, 1);
/* 127 */     table.put("PopupMenu.border", border);
/* 128 */     border = new EmptyBorder(2, 4, 2, 4);
/* 129 */     table.put("Menu.border", border);
/* 130 */     table.put("MenuItem.border", border);
/* 131 */     table.put("CheckBoxMenuItem.border", border);
/* 132 */     table.put("RadioButtonMenuItem.border", border);
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
/* 145 */     table.put("TabbedPane.selectedTabPadInsets", new Insets(0, 8, 0, 0));
/* 146 */     table.put("TabbedPane.tabInsets", new Insets(6, 4, 5, 6));
/* 147 */     table.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
/* 148 */     table.put("TabbedPane.tabsOverlapBorder", Boolean.valueOf(true));
/* 149 */     table.put("TabbedPane.textIconGap", Integer.valueOf(10));
/* 150 */     table.put("TabbedPane.foreground", Color.white);
/*     */ 
/*     */     
/* 153 */     table.put("Panel.background", BACK_GROUND_DESKTOP);
/* 154 */     table.put("RootPane.frameBorder", new EmptyBorder(1, 1, 1, 1));
/*     */     
/* 156 */     table.put("CheckBox.background", BACK_GROUND_DESKTOP);
/*     */     
/* 158 */     table.put("Table.background", BACK_GROUND);
/*     */ 
/*     */     
/* 161 */     table.put("RadioButton.background", BACK_GROUND_DESKTOP);
/*     */ 
/*     */ 
/*     */     
/* 165 */     table.put("Tree.expandedIcon", DefaultTheme.getImageIcon(getClass(), "treeexpandeddesktop.png"));
/* 166 */     table.put("Tree.collapsedIcon", DefaultTheme.getImageIcon(getClass(), "treecollapseddesktop.png"));
/* 167 */     table.put("Tree.hash", Color.gray);
/* 168 */     table.put("Tree.background", BACK_GROUND);
/*     */ 
/*     */     
/* 171 */     table.put("OptionPane.informationIcon", DefaultTheme.getImageIcon(getClass(), "msginformationdesktop.png"));
/* 172 */     table.put("OptionPane.errorIcon", DefaultTheme.getImageIcon(getClass(), "msgerrordesktop.png"));
/* 173 */     table.put("OptionPane.warningIcon", DefaultTheme.getImageIcon(getClass(), "msgwarningdesktop.png"));
/* 174 */     table.put("OptionPane.questionIcon", DefaultTheme.getImageIcon(getClass(), "msgquestiondesktop.png"));
/* 175 */     table.put("OptionPane.background", BACK_GROUND_DESKTOP);
/*     */     
/* 177 */     table.put("activeCaption", new Color(230, 230, 230));
/*     */     
/* 179 */     table.put("RootPane.plainDialogBorder", new FrameRoundedCornerBorder(4, 8, 8, 8, new Color(230, 230, 230)));
/*     */     
/* 181 */     table.put("Label.foreground", new Color(21, 59, 53));
/*     */     
/* 183 */     table.put("InternalFrame.border", new FrameRoundedCornerBorder(8, 8, 8, 8, null));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 188 */     if ("0601".equalsIgnoreCase(JLbsConstants.getProductType())) {
/* 189 */       table.put("InternalFrame.icon", DefaultTheme.getImageIcon(getClass(), "LbsApplicationDesktopSaasErp.png"));
/*     */     } else {
/* 191 */       table.put("InternalFrame.icon", DefaultTheme.getImageIcon(getClass(), "LbsApplicationDesktop3.png"));
/* 192 */     }  if (JLbsConstants.HR)
/* 193 */       table.put("InternalFrame.icon", DefaultTheme.getImageIcon(getClass(), "jHR_favicon.png")); 
/* 194 */     table.put("InternalFrame.iconifyIcon", DefaultTheme.getImageIcon(getClass(), "icon.png"));
/* 195 */     table.put("InternalFrame.maximizeIcon", DefaultTheme.getImageIcon(getClass(), "maxmin.png"));
/* 196 */     table.put("InternalFrame.minimizeIcon", DefaultTheme.getImageIcon(getClass(), "maxmin.png"));
/* 197 */     table.put("InternalFrame.closeIcon", DefaultTheme.getImageIcon(getClass(), "close.png"));
/* 198 */     table.put("InternalFrame.activeTitleBackground", new Color(200, 200, 200));
/* 199 */     table.put("InternalFrame.inactiveTitleBackground", new Color(230, 230, 230));
/* 200 */     table.put("InternalFrame.titleButtonHeight", Integer.valueOf(20));
/* 201 */     table.put("InternalFrame.titleButtonWidth", Integer.valueOf(20));
/* 202 */     table.put("InternalFrame.titlePaneHeight", Integer.valueOf(50));
/* 203 */     table.put("InternalFrame.titleFont", new Font(JLbsConstants.APP_FONT, 1, JLbsConstants.APP_FONT_SIZE));
/* 204 */     table.put("InternalFrameTitlePane.iconifyButtonOpacity", Boolean.valueOf(false));
/* 205 */     table.put("InternalFrameTitlePane.maximizeButtonOpacity", Boolean.valueOf(false));
/* 206 */     table.put("InternalFrameTitlePane.miButtonOpacity", Boolean.valueOf(false));
/* 207 */     table.put("InternalFrameTitlePane.closeButtonOpacity", Boolean.valueOf(false));
/*     */     
/* 209 */     table.put("DateEdit.dateIcon", DefaultTheme.getImageIcon(getClass(), "calendar.png"));
/*     */     
/* 211 */     table.put("FileChooser.upFolderIcon", DefaultTheme.getImageIcon(getClass(), "upFolder.png"));
/* 212 */     table.put("FileChooser.homeFolderIcon", DefaultTheme.getImageIcon(getClass(), "homeFolder.png"));
/* 213 */     table.put("FileChooser.newFolderIcon", DefaultTheme.getImageIcon(getClass(), "newFolder.png"));
/* 214 */     table.put("FileChooser.listViewIcon", DefaultTheme.getImageIcon(getClass(), "listView.png"));
/* 215 */     table.put("FileChooser.detailsViewIcon", DefaultTheme.getImageIcon(getClass(), "detailsView.png"));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean checkAppFontSize(StyleContext ctx, JLabel label, int labelWidth) {
/* 220 */     int l_width = ctx.getFontMetrics(label.getFont()).stringWidth(label.getText());
/* 221 */     double cmDefault = convertCM(labelWidth, 96);
/* 222 */     double cmLabel = convertCM(l_width, Toolkit.getDefaultToolkit().getScreenResolution());
/* 223 */     if (Math.abs(cmDefault - cmLabel) < SIZE_DIFF.doubleValue()) {
/* 224 */       return true;
/*     */     }
/* 226 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void prepareAppFontSize() {
/* 231 */     if (JLbsConstants.APP_SIZE_DEFAULT != 11) {
/* 232 */       JLbsConstants.APP_FONT_SIZE = JLbsConstants.APP_SIZE_DEFAULT;
/*     */     }
/* 234 */     if (JLbsConstants.APP_FONT_SIZE == 11) {
/*     */       
/* 236 */       int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
/* 237 */       if (dpi > 96) {
/*     */         
/* 239 */         int appSize = JLbsConstants.APP_FONT_SIZE;
/* 240 */         String text = "XWI";
/* 241 */         StyleContext ctx = new StyleContext();
/* 242 */         Font f = new Font(JLbsConstants.APP_FONT, 0, appSize);
/* 243 */         JLabel label = new JLabel(text);
/* 244 */         label.setFont(f);
/* 245 */         int labelWidth = ctx.getFontMetrics(label.getFont()).stringWidth(label.getText());
/* 246 */         boolean found = checkAppFontSize(ctx, label, labelWidth);
/* 247 */         while (!found) {
/*     */           
/* 249 */           if (appSize == 17)
/*     */             break; 
/* 251 */           appSize++;
/* 252 */           Font newFont = new Font(f.getFontName(), f.getStyle(), appSize);
/* 253 */           label.setFont(newFont);
/* 254 */           found = checkAppFontSize(ctx, label, labelWidth);
/*     */         } 
/* 256 */         JLbsConstants.APP_FONT_SIZE = appSize;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double convertCM(int size, int res) {
/* 264 */     return size * 2.54D / res;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initClassDefaults(UIDefaults table) {
/* 269 */     super.initClassDefaults(table);
/* 270 */     ComboBoxUI.ms_DefaultBorder = ms_TextBorder;
/* 271 */     putTable(table, "ButtonUI", ButtonDesktopUI.class);
/* 272 */     putTable(table, "ComboBoxUI", ComboBoxUI.class);
/* 273 */     putTable(table, "CheckBoxUI", CheckBoxUI.class);
/* 274 */     putTable(table, "RadioButtonUI", RadioButtonUI.class);
/* 275 */     putTable(table, "PopupMenuSeparatorUI", PopupMenuSeparatorUI.class);
/* 276 */     putTable(table, "SplitPaneUI", SplitPaneUI.class);
/* 277 */     putTable(table, "MenuItemUI", MenuItemUI.class);
/* 278 */     putTable(table, "MenuUI", MenuUI.class);
/* 279 */     putTable(table, "TabbedPaneUI", TabbedPaneUI.class);
/* 280 */     putTable(table, "ScrollBarUI", ScrollBarUI.class);
/* 281 */     putTable(table, "ToolBarUI", ToolBarUI.class);
/*     */     
/* 283 */     putTable(table, "ListUI", ListUI.class);
/* 284 */     putTable(table, "TreeUI", TreeUI.class);
/* 285 */     putTable(table, "TableUI", TableUI.class);
/* 286 */     putTable(table, "RootPaneUI", DesktopRootPaneUI.class);
/* 287 */     putTable(table, "ToggleButtonUI", ButtonDesktopUI.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getSupportsWindowDecorations() {
/* 292 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Color getToolbarTextColorForImageButton() {
/* 297 */     return DEFAULT_TOOLBARTEXT_COLOR;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\mac\DesktopLookAndFeel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */