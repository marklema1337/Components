/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.data.application.IApplicationEnvironment;
/*     */ import com.lbs.data.objects.BasicBusinessObject;
/*     */ import com.lbs.data.objects.BusinessObjectEvent;
/*     */ import com.lbs.data.objects.IBusinessObjectChangeListener;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.Calendar;
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
/*     */ public class BusinessObjectChangeRecorder
/*     */   implements IBusinessObjectChangeListener
/*     */ {
/*     */   private ObjectOutputStream m_Out;
/*     */   private File m_OutputFile;
/*     */   private boolean m_LogRecorded = false;
/*     */   public static final String FILE_DIR_EXT = "BODrafts";
/*     */   
/*     */   public BusinessObjectChangeRecorder(BasicBusinessObject obj, String formName, String formTitle, int userID, IApplicationEnvironment appEnv) {
/*  35 */     BusinessObjectChangeHeader header = new BusinessObjectChangeHeader(userID, formName, formTitle, obj, appEnv, Calendar.getInstance());
/*     */     
/*     */     try {
/*  38 */       initFile(header);
/*     */     }
/*  40 */     catch (Exception e) {
/*     */       
/*  42 */       LbsConsole.getLogger("Data.Client.BOChangeHandler").error(null, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void changed(BusinessObjectEvent e) {
/*  51 */     LbsConsole ms_Logger = LbsConsole.getLogger("Data.Client.BOChangeHandler");
/*  52 */     if (this.m_Out != null) {
/*     */       
/*     */       try {
/*     */         
/*  56 */         this.m_Out.writeObject(e);
/*  57 */         this.m_Out.flush();
/*  58 */         this.m_LogRecorded = true;
/*     */       }
/*  60 */       catch (Exception e1) {
/*     */         
/*  62 */         ms_Logger.error(null, e1);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  68 */     switch (e.getReason()) {
/*     */       
/*     */       case 1:
/*  71 */         ms_Logger.debug("Member Changed : '" + e.getMemberName() + "' value=" + e.getValue());
/*     */         break;
/*     */       
/*     */       case 2:
/*  75 */         ms_Logger.debug("Method invoked on member : '" + e.getMemberName() + "' method name='" + e.getMethodName() + "' value=" + e
/*  76 */             .getValue());
/*     */         break;
/*     */       
/*     */       case 3:
/*  80 */         ms_Logger.debug("List Created : " + e.getMemberName() + " value=" + e.getValue());
/*     */         break;
/*     */       
/*     */       case 4:
/*  84 */         ms_Logger.debug("List Item Added : " + e.getMemberName() + " value=" + e.getValue());
/*     */         break;
/*     */       
/*     */       case 5:
/*  88 */         ms_Logger.debug("List Item Removed : " + e.getMemberName() + " value=" + e.getValue());
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initFile(BusinessObjectChangeHeader header) throws IOException {
/*  95 */     if (JLbsClientFS.makeDirectory("BODrafts")) {
/*     */       
/*  97 */       File parentDir = new File(JLbsClientFS.getFullPath("BODrafts"));
/*  98 */       String xuiFormName = header.getXUIFormName();
/*  99 */       xuiFormName = xuiFormName.replaceAll("/", "_");
/* 100 */       this.m_OutputFile = File.createTempFile(xuiFormName + "_", ".tmp", parentDir);
/* 101 */       header.setFileCanonicalPath(this.m_OutputFile.getCanonicalPath());
/* 102 */       FileOutputStream fout = new FileOutputStream(this.m_OutputFile);
/* 103 */       this.m_Out = new ObjectOutputStream(fout);
/* 104 */       this.m_Out.writeObject(header);
/* 105 */       this.m_Out.flush();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 113 */     super.finalize();
/* 114 */     doCleanUp(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doCleanUp(boolean deleteLogFile) {
/*     */     try {
/* 121 */       if (this.m_Out != null)
/* 122 */         this.m_Out.close(); 
/* 123 */       if (this.m_OutputFile != null)
/*     */       {
/* 125 */         if (deleteLogFile || !this.m_LogRecorded) {
/* 126 */           this.m_OutputFile.delete();
/*     */         }
/*     */       }
/* 129 */     } catch (Exception e) {
/*     */       
/* 131 */       LbsConsole.getLogger("Data.Client.BOChangeHandler").error(null, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMemberFullPath() {
/* 139 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\BusinessObjectChangeRecorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */