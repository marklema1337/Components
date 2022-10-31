/*     */ package com.lbs.laf.common;
/*     */ 
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.plaf.basic.BasicScrollBarUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SkinnedScrollBarUI
/*     */   extends BasicScrollBarUI
/*     */ {
/*     */   protected boolean m_IsRollover = false;
/*     */   protected boolean m_WasRollover = false;
/*     */   protected boolean m_FreeStanding = false;
/*     */   protected int m_ScrollBarWidth;
/*  33 */   protected SkinnedButtonIndexModel m_skinThumbIndexModel = new SkinnedButtonIndexModel();
/*     */ 
/*     */   
/*     */   JButton m_DecreaseButton;
/*     */   
/*     */   JButton m_IncreaseButton;
/*     */ 
/*     */   
/*     */   protected void setThumbBounds(int x, int y, int width, int height) {
/*  42 */     if (height < 16)
/*  43 */       height = 16; 
/*  44 */     super.setThumbBounds(x, y, width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/*  49 */     this.m_ScrollBarWidth = getButtonImage(0).getHsize();
/*  50 */     super.installDefaults();
/*  51 */     this.scrollbar.setBorder((Border)null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected JButton createDecreaseButton(int orientation) {
/*  56 */     this.m_DecreaseButton = new SkinnedScrollButton(getButtonImage(orientation), orientation, this.m_ScrollBarWidth, this.m_FreeStanding);
/*  57 */     return this.m_DecreaseButton;
/*     */   }
/*     */ 
/*     */   
/*     */   protected JButton createIncreaseButton(int orientation) {
/*  62 */     this.m_IncreaseButton = new SkinnedScrollButton(getButtonImage(orientation), orientation, this.m_ScrollBarWidth, this.m_FreeStanding);
/*  63 */     return this.m_IncreaseButton;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(JComponent c) {
/*  68 */     if (this.scrollbar.getOrientation() == 1)
/*     */     {
/*  70 */       return new Dimension(this.m_ScrollBarWidth, this.m_ScrollBarWidth * 3 + 10);
/*     */     }
/*     */ 
/*     */     
/*  74 */     return new Dimension(this.m_ScrollBarWidth * 3 + 10, this.m_ScrollBarWidth);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/*  80 */     Rectangle trackBounds = getTrackBounds();
/*  81 */     getSkinTrack().draw(g, 0, trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
/*  82 */     Rectangle thumbBounds = getThumbBounds();
/*  83 */     int index = this.m_skinThumbIndexModel.getIndexForState(c.isEnabled(), this.m_IsRollover, this.isDragging);
/*  84 */     getSkinThumb().draw(g, index, thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
/*  85 */     SkinImage gripper = getSkinGripper();
/*  86 */     if (gripper != null) {
/*  87 */       gripper.drawCentered(g, index, thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isThumbVisible() {
/*  93 */     if (this.scrollbar.getOrientation() == 1) {
/*     */       
/*  95 */       if ((getThumbBounds()).height == 0) {
/*  96 */         return false;
/*     */       }
/*  98 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 102 */     if ((getThumbBounds()).width == 0) {
/* 103 */       return false;
/*     */     }
/* 105 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BasicScrollBarUI.TrackListener createTrackListener() {
/* 112 */     return new ButtonTrackListener();
/*     */   }
/*     */   
/*     */   protected class ButtonTrackListener
/*     */     extends BasicScrollBarUI.TrackListener
/*     */   {
/*     */     public void mouseReleased(MouseEvent e) {
/* 119 */       super.mouseReleased(e);
/* 120 */       SkinnedScrollBarUI.this.scrollbar.repaint();
/*     */     }
/*     */ 
/*     */     
/*     */     public void mousePressed(MouseEvent e) {
/* 125 */       super.mousePressed(e);
/* 126 */       SkinnedScrollBarUI.this.scrollbar.repaint();
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseEntered(MouseEvent e) {
/* 131 */       SkinnedScrollBarUI.this.m_IsRollover = false;
/* 132 */       SkinnedScrollBarUI.this.m_WasRollover = false;
/* 133 */       if (SkinnedScrollBarUI.this.getThumbBounds().contains(e.getX(), e.getY()))
/*     */       {
/* 135 */         SkinnedScrollBarUI.this.m_IsRollover = true;
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseExited(MouseEvent e) {
/* 141 */       SkinnedScrollBarUI.this.m_IsRollover = false;
/* 142 */       if (SkinnedScrollBarUI.this.m_IsRollover != SkinnedScrollBarUI.this.m_WasRollover) {
/*     */         
/* 144 */         SkinnedScrollBarUI.this.scrollbar.repaint();
/* 145 */         SkinnedScrollBarUI.this.m_WasRollover = SkinnedScrollBarUI.this.m_IsRollover;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseDragged(MouseEvent e) {
/* 151 */       if (SkinnedScrollBarUI.this.getThumbBounds().contains(e.getX(), e.getY()))
/*     */       {
/* 153 */         SkinnedScrollBarUI.this.m_IsRollover = true;
/*     */       }
/* 155 */       super.mouseDragged(e);
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseMoved(MouseEvent e) {
/* 160 */       if (SkinnedScrollBarUI.this.getThumbBounds().contains(e.getX(), e.getY())) {
/*     */         
/* 162 */         SkinnedScrollBarUI.this.m_IsRollover = true;
/* 163 */         if (SkinnedScrollBarUI.this.m_IsRollover != SkinnedScrollBarUI.this.m_WasRollover)
/*     */         {
/* 165 */           SkinnedScrollBarUI.this.scrollbar.repaint();
/* 166 */           SkinnedScrollBarUI.this.m_WasRollover = SkinnedScrollBarUI.this.m_IsRollover;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 171 */         SkinnedScrollBarUI.this.m_IsRollover = false;
/* 172 */         if (SkinnedScrollBarUI.this.m_IsRollover != SkinnedScrollBarUI.this.m_WasRollover) {
/*     */           
/* 174 */           SkinnedScrollBarUI.this.scrollbar.repaint();
/* 175 */           SkinnedScrollBarUI.this.m_WasRollover = SkinnedScrollBarUI.this.m_IsRollover;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public SkinImage getSkinTrack() {
/* 183 */     return getSkinTrack((this.scrollbar.getOrientation() == 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public SkinImage getSkinThumb() {
/* 188 */     return getSkinThumb((this.scrollbar.getOrientation() == 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public SkinImage getSkinGripper() {
/* 193 */     return getSkinGripper((this.scrollbar.getOrientation() == 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SkinImage getButtonImage(int orientation) {
/* 199 */     switch (orientation) {
/*     */       
/*     */       case 1:
/* 202 */         filename = "up";
/*     */         break;
/*     */       case 5:
/* 205 */         filename = "down";
/*     */         break;
/*     */       case 3:
/* 208 */         filename = "right";
/*     */         break;
/*     */       
/*     */       default:
/* 212 */         filename = "left";
/*     */         break;
/*     */     } 
/* 215 */     String filename = "scrollbutton" + filename + (JLbsConstants.DESKTOP_MODE ? 
/* 216 */       "desktop" : 
/* 217 */       "") + ".png";
/* 218 */     return DefaultSkinnableTheme.getSkinImage(getClass(), filename, 4, 0);
/*     */   }
/*     */   
/*     */   protected abstract SkinImage getSkinTrack(boolean paramBoolean);
/*     */   
/*     */   protected abstract SkinImage getSkinThumb(boolean paramBoolean);
/*     */   
/*     */   protected abstract SkinImage getSkinGripper(boolean paramBoolean);
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinnedScrollBarUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */