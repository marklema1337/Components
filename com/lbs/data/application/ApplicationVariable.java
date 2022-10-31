/*     */ package com.lbs.data.application;
/*     */ 
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.io.Serializable;
/*     */ import java.text.NumberFormat;
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
/*     */ public class ApplicationVariable
/*     */   implements IApplicationVariableConstants, Serializable
/*     */ {
/*     */   private static final int JUGNU_FIRM_SIZE = 5;
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected String m_Name;
/*     */   protected int m_Type;
/*     */   protected int m_Size;
/*     */   protected int m_Options;
/*     */   protected boolean m_PartOfDBName;
/*     */   protected int m_StringTag;
/*  28 */   protected String m_DefaultValue = null;
/*     */   
/*     */   protected boolean m_TaskVar = false;
/*     */ 
/*     */   
/*     */   public ApplicationVariable() {}
/*     */ 
/*     */   
/*     */   public ApplicationVariable(String name, int type, int size, int options, boolean partOfDBName, int stringTag) {
/*  37 */     this(name, type);
/*  38 */     this.m_Size = size;
/*  39 */     this.m_Options = options;
/*  40 */     this.m_PartOfDBName = partOfDBName;
/*  41 */     this.m_StringTag = stringTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public ApplicationVariable(String name, int type, int size) {
/*  46 */     this(name, type);
/*  47 */     this.m_Size = size;
/*     */   }
/*     */ 
/*     */   
/*     */   public ApplicationVariable(String name, int type) {
/*  52 */     this.m_Name = name;
/*  53 */     this.m_Type = type;
/*     */     
/*  55 */     switch (this.m_Type) {
/*     */       
/*     */       case 1:
/*  58 */         this.m_Options = 1;
/*     */         break;
/*     */     } 
/*     */     
/*  62 */     this.m_Size = 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  67 */     return "$V(" + this.m_Name + ")";
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString(Object value) {
/*  72 */     if (value instanceof String) {
/*  73 */       return (String)value;
/*     */     }
/*  75 */     if (value instanceof Integer) {
/*  76 */       return toString(((Integer)value).intValue());
/*     */     }
/*  78 */     return value.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString(int value) {
/*  83 */     if (this.m_Type == 1)
/*     */     {
/*  85 */       if ((this.m_Options & 0x1) == 1) {
/*     */         
/*  87 */         NumberFormat nf = NumberFormat.getInstance();
/*  88 */         nf.setGroupingUsed(false);
/*  89 */         nf.setMinimumIntegerDigits(getSize());
/*     */         
/*  91 */         return nf.format(value);
/*     */       } 
/*     */     }
/*     */     
/*  95 */     return Integer.toString(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 100 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 105 */     return this.m_Options;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 110 */     if (JLbsConstants.checkAppCloud() && this.m_Name.equals("firm"))
/* 111 */       return 5; 
/* 112 */     return this.m_Size;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getType() {
/* 117 */     return this.m_Type;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPartOfDBName() {
/* 122 */     return this.m_PartOfDBName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPartOfDBName(boolean b) {
/* 127 */     this.m_PartOfDBName = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setName(String string) {
/* 132 */     this.m_Name = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOptions(int i) {
/* 137 */     this.m_Options = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(int i) {
/* 142 */     this.m_Size = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(int i) {
/* 147 */     this.m_Type = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStringTag() {
/* 152 */     return this.m_StringTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStringTag(int i) {
/* 157 */     this.m_StringTag = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDefaultValue(String defaultValue) {
/* 162 */     this.m_DefaultValue = defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDefaultValue() {
/* 167 */     return this.m_DefaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTaskVar(boolean taskVar) {
/* 172 */     this.m_TaskVar = taskVar;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTaskVar() {
/* 177 */     return this.m_TaskVar;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\application\ApplicationVariable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */