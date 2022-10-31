/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.data.factory.ICacheKey;
/*     */ import com.lbs.data.factory.ISubstitutionVariables;
/*     */ import com.lbs.data.factory.NamedVariables;
/*     */ import com.lbs.data.objects.ILbsRttiCachable;
/*     */ import com.lbs.data.xstream.ILbsXStreamListener;
/*     */ import com.lbs.util.EnabledList;
/*     */ import com.lbs.util.NegatedList;
/*     */ import com.lbs.util.ObjectUtil;
/*     */ import com.lbs.util.SetUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import com.thoughtworks.xstream.annotations.XStreamImplicit;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
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
/*     */ public class QueryParams
/*     */   implements Serializable, Cloneable, Externalizable, ILbsXStreamListener, ICacheKey, ILbsRttiCachable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int OPTION_NONE = 0;
/*     */   public static final int OPTION_FORCE_SUBQUERY_ROWNUM = 1;
/*     */   public static final int OPTION_FORCE_WHERE_ROWNUM = 2;
/*     */   public static final int OPTION_FORCE_NO_HINT = 4;
/*     */   public static final int OPTION_AUTO_AUTHORIZATION = 8;
/*     */   public static final int OPTION_NO_ORDER = 16;
/*     */   public static final int OPTION_NO_SQL_ROWLIMIT = 32;
/*     */   public static final int OPTION_NO_JOINS = 64;
/*     */   public static final int OPTION_ONLY_KEYS = 128;
/*     */   public static final int OPTION_BUILD_GLOBALORD = 256;
/*     */   public static final int OPTION_ESCAPE_PROCESSOR = 512;
/*     */   public static final int OPTION_READ_CURSOR_AT_ONCE = 1024;
/*     */   public static final int OPTION_DOMAINLESS = 2048;
/*     */   public static final int OPTION_DOMAIN_AWARE = 4096;
/*     */   public static final int OPTION_IS_SUBQUERY = 8192;
/*     */   public static final int OPTION_NOT_CAPITALIZE_SUBQUERY = 16384;
/*     */   public static final int OPTION_SKIP_RECORD_LOG = 32768;
/*     */   public static final int OPTION_EVAL_OBJ_MODE = 65536;
/*     */   public static final int OPTION_FIRST_BY_KEY = 131072;
/*     */   public static final int OPTION_DO_NOT_READ_ENCRYPTED = 262144;
/*     */   public static final int OPTION_MULTILANG = 524288;
/*     */   public static final int OPTION_MV_QUERY = 1048576;
/*     */   public static final int OPTION_DISTINCT = 2097152;
/*     */   public static final int OPTION_DISABLE_ROW_LIMIT = 4194304;
/*     */   private static final String TERM_SEPARATOR = ";";
/*     */   @XStreamAlias("Variables")
/*  70 */   private ISubstitutionVariables m_Variables = null;
/*     */   @XStreamAlias("Parameters")
/*  72 */   private NamedVariables m_Parameters = null;
/*     */   
/*     */   @XStreamAlias("MainTableParams")
/*     */   protected QueryTableParam m_MainTableParams;
/*     */   
/*     */   @XStreamAlias("EnabledTableLinks")
/*     */   protected QueryTableParams m_EnabledTableLinks;
/*     */   
/*     */   @XStreamAlias("SearchParams")
/*     */   protected QuerySearchParams m_SearchParams;
/*     */   @XStreamAlias("FilterParams")
/*     */   protected QueryFilterTerms m_FilterTerms;
/*     */   @XStreamAlias("EnabledTerms")
/*     */   protected EnabledList m_EnabledTerms;
/*     */   @XStreamAlias("NegatedTerms")
/*     */   protected NegatedList m_NegatedTerms;
/*     */   @XStreamAlias("EnabledUnions")
/*     */   protected EnabledList m_EnabledUnions;
/*     */   @XStreamAlias("ActiveLookupTerm")
/*     */   protected String m_ActiveLookupTerm;
/*     */   @XStreamAlias("GlobalOrderName")
/*     */   protected String m_GlobalOrderName;
/*     */   @XStreamAlias("UserOrder")
/*     */   protected String m_UserOrder;
/*     */   @XStreamAlias("Customization")
/*     */   protected String m_Customization;
/*     */   @XStreamAlias("AuthorizationTerms")
/*     */   private String m_AuthorizationTerms;
/*     */   @XStreamAlias("TableOrders")
/*     */   @XStreamImplicit(itemFieldName = "item")
/*     */   protected String[] m_TableOrder;
/*     */   @XStreamAlias("UserOrderTag")
/*     */   protected int m_UserOrderTag;
/*     */   @XStreamAlias("GridOrderTag")
/*     */   protected int m_GridOrderTag;
/*     */   @XStreamAlias("MaxRowCount")
/* 108 */   protected int m_MaxRowCount = -1;
/*     */   @XStreamAlias("AuthModeID")
/* 110 */   private int m_AuthModeID = -1;
/*     */   @XStreamAlias("AuthUserID")
/* 112 */   private int m_AuthUserID = 0;
/*     */   @XStreamAlias("Options")
/* 114 */   private int m_Options = 0;
/*     */   
/*     */   @XStreamAlias("CacheObjectName")
/*     */   private String m_CacheObjectName;
/*     */   
/*     */   @XStreamAlias("EnableModifiedBy")
/*     */   private boolean m_EnableModifiedBy = true;
/*     */   
/*     */   private transient boolean m_LogSeperately = false;
/* 123 */   private transient int m_KeyHashValue = DO_NOT_CACHE.intValue();
/*     */ 
/*     */   
/*     */   public QueryParams() {
/* 127 */     afterXStreamDeserialization();
/*     */   }
/*     */ 
/*     */   
/*     */   public void afterXStreamDeserialization() {
/* 132 */     if (this.m_EnabledTerms == null) {
/* 133 */       this.m_EnabledTerms = new EnabledList();
/*     */     } else {
/* 135 */       this.m_EnabledTerms.afterXStreamDeserialization();
/* 136 */     }  if (this.m_NegatedTerms == null) {
/* 137 */       this.m_NegatedTerms = new NegatedList();
/*     */     } else {
/* 139 */       this.m_NegatedTerms.afterXStreamDeserialization();
/* 140 */     }  if (this.m_MainTableParams == null) {
/* 141 */       this.m_MainTableParams = new QueryTableParam(null);
/*     */     } else {
/* 143 */       this.m_MainTableParams.afterXStreamDeserialization();
/* 144 */     }  if (this.m_EnabledTableLinks == null) {
/* 145 */       this.m_EnabledTableLinks = new QueryTableParams();
/*     */     } else {
/* 147 */       this.m_EnabledTableLinks.afterXStreamDeserialization();
/* 148 */     }  if (this.m_Parameters == null) {
/* 149 */       this.m_Parameters = new NamedVariables();
/*     */     } else {
/* 151 */       this.m_Parameters.afterXStreamDeserialization();
/* 152 */     }  if (this.m_FilterTerms == null) {
/* 153 */       this.m_FilterTerms = new QueryFilterTerms();
/*     */     } else {
/* 155 */       this.m_FilterTerms.afterXStreamDeserialization();
/* 156 */     }  if (this.m_Variables == null)
/* 157 */       this.m_Variables = (ISubstitutionVariables)new NamedVariables(); 
/* 158 */     if (this.m_EnabledUnions == null) {
/* 159 */       this.m_EnabledUnions = new EnabledList();
/*     */     } else {
/* 161 */       this.m_EnabledUnions.afterXStreamDeserialization();
/* 162 */     }  this.m_GlobalOrderName = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnabledList getEnabledTerms() {
/* 170 */     return this.m_EnabledTerms;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnabledList getEnabledUnions() {
/* 175 */     return this.m_EnabledUnions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NamedVariables getParameters() {
/* 183 */     return this.m_Parameters;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParameters(NamedVariables parameters) {
/* 188 */     this.m_Parameters = parameters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ISubstitutionVariables getVariables() {
/* 196 */     return this.m_Variables;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVariables(ISubstitutionVariables variables) {
/* 204 */     this.m_Variables = variables;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 214 */       Object obj = super.clone();
/* 215 */       ObjectUtil.deepCopy(this, obj, QueryParams.class);
/* 216 */       return obj;
/*     */     }
/* 218 */     catch (Exception e) {
/*     */       
/* 220 */       LbsConsole.getLogger("Data.Client.QueryParams").error("[QueryParams.clone()]", e);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 228 */       throw new AssertionError();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryTableParam getMainTableParams() {
/* 234 */     return this.m_MainTableParams;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryTableParams getEnabledTableLinks() {
/* 239 */     return this.m_EnabledTableLinks;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getActiveLookupTerm() {
/* 244 */     return this.m_ActiveLookupTerm;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActiveLookupTerm(String string) {
/* 249 */     this.m_ActiveLookupTerm = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMainTableParams(QueryTableParam param) {
/* 254 */     this.m_MainTableParams = param;
/*     */   }
/*     */ 
/*     */   
/*     */   public NegatedList getNegatedTerms() {
/* 259 */     return this.m_NegatedTerms;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGlobalOrderName() {
/* 264 */     return this.m_GlobalOrderName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOrder(String orderName, boolean descending) {
/* 270 */     setOrderParams(new QueryOrderParams(orderName), descending);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOrderParams(QueryOrderParams orderParams, boolean descending) {
/* 275 */     if (orderParams == null)
/*     */       return; 
/* 277 */     setGlobalOrderName(orderParams.getGlobalOrderName());
/* 278 */     toggleOption(256, orderParams.isBuildGlobalOrderFromSubOrders());
/* 279 */     ArrayList<String> orders = new ArrayList<>();
/*     */     
/* 281 */     GridQuerySubOrder[] subOrders = orderParams.getOrders();
/*     */     
/* 283 */     for (int i = 0; i < subOrders.length; i++) {
/*     */       
/* 285 */       GridQuerySubOrder subOrder = subOrders[i];
/*     */       
/* 287 */       if (StringUtil.isEmpty(subOrder.getTableAlias()) || 
/* 288 */         StringUtil.equals(this.m_MainTableParams.getAlias(), subOrder.getTableAlias())) {
/*     */         
/* 290 */         this.m_MainTableParams.setOrder(subOrder.getOrderName(), descending);
/* 291 */         orders.add("");
/*     */       }
/*     */       else {
/*     */         
/* 295 */         QueryTableParam params = this.m_EnabledTableLinks.getParams(subOrder.getTableAlias());
/* 296 */         params.setOrder(subOrder.getOrderName(), descending);
/* 297 */         orders.add(subOrder.getTableAlias());
/*     */       } 
/*     */     } 
/* 300 */     if (orderParams.isBuildGlobalOrderFromSubOrders()) {
/*     */       
/* 302 */       this.m_TableOrder = orders.<String>toArray(new String[orders.size()]);
/*     */     } else {
/*     */       
/* 305 */       this.m_TableOrder = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setGlobalOrderName(String orderName) {
/* 310 */     this.m_GlobalOrderName = orderName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUserOrder() {
/* 315 */     return this.m_UserOrder;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUserOrder(String string) {
/* 320 */     this.m_UserOrder = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUserOrderTag() {
/* 325 */     return this.m_UserOrderTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUserOrderTag(int tag) {
/* 330 */     this.m_UserOrderTag = tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getGridOrderTag() {
/* 335 */     return this.m_GridOrderTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGridOrderTag(int tag) {
/* 340 */     this.m_GridOrderTag = tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCustomization() {
/* 345 */     return this.m_Customization;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomization(String guid) {
/* 350 */     this.m_Customization = guid;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 355 */     return this.m_Options;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 360 */     this.m_Options = options;
/*     */   }
/*     */ 
/*     */   
/*     */   public void toggleOption(int option, boolean set) {
/* 365 */     this.m_Options = SetUtil.toggleOption(this.m_Options, option, set);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasOption(int option) {
/* 370 */     return SetUtil.isOptionSet(this.m_Options, option);
/*     */   }
/*     */ 
/*     */   
/*     */   public QuerySearchParams getSearchParams() {
/* 375 */     return this.m_SearchParams;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSearchParams(QuerySearchParams searchParams) {
/* 380 */     this.m_SearchParams = searchParams;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryFilterTerms getFilterTerms() {
/* 385 */     return this.m_FilterTerms;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabledTerms(EnabledList enabledTerms) {
/* 390 */     if (enabledTerms != null) {
/* 391 */       this.m_EnabledTerms = enabledTerms;
/*     */     } else {
/* 393 */       this.m_EnabledTerms = new EnabledList();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setEnabledUnions(EnabledList enabledUnions) {
/* 398 */     if (this.m_EnabledTerms != null) {
/* 399 */       this.m_EnabledUnions = enabledUnions;
/*     */     } else {
/* 401 */       this.m_EnabledUnions = new EnabledList();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setFilterTerms(QueryFilterTerms filterTerms) {
/* 406 */     if (filterTerms != null) {
/* 407 */       this.m_FilterTerms = filterTerms;
/*     */     } else {
/* 409 */       this.m_FilterTerms = new QueryFilterTerms();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String[] getAuthorizationTerms() {
/* 414 */     if (StringUtil.isEmpty(this.m_AuthorizationTerms))
/* 415 */       return new String[0]; 
/* 416 */     return StringUtil.split(this.m_AuthorizationTerms, ";");
/*     */   }
/*     */ 
/*     */   
/*     */   public void addAuthorizationTerm(String termName) {
/* 421 */     if (StringUtil.isEmpty(this.m_AuthorizationTerms)) {
/* 422 */       this.m_AuthorizationTerms = termName;
/*     */     } else {
/* 424 */       this.m_AuthorizationTerms += ";" + termName;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setAuthModeID(int authModeID) {
/* 429 */     this.m_AuthModeID = authModeID;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAuthModeID() {
/* 434 */     return this.m_AuthModeID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAuthUserID(int authUserID) {
/* 439 */     this.m_AuthUserID = authUserID;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAuthUserID() {
/* 444 */     return this.m_AuthUserID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxRowCount() {
/* 452 */     return this.m_MaxRowCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxRowCount(int maxRowCount) {
/* 460 */     this.m_MaxRowCount = maxRowCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLogSeperately() {
/* 465 */     return this.m_LogSeperately;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLogSeperately(boolean logSeperately) {
/* 470 */     this.m_LogSeperately = logSeperately;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDomainless(boolean domainless) {
/* 475 */     if (domainless) {
/* 476 */       this.m_Options = SetUtil.setOption(this.m_Options, 2048);
/*     */     } else {
/* 478 */       this.m_Options = SetUtil.resetOption(this.m_Options, 2048);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setSqlDomainAware(boolean domainAware) {
/* 483 */     if (domainAware) {
/* 484 */       this.m_Options = SetUtil.setOption(this.m_Options, 4096);
/*     */     } else {
/* 486 */       this.m_Options = SetUtil.resetOption(this.m_Options, 4096);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 491 */     this.m_Variables = (ISubstitutionVariables)in.readObject();
/* 492 */     this.m_Parameters.readExternal(in);
/*     */     
/* 494 */     this.m_MainTableParams.readExternal(in);
/* 495 */     this.m_EnabledTableLinks.readExternal(in);
/* 496 */     this.m_SearchParams = (QuerySearchParams)in.readObject();
/* 497 */     this.m_FilterTerms.readExternal(in);
/* 498 */     this.m_EnabledTerms.readExternal(in);
/* 499 */     this.m_NegatedTerms.readExternal(in);
/*     */     
/* 501 */     this.m_ActiveLookupTerm = (String)in.readObject();
/* 502 */     this.m_GlobalOrderName = (String)in.readObject();
/* 503 */     this.m_UserOrder = (String)in.readObject();
/* 504 */     this.m_Customization = (String)in.readObject();
/* 505 */     this.m_AuthorizationTerms = (String)in.readObject();
/*     */     
/* 507 */     int length = in.readInt();
/* 508 */     if (length == 0) {
/*     */       
/* 510 */       this.m_TableOrder = null;
/*     */     }
/*     */     else {
/*     */       
/* 514 */       this.m_TableOrder = new String[length];
/* 515 */       for (int i = 0; i < length; i++)
/* 516 */         this.m_TableOrder[i] = (String)in.readObject(); 
/*     */     } 
/* 518 */     this.m_UserOrderTag = in.readInt();
/* 519 */     this.m_GridOrderTag = in.readInt();
/* 520 */     this.m_MaxRowCount = in.readInt();
/* 521 */     this.m_AuthModeID = in.readInt();
/* 522 */     this.m_AuthUserID = in.readInt();
/* 523 */     this.m_Options = in.readInt();
/* 524 */     this.m_EnabledUnions.readExternal(in);
/* 525 */     this.m_EnableModifiedBy = in.readBoolean();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 530 */     out.writeObject(this.m_Variables);
/* 531 */     this.m_Parameters.writeExternal(out);
/*     */     
/* 533 */     this.m_MainTableParams.writeExternal(out);
/* 534 */     this.m_EnabledTableLinks.writeExternal(out);
/* 535 */     out.writeObject(this.m_SearchParams);
/* 536 */     this.m_FilterTerms.writeExternal(out);
/* 537 */     this.m_EnabledTerms.writeExternal(out);
/* 538 */     this.m_NegatedTerms.writeExternal(out);
/*     */     
/* 540 */     out.writeObject(this.m_ActiveLookupTerm);
/* 541 */     out.writeObject(this.m_GlobalOrderName);
/* 542 */     out.writeObject(this.m_UserOrder);
/* 543 */     out.writeObject(this.m_Customization);
/* 544 */     out.writeObject(this.m_AuthorizationTerms);
/*     */     
/* 546 */     if (this.m_TableOrder == null) {
/*     */       
/* 548 */       out.writeInt(0);
/*     */     }
/*     */     else {
/*     */       
/* 552 */       out.writeInt(this.m_TableOrder.length);
/* 553 */       for (int i = 0; i < this.m_TableOrder.length; i++)
/* 554 */         out.writeObject(this.m_TableOrder[i]); 
/*     */     } 
/* 556 */     out.writeInt(this.m_UserOrderTag);
/* 557 */     out.writeInt(this.m_GridOrderTag);
/* 558 */     out.writeInt(this.m_MaxRowCount);
/* 559 */     out.writeInt(this.m_AuthModeID);
/* 560 */     out.writeInt(this.m_AuthUserID);
/* 561 */     out.writeInt(this.m_Options);
/* 562 */     this.m_EnabledUnions.writeExternal(out);
/* 563 */     out.writeBoolean(this.m_EnableModifiedBy);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnableModifiedBy() {
/* 568 */     return this.m_EnableModifiedBy;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnableModifiedBy(boolean enableModifiedBy) {
/* 573 */     this.m_EnableModifiedBy = enableModifiedBy;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer toKeyValue() {
/* 579 */     return Integer.valueOf(this.m_KeyHashValue);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCacheObjectName() {
/* 585 */     return this.m_CacheObjectName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void setCacheObjectName(String objName) {
/* 591 */     this.m_CacheObjectName = objName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCacheKeyValue_(int keyValue) {
/* 597 */     this.m_KeyHashValue = keyValue;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */