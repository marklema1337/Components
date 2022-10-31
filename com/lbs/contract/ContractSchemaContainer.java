/*     */ package com.lbs.contract;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.contract.execution.ParameterMapping;
/*     */ import com.lbs.platform.interfaces.ICacheManager;
/*     */ import com.lbs.platform.interfaces.ICachedHashTable;
/*     */ import com.lbs.platform.interfaces.ICachedList;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
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
/*     */ public class ContractSchemaContainer
/*     */ {
/*     */   private static boolean ms_NoVerify = false;
/*     */   private static final String CONTRACT_IMPL_GROUPS = "ContractImplGroups";
/*     */   private static final String CONTRACT_IMPL_TEMPLATES = "ContractImplTemplates";
/*  34 */   private static final LbsConsole ms_Logger = LbsConsole.getLogger(ContractSchemaContainer.class);
/*     */   
/*     */   private final ICachedHashTable<String, ContractSchema> m_Contracts;
/*     */   
/*     */   private final ICachedHashTable<String, ContractImplementation> m_ContractImpls;
/*     */   
/*     */   private final ICachedHashTable<String, String> m_ContractImplMap;
/*     */   
/*     */   private final ICachedHashTable<String, ParameterMapping> m_ParameterMappings;
/*     */   
/*     */   private ICachedHashTable<String, ContractImplTemplate> m_ImplTemplates;
/*     */   private ICachedList<ContractImplGroup> m_ImplGroups;
/*     */   private final ICacheManager m_CacheManager;
/*     */   private String m_Guid;
/*  48 */   private Hashtable<String, ArrayList<String>> ms_LookupParametersByBrowserName = new Hashtable<>();
/*     */ 
/*     */   
/*     */   ContractSchemaContainer(ICacheManager cacheManager, String guid) {
/*  52 */     this.m_CacheManager = cacheManager;
/*  53 */     this.m_Guid = guid;
/*  54 */     this.m_Contracts = cacheManager.getCachedHashTable("ContractRegistry" + guid, String.class, ContractSchema.class, false);
/*  55 */     this.m_ContractImpls = cacheManager.getCachedHashTable("ContractImplementations" + guid, String.class, 
/*  56 */         ContractImplementation.class, false);
/*  57 */     this.m_ContractImplMap = cacheManager.getCachedHashTable("ContractImplMap" + guid, String.class, String.class, false);
/*  58 */     this.m_ParameterMappings = cacheManager.getCachedHashTable("ParameterMappings" + guid, String.class, ParameterMapping.class, 
/*  59 */         false);
/*     */     
/*  61 */     this.m_ImplTemplates = cacheManager.getCachedHashTable("ContractImplTemplates" + guid, String.class, ContractImplTemplate.class, 
/*  62 */         false);
/*  63 */     this.m_ImplGroups = cacheManager.getCachedList("ContractImplGroups" + guid, ContractImplGroup.class, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void debugMessage(Object message) {
/*  68 */     ms_Logger.debug(message);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  73 */     if (this.m_Contracts != null) {
/*  74 */       this.m_Contracts.clear();
/*     */     }
/*  76 */     if (this.m_ContractImpls != null) {
/*  77 */       this.m_ContractImpls.clear();
/*     */     }
/*  79 */     if (this.m_ContractImplMap != null) {
/*  80 */       this.m_ContractImplMap.clear();
/*     */     }
/*  82 */     if (this.m_ParameterMappings != null) {
/*  83 */       this.m_ParameterMappings.clear();
/*     */     }
/*  85 */     if (this.m_ImplTemplates != null) {
/*  86 */       this.m_ImplTemplates.clear();
/*     */     }
/*  88 */     if (this.m_ImplGroups != null) {
/*  89 */       this.m_ImplGroups.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   public void registerContract(ContractSchema contract) {
/*  94 */     this.m_Contracts.put(contract.getIdentifier().getId(), contract);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsContract(String id) {
/*  99 */     return this.m_Contracts.containsKey(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public ContractSchema getContract(String id) {
/* 104 */     return (ContractSchema)this.m_Contracts.get(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public ICachedHashTable<String, ContractSchema> getContracts() {
/* 109 */     return this.m_Contracts;
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerContractImplementation(ContractImplementation implementation) {
/* 114 */     this.m_ContractImpls.put(String.valueOf(implementation.getCategory()) + "~" + implementation.getIdentifier(), implementation);
/*     */   }
/*     */ 
/*     */   
/*     */   public ContractImplementation getContractImplementation(String contractId, String category) {
/* 119 */     return (ContractImplementation)this.m_ContractImpls.get(String.valueOf(category) + "~" + contractId);
/*     */   }
/*     */ 
/*     */   
/*     */   public ICachedHashTable<String, ContractImplementation> getContractImplementations() {
/* 124 */     return this.m_ContractImpls;
/*     */   }
/*     */ 
/*     */   
/*     */   public void mapContractImplementation(String contractId, String category) {
/* 129 */     this.m_ContractImplMap.put(contractId, category);
/*     */   }
/*     */ 
/*     */   
/*     */   public ContractImplementation getCurrentImplementation(String contractId) {
/* 134 */     String category = (String)this.m_ContractImplMap.get(contractId);
/* 135 */     if (!StringUtil.isEmpty(category))
/*     */     {
/* 137 */       return getContractImplementation(contractId, category);
/*     */     }
/*     */ 
/*     */     
/* 141 */     ContractSchema contract = getContract(contractId);
/* 142 */     if (contract != null) {
/*     */       
/* 144 */       category = (String)this.m_ContractImplMap.get(contract.getModule());
/* 145 */       if (!StringUtil.isEmpty(category)) {
/* 146 */         return getContractImplementation(contractId, category);
/*     */       }
/*     */     } 
/* 149 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerParameterMapping(ParameterMapping mapping) {
/* 154 */     this.m_ParameterMappings.put(mapping.getParameterId(), mapping);
/*     */   }
/*     */ 
/*     */   
/*     */   public ParameterMapping getParameterMapping(String parameterId) {
/* 159 */     return (ParameterMapping)this.m_ParameterMappings.get(parameterId);
/*     */   }
/*     */ 
/*     */   
/*     */   void addImplementationTemplate(ContractImplTemplate template) {
/* 164 */     this.m_ImplTemplates.put(template.getName(), template);
/*     */   }
/*     */ 
/*     */   
/*     */   ContractImplTemplate findTemplate(String templateName) {
/* 169 */     return (ContractImplTemplate)this.m_ImplTemplates.get(templateName);
/*     */   }
/*     */ 
/*     */   
/*     */   void addImplementationGroup(ContractImplGroup group) {
/* 174 */     this.m_ImplGroups.add(group);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialize() throws ContractException {
/* 179 */     ArrayList<String> processedTemplates = new ArrayList<>();
/* 180 */     Enumeration<String> keys = this.m_ImplTemplates.keys();
/* 181 */     while (keys.hasMoreElements()) {
/*     */       
/* 183 */       String key = keys.nextElement();
/* 184 */       processForTemplates(processedTemplates, key);
/*     */     } 
/* 186 */     for (int i = 0; i < this.m_ImplGroups.size(); i++) {
/*     */       
/* 188 */       ContractImplGroup group = (ContractImplGroup)this.m_ImplGroups.get(i);
/* 189 */       group.initialize();
/*     */     } 
/*     */     
/* 192 */     keys = this.m_Contracts.keys();
/* 193 */     while (keys.hasMoreElements()) {
/*     */       
/* 195 */       String key = keys.nextElement();
/* 196 */       ContractSchema contract = (ContractSchema)this.m_Contracts.get(key);
/* 197 */       contract.initialize();
/*     */       
/* 199 */       this.m_Contracts.put(key, contract);
/* 200 */       if (!contract.isAbstract() && getCurrentImplementation(key) == null) {
/* 201 */         throw new ContractException("Cannot find a registered implementation for contract with id '" + key + "'!" + 
/* 202 */             " Either register an implementation for this contract or its module!");
/*     */       }
/*     */     } 
/* 205 */     keys = this.m_ContractImpls.keys();
/* 206 */     while (keys.hasMoreElements()) {
/*     */       
/* 208 */       String key = keys.nextElement();
/* 209 */       ContractImplementation implementation = (ContractImplementation)this.m_ContractImpls.get(key);
/* 210 */       if (implementation instanceof ContractImplementationTmp)
/*     */       {
/* 212 */         implementation = implementation.processForTemplates();
/*     */       }
/* 214 */       implementation.initialize();
/* 215 */       verifyImplementation(implementation);
/*     */       
/* 217 */       this.m_ContractImpls.put(key, implementation);
/* 218 */       ms_Logger.trace("Registered contract implementation : " + implementation);
/*     */     } 
/*     */ 
/*     */     
/* 222 */     this.m_CacheManager.releaseCachedHashTable(this.m_ImplTemplates);
/* 223 */     this.m_CacheManager.releaseCachedList(this.m_ImplGroups);
/* 224 */     this.m_ImplGroups = null;
/* 225 */     this.m_ImplTemplates = null;
/*     */     
/* 227 */     keys = this.m_ParameterMappings.keys();
/* 228 */     while (keys.hasMoreElements()) {
/*     */       
/* 230 */       String key = keys.nextElement();
/* 231 */       ParameterMapping mapping = (ParameterMapping)this.m_ParameterMappings.get(key);
/* 232 */       mapping.initialize();
/* 233 */       this.m_ParameterMappings.put(key, mapping);
/* 234 */       ms_Logger.trace("Registered parameter mapping : " + mapping);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void processForTemplates(ArrayList<String> processedTemplates, String key) throws ContractException {
/* 241 */     if (processedTemplates.contains(key))
/*     */       return; 
/* 243 */     ContractImplTemplate template = (ContractImplTemplate)this.m_ImplTemplates.get(key);
/* 244 */     ArrayList<ContractImplInclude> includes = new ArrayList<>();
/* 245 */     template.fillAllIncludes(includes);
/* 246 */     for (ContractImplInclude include : includes) {
/*     */       
/* 248 */       String templateName = include.getTemplateName();
/* 249 */       if (!processedTemplates.contains(templateName))
/* 250 */         processForTemplates(processedTemplates, templateName); 
/*     */     } 
/* 252 */     template = (ContractImplTemplate)template.processForTemplates();
/* 253 */     processedTemplates.add(key);
/*     */     
/* 255 */     this.m_ImplTemplates.put(key, template);
/*     */   }
/*     */ 
/*     */   
/*     */   private void verifyImplementation(ContractImplementation implementation) throws ContractException {
/* 260 */     if (ms_NoVerify) {
/*     */       return;
/*     */     }
/* 263 */     String category = implementation.getCategory();
/* 264 */     IContractImplVerifier verifier = ContractSchemaRegistry.ms_Verifiers.get(category);
/* 265 */     if (verifier != null) {
/* 266 */       verifier.verifyContractImplementation(implementation);
/*     */     }
/*     */   }
/*     */   
/*     */   public ArrayList<ContractImplementation> getReportContractImplementations(String implCategory) {
/* 271 */     ArrayList<ContractImplementation> repImpls = new ArrayList<>();
/* 272 */     Enumeration<String> ids = this.m_Contracts.keys();
/* 273 */     while (ids.hasMoreElements()) {
/*     */       
/* 275 */       String contractId = ids.nextElement();
/* 276 */       ContractSchema contract = (ContractSchema)this.m_Contracts.get(contractId);
/* 277 */       if (contract.getType() == ContractSchema.ContractType.type_report) {
/*     */         
/* 279 */         ContractImplementation implementation = getContractImplementation(contractId, implCategory);
/* 280 */         if (implementation != null)
/* 281 */           repImpls.add(implementation); 
/*     */       } 
/*     */     } 
/* 284 */     return repImpls;
/*     */   }
/*     */ 
/*     */   
/*     */   public void printContracts(PrintWriter writer, String baseUrl, String detailContractId) {
/* 289 */     writer.print("<center>");
/* 290 */     if (!StringUtil.isEmpty(detailContractId)) {
/*     */       
/* 292 */       writer.print("<table border=\"1\" >");
/* 293 */       writer.print("<tr>");
/* 294 */       writer.print("<td>");
/* 295 */       writer.println("<b>Contract " + detailContractId + " : </b>");
/* 296 */       writer.print("</td>");
/* 297 */       writer.print("</tr>");
/* 298 */       writer.print("<tr>");
/* 299 */       writer.print("<td>");
/* 300 */       getContract(detailContractId).printHtml(writer);
/* 301 */       writer.print("</td>");
/* 302 */       writer.print("</tr>");
/* 303 */       writer.print("<tr>");
/* 304 */       writer.print("<td>");
/* 305 */       writer.print("<b>Active Implementation : </b>");
/* 306 */       getCurrentImplementation(detailContractId).printHtml(writer);
/* 307 */       writer.print("</td>");
/* 308 */       writer.print("</tr>");
/* 309 */       writer.print("</table>");
/* 310 */       writer.print("<hr>");
/*     */     } 
/* 312 */     writer.println("<h3>All Contracts</h3>");
/* 313 */     writer.print("<hr>");
/* 314 */     Enumeration<String> keys = this.m_Contracts.keys();
/* 315 */     List<String> ids = Collections.list(keys);
/* 316 */     Collections.sort(ids);
/* 317 */     writer.print("<table border=\"0\" >");
/* 318 */     for (String contractId : ids) {
/*     */       
/* 320 */       writer.print("<tr>");
/* 321 */       writer.print("<td>");
/* 322 */       writer.println("<a href=\"" + baseUrl + "?id=" + contractId + "\">" + contractId + "</a>");
/* 323 */       writer.print("</td>");
/* 324 */       writer.print("</tr>");
/*     */     } 
/* 326 */     writer.print("</table>");
/* 327 */     writer.print("</center>");
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setNoVerify(boolean noVerify) {
/* 332 */     ms_NoVerify = noVerify;
/*     */   }
/*     */ 
/*     */   
/*     */   public Hashtable<String, ArrayList<String>> getLookupParametersByBrowserName() {
/* 337 */     return this.ms_LookupParametersByBrowserName;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<String> getLookupParameterList(String browserName) {
/* 342 */     if (this.ms_LookupParametersByBrowserName.containsKey(browserName))
/* 343 */       return this.ms_LookupParametersByBrowserName.get(browserName); 
/* 344 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractSchemaContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */