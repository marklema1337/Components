/*    */ package com.lbs.data.database;
/*    */ 
/*    */ import com.lbs.data.factory.INamedVariables;
/*    */ import com.lbs.data.factory.ISubstitutionListener;
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
/*    */ public class DBEntityItem
/*    */   extends DBEntity
/*    */   implements Cloneable, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 20 */   private DBEntityCollection m_Collection = null;
/*    */   
/*    */   private boolean m_Generated = false;
/*    */ 
/*    */   
/*    */   public DBEntityItem(DBEntityItem src) {
/* 26 */     super(src);
/* 27 */     this.m_Collection = src.m_Collection;
/*    */   }
/*    */ 
/*    */   
/*    */   public DBEntityItem(int type) {
/* 32 */     super(type);
/*    */   }
/*    */ 
/*    */   
/*    */   public DBEntityCollection getCollection() {
/* 37 */     return this.m_Collection;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getCollectionOwner() {
/* 42 */     if (this.m_Collection != null) {
/* 43 */       return this.m_Collection.getOwner();
/*    */     }
/* 45 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCollection(DBEntityCollection collection) {
/* 50 */     this.m_Collection = collection;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void changeName(INameProducer namer, INamedVariables variables, ISubstitutionListener listener, Object dbConnection) {
/* 56 */     this.m_PhysicalName = namer.getPhysicalName(this, variables, listener, dbConnection);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object clone() throws CloneNotSupportedException {
/* 61 */     return super.clone();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPhysicalName() {
/* 66 */     return this.m_PhysicalName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPhysicalName(String string) {
/* 74 */     this.m_PhysicalName = string;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isGenerated() {
/* 79 */     return this.m_Generated;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setGenerated(boolean generated) {
/* 84 */     this.m_Generated = generated;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\DBEntityItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */