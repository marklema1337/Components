/*    */ package com.lbs.juel.tree;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TreeStore
/*    */ {
/*    */   private final TreeCache cache;
/*    */   private final TreeBuilder builder;
/*    */   
/*    */   public TreeStore(TreeBuilder builder, TreeCache cache) {
/* 40 */     this.builder = builder;
/* 41 */     this.cache = cache;
/*    */   }
/*    */ 
/*    */   
/*    */   public TreeBuilder getBuilder() {
/* 46 */     return this.builder;
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
/*    */   
/*    */   public Tree get(String expression) throws TreeBuilderException {
/* 59 */     if (this.cache == null)
/*    */     {
/* 61 */       return this.builder.build(expression);
/*    */     }
/* 63 */     Tree tree = this.cache.get(expression);
/* 64 */     if (tree == null)
/*    */     {
/* 66 */       this.cache.put(expression, tree = this.builder.build(expression));
/*    */     }
/* 68 */     return tree;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\TreeStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */