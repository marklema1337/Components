/*     */ package com.lbs.controls.groupbox;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsRadioButton;
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import java.awt.event.ActionEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsRadioButtonGroupImplementor
/*     */   extends LbsMultiColGroupBoxImplementor
/*     */ {
/*  19 */   protected ILbsRadioButton m_Selected = null;
/*     */ 
/*     */   
/*     */   public LbsRadioButtonGroupImplementor(ILbsInternalRadioButtonGroup component) {
/*  23 */     super(component);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(Object value, int iTag, boolean bChecked) {
/*  28 */     JLbsMultiColGroupBoxItem item = createItem(value, new Boolean(bChecked), iTag);
/*  29 */     internalAdd(item, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedItemTag() {
/*  34 */     if (this.m_Selected != null)
/*  35 */       return getComponentTag(this.m_Selected); 
/*  36 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedItemByTag(int tag) {
/*  41 */     for (int i = 0; i < this.m_Component.getComponentCount(); i++) {
/*     */       
/*  43 */       Object aComp = this.m_Component.getChildAt(i);
/*  44 */       if (aComp instanceof ILbsRadioButton) {
/*     */         
/*  46 */         ILbsRadioButton radio = (ILbsRadioButton)aComp;
/*  47 */         if (getComponentTag(radio) == tag)
/*     */         {
/*  49 */           setSelected(radio);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnableItemByTag(int tag, boolean enabled) {
/*  57 */     for (int i = 0; i < this.m_Component.getComponentCount(); i++) {
/*     */       
/*  59 */       Object aComp = this.m_Component.getChildAt(i);
/*  60 */       if (aComp instanceof ILbsRadioButton) {
/*     */         
/*  62 */         ILbsRadioButton radio = (ILbsRadioButton)aComp;
/*  63 */         if (getComponentTag(radio) == tag)
/*     */         {
/*  65 */           radio.setEnabled(enabled);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelected(ILbsRadioButton button) {
/*  73 */     if (this.m_Selected != button) {
/*     */       
/*  75 */       if (this.m_Selected != null) {
/*     */         
/*  77 */         this.m_Selected.setSelected(false);
/*  78 */         ((ILbsInternalRadioButtonGroup)this.m_Component).recordSetItemSelected((ILbsComponentBase)button, true);
/*     */       } 
/*  80 */       this.m_Selected = button;
/*  81 */       this.m_Selected.setSelected(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/*  87 */     ILbsRadioButton button = (ILbsRadioButton)e.getSource();
/*  88 */     if (!button.isSelected()) {
/*     */       
/*  90 */       button.setSelected(true);
/*  91 */       ((ILbsInternalRadioButtonGroup)this.m_Component).recordSetItemSelected((ILbsComponentBase)button, true);
/*     */     } else {
/*     */       
/*  94 */       setSelected(button);
/*  95 */     }  super.actionPerformed(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemSelected(Object c, boolean flag) {
/* 100 */     if (flag && c instanceof ILbsRadioButton) {
/*     */       
/* 102 */       ILbsRadioButton rbt = (ILbsRadioButton)c;
/* 103 */       setSelected((ILbsRadioButton)c);
/* 104 */       ((ILbsInternalRadioButtonGroup)this.m_Component).recordSetItemSelected((ILbsComponentBase)rbt, true);
/* 105 */       actionPerformed(new ActionEvent(c, 1001, null));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsRadioButton getRadioButtonControl(int index) {
/* 111 */     Object c = this.m_Component.getChildAt(index);
/* 112 */     if (c instanceof ILbsRadioButton)
/* 113 */       return (ILbsRadioButton)c; 
/* 114 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected JLbsMultiColGroupBoxItem createItem(Object value, Object params, int iTag) {
/* 119 */     boolean bSelected = (params instanceof Boolean && ((Boolean)params).booleanValue());
/* 120 */     String caption = value.toString();
/* 121 */     ILbsRadioButton button = ((ILbsInternalRadioButtonGroup)this.m_Component).createRadioButton(caption, bSelected);
/* 122 */     if (bSelected)
/* 123 */       setSelected(button); 
/* 124 */     JLbsMultiColGroupBoxItem groupItem = new JLbsMultiColGroupBoxItem((ILbsComponent)button, iTag);
/* 125 */     createItemSucceeded(groupItem);
/* 126 */     return groupItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemSelected(Object c) {
/* 131 */     if (c instanceof ILbsRadioButton)
/* 132 */       return ((ILbsRadioButton)c).isSelected(); 
/* 133 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\groupbox\LbsRadioButtonGroupImplementor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */