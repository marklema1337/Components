/*    */ package com.lbs.mail;
/*    */ 
/*    */ import com.lbs.data.IParameterMandatoryListener;
/*    */ import com.lbs.data.Identifier;
/*    */ import com.lbs.data.Parameter;
/*    */ import com.lbs.interfaces.IParameter;
/*    */ import com.lbs.interfaces.ParameterException;
/*    */ import com.lbs.localization.ILocalizationServices;
/*    */ import com.lbs.util.JLbsListedHashtable;
/*    */ import com.lbs.util.XMLDescribeBuffer;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Hashtable;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class JLbsMailInputObjects
/*    */   implements IParameter
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 20 */   private Hashtable<String, ArrayList<Object>> m_AliasMap = new Hashtable<>();
/* 21 */   private transient Object m_TaskOperationInstance = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setTaskOperationInstance(Object taskOperationInstance) {
/* 29 */     this.m_TaskOperationInstance = taskOperationInstance;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getTaskOperationInstance() {
/* 34 */     return this.m_TaskOperationInstance;
/*    */   }
/*    */ 
/*    */   
/*    */   public void put(String alias, Object value) {
/* 39 */     if (value instanceof List) {
/*    */ 
/*    */       
/* 42 */       List<Object> list = (List<Object>)value;
/* 43 */       JLbsListedHashtable.putAll(this.m_AliasMap, alias, list);
/*    */     } else {
/*    */       
/* 46 */       JLbsListedHashtable.put(this.m_AliasMap, alias, value);
/*    */     } 
/*    */   }
/*    */   
/*    */   public ArrayList<Object> getValues(String alias) {
/* 51 */     return this.m_AliasMap.get(alias);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void describeXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Identifier getParameterIdentifier() {
/* 78 */     return Parameter.getParameterIdentifier(getClass());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<String> getEmptyPropertyNames(IParameterMandatoryListener mandatoryListener) {
/* 85 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mail\JLbsMailInputObjects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */