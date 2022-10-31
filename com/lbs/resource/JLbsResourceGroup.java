/*    */ package com.lbs.resource;
/*    */ 
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
/*    */ public class JLbsResourceGroup
/*    */ {
/*    */   private String m_Name;
/*    */   private ArrayList m_ResourceList;
/*    */   
/*    */   public JLbsResourceGroup(String name) {
/* 22 */     setName(name);
/* 23 */     this.m_ResourceList = new ArrayList();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 31 */     return this.m_Name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 39 */     this.m_Name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addResourceNumber(int resourceNumber) {
/* 44 */     if (!this.m_ResourceList.contains(Integer.valueOf(resourceNumber))) {
/* 45 */       this.m_ResourceList.add(Integer.valueOf(resourceNumber));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList getResourceList() {
/* 53 */     return this.m_ResourceList;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\resource\JLbsResourceGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */