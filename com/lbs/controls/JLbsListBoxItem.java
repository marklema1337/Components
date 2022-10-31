/*    */ package com.lbs.controls;
/*    */ 
/*    */ import javax.swing.ImageIcon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsListBoxItem
/*    */ {
/*    */   public ImageIcon Image;
/*    */   public String Text;
/*    */   public int ImageIndex;
/*    */   
/*    */   public JLbsListBoxItem() {}
/*    */   
/*    */   public JLbsListBoxItem(String text) {
/* 18 */     this(text, null, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsListBoxItem(String text, ImageIcon image) {
/* 23 */     this(text, image, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsListBoxItem(String text, ImageIcon image, int imageIndex) {
/* 28 */     this.Image = image;
/* 29 */     this.Text = text;
/* 30 */     this.ImageIndex = imageIndex;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 35 */     return this.Text;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toCopyString() {
/* 40 */     switch (this.ImageIndex) {
/*    */       
/*    */       case 1:
/* 43 */         return "(!)\t" + this.Text;
/*    */       case 2:
/* 45 */         return "(?)\t" + this.Text;
/*    */       case 3:
/* 47 */         return "(i)\t" + this.Text;
/*    */       case 4:
/* 49 */         return "(Err)\t" + this.Text;
/*    */     } 
/* 51 */     return "\t" + this.Text;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsListBoxItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */