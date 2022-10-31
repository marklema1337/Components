/*    */ package com.lbs.juel.tree;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.util.Stack;
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
/*    */ public class NodePrinter
/*    */ {
/*    */   private static boolean isLastSibling(Node node, Node parent) {
/* 30 */     if (parent != null)
/*    */     {
/* 32 */       return (node == parent.getChild(parent.getCardinality() - 1));
/*    */     }
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private static void dump(PrintWriter writer, Node node, Stack<Node> predecessors) {
/* 39 */     if (!predecessors.isEmpty()) {
/*    */       
/* 41 */       Node node1 = null;
/* 42 */       for (Node predecessor : predecessors) {
/*    */         
/* 44 */         if (isLastSibling(predecessor, node1)) {
/*    */           
/* 46 */           writer.print("   ");
/*    */         }
/*    */         else {
/*    */           
/* 50 */           writer.print("|  ");
/*    */         } 
/* 52 */         node1 = predecessor;
/*    */       } 
/* 54 */       writer.println("|");
/*    */     } 
/* 56 */     Node parent = null;
/* 57 */     for (Node predecessor : predecessors) {
/*    */       
/* 59 */       if (isLastSibling(predecessor, parent)) {
/*    */         
/* 61 */         writer.print("   ");
/*    */       }
/*    */       else {
/*    */         
/* 65 */         writer.print("|  ");
/*    */       } 
/* 67 */       parent = predecessor;
/*    */     } 
/* 69 */     writer.print("+- ");
/* 70 */     writer.println(node.toString());
/*    */     
/* 72 */     predecessors.push(node);
/* 73 */     for (int i = 0; i < node.getCardinality(); i++)
/*    */     {
/* 75 */       dump(writer, node.getChild(i), predecessors);
/*    */     }
/* 77 */     predecessors.pop();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void dump(PrintWriter writer, Node node) {
/* 82 */     dump(writer, node, new Stack<>());
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\NodePrinter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */