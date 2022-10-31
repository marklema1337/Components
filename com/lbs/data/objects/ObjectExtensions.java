/*     */ package com.lbs.data.objects;
/*     */ 
/*     */ import com.lbs.util.ExternalizationUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObjectExtensions
/*     */   implements Serializable, Cloneable, Externalizable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  29 */   protected ArrayList<CustomBusinessObject> m_List = new ArrayList<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public CustomBusinessObject get(int index) {
/*  34 */     return this.m_List.get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public CustomBusinessObject get(String name) {
/*  39 */     int idx = find(name);
/*  40 */     if (idx == -1) {
/*  41 */       return null;
/*     */     }
/*  43 */     return this.m_List.get(idx);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  48 */     this.m_List.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(CustomBusinessObject obj) {
/*  53 */     int idx = find(obj._getObjectName());
/*  54 */     if (idx != -1) {
/*  55 */       this.m_List.set(idx, obj);
/*     */     } else {
/*  57 */       this.m_List.add(obj);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int find(String name) {
/*  63 */     for (int i = 0; i < this.m_List.size(); i++) {
/*     */       
/*  65 */       CustomBusinessObject cObj = this.m_List.get(i);
/*     */       
/*  67 */       if (StringUtil.equals(cObj._getObjectName(), name)) {
/*  68 */         return i;
/*     */       }
/*     */     } 
/*  71 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<CustomBusinessObject> getList() {
/*  76 */     return this.m_List;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/*  81 */     return super.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(String name) {
/*  86 */     return (find(name) != -1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void filter(String customization) {
/*  92 */     for (int i = this.m_List.size() - 1; i >= 0; i--) {
/*     */       
/*  94 */       CustomBusinessObject cObj = this.m_List.get(i);
/*     */       
/*  96 */       if (!StringUtil.equals(cObj._getCustomization(), customization)) {
/*  97 */         this.m_List.remove(i);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 103 */     ExternalizationUtil.writeExternal(this.m_List, out);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 108 */     ExternalizationUtil.readExternal(this.m_List, in);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\ObjectExtensions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */