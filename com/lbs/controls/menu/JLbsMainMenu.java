/*     */ package com.lbs.controls.menu;
/*     */ 
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.controls.JLbsEventRecorderHelper;
/*     */ import com.lbs.controls.JLbsSwingUtilities;
/*     */ import com.lbs.recording.JLbsDataPoolItem;
/*     */ import com.lbs.recording.JLbsRecordItem;
/*     */ import com.lbs.recording.interfaces.ILbsMenuRecordingEvents;
/*     */ import com.lbs.util.ILbsCaptionTag;
/*     */ import com.lbs.util.UIHelperUtil;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.KeyStroke;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsMainMenu
/*     */   extends JMenu
/*     */   implements ActionListener, ILbsCaptionTag, ILbsComponentBase, ILbsMenuRecordingEvents
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int ITEM_PLAIN = 0;
/*     */   public static final int ITEM_CHECK = 1;
/*     */   public static final int ITEM_RADIO = 2;
/*     */   private ILbsMainMenuActionListener m_MenuActionListener;
/*     */   private int m_Tag;
/*     */   
/*     */   public JLbsMainMenu() {}
/*     */   
/*     */   public JLbsMainMenu(String text) {
/*  53 */     UIHelperUtil.setCaption(this, text);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsMainMenu(String text, String[] textList) {
/*  59 */     UIHelperUtil.setCaption(this, text);
/*  60 */     if (textList != null && textList.length > 0)
/*     */     {
/*  62 */       for (int i = 0; i < textList.length; i++) {
/*  63 */         createMenuItem(0, textList[i]);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsMainMenu(String[] textList) {
/*  70 */     if (textList != null && textList.length > 0) {
/*     */       
/*  72 */       UIHelperUtil.setCaption(this, textList[0]);
/*  73 */       for (int i = 1; i < textList.length; i++) {
/*  74 */         createMenuItem(0, textList[i]);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public JMenuItem createMenuItem(int iType, String sText) {
/*  81 */     return createMenuItem(iType, sText, (ImageIcon)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public JMenuItem createMenuItem(int iType, String sText, ImageIcon image) {
/*  86 */     return createMenuItem(iType, sText, image, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public JMenuItem createMenuItem(int iType, String sText, ImageIcon image, int acceleratorKey) {
/*  91 */     return createMenuItem(iType, sText, image, acceleratorKey, (String)null);
/*     */   }
/*     */   
/*     */   public JMenuItem createMenuItem(int iType, String sText, ImageIcon image, int acceleratorKey, String sToolTip) {
/*     */     JMenuItem menuItem;
/*  96 */     if (sText != null && sText.compareTo("-") == 0) {
/*     */       
/*  98 */       addSeparator();
/*  99 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 103 */     switch (iType) {
/*     */       
/*     */       case 2:
/* 106 */         menuItem = new JRadioButtonMenuItemEx();
/*     */         break;
/*     */       case 1:
/* 109 */         menuItem = new JCheckBoxMenuItemEx();
/*     */         break;
/*     */       default:
/* 112 */         menuItem = new JMenuItemEx();
/*     */         break;
/*     */     } 
/*     */     
/* 116 */     UIHelperUtil.setCaption(menuItem, sText);
/*     */     
/* 118 */     if (image != null) {
/* 119 */       menuItem.setIcon(image);
/*     */     }
/* 121 */     if (acceleratorKey > 0) {
/* 122 */       menuItem.setMnemonic(acceleratorKey);
/*     */     }
/* 124 */     if (sToolTip != null) {
/* 125 */       menuItem.setToolTipText(sToolTip);
/*     */     }
/* 127 */     menuItem.addActionListener(this);
/* 128 */     add(menuItem);
/* 129 */     return menuItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemKeyStroke(int index, KeyStroke key) {
/* 134 */     if (index < getMenuComponentCount() && index >= 0) {
/*     */       
/*     */       try {
/* 137 */         Object comp = getMenuComponent(index);
/* 138 */         if (comp instanceof JMenuItem)
/*     */         {
/* 140 */           JMenuItem item = (JMenuItem)getMenuComponent(index);
/* 141 */           item.setAccelerator(key);
/*     */         }
/*     */       
/* 144 */       } catch (Exception exception) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItemIcon(int index, ImageIcon icon) {
/* 151 */     if (index < getMenuComponentCount() && index >= 0) {
/*     */       
/*     */       try {
/* 154 */         JMenuItem item = (JMenuItem)getMenuComponent(index);
/* 155 */         item.setIcon(icon);
/*     */       }
/* 157 */       catch (Exception exception) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItemEnabled(int index, boolean enabled) {
/* 164 */     if (index < getMenuComponentCount() && index >= 0) {
/*     */       
/*     */       try {
/* 167 */         JMenuItem item = (JMenuItem)getMenuComponent(index);
/* 168 */         item.setEnabled(enabled);
/*     */       }
/* 170 */       catch (Exception exception) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItemsEnabled(boolean enabled) {
/* 177 */     for (int i = 0; i < getMenuComponentCount(); i++) {
/*     */       
/*     */       try {
/* 180 */         JMenuItem item = (JMenuItem)getMenuComponent(i);
/* 181 */         item.setEnabled(enabled);
/*     */       }
/* 183 */       catch (Exception exception) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JMenuItem getItemByTag(int tag) {
/* 190 */     int count = getMenuComponentCount();
/* 191 */     for (int i = 0; i < count; i++) {
/*     */       
/* 193 */       Component c = getMenuComponent(i);
/* 194 */       if (c instanceof JMenuItem && c instanceof ILbsCaptionTag && ((ILbsCaptionTag)c).getTag() == tag)
/* 195 */         return (JMenuItem)c; 
/*     */     } 
/* 197 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public JMenuItem getItemByIndex(int index) {
/* 202 */     if (index < getMenuComponentCount() && index >= 0) {
/*     */       
/*     */       try {
/* 205 */         return (JMenuItem)getMenuComponent(index);
/*     */       }
/* 207 */       catch (Exception exception) {}
/*     */     }
/*     */     
/* 210 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 215 */     Object src = e.getSource();
/* 216 */     if (this.m_MenuActionListener != null && src instanceof JMenuItem) {
/*     */       
/* 218 */       this.m_MenuActionListener.menuActionPerformed(this, (JMenuItem)src, (src instanceof ILbsCaptionTag) ? (
/* 219 */           (ILbsCaptionTag)src).getTag() : 
/* 220 */           0);
/* 221 */       recordMenuActionPerformed(this, (JMenuItem)src, (src instanceof ILbsCaptionTag) ? (
/* 222 */           (ILbsCaptionTag)src).getTag() : 
/* 223 */           0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void doActionPerformed(ActionEvent e, int actionID) {
/* 229 */     final ActionEvent actionEvent = e;
/* 230 */     final int mActionID = actionID;
/* 231 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 235 */             JLbsMainMenu.this.actionPerformed(actionEvent);
/* 236 */             if (JLbsMainMenu.this.isShowing()) {
/* 237 */               JLbsComponentHelper.statusChanged(4, mActionID, null);
/*     */             } else {
/* 239 */               JLbsComponentHelper.statusChanged(1, mActionID, "Main Menu can not be shown");
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSubMenu(int tag, String[] items, ILbsMainMenuActionListener actionListener) {
/* 247 */     JMenuItem item = getItemByTag(tag);
/* 248 */     if (item != null && items != null && items.length > 0) {
/*     */       
/* 250 */       int index = getItemIndex(item);
/* 251 */       remove(item);
/* 252 */       JLbsMainMenu newMenu = new JLbsMainMenu(item.getText(), items);
/* 253 */       newMenu.setTag(tag);
/* 254 */       newMenu.setMenuActionListener(actionListener);
/* 255 */       insert(newMenu, index);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getItemIndex(JMenuItem item) {
/* 261 */     for (int i = getMenuComponentCount() - 1; i >= 0; i--) {
/* 262 */       if (getMenuComponent(i) == item)
/* 263 */         return i; 
/* 264 */     }  return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTag() {
/* 269 */     return this.m_Tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTag(int tag) {
/* 274 */     this.m_Tag = tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsMainMenuActionListener getMenuActionListener() {
/* 279 */     return this.m_MenuActionListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMenuActionListener(ILbsMainMenuActionListener listener) {
/* 284 */     this.m_MenuActionListener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemVisibleByIndex(int index, boolean visible) {
/* 289 */     if (index < getMenuComponentCount() && index >= 0) {
/*     */       
/* 291 */       Component c = getMenuComponent(index);
/* 292 */       if (c != null) {
/* 293 */         c.setVisible(visible);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getResourceIdentifier() {
/* 299 */     return JLbsComponentHelper.getResourceIdentifier(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 304 */     return JLbsComponentHelper.getUniqueIdentifier(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordMenuActionPerformed(JLbsMainMenu menu, JMenuItem item, int tag) {
/* 309 */     JLbsRecordItem recordItem = new JLbsRecordItem(new JLbsDataPoolItem(tag, menu.getTag(), 0, "", 
/* 310 */           "Unknown", null), "MAINMENU_ITEM_SELECTED");
/* 311 */     JLbsEventRecorderHelper.addRecordItem(recordItem);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordActionPerformed(ActionEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordKeyPressed(KeyEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordMouseClicked(MouseEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordRequestFocus() {}
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 332 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\menu\JLbsMainMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */