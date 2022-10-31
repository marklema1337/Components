/*     */ package com.lbs.contract;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.contract.execution.EmptyExecutorReader;
/*     */ import com.lbs.contract.execution.ParameterMapping;
/*     */ import com.lbs.contract.execution.PropertyMapperReader;
/*     */ import com.lbs.contract.execution.PropertyMapping;
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.localization.JLbsResourceId;
/*     */ import com.lbs.parameter.ParameterSchema;
/*     */ import com.lbs.parameter.ParameterSchemaRegistry;
/*     */ import com.lbs.util.DOMUtil;
/*     */ import com.lbs.util.SetUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.File;
/*     */ import java.io.FilenameFilter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
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
/*     */ public class ContractSchemaLoader
/*     */ {
/*  40 */   private static final LbsConsole ms_Logger = LbsConsole.getLogger(ContractSchemaLoader.class);
/*     */   
/*     */   public static final int OPTION_CONTRACTS = 1;
/*     */   
/*     */   public static final int OPTION_IMPLS = 2;
/*     */   public static final int OPTION_REGISTRY = 4;
/*     */   public static final int OPTION_EXECUTIONS = 8;
/*  47 */   private static int ms_LoadOption = 15;
/*     */   
/*  49 */   private static final ArrayList<PropertyMapperReader> ms_Mappers = new ArrayList<>();
/*  50 */   private static final ArrayList<EmptyExecutorReader> ms_EmptyExecutors = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public static void registerPropertyMapperReader(PropertyMapperReader reader) {
/*  54 */     ms_Mappers.add(reader);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerEmptyExecutorReader(EmptyExecutorReader reader) {
/*  59 */     ms_EmptyExecutors.add(reader);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setLoadOption(int option) {
/*  64 */     ms_LoadOption = option;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadContracts(File folder) throws ContractException {
/*  69 */     File[] files = folder.listFiles(new FilenameFilter()
/*     */         {
/*     */           public boolean accept(File dir, String name)
/*     */           {
/*  73 */             return name.endsWith(".xml");
/*     */           }
/*     */         });
/*  76 */     loadContracts(files);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadContractsForJRFNavi(File folder) throws ContractException {
/*  81 */     File[] files = folder.listFiles(new FilenameFilter()
/*     */         {
/*     */           public boolean accept(File dir, String name)
/*     */           {
/*  85 */             return name.endsWith(".xml");
/*     */           }
/*     */         });
/*  88 */     loadContractsForJRFNavi(files);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadContractsForJRFNavi(File[] files) throws ContractException {
/*  93 */     for (int i = 0; i < files.length; i++) {
/*     */       
/*  95 */       File xmlFile = files[i];
/*     */       
/*     */       try {
/*  98 */         ms_Logger.debug("Loading File : " + xmlFile.getAbsolutePath());
/*  99 */         Document document = DOMUtil.loadXmlDocumentValidated(xmlFile.getAbsolutePath());
/* 100 */         Element root = document.getDocumentElement();
/* 101 */         String rootTag = root.getTagName();
/* 102 */         ms_Logger.debug("Type  : " + rootTag);
/* 103 */         if (rootTag.equals("contracts")) {
/* 104 */           loadContracts(document, root, xmlFile);
/* 105 */         } else if (rootTag.equals("implementations")) {
/* 106 */           loadImplementations(document, root, xmlFile);
/* 107 */         } else if (rootTag.equals("contract-registry")) {
/* 108 */           loadRegistry(document, root, xmlFile);
/* 109 */         }  ms_Logger.debug("Loading Contracts Completed : " + xmlFile.getAbsolutePath());
/*     */       }
/* 111 */       catch (Exception e) {
/*     */         
/* 113 */         ms_Logger.error(e, e);
/* 114 */         throw new ContractException("Cannot continue contract schema load, an exception occurred!", e);
/*     */       } 
/*     */     } 
/* 117 */     ContractSchemaRegistry.initialize();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadContracts(File[] files) throws ContractException {
/* 122 */     for (int i = 0; i < files.length; i++) {
/*     */       
/* 124 */       File xmlFile = files[i];
/*     */       
/*     */       try {
/* 127 */         ms_Logger.debug("Loading File : " + xmlFile.getAbsolutePath());
/* 128 */         Document document = DOMUtil.loadXmlDocumentValidated(xmlFile.getAbsolutePath());
/* 129 */         Element root = document.getDocumentElement();
/* 130 */         String rootTag = root.getTagName();
/* 131 */         ms_Logger.debug("Type  : " + rootTag);
/* 132 */         if (rootTag.equals("contracts")) {
/* 133 */           loadContracts(document, root, xmlFile);
/* 134 */         } else if (rootTag.equals("implementations")) {
/* 135 */           loadImplementations(document, root, xmlFile);
/* 136 */         } else if (rootTag.equals("contract-registry")) {
/* 137 */           loadRegistry(document, root, xmlFile);
/* 138 */         } else if (rootTag.equals("execution-definitions")) {
/* 139 */           loadParameterMappings(xmlFile.getAbsolutePath(), root);
/* 140 */         }  ms_Logger.debug("Loading Contracts Completed : " + xmlFile.getAbsolutePath());
/*     */       }
/* 142 */       catch (Exception e) {
/*     */         
/* 144 */         ms_Logger.error(e, e);
/* 145 */         throw new ContractException("Cannot continue contract schema load, an exception occurred!", e);
/*     */       } 
/*     */     } 
/* 148 */     ContractSchemaRegistry.initialize();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadParameterMappings(String fileName, Element root) throws Exception {
/* 153 */     if (!SetUtil.isOptionSet(ms_LoadOption, 8))
/*     */       return; 
/* 155 */     NodeList list = DOMUtil.getChildElementsByName(root, "parameter-mapping");
/* 156 */     if (list != null)
/*     */     {
/* 158 */       for (int i = 0; i < list.getLength(); i++)
/*     */       {
/* 160 */         loadParameterMapping(fileName, (Element)list.item(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void loadParameterMapping(String fileName, Element mappingElement) throws Exception {
/* 167 */     String parameterId = DOMUtil.getMandatoryAttribute(mappingElement, fileName, "id");
/* 168 */     Identifier id = new Identifier(parameterId);
/* 169 */     ParameterMapping mapping = ContractSchemaRegistry.getParameterMapping(id);
/* 170 */     if (mapping == null)
/* 171 */       mapping = new ParameterMapping(); 
/* 172 */     ParameterSchema schema = ParameterSchemaRegistry.findParameter(id);
/* 173 */     if (schema == null)
/* 174 */       throw new Exception("Parameter schema with id '" + id + "' could not be found at file : " + fileName); 
/* 175 */     mapping.setParameterIdentifier(id);
/* 176 */     NodeList list = DOMUtil.getChildElementsByName(mappingElement, "property");
/* 177 */     if (list != null)
/*     */     {
/* 179 */       for (int i = 0; i < list.getLength(); i++)
/*     */       {
/* 181 */         loadPropertyMapping(schema, mapping, fileName, (Element)list.item(i));
/*     */       }
/*     */     }
/* 184 */     ContractSchemaRegistry.registerParameterMapping(mapping);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void loadPropertyMapping(ParameterSchema schema, ParameterMapping mapping, String fileName, Element propElem) throws Exception {
/* 190 */     String propertyName = DOMUtil.getMandatoryAttribute(propElem, fileName, "name");
/* 191 */     if (!schema.hasProperty(propertyName)) {
/* 192 */       throw new Exception("Parameter with id '" + schema.getIdentifier() + "' does not have a property with name '" + 
/* 193 */           propertyName + "' at file : " + fileName);
/*     */     }
/* 195 */     boolean add = false;
/* 196 */     PropertyMapping propMapping = mapping.getMapping(propertyName);
/* 197 */     if (propMapping == null) {
/*     */       
/* 199 */       propMapping = new PropertyMapping();
/* 200 */       add = true;
/*     */     } 
/* 202 */     propMapping.setPropertyName(propertyName);
/* 203 */     propMapping.setAlwaysMap(DOMUtil.getBooleanAttribute(propElem, "always-map"));
/*     */     
/* 205 */     Element mapperParentElement = DOMUtil.getChildElementByName(propElem, "mappers");
/* 206 */     if (mapperParentElement != null)
/*     */     {
/* 208 */       for (PropertyMapperReader mapper : ms_Mappers)
/*     */       {
/* 210 */         mapper.readFromXML(fileName, propMapping, mapperParentElement);
/*     */       }
/*     */     }
/* 213 */     Element emptyExecutorParentElement = DOMUtil.getChildElementByName(propElem, "empty-executors");
/* 214 */     if (emptyExecutorParentElement != null)
/*     */     {
/* 216 */       for (EmptyExecutorReader emptyExecutor : ms_EmptyExecutors)
/*     */       {
/* 218 */         emptyExecutor.readFromXML(fileName, propMapping, emptyExecutorParentElement);
/*     */       }
/*     */     }
/*     */     
/* 222 */     if (add) {
/* 223 */       mapping.addPropertyMapping(propMapping);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void loadRegistry(Document document, Element root, File xmlFile) throws ContractException {
/* 228 */     if (!SetUtil.isOptionSet(ms_LoadOption, 4))
/*     */       return; 
/* 230 */     NodeList list = root.getElementsByTagName("module-mapping");
/* 231 */     if (list != null)
/*     */     {
/* 233 */       for (int i = 0; i < list.getLength(); i++) {
/*     */         
/* 235 */         Element moduleMapElem = (Element)list.item(i);
/* 236 */         String module = getMandatoryAttribute(moduleMapElem, xmlFile, "module", "contract registry module mapping");
/* 237 */         String category = getMandatoryAttribute(moduleMapElem, xmlFile, "implementation-category", 
/* 238 */             "contract registry module mapping");
/* 239 */         ContractSchemaRegistry.getApplicationContainer().mapContractImplementation(module, category);
/*     */       } 
/*     */     }
/* 242 */     list = root.getElementsByTagName("mapping");
/* 243 */     if (list != null)
/*     */     {
/* 245 */       for (int i = 0; i < list.getLength(); i++) {
/*     */         
/* 247 */         Element mapElem = (Element)list.item(i);
/* 248 */         String contractId = getMandatoryAttribute(mapElem, xmlFile, "contract-id", "contract registry mapping");
/* 249 */         String category = getMandatoryAttribute(mapElem, xmlFile, "implementation-category", "contract registry mapping");
/* 250 */         ContractSchemaRegistry.getApplicationContainer().mapContractImplementation(contractId, category);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadImplementations(Document document, Element root, File xmlFile) throws ContractException {
/* 257 */     if (!SetUtil.isOptionSet(ms_LoadOption, 2))
/*     */       return; 
/* 259 */     String category = getMandatoryAttribute(root, xmlFile, "category", "contract implementation");
/* 260 */     NodeList nodes = root.getChildNodes();
/* 261 */     for (int i = 0; i < nodes.getLength(); i++) {
/*     */       
/* 263 */       Node node = nodes.item(i);
/* 264 */       if (node instanceof Element) {
/*     */         
/* 266 */         String tag = ((Element)node).getTagName();
/* 267 */         if (tag.equals("implementation")) {
/*     */           
/* 269 */           ContractImplementation implementation = loadImplementation((Element)node, category, xmlFile);
/* 270 */           ContractSchemaRegistry.getApplicationContainer().registerContractImplementation(implementation);
/*     */         }
/* 272 */         else if (tag.equals("implementation-group")) {
/*     */           
/* 274 */           ContractImplGroup implGrp = loadImplementationGroup((Element)node, category, xmlFile);
/* 275 */           ContractSchemaRegistry.getApplicationContainer().addImplementationGroup(implGrp);
/* 276 */           prepareBrowserLookupParameters(implGrp, ContractSchemaRegistry.getApplicationContainer());
/*     */         
/*     */         }
/* 279 */         else if (tag.equals("template")) {
/*     */           
/* 281 */           ContractImplTemplate template = loadImplementationTemplate((Element)node, category, xmlFile);
/* 282 */           ContractSchemaRegistry.getApplicationContainer().addImplementationTemplate(template);
/* 283 */           for (ContractImplGroup impl : template.getGroups()) {
/* 284 */             prepareBrowserLookupParameters(impl, ContractSchemaRegistry.getApplicationContainer());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void prepareBrowserLookupParameters(ContractImplGroup implGrp, ContractSchemaContainer container) {
/*     */     try {
/* 294 */       for (ContractImplInclude include : implGrp.getIncludes()) {
/*     */         
/* 296 */         Hashtable<String, String> subst = include.getVarSubstitutions();
/* 297 */         if (subst.containsKey("BrowserForm")) {
/*     */           
/* 299 */           String browName = subst.get("BrowserForm");
/* 300 */           if (browName.indexOf("Mode") > 0)
/*     */           {
/* 302 */             if (browName.indexOf("%") > 0) {
/* 303 */               browName = String.valueOf(browName.substring(0, browName.indexOf("%"))) + ".jfm";
/*     */             } else {
/* 305 */               browName = browName.substring(0);
/*     */             } 
/*     */           }
/* 308 */           ArrayList<String> lookupParams = getLookupParmsForImplGroup(implGrp);
/* 309 */           if (lookupParams.size() > 0 && !container.getLookupParametersByBrowserName().containsKey(browName)) {
/* 310 */             container.getLookupParametersByBrowserName().put(browName, lookupParams);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 315 */       for (ContractImplGroup group : implGrp.getGroups()) {
/*     */         
/* 317 */         String groupName = group.getName();
/* 318 */         if (groupName.indexOf("Browser") > -1)
/*     */         {
/* 320 */           Hashtable<String, String> props = group.getProperties();
/* 321 */           if (props.containsKey("FormName"))
/*     */           {
/* 323 */             String browName = props.get("FormName");
/* 324 */             if (browName.indexOf("Mode") > 0) {
/*     */               
/* 326 */               if (browName.indexOf("%") > 0) {
/* 327 */                 browName = String.valueOf(browName.substring(0, browName.indexOf("%"))) + ".jfm";
/*     */               } else {
/* 329 */                 browName = browName.substring(0);
/*     */               } 
/* 331 */             } else if (browName.indexOf("GroupCode") > 0) {
/* 332 */               browName = String.valueOf(browName.substring(0, browName.indexOf("%"))) + ".jfm";
/*     */             } 
/* 334 */             ArrayList<String> lookupParams = getLookupParmsForImplGroup(implGrp);
/* 335 */             if (lookupParams.size() > 0 && !container.getLookupParametersByBrowserName().containsKey(browName)) {
/* 336 */               container.getLookupParametersByBrowserName().put(browName, lookupParams);
/*     */             }
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       } 
/* 343 */     } catch (Exception e) {
/*     */       
/* 345 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static ArrayList<String> getLookupParmsForImplGroup(ContractImplGroup implGrp) {
/* 352 */     ArrayList<String> paramList = new ArrayList<>();
/* 353 */     Enumeration<String> keys = implGrp.getProperties().keys();
/* 354 */     while (keys.hasMoreElements()) {
/*     */       
/* 356 */       String key = keys.nextElement();
/* 357 */       if (key.startsWith("lookupinfo."))
/* 358 */         paramList.add(String.valueOf(key) + "&&" + (String)implGrp.getProperties().get(key)); 
/*     */     } 
/* 360 */     return paramList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static ContractImplTemplate loadImplementationTemplate(Element node, String category, File xmlFile) throws ContractException {
/* 366 */     ContractImplTemplate template = new ContractImplTemplate();
/* 367 */     String name = getMandatoryAttribute(node, xmlFile, "name", "contract implementation template");
/* 368 */     template.setName(name);
/* 369 */     loadImplGroup(node, category, xmlFile, template);
/* 370 */     return template;
/*     */   }
/*     */ 
/*     */   
/*     */   private static ContractImplGroup loadImplementationGroup(Element node, String category, File xmlFile) throws ContractException {
/* 375 */     ContractImplGroup group = new ContractImplGroup();
/* 376 */     String name = node.getAttribute("name");
/* 377 */     group.setName(name);
/* 378 */     loadImplGroup(node, category, xmlFile, group);
/* 379 */     return group;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void loadImplGroup(Element node, String category, File xmlFile, ContractImplGroup group) throws ContractException {
/* 384 */     NodeList list = node.getChildNodes();
/* 385 */     if (list != null)
/*     */     {
/* 387 */       for (int i = 0; i < list.getLength(); i++) {
/*     */         
/* 389 */         Node n = list.item(i);
/* 390 */         if (n instanceof Element) {
/*     */           
/* 392 */           String tag = ((Element)n).getTagName();
/* 393 */           if (tag.equals("implementation-group")) {
/* 394 */             group.getGroups().add(loadImplementationGroup((Element)n, category, xmlFile));
/* 395 */           } else if (tag.equals("implementation")) {
/* 396 */             group.getImplementations().add(loadImplementation((Element)n, category, xmlFile));
/* 397 */           } else if (tag.equals("include")) {
/* 398 */             group.getIncludes().add(loadInclude(xmlFile, (Element)n));
/* 399 */           } else if (tag.equals("property")) {
/* 400 */             loadProperty((Element)n, xmlFile, group.getProperties());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static ContractImplementation loadImplementation(Element implElem, String category, File xmlFile) throws ContractException {
/* 409 */     NodeList list = implElem.getElementsByTagName("include");
/* 410 */     boolean hasIncl = (list != null && list.getLength() > 0);
/* 411 */     ContractImplementation implementation = hasIncl ? 
/* 412 */       new ContractImplementationTmp() : 
/* 413 */       new ContractImplementation();
/* 414 */     implementation.setCategory(category);
/* 415 */     String id = getMandatoryAttribute(implElem, xmlFile, "id", "contract implementation");
/* 416 */     implementation.setIdentifier(new Identifier(id));
/* 417 */     if (list != null)
/*     */     {
/* 419 */       for (int i = 0; i < list.getLength(); i++) {
/*     */         
/* 421 */         Element includeElem = (Element)list.item(i);
/* 422 */         ContractImplInclude include = loadInclude(xmlFile, includeElem);
/* 423 */         ((ContractImplementationTmp)implementation).getIncludes().add(include);
/*     */       } 
/*     */     }
/*     */     
/* 427 */     list = implElem.getElementsByTagName("property");
/* 428 */     if (list != null)
/*     */     {
/* 430 */       for (int i = 0; i < list.getLength(); i++) {
/*     */         
/* 432 */         Element propElem = (Element)list.item(i);
/* 433 */         loadProperty(propElem, xmlFile, implementation.getProperties());
/*     */       } 
/*     */     }
/* 436 */     ms_Logger.debug("loadImplementation completed : " + implementation);
/* 437 */     return implementation;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void loadProperty(Element propElem, File xmlFile, Hashtable<String, String> propMap) throws ContractException {
/* 442 */     String name = getMandatoryAttribute(propElem, xmlFile, "name", "contract implementation property");
/* 443 */     String value = getMandatoryAttribute(propElem, xmlFile, "value", "contract implementation property");
/* 444 */     propMap.put(name, value);
/*     */   }
/*     */ 
/*     */   
/*     */   private static ContractImplInclude loadInclude(File xmlFile, Element includeElem) throws ContractException {
/* 449 */     String templateName = getMandatoryAttribute(includeElem, xmlFile, "template-name", "contract implementation include");
/* 450 */     ContractImplInclude include = new ContractImplInclude();
/* 451 */     include.setTemplateName(templateName);
/* 452 */     NodeList vars = includeElem.getElementsByTagName("template-var");
/* 453 */     if (vars != null)
/*     */     {
/* 455 */       for (int j = 0; j < vars.getLength(); j++) {
/*     */         
/* 457 */         Element varElem = (Element)vars.item(j);
/* 458 */         String name = getMandatoryAttribute(varElem, xmlFile, "name", "contract implementation template-var");
/* 459 */         String value = getMandatoryAttribute(varElem, xmlFile, "value", "contract implementation template-var");
/* 460 */         include.getVarSubstitutions().put(name, value);
/*     */       } 
/*     */     }
/* 463 */     return include;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadContracts(Document document, Element root, File xmlFile) throws ContractException {
/* 468 */     if (!SetUtil.isOptionSet(ms_LoadOption, 1)) {
/*     */       return;
/*     */     }
/* 471 */     String module = getMandatoryAttribute(root, xmlFile, "module", "contract");
/* 472 */     String resGroup = root.getAttribute("res-group");
/* 473 */     NodeList list = root.getElementsByTagName("contract");
/* 474 */     for (int i = 0; i < list.getLength(); i++) {
/*     */       
/* 476 */       Element contractElem = (Element)list.item(i);
/* 477 */       loadContract(contractElem, module, resGroup, xmlFile);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void loadContract(Element contractElem, String module, String resGroup, File xmlFile) throws ContractException {
/* 483 */     ms_Logger.debug("Starting to loadContract file :  " + xmlFile.getAbsolutePath());
/* 484 */     ContractSchema contract = new ContractSchema();
/* 485 */     contract.setModule(module);
/* 486 */     String id = getMandatoryAttribute(contractElem, xmlFile, "id", "contract");
/* 487 */     if (ContractSchemaRegistry.getApplicationContainer().containsContract(id))
/* 488 */       throw new ContractException("Contract with id '" + id + "' is already defined : " + xmlFile); 
/* 489 */     contract.setIdentifier(new Identifier(id));
/* 490 */     String name = getMandatoryAttribute(contractElem, xmlFile, "name", "contract");
/* 491 */     contract.setName(name);
/* 492 */     ms_Logger.debug("loadContract id & name:  " + id + " " + name);
/* 493 */     String description = getMandatoryAttribute(contractElem, xmlFile, "description", "contract");
/* 494 */     contract.setDescription(description);
/* 495 */     contract.setAbstract(DOMUtil.getBooleanAttribute(contractElem, "abstract"));
/* 496 */     if (!StringUtil.isEmpty(contractElem.getAttribute("base-contract-name"))) {
/* 497 */       contract.setBaseContractIdentifier(new Identifier(contractElem.getAttribute("base-contract-name")));
/*     */     }
/*     */     
/* 500 */     String hasRevision = contractElem.getAttribute("has-revision");
/* 501 */     String revisionWithApproval = contractElem.getAttribute("revision-with-approval");
/* 502 */     if (!StringUtil.isEmpty(hasRevision))
/* 503 */       contract.setHasRevision(Boolean.valueOf(hasRevision).booleanValue()); 
/* 504 */     if (!StringUtil.isEmpty(revisionWithApproval)) {
/* 505 */       contract.setRevisionWithApproval(Boolean.valueOf(revisionWithApproval).booleanValue());
/*     */     }
/* 507 */     if (!StringUtil.isEmpty(hasRevision) && Boolean.valueOf(hasRevision).booleanValue()) {
/*     */       
/* 509 */       Hashtable<String, Boolean> revisionDataList = new Hashtable<>();
/* 510 */       revisionDataList.put("has-revision", Boolean.valueOf(hasRevision));
/* 511 */       if (!StringUtil.isEmpty(revisionWithApproval) && Boolean.valueOf(revisionWithApproval).booleanValue())
/* 512 */         revisionDataList.put("revision-with-approval", Boolean.valueOf(revisionWithApproval)); 
/* 513 */       ContractRevisions.putContractRevision(id, revisionDataList);
/*     */     } 
/*     */     
/* 516 */     boolean typeMandatory = (contract.getBaseContractIdentifier() == null);
/* 517 */     String typeName = typeMandatory ? 
/* 518 */       getMandatoryAttribute(contractElem, xmlFile, "type", "contract") : 
/* 519 */       contractElem.getAttribute("type");
/* 520 */     if (!StringUtil.isEmpty(typeName)) {
/*     */       
/* 522 */       ContractSchema.ContractType type = ContractSchema.ContractType.get(typeName);
/* 523 */       if (type == null)
/* 524 */         throw new ContractException("Unrecognized type '" + typeName + "' in contract file : " + xmlFile); 
/* 525 */       contract.setType(type);
/*     */     } 
/* 527 */     String val = contractElem.getAttribute("non-interactive");
/* 528 */     if (!StringUtil.isEmpty(val))
/*     */     {
/* 530 */       contract.setNonInteractive(Boolean.valueOf(val).booleanValue());
/*     */     }
/* 532 */     Element ins = DOMUtil.getOptionalFirstElement(contractElem, "inputs");
/* 533 */     if (ins != null) {
/*     */       
/* 535 */       NodeList nodeList = ins.getElementsByTagName("input");
/* 536 */       if (nodeList != null)
/*     */       {
/* 538 */         for (int i = 0; i < nodeList.getLength(); i++) {
/*     */           
/* 540 */           Element inElem = (Element)nodeList.item(i);
/* 541 */           String inName = getMandatoryAttribute(inElem, xmlFile, "name", "contract input");
/* 542 */           String inAlias = getMandatoryAttribute(inElem, xmlFile, "alias", "contract input");
/* 543 */           boolean optional = DOMUtil.getBooleanAttribute(inElem, "optional", false);
/* 544 */           if (contract.hasInputWithAlias(inAlias))
/* 545 */             throw new ContractException("Alias '" + inAlias + "' already used for another input for contract '" + 
/* 546 */                 contract.getId() + "' in contract file : " + xmlFile); 
/* 547 */           contract.getInputs().add(new ContractSchemaInOut(new Identifier(inName), inAlias, optional));
/* 548 */           Element mandatoryElem = DOMUtil.getChildElementByName(inElem, "mandatory-map");
/* 549 */           if (mandatoryElem != null) {
/*     */             
/* 551 */             MandatoryMap map = new MandatoryMap();
/* 552 */             map.setMarkAllNonMandatory(DOMUtil.getBooleanAttribute(mandatoryElem, "mark-all-non-mandatory"));
/* 553 */             NodeList list2 = DOMUtil.getChildElementsByName(mandatoryElem, "property");
/* 554 */             for (int j = 0; j < list2.getLength(); j++) {
/*     */               
/* 556 */               Element propElem = (Element)list2.item(j);
/* 557 */               map.addMandatoryPropMap(getMandatoryAttribute(propElem, xmlFile, "name", propElem.getTagName()), 
/* 558 */                   DOMUtil.getBooleanAttribute(propElem, "mandatory", true));
/*     */             } 
/* 560 */             contract.addMandatoryMap(inAlias, map);
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 565 */     Element outs = DOMUtil.getOptionalFirstElement(contractElem, "outputs");
/* 566 */     if (outs != null) {
/*     */       
/* 568 */       NodeList nodeList = outs.getElementsByTagName("output");
/* 569 */       if (nodeList != null)
/*     */       {
/* 571 */         for (int i = 0; i < nodeList.getLength(); i++) {
/*     */           
/* 573 */           Element outElem = (Element)nodeList.item(i);
/* 574 */           String outName = getMandatoryAttribute(outElem, xmlFile, "name", "contract output");
/* 575 */           String outAlias = getMandatoryAttribute(outElem, xmlFile, "alias", "contract output");
/* 576 */           boolean optional = DOMUtil.getBooleanAttribute(outElem, "optional", false);
/* 577 */           if (contract.hasOutputWithAlias(outAlias))
/* 578 */             throw new ContractException("Alias '" + outAlias + "' already used for another output for contract '" + 
/* 579 */                 contract.getId() + "' in contract file : " + xmlFile); 
/* 580 */           contract.getOutputs().add(new ContractSchemaInOut(new Identifier(outName), outAlias, optional));
/*     */         } 
/*     */       }
/*     */     } 
/* 584 */     NodeList list = contractElem.getElementsByTagName("resource");
/* 585 */     if (list != null)
/*     */     {
/* 587 */       for (int i = 0; i < list.getLength(); i++) {
/*     */         
/* 589 */         Element resElem = (Element)list.item(i);
/* 590 */         String resGrp = resElem.getAttribute("res-group");
/* 591 */         if (StringUtil.isEmpty(resGrp))
/* 592 */           resGrp = resGroup; 
/* 593 */         int listId = DOMUtil.getIntegerAttribute(resElem, "list-id");
/* 594 */         int strTag = DOMUtil.getIntegerAttribute(resElem, "string-tag");
/* 595 */         String property = getMandatoryAttribute(resElem, xmlFile, "property", "contract resource");
/* 596 */         if (property.equals("name")) {
/* 597 */           contract.setNameResource(new JLbsResourceId(resGrp, listId, strTag));
/* 598 */         } else if (property.equals("description")) {
/* 599 */           contract.setDescriptionResource(new JLbsResourceId(resGrp, listId, strTag));
/*     */         } 
/*     */       } 
/*     */     }
/* 603 */     ms_Logger.debug("loadContract : registerContract " + contract.getName());
/* 604 */     ContractSchemaRegistry.getApplicationContainer().registerContract(contract);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getMandatoryAttribute(Element elem, File xmlFile, String attribute, String definition) throws ContractException {
/*     */     try {
/* 613 */       return DOMUtil.getMandatoryAttribute(elem, xmlFile.toString(), attribute, definition);
/*     */     }
/* 615 */     catch (Exception e) {
/*     */       
/* 617 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractSchemaLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */