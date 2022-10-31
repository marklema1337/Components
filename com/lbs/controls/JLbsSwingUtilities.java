/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsInvokeHandler;
/*     */ import com.lbs.control.interfaces.ILbsTimer;
/*     */ import com.lbs.controls.emulator.ILbsComponentEmulator;
/*     */ import com.lbs.controls.menu.JLbsPopupMenu;
/*     */ import com.lbs.xui.RunnableWithReturn;
/*     */ import com.lbs.xui.SwingWorker;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.Timer;
/*     */ import javax.swing.UIManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsSwingUtilities
/*     */ {
/*     */   private static boolean ms_SuspendInvokeLater = false;
/*  37 */   private static Vector<Runnable> ms_Runnables = new Vector<>();
/*     */   
/*     */   private static ILbsInvokeLaterHandler invokeLaterHandler;
/*     */   
/*     */   public static synchronized void setSuspendInvokeLater(boolean suspendInvokeLater) {
/*  42 */     ms_SuspendInvokeLater = suspendInvokeLater;
/*  43 */     if (!ms_SuspendInvokeLater) {
/*     */       
/*  45 */       for (Runnable runnable : ms_Runnables)
/*     */       {
/*  47 */         SwingUtilities.invokeLater(runnable);
/*     */       }
/*  49 */       ms_Runnables.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void invokeLater(Runnable runnable) {
/*  55 */     invokeLater(null, runnable);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void invokeLater(Object source, Runnable runnable) {
/*  60 */     ILbsInvokeHandler invokeHandler = JLbsComponentHelper.getInvokeHandler(source);
/*  61 */     if (invokeHandler != null) {
/*  62 */       invokeHandler.invokeLater(runnable);
/*  63 */     } else if (isRunningServerSide(source)) {
/*  64 */       invokeLaterHandler.invokeLater(source, runnable);
/*     */     } else {
/*  66 */       innerInvokeLater(source, runnable);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static synchronized void innerInvokeLater(Object source, Runnable runnable) {
/*  71 */     if (ms_SuspendInvokeLater) {
/*     */       
/*  73 */       ms_Runnables.add(runnable);
/*     */     } else {
/*     */       
/*  76 */       SwingUtilities.invokeLater(runnable);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ILbsTimer getTimer(Object source) {
/*  81 */     ILbsInvokeHandler invokeHandler = JLbsComponentHelper.getInvokeHandler(source);
/*  82 */     if (invokeHandler != null) {
/*     */       
/*  84 */       ILbsTimer timer = invokeHandler.getTimer();
/*  85 */       if (timer != null)
/*  86 */         return timer; 
/*     */     } 
/*  88 */     return new SwingTimer();
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object runWithProgressImpl(Object source, String message, RunnableWithReturn operation) throws Exception {
/*  93 */     ILbsInvokeHandler invokeHandler = JLbsComponentHelper.getInvokeHandler(source);
/*  94 */     if (invokeHandler != null) {
/*  95 */       return invokeHandler.runWithProgressImpl(message, operation);
/*     */     }
/*     */     
/*  98 */     setSuspendInvokeLater(true);
/*  99 */     boolean defLwPopupEnabled = JLbsPopupMenu.getDefaultLightWeightPopupEnabled();
/*     */     
/*     */     try {
/* 102 */       JLbsPopupMenu.setDefaultLightWeightPopupEnabled(true);
/* 103 */       ProgressDialog progressDialog = new ProgressDialog(message);
/* 104 */       ProgressBarSwingWorker worker = new ProgressBarSwingWorker(operation, progressDialog);
/* 105 */       worker.start();
/* 106 */       progressDialog.setVisible(true);
/* 107 */       Exception e = worker.getException();
/* 108 */       if (e != null)
/* 109 */         throw e; 
/* 110 */       return worker.get();
/*     */     }
/*     */     finally {
/*     */       
/* 114 */       JLbsPopupMenu.setDefaultLightWeightPopupEnabled(defLwPopupEnabled);
/* 115 */       setSuspendInvokeLater(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object deleteRunWithProgressImpl(Object source, String message, RunnableWithReturn operation, int firstRecord, int selectedRowCount) throws Exception {
/* 122 */     ILbsInvokeHandler invokeHandler = JLbsComponentHelper.getInvokeHandler(source);
/* 123 */     Object result = null;
/* 124 */     if (invokeHandler != null) {
/* 125 */       return invokeHandler.runWithProgressImpl(message, operation);
/*     */     }
/*     */     
/* 128 */     setSuspendInvokeLater(true);
/* 129 */     boolean defLwPopupEnabled = JLbsPopupMenu.getDefaultLightWeightPopupEnabled();
/*     */     
/*     */     try {
/* 132 */       JLbsPopupMenu.setDefaultLightWeightPopupEnabled(true);
/* 133 */       ProgressDialog progressDialog = new ProgressDialog(String.valueOf(message) + firstRecord + "/" + selectedRowCount);
/* 134 */       ProgressBarSwingWorker worker = new ProgressBarSwingWorker(operation, progressDialog);
/* 135 */       worker.start();
/* 136 */       progressDialog.setVisible(true);
/* 137 */       Exception e = worker.getException();
/* 138 */       if (e != null)
/* 139 */         throw e; 
/* 140 */       return worker.get();
/*     */     }
/*     */     finally {
/*     */       
/* 144 */       JLbsPopupMenu.setDefaultLightWeightPopupEnabled(defLwPopupEnabled);
/* 145 */       setSuspendInvokeLater(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void invokeAndWait(Object source, Runnable runnable) throws InterruptedException, InvocationTargetException {
/* 155 */     SwingUtilities.invokeAndWait(runnable);
/*     */   }
/*     */   
/*     */   public static class SwingTimer
/*     */     implements ILbsTimer
/*     */   {
/*     */     private Timer m_Timer;
/*     */     
/*     */     public void schedule(int delay, ActionListener listener) {
/* 164 */       this.m_Timer = new Timer(delay, listener);
/*     */     }
/*     */ 
/*     */     
/*     */     public void stop() {
/* 169 */       if (this.m_Timer != null) {
/* 170 */         this.m_Timer.stop();
/*     */       }
/*     */     }
/*     */     
/*     */     public void start() {
/* 175 */       if (this.m_Timer != null) {
/* 176 */         this.m_Timer.start();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static class ProgressBarSwingWorker
/*     */     extends SwingWorker
/*     */   {
/*     */     private RunnableWithReturn m_Operation;
/*     */     private JDialog m_ProgressDialog;
/*     */     private Exception m_Exception;
/*     */     
/*     */     public ProgressBarSwingWorker(RunnableWithReturn operation, JDialog progressDialog) {
/* 190 */       this.m_Operation = operation;
/* 191 */       this.m_ProgressDialog = progressDialog;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object construct() {
/* 196 */       Object result = null;
/*     */       
/*     */       try {
/*     */         do {
/*     */         
/* 201 */         } while (!this.m_ProgressDialog.isFocused());
/*     */ 
/*     */         
/* 204 */         result = this.m_Operation.run();
/*     */       }
/* 206 */       catch (Exception e) {
/*     */         
/* 208 */         e.printStackTrace();
/* 209 */         this.m_Exception = e;
/*     */       } 
/* 211 */       return result;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void finished() {
/* 217 */       super.finished();
/* 218 */       this.m_ProgressDialog.dispose();
/*     */     }
/*     */ 
/*     */     
/*     */     public Exception getException() {
/* 223 */       return this.m_Exception;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ProgressDialog
/*     */     extends JDialog
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     public static JProgressBar bar;
/*     */     
/*     */     public JProgressBar getBar() {
/* 234 */       return bar;
/*     */     }
/*     */     
/*     */     public void setBar(JProgressBar bar) {
/* 238 */       ProgressDialog.bar = bar;
/*     */     }
/*     */ 
/*     */     
/*     */     public ProgressDialog(String message) {
/* 243 */       getContentPane().setLayout(new BorderLayout());
/*     */       
/* 245 */       UIManager.put("ProgressBar.background", Color.WHITE);
/* 246 */       UIManager.put("ProgressBar.foreground", new Color(100, 150, 255));
/* 247 */       UIManager.put("ProgressBar.selectionBackground", Color.BLUE);
/* 248 */       UIManager.put("ProgressBar.selectionForeground", Color.WHITE);
/*     */       
/* 250 */       bar = new JProgressBar();
/* 251 */       bar.setIndeterminate(true);
/* 252 */       bar.setString(message);
/* 253 */       bar.setStringPainted(true);
/* 254 */       bar.setBackground(Color.white);
/*     */       
/* 256 */       getContentPane().add(bar, "Center");
/* 257 */       setModal(true);
/* 258 */       setDefaultCloseOperation(0);
/* 259 */       setUndecorated(true);
/* 260 */       getRootPane().setWindowDecorationStyle(0);
/* 261 */       pack();
/* 262 */       centerScreen();
/*     */     }
/*     */ 
/*     */     
/*     */     public void centerScreen() {
/* 267 */       Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 268 */       int x = (scrSize.width - getWidth()) / 2;
/* 269 */       int y = (scrSize.height - getHeight()) / 2;
/* 270 */       setLocation(x, y);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setInvokeLaterHandler(ILbsInvokeLaterHandler invokeLaterHandler) {
/* 276 */     JLbsSwingUtilities.invokeLaterHandler = invokeLaterHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isWebClientThread() {
/* 286 */     return (invokeLaterHandler != null && invokeLaterHandler.isWebClientThread());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isEmulating(Object component) {
/* 291 */     return (component instanceof ILbsComponentEmulator && ((ILbsComponentEmulator)component).isEmulating());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isRunningServerSide(Object component) {
/* 296 */     return isWebClientThread();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsSwingUtilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */