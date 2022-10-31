/*     */ package com.lbs.data.query;
/*     */ 
/*     */ import com.lbs.util.StringUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GridQueryOrder
/*     */ {
/*     */   protected String m_GlobalOrderName;
/*     */   protected GridQuerySubOrder[] m_Orders;
/*     */   
/*     */   public GridQueryOrder(String orderDef) {
/*  20 */     parse(orderDef);
/*     */   }
/*     */ 
/*     */   
/*     */   public void parse(String orderDef) {
/*  25 */     int idx = orderDef.indexOf(':');
/*     */     
/*  27 */     if (idx != -1) {
/*     */       
/*  29 */       this.m_GlobalOrderName = orderDef.substring(0, idx);
/*  30 */       orderDef = orderDef.substring(idx + 1);
/*     */     } 
/*     */     
/*  33 */     String[] parts = StringUtil.split(orderDef, ',');
/*  34 */     if (parts != null) {
/*     */       
/*  36 */       this.m_Orders = new GridQuerySubOrder[parts.length];
/*     */       
/*  38 */       for (int i = 0; i < parts.length; i++)
/*     */       {
/*  40 */         this.m_Orders[i] = new GridQuerySubOrder(parts[i]);
/*     */       }
/*     */     } else {
/*     */       
/*  44 */       this.m_Orders = (GridQuerySubOrder[])new QuerySubOrderParams[0];
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/*  49 */     StringBuilder sb = new StringBuilder();
/*     */     
/*  51 */     if (!StringUtil.isEmpty(this.m_GlobalOrderName)) {
/*     */       
/*  53 */       sb.append(this.m_GlobalOrderName);
/*  54 */       sb.append(":");
/*     */     } 
/*     */     
/*  57 */     for (int i = 0; i < this.m_Orders.length; i++) {
/*     */       
/*  59 */       sb.append(this.m_Orders[i].toString());
/*     */       
/*  61 */       if (i < this.m_Orders.length - 1) {
/*  62 */         sb.append(',');
/*     */       }
/*     */     } 
/*  65 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*  70 */     GridQueryOrder x = new GridQueryOrder("GlobalOrderName:TableAlias1.order1, TableAlias2.order2");
/*     */     
/*  72 */     System.out.println(x);
/*     */     
/*  74 */     x = new GridQueryOrder("TableAlias1.order1, order2");
/*     */     
/*  76 */     System.out.println(x);
/*     */   }
/*     */ 
/*     */   
/*     */   public GridQuerySubOrder getTableOrder(String tableAlias) {
/*  81 */     for (int i = 0; i < this.m_Orders.length; i++) {
/*     */       
/*  83 */       if (StringUtil.equals((this.m_Orders[i]).m_TableAlias, tableAlias)) {
/*  84 */         return this.m_Orders[i];
/*     */       }
/*     */     } 
/*  87 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int findOrder(String tableAlias, String orderName) {
/*  93 */     for (int i = 0; i < this.m_Orders.length; i++) {
/*     */       
/*  95 */       GridQuerySubOrder subOrder = this.m_Orders[i];
/*  96 */       if (tableAlias == null || StringUtil.equals(subOrder.m_TableAlias, tableAlias))
/*     */       {
/*  98 */         if (StringUtil.equals(subOrder.m_OrderName, orderName)) {
/*  99 */           return i;
/*     */         }
/*     */       }
/*     */     } 
/* 103 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGlobalOrderName() {
/* 108 */     return this.m_GlobalOrderName;
/*     */   }
/*     */ 
/*     */   
/*     */   public GridQuerySubOrder[] getOrders() {
/* 113 */     return this.m_Orders;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\GridQueryOrder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */