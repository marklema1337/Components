/*     */ package com.lbs.controls.toolbar;
/*     */ 
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.JLbsComboBox;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.controls.JLbsImageButton;
/*     */ import com.lbs.controls.JLbsMenuButton;
/*     */ import com.lbs.util.ILbsCaptionTag;
/*     */ import com.lbs.util.UIHelperUtil;
/*     */ import java.awt.Component;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JToggleButton;
/*     */ import javax.swing.JToolBar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsToolbar
/*     */   extends JToolBar
/*     */   implements ActionListener, ILbsComponentBase, ItemListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  40 */   private static final Insets ms_ToolbarMargin = new Insets(4, 4, 4, 4);
/*     */   
/*     */   private ILbsToolbarActionListener m_ActionListener;
/*     */   
/*     */   private ILbsToolbarStateListener m_StateListener;
/*     */   private ILbsToolbarItemListener m_ItemListener;
/*     */   
/*     */   public JLbsToolbar() {
/*  48 */     putClientProperty("JToolBar.isRollover", Boolean.TRUE);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsToolbar(String title) {
/*  54 */     setName(title);
/*     */   }
/*     */ 
/*     */   
/*     */   public JButton createToolButton(ImageIcon icon, String tooltip) {
/*  59 */     return createToolButton(icon, tooltip, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public JButton createToolButton(ImageIcon icon, String tooltip, int tag) {
/*  64 */     JLbsToolbarButton button = new JLbsToolbarButton();
/*  65 */     if (icon != null)
/*  66 */       button.setIcon(icon); 
/*  67 */     UIHelperUtil.setCaption(button, tooltip);
/*  68 */     if (tag > 0)
/*  69 */       button.setTag(tag); 
/*  70 */     button.setMargin(ms_ToolbarMargin);
/*  71 */     button.addActionListener(this);
/*  72 */     add(button);
/*  73 */     return button;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsImageButton createToolImgButton(ImageIcon icon, String tooltip) {
/*  78 */     return createToolImgButton(icon, tooltip, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsImageButton createToolImgButton(ImageIcon icon, String tooltip, int tag) {
/*  83 */     JLbsImageButton button = new JLbsImageButton();
/*  84 */     if (icon != null)
/*  85 */       button.setIcon(icon); 
/*  86 */     button.setToolTipText(tooltip);
/*  87 */     if (tag > 0)
/*  88 */       button.setTag(tag); 
/*  89 */     button.setMargin(ms_ToolbarMargin);
/*  90 */     button.addActionListener(this);
/*  91 */     add((Component)button);
/*  92 */     return button;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsMenuButton createToolMenuButton(ImageIcon icon, int tag, JLbsMenuButton button, String tooltip) {
/*  97 */     JLbsMenuButton menuButton = new JLbsMenuButton();
/*  98 */     UIHelperUtil.setCaption((JComponent)button, tooltip);
/*  99 */     if (button != null)
/* 100 */       menuButton = button; 
/* 101 */     if (icon != null)
/* 102 */       menuButton.setIcon(icon); 
/* 103 */     menuButton.putClientProperty("Tag", Integer.valueOf(tag));
/* 104 */     add((Component)menuButton);
/* 105 */     addSeparator();
/* 106 */     return menuButton;
/*     */   }
/*     */ 
/*     */   
/*     */   public Component getToolbarButtonByTag(int tag) {
/* 111 */     int count = getComponentCount();
/* 112 */     for (int i = 0; i < count; i++) {
/*     */       
/* 114 */       Component c = getComponent(i);
/* 115 */       if (c instanceof JLbsMenuButton || c instanceof JLbsComboBox) {
/*     */         
/* 117 */         JComponent component = (JComponent)c;
/* 118 */         if (component.getClientProperty("Tag") != null && ((Integer)component.getClientProperty("Tag")).intValue() == tag) {
/* 119 */           return component;
/*     */         }
/*     */       }
/* 122 */       else if (c instanceof JLbsToolbarButton || c instanceof JLbsToolbarToggleButton) {
/*     */         
/* 124 */         ILbsCaptionTag component = (ILbsCaptionTag)c;
/* 125 */         if (component.getTag() == tag)
/* 126 */           return (JComponent)component; 
/*     */       } 
/*     */     } 
/* 129 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public JToggleButton createToggleToolButton(ImageIcon icon, String tooltip, int tag) {
/* 134 */     JLbsToolbarToggleButton button = new JLbsToolbarToggleButton();
/* 135 */     if (icon != null)
/* 136 */       button.setIcon(icon); 
/* 137 */     UIHelperUtil.setCaption(button, tooltip);
/* 138 */     if (tag > 0)
/* 139 */       button.setTag(tag); 
/* 140 */     button.setMargin(ms_ToolbarMargin);
/* 141 */     button.addActionListener(this);
/* 142 */     add(button);
/* 143 */     return button;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsComboBox createToolCombobox(JLbsComboBox cbbox) {
/* 148 */     cbbox.addItemListener(this);
/* 149 */     add((Component)cbbox);
/* 150 */     return cbbox;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsToolbarActionListener getToolbarActionListener() {
/* 155 */     return this.m_ActionListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToolbarActionListener(ILbsToolbarActionListener listener) {
/* 160 */     this.m_ActionListener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsToolbarStateListener getButtonStateListener() {
/* 165 */     return this.m_StateListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setButtonStateListener(ILbsToolbarStateListener listener) {
/* 170 */     this.m_StateListener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsToolbarItemListener getItemStateListener() {
/* 175 */     return this.m_ItemListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemStateListener(ILbsToolbarItemListener listener) {
/* 180 */     this.m_ItemListener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 185 */     if (this.m_ActionListener != null) {
/*     */       
/* 187 */       Object src = e.getSource();
/* 188 */       int tag = (src instanceof ILbsCaptionTag) ? (
/* 189 */         (ILbsCaptionTag)src).getTag() : 
/* 190 */         0;
/* 191 */       this.m_ActionListener.actionPerformed(this, e, tag);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateButtonStates(Object ctx) {
/* 197 */     if (this.m_StateListener == null)
/*     */       return; 
/* 199 */     int count = getComponentCount();
/* 200 */     for (int i = 0; i < count; i++) {
/*     */       
/* 202 */       Component c = getComponent(i);
/* 203 */       if ((c instanceof JButton || c instanceof JToggleButton) && c instanceof ILbsCaptionTag) {
/* 204 */         c.setEnabled(this.m_StateListener.getButtonState(this, ((ILbsCaptionTag)c).getTag(), ctx));
/* 205 */       } else if (c instanceof JLbsMenuButton || c instanceof JLbsComboBox) {
/*     */         
/* 207 */         if (((JComponent)c).getClientProperty("Tag") != null)
/*     */         {
/* 209 */           c.setEnabled(this.m_StateListener.getButtonState(this, ((Integer)((JComponent)c).getClientProperty("Tag")).intValue(), ctx));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 219 */     return JLbsComponentHelper.getResourceIdentifier(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 224 */     return JLbsComponentHelper.getUniqueIdentifier(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 229 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void itemStateChanged(ItemEvent e) {
/* 235 */     if (this.m_ItemListener != null) {
/*     */       
/* 237 */       Object src = e.getSource();
/* 238 */       int tag = (src instanceof ILbsCaptionTag) ? (
/* 239 */         (ILbsCaptionTag)src).getTag() : 
/* 240 */         0;
/* 241 */       this.m_ItemListener.itemStateChanged(e, tag);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\toolbar\JLbsToolbar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */