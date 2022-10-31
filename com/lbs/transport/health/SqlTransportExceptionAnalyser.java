/*    */ package com.lbs.transport.health;
/*    */ 
/*    */ import com.lbs.util.StringUtil;
/*    */ import java.sql.SQLException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SqlTransportExceptionAnalyser
/*    */   implements TransportExceptionAnalyser
/*    */ {
/*    */   private ArrayList<Pattern> m_Patterns;
/*    */   
/*    */   public SqlTransportExceptionAnalyser() {
/* 17 */     this.m_Patterns = new ArrayList<>();
/*    */     
/* 19 */     this.m_Patterns.add(Pattern.compile(".*jdbc.*CommunicationsException.*", 32));
/*    */     
/* 21 */     this.m_Patterns.add(Pattern.compile(".*ORA-17008.*", 32));
/*    */     
/* 23 */     this.m_Patterns.add(Pattern.compile(".*ORA-17002.*", 32));
/*    */     
/* 25 */     this.m_Patterns.add(Pattern.compile(".*ORA-17016.*", 32));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isTransportBlocker(Throwable t) {
/* 31 */     if (t instanceof SQLException) {
/*    */       
/* 33 */       SQLException ex = (SQLException)t;
/* 34 */       String sqlState = ex.getSQLState();
/* 35 */       if (!StringUtil.isEmpty(sqlState) && sqlState.startsWith("08"))
/* 36 */         return true; 
/* 37 */       for (Pattern pattern : this.m_Patterns) {
/*    */         
/* 39 */         Matcher matcher = pattern.matcher(ex.toString());
/* 40 */         if (matcher.matches())
/* 41 */           return true; 
/*    */       } 
/*    */     } 
/* 44 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\health\SqlTransportExceptionAnalyser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */