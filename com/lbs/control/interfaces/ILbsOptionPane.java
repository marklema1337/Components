/*     */ package com.lbs.control.interfaces;
/*     */ 
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsOptionPane;
/*     */ import java.awt.Component;
/*     */ import java.awt.HeadlessException;
/*     */ import javax.swing.Icon;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ILbsOptionPane
/*     */ {
/*  14 */   static Class<?> vaadinClass = null;
/*  15 */   static Class<?> swingClass = null;
/*     */ 
/*     */   
/*     */   static {
/*  19 */     initialize();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static void initialize() {
/*     */     try {
/*  26 */       vaadinClass = ILbsOptionPane.class.getClassLoader().loadClass("com.lbs.xui.vaadin.VLbsOptionPane");
/*     */     }
/*  28 */     catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  33 */       swingClass = JLbsOptionPane.class;
/*     */     }
/*  35 */     catch (Throwable throwable) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Class<?> getImplClass() {
/*  42 */     if (JLbsConstants.isRunningServerSide(null))
/*     */     {
/*  44 */       return vaadinClass;
/*     */     }
/*  46 */     return swingClass;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int showOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, int timeOut) {
/*  52 */     return showOptionDialog(parentComponent, message, title, optionType, messageType, null, null, null, timeOut);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int showOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue, int timeOut) throws HeadlessException {
/*  58 */     Class[] parameterTypes = { Component.class, Object.class, String.class, int.class, int.class, 
/*  59 */         Icon.class, Object[].class, Object.class, int.class };
/*  60 */     return ((Integer)invokeMethod("showOptionDialog", parameterTypes, new Object[] { parentComponent, message, title, Integer.valueOf(optionType), Integer.valueOf(messageType), 
/*  61 */           icon, options, initialValue, Integer.valueOf(timeOut) })).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean confirm(Component parent, String message) {
/*  66 */     Class[] parameterTypes = { Component.class, String.class };
/*  67 */     return ((Boolean)invokeMethod("confirm", parameterTypes, new Object[] { parent, message })).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int confirmYNC(Component parent, String message) {
/*  72 */     Class[] parameterTypes = { Component.class, String.class };
/*  73 */     return ((Integer)invokeMethod("confirmYNC", parameterTypes, new Object[] { parent, message })).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void warn(Component parent, String message) {
/*  78 */     Class[] parameterTypes = { Component.class, String.class };
/*  79 */     invokeMethod("warn", parameterTypes, new Object[] { parent, message });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void warnError(Component parent, String message) {
/*  84 */     Class[] parameterTypes = { Component.class, String.class };
/*  85 */     invokeMethod("warnError", parameterTypes, new Object[] { parent, message });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void message(Component parent, String message) {
/*  90 */     Class[] parameterTypes = { Component.class, String.class };
/*  91 */     invokeMethod("message", parameterTypes, new Object[] { parent, message });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void message(Component parent, String message, String title) {
/*  96 */     Class[] parameterTypes = { Component.class, String.class, String.class };
/*  97 */     invokeMethod("message", parameterTypes, new Object[] { parent, message, title });
/*     */   }
/*     */ 
/*     */   
/*     */   public static String showInputDialog(Object message) throws HeadlessException {
/* 102 */     return showInputDialog((Component)null, message);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String showInputDialog(Object message, Object initialSelectionValue) {
/* 107 */     return showInputDialog(null, message, initialSelectionValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String showInputDialog(Component parentComponent, Object message) throws HeadlessException {
/* 112 */     return showInputDialog(parentComponent, message, "", 3);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String showInputDialog(Component parentComponent, Object message, Object initialSelectionValue) {
/* 117 */     return (String)showInputDialog(parentComponent, message, "", 3, null, null, 
/* 118 */         initialSelectionValue);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String showInputDialog(Component parentComponent, Object message, String title, int messageType) throws HeadlessException {
/* 124 */     return (String)showInputDialog(parentComponent, message, title, messageType, null, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object showInputDialog(Component parentComponent, Object message, String title, int messageType, Icon icon, Object[] selectionValues, Object initialSelectionValue) throws HeadlessException {
/* 130 */     Class[] parameterTypes = { Component.class, Object.class, String.class, int.class, Icon.class, Object[].class, Object.class };
/* 131 */     return invokeMethod("showInputDialog", parameterTypes, new Object[] { parentComponent, message, title, Integer.valueOf(messageType), icon, selectionValues, initialSelectionValue });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void showMessageDialog(Component parentComponent, Object message) throws HeadlessException {
/* 136 */     showMessageDialog(parentComponent, message, "", 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType) throws HeadlessException {
/* 142 */     showMessageDialog(parentComponent, message, title, messageType, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType, Icon icon) throws HeadlessException {
/* 148 */     showOptionDialog(parentComponent, message, title, -1, messageType, icon, null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int showConfirmDialog(Component parentComponent, Object message) throws HeadlessException {
/* 153 */     return showConfirmDialog(parentComponent, message, "", 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType) throws HeadlessException {
/* 159 */     return showConfirmDialog(parentComponent, message, title, optionType, 3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType) throws HeadlessException {
/* 165 */     return showConfirmDialog(parentComponent, message, title, optionType, messageType, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon) throws HeadlessException {
/* 171 */     return showOptionDialog(parentComponent, message, title, optionType, messageType, icon, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int showOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue) throws HeadlessException {
/* 177 */     Class[] parameterTypes = { Component.class, Object.class, String.class, int.class, int.class, Icon.class, Object[].class, Object.class };
/* 178 */     return ((Integer)invokeMethod("showOptionDialog", parameterTypes, new Object[] { parentComponent, message, title, Integer.valueOf(optionType), Integer.valueOf(messageType), icon, options, initialValue })).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Object invokeMethod(String name, Class[] parameterTypes, Object... args) {
/*     */     try {
/* 185 */       Class<?> cls = getImplClass();
/* 186 */       return cls.getMethod(name, parameterTypes).invoke(null, args);
/*     */     }
/* 188 */     catch (Exception e) {
/*     */       
/* 190 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsOptionPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */