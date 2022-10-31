/*     */ package com.lbs.image;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JComponent;
/*     */ 
/*     */ public class JLbsImagePreview
/*     */   extends JComponent
/*     */   implements KeyListener, MouseListener {
/*     */   private static final long serialVersionUID = 1L;
/*  20 */   private static double[] ms_ZoomFactors = new double[] { 0.1D, 0.2D, 0.5D, 0.75D, 1.0D, 1.5D, 2.0D, 3.0D, 4.0D };
/*     */   private Image m_Image;
/*     */   private int m_ImgWidth;
/*     */   private int m_ImgHeight;
/*  24 */   private double m_ZoomFactor = 1.0D;
/*     */ 
/*     */   
/*     */   public JLbsImagePreview() {
/*  28 */     addKeyListener(this);
/*  29 */     addMouseListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsImagePreview(ImageIcon imageIcon) {
/*  34 */     this();
/*  35 */     if (imageIcon != null) {
/*  36 */       this.m_Image = imageIcon.getImage();
/*     */     }
/*     */   }
/*     */   
/*     */   public JLbsImagePreview(Image image) {
/*  41 */     this();
/*  42 */     this.m_Image = image;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusable() {
/*  47 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/*  52 */     super.paintComponent(g);
/*  53 */     if (this.m_Image != null) {
/*     */       
/*  55 */       Rectangle rect = getClientRectInsideBorder(this);
/*  56 */       g.drawImage(this.m_Image, rect.x, rect.y, rect.x + rect.width, rect.x + rect.height, 0, 0, this.m_Image.getWidth(null), 
/*  57 */           this.m_Image.getHeight(null), getBackground(), null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Rectangle getClientRectInsideBorder(JComponent c) {
/*  63 */     if (c == null)
/*  64 */       return new Rectangle(); 
/*  65 */     Dimension size = c.getSize();
/*  66 */     Insets inset = c.getInsets();
/*  67 */     if (inset != null)
/*  68 */       return new Rectangle(inset.left, inset.top, size.width - inset.left - inset.right, size.height - inset.top - 
/*  69 */           inset.bottom); 
/*  70 */     return new Rectangle(0, 0, size.width, size.height);
/*     */   }
/*     */ 
/*     */   
/*     */   public Image getImage() {
/*  75 */     return this.m_Image;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImage(Image image) {
/*  80 */     if (this.m_Image != image) {
/*     */       
/*  82 */       this.m_Image = image;
/*  83 */       if (image != null) {
/*     */         
/*  85 */         this.m_ImgWidth = image.getWidth(null);
/*  86 */         this.m_ImgHeight = image.getHeight(null);
/*     */       }
/*     */       else {
/*     */         
/*  90 */         this.m_ImgWidth = 100;
/*  91 */         this.m_ImgHeight = 100;
/*     */       } 
/*  93 */       Dimension size = new Dimension(this.m_ImgWidth, this.m_ImgHeight);
/*  94 */       setPreferredSize(size);
/*  95 */       setSize(size);
/*  96 */       revalidate();
/*  97 */       repaint();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void keyPressed(KeyEvent e) {
/* 103 */     switch (e.getKeyChar()) {
/*     */       
/*     */       case '+':
/* 106 */         setZoomFactor(getNextZoomFactor());
/* 107 */         System.out.println("zoom in");
/*     */         break;
/*     */       case '-':
/* 110 */         setZoomFactor(getPrevZoomFactor());
/* 111 */         System.out.println("zoom out");
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double getZoomFactor() {
/* 118 */     return this.m_ZoomFactor;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setZoomFactor(double d) {
/* 123 */     d = Math.max(0.1D, Math.min(4.0D, d));
/* 124 */     if (this.m_ZoomFactor != d) {
/*     */       
/* 126 */       this.m_ZoomFactor = d;
/* 127 */       int newW = (int)(this.m_ImgWidth * d);
/* 128 */       int newH = (int)(this.m_ImgHeight * d);
/* 129 */       Dimension dim = new Dimension(newW, newH);
/* 130 */       setPreferredSize(dim);
/* 131 */       setSize(dim);
/* 132 */       revalidate();
/* 133 */       Container c = getParent();
/* 134 */       if (c != null) {
/* 135 */         ((JComponent)c).revalidate();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getNextZoomFactor() {
/* 141 */     double d = this.m_ZoomFactor;
/* 142 */     for (int i = 0; i < ms_ZoomFactors.length; i++) {
/* 143 */       if (d < ms_ZoomFactors[i])
/* 144 */         return ms_ZoomFactors[i]; 
/* 145 */     }  return ms_ZoomFactors[ms_ZoomFactors.length - 1];
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPrevZoomFactor() {
/* 150 */     double d = this.m_ZoomFactor;
/* 151 */     for (int i = ms_ZoomFactors.length - 1; i >= 0; i--) {
/* 152 */       if (d > ms_ZoomFactors[i])
/* 153 */         return ms_ZoomFactors[i]; 
/* 154 */     }  return ms_ZoomFactors[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void keyReleased(KeyEvent e) {}
/*     */ 
/*     */   
/*     */   public void keyTyped(KeyEvent e) {}
/*     */ 
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {
/* 165 */     if (e.getClickCount() == 1)
/*     */     {
/* 167 */       if (e.isControlDown()) {
/* 168 */         setZoomFactor(getNextZoomFactor());
/* 169 */       } else if (e.isShiftDown()) {
/* 170 */         setZoomFactor(getPrevZoomFactor());
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */   
/*     */   public void mouseExited(MouseEvent e) {}
/*     */   
/*     */   public void mousePressed(MouseEvent e) {
/* 181 */     if (!hasFocus())
/* 182 */       requestFocus(); 
/*     */   }
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\image\JLbsImagePreview.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */