/*     */ package com.lbs.laf;
/*     */ 
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.Enumeration;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIDefaults;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LAFChanger
/*     */   implements ActionListener
/*     */ {
/*     */   private String lafClassName;
/*     */   private IClientContext m_Context;
/*     */   
/*     */   public static JMenuItem getMenuItem(IClientContext context, String lafName, String lafClassName) {
/*  63 */     JMenuItem result = new JMenuItem(lafName);
/*  64 */     result.addActionListener(new LAFChanger(context, lafClassName));
/*  65 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LAFChanger(IClientContext context, String lafClassName) {
/*  71 */     this.lafClassName = lafClassName;
/*  72 */     this.m_Context = context;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/*  78 */     changeTheme(this.m_Context, this.lafClassName);
/*  79 */     LAFPluginManager.saveLAFPreferences(this.m_Context);
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
/*     */   private void dumpUIDefaults() {
/*  91 */     UIDefaults defaults = UIManager.getDefaults();
/*  92 */     System.out.println();
/*  93 */     Enumeration keys = defaults.keys();
/*     */     
/*  95 */     while (keys.hasMoreElements()) {
/*     */       
/*  97 */       Object key = keys.nextElement();
/*     */       
/*  99 */       if (key instanceof String) {
/*     */         
/* 101 */         String s = (String)key;
/* 102 */         if (s.startsWith("Button.")) {
/* 103 */           System.out.println(key + "=" + defaults.get(key));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void changeTheme(IClientContext context, String lafClassName) {
/*     */     LookAndFeel laf;
/*     */     try {
/* 116 */       laf = (LookAndFeel)context.createInstance(lafClassName);
/*     */     }
/* 118 */     catch (Exception cnfe) {
/*     */       
/* 120 */       out("LAF main class '" + lafClassName + "' not found");
/*     */       
/*     */       return;
/*     */     } 
/* 124 */     boolean canBeDecoratedByLAF = laf.getSupportsWindowDecorations();
/*     */     
/* 126 */     JDialog.setDefaultLookAndFeelDecorated(canBeDecoratedByLAF);
/* 127 */     JFrame.setDefaultLookAndFeelDecorated(canBeDecoratedByLAF);
/*     */ 
/*     */     
/*     */     try {
/* 131 */       UIManager.setLookAndFeel(laf);
/*     */     }
/* 133 */     catch (UnsupportedLookAndFeelException e1) {
/*     */       
/* 135 */       e1.printStackTrace();
/*     */     } 
/*     */     
/* 138 */     Frame[] frames = Frame.getFrames();
/* 139 */     for (int i = 0; i < frames.length; i++) {
/*     */       
/* 141 */       if (frames[i] instanceof JFrame) {
/* 142 */         changeLAF(laf, (JFrame)frames[i], canBeDecoratedByLAF);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void out(Object obj) {
/*     */     try {
/* 150 */       System.out.println(obj);
/*     */     }
/* 152 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void changeLAF(LookAndFeel laf, final JFrame frame, final boolean canBeDecoratedByLAF) {
/* 160 */     SwingUtilities.invokeLater(new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 164 */             boolean wasDecoratedByOS = !frame.isUndecorated();
/*     */ 
/*     */ 
/*     */             
/*     */             try {
/* 169 */               SwingUtilities.invokeLater(new Runnable()
/*     */                   {
/*     */                     public void run()
/*     */                     {
/* 173 */                       SwingUtilities.updateComponentTreeUI(frame);
/*     */                     }
/*     */                   });
/*     */             }
/* 177 */             catch (Exception exc) {
/*     */               
/* 179 */               exc.printStackTrace();
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 185 */             if (canBeDecoratedByLAF == wasDecoratedByOS) {
/*     */               
/* 187 */               boolean wasVisible = frame.isVisible();
/*     */               
/* 189 */               frame.setVisible(false);
/* 190 */               frame.dispose();
/* 191 */               if (!canBeDecoratedByLAF) {
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 196 */                 frame.setUndecorated(false);
/* 197 */                 frame.getRootPane().setWindowDecorationStyle(0);
/*     */               
/*     */               }
/*     */               else {
/*     */                 
/* 202 */                 frame.setUndecorated(true);
/* 203 */                 frame.getRootPane().setWindowDecorationStyle(1);
/*     */               } 
/* 205 */               frame.setVisible(wasVisible);
/* 206 */               wasDecoratedByOS = !frame.isUndecorated();
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\LAFChanger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */