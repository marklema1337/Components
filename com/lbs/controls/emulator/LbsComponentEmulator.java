/*     */ package com.lbs.controls.emulator;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsContainer;
/*     */ import com.lbs.control.interfaces.ILbsInputVerifier;
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import java.awt.AWTEventMulticaster;
/*     */ import java.awt.Color;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.dnd.DropTarget;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.util.Hashtable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsComponentEmulator
/*     */   implements ILbsContainer, ILbsComponentEmulator
/*     */ {
/*     */   protected LbsFocusManager m_FocusManager;
/*     */   private FocusListener m_FocusListener;
/*     */   private KeyListener m_KeyListener;
/*     */   private MouseListener m_MouseListener;
/*     */   private ILbsInputVerifier m_Verifier;
/*  47 */   private Hashtable m_ClientProperties = new Hashtable<>();
/*     */   
/*     */   private LbsComponentEmulator m_Parent;
/*  50 */   LbsComponentEmulator[] m_Children = new LbsComponentEmulator[0];
/*  51 */   int m_ChildCount = 0;
/*     */   
/*     */   private boolean m_Visible = true;
/*     */   private boolean m_Enabled = true;
/*     */   private boolean m_Focusable = true;
/*  56 */   private Dimension m_Size = new Dimension(10, 10);
/*  57 */   private Dimension m_MaxSize = new Dimension(10, 10);
/*  58 */   private Dimension m_MinSize = new Dimension(10, 10);
/*  59 */   private Dimension m_PrefSize = new Dimension(10, 10);
/*  60 */   private Point m_Point = new Point(0, 0);
/*  61 */   private Insets m_Insets = new Insets(0, 0, 10, 10);
/*  62 */   private Rectangle m_Rectangle = new Rectangle(0, 0, 10, 10);
/*     */   
/*  64 */   protected static final Dimension SIZE = new Dimension(10, 10);
/*  65 */   protected static final Point POINT = new Point(0, 0);
/*  66 */   protected static final Insets INSETS = new Insets(0, 0, 10, 10);
/*  67 */   protected static final Rectangle RECTANGLE = new Rectangle(0, 0, 10, 10);
/*  68 */   protected static final Font FONT = new Font(null, 10, 0);
/*     */ 
/*     */   
/*     */   public void setFocusManager(LbsFocusManager focusManager) {
/*  72 */     this.m_FocusManager = focusManager;
/*  73 */     if (this.m_Children != null)
/*  74 */       for (int i = 0; i < this.m_Children.length; i++) {
/*     */         
/*  76 */         if (this.m_Children[i] != null) {
/*  77 */           this.m_Children[i].setFocusManager(focusManager);
/*     */         }
/*     */       }  
/*     */   }
/*     */   
/*     */   public LbsFocusManager getFocusManager() {
/*  83 */     return this.m_FocusManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addFocusListener(FocusListener listener) {
/*  89 */     if (listener == null) {
/*     */       return;
/*     */     }
/*  92 */     this.m_FocusListener = AWTEventMulticaster.add(this.m_FocusListener, listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void removeFocusListener(FocusListener l) {
/*  97 */     if (l == null)
/*     */       return; 
/*  99 */     this.m_FocusListener = AWTEventMulticaster.remove(this.m_FocusListener, l);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void addKeyListener(KeyListener listener) {
/* 104 */     if (listener == null) {
/*     */       return;
/*     */     }
/* 107 */     this.m_KeyListener = AWTEventMulticaster.add(this.m_KeyListener, listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void removeKeyListener(KeyListener l) {
/* 112 */     if (l == null) {
/*     */       return;
/*     */     }
/* 115 */     this.m_KeyListener = AWTEventMulticaster.remove(this.m_KeyListener, l);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void addMouseListener(MouseListener listener) {
/* 120 */     if (listener == null) {
/*     */       return;
/*     */     }
/* 123 */     this.m_MouseListener = AWTEventMulticaster.add(this.m_MouseListener, listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void removeMouseListener(MouseListener l) {
/* 128 */     if (l == null) {
/*     */       return;
/*     */     }
/* 131 */     this.m_MouseListener = AWTEventMulticaster.remove(this.m_MouseListener, l);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyComponentOrientation(ComponentOrientation o) {}
/*     */ 
/*     */   
/*     */   public boolean getAutoscrolls() {
/* 140 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getBackground() {
/* 145 */     return Color.gray;
/*     */   }
/*     */ 
/*     */   
/*     */   public Border getBorder() {
/* 150 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/* 155 */     return this.m_Rectangle;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getClientProperty(Object key) {
/* 160 */     return this.m_ClientProperties.get(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public ComponentOrientation getComponentOrientation() {
/* 165 */     return ComponentOrientation.LEFT_TO_RIGHT;
/*     */   }
/*     */ 
/*     */   
/*     */   public Font getFont() {
/* 170 */     return FONT;
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getForeground() {
/* 175 */     return Color.black;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 180 */     return (this.m_Size != null) ? 
/* 181 */       this.m_Size.height : 
/* 182 */       SIZE.height;
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getInsets() {
/* 187 */     return this.m_Insets;
/*     */   }
/*     */ 
/*     */   
/*     */   public Point getLocation() {
/* 192 */     return this.m_Point;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize() {
/* 197 */     return this.m_MaxSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize() {
/* 202 */     return this.m_MinSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 207 */     return "SimulatorComponent";
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 212 */     return this.m_Parent;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 217 */     return this.m_PrefSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getSize() {
/* 222 */     return this.m_Size;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolTipText() {
/* 227 */     return "SimulatorTooltip";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 232 */     return (this.m_Size != null) ? 
/* 233 */       this.m_Size.width : 
/* 234 */       SIZE.width;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 239 */     return (this.m_Rectangle != null) ? 
/* 240 */       this.m_Rectangle.x : 
/* 241 */       RECTANGLE.x;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/* 246 */     return (this.m_Rectangle != null) ? 
/* 247 */       this.m_Rectangle.y : 
/* 248 */       RECTANGLE.y;
/*     */   }
/*     */ 
/*     */   
/*     */   public void grabFocus() {
/* 253 */     requestFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFocus() {
/* 258 */     return (this.m_FocusManager != null && this.m_FocusManager.getFocusOwner() == this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void invalidate() {}
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 267 */     return (this.m_Enabled && (this.m_Parent == null || this.m_Parent.isEnabled()));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusable() {
/* 272 */     return this.m_Focusable;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusOwner() {
/* 277 */     return hasFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaque() {
/* 282 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowing() {
/* 287 */     return (this.m_Visible && (this.m_Parent == null || this.m_Parent.isShowing()));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 292 */     return this.m_Visible;
/*     */   }
/*     */ 
/*     */   
/*     */   public void putClientProperty(Object key, Object value) {
/* 297 */     this.m_ClientProperties.put(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAll() {
/* 302 */     while (this.m_ChildCount > 0) {
/*     */       
/* 304 */       this.m_ChildCount--;
/* 305 */       this.m_Children[this.m_ChildCount] = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void repaint() {}
/*     */ 
/*     */   
/*     */   protected boolean runInputVerifier() {
/* 315 */     if (this.m_FocusManager == null) {
/* 316 */       return true;
/*     */     }
/* 318 */     ILbsComponent focusOwner = this.m_FocusManager.getFocusOwner();
/*     */     
/* 320 */     if (focusOwner == this)
/*     */     {
/* 322 */       return true;
/*     */     }
/* 324 */     if (focusOwner == null || !(focusOwner instanceof LbsComponentEmulator))
/*     */     {
/* 326 */       return true;
/*     */     }
/*     */     
/* 329 */     LbsComponentEmulator jFocusOwner = (LbsComponentEmulator)focusOwner;
/* 330 */     ILbsInputVerifier iv = jFocusOwner.getLbsInputVerifier();
/*     */     
/* 332 */     if (iv == null)
/*     */     {
/* 334 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 338 */     return iv.verify((ILbsComponent)jFocusOwner);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void requestFocus() {
/* 345 */     requestFocus(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requestFocus(boolean temporary) {
/* 350 */     if (runInputVerifier() && 
/* 351 */       isFocusable() && isVisible() && this.m_FocusManager != null) {
/*     */       
/* 353 */       this.m_FocusManager.setFocusOwner(this, temporary);
/* 354 */       return true;
/*     */     } 
/* 356 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void revalidate() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAutoscrolls(boolean autoScrolls) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBackground(Color color) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBorder(Border border) {}
/*     */ 
/*     */   
/*     */   public void setBounds(Rectangle bounds) {
/* 377 */     this.m_Rectangle = bounds;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(int x, int y, int width, int height) {
/* 382 */     this.m_Rectangle = new Rectangle(x, y, width, height);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComponentOrientation(ComponentOrientation o) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDropTarget(DropTarget dt) {}
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 395 */     this.m_Enabled = enabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFocusable(boolean focusable) {
/* 400 */     this.m_Focusable = focusable;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFont(Font font) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setForeground(Color color) {}
/*     */ 
/*     */   
/*     */   public void setLocation(int x, int y) {
/* 413 */     this.m_Point = new Point(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(Point location) {
/* 418 */     this.m_Point = location;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximumSize(Dimension size) {
/* 423 */     this.m_MaxSize = size;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMinimumSize(Dimension size) {
/* 428 */     this.m_MinSize = size;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOpaque(boolean opaque) {}
/*     */ 
/*     */   
/*     */   public void setPreferredSize(Dimension prefSize) {
/* 437 */     this.m_PrefSize = prefSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(Dimension size) {
/* 442 */     this.m_Size = size;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(int width, int height) {
/* 447 */     this.m_Size = new Dimension(width, height);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setToolTipText(String text) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTransferHandler(TransferHandler handler) {}
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 460 */     this.m_Visible = visible;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateUI() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void validate() {}
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 473 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 478 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 483 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processFocusEvent(FocusEvent e) {
/* 490 */     FocusListener listener = this.m_FocusListener;
/* 491 */     if (listener != null) {
/*     */       
/* 493 */       int id = e.getID();
/* 494 */       switch (id) {
/*     */         
/*     */         case 1004:
/* 497 */           listener.focusGained(e);
/*     */           break;
/*     */         case 1005:
/* 500 */           listener.focusLost(e);
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processKeyEvent(KeyEvent e) {
/* 508 */     KeyListener listener = this.m_KeyListener;
/* 509 */     if (listener != null) {
/*     */       
/* 511 */       int id = e.getID();
/* 512 */       switch (id) {
/*     */         
/*     */         case 400:
/* 515 */           listener.keyTyped(e);
/*     */           break;
/*     */         case 401:
/* 518 */           listener.keyPressed(e);
/*     */           break;
/*     */         case 402:
/* 521 */           listener.keyReleased(e);
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processMouseEvent(MouseEvent e) {
/* 529 */     MouseListener listener = this.m_MouseListener;
/* 530 */     if (listener != null) {
/*     */       
/* 532 */       int id = e.getID();
/* 533 */       switch (id) {
/*     */         
/*     */         case 501:
/* 536 */           listener.mousePressed(e);
/*     */           break;
/*     */         case 502:
/* 539 */           listener.mouseReleased(e);
/*     */           break;
/*     */         case 500:
/* 542 */           listener.mouseClicked(e);
/*     */           break;
/*     */         case 505:
/* 545 */           listener.mouseExited(e);
/*     */           break;
/*     */         case 504:
/* 548 */           listener.mouseEntered(e);
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void add(LbsComponentEmulator comp, int index) {
/* 556 */     if (index > this.m_ChildCount || (index < 0 && index != -1))
/*     */     {
/* 558 */       throw new IllegalArgumentException("illegal component position");
/*     */     }
/*     */     
/* 561 */     for (LbsComponentEmulator cn = this; cn != null; cn = cn.m_Parent) {
/*     */       
/* 563 */       if (cn == comp)
/*     */       {
/* 565 */         throw new IllegalArgumentException("adding container's parent to itself");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 570 */     if (comp.m_Parent != null) {
/*     */       
/* 572 */       comp.m_Parent.remove(comp);
/* 573 */       if (index > this.m_ChildCount)
/*     */       {
/* 575 */         throw new IllegalArgumentException("illegal component position");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 580 */     if (this.m_ChildCount == this.m_Children.length) {
/*     */       
/* 582 */       LbsComponentEmulator[] newcomponents = new LbsComponentEmulator[this.m_ChildCount * 2 + 1];
/* 583 */       System.arraycopy(this.m_Children, 0, newcomponents, 0, this.m_ChildCount);
/* 584 */       this.m_Children = newcomponents;
/*     */     } 
/* 586 */     if (index == -1 || index == this.m_ChildCount) {
/*     */       
/* 588 */       this.m_Children[this.m_ChildCount++] = comp;
/*     */     }
/*     */     else {
/*     */       
/* 592 */       System.arraycopy(this.m_Children, index, this.m_Children, index + 1, this.m_ChildCount - index);
/* 593 */       this.m_Children[index] = comp;
/* 594 */       this.m_ChildCount++;
/*     */     } 
/* 596 */     comp.m_Parent = this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeChild(ILbsComponent child) {
/* 601 */     if (child instanceof LbsComponentEmulator) {
/* 602 */       remove((LbsComponentEmulator)child);
/*     */     }
/*     */   }
/*     */   
/*     */   public void remove(int index) {
/* 607 */     if (index < 0 || index >= this.m_ChildCount)
/*     */     {
/* 609 */       throw new ArrayIndexOutOfBoundsException(index);
/*     */     }
/* 611 */     LbsComponentEmulator comp = this.m_Children[index];
/*     */     
/* 613 */     comp.m_Parent = null;
/* 614 */     System.arraycopy(this.m_Children, index + 1, this.m_Children, index, this.m_ChildCount - index - 1);
/* 615 */     this.m_Children[--this.m_ChildCount] = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void remove(LbsComponentEmulator comp) {
/* 620 */     if (comp.m_Parent == this) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 625 */       LbsComponentEmulator[] component = this.m_Children;
/* 626 */       for (int i = this.m_ChildCount; --i >= 0;) {
/*     */         
/* 628 */         if (component[i] == comp)
/*     */         {
/* 630 */           remove(i);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLbsInputVerifier(ILbsInputVerifier verifier) {
/* 638 */     this.m_Verifier = verifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsInputVerifier getLbsInputVerifier() {
/* 643 */     return this.m_Verifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addChild(ILbsComponent child) {
/* 648 */     add((LbsComponentEmulator)child, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getChildAt(int index) {
/* 653 */     if (index < 0 || index >= this.m_ChildCount)
/*     */     {
/* 655 */       throw new ArrayIndexOutOfBoundsException(index);
/*     */     }
/* 657 */     return this.m_Children[index];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getComponentCount() {
/* 662 */     return this.m_ChildCount;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmulating() {
/* 668 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\emulator\LbsComponentEmulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */