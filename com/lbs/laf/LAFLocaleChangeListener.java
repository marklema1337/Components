/*     */ package com.lbs.laf;
/*     */ 
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.globalization.JLbsCultureInfoBase;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import java.awt.Component;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Container;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.Locale;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.SwingUtilities;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LAFLocaleChangeListener
/*     */   implements ActionListener
/*     */ {
/*     */   private String langCode;
/*     */   private String countryCode;
/*     */   private JRootPane frame;
/*     */   
/*     */   public LAFLocaleChangeListener(String langCode, String countryCode, JRootPane rootPane) {
/*  79 */     this.langCode = langCode;
/*  80 */     this.countryCode = countryCode;
/*  81 */     this.frame = rootPane;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setLocale(Component component, Locale locale) {
/* 105 */     component.setLocale(locale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     if (component instanceof Container) {
/*     */       
/* 117 */       Container cont = (Container)component;
/* 118 */       for (int i = 0; i < cont.getComponentCount(); i++) {
/* 119 */         setLocale(cont.getComponent(i), locale);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 125 */     SwingUtilities.invokeLater(new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 129 */             LookAndFeel currLaf = UIManager.getLookAndFeel();
/*     */             
/* 131 */             Locale newLocale = new Locale(LAFLocaleChangeListener.this.langCode, LAFLocaleChangeListener.this.countryCode);
/* 132 */             Locale.setDefault(newLocale);
/*     */             
/* 134 */             ILbsCultureInfo newCulture = JLbsCultureInfoBase.getCultureInfo(newLocale);
/* 135 */             if (newCulture != null) {
/* 136 */               JLbsLocalizer.setCultureInfo(newCulture);
/*     */             }
/* 138 */             if (LAFLocaleChangeListener.this.frame == null) {
/*     */               return;
/*     */             }
/* 141 */             LAFLocaleChangeListener.this.frame.applyComponentOrientation(ComponentOrientation.getOrientation(Locale.getDefault()));
/*     */             
/*     */             try {
/* 144 */               UIManager.setLookAndFeel(currLaf.getClass().getName());
/*     */             }
/* 146 */             catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */             
/* 150 */             SwingUtilities.updateComponentTreeUI(LAFLocaleChangeListener.this.frame);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\LAFLocaleChangeListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */