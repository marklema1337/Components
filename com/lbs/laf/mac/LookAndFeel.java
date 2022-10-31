/*     */ package com.lbs.laf.mac;
/*     */ 
/*     */ import com.lbs.controls.groupbox.JLbsRoundGroupBorder;
/*     */ import com.lbs.laf.common.DefaultSkinnableTheme;
/*     */ import com.lbs.laf.common.SkinImage;
/*     */ import com.lbs.laf.common.SkinableLookAndFeel;
/*     */ import com.lbs.laf.common.SkinnedBorder;
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
/*     */ import javax.swing.plaf.InsetsUIResource;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LookAndFeel
/*     */   extends SkinableLookAndFeel
/*     */ {
/*  41 */   private static final Color BACK_GROUND = new Color(245, 245, 245);
/*  42 */   private static final Color DEFAULT_TOOLBARTEXT_COLOR = new Color(0, 0, 0);
/*     */   private static final long serialVersionUID = 1L;
/*  44 */   private static Border ms_TextBorder = (Border)new SkinnedBorder(new Insets(2, 5, 2, 5), 
/*  45 */       new SkinImage(getImagePath("textbox.png"), 3, 3));
/*     */   
/*     */   private Color m_ControlColor;
/*  48 */   private static final Double SIZE_DIFF = Double.valueOf(0.04D);
/*     */   
/*     */   private static final int DEFAULT_DPI = 96;
/*     */   private static final int MAX_APP_FONT_SIZE = 17;
/*     */   
/*     */   public LookAndFeel() {
/*  54 */     this.m_ControlColor = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public LookAndFeel(Color cl) {
/*  59 */     this.m_ControlColor = cl;
/*     */   }
/*     */ 
/*     */   
/*     */   protected MetalTheme getDefaultTheme() {
/*  64 */     DefaultTheme theme = new DefaultTheme();
/*  65 */     if (this.m_ControlColor != null)
/*  66 */       theme.secondary3 = new ColorUIResource(this.m_ControlColor); 
/*  67 */     return (MetalTheme)theme;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getImagePath(String fileName) {
/*  72 */     return DefaultSkinnableTheme.getImagePath(LookAndFeel.class, fileName);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initComponentDefaults(UIDefaults table) {
/*  77 */     super.initComponentDefaults(table);
/*  78 */     prepareAppFontSize();
/*  79 */     table.put("Button.margin", new InsetsUIResource(4, 16, 4, 16));
/*  80 */     table.put("Button.border", new BasicBorders.MarginBorder());
/*  81 */     table.put("Button.font", new Font(JLbsConstants.APP_FONT, 1, 10));
/*  82 */     table.put("Button.foreground", Color.white);
/*  83 */     table.put("Button.roll", Color.black);
/*  84 */     table.put("Button.disabledText", Color.white);
/*     */     
/*  86 */     table.put("ToggleButton.border", new BasicBorders.MarginBorder());
/*  87 */     Border border = null;
/*     */     
/*  89 */     table.put("TextField.border", ms_TextBorder);
/*  90 */     table.put("FormattedTextField.border", ms_TextBorder);
/*  91 */     table.put("TextField.selectionForeground", Color.white);
/*  92 */     table.put("TextField.selectionBackground", new Color(50, 50, 80));
/*  93 */     table.put("TextField.background", Color.white);
/*  94 */     table.put("TextField.disabledBackground", new Color(236, 233, 216));
/*     */     
/*  96 */     table.put("PasswordField.border", ms_TextBorder);
/*  97 */     table.put("PasswordField.selectionForeground", Color.white);
/*  98 */     table.put("PasswordField.selectionBackground", new Color(50, 50, 80));
/*  99 */     table.put("PasswordField.background", Color.white);
/* 100 */     table.put("PasswordField.disabledBackground", new Color(236, 233, 216));
/*     */     
/* 102 */     table.put("FormattedTextField.disabledBackground", new Color(236, 233, 216));
/* 103 */     table.put("FormattedTextField.selectionForeground", Color.white);
/* 104 */     table.put("FormattedTextField.selectionBackground", new Color(50, 50, 80));
/* 105 */     table.put("FormattedTextField.background", Color.white);
/* 106 */     table.put("TextField.inactiveBackground", Color.blue);
/*     */     
/* 108 */     table.put("ComboEdit.border", ms_TextBorder);
/* 109 */     table.put("ShadowedBorder", new SkinnedBorder(new Insets(0, 0, 3, 0), new SkinImage(getImagePath("shadowedborder.png"), 2, 
/* 110 */             3)));
/*     */     
/* 112 */     table.put("ComboBox.border", BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0));
/* 113 */     table.put("ComboBox.foreground", Color.black);
/* 114 */     table.put("ComboBox.background", Color.white);
/* 115 */     table.put("ComboBox.selectionForeground", new Color(169, 169, 169));
/* 116 */     table.put("ComboBox.selectionBackground", new Color(50, 50, 80));
/* 117 */     table.put("ComboBox.disabledBackground", Color.white);
/*     */     
/* 119 */     DefaultTheme theme = (DefaultTheme)getDefaultTheme();
/* 120 */     table.put("ToolTip.font", theme.getControlTextFont());
/* 121 */     table.put("ToolTip.border", new CompoundBorder(new LineBorder(Color.black, 1), new EmptyBorder(2, 2, 2, 2)));
/*     */     
/* 123 */     table.put("Panel.roundFrame", new JLbsRoundGroupBorder());
/* 124 */     table.put("Panel.CaptionColor", new Color(17763));
/* 125 */     table.put("Panel.CaptionColorDisabled", new Color(10991103));
/*     */     
/* 127 */     border = new LineBorder(Color.LIGHT_GRAY, 1);
/* 128 */     table.put("PopupMenu.border", border);
/* 129 */     border = new EmptyBorder(2, 4, 2, 4);
/* 130 */     table.put("Menu.border", border);
/* 131 */     table.put("MenuItem.border", border);
/* 132 */     table.put("CheckBoxMenuItem.border", border);
/* 133 */     table.put("RadioButtonMenuItem.border", border);
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
/* 146 */     table.put("TabbedPane.selectedTabPadInsets", new Insets(1, 1, 2, 2));
/* 147 */     table.put("TabbedPane.tabInsets", new Insets(2, 0, 1, 2));
/* 148 */     table.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
/* 149 */     table.put("TabbedPane.tabsOverlapBorder", Boolean.valueOf(true));
/* 150 */     table.put("TabbedPane.textIconGap", Integer.valueOf(10));
/* 151 */     table.put("TabbedPane.foreground", Color.white);
/*     */     
/* 153 */     table.put("Panel.background", BACK_GROUND);
/* 154 */     table.put("RootPane.frameBorder", new EmptyBorder(1, 1, 1, 1));
/*     */     
/* 156 */     table.put("CheckBox.background", BACK_GROUND);
/*     */     
/* 158 */     table.put("Table.background", BACK_GROUND);
/*     */ 
/*     */     
/* 161 */     table.put("RadioButton.background", BACK_GROUND);
/*     */ 
/*     */ 
/*     */     
/* 165 */     table.put("Tree.expandedIcon", DefaultTheme.getImageIcon(getClass(), "treeexpanded.png"));
/* 166 */     table.put("Tree.collapsedIcon", DefaultTheme.getImageIcon(getClass(), "treecollapsed.png"));
/* 167 */     table.put("Tree.hash", Color.gray);
/* 168 */     table.put("Tree.background", BACK_GROUND);
/*     */ 
/*     */     
/* 171 */     table.put("OptionPane.informationIcon", DefaultTheme.getImageIcon(getClass(), "msginformation.png"));
/* 172 */     table.put("OptionPane.errorIcon", DefaultTheme.getImageIcon(getClass(), "msgerror.png"));
/* 173 */     table.put("OptionPane.warningIcon", DefaultTheme.getImageIcon(getClass(), "msgwarning.png"));
/* 174 */     table.put("OptionPane.questionIcon", DefaultTheme.getImageIcon(getClass(), "msgquestion.png"));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean checkAppFontSize(StyleContext ctx, JLabel label, int labelWidth) {
/* 179 */     int l_width = ctx.getFontMetrics(label.getFont()).stringWidth(label.getText());
/* 180 */     double cmDefault = convertCM(labelWidth, 96);
/* 181 */     double cmLabel = convertCM(l_width, Toolkit.getDefaultToolkit().getScreenResolution());
/* 182 */     if (Math.abs(cmDefault - cmLabel) < SIZE_DIFF.doubleValue()) {
/* 183 */       return true;
/*     */     }
/* 185 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void prepareAppFontSize() {
/* 190 */     if (JLbsConstants.APP_SIZE_DEFAULT != 11) {
/* 191 */       JLbsConstants.APP_FONT_SIZE = JLbsConstants.APP_SIZE_DEFAULT;
/*     */     }
/* 193 */     if (JLbsConstants.APP_FONT_SIZE == 11) {
/*     */       
/* 195 */       int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
/* 196 */       if (dpi > 96) {
/*     */         
/* 198 */         int appSize = JLbsConstants.APP_FONT_SIZE;
/* 199 */         String text = "XWI";
/* 200 */         StyleContext ctx = new StyleContext();
/* 201 */         Font f = new Font(JLbsConstants.APP_FONT, 0, appSize);
/* 202 */         JLabel label = new JLabel(text);
/* 203 */         label.setFont(f);
/* 204 */         int labelWidth = ctx.getFontMetrics(label.getFont()).stringWidth(label.getText());
/* 205 */         boolean found = checkAppFontSize(ctx, label, labelWidth);
/* 206 */         while (!found) {
/*     */           
/* 208 */           if (appSize == 17)
/*     */             break; 
/* 210 */           appSize++;
/* 211 */           Font newFont = new Font(f.getFontName(), f.getStyle(), appSize);
/* 212 */           label.setFont(newFont);
/* 213 */           found = checkAppFontSize(ctx, label, labelWidth);
/*     */         } 
/* 215 */         JLbsConstants.APP_FONT_SIZE = appSize;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double convertCM(int size, int res) {
/* 223 */     return size * 2.54D / res;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initClassDefaults(UIDefaults table) {
/* 228 */     super.initClassDefaults(table);
/* 229 */     ComboBoxUI.ms_DefaultBorder = ms_TextBorder;
/* 230 */     putTable(table, "ButtonUI", ButtonUI.class);
/* 231 */     putTable(table, "ComboBoxUI", ComboBoxUI.class);
/* 232 */     putTable(table, "CheckBoxUI", CheckBoxUI.class);
/* 233 */     putTable(table, "RadioButtonUI", RadioButtonUI.class);
/* 234 */     putTable(table, "PopupMenuSeparatorUI", PopupMenuSeparatorUI.class);
/* 235 */     putTable(table, "SplitPaneUI", SplitPaneUI.class);
/* 236 */     putTable(table, "MenuItemUI", MenuItemUI.class);
/* 237 */     putTable(table, "MenuUI", MenuUI.class);
/* 238 */     putTable(table, "TabbedPaneUI", TabbedPaneUI.class);
/* 239 */     putTable(table, "ScrollBarUI", ScrollBarUI.class);
/* 240 */     putTable(table, "ToolBarUI", ToolBarUI.class);
/* 241 */     putTable(table, "MenuBarUI", MenuBarUI.class);
/* 242 */     putTable(table, "ListUI", ListUI.class);
/* 243 */     putTable(table, "TreeUI", TreeUI.class);
/* 244 */     putTable(table, "TableUI", TableUI.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getSupportsWindowDecorations() {
/* 249 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Color getToolbarTextColorForImageButton() {
/* 254 */     return DEFAULT_TOOLBARTEXT_COLOR;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\mac\LookAndFeel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */