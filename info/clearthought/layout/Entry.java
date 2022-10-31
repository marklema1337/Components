/*    */ package info.clearthought.layout;
/*    */ 
/*    */ import java.awt.Component;
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
/*    */ public class Entry
/*    */   implements Cloneable
/*    */ {
/*    */   public Component component;
/*    */   public int[] cr1;
/*    */   public int[] cr2;
/*    */   public int[] alignment;
/*    */   
/*    */   public Entry(Component component, TableLayoutConstraints constraint) {
/* 27 */     int[] cr1 = { constraint.col1, constraint.row1 };
/* 28 */     int[] cr2 = { constraint.col2, constraint.row2 };
/* 29 */     int[] alignment = { constraint.hAlign, constraint.vAlign };
/*    */     
/* 31 */     this.cr1 = cr1;
/* 32 */     this.cr2 = cr2;
/* 33 */     this.alignment = alignment;
/* 34 */     this.component = component;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object copy() throws CloneNotSupportedException {
/* 43 */     return clone();
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
/*    */   public String toString() {
/* 55 */     TableLayoutConstraints c = new TableLayoutConstraints(this.cr1[0], this.cr1[1], this.cr2[0], 
/* 56 */         this.cr2[1], this.alignment[0], this.alignment[1]);
/*    */     
/* 58 */     return "(" + c + ") " + this.component;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\info\clearthought\layout\Entry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */