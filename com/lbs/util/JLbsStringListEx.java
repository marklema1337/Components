/*     */ package com.lbs.util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsStringListEx
/*     */   extends JLbsStringList
/*     */ {
/*     */   private static final long serialVersionUID = -2629721431269590011L;
/*     */   
/*     */   public JLbsStringListEx() {}
/*     */   
/*     */   public JLbsStringListEx(JLbsStringList list) {
/*  19 */     super(list);
/*     */   }
/*     */   
/*     */   public JLbsStringListEx(String list) {
/*  23 */     super(list);
/*     */   }
/*     */   
/*     */   public JLbsStringListEx(String[] list) {
/*  27 */     super(list);
/*     */   }
/*     */   
/*     */   public int add(int index, String item, int iTag, Object obj) {
/*  31 */     beginUpdate();
/*  32 */     this.m_List.add(index, createItem(item, iTag, obj));
/*  33 */     endUpdate();
/*  34 */     return index;
/*     */   }
/*     */ 
/*     */   
/*     */   public int add(JLbsStringListItemEx item) {
/*  39 */     int index = this.m_List.size();
/*  40 */     beginUpdate();
/*  41 */     this.m_List.add(index, item);
/*  42 */     endUpdate();
/*  43 */     return index;
/*     */   }
/*     */ 
/*     */   
/*     */   public int add(String item, int iTag, Object obj) {
/*  48 */     return add(this.m_List.size(), item, iTag, obj);
/*     */   }
/*     */ 
/*     */   
/*     */   protected JLbsStringListItem createItem(JLbsStringListItem item) {
/*  53 */     return new JLbsStringListItemEx(item);
/*     */   }
/*     */ 
/*     */   
/*     */   protected JLbsStringListItem createItem(String s) {
/*  58 */     return new JLbsStringListItemEx(s);
/*     */   }
/*     */ 
/*     */   
/*     */   protected JLbsStringListItem createItem(String s, int tag) {
/*  63 */     return new JLbsStringListItemEx(s, tag);
/*     */   }
/*     */ 
/*     */   
/*     */   protected JLbsStringListItem createItem(String s, int tag, Object obj) {
/*  68 */     return new JLbsStringListItemEx(s, tag, obj);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getFlagAtTag(int iTag) {
/*  73 */     int index = indexOf(iTag);
/*  74 */     if (index >= 0)
/*  75 */       return ((JLbsStringListItemEx)this.m_List.get(index)).getFlag(); 
/*  76 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFlagAtTag(int iTag, boolean flag) {
/*  81 */     int index = indexOf(iTag);
/*  82 */     if (index >= 0) {
/*  83 */       ((JLbsStringListItemEx)this.m_List.get(index)).setFlag(flag);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean getFlag(int index) {
/*  88 */     if (index >= 0 && index < size())
/*  89 */       return ((JLbsStringListItemEx)this.m_List.get(index)).getFlag(); 
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFlag(int index, boolean flag) {
/*  95 */     if (index >= 0 && index < size()) {
/*  96 */       ((JLbsStringListItemEx)this.m_List.get(index)).setFlag(flag);
/*     */     }
/*     */   }
/*     */   
/*     */   public Object getObjectAtTag(int iTag) {
/* 101 */     int index = indexOf(iTag);
/* 102 */     if (index >= 0)
/* 103 */       return ((JLbsStringListItemEx)this.m_List.get(index)).getObject(); 
/* 104 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getObject(int index) {
/* 109 */     if (index >= 0 && index < size())
/* 110 */       return ((JLbsStringListItemEx)this.m_List.get(index)).getObject(); 
/* 111 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getObject(String value) {
/* 116 */     int index = indexOf(value);
/* 117 */     if (index >= 0)
/* 118 */       return ((JLbsStringListItemEx)this.m_List.get(index)).getObject(); 
/* 119 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsStringListEx.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */