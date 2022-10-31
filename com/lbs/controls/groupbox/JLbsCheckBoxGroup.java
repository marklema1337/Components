/*     */ package com.lbs.controls.groupbox;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsCheckBox;
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.JLbsCheckBox;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.controls.JLbsEventRecorderHelper;
/*     */ import com.lbs.controls.JLbsLabel;
/*     */ import com.lbs.controls.JLbsPanel;
/*     */ import com.lbs.controls.JLbsSwingUtilities;
/*     */ import com.lbs.controls.menu.JLbsPopupMenu;
/*     */ import com.lbs.controls.menu.JLbsPopupMenuItem;
/*     */ import com.lbs.recording.interfaces.ILbsSelectionRecordingEvents;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsFrame;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import com.lbs.util.JLbsStringListItem;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.UIHelperUtil;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.JComponent;
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
/*     */ public class JLbsCheckBoxGroup
/*     */   extends JLbsMultiColGroupBox
/*     */   implements ILbsSelectionRecordingEvents, ILbsInternalCheckBoxGroup
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final int SELECT_ALL = 1;
/*     */   private static final int DESELECT_ALL = 2;
/*     */   private static final int TOGGLE_SELECTION = 3;
/*  55 */   private static final JLbsStringList DEFAULT_LIST = new JLbsStringList("Select All~1|Deselect All~2|Toggle Selection~3");
/*     */ 
/*     */   
/*  58 */   private static Image ms_CheckAllIcon = JLbsGroupBox.getResourceBitmap(JLbsConstants.DESKTOP_MODE ? 
/*  59 */       "CheckAlldesktop.png" : 
/*  60 */       "CheckAll.png");
/*  61 */   private JLbsStringList m_PopupList = DEFAULT_LIST;
/*     */   
/*     */   private JLbsPopupMenu m_PopupMenu;
/*     */ 
/*     */   
/*     */   public JLbsCheckBoxGroup() {
/*  67 */     addFocusListener(new FocusListener()
/*     */         {
/*     */           
/*     */           public void focusGained(FocusEvent e)
/*     */           {
/*  72 */             JLbsCheckBoxGroup.this.recordRequestFocus();
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public void focusLost(FocusEvent e) {}
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void createPopup() {
/*  84 */     this.m_PopupMenu = new JLbsPopupMenu();
/*  85 */     this.m_PopupMenu.setDynamicMenuExtensionEnabled(false);
/*  86 */     this.m_PopupMenu.setActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  90 */             if (e.getSource() instanceof JLbsPopupMenuItem) {
/*     */               
/*  92 */               JLbsPopupMenuItem item = (JLbsPopupMenuItem)e.getSource();
/*  93 */               switch (item.getId()) {
/*     */                 
/*     */                 case 1:
/*  96 */                   JLbsCheckBoxGroup.this.doSelectAll();
/*     */                   break;
/*     */                 case 2:
/*  99 */                   JLbsCheckBoxGroup.this.doDeselectAll();
/*     */                   break;
/*     */                 case 3:
/* 102 */                   JLbsCheckBoxGroup.this.doToggleSelection();
/*     */                   break;
/*     */               } 
/*     */             
/*     */             } 
/*     */           }
/*     */         });
/* 109 */     this.m_PopupMenu.addItems(getPopupList());
/* 110 */     addMouseListener(new MouseListener()
/*     */         {
/*     */           public void mouseReleased(MouseEvent e)
/*     */           {
/* 114 */             if (JLbsCheckBoxGroup.this.m_PopupMenu != null) {
/*     */               
/* 116 */               ComponentOrientation orientation = JLbsCheckBoxGroup.this.m_PopupMenu.getComponentOrientation();
/* 117 */               if (orientation != null && !orientation.isLeftToRight()) {
/*     */                 
/* 119 */                 if (!e.isPopupTrigger() && e.getY() < 15 && e.getX() < 30)
/*     */                 {
/* 121 */                   JLbsCheckBoxGroup.this.m_PopupMenu.show(e.getComponent(), e.getX() - JLbsCheckBoxGroup.this.m_PopupMenu.getWidth(), 15);
/*     */                 
/*     */                 }
/*     */               
/*     */               }
/* 126 */               else if (!e.isPopupTrigger() && e.getY() < 15 && e.getX() > JLbsCheckBoxGroup.this.getWidth() - 15) {
/*     */                 
/* 128 */                 JLbsCheckBoxGroup.this.m_PopupMenu.show(e.getComponent(), JLbsCheckBoxGroup.this.getWidth() - JLbsCheckBoxGroup.this.m_PopupMenu.getWidth(), 15);
/*     */               } 
/*     */             } 
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void mousePressed(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */           
/*     */           public void mouseExited(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */           
/*     */           public void mouseEntered(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */           
/*     */           public void mouseClicked(MouseEvent e) {}
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doToggleSelection() {
/* 155 */     if (getSelectedItemArray() == null) {
/*     */       
/* 157 */       doSelectAll();
/*     */       
/*     */       return;
/*     */     } 
/* 161 */     JLbsStringList list = getItems();
/* 162 */     if (list != null)
/*     */     {
/* 164 */       for (int i = 0; i < list.size(); i++) {
/*     */         
/* 166 */         if (list.getAt(i) != null) {
/*     */           
/* 168 */           ILbsComponent comp = getControlByTag(list.getTagAt(i));
/* 169 */           setSelectedControl(comp, !isSelected(list.getTagAt(i)));
/* 170 */           comp.requestFocus();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 175 */     if (list.size() == 0) {
/*     */       
/* 177 */       Component[] comps = getComponents();
/* 178 */       for (int i = 0; i < comps.length; i++) {
/*     */         
/* 180 */         Component comp = comps[i];
/* 181 */         setItemSelected(comp, !isSelected(getComponentTag(comp)));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isSelected(int tag) {
/* 188 */     int[] selected = getSelectedItemArray();
/* 189 */     if (selected != null)
/*     */     {
/* 191 */       for (int i = 0; i < selected.length; i++) {
/*     */         
/* 193 */         if (selected[i] == tag)
/* 194 */           return true; 
/*     */       } 
/*     */     }
/* 197 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doDeselectAll() {
/* 202 */     setSelectedItemArray(new int[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   private void doSelectAll() {
/* 207 */     JLbsStringList list = getItems();
/* 208 */     if (list != null) {
/*     */       
/* 210 */       int[] tags = new int[list.size()];
/* 211 */       for (int i = 0; i < tags.length; i++)
/*     */       {
/* 213 */         tags[i] = list.getTagAt(i);
/*     */       }
/* 215 */       setSelectedItemArray(tags);
/*     */     } 
/* 217 */     if (list.size() == 0) {
/*     */       
/* 219 */       Component[] comps = getComponents();
/* 220 */       int[] tags = new int[comps.length];
/* 221 */       for (int i = 0; i < comps.length; i++)
/*     */       {
/* 223 */         tags[i] = getComponentTag(comps[i]);
/*     */       }
/* 225 */       setSelectedItemArray(tags);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics g) {
/* 232 */     super.paint(g);
/* 233 */     Border border = getBorder();
/* 234 */     if (border instanceof JLbsGroupBorder && !JLbsStringUtil.isEmpty(((JLbsGroupBorder)border).getTitle()))
/*     */     {
/* 236 */       if (isEnabled() && this.m_PopupMenu != null) {
/*     */         
/* 238 */         int w = 10;
/* 239 */         int h = 10;
/* 240 */         if (JLbsConstants.DESKTOP_MODE) {
/*     */           
/* 242 */           w = 16;
/* 243 */           h = 16;
/*     */         } 
/* 245 */         ComponentOrientation orientation = getComponentOrientation();
/* 246 */         if (orientation != null && !orientation.isLeftToRight()) {
/* 247 */           g.drawImage(ms_CheckAllIcon, getX(), 5, w, h, null);
/*     */         } else {
/* 249 */           g.drawImage(ms_CheckAllIcon, getWidth() - w - 2, 5, w, h, null);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 256 */     JLbsFrame frame = new JLbsFrame();
/* 257 */     frame.setSize(new Dimension(400, 200));
/* 258 */     JLbsPanel content = new JLbsPanel();
/* 259 */     content.setLayout(new BorderLayout());
/* 260 */     frame.setContentPane((Container)content);
/*     */     
/* 262 */     JLbsCheckBoxGroup group = new JLbsCheckBoxGroup();
/* 263 */     group.setColumnCount(1);
/*     */     
/* 265 */     group.addItem(new JLbsStringListItem("Alper", 1), 1);
/* 266 */     group.addItem(new JLbsStringListItem("Safak", 2), 2);
/* 267 */     group.addItem(new JLbsStringListItem("Volkan", 3), 3);
/* 268 */     group.addItem(new JLbsStringListItem("Burhan", 4), 4);
/* 269 */     content.add((Component)new JLbsLabel(), "North");
/* 270 */     content.add((Component)new JLbsLabel(), "East");
/* 271 */     content.add((Component)new JLbsLabel(), "West");
/* 272 */     content.add((Component)new JLbsLabel(), "South");
/* 273 */     content.add((Component)group, "Center");
/*     */     
/* 275 */     frame.show();
/*     */   }
/*     */ 
/*     */   
/*     */   protected LbsMultiColGroupBoxImplementor createImplementor() {
/* 280 */     return new LbsCheckBoxGroupImplementor(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsCheckBox createCheckBox(String caption, boolean selected) {
/* 285 */     JLbsCheckBox chkBox = new JLbsCheckBox(caption, selected);
/* 286 */     UIHelperUtil.setCaption((JComponent)chkBox, caption);
/* 287 */     return (ILbsCheckBox)chkBox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(Object value, int iTag, boolean bChecked) {
/* 298 */     ((LbsCheckBoxGroupImplementor)this.m_Implementor).addItem(value, iTag, bChecked);
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 303 */     recordSetItemSelected((ILbsComponentBase)e.getSource(), true);
/* 304 */     super.actionPerformed(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemSelected(Component c, boolean flag) {
/* 309 */     super.setItemSelected(c, flag);
/* 310 */     if (c instanceof ILbsCheckBox)
/*     */     {
/* 312 */       recordSetItemSelected((ILbsComponentBase)c, flag);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItemDeselectedByTag(int iTag) {
/* 319 */     ((LbsCheckBoxGroupImplementor)this.m_Implementor).setItemDeselectedByTag(iTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doSetItemSelected(Component c, boolean flag, int actionID) {
/* 324 */     if (c == null)
/*     */     {
/* 326 */       JLbsComponentHelper.statusChanged(1, actionID, null);
/*     */     }
/* 328 */     final Component comp = c;
/* 329 */     final boolean mFlag = flag;
/* 330 */     final int action = actionID;
/*     */     
/* 332 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 336 */             JLbsCheckBoxGroup.this.setItemSelected(comp, mFlag);
/* 337 */             if (((JLbsCheckBox)comp).isSelected() != mFlag && mFlag) {
/* 338 */               JLbsComponentHelper.statusChanged(1, action, null);
/* 339 */             } else if (((JLbsCheckBox)comp).isSelected() != mFlag && !mFlag) {
/* 340 */               JLbsComponentHelper.statusChanged(1, action, null);
/*     */             } else {
/* 342 */               JLbsComponentHelper.statusChanged(4, action, null);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void recordSetItemSelected(ILbsComponentBase checkbox, boolean flag) {
/* 349 */     ((JLbsCheckBox)checkbox).putClientProperty("EVENT_TAG", 
/* 350 */         Integer.valueOf(getComponentTag((Component)checkbox)));
/* 351 */     ((JLbsCheckBox)checkbox).putClientProperty("EVENT_PARENT_TAG", 
/* 352 */         Integer.valueOf(JLbsComponentHelper.getTag((ILbsComponentBase)this)));
/* 353 */     ((JLbsCheckBox)checkbox).putClientProperty("EVENT_TYPE", Integer.valueOf(107));
/* 354 */     ((JLbsCheckBox)checkbox).putClientProperty("EVENT_VALUE", 
/* 355 */         ((JLbsCheckBox)checkbox).isSelected() ? 
/* 356 */         Integer.valueOf(1) : 
/* 357 */         Integer.valueOf(0));
/* 358 */     JLbsEventRecorderHelper.addRecordItem(checkbox, "SET_SELECTED");
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
/*     */   public void recordFireItemStateChanged(ItemEvent event) {}
/*     */ 
/*     */   
/*     */   public void recordRequestFocus() {
/* 379 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "REQUEST_FOCUS");
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRequestFocus(int actionID) {
/* 384 */     final int action = actionID;
/* 385 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 389 */             JLbsCheckBoxGroup.this.requestFocus();
/* 390 */             JLbsComponentHelper.statusChanged(4, action, null);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsCheckBox getCheckBoxControl(int index) {
/* 397 */     return ((LbsCheckBoxGroupImplementor)this.m_Implementor).getCheckBoxControl(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPopupList(JLbsStringList popupList) {
/* 402 */     this.m_PopupList = popupList;
/* 403 */     createPopup();
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getPopupList() {
/* 408 */     if (this.m_PopupList == null)
/* 409 */       this.m_PopupList = DEFAULT_LIST; 
/* 410 */     return this.m_PopupList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPopupMenuVisible(boolean value) {
/* 415 */     if (this.m_PopupMenu != null)
/*     */     {
/* 417 */       this.m_PopupMenu.setEnabled(value);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\groupbox\JLbsCheckBoxGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */