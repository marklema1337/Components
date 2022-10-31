/*     */ package com.lbs.controls.misc;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsViewport;
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import java.awt.Component;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Point;
/*     */ import javax.swing.JViewport;
/*     */ import javax.swing.Scrollable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsViewport
/*     */   extends JViewport
/*     */   implements ILbsViewport
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Dimension lastLayoutSize;
/*     */   private boolean horizontallyFixed;
/*     */   private boolean verticallyFixed;
/*     */   private transient boolean removeFixRecursion;
/*     */   private transient boolean dontFire;
/*     */   
/*     */   public JLbsViewport() {
/*  83 */     this((Component)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsViewport(Component view) {
/*  88 */     setView(view);
/*  89 */     this.lastLayoutSize = getSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAll() {
/*  99 */     for (int i = getComponentCount() - 1; i >= 0; i--) {
/* 100 */       remove(getComponent(i));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(int index) {
/* 106 */     if (this.removeFixRecursion) {
/*     */       
/* 108 */       super.remove(index);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 113 */     this.removeFixRecursion = true;
/*     */ 
/*     */     
/*     */     try {
/* 117 */       remove(getComponent(index));
/*     */     }
/*     */     finally {
/*     */       
/* 121 */       this.removeFixRecursion = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void reshape(int x, int y, int w, int h) {
/* 128 */     this.dontFire = true;
/*     */ 
/*     */     
/*     */     try {
/* 132 */       super.reshape(x, y, w, h);
/*     */     }
/*     */     finally {
/*     */       
/* 136 */       this.dontFire = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComponentOrientation(ComponentOrientation value) {
/* 145 */     ComponentOrientation old = getComponentOrientation();
/*     */     
/* 147 */     int i = old.isLeftToRight() ^ value.isLeftToRight();
/*     */     
/* 149 */     if (i != 0) {
/*     */       
/* 151 */       if (getView() != null) {
/*     */         
/* 153 */         Dimension d = getView().getSize();
/*     */         
/* 155 */         int oldMaxX = maxX(d);
/*     */         
/* 157 */         Point p = getViewPosition();
/*     */         
/* 159 */         super.setComponentOrientation(value);
/*     */         
/* 161 */         Point q = getViewPosition();
/*     */         
/* 163 */         if (q.equals(p)) {
/*     */           
/* 165 */           q.x = oldMaxX - q.x + minX(d);
/*     */           
/* 167 */           setViewPosition(q);
/*     */           
/* 169 */           this.scrollUnderway = false;
/*     */         } 
/*     */         
/* 172 */         repaint();
/*     */       }
/*     */       else {
/*     */         
/* 176 */         super.setComponentOrientation(value);
/*     */         
/* 178 */         if (this.lastLayoutSize.width > 0) {
/* 179 */           fireStateChanged();
/*     */         }
/*     */       } 
/*     */     } else {
/* 183 */       super.setComponentOrientation(value);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setView(Component value) {
/* 189 */     if (getView() == value)
/*     */       return; 
/* 191 */     if (value != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 200 */       value.setBounds(getComponentOrientation().isLeftToRight() ? 
/* 201 */           0 : 
/* 202 */           this.lastLayoutSize.width, 0, 0, 0);
/*     */       
/* 204 */       this.scrollUnderway = false;
/*     */     } 
/*     */     
/* 207 */     super.setView(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isHorizontallyFixed() {
/* 215 */     return this.horizontallyFixed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHorizontallyFixed(boolean value) {
/* 220 */     if (this.horizontallyFixed == value) {
/*     */       return;
/*     */     }
/* 223 */     this.horizontallyFixed = value;
/*     */     
/* 225 */     revalidate();
/* 226 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isVerticallyFixed() {
/* 233 */     return this.verticallyFixed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVerticallyFixed(boolean value) {
/* 238 */     if (this.verticallyFixed == value) {
/*     */       return;
/*     */     }
/* 241 */     this.verticallyFixed = value;
/*     */     
/* 243 */     revalidate();
/* 244 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected final LayoutManager createLayoutManager() {
/* 250 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final JViewport.ViewListener createViewListener() {
/* 257 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final Dimension toViewCoordinates(Dimension size) {
/* 263 */     return new Dimension(size);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final Point toViewCoordinates(Point p) {
/* 269 */     return new Point(p);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final Dimension getExtentSize() {
/* 275 */     return getSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setExtentSize(Dimension value) {
/* 282 */     setSize(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getViewSize() {
/* 289 */     Dimension result = super.getViewSize();
/*     */     
/* 291 */     if (!this.isViewSizeSet) {
/* 292 */       result = new Dimension(result);
/*     */     }
/* 294 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Point getViewPosition() {
/* 300 */     if (getView() == null) {  } else {  }  return 
/*     */ 
/*     */ 
/*     */       
/* 304 */       super.getViewPosition();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Component getView() {
/* 310 */     if (getComponents() != null && (getComponents()).length != 0)
/* 311 */       return super.getView(); 
/* 312 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setViewPosition(Point p) {
/* 320 */     if (getView() == null) {
/*     */       
/* 322 */       super.setViewPosition(p);
/*     */     }
/*     */     else {
/*     */       
/* 326 */       Dimension d = getView().getSize();
/*     */       
/* 328 */       Point q = new Point(bounded(minX(d), p.x, maxX(d)), bounded(minY(d), p.y, maxY(d)));
/*     */       
/* 330 */       super.setViewPosition(q);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize() {
/* 337 */     if (isMinimumSizeSet()) {
/* 338 */       return super.getMinimumSize();
/*     */     }
/* 340 */     Component v = getView();
/*     */ 
/*     */ 
/*     */     
/* 344 */     if (v instanceof ILbsScrollable) {
/*     */       
/* 346 */       Dimension result = ((ILbsScrollable)v).getMinimumScrollableViewportSize();
/*     */       
/* 348 */       if (this.horizontallyFixed || this.verticallyFixed) {
/*     */         
/* 350 */         Dimension d = new Dimension(v.getMinimumSize());
/*     */         
/* 352 */         if (this.horizontallyFixed)
/* 353 */           result.width = d.width; 
/* 354 */         if (this.verticallyFixed) {
/* 355 */           result.height = d.height;
/*     */         }
/*     */       } 
/* 358 */       return result;
/*     */     } 
/*     */     
/* 361 */     return super.getMinimumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/*     */     Dimension result;
/* 367 */     if (isPreferredSizeSet()) {
/* 368 */       return super.getPreferredSize();
/*     */     }
/*     */ 
/*     */     
/* 372 */     Component v = getView();
/*     */     
/* 374 */     if (v instanceof Scrollable) {
/*     */       
/* 376 */       result = new Dimension(((Scrollable)v).getPreferredScrollableViewportSize());
/*     */       
/* 378 */       if (this.horizontallyFixed || this.verticallyFixed) {
/*     */         
/* 380 */         Dimension d = v.getPreferredSize();
/* 381 */         if (this.horizontallyFixed)
/* 382 */           result.width = d.width; 
/* 383 */         if (this.verticallyFixed) {
/* 384 */           result.height = d.height;
/*     */         }
/*     */       } 
/*     */     } else {
/* 388 */       result = v.getPreferredSize();
/*     */     } 
/* 390 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize() {
/*     */     Dimension result;
/* 396 */     if (isMaximumSizeSet()) {
/* 397 */       return super.getMaximumSize();
/*     */     }
/*     */ 
/*     */     
/* 401 */     Component v = getView();
/*     */     
/* 403 */     if (v instanceof ILbsScrollable) {
/*     */       
/* 405 */       result = ((ILbsScrollable)v).getMaximumScrollableViewportSize();
/*     */       
/* 407 */       if (this.horizontallyFixed || this.verticallyFixed) {
/*     */         
/* 409 */         Dimension d = v.getMaximumSize();
/*     */         
/* 411 */         if (this.horizontallyFixed)
/* 412 */           result.width = d.width; 
/* 413 */         if (this.verticallyFixed) {
/* 414 */           result.height = d.height;
/*     */         }
/*     */       } 
/*     */     } else {
/* 418 */       result = super.getMaximumSize();
/*     */     } 
/* 420 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doLayout() {
/* 426 */     super.doLayout();
/*     */     
/* 428 */     Component v = getView();
/*     */     
/* 430 */     if (v == null) {
/*     */       
/* 432 */       if (!getComponentOrientation().isLeftToRight() && getWidth() != this.lastLayoutSize.width) {
/*     */         
/* 434 */         this.lastLayoutSize = getSize();
/* 435 */         fireStateChanged();
/*     */       } else {
/*     */         
/* 438 */         this.lastLayoutSize = getSize();
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 443 */     Scrollable s = (v instanceof Scrollable) ? 
/* 444 */       (Scrollable)v : 
/* 445 */       null;
/*     */     
/* 447 */     Dimension size = getSize();
/* 448 */     Point p = getViewPosition();
/*     */     
/* 450 */     Point old = new Point(p);
/*     */     
/* 452 */     if (!getComponentOrientation().isLeftToRight()) {
/* 453 */       p.x += this.lastLayoutSize.width - size.width;
/*     */     }
/* 455 */     boolean sizeChanged = !this.lastLayoutSize.equals(size);
/*     */     
/* 457 */     this.lastLayoutSize = size;
/*     */     
/* 459 */     Dimension viewSize = new Dimension(v.getPreferredSize());
/*     */     
/* 461 */     if (s != null) {
/*     */       
/* 463 */       if (s.getScrollableTracksViewportWidth()) {
/* 464 */         viewSize.width = size.width;
/*     */       }
/* 466 */       if (s.getScrollableTracksViewportHeight()) {
/* 467 */         viewSize.height = size.height;
/*     */       }
/*     */     } else {
/*     */       
/* 471 */       Dimension d = v.getMaximumSize();
/*     */       
/* 473 */       viewSize.width = Math.min(d.width, Math.max(viewSize.width, size.width));
/* 474 */       viewSize.height = Math.min(d.height, Math.max(viewSize.height, size.height));
/*     */     } 
/*     */     
/* 477 */     if (this.horizontallyFixed) {
/* 478 */       viewSize.width = size.width;
/*     */     }
/* 480 */     if (this.verticallyFixed) {
/* 481 */       viewSize.height = size.height;
/*     */     }
/* 483 */     Dimension oldViewSize = v.getSize();
/*     */     
/* 485 */     if (!getComponentOrientation().isLeftToRight()) {
/* 486 */       p.x += viewSize.width - oldViewSize.width;
/*     */     }
/* 488 */     if (!oldViewSize.equals(viewSize) || !old.equals(p) || sizeChanged) {
/*     */ 
/*     */       
/* 491 */       this.dontFire = true;
/*     */ 
/*     */       
/*     */       try {
/* 495 */         setViewPosition(p);
/* 496 */         setViewSize(viewSize);
/*     */       
/*     */       }
/*     */       finally {
/*     */         
/* 501 */         this.dontFire = false;
/* 502 */         fireStateChanged();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fireStateChanged() {
/* 509 */     if (!this.dontFire) {
/* 510 */       super.fireStateChanged();
/*     */     }
/*     */   }
/*     */   
/*     */   private int minX(Dimension viewSize) {
/* 515 */     return getComponentOrientation().isLeftToRight() ? 
/* 516 */       0 : 
/* 517 */       Math.min(0, viewSize.width - this.lastLayoutSize.width);
/*     */   }
/*     */ 
/*     */   
/*     */   private int maxX(Dimension viewSize) {
/* 522 */     int d = viewSize.width - this.lastLayoutSize.width;
/*     */     
/* 524 */     return getComponentOrientation().isLeftToRight() ? 
/* 525 */       Math.max(0, d) : 
/* 526 */       d;
/*     */   }
/*     */ 
/*     */   
/*     */   private int minY(Dimension viewSize) {
/* 531 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private int maxY(Dimension viewSize) {
/* 536 */     return Math.max(0, viewSize.height - this.lastLayoutSize.height);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int bounded(int min, int value, int max) {
/* 542 */     return Math.max(min, Math.min(value, max));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 547 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 552 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 557 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 562 */     return getParent();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\misc\JLbsViewport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */