/*     */ package com.lbs.platform.client;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.data.export.params.IDataExchangeParams;
/*     */ import com.lbs.data.export.params.ListDefinition;
/*     */ import com.lbs.data.objects.ChildGrammarGenerator;
/*     */ import com.lbs.data.objects.XMLSerializerBase;
/*     */ import com.lbs.platform.interfaces.IDefObjectResolver;
/*     */ import com.lbs.platform.interfaces.ILbsGrammarGenerator;
/*     */ import com.lbs.util.ILbsDataExchangeWriter;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Locale;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ResolverXMLSerializer
/*     */   extends XMLSerializerBase
/*     */   implements IDefObjectResolver, ILbsGrammarGenerator
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   protected abstract IDefObjectResolver createInstance();
/*     */   
/*     */   protected abstract void writeProperties(ILbsDataExchangeWriter paramILbsDataExchangeWriter, IDefObjectResolver paramIDefObjectResolver, IDataExchangeParams paramIDataExchangeParams);
/*     */   
/*     */   protected abstract void writeResolvers(ILbsDataExchangeWriter paramILbsDataExchangeWriter, IDefObjectResolver paramIDefObjectResolver, IDataExchangeParams paramIDataExchangeParams) throws IOException;
/*     */   
/*     */   protected abstract void readProperties(Element paramElement, IDefObjectResolver paramIDefObjectResolver, IDataExchangeParams paramIDataExchangeParams);
/*     */   
/*     */   protected abstract void readResolvers(NodeList paramNodeList, IDefObjectResolver paramIDefObjectResolver, IDataExchangeParams paramIDataExchangeParams);
/*     */   
/*     */   protected abstract String[] getProperties();
/*     */   
/*     */   protected abstract ChildGrammarGenerator[] getChildren();
/*     */   
/*     */   public IDefObjectResolver read(NodeList nodes, IDataExchangeParams params, String propertyName) {
/*     */     try {
/*  62 */       for (int i = 0; i < nodes.getLength(); i++) {
/*     */         
/*  64 */         Node item = nodes.item(i);
/*  65 */         if (item instanceof Element) {
/*     */           
/*  67 */           Element node = (Element)item;
/*  68 */           String propName = node.getTagName();
/*     */           
/*  70 */           if (StringUtil.equals(propName, propertyName)) {
/*     */             
/*  72 */             nodes = node.getChildNodes();
/*  73 */             IDefObjectResolver object = createInstance();
/*  74 */             if (object == null) {
/*  75 */               return null;
/*     */             }
/*  77 */             for (int j = 0; j < nodes.getLength(); j++) {
/*     */               
/*  79 */               item = nodes.item(j);
/*  80 */               if (item instanceof Element) {
/*     */                 
/*  82 */                 node = (Element)item;
/*  83 */                 propName = node.getTagName();
/*     */                 
/*  85 */                 if (StringUtil.equals(propName, getQualifiedName(getTagName(object))))
/*     */                 {
/*  87 */                   readProperties(node, object, params);
/*  88 */                   Element childNode = getChildByName(node, "PropertyResolvers");
/*  89 */                   if (childNode != null) {
/*     */                     
/*  91 */                     NodeList children = childNode.getChildNodes();
/*  92 */                     if (children != null && children.getLength() > 0)
/*     */                     {
/*  94 */                       readResolvers(children, object, params);
/*     */                     }
/*     */                   } 
/*     */                   
/*  98 */                   return object;
/*     */                 }
/*     */               
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 106 */     } catch (Exception e) {
/*     */       
/* 108 */       LbsConsole.getLogger(getClass()).error(e.getMessage(), e);
/*     */     } 
/* 110 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(ILbsDataExchangeWriter writer, IDefObjectResolver object, IDataExchangeParams params, String propertyName) {
/* 115 */     if (object == null) {
/*     */       return;
/*     */     }
/*     */     try {
/* 119 */       writeHeader(writer, object, propertyName);
/* 120 */       ListDefinition listDef = new ListDefinition();
/* 121 */       listDef = processListDefinition(listDef);
/* 122 */       if (params != null)
/* 123 */         params.setListDefinition(listDef); 
/* 124 */       writeProperties(writer, object, params);
/* 125 */       writeResolvers(writer, object, params);
/* 126 */       writeFooter(writer, object, propertyName);
/*     */     }
/* 128 */     catch (Exception e) {
/*     */       
/* 130 */       LbsConsole.getLogger(getClass()).error(e.getMessage(), e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeGrammar(int grammarType, PrintWriter writer, ArrayList writtenGenerators, String propertyName) {
/* 136 */     switch (grammarType) {
/*     */       
/*     */       case 0:
/* 139 */         writeDTDGrammar(writer, writtenGenerators, propertyName);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameSpace() {
/* 146 */     String name = getClass().getName();
/* 147 */     int idx = name.lastIndexOf('.');
/* 148 */     if (idx >= 0)
/* 149 */       name = name.substring(idx + 1); 
/* 150 */     return name.toLowerCase(Locale.UK);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeDTDGrammar(PrintWriter writer, ArrayList<String> writtenGenerators, String propertyName) {
/* 155 */     if (writtenGenerators.contains(propertyName)) {
/*     */       return;
/*     */     }
/* 158 */     writtenGenerators.add(propertyName);
/*     */     
/* 160 */     writer.println("<!ELEMENT " + propertyName + " (" + getQualifiedName(getTagName((IDefObjectResolver)null)) + ") >");
/* 161 */     writer.println("<!ATTLIST " + propertyName + " >");
/* 162 */     writer.println("");
/*     */     
/* 164 */     if (writtenGenerators.contains(getQualifiedName(getTagName((IDefObjectResolver)null)))) {
/*     */       return;
/*     */     }
/* 167 */     writtenGenerators.add(getQualifiedName(getTagName((IDefObjectResolver)null)));
/*     */     
/* 169 */     String[] props = getProperties();
/* 170 */     ChildGrammarGenerator[] children = getChildren();
/* 171 */     boolean hasChild = false;
/* 172 */     writer.print("<!ELEMENT " + getQualifiedName(getTagName((IDefObjectResolver)null)));
/* 173 */     if (props != null)
/*     */     {
/*     */       
/* 176 */       for (int i = 0; i < props.length; i++) {
/*     */         
/* 178 */         String prop = props[i];
/* 179 */         if (!hasChild) {
/* 180 */           writer.print(" (");
/*     */         } else {
/* 182 */           writer.print(",");
/* 183 */         }  writer.print(getQualifiedName(prop));
/* 184 */         hasChild = true;
/*     */       } 
/*     */     }
/* 187 */     if (children != null)
/*     */     {
/* 189 */       if (children.length > 0) {
/*     */         
/* 191 */         if (!hasChild) {
/* 192 */           writer.print(" (");
/*     */         } else {
/* 194 */           writer.print(",");
/* 195 */         }  writer.print(getQualifiedName("PropertyResolvers"));
/*     */         
/* 197 */         hasChild = true;
/*     */       } 
/*     */     }
/* 200 */     if (hasChild) {
/*     */       
/* 202 */       writer.println(") >");
/* 203 */       writer.println("<!ATTLIST " + getQualifiedName(getTagName((IDefObjectResolver)null)) + " ");
/* 204 */       writer.println("\tpackage CDATA #FIXED \"" + getClass().getPackage().getName() + "\"");
/* 205 */       if (!StringUtil.isEmpty(this.m_NameSpace))
/* 206 */         writer.println("\txmlns:" + this.m_NameSpace + " CDATA #FIXED \"" + getClass().getName() + "\""); 
/* 207 */       writer.println(">");
/* 208 */       writer.println("");
/* 209 */       if (props != null)
/*     */       {
/*     */         
/* 212 */         for (int i = 0; i < props.length; i++) {
/*     */           
/* 214 */           String prop = props[i];
/* 215 */           writer.println("<!ELEMENT " + getQualifiedName(prop) + " (#PCDATA) >");
/* 216 */           writer.println("<!ATTLIST " + getQualifiedName(prop) + " >");
/* 217 */           writer.println("");
/*     */         } 
/*     */       }
/* 220 */       if (children != null)
/*     */       {
/* 222 */         if (children.length > 0) {
/*     */           
/* 224 */           writer.print("<!ELEMENT " + getQualifiedName("PropertyResolvers") + " ");
/*     */           
/* 226 */           hasChild = false; int i;
/* 227 */           for (i = 0; i < children.length; i++) {
/*     */             
/* 229 */             ChildGrammarGenerator child = children[i];
/* 230 */             if (child != null) {
/*     */               
/* 232 */               if (!hasChild) {
/* 233 */                 writer.print(" (");
/*     */               } else {
/* 235 */                 writer.print(",");
/* 236 */               }  writer.print(getQualifiedName(child.getPropertyName()));
/* 237 */               hasChild = true;
/*     */             } 
/*     */           } 
/* 240 */           writer.print(") >");
/* 241 */           writer.println("<!ATTLIST " + getQualifiedName("PropertyResolvers") + " >");
/* 242 */           for (i = 0; i < children.length; i++)
/*     */           {
/* 244 */             ChildGrammarGenerator child = children[i];
/* 245 */             if (child != null)
/*     */             {
/* 247 */               child.getChild().writeGrammar(0, writer, writtenGenerators, 
/* 248 */                   getQualifiedName(child.getPropertyName()));
/*     */             }
/*     */           }
/*     */         
/*     */         } 
/*     */       }
/*     */     } else {
/*     */       
/* 256 */       writer.println(" EMPTY >");
/* 257 */       writer.println("<!ATTLIST " + getQualifiedName(getTagName((IDefObjectResolver)null)) + " ");
/* 258 */       writer.println("\tpackage CDATA #FIXED \"" + getClass().getPackage().getName() + "\"");
/* 259 */       if (!StringUtil.isEmpty(this.m_NameSpace))
/* 260 */         writer.println("\txmlns:" + this.m_NameSpace + " CDATA #FIXED \"" + getClass().getName() + "\""); 
/* 261 */       writer.println(">");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeFooter(ILbsDataExchangeWriter writer, IDefObjectResolver object, String propertyName) throws IOException {
/* 268 */     writer.writeObjectFooter(endTag(getTagName(object)));
/* 269 */     writer.writePropertyResolverEndTag(propertyName);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void writeHeader(ILbsDataExchangeWriter writer, IDefObjectResolver object, String propertyName) throws IOException {
/* 274 */     writer.writePropertyResolverStartTag(propertyName);
/* 275 */     writer.writeObjectHeader(startTag(getTagName(object)), "", "", "", object.getClass().getPackage().getName(), this.m_NameSpace, 
/* 276 */         object.getClass().getName(), false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 284 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 289 */     if (obj instanceof ResolverXMLSerializer)
/*     */     {
/* 291 */       return (obj.getClass() == getClass() && StringUtil.equals(this.m_NameSpace, ((ResolverXMLSerializer)obj).m_NameSpace));
/*     */     }
/* 293 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getTagName(IDefObjectResolver object) {
/* 298 */     Class<?> clazz = null;
/* 299 */     if (object == null) {
/* 300 */       clazz = getClass();
/*     */     } else {
/* 302 */       clazz = object.getClass();
/* 303 */     }  String name = clazz.getName();
/* 304 */     int idx = name.lastIndexOf('.');
/* 305 */     if (idx >= 0)
/* 306 */       name = name.substring(idx + 1); 
/* 307 */     return name;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\platform\client\ResolverXMLSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */