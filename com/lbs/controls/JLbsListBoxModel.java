/*     */ package com.lbs.controls;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.AbstractListModel;
/*     */ 
/*     */ public class JLbsListBoxModel
/*     */   extends AbstractListModel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  12 */   private ArrayList m_List = new ArrayList();
/*  13 */   private ActionListener m_ChangeListener = null;
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  17 */     synchronized (this.m_List) {
/*     */       
/*  19 */       return this.m_List.size();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getElementAt(int index) {
/*  25 */     synchronized (this.m_List) {
/*     */       
/*  27 */       return (index >= 0 && index < this.m_List.size()) ? 
/*  28 */         this.m_List.get(index) : 
/*  29 */         null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  35 */     synchronized (this) {
/*     */       
/*  37 */       this.m_List.clear();
/*  38 */       changed();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int add(Object item) {
/*  44 */     synchronized (this) {
/*     */       
/*  46 */       if (this.m_List.add(item)) {
/*     */         
/*  48 */         changed();
/*  49 */         int index = this.m_List.size() - 1;
/*  50 */         fireIntervalAdded(this, index, index);
/*  51 */         return index;
/*     */       } 
/*     */     } 
/*  54 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int add(int idx, Object item) {
/*  59 */     synchronized (this) {
/*     */       
/*  61 */       this.m_List.add(idx, item);
/*  62 */       changed();
/*  63 */       int index = this.m_List.size() - 1;
/*  64 */       fireIntervalAdded(this, index, index);
/*  65 */       return index;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(Object o) {
/*  71 */     int index = this.m_List.indexOf(o);
/*  72 */     if (index >= 0) {
/*     */       
/*  74 */       this.m_List.remove(index);
/*  75 */       changed();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean swap(int index1, int index2) {
/*  81 */     if (index1 != index2 && index1 >= 0 && index2 >= 0 && index1 < this.m_List.size() && index2 < this.m_List.size()) {
/*     */       
/*  83 */       Object o1 = this.m_List.get(index1);
/*  84 */       Object o2 = this.m_List.get(index2);
/*  85 */       this.m_List.set(index2, o1);
/*  86 */       this.m_List.set(index1, o2);
/*  87 */       changed();
/*  88 */       return true;
/*     */     } 
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChangeListener(ActionListener listener) {
/*  95 */     this.m_ChangeListener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void changed() {
/* 100 */     if (this.m_ChangeListener != null)
/* 101 */       this.m_ChangeListener.actionPerformed(new ActionEvent(this, 0, null)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsListBoxModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */