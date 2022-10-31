/*     */ package com.lbs.data.expression;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public class QueryItemListBase
/*     */   extends QueryItemBase
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected List<QueryItem> m_List;
/*     */   
/*     */   public QueryItemListBase(QueryItemBase parent) {
/*  25 */     super(parent);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  31 */     if (this.m_List == null)
/*  32 */       return 0; 
/*  33 */     return this.m_List.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(QueryItem item) {
/*  38 */     if (item == null) {
/*     */       return;
/*     */     }
/*  41 */     if (this.m_List == null) {
/*  42 */       this.m_List = new ArrayList<>();
/*     */     }
/*  44 */     this.m_List.add(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(int index, QueryItem item) {
/*  49 */     if (this.m_List == null) {
/*  50 */       this.m_List = new ArrayList<>();
/*     */     }
/*  52 */     this.m_List.set(index, item);
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem get(int index) {
/*  57 */     if (this.m_List == null) {
/*  58 */       return null;
/*     */     }
/*  60 */     return this.m_List.get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem getByType(int type) {
/*  65 */     if (this.m_List == null) {
/*  66 */       return null;
/*     */     }
/*     */     
/*  69 */     List<QueryItem> innerList = this.m_List;
/*  70 */     for (int i = 0; i < innerList.size(); i++) {
/*     */       
/*  72 */       QueryItem item = innerList.get(i);
/*     */       
/*  74 */       if (item.m_Type == type)
/*  75 */         return item; 
/*     */     } 
/*  77 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int indexOf(QueryItem item) {
/*  82 */     if (item == null) {
/*  83 */       return -1;
/*     */     }
/*  85 */     if (this.m_List == null) {
/*  86 */       return -1;
/*     */     }
/*     */     
/*  89 */     List<QueryItem> innerList = this.m_List;
/*  90 */     for (int i = 0; i < innerList.size(); i++) {
/*     */       
/*  92 */       QueryItem curItem = innerList.get(i);
/*  93 */       if (curItem.equals(item)) {
/*  94 */         return i;
/*     */       }
/*     */     } 
/*  97 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(QueryItem item) {
/* 102 */     return (indexOf(item) != -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void merge(QueryItemList list, boolean allowDuplicates, boolean copyItem) {
/* 107 */     if (this.m_List == null) {
/* 108 */       this.m_List = new ArrayList<>();
/*     */     }
/*     */     
/* 111 */     List<QueryItem> innerList = this.m_List;
/*     */     
/* 113 */     if (list != null)
/*     */     {
/* 115 */       for (int i = 0; i < list.size(); i++) {
/*     */         
/* 117 */         QueryItem item = list.get(i);
/* 118 */         boolean timestampFlag = false;
/* 119 */         if (item != null) {
/*     */           
/* 121 */           if (item.getSubItems() != null)
/*     */           {
/*     */             
/* 124 */             for (int j = 0; j < (item.getSubItems()).m_List.size(); j++) {
/*     */               
/* 126 */               if (((QueryItem)(item.getSubItems()).m_List.get(j)).getValue() instanceof String) {
/*     */                 
/* 128 */                 String val = (String)((QueryItem)(item.getSubItems()).m_List.get(j)).getValue();
/* 129 */                 if (val.equalsIgnoreCase("RV"))
/* 130 */                   timestampFlag = true; 
/*     */               } 
/*     */             } 
/*     */           }
/* 134 */           if (!timestampFlag)
/*     */           {
/*     */             
/* 137 */             if (allowDuplicates || !contains(item)) {
/*     */               
/* 139 */               if (copyItem)
/* 140 */                 item = (QueryItem)item.clone(); 
/* 141 */               if (item != null) {
/* 142 */                 innerList.add(item);
/*     */               }
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 152 */     QueryItemListBase cloneList = new QueryItemListBase(null);
/* 153 */     copy(cloneList, (IQueryItemCopy)null);
/* 154 */     return cloneList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void copy(QueryItemBase dst, IQueryItemCopy copyHandler) {
/* 162 */     super.copy(dst, copyHandler);
/*     */     
/* 164 */     if (!(dst instanceof QueryItemListBase)) {
/*     */       return;
/*     */     }
/* 167 */     if (this.m_List == null) {
/* 168 */       this.m_List = new ArrayList<>();
/*     */     }
/* 170 */     QueryItemListBase dstList = (QueryItemListBase)dst;
/*     */ 
/*     */ 
/*     */     
/* 174 */     List<QueryItem> innerList = this.m_List;
/* 175 */     for (int i = 0; i < innerList.size(); i++) {
/*     */       QueryItem subItem;
/* 177 */       if (innerList.get(i) == null) {
/* 178 */         subItem = new QueryItem();
/*     */       } else {
/* 180 */         subItem = innerList.get(i);
/* 181 */       }  QueryItem newItem = new QueryItem();
/*     */       
/* 183 */       dstList.add(newItem);
/* 184 */       subItem.copy(newItem, copyHandler);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 190 */     if (this.m_List != null) {
/* 191 */       this.m_List.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   public static QueryItemList subtutiateTimestampColumnsOnNames(QueryItemList list) {
/* 196 */     if (list == null) {
/* 197 */       return null;
/*     */     }
/* 199 */     QueryItemList innerList = (QueryItemList)list.clone();
/* 200 */     Object timestampCol = null;
/* 201 */     innerList.clear();
/* 202 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 204 */       QueryItem item = list.get(i);
/* 205 */       if (item != null) {
/*     */         
/* 207 */         if (item.getSubItems() != null)
/*     */         {
/* 209 */           for (int j = 0; j < (item.getSubItems()).m_List.size(); j++) {
/*     */             
/* 211 */             if (((QueryItem)(item.getSubItems()).m_List.get(j)).getValue() instanceof String) {
/*     */               
/* 213 */               String val = (String)((QueryItem)(item.getSubItems()).m_List.get(j)).getValue();
/* 214 */               if (val.equalsIgnoreCase("RV"))
/* 215 */                 timestampCol = (item.getSubItems()).m_List.get(j); 
/*     */             } 
/*     */           } 
/*     */         }
/* 219 */         if (timestampCol == null)
/*     */         {
/* 221 */           innerList.add(item); } 
/*     */       } 
/* 223 */     }  innerList.m_Parent = list.getParent();
/* 224 */     if (timestampCol != null) {
/* 225 */       innerList.m_List.remove(timestampCol);
/*     */     }
/* 227 */     return innerList;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void removeTimestampColumnsOnNames(QueryItemList list) {
/* 232 */     if (list == null) {
/*     */       return;
/*     */     }
/* 235 */     Object timestampCol = null;
/* 236 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 238 */       QueryItem item = list.get(i);
/* 239 */       if (item != null) {
/*     */         
/* 241 */         if (item.getSubItems() != null)
/*     */         {
/* 243 */           for (int j = 0; j < (item.getSubItems()).m_List.size(); j++) {
/*     */             
/* 245 */             if (((QueryItem)(item.getSubItems()).m_List.get(j)).getValue() instanceof String) {
/*     */               
/* 247 */               String val = (String)((QueryItem)(item.getSubItems()).m_List.get(j)).getValue();
/* 248 */               if (val.equalsIgnoreCase("RV")) {
/*     */                 
/* 250 */                 timestampCol = (item.getSubItems()).m_List.get(j);
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/* 256 */         if (timestampCol != null)
/*     */           break; 
/*     */       } 
/* 259 */     }  if (timestampCol != null) {
/* 260 */       list.m_List.remove(timestampCol);
/*     */     }
/*     */   }
/*     */   
/*     */   public static QueryItemList subtutiateTimestampColumnsOnValues(QueryItemList list) {
/* 265 */     if (list == null) {
/* 266 */       return null;
/*     */     }
/* 268 */     QueryItemList innerList = (QueryItemList)list.clone();
/* 269 */     Object timestampCol = null;
/* 270 */     innerList.clear();
/* 271 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 273 */       QueryItem item = list.get(i);
/* 274 */       if (item != null) {
/*     */         
/* 276 */         String val = (String)item.getValue();
/* 277 */         if (val != null && val.equals("RV"))
/* 278 */           timestampCol = item; 
/*     */       } 
/* 280 */       if (timestampCol == null)
/*     */       {
/* 282 */         if (item != null)
/* 283 */           innerList.add(item);  } 
/*     */     } 
/* 285 */     innerList.m_Parent = list.getParent();
/* 286 */     if (timestampCol != null) {
/* 287 */       innerList.m_List.remove(timestampCol);
/*     */     }
/* 289 */     return innerList;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void removeTimestampColumnsOnValues(QueryItemList list) {
/* 294 */     if (list == null) {
/*     */       return;
/*     */     }
/* 297 */     Object timestampCol = null;
/* 298 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 300 */       QueryItem item = list.get(i);
/* 301 */       if (item != null) {
/*     */         
/* 303 */         String val = (String)item.getValue();
/* 304 */         if (val != null && val.equals("RV"))
/* 305 */           timestampCol = item; 
/*     */       } 
/* 307 */       if (timestampCol != null)
/*     */         break; 
/*     */     } 
/* 310 */     if (timestampCol != null)
/* 311 */       list.m_List.remove(timestampCol); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\expression\QueryItemListBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */