/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import com.lbs.util.ExternalizationUtil;
/*    */ import java.io.Externalizable;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInput;
/*    */ import java.io.ObjectOutput;
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
/*    */ 
/*    */ public class QueryGridGlobalOrder
/*    */   implements Serializable, Externalizable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected String m_Name;
/*    */   protected String[] m_TableAliases;
/*    */   
/*    */   public QueryGridGlobalOrder() {}
/*    */   
/*    */   public QueryGridGlobalOrder(QueryGlobalOrder order) {
/* 34 */     this.m_Name = order.getName();
/*    */     
/* 36 */     ArrayList<String> aliases = order.getTableAliases();
/* 37 */     int size = aliases.size();
/* 38 */     this.m_TableAliases = new String[size];
/*    */     
/* 40 */     for (int i = 0; i < size; i++)
/*    */     {
/* 42 */       this.m_TableAliases[i] = aliases.get(i);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 48 */     return this.m_Name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getTableAliases() {
/* 53 */     return this.m_TableAliases;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 58 */     this.m_Name = (String)in.readObject();
/* 59 */     this.m_TableAliases = ExternalizationUtil.readStringArray(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeExternal(ObjectOutput out) throws IOException {
/* 64 */     out.writeObject(this.m_Name);
/* 65 */     ExternalizationUtil.writeStringArray(this.m_TableAliases, out);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryGridGlobalOrder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */