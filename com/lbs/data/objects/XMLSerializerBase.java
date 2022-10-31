/*      */ package com.lbs.data.objects;
/*      */ 
/*      */ import com.lbs.console.LbsConsole;
/*      */ import com.lbs.data.export.JExportMapItem;
/*      */ import com.lbs.data.export.params.IDataExchangeDescriptor;
/*      */ import com.lbs.data.export.params.IDataExchangeParams;
/*      */ import com.lbs.data.export.params.IDataExportParams;
/*      */ import com.lbs.data.export.params.ListDefinition;
/*      */ import com.lbs.invoke.RttiUtil;
/*      */ import com.lbs.localization.LbsLocalizableException;
/*      */ import com.lbs.localization.LbsMessage;
/*      */ import com.lbs.platform.interfaces.IDataWriter;
/*      */ import com.lbs.util.Base64;
/*      */ import com.lbs.util.CompareUtil;
/*      */ import com.lbs.util.ILbsDataExchangeWriter;
/*      */ import com.lbs.util.ILbsExceptionConstants;
/*      */ import com.lbs.util.IParameterSerializer;
/*      */ import com.lbs.util.JLbsConstants;
/*      */ import com.lbs.util.JLbsStringUtil;
/*      */ import com.lbs.util.SetUtil;
/*      */ import com.lbs.util.StringUtil;
/*      */ import com.lbs.util.xml.LineBreak;
/*      */ import com.thoughtworks.xstream.XStream;
/*      */ import java.io.IOException;
/*      */ import java.io.Serializable;
/*      */ import java.io.StringWriter;
/*      */ import java.lang.reflect.Array;
/*      */ import java.math.BigDecimal;
/*      */ import java.util.Calendar;
/*      */ import java.util.Hashtable;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import javax.xml.transform.Transformer;
/*      */ import javax.xml.transform.TransformerFactory;
/*      */ import javax.xml.transform.dom.DOMSource;
/*      */ import javax.xml.transform.stream.StreamResult;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.NamedNodeMap;
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
/*      */ public abstract class XMLSerializerBase
/*      */   implements Serializable, ILbsExceptionConstants
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*   61 */   protected String m_NameSpace = "";
/*      */ 
/*      */   
/*      */   protected boolean shouldWriteProperty(String propName, Object propValue, Object defaultPropValue, IDataExchangeParams params) {
/*   65 */     return (containsProperty(params, propName) && checkDefaultValue(propValue, defaultPropValue, params));
/*      */   }
/*      */ 
/*      */   
/*      */   public static LbsLocalizableException prepareResolveException(String resolverName) {
/*   70 */     return new LbsLocalizableException(-1003, 46, new LbsMessage("Business object '~1' could not be resolved", new String[] { resolverName }));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isSameWithDefaultValue(Object propValue, Object defaultPropValue) {
/*   76 */     return (CompareUtil.areEqual(propValue, defaultPropValue) || (propValue instanceof Calendar && JLbsConstants.MIN_DATE
/*   77 */       .equals(propValue)));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean areAllValuesSameWithDefaults(Object[] propValues, Object[] defaultValues) {
/*   82 */     if (propValues == null)
/*   83 */       return false; 
/*   84 */     if (defaultValues == null)
/*   85 */       return false; 
/*   86 */     if (propValues.length != defaultValues.length)
/*   87 */       return false;  int i;
/*   88 */     for (i = 0; i < propValues.length; i++) {
/*      */       
/*   90 */       if (defaultValues[i] instanceof String && JLbsStringUtil.isEmpty((String)propValues[i]))
/*   91 */         return true; 
/*      */     } 
/*   93 */     for (i = 0; i < propValues.length; i++) {
/*      */       
/*   95 */       if (!isSameWithDefaultValue(propValues[i], defaultValues[i]))
/*   96 */         return false; 
/*      */     } 
/*   98 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean checkDefaultValue(Object propValue, Object defaultPropValue, IDataExchangeParams params) {
/*  103 */     if (params instanceof IDataExportParams && ((IDataExportParams)params).isSkipDefaultValues())
/*      */     {
/*  105 */       return !isSameWithDefaultValue(propValue, defaultPropValue);
/*      */     }
/*  107 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeProperty(ILbsDataExchangeWriter writer, String propName, Object propValue, int propDBType, IDataExchangeParams params) {
/*      */     try {
/*  115 */       if (writer instanceof com.lbs.util.xml.JLbsDataExchangeWriterJson) {
/*      */         return;
/*      */       }
/*  118 */       if (params.getExchangeType() == 1) {
/*      */         
/*  120 */         writer.writePropertyStartTag(startTag(propName));
/*  121 */         LineBreak orgLineBreak = writer.getLineBreak();
/*  122 */         String indentation = writer.getIndentation();
/*  123 */         writer.setLineBreak(LineBreak.NONE);
/*  124 */         writer.pcdata(getStringValue(propValue, propDBType, params));
/*  125 */         writer.writePropertyEndTag(endTag(propName));
/*  126 */         writer.setLineBreak(orgLineBreak);
/*  127 */         if (orgLineBreak != LineBreak.NONE) {
/*  128 */           writer.setIndentation(indentation);
/*      */         }
/*      */       } else {
/*      */         
/*  132 */         writer.writeObjectProperty(propName, getStringValue(propValue, propDBType, params));
/*      */       }
/*      */     
/*  135 */     } catch (Exception e) {
/*      */       
/*  137 */       LbsConsole.getLogger(getClass()).error("Exception in property write (" + propName + ")" + e.getMessage(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeProperty(IDataWriter writer, String propName, Object propValue, int propDBType, IDataExchangeParams params, Hashtable<String, String> propertyAttributes) {
/*      */     try {
/*  149 */       writer.writeProperty(propName, getStringValue(propValue, propDBType, params), propertyAttributes);
/*      */     }
/*  151 */     catch (Exception e) {
/*      */       
/*  153 */       LbsConsole.getLogger(getClass()).error("Exception in property write (" + propName + ")" + e.getMessage(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeParameterProperty(ILbsDataExchangeWriter writer, String parameterName, String propName, Object propValue, IDataExchangeParams params) {
/*      */     try {
/*  162 */       if (params.getExchangeType() == 1) {
/*      */         
/*  164 */         writer.writePropertyStartTag(startParameterTag(parameterName, propName));
/*  165 */         LineBreak orgLineBreak = writer.getLineBreak();
/*  166 */         String indentation = writer.getIndentation();
/*  167 */         writer.setLineBreak(LineBreak.NONE);
/*  168 */         writer.pcdata(getStringValue(propValue, 0, params));
/*  169 */         writer.writePropertyEndTag(endParameterTag(parameterName, propName));
/*  170 */         writer.setLineBreak(orgLineBreak);
/*  171 */         if (orgLineBreak != LineBreak.NONE) {
/*  172 */           writer.setIndentation(indentation);
/*      */         }
/*      */       } 
/*  175 */     } catch (Exception e) {
/*      */       
/*  177 */       LbsConsole.getLogger(getClass()).error("Exception in property write (" + propName + ")" + e.getMessage(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeParameterPropertyOther(ILbsDataExchangeWriter writer, String parameterName, String propName, Object propValue, IDataExchangeParams params) {
/*      */     try {
/*  186 */       if (params.getExchangeType() == 1) {
/*      */         
/*  188 */         writer.writePropertyStartTag(startParameterTag(parameterName, propName));
/*  189 */         LineBreak orgLineBreak = writer.getLineBreak();
/*  190 */         String indentation = writer.getIndentation();
/*  191 */         writer.setLineBreak(LineBreak.NONE);
/*  192 */         if (propValue != null) {
/*      */           
/*  194 */           XStream magicApi = new XStream();
/*  195 */           magicApi.alias("map", Map.class);
/*  196 */           magicApi.alias("list", List.class);
/*  197 */           String xml = magicApi.toXML(propValue);
/*  198 */           writer.pcdata(xml);
/*      */         } 
/*  200 */         writer.writePropertyEndTag(endParameterTag(parameterName, propName));
/*  201 */         writer.setLineBreak(orgLineBreak);
/*  202 */         if (orgLineBreak != LineBreak.NONE) {
/*  203 */           writer.setIndentation(indentation);
/*      */         }
/*      */       } 
/*  206 */     } catch (Exception e) {
/*      */       
/*  208 */       LbsConsole.getLogger(getClass()).error("Exception in property write (" + propName + ")" + e.getMessage(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeParameterPropertyObject(ILbsDataExchangeWriter writer, String parameterName, String propName, Object propValue, IDataExchangeParams params) {
/*      */     try {
/*  217 */       if (params.getExchangeType() == 1) {
/*      */         
/*  219 */         writer.writePropertyStartTag(startParameterTag(parameterName, propName));
/*  220 */         LineBreak orgLineBreak = writer.getLineBreak();
/*  221 */         String indentation = writer.getIndentation();
/*  222 */         writer.setLineBreak(LineBreak.NONE);
/*  223 */         if (propValue != null)
/*      */         {
/*  225 */           if (propValue instanceof SimpleBusinessObject) {
/*      */             
/*  227 */             SimpleBusinessObject obj = (SimpleBusinessObject)propValue;
/*  228 */             obj.getSerializer().write(writer, obj, params);
/*      */           
/*      */           }
/*  231 */           else if (propValue instanceof IParameterSerializer) {
/*      */             
/*  233 */             IParameterSerializer param = (IParameterSerializer)propValue;
/*  234 */             param.writeParameter(this, writer, params);
/*      */           }
/*      */           else {
/*      */             
/*  238 */             XStream magicApi = new XStream();
/*  239 */             String xml = magicApi.toXML(propValue);
/*  240 */             writer.pcdata(xml);
/*      */           } 
/*      */         }
/*  243 */         writer.writePropertyEndTag(endParameterTag(parameterName, propName));
/*  244 */         writer.setLineBreak(orgLineBreak);
/*  245 */         if (orgLineBreak != LineBreak.NONE) {
/*  246 */           writer.setIndentation(indentation);
/*      */         }
/*      */       } 
/*  249 */     } catch (Exception e) {
/*      */       
/*  251 */       LbsConsole.getLogger(getClass()).error("Exception in property write (" + propName + ")" + e.getMessage(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeParameterProperty(IDataWriter writer, String propName, Object propValue, int propType, IDataExchangeParams params) {
/*      */     try {
/*  263 */       if (params.getExchangeType() == 1)
/*      */       {
/*  265 */         writer.writeProperty(propName, getStringValue(propValue, propType, params), null);
/*      */       }
/*      */     }
/*  268 */     catch (Exception e) {
/*      */       
/*  270 */       LbsConsole.getLogger(getClass()).error("Exception in property write (" + propName + ")" + e.getMessage(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeParameterPropertyOther(IDataWriter writer, String propName, Object propValue, IDataExchangeParams params) {
/*      */     try {
/*  281 */       if (params.getExchangeType() == 1)
/*      */       {
/*  283 */         writer.writeInnerObjectStart(propName);
/*  284 */         String xml = "";
/*  285 */         if (propValue != null) {
/*      */           
/*  287 */           XStream magicApi = new XStream();
/*  288 */           magicApi.alias("map", Map.class);
/*  289 */           magicApi.alias("list", List.class);
/*  290 */           xml = magicApi.toXML(propValue);
/*  291 */           writer.pcdata(xml);
/*      */         } 
/*  293 */         writer.writeInnerObjectEnd(propName);
/*      */       }
/*      */     
/*  296 */     } catch (Exception e) {
/*      */       
/*  298 */       LbsConsole.getLogger(getClass()).error("Exception in property write (" + propName + ")" + e.getMessage(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeParameterPropertyObject(IDataWriter writer, String propName, Object propValue, IDataExchangeParams params) {
/*      */     try {
/*  309 */       if (params.getExchangeType() == 1)
/*      */       {
/*  311 */         writer.writeInnerObjectStart(propName);
/*  312 */         if (propValue != null)
/*      */         {
/*  314 */           if (propValue instanceof SimpleBusinessObject) {
/*      */             
/*  316 */             SimpleBusinessObject obj = (SimpleBusinessObject)propValue;
/*  317 */             obj.getDataSerializer().write(writer, obj, params);
/*      */           
/*      */           }
/*  320 */           else if (propValue instanceof IParameterSerializer) {
/*      */             
/*  322 */             IParameterSerializer param = (IParameterSerializer)propValue;
/*  323 */             param.writeParameter(this, writer, params);
/*      */           }
/*      */           else {
/*      */             
/*  327 */             XStream magicApi = new XStream();
/*  328 */             String xml = magicApi.toXML(propValue);
/*  329 */             writer.pcdata(xml);
/*      */           } 
/*      */         }
/*  332 */         writer.writeInnerObjectEnd(propName);
/*      */       }
/*      */     
/*  335 */     } catch (Exception e) {
/*      */       
/*  337 */       LbsConsole.getLogger(getClass()).error("Exception in property write (" + propName + ")" + e.getMessage(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writePropertyDescription(ILbsDataExchangeWriter writer, String propName, int listId, int stringTag, IDataExchangeParams params) {
/*      */     try {
/*  346 */       if (writer instanceof com.lbs.util.xml.JLbsDataExchangeWriterJson) {
/*      */         
/*  348 */         writer.writeComment(propName + "-" + listId + "-" + stringTag);
/*      */         
/*      */         return;
/*      */       } 
/*  352 */       if (isDescriptive(params))
/*      */       {
/*      */ 
/*      */         
/*  356 */         IDataExchangeDescriptor descriptor = (params == null) ? null : params.getDescriptor();
/*  357 */         if (descriptor != null)
/*      */         {
/*  359 */           String description = descriptor.getDescription(listId, stringTag);
/*  360 */           if (!StringUtil.isEmpty(description))
/*      */           {
/*      */ 
/*      */ 
/*      */             
/*  365 */             writer.writeComment("Property: " + propName + ", Description:" + description);
/*      */           
/*      */           }
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  373 */     catch (Exception e) {
/*      */       
/*  375 */       LbsConsole.getLogger(getClass()).error("Exception in property description write (" + propName + ")" + e
/*  376 */           .getMessage(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writePropertyDescription(IDataWriter writer, String propName, int listId, int stringTag, IDataExchangeParams params) {
/*      */     try {
/*  388 */       if (isDescriptive(params)) {
/*      */ 
/*      */ 
/*      */         
/*  392 */         IDataExchangeDescriptor descriptor = (params == null) ? null : params.getDescriptor();
/*  393 */         if (descriptor != null)
/*      */         {
/*  395 */           String description = descriptor.getDescription(listId, stringTag);
/*  396 */           if (!StringUtil.isEmpty(description))
/*      */           {
/*  398 */             writer.writeComment("Property: " + propName + ", Description:" + description);
/*      */           }
/*      */         }
/*      */       
/*      */       } 
/*  403 */     } catch (Exception e) {
/*      */       
/*  405 */       LbsConsole.getLogger(getClass()).error("Exception in property description write (" + propName + ")" + e
/*  406 */           .getMessage(), e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getStringValue(Object propValue, int propDBType, IDataExchangeParams params) {
/*  412 */     int options = 144;
/*  413 */     if (params != null)
/*  414 */       options = params.getExchangeOptions(); 
/*  415 */     if (!canExportImportValue(options, propDBType))
/*  416 */       return ""; 
/*  417 */     if (propValue == null)
/*  418 */       return ""; 
/*  419 */     if (byte[].class == propValue.getClass()) {
/*  420 */       return Base64.encodeBytes((byte[])propValue);
/*      */     }
/*  422 */     if (propValue.getClass().isArray()) {
/*      */       
/*  424 */       StringBuilder buffer = new StringBuilder();
/*  425 */       int length = Array.getLength(propValue);
/*  426 */       if (length <= 0) {
/*  427 */         return "!![]!!";
/*      */       }
/*  429 */       buffer.append("!![" + getStringValue(Array.get(propValue, 0), propDBType, params));
/*  430 */       for (int i = 1; i < length; i++)
/*      */       {
/*  432 */         buffer.append("," + getStringValue(Array.get(propValue, i), propDBType, params));
/*      */       }
/*      */       
/*  435 */       buffer.append("]!!");
/*      */       
/*  437 */       return buffer.toString();
/*      */     } 
/*  439 */     if (propValue instanceof Calendar) {
/*      */       
/*  441 */       Calendar cal = (Calendar)propValue;
/*  442 */       int calOption = 0;
/*  443 */       if (params != null)
/*  444 */         calOption = params.getCalendarExchangeOption(); 
/*  445 */       switch (calOption) {
/*      */         
/*      */         case 1:
/*  448 */           return StringUtil.toCanonicalString(cal, true);
/*      */       } 
/*      */       
/*  451 */       return String.valueOf(cal.getTimeInMillis());
/*      */     } 
/*      */     
/*  454 */     if (propValue instanceof BigDecimal)
/*      */     {
/*  456 */       if ((new BigDecimal(0)).compareTo((BigDecimal)propValue) == 0)
/*  457 */         return "0"; 
/*      */     }
/*  459 */     Class<?> clazz = propValue.getClass();
/*  460 */     if (RttiUtil.isSimpleClassValueOrWrapper(clazz))
/*  461 */       return String.valueOf(propValue); 
/*  462 */     if (propValue instanceof Serializable) {
/*  463 */       return StringUtil.toSerializedString(propValue);
/*      */     }
/*  465 */     return String.valueOf(propValue);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object readPropertyValue(Element parentNode, String propertyName, int propDBType, Class<?> memberClass, IDataExchangeParams params) {
/*  471 */     if (parentNode == null) {
/*  472 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  477 */       if (containsProperty(params, propertyName))
/*      */       {
/*  479 */         int options = 144;
/*  480 */         if (params != null)
/*  481 */           options = params.getExchangeOptions(); 
/*  482 */         if (!canExportImportValue(options, propDBType)) {
/*  483 */           return null;
/*      */         }
/*  485 */         Element propElem = getChildByName(parentNode, propertyName);
/*  486 */         if (propElem == null) {
/*  487 */           return null;
/*      */         }
/*  489 */         String val = propElem.getNodeValue();
/*  490 */         if (val != null)
/*  491 */           val = val.trim(); 
/*  492 */         if (propElem.hasChildNodes()) {
/*      */           
/*  494 */           StringBuilder buffer = new StringBuilder();
/*  495 */           val = "";
/*  496 */           NodeList list = propElem.getChildNodes();
/*      */           
/*  498 */           for (int i = 0; i < list.getLength(); i++) {
/*      */             
/*  500 */             Node node = list.item(i);
/*  501 */             if (node.getNodeType() == 3) {
/*  502 */               buffer.append(node.getNodeValue());
/*      */             }
/*      */           } 
/*  505 */           val = buffer.toString();
/*      */         } 
/*  507 */         if (!StringUtil.isEmpty(val)) {
/*      */           
/*  509 */           int idx = val.indexOf("!![");
/*  510 */           int endIdx = val.lastIndexOf("]!!");
/*  511 */           if (idx >= 0 && endIdx >= 0) {
/*      */             
/*  513 */             val = val.substring(idx + 3, endIdx);
/*  514 */             if (memberClass.isArray())
/*      */             {
/*  516 */               memberClass = memberClass.getComponentType();
/*      */             }
/*  518 */             if (StringUtil.isEmpty(val))
/*      */             {
/*  520 */               return Array.newInstance(memberClass, 0);
/*      */             }
/*  522 */             String[] vals = val.split(",");
/*  523 */             Object arr = Array.newInstance(memberClass, vals.length);
/*      */             
/*  525 */             for (int i = 0; i < vals.length; i++) {
/*      */ 
/*      */               
/*  528 */               Object arrItem = getObjectFromString(vals[i], memberClass, params);
/*  529 */               Array.set(arr, i, arrItem);
/*      */             } 
/*      */             
/*  532 */             return arr;
/*      */           } 
/*  534 */           return getObjectFromString(val, memberClass, params);
/*      */         } 
/*  536 */         if (val == null && String.class.equals(memberClass))
/*      */         {
/*  538 */           return "";
/*      */         }
/*  540 */         return val;
/*      */       }
/*      */     
/*  543 */     } catch (InvalidClassException e) {
/*      */ 
/*      */       
/*  546 */       throw new InvalidClassException(propertyName + ":" + e.getMessage());
/*      */     } 
/*      */     
/*  549 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object readPropertyValue(Element parentNode, String propertyName, int propDBType, Class<?> memberClass, IDataExchangeParams params, Hashtable<String, String> attributes) {
/*  558 */     if (parentNode == null) {
/*  559 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  564 */       if (containsProperty(params, propertyName))
/*      */       {
/*  566 */         int options = 144;
/*  567 */         if (params != null)
/*  568 */           options = params.getExchangeOptions(); 
/*  569 */         if (!canExportImportValue(options, propDBType)) {
/*  570 */           return null;
/*      */         }
/*  572 */         Element propElem = getChild(parentNode, propertyName);
/*  573 */         if (propElem == null) {
/*  574 */           return null;
/*      */         }
/*  576 */         String val = propElem.getAttribute("value");
/*  577 */         if (val != null)
/*  578 */           val = val.trim(); 
/*  579 */         if (attributes != null) {
/*      */           
/*  581 */           NamedNodeMap attributeMap = propElem.getAttributes();
/*  582 */           for (int i = 0; i < attributeMap.getLength(); i++) {
/*      */             
/*  584 */             Node node = attributeMap.item(i);
/*  585 */             String name = node.getNodeName();
/*  586 */             if (!StringUtil.equals("value", name))
/*      */             {
/*  588 */               attributes.put(name, node.getNodeValue());
/*      */             }
/*      */           } 
/*      */         } 
/*  592 */         if (!StringUtil.isEmpty(val)) {
/*      */           
/*  594 */           int idx = val.indexOf("!![");
/*  595 */           int endIdx = val.lastIndexOf("]!!");
/*  596 */           if (idx >= 0 && endIdx >= 0) {
/*      */             
/*  598 */             val = val.substring(idx + 3, endIdx);
/*  599 */             if (memberClass.isArray())
/*      */             {
/*  601 */               memberClass = memberClass.getComponentType();
/*      */             }
/*  603 */             if (StringUtil.isEmpty(val))
/*      */             {
/*  605 */               return Array.newInstance(memberClass, 0);
/*      */             }
/*  607 */             String[] vals = val.split(",");
/*  608 */             Object arr = Array.newInstance(memberClass, vals.length);
/*      */             
/*  610 */             for (int i = 0; i < vals.length; i++) {
/*      */ 
/*      */               
/*  613 */               Object arrItem = getObjectFromString(vals[i], memberClass, params);
/*  614 */               Array.set(arr, i, arrItem);
/*      */             } 
/*      */             
/*  617 */             return arr;
/*      */           } 
/*  619 */           return getObjectFromString(val, memberClass, params);
/*      */         } 
/*  621 */         if (val == null && String.class.equals(memberClass))
/*      */         {
/*  623 */           return "";
/*      */         }
/*  625 */         if (memberClass == byte[].class && StringUtil.isEmpty(val))
/*      */         {
/*  627 */           return null;
/*      */         }
/*  629 */         if (val != null && val.length() == 0 && !memberClass.equals(String.class)) {
/*      */           
/*  631 */           if (memberClass.isPrimitive())
/*  632 */             return RttiUtil.createInstance(memberClass); 
/*  633 */           return null;
/*      */         } 
/*  635 */         return val;
/*      */       }
/*      */     
/*  638 */     } catch (InvalidClassException e) {
/*      */ 
/*      */       
/*  641 */       throw new InvalidClassException(propertyName + ":" + e.getMessage());
/*      */     } 
/*      */     
/*  644 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object readParameterPropertyValue(Element parentNode, String parameterName, String propertyName, Class<?> memberClass, IDataExchangeParams params) {
/*  650 */     if (parentNode == null) {
/*  651 */       return null;
/*      */     }
/*      */     
/*      */     try {
/*  655 */       Element propElem = getParameterChildByName(parameterName, parentNode, propertyName);
/*  656 */       if (propElem == null) {
/*  657 */         return null;
/*      */       }
/*  659 */       String val = propElem.getNodeValue();
/*  660 */       if (propElem.hasChildNodes()) {
/*      */         
/*  662 */         val = "";
/*  663 */         StringBuilder buffer = new StringBuilder();
/*  664 */         NodeList list = propElem.getChildNodes();
/*      */         
/*  666 */         for (int i = 0; i < list.getLength(); i++) {
/*      */           
/*  668 */           Node node = list.item(i);
/*  669 */           if (node.getNodeType() == 3) {
/*  670 */             buffer.append(node.getNodeValue());
/*      */           }
/*      */         } 
/*  673 */         val = buffer.toString();
/*      */       } 
/*  675 */       if (!StringUtil.isEmpty(val)) {
/*      */         
/*  677 */         int idx = val.indexOf("!![");
/*  678 */         int endIdx = val.lastIndexOf("]!!");
/*  679 */         if (idx >= 0 && endIdx >= 0) {
/*      */           
/*  681 */           val = val.substring(idx + 3, endIdx);
/*  682 */           if (memberClass.isArray())
/*      */           {
/*  684 */             memberClass = memberClass.getComponentType();
/*      */           }
/*  686 */           if (StringUtil.isEmpty(val))
/*      */           {
/*  688 */             return Array.newInstance(memberClass, 0);
/*      */           }
/*  690 */           String[] vals = val.split(",");
/*  691 */           Object arr = Array.newInstance(memberClass, vals.length);
/*      */           
/*  693 */           for (int i = 0; i < vals.length; i++) {
/*      */ 
/*      */             
/*  696 */             Object arrItem = getObjectFromString(vals[i], memberClass, params);
/*  697 */             Array.set(arr, i, arrItem);
/*      */           } 
/*      */           
/*  700 */           return arr;
/*      */         } 
/*  702 */         return getObjectFromString(val, memberClass, params);
/*      */       } 
/*  704 */       if (val == null && String.class.equals(memberClass))
/*      */       {
/*  706 */         return "";
/*      */       }
/*  708 */       return val;
/*      */     }
/*  710 */     catch (Exception e) {
/*      */       
/*  712 */       LbsConsole.getLogger(getClass()).error(e.getMessage());
/*      */       
/*  714 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Object readParameterPropertyOther(Element parentNode, String parameterName, String propertyName, Class<?> memberClass, IDataExchangeParams params) {
/*  720 */     if (parentNode == null) {
/*  721 */       return null;
/*      */     }
/*      */     
/*      */     try {
/*  725 */       Element propElem = getParameterChildByName(parameterName, parentNode, propertyName);
/*  726 */       if (propElem == null) {
/*  727 */         return null;
/*      */       }
/*  729 */       if (propElem.hasChildNodes())
/*      */       {
/*  731 */         Node n = propElem.getChildNodes().item(0);
/*  732 */         String xml = getStringFromNode(n);
/*  733 */         XStream magicApi = new XStream();
/*  734 */         magicApi.alias("map", Map.class);
/*  735 */         magicApi.alias("list", List.class);
/*  736 */         return magicApi.fromXML(xml);
/*      */       }
/*      */     
/*  739 */     } catch (Exception e) {
/*      */       
/*  741 */       LbsConsole.getLogger(getClass()).error(e.getMessage());
/*      */     } 
/*  743 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object readParameterPropertyObject(Element parentNode, String parameterName, String propertyName, Class<?> memberClass, IDataExchangeParams params) {
/*  749 */     if (parentNode == null) {
/*  750 */       return null;
/*      */     }
/*      */     
/*      */     try {
/*  754 */       Element propElem = getParameterChildByName(parameterName, parentNode, propertyName);
/*  755 */       if (propElem == null) {
/*  756 */         return null;
/*      */       }
/*  758 */       Object o = memberClass.newInstance();
/*  759 */       if (o instanceof SimpleBusinessObject) {
/*      */         
/*  761 */         SimpleBusinessObject obj = (SimpleBusinessObject)o;
/*  762 */         int[] childIdx = { 0 };
/*  763 */         for (int i = 0; i < propElem.getChildNodes().getLength(); i++) {
/*      */           
/*  765 */           if (propElem.getChildNodes().item(i) instanceof Element)
/*  766 */             return obj.getSerializer().read((Element)propElem.getChildNodes().item(i), childIdx, params); 
/*      */         } 
/*      */       } else {
/*  769 */         if (o instanceof IParameterSerializer) {
/*      */           
/*  771 */           IParameterSerializer param = (IParameterSerializer)o;
/*  772 */           for (int i = 0; i < propElem.getChildNodes().getLength(); i++) {
/*      */             
/*  774 */             if (propElem.getChildNodes().item(i) instanceof Element)
/*  775 */               param.readParameter(this, (Element)propElem.getChildNodes().item(i), params); 
/*      */           } 
/*  777 */           return param;
/*      */         } 
/*      */ 
/*      */         
/*  781 */         if (propElem.hasChildNodes())
/*      */         {
/*  783 */           Node n = propElem.getChildNodes().item(0);
/*  784 */           String xml = getStringFromNode(n);
/*  785 */           XStream magicApi = new XStream();
/*  786 */           return magicApi.fromXML(xml);
/*      */         }
/*      */       
/*      */       } 
/*  790 */     } catch (Exception e) {
/*      */       
/*  792 */       LbsConsole.getLogger(getClass()).error(e.getMessage());
/*      */     } 
/*      */     
/*  795 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object readParameterPropertyValue(Element parentNode, String propertyName, Class<?> memberClass, IDataExchangeParams params) {
/*  804 */     if (parentNode == null) {
/*  805 */       return null;
/*      */     }
/*      */     
/*      */     try {
/*  809 */       Element propElem = getChild(parentNode, propertyName);
/*  810 */       if (propElem == null) {
/*  811 */         return null;
/*      */       }
/*  813 */       String val = propElem.getAttribute("value");
/*  814 */       if (!StringUtil.isEmpty(val)) {
/*      */         
/*  816 */         int idx = val.indexOf("!![");
/*  817 */         int endIdx = val.lastIndexOf("]!!");
/*  818 */         if (idx >= 0 && endIdx >= 0) {
/*      */           
/*  820 */           val = val.substring(idx + 3, endIdx);
/*  821 */           if (memberClass.isArray())
/*      */           {
/*  823 */             memberClass = memberClass.getComponentType();
/*      */           }
/*  825 */           if (StringUtil.isEmpty(val))
/*      */           {
/*  827 */             return Array.newInstance(memberClass, 0);
/*      */           }
/*  829 */           String[] vals = val.split(",");
/*  830 */           Object arr = Array.newInstance(memberClass, vals.length);
/*      */           
/*  832 */           for (int i = 0; i < vals.length; i++) {
/*      */ 
/*      */             
/*  835 */             Object arrItem = getObjectFromString(vals[i], memberClass, params);
/*  836 */             Array.set(arr, i, arrItem);
/*      */           } 
/*      */           
/*  839 */           return arr;
/*      */         } 
/*  841 */         return getObjectFromString(val, memberClass, params);
/*      */       } 
/*  843 */       if (val == null && String.class.equals(memberClass))
/*      */       {
/*  845 */         return "";
/*      */       }
/*  847 */       return val;
/*      */     }
/*  849 */     catch (Exception e) {
/*      */       
/*  851 */       LbsConsole.getLogger(getClass()).error(e.getMessage());
/*      */       
/*  853 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object readParameterPropertyOther(Element parentNode, String propertyName, Class<?> memberClass, IDataExchangeParams params) {
/*  862 */     if (parentNode == null) {
/*  863 */       return null;
/*      */     }
/*      */     
/*      */     try {
/*  867 */       Element propElem = getChild(parentNode, propertyName);
/*  868 */       if (propElem == null) {
/*  869 */         return null;
/*      */       }
/*  871 */       if (propElem.hasChildNodes())
/*      */       {
/*  873 */         Node n = propElem.getChildNodes().item(0);
/*  874 */         String xml = getStringFromNode(n);
/*  875 */         XStream magicApi = new XStream();
/*  876 */         magicApi.alias("map", Map.class);
/*  877 */         magicApi.alias("list", List.class);
/*  878 */         return magicApi.fromXML(xml);
/*      */       }
/*      */     
/*  881 */     } catch (Exception e) {
/*      */       
/*  883 */       LbsConsole.getLogger(getClass()).error(e.getMessage());
/*      */     } 
/*  885 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object readParameterPropertyObject(Element parentNode, String propertyName, Class<?> memberClass, IDataExchangeParams params, String defaultStateStr) {
/*  894 */     if (parentNode == null) {
/*  895 */       return null;
/*      */     }
/*      */     
/*      */     try {
/*  899 */       Element propElem = getChild(parentNode, propertyName);
/*  900 */       if (propElem == null) {
/*  901 */         return null;
/*      */       }
/*  903 */       Object o = memberClass.newInstance();
/*  904 */       if (o instanceof SimpleBusinessObject) {
/*      */         
/*  906 */         SimpleBusinessObject obj = (SimpleBusinessObject)o;
/*  907 */         int[] childIdx = { 0 };
/*  908 */         for (int i = 0; i < propElem.getChildNodes().getLength(); i++) {
/*      */           
/*  910 */           if (propElem.getChildNodes().item(i) instanceof Element) {
/*  911 */             return obj.getDataSerializer().read((Element)propElem.getChildNodes().item(i), params, childIdx, defaultStateStr);
/*      */           }
/*      */         } 
/*      */       } else {
/*  915 */         if (o instanceof IParameterSerializer) {
/*      */           
/*  917 */           IParameterSerializer param = (IParameterSerializer)o;
/*  918 */           for (int i = 0; i < propElem.getChildNodes().getLength(); i++) {
/*      */             
/*  920 */             if (propElem.getChildNodes().item(i) instanceof Element)
/*  921 */               param.readParameter(this, (Element)propElem.getChildNodes().item(i), params, defaultStateStr); 
/*      */           } 
/*  923 */           return param;
/*      */         } 
/*      */ 
/*      */         
/*  927 */         if (propElem.hasChildNodes())
/*      */         {
/*  929 */           Node n = propElem.getChildNodes().item(0);
/*  930 */           String xml = getStringFromNode(n);
/*  931 */           XStream magicApi = new XStream();
/*  932 */           return magicApi.fromXML(xml);
/*      */         }
/*      */       
/*      */       } 
/*  936 */     } catch (Exception e) {
/*      */       
/*  938 */       LbsConsole.getLogger(getClass()).error(e.getMessage());
/*      */     } 
/*      */     
/*  941 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getStringFromNode(Node node) {
/*      */     try {
/*  948 */       DOMSource domSource = new DOMSource(node);
/*  949 */       StringWriter writer = new StringWriter();
/*  950 */       StreamResult result = new StreamResult(writer);
/*  951 */       TransformerFactory tf = TransformerFactory.newInstance();
/*  952 */       Transformer transformer = tf.newTransformer();
/*  953 */       transformer.setOutputProperty("omit-xml-declaration", "yes");
/*  954 */       transformer.setOutputProperty("method", "xml");
/*  955 */       transformer.setOutputProperty("indent", "yes");
/*  956 */       transformer.setOutputProperty("encoding", "UTF-8");
/*  957 */       transformer.transform(domSource, result);
/*  958 */       return writer.toString();
/*      */     
/*      */     }
/*  961 */     catch (Exception ex) {
/*      */       
/*  963 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Element getChildByName(Element node, String childName) {
/*  969 */     if (!StringUtil.isEmpty(this.m_NameSpace) && !childName.startsWith(this.m_NameSpace + ":"))
/*      */     {
/*  971 */       childName = this.m_NameSpace + ":" + childName;
/*      */     }
/*  973 */     return getChild(node, childName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getChild(Element node, String childName) {
/*  981 */     Node child = node.getFirstChild();
/*  982 */     if (child instanceof Element) {
/*      */       
/*  984 */       Element elem = (Element)child;
/*  985 */       if (StringUtil.equals(childName, elem.getTagName()))
/*  986 */         return elem; 
/*      */     } 
/*  988 */     while (child != null) {
/*      */       
/*  990 */       child = child.getNextSibling();
/*  991 */       if (child instanceof Element) {
/*      */         
/*  993 */         Element elem = (Element)child;
/*  994 */         if (StringUtil.equals(childName, elem.getTagName()))
/*  995 */           return elem; 
/*      */       } 
/*      */     } 
/*  998 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Element getParameterChildByName(String parameterName, Element node, String childName) {
/* 1003 */     if (!StringUtil.isEmpty(parameterName) && !childName.startsWith(parameterName + ":"))
/*      */     {
/* 1005 */       childName = parameterName + ":" + childName;
/*      */     }
/* 1007 */     return getChild(node, childName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object getObjectFromString(String val, Class<?> memberClass, IDataExchangeParams params) {
/*      */     try {
/* 1016 */       if (memberClass == Calendar.class) {
/*      */         
/* 1018 */         int calOption = 0;
/* 1019 */         if (params != null)
/* 1020 */           calOption = params.getCalendarExchangeOption(); 
/* 1021 */         switch (calOption) {
/*      */           
/*      */           case 1:
/* 1024 */             return StringUtil.toCalendar(val, true);
/*      */         } 
/*      */         
/* 1027 */         Calendar cal = Calendar.getInstance();
/* 1028 */         cal.setTimeInMillis(Long.parseLong(val));
/* 1029 */         return cal;
/*      */       } 
/*      */       
/* 1032 */       if (memberClass == byte[].class) {
/*      */         
/* 1034 */         if (StringUtil.isEmpty(val))
/* 1035 */           return null; 
/* 1036 */         return Base64.decode(val);
/*      */       } 
/* 1038 */       if (RttiUtil.isSimpleClassValueOrWrapper(memberClass))
/* 1039 */         return ObjectValueManager.convertMemberValue(val, String.class, memberClass, false); 
/* 1040 */       if (RttiUtil.implementsInterface(memberClass, Serializable.class)) {
/* 1041 */         return StringUtil.getSerializedObject(val);
/*      */       }
/*      */     }
/* 1044 */     catch (Exception e) {
/*      */ 
/*      */       
/* 1047 */       throw new InvalidClassException("\"" + val + "\" is not valid for " + memberClass.getName());
/*      */     } 
/*      */     
/* 1050 */     return val;
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean canExportImportValue(int options, int propDBType) {
/* 1055 */     switch (propDBType) {
/*      */       
/*      */       case 13:
/* 1058 */         return !SetUtil.isOptionSet(options, 1);
/*      */       case 14:
/* 1060 */         return !SetUtil.isOptionSet(options, 2);
/*      */     } 
/* 1062 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isDescriptive(IDataExchangeParams params) {
/* 1067 */     if (params == null) {
/* 1068 */       return true;
/*      */     }
/* 1070 */     return params.isDescriptive();
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean containsProperty(IDataExchangeParams params, String propName) {
/* 1075 */     if (params == null)
/* 1076 */       return true; 
/* 1077 */     ListDefinition listDef = params.getListDefinition();
/* 1078 */     if (listDef == null)
/* 1079 */       return true; 
/* 1080 */     if (listDef.isExcludeAll()) {
/*      */       
/* 1082 */       List<?> list1 = listDef.getIncludeList();
/* 1083 */       if (list1.contains(propName))
/* 1084 */         return true; 
/* 1085 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 1089 */     List<?> list = listDef.getExcludeList();
/* 1090 */     if (!list.contains(propName))
/* 1091 */       return true; 
/* 1092 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected ListDefinition processListDefinition(ListDefinition listDef) {
/* 1098 */     if (listDef == null)
/* 1099 */       return null; 
/* 1100 */     ListDefinition newList = new ListDefinition();
/* 1101 */     newList.setExcludeAll(listDef.isExcludeAll());
/* 1102 */     List<?> list = listDef.getExcludeList();
/*      */     int i;
/* 1104 */     for (i = 0; i < list.size(); i++) {
/*      */       
/* 1106 */       Object item = list.get(i);
/* 1107 */       if (item instanceof JExportMapItem) {
/* 1108 */         newList.addExcludeListItem(((JExportMapItem)item).getQualifiedPropertyName());
/* 1109 */       } else if (item instanceof String) {
/* 1110 */         newList.addExcludeListItem(item);
/*      */       } 
/* 1112 */     }  list = listDef.getIncludeList();
/* 1113 */     for (i = 0; i < list.size(); i++) {
/*      */       
/* 1115 */       Object item = list.get(i);
/* 1116 */       if (item instanceof JExportMapItem) {
/* 1117 */         newList.addIncludeListItem(((JExportMapItem)item).getQualifiedPropertyName());
/* 1118 */       } else if (item instanceof String) {
/* 1119 */         newList.addIncludeListItem(item);
/*      */       } 
/* 1121 */     }  return newList;
/*      */   }
/*      */ 
/*      */   
/*      */   public String endTag(String string) throws IOException {
/* 1126 */     String endTag = "";
/* 1127 */     if (!StringUtil.isEmpty(this.m_NameSpace)) {
/* 1128 */       endTag = this.m_NameSpace + ":" + string;
/*      */     } else {
/* 1130 */       endTag = string;
/* 1131 */     }  return endTag;
/*      */   }
/*      */ 
/*      */   
/*      */   public String startTag(String string) throws IOException {
/* 1136 */     String startTag = "";
/* 1137 */     if (!StringUtil.isEmpty(this.m_NameSpace)) {
/* 1138 */       startTag = this.m_NameSpace + ":" + string;
/*      */     } else {
/* 1140 */       startTag = string;
/* 1141 */     }  return startTag;
/*      */   }
/*      */ 
/*      */   
/*      */   public String startParameterTag(String parameterName, String string) throws IOException {
/* 1146 */     String startTag = "";
/* 1147 */     if (!StringUtil.isEmpty(parameterName)) {
/* 1148 */       startTag = parameterName + ":" + string;
/*      */     } else {
/* 1150 */       startTag = string;
/* 1151 */     }  return startTag;
/*      */   }
/*      */ 
/*      */   
/*      */   public String endParameterTag(String parameterName, String string) throws IOException {
/* 1156 */     String endTag = "";
/* 1157 */     if (!StringUtil.isEmpty(parameterName)) {
/* 1158 */       endTag = parameterName + ":" + string;
/*      */     } else {
/* 1160 */       endTag = string;
/* 1161 */     }  return endTag;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getQualifiedName(String objName) {
/* 1166 */     return getQualifiedName(objName, this.m_NameSpace);
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getQualifiedName(String objName, String nameSpace) {
/* 1171 */     if (!StringUtil.isEmpty(nameSpace))
/* 1172 */       return nameSpace + ":" + objName; 
/* 1173 */     return objName;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String mergeWithPrefix(String prefix, String name) {
/* 1178 */     if (!StringUtil.isEmpty(prefix))
/* 1179 */       return prefix + "." + name; 
/* 1180 */     return name;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Hashtable<String, String> getAttributesHash(boolean resolve) {
/* 1185 */     Hashtable<String, String> hash = new Hashtable<>();
/* 1186 */     hash.put("resolve", String.valueOf(resolve));
/* 1187 */     return hash;
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\XMLSerializerBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */