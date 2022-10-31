/*     */ package com.lbs.contract;
/*     */ 
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
/*     */ public class ContractImplGroup
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String m_Name;
/*  22 */   private ArrayList<ContractImplGroup> m_Groups = new ArrayList<>();
/*  23 */   private ArrayList<ContractImplementation> m_Implementations = new ArrayList<>();
/*  24 */   private ArrayList<ContractImplInclude> m_Includes = new ArrayList<>();
/*  25 */   private Hashtable<String, String> m_Properties = new Hashtable<>();
/*     */ 
/*     */   
/*     */   public void initialize() throws ContractException {
/*  29 */     ContractImplGroup group = processForTemplates();
/*  30 */     group.registerImplementations();
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerImplementations() {
/*  35 */     for (ContractImplGroup group : this.m_Groups) {
/*     */       
/*  37 */       putProperties(group.getProperties());
/*  38 */       group.registerImplementations();
/*     */     } 
/*  40 */     for (ContractImplementation implementation : this.m_Implementations) {
/*     */       
/*  42 */       putProperties(implementation.getProperties());
/*  43 */       ContractSchemaRegistry.registerContractImplementation(implementation);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void putProperties(Hashtable<String, String> target) {
/*  50 */     Enumeration<String> keys = this.m_Properties.keys();
/*  51 */     while (keys.hasMoreElements()) {
/*     */       
/*  53 */       String key = keys.nextElement();
/*  54 */       if (!target.containsKey(key)) {
/*  55 */         target.put(key, this.m_Properties.get(key));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ContractImplGroup processForTemplates() throws ContractException {
/*     */     try {
/*  63 */       ContractImplGroup clone = (ContractImplGroup)clone();
/*  64 */       for (ContractImplInclude include : this.m_Includes) {
/*     */         
/*  66 */         ContractImplTemplate template = ContractImplTemplate.findAndProcessForTemplate(include, 
/*  67 */             "contract implementation group '" + this.m_Name + "'");
/*  68 */         clone.m_Groups.addAll(template.getGroups());
/*  69 */         clone.m_Implementations.addAll(template.getImplementations());
/*  70 */         Hashtable<String, String> templateProps = template.getProperties();
/*  71 */         Enumeration<String> keys = templateProps.keys();
/*  72 */         while (keys.hasMoreElements()) {
/*     */           
/*  74 */           String key = keys.nextElement();
/*  75 */           String value = templateProps.get(key);
/*  76 */           if (!clone.m_Properties.containsKey(key))
/*  77 */             clone.m_Properties.put(key, value); 
/*     */         } 
/*     */       }  int i;
/*  80 */       for (i = 0; i < clone.m_Groups.size(); i++) {
/*     */         
/*  82 */         ContractImplGroup group = clone.m_Groups.get(i);
/*  83 */         clone.m_Groups.set(i, group.processForTemplates());
/*     */       } 
/*  85 */       for (i = 0; i < clone.m_Implementations.size(); i++) {
/*     */         
/*  87 */         ContractImplementation implementation = clone.m_Implementations.get(i);
/*  88 */         clone.m_Implementations.set(i, implementation.processForTemplates());
/*     */       } 
/*  90 */       return clone;
/*     */     }
/*  92 */     catch (CloneNotSupportedException e) {
/*     */       
/*  94 */       throw new ContractException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 100 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 105 */     this.m_Name = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<ContractImplGroup> getGroups() {
/* 110 */     return this.m_Groups;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGroups(ArrayList<ContractImplGroup> groups) {
/* 115 */     this.m_Groups = groups;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<ContractImplementation> getImplementations() {
/* 120 */     return this.m_Implementations;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImplementations(ArrayList<ContractImplementation> implementations) {
/* 125 */     this.m_Implementations = implementations;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<ContractImplInclude> getIncludes() {
/* 130 */     return this.m_Includes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void fillAllIncludes(ArrayList<ContractImplInclude> all) {
/* 135 */     if (this.m_Includes != null)
/* 136 */       all.addAll(this.m_Includes); 
/* 137 */     for (ContractImplGroup group : this.m_Groups)
/*     */     {
/* 139 */       group.fillAllIncludes(all);
/*     */     }
/* 141 */     for (ContractImplementation impl : this.m_Implementations)
/*     */     {
/* 143 */       impl.fillAllIncludes(all);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIncludes(ArrayList<ContractImplInclude> includes) {
/* 149 */     this.m_Includes = includes;
/*     */   }
/*     */ 
/*     */   
/*     */   public Hashtable<String, String> getProperties() {
/* 154 */     return this.m_Properties;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProperties(Hashtable<String, String> properties) {
/* 159 */     this.m_Properties = properties;
/*     */   }
/*     */ 
/*     */   
/*     */   public void processVariableSubstitutions(Hashtable<String, String> varSubs) {
/* 164 */     Enumeration<String> keys = varSubs.keys();
/* 165 */     while (keys.hasMoreElements()) {
/*     */       
/* 167 */       String key = keys.nextElement();
/* 168 */       String value = varSubs.get(key);
/* 169 */       this.m_Name = ContractImplementation.processStringForTemplateVars(this.m_Name, key, value);
/* 170 */       this.m_Properties = ContractImplementation.processPropertiesForTemplateVars(this.m_Properties, key, value);
/*     */     } 
/* 172 */     for (ContractImplGroup group : this.m_Groups)
/*     */     {
/* 174 */       group.processVariableSubstitutions(varSubs);
/*     */     }
/* 176 */     for (ContractImplementation implementation : this.m_Implementations)
/*     */     {
/* 178 */       implementation.processVariableSubstitutions(varSubs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 186 */     ContractImplGroup clone = (ContractImplGroup)super.clone();
/*     */     
/* 188 */     clone.m_Groups = new ArrayList<>();
/* 189 */     for (ContractImplGroup group : this.m_Groups)
/*     */     {
/* 191 */       clone.m_Groups.add((ContractImplGroup)group.clone());
/*     */     }
/* 193 */     clone.m_Implementations = new ArrayList<>();
/* 194 */     for (ContractImplementation implementation : this.m_Implementations)
/*     */     {
/* 196 */       clone.m_Implementations.add((ContractImplementation)implementation.clone());
/*     */     }
/* 198 */     clone.m_Includes = new ArrayList<>();
/* 199 */     for (ContractImplInclude include : this.m_Includes)
/*     */     {
/* 201 */       clone.m_Includes.add((ContractImplInclude)include.clone());
/*     */     }
/* 203 */     clone.m_Properties = (Hashtable<String, String>)this.m_Properties.clone();
/* 204 */     return clone;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractImplGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */