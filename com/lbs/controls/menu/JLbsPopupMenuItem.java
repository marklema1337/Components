/*     */ package com.lbs.controls.menu;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsPopupMenu;
/*     */ import com.lbs.control.interfaces.ILbsPopupMenuItem;
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.controls.JLbsEventRecorderHelper;
/*     */ import com.lbs.controls.JLbsSwingUtilities;
/*     */ import com.lbs.recording.interfaces.ILbsRecordingEvents;
/*     */ import com.lbs.util.UIHelperUtil;
/*     */ import java.awt.Component;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.JMenuItem;
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
/*     */ public class JLbsPopupMenuItem
/*     */   extends JMenuItem
/*     */   implements ILbsPopupMenuItem, ILbsRecordingEvents
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  40 */   protected int m_iId = 0;
/*  41 */   protected int m_iIndex = 0;
/*  42 */   protected ILbsPopupMenu m_RootMenu = null;
/*     */ 
/*     */   
/*     */   protected boolean m_isSubMenuItem = false;
/*     */ 
/*     */   
/*     */   protected boolean m_ActionFinished = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsPopupMenuItem(String s) {
/*  53 */     UIHelperUtil.setCaption(this, s);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsPopupMenuItem(ILbsPopupMenu rootMenu, String s, int id, int index) {
/*  64 */     this(s);
/*  65 */     this.m_RootMenu = rootMenu;
/*  66 */     this.m_iId = id;
/*  67 */     this.m_iIndex = index;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsPopupMenuItem(ILbsPopupMenu rootMenu, String s, int id, int index, boolean subMenu) {
/*  72 */     this(s);
/*  73 */     this.m_isSubMenuItem = subMenu;
/*  74 */     this.m_RootMenu = rootMenu;
/*  75 */     this.m_iId = id;
/*  76 */     this.m_iIndex = index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getId() {
/*  85 */     return this.m_iId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIndex() {
/*  94 */     return this.m_iIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTag() {
/*  99 */     return this.m_iId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toEditString() {
/* 104 */     String text = getText();
/* 105 */     return toEditString(text, this.m_iId);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toEditString(String text, int id) {
/* 110 */     StringBuilder buffer = new StringBuilder();
/* 111 */     if (text != null)
/* 112 */       buffer.append(text); 
/* 113 */     buffer.append('~');
/* 114 */     buffer.append(id);
/* 115 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 120 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 125 */     return this.m_iId;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isActionFinished() {
/* 130 */     return this.m_ActionFinished;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionFinished(boolean actionFinished) {
/* 135 */     this.m_ActionFinished = actionFinished;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fireActionPerformed(ActionEvent event) {
/* 140 */     boolean controlKeyPressed = ((event.getModifiers() & 0x2) == 2);
/* 141 */     boolean shiftKeyPressed = ((event.getModifiers() & 0x1) == 1);
/* 142 */     if (this.m_RootMenu != null && this.m_RootMenu instanceof JLbsPopupMenu) {
/*     */       
/* 144 */       Object rootComp = null;
/* 145 */       if (this.m_isSubMenuItem) {
/*     */         
/* 147 */         if (this.m_RootMenu.getParentComponent() != null) {
/* 148 */           rootComp = this.m_RootMenu;
/*     */         } else {
/* 150 */           rootComp = this.m_RootMenu.getPopupParent();
/*     */         } 
/*     */       } else {
/* 153 */         rootComp = ((JLbsPopupMenu)this.m_RootMenu).getInvoker();
/*     */       } 
/* 155 */       if (this.m_isSubMenuItem) {
/*     */         
/* 157 */         String ParentTagStr = "";
/* 158 */         Integer parentTag = (Integer)getClientProperty(JLbsComponentHelper.getParentPopupTagConstant());
/* 159 */         if (parentTag != null) {
/* 160 */           ParentTagStr = parentTag.toString();
/*     */         }
/* 162 */         JLbsComponentHelper.setPopupItemUsagesStr(rootComp, String.valueOf(ParentTagStr) + Integer.toString(this.m_iId), 1);
/*     */ 
/*     */       
/*     */       }
/* 166 */       else if (!(this.m_RootMenu.getPopupParent() instanceof com.lbs.controls.JLbsMenuButton)) {
/* 167 */         JLbsComponentHelper.setPopupItemUsagesStr(rootComp, Integer.toString(this.m_iId), 1);
/*     */       } 
/*     */       
/* 170 */       if (controlKeyPressed) {
/* 171 */         System.setProperty("ModifierKeyPressed", JLbsComponentHelper.getFormName(rootComp));
/*     */       }
/* 173 */       if (shiftKeyPressed) {
/* 174 */         JLbsComponentHelper.addPopupItemToFavorites(rootComp, this);
/*     */       }
/*     */     } 
/* 177 */     recordActionPerformed(event);
/* 178 */     super.fireActionPerformed(event);
/*     */     
/* 180 */     if (controlKeyPressed) {
/* 181 */       System.setProperty("ModifierKeyPressed", "0");
/*     */     }
/*     */   }
/*     */   
/*     */   public void doFireActionPerformed(ActionEvent event, int actionID) {
/* 186 */     final ActionEvent actionEvent = event;
/* 187 */     final int mActionID = actionID;
/* 188 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 192 */             JLbsPopupMenuItem.this.paintAsSelected();
/* 193 */             JLbsComponentHelper.statusChanged(4, mActionID, null);
/* 194 */             JLbsPopupMenuItem.this.fireActionPerformed(actionEvent);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintAsSelected() {
/* 203 */     if (this.m_RootMenu != null && this.m_RootMenu instanceof JLbsPopupMenu)
/*     */     {
/* 205 */       for (int i = 0; i < this.m_RootMenu.getMenuItemCount(); i++) {
/*     */         
/* 207 */         JMenuItem menuItem = ((JLbsPopupMenu)this.m_RootMenu).getMenuItem(i);
/* 208 */         if (menuItem != null && menuItem.isVisible()) {
/*     */           
/* 210 */           menuItem.getModel().setArmed(false);
/* 211 */           menuItem.getModel().setPressed(false);
/* 212 */           menuItem.paintImmediately(new Rectangle(0, 0, (getSize()).width, (getSize()).height));
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 217 */     getModel().setArmed(true);
/* 218 */     getModel().setPressed(true);
/* 219 */     paintImmediately(new Rectangle(0, 0, (getSize()).width, (getSize()).height));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordActionPerformed(ActionEvent evt) {
/* 225 */     if (evt == null) {
/*     */       return;
/*     */     }
/* 228 */     Component invoker = null;
/*     */ 
/*     */     
/* 231 */     if (this.m_RootMenu != null && this.m_RootMenu instanceof JLbsPopupMenu) {
/*     */       
/* 233 */       invoker = ((JLbsPopupMenu)this.m_RootMenu).getInvoker();
/*     */       
/* 235 */       if (((JLbsPopupMenu)this.m_RootMenu).getPopupParent() instanceof com.lbs.controls.JLbsMenuButton || invoker instanceof com.lbs.controls.JLbsTabPage || 
/* 236 */         invoker instanceof javax.swing.JToolBar || invoker instanceof com.lbs.controls.datedit.JLbsCalendarPanel) {
/*     */         return;
/*     */       }
/*     */     } 
/* 240 */     StringBuilder buffer = new StringBuilder();
/* 241 */     buffer.append("POPUPMENU_ITEM_SELECTED");
/* 242 */     buffer.append("|");
/*     */     
/* 244 */     if (invoker instanceof ILbsComponentBase) {
/*     */       
/* 246 */       int gridTag, ownerTag, invokerType = JLbsComponentHelper.getInvokerType((ILbsComponentBase)invoker);
/*     */       
/* 248 */       switch (invokerType) {
/*     */         
/*     */         case 2:
/* 251 */           gridTag = JLbsComponentHelper.getGridTag((ILbsComponentBase)invoker);
/* 252 */           buffer.append(gridTag);
/*     */           break;
/*     */         
/*     */         case 1:
/* 256 */           ownerTag = JLbsComponentHelper.getInpEditorOwnerTag((ILbsComponentBase)invoker);
/* 257 */           buffer.append(ownerTag);
/*     */           break;
/*     */         
/*     */         default:
/* 261 */           buffer.append(-1);
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/*     */     } else {
/* 267 */       buffer.append(-1);
/*     */     } 
/*     */     
/* 270 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, buffer.toString());
/*     */   }
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
/* 287 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 292 */     return this.m_RootMenu;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\menu\JLbsPopupMenuItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */