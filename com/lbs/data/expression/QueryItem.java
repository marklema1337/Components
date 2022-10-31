/*     */ package com.lbs.data.expression;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.util.ObjectUtil;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QueryItem
/*     */   extends QueryItemBase
/*     */   implements IQueryItem, Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  20 */   protected QueryItemList m_SubItems = null;
/*  21 */   protected Object m_Value = null;
/*  22 */   protected int m_Type = 0;
/*     */   
/*     */   protected Object m_Data;
/*     */   private boolean m_Collatable;
/*     */   
/*     */   public QueryItem() {
/*  28 */     super(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem(String value) {
/*  33 */     super(null);
/*  34 */     this.m_Value = value;
/*  35 */     this.m_Type = 0;
/*  36 */     this.m_SubItems = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem(int type, Object value, boolean createList) {
/*  41 */     super(null);
/*  42 */     this.m_Type = type;
/*  43 */     this.m_Value = value;
/*     */     
/*  45 */     if (createList) {
/*  46 */       this.m_SubItems = new QueryItemList(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public QueryItem(int type, Object value) {
/*  51 */     super(null);
/*  52 */     this.m_Type = type;
/*  53 */     this.m_Value = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem(int type, QueryItem lItem, QueryItem rItem) {
/*  58 */     super(null);
/*  59 */     this.m_Type = type;
/*     */     
/*  61 */     this.m_SubItems = new QueryItemList(this);
/*     */     
/*  63 */     if (lItem != null) {
/*  64 */       this.m_SubItems.add(lItem);
/*     */     }
/*  66 */     if (rItem != null) {
/*  67 */       this.m_SubItems.add(rItem);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addSubItem(QueryItem item) {
/*  72 */     addSubItem(item, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addSubItem(QueryItem item, boolean copy) {
/*  77 */     if (this.m_SubItems == null) {
/*  78 */       this.m_SubItems = new QueryItemList(this);
/*     */     }
/*  80 */     if (copy) {
/*  81 */       item = (QueryItem)item.clone();
/*     */     }
/*  83 */     this.m_SubItems.add(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public void replaceSubItem(QueryItem item, QueryItem newItem) {
/*  88 */     replaceSubItem(item, newItem, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void replaceSubItem(QueryItem item, QueryItem newItem, boolean copyItem) {
/*  94 */     for (int i = 0; i < this.m_SubItems.size(); i++) {
/*     */       
/*  96 */       QueryItem subItem = this.m_SubItems.get(i);
/*     */       
/*  98 */       if (subItem == item) {
/*     */         
/* 100 */         if (copyItem) {
/* 101 */           newItem = (QueryItem)newItem.clone();
/*     */         }
/* 103 */         this.m_SubItems.m_List.set(i, newItem);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void replaceSubItems(QueryItem item, QueryItem newItem) {
/* 111 */     replaceSubItems(item, newItem, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void replaceSubItems(QueryItem item, QueryItem newItem, boolean copyItem) {
/* 117 */     if (this.m_SubItems != null) {
/* 118 */       for (int i = 0; i < this.m_SubItems.size(); i++) {
/*     */         
/* 120 */         QueryItem subItem = this.m_SubItems.get(i);
/*     */         
/* 122 */         if (subItem.m_Value != null)
/*     */         {
/* 124 */           if (subItem.m_Type == item.m_Type && subItem.m_Value == item.m_Value) {
/*     */             
/* 126 */             if (copyItem) {
/* 127 */               newItem = (QueryItem)newItem.clone();
/*     */             }
/* 129 */             this.m_SubItems.m_List.set(i, newItem);
/*     */             return;
/*     */           } 
/*     */         }
/* 133 */         subItem.replaceSubItems(item, newItem, copyItem);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 142 */     if (obj instanceof QueryItem) {
/*     */       
/* 144 */       QueryItem item = (QueryItem)obj;
/*     */       
/* 146 */       if (item.m_Type != this.m_Type) {
/* 147 */         return false;
/*     */       }
/* 149 */       if (item.m_Value != null && this.m_Value != null) {
/*     */         
/* 151 */         if (!item.m_Value.equals(this.m_Value)) {
/* 152 */           return false;
/*     */         }
/* 154 */       } else if (item.m_Value != null || this.m_Value != null) {
/*     */         
/* 156 */         return false;
/*     */       } 
/*     */       
/* 159 */       if (item.m_SubItems != null && this.m_SubItems != null) {
/*     */         
/* 161 */         if (item.m_SubItems.size() != this.m_SubItems.size()) {
/* 162 */           return false;
/*     */         }
/* 164 */         int cnt = this.m_SubItems.size();
/*     */         
/* 166 */         for (int i = 0; i < cnt; i++) {
/*     */           
/* 168 */           QueryItem item1 = item.m_SubItems.get(i);
/* 169 */           QueryItem item2 = this.m_SubItems.get(i);
/*     */           
/* 171 */           if (!item1.equals(item2)) {
/* 172 */             return false;
/*     */           }
/*     */         } 
/* 175 */       } else if (item.m_SubItems != null || this.m_SubItems != null) {
/*     */         
/* 177 */         return false;
/*     */       } 
/*     */       
/* 180 */       return true;
/*     */     } 
/*     */     
/* 183 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 189 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*     */     try {
/* 195 */       IQueryItemGenerator itemGen = new QueryItemGenerator();
/* 196 */       return itemGen.generate(itemGen, this, null);
/*     */     }
/* 198 */     catch (Exception e) {
/*     */       
/* 200 */       LbsConsole.getLogger("Data.Client.QueryItem").error(null, e);
/*     */       
/* 202 */       return "(exception)";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryItemList getSubItems() {
/* 212 */     return this.m_SubItems;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 221 */     return this.m_Type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 230 */     return this.m_Value;
/*     */   }
/*     */ 
/*     */   
/*     */   public void copy(QueryItemBase dst, IQueryItemCopy copyHandler) {
/* 235 */     super.copy(dst, copyHandler);
/*     */     
/* 237 */     if (!(dst instanceof QueryItem)) {
/*     */       return;
/*     */     }
/* 240 */     QueryItem dstItem = (QueryItem)dst;
/*     */     
/* 242 */     if (copyHandler != null)
/*     */     {
/* 244 */       if (copyHandler.copy(this, dstItem))
/*     */         return; 
/*     */     }
/* 247 */     dstItem
/*     */       
/* 249 */       .m_Value = (this.m_Type == 101) ? this.m_Value : ObjectUtil.createCopy(this.m_Value);
/* 250 */     dstItem.m_Type = this.m_Type;
/* 251 */     dstItem.m_Data = this.m_Data;
/*     */     
/* 253 */     if (this.m_SubItems != null) {
/*     */       
/* 255 */       dstItem.m_SubItems = new QueryItemList(dstItem);
/* 256 */       this.m_SubItems.copy(dstItem.m_SubItems, copyHandler);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void visit(IQueryItemVisitor visitor) {
/* 262 */     boolean ok = visitor.visit(this);
/*     */     
/* 264 */     if (!ok) {
/*     */       return;
/*     */     }
/* 267 */     if (this.m_Data instanceof QueryItem) {
/* 268 */       ((QueryItem)this.m_Data).visit(visitor);
/*     */     }
/* 270 */     if (this.m_Value instanceof QueryItem) {
/* 271 */       ((QueryItem)this.m_Value).visit(visitor);
/*     */     }
/* 273 */     if (!ok || this.m_SubItems == null) {
/*     */       return;
/*     */     }
/*     */     
/* 277 */     for (int i = 0; i < this.m_SubItems.size(); i++) {
/*     */       
/* 279 */       QueryItem subItem = this.m_SubItems.get(i);
/* 280 */       if (subItem != null) {
/* 281 */         subItem.visit(visitor);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static QueryItemList subtutiateTimestampColumns(QueryItemList list, String method) {
/* 287 */     switch (method) {
/*     */       
/*     */       case "names":
/* 290 */         return QueryItemListBase.subtutiateTimestampColumnsOnNames(list);
/*     */       case "values":
/* 292 */         return QueryItemListBase.subtutiateTimestampColumnsOnValues(list);
/*     */     } 
/* 294 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removeTimestampColumns(QueryItemList list, String method) {
/* 300 */     switch (method) {
/*     */       
/*     */       case "names":
/* 303 */         QueryItemListBase.removeTimestampColumnsOnNames(list);
/*     */         return;
/*     */       case "values":
/* 306 */         QueryItemListBase.removeTimestampColumnsOnValues(list);
/*     */         return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mergeSubItems(QueryItem item, boolean allowDuplicates, boolean copyItem) {
/* 315 */     this.m_SubItems.merge(item.m_SubItems, allowDuplicates, copyItem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 323 */     QueryItem cloneItem = new QueryItem();
/* 324 */     copy(cloneItem, null);
/* 325 */     return cloneItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem getParentItem() {
/* 330 */     QueryItemBase parent = getParent();
/*     */     
/* 332 */     if (parent == null) {
/* 333 */       return null;
/*     */     }
/* 335 */     if (parent instanceof QueryItem) {
/* 336 */       return (QueryItem)parent;
/*     */     }
/* 338 */     if (parent instanceof QueryItemList) {
/* 339 */       return (QueryItem)parent.getParent();
/*     */     }
/* 341 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(Object object) {
/* 346 */     this.m_Value = object;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(int i) {
/* 351 */     this.m_Type = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void replaceItemValue(QueryItem item, Object value) {
/* 356 */     if (this.m_SubItems == null || this.m_SubItems.size() == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 360 */     for (int i = 0; i < this.m_SubItems.size(); i++) {
/*     */       
/* 362 */       QueryItem subItem = this.m_SubItems.get(i);
/*     */       
/* 364 */       if (subItem.m_Value != null) {
/*     */         
/* 366 */         if (subItem.m_Type == item.m_Type && subItem.m_Value == item.m_Value) {
/* 367 */           subItem.m_Value = value;
/*     */         }
/*     */       } else {
/*     */         
/* 371 */         subItem.replaceItemValue(item, value);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void createSubItems() {
/* 378 */     if (this.m_SubItems == null) {
/* 379 */       this.m_SubItems = new QueryItemList(this);
/*     */     }
/*     */   }
/*     */   
/*     */   private void negateSubItems(QueryItem item) {
/* 384 */     item.createSubItems();
/* 385 */     for (int i = 0; i < this.m_SubItems.size(); i++) {
/* 386 */       item.m_SubItems.add(this.m_SubItems.get(i).negate());
/*     */     }
/*     */   }
/*     */   
/*     */   private void cloneSubItems(QueryItem item) {
/* 391 */     item.createSubItems();
/* 392 */     for (int i = 0; i < this.m_SubItems.size(); i++) {
/* 393 */       item.m_SubItems.add((QueryItem)this.m_SubItems.get(i).clone());
/*     */     }
/*     */   }
/*     */   
/*     */   public QueryItem negate() {
/* 398 */     QueryItem item = new QueryItem();
/*     */     
/* 400 */     switch (this.m_Type)
/*     */     
/*     */     { case 70:
/* 403 */         item.m_Type = 71;
/* 404 */         negateSubItems(item);
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
/* 453 */         return item;case 71: item.m_Type = 70; negateSubItems(item); return item;case 72: return this.m_SubItems.get(0);case 40: item.m_Type = 45; cloneSubItems(item); return item;case 45: item.m_Type = 40; cloneSubItems(item); return item;case 44: item.m_Type = 41; cloneSubItems(item); return item;case 43: item.m_Type = 42; cloneSubItems(item); return item;case 42: item.m_Type = 43; cloneSubItems(item); return item;case 41: item.m_Type = 44; cloneSubItems(item); return item; }  item.m_Type = 72; item.createSubItems(); item.addSubItem(this, true); return item;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getData() {
/* 458 */     return this.m_Data;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setData(Object data) {
/* 463 */     this.m_Data = data;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCollatable(boolean collatable) {
/* 468 */     this.m_Collatable = collatable;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCollatable() {
/* 473 */     return this.m_Collatable;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\expression\QueryItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */