/*    */ package com.lbs.data.query.select;
/*    */ 
/*    */ import com.lbs.data.objects.BusinessSchemaException;
/*    */ import com.lbs.data.query.QueryColumn;
/*    */ import com.lbs.data.query.QuerySchema;
/*    */ import com.lbs.data.query.QueryTable;
/*    */ import java.io.Serializable;
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
/*    */ public class SelectQueryTable
/*    */   extends QueryTable
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public static boolean hasAggregates(QueryTable table) {
/* 25 */     for (int i = 0; i < table.getColumns().size(); i++) {
/*    */       
/* 27 */       QueryColumn column = table.getColumns().item(i);
/*    */       
/* 29 */       if (!(column instanceof SelectQueryColumn)) {
/* 30 */         return false;
/*    */       }
/* 32 */       if (column.isAggregate() && !((SelectQueryColumn)column).isPartitionOver()) {
/* 33 */         return true;
/*    */       }
/*    */     } 
/* 36 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasAggregates() {
/* 41 */     return hasAggregates(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void checkColumns(QuerySchema qrySchema) throws BusinessSchemaException {
/* 47 */     for (int i = 0; i < this.m_Columns.size(); i++) {
/*    */       
/* 49 */       QueryColumn column = this.m_Columns.item(i);
/*    */       
/* 51 */       if (column.isPrimaryKey()) {
/*    */         return;
/*    */       }
/*    */     } 
/* 55 */     if (this.m_Table != null)
/*    */     {
/* 57 */       throw new BusinessSchemaException(qrySchema, "Query table '" + this.m_Table.getName() + "' must contain the primary key column!");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean hasOnlyNativeAggregate(QueryTable table) {
/* 70 */     boolean hsNative = false;
/* 71 */     for (int i = 0; i < table.getColumns().size(); i++) {
/*    */       
/* 73 */       QueryColumn column = table.getColumns().item(i);
/*    */       
/* 75 */       if (!(column instanceof SelectQueryColumn)) {
/* 76 */         return false;
/*    */       }
/* 78 */       if (column.isNativeAggregate()) {
/* 79 */         hsNative = true;
/*    */       }
/* 81 */       if (column.isAggregate() && !column.isNativeAggregate()) {
/* 82 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 86 */     return hsNative;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasOnlyNativeAggregate() {
/* 92 */     return hasOnlyNativeAggregate(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\select\SelectQueryTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */