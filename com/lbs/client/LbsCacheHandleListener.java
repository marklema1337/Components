/*    */ package com.lbs.client;
/*    */ 
/*    */ import com.lbs.cache.ICacheConstants;
/*    */ import com.lbs.cache.JLbsClientCacheConfigurator;
/*    */ import com.lbs.console.LbsConsole;
/*    */ import com.lbs.remoteclient.IClientContext;
/*    */ import com.lbs.start.ILbsCacheHandleListener;
/*    */ import java.awt.event.WindowEvent;
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
/*    */ public class LbsCacheHandleListener
/*    */   implements ILbsCacheHandleListener, ICacheConstants
/*    */ {
/*    */   private IClientContext m_Context;
/* 23 */   private final transient LbsConsole m_Logger = LbsConsole.getLogger("LbsAppletListener");
/*    */ 
/*    */   
/*    */   public LbsCacheHandleListener(IClientContext context) {
/* 27 */     this.m_Context = context;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void flushCacheContentToDisk(boolean logout) {
/*    */     try {
/* 34 */       if (logout) {
/* 35 */         this.m_Context.logout();
/*    */       }
/*    */     } finally {
/*    */       
/* 39 */       System.out.println("Flushing cache content to disk..");
/*    */       
/*    */       try {
/* 42 */         JLbsClientCacheConfigurator.shutdown(true);
/*    */       }
/* 44 */       catch (Exception e) {
/*    */         
/* 46 */         this.m_Logger.warn("Could not flush content to disk, see the exception below" + e.getMessage(), e);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void windowActivated(WindowEvent e) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void windowClosed(WindowEvent e) {}
/*    */ 
/*    */   
/*    */   public void windowClosing(WindowEvent e) {
/* 61 */     flushCacheContentToDisk(true);
/*    */   }
/*    */   
/*    */   public void windowDeactivated(WindowEvent e) {}
/*    */   
/*    */   public void windowDeiconified(WindowEvent e) {}
/*    */   
/*    */   public void windowIconified(WindowEvent e) {}
/*    */   
/*    */   public void windowOpened(WindowEvent e) {}
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\client\LbsCacheHandleListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */