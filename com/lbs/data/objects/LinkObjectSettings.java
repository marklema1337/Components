/*    */ package com.lbs.data.objects;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LinkObjectSettings
/*    */ {
/*    */   private int m_CascadeOptions;
/*    */   private boolean m_Collection;
/*    */   
/*    */   public LinkObjectSettings(int cascadeOptions, boolean collection) {
/* 11 */     this.m_CascadeOptions = cascadeOptions;
/* 12 */     this.m_Collection = collection;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCascadeOptions() {
/* 17 */     return this.m_CascadeOptions;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCascadeOptions(int cascadeOptions) {
/* 22 */     this.m_CascadeOptions = cascadeOptions;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCollection() {
/* 27 */     return this.m_Collection;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCollection(boolean collection) {
/* 32 */     this.m_Collection = collection;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\LinkObjectSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */