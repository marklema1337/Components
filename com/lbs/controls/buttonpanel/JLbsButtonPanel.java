/*     */ package com.lbs.controls.buttonpanel;
/*     */ 
/*     */ import com.lbs.controls.JLbsControlHelper;
/*     */ import com.lbs.controls.JLbsPanel;
/*     */ import com.lbs.controls.maskededit.JLbsMaskedEdit;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.awt.Component;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.swing.JComponent;
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
/*     */ 
/*     */ 
/*     */ public class JLbsButtonPanel
/*     */   extends JLbsPanel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   static final int MINBUTTONWIDTH = 6;
/*  41 */   private int m_iUpdateCount = 0;
/*     */   protected boolean m_bLeftJustify = false;
/*  43 */   protected JLbsMaskedEdit m_FillComp = null;
/*  44 */   protected ArrayList m_Buttons = new ArrayList();
/*  45 */   protected int m_iTotalButtonWidth = 0;
/*  46 */   protected ILbsButtonPanelListener m_ButtonListener = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsButtonPanel() {
/*  52 */     enableEvents(56L);
/*  53 */     this.m_bLeftJustify = !getComponentOrientation().isLeftToRight();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponentOrientation(ComponentOrientation o) {
/*  58 */     this.m_bLeftJustify = !o.isLeftToRight();
/*  59 */     super.setComponentOrientation(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/*  64 */     super.updateUI();
/*  65 */     if (this.m_Buttons != null)
/*     */     {
/*  67 */       for (int i = 0; i < this.m_Buttons.size(); i++) {
/*     */         
/*  69 */         Object button = this.m_Buttons.get(i);
/*  70 */         if (button instanceof JButtonInfoHolder) {
/*     */           
/*  72 */           ILbsButtonPanelChild child = ((JButtonInfoHolder)button).Button;
/*  73 */           if (child != null) {
/*  74 */             child.updateUI();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int addButton(ILbsButtonPanelChild button, int id) {
/*  90 */     if (this.m_Buttons.add(new JButtonInfoHolder(button, id)))
/*  91 */       return this.m_Buttons.size() - 1; 
/*  92 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFillComponent(JLbsMaskedEdit c) {
/* 102 */     if (this.m_FillComp != null)
/* 103 */       remove((Component)c); 
/* 104 */     this.m_FillComp = c;
/* 105 */     UpdateChildComponentLayout();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsMaskedEdit getFillComponent() {
/* 113 */     return this.m_FillComp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setButtonListener(ILbsButtonPanelListener buttonListener) {
/* 124 */     this.m_ButtonListener = buttonListener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void beginUpdate() {
/* 136 */     this.m_iUpdateCount++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endUpdate() {
/* 145 */     if (this.m_iUpdateCount <= 0)
/*     */       return; 
/* 147 */     this.m_iUpdateCount--;
/* 148 */     if (this.m_iUpdateCount == 0) {
/*     */       
/* 150 */       UpdateChildComponentLayout();
/* 151 */       repaint();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void doLayout() {
/* 157 */     super.doLayout();
/* 158 */     UpdateChildComponentLayout();
/*     */   }
/*     */ 
/*     */   
/*     */   public void UpdateChildComponentLayout() {
/* 163 */     if (JLbsConstants.DESKTOP_MODE) {
/*     */       
/* 165 */       Dimension preferredSize = getPreferredSize();
/* 166 */       if (preferredSize == null || (preferredSize.width == 0 && preferredSize.height == 0))
/* 167 */         setPreferredSize(new Dimension(104, 24)); 
/*     */     } 
/* 169 */     calcTotalButtonWidth();
/* 170 */     if (this.m_FillComp == null || (getComponentCount() >= 1 && getComponent(0) instanceof javax.swing.CellRendererPane))
/*     */       return; 
/* 172 */     Rectangle clientRect = JLbsControlHelper.getClientRectInsideBorder((JComponent)this);
/* 173 */     int iFillWidth = clientRect.width - this.m_iTotalButtonWidth;
/* 174 */     int iFillHeight = clientRect.height;
/*     */     
/* 176 */     Dimension newSize = new Dimension(Math.max(0, iFillWidth), iFillHeight);
/* 177 */     if (getComponentCount() < 1 || getComponent(0) != this.m_FillComp) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 182 */       removeAll();
/* 183 */       setLayout(null);
/* 184 */       add((Component)this.m_FillComp);
/*     */     } 
/* 186 */     newSize = getFillComponentSize(newSize);
/* 187 */     this.m_FillComp.setSize(newSize);
/* 188 */     JLbsMaskedEdit jLbsMaskedEdit = this.m_FillComp;
/* 189 */     jLbsMaskedEdit.setPreferredSize(newSize);
/* 190 */     jLbsMaskedEdit.setMinimumSize(newSize);
/* 191 */     jLbsMaskedEdit.setMaximumSize(newSize);
/* 192 */     this.m_FillComp.setLocation(getFillComponentLocation());
/* 193 */     repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void calcTotalButtonWidth() {
/* 198 */     this.m_iTotalButtonWidth = 0;
/* 199 */     List<?> list = Collections.synchronizedList(this.m_Buttons);
/* 200 */     Iterator<?> it = list.iterator();
/* 201 */     while (it.hasNext()) {
/*     */       
/* 203 */       JButtonInfoHolder holder = (JButtonInfoHolder)it.next();
/* 204 */       if (holder.Button != null && holder.visible) {
/*     */         
/* 206 */         int iButtonWidth = holder.Button.getWidth();
/* 207 */         if (iButtonWidth >= 6) {
/* 208 */           this.m_iTotalButtonWidth += iButtonWidth;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setBounds(int x, int y, int width, int height) {
/* 215 */     super.setBounds(x, y, width, height);
/* 216 */     UpdateChildComponentLayout();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Point getFillComponentLocation() {
/* 222 */     if (this.m_FillComp == null)
/* 223 */       return null; 
/* 224 */     Insets inset = getInsets();
/* 225 */     int xOffset = this.m_bLeftJustify ? (
/* 226 */       getWidth() - this.m_FillComp.getWidth() - inset.left - inset.right) : 
/* 227 */       inset.left;
/* 228 */     return new Point(xOffset, inset.top);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Dimension getFillComponentSize(Dimension dim) {
/* 233 */     return dim;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fireButtonDown(JButtonInfoHolder button) {
/* 238 */     if (this.m_ButtonListener != null) {
/* 239 */       this.m_ButtonListener.buttonDown(button.Button, button.Index, button.Id);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void fireButtonClick(JButtonInfoHolder button) {
/* 244 */     if (this.m_ButtonListener != null) {
/* 245 */       this.m_ButtonListener.buttonClick(button.Button, button.Index, button.Id);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g) {
/* 251 */     paintBackground(g);
/* 252 */     super.paint(g);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintBackground(Graphics g) {
/* 257 */     Rectangle rectClient = JLbsControlHelper.getClientRect((JComponent)this);
/* 258 */     g.setColor(getBackground());
/* 259 */     g.fillRect(rectClient.x, rectClient.y, rectClient.width, rectClient.height);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/* 265 */     Rectangle rectClient = JLbsControlHelper.getClientRect((JComponent)this);
/* 266 */     Rectangle rectButton = new Rectangle(rectClient.x + (this.m_bLeftJustify ? 
/* 267 */         0 : (
/* 268 */         rectClient.width - this.m_iTotalButtonWidth)), rectClient.y, 0, rectClient.height);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 282 */     List<?> list = Collections.synchronizedList(this.m_Buttons);
/* 283 */     Iterator<?> it = list.iterator();
/* 284 */     while (it.hasNext()) {
/*     */       
/* 286 */       JButtonInfoHolder holder = (JButtonInfoHolder)it.next();
/* 287 */       JLbsButtonPanelSimpleButton sb = (JLbsButtonPanelSimpleButton)holder.Button;
/*     */       
/* 289 */       if (holder.Button != null && holder.visible) {
/*     */         
/* 291 */         int iButtonWidth = holder.Button.getWidth();
/* 292 */         if (iButtonWidth >= 6) {
/*     */           
/* 294 */           rectButton.width = iButtonWidth;
/* 295 */           switch (holder.State) {
/*     */             
/*     */             case 1:
/* 298 */               if (!holder.Button.PaintButtonHover((Component)this, g, rectButton))
/* 299 */                 holder.Button.PaintButton((Component)this, g, rectButton); 
/*     */               break;
/*     */             case 2:
/* 302 */               if (!holder.Button.PaintButtonPressed((Component)this, g, rectButton))
/* 303 */                 holder.Button.PaintButton((Component)this, g, rectButton); 
/*     */               break;
/*     */             default:
/* 306 */               holder.Button.PaintButton((Component)this, g, rectButton);
/*     */               break;
/*     */           } 
/* 309 */           holder.DrawRect = (Rectangle)rectButton.clone();
/* 310 */           rectButton.x += rectButton.width;
/*     */           continue;
/*     */         } 
/* 313 */         holder.DrawRect = new Rectangle(0, 0);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processMouseEvent(MouseEvent event) {
/*     */     JButtonInfoHolder button;
/* 321 */     super.processMouseEvent(event);
/* 322 */     switch (event.getID()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 501:
/* 330 */         if (!isEnabled() || event.getButton() != 1)
/*     */           break; 
/* 332 */         if (this.m_FillComp != null)
/* 333 */           this.m_FillComp.requestFocus(); 
/* 334 */         button = getButtonAt(event.getX(), event.getY());
/* 335 */         if (button != null) {
/*     */           
/* 337 */           button.State = 2;
/* 338 */           repaint(button.DrawRect);
/* 339 */           fireButtonDown(button);
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 502:
/* 345 */         button = getButtonAt(event.getX(), event.getY());
/* 346 */         if (button != null && button.State == 2)
/* 347 */           fireButtonClick(button); 
/* 348 */         releaseAllButtons();
/*     */         break;
/*     */ 
/*     */       
/*     */       case 505:
/* 353 */         releaseAllButtons();
/* 354 */         event.consume();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void releaseAllButtons() {
/* 361 */     List<?> list = Collections.synchronizedList(this.m_Buttons);
/* 362 */     Iterator<?> it = list.iterator();
/* 363 */     while (it.hasNext()) {
/*     */       
/* 365 */       JButtonInfoHolder holder = (JButtonInfoHolder)it.next();
/* 366 */       if (holder.Button != null && holder.State != 0) {
/*     */         
/* 368 */         holder.State = 0;
/* 369 */         if (holder.DrawRect != null && !holder.DrawRect.isEmpty()) {
/* 370 */           repaint(holder.DrawRect);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JButtonInfoHolder getButtonAt(int x, int y) {
/* 402 */     List<?> list = Collections.synchronizedList(this.m_Buttons);
/* 403 */     Iterator<?> it = list.iterator();
/* 404 */     int index = 0;
/* 405 */     while (it.hasNext()) {
/*     */       
/* 407 */       JButtonInfoHolder holder = (JButtonInfoHolder)it.next();
/* 408 */       if (holder.Button != null && holder.DrawRect.contains(x, y)) {
/*     */         
/* 410 */         holder.Index = index;
/* 411 */         return holder;
/*     */       } 
/* 413 */       index++;
/*     */     } 
/* 415 */     return null;
/*     */   }
/*     */   
/*     */   protected class JButtonInfoHolder
/*     */   {
/*     */     static final int NORMAL = 0;
/*     */     static final int HOVER = 1;
/*     */     static final int PRESSED = 2;
/*     */     public ILbsButtonPanelChild Button;
/*     */     public int Id;
/* 425 */     public int Index = -1;
/* 426 */     public int State = 0;
/* 427 */     public Rectangle DrawRect = new Rectangle(0, 0, 0, 0);
/*     */     
/*     */     public boolean visible = true;
/*     */     
/*     */     public JButtonInfoHolder(ILbsButtonPanelChild button, int id) {
/* 432 */       this.Button = button;
/* 433 */       this.Id = id;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean flag) {
/* 439 */     super.setEnabled(flag);
/* 440 */     if (this.m_FillComp != null) {
/* 441 */       this.m_FillComp.setEnabled(flag);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setBorder(Border border) {
/* 446 */     super.setBorder(border);
/* 447 */     if (this.m_FillComp != null) {
/* 448 */       UpdateChildComponentLayout();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 458 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\buttonpanel\JLbsButtonPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */