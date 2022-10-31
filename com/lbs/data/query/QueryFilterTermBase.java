/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.data.objects.ILbsRttiCachable;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.util.ObjectUtil;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
/*     */ import java.util.GregorianCalendar;
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
/*     */ public class QueryFilterTermBase
/*     */   implements Serializable, Cloneable, Externalizable, ILbsRttiCachable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int SEARCH_NEXT_OR_EQUAL = 1;
/*     */   public static final int SEARCH_NEXT = 2;
/*     */   public static final int SEARCH_LIKE = 3;
/*     */   public static final int OP_IN = 4;
/*     */   public static final int OP_EQ = 5;
/*     */   public static final int SEARCH_PREV_OR_EQUAL = 6;
/*     */   public static final int SEARCH_PREV = 7;
/*     */   public static final int OP_FILT_EXPRESSION = 8;
/*     */   public static final int OP_NEQ = 9;
/*     */   public static final int SEARCH_NOT_LIKE = 10;
/*     */   public static final int OP_NOT_IN = 11;
/*     */   public static final int OP_AND = 101;
/*     */   public static final int OP_OR = 102;
/*     */   public static final int FILTER_RESTRICT = 1;
/*     */   public static final int FILTER_INITIAL = 2;
/*     */   public static final int FILTER_LKP_SELECT = 3;
/*     */   public static final int OWNER_FILTER_GRID = 1;
/*     */   public static final int OWNER_SEARCH_ROW = 2;
/*     */   public static final int OWNER_QUICK_SEARCH = 3;
/*     */   protected Object m_SearchValue;
/*     */   protected int m_Operation;
/*     */   protected int m_WhereConnector;
/*     */   protected int m_FilterType;
/*     */   protected int m_OwnerType;
/*     */   protected boolean m_HasInlinePercent = false;
/*     */   
/*     */   public int getOperation() {
/*  61 */     return this.m_Operation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperation(int operation) {
/*  66 */     boolean makeLike = (this.m_Operation != operation && operation == 3);
/*  67 */     if (makeLike)
/*     */     {
/*  69 */       if (this.m_SearchValue != null)
/*  70 */         if (this.m_SearchValue instanceof String) {
/*     */           
/*  72 */           if (!hasLikeConstitute((String)this.m_SearchValue)) {
/*  73 */             this.m_SearchValue += "%";
/*     */           }
/*     */         } else {
/*     */           return;
/*     */         }  
/*     */     }
/*  79 */     this.m_Operation = operation;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getSearchValue() {
/*  84 */     return this.m_SearchValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSearchValue(Object searchValue) {
/*  89 */     boolean[] restrictive = new boolean[1];
/*     */     
/*  91 */     if (searchValue instanceof String && (this.m_Operation == 4 || this.m_Operation == 11)) {
/*     */       
/*  93 */       boolean ok = true;
/*  94 */       String[] items = ((String)searchValue).split(",");
/*  95 */       int[] results = new int[items.length];
/*  96 */       for (int i = 0; i < items.length; i++) {
/*     */ 
/*     */         
/*     */         try {
/* 100 */           results[i] = Integer.parseInt(items[i]);
/*     */         }
/* 102 */         catch (NumberFormatException nfe) {
/*     */           
/* 104 */           ok = false;
/*     */           break;
/*     */         } 
/*     */       } 
/* 108 */       if (ok) {
/* 109 */         searchValue = results;
/*     */       }
/*     */     } 
/* 112 */     if (searchValue instanceof String) {
/*     */       
/* 114 */       this.m_HasInlinePercent = (((String)searchValue).indexOf('%') >= 0);
/* 115 */       searchValue = getSearchText(null, (String)searchValue, restrictive);
/* 116 */       if (restrictive[0] && this.m_Operation != 3) {
/* 117 */         this.m_Operation = 3;
/*     */       }
/* 119 */     } else if (searchValue != null && searchValue.getClass().isArray()) {
/* 120 */       this.m_Operation = 4;
/* 121 */     } else if (searchValue instanceof QueryFilterTree) {
/* 122 */       this.m_Operation = 8;
/*     */     } 
/* 124 */     this.m_SearchValue = searchValue;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object getSearchText(ILbsCultureInfo culture, String keyText, boolean[] restrictive) {
/* 129 */     restrictive[0] = false;
/* 130 */     int x = keyText.indexOf("*");
/* 131 */     while (x >= 0) {
/*     */       
/* 133 */       keyText = keyText.replace('*', '%');
/* 134 */       x = keyText.indexOf("*");
/*     */     } 
/* 136 */     restrictive[0] = hasLikeConstitute(keyText);
/* 137 */     x = keyText.indexOf("?");
/* 138 */     while (x >= 0) {
/*     */       
/* 140 */       keyText = keyText.replace('?', '_');
/* 141 */       x = keyText.indexOf("?");
/*     */     } 
/*     */     
/* 144 */     Object srcParam = keyText;
/* 145 */     if (keyText.length() == 10 && isSeparator(keyText.charAt(2)) && isSeparator(keyText.charAt(5))) {
/*     */       
/* 147 */       srcParam = parseToDate(culture, keyText);
/* 148 */       if (srcParam instanceof java.util.Calendar) {
/* 149 */         restrictive[0] = false;
/*     */       }
/*     */     } 
/* 152 */     return srcParam;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hasLikeConstitute(String keyText) {
/* 157 */     return (keyText.indexOf("%") >= 0 || keyText.indexOf("?") >= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isSeparator(char c) {
/* 162 */     return (c == '.' || c == '/' || c == '-');
/*     */   }
/*     */ 
/*     */   
/*     */   private Object parseToDate(ILbsCultureInfo culture, String keyText) {
/* 167 */     int par1 = strToInt(keyText.substring(0, 2));
/* 168 */     int par2 = strToInt(keyText.substring(3, 5));
/* 169 */     int year = strToInt(keyText.substring(6));
/*     */     
/* 171 */     if (par1 > 0 && par1 <= 31 && par2 > 0 && par2 <= 12 && year >= 1900) {
/*     */       
/* 173 */       int day = par1;
/* 174 */       int mon = par2 - 1;
/*     */       
/* 176 */       if (culture != null) {
/*     */         
/* 178 */         String dateFormat = culture.getDefaultDateFormat();
/* 179 */         if (dateFormat.indexOf("M") < dateFormat.indexOf("d")) {
/*     */           
/* 181 */           day = par2;
/* 182 */           mon = par1;
/*     */         } 
/*     */       } 
/* 185 */       return new GregorianCalendar(year, mon, day);
/*     */     } 
/*     */     
/* 188 */     return keyText;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int strToInt(String string) {
/*     */     try {
/* 195 */       return Integer.parseInt(string);
/*     */     }
/* 197 */     catch (Exception ex) {
/*     */       
/* 199 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 205 */     Object obj = super.clone();
/*     */     
/* 207 */     ObjectUtil.deepCopy(this, obj, QueryFilterTermBase.class);
/*     */     
/* 209 */     return obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWhereConnector(int whereConnector) {
/* 214 */     this.m_WhereConnector = whereConnector;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWhereConnector() {
/* 219 */     return this.m_WhereConnector;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFilterType(int filterType) {
/* 224 */     this.m_FilterType = filterType;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFilterType() {
/* 229 */     return this.m_FilterType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwnerType(int ownerType) {
/* 234 */     this.m_OwnerType = ownerType;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOwnerType() {
/* 239 */     return this.m_OwnerType;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeRemoved(int ownerType) {
/* 244 */     if ((this.m_OwnerType == 0 || this.m_OwnerType == ownerType) && (this.m_FilterType != 1 || this instanceof QueryFilterTermTree))
/*     */     {
/* 246 */       return true; } 
/* 247 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 252 */     this.m_SearchValue = in.readObject();
/* 253 */     this.m_Operation = in.readInt();
/* 254 */     this.m_WhereConnector = in.readInt();
/* 255 */     this.m_FilterType = in.readInt();
/* 256 */     this.m_OwnerType = in.readInt();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 261 */     out.writeObject(this.m_SearchValue);
/* 262 */     out.writeInt(this.m_Operation);
/* 263 */     out.writeInt(this.m_WhereConnector);
/* 264 */     out.writeInt(this.m_FilterType);
/* 265 */     out.writeInt(this.m_OwnerType);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryFilterTermBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */