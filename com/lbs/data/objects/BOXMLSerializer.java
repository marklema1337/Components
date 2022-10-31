/*      */ package com.lbs.data.objects;
/*      */ 
/*      */ import com.lbs.console.LbsConsole;
/*      */ import com.lbs.data.export.IExportPropertyProvider;
/*      */ import com.lbs.data.export.JExportMapItem;
/*      */ import com.lbs.data.export.params.IDataExchangeParams;
/*      */ import com.lbs.data.export.params.IDataExportParams;
/*      */ import com.lbs.data.export.params.ListDefinition;
/*      */ import com.lbs.data.factory.ObjectInitializationException;
/*      */ import com.lbs.platform.interfaces.IDataSerializer;
/*      */ import com.lbs.platform.interfaces.IDataWriter;
/*      */ import com.lbs.platform.interfaces.ILbsBOXMLSerializer;
/*      */ import com.lbs.platform.interfaces.ILbsExchangeCustomizationSerializer;
/*      */ import com.lbs.platform.interfaces.ILbsValidationResult;
/*      */ import com.lbs.util.DOMUtil;
/*      */ import com.lbs.util.ILbsDataExchangeWriter;
/*      */ import com.lbs.util.JLbsConstants;
/*      */ import com.lbs.util.SetUtil;
/*      */ import com.lbs.util.StringUtil;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class BOXMLSerializer
/*      */   extends XMLSerializerBase
/*      */   implements ILbsBOXMLSerializer, Serializable, IDataSerializer
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   
/*      */   protected abstract void writeProperties(ILbsDataExchangeWriter paramILbsDataExchangeWriter, SimpleBusinessObject paramSimpleBusinessObject, IDataExchangeParams paramIDataExchangeParams);
/*      */   
/*      */   protected abstract void writeProperties(IDataWriter paramIDataWriter, SimpleBusinessObject paramSimpleBusinessObject, IDataExchangeParams paramIDataExchangeParams);
/*      */   
/*      */   protected abstract void writeLinkedObjects(ILbsDataExchangeWriter paramILbsDataExchangeWriter, SimpleBusinessObject paramSimpleBusinessObject, IDataExchangeParams paramIDataExchangeParams);
/*      */   
/*      */   protected abstract void writeInnerObjects(IDataWriter paramIDataWriter, SimpleBusinessObject paramSimpleBusinessObject, IDataExchangeParams paramIDataExchangeParams);
/*      */   
/*      */   protected abstract void writeCollections(ILbsDataExchangeWriter paramILbsDataExchangeWriter, SimpleBusinessObject paramSimpleBusinessObject, IDataExchangeParams paramIDataExchangeParams);
/*      */   
/*      */   protected abstract void writeResolvers(ILbsDataExchangeWriter paramILbsDataExchangeWriter, SimpleBusinessObject paramSimpleBusinessObject, IDataExchangeParams paramIDataExchangeParams);
/*      */   
/*      */   protected abstract void addDXRequiredProps(String paramString, ArrayList paramArrayList1, ArrayList paramArrayList2);
/*      */   
/*      */   protected abstract void addDXRequiredPropsOfObjects(String paramString, ArrayList paramArrayList1, ArrayList paramArrayList2);
/*      */   
/*      */   protected abstract void addProperties(List paramList, String paramString);
/*      */   
/*      */   protected abstract void addLinkedProperties(List paramList, String paramString);
/*      */   
/*      */   protected abstract SimpleBusinessObject createObjectInstance();
/*      */   
/*      */   protected abstract void readInitRequiredProperties(Element paramElement, SimpleBusinessObject paramSimpleBusinessObject, IDataExchangeParams paramIDataExchangeParams);
/*      */   
/*      */   protected abstract void readInitRequiredProperties(Element paramElement, IDataExchangeParams paramIDataExchangeParams, SimpleBusinessObject paramSimpleBusinessObject);
/*      */   
/*      */   protected abstract void readProperties(Element paramElement, SimpleBusinessObject paramSimpleBusinessObject, IDataExchangeParams paramIDataExchangeParams);
/*      */   
/*      */   protected abstract void readProperties(Element paramElement, IDataExchangeParams paramIDataExchangeParams, SimpleBusinessObject paramSimpleBusinessObject);
/*      */   
/*      */   protected abstract void readInnerObjects(Element paramElement, IDataExchangeParams paramIDataExchangeParams, SimpleBusinessObject paramSimpleBusinessObject, String paramString) throws ObjectInitializationException;
/*      */   
/*      */   protected abstract void readLinkedObjects(NodeList paramNodeList, SimpleBusinessObject paramSimpleBusinessObject, IDataExchangeParams paramIDataExchangeParams) throws ObjectInitializationException;
/*      */   
/*      */   protected abstract void readCollections(NodeList paramNodeList, SimpleBusinessObject paramSimpleBusinessObject, IDataExchangeParams paramIDataExchangeParams) throws ObjectInitializationException;
/*      */   
/*      */   protected abstract void readResolvers(NodeList paramNodeList, SimpleBusinessObject paramSimpleBusinessObject, IDataExchangeParams paramIDataExchangeParams);
/*      */   
/*      */   public List getProperties(String parentName) {
/*  112 */     ArrayList list = new ArrayList();
/*  113 */     addProperties(list, parentName);
/*  114 */     addLinkedProperties(list, parentName);
/*  115 */     return list;
/*      */   }
/*      */ 
/*      */   
/*      */   public String[] getDXRequiredProperties(SimpleBusinessObject object, boolean includeObjects) {
/*  120 */     ArrayList props = new ArrayList();
/*  121 */     ArrayList objects = new ArrayList();
/*      */     
/*  123 */     collectDXRequiredProps(props, objects, object, includeObjects, "");
/*      */     
/*  125 */     String[] propNames = new String[props.size()];
/*  126 */     propNames = (String[])props.toArray((Object[])propNames);
/*  127 */     return propNames;
/*      */   }
/*      */ 
/*      */   
/*      */   public void write(ILbsDataExchangeWriter writer, SimpleBusinessObject object, IDataExchangeParams params) {
/*  132 */     if (object == null) {
/*      */       return;
/*      */     }
/*      */     try {
/*  136 */       writeObjectHeader(writer, object, params);
/*      */ 
/*      */       
/*  139 */       ListDefinition listDef = (params == null) ? null : params.getListDefinition();
/*  140 */       listDef = processListDefinition(listDef);
/*  141 */       if (params != null)
/*  142 */         params.setListDefinition(listDef); 
/*  143 */       writeProperties(writer, object, params);
/*  144 */       writeLinkObjectStartTag(writer, startTag("LinkObjects"));
/*  145 */       writeLinkedObjects(writer, object, params);
/*  146 */       writeLinkObjectEndTag(writer, endTag("LinkObjects"));
/*  147 */       writeCollectionsStartTag(writer, startTag("Collections"));
/*  148 */       writeCollections(writer, object, params);
/*  149 */       writeCollectionsEndTag(writer, endTag("Collections"));
/*  150 */       writeExtensions(writer, object, params);
/*  151 */       writePropertyResolverStartTag(writer, startTag("PropertyResolvers"));
/*  152 */       writeResolvers(writer, object, params);
/*  153 */       writePropertyResolverEndTag(writer, endTag("PropertyResolvers"));
/*      */       
/*  155 */       int options = params.getExchangeOptions();
/*  156 */       if (SetUtil.isOptionSet(options, 4)) {
/*  157 */         writeParameter(writer, object, params);
/*      */       }
/*  159 */       writeObjectFooter(writer, object);
/*      */     }
/*  161 */     catch (Exception e) {
/*      */       
/*  163 */       LbsConsole.getLogger(getClass()).error(e.getMessage(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void write(IDataWriter writer, SimpleBusinessObject object, IDataExchangeParams params) {
/*  173 */     if (object == null) {
/*      */       return;
/*      */     }
/*      */     try {
/*  177 */       String custGUID = "";
/*      */       
/*  179 */       if (object instanceof CustomBusinessObject) {
/*  180 */         custGUID = object._getCustomization();
/*      */       }
/*  182 */       String objectName = getObjectNameNewVersion(object);
/*  183 */       writer.writeObjectStart(objectName, custGUID, this.m_NameSpace, object.getClass().getName());
/*      */ 
/*      */       
/*  186 */       ListDefinition listDef = (params == null) ? null : params.getListDefinition();
/*  187 */       listDef = processListDefinition(listDef);
/*  188 */       if (params != null)
/*  189 */         params.setListDefinition(listDef); 
/*  190 */       writeProperties(writer, object, params);
/*  191 */       writeInnerObjects(writer, object, params);
/*  192 */       writeExtensions(writer, object, params);
/*      */       
/*  194 */       int options = params.getExchangeOptions();
/*  195 */       if (SetUtil.isOptionSet(options, 4)) {
/*  196 */         writeParameter(writer, object, params);
/*      */       }
/*  198 */       writer.writeObjectEnd();
/*      */     }
/*  200 */     catch (Exception e) {
/*      */       
/*  202 */       LbsConsole.getLogger(getClass()).error(e.getMessage(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String getObjectNameNewVersion(SimpleBusinessObject object) {
/*  208 */     String objectName = object._getObjectName();
/*  209 */     if (objectName.length() > 3)
/*      */     {
/*  211 */       if (objectName.charAt(2) == 'X' && objectName.charAt(3) == 'O')
/*      */       {
/*  213 */         objectName = objectName.substring(0, 2) + "B" + objectName.substring(3);
/*      */       }
/*      */     }
/*  216 */     return objectName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SimpleBusinessObject read(Element parentNode, IDataExchangeParams params, int[] childIdx, String defaultStateStr) throws ObjectInitializationException {
/*      */     try {
/*  228 */       if (parentNode == null || childIdx == null) {
/*  229 */         return null;
/*      */       }
/*  231 */       if (params == null) {
/*  232 */         throw new ObjectInitializationException(new Exception("IDataExchangeParams is mandatory for object inititalization!"), null);
/*      */       }
/*      */       
/*  235 */       SimpleBusinessObject object = createObjectInstance();
/*  236 */       if (object == null) {
/*  237 */         return null;
/*      */       }
/*  239 */       Element node = readObjectHeader((object instanceof CustomBusinessObject) ? (Element)parentNode
/*  240 */           .getParentNode() : parentNode, object, childIdx, defaultStateStr);
/*      */       
/*  242 */       if (node == null)
/*  243 */         return null; 
/*  244 */       int state = object._getState();
/*      */       
/*  246 */       ListDefinition listDef = params.getListDefinition();
/*  247 */       listDef = processListDefinition(listDef);
/*  248 */       params.setListDefinition(listDef);
/*      */       
/*  250 */       int options = params.getExchangeOptions();
/*  251 */       if (SetUtil.isOptionSet(options, 4)) {
/*      */         
/*  253 */         Element parameterNode = getChild(node, "Parameter");
/*  254 */         if (parameterNode != null) {
/*      */           
/*  256 */           NodeList parameter = parameterNode.getChildNodes();
/*  257 */           if (parameter != null)
/*      */           {
/*  259 */             for (int i = 0; i < parameter.getLength(); i++) {
/*      */               
/*  261 */               if (parameter.item(i) instanceof Element) {
/*  262 */                 readParameter((Element)parameter.item(i), object, getSubParams(params, "Parameter"), defaultStateStr);
/*      */               }
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  269 */       readInitRequiredProperties(node, params, object);
/*      */       
/*  271 */       if (object._getState() != 2) {
/*  272 */         validateObject(params, object);
/*      */       }
/*  274 */       readProperties(node, params, object);
/*      */       
/*  276 */       if (object._getState() == 2) {
/*  277 */         validateObject(params, object);
/*      */       }
/*  279 */       readInnerObjects(node, params, object, defaultStateStr);
/*  280 */       Element extNode = getChild(node, "Extensions");
/*  281 */       if (extNode != null) {
/*      */         
/*  283 */         NodeList extensions = extNode.getChildNodes();
/*  284 */         readExtensions(extensions, getSubParams(params, "Extensions"), object, defaultStateStr);
/*      */       } 
/*  286 */       Element extraPropNode = getChild(node, "ExtraProperties");
/*  287 */       if (extraPropNode != null) {
/*      */         
/*  289 */         NodeList extraProps = extraPropNode.getChildNodes();
/*  290 */         if (extraProps != null)
/*      */         {
/*      */           
/*  293 */           for (int i = 0; i < extraProps.getLength(); i++) {
/*      */             
/*  295 */             Node n = extraProps.item(i);
/*  296 */             if (n instanceof Element) {
/*      */               
/*  298 */               Element e = (Element)n;
/*      */               
/*      */               try {
/*  301 */                 object.set(e.getTagName(), e.getAttribute("value"));
/*      */               }
/*  303 */               catch (Exception e1) {
/*      */                 
/*  305 */                 LbsConsole.getLogger(getClass()).error(e1, e1);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */       
/*  312 */       object._setState(state);
/*  313 */       if (JLbsConstants.ENABLE_MULTI_DOMAIN) {
/*      */         
/*  315 */         object.getProperties().set("Exch_DomainID", Integer.valueOf(params.getDomainNr()));
/*  316 */         object.getProperties().set("Exch_OrgRef", 
/*  317 */             Integer.valueOf(object.getUniqueIdentifier().getSimpleKey()));
/*      */       } 
/*  319 */       object.getProperties().set("Exch_Rights", Integer.valueOf(params.getForbiddenOperations()));
/*  320 */       object.getProperties().set("!!!éééREAD_FROM_XMLééé!!!", true);
/*  321 */       return object;
/*      */     }
/*  323 */     catch (ObjectInitializationException e) {
/*      */       
/*  325 */       throw e;
/*      */     }
/*  327 */     catch (InvalidClassException e) {
/*      */       
/*  329 */       throw e;
/*      */     }
/*  331 */     catch (Exception e) {
/*      */       
/*  333 */       LbsConsole.getLogger(getClass()).error(e.getMessage(), e);
/*  334 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void validateObject(IDataExchangeParams params, SimpleBusinessObject object) throws ObjectInitializationException {
/*      */     try {
/*  342 */       ILbsValidationResult result = params.initialize(object);
/*  343 */       if (result == null || !result.canContinue()) {
/*  344 */         throw new ObjectInitializationException(result, object);
/*      */       }
/*  346 */     } catch (Exception e) {
/*      */       
/*  348 */       throw new ObjectInitializationException(e, object);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SimpleBusinessObject read(Element parentNode, int[] childIdx, IDataExchangeParams params) throws ObjectInitializationException {
/*      */     try {
/*  357 */       if (parentNode == null || childIdx == null) {
/*  358 */         return null;
/*      */       }
/*  360 */       if (params == null) {
/*  361 */         throw new ObjectInitializationException(new Exception("IDataExchangeParams is mandatory for object inititalization!"), null);
/*      */       }
/*      */       
/*  364 */       SimpleBusinessObject object = createObjectInstance();
/*  365 */       if (object == null) {
/*  366 */         return null;
/*      */       }
/*  368 */       Element node = readObjectHeader(parentNode, childIdx, object);
/*  369 */       if (node == null)
/*  370 */         return null; 
/*  371 */       int state = object._getState();
/*      */       
/*  373 */       ListDefinition listDef = params.getListDefinition();
/*  374 */       listDef = processListDefinition(listDef);
/*  375 */       params.setListDefinition(listDef);
/*      */ 
/*      */ 
/*      */       
/*  379 */       Element linksElem = getChildByName(node, "LinkObjects");
/*  380 */       if (linksElem != null) {
/*      */         
/*  382 */         NodeList links = linksElem.getChildNodes();
/*  383 */         if (links != null && links.getLength() > 0)
/*      */         {
/*  385 */           readLinkedObjects(links, object, params);
/*      */         }
/*      */       } 
/*  388 */       Element childNode = getChildByName(node, "PropertyResolvers");
/*  389 */       if (childNode != null) {
/*      */         
/*  391 */         NodeList children = childNode.getChildNodes();
/*  392 */         if (children != null && children.getLength() > 0)
/*      */         {
/*  394 */           readResolvers(children, object, params);
/*      */         }
/*      */       } 
/*      */       
/*  398 */       int options = params.getExchangeOptions();
/*  399 */       if (SetUtil.isOptionSet(options, 4)) {
/*      */         
/*  401 */         Element parameterNode = getChildByName(node, "Parameter");
/*  402 */         if (parameterNode != null) {
/*      */           
/*  404 */           NodeList parameter = parameterNode.getChildNodes();
/*  405 */           if (parameter != null)
/*      */           {
/*  407 */             for (int i = 0; i < parameter.getLength(); i++) {
/*      */               
/*  409 */               if (parameter.item(i) instanceof Element) {
/*  410 */                 readParameter((Element)parameter.item(i), object, getSubParams(params, "Parameter"));
/*      */               }
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*  416 */       readInitRequiredProperties(node, object, params);
/*  417 */       validateObject(params, object);
/*      */       
/*  419 */       readProperties(node, object, params);
/*      */       
/*  421 */       Element collsElem = getChildByName(node, "Collections");
/*  422 */       if (collsElem != null) {
/*      */         
/*  424 */         NodeList collections = collsElem.getChildNodes();
/*  425 */         if (collections != null && collections.getLength() > 0)
/*      */         {
/*  427 */           readCollections(collections, object, params);
/*      */         }
/*      */       } 
/*  430 */       Element extNode = getChildByName(node, "Extensions");
/*  431 */       if (extNode != null) {
/*      */         
/*  433 */         NodeList extensions = extNode.getChildNodes();
/*  434 */         readExtensions(extensions, object, getSubParams(params, "Extensions"));
/*      */       } 
/*  436 */       Element extraPropNode = getChildByName(node, "ExtraProperties");
/*  437 */       if (extraPropNode != null) {
/*      */         
/*  439 */         NodeList extraProps = extraPropNode.getChildNodes();
/*  440 */         if (extraProps != null)
/*      */         {
/*      */           
/*  443 */           for (int i = 0; i < extraProps.getLength(); i++) {
/*      */             
/*  445 */             Node n = extraProps.item(i);
/*  446 */             if (n instanceof Element) {
/*      */               
/*  448 */               Element e = (Element)n;
/*      */               
/*      */               try {
/*  451 */                 object.set(e.getTagName(), e.getTextContent());
/*      */               }
/*  453 */               catch (Exception e1) {
/*      */                 
/*  455 */                 LbsConsole.getLogger(getClass()).error(e1, e1);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */       
/*  462 */       object._setState(state);
/*  463 */       if (JLbsConstants.ENABLE_MULTI_DOMAIN) {
/*      */         
/*  465 */         object.getProperties().set("Exch_DomainID", Integer.valueOf(params.getDomainNr()));
/*  466 */         object.getProperties().set("Exch_OrgRef", 
/*  467 */             Integer.valueOf(object.getUniqueIdentifier().getSimpleKey()));
/*      */       } 
/*  469 */       object.getProperties().set("Exch_Rights", Integer.valueOf(params.getForbiddenOperations()));
/*  470 */       object.getProperties().set("!!!éééREAD_FROM_XMLééé!!!", true);
/*  471 */       return object;
/*      */     }
/*  473 */     catch (ObjectInitializationException e) {
/*      */       
/*  475 */       throw e;
/*      */     }
/*  477 */     catch (InvalidClassException e) {
/*      */       
/*  479 */       throw e;
/*      */     }
/*  481 */     catch (Exception e) {
/*      */       
/*  483 */       LbsConsole.getLogger(getClass()).error(e.getMessage(), e);
/*  484 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void writeGrammar(int grammarType, PrintWriter writer, ArrayList writtenTags) {
/*  490 */     switch (grammarType) {
/*      */       
/*      */       case 0:
/*  493 */         writeDTDGrammar(writer, writtenTags);
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNameSpace() {
/*  500 */     return getObjectName().toLowerCase(Locale.UK);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getRootElement() {
/*  505 */     return getQualifiedName(getObjectName());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeDTDGrammar(PrintWriter writer, ArrayList<String> writtenTags) {
/*      */     try {
/*  512 */       if (writer != null && !writtenTags.contains(getRootElement()))
/*      */       {
/*  514 */         writtenTags.add(getRootElement());
/*  515 */         String objName = getObjectName();
/*      */         
/*  517 */         PropertyPair[] links = getLinkObjects();
/*  518 */         PropertyPair[] colls = getCollections();
/*  519 */         writeDTDHeader(writer, objName, links, colls);
/*  520 */         writeDTDLinks(writer, links);
/*  521 */         writeDTDCollections(writer, colls);
/*  522 */         writeDTDExtensions(writer);
/*  523 */         writeDTDPropertyResolvers(writer, writtenTags);
/*  524 */         writeChildDTDs(writer, writtenTags, links, colls);
/*      */       }
/*      */     
/*  527 */     } catch (Exception e) {
/*      */       
/*  529 */       LbsConsole.getLogger(getClass()).error(e.getMessage(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeDTDPropertyResolvers(PrintWriter writer, ArrayList writtenGenerators) {
/*  535 */     ChildGrammarGenerator[] resolvers = getPropertyResolvers();
/*  536 */     if (resolvers == null || resolvers.length == 0) {
/*      */       
/*  538 */       writer.println("<!ELEMENT " + getQualifiedName("PropertyResolvers") + " EMPTY >");
/*  539 */       writer.println("<!ATTLIST " + getQualifiedName("PropertyResolvers") + " >");
/*  540 */       writer.println("");
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  545 */       writer.print("<!ELEMENT " + getQualifiedName("PropertyResolvers") + " ("); int i;
/*  546 */       for (i = 0; i < resolvers.length; i++) {
/*      */         
/*  548 */         ChildGrammarGenerator child = resolvers[i];
/*  549 */         if (i > 0)
/*  550 */           writer.print(","); 
/*  551 */         writer.print(getQualifiedName(child.getPropertyName()) + "?");
/*      */       } 
/*  553 */       writer.println(") >");
/*  554 */       writer.println("<!ATTLIST " + getQualifiedName("PropertyResolvers") + " >");
/*  555 */       writer.println("");
/*      */       
/*  557 */       for (i = 0; i < resolvers.length; i++) {
/*      */         
/*  559 */         ChildGrammarGenerator child = resolvers[i];
/*  560 */         child.getChild().writeGrammar(0, writer, writtenGenerators, 
/*  561 */             getQualifiedName(child.getPropertyName()));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeDTDExtensions(PrintWriter writer) {
/*  568 */     writer.println("<!ELEMENT " + getQualifiedName("Extensions") + " ANY >");
/*  569 */     writer.println("<!ATTLIST " + getQualifiedName("Extensions") + " >");
/*  570 */     writer.println("");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected PropertyPair[] mergeArrays(PropertyPair[] arr1, PropertyPair[] arr2) {
/*  601 */     if (arr1 == null) {
/*      */       
/*  603 */       if (arr2 == null) {
/*  604 */         return null;
/*      */       }
/*  606 */       return arr2;
/*      */     } 
/*  608 */     if (arr2 == null) {
/*  609 */       return arr1;
/*      */     }
/*  611 */     ArrayList<PropertyPair> merged = new ArrayList();
/*      */     int i;
/*  613 */     for (i = 0; i < arr1.length; i++) {
/*      */       
/*  615 */       PropertyPair pair = arr1[i];
/*  616 */       if (merged.contains(pair))
/*  617 */         merged.remove(pair); 
/*  618 */       merged.add(pair);
/*      */     } 
/*  620 */     for (i = 0; i < arr2.length; i++) {
/*      */       
/*  622 */       PropertyPair pair = arr2[i];
/*  623 */       if (merged.contains(pair))
/*  624 */         merged.remove(pair); 
/*  625 */       merged.add(pair);
/*      */     } 
/*  627 */     PropertyPair[] arr = new PropertyPair[merged.size()];
/*  628 */     arr = merged.<PropertyPair>toArray(arr);
/*  629 */     return arr;
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeChildDTDs(PrintWriter writer, ArrayList writtenTags, PropertyPair[] links, PropertyPair[] colls) {
/*  634 */     internalWriteChildDTDs(writer, writtenTags, links);
/*  635 */     internalWriteChildDTDs(writer, writtenTags, colls);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void internalWriteChildDTDs(PrintWriter writer, ArrayList writtenTags, PropertyPair[] links) {
/*  644 */     if (links != null)
/*      */     {
/*  646 */       for (int i = 0; i < links.length; i++) {
/*      */         
/*  648 */         PropertyPair pair = links[i];
/*  649 */         if (pair != null) {
/*      */           
/*  651 */           String propName = pair.getPropertyName();
/*  652 */           SimpleBusinessObject propInstance = pair.getPropertyInstance();
/*  653 */           if (!StringUtil.isEmpty(propName) && propInstance != null) {
/*      */             
/*  655 */             ILbsBOXMLSerializer serializer = propInstance.getSerializer();
/*  656 */             if (serializer != null) {
/*  657 */               serializer.writeGrammar(0, writer, writtenTags);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private void writeDTDHeader(PrintWriter writer, String objName, PropertyPair[] links, PropertyPair[] colls) {
/*  666 */     writer.println("");
/*  667 */     writer.print("<!ELEMENT " + getQualifiedName(objName) + " (");
/*  668 */     List<JExportMapItem> properties = getProperties("");
/*  669 */     String[] dxReqProps = getDXRequiredProperties(createObjectInstance(), false);
/*  670 */     Arrays.sort((Object[])dxReqProps);
/*      */     
/*      */     int i;
/*  673 */     for (i = 0; i < properties.size(); i++) {
/*      */       
/*  675 */       String str = "?";
/*  676 */       JExportMapItem item = properties.get(i);
/*  677 */       String propName = item.getPropertyName();
/*  678 */       if (!contains(links, propName) && !contains(colls, propName)) {
/*      */         
/*  680 */         if (Arrays.binarySearch((Object[])dxReqProps, propName) >= 0)
/*  681 */           str = ""; 
/*  682 */         writer.print(getQualifiedName(propName) + str + ",");
/*      */       } 
/*      */     } 
/*  685 */     writer.println(getQualifiedName("LinkObjects?") + "," + getQualifiedName("Collections?") + "," + 
/*  686 */         getQualifiedName("Extensions?") + "," + getQualifiedName("PropertyResolvers?") + ") >");
/*  687 */     writer.println("<!ATTLIST " + getQualifiedName(objName));
/*  688 */     writer.println("\tobject-state (new | modified | unmodified | deleted) \"new\"");
/*  689 */     writer.println("\tobject-type (class|custom) \"class\"");
/*  690 */     writer.println("\tpackage CDATA #FIXED \"" + getPackageName() + "\"");
/*  691 */     if (!StringUtil.isEmpty(this.m_NameSpace))
/*  692 */       writer.println("\txmlns:" + this.m_NameSpace + " CDATA #FIXED \"" + getPackageName() + "." + getObjectName() + "\""); 
/*  693 */     writer.println(">");
/*  694 */     writer.println("");
/*      */     
/*  696 */     for (i = 0; i < properties.size(); i++) {
/*      */       
/*  698 */       JExportMapItem item = properties.get(i);
/*  699 */       String propName = item.getPropertyName();
/*  700 */       if (!contains(links, propName) && !contains(colls, propName)) {
/*      */         
/*  702 */         writer.println("<!ELEMENT " + getQualifiedName(propName) + " (#PCDATA) >");
/*  703 */         writer.println("<!ATTLIST " + getQualifiedName(propName) + " >");
/*  704 */         writer.println("");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean contains(PropertyPair[] links, String propName) {
/*  711 */     if (links == null)
/*  712 */       return false; 
/*  713 */     for (int i = 0; i < links.length; i++) {
/*      */       
/*  715 */       PropertyPair pair = links[i];
/*  716 */       if (pair != null && StringUtil.equals(propName, pair.getPropertyName()))
/*  717 */         return true; 
/*      */     } 
/*  719 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeDTDCollections(PrintWriter writer, PropertyPair[] colls) {
/*  724 */     internalWriteDTDLinkedProps(writer, colls, true);
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeDTDLinks(PrintWriter writer, PropertyPair[] links) {
/*  729 */     internalWriteDTDLinkedProps(writer, links, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void internalWriteDTDLinkedProps(PrintWriter writer, PropertyPair[] links, boolean collection) {
/*  738 */     writer.print("<!ELEMENT " + getQualifiedName(collection ? "Collections " : "LinkObjects "));
/*      */ 
/*      */     
/*  741 */     StringBuilder buffer = new StringBuilder();
/*      */     
/*  743 */     boolean written = false;
/*  744 */     if (links != null)
/*      */     {
/*  746 */       for (int i = 0; i < links.length; i++) {
/*      */         
/*  748 */         PropertyPair pair = links[i];
/*  749 */         if (pair != null) {
/*      */           
/*  751 */           String propName = pair.getPropertyName();
/*  752 */           SimpleBusinessObject propInstance = pair.getPropertyInstance();
/*  753 */           if (!StringUtil.isEmpty(propName) && propInstance != null) {
/*      */             String objectName, nameSpace;
/*  755 */             ILbsBOXMLSerializer serializer = propInstance.getSerializer();
/*  756 */             if (serializer instanceof BOXMLSerializer) {
/*      */               
/*  758 */               objectName = ((BOXMLSerializer)serializer).getObjectName();
/*      */             }
/*      */             else {
/*      */               
/*  762 */               objectName = propInstance.getClass().getName();
/*  763 */               int idx = objectName.lastIndexOf('.');
/*  764 */               if (idx >= 0)
/*  765 */                 objectName = objectName.substring(idx + 1); 
/*      */             } 
/*  767 */             if (serializer != null) {
/*  768 */               nameSpace = serializer.getNameSpace();
/*      */             } else {
/*  770 */               nameSpace = objectName.toLowerCase(Locale.UK);
/*      */             } 
/*  772 */             if (written) {
/*  773 */               writer.print(",");
/*      */             } else {
/*  775 */               writer.print("(");
/*  776 */             }  writer.print(getQualifiedName(propName) + "?");
/*  777 */             written = true;
/*  778 */             buffer.append("<!ELEMENT " + getQualifiedName(propName) + " (" + getQualifiedName(objectName, nameSpace) + (collection ? "+" : "") + ") >\n");
/*      */ 
/*      */ 
/*      */             
/*  782 */             buffer.append("<!ATTLIST " + getQualifiedName(propName) + " >\n");
/*  783 */             buffer.append("\n");
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*  788 */     if (written) {
/*  789 */       writer.println(") >");
/*      */     } else {
/*  791 */       writer.println(" EMPTY >");
/*  792 */     }  writer.println("<!ATTLIST " + getQualifiedName(collection ? "Collections " : "LinkObjects ") + " >");
/*      */ 
/*      */     
/*  795 */     writer.println("");
/*  796 */     writer.print(buffer.toString());
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getObjectName() {
/*  801 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getPackageName() {
/*  806 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   public PropertyPair[] getLinkObjects() {
/*  811 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public PropertyPair[] getCollections() {
/*  816 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected ChildGrammarGenerator[] getPropertyResolvers() {
/*  821 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void readExtensions(NodeList extensions, SimpleBusinessObject object, IDataExchangeParams params) throws ObjectInitializationException {
/*  827 */     ObjectExtensions objExts = object.getExtensions();
/*  828 */     if (objExts == null || params == null) {
/*      */       return;
/*      */     }
/*  831 */     Hashtable serializers = params.getExtensionSerializers();
/*  832 */     if (serializers != null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  839 */       for (int i = 0; i < extensions.getLength(); i++) {
/*      */         
/*  841 */         Node item = extensions.item(i);
/*  842 */         if (item instanceof Element) {
/*      */           
/*  844 */           String extName = ((Element)item).getTagName();
/*  845 */           if (containsProperty(params, extName)) {
/*      */             
/*  847 */             ILbsBOXMLSerializer serializer = (ILbsBOXMLSerializer)serializers.get(extName);
/*  848 */             if (serializer != null) {
/*      */               
/*  850 */               IDataExchangeParams subParams = getSubParams(params, extName);
/*  851 */               SimpleBusinessObject extObj = serializer.read((Element)item, new int[] { 0 }, subParams);
/*  852 */               if (extObj instanceof CustomBusinessObject) {
/*  853 */                 objExts.add((CustomBusinessObject)extObj);
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void readExtensions(NodeList extensions, IDataExchangeParams params, SimpleBusinessObject object, String defaultStateStr) throws ObjectInitializationException {
/*  867 */     ObjectExtensions objExts = object.getExtensions();
/*  868 */     if (objExts == null || params == null) {
/*      */       return;
/*      */     }
/*  871 */     Hashtable serializers = params.getExtensionSerializers();
/*  872 */     if (serializers != null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  879 */       for (int i = 0; i < extensions.getLength(); i++) {
/*      */         
/*  881 */         Node item = extensions.item(i);
/*  882 */         if (item instanceof Element) {
/*      */           
/*  884 */           String extName = ((Element)item).getTagName();
/*  885 */           if (containsProperty(params, extName)) {
/*      */             
/*  887 */             IDataSerializer serializer = (IDataSerializer)serializers.get(extName);
/*  888 */             if (serializer != null) {
/*      */               
/*  890 */               IDataExchangeParams subParams = getSubParams(params, extName);
/*  891 */               SimpleBusinessObject extObj = serializer.read((Element)item, subParams, new int[] { 0 }, defaultStateStr);
/*  892 */               if (extObj instanceof CustomBusinessObject) {
/*  893 */                 objExts.add((CustomBusinessObject)extObj);
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected SimpleBusinessObject readLinkedObject(NodeList linkObjects, String propertyName, SimpleBusinessObject linkObject, IDataExchangeParams params) throws ObjectInitializationException {
/*  904 */     if (linkObjects == null || linkObjects.getLength() <= 0 || linkObject == null || !containsProperty(params, propertyName)) {
/*  905 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  912 */     for (int i = 0; i < linkObjects.getLength(); i++) {
/*      */       
/*  914 */       Node node = linkObjects.item(i);
/*  915 */       if (node instanceof Element) {
/*      */         
/*  917 */         Element item = (Element)node;
/*  918 */         String memberName = item.getTagName();
/*  919 */         if (StringUtil.equals(memberName, getQualifiedName(propertyName))) {
/*      */           
/*  921 */           ILbsBOXMLSerializer serializer = linkObject.getSerializer();
/*  922 */           if (serializer != null) {
/*      */             
/*  924 */             IDataExchangeParams subParams = getSubParams(params, propertyName);
/*  925 */             return serializer.read(item, new int[] { 0 }, subParams);
/*      */           } 
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*  931 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected SimpleBusinessObjects readCollection(NodeList collections, String propertyName, SimpleBusinessObject itemInstance, SimpleBusinessObjects<SimpleBusinessObject> collection, IDataExchangeParams params) throws ObjectInitializationException {
/*  937 */     if (collections == null || collections.getLength() <= 0 || itemInstance == null || collection == null || 
/*  938 */       !containsProperty(params, propertyName)) {
/*  939 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  944 */     SimpleBusinessObject itemObj = null;
/*      */     
/*  946 */     int[] childIdx = { 0 };
/*      */     
/*  948 */     for (int i = 0; i < collections.getLength(); i++) {
/*      */       
/*  950 */       Node node = collections.item(i);
/*  951 */       if (node instanceof Element) {
/*      */         
/*  953 */         Element item = (Element)node;
/*  954 */         String memberName = item.getTagName();
/*  955 */         if (StringUtil.equals(memberName, getQualifiedName(propertyName))) {
/*      */           
/*  957 */           ILbsBOXMLSerializer serializer = itemInstance.getSerializer();
/*  958 */           if (serializer != null) {
/*      */             
/*  960 */             IDataExchangeParams subParams = getSubParams(params, propertyName);
/*      */             
/*      */             while (true) {
/*  963 */               itemObj = serializer.read(item, childIdx, subParams);
/*  964 */               if (itemObj != null) {
/*  965 */                 collection.add(itemObj); continue;
/*      */               } 
/*      */               break;
/*      */             } 
/*      */           } 
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*  974 */     return collection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SimpleBusinessObject readLinkedObject(Element parentNode, String propertyName, SimpleBusinessObject linkObject, IDataExchangeParams params, String defaultStateStr) throws ObjectInitializationException {
/*  983 */     if (parentNode == null || linkObject == null || !containsProperty(params, propertyName)) {
/*  984 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  991 */     NodeList childNode = parentNode.getChildNodes();
/*  992 */     for (int i = 0; i < childNode.getLength(); i++) {
/*      */       
/*  994 */       Node node = childNode.item(i);
/*  995 */       if (node instanceof Element) {
/*      */         
/*  997 */         Element item = (Element)node;
/*  998 */         String memberName = item.getTagName();
/*  999 */         if (StringUtil.equals(memberName, propertyName)) {
/*      */           
/* 1001 */           IDataSerializer serializer = linkObject.getDataSerializer();
/* 1002 */           if (serializer != null) {
/*      */             
/* 1004 */             IDataExchangeParams subParams = getSubParams(params, propertyName);
/* 1005 */             return serializer.read(item, subParams, new int[] { 0 }, defaultStateStr);
/*      */           } 
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1011 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SimpleBusinessObjects readCollection(Element parentNode, String propertyName, SimpleBusinessObject itemInstance, SimpleBusinessObjects<SimpleBusinessObject> collection, IDataExchangeParams params, String defaultStateStr) throws ObjectInitializationException {
/* 1021 */     if (parentNode == null || itemInstance == null || collection == null || !containsProperty(params, propertyName)) {
/* 1022 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1027 */     SimpleBusinessObject itemObj = null;
/*      */     
/* 1029 */     int[] childIdx = { 0 };
/*      */     
/* 1031 */     NodeList childNodes = parentNode.getChildNodes();
/* 1032 */     for (int i = 0; i < childNodes.getLength(); i++) {
/*      */       
/* 1034 */       Node node = childNodes.item(i);
/* 1035 */       if (node instanceof Element) {
/*      */         
/* 1037 */         Element item = (Element)node;
/* 1038 */         String memberName = item.getTagName();
/* 1039 */         if (StringUtil.equals(memberName, propertyName)) {
/*      */           
/* 1041 */           IDataSerializer serializer = itemInstance.getDataSerializer();
/* 1042 */           if (serializer != null) {
/*      */             
/* 1044 */             IDataExchangeParams subParams = getSubParams(params, propertyName);
/*      */             
/*      */             while (true) {
/* 1047 */               itemObj = serializer.read(item, subParams, childIdx, defaultStateStr);
/* 1048 */               if (itemObj != null) {
/* 1049 */                 collection.add(itemObj); continue;
/*      */               } 
/*      */               break;
/*      */             } 
/*      */           } 
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1058 */     return collection;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Element readObjectHeader(Element parentNode, int[] childIdx, SimpleBusinessObject object) {
/* 1063 */     String objTag = getQualifiedName(object._getObjectName());
/* 1064 */     if (!StringUtil.isEmpty(objTag)) {
/*      */       
/* 1066 */       NodeList elems = parentNode.getElementsByTagName(objTag);
/* 1067 */       if (elems != null && elems.getLength() > childIdx[0]) {
/*      */         
/* 1069 */         Element objNode = (Element)elems.item(childIdx[0]);
/* 1070 */         childIdx[0] = childIdx[0] + 1;
/* 1071 */         if (objNode != null) {
/*      */           
/* 1073 */           String stateStr = objNode.getAttribute("object-state");
/* 1074 */           int state = getObjectState(stateStr);
/* 1075 */           object._setState(state);
/* 1076 */           return objNode;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1080 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Element readObjectHeader(Element parentNode, SimpleBusinessObject object, int[] childIdx, String defaultStateStr) {
/* 1088 */     String objTag = getObjectNameNewVersion(object);
/* 1089 */     if (!StringUtil.isEmpty(objTag)) {
/*      */       
/* 1091 */       NodeList elems = DOMUtil.getChildElementsByName(parentNode, objTag);
/* 1092 */       if (elems != null && elems.getLength() > childIdx[0]) {
/*      */         
/* 1094 */         Element objNode = (Element)elems.item(childIdx[0]);
/* 1095 */         childIdx[0] = childIdx[0] + 1;
/* 1096 */         if (objNode != null) {
/*      */           
/* 1098 */           String stateStr = objNode.getAttribute("object-state");
/* 1099 */           if (StringUtil.isEmpty(stateStr))
/*      */           {
/* 1101 */             stateStr = defaultStateStr;
/*      */           }
/* 1103 */           int state = getObjectState(stateStr);
/* 1104 */           object._setState(state);
/* 1105 */           return objNode;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1109 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addProperty(List list, String parentName, String propName, boolean readOnly) {
/* 1114 */     addProperty(list, parentName, propName, readOnly, 0, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addProperty(List<JExportMapItem> list, String parentName, String propName, boolean readOnly, int listId, int tag) {
/* 1119 */     JExportMapItem item = new JExportMapItem();
/* 1120 */     item.setInclude(1);
/* 1121 */     item.setParentPropertyName(parentName);
/* 1122 */     item.setMapName(propName);
/* 1123 */     item.setPropertyName(propName);
/* 1124 */     item.setReadOnly(readOnly);
/* 1125 */     item.setListId(listId);
/* 1126 */     item.setTag(tag);
/* 1127 */     list.add(item);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addProperty(List<JExportMapItem> list, String parentName, String propName, boolean readOnly, int listId, int tag, int propertyDbType) {
/* 1133 */     JExportMapItem item = new JExportMapItem();
/* 1134 */     item.setInclude(1);
/* 1135 */     item.setParentPropertyName(parentName);
/* 1136 */     item.setMapName(propName);
/* 1137 */     item.setPropertyName(propName);
/* 1138 */     item.setReadOnly(readOnly);
/* 1139 */     item.setListId(listId);
/* 1140 */     item.setTag(tag);
/* 1141 */     item.setPropertyDbType(propertyDbType);
/* 1142 */     list.add(item);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addLinkedObject(List list, String parentName, String propName, SimpleBusinessObject obj) {
/* 1147 */     addObject(list, parentName, propName, obj, 2, 0, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addLinkedObject(List list, String parentName, String propName, SimpleBusinessObject obj, int listId, int tag) {
/* 1152 */     addObject(list, parentName, propName, obj, 2, listId, tag);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addCollection(List list, String parentName, String propName, SimpleBusinessObject itemObject) {
/* 1157 */     addObject(list, parentName, propName, itemObject, 1, 0, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addCollection(List list, String parentName, String propName, SimpleBusinessObject itemObject, int listId, int tag) {
/* 1162 */     addObject(list, parentName, propName, itemObject, 1, listId, tag);
/*      */   }
/*      */ 
/*      */   
/*      */   private void addObject(List list, String parentName, String propName, SimpleBusinessObject obj, int type, int listId, int tag) {
/* 1167 */     JExportMapItem item = new JExportMapItem();
/* 1168 */     item.setInclude(1);
/* 1169 */     item.setParentPropertyName(parentName);
/* 1170 */     item.setMapName(propName);
/* 1171 */     item.setPropertyName(propName);
/* 1172 */     item.setPropertyType(type);
/* 1173 */     item.setPropertyProvider((IExportPropertyProvider)obj.getSerializer());
/* 1174 */     item.setListId(listId);
/* 1175 */     item.setTag(tag);
/* 1176 */     item.setObjectName(obj.getClass().getName());
/* 1177 */     addToList(list, item);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addToList(List<JExportMapItem> list, JExportMapItem itemToAdd) {
/* 1184 */     for (int i = list.size() - 1; i >= 0; i--) {
/*      */       
/* 1186 */       Object o = list.get(i);
/* 1187 */       if (o instanceof JExportMapItem) {
/*      */         
/* 1189 */         JExportMapItem item = (JExportMapItem)o;
/* 1190 */         if (item.equals(itemToAdd)) {
/*      */           
/* 1192 */           list.set(i, itemToAdd);
/*      */           return;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1197 */     list.add(itemToAdd);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void collectDXRequiredProps(ArrayList props, ArrayList<Class<?>> objects, SimpleBusinessObject object, boolean includeObjects, String prefix) {
/* 1203 */     if (!objects.contains(object.getClass())) {
/*      */       
/* 1205 */       objects.add(object.getClass());
/* 1206 */       addDXRequiredProps(prefix, objects, props);
/* 1207 */       if (includeObjects)
/*      */       {
/* 1209 */         addDXRequiredPropsOfObjects(prefix, objects, props);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addDXRequiredProp(ArrayList<String> props, ArrayList objects, String prefix, String propName) {
/* 1216 */     prefix = qualifyPropName(prefix, propName);
/* 1217 */     if (!props.contains(prefix)) {
/* 1218 */       props.add(prefix);
/*      */     }
/*      */   }
/*      */   
/*      */   private String qualifyPropName(String prefix, String propName) {
/* 1223 */     if (!StringUtil.isEmpty(prefix)) {
/* 1224 */       return prefix + "." + propName;
/*      */     }
/* 1226 */     return propName;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addDXRequiredObject(ArrayList props, ArrayList objects, String prefix, String propName, SimpleBusinessObject obj) {
/* 1231 */     prefix = qualifyPropName(prefix, propName);
/* 1232 */     ILbsBOXMLSerializer serializer = obj.getSerializer();
/* 1233 */     if (serializer instanceof BOXMLSerializer) {
/*      */       
/* 1235 */       ((BOXMLSerializer)serializer).collectDXRequiredProps(props, objects, obj, true, prefix);
/*      */     }
/*      */     else {
/*      */       
/* 1239 */       String[] propNames = serializer.getDXRequiredProperties(obj, true);
/* 1240 */       for (int i = 0; i < propNames.length; i++)
/*      */       {
/* 1242 */         addDXRequiredProp(props, objects, prefix, propNames[i]);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeExtensions(ILbsDataExchangeWriter writer, SimpleBusinessObject object, IDataExchangeParams params) throws IOException {
/* 1250 */     if (containsProperty(params, "Extensions")) {
/*      */       
/* 1252 */       ObjectExtensions exts = object.getExtensions();
/* 1253 */       if (exts != null && exts.m_List.size() > 0) {
/*      */         
/* 1255 */         Hashtable serializers = params.getExtensionSerializers();
/* 1256 */         if (serializers != null) {
/*      */           
/* 1258 */           writer.writeExtensionsStartTag("Extensions");
/* 1259 */           params = getSubParams(params, "Extensions");
/*      */           
/* 1261 */           Enumeration<String> keys = serializers.keys();
/*      */ 
/*      */ 
/*      */           
/* 1265 */           while (keys.hasMoreElements()) {
/*      */             
/* 1267 */             String extName = keys.nextElement();
/* 1268 */             SimpleBusinessObject extObj = exts.get(extName);
/* 1269 */             if (extObj != null && containsProperty(params, extName)) {
/*      */               
/* 1271 */               ILbsBOXMLSerializer serializer = (ILbsBOXMLSerializer)serializers.get(extName);
/* 1272 */               if (serializer != null) {
/*      */                 
/* 1274 */                 IDataExchangeParams subParams = getSubParams(params, extName);
/* 1275 */                 serializer.write(writer, extObj, subParams);
/*      */               } 
/*      */             } 
/*      */           } 
/* 1279 */           writer.writeExtensionsEndTag("Extensions");
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeExtensions(IDataWriter writer, SimpleBusinessObject object, IDataExchangeParams params) throws IOException {
/* 1290 */     if (containsProperty(params, "Extensions")) {
/*      */       
/* 1292 */       ObjectExtensions exts = object.getExtensions();
/* 1293 */       if (exts != null && exts.m_List.size() > 0) {
/*      */         
/* 1295 */         writer.writeInnerObjectStart("Extensions");
/* 1296 */         Hashtable serializers = params.getExtensionSerializers();
/* 1297 */         if (serializers != null && serializers.size() > 0) {
/*      */           
/* 1299 */           params = getSubParams(params, "Extensions");
/*      */           
/* 1301 */           Enumeration<String> keys = serializers.keys();
/*      */ 
/*      */ 
/*      */           
/* 1305 */           while (keys.hasMoreElements()) {
/*      */             
/* 1307 */             String extName = keys.nextElement();
/* 1308 */             SimpleBusinessObject extObj = exts.get(extName);
/* 1309 */             if (extObj != null && containsProperty(params, extName)) {
/*      */               
/* 1311 */               IDataSerializer serializer = (IDataSerializer)serializers.get(extName);
/* 1312 */               if (serializer != null)
/*      */               {
/* 1314 */                 IDataExchangeParams subParams = getSubParams(params, extName);
/* 1315 */                 serializer.write(writer, extObj, subParams);
/*      */               }
/*      */             
/*      */             } 
/*      */           } 
/*      */         } else {
/*      */           
/* 1322 */           String GUID = params.getCustGUID();
/* 1323 */           if (GUID != null && params instanceof IDataExportParams) {
/*      */             
/* 1325 */             ILbsExchangeCustomizationSerializer customSerializerFinder = ((IDataExportParams)params).getCustomSerializerFinder();
/* 1326 */             if (customSerializerFinder != null) {
/*      */               
/* 1328 */               ArrayList<ILbsBOXMLSerializer> serializers2 = new ArrayList<>();
/* 1329 */               ArrayList<CustomBusinessObject> objectsWithSpecificSerializer = new ArrayList<>(); int i;
/* 1330 */               for (i = 0; i < exts.m_List.size(); i++) {
/*      */                 
/* 1332 */                 CustomBusinessObject element = exts.m_List.get(i);
/* 1333 */                 if (GUID.equalsIgnoreCase(element._getCustomization())) {
/*      */                   
/* 1335 */                   serializers2.add(customSerializerFinder.getCustomizationSerializer(element, GUID));
/* 1336 */                   objectsWithSpecificSerializer.add(element);
/*      */                 } 
/*      */               } 
/*      */               
/* 1340 */               if (!serializers2.isEmpty())
/*      */               {
/* 1342 */                 for (i = 0; i < serializers2.size(); i++) {
/*      */                   
/* 1344 */                   ILbsBOXMLSerializer serializer = serializers2.get(i);
/* 1345 */                   CustomBusinessObject extObj = objectsWithSpecificSerializer.get(i);
/* 1346 */                   ((IDataSerializer)serializer).write(writer, extObj, getSubParams(params, "Extensions"));
/*      */                 } 
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/* 1352 */         writer.writeInnerObjectEnd("Extensions");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeObjectHeader(ILbsDataExchangeWriter writer, SimpleBusinessObject object, IDataExchangeParams params) throws IOException {
/* 1360 */     boolean isCustom = object instanceof CustomBusinessObject;
/* 1361 */     String custGUID = "";
/*      */     
/* 1363 */     if (isCustom) {
/* 1364 */       custGUID = object._getCustomization();
/*      */     }
/* 1366 */     String packageName = (object.getClass().getPackage() != null) ? object.getClass().getPackage().getName() : "";
/*      */ 
/*      */     
/* 1369 */     writer.writeObjectHeader(startTag(object._getObjectName()), getObjectState(object, params), isCustom ? "custom" : "class", custGUID, packageName, this.m_NameSpace, object
/*      */         
/* 1371 */         .getClass().getName(), isCustom);
/*      */   }
/*      */ 
/*      */   
/*      */   private String getObjectState(SimpleBusinessObject object, IDataExchangeParams params) {
/* 1376 */     int state = params.getDesiredObjectState();
/* 1377 */     if (state < 0)
/* 1378 */       state = object._getState(); 
/* 1379 */     switch (state) {
/*      */       
/*      */       case 3:
/* 1382 */         return "deleted";
/*      */       case 2:
/* 1384 */         return "modified";
/*      */       case 1:
/* 1386 */         return "unmodified";
/*      */     } 
/*      */     
/* 1389 */     return "new";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private int getObjectState(String state) {
/* 1395 */     if (StringUtil.equals(state, "deleted"))
/* 1396 */       return 3; 
/* 1397 */     if (StringUtil.equals(state, "modified"))
/* 1398 */       return 2; 
/* 1399 */     if (StringUtil.equals(state, "new"))
/* 1400 */       return 0; 
/* 1401 */     if (StringUtil.equals(state, "unmodified")) {
/* 1402 */       return 1;
/*      */     }
/* 1404 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeObjectFooter(ILbsDataExchangeWriter writer, SimpleBusinessObject object) throws IOException {
/* 1410 */     writer.writeObjectFooter(endTag(object._getObjectName()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeLinkedProperty(ILbsDataExchangeWriter writer, String propName, SimpleBusinessObject propValue, IDataExchangeParams params) {
/*      */     try {
/* 1418 */       if (containsProperty(params, propName))
/*      */       {
/* 1420 */         if (propValue != null && propValue.getUniqueIdentifier().getSimpleKey() > 0) {
/*      */           
/* 1422 */           ILbsBOXMLSerializer serializer = propValue.getSerializer();
/* 1423 */           if (serializer != null)
/*      */           {
/* 1425 */             params = getSubParams(params, propName);
/* 1426 */             writer.writeLinkedObjectStartTag(startTag(propName));
/* 1427 */             serializer.write(writer, propValue, params);
/* 1428 */             writer.writeLinkedObjectEndTag(endTag(propName));
/*      */           }
/*      */         
/*      */         } 
/*      */       }
/* 1433 */     } catch (Exception e) {
/*      */       
/* 1435 */       LbsConsole.getLogger(getClass()).error("Exception in link object write (" + propName + ")" + e.getMessage(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeCollectionProperty(ILbsDataExchangeWriter writer, String propName, SimpleBusinessObjects<SimpleBusinessObject> propValue, IDataExchangeParams params) {
/*      */     try {
/* 1444 */       if (containsProperty(params, propName))
/*      */       {
/*      */         
/* 1447 */         if (propValue != null) {
/*      */ 
/*      */ 
/*      */           
/* 1451 */           int size = propValue.size();
/* 1452 */           if (size > 0)
/*      */           {
/* 1454 */             params = getSubParams(params, propName);
/*      */             
/* 1456 */             writer.writeCollectionsStartTag(startTag(propName));
/* 1457 */             for (int i = 0; i < size; i++) {
/*      */               
/* 1459 */               SimpleBusinessObject item = propValue.itemAt(i);
/* 1460 */               if (item.getUniqueIdentifier().getSimpleKey() != 0) {
/*      */                 
/* 1462 */                 ILbsBOXMLSerializer serializer = item.getSerializer();
/* 1463 */                 if (serializer != null)
/*      */                 {
/* 1465 */                   serializer.write(writer, item, params); } 
/*      */               } 
/*      */             } 
/* 1468 */             writeCollectionsEndTag(writer, endTag(propName));
/*      */           }
/*      */         
/*      */         } 
/*      */       }
/* 1473 */     } catch (Exception e) {
/*      */       
/* 1475 */       LbsConsole.getLogger(getClass()).error("Exception in link object write (" + propName + ")" + e.getMessage(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeLinkedProperty(IDataWriter writer, String propName, SimpleBusinessObject propValue, IDataExchangeParams params) {
/*      */     try {
/* 1487 */       if (containsProperty(params, propName))
/*      */       {
/* 1489 */         if (propValue != null && propValue.getUniqueIdentifier().getSimpleKey() > 0) {
/*      */           
/* 1491 */           IDataSerializer serializer = propValue.getDataSerializer();
/* 1492 */           if (serializer != null)
/*      */           {
/* 1494 */             params = getSubParams(params, propName);
/* 1495 */             writer.writeInnerObjectStart(propName);
/* 1496 */             serializer.write(writer, propValue, params);
/* 1497 */             writer.writeInnerObjectEnd(propName);
/*      */           }
/*      */         
/*      */         } 
/*      */       }
/* 1502 */     } catch (Exception e) {
/*      */       
/* 1504 */       LbsConsole.getLogger(getClass()).error("Exception in link object write (" + propName + ")" + e.getMessage(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeCollectionProperty(IDataWriter writer, String propName, SimpleBusinessObjects<SimpleBusinessObject> propValue, IDataExchangeParams params) {
/*      */     try {
/* 1516 */       if (containsProperty(params, propName))
/*      */       {
/*      */         
/* 1519 */         if (propValue != null) {
/*      */ 
/*      */ 
/*      */           
/* 1523 */           int size = propValue.size();
/* 1524 */           if (size > 0)
/*      */           {
/* 1526 */             params = getSubParams(params, propName);
/*      */             
/* 1528 */             writer.writeInnerObjectStart(propName);
/* 1529 */             for (int i = 0; i < size; i++) {
/*      */               
/* 1531 */               SimpleBusinessObject item = propValue.itemAt(i);
/* 1532 */               if (item.getUniqueIdentifier().getSimpleKey() != 0) {
/*      */                 
/* 1534 */                 IDataSerializer serializer = item.getDataSerializer();
/* 1535 */                 if (serializer != null)
/*      */                 {
/* 1537 */                   serializer.write(writer, item, params); } 
/*      */               } 
/*      */             } 
/* 1540 */             writer.writeInnerObjectEnd(propName);
/*      */           }
/*      */         
/*      */         } 
/*      */       }
/* 1545 */     } catch (Exception e) {
/*      */       
/* 1547 */       LbsConsole.getLogger(getClass()).error("Exception in link object write (" + propName + ")" + e.getMessage(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private IDataExchangeParams getSubParams(IDataExchangeParams params, String propName) {
/* 1553 */     if (params == null) {
/* 1554 */       return null;
/*      */     }
/* 1556 */     IDataExchangeParams subParams = params.cloneParams();
/* 1557 */     ListDefinition listDef = params.getListDefinition();
/* 1558 */     if (listDef != null) {
/*      */       
/* 1560 */       List<String> list = listDef.getExcludeList();
/* 1561 */       String searchStr = propName + ".";
/* 1562 */       int searchLength = searchStr.length();
/*      */       
/* 1564 */       ListDefinition subList = new ListDefinition();
/* 1565 */       subList.setExcludeAll(listDef.isExcludeAll()); int i;
/* 1566 */       for (i = 0; i < list.size(); i++) {
/*      */         
/* 1568 */         String item = list.get(i);
/* 1569 */         if (item.startsWith(searchStr))
/*      */         {
/* 1571 */           subList.addExcludeListItem(item.substring(searchLength));
/*      */         }
/*      */       } 
/* 1574 */       list = listDef.getIncludeList();
/* 1575 */       for (i = 0; i < list.size(); i++) {
/*      */         
/* 1577 */         String item = list.get(i);
/* 1578 */         if (item.startsWith(searchStr))
/*      */         {
/* 1580 */           subList.addIncludeListItem(item.substring(searchLength));
/*      */         }
/*      */       } 
/* 1583 */       subParams.setListDefinition(subList);
/*      */     } 
/* 1585 */     return subParams;
/*      */   }
/*      */ 
/*      */   
/*      */   public class PropertyPair
/*      */     implements Serializable
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */     
/*      */     private String m_PropertyName;
/*      */     private SimpleBusinessObject m_PropertyInstance;
/*      */     
/*      */     public PropertyPair(String propertyName, SimpleBusinessObject propertyInstance) {
/* 1598 */       this.m_PropertyName = propertyName;
/* 1599 */       this.m_PropertyInstance = propertyInstance;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getPropertyName() {
/* 1604 */       return this.m_PropertyName;
/*      */     }
/*      */ 
/*      */     
/*      */     public SimpleBusinessObject getPropertyInstance() {
/* 1609 */       return this.m_PropertyInstance;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean equals(Object obj) {
/* 1614 */       if (obj instanceof PropertyPair)
/*      */       {
/* 1616 */         return StringUtil.equals(((PropertyPair)obj).getPropertyName(), this.m_PropertyName);
/*      */       }
/* 1618 */       return super.equals(obj);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void writeLinkObjectStartTag(ILbsDataExchangeWriter writer, String string) {
/* 1624 */     writer.writeLinkedObjectStartTag(string);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void writeLinkObjectEndTag(ILbsDataExchangeWriter writer, String string) {
/* 1629 */     writer.writeLinkedObjectEndTag(string);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void writeCollectionsStartTag(ILbsDataExchangeWriter writer, String string) {
/* 1634 */     writer.writeCollectionsStartTag(string);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void writeCollectionsEndTag(ILbsDataExchangeWriter writer, String string) {
/* 1639 */     writer.writeCollectionsEndTag(string);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void writePropertyResolverStartTag(ILbsDataExchangeWriter writer, String string) {
/* 1644 */     writer.writePropertyResolverStartTag(string);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void writePropertyResolverEndTag(ILbsDataExchangeWriter writer, String string) {
/* 1649 */     writer.writePropertyResolverEndTag(string);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void writeExtensionsStartTag(ILbsDataExchangeWriter writer, String string) {
/* 1654 */     writer.writeExtensionsStartTag(string);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void writeExtensionsEndTag(ILbsDataExchangeWriter writer, String string) {
/* 1659 */     writer.writeExtensionsEndTag(string);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void writeParameterStartTag(ILbsDataExchangeWriter writer, String string) {
/* 1664 */     writer.writeParameterStartTag(string);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void writeParameterEndTag(ILbsDataExchangeWriter writer, String string) {
/* 1669 */     writer.writeParameterEndTag(string);
/*      */   }
/*      */   
/*      */   protected void writeParameter(ILbsDataExchangeWriter writer, SimpleBusinessObject object, IDataExchangeParams params) throws Exception {}
/*      */   
/*      */   protected void writeParameter(IDataWriter writer, SimpleBusinessObject object, IDataExchangeParams params) throws Exception {}
/*      */   
/*      */   protected void readParameter(Element parentNode, SimpleBusinessObject object, IDataExchangeParams params) {}
/*      */   
/*      */   protected void readParameter(Element parentNode, SimpleBusinessObject object, IDataExchangeParams params, String defaultStateStr) {}
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\BOXMLSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */