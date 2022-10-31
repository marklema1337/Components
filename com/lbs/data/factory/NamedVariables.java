/*     */ package com.lbs.data.factory;
/*     */ 
/*     */ import com.lbs.data.objects.ILbsRttiCachable;
/*     */ import com.lbs.data.xstream.ILbsXStreamListener;
/*     */ import com.lbs.util.ExternalizationUtil;
/*     */ import com.lbs.util.ObjectUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
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
/*     */ public class NamedVariables
/*     */   implements ISubstitutionVariables, Serializable, Cloneable, Externalizable, ILbsXStreamListener, ILbsRttiCachable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("Variables")
/*     */   private Hashtable<String, Object> m_Variables;
/*     */   
/*     */   public NamedVariables() {
/*  40 */     afterXStreamDeserialization();
/*     */   }
/*     */ 
/*     */   
/*     */   public void afterXStreamDeserialization() {
/*  45 */     if (this.m_Variables == null) {
/*  46 */       this.m_Variables = new Hashtable<>();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Enumeration<String> keys() {
/*  52 */     return this.m_Variables.keys();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean exists(String name) {
/*  57 */     return this.m_Variables.containsKey(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public void put(String name, Object value) {
/*  62 */     if (StringUtil.isEmpty(name)) {
/*     */       return;
/*     */     }
/*  65 */     if (value != null)
/*     */     {
/*  67 */       if (value instanceof Object[]) {
/*     */         
/*  69 */         Object[] items = (Object[])value;
/*  70 */         for (int i = 0; i < items.length; i++) {
/*     */           
/*  72 */           Object item = items[i];
/*  73 */           if (item instanceof String)
/*     */           {
/*  75 */             items[i] = replaceChar(item);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*  80 */     Object prev = this.m_Variables.get(name);
/*     */     
/*  82 */     if (prev != null) {
/*  83 */       this.m_Variables.remove(name);
/*     */     }
/*  85 */     if (value == null) {
/*     */       return;
/*     */     }
/*  88 */     this.m_Variables.put(name, value);
/*     */   }
/*     */ 
/*     */   
/*     */   private String replaceChar(Object value) {
/*  93 */     return ((String)value).replace("'", "''");
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getObject(String name) {
/*  98 */     return this.m_Variables.get(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setObject(String name, Object obj) {
/* 103 */     this.m_Variables.put((name != null) ? name : "", obj);
/* 104 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 109 */     this.m_Variables.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 118 */     Object obj = super.clone();
/*     */     
/* 120 */     ObjectUtil.deepCopy(this, obj, NamedVariables.class);
/*     */     
/* 122 */     return obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 127 */     ExternalizationUtil.writeExternal(this.m_Variables, out);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 132 */     ExternalizationUtil.readExternal(this.m_Variables, in);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 139 */     if (this.m_Variables != null)
/* 140 */       return this.m_Variables.toString(); 
/* 141 */     return super.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\NamedVariables.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */