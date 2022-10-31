/*     */ package com.lbs.contract;
/*     */ 
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
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
/*     */ public class ContractImplementation
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String m_Category;
/*     */   private Identifier m_Identifier;
/*  27 */   private Hashtable<String, String> m_Properties = new Hashtable<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public ContractImplementation() {}
/*     */ 
/*     */   
/*     */   public ContractImplementation(ContractImplementation clone) {
/*  35 */     this.m_Category = clone.m_Category;
/*  36 */     this.m_Identifier = clone.m_Identifier;
/*  37 */     this.m_Properties = clone.m_Properties;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillAllIncludes(ArrayList<ContractImplInclude> all) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() throws ContractException {}
/*     */ 
/*     */   
/*     */   public String getCategory() {
/*  50 */     return this.m_Category;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCategory(String category) {
/*  55 */     this.m_Category = category;
/*     */   }
/*     */ 
/*     */   
/*     */   public Identifier getIdentifier() {
/*  60 */     return this.m_Identifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdentifier(Identifier id) {
/*  65 */     this.m_Identifier = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public Hashtable<String, String> getProperties() {
/*  70 */     return this.m_Properties;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProperties(Hashtable<String, String> properties) {
/*  75 */     this.m_Properties = properties;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContractImplementation processForTemplates() throws ContractException {
/*  80 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void processVariableSubstitutions(Hashtable<String, String> varSubs) {
/*  85 */     Enumeration<String> keys = varSubs.keys();
/*  86 */     while (keys.hasMoreElements()) {
/*     */       
/*  88 */       String key = keys.nextElement();
/*  89 */       String value = varSubs.get(key);
/*  90 */       this.m_Identifier = new Identifier(this.m_Identifier.getGuid(), processStringForTemplateVars(this.m_Identifier.getId(), key, value));
/*  91 */       this.m_Properties = processPropertiesForTemplateVars(this.m_Properties, key, value);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Hashtable<String, String> processPropertiesForTemplateVars(Hashtable<String, String> props, String varName, String varValue) {
/*  98 */     Hashtable<String, String> result = new Hashtable<>();
/*  99 */     Enumeration<String> keys = props.keys();
/* 100 */     while (keys.hasMoreElements()) {
/*     */       
/* 102 */       String key = keys.nextElement();
/* 103 */       String value = props.get(key);
/* 104 */       result.put(processStringForTemplateVars(key, varName, varValue), processStringForTemplateVars(value, varName, varValue));
/*     */     } 
/* 106 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String processStringForTemplateVars(String s, String varName, String varValue) {
/* 111 */     if (StringUtil.isEmpty(s)) {
/* 112 */       return s;
/*     */     }
/* 114 */     return StringUtil.replaceAll(s, "${" + varName + "}", varValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 121 */     ContractImplementation clone = (ContractImplementation)super.clone();
/* 122 */     clone.m_Properties = (Hashtable<String, String>)this.m_Properties.clone();
/* 123 */     return clone;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 129 */     StringBuilder sb = new StringBuilder();
/* 130 */     sb.append(getClass().getName());
/* 131 */     sb.append(" : " + this.m_Category + ", " + this.m_Identifier);
/* 132 */     sb.append(" (" + this.m_Properties + ")");
/* 133 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public void printHtml(PrintWriter writer) {
/* 138 */     writer.println(this.m_Category);
/* 139 */     writer.print("<table border=\"1\">");
/* 140 */     writer.print("<tr>");
/* 141 */     writer.print("<th colspan=\"2\" align=\"left\">");
/* 142 */     writer.print("Properties");
/* 143 */     writer.print("</th>");
/* 144 */     writer.print("</tr>");
/* 145 */     Enumeration<String> keys = this.m_Properties.keys();
/* 146 */     while (keys.hasMoreElements()) {
/*     */       
/* 148 */       String key = keys.nextElement();
/* 149 */       writer.print("<tr>");
/* 150 */       writer.print("<td>");
/* 151 */       writer.print(key);
/* 152 */       writer.print("</td>");
/* 153 */       writer.print("<td>");
/* 154 */       writer.print(this.m_Properties.get(key));
/* 155 */       writer.print("</td>");
/* 156 */       writer.print("</tr>");
/*     */     } 
/* 158 */     writer.print("</table>");
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractImplementation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */