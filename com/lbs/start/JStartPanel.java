/*     */ package com.lbs.start;
/*     */ 
/*     */ import com.lbs.controls.JLbsProgressAnim;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.util.Random;
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
/*     */ class JStartPanel
/*     */   extends JPanel
/*     */   implements Runnable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/* 783 */   private static Color[] colors = new Color[] { new Color(220, 220, 220) };
/* 784 */   private final Font m_LargeFont = new Font("Tahoma", 1, 16);
/* 785 */   private final Font m_NormalFont = new Font("Verdana", 0, 14);
/*     */   
/*     */   private final Ellipse2D.Float[] m_Ellipses;
/*     */   private final double[] m_ESize;
/*     */   private final float[] m_EStroke;
/*     */   private final Random rand;
/*     */   private JLbsProgressAnim m_Anim;
/*     */   private Thread m_Thread;
/*     */   private String m_Title;
/*     */   private String m_Message;
/*     */   
/*     */   public JStartPanel() {
/* 797 */     this.rand = new Random();
/* 798 */     setBackground(Color.WHITE);
/* 799 */     this.m_Ellipses = new Ellipse2D.Float[16];
/* 800 */     this.m_ESize = new double[this.m_Ellipses.length];
/* 801 */     this.m_EStroke = new float[this.m_Ellipses.length];
/*     */     
/* 803 */     for (int i = 0; i < this.m_Ellipses.length; i++) {
/*     */       
/* 805 */       this.m_Ellipses[i] = new Ellipse2D.Float();
/*     */       
/* 807 */       getRandomXY(i, 20.0D * Math.random(), 800, 600);
/*     */     } 
/* 809 */     start();
/*     */   }
/*     */ 
/*     */   
/*     */   public void getRandomXY(int i, double size, int w, int h) {
/* 814 */     this.m_ESize[i] = Math.abs(this.rand.nextInt() % size);
/* 815 */     this.m_EStroke[i] = 1.0F;
/* 816 */     double x = Math.abs(this.rand.nextInt() % w);
/* 817 */     double y = Math.abs(this.rand.nextInt() % h);
/* 818 */     this.m_Ellipses[i].setFrame(x, y, size, size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset(int w, int h) {
/* 823 */     for (int i = 0; i < this.m_Ellipses.length; i++) {
/* 824 */       getRandomXY(i, (getWidth() / 10) * Math.random(), w, h);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean notIn(Ellipse2D.Float el, double size, int w, int h) {
/* 829 */     int x1 = (int)(el.x - size);
/* 830 */     int x2 = (int)(el.x + size);
/* 831 */     int y1 = (int)(el.y - size);
/* 832 */     int y2 = (int)(el.y + size);
/* 833 */     return (x1 < 0 || x2 > w || y1 < 0 || y2 > h);
/*     */   }
/*     */ 
/*     */   
/*     */   public void step(int w, int h) {
/* 838 */     for (int i = 0; i < this.m_Ellipses.length; i++) {
/*     */       
/* 840 */       this.m_EStroke[i] = this.m_EStroke[i] + 0.025F;
/* 841 */       int expand = (int)(this.m_EStroke[i] * 2.0F);
/* 842 */       this.m_ESize[i] = this.m_ESize[i] + expand;
/* 843 */       if (notIn(this.m_Ellipses[i], this.m_ESize[i], w, h)) {
/* 844 */         getRandomXY(i, 1.0D, w, h);
/*     */       } else {
/* 846 */         this.m_Ellipses[i].setFrame(this.m_Ellipses[i].getX() - (expand / 2), this.m_Ellipses[i].getY() - (expand / 2), this.m_ESize[i], this.m_ESize[i]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawDemo(int w, int h, Graphics2D g2) {
/* 854 */     for (int i = 0; i < this.m_Ellipses.length; i++) {
/*     */       
/* 856 */       g2.setColor(colors[i % colors.length]);
/* 857 */       g2.setStroke(new BasicStroke(this.m_EStroke[i]));
/* 858 */       g2.draw(this.m_Ellipses[i]);
/*     */     } 
/* 860 */     g2.setStroke(new BasicStroke(1.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/* 866 */     super.paintComponent(g);
/* 867 */     Graphics2D g2 = (Graphics2D)g;
/* 868 */     RenderingHints hints = g2.getRenderingHints();
/* 869 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 870 */     drawDemo(getWidth(), getHeight(), g2);
/* 871 */     step(getWidth(), getHeight());
/*     */     
/* 873 */     if (this.m_Title != null && this.m_Title.length() > 0) {
/*     */       
/* 875 */       g.setColor(Color.BLACK);
/* 876 */       g.setFont(this.m_LargeFont);
/* 877 */       FontMetrics fm = g.getFontMetrics(this.m_LargeFont);
/* 878 */       int sWidth = fm.stringWidth(this.m_Title);
/* 879 */       int sHeight = fm.getHeight() + fm.getMaxAscent();
/* 880 */       int x = (getWidth() - sWidth) / 2;
/* 881 */       int y = sHeight + 16;
/* 882 */       g.setColor(Color.BLACK);
/* 883 */       g.drawString(this.m_Title, --x, --y);
/*     */     } 
/* 885 */     if (this.m_Message != null && this.m_Message.length() > 0) {
/*     */       
/* 887 */       g.setFont(this.m_NormalFont);
/* 888 */       FontMetrics fm = g.getFontMetrics(this.m_NormalFont);
/* 889 */       int sWidth = fm.stringWidth(this.m_Message);
/* 890 */       int sHeight = fm.getHeight() + fm.getMaxAscent();
/* 891 */       int x = (getWidth() - sWidth) / 2;
/* 892 */       int y = (getHeight() - 30) / 2 - sHeight;
/* 893 */       if (getForeground() != Color.BLACK) {
/* 894 */         g.setColor(getForeground());
/*     */       } else {
/*     */         
/* 897 */         g.setColor(Color.GRAY);
/* 898 */         g.drawString(this.m_Message, --x, --y);
/* 899 */         g.setColor(Color.BLACK);
/*     */       } 
/* 901 */       g.drawString(this.m_Message, --x, --y);
/*     */     } 
/* 903 */     g2.setRenderingHints(hints);
/*     */   }
/*     */ 
/*     */   
/*     */   public void start() {
/* 908 */     this.m_Thread = new Thread(this);
/* 909 */     this.m_Thread.setPriority(1);
/* 910 */     this.m_Thread.start();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void stop() {
/* 915 */     this.m_Thread = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/* 920 */     Thread me = Thread.currentThread();
/* 921 */     while (this.m_Thread == me) {
/*     */       
/* 923 */       repaint();
/*     */       
/*     */       try {
/* 926 */         if (this.m_Anim != null && this.m_Anim.isVisible())
/* 927 */           this.m_Anim.doAnimate(); 
/* 928 */         Thread.sleep(40L);
/*     */       }
/* 930 */       catch (InterruptedException e) {
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 935 */     this.m_Thread = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 940 */     return this.m_Title;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTitle(String string) {
/* 945 */     this.m_Title = string;
/* 946 */     repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProgress(JLbsProgressAnim anim) {
/* 951 */     this.m_Anim = anim;
/* 952 */     add((Component)anim);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMessage() {
/* 957 */     return this.m_Message;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMessage(String string) {
/* 962 */     this.m_Message = string;
/* 963 */     repaint();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\start\JStartPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */