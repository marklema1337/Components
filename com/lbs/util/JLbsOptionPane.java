/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.HeadlessException;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.ComponentAdapter;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JOptionPane;
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
/*     */ public class JLbsOptionPane
/*     */   extends JOptionPane
/*     */ {
/*     */   private static final int ms_ResID = -20000;
/*     */   
/*     */   public JLbsOptionPane(Object message, int messageType, int optionType, Icon icon, Object[] options, Object initialValue) {
/*  41 */     super(message, messageType, optionType, icon, options, initialValue);
/*     */   }
/*     */ 
/*     */   
/*     */   private static Window getWindowForComponentX(Component parentComponent) throws HeadlessException {
/*  46 */     if (parentComponent == null)
/*  47 */       return getRootFrame(); 
/*  48 */     if (parentComponent instanceof Frame || parentComponent instanceof Dialog)
/*  49 */       return (Window)parentComponent; 
/*  50 */     return getWindowForComponentX(parentComponent.getParent());
/*     */   }
/*     */ 
/*     */   
/*     */   private JDialog createDialog(Component parentComponent, String title, int style) throws HeadlessException {
/*     */     final JDialog dialog;
/*  56 */     Window window = getWindowForComponentX(parentComponent);
/*  57 */     if (window instanceof Frame) {
/*     */       
/*  59 */       dialog = new JDialog((Frame)window, title, true);
/*     */     }
/*     */     else {
/*     */       
/*  63 */       dialog = new JDialog((Dialog)window, title, true);
/*     */     } 
/*  65 */     Container contentPane = dialog.getContentPane();
/*  66 */     contentPane.setLayout(new BorderLayout());
/*  67 */     contentPane.add(this, "Center");
/*  68 */     dialog.setResizable(false);
/*  69 */     if (JDialog.isDefaultLookAndFeelDecorated()) {
/*     */       
/*  71 */       boolean supportsWindowDecorations = UIManager.getLookAndFeel().getSupportsWindowDecorations();
/*  72 */       if (supportsWindowDecorations) {
/*     */         
/*  74 */         dialog.setUndecorated(true);
/*  75 */         getRootPane().setWindowDecorationStyle(style);
/*     */       } 
/*     */     } 
/*  78 */     dialog.pack();
/*  79 */     dialog.setLocationRelativeTo(parentComponent);
/*  80 */     dialog.addWindowListener(new WindowAdapter()
/*     */         {
/*     */           private boolean gotFocus = false;
/*     */ 
/*     */           
/*     */           public void windowClosing(WindowEvent we) {
/*  86 */             JLbsOptionPane.this.setValue((Object)null);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public void windowGainedFocus(WindowEvent we) {
/*  92 */             if (!this.gotFocus) {
/*     */               
/*  94 */               JLbsOptionPane.this.selectInitialValue();
/*  95 */               this.gotFocus = true;
/*     */             } 
/*     */           }
/*     */ 
/*     */           
/*     */           public void windowDeactivated(WindowEvent e) {
/* 101 */             if (JLbsConstants.DESIGN_TIME)
/* 102 */               dialog.dispose(); 
/*     */           }
/*     */         });
/* 105 */     dialog.addComponentListener(new ComponentAdapter()
/*     */         {
/*     */           
/*     */           public void componentShown(ComponentEvent ce)
/*     */           {
/* 110 */             JLbsOptionPane.this.setValue(JOptionPane.UNINITIALIZED_VALUE);
/*     */           }
/*     */         });
/* 113 */     addPropertyChangeListener(new PropertyChangeListener()
/*     */         {
/*     */ 
/*     */ 
/*     */           
/*     */           public void propertyChange(PropertyChangeEvent event)
/*     */           {
/* 120 */             if (dialog.isVisible() && event.getSource() == JLbsOptionPane.this && 
/* 121 */               event.getPropertyName().equals("value") && event.getNewValue() != null && 
/* 122 */               event.getNewValue() != JOptionPane.UNINITIALIZED_VALUE)
/*     */             {
/* 124 */               dialog.setVisible(false);
/*     */             }
/*     */           }
/*     */         });
/* 128 */     return dialog;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int showOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, int timeOut) {
/* 134 */     return showOptionDialog(parentComponent, message, title, optionType, messageType, (Icon)null, (Object[])null, (Object)null, timeOut);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int showOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue, int timeOut) throws HeadlessException {
/* 140 */     localizeButtons();
/* 141 */     JLbsOptionPane pane = new JLbsOptionPane(message, messageType, optionType, icon, options, initialValue);
/* 142 */     pane.setInitialValue(initialValue);
/* 143 */     pane.setComponentOrientation(((parentComponent == null) ? 
/* 144 */         getRootFrame() : 
/* 145 */         parentComponent).getComponentOrientation());
/* 146 */     int style = 3;
/* 147 */     final JDialog dialog = pane.createDialog(parentComponent, title, style);
/* 148 */     if (JLbsConstants.DESIGN_TIME)
/* 149 */       dialog.addWindowListener(new WindowAdapter()
/*     */           {
/*     */             public void windowDeactivated(WindowEvent e)
/*     */             {
/* 153 */               dialog.dispose();
/*     */             }
/*     */           }); 
/* 156 */     pane.selectInitialValue();
/* 157 */     Timer timer = null;
/* 158 */     if (timeOut > 0) {
/*     */       
/* 160 */       JLbsOptionPaneTimeout timeout = new JLbsOptionPaneTimeout(dialog);
/* 161 */       timer = new Timer(timeOut, timeout);
/* 162 */       timer.start();
/*     */     } 
/*     */     
/* 165 */     dialog.show();
/*     */     
/* 167 */     System.out.println("Dialog returned with result :" + pane.getValue());
/*     */     
/* 169 */     if (timer != null)
/* 170 */       timer.stop(); 
/* 171 */     dialog.dispose();
/* 172 */     Object selectedValue = pane.getValue();
/* 173 */     if (selectedValue == null)
/* 174 */       return -1; 
/* 175 */     if (options == null) {
/*     */       
/* 177 */       if (selectedValue instanceof Integer)
/* 178 */         return ((Integer)selectedValue).intValue(); 
/* 179 */       return -1;
/*     */     } 
/* 181 */     for (int counter = 0, maxCounter = options.length; counter < maxCounter; counter++) {
/*     */       
/* 183 */       if (options[counter].equals(selectedValue))
/* 184 */         return counter; 
/*     */     } 
/* 186 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean confirm(Component parent, String message) {
/* 191 */     localizeButtons();
/* 192 */     message = JLbsStringUtil.checkNewLineChars(message);
/* 193 */     return (showConfirmDialog(parent, message, 
/* 194 */         JLbsLocalizer.getStringByIndexDef("UN", -20000, 0, "Confirm"), 0) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int confirmYNC(Component parent, String message) {
/* 199 */     localizeButtons();
/* 200 */     message = JLbsStringUtil.checkNewLineChars(message);
/* 201 */     return showConfirmDialog(parent, message, 
/* 202 */         JLbsLocalizer.getStringDef("UN", -20000, 1, "Confirm"), 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void warn(Component parent, String message) {
/* 207 */     localizeButtons();
/* 208 */     message = JLbsStringUtil.checkNewLineChars(message);
/* 209 */     showMessageDialog(parent, message, 
/* 210 */         JLbsLocalizer.getStringDef("UN", -20000, 2, "Warning"), 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void warnError(Component parent, String message) {
/* 215 */     localizeButtons();
/* 216 */     message = JLbsStringUtil.checkNewLineChars(message);
/* 217 */     showMessageDialog(parent, message, 
/* 218 */         JLbsLocalizer.getStringDef("UN", -20000, 3, "Error"), 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void message(Component parent, String message) {
/* 223 */     localizeButtons();
/* 224 */     message(parent, message, JLbsLocalizer.getStringByTagDef("UN", -20000, 3, "Message"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void message(Component parent, String message, String title) {
/* 229 */     localizeButtons();
/* 230 */     message = JLbsStringUtil.checkNewLineChars(message);
/* 231 */     showMessageDialog(parent, message, title, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   private static String addSpace(String s) {
/* 236 */     StringBuilder buff = new StringBuilder();
/* 237 */     buff.append("    ");
/* 238 */     buff.append(s);
/* 239 */     buff.append("    ");
/* 240 */     return buff.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void localizeButtons() {
/* 245 */     JLbsStringList list = JLbsLocalizer.getStringList("UN", -20000);
/* 246 */     if (list != null) {
/*     */       
/* 248 */       UIManager.put("OptionPane.yesButtonText", addSpace(JLbsLocalizer.getStringDef(list, 10, "Yes")));
/* 249 */       UIManager.put("OptionPane.noButtonText", addSpace(JLbsLocalizer.getStringDef(list, 11, "No")));
/* 250 */       UIManager.put("OptionPane.cancelButtonText", addSpace(JLbsLocalizer.getStringDef(list, 12, "Cancel")));
/* 251 */       UIManager.put("OptionPane.okButtonText", addSpace(JLbsLocalizer.getStringDef(list, 13, "Ok")));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsOptionPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */