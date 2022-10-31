/*     */ package com.lbs.data.database;
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
/*     */ public interface IDBField
/*     */ {
/*     */   public static final String UID_COLUMN_NAME = "UUID";
/*     */   public static final String WPINST_COLUMN_NAME = "TE_WPIID";
/*     */   public static final String WPINST_COLUMN_ALIAS = "WorkProductInstanceID";
/*     */   public static final String RECSTATUS_COLUMN_NAME = "TE_RECSTATUS";
/*     */   public static final String RECSTATUS_COLUMN_ALIAS = "RecStatus";
/*     */   public static final String WFINST_COLUMN_NAME = "TE_WFIID";
/*     */   public static final String WFINST_COLUMN_ALIAS = "WorkFlowInstanceID";
/*     */   public static final String AUTH_COLUMN_NAME = "AUTHCODE";
/*     */   public static final String AUTH_COLUMN_ALIAS = "AuthCode";
/*     */   public static final String CREATEDON_COLUMN_NAME = "CREATEDON";
/*     */   public static final String CREATEDON_COLUMN_ALIAS = "Created_On";
/*     */   public static final String TIMESTAMP_COLUMN_NAME = "RV";
/*     */   public static final String TIMESTAMP_COLUMN_ALIAS = "RowVersion";
/*     */   public static final String CREATEDBY_COLUMN_NAME = "CREATEDBY";
/*     */   public static final String CREATEDBY_COLUMN_ALIAS = "Created_By";
/*     */   public static final String CREATEDBYNAME_COLUMN_NAME = "CREATEDBYNAME";
/*     */   public static final String CREATEDBYNAME_COLUMN_ALIAS = "Created_ByName";
/*     */   public static final String UPDATEDON_COLUMN_NAME = "MODIFIEDON";
/*     */   public static final String UPDATEDON_COLUMN_ALIAS = "Modified_On";
/*     */   public static final String UPDATEDBY_COLUMN_NAME = "MODIFIEDBY";
/*     */   public static final String UPDATEDBY_COLUMN_ALIAS = "Modified_By";
/*     */   public static final String UPDATEDBYNAME_COLUMN_NAME = "MODIFIEDBYNAME";
/*     */   public static final String UPDATEDBYNAME_COLUMN_ALIAS = "Modified_ByName";
/*     */   public static final String CUSTOM_PARENT_KEY_COLUMN_NAME = "PARENTREF";
/*     */   public static final String CUSTOM_PARENT_KEY_COLUMN_ALIAS = "PARENTREF";
/*     */   public static final String TRGCTRL_COLUMN_NAME = "TE_TRGCTRL";
/*     */   public static final String TRGCTRL_COLUMN_ALIAS = "TriggerControl";
/*     */   public static final String ARCHIVE_COLUMN_NAME = "TE_ARCHSTAT";
/*     */   public static final String ARCHIVE_COLUMN_ALIAS = "ArchiveStatus";
/*     */   public static final String LOCK_COLUMN_NAME = "TE_LOCKED";
/*     */   public static final String LOCK_COLUMN_ALIAS = "TE_Locked";
/*     */   public static final String DOMAINN_COLUMN_NAME = "TE_DOMAINID";
/*     */   public static final String DOMAINN_COLUMN_ALIAS = "DomainId";
/*     */   public static final String LABELS_COLUMN_NAME = "TE_LABELS";
/*     */   public static final String LABELS_COLUMN_ALIAS = "TE_Labels";
/*     */   public static final int DBTYPE_NONE = 0;
/*     */   public static final int DBTYPE_BYTE = 1;
/*     */   public static final int DBTYPE_SMALLINT = 2;
/*     */   public static final int DBTYPE_INTEGER = 3;
/*     */   public static final int DBTYPE_INT64 = 4;
/*     */   public static final int DBTYPE_FLOAT = 5;
/*     */   public static final int DBTYPE_DOUBLE = 6;
/*     */   public static final int DBTYPE_NUMERIC = 7;
/*     */   public static final int DBTYPE_DATE = 8;
/*     */   public static final int DBTYPE_TIME = 9;
/*     */   public static final int DBTYPE_DATETIME = 10;
/*     */   public static final int DBTYPE_STRING = 11;
/*     */   public static final int DBTYPE_BINARY = 12;
/*     */   public static final int DBTYPE_BLOB = 13;
/*     */   public static final int DBTYPE_CLOB = 14;
/*     */   public static final int DBTYPE_UUID = 15;
/*     */   public static final int DBTYPE_TIMESTAMP = 16;
/*     */   public static final int LBSTYPE_NONE = 0;
/*     */   public static final int LBSTYPE_LOGICALREF = 1;
/*     */   public static final int LBSTYPE_CODE = 2;
/*     */   public static final int LBSTYPE_DESCRIPTION = 3;
/*     */   public static final int LBSTYPE_ENUMERATION = 4;
/*     */   public static final int LBSTYPE_AUXCODE = 5;
/*     */   public static final int LBSTYPE_GRPCODE = 6;
/*     */   public static final int LBSTYPE_SLIPNR = 7;
/*     */   public static final int OPTION_NONE = 0;
/*     */   public static final int OPTION_NULLABLE = 1;
/*     */   public static final int OPTION_PRIMARY = 2;
/*     */   public static final int OPTION_ENCRYPTED = 4;
/*     */   public static final int OPTION_AUTOINC = 8;
/*     */   public static final int OPTION_IDENTITY = 16;
/*     */   public static final int OPTION_CAN_ENCRYPT = 32;
/*     */   public static final int OPTION_MULTILANG = 64;
/*     */   public static final int OPTION_REQENUMLIST = 128;
/*     */   public static final int OPTION_DXREQUIRED = 256;
/*     */   public static final int OPTION_INITREQUIRED = 512;
/*     */   public static final int OPTION_DOMAIN_AWARELINK = 1024;
/*     */   public static final int OPTION_GENERATED_DOMAINCOL = 2048;
/*     */   public static final int OPTION_FROMCUSTOMSCHEMA = 4096;
/*     */   public static final String EXCH_DOMAIN_COLUMN_NAME = "TE_DID";
/*     */   public static final String EXCH_DOMAIN_COLUMN_ALIAS = "Exch_DomainID";
/*     */   public static final String ORGREF_COLUMN_NAME = "TE_ORGREF";
/*     */   public static final String ORGREF_COLUMN_ALIAS = "Exch_OrgRef";
/*     */   public static final String RIGHTS_COLUMN_NAME = "TE_RIGHTS";
/*     */   public static final String RIGHTS_COLUMN_ALIAS = "Exch_Rights";
/* 115 */   public static final String[] GROUPING_COLUMNS = new String[] { "TE_WPIID", "TE_RIGHTS", "TE_LOCKED", "TE_DOMAINID", "UUID" };
/*     */   public static final String PRIMARY_KEY_COL_VAR = "PrimaryKeyColumn";
/*     */   public static final String EASY_CUST_BO = "EasyCustBO";
/*     */   public static final String LABELS_COLUMN_VALUE = "LabelsValue";
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\IDBField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */