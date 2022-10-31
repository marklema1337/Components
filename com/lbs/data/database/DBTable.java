/*     */ package com.lbs.data.database;
/*     */ 
/*     */ import com.lbs.data.IStringListDescription;
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.data.expression.QueryItem;
/*     */ import com.lbs.data.factory.INamedVariables;
/*     */ import com.lbs.data.factory.ISubstitutionListener;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.StringUtil;
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
/*     */ public class DBTable
/*     */   extends DBEntity
/*     */   implements Cloneable, Serializable, IStringListDescription, IDBTable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String m_PackageName;
/*     */   private int m_Partitions;
/*     */   protected boolean m_WorkProductTable = true;
/*     */   private boolean m_Customization = false;
/*     */   private boolean m_TemplateTable;
/*     */   protected DBFields m_Fields;
/*     */   protected DBFields m_PartitionFields;
/*     */   protected DBIndexes m_Indexes;
/*     */   protected String m_FileName;
/*  50 */   private DBIndex m_KeyIndex = null;
/*  51 */   private DBField m_KeyField = null;
/*  52 */   private DBField m_AutoIncrementField = null;
/*     */   private boolean m_ViewTable = false;
/*  54 */   private DBIndex m_SecondaryKeyIndex = null;
/*     */ 
/*     */   
/*     */   private String m_ExtensionTableName;
/*     */   
/*     */   private String[] m_SetValues;
/*     */   
/*     */   private transient Hashtable m_PreparationMap;
/*     */   
/*  63 */   private int m_MultiCompany = 0;
/*     */   
/*     */   private String m_TemplateName;
/*     */   
/*     */   private DBTableTemplate m_Template;
/*     */   private NamedCollection m_Modes;
/*     */   private NamedCollection m_Links;
/*     */   private boolean m_AuthorizationEnabled = false;
/*     */   private boolean m_RefCountRequired = false;
/*  72 */   private String m_RecordInfo = "false";
/*  73 */   private String m_TimestampCol = "false";
/*     */   
/*     */   protected boolean m_CrossEnabled = true;
/*     */   
/*     */   private final ArrayList m_BackwardsRefCountLinks;
/*     */   
/*     */   protected boolean m_Exchangeable = true;
/*     */   
/*     */   private boolean m_ArchiveTable = false;
/*     */   private boolean m_LockEnabled = false;
/*     */   private boolean m_HasUUID = true;
/*     */   private boolean m_TriggerControl;
/*     */   private boolean m_NonSchemaTable = false;
/*     */   private boolean m_NeedsAutoUpgrade = false;
/*     */   private boolean m_DomainAware = false;
/*     */   private boolean m_HasDomainAwareLink = false;
/*     */   private boolean m_EasyCustTable = false;
/*  90 */   private List<String> m_ObjectSchemaNames = new ArrayList<>();
/*  91 */   private int m_DefaultSchemaNameIdx = 0;
/*     */   
/*  93 */   private DBType m_DBType = DBType.application;
/*     */   
/*  95 */   private ArrayList<Identifier> m_ReferringSummaryTables = new ArrayList<>();
/*     */   
/*  97 */   protected Long m_HashValue = null;
/*     */   
/*     */   private boolean m_FromCustomSchema = false;
/*     */ 
/*     */   
/*     */   public DBTable() {
/* 103 */     super(4);
/* 104 */     this.m_Fields = new DBFields(this);
/* 105 */     this.m_Indexes = new DBIndexes(this);
/* 106 */     this.m_PartitionFields = new DBFields(this);
/*     */     
/* 108 */     this.m_ViewTable = false;
/* 109 */     this.m_PreparationMap = new Hashtable<>();
/*     */     
/* 111 */     this.m_Modes = new NamedCollection();
/* 112 */     this.m_Links = new NamedCollection();
/*     */     
/* 114 */     this.m_BackwardsRefCountLinks = new ArrayList();
/*     */   }
/*     */ 
/*     */   
/*     */   public DBTable(String name, String physicalName) {
/* 119 */     this();
/* 120 */     setName(name);
/* 121 */     setPhysicalName(physicalName);
/*     */   }
/*     */ 
/*     */   
/*     */   public DBTable(boolean isViewTable) {
/* 126 */     this();
/* 127 */     this.m_ViewTable = isViewTable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DBFields getFields() {
/* 137 */     return this.m_Fields;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DBIndexes getIndexes() {
/* 147 */     return this.m_Indexes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DBField getKeyField() {
/* 157 */     return this.m_KeyField;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DBIndex getKeyIndex() {
/* 167 */     return this.m_KeyIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DBField getAutoIncrementField() {
/* 177 */     return this.m_AutoIncrementField;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAutoIncrementField(DBField autoIncrementField) {
/* 188 */     this.m_AutoIncrementField = autoIncrementField;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKeyField(DBField keyField) {
/* 199 */     this.m_KeyField = keyField;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKeyIndex(DBIndex keyIndex) {
/* 210 */     this.m_KeyIndex = keyIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 219 */     DBTable cloneTable = (DBTable)super.clone();
/* 220 */     cloneTable.m_Fields = new DBFields(cloneTable);
/* 221 */     cloneTable.m_PartitionFields = new DBFields(cloneTable);
/* 222 */     cloneTable.m_Indexes = new DBIndexes(cloneTable);
/* 223 */     cloneTable.m_Modes = new NamedCollection();
/* 224 */     cloneTable.m_Links = new NamedCollection();
/* 225 */     cloneTable.m_KeyField = null;
/* 226 */     cloneTable.m_KeyIndex = null;
/* 227 */     cloneTable.m_AutoIncrementField = null;
/*     */     int i;
/* 229 */     for (i = 0; i < this.m_Fields.size(); i++) {
/* 230 */       cloneTable.m_Fields.add((DBField)this.m_Fields.item(i).clone());
/*     */     }
/* 232 */     for (i = 0; i < this.m_PartitionFields.size(); i++) {
/* 233 */       cloneTable.m_PartitionFields.add((DBField)this.m_PartitionFields.item(i).clone());
/*     */     }
/* 235 */     for (i = 0; i < this.m_Indexes.size(); i++) {
/* 236 */       cloneTable.m_Indexes.add((DBIndex)this.m_Indexes.item(i).clone());
/*     */     }
/* 238 */     for (i = 0; i < this.m_Modes.size(); i++) {
/* 239 */       cloneTable.m_Modes.add((E)((DBTableMode)this.m_Modes.get(i)).clone());
/*     */     }
/* 241 */     for (i = 0; i < this.m_Links.size(); i++) {
/* 242 */       cloneTable.m_Links.add((E)((DBTableLink)this.m_Links.get(i)).clone());
/*     */     }
/* 244 */     for (i = 0; i < this.m_Indexes.getCustIndexes().size(); i++) {
/* 245 */       cloneTable.m_Indexes.getCustIndexes().add(((SimpleIndex)this.m_Indexes.getCustIndexes().get(i)).clone());
/*     */     }
/* 247 */     return cloneTable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeName(INameProducer namer, INamedVariables variables, ISubstitutionListener listener, Object dbConnection) {
/* 257 */     super.changeName(namer, variables, listener, dbConnection);
/*     */     
/* 259 */     this.m_Fields.changeNames(namer, variables, listener, dbConnection);
/* 260 */     this.m_Indexes.changeNames(namer, variables, listener, dbConnection);
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem getNameItem() {
/* 265 */     return new QueryItem(101, this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryItem getDefinitionItem() {
/* 271 */     return getNameItem();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 277 */     if (StringUtil.isEmpty(this.m_Guid)) {
/* 278 */       return "$X(" + this.m_Name + ")";
/*     */     }
/* 280 */     return "$X(" + this.m_Guid + "-" + this.m_Name + ")";
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTemplateTable(boolean isTemplate) {
/* 285 */     this.m_TemplateTable = isTemplate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTemplateTable() {
/* 291 */     return this.m_TemplateTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCustomization() {
/* 296 */     return this.m_Customization;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomization(boolean b) {
/* 301 */     this.m_Customization = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public Hashtable getPreparationMap() {
/* 306 */     if (this.m_PreparationMap == null) {
/* 307 */       this.m_PreparationMap = new Hashtable<>();
/*     */     }
/* 309 */     return this.m_PreparationMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWorkProductTable(boolean isWorkProductTable) {
/* 314 */     this.m_WorkProductTable = isWorkProductTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWorkProductTable() {
/* 319 */     return (!this.m_NonSchemaTable && this.m_WorkProductTable);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFileName() {
/* 324 */     return this.m_FileName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFileName(String fileName) {
/* 329 */     this.m_FileName = fileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExtensionTableName() {
/* 340 */     return this.m_ExtensionTableName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExtensionTableName(String extensionTableName) {
/* 345 */     this.m_ExtensionTableName = extensionTableName;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isViewTable() {
/* 350 */     return this.m_ViewTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getSetValues() {
/* 355 */     return this.m_SetValues;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSetValues(String[] setValues) {
/* 360 */     this.m_SetValues = setValues;
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
/*     */   public void setMultiCompany(int option) {
/* 372 */     this.m_MultiCompany = option;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMultiCompanyTable() {
/* 377 */     return (!this.m_NonSchemaTable && this.m_MultiCompany >= 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMCReplicable() {
/* 382 */     return (!this.m_NonSchemaTable && this.m_MultiCompany == 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTemplateName() {
/* 387 */     return this.m_TemplateName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTemplateName(String templateName) {
/* 392 */     this.m_TemplateName = templateName;
/*     */   }
/*     */ 
/*     */   
/*     */   public NamedCollection getLinks() {
/* 397 */     return this.m_Links;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLinks(NamedCollection links) {
/* 402 */     this.m_Links = links;
/*     */   }
/*     */ 
/*     */   
/*     */   public NamedCollection getModesBySetName(String setName) {
/* 407 */     NamedCollection modes = new NamedCollection();
/* 408 */     if (this.m_Modes != null)
/*     */     {
/* 410 */       for (int i = 0; i < this.m_Modes.size(); i++) {
/*     */         
/* 412 */         Object objMode = this.m_Modes.get(i);
/* 413 */         if (objMode instanceof DBTableMode) {
/*     */           
/* 415 */           DBTableMode mode = (DBTableMode)objMode;
/* 416 */           if (JLbsStringUtil.areEqual(mode.getSetValue(), setName))
/* 417 */             modes.add((E)mode); 
/*     */         } 
/*     */       } 
/*     */     }
/* 421 */     return modes;
/*     */   }
/*     */ 
/*     */   
/*     */   public NamedCollection getModesWithoutSetName() {
/* 426 */     NamedCollection modes = new NamedCollection();
/* 427 */     if (this.m_Modes != null)
/*     */     {
/* 429 */       for (int i = 0; i < this.m_Modes.size(); i++) {
/*     */         
/* 431 */         Object objMode = this.m_Modes.get(i);
/* 432 */         if (objMode instanceof DBTableMode) {
/*     */           
/* 434 */           DBTableMode mode = (DBTableMode)objMode;
/* 435 */           if (JLbsStringUtil.isEmpty(mode.getSetValue()))
/* 436 */             modes.add((E)mode); 
/*     */         } 
/*     */       } 
/*     */     }
/* 440 */     return modes;
/*     */   }
/*     */ 
/*     */   
/*     */   public NamedCollection getModes() {
/* 445 */     return this.m_Modes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModes(NamedCollection modes) {
/* 450 */     this.m_Modes = modes;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAuthIDByModeID(int modeID) {
/* 455 */     if (this.m_Modes != null && modeID != JLbsConstants.DUMMY_AUTH_ID)
/*     */     {
/* 457 */       for (int i = 0; i < this.m_Modes.size(); i++) {
/*     */         
/* 459 */         Object objMode = this.m_Modes.get(i);
/* 460 */         if (objMode instanceof DBTableMode) {
/*     */           
/* 462 */           DBTableMode mode = (DBTableMode)objMode;
/* 463 */           if (mode.getID() == modeID)
/* 464 */             return mode.getAuthID(); 
/*     */         } 
/*     */       } 
/*     */     }
/* 468 */     return JLbsConstants.DUMMY_AUTH_ID;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAuthorizationEnabled() {
/* 473 */     return (!this.m_NonSchemaTable && this.m_AuthorizationEnabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAuthorizationEnabled(boolean authorizationEnabled) {
/* 478 */     this.m_AuthorizationEnabled = authorizationEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DBEntityCollection getFieldList() {
/* 484 */     return this.m_Fields;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DBEntityCollection getIndexList() {
/* 490 */     return this.m_Indexes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTypeName() {
/* 496 */     return "Table";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasRefCountLink() {
/* 502 */     for (int i = 0; i < this.m_Links.size(); i++) {
/*     */       
/* 504 */       DBTableLink link = (DBTableLink)this.m_Links.get(i);
/* 505 */       if (link.isRefCount())
/* 506 */         return true; 
/*     */     } 
/* 508 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRefCountRequired() {
/* 513 */     return this.m_RefCountRequired;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRefCountRequired(boolean refCountRequired) {
/* 518 */     this.m_RefCountRequired = refCountRequired;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRecordInfo() {
/* 523 */     return (!this.m_NonSchemaTable && !"false".equalsIgnoreCase(this.m_RecordInfo));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasTimestampCol() {
/* 531 */     return (!this.m_NonSchemaTable && !"false".equalsIgnoreCase(this.m_TimestampCol));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRecordInfoComplex() {
/* 536 */     if (JLbsConstants.COMPLEX_RECINFO_FORALL) {
/* 537 */       return (!this.m_NonSchemaTable && !"false".equalsIgnoreCase(this.m_RecordInfo));
/*     */     }
/* 539 */     return (!this.m_NonSchemaTable && "complex".equalsIgnoreCase(this.m_RecordInfo));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRecordInfo(boolean collectModification) {
/* 544 */     this.m_RecordInfo = collectModification ? "true" : "false";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRecordInfo(String recInfoComplex) {
/* 554 */     if (JLbsStringUtil.isEmpty(recInfoComplex)) {
/* 555 */       this.m_RecordInfo = "false";
/*     */     } else {
/* 557 */       this.m_RecordInfo = recInfoComplex;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setTimestampCol(boolean collectModification) {
/* 562 */     this.m_TimestampCol = collectModification ? "true" : "false";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTimestampCol(String recInfoComplex) {
/* 569 */     if (JLbsStringUtil.isEmpty(recInfoComplex)) {
/* 570 */       this.m_TimestampCol = "false";
/*     */     } else {
/* 572 */       this.m_TimestampCol = recInfoComplex;
/*     */     } 
/*     */   }
/*     */   
/*     */   public DBTableTemplate getTemplate() {
/* 577 */     return this.m_Template;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTemplate(DBTableTemplate template) {
/* 582 */     this.m_Template = template;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addBackwardsRefCountLink(DBTableLink link) {
/* 587 */     if (!this.m_BackwardsRefCountLinks.contains(link)) {
/* 588 */       this.m_BackwardsRefCountLinks.add(link);
/*     */     }
/*     */   }
/*     */   
/*     */   public ArrayList getBackwardsRefCountLinks() {
/* 593 */     return this.m_BackwardsRefCountLinks;
/*     */   }
/*     */ 
/*     */   
/*     */   public DBIndex getSecondaryKeyIndex() {
/* 598 */     return this.m_SecondaryKeyIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSecondaryKeyIndex(DBIndex secondaryKeyIndex) {
/* 603 */     this.m_SecondaryKeyIndex = secondaryKeyIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPackageName(String packageName) {
/* 608 */     this.m_PackageName = packageName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPackageName() {
/* 613 */     return this.m_PackageName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResolverInterfaceName() {
/* 618 */     return "I" + this.m_Name + "Resolver";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDefaultResolverName() {
/* 623 */     return this.m_Name + "Resolver";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCrossEnabled() {
/* 628 */     return (!this.m_NonSchemaTable && this.m_CrossEnabled);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCrossEnabled() {
/* 636 */     return this.m_CrossEnabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCrossEnabled(boolean crossEnabled) {
/* 641 */     this.m_CrossEnabled = crossEnabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExchangeable() {
/* 646 */     return (!this.m_NonSchemaTable && this.m_Exchangeable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExchangeable(boolean exchangable) {
/* 651 */     this.m_Exchangeable = exchangable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTriggerControl(boolean triggerControl) {
/* 656 */     this.m_TriggerControl = triggerControl;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTriggerControl() {
/* 661 */     return this.m_TriggerControl;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setArchiveTable(boolean archiveTable) {
/* 666 */     this.m_ArchiveTable = archiveTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isArchiveTable() {
/* 671 */     return (!this.m_NonSchemaTable && this.m_ArchiveTable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLockEnabled(boolean lockEnabled) {
/* 676 */     this.m_LockEnabled = lockEnabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLockEnabled() {
/* 681 */     return (!this.m_NonSchemaTable && this.m_LockEnabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNonSchemaTable() {
/* 686 */     return this.m_NonSchemaTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNonSchemaTable(boolean nonSchemaTable) {
/* 691 */     this.m_NonSchemaTable = nonSchemaTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNeedsAutoUpgrade(boolean needsAutoUpgrade) {
/* 696 */     this.m_NeedsAutoUpgrade = needsAutoUpgrade;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNeedsAutoUpgrade() {
/* 701 */     return this.m_NeedsAutoUpgrade;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDomainAware(boolean domainAware) {
/* 706 */     this.m_DomainAware = domainAware;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDomainAware() {
/* 712 */     return this.m_DomainAware;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHasDomainAwareLink(boolean hasDomainAwareLink) {
/* 717 */     this.m_HasDomainAwareLink = hasDomainAwareLink;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHasDomainAwareLink() {
/* 722 */     return this.m_HasDomainAwareLink;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getObjectSchemaNames() {
/* 727 */     return this.m_ObjectSchemaNames;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addObjectSchemaName(String name, boolean defaultSchema) {
/* 732 */     if (!this.m_ObjectSchemaNames.contains(name))
/* 733 */       this.m_ObjectSchemaNames.add(name); 
/* 734 */     if (defaultSchema) {
/* 735 */       this.m_DefaultSchemaNameIdx = this.m_ObjectSchemaNames.indexOf(name);
/*     */     }
/*     */   }
/*     */   
/*     */   public String getDefaultObjectSchemaName() {
/* 740 */     if (this.m_DefaultSchemaNameIdx >= 0 && this.m_DefaultSchemaNameIdx < this.m_ObjectSchemaNames.size())
/* 741 */       return this.m_ObjectSchemaNames.get(this.m_DefaultSchemaNameIdx); 
/* 742 */     if (this.m_ObjectSchemaNames.size() > 0)
/* 743 */       return this.m_ObjectSchemaNames.get(0); 
/* 744 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<Identifier> getReferringSummaryTables() {
/* 749 */     return this.m_ReferringSummaryTables;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addReferringSummaryTable(Identifier identifier) {
/* 754 */     if (!this.m_ReferringSummaryTables.contains(identifier)) {
/* 755 */       this.m_ReferringSummaryTables.add(identifier);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getMultiCompany() {
/* 760 */     return this.m_MultiCompany;
/*     */   }
/*     */ 
/*     */   
/*     */   public DBType getDBType() {
/* 765 */     return this.m_DBType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDBType(DBType dBType) {
/* 770 */     this.m_DBType = dBType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHasUUID(boolean hasUUID) {
/* 775 */     this.m_HasUUID = hasUUID;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveUid() {
/* 780 */     return (!this.m_ViewTable && JLbsConstants.isEnableUUID() && this.m_HasUUID);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPartitions() {
/* 785 */     return this.m_Partitions;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPartitions(int partitions) {
/* 790 */     this.m_Partitions = partitions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DBEntityCollection getPartitionFieldList() {
/* 796 */     return this.m_PartitionFields;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEasyCustTable() {
/* 801 */     return this.m_EasyCustTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEasyCustTable(boolean easyCustTable) {
/* 806 */     this.m_EasyCustTable = easyCustTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getHashValue() {
/* 811 */     return this.m_HashValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHashValue(Long hashValue) {
/* 816 */     this.m_HashValue = hashValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFromCustomSchema() {
/* 821 */     return this.m_FromCustomSchema;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFromCustomSchema(boolean fromCustomSchema) {
/* 826 */     this.m_FromCustomSchema = fromCustomSchema;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\DBTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */