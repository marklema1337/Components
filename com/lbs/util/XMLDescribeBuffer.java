/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.interfaces.IXMLDescriber;
/*     */ import com.lbs.invoke.RttiUtil;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Stack;
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
/*     */ public class XMLDescribeBuffer
/*     */ {
/*     */   private StringBuffer m_Buffer;
/*     */   private int m_Level;
/*  26 */   private Stack<String> m_Tags = new Stack<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLDescribeBuffer(StringBuffer buffer) {
/*  31 */     this.m_Buffer = buffer;
/*  32 */     if (this.m_Buffer == null) {
/*  33 */       this.m_Buffer = new StringBuffer();
/*     */     }
/*     */   }
/*     */   
/*     */   public void startObject(String objectTagName, Hashtable<String, String> atts) {
/*  38 */     startTag(objectTagName, atts);
/*  39 */     newLine();
/*  40 */     indent();
/*  41 */     this.m_Tags.push(objectTagName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void startObject(String objectTagName, String description) {
/*  46 */     if (description == null)
/*  47 */       description = ""; 
/*  48 */     Hashtable<String, String> atts = new Hashtable<>();
/*  49 */     atts.put("description", description);
/*  50 */     startTag(objectTagName, atts);
/*  51 */     newLine();
/*  52 */     indent();
/*  53 */     this.m_Tags.push(objectTagName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeProperty(String propertyName, String propertyType, String description, boolean mandatory) {
/*  58 */     Hashtable<String, String> atts = preparePropAtts(propertyType, description, mandatory);
/*  59 */     startTag(propertyName, atts);
/*  60 */     this.m_Buffer.append("<!-- Value -->");
/*  61 */     this.m_Buffer.append("</" + propertyName + ">");
/*  62 */     newLine();
/*     */   }
/*     */ 
/*     */   
/*     */   private Hashtable<String, String> preparePropAtts(String propertyType, String description, boolean mandatory) {
/*  67 */     if (description == null)
/*  68 */       description = ""; 
/*  69 */     Hashtable<String, String> atts = new Hashtable<>();
/*  70 */     if (!JLbsStringUtil.isEmpty(propertyType))
/*  71 */       atts.put("type", propertyType); 
/*  72 */     atts.put("description", description);
/*  73 */     atts.put("mandatory", String.valueOf(mandatory));
/*  74 */     return atts;
/*     */   }
/*     */ 
/*     */   
/*     */   public void startObjectProperty(String propertyName, String propertyType, String description, boolean mandatory) {
/*  79 */     propertyType = JLbsStringUtil.replace(propertyType, '<', '(');
/*  80 */     propertyType = JLbsStringUtil.replace(propertyType, '>', ')');
/*  81 */     if (propertyName.equals("Filters") && propertyType.equals("JLbsFilterList"))
/*  82 */       propertyType = null; 
/*  83 */     Hashtable<String, String> atts = preparePropAtts(propertyType, description, mandatory);
/*  84 */     startTag(propertyName, atts);
/*  85 */     newLine();
/*  86 */     indent();
/*  87 */     this.m_Tags.push(propertyName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void endObjectProperty() {
/*  92 */     endObject();
/*     */   }
/*     */ 
/*     */   
/*     */   public void endObject() {
/*  97 */     String tag = this.m_Tags.pop();
/*  98 */     unindent();
/*  99 */     endTag(tag);
/* 100 */     newLine();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeInnerObject(Object o, Class<?> c, ILocalizationServices localizationService) {
/*     */     try {
/* 107 */       if (o instanceof IXMLDescriber) {
/*     */         
/* 109 */         ((IXMLDescriber)o).describePropertiesXML(this, localizationService);
/*     */       }
/* 111 */       else if (!isAbstract(c)) {
/*     */         
/* 113 */         o = c.newInstance();
/* 114 */         if (o instanceof IXMLDescriber) {
/* 115 */           ((IXMLDescriber)o).describePropertiesXML(this, localizationService);
/*     */         } else {
/* 117 */           writeOnlyClass(c, "Details of this object is not known");
/*     */         } 
/*     */       } else {
/*     */         
/* 121 */         o = JLbsComponentHelper.createAbstractClassInstance(c);
/* 122 */         if (o instanceof IXMLDescriber) {
/* 123 */           ((IXMLDescriber)o).describePropertiesXML(this, localizationService);
/*     */         } else {
/* 125 */           writeOnlyClass(c, "Details of this object is not known");
/*     */         } 
/*     */       } 
/* 128 */     } catch (Throwable e) {
/*     */       
/* 130 */       e.printStackTrace();
/* 131 */       writeOnlyClass(c, "Details of this object is not known");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeInnerListObject(Class<?> itemClass, ILocalizationServices localizationService) {
/*     */     try {
/* 139 */       writeFromClass(itemClass, "Value", localizationService);
/*     */     }
/* 141 */     catch (Throwable e) {
/*     */       
/* 143 */       e.printStackTrace();
/* 144 */       writeOnlyClass(itemClass, "Details of this object is not known");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeInnerMapObject(Class<?> keyClass, Class<?> valueClass, ILocalizationServices localizationService) {
/*     */     try {
/* 152 */       startTag("entry");
/* 153 */       newLine();
/* 154 */       indent();
/* 155 */       writeFromClass(keyClass, "Key", localizationService);
/* 156 */       writeFromClass(valueClass, "Value", localizationService);
/* 157 */       unindent();
/* 158 */       endTag("entry");
/* 159 */       newLine();
/*     */     }
/* 161 */     catch (Throwable e) {
/*     */       
/* 163 */       e.printStackTrace();
/* 164 */       writeOnlyClass(keyClass, "Details of this object is not known");
/* 165 */       writeOnlyClass(valueClass, "Details of this object is not known");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeFromClass(Class<?> keyClass, String comment, ILocalizationServices localizationService) throws InstantiationException, IllegalAccessException {
/* 173 */     if (RttiUtil.isFirstClassValueOrWrapper(keyClass)) {
/*     */       
/* 175 */       writeOnlyClass(keyClass, comment);
/*     */     }
/* 177 */     else if (!isAbstract(keyClass)) {
/*     */       
/* 179 */       Object key = keyClass.newInstance();
/* 180 */       if (key instanceof IXMLDescriber) {
/* 181 */         ((IXMLDescriber)key).describeXML(this, localizationService);
/*     */       } else {
/* 183 */         writeOnlyClass(keyClass, "Details of this object is not known");
/*     */       } 
/*     */     } else {
/*     */       
/* 187 */       Object key = JLbsComponentHelper.createAbstractClassInstance(keyClass);
/* 188 */       if (key instanceof IXMLDescriber) {
/* 189 */         ((IXMLDescriber)key).describeXML(this, localizationService);
/*     */       } else {
/* 191 */         writeOnlyClass(keyClass, "Details of this object is not known");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeOnlyClass(Class<?> keyClass, String comment) {
/* 197 */     String className = keyClass.getName();
/* 198 */     if (className.startsWith("java.lang"))
/* 199 */       className = className.substring(10); 
/* 200 */     startTag(className);
/* 201 */     this.m_Buffer.append("<!-- " + comment + " -->");
/* 202 */     this.m_Buffer.append("</" + className + ">");
/* 203 */     newLine();
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isAbstract(Class<?> c) {
/* 208 */     return ((c.getModifiers() & 0x400) == 1024);
/*     */   }
/*     */ 
/*     */   
/*     */   private void newLine() {
/* 213 */     this.m_Buffer.append("\n");
/*     */   }
/*     */ 
/*     */   
/*     */   private void indent() {
/* 218 */     this.m_Level++;
/*     */   }
/*     */ 
/*     */   
/*     */   private void unindent() {
/* 223 */     this.m_Level--;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getTabs() {
/* 228 */     StringBuffer s = new StringBuffer();
/* 229 */     for (int i = 0; i < this.m_Level; i++)
/*     */     {
/* 231 */       s.append("\t");
/*     */     }
/*     */     
/* 234 */     return s.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private void startTag(String tagName, Hashtable<String, String> atts) {
/* 239 */     this.m_Buffer.append(String.valueOf(getTabs()) + "<" + tagName + " ");
/* 240 */     if (atts != null) {
/*     */       
/* 242 */       Enumeration<String> keys = atts.keys();
/* 243 */       while (keys.hasMoreElements()) {
/*     */         
/* 245 */         String key = keys.nextElement();
/* 246 */         String value = atts.get(key);
/* 247 */         this.m_Buffer.append(String.valueOf(key) + "=\"" + value + "\" ");
/*     */       } 
/*     */     } 
/* 250 */     this.m_Buffer.append(">");
/*     */   }
/*     */ 
/*     */   
/*     */   private void startTag(String tagName) {
/* 255 */     this.m_Buffer.append(String.valueOf(getTabs()) + "<" + tagName + ">");
/*     */   }
/*     */ 
/*     */   
/*     */   private void endTag(String tagName) {
/* 260 */     this.m_Buffer.append(String.valueOf(getTabs()) + "</" + tagName + ">");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 266 */     if (this.m_Buffer != null)
/* 267 */       return this.m_Buffer.toString(); 
/* 268 */     return super.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLevel() {
/* 273 */     return this.m_Level;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\XMLDescribeBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */