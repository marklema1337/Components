/*     */ package com.lbs.controls.groupbox;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsCheckBox;
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsCheckBoxGroupImplementor
/*     */   extends LbsMultiColGroupBoxImplementor
/*     */ {
/*     */   public LbsCheckBoxGroupImplementor(ILbsInternalCheckBoxGroup component) {
/*  22 */     super(component);
/*     */   }
/*     */ 
/*     */   
/*     */   protected JLbsMultiColGroupBoxItem createItem(Object value, Object params, int iTag) {
/*  27 */     String caption = value.toString();
/*  28 */     ILbsCheckBox chkBox = ((ILbsInternalCheckBoxGroup)this.m_Component).createCheckBox(caption, (
/*  29 */         params instanceof Boolean && ((Boolean)params).booleanValue()));
/*  30 */     JLbsMultiColGroupBoxItem groupItem = new JLbsMultiColGroupBoxItem((ILbsComponent)chkBox, iTag);
/*  31 */     createItemSucceeded(groupItem);
/*  32 */     return groupItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object value, int iTag, boolean bChecked) {
/*  37 */     JLbsMultiColGroupBoxItem item = createItem(value, new Boolean(bChecked), iTag);
/*  38 */     internalAdd(item, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemSelected(Object c) {
/*  44 */     if (c instanceof ILbsCheckBox)
/*  45 */       return (((ILbsCheckBox)c).isSelected() && ((ILbsCheckBox)c).isVisible()); 
/*  46 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItemDeselectedByTag(int iTag) {
/*  52 */     Iterator it = this.m_HashMap.keySet().iterator();
/*  53 */     while (it.hasNext()) {
/*     */       
/*  55 */       Object o = it.next();
/*  56 */       Object value = this.m_HashMap.get(o);
/*  57 */       if (value instanceof Integer && o instanceof ILbsCheckBox && ((Integer)value).intValue() == iTag) {
/*  58 */         setItemSelected(o, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setSelectedItemArray(int[] iList) {
/*  64 */     int iLen = this.m_Component.getComponentCount();
/*  65 */     for (int i = 0; i < iLen; i++) {
/*     */       
/*  67 */       Object c = this.m_Component.getChildAt(i);
/*  68 */       int tag = getComponentTag(c);
/*  69 */       boolean selected = false;
/*  70 */       ILbsCheckBox cbox = getComponentByTag(tag);
/*  71 */       if (iList != null)
/*  72 */         for (int j = 0; j < iList.length; j++) {
/*     */ 
/*     */           
/*  75 */           if (tag == iList[j] && cbox != null && cbox.isEnabled()) {
/*     */             
/*  77 */             selected = true;
/*     */             break;
/*     */           } 
/*     */         }  
/*  81 */       if (cbox.isEnabled()) {
/*     */         
/*  83 */         setItemSelected(c, selected);
/*  84 */         cbox.requestFocus();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ILbsCheckBox getComponentByTag(int iTag) {
/*  93 */     Iterator it = this.m_HashMap.keySet().iterator();
/*  94 */     while (it.hasNext()) {
/*     */       
/*  96 */       Object o = it.next();
/*  97 */       Object value = this.m_HashMap.get(o);
/*  98 */       if (value instanceof Integer && o instanceof ILbsCheckBox && ((Integer)value).intValue() == iTag)
/*  99 */         return (ILbsCheckBox)o; 
/*     */     } 
/* 101 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsCheckBox getCheckBoxControl(int index) {
/* 106 */     Object c = this.m_Component.getChildAt(index);
/* 107 */     if (c instanceof ILbsCheckBox)
/* 108 */       return (ILbsCheckBox)c; 
/* 109 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 114 */     ILbsCheckBox button = (ILbsCheckBox)e.getSource();
/* 115 */     button.setSelected(button.isSelected());
/* 116 */     super.actionPerformed(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemSelected(Object c, boolean flag) {
/* 121 */     if (c instanceof ILbsCheckBox)
/*     */     {
/* 123 */       ((ILbsCheckBox)c).setSelected(flag);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\groupbox\LbsCheckBoxGroupImplementor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */