/*     */ package com.lbs.data.database;
/*     */ 
/*     */ import com.lbs.data.IStringListDescription;
/*     */ import com.lbs.data.expression.QueryItem;
/*     */ import com.lbs.util.SetUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
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
/*     */ public class DBField
/*     */   extends DBEntityItem
/*     */   implements IDBField, Serializable, IStringListDescription
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String FIELD_SUFFIX = "___";
/*     */   private int m_Type;
/*  30 */   private int m_SubType = 0;
/*  31 */   private int m_Size = -1;
/*  32 */   private Object m_DefaultValue = null;
/*     */   
/*  34 */   private int m_Options = 0;
/*     */   
/*     */   private String m_Alias;
/*     */   
/*     */   private int m_EnumListID;
/*     */   
/*     */   private int m_LbsType;
/*     */   
/*     */   private String m_RestPropName;
/*     */   private String m_CustFieldName;
/*  44 */   private final List<DBTableLink> m_RefLinks = new ArrayList<>();
/*  45 */   private String m_ApplicationVarName = null;
/*     */   
/*     */   private int m_ColumnListId;
/*     */   
/*     */   public DBField() {
/*  50 */     super(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public DBField(DBField src) {
/*  55 */     super(src);
/*  56 */     setOptions(SetUtil.toggleOption(this.m_Options, 2, src.isPrimaryKey()));
/*  57 */     setOptions(SetUtil.toggleOption(this.m_Options, 8, src.isAutoIncrement()));
/*     */   }
/*     */ 
/*     */   
/*     */   public DBField(String name, String physicalName, int type, int size, int options) {
/*  62 */     this();
/*  63 */     setName(name);
/*  64 */     setPhysicalName(physicalName);
/*  65 */     setType(type);
/*  66 */     setSize(size);
/*     */ 
/*     */     
/*  69 */     if (type == 16) {
/*  70 */       setAllowNullValues(true);
/*     */     }
/*  72 */     if ((options & 0x8) == 8) {
/*  73 */       setAutoIncrement(true);
/*     */     }
/*  75 */     if ((options & 0x2) == 2) {
/*  76 */       setPrimaryKey(true);
/*     */     }
/*  78 */     if ((options & 0x4) == 4) {
/*  79 */       setEncrypted(true);
/*     */     }
/*  81 */     if ((options & 0x20) == 32) {
/*  82 */       setUserCanEncrypt(true);
/*     */     }
/*  84 */     if ((options & 0x1) == 1) {
/*  85 */       setAllowNullValues(true);
/*     */     }
/*  87 */     if ((options & 0x10) == 16) {
/*  88 */       setIdentity(true);
/*     */     }
/*  90 */     if ((options & 0x40) == 64) {
/*  91 */       setMultiLang(true);
/*     */     }
/*     */   }
/*     */   
/*     */   public DBField(String name, String physicalName, int type, int size) {
/*  96 */     this(name, physicalName, type, size, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public DBField(String name, String physicalName, int type) {
/* 101 */     this(name, physicalName, type, 0, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static DBField addAutoIncrementField(DBTable table, String name) {
/* 106 */     DBField field = new DBField(name, name.toUpperCase(Locale.US), 3);
/* 107 */     table.getFields().add(field);
/* 108 */     field.setAllowNullValues(false);
/* 109 */     field.setAutoIncrement(true);
/*     */     
/* 111 */     return field;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 117 */     super.setName(name);
/* 118 */     if (StringUtil.isEmpty(this.m_Alias)) {
/* 119 */       this.m_Alias = this.m_Name;
/*     */     }
/*     */   }
/*     */   
/*     */   public DBTable getTable() {
/* 124 */     return (DBTable)getCollectionOwner();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTableName() {
/* 129 */     DBTable table = getTable();
/* 130 */     if (table != null)
/* 131 */       return table.getName(); 
/* 132 */     return null;
/*     */   }
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
/*     */   public boolean isPrimaryKey() {
/* 154 */     return SetUtil.isOptionSet(this.m_Options, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 163 */     if (this.m_Size == -1) {
/* 164 */       this.m_Size = getFieldDataSize(this);
/*     */     }
/* 166 */     return this.m_Size;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDatabaseSize() {
/* 171 */     int size = getSize();
/*     */     
/* 173 */     if (isEncrypted()) {
/* 174 */       return getEncryptedFieldSize(size);
/*     */     }
/* 176 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDatabaseType() {
/* 181 */     if (isEncrypted()) {
/* 182 */       return 11;
/*     */     }
/* 184 */     return this.m_Type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 193 */     return this.m_Type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrimaryKey(boolean primaryKey) {
/* 202 */     if (primaryKey) {
/*     */       
/* 204 */       this.m_Options = SetUtil.toggleOption(this.m_Options, 1, false);
/*     */       
/* 206 */       DBTable table = getTable();
/* 207 */       if (table != null)
/*     */       {
/*     */         
/* 210 */         table.setKeyField(this);
/*     */       }
/*     */     } 
/* 213 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 2, primaryKey);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSize(int size) {
/* 222 */     this.m_Size = size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(int type) {
/* 231 */     this.m_Type = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public Class<?> getJavaType() {
/* 236 */     return DBFieldHelper.DbToJavaType(this.m_Type);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getJavaTypeString() {
/* 241 */     Class<?> c = getJavaType();
/* 242 */     if (c.isArray())
/* 243 */       return c.getComponentType().getName() + "[]"; 
/* 244 */     return c.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAutoIncrement() {
/* 253 */     return SetUtil.isOptionSet(this.m_Options, 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAutoIncrement(boolean autoIncrement) {
/* 262 */     if (autoIncrement) {
/*     */       
/* 264 */       DBTable table = getTable();
/* 265 */       if (table != null)
/*     */       {
/* 267 */         table.setAutoIncrementField(this);
/*     */       }
/*     */     } 
/* 270 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 8, autoIncrement);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getMaximumValue() {
/* 275 */     return DBFieldHelper.getMaximumValue(this.m_Type, this.m_Size);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getMinimumValue() {
/* 280 */     return DBFieldHelper.getMinimumValue(this.m_Type);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getBase64EncodedSize(int size) {
/* 285 */     return (size + 2) / 3 * 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getEncryptBufferSize(int size) {
/* 290 */     return ((size + 4) * 8 + 6) / 7;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getEncryptedFieldSize(int size) {
/* 295 */     size = getEncryptBufferSize(size);
/*     */     
/* 297 */     int res = (size & 0xFFFFFFF8) + 8;
/* 298 */     res = getBase64EncodedSize(res);
/*     */     
/* 300 */     res++;
/* 301 */     return res;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getFieldDataSize(DBField field) {
/* 306 */     switch (field.getType()) {
/*     */       
/*     */       case 1:
/* 309 */         return 1;
/*     */       
/*     */       case 2:
/* 312 */         return 2;
/*     */       case 3:
/* 314 */         return 4;
/*     */       
/*     */       case 5:
/* 317 */         return 4;
/*     */       case 6:
/* 319 */         return 8;
/*     */       case 7:
/* 321 */         return 8;
/*     */       
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/*     */       case 16:
/* 327 */         return 4;
/*     */       
/*     */       case 12:
/* 330 */         return field.m_Size;
/*     */       
/*     */       case 13:
/*     */       case 14:
/* 334 */         return -1;
/*     */       
/*     */       case 15:
/* 337 */         return 4;
/*     */       
/*     */       case 11:
/* 340 */         return field.m_Size;
/*     */     } 
/*     */     
/* 343 */     return field.m_Size;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAllowNullValues() {
/* 348 */     return SetUtil.isOptionSet(this.m_Options, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllowNullValues(boolean b) {
/* 353 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 1, b);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 359 */     DBTable table = getTable();
/*     */     
/* 361 */     if (table == null) {
/* 362 */       return "$Y(" + this.m_Name + ")";
/*     */     }
/* 364 */     if (StringUtil.isEmpty(table.getGuid())) {
/* 365 */       return "$Y(" + table.getName() + "." + this.m_Name + ")";
/*     */     }
/* 367 */     return "$Y(" + table.getGuid() + "-" + table.getName() + "." + this.m_Name + ")";
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getDefaultValue() {
/* 372 */     return this.m_DefaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDefaultValue(Object object) {
/* 377 */     this.m_DefaultValue = object;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getListID() {
/* 384 */     if (this.m_ListID != -1) {
/* 385 */       return this.m_ListID;
/*     */     }
/* 387 */     DBTable table = getTable();
/*     */     
/* 389 */     if (table == null) {
/* 390 */       return -1;
/*     */     }
/* 392 */     return table.getListID();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSubType() {
/* 397 */     return this.m_SubType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSubType(int subType) {
/* 402 */     this.m_SubType = subType;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 407 */     return this.m_Options;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 412 */     this.m_Options = options;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAlias() {
/* 417 */     return this.m_Alias;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlias(String alias) {
/* 422 */     this.m_Alias = alias;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEnumListID() {
/* 427 */     return this.m_EnumListID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnumListID(int enumListID) {
/* 432 */     this.m_EnumListID = enumListID;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLbsType() {
/* 437 */     return this.m_LbsType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLbsType(int lbsType) {
/* 442 */     this.m_LbsType = lbsType;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<DBTableLink> getRefLinks() {
/* 447 */     return this.m_RefLinks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRefLink(DBTableLink refLink) {
/* 452 */     for (DBTableLink cand : this.m_RefLinks) {
/* 453 */       if (cand.getAlias().equals(refLink.getAlias()) && cand.getLinkTable().equals(refLink.getLinkTable()))
/*     */         return; 
/* 455 */     }  this.m_RefLinks.add(refLink);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasRefLink() {
/* 460 */     return (this.m_RefLinks.size() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setApplicationVarName(String exchangeVarName) {
/* 465 */     this.m_ApplicationVarName = exchangeVarName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getApplicationVarName() {
/* 470 */     return this.m_ApplicationVarName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdentity(boolean identity) {
/* 475 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 16, identity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIdentity() {
/* 480 */     return SetUtil.isOptionSet(this.m_Options, 16);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEncrypted(boolean b) {
/* 485 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 4, b);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEncrypted() {
/* 490 */     return SetUtil.isOptionSet(this.m_Options, 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDXRequired(boolean mustImportExport) {
/* 495 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 256, mustImportExport);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDXRequired() {
/* 500 */     return SetUtil.isOptionSet(this.m_Options, 256);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFromCustomSchema(boolean fromCustomSchema) {
/* 505 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 4096, fromCustomSchema);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFromCustomSchema() {
/* 510 */     return SetUtil.isOptionSet(this.m_Options, 4096);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInitRequired(boolean initRequired) {
/* 515 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 512, initRequired);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInitRequired() {
/* 520 */     return SetUtil.isOptionSet(this.m_Options, 512);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean userCanEncrypt() {
/* 525 */     return SetUtil.isOptionSet(this.m_Options, 32);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUserCanEncrypt(boolean canEncrypt) {
/* 530 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 32, canEncrypt);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForDomainAwareLink(boolean forDomainAwareLink) {
/* 535 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 1024, forDomainAwareLink);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isForDomainAwareLink() {
/* 540 */     return SetUtil.isOptionSet(this.m_Options, 1024);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGeneratedDomainColumn(boolean generatedDomainColumn) {
/* 545 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 2048, generatedDomainColumn);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGeneratedDomainColumn() {
/* 550 */     return SetUtil.isOptionSet(this.m_Options, 2048);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRequiresEnumList() {
/* 555 */     return SetUtil.isOptionSet(this.m_Options, 128);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRequiresEnumList(boolean requiresEnumList) {
/* 560 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 128, requiresEnumList);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMultiLang() {
/* 565 */     return SetUtil.isOptionSet(this.m_Options, 64);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMultiLang(boolean multiLang) {
/* 570 */     this.m_Options = SetUtil.toggleOption(this.m_Options, 64, multiLang);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAllowNullValues() {
/* 575 */     return isAllowNullValues();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getPrimaryKey() {
/* 580 */     return isPrimaryKey();
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem getNameItem() {
/* 585 */     return new QueryItem(12, getPhysicalName());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRestPropName(String restPropName) {
/* 590 */     this.m_RestPropName = restPropName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRestPropName() {
/* 595 */     return this.m_RestPropName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCustFieldName() {
/* 600 */     return this.m_CustFieldName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustFieldName(String custFieldName) {
/* 605 */     this.m_CustFieldName = custFieldName;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColumnListId() {
/* 610 */     return this.m_ColumnListId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumnListId(int listId) {
/* 615 */     this.m_ColumnListId = listId;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\DBField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */