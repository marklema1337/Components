/*     */ package com.lbs.control.interfaces.adapters.base;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.control.interfaces.ILbsComponentAdapter;
/*     */ import com.lbs.control.interfaces.ILbsRichTextEditor;
/*     */ import com.lbs.controls.ILbsRichTextSaveLoadListener;
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
/*     */ import javax.swing.text.AttributeSet;
/*     */ 
/*     */ 
/*     */ public abstract class LbsRichTextEditorAdapterBase
/*     */   implements ILbsRichTextEditor, ILbsComponentAdapter
/*     */ {
/*     */   private final ILbsRichTextEditor delegate;
/*     */   
/*     */   protected LbsRichTextEditorAdapterBase(ILbsRichTextEditor delegate) {
/*  29 */     this.delegate = delegate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFocusListener(FocusListener arg0) {
/*  36 */     this.delegate.addFocusListener(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addKeyListener(KeyListener arg0) {
/*  41 */     this.delegate.addKeyListener(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMouseListener(MouseListener arg0) {
/*  46 */     this.delegate.addMouseListener(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyComponentOrientation(ComponentOrientation arg0) {
/*  51 */     this.delegate.applyComponentOrientation(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentX() {
/*  56 */     return this.delegate.getAlignmentX();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlignmentY() {
/*  61 */     return this.delegate.getAlignmentY();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAutoscrolls() {
/*  66 */     return this.delegate.getAutoscrolls();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getBackground() {
/*  71 */     return this.delegate.getBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   public Border getBorder() {
/*  76 */     return this.delegate.getBorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/*  81 */     return this.delegate.getBounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getClientProperty(Object arg0) {
/*  86 */     return this.delegate.getClientProperty(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public ComponentOrientation getComponentOrientation() {
/*  91 */     return this.delegate.getComponentOrientation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Font getFont() {
/*  96 */     return this.delegate.getFont();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getForeground() {
/* 101 */     return this.delegate.getForeground();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 106 */     return this.delegate.getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getInsets() {
/* 111 */     return this.delegate.getInsets();
/*     */   }
/*     */ 
/*     */   
/*     */   public Point getLocation() {
/* 116 */     return this.delegate.getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize() {
/* 121 */     return this.delegate.getMaximumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize() {
/* 126 */     return this.delegate.getMinimumSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public MouseListener[] getMouseListeners() {
/* 131 */     return this.delegate.getMouseListeners();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 136 */     return this.delegate.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 141 */     return this.delegate.getParentComponent();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 146 */     return this.delegate.getPreferredSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getSize() {
/* 151 */     return this.delegate.getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolTipText() {
/* 156 */     return this.delegate.getToolTipText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 161 */     return this.delegate.getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 166 */     return this.delegate.getX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/* 171 */     return this.delegate.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   public void grabFocus() {
/* 176 */     this.delegate.grabFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFocus() {
/* 181 */     return this.delegate.hasFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 186 */     this.delegate.invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 191 */     return this.delegate.isEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusOwner() {
/* 196 */     return this.delegate.isFocusOwner();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFocusable() {
/* 201 */     return this.delegate.isFocusable();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaque() {
/* 206 */     return this.delegate.isOpaque();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowing() {
/* 211 */     return this.delegate.isShowing();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 216 */     return this.delegate.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void putClientProperty(Object arg0, Object arg1) {
/* 221 */     this.delegate.putClientProperty(arg0, arg1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAll() {
/* 226 */     this.delegate.removeAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeFocusListener(FocusListener arg0) {
/* 231 */     this.delegate.removeFocusListener(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeKeyListener(KeyListener arg0) {
/* 236 */     this.delegate.removeKeyListener(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMouseListener(MouseListener arg0) {
/* 241 */     this.delegate.removeMouseListener(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void repaint() {
/* 246 */     this.delegate.repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestFocus() {
/* 251 */     this.delegate.requestFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requestFocus(boolean arg0) {
/* 256 */     return this.delegate.requestFocus(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void revalidate() {
/* 261 */     this.delegate.revalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentX(float arg0) {
/* 266 */     this.delegate.setAlignmentX(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlignmentY(float arg0) {
/* 271 */     this.delegate.setAlignmentY(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoscrolls(boolean arg0) {
/* 276 */     this.delegate.setAutoscrolls(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackground(Color arg0) {
/* 281 */     this.delegate.setBackground(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBorder(Border arg0) {
/* 286 */     this.delegate.setBorder(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(Rectangle arg0) {
/* 291 */     this.delegate.setBounds(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBounds(int arg0, int arg1, int arg2, int arg3) {
/* 296 */     this.delegate.setBounds(arg0, arg1, arg2, arg3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponentOrientation(ComponentOrientation arg0) {
/* 301 */     this.delegate.setComponentOrientation(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDropTarget(DropTarget arg0) {
/* 306 */     this.delegate.setDropTarget(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean arg0) {
/* 311 */     this.delegate.setEnabled(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFocusable(boolean arg0) {
/* 316 */     this.delegate.setFocusable(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFont(Font arg0) {
/* 321 */     this.delegate.setFont(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForeground(Color arg0) {
/* 326 */     this.delegate.setForeground(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(Point arg0) {
/* 331 */     this.delegate.setLocation(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocation(int arg0, int arg1) {
/* 336 */     this.delegate.setLocation(arg0, arg1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximumSize(Dimension arg0) {
/* 341 */     this.delegate.setMaximumSize(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMinimumSize(Dimension arg0) {
/* 346 */     this.delegate.setMinimumSize(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOpaque(boolean arg0) {
/* 351 */     this.delegate.setOpaque(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPreferredSize(Dimension arg0) {
/* 356 */     this.delegate.setPreferredSize(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(Dimension arg0) {
/* 361 */     this.delegate.setSize(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(int arg0, int arg1) {
/* 366 */     this.delegate.setSize(arg0, arg1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToolTipText(String arg0) {
/* 371 */     this.delegate.setToolTipText(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTransferHandler(TransferHandler arg0) {
/* 376 */     this.delegate.setTransferHandler(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean arg0) {
/* 381 */     this.delegate.setVisible(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 386 */     this.delegate.updateUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void validate() {
/* 391 */     this.delegate.validate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPaneFocusListener(FocusListener arg0) {
/* 400 */     this.delegate.addPaneFocusListener(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void documentReplace(int arg0, int arg1, String arg2, AttributeSet arg3) {
/* 405 */     this.delegate.documentReplace(arg0, arg1, arg2, arg3);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCaretPosition() {
/* 410 */     return this.delegate.getCaretPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getCharacterElement(int arg0) {
/* 415 */     return this.delegate.getCharacterElement(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getDocumentContents() {
/* 420 */     return this.delegate.getDocumentContents();
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsRichTextSaveLoadListener getOperationListener() {
/* 425 */     return this.delegate.getOperationListener();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRichDocText(int arg0, int arg1) {
/* 430 */     return this.delegate.getRichDocText(arg0, arg1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRichDocTextLenght() {
/* 435 */     return this.delegate.getRichDocTextLenght();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSelectedText() {
/* 440 */     return this.delegate.getSelectedText();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectionEnd() {
/* 445 */     return this.delegate.getSelectionEnd();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectionStart() {
/* 450 */     return this.delegate.getSelectionStart();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText() {
/* 455 */     return this.delegate.getText();
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertString(int arg0, String arg1, AttributeSet arg2) {
/* 460 */     this.delegate.insertString(arg0, arg1, arg2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadDocument() {
/* 465 */     this.delegate.loadDocument();
/*     */   }
/*     */ 
/*     */   
/*     */   public void putPaneClientProperty(Object arg0, Object arg1) {
/* 470 */     this.delegate.putPaneClientProperty(arg0, arg1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveDocument() {
/* 475 */     this.delegate.saveDocument();
/*     */   }
/*     */ 
/*     */   
/*     */   public void select(int arg0, int arg1) {
/* 480 */     this.delegate.select(arg0, arg1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCaretPosition(int arg0) {
/* 485 */     this.delegate.setCaretPosition(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDocumentContents(byte[] arg0) {
/* 490 */     this.delegate.setDocumentContents(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperationListener(ILbsRichTextSaveLoadListener arg0) {
/* 495 */     this.delegate.setOperationListener(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPaneEnabled(boolean arg0) {
/* 500 */     this.delegate.setPaneEnabled(arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String arg0) {
/* 505 */     this.delegate.setText(arg0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 514 */     return this.delegate.canHaveLayoutManager();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 519 */     return this.delegate.getResourceIdentifier();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 524 */     return this.delegate.getUniqueIdentifier();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ILbsComponent getDelegate() {
/* 533 */     return (ILbsComponent)this.delegate;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\control\interfaces\adapters\base\LbsRichTextEditorAdapterBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */