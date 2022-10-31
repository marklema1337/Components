/*    */ package com.lbs.laf.mac;
/*    */ 
/*    */ import com.lbs.laf.common.SkinnedComboBoxButton;
/*    */ import com.lbs.laf.common.SkinnedComboBoxUI;
/*    */ import com.lbs.util.JLbsConstants;
/*    */ import java.awt.Insets;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.border.Border;
/*    */ import javax.swing.plaf.ComponentUI;
/*    */ import javax.swing.plaf.metal.MetalComboBoxIcon;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ComboBoxUI
/*    */   extends SkinnedComboBoxUI
/*    */ {
/*    */   public static Border ms_DefaultBorder;
/*    */   
/*    */   public static ComponentUI createUI(JComponent c) {
/* 32 */     return (ComponentUI)new ComboBoxUI();
/*    */   }
/*    */ 
/*    */   
/*    */   public ComboBoxUI() {
/* 37 */     super(ms_DefaultBorder, 21);
/*    */   }
/*    */ 
/*    */   
/*    */   protected JButton createArrowButton() {
/* 42 */     SkinnedComboBoxButton button = new SkinnedComboBoxButton(this.comboBox, new MetalComboBoxIcon(), this.comboBox.isEditable(), 
/* 43 */         this.currentValuePane, this.listBox);
/* 44 */     button.setMargin(new Insets(0, 0, 0, 0));
/* 45 */     button.m_SkinArrow = DefaultTheme.getSkinImage(ComboBoxUI.class, "comboboxarrow.png", 4, 0);
/* 46 */     button.m_SkinButton = DefaultTheme.getSkinImage(ComboBoxUI.class, JLbsConstants.DESKTOP_MODE ? 
/* 47 */         "lookupbuttondesktop.png" : 
/* 48 */         "lookupbutton.png", 9, 0);
/* 49 */     button.m_SkinCombo = DefaultTheme.getSkinImage(ComboBoxUI.class, "combobox.png", 4, 0, 0, 21, 0);
/* 50 */     button.m_SkinIcon = DefaultTheme.getImageIcon(ComboBoxUI.class, "comboboxfocus.png");
/* 51 */     button.m_ButtonWidth = 21;
/* 52 */     button.m_ButtonBorder = 2;
/*    */     
/* 54 */     return (JButton)button;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\mac\ComboBoxUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */