/*     */ package com.lbs.contract;
/*     */ 
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.parameter.ParameterSchema;
/*     */ import com.lbs.parameter.ParameterSchemaRegistry;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Serializable;
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
/*     */ public class ContractSchemaInOut
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Identifier m_Id;
/*     */   private String m_ParameterClassName;
/*     */   private String m_Alias;
/*     */   private boolean m_Optional;
/*     */   
/*     */   public ContractSchemaInOut() {}
/*     */   
/*     */   public ContractSchemaInOut(Identifier id, String alias, boolean optional) {
/*  37 */     this.m_Id = id;
/*  38 */     this.m_Alias = alias;
/*  39 */     this.m_Optional = optional;
/*     */   }
/*     */ 
/*     */   
/*     */   public Identifier getId() {
/*  44 */     return this.m_Id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(Identifier id) {
/*  49 */     this.m_Id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAlias() {
/*  54 */     return this.m_Alias;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlias(String alias) {
/*  59 */     this.m_Alias = alias;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getParameterClassName() {
/*  64 */     return this.m_ParameterClassName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOptional(boolean optional) {
/*  69 */     this.m_Optional = optional;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOptional() {
/*  74 */     return this.m_Optional;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialize(Identifier contractId) throws ContractException {
/*  79 */     ParameterSchema param = ParameterSchemaRegistry.findParameter(this.m_Id);
/*  80 */     if (param == null)
/*  81 */       throw new ContractException("Cannot find parameter named '" + this.m_Id + "' in contract '" + contractId + "'!"); 
/*  82 */     if (JLbsStringUtil.isEmpty(contractId.getGuid())) {
/*  83 */       this.m_ParameterClassName = param.getQualifiedName();
/*     */     } else {
/*  85 */       this.m_ParameterClassName = param.getQualifiedId();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  91 */     if (obj instanceof ContractSchemaInOut)
/*     */     {
/*  93 */       return StringUtil.equals(this.m_Alias, ((ContractSchemaInOut)obj).getAlias());
/*     */     }
/*  95 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 101 */     return this.m_Alias.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 106 */     return "Contract InOut with parameter and alias : " + this.m_Id + " - " + this.m_Alias;
/*     */   }
/*     */ 
/*     */   
/*     */   public void printHtml(PrintWriter writer) {
/* 111 */     writer.print("<li>" + this.m_Alias + " - " + this.m_Id + "</li>");
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractSchemaInOut.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */