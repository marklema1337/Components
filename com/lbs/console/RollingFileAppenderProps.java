/*    */ package com.lbs.console;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ public class RollingFileAppenderProps
/*    */   extends AppenderProps
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private LbsLayoutFactory.LogFormat format;
/*    */   private String relativeLogFilePath;
/* 22 */   private String absoluteLogFilePath = "";
/*    */   
/*    */   private int maxNumFiles;
/*    */   
/*    */   private int maxSizeOfAFile;
/*    */ 
/*    */   
/*    */   public RollingFileAppenderProps(String name, int domainId, LbsLayoutFactory.LogFormat format, String relativeLogFilePath, int maxNumFiles, int maxSizeOfAFile) {
/* 30 */     super(name, domainId);
/* 31 */     if (format == null)
/* 32 */       throw new IllegalArgumentException("Log format cannot be null!"); 
/* 33 */     if (relativeLogFilePath == null || relativeLogFilePath.length() == 0)
/* 34 */       throw new IllegalArgumentException("Log file path cannot be null!"); 
/* 35 */     this.format = format;
/* 36 */     this.relativeLogFilePath = relativeLogFilePath;
/* 37 */     this.maxNumFiles = maxNumFiles;
/* 38 */     this.maxSizeOfAFile = maxSizeOfAFile;
/*    */   }
/*    */ 
/*    */   
/*    */   public LbsLayoutFactory.LogFormat getFormat() {
/* 43 */     return this.format;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLogFilePath() {
/* 48 */     return this.relativeLogFilePath;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAbsoluteFilePath() {
/* 53 */     return this.absoluteLogFilePath;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAbsoluteLogFilePath(String absoluteLogFilePath) {
/* 58 */     this.absoluteLogFilePath = absoluteLogFilePath;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxNumFiles() {
/* 63 */     return this.maxNumFiles;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxSizeOfAFile() {
/* 68 */     return this.maxSizeOfAFile;
/*    */   }
/*    */ 
/*    */   
/*    */   protected ILbsAppender createAppender() throws IOException {
/* 73 */     return LbsAppenderFactory.createAppender(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object clone() {
/* 78 */     RollingFileAppenderProps props = new RollingFileAppenderProps(getName(), getDomainId(), 
/* 79 */         (LbsLayoutFactory.LogFormat)this.format.clone(), this.relativeLogFilePath, this.maxNumFiles, this.maxSizeOfAFile);
/* 80 */     props.absoluteLogFilePath = this.absoluteLogFilePath;
/* 81 */     return props;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\RollingFileAppenderProps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */