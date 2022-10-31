/*     */ package com.lbs.parameter;
/*     */ 
/*     */ import com.lbs.cache.LbsNonCachedCacheManager;
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.localization.JLbsResourceId;
/*     */ import com.lbs.platform.interfaces.ICacheManager;
/*     */ import com.lbs.util.DOMUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NamedNodeMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParameterSchemaLoader
/*     */ {
/*  40 */   private static final LbsConsole ms_Logger = LbsConsole.getLogger(ParameterSchemaLoader.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void loadParameters(ArrayList<File> searchPaths, File parFile, Hashtable<String, GenerationConfig> fileHash) throws ParameterSchemaException {
/*  46 */     if (!ParameterSchemaRegistry.isInitForCache()) {
/*  47 */       ParameterSchemaRegistry.initForCache((ICacheManager)new LbsNonCachedCacheManager());
/*     */     }
/*     */     try {
/*  50 */       if (fileHash.containsKey(parFile.getName())) {
/*     */         return;
/*     */       }
/*  53 */       ms_Logger.debug("Loading parameters from file : " + parFile.getAbsolutePath());
/*  54 */       Document doc = DOMUtil.loadXmlDocumentValidated(parFile.getAbsolutePath());
/*  55 */       Element root = doc.getDocumentElement();
/*  56 */       Element generationConfig = DOMUtil.getOptionalFirstElement(root, "generation-config");
/*  57 */       if (generationConfig != null) {
/*     */         
/*  59 */         String moduleName = generationConfig.getAttribute("module");
/*  60 */         if (StringUtil.isEmpty(moduleName))
/*  61 */           throw new ParameterSchemaException("'module' is required for file : " + parFile); 
/*  62 */         String targetDir = generationConfig.getAttribute("target-dir");
/*  63 */         if (StringUtil.isEmpty(targetDir))
/*  64 */           throw new ParameterSchemaException("'target-dir' is required for file : " + parFile); 
/*  65 */         String packageName = generationConfig.getAttribute("package-name");
/*  66 */         fileHash.put(parFile.getName(), new GenerationConfig(targetDir, moduleName, packageName));
/*  67 */         Element parFiles = DOMUtil.getOptionalFirstElement(generationConfig, "param-files");
/*  68 */         if (parFiles != null) {
/*     */           
/*  70 */           NodeList list = parFiles.getElementsByTagName("param-file");
/*  71 */           if (list != null)
/*     */           {
/*  73 */             for (int i = 0; i < list.getLength(); i++) {
/*     */               
/*  75 */               Element item = (Element)list.item(i);
/*  76 */               String fileName = item.getAttribute("name");
/*  77 */               File f = findFile(searchPaths, fileName);
/*  78 */               if (f == null) {
/*  79 */                 throw new ParameterSchemaException("Could not find required parameter file named : " + fileName + " in any of the search paths!");
/*     */               }
/*  81 */               loadParameters(searchPaths, f, fileHash);
/*     */             } 
/*     */           }
/*     */         } 
/*  85 */         Element paramsElem = DOMUtil.getOptionalFirstElement(root, "parameters");
/*  86 */         if (paramsElem != null) {
/*     */           
/*  88 */           NodeList paramList = paramsElem.getElementsByTagName("parameter");
/*  89 */           for (int i = 0; i < paramList.getLength(); i++)
/*     */           {
/*  91 */             Element paramElem = (Element)paramList.item(i);
/*  92 */             loadParameter(paramElem, moduleName, parFile, packageName);
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/*  97 */     } catch (Exception e) {
/*     */       
/*  99 */       fileHash.remove(parFile.getName());
/* 100 */       throw new ParameterSchemaException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static File findFile(ArrayList<File> searchPaths, String fileName) {
/* 106 */     for (File searchDir : searchPaths) {
/*     */       
/* 108 */       File f = findFile(searchDir, fileName);
/* 109 */       if (f != null)
/* 110 */         return f; 
/*     */     } 
/* 112 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private static File findFile(File searchDir, String fileName) {
/* 117 */     File test = new File(searchDir, fileName);
/* 118 */     if (test.exists())
/* 119 */       return test; 
/* 120 */     File[] childDirs = searchDir.listFiles(new FileFilter()
/*     */         {
/*     */           public boolean accept(File pathname)
/*     */           {
/* 124 */             return pathname.isDirectory();
/*     */           }
/*     */         });
/* 127 */     for (int i = 0; i < childDirs.length; i++) {
/*     */       
/* 129 */       test = findFile(childDirs[i], fileName);
/* 130 */       if (test != null)
/* 131 */         return test; 
/*     */     } 
/* 133 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void loadParameter(Element paramElem, String moduleName, File parFile, String packageName) throws ParameterSchemaException {
/* 139 */     ParameterSchema param = new ParameterSchema();
/* 140 */     String name = paramElem.getAttribute("name");
/* 141 */     if (StringUtil.isEmpty(name))
/* 142 */       throw new ParameterSchemaException("'name' attribute of parameter is mandatory in file : " + parFile); 
/* 143 */     param.setIdentifier(new Identifier(name));
/* 144 */     param.setModuleName(moduleName);
/* 145 */     String s = paramElem.getAttribute("package-name");
/* 146 */     if (StringUtil.isEmpty(s))
/* 147 */       s = packageName; 
/* 148 */     param.setPackageName(s);
/* 149 */     String baseParamName = paramElem.getAttribute("base-parameter");
/* 150 */     if (!StringUtil.isEmpty(baseParamName))
/* 151 */       param.setBaseParamIdentifier(new Identifier(baseParamName)); 
/* 152 */     param.setGenerate(DOMUtil.getBooleanAttribute(paramElem, "generate"));
/* 153 */     param.setWrapper(DOMUtil.getBooleanAttribute(paramElem, "wrapper"));
/*     */     
/* 155 */     String resGroup = paramElem.getAttribute("res-group");
/* 156 */     if (StringUtil.isEmpty(resGroup))
/* 157 */       resGroup = "UN"; 
/* 158 */     int listId = DOMUtil.getIntegerAttribute(paramElem, "list-id");
/* 159 */     int stringTag = DOMUtil.getIntegerAttribute(paramElem, "string-tag");
/* 160 */     if (listId != 0)
/* 161 */       param.setDescResource(new JLbsResourceId(resGroup, listId, stringTag)); 
/* 162 */     param.setDescription(paramElem.getAttribute("description"));
/*     */     
/* 164 */     NodeList list = paramElem.getChildNodes(); int i;
/* 165 */     for (i = 0; i < list.getLength(); i++) {
/*     */       
/* 167 */       Node item = list.item(i);
/* 168 */       if (item instanceof Element) {
/*     */         
/* 170 */         Element elem = (Element)item;
/* 171 */         if (StringUtil.equals(elem.getTagName(), "property") || StringUtil.equals(elem.getTagName(), "list-property") || 
/* 172 */           StringUtil.equals(elem.getTagName(), "map-property") || 
/* 173 */           StringUtil.equals(elem.getTagName(), "object-identifier-property"))
/*     */         {
/* 175 */           loadProperty(param, elem, parFile);
/*     */         }
/* 177 */         if (StringUtil.equals(elem.getTagName(), "filter-list-property"))
/* 178 */           loadFilterListProperty(param, elem, parFile); 
/* 179 */         if (StringUtil.equals(elem.getTagName(), "browser-list-property"))
/* 180 */           loadBrowserListProperty(param, elem, parFile); 
/* 181 */         if (StringUtil.equals(elem.getTagName(), "browser-list-content"))
/* 182 */           loadBrowserListContent(param, elem, parFile); 
/*     */       } 
/*     */     } 
/* 185 */     list = paramElem.getElementsByTagName("filter-list-content");
/* 186 */     if (list != null)
/*     */     {
/* 188 */       for (i = 0; i < list.getLength(); i++) {
/*     */         
/* 190 */         Element listContentElem = (Element)list.item(i);
/*     */         
/* 192 */         if (listContentElem.getParentNode() == paramElem) {
/*     */ 
/*     */           
/* 195 */           String filterPropName = "Filters";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 201 */           ArrayList<ParameterFilterGroup> filterGroups = loadFilterList(parFile, listContentElem);
/* 202 */           param.addFilterGroups(filterPropName, filterGroups);
/*     */         } 
/*     */       }  } 
/* 205 */     list = paramElem.getElementsByTagName("constructor");
/* 206 */     if (list != null)
/*     */     {
/* 208 */       for (i = 0; i < list.getLength(); i++) {
/*     */         
/* 210 */         Element constructorElem = (Element)list.item(i);
/* 211 */         loadConstructor(param, constructorElem, parFile);
/*     */       } 
/*     */     }
/*     */     
/* 215 */     ParameterSchemaRegistry.addSchema(param);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void loadBrowserListContent(ParameterSchema param, Element propElem, File parFile) throws ParameterSchemaException {
/* 221 */     if (param.getBaseParamIdentifier() == null) {
/* 222 */       throw new ParameterSchemaException("browser-list-content is allowed only in parameters that extend a parameter with browser-list-property defined : " + parFile);
/*     */     }
/*     */     
/* 225 */     ParameterBrowserListProperty prop = new ParameterBrowserListProperty();
/* 226 */     String typeName = propElem.getAttribute("type-name");
/* 227 */     if (StringUtil.isEmpty(typeName)) {
/* 228 */       throw new ParameterSchemaException("'type-name' attribute of browser-list-content is mandatory in file : " + parFile);
/*     */     }
/* 230 */     prop.setTypeName(typeName);
/* 231 */     int stringTag = DOMUtil.getIntegerAttribute(propElem, "string-tag");
/* 232 */     if (stringTag > 0 && param.getDescResource() != null)
/* 233 */       prop.setDescResource(new JLbsResourceId(param.getDescResource().getResourceGroup(), param
/* 234 */             .getDescResource().getListId(), stringTag)); 
/* 235 */     prop.setDescription(propElem.getAttribute("description"));
/*     */     
/* 237 */     param.getProperties().add(prop);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void loadBrowserListProperty(ParameterSchema param, Element propElem, File parFile) throws ParameterSchemaException {
/* 243 */     ParameterBrowserListProperty prop = new ParameterBrowserListProperty();
/* 244 */     String name = propElem.getAttribute("name");
/* 245 */     if (StringUtil.isEmpty(name)) {
/* 246 */       throw new ParameterSchemaException("'name' attribute of browser-list-property is mandatory in file : " + parFile);
/*     */     }
/* 248 */     prop.setName(name);
/* 249 */     String typeName = propElem.getAttribute("type-name");
/* 250 */     if (StringUtil.isEmpty(typeName)) {
/* 251 */       throw new ParameterSchemaException("'type-name' attribute of browser-list-property is mandatory in file : " + parFile);
/*     */     }
/* 253 */     prop.setTypeName(typeName);
/* 254 */     prop.setAbstractList(true);
/* 255 */     loadModifier(propElem, parFile, prop);
/* 256 */     loadInitialValue(propElem, parFile, prop);
/* 257 */     loadPartOfIdentifier(propElem, prop);
/* 258 */     param.getProperties().add(prop);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void loadConstructor(ParameterSchema param, Element constructorElem, File parFile) throws ParameterSchemaException {
/* 264 */     boolean all = DOMUtil.getBooleanAttribute(constructorElem, "include-all");
/* 265 */     if (all) {
/*     */       
/* 267 */       ParameterConstructor consProps = new ParameterConstructor();
/* 268 */       consProps.getProperties().addAll(param.getProperties());
/* 269 */       param.getConstructors().add(consProps);
/*     */     } 
/* 271 */     NodeList list = constructorElem.getElementsByTagName("prop-ref");
/* 272 */     if (list != null && list.getLength() > 0) {
/*     */       
/* 274 */       ParameterConstructor consProps = new ParameterConstructor();
/* 275 */       for (int i = 0; i < list.getLength(); i++) {
/*     */         
/* 277 */         Element consProp = (Element)list.item(i);
/* 278 */         String propName = consProp.getAttribute("property");
/* 279 */         if (StringUtil.isEmpty(propName)) {
/* 280 */           throw new ParameterSchemaException("'property' attribute is required for 'prop-ref' element of constructor in file : " + parFile);
/*     */         }
/* 282 */         ParameterProperty prop = findProp(param, propName);
/* 283 */         if (prop == null)
/* 284 */           throw new ParameterSchemaException("Property named '" + propName + "' cannot be found on parameter '" + param
/* 285 */               .getIdentifier() + "' in file : " + parFile); 
/* 286 */         consProps.getProperties().add(prop);
/*     */       } 
/* 288 */       param.getConstructors().add(consProps);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static ParameterProperty findProp(ParameterSchema param, String propName) {
/* 294 */     for (ParameterProperty prop : param.getProperties()) {
/*     */       
/* 296 */       if (StringUtil.equals(prop.getName(), propName))
/* 297 */         return prop; 
/*     */     } 
/* 299 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void loadProperty(ParameterSchema param, Element propElem, File parFile) throws ParameterSchemaException {
/* 304 */     ParameterProperty prop = new ParameterProperty();
/* 305 */     String name = propElem.getAttribute("name");
/* 306 */     if (StringUtil.isEmpty(name)) {
/* 307 */       throw new ParameterSchemaException("'name' attribute of property is mandatory in file : " + parFile);
/*     */     }
/* 309 */     boolean list = StringUtil.equals(propElem.getTagName(), "list-property");
/* 310 */     boolean map = StringUtil.equals(propElem.getTagName(), "map-property");
/* 311 */     boolean objId = StringUtil.equals(propElem.getTagName(), "object-identifier-property");
/* 312 */     boolean serializable = DOMUtil.getBooleanAttribute(propElem, "serializable");
/*     */     
/* 314 */     String typeAtt = "type";
/* 315 */     if (list) {
/*     */       
/* 317 */       typeAtt = "item-type";
/* 318 */       prop = new ParameterListProperty();
/*     */     } 
/* 320 */     if (map) {
/*     */       
/* 322 */       typeAtt = "value-type";
/* 323 */       prop = new ParameterMapProperty();
/*     */     } 
/* 325 */     if (objId)
/*     */     {
/* 327 */       prop = new ObjectIdentifierProperty();
/*     */     }
/* 329 */     prop.setName(name);
/* 330 */     if (!objId) {
/*     */       
/* 332 */       String typeName = propElem.getAttribute(typeAtt);
/* 333 */       if (StringUtil.isEmpty(typeName)) {
/*     */         
/* 335 */         if (!list && !map)
/* 336 */           throw new ParameterSchemaException("'" + typeAtt + "' attribute of property is mandatory in file : " + parFile); 
/* 337 */         if (serializable) {
/* 338 */           throw new ParameterSchemaException("'" + typeAtt + "' attribute of property is mandatory in file : " + parFile);
/*     */         }
/*     */       } else {
/*     */         
/* 342 */         ParameterProperty.PropertyType type = ParameterProperty.PropertyType.get(typeName);
/* 343 */         if (type == null) {
/* 344 */           throw new ParameterSchemaException("Undefined type value : " + typeName + " in file : " + parFile);
/*     */         }
/* 346 */         prop.setType(type);
/* 347 */         String typeVal = propElem.getAttribute(typeAtt + "-name");
/* 348 */         if (type == ParameterProperty.PropertyType.type_business_object || type == ParameterProperty.PropertyType.type_parameter)
/*     */         {
/* 350 */           if (StringUtil.isEmpty(typeVal)) {
/* 351 */             throw new ParameterSchemaException("'" + typeAtt + "-name' is required if type is business-object or parameter in file : " + parFile);
/*     */           }
/*     */         }
/* 354 */         prop.setTypeName(typeVal);
/*     */       } 
/*     */       
/* 357 */       if (map) {
/*     */         
/* 359 */         typeAtt = "key-type";
/* 360 */         typeName = propElem.getAttribute(typeAtt);
/* 361 */         if (StringUtil.isEmpty(typeName)) {
/*     */           
/* 363 */           if (serializable) {
/* 364 */             throw new ParameterSchemaException("'" + typeAtt + "' attribute of property is mandatory in file : " + parFile);
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 369 */           ParameterProperty.PropertyType type = ParameterProperty.PropertyType.get(typeName);
/* 370 */           if (type == null) {
/* 371 */             throw new ParameterSchemaException("Undefined type value : " + typeName + " in file : " + parFile);
/*     */           }
/* 373 */           ((ParameterMapProperty)prop).setKeyType(type);
/* 374 */           String typeVal = propElem.getAttribute(typeAtt + "-name");
/* 375 */           if (type == ParameterProperty.PropertyType.type_business_object || type == ParameterProperty.PropertyType.type_parameter)
/*     */           {
/* 377 */             if (StringUtil.isEmpty(typeVal)) {
/* 378 */               throw new ParameterSchemaException("'" + typeAtt + "-name' is required if type is business-object or parameter in file : " + parFile);
/*     */             }
/*     */           }
/* 381 */           ((ParameterMapProperty)prop).setKeyTypeName(typeVal);
/*     */         } 
/*     */       } 
/*     */     } 
/* 385 */     if (objId) {
/*     */       
/* 387 */       ((ObjectIdentifierProperty)prop).setTableName(propElem.getAttribute("table-name"));
/* 388 */       ((ObjectIdentifierProperty)prop).setSetValue(propElem.getAttribute("set-value"));
/*     */       
/*     */       try {
/* 391 */         loadObjectIdentifierCases((ObjectIdentifierProperty)prop, propElem, parFile);
/*     */       }
/* 393 */       catch (Exception e) {
/*     */         
/* 395 */         ms_Logger.error(e, e);
/* 396 */         throw new ParameterSchemaException(e);
/*     */       } 
/*     */     } 
/* 399 */     loadModifier(propElem, parFile, prop);
/* 400 */     prop.setConstant(DOMUtil.getBooleanAttribute(propElem, "constant"));
/* 401 */     prop.setMandatory(DOMUtil.getBooleanAttribute(propElem, "mandatory"));
/* 402 */     prop.setHasGetter(DOMUtil.getBooleanAttribute(propElem, "has-getter"));
/* 403 */     prop.setHasSetter(DOMUtil.getBooleanAttribute(propElem, "has-setter"));
/* 404 */     prop.setArray(DOMUtil.getBooleanAttribute(propElem, "is-array"));
/* 405 */     loadPartOfIdentifier(propElem, prop);
/* 406 */     prop.setSerializable(serializable);
/* 407 */     prop.setEmptyValue(propElem.getAttribute("empty-value"));
/* 408 */     String s = propElem.getAttribute("initial-value");
/* 409 */     if (!StringUtil.isEmpty(s)) {
/* 410 */       prop.setInitialValue(new SimplePropertyValue(s));
/*     */     }
/* 412 */     loadInitialValue(propElem, parFile, prop);
/*     */     
/* 414 */     int stringTag = DOMUtil.getIntegerAttribute(propElem, "string-tag");
/* 415 */     if (stringTag > 0 && param.getDescResource() != null)
/* 416 */       prop.setDescResource(new JLbsResourceId(param.getDescResource().getResourceGroup(), param
/* 417 */             .getDescResource().getListId(), stringTag)); 
/* 418 */     prop.setDescription(propElem.getAttribute("description"));
/*     */     
/* 420 */     param.getProperties().add(prop);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void loadObjectIdentifierCases(ObjectIdentifierProperty prop, Element propElem, File xmlFile) throws Exception {
/* 425 */     NodeList list = DOMUtil.getChildElementsByName(propElem, "case");
/* 426 */     if (list != null)
/*     */     {
/* 428 */       for (int i = 0; i < list.getLength(); i++) {
/*     */         
/* 430 */         Element node = (Element)list.item(i);
/* 431 */         ObjectIdentifierProperty.ObjectIdentifierCase newCase = new ObjectIdentifierProperty.ObjectIdentifierCase();
/* 432 */         newCase.setTableName(DOMUtil.getMandatoryAttribute(node, xmlFile.getAbsolutePath(), "table-name"));
/* 433 */         newCase.setSetValue(node.getAttribute("set-value"));
/* 434 */         NodeList condList = DOMUtil.getChildElementsByName(node, "condition");
/* 435 */         for (int j = 0; j < condList.getLength(); j++) {
/*     */           
/* 437 */           Element condElem = (Element)condList.item(j);
/* 438 */           ObjectIdentifierProperty.ObjectIdentifierCondition condition = new ObjectIdentifierProperty.ObjectIdentifierCondition();
/* 439 */           condition.setPropertyName(DOMUtil.getMandatoryAttribute(condElem, xmlFile.getAbsolutePath(), "property-name"));
/* 440 */           condition.setValue(DOMUtil.getMandatoryAttribute(condElem, xmlFile.getAbsolutePath(), "value"));
/* 441 */           String opStr = condElem.getAttribute("operator");
/* 442 */           ObjectIdentifierProperty.CaseOperator operator = ObjectIdentifierProperty.CaseOperator.valueOf(opStr);
/*     */ 
/*     */           
/* 445 */           condition.setOperator(operator);
/* 446 */           newCase.addCondition(condition);
/*     */         } 
/* 448 */         prop.addCase(newCase);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void loadModifier(Element propElem, File parFile, ParameterProperty prop) throws ParameterSchemaException {
/* 455 */     String modName = propElem.getAttribute("modifier");
/* 456 */     if (!StringUtil.isEmpty(modName)) {
/*     */       
/* 458 */       ParameterProperty.Modifier mod = ParameterProperty.Modifier.get(modName);
/* 459 */       if (mod == null)
/* 460 */         throw new ParameterSchemaException("Undefined modifier value : " + modName + " in file : " + parFile); 
/* 461 */       prop.setModifier(mod);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void loadPartOfIdentifier(Element propElem, ParameterProperty prop) {
/* 467 */     prop.setPartOfIdentifier(DOMUtil.getBooleanAttribute(propElem, "part-of-identifier", false));
/*     */   }
/*     */ 
/*     */   
/*     */   private static void loadInitialValue(Element propElem, File parFile, ParameterProperty prop) throws ParameterSchemaException {
/* 472 */     Element initialValElem = DOMUtil.getOptionalFirstElement(propElem, "initial-value");
/* 473 */     if (initialValElem != null) {
/*     */       
/* 475 */       PropertyValue value = loadValueElem(initialValElem, parFile);
/* 476 */       if (value != null) {
/* 477 */         prop.setInitialValue(value);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void loadFilterListProperty(ParameterSchema param, Element propElem, File parFile) throws ParameterSchemaException {
/* 484 */     ParameterFilterlistProperty prop = new ParameterFilterlistProperty();
/* 485 */     String name = "Filters";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 490 */     boolean serializable = DOMUtil.getBooleanAttribute(propElem, "serializable");
/*     */     
/* 492 */     prop.setName(name);
/* 493 */     String modName = propElem.getAttribute("modifier");
/* 494 */     if (!StringUtil.isEmpty(modName)) {
/*     */       
/* 496 */       ParameterProperty.Modifier mod = ParameterProperty.Modifier.get(modName);
/* 497 */       if (mod == null)
/* 498 */         throw new ParameterSchemaException("Undefined modifier value : " + modName + " in file : " + parFile); 
/* 499 */       prop.setModifier(mod);
/*     */     } 
/* 501 */     prop.setMandatory(DOMUtil.getBooleanAttribute(propElem, "mandatory"));
/* 502 */     prop.setHasGetter(DOMUtil.getBooleanAttribute(propElem, "has-getter"));
/* 503 */     prop.setHasSetter(DOMUtil.getBooleanAttribute(propElem, "has-setter"));
/* 504 */     prop.setSerializable(serializable);
/* 505 */     int stringTag = DOMUtil.getIntegerAttribute(propElem, "string-tag");
/* 506 */     if (stringTag > 0 && param.getDescResource() != null)
/* 507 */       prop.setDescResource(new JLbsResourceId(param.getDescResource().getResourceGroup(), param
/* 508 */             .getDescResource().getListId(), stringTag)); 
/* 509 */     prop.setDescription(propElem.getAttribute("description"));
/*     */     
/* 511 */     Element child = DOMUtil.getOptionalFirstElement(propElem, "filter-list-content");
/* 512 */     if (child != null)
/*     */     {
/* 514 */       prop.setFilterGroups(loadFilterList(parFile, child));
/*     */     }
/*     */     
/* 517 */     param.getProperties().add(prop);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static ArrayList<ParameterFilterGroup> loadFilterList(File parFile, Element listContentElem) throws ParameterSchemaException {
/* 523 */     ArrayList<ParameterFilterGroup> filterGroups = new ArrayList<>();
/* 524 */     String resGroup = listContentElem.getAttribute("resource-group");
/* 525 */     if (StringUtil.isEmpty(resGroup)) {
/* 526 */       resGroup = "UN";
/*     */     }
/* 528 */     NodeList groupList = listContentElem.getElementsByTagName("FilterGroup");
/* 529 */     if (groupList != null)
/*     */     {
/* 531 */       for (int j = 0; j < groupList.getLength(); j++) {
/*     */         
/* 533 */         Element groupElem = (Element)groupList.item(j);
/* 534 */         ParameterFilterGroup group = new ParameterFilterGroup();
/* 535 */         NamedNodeMap attributes = groupElem.getAttributes();
/* 536 */         for (int i = 0; i < attributes.getLength(); i++) {
/*     */           
/* 538 */           Node attNode = attributes.item(i);
/* 539 */           String propName = attNode.getNodeName();
/* 540 */           if (!StringUtil.isEmpty(propName))
/* 541 */             group.getXUIProperties().put(propName, groupElem.getAttribute(propName)); 
/*     */         } 
/* 543 */         loadFilters(parFile, groupElem, group, resGroup);
/* 544 */         filterGroups.add(group);
/*     */       } 
/*     */     }
/* 547 */     ParameterFilterGroup ungrouped = new ParameterFilterGroup();
/* 548 */     loadFilters(parFile, listContentElem, ungrouped, resGroup);
/* 549 */     filterGroups.add(ungrouped);
/*     */     
/* 551 */     return filterGroups;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void loadFilters(File parFile, Element listContentElem, ParameterFilterGroup filterGroup, String resGroup) throws ParameterSchemaException {
/* 557 */     NodeList list = listContentElem.getElementsByTagName("Filter");
/* 558 */     for (int i = 0; i < list.getLength(); i++) {
/*     */       
/* 560 */       Element filterElem = (Element)list.item(i);
/* 561 */       if (filterElem.getParentNode() == listContentElem) {
/*     */ 
/*     */         
/* 564 */         ParameterFilter filter = new ParameterFilter();
/* 565 */         filter.setXUIDoc(filterElem.getAttribute("XUIDoc"));
/*     */         
/* 567 */         String typeName = filterElem.getAttribute("Type");
/* 568 */         if (StringUtil.isEmpty(typeName))
/*     */         {
/* 570 */           throw new ParameterSchemaException("'Type' attribute of filter is mandatory in file : " + parFile);
/*     */         }
/*     */ 
/*     */         
/* 574 */         ParameterFilter.FilterType type = ParameterFilter.FilterType.get(typeName);
/* 575 */         if (type == null) {
/* 576 */           throw new ParameterSchemaException("Undefined type value : " + typeName + " in file : " + parFile);
/*     */         }
/* 578 */         filter.setType(type);
/* 579 */         if (type.equals(ParameterFilter.FilterType.type_AUTOCOMPLETE)) {
/* 580 */           filter.setForGwt(true);
/*     */         }
/*     */         
/* 583 */         filter.setTitle(filterElem.getAttribute("Title"));
/* 584 */         String tag = filterElem.getAttribute("Tag");
/* 585 */         if (StringUtil.isEmpty(tag))
/* 586 */           throw new ParameterSchemaException("'Type' attribute of filter is mandatory in file : " + parFile); 
/* 587 */         filter.setTag(getIntValue(filterElem, "Tag", parFile));
/* 588 */         filter.setApplicationID(getIntValue(filterElem, "ApplicationID", parFile));
/* 589 */         filter.setResourceGroup(resGroup);
/* 590 */         NodeList propList = filterElem.getElementsByTagName("Property");
/* 591 */         if (propList != null)
/*     */         {
/* 593 */           for (int j = 0; j < propList.getLength(); j++) {
/*     */             
/* 595 */             Element filterProp = (Element)propList.item(j);
/* 596 */             NamedNodeMap attributes = filterProp.getAttributes();
/* 597 */             for (int k = 0; k < attributes.getLength(); k++) {
/*     */               
/* 599 */               Node attNode = attributes.item(k);
/* 600 */               String propName = attNode.getNodeName();
/* 601 */               String propValue = filterProp.getAttribute(propName);
/* 602 */               if (!StringUtil.isEmpty(propValue)) {
/*     */                 
/* 604 */                 Attr attribute = (Attr)filterProp.getAttributes().getNamedItem(propName);
/* 605 */                 if (attribute != null)
/*     */                 {
/* 607 */                   if (propName.equals("DataField")) {
/* 608 */                     filter.setDataField(propValue);
/* 609 */                   } else if (propName.equals("QueryParameter")) {
/* 610 */                     filter.setQueryParameter(propValue);
/* 611 */                   } else if (propName.equals("QueryTableLink")) {
/* 612 */                     filter.setQueryTableLink(propValue);
/* 613 */                   } else if (propName.equals("QueryParameterLB")) {
/* 614 */                     filter.setQueryParameterLB(propValue);
/* 615 */                   } else if (propName.equals("QueryParameterUB")) {
/* 616 */                     filter.setQueryParameterUB(propValue);
/* 617 */                   } else if (propName.equals("QueryRestrictiveTerm")) {
/* 618 */                     filter.setQueryRestrictiveTerm(propValue);
/* 619 */                   } else if (propName.equals("QueryRangeTerm")) {
/* 620 */                     filter.setQueryRangeTerm(propValue);
/* 621 */                   } else if (propName.equals("ListID")) {
/* 622 */                     filter.setListID(getIntValue(filterProp, "ListID", parFile));
/*     */                   }
/* 624 */                   else if (j == 0) {
/* 625 */                     filter.getXUIProperties().put(propName, propValue);
/* 626 */                   } else if (attribute.getSpecified()) {
/* 627 */                     filter.getXUIProperties().put(propName, propValue);
/*     */                   }  } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/* 633 */         NodeList res = filterElem.getElementsByTagName("Resource");
/* 634 */         if (res != null && res.getLength() > 0) {
/*     */           
/* 636 */           Element resNode = (Element)res.item(0);
/* 637 */           filter.setResourceId(new JLbsResourceId(resGroup, getIntValue(resNode, "ListID", parFile), getIntValue(resNode, "StringTag", parFile)));
/*     */         } 
/*     */         
/* 640 */         NodeList modeAttr = filterElem.getElementsByTagName("ModeAttributes");
/* 641 */         if (modeAttr != null && modeAttr.getLength() > 0) {
/*     */           
/* 643 */           Element modeNode = (Element)modeAttr.item(0);
/* 644 */           String modeParams = "";
/* 645 */           for (int j = 0; j < modeNode.getChildNodes().getLength(); j++) {
/*     */             
/* 647 */             if (modeNode.getChildNodes().item(j).toString().contains("ModeParameter")) {
/*     */               
/* 649 */               modeParams = modeParams + "\t<ModeParameter ";
/* 650 */               for (int k = 0; k < modeNode.getChildNodes().item(j).getAttributes().getLength(); k++) {
/*     */                 
/* 652 */                 if (!StringUtil.isEmpty(modeNode.getChildNodes().item(j).getAttributes().item(k).toString()))
/* 653 */                   modeParams = modeParams + modeNode.getChildNodes().item(j).getAttributes().item(k).toString() + " "; 
/*     */               } 
/* 655 */               modeParams = modeParams + "/>\n";
/*     */             } 
/*     */           } 
/* 658 */           filter.setModeAttributes(modeParams);
/*     */         } 
/* 660 */         filterGroup.getFilters().add(filter);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getIntValue(Element filterElem, String attribute, File parFile) throws ParameterSchemaException {
/*     */     try {
/* 668 */       String value = filterElem.getAttribute(attribute);
/* 669 */       if (!StringUtil.isEmpty(value))
/* 670 */         return Integer.parseInt(value); 
/* 671 */       return 0;
/*     */     }
/* 673 */     catch (Exception e) {
/*     */       
/* 675 */       throw new ParameterSchemaException("'" + attribute + "' is not a valid integer value in file : " + parFile);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static PropertyValue loadValueElem(Element elem, File parFile) throws ParameterSchemaException {
/* 681 */     Element e = DOMUtil.getOptionalFirstElement(elem, "variable-from-context");
/* 682 */     if (e != null) {
/*     */       
/* 684 */       String varName = e.getAttribute("var-name");
/* 685 */       if (StringUtil.isEmpty(varName))
/* 686 */         throw new ParameterSchemaException("'var-name' attribute is reqired for variable-from-context in file : " + parFile); 
/* 687 */       return new PropertyValueFromContext(varName);
/*     */     } 
/* 689 */     e = DOMUtil.getOptionalFirstElement(elem, "assignment");
/* 690 */     if (e != null) {
/*     */       
/* 692 */       String className = e.getAttribute("class-name");
/* 693 */       if (StringUtil.isEmpty(className))
/* 694 */         throw new ParameterSchemaException("'class-name' attribute is reqired for assignment in file : " + parFile); 
/* 695 */       String memberName = e.getAttribute("member-name");
/* 696 */       if (StringUtil.isEmpty(memberName))
/* 697 */         throw new ParameterSchemaException("'member-name' attribute is reqired for assignment in file : " + parFile); 
/* 698 */       return new PropertyValueJava(className, memberName);
/*     */     } 
/* 700 */     e = DOMUtil.getOptionalFirstElement(elem, "new-instance");
/* 701 */     if (e != null)
/*     */     {
/* 703 */       return new PropertyValueNewInstance();
/*     */     }
/* 705 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class GenerationConfig
/*     */   {
/*     */     private String m_TargetDir;
/*     */     
/*     */     private String m_ModuleName;
/*     */     private String m_PackageName;
/*     */     
/*     */     public GenerationConfig(String targetDir, String moduleName, String packageName) {
/* 717 */       this.m_TargetDir = targetDir;
/* 718 */       this.m_ModuleName = moduleName;
/* 719 */       this.m_PackageName = packageName;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getTargetDir() {
/* 724 */       return this.m_TargetDir;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setTargetDir(String targetDir) {
/* 729 */       this.m_TargetDir = targetDir;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getModuleName() {
/* 734 */       return this.m_ModuleName;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setModuleName(String moduleName) {
/* 739 */       this.m_ModuleName = moduleName;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setPackageName(String packageName) {
/* 744 */       this.m_PackageName = packageName;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getPackageName() {
/* 749 */       return this.m_PackageName;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\parameter\ParameterSchemaLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */