/*     */ package com.lbs.laf.common;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.Insets;
/*     */ import java.awt.image.BufferedImage;
/*     */ import javax.swing.CellRendererPane;
/*     */ import javax.swing.DefaultButtonModel;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.ListCellRenderer;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
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
/*     */ public class SkinnedComboBoxButton
/*     */   extends JButton
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  41 */   protected static SkinnedButtonIndexModel m_IndexModel = new SkinnedButtonIndexModel();
/*     */   
/*     */   protected JComboBox m_ComboBox;
/*     */   
/*     */   protected JList m_ListBox;
/*     */   
/*     */   protected CellRendererPane m_RendererPane;
/*     */   
/*     */   protected Icon m_ComboIcon;
/*     */   protected boolean m_IconOnly = false;
/*     */   protected BufferedImage m_FocusImg;
/*     */   public SkinImage m_SkinCombo;
/*     */   public SkinImage m_SkinArrow;
/*     */   public SkinImage m_SkinButton;
/*     */   public ImageIcon m_SkinIcon;
/*     */   public int m_ButtonWidth;
/*     */   public int m_ButtonBorder;
/*     */   
/*     */   public boolean isFocusable() {
/*  60 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public final JComboBox getComboBox() {
/*  65 */     return this.m_ComboBox;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void setComboBox(JComboBox cb) {
/*  70 */     this.m_ComboBox = cb;
/*     */   }
/*     */ 
/*     */   
/*     */   public final Icon getComboIcon() {
/*  75 */     return this.m_ComboIcon;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void setComboIcon(Icon i) {
/*  80 */     this.m_ComboIcon = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isIconOnly() {
/*  85 */     return this.m_IconOnly;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void setIconOnly(boolean isIconOnly) {
/*  90 */     this.m_IconOnly = isIconOnly;
/*     */   }
/*     */ 
/*     */   
/*     */   public SkinnedComboBoxButton() {
/*  95 */     super("");
/*  96 */     DefaultButtonModel model = new DefaultButtonModel()
/*     */       {
/*     */         private static final long serialVersionUID = 1L;
/*     */ 
/*     */         
/*     */         public void setArmed(boolean armed) {
/* 102 */           super.setArmed(isPressed() ? true : 
/*     */               
/* 104 */               armed);
/*     */         }
/*     */       };
/* 107 */     setModel(model);
/*     */     
/* 109 */     setBackground(UIManager.getColor("ComboBox.background"));
/* 110 */     setForeground(UIManager.getColor("ComboBox.foreground"));
/*     */   }
/*     */ 
/*     */   
/*     */   public SkinnedComboBoxButton(JComboBox cb, Icon i, CellRendererPane pane, JList list) {
/* 115 */     this();
/* 116 */     this.m_ComboBox = cb;
/* 117 */     this.m_ComboIcon = i;
/* 118 */     this.m_RendererPane = pane;
/* 119 */     this.m_ListBox = list;
/* 120 */     setEnabled(this.m_ComboBox.isEnabled());
/*     */   }
/*     */ 
/*     */   
/*     */   public SkinnedComboBoxButton(JComboBox cb, Icon i, boolean onlyIcon, CellRendererPane pane, JList list) {
/* 125 */     this(cb, i, pane, list);
/* 126 */     this.m_IconOnly = onlyIcon;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawArrowIcon(Graphics g, int index, int x, int y, int width, int height, boolean leftToRight) {
/* 131 */     Image arrowImage = SkinnedComboBoxUI.getArrowImage(index, leftToRight);
/* 132 */     g.drawImage(arrowImage, x, y, width, height, null);
/*     */     
/* 134 */     int middle = (height - this.m_SkinArrow.getVsize()) / 2;
/* 135 */     this.m_SkinArrow.draw(g, index, x + 1, middle, this.m_SkinArrow.getHsize(), this.m_SkinArrow.getVsize());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintComponent(Graphics g) {
/* 144 */     ComponentOrientation orientation = this.m_ComboBox.getComponentOrientation();
/* 145 */     boolean leftToRight = orientation.isLeftToRight();
/*     */     
/* 147 */     int index = m_IndexModel.getIndexForState(this.model.isEnabled(), this.model.isRollover(), !((!this.model.isArmed() || !this.model.isPressed()) && 
/* 148 */         !this.model.isSelected()));
/*     */     
/* 150 */     int height = getHeight();
/* 151 */     int btnRight = getWidth() - 2;
/* 152 */     if (this.m_ComboBox.getBorder() == null) {
/* 153 */       height += this.m_ButtonBorder;
/*     */     }
/*     */     
/* 156 */     int iconOffset = leftToRight ? (
/* 157 */       btnRight - this.m_ButtonWidth + 1) : 
/* 158 */       1;
/* 159 */     if (this.m_IconOnly) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 164 */       this.m_SkinButton.draw(g, index, btnRight - this.m_ButtonWidth, 0, this.m_ButtonWidth, height);
/*     */       
/* 166 */       int middle = (getHeight() - this.m_SkinArrow.getVsize()) / 2;
/* 167 */       this.m_SkinArrow.draw(g, index, btnRight - this.m_SkinArrow.getHsize(), middle, this.m_SkinArrow.getHsize(), this.m_SkinArrow.getVsize());
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 173 */       this.m_SkinButton.draw(g, index, iconOffset, 0, this.m_ButtonWidth, height);
/* 174 */       int middle = (getHeight() - this.m_SkinArrow.getVsize()) / 2;
/* 175 */       this.m_SkinArrow.draw(g, index, iconOffset, middle, this.m_SkinArrow.getHsize(), this.m_SkinArrow.getVsize());
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */     Insets insets = new Insets(1, 4, 1, 1);
/*     */     
/* 187 */     int width = btnRight - insets.left + insets.right;
/*     */     
/* 189 */     height = getHeight() - insets.top + insets.bottom;
/*     */     
/* 191 */     if (height <= 0 || width <= 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 196 */     int left = insets.left;
/* 197 */     int top = insets.top;
/*     */ 
/*     */ 
/*     */     
/* 201 */     int iconWidth = this.m_ButtonWidth;
/*     */ 
/*     */ 
/*     */     
/* 205 */     Component c = null;
/* 206 */     boolean mustResetOpaque = false;
/* 207 */     boolean savedOpaque = false;
/* 208 */     if (this.m_ComboBox != null) {
/*     */       
/* 210 */       Color bkColor = this.m_ComboBox.getBackground();
/* 211 */       Color fgColor = this.m_ComboBox.getForeground();
/* 212 */       ListCellRenderer<Object> renderer = this.m_ComboBox.getRenderer();
/* 213 */       boolean renderPressed = getModel().isPressed();
/* 214 */       c = renderer.getListCellRendererComponent(this.m_ListBox, this.m_ComboBox.getSelectedItem(), -1, renderPressed, false);
/* 215 */       if (c == null) {
/*     */         
/* 217 */         JLabel lbl = new JLabel();
/* 218 */         c = lbl;
/*     */       } 
/* 220 */       c.setFont(this.m_RendererPane.getFont());
/*     */       
/* 222 */       if (!this.m_ComboBox.isEnabled()) {
/*     */         
/* 224 */         if (isOpaque())
/* 225 */           bkColor = UIManager.getColor("ComboBox.disabledBackground"); 
/* 226 */         fgColor = UIManager.getColor("ComboBox.disabledForeground");
/*     */       }
/* 228 */       else if (this.m_ComboBox.hasFocus() && !this.m_ComboBox.isPopupVisible()) {
/*     */         
/* 230 */         if (c instanceof JComponent) {
/*     */           
/* 232 */           mustResetOpaque = true;
/* 233 */           JComponent jc = (JComponent)c;
/* 234 */           savedOpaque = jc.isOpaque();
/* 235 */           jc.setOpaque(true);
/*     */         } 
/*     */       } 
/* 238 */       if (!mustResetOpaque && c instanceof JComponent) {
/*     */         
/* 240 */         mustResetOpaque = true;
/* 241 */         JComponent jc = (JComponent)c;
/* 242 */         savedOpaque = jc.isOpaque();
/* 243 */         jc.setOpaque(false);
/*     */       } 
/*     */       
/* 246 */       int cWidth = width - insets.right + iconWidth;
/*     */ 
/*     */       
/* 249 */       boolean shouldValidate = false;
/* 250 */       if (c instanceof javax.swing.JPanel)
/*     */       {
/* 252 */         shouldValidate = true;
/*     */       }
/*     */       
/* 255 */       if (!this.m_IconOnly) {
/*     */         
/* 257 */         g.setColor(bkColor);
/* 258 */         if (leftToRight) {
/* 259 */           g.fillRect(0, 0, this.m_ComboBox.getWidth() - this.m_ButtonWidth - 1, this.m_ComboBox.getHeight());
/*     */         } else {
/* 261 */           g.fillRect(this.m_ButtonWidth + 1, 0, this.m_ComboBox.getWidth() - this.m_ButtonWidth - 1, this.m_ComboBox.getHeight());
/*     */         } 
/* 263 */         g.setColor(fgColor);
/* 264 */         c.setForeground(this.m_ComboBox.getForeground());
/* 265 */         c.setBackground(this.m_ComboBox.getBackground());
/*     */         
/* 267 */         if (leftToRight) {
/* 268 */           this.m_RendererPane.paintComponent(g, c, this, left, top, cWidth, height, shouldValidate);
/*     */         } else {
/* 270 */           this.m_RendererPane.paintComponent(g, c, this, left + iconWidth, top, cWidth, height, shouldValidate);
/*     */         } 
/* 272 */         if (c instanceof JComponent) {
/*     */           
/* 274 */           Border border = this.m_ComboBox.getBorder();
/* 275 */           if (border != null && !(border instanceof javax.swing.border.EmptyBorder)) {
/*     */             
/* 277 */             border = UIManager.getBorder("FormattedTextField.border");
/* 278 */             border.paintBorder(c, g, 0, 0, this.m_ComboBox.getWidth(), this.m_ComboBox.getHeight());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 283 */     if (mustResetOpaque) {
/*     */       
/* 285 */       JComponent jc = (JComponent)c;
/* 286 */       jc.setOpaque(savedOpaque);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedComboBoxButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */