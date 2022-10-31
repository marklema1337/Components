/*     */ package com.lbs.control.interfaces;
/*     */ 
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import java.awt.Color;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.dnd.DropTarget;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.TransferHandler;
/*     */ import javax.swing.border.Border;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface ILbsComponent
/*     */   extends ILbsComponentBase
/*     */ {
/*     */   String getName();
/*     */   
/*     */   void setEnabled(boolean paramBoolean);
/*     */   
/*     */   boolean isEnabled();
/*     */   
/*     */   void setVisible(boolean paramBoolean);
/*     */   
/*     */   boolean isVisible();
/*     */   
/*     */   boolean hasFocus();
/*     */   
/*     */   void grabFocus();
/*     */   
/*     */   void requestFocus();
/*     */   
/*     */   boolean requestFocus(boolean paramBoolean);
/*     */   
/*     */   void setFocusable(boolean paramBoolean);
/*     */   
/*     */   boolean isFocusable();
/*     */   
/*     */   boolean isFocusOwner();
/*     */   
/*     */   Point getLocation();
/*     */   
/*     */   void setLocation(int paramInt1, int paramInt2);
/*     */   
/*     */   void setLocation(Point paramPoint);
/*     */   
/*     */   void setBackground(Color paramColor);
/*     */   
/*     */   Color getBackground();
/*     */   
/*     */   void setForeground(Color paramColor);
/*     */   
/*     */   Color getForeground();
/*     */   
/*     */   int getHeight();
/*     */   
/*     */   int getWidth();
/*     */   
/*     */   void addKeyListener(KeyListener paramKeyListener);
/*     */   
/*     */   void removeKeyListener(KeyListener paramKeyListener);
/*     */   
/*     */   void addFocusListener(FocusListener paramFocusListener);
/*     */   
/*     */   void removeFocusListener(FocusListener paramFocusListener);
/*     */   
/*     */   void addMouseListener(MouseListener paramMouseListener);
/*     */   
/*     */   void removeMouseListener(MouseListener paramMouseListener);
/*     */   
/*     */   Font getFont();
/*     */   
/*     */   void setFont(Font paramFont);
/*     */   
/*     */   Dimension getSize();
/*     */   
/*     */   void setSize(Dimension paramDimension);
/*     */   
/*     */   void setSize(int paramInt1, int paramInt2);
/*     */   
/*     */   Dimension getMaximumSize();
/*     */   
/*     */   void setMaximumSize(Dimension paramDimension);
/*     */   
/*     */   void setMinimumSize(Dimension paramDimension);
/*     */   
/*     */   Dimension getMinimumSize();
/*     */   
/*     */   Insets getInsets();
/*     */   
/*     */   void revalidate();
/*     */   
/*     */   void validate();
/*     */   
/*     */   void invalidate();
/*     */   
/*     */   void repaint();
/*     */   
/*     */   Object getClientProperty(Object paramObject);
/*     */   
/*     */   void putClientProperty(Object paramObject1, Object paramObject2);
/*     */   
/*     */   void removeAll();
/*     */   
/*     */   void setToolTipText(String paramString);
/*     */   
/*     */   String getToolTipText();
/*     */   
/*     */   void setBorder(Border paramBorder);
/*     */   
/*     */   Border getBorder();
/*     */   
/*     */   void setOpaque(boolean paramBoolean);
/*     */   
/*     */   boolean isOpaque();
/*     */   
/*     */   void setPreferredSize(Dimension paramDimension);
/*     */   
/*     */   Dimension getPreferredSize();
/*     */   
/*     */   void setAutoscrolls(boolean paramBoolean);
/*     */   
/*     */   boolean getAutoscrolls();
/*     */   
/*     */   boolean isShowing();
/*     */   
/*     */   void updateUI();
/*     */   
/*     */   void setTransferHandler(TransferHandler paramTransferHandler);
/*     */   
/*     */   void setDropTarget(DropTarget paramDropTarget);
/*     */   
/*     */   Object getParentComponent();
/*     */   
/*     */   Rectangle getBounds();
/*     */   
/*     */   void setBounds(Rectangle paramRectangle);
/*     */   
/*     */   void setBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*     */   
/*     */   int getX();
/*     */   
/*     */   int getY();
/*     */   
/*     */   void applyComponentOrientation(ComponentOrientation paramComponentOrientation);
/*     */   
/*     */   ComponentOrientation getComponentOrientation();
/*     */   
/*     */   void setComponentOrientation(ComponentOrientation paramComponentOrientation);
/*     */   
/*     */   default void setAlignmentX(float alignmentX) {}
/*     */   
/*     */   default float getAlignmentX() {
/* 165 */     return 0.0F;
/*     */   } default float getAlignmentY() {
/* 167 */     return 0.0F;
/*     */   }
/*     */   default MouseListener[] getMouseListeners() {
/* 170 */     return new MouseListener[0];
/*     */   }
/*     */   
/*     */   default void setAlignmentY(float alignmentY) {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\ILbsComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */