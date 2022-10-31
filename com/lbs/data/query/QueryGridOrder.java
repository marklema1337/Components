/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import com.lbs.util.ExternalizationUtil;
/*    */ import java.io.Externalizable;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInput;
/*    */ import java.io.ObjectOutput;
/*    */ import java.io.Serializable;
/*    */ import java.util.Arrays;
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
/*    */ public class QueryGridOrder
/*    */   implements Serializable, Externalizable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected String m_TableAlias;
/*    */   protected String m_Name;
/*    */   protected String[] m_SegmentNames;
/*    */   protected String[] m_SegmentPhysicalNames;
/*    */   
/*    */   public QueryGridOrder() {}
/*    */   
/*    */   public QueryGridOrder(String tableAlias, QueryOrder order) {
/* 36 */     this.m_TableAlias = tableAlias;
/* 37 */     this.m_Name = order.getName();
/*    */     
/* 39 */     QueryOrderColumns columns = order.getOrderColumns();
/* 40 */     int size = columns.size();
/*    */ 
/*    */     
/* 43 */     this.m_SegmentNames = new String[size];
/* 44 */     this.m_SegmentPhysicalNames = new String[size];
/*    */     
/* 46 */     for (int i = 0; i < size; i++) {
/*    */       
/* 48 */       QueryColumn column = columns.get(i).getColumn();
/* 49 */       this.m_SegmentNames[i] = column.getAlias();
/* 50 */       this.m_SegmentPhysicalNames[i] = column.getField().getName();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 56 */     return this.m_Name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getSegmentNames() {
/* 61 */     return this.m_SegmentNames;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 66 */     return "[Name='" + this.m_Name + "', Alias='" + this.m_TableAlias + "', Segments=" + Arrays.toString((Object[])this.m_SegmentNames) + "]";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getSegmentPhysicalNames() {
/* 71 */     return this.m_SegmentPhysicalNames;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 76 */     this.m_TableAlias = (String)in.readObject();
/* 77 */     this.m_Name = (String)in.readObject();
/*    */     
/* 79 */     this.m_SegmentNames = ExternalizationUtil.readStringArray(in);
/* 80 */     this.m_SegmentPhysicalNames = ExternalizationUtil.readStringArray(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeExternal(ObjectOutput out) throws IOException {
/* 85 */     out.writeObject(this.m_TableAlias);
/* 86 */     out.writeObject(this.m_Name);
/* 87 */     ExternalizationUtil.writeStringArray(this.m_SegmentNames, out);
/* 88 */     ExternalizationUtil.writeStringArray(this.m_SegmentPhysicalNames, out);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryGridOrder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */