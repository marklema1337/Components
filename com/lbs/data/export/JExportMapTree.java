/*    */ package com.lbs.data.export;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class JExportMapTree
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   JExportMapItem m_Item;
/* 11 */   private ArrayList<JExportMapTree> m_Children = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public JExportMapTree() {}
/*    */ 
/*    */   
/*    */   public JExportMapTree(JExportMapItem item) {
/* 19 */     this.m_Item = item;
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<JExportMapTree> getChildren() {
/* 24 */     return this.m_Children;
/*    */   }
/*    */ 
/*    */   
/*    */   public JExportMapItem getItem() {
/* 29 */     return this.m_Item;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasChildren() {
/* 34 */     return (this.m_Children.size() > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addChild(JExportMapTree child) {
/* 39 */     this.m_Children.add(child);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\JExportMapTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */