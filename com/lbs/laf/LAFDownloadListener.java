/*     */ package com.lbs.laf;
/*     */ 
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.SwingUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class LAFDownloadListener
/*     */   implements ActionListener
/*     */ {
/*     */   protected IClientContext m_Context;
/*     */   protected JMenu m_ParentMenu;
/*     */   protected JMenuItem m_DownloadMenuItem;
/*     */   protected boolean m_Downloading;
/*     */   protected int m_Progress;
/*     */   
/*     */   public LAFDownloadListener(IClientContext context, JMenu parentMenu, JMenuItem menuItem) {
/* 135 */     this.m_Context = context;
/* 136 */     this.m_ParentMenu = parentMenu;
/* 137 */     this.m_DownloadMenuItem = menuItem;
/* 138 */     this.m_Downloading = false;
/* 139 */     this.m_Progress = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 144 */     if (this.m_Downloading) {
/*     */       return;
/*     */     }
/* 147 */     this.m_DownloadMenuItem.setText("Downloading - Please Wait");
/* 148 */     (new Thread()
/*     */       {
/*     */         
/*     */         public void run()
/*     */         {
/*     */           try {
/* 154 */             LAFDownloadListener.this.m_Downloading = true;
/*     */             
/* 156 */             PluginManager.loadPlugin(LAFDownloadListener.this.m_Context, LAFPluginManager.SUBSTANCE_PLUGIN_NAME, 
/* 157 */                 LAFPluginManager.SUBSTANCE_PLUGIN_SIZE, new IDownloadProgressListener()
/*     */                 {
/*     */                   public void reportProgress(long numBytes)
/*     */                   {
/* 161 */                     int progress = (int)(numBytes * 100L / LAFPluginManager.SUBSTANCE_PLUGIN_SIZE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 170 */                     synchronized ((LAFDownloadListener.null.access$0(LAFDownloadListener.null.this)).m_DownloadMenuItem) {
/*     */                       
/* 172 */                       (LAFDownloadListener.null.access$0(LAFDownloadListener.null.this)).m_DownloadMenuItem.setText("Downloading - " + progress + "% Complete");
/* 173 */                       SwingUtilities.invokeLater(new Runnable()
/*     */                           {
/*     */                             public void run()
/*     */                             {
/* 177 */                               (LAFDownloadListener.null.access$0(LAFDownloadListener.null.null.access$0(LAFDownloadListener.null.null.this))).m_DownloadMenuItem.repaint();
/*     */                             }
/*     */                           });
/*     */                     } 
/*     */                   }
/*     */                 });
/*     */ 
/*     */ 
/*     */             
/* 186 */             LAFDownloadListener.this.m_DownloadMenuItem.setVisible(false);
/* 187 */             LAFDownloadListener.this.m_DownloadMenuItem = null;
/* 188 */             LAFDownloadListener.this.m_ParentMenu.removeAll();
/* 189 */             LAFMenuFactory.createSubstanceMenu(LAFDownloadListener.this.m_Context, LAFDownloadListener.this.m_ParentMenu);
/* 190 */             SwingUtilities.invokeLater(new Runnable()
/*     */                 {
/*     */                   public void run()
/*     */                   {
/* 194 */                     (LAFDownloadListener.null.access$0(LAFDownloadListener.null.this)).m_ParentMenu.repaint();
/*     */                   }
/*     */                 });
/*     */           }
/* 198 */           catch (Exception e1) {
/*     */             
/* 200 */             e1.printStackTrace();
/*     */           }
/* 202 */           catch (Throwable t) {
/*     */             
/* 204 */             t.printStackTrace();
/*     */           }
/*     */           finally {
/*     */             
/* 208 */             if (LAFDownloadListener.this.m_DownloadMenuItem != null) {
/* 209 */               LAFDownloadListener.this.m_DownloadMenuItem.setText(LAFPluginManager.SUBSTANCE_PLUGIN_MENU);
/*     */             }
/* 211 */             LAFDownloadListener.this.m_Downloading = false;
/*     */           } 
/*     */         }
/* 214 */       }).start();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\LAFDownloadListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */