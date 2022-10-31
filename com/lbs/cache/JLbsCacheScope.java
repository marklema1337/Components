/*     */ package com.lbs.cache;
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
/*     */ 
/*     */ public abstract class JLbsCacheScope
/*     */ {
/*     */   abstract String getUniqueID();
/*     */   
/*     */   abstract String[] getScopeSearchPatterns();
/*     */   
/*     */   public static JLbsCacheScope USER(int userID, int[] groupIDs) {
/*  30 */     return new JLbsUserScope(userID, groupIDs);
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JLbsCacheScope GROUP(int[] groupIDs) {
/*  49 */     return new JLbsGroupScope(groupIDs);
/*     */   }
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
/*     */   
/*     */   public static JLbsCacheScope GLOBAL() {
/*  63 */     return new JLbsGlobalScope();
/*     */   }
/*     */   
/*     */   static class JLbsUserScope
/*     */     extends JLbsGroupScope {
/*  68 */     private int m_UserID = 0;
/*     */     
/*     */     public static final String ID = "U";
/*     */     
/*     */     JLbsUserScope(int userID, int[] groupIDs) {
/*  73 */       super(groupIDs);
/*  74 */       this.m_UserID = userID;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String getUniqueID() {
/*  80 */       return "U" + this.m_UserID;
/*     */     }
/*     */ 
/*     */     
/*     */     protected String[] getScopeSearchPatterns() {
/*  85 */       String[] inheritedPatterns = super.getScopeSearchPatterns();
/*  86 */       String[] searchPatterns = new String[1 + inheritedPatterns.length];
/*  87 */       searchPatterns[0] = "U" + this.m_UserID;
/*  88 */       System.arraycopy(inheritedPatterns, 0, searchPatterns, 1, inheritedPatterns.length);
/*  89 */       return searchPatterns;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  94 */       return "USER [" + getUniqueID() + "] (USER+GROUP+GLOBAL)";
/*     */     }
/*     */   }
/*     */   
/*     */   static class JLbsGroupScope
/*     */     extends JLbsGlobalScope
/*     */   {
/*     */     private int[] m_GroupIDs;
/*     */     public static final String ID = "G";
/*     */     
/*     */     JLbsGroupScope(int[] groupIDs) {
/* 105 */       if (groupIDs == null) {
/* 106 */         this.m_GroupIDs = new int[0];
/*     */       } else {
/* 108 */         this.m_GroupIDs = groupIDs;
/*     */       } 
/*     */     }
/*     */     
/*     */     public String getUniqueID() {
/* 113 */       if (this.m_GroupIDs.length == 0) {
/* 114 */         return "G";
/*     */       }
/* 116 */       return "G" + this.m_GroupIDs[0];
/*     */     }
/*     */ 
/*     */     
/*     */     protected String[] getScopeSearchPatterns() {
/* 121 */       String[] inheritedPatterns = super.getScopeSearchPatterns();
/* 122 */       String[] searchPatterns = new String[this.m_GroupIDs.length + inheritedPatterns.length];
/* 123 */       int i = 0;
/* 124 */       for (; i < this.m_GroupIDs.length; i++)
/* 125 */         searchPatterns[i] = "G" + this.m_GroupIDs[i]; 
/* 126 */       System.arraycopy(inheritedPatterns, 0, searchPatterns, i, inheritedPatterns.length);
/* 127 */       return searchPatterns;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 132 */       return "GROUP [" + getUniqueID() + "] (GROUP+GLOBAL)";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class JLbsGlobalScope
/*     */     extends JLbsCacheScope
/*     */   {
/*     */     public static final String ID = "GL";
/*     */ 
/*     */ 
/*     */     
/*     */     public String getUniqueID() {
/* 146 */       return "GL";
/*     */     }
/*     */ 
/*     */     
/*     */     protected String[] getScopeSearchPatterns() {
/* 151 */       return new String[] { "GL" };
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 156 */       return "GLOBAL [" + getUniqueID() + "]";
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\JLbsCacheScope.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */