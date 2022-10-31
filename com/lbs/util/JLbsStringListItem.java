/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.data.IParameterMandatoryListener;
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.data.Parameter;
/*     */ import com.lbs.interfaces.ILbsXUIComparable;
/*     */ import com.lbs.interfaces.IParameter;
/*     */ import com.lbs.interfaces.ParameterException;
/*     */ import com.lbs.localization.ILocalizationServices;
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
/*     */ 
/*     */ 
/*     */ public class JLbsStringListItem
/*     */   implements Serializable, ILbsXUIComparable<JLbsStringListItem>, IParameter
/*     */ {
/*     */   private static final long serialVersionUID = 9042472829360364485L;
/*     */   public String Value;
/*     */   public int Tag;
/*     */   
/*     */   public JLbsStringListItem() {}
/*     */   
/*     */   public JLbsStringListItem(String s) {
/*  38 */     if (s != null && s.length() > 0) {
/*     */       
/*  40 */       int index = s.lastIndexOf('~');
/*  41 */       if (index >= 0) {
/*     */         
/*  43 */         this.Value = s.substring(0, index);
/*  44 */         int iLen = s.length();
/*  45 */         if (index < iLen - 1) {
/*     */           
/*     */           try {
/*  48 */             String subStr = s.substring(index + 1, iLen);
/*  49 */             this.Tag = Integer.valueOf(subStr.trim()).intValue();
/*     */           }
/*  51 */           catch (Exception e) {
/*     */             
/*  53 */             this.Tag = 0;
/*     */           } 
/*     */         } else {
/*  56 */           this.Tag = 0;
/*     */         }  return;
/*     */       } 
/*     */     } 
/*  60 */     this.Value = s;
/*  61 */     this.Tag = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringListItem(String s, int iTag) {
/*  66 */     this.Value = s;
/*  67 */     this.Tag = iTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringListItem(JLbsStringListItem item) {
/*  72 */     if (item != null) {
/*     */       
/*  74 */       this.Value = item.Value;
/*  75 */       this.Tag = item.Tag;
/*     */     }
/*     */     else {
/*     */       
/*  79 */       this.Value = null;
/*  80 */       this.Tag = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  86 */     return "(" + this.Value + "," + this.Tag + ")";
/*     */   }
/*     */ 
/*     */   
/*     */   public String toEditString() {
/*  91 */     if (this.Tag != 0)
/*  92 */       return String.valueOf(this.Value) + "~" + this.Tag; 
/*  93 */     return this.Value;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  98 */     if (obj instanceof JLbsStringListItem) {
/*     */       
/* 100 */       JLbsStringListItem item = (JLbsStringListItem)obj;
/* 101 */       if (item.Tag == this.Tag) {
/*     */         
/* 103 */         if (item.Value == this.Value)
/* 104 */           return true; 
/* 105 */         if (item.Value != null && this.Value != null)
/* 106 */           return (this.Value.compareTo(item.Value) == 0); 
/*     */       } 
/* 108 */       return false;
/*     */     } 
/* 110 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 115 */     return toEditString().hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTag() {
/* 120 */     return this.Tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValue() {
/* 125 */     return this.Value;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTag(int t) {
/* 130 */     this.Tag = t;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(String item) {
/* 135 */     this.Value = item;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSameDefinition(JLbsStringListItem obj) {
/* 140 */     return (obj != null && this.Tag == obj.Tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void calculateDelta(JLbsStringListItem obj, ArrayList<String> delta, String prefix) {
/* 145 */     if (!JLbsStringUtil.equals(this.Value, obj.Value)) {
/* 146 */       delta.add("Values are different for StringListItem : (" + getDeltaIdentifier(prefix) + ") : source value : (" + this.Value + 
/* 147 */           ") , dest value : (" + obj.Value + ")");
/*     */     }
/*     */   }
/*     */   
/*     */   public String getDeltaIdentifier(String prefix) {
/* 152 */     return String.valueOf(prefix) + " --> " + toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringListItem getThis() {
/* 157 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 162 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void describeXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 167 */     writer.startObject(getClass().getName(), "");
/* 168 */     describePropertiesXML(writer, localizationService);
/* 169 */     writer.endObject();
/*     */   }
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 174 */     writer.writeProperty("Value", "String", null, false);
/* 175 */     writer.writeProperty("Tag", "int", null, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getEmptyPropertyNames(IParameterMandatoryListener mandatoryListener) {
/* 185 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier getParameterIdentifier() {
/* 191 */     return Parameter.getParameterIdentifier(getClass());
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsStringListItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */