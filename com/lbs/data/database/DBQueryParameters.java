/*    */ package com.lbs.data.database;
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
/*    */ public class DBQueryParameters
/*    */   extends ArrayList
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private boolean m_Dublicates = false;
/*    */   
/*    */   public DBQueryParameter item(int index) {
/* 21 */     return (DBQueryParameter)get(index);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean contains(DBQueryParameter item) {
/* 27 */     for (int i = 0; i < size(); i++) {
/*    */       
/* 29 */       DBQueryParameter param = item(i);
/*    */       
/* 31 */       if (param.getName().equals(item.getName())) {
/* 32 */         return true;
/*    */       }
/*    */     } 
/* 35 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean add(DBQueryParameter item) {
/* 40 */     if (this.m_Dublicates || !contains(item)) {
/* 41 */       return add((E)item);
/*    */     }
/* 43 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void append(DBQueryParameters params) {
/* 49 */     for (int i = 0; i < params.size(); i++) {
/*    */       
/* 51 */       DBQueryParameter param = params.item(i);
/* 52 */       add(new DBQueryParameter(param));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getParamNames() {
/* 58 */     String[] paramNames = new String[size()];
/*    */     
/* 60 */     for (int i = 0; i < size(); i++) {
/* 61 */       paramNames[i] = item(i).getName();
/*    */     }
/* 63 */     return paramNames;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int find(String name) {
/* 69 */     for (int i = 0; i < size(); i++) {
/*    */       
/* 71 */       DBQueryParameter param = item(i);
/*    */       
/* 73 */       if (param.getName().equals(name))
/* 74 */         return i; 
/*    */     } 
/* 76 */     return -1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isDublicates() {
/* 85 */     return this.m_Dublicates;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDublicates(boolean dublicates) {
/* 94 */     this.m_Dublicates = dublicates;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\DBQueryParameters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */