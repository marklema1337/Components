/*     */ package com.lbs.controls.buttonpanel;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsMaskedEdit;
/*     */ import com.lbs.control.interfaces.ILbsPrefixPanel;
/*     */ import com.lbs.controls.JLbsControlHelper;
/*     */ import com.lbs.controls.maskededit.JLbsMaskedEdit;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.border.EtchedBorder;
/*     */ import javax.swing.plaf.PanelUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsPrefixPanel
/*     */   extends JLbsButtonPanel
/*     */   implements FocusListener, ILbsPrefixPanel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected String m_PrefixString;
/*     */   protected int m_PrefixWidth;
/*     */   protected Color m_PrefixColor;
/*     */   protected int m_PrefixOfset;
/*     */   protected Dimension m_FillPrefSize;
/*     */   protected boolean firstView = false;
/*     */   
/*     */   public JLbsPrefixPanel() {
/*  44 */     this(new JLbsMaskedEdit());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsPrefixPanel(JLbsMaskedEdit textControl) {
/*  50 */     this.m_FillComp = textControl;
/*  51 */     if (this.m_FillComp != null) {
/*     */       
/*  53 */       JLbsMaskedEdit edit = this.m_FillComp;
/*  54 */       if (edit.getBorder() != null)
/*  55 */         setBorder(new EtchedBorder(1)); 
/*  56 */       edit.setBorder(new EmptyBorder(0, 0, 0, 0));
/*  57 */       this.m_FillComp.addFocusListener(this);
/*     */     } 
/*  59 */     updateUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/*  64 */     super.updateUI();
/*  65 */     resetBkColor(false, false);
/*  66 */     Border border = getBorder();
/*  67 */     if (border != null && !(border instanceof EmptyBorder)) {
/*     */       
/*  69 */       border = UIManager.getBorder("ComboEdit.border");
/*  70 */       setBorder((border != null) ? 
/*  71 */           border : 
/*  72 */           new EtchedBorder(1));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUI(PanelUI ui) {
/*  78 */     super.setUI(ui);
/*  79 */     if (ui != null && getBorder() != null) {
/*     */       
/*  81 */       Border border = UIManager.getBorder("ComboEdit.border");
/*  82 */       if (border != null) {
/*  83 */         setBorder(border);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setPrefix(String prefix) {
/*  89 */     this.m_PrefixString = prefix;
/*  90 */     updatePrefixWidth();
/*  91 */     UpdateChildComponentLayout();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFont(Font f) {
/*  96 */     super.setFont(f);
/*  97 */     updatePrefixWidth();
/*  98 */     repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrefix() {
/* 103 */     return this.m_PrefixString;
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetBkColor(boolean bFocused, boolean bForce) {
/* 108 */     if (this.m_FillComp != null) {
/*     */       
/* 110 */       boolean focused = bForce ? 
/* 111 */         bFocused : 
/* 112 */         this.m_FillComp.hasFocus();
/* 113 */       Color bkColor = focused ? 
/* 114 */         JLbsMaskedEdit.getSelectedBkColor() : 
/* 115 */         this.m_FillComp.getNormalBkColor();
/* 116 */       setBackground(bkColor);
/* 117 */       invalidate();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintBackground(Graphics g) {
/* 123 */     if (this.m_FillComp != null) {
/*     */       
/* 125 */       g.setColor(this.m_FillComp.getBackground());
/* 126 */       g.fillRect(0, 0, getWidth(), getHeight());
/*     */     } else {
/*     */       
/* 129 */       super.paintBackground(g);
/* 130 */     }  g.setColor((this.m_PrefixColor != null) ? 
/* 131 */         this.m_PrefixColor : (
/* 132 */         (this.m_FillComp != null) ? 
/* 133 */         this.m_FillComp.getForeground() : 
/* 134 */         Color.BLACK));
/* 135 */     Insets inset = getInsets();
/* 136 */     g.setFont(getPrefixFont());
/* 137 */     if (this.m_PrefixString != null) {
/*     */       
/* 139 */       if (!this.firstView) {
/*     */         
/* 141 */         this.m_FillComp.requestFocus();
/* 142 */         this.firstView = true;
/*     */       } 
/* 144 */       JLbsControlHelper.drawStringVCentered((JComponent)this, g, new Rectangle(0, 0, this.m_PrefixWidth + inset.left, getHeight()), 
/* 145 */           this.m_PrefixString, 2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsMaskedEdit getFillControl() {
/* 152 */     return (ILbsMaskedEdit)getFillComponent();
/*     */   }
/*     */ 
/*     */   
/*     */   protected Dimension getFillComponentSize(Dimension dim) {
/* 157 */     if (dim != null)
/* 158 */       dim.width -= this.m_PrefixWidth; 
/* 159 */     return dim;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Point getFillComponentLocation() {
/* 164 */     Point p = super.getFillComponentLocation();
/* 165 */     p.x += this.m_PrefixWidth;
/* 166 */     return p;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getPrefixStringSize() {
/* 171 */     Dimension dim = JLbsControlHelper.measureStringCorrected(this.m_PrefixString, getGraphics(), getPrefixFont());
/* 172 */     return (dim != null) ? (
/* 173 */       dim.width + this.m_PrefixOfset - 2) : 
/* 174 */       0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Font getPrefixFont() {
/* 179 */     return (this.m_FillComp != null) ? 
/* 180 */       this.m_FillComp.getFont() : 
/* 181 */       getFont();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updatePrefixWidth() {
/* 186 */     this.m_PrefixWidth = getPrefixStringSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 191 */     if (this.m_FillPrefSize != null) {
/*     */       
/* 193 */       Dimension dim = new Dimension(this.m_FillPrefSize);
/* 194 */       dim.width += getPrefixStringSize();
/* 195 */       if (this.m_Buttons.size() > 0 && this.m_iTotalButtonWidth == 0)
/* 196 */         calcTotalButtonWidth(); 
/* 197 */       dim.width += this.m_iTotalButtonWidth;
/* 198 */       dim.height = 22;
/* 199 */       return dim;
/*     */     } 
/* 201 */     return super.getPreferredSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void focusGained(FocusEvent e) {
/* 206 */     updatePrefixWidth();
/* 207 */     if (e.getSource() == this.m_FillComp) {
/* 208 */       resetBkColor(true, true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void focusLost(FocusEvent e) {
/* 213 */     if (e.getSource() == this.m_FillComp) {
/* 214 */       resetBkColor(false, true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void grabFocus() {
/* 219 */     if (this.m_FillComp != null) {
/* 220 */       this.m_FillComp.grabFocus();
/*     */     } else {
/* 222 */       super.grabFocus();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Color getPrefixColor() {
/* 227 */     return this.m_PrefixColor;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrefixColor(Color color) {
/* 232 */     this.m_PrefixColor = color;
/* 233 */     repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPrefixOfset() {
/* 238 */     return this.m_PrefixOfset;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrefixOfset(int i) {
/* 243 */     this.m_PrefixOfset = i;
/* 244 */     repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFillPreferredSize(Dimension d) {
/* 249 */     this.m_FillPrefSize = d;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\buttonpanel\JLbsPrefixPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */