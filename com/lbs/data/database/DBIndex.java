/*     */ package com.lbs.data.database;
/*     */ 
/*     */ import com.lbs.data.IStringListDescription;
/*     */ import com.lbs.data.expression.QueryItem;
/*     */ import com.lbs.data.factory.INamedVariables;
/*     */ import com.lbs.data.factory.ISubstitutionListener;
/*     */ import com.lbs.util.StringUtil;
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
/*     */ 
/*     */ 
/*     */ public class DBIndex
/*     */   extends DBEntityItem
/*     */   implements Serializable, IStringListDescription
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private DBIndexSegments m_Segments;
/*     */   private boolean m_Unique;
/*     */   private boolean m_Primary;
/*     */   private boolean m_Secondary;
/*     */   private boolean m_UUIDIndex = false;
/*  30 */   protected Long m_HashValue = null;
/*     */ 
/*     */   
/*  33 */   private String m_Suffix = "0";
/*     */ 
/*     */   
/*  36 */   private int m_Type = 0;
/*     */ 
/*     */   
/*     */   public DBIndex() {
/*  40 */     super(2);
/*  41 */     this.m_Segments = new DBIndexSegments(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public DBIndex(DBTable table, String name, String physicalName, String[] segmentNames) {
/*  46 */     this();
/*     */     
/*  48 */     setName(name);
/*  49 */     setPhysicalName(physicalName);
/*     */     
/*  51 */     DBFields fields = table.getFields();
/*     */     
/*  53 */     for (int i = 0; i < segmentNames.length; i++) {
/*     */       
/*  55 */       DBField field = fields.get(segmentNames[i]);
/*     */       
/*  57 */       if (field == null) {
/*  58 */         throw new IllegalArgumentException("Segment column '" + segmentNames[i] + "' not found!");
/*     */       }
/*  60 */       this.m_Segments.add(new DBIndexSegment(field));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public DBTable getTable() {
/*  66 */     return (DBTable)getCollectionOwner();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DBIndexSegments getSegments() {
/*  75 */     return this.m_Segments;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPrimary() {
/*  84 */     return this.m_Primary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUnique() {
/*  93 */     return this.m_Unique;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSecondaryKey() {
/*  98 */     return this.m_Secondary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrimary(boolean primary) {
/* 107 */     if (primary) {
/*     */       
/* 109 */       DBTable table = getTable();
/* 110 */       if (table != null) {
/* 111 */         table.setKeyIndex(this);
/*     */       }
/*     */     } 
/* 114 */     this.m_Primary = primary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnique(boolean unique) {
/* 123 */     this.m_Unique = unique;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSecondaryKey(boolean secondary) {
/* 128 */     if (secondary) {
/*     */       
/* 130 */       DBTable table = getTable();
/* 131 */       if (table != null) {
/* 132 */         table.setSecondaryKeyIndex(this);
/*     */       }
/*     */     } 
/* 135 */     this.m_Secondary = secondary;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isForcedUnique() {
/* 140 */     if (this.m_Segments.size() > 1) {
/*     */       
/* 142 */       DBField field = this.m_Segments.item(this.m_Segments.size() - 1).getField();
/*     */       
/* 144 */       return (field != null && field.isPrimaryKey());
/*     */     } 
/*     */     
/* 147 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void changeName(INameProducer namer, INamedVariables variables, ISubstitutionListener listener, Object dbConnection) {
/* 155 */     super.changeName(namer, variables, listener, dbConnection);
/* 156 */     this.m_Segments.changeNames(namer, variables, listener, dbConnection);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 164 */     DBIndex cloneIndex = (DBIndex)super.clone();
/*     */     
/* 166 */     cloneIndex.m_Segments = new DBIndexSegments(cloneIndex);
/*     */     
/* 168 */     for (int i = 0; i < this.m_Segments.size(); i++) {
/* 169 */       cloneIndex.m_Segments.add((DBIndexSegment)this.m_Segments.item(i).clone());
/*     */     }
/* 171 */     return cloneIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryItem getAccessItem() {
/* 176 */     return new QueryItem(101, this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 182 */     DBTable table = getTable();
/*     */     
/* 184 */     if (StringUtil.isEmpty(table.getGuid())) {
/* 185 */       return "$Z(" + table.getName() + "." + this.m_Name + ")";
/*     */     }
/* 187 */     return "$Z(" + table.getGuid() + "-" + table.getName() + "." + this.m_Name + ")";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getListID() {
/* 192 */     DBTable table = getTable();
/*     */     
/* 194 */     if (table == null) {
/* 195 */       return -1;
/*     */     }
/* 197 */     return table.getListID();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSuffix() {
/* 202 */     return this.m_Suffix;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSuffix(String suffix) {
/* 207 */     this.m_Suffix = suffix;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getNameFromSuffix(String suffix, IDBTable owner) {
/* 212 */     if (owner != null)
/* 213 */       return owner.getName() + "_" + suffix; 
/* 214 */     return suffix;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getPhysicalNameFromSuffix(String suffix, IDBTable owner) {
/* 219 */     if (owner != null)
/* 220 */       return owner.getPhysicalName() + "_" + suffix; 
/* 221 */     return suffix;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(int idxType) {
/* 226 */     this.m_Type = idxType;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getType() {
/* 231 */     return this.m_Type;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUUIDIndex() {
/* 236 */     return this.m_UUIDIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUUIDIndex(boolean uUIDIndex) {
/* 241 */     this.m_UUIDIndex = uUIDIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getHashValue() {
/* 246 */     return this.m_HashValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHashValue(Long hashValue) {
/* 251 */     this.m_HashValue = hashValue;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\DBIndex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */