/*    */ package com.lbs.contract.execution;
/*    */ 
/*    */ import com.lbs.contract.ContractException;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PropertyMapping
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_PropertyName;
/*    */   private boolean m_AlwaysMap = false;
/* 24 */   private ArrayList<PropertyMapper> m_Mappers = new ArrayList<>();
/* 25 */   private ArrayList<EmptyExecutor> m_EmptyExecutors = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public String getPropertyName() {
/* 29 */     return this.m_PropertyName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPropertyName(String propertyName) {
/* 34 */     this.m_PropertyName = propertyName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAlwaysMap(boolean alwaysMap) {
/* 39 */     this.m_AlwaysMap = alwaysMap;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAlwaysMap() {
/* 44 */     return this.m_AlwaysMap;
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<PropertyMapper> getMappers() {
/* 49 */     return this.m_Mappers;
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<EmptyExecutor> getEmptyExecutors() {
/* 54 */     return this.m_EmptyExecutors;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addPropertyMapper(PropertyMapper mapper) {
/* 59 */     this.m_Mappers.add(mapper);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addEmptyExecutor(EmptyExecutor executor) {
/* 64 */     this.m_EmptyExecutors.add(executor);
/*    */   }
/*    */ 
/*    */   
/*    */   public void initialize() throws ContractException {
/* 69 */     for (PropertyMapper mapper : this.m_Mappers)
/*    */     {
/* 71 */       mapper.initialize();
/*    */     }
/* 73 */     for (EmptyExecutor executor : this.m_EmptyExecutors)
/*    */     {
/* 75 */       executor.initialize();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\execution\PropertyMapping.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */