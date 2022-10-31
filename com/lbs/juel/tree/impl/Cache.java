/*    */ package com.lbs.juel.tree.impl;
/*    */ 
/*    */ import com.lbs.juel.tree.Tree;
/*    */ import com.lbs.juel.tree.TreeCache;
/*    */ import java.util.Collections;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import java.util.WeakHashMap;
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
/*    */ public final class Cache
/*    */   implements TreeCache
/*    */ {
/*    */   private final Map<String, Tree> primary;
/*    */   private final Map<String, Tree> secondary;
/*    */   
/*    */   public Cache(int size) {
/* 46 */     this(size, new WeakHashMap<>());
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
/*    */   public Cache(final int size, Map<String, Tree> secondary) {
/* 59 */     this.primary = Collections.synchronizedMap(new LinkedHashMap<String, Tree>(16, 0.75F, true)
/*    */         {
/*    */           
/*    */           protected boolean removeEldestEntry(Map.Entry<String, Tree> eldest)
/*    */           {
/* 64 */             if (size() > size) {
/*    */               
/* 66 */               if (Cache.this.secondary != null)
/*    */               {
/* 68 */                 Cache.this.secondary.put(eldest.getKey(), eldest.getValue());
/*    */               }
/* 70 */               return true;
/*    */             } 
/* 72 */             return false;
/*    */           }
/*    */         });
/* 75 */     this.secondary = (secondary == null) ? 
/* 76 */       null : 
/* 77 */       Collections.<String, Tree>synchronizedMap(secondary);
/*    */   }
/*    */ 
/*    */   
/*    */   public Tree get(String expression) {
/* 82 */     if (this.secondary == null)
/*    */     {
/* 84 */       return this.primary.get(expression);
/*    */     }
/*    */ 
/*    */     
/* 88 */     Tree tree = this.primary.get(expression);
/* 89 */     if (tree == null)
/*    */     {
/* 91 */       tree = this.secondary.get(expression);
/*    */     }
/* 93 */     return tree;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void put(String expression, Tree tree) {
/* 99 */     this.primary.put(expression, tree);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\Cache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */