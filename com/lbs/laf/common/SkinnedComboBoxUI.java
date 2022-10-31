/*     */ package com.lbs.laf.common;
/*     */ 
/*     */ import com.lbs.controls.JLbsComboBox;
/*     */ import com.lbs.controls.JLbsControlHelper;
/*     */ import com.lbs.controls.maskededit.JLbsMaskedEdit;
/*     */ import com.lbs.laf.mac.ComboBoxUI;
/*     */ import com.lbs.laf.mac.DefaultTheme;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.HashMap;
/*     */ import javax.swing.ComboBoxEditor;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.ListCellRenderer;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.plaf.basic.BasicComboBoxUI;
/*     */ import javax.swing.plaf.basic.BasicComboPopup;
/*     */ import javax.swing.plaf.basic.ComboPopup;
/*     */ import javax.swing.text.JTextComponent;
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
/*     */ public class SkinnedComboBoxUI
/*     */   extends BasicComboBoxUI
/*     */ {
/*     */   public static final int ARROW_BUTTON_WIDTH = 21;
/*     */   
/*     */   public class ComboPropertyChangeListener
/*     */     extends BasicComboBoxUI.PropertyChangeHandler {
/*     */     public void propertyChange(final PropertyChangeEvent e)
/*     */     {
/* 183 */       super.propertyChange(e);
/* 184 */       String propertyName = e.getPropertyName();
/* 185 */       if (propertyName.equals("editable")) {
/*     */         
/* 187 */         SkinnedComboBoxButton button = (SkinnedComboBoxButton)SkinnedComboBoxUI.this.arrowButton;
/* 188 */         button.setIconOnly(SkinnedComboBoxUI.this.comboBox.isEditable());
/* 189 */         SkinnedComboBoxUI.this.comboBox.repaint();
/*     */       }
/* 191 */       else if (propertyName.equals("background")) {
/*     */         
/* 193 */         Color color = (Color)e.getNewValue();
/* 194 */         SkinnedComboBoxUI.this.listBox.setBackground(color);
/*     */       
/*     */       }
/* 197 */       else if (propertyName.equals("foreground")) {
/*     */         
/* 199 */         Color color = (Color)e.getNewValue();
/* 200 */         SkinnedComboBoxUI.this.listBox.setForeground(color);
/*     */       }
/* 202 */       else if (propertyName.equals("componentOrientation")) {
/*     */         
/* 204 */         SwingUtilities.invokeLater(new Runnable()
/*     */             {
/*     */               public void run()
/*     */               {
/* 208 */                 if ((SkinnedComboBoxUI.ComboPropertyChangeListener.access$0(SkinnedComboBoxUI.ComboPropertyChangeListener.this)).comboBox == null) {
/*     */                   return;
/*     */                 }
/* 211 */                 ComponentOrientation newOrientation = (ComponentOrientation)e.getNewValue();
/* 212 */                 if (newOrientation.equals(ComponentOrientation.UNKNOWN)) {
/*     */                   return;
/*     */                 }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 220 */                 ListCellRenderer cellRenderer = (SkinnedComboBoxUI.ComboPropertyChangeListener.access$0(SkinnedComboBoxUI.ComboPropertyChangeListener.this)).comboBox.getRenderer();
/* 221 */                 ComboBoxEditor comboEditor = (SkinnedComboBoxUI.ComboPropertyChangeListener.access$0(SkinnedComboBoxUI.ComboPropertyChangeListener.this)).comboBox.getEditor();
/*     */                 
/* 223 */                 boolean compOrientationApplied = false;
/* 224 */                 if ((SkinnedComboBoxUI.ComboPropertyChangeListener.access$0(SkinnedComboBoxUI.ComboPropertyChangeListener.this)).popup instanceof Component) {
/*     */                   
/* 226 */                   Component cPopup = (Component)(SkinnedComboBoxUI.ComboPropertyChangeListener.access$0(SkinnedComboBoxUI.ComboPropertyChangeListener.this)).popup;
/* 227 */                   if (cPopup.getComponentOrientation() == null || 
/* 228 */                     !cPopup.getComponentOrientation().equals(newOrientation)) {
/*     */                     
/* 230 */                     cPopup.applyComponentOrientation(newOrientation);
/* 231 */                     cPopup.doLayout();
/* 232 */                     compOrientationApplied = true;
/*     */                   } 
/*     */                 } 
/*     */                 
/* 236 */                 if (cellRenderer instanceof Component)
/*     */                 {
/* 238 */                   if (((Component)cellRenderer).getComponentOrientation() == null || 
/* 239 */                     !((Component)cellRenderer).getComponentOrientation().equals(newOrientation)) {
/*     */                     
/* 241 */                     ((Component)cellRenderer).applyComponentOrientation(newOrientation);
/* 242 */                     compOrientationApplied = true;
/*     */                   } 
/*     */                 }
/*     */                 
/* 246 */                 if (comboEditor != null && comboEditor.getEditorComponent() != null)
/*     */                 {
/* 248 */                   if (comboEditor.getEditorComponent().getComponentOrientation() == null || 
/* 249 */                     !comboEditor.getEditorComponent().getComponentOrientation().equals(newOrientation)) {
/*     */                     
/* 251 */                     comboEditor.getEditorComponent().applyComponentOrientation(newOrientation);
/* 252 */                     compOrientationApplied = true;
/*     */                   } 
/*     */                 }
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 259 */                 if ((SkinnedComboBoxUI.ComboPropertyChangeListener.access$0(SkinnedComboBoxUI.ComboPropertyChangeListener.this)).comboBox != null && compOrientationApplied)
/* 260 */                   (SkinnedComboBoxUI.ComboPropertyChangeListener.access$0(SkinnedComboBoxUI.ComboPropertyChangeListener.this)).comboBox.repaint(); 
/*     */               }
/*     */             });
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected static HashMap m_ArrowImages = new HashMap<>();
/*     */   protected static SkinImage m_SkinCombo = DefaultTheme.getSkinImage(ComboBoxUI.class, "combobox.png", 4, 0, 0, 21, 0);
/*     */   protected Border m_EditorBorder;
/*     */   protected int m_ButtonWidth;
/*     */   protected Color m_SaveColor;
/*     */   
/* 273 */   protected LayoutManager createLayoutManager() { return new SkinnedComboBoxLayoutManager(); }
/*     */   SkinnableComboBoxEditor m_Editor;
/*     */   private static Image createArrowImage(int index, boolean leftToRight) { int width = 21; int height = m_SkinCombo.getVsize(); BufferedImage image = new BufferedImage(width, height, 2); Graphics2D graphics = (Graphics2D)image.getGraphics().create(); graphics.setColor(new Color(0, 0, 0, 0)); graphics.setComposite(AlphaComposite.Src); graphics.fillRect(0, 0, width, height); if (!leftToRight) {
/*     */       AffineTransform t = new AffineTransform(); t.setToTranslation(width, 0.0D); t.concatenate(AffineTransform.getScaleInstance(-1.0D, 1.0D)); graphics.setTransform(t);
/*     */     }  m_SkinCombo.draw(graphics, index, 0, 0, width, height); graphics.dispose(); return image; }
/*     */   public static synchronized Image getArrowImage(int index, boolean leftToRight) { String key = String.valueOf(index) + ":" + leftToRight; Image image = (Image)m_ArrowImages.get(key); if (image == null) {
/*     */       image = createArrowImage(index, leftToRight); if (image != null)
/*     */         m_ArrowImages.put(key, image); 
/*     */     }  return image; }
/*     */   public SkinnedComboBoxUI(Border editorBorder, int buttonWidth) { this.m_EditorBorder = editorBorder; this.m_ButtonWidth = buttonWidth; } public void paint(Graphics g, JComponent c) {} protected ComboBoxEditor createEditor() { SkinnableComboBoxEditor comboEditor = new SkinnableComboBoxEditor(this.m_EditorBorder); this.m_Editor = comboEditor; return comboEditor; } protected ComboPopup createPopup() { final ComboPopup sPopup = (this.comboBox instanceof JLbsComboBox) ? JLbsComboBox.createComboBoxPopup((JLbsComboBox)this.comboBox) : new MetalComboPopup(this.comboBox); final ComponentOrientation currOrientation = this.comboBox.getComponentOrientation(); SwingUtilities.invokeLater(new Runnable() {
/*     */           public void run() { if (SkinnedComboBoxUI.this.comboBox == null)
/*     */               return;  if (sPopup instanceof Component) {
/*     */               Component cPopup = (Component)sPopup; cPopup.applyComponentOrientation(currOrientation);
/*     */               cPopup.doLayout();
/*     */             } 
/*     */             ListCellRenderer cellRenderer = SkinnedComboBoxUI.this.comboBox.getRenderer();
/*     */             if (cellRenderer instanceof Component)
/*     */               ((Component)cellRenderer).applyComponentOrientation(currOrientation); 
/*     */             ComboBoxEditor comboEditor = SkinnedComboBoxUI.this.comboBox.getEditor();
/*     */             if (comboEditor != null && comboEditor.getEditorComponent() != null)
/*     */               comboEditor.getEditorComponent().applyComponentOrientation(currOrientation); 
/*     */             SkinnedComboBoxUI.this.comboBox.repaint(); }
/*     */         });
/*     */     return sPopup; } protected JButton createArrowButton() { return null; } public PropertyChangeListener createPropertyChangeListener() { return new ComboPropertyChangeListener(); } protected void editablePropertyChanged(PropertyChangeEvent e) {} public class SkinnedComboBoxLayoutManager extends BasicComboBoxUI.ComboBoxLayoutManager
/*     */   {
/* 298 */     public void layoutContainer2(Container parent) { if (parent instanceof JComboBox) {
/*     */         
/* 300 */         JComboBox cb = (JComboBox)parent;
/* 301 */         int width = cb.getWidth();
/* 302 */         int height = cb.getHeight();
/*     */ 
/*     */ 
/*     */         
/* 306 */         if (SkinnedComboBoxUI.this.comboBox.isEditable()) {
/*     */           
/* 308 */           if (SkinnedComboBoxUI.this.arrowButton != null)
/*     */           {
/* 310 */             SkinnedComboBoxUI.this.arrowButton.setBounds(0, 0, SkinnedComboBoxUI.this.m_ButtonWidth, height);
/*     */           }
/* 312 */           if (SkinnedComboBoxUI.this.editor != null)
/*     */           {
/* 314 */             Rectangle cvb = SkinnedComboBoxUI.this.rectangleForCurrentValue();
/* 315 */             SkinnedComboBoxUI.this.editor.setBounds(cvb);
/*     */ 
/*     */           
/*     */           }
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 325 */           SkinnedComboBoxUI.this.arrowButton.setBounds(0, 0, width, height);
/*     */         } 
/*     */       }  }
/*     */ 
/*     */ 
/*     */     
/*     */     public void layoutContainer(Container parent) {
/* 332 */       JComboBox cb = null;
/* 333 */       if (parent instanceof JComboBox) {
/*     */         
/* 335 */         cb = (JComboBox)parent;
/*     */         
/* 337 */         int width = cb.getWidth();
/* 338 */         int height = cb.getHeight();
/*     */ 
/*     */ 
/*     */         
/* 342 */         int buttonHeight = height;
/*     */         
/* 344 */         if (SkinnedComboBoxUI.this.arrowButton != null)
/*     */         {
/*     */ 
/*     */ 
/*     */           
/* 349 */           if (SkinnedComboBoxUI.this.comboBox.isEditable())
/* 350 */           { if (cb.getComponentOrientation().isLeftToRight()) {
/* 351 */               SkinnedComboBoxUI.this.arrowButton.setBounds(width - SkinnedComboBoxUI.this.m_ButtonWidth + (JLbsConstants.DESKTOP_MODE ? 
/* 352 */                   2 : 
/* 353 */                   0), 0, SkinnedComboBoxUI.this.m_ButtonWidth, height);
/*     */             } else {
/* 355 */               SkinnedComboBoxUI.this.arrowButton.setBounds(0, 0, SkinnedComboBoxUI.this.m_ButtonWidth, height);
/*     */             }  }
/* 357 */           else { SkinnedComboBoxUI.this.arrowButton.setBounds(0, 0, width, buttonHeight); }
/*     */         
/*     */         }
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
/* 371 */         if (SkinnedComboBoxUI.this.editor != null) {
/*     */           
/* 373 */           Rectangle r = SkinnedComboBoxUI.this.rectangleForCurrentValue();
/* 374 */           SkinnedComboBoxUI.this.editor.setBounds(r);
/*     */         } 
/*     */       } 
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
/*     */   protected void removeListeners() {
/* 415 */     if (this.propertyChangeListener != null) {
/* 416 */       this.comboBox.removePropertyChangeListener(this.propertyChangeListener);
/*     */     }
/*     */   }
/*     */   
/*     */   public void configureEditor() {
/* 421 */     super.configureEditor();
/* 422 */     if (this.editor instanceof JTextComponent) {
/* 423 */       ((JTextComponent)this.editor).setBorder((Border)null);
/*     */     }
/* 425 */     JTextComponent textComp = null;
/*     */     
/* 427 */     if (this.m_Editor != null) {
/* 428 */       textComp = (JTextComponent)this.m_Editor.getEditorComponent();
/*     */     }
/* 430 */     if (textComp != null) {
/*     */       
/* 432 */       textComp.setBorder((Border)null);
/* 433 */       this.m_SaveColor = this.comboBox.getBackground();
/* 434 */       textComp.addFocusListener(new FocusListener()
/*     */           {
/*     */             public void focusGained(FocusEvent e)
/*     */             {
/* 438 */               JTextComponent textComp = (JTextComponent)e.getSource();
/* 439 */               Color selColor = JLbsMaskedEdit.getSelectedBkColor();
/* 440 */               textComp.setBackground(selColor);
/* 441 */               SkinnedComboBoxUI.this.comboBox.setBackground(selColor);
/* 442 */               textComp.invalidate();
/* 443 */               SkinnedComboBoxUI.this.comboBox.invalidate();
/*     */             }
/*     */ 
/*     */             
/*     */             public void focusLost(FocusEvent e) {
/* 448 */               JTextComponent textComp = (JTextComponent)e.getSource();
/* 449 */               textComp.setBackground(SkinnedComboBoxUI.this.m_SaveColor);
/* 450 */               SkinnedComboBoxUI.this.comboBox.setBackground(SkinnedComboBoxUI.this.m_SaveColor);
/* 451 */               textComp.invalidate();
/* 452 */               SkinnedComboBoxUI.this.comboBox.invalidate();
/*     */             }
/*     */           });
/*     */     } 
/* 456 */     JLbsControlHelper.updateUIBorder(this.comboBox, "FormattedTextField.border");
/*     */   }
/*     */ 
/*     */   
/*     */   public void unconfigureEditor() {
/* 461 */     super.unconfigureEditor();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize(JComponent c) {
/* 466 */     if (!this.isMinimumSizeDirty)
/*     */     {
/* 468 */       return new Dimension(this.cachedMinimumSize);
/*     */     }
/*     */     
/* 471 */     Dimension size = null;
/*     */     
/* 473 */     if (!this.comboBox.isEditable() && this.arrowButton != null && this.arrowButton instanceof SkinnedComboBoxButton) {
/*     */ 
/*     */       
/* 476 */       SkinnedComboBoxButton button = (SkinnedComboBoxButton)this.arrowButton;
/* 477 */       Insets buttonInsets = new Insets(0, 0, 0, 0);
/* 478 */       Insets insets = this.comboBox.getInsets();
/*     */       
/* 480 */       size = getDisplaySize();
/* 481 */       size.width += this.m_ButtonWidth + insets.left + insets.right;
/* 482 */       size.width += buttonInsets.left + buttonInsets.right;
/* 483 */       size.width += buttonInsets.right + button.getComboIcon().getIconWidth();
/* 484 */       size.height += insets.top + insets.bottom;
/* 485 */       size.height += buttonInsets.top + buttonInsets.bottom;
/* 486 */       size.height = Math.max(21, size.height);
/*     */     }
/* 488 */     else if (this.comboBox.isEditable() && this.arrowButton != null && this.editor != null) {
/*     */       
/* 490 */       size = super.getMinimumSize(c);
/* 491 */       Insets margin = this.arrowButton.getMargin();
/* 492 */       Insets insets = this.comboBox.getInsets();
/* 493 */       this.editor instanceof JComponent;
/*     */ 
/*     */ 
/*     */       
/* 497 */       size.height += margin.top + margin.bottom;
/* 498 */       size.height += insets.top + insets.bottom;
/*     */     }
/*     */     else {
/*     */       
/* 502 */       size = super.getMinimumSize(c);
/*     */     } 
/*     */     
/* 505 */     this.cachedMinimumSize.setSize(size.width, size.height);
/* 506 */     this.isMinimumSizeDirty = false;
/*     */     
/* 508 */     return new Dimension(this.cachedMinimumSize);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getButtonSize() {
/* 513 */     return this.m_ButtonWidth;
/*     */   }
/*     */   
/*     */   public class MetalComboPopup
/*     */     extends BasicComboPopup
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public MetalComboPopup(JComboBox<Object> combo) {
/* 522 */       super(combo);
/*     */     }
/*     */ 
/*     */     
/*     */     public void delegateFocus(MouseEvent e) {
/* 527 */       super.delegateFocus(e);
/*     */     }
/*     */ 
/*     */     
/*     */     protected Rectangle computePopupBounds(int px, int py, int pw, int ph) {
/* 532 */       return super.computePopupBounds(px, py, pw, ph);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedComboBoxUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */