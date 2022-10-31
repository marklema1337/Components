/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsAbstractButton;
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsMenuButton;
/*     */ import com.lbs.controls.JLbsMenuButton;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JMenu;
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
/*     */ public abstract class UIHelperUtil
/*     */ {
/*     */   public static void setCaption(JComponent comp, String caption) {
/*  28 */     char ch = getMnemonicChar(caption);
/*  29 */     if (ch != '\000')
/*  30 */       caption = stripMenuCaption(caption); 
/*  31 */     int index = JLbsStringUtil.isEmpty(caption) ? 
/*  32 */       -1 : 
/*  33 */       caption.indexOf("~");
/*  34 */     if (index >= 0 && comp instanceof ILbsCaptionTag) {
/*     */       
/*     */       try {
/*  37 */         String szTag = caption.substring(index + 1, caption.length());
/*  38 */         caption = caption.substring(0, index);
/*  39 */         ((ILbsCaptionTag)comp).setTag(Integer.valueOf(szTag).intValue());
/*     */       }
/*  41 */       catch (Exception exception) {}
/*     */     }
/*     */     
/*  44 */     if (ch != '\000' && comp instanceof AbstractButton)
/*  45 */       ((AbstractButton)comp).setMnemonic(ch); 
/*  46 */     if (comp instanceof AbstractButton) {
/*  47 */       ((AbstractButton)comp).setText(caption);
/*  48 */     } else if (comp instanceof JMenu) {
/*  49 */       ((JMenu)comp).setText(caption);
/*  50 */     } else if (comp instanceof JLbsMenuButton) {
/*  51 */       ((JLbsMenuButton)comp).setToolTipText(caption.substring(0, index));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setVaadinCaption(ILbsComponent comp, String caption) {
/*  56 */     char ch = getMnemonicChar(caption);
/*  57 */     if (ch != '\000')
/*  58 */       caption = stripMenuCaption(caption); 
/*  59 */     int index = caption.indexOf("~");
/*  60 */     if (index >= 0 && comp instanceof ILbsCaptionTag) {
/*     */       
/*     */       try {
/*  63 */         String szTag = caption.substring(index + 1, caption.length());
/*  64 */         caption = caption.substring(0, index);
/*  65 */         ((ILbsCaptionTag)comp).setTag(Integer.valueOf(szTag).intValue());
/*     */       }
/*  67 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */     
/*  71 */     if (comp instanceof ILbsAbstractButton) {
/*  72 */       ((ILbsAbstractButton)comp).setText(caption);
/*  73 */     } else if (comp instanceof JMenu) {
/*  74 */       ((JMenu)comp).setText(caption);
/*  75 */     } else if (comp instanceof ILbsMenuButton) {
/*  76 */       ((ILbsMenuButton)comp).setToolTipText(caption.substring(0, index));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static char getMnemonicChar(String caption) {
/*  81 */     if (caption != null) {
/*     */       
/*  83 */       int iLen = caption.length() - 1;
/*  84 */       for (int i = 0; i < iLen; i++) {
/*  85 */         if (caption.charAt(i) == '&') {
/*     */           
/*  87 */           char ch = caption.charAt(i + 1);
/*  88 */           if (ch == '&' || ch == ' ')
/*  89 */           { i++; }
/*     */           else
/*  91 */           { return ch; } 
/*     */         } 
/*     */       } 
/*  94 */     }  return Character.MIN_VALUE;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String stripMenuCaption(String caption) {
/*  99 */     if (caption != null) {
/*     */       
/* 101 */       StringBuilder buffer = new StringBuilder(caption);
/* 102 */       int iLen = buffer.length() - 1;
/* 103 */       for (int i = 0; i < iLen; i++) {
/* 104 */         if (buffer.charAt(i) == '&') {
/*     */           
/* 106 */           char ch = caption.charAt(i + 1);
/* 107 */           if (ch == '&') {
/* 108 */             i++;
/*     */           } else {
/*     */             
/* 111 */             buffer.delete(i, i + 1); break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 115 */       return buffer.toString();
/*     */     } 
/* 117 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void localizeFileChooser() {
/* 122 */     ILocalizationServices lclService = JLbsLocalizer.getLocalizationService();
/* 123 */     JLbsStringList list = lclService.getList(24510, "UN");
/* 124 */     if (list != null) {
/*     */       
/* 126 */       UIManager.put("FileChooser.lookInLabelText", list.getValueAtTag(1));
/* 127 */       UIManager.put("FileChooser.fileNameLabelText", list.getValueAtTag(2));
/* 128 */       UIManager.put("FileChooser.filesOfTypeLabelText", list.getValueAtTag(3));
/* 129 */       UIManager.put("FileChooser.openButtonText", list.getValueAtTag(4));
/* 130 */       UIManager.put("FileChooser.cancelButtonText", list.getValueAtTag(5));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\UIHelperUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */