/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsMaskedEdit;
/*     */ import com.lbs.controls.buttonpanel.ILbsButtonPanelChild;
/*     */ import com.lbs.controls.buttonpanel.JLbsButtonPanel;
/*     */ import com.lbs.controls.buttonpanel.JLbsButtonPanelSimpleButton;
/*     */ import com.lbs.controls.maskededit.JLbsMaskedEdit;
/*     */ import com.lbs.controls.maskededit.JLbsTextEdit;
/*     */ import com.lbs.recording.interfaces.ILbsComboEditRecordingEvents;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.border.EtchedBorder;
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
/*     */ public class JLbsDualComboEdit
/*     */   extends JLbsButtonPanel
/*     */   implements FocusListener, ILbsInternalDualComboEdit, ILbsComboEditRecordingEvents
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private LbsDualComboEditImplementor m_Implementor;
/*     */   protected JLbsMaskedEdit m_Edit;
/*     */   private JLbsTextEdit m_Edit2;
/*     */   
/*     */   public JLbsDualComboEdit() {
/*  55 */     this(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsDualComboEdit(boolean bEllipsis) {
/*  62 */     this((JLbsMaskedEdit)new JLbsTextEdit(), bEllipsis ? 1 : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsDualComboEdit(JLbsMaskedEdit edit, int iButtonType) {
/*  67 */     this.m_Implementor = new LbsDualComboEditImplementor(this);
/*  68 */     initializeFillComponent(edit);
/*  69 */     if (this.m_Edit == null)
/*     */       return; 
/*  71 */     if (this.m_Edit.getBorder() != null)
/*  72 */       setBorder(new EtchedBorder(1)); 
/*  73 */     setPreferredSize(new Dimension(130, 22));
/*  74 */     setButtonListener(new JLbsDualComboEditButtonAdapter(this));
/*  75 */     beginUpdate();
/*  76 */     addButton((ILbsButtonPanelChild)new JLbsButtonPanelSimpleButton(24, iButtonType), 0);
/*  77 */     setFillComponent(this.m_Edit);
/*  78 */     endUpdate();
/*     */     
/*  80 */     if (JLbsConstants.DESIGN_TIME) {
/*  81 */       setBackground(Color.WHITE);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setFillControl(ILbsMaskedEdit edit) {
/*  86 */     setFillComponent((JLbsMaskedEdit)edit);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initializeFillComponent(JLbsMaskedEdit edit) {
/*  91 */     if (this.m_Edit2 != null) {
/*     */       
/*  93 */       this.m_Edit2 = null;
/*  94 */       this.m_Edit = null;
/*     */     } 
/*  96 */     this.m_Edit2 = new JLbsTextEdit();
/*  97 */     this.m_Edit2.setBorder(new EmptyBorder(1, 1, 1, 0));
/*  98 */     this.m_Edit = (edit != null) ? 
/*  99 */       edit : 
/* 100 */       (JLbsMaskedEdit)new JLbsTextEdit();
/* 101 */     this.m_Edit.setBorder(new EmptyBorder(1, 1, 1, 0));
/* 102 */     this.m_Edit.addKeyListener(new JLbsDualComboEditEditKeyAdapter(this));
/*     */     
/* 104 */     this.m_Implementor.setEditors((ILbsMaskedEdit)this.m_Edit, (ILbsMaskedEdit)this.m_Edit2);
/*     */     
/* 106 */     this.m_Edit.updateUI();
/* 107 */     this.m_Edit2.updateUI();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(Component comp) {
/* 113 */     if (this.m_FillComp == comp) {
/*     */       
/* 115 */       this.m_FillComp = null;
/* 116 */       this.m_Edit = null;
/*     */     } 
/* 118 */     super.remove(comp);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 123 */     super.updateUI();
/* 124 */     resetBkColor(false, false);
/* 125 */     Border border = getBorder();
/* 126 */     if (border != null && !(border instanceof EmptyBorder)) {
/*     */       
/* 128 */       border = UIManager.getBorder("ComboEdit.border");
/* 129 */       setBorder((border != null) ? 
/* 130 */           border : 
/* 131 */           new EtchedBorder(1));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setEditor(JLbsMaskedEdit edit) {
/* 137 */     if (this.m_Edit == null && edit != null) {
/*     */       
/* 139 */       initializeFillComponent(edit);
/* 140 */       beginUpdate();
/* 141 */       setFillComponent(this.m_Edit);
/* 142 */       endUpdate();
/* 143 */       validate();
/* 144 */       return true;
/*     */     } 
/* 146 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void grabFocus() {
/* 151 */     if (!this.m_Implementor.grabFocus()) {
/* 152 */       super.grabFocus();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setBackground(Color bg) {
/* 157 */     super.setBackground(bg);
/* 158 */     if (this.m_Implementor != null) {
/* 159 */       this.m_Implementor.setBackground(bg);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setUI(PanelUI ui) {
/* 164 */     super.setUI(ui);
/* 165 */     if (ui != null && getBorder() != null) {
/*     */       
/* 167 */       Border border = UIManager.getBorder("ComboEdit.border");
/* 168 */       if (border != null) {
/* 169 */         setBorder(border);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setActionListener(ActionListener listener) {
/* 175 */     this.m_Implementor.setActionListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addLbsFocusListener(FocusListener listener) {
/* 180 */     this.m_Implementor.addLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeLbsFocusListener(FocusListener listener) {
/* 185 */     this.m_Implementor.removeLbsFocusListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsMaskedEdit getEditControl() {
/* 190 */     return (ILbsMaskedEdit)this.m_Edit;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsMaskedEdit getEditComponent() {
/* 195 */     return this.m_Edit;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEmptyBlockWidth() {
/* 200 */     return this.m_Implementor.getEmptyBlockWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmptyBlockWidth(int width) {
/* 205 */     this.m_Implementor.setEmptyBlockWidth(width);
/* 206 */     UpdateChildComponentLayout();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doPopup(int index, int id) {
/* 211 */     if (this.m_Implementor.m_ButtonListener != null)
/*     */     {
/* 213 */       recordDoPopup(index, id);
/*     */     }
/* 215 */     this.m_Implementor.doPopup(index, id);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPopup(int actionID) {
/* 220 */     JLbsComponentHelper.statusChanged(4, actionID, null);
/* 221 */     doPopup(0, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void focusGained(FocusEvent e) {
/* 226 */     this.m_Implementor.focusGained(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public void focusLost(FocusEvent e) {
/* 231 */     this.m_Implementor.focusLost(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 236 */     super.setEnabled(enabled);
/* 237 */     this.m_Implementor.setEnabled(enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetBkColor(boolean bFocused, boolean bForce) {
/* 242 */     if (this.m_Edit != null && isEnabled() && !JLbsConstants.DESIGN_TIME) {
/*     */       
/* 244 */       boolean focused = bForce ? 
/* 245 */         bFocused : 
/* 246 */         this.m_Edit.hasFocus();
/* 247 */       setBackground(focused ? 
/* 248 */           JLbsMaskedEdit.getSelectedBkColor() : 
/* 249 */           this.m_Edit.getNormalBkColor());
/* 250 */       repaint();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Point getFillComponentLocation() {
/* 256 */     Point p = super.getFillComponentLocation();
/* 257 */     if (getEmptyBlockWidth() > 0)
/* 258 */       p.x += getEmptyBlockWidth(); 
/* 259 */     return p;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Dimension getFillComponentSize(Dimension dim) {
/* 264 */     Dimension result = super.getFillComponentSize(dim);
/* 265 */     if (getEmptyBlockWidth() > 0)
/* 266 */       result.width -= getEmptyBlockWidth(); 
/* 267 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEditorText(String text) {
/* 272 */     this.m_Implementor.setEditorText(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEditorText() {
/* 277 */     return this.m_Implementor.getEditorText();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String text1, String text2) {
/* 282 */     this.m_Implementor.setText(text1, text2);
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getText() {
/* 287 */     return this.m_Implementor.getText();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setToolTipFormat(String format) {
/* 297 */     this.m_Implementor.setToolTipFormat(format);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 302 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 307 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
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
/* 325 */     if (getClientProperty("INP") == null) {
/* 326 */       JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "LOOKUP");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordRequestFocus() {}
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 335 */     JDialog dlg = new JDialog();
/* 336 */     JPanel pnl = new JPanel();
/* 337 */     pnl.setBackground(Color.RED);
/* 338 */     JLbsDualComboEdit edt = new JLbsDualComboEdit();
/* 339 */     edt.setText("Deneme", "işte 2");
/* 340 */     edt.setToolTipFormat("Kod :%s - Açıklama :%s");
/* 341 */     pnl.add((Component)edt);
/* 342 */     pnl.add((Component)new JLbsComboEdit());
/* 343 */     dlg.setContentPane(pnl);
/* 344 */     dlg.setSize(new Dimension(200, 200));
/* 345 */     dlg.setModal(true);
/* 346 */     dlg.show();
/* 347 */     System.exit(0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsDualComboEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */