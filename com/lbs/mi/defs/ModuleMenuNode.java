/*     */ package com.lbs.mi.defs;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ModuleMenuNode
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int m_ModuleId;
/*     */   private int m_Id;
/*     */   private int m_ActionId;
/*  14 */   private String m_Code = "";
/*  15 */   private String m_Name = "";
/*  16 */   private String m_ResourceTitle = "";
/*     */   private ModuleAction m_Action;
/*     */   private boolean m_Custom = false;
/*     */   private boolean m_Anonymous = false;
/*     */   private boolean m_Web = true;
/*  21 */   private MaturityLevel m_MaturityLevel = MaturityLevel.release_candidate;
/*     */   private transient int m_Selected;
/*     */   private boolean m_Missing = false;
/*     */   private boolean m_NotExport = false;
/*     */   private boolean m_Mobile = false;
/*  26 */   private int m_ExportOrder = 0;
/*  27 */   private String m_IconName = "";
/*  28 */   private String m_Type = "";
/*  29 */   private int m_ParentTreeId = 0;
/*  30 */   private String m_Description = "";
/*     */   
/*  32 */   private HashMap<String, Object> m_WidgetDefList = new HashMap<>();
/*     */   
/*  34 */   private transient HashMap<String, String> languageDescriptions = new HashMap<>();
/*     */   private ModuleMenuNode[] m_Children;
/*     */   
/*     */   public boolean isMobile() {
/*  38 */     if (this.m_Mobile) {
/*  39 */       return true;
/*     */     }
/*  41 */     if (this.m_Children != null && this.m_Children.length > 0) {
/*     */       
/*  43 */       boolean flag = false;
/*  44 */       for (int i = 0; i < this.m_Children.length; i++)
/*     */       {
/*  46 */         flag |= this.m_Children[i].isMobile();
/*     */       }
/*  48 */       return flag;
/*     */     } 
/*     */     
/*  51 */     return this.m_Mobile;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addWidget(String key, Object obj) {
/*  56 */     this.m_WidgetDefList.put(key, obj);
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<String, Object> getWidgetDefList() {
/*  61 */     return this.m_WidgetDefList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWidgetDefList(HashMap<String, Object> widgetDefList) {
/*  66 */     this.m_WidgetDefList = widgetDefList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMobile(boolean mobile) {
/*  71 */     this.m_Mobile = mobile;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExportOrder(int exportOrder) {
/*  76 */     this.m_ExportOrder = exportOrder;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExportOrder() {
/*  81 */     return this.m_ExportOrder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModuleMenuNode() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModuleMenuNode(ModuleMenuNode node, boolean copyChildren) {
/*  95 */     this.m_ModuleId = node.getModuleId();
/*  96 */     this.m_Id = node.getId();
/*  97 */     this.m_ActionId = node.getActionId();
/*  98 */     this.m_Code = node.getCode();
/*  99 */     this.m_Name = node.getName();
/* 100 */     this.m_ResourceTitle = node.getResourceTitle();
/* 101 */     this.m_Action = node.getAction();
/* 102 */     this.m_Custom = node.m_Custom;
/* 103 */     this.m_MaturityLevel = node.m_MaturityLevel;
/* 104 */     this.m_Missing = node.m_Missing;
/* 105 */     this.m_NotExport = node.m_NotExport;
/* 106 */     this.m_Mobile = node.m_Mobile;
/* 107 */     this.m_ExportOrder = node.m_ExportOrder;
/* 108 */     this.m_WidgetDefList = node.m_WidgetDefList;
/* 109 */     this.m_IconName = node.m_IconName;
/* 110 */     this.m_Type = node.m_Type;
/* 111 */     this.m_Description = node.getDescription();
/* 112 */     this.m_ParentTreeId = node.m_ParentTreeId;
/*     */     
/* 114 */     if (copyChildren) {
/*     */       
/* 116 */       ModuleMenuNode[] sourceChl = node.getChildren();
/* 117 */       if (sourceChl != null) {
/*     */         
/* 119 */         this.m_Children = new ModuleMenuNode[sourceChl.length];
/* 120 */         for (int i = 0; i < sourceChl.length; i++) {
/* 121 */           this.m_Children[i] = new ModuleMenuNode(sourceChl[i]);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public ModuleMenuNode(ModuleMenuNode node) {
/* 128 */     this(node, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModuleAction getAction() {
/* 133 */     return this.m_Action;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getActionId() {
/* 138 */     return this.m_ActionId;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModuleMenuNode[] getChildren() {
/* 143 */     return this.m_Children;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCode() {
/* 148 */     return this.m_Code;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/* 153 */     return this.m_Id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 158 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCaption() {
/* 163 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAction(ModuleAction action) {
/* 168 */     this.m_Action = action;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActionId(int i) {
/* 173 */     this.m_ActionId = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChildren(ModuleMenuNode[] nodes) {
/* 178 */     this.m_Children = nodes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCode(String string) {
/* 183 */     this.m_Code = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int i) {
/* 188 */     this.m_Id = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 193 */     this.m_Name = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceTitle() {
/* 198 */     return this.m_ResourceTitle;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setResourceTitle(String title) {
/* 203 */     this.m_ResourceTitle = title;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustom(boolean custom) {
/* 208 */     this.m_Custom = custom;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCustom() {
/* 213 */     return this.m_Custom;
/*     */   }
/*     */ 
/*     */   
/*     */   public MaturityLevel getMaturityLevel() {
/* 218 */     return this.m_MaturityLevel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaturityLevel(MaturityLevel maturityLevel) {
/* 223 */     this.m_MaturityLevel = maturityLevel;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelected() {
/* 228 */     return this.m_Selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelected(int selected) {
/* 233 */     this.m_Selected = selected;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAnonymous(boolean anonymous) {
/* 241 */     this.m_Anonymous = anonymous;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnonymous() {
/* 249 */     return this.m_Anonymous;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWeb() {
/* 254 */     return this.m_Web;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWeb(boolean web) {
/* 259 */     this.m_Web = web;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMissing(boolean missing) {
/* 267 */     this.m_Missing = missing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMissing() {
/* 275 */     return this.m_Missing;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNotExport(boolean notExport) {
/* 280 */     this.m_NotExport = notExport;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNotExport() {
/* 285 */     return this.m_NotExport;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModuleId(int moduleId) {
/* 290 */     this.m_ModuleId = moduleId;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getModuleId() {
/* 295 */     return this.m_ModuleId;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAllCount() {
/* 300 */     if (this.m_Children != null)
/* 301 */       return getChildCount(this.m_Children); 
/* 302 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getChildCount(ModuleMenuNode[] children) {
/* 307 */     int count = 0;
/* 308 */     for (int i = 0; i < children.length; i++) {
/*     */       
/* 310 */       if (children[i].getChildren() != null)
/*     */       {
/* 312 */         count += getChildCount(children[i].getChildren());
/*     */       }
/* 314 */       count++;
/*     */     } 
/* 316 */     return count;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIconName() {
/* 321 */     return this.m_IconName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIconName(String iconName) {
/* 326 */     this.m_IconName = iconName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/* 331 */     return this.m_Type;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(String type) {
/* 336 */     this.m_Type = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getParentTreeId() {
/* 341 */     return this.m_ParentTreeId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParentTreeId(int parentTreeId) {
/* 346 */     this.m_ParentTreeId = parentTreeId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 351 */     return this.m_Description;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/* 356 */     this.m_Description = description;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, String> getLanguageDescriptions() {
/* 361 */     return this.languageDescriptions;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mi\defs\ModuleMenuNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */