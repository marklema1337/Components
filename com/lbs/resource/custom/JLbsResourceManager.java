/*     */ package com.lbs.resource.custom;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.platform.interfaces.IApplicationContext;
/*     */ import com.lbs.util.JLbsNameValueMap;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.io.FileNotFoundException;
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
/*     */ class JLbsResourceManager
/*     */ {
/*     */   protected IApplicationContext m_Context;
/*     */   protected JLbsNameValueMap m_Table;
/*     */   
/*     */   public JLbsResourceManager(IApplicationContext context) {
/* 408 */     this.m_Context = context;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearCache() {
/* 413 */     this.m_Table = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsStringList getStringList(String resName) {
/* 419 */     JLbsNameValueMap map = getCacheTable();
/* 420 */     Object obj = map.getValue(resName);
/* 421 */     if (obj instanceof JLbsStringList) {
/* 422 */       return (JLbsStringList)obj;
/*     */     }
/*     */     try {
/* 425 */       byte[] retData = (this.m_Context != null) ? 
/* 426 */         this.m_Context.getResource(resName, true) : 
/* 427 */         null;
/* 428 */       if (retData != null)
/*     */       {
/* 430 */         String sFileContent = JLbsStringUtil.getString(retData);
/* 431 */         String[] resLines = sFileContent.replaceAll("\r", "").split("\n");
/* 432 */         JLbsStringList result = new JLbsStringList();
/* 433 */         for (int i = 0; i < resLines.length; i++)
/* 434 */           result.add(resLines[i]); 
/* 435 */         map.setValue(resName, result);
/* 436 */         return result;
/*     */       }
/*     */     
/* 439 */     } catch (FileNotFoundException fnfe) {
/*     */       
/* 441 */       LbsConsole.getLogger("Transport.ResourceMgr")
/* 442 */         .warn("JLbsResourceManager.getStringList list not found (" + resName + ")");
/* 443 */       map.setValue(resName, new JLbsStringList());
/*     */     }
/* 445 */     catch (Exception e) {
/*     */       
/* 447 */       LbsConsole.getLogger("Transport.ResourceMgr").error("JLbsResourceManager.getStringList exception (" + resName + ")", e);
/*     */     } 
/* 449 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getStringList(int iListNo) {
/* 454 */     if (iListNo == 0)
/* 455 */       return null; 
/* 456 */     return getStringList(getStringListPath(iListNo));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStringListItemValue(int iListNo, int iTag) {
/* 461 */     return getStringListItemValue(getStringListPath(iListNo), iTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStringListItemValue(String resName, int iTag) {
/* 466 */     JLbsStringList list = getStringList(resName);
/* 467 */     if (list != null)
/* 468 */       return list.getValueAtTag(iTag); 
/* 469 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getStringListPath(int iListNo) {
/* 474 */     return "slist/" + iListNo + ".txt";
/*     */   }
/*     */ 
/*     */   
/*     */   protected synchronized JLbsNameValueMap getCacheTable() {
/* 479 */     if (this.m_Table == null)
/* 480 */       this.m_Table = new JLbsNameValueMap(); 
/* 481 */     return this.m_Table;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\resource\custom\JLbsResourceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */