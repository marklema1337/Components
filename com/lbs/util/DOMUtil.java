/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.sun.org.apache.xml.internal.serialize.OutputFormat;
/*     */ import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.StringReader;
/*     */ import java.util.ArrayList;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.transform.Result;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
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
/*     */ public class DOMUtil
/*     */ {
/*     */   public static final String GITLAB_DEFAULTS_IN = "IN";
/*     */   public static final String GITLAB_DEFAULTS = "Defaults";
/*     */   public static final String GITLAB_RESOURCES = "resources";
/*     */   public static final String GITLAB_IMPORT = "import";
/*     */   public static final String GITLAB_JUGNU = "jugnu";
/*     */   public static final String GIT_PREFIX = "git:|";
/*     */   public static final int FILE_TYPE_IMPORT = 1;
/*     */   public static final int FILE_TYPE_DEFAULTS_IN = 2;
/*     */   public static String ms_GitLabBaseDir;
/*     */   
/*     */   private static DocumentBuilderFactory getFactory() {
/*  66 */     return JLbsClientXMLUtil.getDomFactory();
/*     */   }
/*     */ 
/*     */   
/*     */   public static Document loadXmlDocumentValidated(String fileName) throws Exception {
/*  71 */     return loadXmlDocument(fileName, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Document loadXmlDocument(String fileName, boolean validating, boolean ignoreComments, int fileType) throws Exception {
/*  77 */     Document document = null;
/*     */ 
/*     */     
/*     */     try {
/*  81 */       DocumentBuilderFactory factory = getFactory();
/*  82 */       factory.setValidating(validating);
/*  83 */       factory.setExpandEntityReferences(validating);
/*  84 */       factory.setIgnoringComments(ignoreComments);
/*  85 */       DocumentBuilder parser = factory.newDocumentBuilder();
/*     */       
/*  87 */       if (fileName.contains("git:|") && !JLbsStringUtil.isEmpty(ms_GitLabBaseDir)) {
/*     */         
/*  89 */         String newName = fileName.split("git:\\|")[1];
/*  90 */         if (fileType == 1) {
/*  91 */           fileName = ms_GitLabBaseDir + File.separator + "jugnu" + File.separator + "import" + File.separator + newName;
/*     */         }
/*  93 */         else if (fileType == 2) {
/*  94 */           fileName = ms_GitLabBaseDir + File.separator + "jugnu" + File.separator + "resources" + File.separator + "Defaults" + File.separator + "IN" + File.separator + newName;
/*     */         } 
/*     */       } 
/*  97 */       document = parser.parse(fileName);
/*     */     }
/*  99 */     catch (ParserConfigurationException e) {
/*     */       
/* 101 */       throw new Exception("Exception in DOMUtil.LoadXmlDocument for file '" + fileName + "' :" + e.getMessage());
/*     */     }
/* 103 */     catch (SAXException e) {
/*     */       
/* 105 */       throw new Exception("Exception in DOMUtil.LoadXmlDocument for file '" + fileName + "' :" + e.getMessage());
/*     */     }
/* 107 */     catch (IOException e) {
/*     */       
/* 109 */       throw new Exception("Exception in DOMUtil.LoadXmlDocument for file '" + fileName + "' :" + e.getMessage());
/*     */     } 
/*     */     
/* 112 */     return document;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Document loadXmlDocument(String fileName, boolean validating) throws Exception {
/* 117 */     Document document = null;
/*     */ 
/*     */     
/*     */     try {
/* 121 */       DocumentBuilderFactory factory = getFactory();
/* 122 */       factory.setValidating(validating);
/* 123 */       factory.setExpandEntityReferences(validating);
/* 124 */       DocumentBuilder parser = factory.newDocumentBuilder();
/*     */       
/* 126 */       if (fileName.contains("git:|") && !JLbsStringUtil.isEmpty(ms_GitLabBaseDir)) {
/*     */         
/* 128 */         String newName = fileName.split("git:\\|")[1];
/* 129 */         fileName = ms_GitLabBaseDir + File.separator + "jugnu" + File.separator + "import" + File.separator + newName;
/*     */       } 
/*     */       
/* 132 */       document = parser.parse(fileName);
/*     */     }
/* 134 */     catch (ParserConfigurationException e) {
/*     */       
/* 136 */       throw new Exception("Exception in DOMUtil.LoadXmlDocument for file '" + fileName + "' :" + e.getMessage());
/*     */     }
/* 138 */     catch (SAXException e) {
/*     */       
/* 140 */       throw new Exception("Exception in DOMUtil.LoadXmlDocument for file '" + fileName + "' :" + e.getMessage());
/*     */     }
/* 142 */     catch (IOException e) {
/*     */       
/* 144 */       throw new Exception("Exception in DOMUtil.LoadXmlDocument for file '" + fileName + "' :" + e.getMessage());
/*     */     } 
/*     */     
/* 147 */     return document;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Document loadXmlDocument(InputStream stream, boolean validating) {
/* 152 */     Document document = null;
/*     */ 
/*     */     
/*     */     try {
/* 156 */       DocumentBuilderFactory factory = getFactory();
/* 157 */       factory.setValidating(validating);
/* 158 */       DocumentBuilder parser = factory.newDocumentBuilder();
/* 159 */       byte[] data = JLbsFileUtil.readFile(stream);
/* 160 */       String s = JLbsStringUtil.getString(data);
/* 161 */       InputSource inputSource = new InputSource(new StringReader(s));
/* 162 */       document = parser.parse(inputSource);
/*     */     }
/* 164 */     catch (Exception e) {
/*     */       
/* 166 */       e.printStackTrace();
/* 167 */       LbsConsole.getLogger("Data.Client.DOMUtil").error("Load XML Document", e);
/*     */     } 
/* 169 */     return document;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Document loadXmlDocument(InputStream stream) {
/* 174 */     return loadXmlDocument(stream, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Document loadXmlDocument(byte[] data) {
/* 179 */     return loadXmlDocument(data, (EntityResolver)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Document loadXmlDocument(byte[] data, EntityResolver resolver) {
/* 184 */     Document document = null;
/*     */ 
/*     */     
/*     */     try {
/* 188 */       DocumentBuilderFactory factory = getFactory();
/* 189 */       DocumentBuilder parser = factory.newDocumentBuilder();
/* 190 */       if (resolver != null)
/* 191 */         parser.setEntityResolver(resolver); 
/* 192 */       String s = JLbsStringUtil.getString(data);
/* 193 */       InputSource inputSource = new InputSource(new StringReader(s));
/* 194 */       document = parser.parse(inputSource);
/*     */     }
/* 196 */     catch (Exception e) {
/*     */       
/* 198 */       LbsConsole.getLogger("Data.Client.DOMUtil").error("Load XML Document", e);
/*     */     } 
/*     */     
/* 201 */     return document;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Document loadXmlDocument(String s) {
/* 206 */     Document document = null;
/*     */ 
/*     */     
/*     */     try {
/* 210 */       DocumentBuilderFactory factory = getFactory();
/* 211 */       DocumentBuilder parser = factory.newDocumentBuilder();
/* 212 */       InputSource inputSource = new InputSource(new StringReader(s));
/*     */       
/* 214 */       document = parser.parse(inputSource);
/*     */     }
/* 216 */     catch (Exception e) {
/*     */       
/* 218 */       LbsConsole.getLogger("Data.Client.DOMUtil").error("Load XML Document", e);
/*     */     } 
/*     */     
/* 221 */     return document;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Element getFirstElement(Element element, String name) {
/* 226 */     NodeList nl = element.getElementsByTagName(name);
/*     */     
/* 228 */     if (nl.getLength() < 1) {
/* 229 */       throw new RuntimeException("Element: " + element + " does not contain: " + name);
/*     */     }
/* 231 */     return (Element)nl.item(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Element getFirstChildElement(Element element) {
/* 236 */     NodeList list = element.getChildNodes();
/*     */     
/* 238 */     for (int i = 0; i < list.getLength(); i++) {
/*     */       
/* 240 */       Node node = list.item(i);
/*     */       
/* 242 */       short type = node.getNodeType();
/* 243 */       if (type == 1) {
/* 244 */         return (Element)list.item(i);
/*     */       }
/*     */     } 
/* 247 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Element getOptionalFirstElement(Element element, String name) {
/* 252 */     NodeList nl = element.getElementsByTagName(name);
/*     */     
/* 254 */     if (nl.getLength() < 1) {
/* 255 */       return null;
/*     */     }
/* 257 */     return (Element)nl.item(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Element getContainerElement(Element element, String name) {
/* 262 */     NodeList nl = element.getElementsByTagName(name);
/*     */     
/* 264 */     if (nl.getLength() < 1) {
/* 265 */       return null;
/*     */     }
/* 267 */     return (Element)nl.item(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getSimpleElementText(Element element, String name) {
/* 272 */     Element namedElement = getFirstElement(element, name);
/* 273 */     return getSimpleElementText(namedElement);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getSimpleElementText(Element element) {
/* 278 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 280 */     NodeList children = element.getChildNodes();
/*     */     
/* 282 */     for (int i = 0; i < children.getLength(); i++) {
/*     */       
/* 284 */       Node child = children.item(i);
/* 285 */       if (child instanceof org.w3c.dom.Text)
/* 286 */         sb.append(child.getNodeValue()); 
/*     */     } 
/* 288 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean getBooleanAttribute(Element element, String name) {
/* 293 */     return getBooleanAttribute(element, name, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean getBooleanAttribute(Element element, String name, boolean defaultValue) {
/* 298 */     String attr = element.getAttribute(name);
/* 299 */     if (StringUtil.isEmpty(attr)) {
/* 300 */       return defaultValue;
/*     */     }
/* 302 */     Boolean val = Boolean.valueOf(attr);
/*     */     
/* 304 */     return val.booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getIntegerAttribute(Element element, String name) {
/* 309 */     return getIntegerAttribute(element, name, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getIntegerAttribute(Element element, String name, int defaultValue) {
/* 314 */     String attr = element.getAttribute(name);
/*     */     
/* 316 */     if (StringUtil.isEmpty(attr)) {
/* 317 */       return defaultValue;
/*     */     }
/* 319 */     Integer val = Integer.valueOf(attr);
/*     */     
/* 321 */     return val.intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getLongAttribute(Element element, String name) {
/* 326 */     return getLongAttribute(element, name, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getLongAttribute(Element element, String name, int defaultValue) {
/* 331 */     String attr = element.getAttribute(name);
/*     */     
/* 333 */     if (StringUtil.isEmpty(attr)) {
/* 334 */       return defaultValue;
/*     */     }
/* 336 */     long longValue = defaultValue;
/*     */     
/*     */     try {
/* 339 */       longValue = Long.parseLong(attr);
/*     */     }
/* 341 */     catch (Exception e) {
/*     */       
/* 343 */       return defaultValue;
/*     */     } 
/* 345 */     return longValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public static StringBuilder convert(Document doc) {
/* 350 */     StringBuilder stringBuilder = null;
/*     */     
/*     */     try {
/* 353 */       ByteArrayOutputStream stream = new ByteArrayOutputStream();
/* 354 */       OutputFormat outputformat = new OutputFormat();
/* 355 */       outputformat.setIndent(4);
/* 356 */       outputformat.setIndenting(true);
/* 357 */       outputformat.setPreserveSpace(false);
/* 358 */       outputformat.setEncoding(doc.getXmlEncoding());
/* 359 */       if (doc.getDoctype() != null)
/* 360 */         outputformat.setDoctype(doc.getDoctype().getPublicId(), doc.getDoctype().getSystemId()); 
/* 361 */       XMLSerializer serializer = new XMLSerializer();
/* 362 */       serializer.setOutputFormat(outputformat);
/* 363 */       serializer.setOutputByteStream(stream);
/* 364 */       serializer.asDOMSerializer();
/* 365 */       serializer.serialize(doc.getDocumentElement());
/*     */       
/* 367 */       stringBuilder = new StringBuilder(stream.toString());
/*     */     }
/* 369 */     catch (Exception except) {
/*     */       
/* 371 */       except.getMessage();
/*     */     } 
/* 373 */     return stringBuilder;
/*     */   }
/*     */ 
/*     */   
/*     */   public static NodeList getChildElementsByName(Element node, String name) {
/* 378 */     NodeList children = node.getChildNodes();
/* 379 */     final ArrayList<Element> list = new ArrayList<>();
/* 380 */     if (children != null)
/*     */     {
/* 382 */       for (int i = 0, size = children.getLength(); i < size; i++) {
/*     */         
/* 384 */         Node child = children.item(i);
/* 385 */         if (child instanceof Element && child.getNodeName().equals(name))
/* 386 */           list.add((Element)child); 
/*     */       } 
/*     */     }
/* 389 */     return new NodeList()
/*     */       {
/*     */         
/*     */         public Node item(int index)
/*     */         {
/* 394 */           return list.get(index);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public int getLength() {
/* 400 */           return list.size();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public static Element getChildElementByName(Element node, String name) {
/* 407 */     NodeList list = getChildElementsByName(node, name);
/* 408 */     if (list != null)
/*     */     {
/* 410 */       if (list.getLength() > 0)
/* 411 */         return (Element)list.item(0); 
/*     */     }
/* 413 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getMandatoryAttribute(Element elem, String xmlFile, String attribute) throws Exception {
/* 418 */     return getMandatoryAttribute(elem, xmlFile, attribute, elem.getTagName());
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getMandatoryAttribute(Element elem, String xmlFile, String attribute, String definition) throws Exception {
/* 423 */     String id = elem.getAttribute(attribute);
/* 424 */     if (StringUtil.isEmpty(id))
/* 425 */       throw new Exception(attribute + " attribute is mandatory for " + elem.getTagName() + " definitions : " + xmlFile); 
/* 426 */     return id;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeXMLToFile(Document doc, String targetFile, String[] docTypeLine) throws Exception {
/* 431 */     FileOutputStream fout = null;
/*     */     
/*     */     try {
/* 434 */       TransformerFactory transFactory = TransformerFactory.newInstance();
/* 435 */       Transformer transformer = transFactory.newTransformer();
/* 436 */       transformer.setOutputProperty("indent", "yes");
/* 437 */       transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "8");
/*     */       
/* 439 */       DOMSource source = new DOMSource(doc);
/* 440 */       ByteArrayOutputStream myOutStream = new ByteArrayOutputStream();
/* 441 */       Result result = new StreamResult(new OutputStreamWriter(myOutStream, "utf-8"));
/* 442 */       transformer.transform(source, result);
/*     */       
/* 444 */       String xmlText = new String(myOutStream.toByteArray(), "utf-8");
/* 445 */       int docTypeIdx = xmlText.indexOf("\n");
/* 446 */       if (docTypeIdx >= 0 && docTypeLine[0] != null)
/*     */       {
/* 448 */         xmlText = xmlText.replaceFirst("\n", "\n" + docTypeLine[0] + "\n");
/*     */       }
/* 450 */       fout = new FileOutputStream(new File(targetFile));
/* 451 */       fout.write(xmlText.getBytes("utf-8"));
/* 452 */       fout.flush();
/*     */     }
/*     */     finally {
/*     */       
/* 456 */       if (fout != null)
/* 457 */         fout.close(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\DOMUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */