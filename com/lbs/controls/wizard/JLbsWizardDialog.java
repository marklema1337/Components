/*     */ package com.lbs.controls.wizard;
/*     */ 
/*     */ import com.lbs.controls.JLbsControlHelper;
/*     */ import com.lbs.controls.JLbsLabel;
/*     */ import com.lbs.controls.JLbsVerticalBevel;
/*     */ import com.lbs.controls.JLbsVerticalSeparator;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsDialog;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.UIHelperUtil;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsWizardDialog
/*     */   extends JLbsDialog
/*     */   implements ActionListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  41 */   public static String[] ms_Captions = new String[] { "Title", "Description", "< &Previous", "  &Next >", "&Cancel", "&Finish" };
/*     */   
/*     */   static final int RESID = -20012;
/*     */   
/*     */   private JLbsLabel m_TopTitle;
/*     */   
/*     */   private JLbsLabel m_TopDescr;
/*     */   private JPanel m_CenterPanel;
/*     */   private JComponent m_PaneControl;
/*     */   private ArrayList m_PageHistory;
/*     */   protected JButton m_BtnPrev;
/*     */   protected JButton m_BtnNext;
/*     */   protected JButton m_BtnCancel;
/*     */   private JPanel m_TopPanel;
/*     */   
/*     */   public JLbsWizardDialog() {
/*  57 */     localize();
/*  58 */     this.m_PageHistory = new ArrayList();
/*  59 */     setResizable(true);
/*  60 */     JPanel contPanel = new JPanel();
/*  61 */     contPanel.setLayout(new BorderLayout());
/*  62 */     this.m_TopPanel = new JPanel();
/*  63 */     this.m_TopPanel.setLayout(new BorderLayout());
/*  64 */     this.m_TopPanel.setBackground(Color.WHITE);
/*  65 */     this.m_TopPanel.setPreferredSize(new Dimension(400, 70));
/*  66 */     JLbsVerticalBevel topBevel = new JLbsVerticalBevel();
/*  67 */     JLabel lblTopIcon = new JLabel();
/*  68 */     lblTopIcon.setIcon(JLbsControlHelper.getImageIcon(JLbsWizardDialog.class, "wiztop.png"));
/*  69 */     if (JLbsConstants.DESKTOP_MODE)
/*  70 */       lblTopIcon.setIcon(JLbsControlHelper.getImageIcon(JLbsWizardDialog.class, "wiztopdesktop.png")); 
/*  71 */     this.m_TopPanel.add(lblTopIcon, "East");
/*  72 */     this.m_TopPanel.add((Component)topBevel, "South");
/*  73 */     JPanel labelPanel = new JPanel();
/*  74 */     labelPanel.setLayout((LayoutManager)null);
/*  75 */     labelPanel.setOpaque(false);
/*  76 */     this.m_TopTitle = new JLbsLabel();
/*  77 */     this.m_TopTitle.setLocation(16, 10);
/*  78 */     this.m_TopTitle.setSize(340, 20);
/*  79 */     this.m_TopTitle.setFont(new Font(JLbsConstants.APP_FONT, 1, JLbsConstants.APP_FONT_SIZE + 1));
/*  80 */     this.m_TopTitle.setText(ms_Captions[0]);
/*  81 */     labelPanel.add((Component)this.m_TopTitle);
/*  82 */     this.m_TopDescr = new JLbsLabel();
/*  83 */     this.m_TopDescr.setLocation(30, 28);
/*  84 */     this.m_TopDescr.setSize(320, 20);
/*  85 */     this.m_TopDescr.setText(ms_Captions[1]);
/*  86 */     labelPanel.add((Component)this.m_TopTitle);
/*  87 */     labelPanel.add((Component)this.m_TopDescr);
/*  88 */     this.m_TopPanel.add(labelPanel, "Center");
/*     */     
/*  90 */     this.m_CenterPanel = new JPanel();
/*  91 */     this.m_CenterPanel.setPreferredSize(new Dimension(420, 260));
/*  92 */     this.m_CenterPanel.setLayout(new BorderLayout());
/*  93 */     contPanel.add(this.m_CenterPanel, "Center");
/*     */     
/*  95 */     JPanel bottomPanel = new JPanel();
/*  96 */     bottomPanel.setLayout(new BorderLayout());
/*  97 */     bottomPanel.setBorder(new EmptyBorder(4, 0, 0, 0));
/*  98 */     bottomPanel.add((Component)new JLbsVerticalSeparator(), "North");
/*  99 */     JPanel buttonPanel = new JPanel();
/* 100 */     buttonPanel.setLayout(new FlowLayout(2, 8, 4));
/* 101 */     JPanel leftPanel = new JPanel();
/* 102 */     leftPanel.setLayout(new FlowLayout(2, 4, 0));
/* 103 */     leftPanel.add(this.m_BtnPrev = createButton(ms_Captions[2], "prev"));
/* 104 */     leftPanel.add(this.m_BtnNext = createButton(ms_Captions[3], "next"));
/*     */     
/* 106 */     buttonPanel.add(leftPanel);
/* 107 */     JPanel rightPanel = new JPanel();
/* 108 */     rightPanel.setLayout(new FlowLayout(2, 0, 0));
/* 109 */     rightPanel.add(this.m_BtnCancel = createButton(ms_Captions[4], "cancel"));
/* 110 */     buttonPanel.add(rightPanel);
/* 111 */     bottomPanel.add(buttonPanel, "Center");
/* 112 */     contPanel.add(this.m_TopPanel, "North");
/* 113 */     contPanel.add(bottomPanel, "South");
/* 114 */     setContentPane(contPanel);
/* 115 */     this.m_BtnPrev.setEnabled(false);
/* 116 */     getRootPane().setDefaultButton(this.m_BtnNext);
/* 117 */     pack();
/* 118 */     centerScreen();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean localize() {
/* 123 */     JLbsStringList list = JLbsLocalizer.getStringAsList(-20012, 92, true);
/* 124 */     if (list != null)
/* 125 */       JLbsLocalizer.localizeObjectArray(list, 0, (Object[])ms_Captions); 
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPaneControl(JComponent control) {
/* 131 */     setPaneControlInt(control, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPaneControl(JComponent control, boolean addHistory) {
/* 136 */     setPaneControlInt(control, addHistory);
/*     */   }
/*     */   
/*     */   public void clearPageHistory() {
/* 140 */     this.m_PageHistory.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void doLayoutInternal() {
/* 145 */     if (this.m_CenterPanel != null) {
/*     */       
/* 147 */       this.m_CenterPanel.invalidate();
/* 148 */       if (this.m_PaneControl != null)
/* 149 */         this.m_PaneControl.invalidate(); 
/* 150 */       SwingUtilities.updateComponentTreeUI(this.m_CenterPanel);
/* 151 */       this.m_CenterPanel.repaint();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setPaneControlInt(JComponent control, boolean addHistory) {
/* 157 */     if (control == null)
/*     */       return; 
/* 159 */     if (addHistory && this.m_PaneControl != null)
/* 160 */       this.m_PageHistory.add(this.m_PaneControl); 
/* 161 */     boolean bHasPrev = (this.m_PageHistory.size() > 0);
/* 162 */     if (this.m_PaneControl != null)
/* 163 */       this.m_CenterPanel.remove(this.m_PaneControl); 
/* 164 */     this.m_PaneControl = control;
/* 165 */     this.m_CenterPanel.add(control, "Center");
/* 166 */     control.setVisible(true);
/* 167 */     if (this.m_PaneControl instanceof ILbsWizardPane) {
/*     */       
/* 169 */       ILbsWizardPane pane = (ILbsWizardPane)this.m_PaneControl;
/* 170 */       this.m_TopTitle.setText(pane.getTitle());
/* 171 */       this.m_TopDescr.setText(pane.getDescription());
/* 172 */       this.m_TopDescr.setSize(this.m_TopDescr.getPreferredSize());
/* 173 */       if (this.m_TopDescr.getHeight() > 20) {
/*     */         
/* 175 */         int diff = this.m_TopDescr.getHeight() - 20;
/* 176 */         this.m_TopPanel.setPreferredSize(new Dimension(400, 70 + diff));
/*     */       }
/*     */       else {
/*     */         
/* 180 */         this.m_TopPanel.setPreferredSize(new Dimension(400, 70));
/*     */       } 
/*     */     } 
/* 183 */     SwingUtilities.updateComponentTreeUI(control);
/* 184 */     boolean bFinal = (control instanceof ILbsWizardPane && ((ILbsWizardPane)control).isFinalPage());
/* 185 */     UIHelperUtil.setCaption(this.m_BtnNext, ms_Captions[bFinal ? 
/* 186 */           5 : 
/* 187 */           3]);
/* 188 */     this.m_BtnNext.setActionCommand(bFinal ? 
/* 189 */         "finish" : 
/* 190 */         "next");
/* 191 */     this.m_CenterPanel.invalidate();
/* 192 */     control.invalidate();
/* 193 */     this.m_CenterPanel.repaint();
/* 194 */     this.m_BtnPrev.setEnabled(bHasPrev);
/* 195 */     if (this.m_PaneControl instanceof ILbsWizardPane)
/* 196 */       ((ILbsWizardPane)this.m_PaneControl).initialize(); 
/* 197 */     doLayout();
/*     */   }
/*     */ 
/*     */   
/*     */   private JButton createButton(String caption, String action) {
/* 202 */     JButton result = new JButton();
/* 203 */     result.setActionCommand(action);
/* 204 */     result.setPreferredSize(new Dimension(96, 24));
/* 205 */     UIHelperUtil.setCaption(result, caption);
/* 206 */     result.addActionListener(this);
/* 207 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private JComponent getNextPaneControl() {
/* 212 */     if (this.m_PaneControl instanceof ILbsWizardPane) {
/*     */       
/* 214 */       ILbsWizardPane pane = (ILbsWizardPane)this.m_PaneControl;
/* 215 */       return pane.getNextPage();
/*     */     } 
/* 217 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 222 */     String action = (e != null) ? 
/* 223 */       e.getActionCommand() : 
/* 224 */       null;
/* 225 */     if (JLbsStringUtil.areEqual(action, "next")) {
/*     */       
/* 227 */       boolean bCanNext = !(this.m_PaneControl instanceof ILbsWizardPane && !((ILbsWizardPane)this.m_PaneControl).canGoNext());
/* 228 */       if (bCanNext)
/*     */       {
/* 230 */         JComponent nextPage = getNextPaneControl();
/* 231 */         if (nextPage != null) {
/* 232 */           setPaneControl(nextPage);
/*     */         }
/* 234 */         setPrevState();
/*     */       }
/*     */     
/* 237 */     } else if (JLbsStringUtil.areEqual(action, "prev")) {
/*     */       
/* 239 */       if (this.m_PageHistory.size() > 0) {
/*     */         
/* 241 */         boolean bCanPrev = !(this.m_PaneControl instanceof ILbsWizardPane && !((ILbsWizardPane)this.m_PaneControl).canGoPrevious());
/* 242 */         if (bCanPrev) {
/*     */           
/* 244 */           int last = this.m_PageHistory.size() - 1;
/* 245 */           JComponent prevPage = this.m_PageHistory.get(last);
/* 246 */           this.m_PageHistory.remove(last);
/* 247 */           if (prevPage != null) {
/* 248 */             setPaneControlInt(prevPage, false);
/*     */           }
/*     */         } 
/*     */       } 
/* 252 */     } else if (JLbsStringUtil.areEqual(action, "cancel")) {
/*     */       
/* 254 */       boolean bCanCancel = !(this.m_PaneControl instanceof ILbsWizardPane && !((ILbsWizardPane)this.m_PaneControl).finalize(true));
/* 255 */       if (bCanCancel) {
/* 256 */         dispose();
/*     */       }
/* 258 */     } else if (JLbsStringUtil.areEqual(action, "finish")) {
/*     */       
/* 260 */       boolean bCanFinish = !(this.m_PaneControl instanceof ILbsWizardPane && !((ILbsWizardPane)this.m_PaneControl).finalize(false));
/* 261 */       if (bCanFinish) {
/* 262 */         dispose();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setPrevState() {
/* 268 */     boolean bCanPrev = !(this.m_PaneControl instanceof ILbsWizardPane && !((ILbsWizardPane)this.m_PaneControl).canGoPrevious());
/* 269 */     if (!bCanPrev) {
/* 270 */       this.m_BtnPrev.setEnabled(false);
/*     */     } else {
/* 272 */       this.m_BtnPrev.setEnabled(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void enableButtons(boolean b) {
/* 277 */     this.m_BtnCancel.setEnabled(b);
/* 278 */     this.m_BtnNext.setEnabled(b);
/* 279 */     this.m_BtnPrev.setEnabled(b);
/* 280 */     if (b)
/* 281 */       setPrevState(); 
/* 282 */     if (this.m_CenterPanel != null) {
/* 283 */       this.m_CenterPanel.setEnabled(b);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void setButtonTexts() {
/* 288 */     if (this.m_BtnPrev != null) {
/* 289 */       this.m_BtnPrev.setText(ms_Captions[2]);
/*     */     }
/* 291 */     if (this.m_BtnNext != null) {
/* 292 */       this.m_BtnNext.setText(ms_Captions[3]);
/*     */     }
/* 294 */     if (this.m_BtnCancel != null) {
/* 295 */       this.m_BtnCancel.setText(ms_Captions[4]);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void closeDialog() {
/* 300 */     dispose();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 305 */     JLbsWizardDialog dlg = new JLbsWizardDialog();
/* 306 */     dlg.setTitle("Wizard Dialog Example");
/* 307 */     dlg.setModal(true);
/* 308 */     dlg.setPaneControl(new JFirstPage());
/* 309 */     dlg.show();
/* 310 */     System.exit(0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\wizard\JLbsWizardDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */