/*    */ package com.lbs.data.database;
/*    */ 
/*    */ import com.lbs.util.StringUtil;
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
/*    */ public class NamedCollection
/*    */   extends ArrayList
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public int find(String name) {
/* 23 */     for (int i = 0; i < size(); i++) {
/*    */       
/* 25 */       Object item = get(i);
/* 26 */       if (item instanceof INamedEntity && 
/* 27 */         StringUtil.equals(((INamedEntity)item).getName(), name))
/* 28 */         return i; 
/*    */     } 
/* 30 */     return -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getByName(String name) {
/* 35 */     int idx = find(name);
/* 36 */     if (idx >= 0)
/* 37 */       return get(idx); 
/* 38 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean exists(String name) {
/* 43 */     return (find(name) >= 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\NamedCollection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */