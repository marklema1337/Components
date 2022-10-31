/*    */ package com.lbs.data.application;
/*    */ 
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
/*    */ 
/*    */ public class ApplicationVariables
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 22 */   protected ArrayList m_List = new ArrayList();
/*    */ 
/*    */ 
/*    */   
/*    */   public void add(ApplicationVariable var) {
/* 27 */     this.m_List.add(var);
/*    */   }
/*    */ 
/*    */   
/*    */   public ApplicationVariable get(int index) {
/* 32 */     return this.m_List.get(index);
/*    */   }
/*    */ 
/*    */   
/*    */   public int size() {
/* 37 */     return this.m_List.size();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int find(String name) {
/* 43 */     for (int i = 0; i < this.m_List.size(); i++) {
/*    */       
/* 45 */       ApplicationVariable var = this.m_List.get(i);
/*    */       
/* 47 */       if (var.getName().equals(name)) {
/* 48 */         return i;
/*    */       }
/*    */     } 
/* 51 */     return -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public ApplicationVariable get(String name) {
/* 56 */     int idx = find(name);
/*    */     
/* 58 */     if (idx != -1) {
/* 59 */       return this.m_List.get(idx);
/*    */     }
/* 61 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void clear() {
/* 66 */     this.m_List.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean containsVariable(String str, String variable) {
/* 71 */     return (str.indexOf("$V(" + variable + ")") != -1);
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getReferencedVariables(String str) {
/* 76 */     ArrayList<String> list = new ArrayList();
/*    */     
/* 78 */     for (int i = 0; i < this.m_List.size(); i++) {
/*    */       
/* 80 */       ApplicationVariable appVar = this.m_List.get(i);
/*    */       
/* 82 */       if (containsVariable(str, appVar.m_Name))
/*    */       {
/* 84 */         if (!list.contains(appVar.m_Name)) {
/* 85 */           list.add(appVar.m_Name);
/*    */         }
/*    */       }
/*    */     } 
/* 89 */     return list.<String>toArray(new String[list.size()]);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\application\ApplicationVariables.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */