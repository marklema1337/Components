/*     */ package com.lbs.laf.mac;
/*     */ 
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.laf.common.borders.FrameRoundedCornerBorder;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import java.awt.Color;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.accessibility.AccessibleContext;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.SwingConstants;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.event.MouseInputAdapter;
/*     */ import javax.swing.plaf.basic.BasicButtonUI;
/*     */ import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
/*     */ import javax.swing.plaf.basic.BasicInternalFrameUI;
/*     */ import sun.swing.SwingUtilities2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DesktopInternalFrameUI
/*     */   extends BasicInternalFrameUI
/*     */ {
/*     */   private String closeButtonToolTip;
/*     */   private String iconButtonToolTip;
/*     */   private String restoreButtonToolTip;
/*     */   private String maxButtonToolTip;
/*     */   private boolean widget;
/*     */   private Color titleColor;
/*     */   
/*     */   public DesktopInternalFrameUI(JInternalFrame b) {
/*  43 */     this(b, false, (Color)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public DesktopInternalFrameUI(JInternalFrame b, boolean widget, Color titleColor) {
/*  48 */     super(b);
/*  49 */     this.widget = widget;
/*  50 */     this.titleColor = titleColor;
/*  51 */     this.closeButtonToolTip = UIManager.getString("InternalFrame.closeButtonToolTip");
/*  52 */     this.iconButtonToolTip = UIManager.getString("InternalFrame.iconButtonToolTip");
/*  53 */     this.restoreButtonToolTip = UIManager.getString("InternalFrame.restoreButtonToolTip");
/*  54 */     this.maxButtonToolTip = UIManager.getString("InternalFrame.maxButtonToolTip");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/*  60 */     super.installDefaults();
/*  61 */     if (this.widget) {
/*     */       
/*  63 */       this.frame.setFrameIcon(DefaultTheme.getImageIcon(getClass(), "widget.png"));
/*  64 */       this.frame.setBorder((Border)new FrameRoundedCornerBorder(4, 0, 0, 0, 8, this.titleColor));
/*     */     } 
/*     */     
/*  67 */     ILbsCultureInfo cul = JLbsLocalizer.getCultureInfo();
/*  68 */     if (cul != null) {
/*     */       
/*  70 */       ComponentOrientation orientation = cul.getComponentOrientation();
/*  71 */       if (orientation != null) {
/*  72 */         this.frame.setComponentOrientation(orientation);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected JComponent createNorthPane(JInternalFrame w) {
/*  80 */     this.titlePane = new BasicInternalFrameTitlePaneExtension(w, null);
/*  81 */     this.titlePane.setSize(w.getWidth(), 24);
/*  82 */     this.titlePane.setPreferredSize(new Dimension(w.getWidth(), 24));
/*  83 */     return this.titlePane;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected MouseInputAdapter createBorderListener(JInternalFrame w) {
/*  89 */     return new BorderListener1(null);
/*     */   }
/*     */   
/*     */   private class BasicInternalFrameTitlePaneExtension
/*     */     extends BasicInternalFrameTitlePane {
/*     */     private static final int CHAR_BUFFER_SIZE = 100;
/*  95 */     private final Object charsBufferLock = new Object();
/*  96 */     private char[] charsBuffer = new char[100];
/*     */ 
/*     */     
/*     */     private BasicInternalFrameTitlePaneExtension(JInternalFrame f) {
/* 100 */       super(f);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void installDefaults() {
/* 106 */       super.installDefaults();
/* 107 */       if (DesktopInternalFrameUI.this.widget) {
/*     */         
/* 109 */         this.frame.setBackground(DesktopInternalFrameUI.this.titleColor);
/* 110 */         this.selectedTitleColor = DesktopInternalFrameUI.this.titleColor;
/* 111 */         this.notSelectedTitleColor = DesktopInternalFrameUI.this.titleColor;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void createButtons() {
/* 118 */       this.iconButton = new DesktopInternalFrameUI.NoFocusButton("InternalFrameTitlePane.iconifyButtonAccessibleName", 
/* 119 */           "InternalFrameTitlePane.iconifyButtonOpacity");
/* 120 */       this.iconButton.addActionListener(this.iconifyAction);
/* 121 */       if (DesktopInternalFrameUI.this.iconButtonToolTip != null && DesktopInternalFrameUI.this.iconButtonToolTip.length() != 0)
/*     */       {
/* 123 */         this.iconButton.setToolTipText(DesktopInternalFrameUI.this.iconButtonToolTip);
/*     */       }
/*     */       
/* 126 */       this.maxButton = new DesktopInternalFrameUI.NoFocusButton("InternalFrameTitlePane.maximizeButtonAccessibleName", 
/* 127 */           "InternalFrameTitlePane.maximizeButtonOpacity");
/* 128 */       this.maxButton.addActionListener(this.maximizeAction);
/*     */       
/* 130 */       this.closeButton = new DesktopInternalFrameUI.NoFocusButton("InternalFrameTitlePane.closeButtonAccessibleName", 
/* 131 */           "InternalFrameTitlePane.closeButtonOpacity");
/* 132 */       this.closeButton.addActionListener(this.closeAction);
/* 133 */       if (DesktopInternalFrameUI.this.closeButtonToolTip != null && DesktopInternalFrameUI.this.closeButtonToolTip.length() != 0)
/*     */       {
/* 135 */         this.closeButton.setToolTipText(DesktopInternalFrameUI.this.closeButtonToolTip);
/*     */       }
/*     */       
/* 138 */       setButtonIcons();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void assembleSystemMenu() {
/* 143 */       this.menuBar = createSystemMenuBar();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void paintComponent(Graphics g) {
/* 149 */       paintTitleBackground(g);
/*     */       
/* 151 */       if (this.frame.getTitle() != null) {
/*     */         int titleX;
/* 153 */         boolean isSelected = this.frame.isSelected();
/* 154 */         Font f = g.getFont();
/* 155 */         g.setFont(getFont());
/* 156 */         if (isSelected) {
/* 157 */           g.setColor(this.selectedTextColor);
/*     */         } else {
/* 159 */           g.setColor(this.notSelectedTextColor);
/*     */         } 
/* 161 */         FontMetrics fm = SwingUtilities2.getFontMetrics(this.frame, g);
/* 162 */         int baseline = (getHeight() + fm.getAscent() - fm.getLeading() - fm.getDescent()) / 2;
/*     */ 
/*     */         
/* 165 */         Rectangle r = new Rectangle(0, 0, 0, 0);
/* 166 */         if (this.frame.isIconifiable()) {
/* 167 */           r = this.iconButton.getBounds();
/* 168 */         } else if (this.frame.isMaximizable()) {
/* 169 */           r = this.maxButton.getBounds();
/* 170 */         } else if (this.frame.isClosable()) {
/* 171 */           r = this.closeButton.getBounds();
/*     */         } 
/*     */         
/* 174 */         String title = this.frame.getTitle();
/* 175 */         if (this.frame.getComponentOrientation() == ComponentOrientation.LEFT_TO_RIGHT) {
/*     */           
/* 177 */           if (r.x == 0)
/* 178 */             r.x = this.frame.getWidth() - (this.frame.getInsets()).right; 
/* 179 */           titleX = this.menuBar.getX() + this.menuBar.getWidth() + 2;
/* 180 */           int titleW = r.x - titleX - 3;
/* 181 */           title = getTitle(this.frame.getTitle(), fm, titleW);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 186 */           int titleW = Math.abs(r.x + (int)r.getWidth() - this.menuBar.getX());
/* 187 */           title = clipForRight2Left(this.frame.getTitle(), fm, titleW);
/* 188 */           int stringWidth = SwingUtilities2.stringWidth(this.frame, fm, title);
/* 189 */           titleX = this.menuBar.getX() - 2 - stringWidth;
/*     */         } 
/* 191 */         SwingUtilities2.drawString(this.frame, g, title, titleX, baseline);
/* 192 */         g.setFont(f);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private String clipForRight2Left(String text, FontMetrics fm, int availTextWidth) {
/* 199 */       String clipString = "...";
/* 200 */       int stringWidth = SwingUtilities2.stringWidth(this.frame, fm, text);
/*     */       
/* 202 */       availTextWidth -= SwingUtilities2.stringWidth(this.frame, fm, clipString);
/* 203 */       if (availTextWidth <= 0)
/* 204 */         return clipString; 
/* 205 */       if (stringWidth < availTextWidth)
/* 206 */         return text; 
/* 207 */       synchronized (this.charsBufferLock) {
/*     */         
/* 209 */         int stringLength = syncCharsBuffer(text);
/* 210 */         int width = 0;
/* 211 */         for (int nChars = 0; nChars < stringLength; nChars++) {
/*     */           
/* 213 */           width += fm.charWidth(this.charsBuffer[nChars]);
/* 214 */           if (width > availTextWidth) {
/*     */             
/* 216 */             text = text.substring(0, nChars);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 221 */       return String.valueOf(text) + clipString;
/*     */     }
/*     */ 
/*     */     
/*     */     private int syncCharsBuffer(String s) {
/* 226 */       int length = s.length();
/* 227 */       if (this.charsBuffer == null || this.charsBuffer.length < length) {
/*     */         
/* 229 */         this.charsBuffer = s.toCharArray();
/*     */       }
/*     */       else {
/*     */         
/* 233 */         s.getChars(0, length, this.charsBuffer, 0);
/*     */       } 
/* 235 */       return length;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class NoFocusButton
/*     */     extends JButton
/*     */   {
/*     */     private String uiKey;
/*     */     
/*     */     public NoFocusButton(String uiKey, String opacityKey) {
/* 245 */       setFocusPainted(false);
/* 246 */       setMargin(new Insets(0, 0, 0, 0));
/* 247 */       this.uiKey = uiKey;
/*     */       
/* 249 */       Object opacity = UIManager.get(opacityKey);
/* 250 */       if (opacity instanceof Boolean)
/*     */       {
/* 252 */         setOpaque(((Boolean)opacity).booleanValue());
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isFocusTraversable() {
/* 258 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void requestFocus() {}
/*     */ 
/*     */     
/*     */     public AccessibleContext getAccessibleContext() {
/* 267 */       AccessibleContext ac = super.getAccessibleContext();
/* 268 */       if (this.uiKey != null) {
/*     */         
/* 270 */         ac.setAccessibleName(UIManager.getString(this.uiKey));
/* 271 */         this.uiKey = null;
/*     */       } 
/* 273 */       return ac;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void updateUI() {
/* 279 */       setUI(new BasicButtonUI());
/*     */     }
/*     */   }
/*     */   
/*     */   private class BorderListener1
/*     */     extends BasicInternalFrameUI.BorderListener implements SwingConstants {
/*     */     private BorderListener1() {}
/*     */     
/*     */     Rectangle getIconBounds() {
/* 288 */       boolean leftToRight = DesktopInternalFrameUI.this.frame.getComponentOrientation().isLeftToRight();
/*     */       
/* 290 */       int xOffset = leftToRight ? 
/* 291 */         5 : (
/* 292 */         DesktopInternalFrameUI.this.titlePane.getWidth() - 5);
/* 293 */       Rectangle rect = null;
/*     */       
/* 295 */       Icon icon = DesktopInternalFrameUI.this.frame.getFrameIcon();
/* 296 */       if (icon != null) {
/*     */         
/* 298 */         if (!leftToRight)
/*     */         {
/* 300 */           xOffset -= icon.getIconWidth();
/*     */         }
/* 302 */         int iconY = DesktopInternalFrameUI.this.titlePane.getHeight() / 2 - icon.getIconHeight() / 2;
/* 303 */         rect = new Rectangle(xOffset, iconY, icon.getIconWidth(), icon.getIconHeight());
/*     */       } 
/* 305 */       return rect;
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseClicked(MouseEvent e) {
/* 310 */       if (e.getClickCount() == 2 && e.getSource() == DesktopInternalFrameUI.this.getNorthPane() && DesktopInternalFrameUI.this.frame.isClosable() && !DesktopInternalFrameUI.this.frame.isIcon()) {
/*     */         
/* 312 */         Rectangle rect = getIconBounds();
/* 313 */         if (rect != null && rect.contains(e.getX(), e.getY()))
/*     */         {
/* 315 */           DesktopInternalFrameUI.this.frame.doDefaultCloseAction();
/*     */         }
/*     */         else
/*     */         {
/* 319 */           super.mouseClicked(e);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 324 */         super.mouseClicked(e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\mac\DesktopInternalFrameUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */