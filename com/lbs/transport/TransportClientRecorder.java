/*     */ package com.lbs.transport;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.zip.GZIPOutputStream;
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
/*     */ public class TransportClientRecorder
/*     */ {
/*  23 */   private static final String[] IGNORE_RESULTS_OF = new String[] { "LoadSingleJarFromResources", "SupportedLanguages" };
/*     */   private ObjectOutputStream oos;
/*     */   private File file;
/*     */   private boolean enabled = true;
/*  27 */   int counter = 0;
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/*  31 */     return this.enabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/*  36 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public TransportClientRecorder(String fileName) {
/*  41 */     int counter = 0;
/*  42 */     String f = String.valueOf(fileName) + counter;
/*  43 */     this.file = new File(f);
/*  44 */     this.file.getParentFile().mkdirs();
/*  45 */     while (this.file.exists()) {
/*  46 */       f = String.valueOf(fileName) + counter++;
/*  47 */       this.file = new File(f);
/*     */     } 
/*     */     
/*     */     try {
/*  51 */       FileOutputStream fos = new FileOutputStream(this.file);
/*  52 */       GZIPOutputStream gz = new GZIPOutputStream(fos);
/*  53 */       this.oos = new ObjectOutputStream(gz);
/*     */     }
/*  55 */     catch (Exception e) {
/*     */       
/*  57 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void dataSent(String sUri, byte[] sendPrefix, byte[] sendData, boolean bPublicInfo, byte[] returnData) {
/*  62 */     if (!this.enabled)
/*     */       return; 
/*  64 */     System.out.println("Recorder : " + sUri);
/*  65 */     TransportWatchedData twd = new TransportWatchedData();
/*  66 */     twd.setsUri(sUri);
/*  67 */     twd.setSendPrefix(sendPrefix);
/*  68 */     twd.setSendData(sendData);
/*  69 */     twd.setbPublicInfo(bPublicInfo);
/*  70 */     boolean skip = false; byte b; int i; String[] arrayOfString;
/*  71 */     for (i = (arrayOfString = IGNORE_RESULTS_OF).length, b = 0; b < i; ) { String irof = arrayOfString[b];
/*  72 */       if (sUri.endsWith("?" + irof) || 
/*  73 */         sUri.indexOf("/ResDownload?") > -1) {
/*  74 */         skip = true; break;
/*     */       }  b++; }
/*     */     
/*  77 */     if (!skip) {
/*  78 */       twd.setReturnData(returnData);
/*     */     }
/*     */     try {
/*  81 */       this.oos.writeObject(twd);
/*  82 */       this.counter++;
/*     */     }
/*  84 */     catch (IOException e) {
/*     */       
/*  86 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dumpAndClear() throws Exception {
/*  91 */     this.oos.flush();
/*  92 */     this.oos.close();
/*  93 */     System.out.println("Watcher : " + this.counter + " objects recorded.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void remoteMethodInvoked(RemoteMethodRequest request, RemoteMethodResponse response) throws IOException {
/*  98 */     if (!this.enabled)
/*     */       return; 
/* 100 */     System.out.println("Recorder : RMI -> " + request.TargetServlet);
/* 101 */     TransportWatchedData twd = new TransportWatchedData();
/* 102 */     twd.setRMIRequest(request);
/* 103 */     twd.setRMIResponse(response);
/* 104 */     this.oos.writeObject(twd);
/* 105 */     this.counter++;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\TransportClientRecorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */