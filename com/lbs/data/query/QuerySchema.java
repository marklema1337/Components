/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.data.IStringListDescription;
/*     */ import com.lbs.data.database.DBField;
/*     */ import com.lbs.data.database.DBTable;
/*     */ import com.lbs.data.expression.QueryItem;
/*     */ import com.lbs.data.objects.BusinessSchema;
/*     */ import com.lbs.data.query.select.SelectQueryColumn;
/*     */ import com.lbs.util.SetUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.Serializable;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
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
/*     */ public class QuerySchema
/*     */   extends BusinessSchema
/*     */   implements Serializable, IStringListDescription
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int QUERY_COMMON = 0;
/*     */   public static final int QUERY_SELECT = 1;
/*     */   public static final int QUERY_UPDATE = 2;
/*     */   public static final int QUERY_DELETE = 3;
/*     */   public static final int QUERY_INSERT = 4;
/*     */   public static final int OPTION_NONE = 0;
/*     */   public static final int OPTION_STRICT_TABLES = 1;
/*     */   public static final int OPTION_STRICT_COLUMNS = 2;
/*  52 */   protected int m_QueryType = 1;
/*     */   
/*     */   protected QueryTable m_MainQueryTable;
/*     */   
/*     */   protected QueryTableLinks m_TableLinks;
/*     */   
/*     */   protected QueryTerms m_Terms;
/*     */   protected QueryParameters m_Parameters;
/*     */   protected QueryTerms m_LookupTerms;
/*     */   protected QueryGroup m_Group;
/*     */   protected List<QueryUnion> m_Unions;
/*     */   protected String m_WhereClause;
/*     */   protected boolean m_HasAggregates = false;
/*     */   protected QueryItem m_WhereExpression;
/*     */   protected boolean m_Navigation = true;
/*  67 */   protected int m_ListID = -1;
/*     */   
/*     */   protected int m_StringTag;
/*     */   protected String m_Description;
/*     */   protected boolean m_Distinct;
/*     */   protected int m_Options;
/*     */   protected boolean m_Simple;
/*     */   protected boolean m_Customized = false;
/*     */   protected boolean m_GeneratedLinksAdded = false;
/*  76 */   protected Hashtable<Integer, WeakReference<IQueryTableLinkConsumer>> m_Consumers = new Hashtable<>();
/*     */   
/*     */   protected QueryGlobalOrders m_GlobalOrders;
/*  79 */   private static final transient LbsConsole ms_Console = LbsConsole.getLogger("Data.Client.QuerySchema");
/*     */   
/*     */   protected String m_FileName;
/*     */   
/*     */   protected String m_InhQueryName;
/*     */ 
/*     */   
/*     */   public QuerySchema() {
/*  87 */     this.m_MainQueryTable = new QueryTable();
/*     */     
/*  89 */     this.m_Terms = new QueryTerms();
/*  90 */     this.m_LookupTerms = new QueryTerms();
/*  91 */     this.m_TableLinks = new QueryTableLinks();
/*  92 */     this.m_Parameters = new QueryParameters();
/*  93 */     this.m_Group = new QueryGroup();
/*  94 */     this.m_GlobalOrders = new QueryGlobalOrders();
/*  95 */     this.m_Unions = new ArrayList<>();
/*  96 */     this.m_Simple = true;
/*     */     
/*  98 */     this.m_Options = 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryTable getMainQueryTable() {
/* 106 */     return this.m_MainQueryTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMainQueryTable(QueryTable table) {
/* 111 */     this.m_MainQueryTable = table;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryTerms getTerms() {
/* 119 */     return this.m_Terms;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryTableLinks getTableLinks() {
/* 127 */     return this.m_TableLinks;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryParameters getParameters() {
/* 135 */     return this.m_Parameters;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<QueryUnion> getUnions() {
/* 140 */     return this.m_Unions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWhereClause() {
/* 148 */     return this.m_WhereClause;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWhereClause(String whereClause) {
/* 156 */     this.m_WhereClause = whereClause;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryColumn getColumn(String alias) {
/* 164 */     int dotIdx = (alias == null) ? -1 : alias.indexOf('.');
/* 165 */     if (dotIdx > 0) {
/*     */       
/* 167 */       String linkAlias = alias.substring(0, dotIdx);
/* 168 */       String colAlias = alias.substring(dotIdx + 1);
/* 169 */       if (StringUtil.equals(linkAlias, this.m_MainQueryTable.getAlias())) {
/*     */         
/* 171 */         QueryColumn queryColumn = this.m_MainQueryTable.m_Columns.findByAlias(colAlias);
/* 172 */         if (queryColumn != null)
/* 173 */           return queryColumn; 
/*     */       } 
/* 175 */       QueryTableLink link = this.m_TableLinks.find(linkAlias);
/* 176 */       if (link != null) {
/*     */         
/* 178 */         QueryColumn queryColumn = link.m_Columns.findByAlias(colAlias);
/* 179 */         if (queryColumn != null)
/* 180 */           return queryColumn; 
/*     */       } 
/*     */     } 
/* 183 */     QueryColumn column = this.m_MainQueryTable.m_Columns.findByAlias(alias);
/* 184 */     if (column != null) {
/* 185 */       return column;
/*     */     }
/* 187 */     for (int i = 0; i < this.m_TableLinks.size(); i++) {
/*     */       
/* 189 */       column = (this.m_TableLinks.item(i)).m_Columns.findByAlias(alias);
/* 190 */       if (column != null) {
/* 191 */         return column;
/*     */       }
/*     */     } 
/* 194 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryColumn getColumn(DBField field) {
/* 199 */     QueryColumn column = this.m_MainQueryTable.m_Columns.find(field);
/* 200 */     if (column != null) {
/* 201 */       return column;
/*     */     }
/* 203 */     for (int i = 0; i < this.m_TableLinks.size(); i++) {
/*     */       
/* 205 */       column = (this.m_TableLinks.item(i)).m_Columns.find(field);
/* 206 */       if (column != null) {
/* 207 */         return column;
/*     */       }
/*     */     } 
/* 210 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryTable getTableByName(String name) {
/* 215 */     DBTable dbTable = this.m_MainQueryTable.getTable();
/* 216 */     if (dbTable != null && dbTable.getName().equals(name)) {
/* 217 */       return this.m_MainQueryTable;
/*     */     }
/*     */     
/* 220 */     for (int i = 0; i < this.m_TableLinks.size(); i++) {
/*     */       
/* 222 */       QueryTable table = this.m_TableLinks.item(i);
/*     */       
/* 224 */       dbTable = table.m_Table;
/* 225 */       if (dbTable != null && dbTable.getName().equals(name)) {
/* 226 */         return table;
/*     */       }
/*     */     } 
/* 229 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryTable getTableByPhysicalName(String name) {
/* 234 */     DBTable dbTable = this.m_MainQueryTable.getTable();
/* 235 */     if (dbTable != null && dbTable.getPhysicalName().equals(name)) {
/* 236 */       return this.m_MainQueryTable;
/*     */     }
/*     */     
/* 239 */     for (int i = 0; i < this.m_TableLinks.size(); i++) {
/*     */       
/* 241 */       QueryTable table = this.m_TableLinks.item(i);
/*     */       
/* 243 */       dbTable = table.m_Table;
/* 244 */       if (dbTable != null && dbTable.getPhysicalName().equals(name)) {
/* 245 */         return table;
/*     */       }
/*     */     } 
/* 248 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryTable getTableByAlias(String alias) {
/* 253 */     if (this.m_MainQueryTable.m_Alias.equals(alias)) {
/* 254 */       return this.m_MainQueryTable;
/*     */     }
/*     */     
/* 257 */     for (int i = 0; i < this.m_TableLinks.size(); i++) {
/*     */       
/* 259 */       QueryTableLink table = this.m_TableLinks.item(i);
/*     */       
/* 261 */       if (table.m_Alias.equals(alias)) {
/* 262 */         return table;
/*     */       }
/*     */     } 
/* 265 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryTable getTableByAliasIgnoreCase(String alias) {
/* 270 */     if (this.m_MainQueryTable.m_Alias.equalsIgnoreCase(alias)) {
/* 271 */       return this.m_MainQueryTable;
/*     */     }
/*     */     
/* 274 */     for (int i = 0; i < this.m_TableLinks.size(); i++) {
/*     */       
/* 276 */       QueryTableLink table = this.m_TableLinks.item(i);
/*     */       
/* 278 */       if (table.m_Alias.equalsIgnoreCase(alias)) {
/* 279 */         return table;
/*     */       }
/*     */     } 
/* 282 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryTable getTableByVariable(String name) {
/* 287 */     DBTable dbTable = this.m_MainQueryTable.getTable();
/* 288 */     if (dbTable != null && (dbTable.getName().equals(name) || dbTable.getPhysicalName().equals(name) || this.m_MainQueryTable
/* 289 */       .getAlias().equals(name))) {
/* 290 */       return this.m_MainQueryTable;
/*     */     }
/*     */     
/* 293 */     for (int i = 0; i < this.m_TableLinks.size(); i++) {
/*     */       
/* 295 */       QueryTableLink table = this.m_TableLinks.item(i);
/*     */       
/* 297 */       dbTable = table.m_Table;
/* 298 */       if (dbTable != null && (dbTable
/* 299 */         .getName().equals(name) || dbTable.getPhysicalName().equals(name) || table.getAlias().equals(name))) {
/* 300 */         return table;
/*     */       }
/*     */     } 
/* 303 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryTerms getLookupTerms() {
/* 308 */     return this.m_LookupTerms;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryGroup getGroup() {
/* 316 */     return this.m_Group;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialize() {
/* 321 */     if (this.m_MainQueryTable.hasAggregates()) {
/*     */       
/* 323 */       this.m_HasAggregates = true;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 328 */       for (int i = 0; i < this.m_TableLinks.size(); i++) {
/*     */         
/* 330 */         QueryTableLink tableLink = this.m_TableLinks.item(i);
/*     */         
/* 332 */         if (tableLink.hasAggregates()) {
/*     */           
/* 334 */           this.m_HasAggregates = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 340 */     boolean onlyNative = false;
/* 341 */     if (this.m_MainQueryTable.hasOnlyNativeAggregate() && this.m_MainQueryTable.hasAggregates()) {
/* 342 */       onlyNative = true;
/*     */     }
/* 344 */     if (onlyNative || !this.m_MainQueryTable.hasAggregates())
/*     */     {
/*     */       
/* 347 */       for (int i = 0; i < this.m_TableLinks.size(); i++) {
/*     */         
/* 349 */         QueryTableLink tableLink = this.m_TableLinks.item(i);
/*     */         
/* 351 */         if (tableLink.hasOnlyNativeAggregate() && tableLink.hasAggregates()) {
/* 352 */           onlyNative = true;
/* 353 */         } else if (tableLink.hasAggregates()) {
/*     */           
/* 355 */           onlyNative = false;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 361 */     if (this.m_HasAggregates)
/*     */     {
/* 363 */       if (onlyNative) {
/* 364 */         this.m_HasAggregates = false;
/*     */       } else {
/*     */         
/* 367 */         this.m_Group.add(this.m_MainQueryTable);
/*     */         
/* 369 */         for (int i = 0; i < this.m_TableLinks.size(); i++) {
/*     */           
/* 371 */           QueryTableLink tableLink = this.m_TableLinks.item(i);
/* 372 */           this.m_Group.add(tableLink);
/*     */         } 
/*     */       } 
/*     */     }
/* 376 */     analyzeColumnAliases();
/*     */   }
/*     */ 
/*     */   
/*     */   public void analyzeColumnAliases() {
/* 381 */     Hashtable<String, ArrayList<QueryColumn>> cols = new Hashtable<>();
/* 382 */     QueryColumns columns = this.m_MainQueryTable.m_Columns;
/*     */     
/* 384 */     collectSameAliasCols(cols, columns);
/*     */     
/* 386 */     for (int i = 0; i < this.m_TableLinks.size(); i++) {
/*     */       
/* 388 */       columns = (this.m_TableLinks.item(i)).m_Columns;
/* 389 */       collectSameAliasCols(cols, columns);
/*     */     } 
/*     */     
/* 392 */     Enumeration<String> keys = cols.keys();
/*     */ 
/*     */     
/* 395 */     while (keys.hasMoreElements()) {
/*     */       
/* 397 */       ArrayList<QueryColumn> colsWithSameAlias = cols.get(keys.nextElement());
/* 398 */       if (colsWithSameAlias.size() == 1) {
/*     */         
/* 400 */         QueryColumn col = colsWithSameAlias.get(0);
/* 401 */         col.setHasUniqueAlias(true);
/*     */         
/*     */         continue;
/*     */       } 
/* 405 */       for (int j = 0; j < colsWithSameAlias.size(); j++) {
/*     */         
/* 407 */         if (j == 0) {
/*     */           
/* 409 */           QueryColumn col = colsWithSameAlias.get(0);
/* 410 */           col.setHasUniqueAlias(true);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void collectSameAliasCols(Hashtable<String, ArrayList<QueryColumn>> cols, QueryColumns columns) {
/* 422 */     for (int i = 0; i < columns.size(); i++) {
/*     */       ArrayList<QueryColumn> colsWithSameAlias;
/* 424 */       QueryColumn col = columns.item(i);
/* 425 */       Object<QueryColumn> obj = (Object<QueryColumn>)cols.get(col.getAlias());
/* 426 */       if (obj == null) {
/*     */         
/* 428 */         colsWithSameAlias = new ArrayList<>();
/* 429 */         cols.put(col.getAlias(), colsWithSameAlias);
/*     */       }
/*     */       else {
/*     */         
/* 433 */         colsWithSameAlias = (ArrayList<QueryColumn>)obj;
/*     */       } 
/* 435 */       colsWithSameAlias.add(col);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasAggregates() {
/* 444 */     return this.m_HasAggregates;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryItem getWhereExpression() {
/* 452 */     return this.m_WhereExpression;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWhereExpression(QueryItem item) {
/* 460 */     this.m_WhereExpression = item;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getQueryType() {
/* 468 */     return this.m_QueryType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQueryType(int i) {
/* 476 */     this.m_QueryType = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasNavigation() {
/* 481 */     return this.m_Navigation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNavigation(boolean b) {
/* 486 */     this.m_Navigation = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 491 */     return this.m_Description;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getListID() {
/* 496 */     return this.m_ListID;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStringTag() {
/* 501 */     return this.m_StringTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescription(String string) {
/* 506 */     this.m_Description = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListID(int i) {
/* 511 */     this.m_ListID = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStringTag(int i) {
/* 516 */     this.m_StringTag = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryGlobalOrders getGlobalOrders() {
/* 521 */     return this.m_GlobalOrders;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDistinct(boolean distinct) {
/* 526 */     this.m_Distinct = distinct;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDistinct() {
/* 531 */     return this.m_Distinct;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 536 */     return this.m_Options;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 541 */     this.m_Options = options;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasOption(int option) {
/* 546 */     return SetUtil.isOptionSet(this.m_Options, option);
/*     */   }
/*     */ 
/*     */   
/*     */   public void toggleOption(int option, boolean set) {
/* 551 */     this.m_Options = SetUtil.toggleOption(this.m_Options, option, set);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSimple() {
/* 556 */     return this.m_Simple;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSimple(boolean b) {
/* 561 */     this.m_Simple = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void showWarning(String message) {
/* 566 */     ms_Console.warn("WARNING : Query name='" + getName() + "' - " + message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showError(String message) {
/* 574 */     ms_Console.error("ERROR : Query name='" + getName() + "' - " + message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCustomized() {
/* 582 */     return this.m_Customized;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomized(boolean customized) {
/* 587 */     this.m_Customized = customized;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearGeneratedLinks() {
/* 593 */     if (this.m_TableLinks != null)
/*     */     {
/* 595 */       for (int i = 0; i < this.m_TableLinks.size(); i++) {
/*     */         
/* 597 */         QueryTableLink link = this.m_TableLinks.get(i);
/* 598 */         if (link.isGenerated()) {
/*     */           
/* 600 */           this.m_TableLinks.remove(i);
/* 601 */           i--;
/*     */         } 
/*     */       } 
/*     */     }
/* 605 */     setGeneratedLinksAdded(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGeneratedLinksAdded() {
/* 610 */     return this.m_GeneratedLinksAdded;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGeneratedLinksAdded(boolean allLinksAdded) {
/* 615 */     this.m_GeneratedLinksAdded = allLinksAdded;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addQueryLinkConsumer(IQueryTableLinkConsumer consumer) {
/* 620 */     if (!this.m_Consumers.containsKey(Integer.valueOf(consumer.hashCode()))) {
/*     */       
/* 622 */       this.m_Consumers.put(Integer.valueOf(consumer.hashCode()), new WeakReference<>(consumer));
/* 623 */       if (this.m_Consumers.size() == 1)
/*     */       {
/* 625 */         consumer.initializeQueryLinks(this);
/*     */       }
/*     */     } 
/* 628 */     checkConsumers();
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryColumns getAggregateQueryColumns() {
/* 633 */     QueryColumns aggColumns = new QueryColumns();
/* 634 */     QueryColumns columns = this.m_MainQueryTable.m_Columns;
/* 635 */     findAggColumn(aggColumns, columns);
/* 636 */     for (int j = 0; j < this.m_TableLinks.size(); j++) {
/*     */       
/* 638 */       QueryColumns cols = (this.m_TableLinks.item(j)).m_Columns;
/* 639 */       findAggColumn(aggColumns, cols);
/*     */     } 
/* 641 */     return aggColumns;
/*     */   }
/*     */ 
/*     */   
/*     */   private void findAggColumn(QueryColumns aggColumns, QueryColumns cols) {
/* 646 */     for (int i = 0; i < cols.size(); i++) {
/*     */       
/* 648 */       QueryColumn col = cols.item(i);
/* 649 */       if (col instanceof SelectQueryColumn) {
/*     */         
/* 651 */         SelectQueryColumn selQryCol = (SelectQueryColumn)col;
/* 652 */         if (selQryCol.isAggregate()) {
/* 653 */           aggColumns.add(col);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkConsumers() {
/* 660 */     ArrayList<Integer> toBeRemoved = new ArrayList<>();
/* 661 */     Enumeration<Integer> keys = this.m_Consumers.keys();
/* 662 */     while (keys.hasMoreElements()) {
/*     */       
/* 664 */       Integer key = keys.nextElement();
/* 665 */       WeakReference<IQueryTableLinkConsumer> consumer = this.m_Consumers.get(key);
/* 666 */       if (consumer.get() == null)
/*     */       {
/* 668 */         toBeRemoved.add(key);
/*     */       }
/*     */     } 
/* 671 */     for (Integer key : toBeRemoved)
/*     */     {
/* 673 */       this.m_Consumers.remove(key);
/*     */     }
/* 675 */     if (this.m_Consumers.size() == 0) {
/* 676 */       clearGeneratedLinks();
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeQueryLinkConsumer(IQueryTableLinkConsumer consumer) {
/* 681 */     this.m_Consumers.remove(Integer.valueOf(consumer.hashCode()));
/* 682 */     checkConsumers();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasActiveUnion() {
/* 687 */     for (QueryUnion union : this.m_Unions) {
/*     */       
/* 689 */       if (union.isEnabled())
/* 690 */         return true; 
/*     */     } 
/* 692 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFileName() {
/* 697 */     return this.m_FileName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFileName(String fileName) {
/* 702 */     this.m_FileName = fileName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQueryTableName() {
/* 707 */     return this.m_MainQueryTable.getTable().getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInhQueryName() {
/* 712 */     return this.m_InhQueryName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInhQueryName(String inhQueryName) {
/* 717 */     this.m_InhQueryName = inhQueryName;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QuerySchema.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */