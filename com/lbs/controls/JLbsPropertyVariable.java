/*    */ package com.lbs.controls;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Comparator;
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
/*    */ public class JLbsPropertyVariable
/*    */   implements Serializable, Comparable<JLbsPropertyVariable>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String m_Name;
/*    */   private String m_Description;
/*    */   private transient boolean m_Processing = false;
/*    */   
/*    */   public JLbsPropertyVariable(String name, String desc) {
/* 29 */     this.m_Name = name;
/* 30 */     this.m_Description = desc;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 35 */     return this.m_Name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 40 */     this.m_Name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 45 */     return this.m_Description;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDescription(String description) {
/* 50 */     this.m_Description = description;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isProcessing() {
/* 55 */     return this.m_Processing;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setProcessing(boolean processing) {
/* 60 */     this.m_Processing = processing;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int compareTo(JLbsPropertyVariable o) {
/* 67 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 74 */     if (!(obj instanceof JLbsPropertyVariable))
/* 75 */       return false; 
/* 76 */     return (compareTo((JLbsPropertyVariable)obj) == 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 83 */     return super.hashCode();
/*    */   }
/*    */   
/* 86 */   public static Comparator<JLbsPropertyVariable> comparator = new Comparator<JLbsPropertyVariable>()
/*    */     {
/*    */ 
/*    */       
/*    */       public int compare(JLbsPropertyVariable var1, JLbsPropertyVariable var2)
/*    */       {
/* 92 */         return var1.getDescription().toUpperCase().compareTo(var2.getDescription().toUpperCase());
/*    */       }
/*    */     };
/*    */   
/*    */   public JLbsPropertyVariable() {}
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsPropertyVariable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */