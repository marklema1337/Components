/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsPanel;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.LineMetrics;
/*     */ import java.io.FileNotFoundException;
/*     */ import javax.swing.JPanel;
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
/*     */ public class JLbsPanel
/*     */   extends JPanel
/*     */   implements ILbsPanel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public String getResourceIdentifier() {
/*  36 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/*  41 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/*  46 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/*  51 */     return getParent();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean bEnable) {
/*  59 */     super.setEnabled(bEnable);
/*  60 */     if (!JLbsComponentHelper.setChildrenEnabled(this, bEnable)) {
/*     */       
/*  62 */       int iCompCount = getComponentCount();
/*  63 */       for (int i = 0; i < iCompCount; i++) {
/*  64 */         getComponent(i).setEnabled(bEnable);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean selected = true;
/*     */   JPanel contentPanel_;
/*     */   HeaderPanel headerPanel_;
/*  72 */   private byte headerHeight = 20;
/*     */ 
/*     */   
/*     */   public JPanel getContentPane() {
/*  76 */     return this.contentPanel_;
/*     */   }
/*     */   
/*     */   private class HeaderPanel
/*     */     extends JPanel implements MouseListener {
/*  81 */     final byte PAD = 2;
/*     */     String text_;
/*     */     Image open;
/*     */     Image closed;
/*     */     
/*     */     public HeaderPanel(String text, ILbsPanel.IImageSupplier imageSupplier) {
/*  87 */       addMouseListener(this);
/*  88 */       this.text_ = text;
/*  89 */       setPreferredSize(new Dimension(100, JLbsPanel.this.headerHeight));
/*     */       
/*     */       try {
/*  92 */         this.open = imageSupplier.getImage("expanded.gif");
/*  93 */         this.closed = imageSupplier.getImage("collapsed.gif");
/*     */       }
/*  95 */       catch (FileNotFoundException e) {
/*     */         
/*  97 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected void paintComponent(Graphics g) {
/* 103 */       super.paintComponent(g);
/* 104 */       Graphics2D g2 = (Graphics2D)g;
/* 105 */       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 106 */       int h = 17;
/* 107 */       if (JLbsPanel.this.selected) {
/*     */         
/* 109 */         if (this.open != null) {
/* 110 */           g2.drawImage(this.open, 2, 0, h, h, this);
/*     */         
/*     */         }
/*     */       }
/* 114 */       else if (this.closed != null) {
/* 115 */         g2.drawImage(this.closed, 2, 0, h, h, this);
/*     */       } 
/* 117 */       FontRenderContext frc = g2.getFontRenderContext();
/* 118 */       LineMetrics lm = g.getFont().getLineMetrics(this.text_, frc);
/* 119 */       float height = lm.getAscent() + lm.getDescent();
/* 120 */       float x = (h + 2);
/* 121 */       float y = (h + height) / 2.0F - lm.getDescent();
/* 122 */       g2.drawString(this.text_, x, y);
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseClicked(MouseEvent e) {
/* 127 */       JLbsPanel.this.toggleSelection();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseEntered(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseExited(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void mousePressed(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseReleased(MouseEvent e) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSize(int width, int height) {
/* 151 */     if (this.contentPanel_ != null) {
/* 152 */       super.setSize(width, height + (this.selected ? 
/* 153 */           this.headerHeight : 
/* 154 */           0));
/*     */     } else {
/* 156 */       super.setSize(width, height);
/*     */     } 
/*     */   }
/* 159 */   private int lastHeight = 0;
/*     */ 
/*     */   
/*     */   public void toggleSelection() {
/* 163 */     Dimension preferredSize = getPreferredSize();
/* 164 */     if (this.selected) {
/*     */       
/* 166 */       this.lastHeight = preferredSize.height;
/* 167 */       this.contentPanel_.setVisible(false);
/* 168 */       Dimension size = preferredSize;
/* 169 */       size.height = this.headerHeight;
/* 170 */       setSize(size);
/* 171 */       setPreferredSize(size);
/*     */     }
/*     */     else {
/*     */       
/* 175 */       Dimension size = preferredSize;
/* 176 */       if (size.height > 0) {
/*     */         
/* 178 */         size.height = this.lastHeight;
/* 179 */         setSize(size);
/* 180 */         setPreferredSize(size);
/*     */       } 
/* 182 */       this.contentPanel_.setVisible(true);
/*     */     } 
/* 184 */     this.selected = !this.selected;
/* 185 */     invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeader(String header, ILbsPanel.IImageSupplier imageSupplier) {
/* 190 */     setLayout(new BorderLayout(0, 0));
/*     */     
/* 192 */     this.selected = false;
/* 193 */     this.headerPanel_ = new HeaderPanel(header, imageSupplier);
/*     */     
/* 195 */     this.contentPanel_ = new JPanel();
/*     */     
/* 197 */     add(this.headerPanel_, "North");
/* 198 */     add(this.contentPanel_, "Center");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCollapsed() {
/* 203 */     return this.selected;
/*     */   }
/*     */ 
/*     */   
/*     */   public void collapse() {
/* 208 */     toggleSelection();
/*     */   }
/*     */ 
/*     */   
/*     */   public void expand() {
/* 213 */     toggleSelection();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addImpl(Component comp, Object constraints, int index) {
/* 219 */     if (this.contentPanel_ != null && comp != this.contentPanel_ && comp != this.headerPanel_) {
/*     */       
/* 221 */       this.contentPanel_.add(comp, constraints, index);
/*     */     }
/*     */     else {
/*     */       
/* 225 */       super.addImpl(comp, constraints, index);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */