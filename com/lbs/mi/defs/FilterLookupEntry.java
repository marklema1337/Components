/*     */ package com.lbs.mi.defs;
/*     */ 
/*     */ import com.lbs.util.JLbsStringListItemEx;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FilterLookupEntry
/*     */   extends ModuleAction
/*     */   implements Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int RES_LISTID = 99000;
/*     */   public static final int FLT_TYP_SIMPLE = 0;
/*     */   public static final int FLT_TYP_MULTIPLE = 1;
/*     */   private int m_Type;
/*     */   private String m_PropMain;
/*     */   private String m_PropDetail;
/*     */   private String m_LookupTerm;
/*     */   private String m_Name;
/*     */   private int m_StringTag;
/*     */   private int m_Id;
/*     */   private String m_OutputAlias;
/*     */   private String m_LookupPropertyName;
/*     */   
/*     */   public FilterLookupEntry(int type, String propMain, String propDetail, String lookupTerm, ModuleAction action) {
/*  29 */     super(action);
/*  30 */     this.m_Type = type;
/*  31 */     this.m_PropMain = propMain;
/*  32 */     this.m_PropDetail = propDetail;
/*  33 */     this.m_Id = action.getActionId();
/*  34 */     this.m_LookupTerm = lookupTerm;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/*  39 */     return super.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getType() {
/*  44 */     return this.m_Type;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(int type) {
/*  49 */     this.m_Type = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPropMain() {
/*  54 */     return this.m_PropMain;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPropMain(String propMain) {
/*  59 */     this.m_PropMain = propMain;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPropDetail() {
/*  64 */     return this.m_PropDetail;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPropDetail(String propDetail) {
/*  69 */     this.m_PropDetail = propDetail;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  74 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/*  79 */     this.m_Name = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  84 */     return this.m_Id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryParamValue(String name, Object value) {
/*  89 */     JLbsStringListItemEx[] items = getQueryParamValues();
/*  90 */     ArrayList<JLbsStringListItemEx> list = new ArrayList<>();
/*  91 */     if (items != null) {
/*     */       
/*  93 */       for (int i = 0; i < items.length; i++) {
/*     */         
/*  95 */         JLbsStringListItemEx jLbsStringListItemEx = items[i];
/*  96 */         if (jLbsStringListItemEx.getValue().equals(name)) {
/*     */           
/*  98 */           jLbsStringListItemEx.setObject(value);
/*     */           return;
/*     */         } 
/*     */       } 
/* 102 */       JLbsStringListItemEx item = new JLbsStringListItemEx(name, 1, value);
/* 103 */       list = convertToArrayList(items);
/* 104 */       list.add(item);
/*     */     }
/*     */     else {
/*     */       
/* 108 */       JLbsStringListItemEx item = new JLbsStringListItemEx(name, 1, value);
/* 109 */       list.add(item);
/*     */     } 
/*     */     
/* 112 */     JLbsStringListItemEx[] strListArr = new JLbsStringListItemEx[list.size()];
/* 113 */     list.toArray(strListArr);
/*     */     
/* 115 */     setQueryParamValues(strListArr);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryVariableValue(String name, Object value) {
/* 120 */     JLbsStringListItemEx[] items = getQueryVariableValues();
/* 121 */     ArrayList<JLbsStringListItemEx> list = new ArrayList<>();
/* 122 */     if (items != null) {
/*     */       
/* 124 */       for (int i = 0; i < items.length; i++) {
/*     */         
/* 126 */         JLbsStringListItemEx jLbsStringListItemEx = items[i];
/* 127 */         if (jLbsStringListItemEx.getValue().equals(name)) {
/*     */           
/* 129 */           jLbsStringListItemEx.setObject(value);
/*     */           return;
/*     */         } 
/*     */       } 
/* 133 */       JLbsStringListItemEx item = new JLbsStringListItemEx(name, 1, value);
/* 134 */       list = convertToArrayList(items);
/* 135 */       list.add(item);
/*     */     }
/*     */     else {
/*     */       
/* 139 */       JLbsStringListItemEx item = new JLbsStringListItemEx(name, 1, value);
/* 140 */       list.add(item);
/*     */     } 
/* 142 */     JLbsStringListItemEx[] strListArr = new JLbsStringListItemEx[list.size()];
/* 143 */     list.toArray(strListArr);
/*     */     
/* 145 */     setQueryVariableValues(strListArr);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParameterValue(String name, Object value) {
/* 150 */     JLbsStringListItemEx[] items = getParamValues();
/* 151 */     ArrayList<JLbsStringListItemEx> list = new ArrayList<>();
/* 152 */     if (items != null) {
/*     */       
/* 154 */       for (int i = 0; i < items.length; i++) {
/*     */         
/* 156 */         JLbsStringListItemEx jLbsStringListItemEx = items[i];
/* 157 */         if (jLbsStringListItemEx.getValue().equals(name)) {
/*     */           
/* 159 */           jLbsStringListItemEx.setObject(value);
/*     */           return;
/*     */         } 
/*     */       } 
/* 163 */       JLbsStringListItemEx item = new JLbsStringListItemEx(name, 1, value);
/* 164 */       list = convertToArrayList(items);
/* 165 */       list.add(item);
/*     */     }
/*     */     else {
/*     */       
/* 169 */       JLbsStringListItemEx item = new JLbsStringListItemEx(name, 1, value);
/* 170 */       list.add(item);
/*     */     } 
/*     */     
/* 173 */     JLbsStringListItemEx[] strListArr = new JLbsStringListItemEx[list.size()];
/* 174 */     list.toArray(strListArr);
/*     */     
/* 176 */     setParamValues(strListArr);
/*     */   }
/*     */ 
/*     */   
/*     */   private ArrayList<JLbsStringListItemEx> convertToArrayList(JLbsStringListItemEx[] items) {
/* 181 */     ArrayList<JLbsStringListItemEx> result = new ArrayList<>();
/* 182 */     if (items != null)
/*     */     {
/* 184 */       for (int i = 0; i < items.length; i++) {
/*     */         
/* 186 */         JLbsStringListItemEx item = items[i];
/* 187 */         result.add(item);
/*     */       } 
/*     */     }
/* 190 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addQueryTerm(String term) {
/* 195 */     String[] termList = getQueryTerms();
/* 196 */     ArrayList<String> result = new ArrayList<>();
/* 197 */     if (termList != null)
/*     */     {
/* 199 */       for (int i = 0; i < termList.length; i++) {
/*     */         
/* 201 */         String item = termList[i];
/* 202 */         result.add(item);
/*     */       } 
/*     */     }
/* 205 */     result.add(term);
/*     */     
/* 207 */     String[] strArr = new String[result.size()];
/* 208 */     result.toArray(strArr);
/*     */     
/* 210 */     setQueryTerms(strArr);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 216 */     StringBuilder toStrBuff = new StringBuilder();
/* 217 */     toStrBuff.append(String.valueOf(getResource()) + " , " + getPropMain());
/* 218 */     if (!JLbsStringUtil.isEmpty(getPropDetail())) {
/* 219 */       toStrBuff.append(" , " + getPropDetail());
/*     */     }
/* 221 */     if (getQueryTerms() != null && (getQueryTerms()).length > 0) {
/*     */       
/* 223 */       toStrBuff.append(" , {");
/*     */       
/* 225 */       for (int i = 0; i < (getQueryTerms()).length; i++) {
/* 226 */         toStrBuff.append(String.valueOf(getQueryTerms()[i]) + " ");
/*     */       }
/*     */       
/* 229 */       toStrBuff.append("}");
/*     */     } 
/* 231 */     if (getQueryParamValues() != null && (getQueryParamValues()).length > 0) {
/*     */       
/* 233 */       toStrBuff.append(" , {");
/*     */       
/* 235 */       for (int i = 0; i < (getQueryParamValues()).length; i++) {
/*     */         
/* 237 */         JLbsStringListItemEx item = getQueryParamValues()[i];
/* 238 */         String name = item.getValue();
/* 239 */         String value = item.getObject().toString();
/* 240 */         toStrBuff.append("(" + name + " " + value + ")");
/*     */       } 
/*     */       
/* 243 */       toStrBuff.append("}");
/*     */     } 
/*     */     
/* 246 */     return toStrBuff.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLookupTerm() {
/* 251 */     return this.m_LookupTerm;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLookupTerm(String lookupTerm) {
/* 256 */     this.m_LookupTerm = lookupTerm;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOutputAlias() {
/* 261 */     return this.m_OutputAlias;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOutputAlias(String outputAlias) {
/* 266 */     this.m_OutputAlias = outputAlias;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLookupPropertyName() {
/* 271 */     return this.m_LookupPropertyName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLookupPropertyName(String lookupPropertyName) {
/* 276 */     this.m_LookupPropertyName = lookupPropertyName;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStringTag() {
/* 281 */     return this.m_StringTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStringTag(int stringTag) {
/* 286 */     this.m_StringTag = stringTag;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mi\defs\FilterLookupEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */