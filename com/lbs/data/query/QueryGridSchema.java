/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.data.query.select.SelectQueryColumn;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.StringItemList;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
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
/*     */ public class QueryGridSchema
/*     */   implements Serializable, Externalizable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected String m_MainTablePhysicalName;
/*     */   protected String m_MainTableAlias;
/*     */   protected QueryGridOrder[] m_Orders;
/*     */   protected QueryGridGlobalOrder[] m_GlobalOrders;
/*  33 */   protected StringItemList m_AggregateColAliasList = new StringItemList();
/*  34 */   protected String m_Test = "";
/*  35 */   protected Hashtable<String, String> m_FieldSizeTypeByAlias = new Hashtable<>();
/*  36 */   protected StringItemList m_ColAliasList = new StringItemList();
/*  37 */   protected StringItemList m_MainTableColAliasList = new StringItemList();
/*     */ 
/*     */   
/*     */   private QueryTableLinks m_QueryTableLinks;
/*     */ 
/*     */   
/*     */   public QueryGridSchema() {}
/*     */ 
/*     */   
/*     */   public QueryGridSchema(QuerySchema qrySchema) {
/*  47 */     this.m_MainTableAlias = qrySchema.getMainQueryTable().getAlias();
/*  48 */     this.m_MainTablePhysicalName = qrySchema.getMainQueryTable().getTablePhysicalName();
/*  49 */     setQueryTableLinks(qrySchema.getTableLinks());
/*     */     
/*  51 */     importGlobalOrders(qrySchema.getGlobalOrders());
/*     */     
/*  53 */     ArrayList<QueryGridOrder> list = new ArrayList();
/*  54 */     importOrders(qrySchema.getMainQueryTable(), list);
/*     */     
/*  56 */     QueryTableLinks links = qrySchema.getTableLinks();
/*     */     
/*  58 */     for (int i = 0; i < links.size(); i++) {
/*     */       
/*  60 */       QueryTableLink link = links.item(i);
/*  61 */       importOrders(link, list);
/*     */     } 
/*     */     
/*  64 */     int cnt = list.size();
/*  65 */     this.m_Orders = new QueryGridOrder[cnt];
/*     */     
/*  67 */     for (int j = 0; j < cnt; j++) {
/*  68 */       this.m_Orders[j] = list.get(j);
/*     */     }
/*  70 */     QueryColumns aggCols = qrySchema.getAggregateQueryColumns();
/*  71 */     collectQueryCOlumns(qrySchema);
/*  72 */     collectMainTableColumns(qrySchema);
/*  73 */     if (aggCols.size() > 0)
/*     */     {
/*  75 */       for (int k = 0; k < aggCols.size(); k++) {
/*     */         
/*  77 */         QueryColumn col = aggCols.item(k);
/*  78 */         if (col instanceof SelectQueryColumn) {
/*  79 */           this.m_AggregateColAliasList.add(((SelectQueryColumn)col).getQueryAlias());
/*     */         }
/*     */       } 
/*     */     }
/*  83 */     if (JLbsConstants.DESKTOP_MODE) {
/*  84 */       prepareFieldSizeTypeList(qrySchema);
/*     */     }
/*     */   }
/*     */   
/*     */   private void collectMainTableColumns(QuerySchema qrySchema) {
/*  89 */     QueryColumns columns = qrySchema.getMainQueryTable().getColumns();
/*  90 */     for (int i = 0; i < columns.size(); i++) {
/*     */       
/*  92 */       QueryColumn col = columns.item(i);
/*  93 */       if (col instanceof SelectQueryColumn) {
/*  94 */         this.m_MainTableColAliasList.add(((SelectQueryColumn)col).getQueryAlias());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void collectQueryCOlumns(QuerySchema qrySchema) {
/* 100 */     QueryColumns columns = qrySchema.getMainQueryTable().getColumns();
/* 101 */     for (int i = 0; i < columns.size(); i++) {
/*     */       
/* 103 */       QueryColumn col = columns.item(i);
/* 104 */       if (col instanceof SelectQueryColumn) {
/* 105 */         this.m_ColAliasList.add(((SelectQueryColumn)col).getQueryAlias());
/*     */       }
/*     */     } 
/* 108 */     QueryTableLinks tableLinks = qrySchema.getTableLinks();
/* 109 */     for (int j = 0; j < tableLinks.size(); j++) {
/*     */       
/* 111 */       QueryTableLink queryTableLink = tableLinks.get(j);
/* 112 */       QueryColumns linkColumns = queryTableLink.getColumns();
/* 113 */       for (int k = 0; k < linkColumns.size(); k++) {
/*     */         
/* 115 */         QueryColumn col = linkColumns.item(k);
/* 116 */         if (col instanceof SelectQueryColumn) {
/* 117 */           this.m_ColAliasList.add(((SelectQueryColumn)col).getQueryAlias());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void prepareFieldSizeTypeList(QuerySchema qrySchema) {
/* 124 */     QueryColumns columns = qrySchema.getMainQueryTable().getColumns();
/* 125 */     this.m_FieldSizeTypeByAlias.clear();
/* 126 */     if (columns != null)
/*     */     {
/* 128 */       for (int i = 0; i < columns.size(); i++) {
/*     */         
/* 130 */         QueryColumn col = columns.item(i);
/* 131 */         if (col != null && col.getField() != null)
/*     */         {
/* 133 */           if (!this.m_FieldSizeTypeByAlias.containsKey(col.getAlias())) {
/* 134 */             this.m_FieldSizeTypeByAlias.put(col.getAlias(), col.getField().getType() + "_" + col.getField().getSize());
/*     */           }
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void importGlobalOrders(QueryGlobalOrders orders) {
/* 142 */     this.m_GlobalOrders = new QueryGridGlobalOrder[orders.size()];
/* 143 */     for (int i = 0; i < this.m_GlobalOrders.length; i++)
/*     */     {
/* 145 */       this.m_GlobalOrders[i] = new QueryGridGlobalOrder(orders.get(i));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void importOrders(QueryTable table, List<QueryGridOrder> list) {
/* 151 */     QueryOrders orders = table.getOrders();
/*     */     
/* 153 */     for (int i = 0; i < orders.size(); i++) {
/*     */       
/* 155 */       QueryGridOrder order = new QueryGridOrder(table.getAlias(), orders.item(i));
/* 156 */       list.add(order);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryGridOrder[] getOrders() {
/* 162 */     return this.m_Orders;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryGridOrder getOrder(String name) {
/* 167 */     for (int i = 0; i < this.m_Orders.length; i++) {
/*     */       
/* 169 */       if (StringUtil.equals(this.m_Orders[i].getName(), name)) {
/* 170 */         return this.m_Orders[i];
/*     */       }
/*     */     } 
/* 173 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryGridOrder getOrder(int index) {
/* 178 */     if (index < 0 || index >= this.m_Orders.length) {
/* 179 */       return null;
/*     */     }
/* 181 */     return this.m_Orders[index];
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryGridGlobalOrder[] getGlobalOrders() {
/* 186 */     return this.m_GlobalOrders;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMainTableAlias() {
/* 191 */     return this.m_MainTableAlias;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryGridGlobalOrder getGlobalOrder(String name) {
/* 196 */     if (StringUtil.isEmpty(name)) {
/* 197 */       return this.m_GlobalOrders[0];
/*     */     }
/* 199 */     for (int i = 0; i < this.m_GlobalOrders.length; i++) {
/*     */       
/* 201 */       if (StringUtil.equals((this.m_GlobalOrders[i]).m_Name, name)) {
/* 202 */         return this.m_GlobalOrders[i];
/*     */       }
/*     */     } 
/* 205 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryGridOrder getTableOrder(String tableAlias, String name) {
/* 210 */     for (int i = 0; i < this.m_Orders.length; i++) {
/*     */       
/* 212 */       if (StringUtil.equals((this.m_Orders[i]).m_Name, name) && StringUtil.equals((this.m_Orders[i]).m_TableAlias, tableAlias)) {
/* 213 */         return this.m_Orders[i];
/*     */       }
/*     */     } 
/* 216 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMainTablePhysicalName() {
/* 221 */     return this.m_MainTablePhysicalName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 226 */     this.m_MainTablePhysicalName = (String)in.readObject();
/* 227 */     this.m_MainTableAlias = (String)in.readObject();
/*     */     
/* 229 */     int length = in.readInt();
/* 230 */     if (length == -1) {
/*     */       
/* 232 */       this.m_Orders = null;
/*     */     }
/*     */     else {
/*     */       
/* 236 */       this.m_Orders = new QueryGridOrder[length];
/* 237 */       for (int i = 0; i < length; i++) {
/*     */         
/* 239 */         this.m_Orders[i] = new QueryGridOrder();
/* 240 */         this.m_Orders[i].readExternal(in);
/*     */       } 
/*     */     } 
/*     */     
/* 244 */     length = in.readInt();
/* 245 */     if (length == -1) {
/*     */       
/* 247 */       this.m_GlobalOrders = null;
/*     */     }
/*     */     else {
/*     */       
/* 251 */       this.m_GlobalOrders = new QueryGridGlobalOrder[length];
/* 252 */       for (int i = 0; i < length; i++) {
/*     */         
/* 254 */         this.m_GlobalOrders[i] = new QueryGridGlobalOrder();
/* 255 */         this.m_GlobalOrders[i].readExternal(in);
/*     */       } 
/*     */     } 
/*     */     
/* 259 */     this.m_AggregateColAliasList.readExternal(in);
/* 260 */     this.m_ColAliasList.readExternal(in);
/*     */     
/* 262 */     this.m_FieldSizeTypeByAlias = (Hashtable<String, String>)in.readObject();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 268 */     out.writeObject(this.m_MainTablePhysicalName);
/* 269 */     out.writeObject(this.m_MainTableAlias);
/*     */     
/* 271 */     if (this.m_Orders == null) {
/*     */       
/* 273 */       out.writeInt(-1);
/*     */     }
/*     */     else {
/*     */       
/* 277 */       out.writeInt(this.m_Orders.length);
/* 278 */       for (int i = 0; i < this.m_Orders.length; i++) {
/* 279 */         this.m_Orders[i].writeExternal(out);
/*     */       }
/*     */     } 
/* 282 */     if (this.m_GlobalOrders == null) {
/*     */       
/* 284 */       out.writeInt(-1);
/*     */     }
/*     */     else {
/*     */       
/* 288 */       out.writeInt(this.m_GlobalOrders.length);
/* 289 */       for (int i = 0; i < this.m_GlobalOrders.length; i++) {
/* 290 */         this.m_GlobalOrders[i].writeExternal(out);
/*     */       }
/*     */     } 
/* 293 */     this.m_AggregateColAliasList.writeExternal(out);
/* 294 */     this.m_ColAliasList.writeExternal(out);
/*     */     
/* 296 */     out.writeObject(this.m_FieldSizeTypeByAlias);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAggregateColumnByAlias(String alias) {
/* 301 */     if (this.m_AggregateColAliasList.size() > 0)
/* 302 */       return (this.m_AggregateColAliasList.indexOf(alias) > -1); 
/* 303 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isColumnByAliasExist(String alias) {
/* 308 */     if (this.m_ColAliasList.size() > 0) {
/* 309 */       return (this.m_ColAliasList.indexOf((alias.contains(".") && alias.lastIndexOf(".") + 1 < alias.length()) ? alias
/* 310 */           .substring(alias.lastIndexOf(".") + 1) : alias) > -1);
/*     */     }
/* 312 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isColumnByAliasExistMainTable(String alias) {
/* 317 */     if (this.m_MainTableColAliasList.size() > 0) {
/* 318 */       return (this.m_MainTableColAliasList.indexOf((alias.contains(".") && alias.lastIndexOf(".") + 1 < alias.length()) ? alias
/* 319 */           .substring(alias.lastIndexOf(".") + 1) : alias) > -1);
/*     */     }
/* 321 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Hashtable<String, String> getFieldSizeTypeByAlias() {
/* 326 */     return this.m_FieldSizeTypeByAlias;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFieldSizeTypeByAlias(Hashtable<String, String> fieldSizeTypeByAlias) {
/* 331 */     this.m_FieldSizeTypeByAlias = fieldSizeTypeByAlias;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryTableLinks getQueryTableLinks() {
/* 336 */     return this.m_QueryTableLinks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryTableLinks(QueryTableLinks queryTableLinks) {
/* 341 */     this.m_QueryTableLinks = queryTableLinks;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryGridSchema.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */