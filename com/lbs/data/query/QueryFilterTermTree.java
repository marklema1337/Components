/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import java.io.Externalizable;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInput;
/*    */ import java.io.ObjectOutput;
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
/*    */ public class QueryFilterTermTree
/*    */   extends QueryFilterTerm
/*    */   implements Externalizable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private ArrayList<QueryFilterTermTree> m_SubItems;
/*    */   
/*    */   public QueryFilterTermTree() {}
/*    */   
/*    */   public QueryFilterTermTree(int operation, int whereConnector, int filterType) {
/* 30 */     this((String)null, (Object)null, operation, whereConnector, filterType);
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryFilterTermTree(String columnName, Object value, int op, int whereConnector, int filterType) {
/* 35 */     super(columnName, value, op, whereConnector, filterType);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addSubTree(QueryFilterTermTree subTree) {
/* 40 */     if (this.m_SubItems == null)
/* 41 */       this.m_SubItems = new ArrayList<>(); 
/* 42 */     this.m_SubItems.add(subTree);
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<QueryFilterTermTree> getSubItems() {
/* 47 */     return this.m_SubItems;
/*    */   }
/*    */   
/*    */   public boolean isValidExpression() {
/*    */     int i;
/* 52 */     switch (this.m_Operation) {
/*    */       
/*    */       case 101:
/*    */       case 102:
/* 56 */         if (this.m_SubItems == null)
/* 57 */           return false; 
/* 58 */         if (this.m_SubItems.size() < 2) {
/* 59 */           return false;
/*    */         }
/* 61 */         for (i = 0; i < this.m_SubItems.size(); i++) {
/*    */           
/* 63 */           QueryFilterTermTree subItem = this.m_SubItems.get(i);
/* 64 */           if (subItem == null)
/* 65 */             return false; 
/* 66 */           if (!subItem.isValidExpression())
/* 67 */             return false; 
/*    */         } 
/* 69 */         return true;
/*    */       case 1:
/*    */       case 2:
/*    */       case 3:
/*    */       case 4:
/*    */       case 5:
/*    */       case 6:
/*    */       case 7:
/*    */       case 9:
/* 78 */         return (this.m_SearchValue != null);
/*    */     } 
/* 80 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 87 */     super.readExternal(in);
/* 88 */     this.m_SubItems = (ArrayList<QueryFilterTermTree>)in.readObject();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeExternal(ObjectOutput out) throws IOException {
/* 93 */     super.writeExternal(out);
/* 94 */     out.writeObject(this.m_SubItems);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryFilterTermTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */