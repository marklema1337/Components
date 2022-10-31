/*     */ package com.lbs.laf.common;
/*     */ 
/*     */ import com.lbs.laf.mac.LookAndFeel;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JToggleButton;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.metal.MetalButtonUI;
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
/*     */ public abstract class SkinnedButtonUI
/*     */   extends MetalButtonUI
/*     */ {
/*     */   public static final boolean HINT_DO_NOT_PAINT_TOOLBARBUTTON_IF_NO_MOUSE_OVER = true;
/*  41 */   SkinnedButtonIndexModel m_ButtonIndexModel = new SkinnedButtonIndexModel(true);
/*     */   
/*  43 */   SkinnedButtonIndexModel m_ToolbarIndexModel = new SkinnedButtonIndexModel();
/*     */   
/*  45 */   public static BasicStroke ms_FocusStroke = new BasicStroke(1.0F, 0, 2, 1.0F, 
/*  46 */       new float[] { 1.0F, 1.0F }, 1.0F);
/*     */   
/*     */   protected boolean m_bDrawFocusRect = true;
/*  49 */   protected int m_iTextOffset = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
/*  57 */     Graphics2D g2d = (Graphics2D)g;
/*  58 */     if (this.m_bDrawFocusRect) {
/*     */       
/*  60 */       Rectangle focusRect = b.getBounds();
/*     */       
/*  62 */       g.setColor(Color.black);
/*  63 */       g2d.setStroke(ms_FocusStroke);
/*  64 */       g2d.drawLine(2, 2, 2 + focusRect.width - 5, 2);
/*  65 */       g2d.drawLine(2, 2 + focusRect.height - 5, 2 + focusRect.width - 5, 2 + focusRect.height - 5);
/*  66 */       g2d.drawLine(2, 2, 2, 2 + focusRect.height - 5);
/*  67 */       g2d.drawLine(2 + focusRect.width - 5, 2, 2 + focusRect.width - 5, 2 + focusRect.height - 5);
/*     */     }
/*     */     else {
/*     */       
/*  71 */       this.m_ToolbarIndexModel.setButton(b);
/*  72 */       int index = this.m_ToolbarIndexModel.getIndexForState();
/*     */       
/*  74 */       if (index == 0 && b.hasFocus() && !b.getModel().isSelected()) {
/*  75 */         paintInternal(g, b, true, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ComponentUI createUI(final JComponent c) {
/*  81 */     if (c instanceof JButton) {
/*     */       
/*  83 */       JButton b = (JButton)c;
/*  84 */       b.setRolloverEnabled(true);
/*     */     }
/*  86 */     else if (c instanceof JToggleButton) {
/*     */       
/*  88 */       JToggleButton b = (JToggleButton)c;
/*  89 */       b.setRolloverEnabled(true);
/*     */     } 
/*     */ 
/*     */     
/*  93 */     c.setOpaque(false);
/*  94 */     c.addPropertyChangeListener("opaque", new PropertyChangeListener()
/*     */         {
/*     */           public void propertyChange(PropertyChangeEvent evt)
/*     */           {
/*  98 */             c.setOpaque(false);
/*     */           }
/*     */         });
/* 101 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintButtonPressed(Graphics g, AbstractButton b) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 114 */     paintInternal(g, c, false, true);
/*     */   }
/*     */ 
/*     */   
/*     */   private void paintInternal(Graphics g, JComponent c, boolean bSelected, boolean bDrawFocus) {
/* 119 */     AbstractButton button = (AbstractButton)c;
/* 120 */     int index = 0;
/* 121 */     if (button.getClientProperty("JToolBar.isToolbarButton") == Boolean.TRUE) {
/*     */       
/* 123 */       this.m_ToolbarIndexModel.setButton(button);
/* 124 */       index = this.m_ToolbarIndexModel.getIndexForState();
/* 125 */       getSkinToolbar().draw(g, index, button.getWidth(), button.getHeight());
/* 126 */       button.setForeground(LookAndFeel.getToolbarTextColorForImageButton());
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 131 */       this.m_ButtonIndexModel.setButton(button);
/* 132 */       this.m_ButtonIndexModel.setCheckForDefaultButton(button instanceof JButton);
/* 133 */       index = this.m_ButtonIndexModel.getIndexForState();
/* 134 */       if (bSelected && index == 0)
/* 135 */         index = 4; 
/* 136 */       if (!bSelected && index == 8)
/* 137 */         index = 0; 
/* 138 */       if (button.getClientProperty("JButton.isLookupButton") == Boolean.TRUE || 
/* 139 */         button.getClientProperty("JButton.isMultilangButton") == Boolean.TRUE) {
/*     */         
/* 141 */         if (JLbsConstants.DESKTOP_MODE) {
/* 142 */           drawDesktopButton(g, index, button.getWidth(), button.getHeight(), button);
/*     */         } else {
/* 144 */           getLookupSkinButton().draw(g, index, button.getWidth(), button.getHeight());
/*     */         } 
/* 146 */       } else if (button.getClientProperty("JButton.isSaveButton") == Boolean.TRUE) {
/* 147 */         drawDesktopButton(g, index, button.getWidth(), button.getHeight(), button);
/* 148 */       } else if (button.getClientProperty("JButton.isCloseButton") == Boolean.TRUE) {
/* 149 */         drawDesktopButton(g, index, button.getWidth(), button.getHeight(), button);
/* 150 */       } else if (button.getClientProperty("JButton.isApplyButton") == Boolean.TRUE) {
/* 151 */         drawDesktopButton(g, index, button.getWidth(), button.getHeight(), button);
/*     */       
/*     */       }
/* 154 */       else if (JLbsConstants.DESKTOP_MODE) {
/* 155 */         drawDesktopButton(g, index, button.getWidth(), button.getHeight(), button);
/*     */       } else {
/* 157 */         getSkinButton().draw(g, index, button.getWidth(), button.getHeight());
/*     */       } 
/*     */     } 
/*     */     
/* 161 */     g.setFont(c.getFont());
/* 162 */     Insets i = c.getInsets();
/* 163 */     Rectangle viewRect = new Rectangle(i.left, i.top, button.getWidth() - i.right + i.left, 
/* 164 */         button.getHeight() - i.bottom + i.top - this.m_iTextOffset);
/* 165 */     Rectangle iconRect = new Rectangle(0, 0, 0, 0);
/* 166 */     Rectangle textRect = new Rectangle(0, 0, 0, 0);
/* 167 */     String text = SwingUtilities.layoutCompoundLabel(c, g.getFontMetrics(), button.getText(), button.getIcon(), 
/* 168 */         button.getVerticalAlignment(), button.getHorizontalAlignment(), button.getVerticalTextPosition(), 
/* 169 */         button.getHorizontalTextPosition(), viewRect, iconRect, textRect, (button.getText() == null) ? 
/* 170 */         0 : 
/* 171 */         button.getIconTextGap());
/* 172 */     if (button.getIcon() != null) {
/* 173 */       paintIcon(g, c, iconRect);
/*     */     }
/* 175 */     paintText(g, button, textRect, text);
/*     */     
/* 177 */     if (bDrawFocus && button.isFocusPainted() && button.hasFocus()) {
/* 178 */       paintFocus(g, button, viewRect, textRect, iconRect);
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawDesktopButton(Graphics g, int index, int sizeX, int sizeY, AbstractButton button) {
/* 183 */     Graphics2D g2d = (Graphics2D)g.create();
/* 184 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 185 */     g2d.setColor(getColor(button, index));
/* 186 */     g2d.fillRoundRect(0, 0, sizeX, sizeY, 8, 8);
/* 187 */     g2d.dispose();
/*     */   }
/*     */ 
/*     */   
/*     */   private Color getColor(AbstractButton button, int index) {
/* 192 */     Color color = new Color(28, 64, 91);
/* 193 */     switch (index) {
/*     */       
/*     */       case 0:
/* 196 */         if (button.getClientProperty("JButton.isSaveButton") == Boolean.TRUE) {
/* 197 */           color = new Color(28, 64, 91); break;
/* 198 */         }  if (button.getClientProperty("JButton.isCloseButton") == Boolean.TRUE) {
/* 199 */           color = new Color(104, 153, 173); break;
/* 200 */         }  if (button.getClientProperty("JButton.isApplyButton") == Boolean.TRUE) {
/* 201 */           color = new Color(43, 101, 126); break;
/* 202 */         }  if (button.getClientProperty("JButton.isLookupButton") == Boolean.TRUE) {
/* 203 */           color = new Color(204, 230, 233); break;
/*     */         } 
/* 205 */         color = new Color(44, 123, 145);
/*     */         break;
/*     */       case 1:
/* 208 */         if (button.getClientProperty("JButton.isSaveButton") == Boolean.TRUE) {
/* 209 */           color = new Color(28, 64, 91); break;
/* 210 */         }  if (button.getClientProperty("JButton.isCloseButton") == Boolean.TRUE) {
/* 211 */           color = new Color(104, 153, 173); break;
/* 212 */         }  if (button.getClientProperty("JButton.isApplyButton") == Boolean.TRUE) {
/* 213 */           color = new Color(43, 101, 126); break;
/* 214 */         }  if (button.getClientProperty("JButton.isLookupButton") == Boolean.TRUE) {
/* 215 */           color = new Color(204, 230, 233); break;
/*     */         } 
/* 217 */         color = new Color(44, 123, 145);
/*     */         break;
/*     */       case 2:
/* 220 */         color = new Color(110, 107, 107);
/*     */         break;
/*     */       case 3:
/* 223 */         color = new Color(116, 116, 116);
/*     */         break;
/*     */       case 4:
/* 226 */         if (button.getClientProperty("JButton.isSaveButton") == Boolean.TRUE) {
/* 227 */           color = new Color(8, 44, 71); break;
/* 228 */         }  if (button.getClientProperty("JButton.isCloseButton") == Boolean.TRUE) {
/* 229 */           color = new Color(84, 133, 153); break;
/* 230 */         }  if (button.getClientProperty("JButton.isApplyButton") == Boolean.TRUE) {
/* 231 */           color = new Color(23, 81, 106); break;
/* 232 */         }  if (button.getClientProperty("JButton.isLookupButton") == Boolean.TRUE) {
/* 233 */           color = new Color(184, 210, 213); break;
/* 234 */         }  if (button instanceof JToggleButton) {
/* 235 */           color = new Color(90, 87, 87); break;
/*     */         } 
/* 237 */         color = new Color(24, 103, 125);
/*     */         break;
/*     */       case 5:
/* 240 */         if (button.getClientProperty("JButton.isSaveButton") == Boolean.TRUE) {
/* 241 */           color = new Color(28, 64, 91); break;
/* 242 */         }  if (button.getClientProperty("JButton.isCloseButton") == Boolean.TRUE) {
/* 243 */           color = new Color(104, 153, 173); break;
/* 244 */         }  if (button.getClientProperty("JButton.isApplyButton") == Boolean.TRUE) {
/* 245 */           color = new Color(43, 101, 126); break;
/* 246 */         }  if (button.getClientProperty("JButton.isLookupButton") == Boolean.TRUE) {
/* 247 */           color = new Color(204, 230, 233); break;
/* 248 */         }  if (button instanceof JToggleButton) {
/* 249 */           color = new Color(110, 107, 107); break;
/*     */         } 
/* 251 */         color = new Color(44, 123, 145);
/*     */         break;
/*     */       case 6:
/* 254 */         color = new Color(110, 107, 107);
/*     */         break;
/*     */       case 7:
/* 257 */         color = new Color(164, 162, 162);
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 262 */     return color;
/*     */   }
/*     */   
/*     */   public abstract SkinImage getLookupSkinButton();
/*     */   
/*     */   public abstract SkinImage getSkinToolbar();
/*     */   
/*     */   public abstract SkinImage getSkinButton();
/*     */   
/*     */   public abstract SkinImage getSkinSaveButton();
/*     */   
/*     */   public abstract SkinImage getSkinCloseButton();
/*     */   
/*     */   public abstract SkinImage getSkinApplyButton();
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedButtonUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */