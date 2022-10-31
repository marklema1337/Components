/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComboEdit;
/*     */ import com.lbs.control.interfaces.ILbsMaskedEdit;
/*     */ import com.lbs.controls.buttonpanel.ILbsButtonPanelChild;
/*     */ import com.lbs.controls.buttonpanel.JLbsButtonPanel;
/*     */ import com.lbs.controls.buttonpanel.JLbsButtonPanelLangButton;
/*     */ import com.lbs.controls.buttonpanel.JLbsButtonPanelSimpleButton;
/*     */ import com.lbs.controls.maskededit.JLbsMaskedEdit;
/*     */ import com.lbs.controls.maskededit.JLbsTextEdit;
/*     */ import com.lbs.recording.interfaces.ILbsComboEditRecordingEvents;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Image;
/*     */ import java.awt.Point;
/*     */ import java.awt.dnd.DropTargetDragEvent;
/*     */ import java.awt.dnd.DropTargetDropEvent;
/*     */ import java.awt.dnd.DropTargetEvent;
/*     */ import java.awt.dnd.DropTargetListener;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.border.EmptyBorder;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsComboEdit
/*     */   extends JLbsButtonPanel
/*     */   implements FocusListener, ILbsComboEdit, ILbsComboEditRecordingEvents, DropTargetListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected LbsComboEditImplementor m_Implementor;
/*     */   protected JLbsMaskedEdit m_Edit;
/*     */   protected JButton m_MultilangButton;
/*     */   
/*     */   public JLbsComboEdit() {
/*  69 */     this(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsComboEdit(boolean bEllipsis) {
/*  76 */     this((JLbsMaskedEdit)new JLbsTextEdit(), bEllipsis ? 1 : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsComboEdit(JLbsMaskedEdit edit, int iButtonType) {
/*  81 */     this.m_Implementor = createImplementor((ILbsMaskedEdit)edit);
/*     */     
/*  83 */     this.m_Edit = edit;
/*  84 */     if (this.m_Edit == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  89 */     setPreferredSize(new Dimension(104, 22));
/*     */ 
/*     */     
/*  92 */     this.m_Edit.setBorder(new EmptyBorder(1, 1, 1, 0));
/*  93 */     this.m_Edit.addKeyListener(new JLbsComboEditEditKeyAdapter(this));
/*  94 */     setButtonListener(new JLbsComboEditButtonAdapter(this));
/*  95 */     beginUpdate();
/*  96 */     addButton((ILbsButtonPanelChild)new JLbsButtonPanelSimpleButton(22, iButtonType), 0);
/*  97 */     setFillComponent(this.m_Edit);
/*  98 */     endUpdate();
/*     */     
/* 100 */     this.m_Edit.addFocusListener(this);
/*     */     
/* 102 */     if (JLbsConstants.DESIGN_TIME) {
/* 103 */       setBackground(Color.WHITE);
/*     */     }
/* 105 */     if (!GraphicsEnvironment.isHeadless());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected LbsComboEditImplementor createImplementor(ILbsMaskedEdit edit) {
/* 112 */     return new LbsComboEditImplementor(this, edit);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addExtraButton(int buttonType) {
/* 117 */     if (this.m_Buttons == null || this.m_Buttons.size() < 2)
/*     */     {
/* 119 */       if (buttonType == 3) {
/* 120 */         addButton((ILbsButtonPanelChild)new JLbsButtonPanelLangButton(22, buttonType, this), 1);
/*     */       } else {
/* 122 */         addButton((ILbsButtonPanelChild)new JLbsButtonPanelSimpleButton(22, buttonType), 1);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void calcTotalButtonWidth() {
/* 129 */     super.calcTotalButtonWidth();
/* 130 */     if (JLbsConstants.DESKTOP_MODE && this.m_iTotalButtonWidth > 0) {
/* 131 */       this.m_iTotalButtonWidth--;
/*     */     }
/*     */   }
/*     */   
/*     */   public void remove(Component comp) {
/* 136 */     if (this.m_FillComp == comp) {
/*     */       
/* 138 */       this.m_FillComp = null;
/* 139 */       this.m_Edit = null;
/*     */     } 
/* 141 */     super.remove(comp);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 146 */     super.updateUI();
/* 147 */     resetBkColor(false, false);
/*     */ 
/*     */     
/* 150 */     Border border = getBorder();
/* 151 */     if (!(border instanceof EmptyBorder))
/*     */     {
/* 153 */       setBorder(UIManager.getBorder("TextField.border"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setEditor(JLbsMaskedEdit edit) {
/* 159 */     if (this.m_Edit == null && edit != null) {
/*     */       
/* 161 */       this.m_Edit = edit;
/* 162 */       this.m_Implementor.setEditor((ILbsMaskedEdit)edit);
/* 163 */       beginUpdate();
/* 164 */       setFillComponent(edit);
/* 165 */       endUpdate();
/* 166 */       edit.setBorder(new EmptyBorder(1, 1, 1, 0));
/*     */       
/* 168 */       this.m_Edit.addKeyListener(new JLbsComboEditEditKeyAdapter(this));
/* 169 */       this.m_Edit.updateUI();
/* 170 */       this.m_Edit.addFocusListener(this);
/* 171 */       updateUI();
/* 172 */       validate();
/* 173 */       return true;
/*     */     } 
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void grabFocus() {
/* 180 */     if (this.m_Implementor == null || !this.m_Implementor.grabFocus()) {
/* 181 */       super.grabFocus();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setBackground(Color bg) {
/* 186 */     super.setBackground(bg);
/* 187 */     if (this.m_Implementor != null) {
/* 188 */       this.m_Implementor.setBackground(bg);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setUI(PanelUI ui) {
/* 193 */     super.setUI(ui);
/* 194 */     if (ui != null && getBorder() != null) {
/*     */       
/* 196 */       Border border = UIManager.getBorder("ComboEdit.border");
/* 197 */       if (border != null) {
/* 198 */         setBorder(border);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setActionListener(ActionListener listener) {
/* 204 */     this.m_Implementor.setActionListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExtraButtonListener(ActionListener extraButtonListener) {
/* 209 */     this.m_Implementor.setExtraButtonListener(extraButtonListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addLbsFocusListener(FocusListener listener) {
/* 214 */     this.m_Implementor.addLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeLbsFocusListener(FocusListener listener) {
/* 219 */     this.m_Implementor.removeLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsMaskedEdit getEditControl() {
/* 224 */     return (ILbsMaskedEdit)this.m_Edit;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsMaskedEdit getEditComponent() {
/* 229 */     return this.m_Edit;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEmptyBlockWidth() {
/* 234 */     return this.m_Implementor.getEmptyBlockWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmptyBlockWidth(int width) {
/* 239 */     this.m_Implementor.setEmptyBlockWidth(width);
/* 240 */     UpdateChildComponentLayout();
/*     */   }
/*     */ 
/*     */   
/*     */   public void doButtonAction(int index, int id) {
/* 245 */     doPopup(index, id);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doPopup(int index, int id) {
/* 250 */     if (!isEnabled())
/*     */       return; 
/* 252 */     if (id == 0 && this.m_Implementor.m_ButtonListener != null)
/*     */     {
/* 254 */       recordDoPopup(index, id);
/*     */     }
/* 256 */     this.m_Edit.setSelectedFiles(null);
/* 257 */     if (this.m_Edit.hasFileDialog()) {
/*     */       
/* 259 */       JFileChooser chooser = new JFileChooser();
/* 260 */       chooser.setMultiSelectionEnabled(true);
/* 261 */       int option = chooser.showOpenDialog(null);
/* 262 */       if (option == 0)
/*     */       {
/* 264 */         this.m_Edit.setSelectedFiles(chooser.getSelectedFiles());
/*     */       }
/*     */     } 
/* 267 */     this.m_Implementor.doPopup(index, id);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPopup(int actionID) {
/* 272 */     final int fActionID = actionID;
/* 273 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 277 */             JLbsComponentHelper.statusChanged(4, fActionID, null);
/* 278 */             JLbsComboEdit.this.doPopup(0, 0);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintBackground(Graphics g) {
/* 285 */     hasFocus();
/*     */ 
/*     */ 
/*     */     
/* 289 */     super.paintBackground(g);
/*     */   }
/*     */ 
/*     */   
/*     */   public void focusGained(FocusEvent e) {
/* 294 */     if (e.getSource() == this.m_Edit && !JLbsConstants.DESIGN_TIME) {
/* 295 */       resetBkColor(true, true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void focusLost(FocusEvent e) {
/* 300 */     if (e.getSource() == this.m_Edit && !JLbsConstants.DESIGN_TIME) {
/* 301 */       resetBkColor(false, true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 306 */     super.setEnabled(enabled);
/* 307 */     resetBkColor(isFocusOwner(), false);
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetBkColor(boolean bFocused, boolean bForce) {
/* 312 */     if (this.m_Edit != null && isEnabled() && !JLbsConstants.DESIGN_TIME) {
/*     */       
/* 314 */       boolean focused = bForce ? 
/* 315 */         bFocused : 
/* 316 */         this.m_Edit.hasFocus();
/* 317 */       setBackground(focused ? 
/* 318 */           JLbsMaskedEdit.getSelectedBkColor() : 
/* 319 */           this.m_Edit.getNormalBkColor());
/* 320 */       repaint();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Point getFillComponentLocation() {
/* 326 */     Point p = super.getFillComponentLocation();
/* 327 */     if (getEmptyBlockWidth() > 0)
/* 328 */       p.x += getEmptyBlockWidth(); 
/* 329 */     return p;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Dimension getFillComponentSize(Dimension dim) {
/* 334 */     Dimension result = super.getFillComponentSize(dim);
/* 335 */     if (getEmptyBlockWidth() > 0)
/* 336 */       result.width -= getEmptyBlockWidth(); 
/* 337 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 342 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 347 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordActionPerformed(ActionEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordKeyPressed(KeyEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordMouseClicked(MouseEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordDoPopup(int index, int id) {
/* 365 */     if (getClientProperty("INP") == null) {
/* 366 */       JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "LOOKUP");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordRequestFocus() {}
/*     */ 
/*     */   
/*     */   public void setFirstLookupButtonVisible(boolean visible) {
/* 376 */     if (this.m_Buttons.size() == 2) {
/*     */       
/* 378 */       JLbsButtonPanel.JButtonInfoHolder b = this.m_Buttons.get(0);
/* 379 */       b.visible = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMultilangButton(JButton button) {
/* 386 */     this.m_MultilangButton = button;
/*     */   }
/*     */ 
/*     */   
/*     */   public JButton getMultilangButton() {
/* 391 */     return this.m_MultilangButton;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIconToMultilangButton(ImageIcon imageIcon) {
/* 396 */     Image image = imageIcon.getImage();
/* 397 */     Image newimg = image.getScaledInstance(imageIcon.getIconWidth() - 4, imageIcon.getIconHeight() - 3, 4);
/* 398 */     imageIcon = new ImageIcon(newimg);
/* 399 */     if (this.m_MultilangButton != null) {
/* 400 */       this.m_MultilangButton.setIcon(imageIcon);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dragEnter(DropTargetDragEvent dtde) {
/* 411 */     dtde.rejectDrag();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dragOver(DropTargetDragEvent dtde) {
/* 417 */     dtde.rejectDrag();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropActionChanged(DropTargetDragEvent dtde) {
/* 423 */     dtde.rejectDrag();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dragExit(DropTargetEvent dte) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void drop(DropTargetDropEvent dtde) {
/* 434 */     dtde.rejectDrop();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsComboEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */