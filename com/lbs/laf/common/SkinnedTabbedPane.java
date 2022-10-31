/*     */ package com.lbs.laf.common;
/*     */ 
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.metal.MetalTabbedPaneUI;
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
/*     */ public abstract class SkinnedTabbedPane
/*     */   extends MetalTabbedPaneUI
/*     */ {
/*  36 */   protected static final Color m_ComponentBackground = UIManager.getColor("control");
/*  37 */   protected static final Color m_TabBackground = UIManager.getColor("controlHighlight");
/*  38 */   protected static final Color m_OuterHighlight = Color.gray;
/*     */   
/*     */   private boolean m_DrawFocusRect = false;
/*     */   private boolean m_DrawBoldSelected = true;
/*  42 */   private int m_Rollover = -1;
/*  43 */   private SkinnedButtonIndexModel m_IndexModel = new SkinnedButtonIndexModel();
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  47 */     super.installUI(c);
/*  48 */     if (c instanceof JTabbedPane && this.m_DrawBoldSelected) {
/*     */       
/*  50 */       JTabbedPane tabPane = (JTabbedPane)c;
/*  51 */       Font font = tabPane.getFont();
/*  52 */       tabPane.setFont(new Font(font.getName(), font.getStyle() | 0x1, font.getSize()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/*  58 */     super.installListeners();
/*     */     
/*  60 */     this.tabPane.addMouseMotionListener((MouseMotionListener)this.mouseListener);
/*  61 */     this.tabPane.addMouseListener(this.mouseListener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void uninstallListeners() {
/*  69 */     super.uninstallListeners();
/*  70 */     this.tabPane.removeMouseMotionListener((MouseMotionListener)this.mouseListener);
/*  71 */     this.tabPane.removeMouseListener(this.mouseListener);
/*     */   }
/*     */ 
/*     */   
/*     */   protected MouseListener createMouseListener() {
/*  76 */     return new TabMouseHandler();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getTabAtLocation(int x, int y) {
/*  99 */     if (this.rects != null) {
/*     */       
/* 101 */       int tabCount = this.tabPane.getTabCount();
/* 102 */       for (int i = 0; i < tabCount && i < this.rects.length; i++) {
/* 103 */         if (this.rects[i] != null && this.rects[i].contains(x, y))
/* 104 */           return i; 
/*     */       } 
/* 106 */     }  return -1;
/*     */   }
/*     */   
/*     */   public class TabMouseHandler
/*     */     implements MouseListener, MouseMotionListener
/*     */   {
/*     */     public void mousePressed(MouseEvent e) {
/* 113 */       if (SkinnedTabbedPane.this.tabPane == null)
/*     */         return; 
/* 115 */       if (!SkinnedTabbedPane.this.tabPane.isEnabled()) {
/*     */         return;
/*     */       }
/*     */       
/* 119 */       int tabIndex = SkinnedTabbedPane.this.getTabAtLocation(e.getX(), e.getY());
/* 120 */       if (tabIndex >= 0 && SkinnedTabbedPane.this.tabPane.isEnabledAt(tabIndex))
/*     */       {
/* 122 */         if (tabIndex == SkinnedTabbedPane.this.tabPane.getSelectedIndex()) {
/*     */           
/* 124 */           if (SkinnedTabbedPane.this.tabPane.isRequestFocusEnabled())
/*     */           {
/* 126 */             SkinnedTabbedPane.this.tabPane.requestFocus();
/* 127 */             SkinnedTabbedPane.this.tabPane.repaint(SkinnedTabbedPane.this.getTabBounds(SkinnedTabbedPane.this.tabPane, tabIndex));
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 132 */           SkinnedTabbedPane.this.tabPane.setSelectedIndex(tabIndex);
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseEntered(MouseEvent e) {}
/*     */ 
/*     */     
/*     */     public void mouseExited(MouseEvent e) {
/* 143 */       if (SkinnedTabbedPane.this.tabPane != null && SkinnedTabbedPane.this.m_Rollover != -1 && SkinnedTabbedPane.this.m_Rollover < SkinnedTabbedPane.this.tabPane.getTabCount()) {
/*     */         
/* 145 */         SkinnedTabbedPane.this.tabPane.repaint(SkinnedTabbedPane.this.getTabBounds(SkinnedTabbedPane.this.tabPane, SkinnedTabbedPane.this.m_Rollover));
/* 146 */         SkinnedTabbedPane.this.m_Rollover = -1;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseClicked(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseReleased(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseDragged(MouseEvent e) {}
/*     */ 
/*     */     
/*     */     public void mouseMoved(MouseEvent e) {
/* 164 */       if (SkinnedTabbedPane.this.tabPane == null)
/*     */         return; 
/* 166 */       if (!SkinnedTabbedPane.this.tabPane.isEnabled()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 171 */       int tabIndex = SkinnedTabbedPane.this.getTabAtLocation(e.getX(), e.getY());
/* 172 */       if (tabIndex != SkinnedTabbedPane.this.m_Rollover && SkinnedTabbedPane.this.m_Rollover != -1) {
/*     */         
/* 174 */         if (SkinnedTabbedPane.this.m_Rollover >= 0 && SkinnedTabbedPane.this.m_Rollover < SkinnedTabbedPane.this.tabPane.getTabCount())
/* 175 */           SkinnedTabbedPane.this.tabPane.repaint(SkinnedTabbedPane.this.getTabBounds(SkinnedTabbedPane.this.tabPane, SkinnedTabbedPane.this.m_Rollover)); 
/* 176 */         if (tabIndex == -1)
/* 177 */           SkinnedTabbedPane.this.m_Rollover = -1; 
/*     */       } 
/* 179 */       if (tabIndex >= 0 && SkinnedTabbedPane.this.tabPane.isEnabledAt(tabIndex) && tabIndex < SkinnedTabbedPane.this.tabPane.getTabCount())
/*     */       {
/* 181 */         if (tabIndex != SkinnedTabbedPane.this.m_Rollover) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 186 */           SkinnedTabbedPane.this.m_Rollover = tabIndex;
/* 187 */           if (tabIndex >= 0 && tabIndex < SkinnedTabbedPane.this.tabPane.getTabCount()) {
/* 188 */             SkinnedTabbedPane.this.tabPane.repaint(SkinnedTabbedPane.this.getTabBounds(SkinnedTabbedPane.this.tabPane, tabIndex));
/*     */           }
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {}
/*     */ 
/*     */   
/*     */   protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
/* 200 */     int width = this.tabPane.getWidth();
/* 201 */     int height = this.tabPane.getHeight();
/* 202 */     Insets insets = this.tabPane.getInsets();
/* 203 */     int x = insets.left;
/* 204 */     int y = insets.top;
/* 205 */     int w = width - insets.right - insets.left;
/* 206 */     int h = height - insets.top - insets.bottom;
/*     */     
/* 208 */     g.setColor(m_OuterHighlight);
/* 209 */     switch (tabPlacement) {
/*     */       
/*     */       case 2:
/* 212 */         x += calculateTabAreaWidth(tabPlacement, this.runCount, this.maxTabWidth);
/*     */         
/* 214 */         g.drawLine(x + 1, y, x + 1, y);
/*     */         return;
/*     */       case 4:
/* 217 */         w -= calculateTabAreaWidth(tabPlacement, this.runCount, this.maxTabWidth);
/* 218 */         g.drawLine(x + w - 4, y, x + w - 4, y);
/*     */         return;
/*     */       case 3:
/* 221 */         h -= calculateTabAreaHeight(tabPlacement, this.runCount, this.maxTabHeight);
/* 222 */         g.drawLine(x, y + h - 4, x, y + h - 4);
/*     */         return;
/*     */     } 
/*     */     
/* 226 */     y += calculateTabAreaHeight(tabPlacement, this.runCount, this.maxTabHeight);
/*     */     
/* 228 */     g.drawLine(x, y + 1, x, y + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintFocusIndicator(Graphics g, int arg1, Rectangle[] arg2, int arg3, Rectangle arg4, Rectangle arg5, boolean arg6) {
/* 235 */     if (this.m_DrawFocusRect) {
/* 236 */       super.paintFocusIndicator(g, arg1, arg2, arg3, arg4, arg5, arg6);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
/* 242 */     if (this.m_DrawBoldSelected)
/*     */     {
/* 244 */       if (isSelected && !font.isBold()) {
/*     */         
/* 246 */         font = new Font(font.getName(), font.getStyle() | 0x1, font.getSize());
/* 247 */         textRect.x -= (int)(textRect.width * 0.125D);
/*     */       }
/* 249 */       else if (!isSelected && font.isBold()) {
/*     */         
/* 251 */         font = new Font(font.getName(), font.getStyle() & 0xFFFFFFFE, font.getSize());
/* 252 */         double mult = JLbsConstants.DESKTOP_MODE ? 
/* 253 */           0.05D : 
/* 254 */           0.1D;
/* 255 */         textRect.x += (int)(textRect.width * mult);
/*     */       } 
/*     */     }
/* 258 */     if (title != null) {
/* 259 */       super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect, isSelected);
/*     */     }
/*     */   }
/*     */   
/*     */   protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
/* 264 */     String title = this.tabPane.getTitleAt(tabIndex);
/* 265 */     int result = (title != null) ? 
/* 266 */       super.calculateTabWidth(tabPlacement, tabIndex, metrics) : 
/* 267 */       0;
/* 268 */     result = Math.max(16, result);
/* 269 */     if (this.m_DrawBoldSelected)
/* 270 */       result = (int)(result * 1.2D); 
/* 271 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
/* 276 */     g.setColor(m_OuterHighlight);
/* 277 */     int index = this.m_IndexModel.getIndexForState(this.tabPane.isEnabledAt(tabIndex), (this.m_Rollover == tabIndex), isSelected);
/* 278 */     switch (tabPlacement) {
/*     */       
/*     */       case 2:
/* 281 */         getSkinLeft().draw(g, index, x, y, w, h);
/*     */         return;
/*     */       case 4:
/* 284 */         getSkinRight().draw(g, index, x - 2, y, w, h);
/*     */         return;
/*     */       case 3:
/* 287 */         if (isSelected)
/* 288 */           y--; 
/* 289 */         getSkinBottom().draw(g, index, x, y - 2, w - 1, h);
/*     */         return;
/*     */     } 
/*     */     
/* 293 */     if (JLbsConstants.DESKTOP_MODE) {
/*     */       
/* 295 */       drawTabHeader(g, index, x, y, w - 1, h);
/*     */     } else {
/*     */       
/* 298 */       if (isSelected)
/* 299 */         h++; 
/* 300 */       getSkinTop().draw(g, index, x, y, w - 1, h);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawTabHeader(Graphics g, int index, int x, int y, int w, int h) {
/* 307 */     Graphics2D g2d = (Graphics2D)g.create();
/* 308 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 309 */     g2d.setColor(getDesktopColor(index));
/* 310 */     g2d.fillRoundRect(x, y, w, h, 8, 8);
/* 311 */     g2d.fillRect(x, y + 8, w, h - 8);
/* 312 */     g2d.dispose();
/*     */   }
/*     */ 
/*     */   
/*     */   private Color getDesktopColor(int index) {
/* 317 */     Color color = new Color(102, 156, 175);
/* 318 */     switch (index) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 325 */         color = new Color(16, 64, 91);
/*     */         break;
/*     */     } 
/* 328 */     return color;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(Graphics g, JComponent c) {
/* 333 */     paint(g, c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 338 */     int width = this.tabPane.getWidth();
/* 339 */     int height = this.tabPane.getHeight();
/* 340 */     Insets insets = this.tabPane.getInsets();
/* 341 */     int x = insets.left;
/* 342 */     int y = insets.top;
/* 343 */     int w = width - insets.right - insets.left;
/* 344 */     int h = height - insets.top - insets.bottom;
/* 345 */     if (c.isOpaque()) {
/*     */       
/* 347 */       g.setColor(m_ComponentBackground);
/* 348 */       g.fillRect(0, 0, c.getWidth(), c.getHeight());
/*     */     } 
/* 350 */     int tabPlacement = this.tabPane.getTabPlacement();
/* 351 */     switch (tabPlacement) {
/*     */       
/*     */       case 2:
/* 354 */         x += calculateTabAreaWidth(tabPlacement, this.runCount, this.maxTabWidth);
/* 355 */         w -= x - insets.left;
/*     */         break;
/*     */       case 4:
/* 358 */         w -= calculateTabAreaWidth(tabPlacement, this.runCount, this.maxTabWidth);
/*     */         break;
/*     */       case 3:
/* 361 */         h -= calculateTabAreaHeight(tabPlacement, this.runCount, this.maxTabHeight);
/*     */         break;
/*     */       
/*     */       default:
/* 365 */         y += calculateTabAreaHeight(tabPlacement, this.runCount, this.maxTabHeight);
/* 366 */         h -= y - insets.top; break;
/*     */     } 
/* 368 */     g.setColor(m_TabBackground);
/* 369 */     g.fillRect(x, y, w, h);
/* 370 */     g.setColor(m_OuterHighlight);
/* 371 */     g.drawLine(x, y, x, y + h - 3);
/* 372 */     g.drawLine(x, y, x + w - 3, y);
/* 373 */     getSkinBorderRight().draw(g, 0, x + w - 3, y, 3, h);
/* 374 */     getSkinBorderBottom().draw(g, 0, x, y + h - 3, w, 3);
/* 375 */     assureRectsCreated(((JTabbedPane)c).getTabCount());
/* 376 */     super.paint(g, c);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Rectangle getTabBounds(int tabIndex, Rectangle dest) {
/* 381 */     if (this.rects != null && tabIndex < this.rects.length)
/* 382 */       return super.getTabBounds(tabIndex, dest); 
/* 383 */     return new Rectangle(0, 0, 0, 0);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int calculateTabAreaWidth(int tabPlacement, int vertRunCount, int maxTabHeight) {
/* 404 */     int width = super.calculateTabAreaWidth(tabPlacement, vertRunCount, maxTabHeight);
/* 405 */     if (this.tabPane.getTabLayoutPolicy() == 1)
/* 406 */       width += 18; 
/* 407 */     return width;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getTabLabelShiftX(int tabPlacement, int tabIndex, boolean isSelected) {
/* 413 */     int nudge = 0;
/* 414 */     switch (tabPlacement)
/*     */     
/*     */     { case 2:
/* 417 */         nudge = isSelected ? 
/* 418 */           -1 : 
/* 419 */           1;
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
/* 431 */         return nudge;case 4: nudge = isSelected ? 1 : -1; return nudge; }  nudge = 0; return nudge;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getTabLabelShiftY(int tabPlacement, int tabIndex, boolean isSelected) {
/* 436 */     Rectangle tabRect = this.rects[tabIndex];
/* 437 */     int nudge = 0;
/* 438 */     switch (tabPlacement)
/*     */     
/*     */     { case 3:
/* 441 */         nudge = isSelected ? 
/* 442 */           1 : 
/* 443 */           -1;
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
/* 455 */         return nudge;case 2: case 4: nudge = tabRect.height % 2; return nudge; }  nudge = isSelected ? 0 : 1; return nudge;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
/* 462 */     return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getTabRunOverlay(int tabPlacement) {
/* 468 */     if (tabPlacement == 2 || tabPlacement == 4) {
/*     */       
/* 470 */       int maxTabHeight = calculateMaxTabHeight(tabPlacement);
/* 471 */       return maxTabHeight / 2;
/*     */     } 
/* 473 */     return JLbsConstants.DESKTOP_MODE ? 
/* 474 */       -4 : 
/* 475 */       0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected LayoutManager createLayoutManager() {
/* 480 */     if (this.tabPane.getTabLayoutPolicy() == 1)
/*     */     {
/* 482 */       return super.createLayoutManager();
/*     */     }
/* 484 */     return new MetalTabbedPaneUI.TabbedPaneLayout(this)
/*     */       {
/*     */         
/*     */         protected void padSelectedTab(int tabPlacement, int selectedIndex)
/*     */         {
/* 489 */           for (int i = 0; i < SkinnedTabbedPane.this.rects.length; i++) {
/*     */             
/* 491 */             Rectangle selRect = SkinnedTabbedPane.this.rects[i];
/* 492 */             Insets padInsets = SkinnedTabbedPane.this.getSelectedTabPadInsets(tabPlacement);
/* 493 */             selRect.x += (i + 1) * padInsets.left;
/* 494 */             selRect.y -= padInsets.top;
/*     */           } 
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public abstract SkinImage getSkinTop();
/*     */   
/*     */   public abstract SkinImage getSkinLeft();
/*     */   
/*     */   public abstract SkinImage getSkinRight();
/*     */   
/*     */   public abstract SkinImage getSkinBottom();
/*     */   
/*     */   public abstract SkinImage getSkinBorderBottom();
/*     */   
/*     */   public abstract SkinImage getSkinBorderRight();
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedTabbedPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */