/*    */ package com.lbs.controls.buttonpanel;
/*    */ 
/*    */ import com.lbs.controls.JLbsComboEdit;
/*    */ import java.awt.Component;
/*    */ import java.awt.Container;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Rectangle;
/*    */ import javax.swing.ButtonModel;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.SwingUtilities;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.plaf.ComponentUI;
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
/*    */ public class JLbsButtonPanelLangButton
/*    */   extends JLbsButtonPanelSimpleButton
/*    */ {
/*    */   private boolean m_LangFlag = false;
/*    */   private JLbsComboEdit m_ComboEdit;
/*    */   
/*    */   public JLbsButtonPanelLangButton(int iWidth, int iGlyphType, JLbsComboEdit comboedit) {
/* 32 */     super(iWidth, iGlyphType);
/* 33 */     this.m_ComboEdit = comboedit;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void InternalPaintButton(Component c, Graphics g, Rectangle rect, boolean bPressed, boolean bHover, int offset) {
/* 40 */     rect.width--;
/*    */     
/* 42 */     JButton button = this.m_ComboEdit.getMultilangButton();
/* 43 */     ButtonModel model = button.getModel();
/* 44 */     model.setPressed(bPressed);
/* 45 */     model.setSelected(bPressed);
/* 46 */     model.setArmed(bPressed);
/* 47 */     if (bHover) {
/* 48 */       model.setRollover(true);
/*    */     } else {
/* 50 */       model.setRollover(false);
/* 51 */     }  g.setColor(UIManager.getColor("control"));
/* 52 */     g.fillRect(rect.x, rect.y, rect.width, rect.height);
/*    */ 
/*    */     
/* 55 */     ComponentUI ui = UIManager.getUI(button);
/* 56 */     if (ui != null) {
/*    */       
/* 58 */       button.setLocation(0, 0);
/* 59 */       button.setSize(rect.getSize());
/* 60 */       g.translate(rect.x, rect.y);
/* 61 */       ui.paint(g, button);
/* 62 */       g.translate(-rect.x, -rect.y);
/*    */     } 
/*    */     
/* 65 */     SwingUtilities.paintComponent(g, button, (Container)c, rect);
/* 66 */     Rectangle rectDraw = new Rectangle(rect.x + 2, rect.y + 2, rect.width - 4, rect.height - 5);
/* 67 */     paintGlyph(g, rectDraw, offset, offset);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isLangFlag() {
/* 72 */     return this.m_LangFlag;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLangFlag(boolean langFlag) {
/* 77 */     this.m_LangFlag = langFlag;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\buttonpanel\JLbsButtonPanelLangButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */