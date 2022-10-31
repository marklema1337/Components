/*    */ package com.lbs.remoteclient;
/*    */ 
/*    */ import com.lbs.util.JLbsFileUtil;
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
/*    */ public class JLbsReportTestParams
/*    */ {
/* 16 */   public String ReportName = null;
/* 17 */   public String CustomReportName = null;
/* 18 */   public int CustomReportRef = 0;
/* 19 */   public int TotalDuration = 0;
/* 20 */   public int ActionID = 0;
/* 21 */   public String SourceReportFile = null;
/* 22 */   public String FilterListFile = null;
/*    */   public boolean printFilter = false;
/* 24 */   public String reportType = null;
/*    */ 
/*    */   
/*    */   private String getStringParameter(String str) {
/* 28 */     if (str == null)
/* 29 */       return "null"; 
/* 30 */     return "\"" + str + "\"";
/*    */   }
/*    */ 
/*    */   
/*    */   public String toScriptString() {
/* 35 */     StringBuilder result = new StringBuilder();
/* 36 */     result.append(getStringParameter(this.ReportName));
/* 37 */     result.append(", ");
/* 38 */     result.append(getStringParameter(this.CustomReportName));
/* 39 */     result.append(", ");
/* 40 */     result.append(this.CustomReportRef);
/* 41 */     result.append(", ");
/* 42 */     result.append(this.TotalDuration);
/* 43 */     result.append(", ");
/* 44 */     result.append(getStringParameter(JLbsFileUtil.getFileName(this.FilterListFile)));
/* 45 */     result.append(", ");
/* 46 */     result.append(getStringParameter(JLbsFileUtil.getFileName(this.SourceReportFile)));
/* 47 */     result.append(", ");
/* 48 */     result.append(this.printFilter);
/* 49 */     return result.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toScriptString4Pdf() {
/* 54 */     StringBuilder result = new StringBuilder();
/* 55 */     result.append(getStringParameter(this.ReportName));
/* 56 */     result.append(", ");
/* 57 */     result.append(getStringParameter(this.CustomReportName));
/* 58 */     result.append(", ");
/* 59 */     result.append(this.CustomReportRef);
/* 60 */     result.append(", ");
/* 61 */     result.append(this.TotalDuration);
/* 62 */     result.append(", ");
/* 63 */     result.append(getStringParameter(JLbsFileUtil.getFileName(this.FilterListFile)));
/* 64 */     result.append(", ");
/* 65 */     result.append(this.printFilter);
/* 66 */     return result.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoteclient\JLbsReportTestParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */