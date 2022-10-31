/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComboBox;
/*     */ import com.lbs.control.interfaces.ILbsInputVerifier;
/*     */ import com.lbs.controls.maskededit.JLbsMaskedEdit;
/*     */ import com.lbs.recording.interfaces.ILbsComboBoxRecordingEvents;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsConvertUtil;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.dnd.DragSource;
/*     */ import java.awt.dnd.DropTargetDragEvent;
/*     */ import java.awt.dnd.DropTargetDropEvent;
/*     */ import java.awt.dnd.DropTargetEvent;
/*     */ import java.awt.dnd.DropTargetListener;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.ComboBoxEditor;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.InputVerifier;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.plaf.basic.ComboPopup;
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
/*     */ public class JLbsComboBox
/*     */   extends JComboBox
/*     */   implements ILbsComboBox, ILbsComboBoxRecordingEvents, DropTargetListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  70 */   private LbsComboBoxImplementor m_Implementor = new LbsComboBoxImplementor(this);
/*     */   
/*     */   boolean m_HasIcon = false;
/*  73 */   private String m_InputIndex = "";
/*     */   
/*     */   private long m_LastTime;
/*  76 */   private AutoCompletion m_AutoCompletion = null;
/*     */   
/*     */   private boolean m_AlreadyEditable = false;
/*     */   
/*     */   private ILbsInputVerifier m_Verifier;
/*     */ 
/*     */   
/*     */   public JLbsComboBox() {
/*  84 */     this((String)null, (Object)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsComboBox(String clientPropertyName, Object clientPropertyValue) {
/*  89 */     if (clientPropertyName != null && clientPropertyValue != null)
/*  90 */       putClientProperty(clientPropertyName, clientPropertyValue); 
/*  91 */     if (!JLbsConstants.DESKTOP_MODE)
/*  92 */       setUI(new JLbsComboBoxUI(this)); 
/*  93 */     enableEvents(8L);
/*  94 */     addFocusListener(new FocusListener()
/*     */         {
/*     */           
/*     */           public void focusGained(FocusEvent e)
/*     */           {
/*  99 */             JLbsComboBox.this.recordRequestFocus();
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void focusLost(FocusEvent e) {}
/*     */         });
/* 108 */     if (!GraphicsEnvironment.isHeadless());
/*     */ 
/*     */     
/* 111 */     this.m_AutoCompletion = AutoCompletion.enable(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInputVerifier(InputVerifier inputVerifier) {
/* 117 */     super.setInputVerifier(inputVerifier);
/* 118 */     if (isEditable()) {
/*     */ 
/*     */ 
/*     */       
/* 122 */       ComboBoxEditor comboBoxEditor = getEditor();
/* 123 */       if (comboBoxEditor != null) {
/*     */         
/* 125 */         Component editorComponent = comboBoxEditor.getEditorComponent();
/* 126 */         if (editorComponent instanceof JComponent)
/*     */         {
/* 128 */           ((JComponent)editorComponent).setInputVerifier(inputVerifier);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEditable(boolean aFlag) {
/* 137 */     super.setEditable(aFlag);
/* 138 */     this.m_AlreadyEditable = aFlag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEditableForAutoComplete(boolean aFlag) {
/* 144 */     super.setEditable(aFlag);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAlreadyEditable() {
/* 149 */     return this.m_AlreadyEditable;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addKeyListener(KeyListener l) {
/* 155 */     super.addKeyListener(l);
/* 156 */     if (isEditable()) {
/*     */ 
/*     */ 
/*     */       
/* 160 */       ComboBoxEditor comboBoxEditor = getEditor();
/* 161 */       if (comboBoxEditor != null) {
/*     */         
/* 163 */         Component editorComponent = comboBoxEditor.getEditorComponent();
/* 164 */         if (editorComponent != null)
/*     */         {
/* 166 */           editorComponent.addKeyListener(l);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 174 */     super.updateUI();
/*     */     
/* 176 */     Border border = getBorder();
/*     */     
/* 178 */     if (!(border instanceof javax.swing.border.EmptyBorder)) {
/* 179 */       setBorder(UIManager.getBorder("ComboBox.border"));
/*     */     }
/* 181 */     if (isEditable()) {
/*     */       
/* 183 */       ComboBoxEditor comboBoxEditor = getEditor();
/* 184 */       if (comboBoxEditor != null) {
/*     */         
/* 186 */         Component editorComponent = comboBoxEditor.getEditorComponent();
/* 187 */         if (editorComponent != null) {
/*     */ 
/*     */ 
/*     */           
/* 191 */           KeyListener[] keyListeners = getKeyListeners(); byte b; int i; KeyListener[] arrayOfKeyListener1;
/* 192 */           for (i = (arrayOfKeyListener1 = keyListeners).length, b = 0; b < i; ) { KeyListener keyListener = arrayOfKeyListener1[b];
/*     */             
/* 194 */             KeyListener[] innerKeyListeners = editorComponent.getKeyListeners();
/* 195 */             boolean fnd = false; byte b1; int j; KeyListener[] arrayOfKeyListener2;
/* 196 */             for (j = (arrayOfKeyListener2 = innerKeyListeners).length, b1 = 0; b1 < j; ) { KeyListener innerKeyListener = arrayOfKeyListener2[b1];
/*     */               
/* 198 */               if (innerKeyListener == keyListener)
/* 199 */                 fnd = true;  b1++; }
/*     */             
/* 201 */             if (!fnd) {
/* 202 */               editorComponent.addKeyListener(keyListener);
/*     */             }
/*     */             b++; }
/*     */         
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
/*     */   public JLbsComboBox(JLbsStringList list) {
/* 219 */     this();
/* 220 */     setItems(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsComboBox(Object[] list) {
/* 229 */     if (list != null)
/* 230 */       for (int i = 0; i < list.length; i++)
/*     */       {
/* 232 */         this.m_Implementor.addItem(list[i], i);
/*     */       } 
/* 234 */     doFilterItems();
/*     */     
/* 236 */     if (!GraphicsEnvironment.isHeadless());
/*     */ 
/*     */     
/* 239 */     this.m_AutoCompletion = AutoCompletion.enable(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getFilteredItems() {
/* 244 */     return this.m_Implementor.getFilteredItemList();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrentCaretPosition() {
/* 249 */     if (this.m_AutoCompletion == null)
/* 250 */       return -1; 
/* 251 */     return this.m_AutoCompletion.getCurrentCaretPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentCaretPosition(int currentCaretPosition) {
/* 256 */     if (this.m_AutoCompletion != null && currentCaretPosition >= 0)
/*     */     {
/* 258 */       this.m_AutoCompletion.setCurrentCaretPosition(currentCaretPosition);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public AutoCompletion getAutoCompletion() {
/* 264 */     return this.m_AutoCompletion;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoCompletion(AutoCompletion autoCompletion) {
/* 269 */     this.m_AutoCompletion = autoCompletion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(Object anObject) {
/* 278 */     if (anObject instanceof JLbsComboBoxItem) {
/* 279 */       super.addItem(anObject);
/*     */     } else {
/* 281 */       super.addItem(new JLbsComboBoxItem(anObject, 0));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(Object anObject, int iTag, ImageIcon icon) {
/* 292 */     if (icon != null)
/* 293 */       this.m_HasIcon = true; 
/* 294 */     this.m_Implementor.addItem(anObject, iTag, icon);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object anObject, int iTag) {
/* 299 */     this.m_Implementor.addItem(anObject, iTag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItems(JLbsStringList list) {
/* 309 */     this.m_Implementor.addItems(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearItems() {
/* 317 */     this.m_Implementor.clearItems();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItems(JLbsStringList list) {
/* 325 */     this.m_Implementor.setItems(list);
/* 326 */     internalVerifyInput();
/*     */   }
/*     */ 
/*     */   
/*     */   public void doInternalVerifyInput(int actionID) {
/* 331 */     final int mActionID = actionID;
/* 332 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           
/*     */           public void run()
/*     */           {
/*     */             try {
/* 338 */               JLbsComboBox.this.m_Implementor.setDisableEvents(true);
/* 339 */               if (JLbsComboBox.this.internalVerifyInput()) {
/* 340 */                 JLbsComponentHelper.statusChanged(4, mActionID, "Verify Input succedded");
/*     */               } else {
/* 342 */                 JLbsComponentHelper.statusChanged(1, mActionID, "Could not be verified!");
/*     */               } 
/* 344 */             } catch (Exception e) {
/*     */               
/* 346 */               JLbsComponentHelper.statusChanged(1, mActionID, "Could not be verified!");
/*     */             }
/*     */             finally {
/*     */               
/* 350 */               JLbsComboBox.this.m_Implementor.setDisableEvents(false);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean internalVerifyInput() {
/* 358 */     InputVerifier verifier = getInputVerifier();
/* 359 */     boolean verified = false;
/* 360 */     if (verifier != null) {
/*     */       
/* 362 */       verified = verifier.verify(this);
/* 363 */       if (verified)
/* 364 */         recordInternalVerifyInput(); 
/*     */     } 
/* 366 */     return verified;
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordInternalVerifyInput() {
/* 371 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "VERIFY");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSelectedItemTag() {
/* 379 */     return this.m_Implementor.getSelectedItemTag();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setSelectedItemTag(int iTag) {
/* 388 */     return this.m_Implementor.setSelectedItemTag(iTag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsComboBoxItem getItemTagObject(int iTag) {
/* 397 */     return this.m_Implementor.getItemTagObject(iTag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasItemTag(int iTag) {
/* 407 */     return this.m_Implementor.hasItemTag(iTag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getItemTagValue(int iTag) {
/* 417 */     return this.m_Implementor.getItemTagValue(iTag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItemTagString(int iTag) {
/* 427 */     return this.m_Implementor.getItemTagString(iTag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setSelectedItem(String itemText) {
/* 436 */     return this.m_Implementor.setSelectedItem(itemText);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getSelectedItemValue() {
/* 441 */     return this.m_Implementor.getSelectedItemValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int setSelectedItemValue(Object value) {
/* 446 */     return this.m_Implementor.setSelectedItemValue(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTagOfItem(Object item) {
/* 451 */     return this.m_Implementor.getTagOfItem(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public int findItemIndex(Object value) {
/* 456 */     return this.m_Implementor.findItemIndex(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public int findItemIndex(String value) {
/* 461 */     return this.m_Implementor.findItemIndex(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] getItemlist() {
/* 466 */     return this.m_Implementor.getItemlist();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsComboFilterListener getFilterListener() {
/* 471 */     return this.m_Implementor.getFilterListener();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFilterListener(ILbsComboFilterListener listener) {
/* 476 */     this.m_Implementor.setFilterListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doFilterItems() {
/* 481 */     this.m_Implementor.doFilterItems();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processFocusEvent(FocusEvent e) {
/* 486 */     switch (e.getID()) {
/*     */       
/*     */       case 1004:
/* 489 */         setBackground(JLbsMaskedEdit.getSelectedBkColor());
/*     */         break;
/*     */       case 1005:
/* 492 */         setBackground(getNormalBkColor());
/*     */         break;
/*     */     } 
/* 495 */     super.processFocusEvent(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getNormalBkColor() {
/* 500 */     return UIManager.getColor("ComboBox.background");
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMouseListenerEx(MouseListener l) {
/* 505 */     if (l == null)
/*     */       return; 
/* 507 */     setMouseListener(this, l);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setMouseListener(Component c, MouseListener l) {
/* 512 */     c.addMouseListener(l);
/* 513 */     if (c instanceof Container) {
/*     */       
/* 515 */       Container cc = (Container)c;
/* 516 */       for (int i = cc.getComponentCount() - 1; i >= 0; i--) {
/* 517 */         setMouseListener(cc.getComponent(i), l);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public Component getComponentAt(int x, int y) {
/* 523 */     if (JLbsConstants.DESIGN_TIME)
/* 524 */       return null; 
/* 525 */     return super.getComponentAt(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ComboPopup createComboBoxPopup(JLbsComboBox combo) {
/* 530 */     return new JLbsComboboxPopup(combo);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getItemsSList() {
/* 535 */     return this.m_Implementor.getItemsSList();
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList getItems() {
/* 540 */     return this.m_Implementor.getItems();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 545 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 550 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doSetSelectedItem(int actionID, Object item) {
/* 555 */     final int mActionID = actionID;
/* 556 */     final Object mItem = item;
/* 557 */     setPopupVisible(true);
/* 558 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           
/*     */           public void run()
/*     */           {
/*     */             try {
/* 564 */               JLbsComboBox.this.m_Implementor.setDisableEvents(true);
/* 565 */               JLbsComboBox.this.setPopupVisible(true);
/* 566 */               JLbsComboBox.this.setSelectedItemTag(JLbsConvertUtil.str2IntDef((String)mItem, -1));
/* 567 */               JLbsComboBox.this.setPopupVisible(false);
/* 568 */               JLbsComponentHelper.statusChanged(4, mActionID, 
/* 569 */                   "Set Selected Item succedded.");
/*     */             }
/* 571 */             catch (Exception e) {
/*     */               
/* 573 */               JLbsComponentHelper.statusChanged(1, mActionID, 
/* 574 */                   "Item " + mItem + " could not be set!");
/*     */ 
/*     */               
/*     */               return;
/*     */             } finally {
/* 579 */               JLbsComboBox.this.m_Implementor.setDisableEvents(false);
/*     */             } 
/*     */           }
/*     */         });
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
/*     */   public void doRequestFocus(int actionID) {
/* 658 */     final int mActionID = actionID;
/* 659 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 663 */             JLbsComboBox.this.requestFocus();
/* 664 */             JLbsComponentHelper.statusChanged(4, mActionID, null);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusOwner() {
/* 671 */     return super.isFocusOwner();
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
/*     */   public void recordActionPerformed(ActionEvent evt) {}
/*     */ 
/*     */   
/*     */   public void recordRequestFocus() {
/* 688 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "REQUEST_FOCUS");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedIndex(int anIndex) {
/* 693 */     if (!this.m_Implementor.isInternal())
/* 694 */       doFilterItems(); 
/* 695 */     super.setSelectedIndex(anIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void selectedItemChanged() {
/* 700 */     if (!this.m_Implementor.isDisableEvents()) {
/*     */       
/* 702 */       super.selectedItemChanged();
/* 703 */       recordSetSelectedItem();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordSetSelectedItem() {
/* 709 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "CMB_SET_SELECTED_ITEM_STR", 
/* 710 */         String.valueOf(getSelectedItemTag()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordSetSelectedItem(ItemEvent e) {
/* 718 */     if (e.getStateChange() == 1 && e.getID() == 701 && getSelectedIndex() != -1)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 726 */       JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "CMB_SET_SELECTED_ITEM_STR", 
/* 727 */           String.valueOf(getSelectedItemTag()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 733 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 738 */     return getParent();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsInputVerifier getLbsInputVerifier() {
/* 745 */     return this.m_Verifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLbsInputVerifier(ILbsInputVerifier verifier) {
/* 750 */     this.m_Verifier = verifier;
/* 751 */     if (verifier instanceof InputVerifier) {
/* 752 */       setInputVerifier((InputVerifier)verifier);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void dragEnter(DropTargetDragEvent dtde) {
/* 758 */     setCursor(DragSource.DefaultMoveNoDrop);
/* 759 */     dtde.rejectDrag();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dragOver(DropTargetDragEvent dtde) {
/* 765 */     dtde.rejectDrag();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropActionChanged(DropTargetDragEvent dtde) {
/* 771 */     dtde.rejectDrag();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dragExit(DropTargetEvent dte) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void drop(DropTargetDropEvent dtde) {
/* 782 */     dtde.rejectDrop();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsComboBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */