/*     */ package com.lbs.contract;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.localization.JLbsResourceId;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.EnumSet;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class ContractSchema
/*     */   implements Serializable
/*     */ {
/*     */   private static transient boolean ms_NoInitialize = false;
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String m_Module;
/*     */   private Identifier m_Identifier;
/*     */   private String m_Name;
/*     */   
/*     */   public ContractSchema() {
/*  39 */     this.m_Abstract = false;
/*  40 */     this.m_NonInteractive = null;
/*     */ 
/*     */     
/*  43 */     this.m_HasRevision = null;
/*  44 */     this.m_RevisionWithApproval = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  49 */     this.m_Inputs = new ArrayList<>();
/*  50 */     this.m_Outputs = new ArrayList<>();
/*  51 */     this.parameterClasses = new HashMap<>();
/*     */     
/*  53 */     this.m_MandatoryMaps = new Hashtable<>();
/*     */     
/*  55 */     this.m_Inited = false;
/*     */   }
/*     */   private String m_Description; private ContractType m_Type; private boolean m_Abstract; private Boolean m_NonInteractive;
/*     */   public void initialize() throws ContractException {
/*  59 */     if (this.m_Inited)
/*     */       return; 
/*  61 */     this.m_Inited = true;
/*  62 */     if (this.m_BaseContractIdentifier != null) {
/*     */       
/*  64 */       ContractSchema baseSchema = ContractSchemaRegistry.getContract(this.m_BaseContractIdentifier);
/*  65 */       if (baseSchema == null)
/*  66 */         throw new ContractException("Cannot find base contract with id '" + this.m_BaseContractIdentifier + "' in contract '" + 
/*  67 */             this.m_Identifier + "'!"); 
/*  68 */       baseSchema.initialize();
/*  69 */       if (this.m_Type == null) {
/*  70 */         this.m_Type = baseSchema.getType();
/*  71 */       } else if (this.m_Type != baseSchema.getType()) {
/*  72 */         throw new ContractException("Cannot extend from base contract '" + this.m_BaseContractIdentifier + "' in contract '" + 
/*  73 */             this.m_Identifier + "', contract types are different!");
/*  74 */       }  if (this.m_NonInteractive == null) {
/*  75 */         this.m_NonInteractive = Boolean.valueOf(baseSchema.isNonInteractive());
/*     */       }
/*  77 */       if (this.m_HasRevision == null) {
/*  78 */         this.m_HasRevision = Boolean.valueOf(baseSchema.hasRevision());
/*     */       }
/*  80 */       if (this.m_RevisionWithApproval == null) {
/*  81 */         this.m_RevisionWithApproval = Boolean.valueOf(baseSchema.isRevisionWithApproval());
/*     */       }
/*  83 */       List<ContractSchemaInOut> baseIns = baseSchema.getInputs();
/*  84 */       int idx = 0;
/*  85 */       for (ContractSchemaInOut baseIn : baseIns) {
/*     */         
/*  87 */         ContractSchemaInOut input = getInputByAlias(baseIn.getAlias());
/*  88 */         if (input != null) {
/*  89 */           checkParameterInheritance(input, baseIn, "input"); continue;
/*     */         } 
/*  91 */         this.m_Inputs.add(idx++, baseIn);
/*     */       } 
/*  93 */       List<ContractSchemaInOut> baseOuts = baseSchema.getOutputs();
/*  94 */       idx = 0;
/*  95 */       for (ContractSchemaInOut baseOut : baseOuts) {
/*     */         
/*  97 */         ContractSchemaInOut output = getOutputByAlias(baseOut.getAlias());
/*  98 */         if (output != null) {
/*  99 */           checkParameterInheritance(output, baseOut, "output"); continue;
/*     */         } 
/* 101 */         this.m_Outputs.add(idx++, baseOut);
/*     */       } 
/*     */     } 
/* 104 */     for (ContractSchemaInOut in : this.m_Inputs)
/*     */     {
/* 106 */       in.initialize(this.m_Identifier);
/*     */     }
/*     */     
/* 109 */     for (ContractSchemaInOut out : this.m_Outputs)
/*     */     {
/* 111 */       out.initialize(this.m_Identifier);
/*     */     }
/*     */     
/* 114 */     if (ms_NoInitialize) {
/*     */       return;
/*     */     }
/* 117 */     if (!this.m_Abstract) {
/*     */       
/* 119 */       boolean hasCommonOutput = false;
/* 120 */       for (ContractSchemaInOut out : this.m_Outputs) {
/*     */ 
/*     */         
/*     */         try {
/* 124 */           Class<?> c = Class.forName(out.getParameterClassName());
/* 125 */           Class<?> resultClass = Class.forName("com.lbs.par.gen.common.ContractResult");
/* 126 */           if (resultClass.isAssignableFrom(c)) {
/*     */             
/* 128 */             hasCommonOutput = true;
/*     */             
/*     */             break;
/*     */           } 
/* 132 */         } catch (ClassNotFoundException e) {
/*     */           
/* 134 */           throw new ContractException(e);
/*     */         } 
/*     */       } 
/* 137 */       if (!hasCommonOutput) {
/*     */         
/* 139 */         ContractSchemaInOut commonOut = new ContractSchemaInOut(new Identifier("ContractResult"), "AutoAddedResult", false);
/* 140 */         commonOut.initialize(this.m_Identifier);
/* 141 */         this.m_Outputs.add(commonOut);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private Identifier m_BaseContractIdentifier; private Boolean m_HasRevision; private Boolean m_RevisionWithApproval; private JLbsResourceId m_NameResource;
/*     */   private JLbsResourceId m_DescriptionResource;
/*     */   
/*     */   private void checkParameterInheritance(ContractSchemaInOut inOut, ContractSchemaInOut baseInOut, String inOutType) throws ContractException {
/* 149 */     if (ms_NoInitialize)
/*     */       return; 
/* 151 */     inOut.initialize(this.m_Identifier);
/* 152 */     baseInOut.initialize(this.m_Identifier);
/* 153 */     String parameterClassName = inOut.getParameterClassName();
/* 154 */     String baseParameterClassName = baseInOut.getParameterClassName();
/*     */     
/*     */     try {
/* 157 */       Class<?> parameterClass = getParameterClass(parameterClassName);
/* 158 */       Class<?> baseParameterClass = getParameterClass(baseParameterClassName);
/* 159 */       if (!baseParameterClass.isAssignableFrom(parameterClass))
/*     */       {
/* 161 */         throw new ContractException(String.valueOf(inOutType) + " with alias '" + inOut.getAlias() + 
/* 162 */             "' is not compatible with base parameter's " + inOutType + " in contract '" + this.m_Identifier + 
/* 163 */             "'! Only parameters that extend the base " + inOutType + " can be defined using the same alias!");
/*     */       }
/*     */     }
/* 166 */     catch (Exception e) {
/*     */       
/* 168 */       if (JLbsConstants.SKIP_CACHE) {
/* 169 */         LbsConsole.getLogger("ContractSchema").error(e);
/*     */       } else {
/* 171 */         throw new ContractException(e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private List<ContractSchemaInOut> m_Inputs; private List<ContractSchemaInOut> m_Outputs; private Map<String, Class<?>> parameterClasses; private Hashtable<String, MandatoryMap> m_MandatoryMaps; private boolean m_Inited;
/*     */   public Class<?> getParameterClass(String parameterClassName) throws Exception {
/* 177 */     Class<?> parameterClass = this.parameterClasses.get(parameterClassName);
/* 178 */     if (parameterClass == null) {
/*     */       
/* 180 */       parameterClass = Class.forName(parameterClassName);
/* 181 */       this.parameterClasses.put(parameterClassName, parameterClass);
/*     */     } 
/* 183 */     return parameterClass;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModule(String module) {
/* 188 */     this.m_Module = module;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getModule() {
/* 193 */     return this.m_Module;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsResourceId getNameResource() {
/* 198 */     return this.m_NameResource;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNameResource(JLbsResourceId nameResource) {
/* 203 */     this.m_NameResource = nameResource;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsResourceId getDescriptionResource() {
/* 208 */     return this.m_DescriptionResource;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescriptionResource(JLbsResourceId descriptionResource) {
/* 213 */     this.m_DescriptionResource = descriptionResource;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<ContractSchemaInOut> getInputs() {
/* 218 */     return this.m_Inputs;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInputs(List<ContractSchemaInOut> inputs) {
/* 223 */     this.m_Inputs = inputs;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<ContractSchemaInOut> getOutputs() {
/* 228 */     return this.m_Outputs;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOutputs(List<ContractSchemaInOut> outputs) {
/* 233 */     this.m_Outputs = outputs;
/*     */   }
/*     */ 
/*     */   
/*     */   public Identifier getIdentifier() {
/* 238 */     return this.m_Identifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdentifier(Identifier id) {
/* 243 */     this.m_Identifier = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getId() {
/* 248 */     return this.m_Identifier.getId();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGuid() {
/* 253 */     return this.m_Identifier.getGuid();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 258 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 263 */     this.m_Name = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 268 */     return this.m_Description;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/* 273 */     this.m_Description = description;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContractType getType() {
/* 278 */     return this.m_Type;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(ContractType type) {
/* 283 */     this.m_Type = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAbstract() {
/* 288 */     return this.m_Abstract;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAbstract(boolean abstract1) {
/* 293 */     this.m_Abstract = abstract1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNonInteractive() {
/* 298 */     if (this.m_NonInteractive != null)
/* 299 */       return this.m_NonInteractive.booleanValue(); 
/* 300 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNonInteractive(boolean nonInteractive) {
/* 305 */     this.m_NonInteractive = Boolean.valueOf(nonInteractive);
/*     */   }
/*     */ 
/*     */   
/*     */   public Identifier getBaseContractIdentifier() {
/* 310 */     return this.m_BaseContractIdentifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaseContractIdentifier(Identifier baseContractIdentifier) {
/* 315 */     this.m_BaseContractIdentifier = baseContractIdentifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContractSchemaInOut getInputByAlias(String alias) {
/* 320 */     return getInOutByAlias(alias, this.m_Inputs);
/*     */   }
/*     */ 
/*     */   
/*     */   public ContractSchemaInOut getOutputByAlias(String alias) {
/* 325 */     return getInOutByAlias(alias, this.m_Outputs);
/*     */   }
/*     */ 
/*     */   
/*     */   private ContractSchemaInOut getInOutByAlias(String alias, List<ContractSchemaInOut> vector) {
/* 330 */     for (ContractSchemaInOut inOut : vector) {
/*     */       
/* 332 */       if (StringUtil.equals(alias, inOut.getAlias()))
/* 333 */         return inOut; 
/*     */     } 
/* 335 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMandatoryMap(String inputAlias, MandatoryMap map) {
/* 340 */     this.m_MandatoryMaps.put(inputAlias, map);
/*     */   }
/*     */ 
/*     */   
/*     */   public Hashtable<String, MandatoryMap> getMandatoryMaps() {
/* 345 */     return this.m_MandatoryMaps;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasInputWithAlias(String alias) {
/* 350 */     return hasInOutWithAlias(alias, this.m_Inputs);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasOutputWithAlias(String alias) {
/* 355 */     return hasInOutWithAlias(alias, this.m_Outputs);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hasInOutWithAlias(String alias, List<ContractSchemaInOut> vector) {
/* 360 */     return (getInOutByAlias(alias, vector) != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 366 */     if (obj instanceof ContractSchema)
/*     */     {
/* 368 */       return this.m_Identifier.equals(((ContractSchema)obj).getIdentifier());
/*     */     }
/* 370 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 376 */     return this.m_Identifier.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public void printHtml(PrintWriter writer) {
/* 381 */     writer.println("Contract with id <b>" + this.m_Identifier + "</b> and type <b>" + this.m_Type.getTypeName() + "</b>");
/* 382 */     writer.print("Inputs : ");
/* 383 */     writer.print("<ul>");
/* 384 */     for (ContractSchemaInOut in : this.m_Inputs)
/*     */     {
/* 386 */       in.printHtml(writer);
/*     */     }
/* 388 */     writer.print("</ul>");
/* 389 */     writer.print("Outputs : ");
/* 390 */     writer.print("<ul>");
/* 391 */     for (ContractSchemaInOut out : this.m_Outputs)
/*     */     {
/* 393 */       out.printHtml(writer);
/*     */     }
/* 395 */     writer.print("</ul>");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 401 */     StringBuilder sb = new StringBuilder();
/* 402 */     sb.append("ContractSchema with id " + this.m_Identifier);
/* 403 */     sb.append(" and type " + this.m_Type.getTypeName());
/* 404 */     sb.append(" Inputs (");
/* 405 */     for (ContractSchemaInOut in : this.m_Inputs)
/*     */     {
/* 407 */       sb.append(in.toString());
/*     */     }
/* 409 */     sb.append(")");
/* 410 */     sb.append(" Outputs (");
/* 411 */     for (ContractSchemaInOut out : this.m_Outputs)
/*     */     {
/* 413 */       sb.append(out.toString());
/*     */     }
/* 415 */     sb.append(")");
/* 416 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setNoInitialize(boolean noInitialize) {
/* 421 */     ms_NoInitialize = noInitialize;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasRevision() {
/* 426 */     if (this.m_HasRevision != null)
/* 427 */       return this.m_HasRevision.booleanValue(); 
/* 428 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHasRevision(boolean hasRevision) {
/* 433 */     this.m_HasRevision = Boolean.valueOf(hasRevision);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRevisionWithApproval() {
/* 438 */     if (this.m_RevisionWithApproval != null)
/* 439 */       return this.m_RevisionWithApproval.booleanValue(); 
/* 440 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRevisionWithApproval(boolean revisionWithApproval) {
/* 445 */     this.m_RevisionWithApproval = Boolean.valueOf(revisionWithApproval);
/*     */   }
/*     */   
/*     */   public enum ContractType {
/* 449 */     type_ui_browser("ui-browser"), type_ui_editor("ui-editor"), type_ui("ui"), type_report("report"), type_batch("batch");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final String m_TypeName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 463 */     private static final Map<String, ContractType> ms_LookupTable = new HashMap<>(); ContractType(String typeName) { this.m_TypeName = typeName; } public String getTypeName() {
/*     */       return this.m_TypeName;
/*     */     }
/*     */     static {
/* 467 */       for (ContractType s : EnumSet.<ContractType>allOf(ContractType.class)) {
/* 468 */         ms_LookupTable.put(s.getTypeName(), s);
/*     */       }
/*     */     }
/*     */     
/*     */     public static ContractType get(String typeName) {
/* 473 */       return ms_LookupTable.get(typeName);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractSchema.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */