/*     */ package com.lbs.test;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComboBox;
/*     */ import com.lbs.controls.ILbsComboFilterListener;
/*     */ import com.lbs.controls.JLbsComboBox;
/*     */ import com.lbs.controls.JLbsComboEdit;
/*     */ import com.lbs.controls.JLbsImageButton;
/*     */ import com.lbs.controls.JLbsListBox;
/*     */ import com.lbs.controls.JLbsMenuButton;
/*     */ import com.lbs.controls.JLbsTabbedPane;
/*     */ import com.lbs.controls.buttonpanel.JLbsPrefixPanel;
/*     */ import com.lbs.controls.datedit.JLbsDateEdit;
/*     */ import com.lbs.controls.datedit.JLbsDateEditWithCalendar;
/*     */ import com.lbs.controls.datedit.JLbsTimeDuration;
/*     */ import com.lbs.controls.datedit.JLbsTimeEdit;
/*     */ import com.lbs.controls.groupbox.ILbsButtonGroupListener;
/*     */ import com.lbs.controls.groupbox.JLbsButtonStateChangeEvent;
/*     */ import com.lbs.controls.groupbox.JLbsCheckBoxGroup;
/*     */ import com.lbs.controls.groupbox.JLbsGroupBorder;
/*     */ import com.lbs.controls.groupbox.JLbsRadioButtonGroup;
/*     */ import com.lbs.controls.maskededit.JLbsMaskedEdit;
/*     */ import com.lbs.controls.maskededit.JLbsTextEdit;
/*     */ import com.lbs.controls.numericedit.JLbsNumericEdit;
/*     */ import com.lbs.controls.numericedit.JLbsNumericEditWithCalculator;
/*     */ import com.lbs.invoke.RttiUtil;
/*     */ import com.lbs.util.JLbsMessageBox;
/*     */ import com.lbs.util.JLbsOpenWindowListing;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import javax.swing.InputVerifier;
/*     */ import javax.swing.JApplet;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JEditorPane;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JToolBar;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.Timer;
/*     */ import javax.swing.UIManager;
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
/*     */ public class UIComponentTest
/*     */   extends JApplet
/*     */   implements ILbsButtonGroupListener
/*     */ {
/*     */   JLbsNumericEdit editNumeric;
/*     */   JLbsTimeEdit editTime;
/*     */   JLbsPrefixPanel editPrefix;
/*  75 */   DebugFocusAdapter focusAdapter = new DebugFocusAdapter();
/*     */   
/*     */   Hashtable uiDefaults;
/*     */   int themeIndex;
/*     */   Timer timer;
/*     */   
/*     */   public void init() {
/*  82 */     getContentPane().setLayout((LayoutManager)null);
/*  83 */     setSize(1000, 500);
/*  84 */     this.editNumeric = createNumericEdit(false);
/*  85 */     add(createLabel("NumericEdit (int)"), 10, 10, 150, 22);
/*  86 */     add((JComponent)this.editNumeric, 170, 10, 200, 26);
/*  87 */     add(createLabel("NumericEdit (real)"), 10, 40, 150, 22);
/*  88 */     add((JComponent)createNumericEdit(true), 170, 40, 200, 22);
/*  89 */     add(createLabel("DateEdit"), 10, 70, 150, 22);
/*  90 */     add((JComponent)createDateEdit(false), 170, 70, 200, 22);
/*  91 */     add((JComponent)createDateEdit(true), 388, 70, 200, 22);
/*  92 */     add(createLabel("TimeEdit"), 10, 100, 150, 22);
/*  93 */     add((JComponent)(this.editTime = createTimeEdit()), 170, 100, 200, 22);
/*  94 */     add(createMemoEdit(), 388, 100, 200, 70);
/*  95 */     add(createLabel("ComboBox"), 10, 130, 150, 22);
/*  96 */     add((JComponent)createComboBox(), 170, 130, 200, 22);
/*  97 */     add(createLabel("CheckBoxGroup"), 10, 160, 150, 22);
/*  98 */     add((JComponent)createCheckBoxGroup(), 170, 160, 400, 76);
/*  99 */     add(createLabel("RadioButtonGroup"), 10, 240, 150, 22);
/* 100 */     add((JComponent)createRadioButtonGroup(), 170, 240, 400, 76);
/* 101 */     add(createLabel("EditWithCombo"), 10, 340, 150, 22);
/* 102 */     add((JComponent)createComboEdit(), 170, 340, 200, 22);
/* 103 */     add(createLabel("TextEditWithLimit"), 10, 370, 150, 22);
/* 104 */     add((JComponent)createLimitedTextEdit(), 170, 370, 200, 22);
/* 105 */     add(createLabel("TextEditPrefix"), 10, 400, 150, 22);
/* 106 */     add((JComponent)(this.editPrefix = createTextWithPrefix()), 170, 400, 200, 22);
/* 107 */     add(createThemeButton(), 170, 470, 200, 22);
/* 108 */     add(createListBox(), 388, 330, 180, 120);
/* 109 */     add(createTabControl(), 600, 10, 340, 100);
/* 110 */     add(createToolbarControl(), 600, 110, 340, 48);
/* 111 */     add((JComponent)new JLbsImageButton(), 600, 170, 32, 32);
/* 112 */     JLbsMenuButton menuBtn = new JLbsMenuButton(null, new JLbsStringList("Ali~1|Veli~2|Deli~3"));
/*     */     
/* 114 */     add((JComponent)menuBtn, 640, 170, 35, 22);
/* 115 */     add((JComponent)createNumericEditWithCalculator(), 600, 220, 200, 22);
/*     */     
/* 117 */     JEditorPane pane = new JEditorPane();
/* 118 */     pane.setFocusable(true);
/*     */     
/* 120 */     add(pane, 1000, 100, 200, 200);
/*     */ 
/*     */     
/*     */     try {
/* 124 */       updateTheme(2);
/* 125 */       RttiUtil.setProperty(this, "Edit.Text", "1234567890");
/* 126 */       add((JComponent)createTimeEdit(), 620, 400, 200, 32);
/* 127 */       JLbsDateEdit edit = new JLbsDateEdit("dd/mm/yy");
/* 128 */       edit.setDisplayFormat(14);
/* 129 */       edit.setBorder(null);
/* 130 */       add((JComponent)edit, 620, 440, 200, 32);
/*     */     
/*     */     }
/* 133 */     catch (Exception e) {
/*     */       
/* 135 */       System.out.println(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void add(JComponent comp, int x, int y, int width, int height) {
/* 142 */     getContentPane().add(comp);
/* 143 */     if (width < 0 || height < 0) {
/*     */       
/* 145 */       comp.setLocation(x, y);
/* 146 */       comp.setSize(comp.getPreferredSize());
/*     */     } else {
/*     */       
/* 149 */       comp.setBounds(x, y, width, height);
/*     */     } 
/*     */   }
/*     */   
/*     */   private JLabel createLabel(String caption) {
/* 154 */     JLabel label = new JLabel();
/* 155 */     label.setText(caption);
/* 156 */     return label;
/*     */   }
/*     */ 
/*     */   
/*     */   private JLbsNumericEdit createNumericEdit(boolean bReal) {
/* 161 */     JLbsNumericEdit edit = new JLbsNumericEdit(bReal);
/*     */ 
/*     */     
/* 164 */     if (bReal) {
/*     */ 
/*     */       
/* 167 */       BigDecimal d = new BigDecimal("20.000100");
/* 168 */       edit.setNumberFormatter(4, 0, 2, null, true);
/* 169 */       edit.setNumber(d);
/*     */     }
/*     */     else {
/*     */       
/* 173 */       edit.setNumberFormatter(4, 0, 3, null, true);
/*     */     } 
/*     */     
/* 176 */     return edit;
/*     */   }
/*     */ 
/*     */   
/*     */   private JLbsDateEditWithCalendar createDateEdit(boolean bExtMenu) {
/* 181 */     JLbsDateEdit edit = new JLbsDateEdit(15);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 202 */     JLbsDateEditWithCalendar calendar = new JLbsDateEditWithCalendar(edit);
/* 203 */     edit.setDisplayFormat(14);
/* 204 */     calendar.setShowWeeks(true);
/* 205 */     if (bExtMenu) {
/* 206 */       calendar.addExtMenuButton();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 214 */     calendar.addLbsFocusListener(this.focusAdapter);
/* 215 */     return calendar;
/*     */   }
/*     */ 
/*     */   
/*     */   private JLbsTimeEdit createTimeEdit() {
/* 220 */     JLbsTimeEdit timeEdit = new JLbsTimeEdit(0, true, true, "Rolex");
/* 221 */     timeEdit.setBorder(null);
/* 222 */     return timeEdit;
/*     */   }
/*     */ 
/*     */   
/*     */   private JComponent createMemoEdit() {
/* 227 */     JTextArea memo = new JTextArea();
/* 228 */     memo.setBorder(UIManager.getBorder("TextArea.border"));
/* 229 */     return memo;
/*     */   }
/*     */ 
/*     */   
/*     */   private JLbsPrefixPanel createTextWithPrefix() {
/* 234 */     JLbsPrefixPanel prefix = new JLbsPrefixPanel();
/* 235 */     prefix.setPrefix("MUH08");
/*     */     
/* 237 */     prefix.setPrefixColor(Color.blue);
/* 238 */     return prefix;
/*     */   }
/*     */ 
/*     */   
/*     */   private JLbsComboBox createComboBox() {
/* 243 */     JLbsComboBox combo = new JLbsComboBox();
/*     */ 
/*     */     
/* 246 */     combo.addItem("Ali", 1);
/* 247 */     combo.addItem("Veli", 2);
/* 248 */     combo.addItem("Deli", 4);
/* 249 */     combo.addItem("Ali", 1);
/* 250 */     combo.addItem("Veli", 2);
/*     */ 
/*     */ 
/*     */     
/* 254 */     combo.addItemListener(new ComboListener());
/* 255 */     combo.setFilterListener(new DebugComboFilter());
/* 256 */     combo.setMaximumRowCount(4);
/* 257 */     combo.doFilterItems();
/* 258 */     combo.setSelectedItem(null);
/* 259 */     if (combo.hasItemTag(4))
/* 260 */       System.out.println("Item tag 4 = " + combo.getItemTagString(4)); 
/* 261 */     return combo;
/*     */   }
/*     */ 
/*     */   
/*     */   private JLbsCheckBoxGroup createCheckBoxGroup() {
/* 266 */     JLbsCheckBoxGroup checkGroup = new JLbsCheckBoxGroup();
/* 267 */     checkGroup.addItem("&Gokay", 1, true);
/* 268 */     checkGroup.addItem("&Arslan", 2, false);
/* 269 */     checkGroup.addItem("&Turgay", 4, true);
/* 270 */     checkGroup.addItem("T&ugrul", 8, false);
/* 271 */     checkGroup.addItem("A&yhan", 16, false);
/* 272 */     checkGroup.setControlEnabled(8, false);
/* 273 */     checkGroup.setColumnCount(3);
/*     */     
/* 275 */     checkGroup.setText("Group border test");
/* 276 */     checkGroup.setBorder((Border)new JLbsGroupBorder());
/* 277 */     checkGroup.setButtonGroupListener(this);
/*     */     
/* 279 */     return checkGroup;
/*     */   }
/*     */ 
/*     */   
/*     */   private JLbsRadioButtonGroup createRadioButtonGroup() {
/* 284 */     JLbsRadioButtonGroup radioGroup = new JLbsRadioButtonGroup();
/* 285 */     radioGroup.addItem("&Arslan", 2, false);
/* 286 */     radioGroup.addItem("&Turgay", 4, true);
/* 287 */     radioGroup.addItem("&Gokay", 1, true);
/* 288 */     radioGroup.addItem("T&ugrul", 8, false);
/* 289 */     radioGroup.addItem("A&yhan", 16, false);
/* 290 */     radioGroup.setColumnCount(3);
/* 291 */     radioGroup.addLbsFocusListener(this.focusAdapter);
/* 292 */     radioGroup.setButtonGroupListener(this);
/* 293 */     return radioGroup;
/*     */   }
/*     */ 
/*     */   
/*     */   private JComponent createListBox() {
/* 298 */     JScrollPane scrollPane = new JScrollPane();
/* 299 */     JLbsListBox list = new JLbsListBox();
/* 300 */     list.setAutoscrolls(true);
/* 301 */     list.addItem("Ali");
/* 302 */     list.addItem("Warning", 1);
/* 303 */     list.addItem("Error", 4);
/* 304 */     list.addItem("Information", 3);
/* 305 */     list.addItem("Question", 2);
/* 306 */     list.addItem("Information: Lazim oldu ancak bu defa cok uzun kaliyor arkadas", 3);
/* 307 */     list.addItem("Extra");
/*     */     
/* 309 */     list.addItem("Extra");
/* 310 */     list.addItem("Extra");
/* 311 */     list.addItem("Extra");
/* 312 */     list.addItem("Extra");
/* 313 */     list.addItem("Extra");
/* 314 */     list.addItem("Extra");
/* 315 */     list.addItem("Extra");
/* 316 */     list.addItem("Extra");
/* 317 */     list.addItem("Extra");
/* 318 */     list.addItem("Extra");
/* 319 */     list.addItem("Extra");
/*     */     
/* 321 */     list.revalidate();
/* 322 */     scrollPane.setBorder((Border)new JLbsGroupBorder("Pane"));
/* 323 */     scrollPane.getViewport().setView((Component)list);
/* 324 */     return scrollPane;
/*     */   }
/*     */ 
/*     */   
/*     */   private JLbsComboEdit createComboEdit() {
/* 329 */     JLbsComboEdit comboEdit = new JLbsComboEdit(true);
/*     */     
/* 331 */     comboEdit.setActionListener(new DebugComboEditEvent());
/* 332 */     comboEdit.addLbsFocusListener(this.focusAdapter);
/* 333 */     comboEdit.setBackground(Color.RED);
/* 334 */     comboEdit.setForeground(Color.WHITE);
/* 335 */     ((JLbsMaskedEdit)comboEdit.getEditControl()).setBackground(Color.RED);
/* 336 */     return comboEdit;
/*     */   }
/*     */ 
/*     */   
/*     */   private JLbsNumericEditWithCalculator createNumericEditWithCalculator() {
/* 341 */     JLbsNumericEditWithCalculator numEdit = new JLbsNumericEditWithCalculator();
/* 342 */     numEdit.setNumberFormatter(4, 0, 8, null, true);
/* 343 */     numEdit.addLbsFocusListener(this.focusAdapter);
/* 344 */     return numEdit;
/*     */   }
/*     */ 
/*     */   
/*     */   private JLbsTextEdit createLimitedTextEdit() {
/* 349 */     JLbsTextEdit edit = new JLbsTextEdit(10);
/* 350 */     edit.setEditStyle(1);
/* 351 */     JLbsTextEdit jLbsTextEdit1 = edit;
/* 352 */     jLbsTextEdit1.setInputVerifier(new JLbsMaskedEditInputVerifier());
/* 353 */     return edit;
/*     */   }
/*     */ 
/*     */   
/*     */   private JButton createThemeButton() {
/* 358 */     JButton result = new JButton("A new button style");
/* 359 */     result.setMnemonic('n');
/* 360 */     result.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 364 */             JLbsTimeDuration time = UIComponentTest.this.editTime.getTime();
/* 365 */             System.out.println(time);
/* 366 */             System.out.println(time.toCalendar());
/* 367 */             System.out.println(new JLbsTimeDuration(time.toCalendar()));
/* 368 */             System.out.println(JLbsTimeDuration.fromInteger(time.toInteger()));
/* 369 */             UIComponentTest.this.editPrefix.setPrefix("Ali veli deli");
/* 370 */             UIComponentTest.this.editNumeric.setSize(100, 100);
/*     */           }
/*     */         });
/*     */     
/* 374 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private JTabbedPane createTabControl() {
/* 379 */     JLbsTabbedPane jLbsTabbedPane = new JLbsTabbedPane();
/* 380 */     JPanel panel = new JPanel();
/* 381 */     JLbsMaskedEdit jLbsMaskedEdit = new JLbsMaskedEdit();
/* 382 */     jLbsMaskedEdit.setPreferredSize(new Dimension(100, 22));
/* 383 */     panel.add((Component)jLbsMaskedEdit);
/* 384 */     jLbsTabbedPane.addTab("General", panel);
/* 385 */     jLbsTabbedPane.addTab("Additional", new JPanel());
/* 386 */     jLbsTabbedPane.addTab("Support", new JPanel());
/* 387 */     jLbsTabbedPane.addTab("Test", new JPanel());
/* 388 */     return (JTabbedPane)jLbsTabbedPane;
/*     */   }
/*     */ 
/*     */   
/*     */   private JButton createToolButton(String s) {
/* 393 */     JButton btn = new JButton();
/* 394 */     btn.setText(s);
/* 395 */     btn.setPreferredSize(new Dimension(50, 48));
/* 396 */     btn.setSize(btn.getPreferredSize());
/* 397 */     return btn;
/*     */   }
/*     */ 
/*     */   
/*     */   private JToolBar createToolbarControl() {
/* 402 */     JToolBar toolbar = new JToolBar();
/* 403 */     toolbar.setLayout(new GridLayout(1, 4));
/* 404 */     toolbar.add(createToolButton("Save"));
/* 405 */     toolbar.add(createToolButton("Load"));
/* 406 */     toolbar.add(createToolButton("Delete"));
/* 407 */     toolbar.add(createToolButton("Rename"));
/* 408 */     toolbar.setFloatable(false);
/* 409 */     return toolbar;
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateTheme(int theme) {
/* 414 */     String themeName = "javax.swing.plaf.metal.MetalLookAndFeel";
/* 415 */     if (theme < 0) {
/* 416 */       theme = this.themeIndex;
/*     */     } else {
/* 418 */       this.themeIndex = theme;
/* 419 */     }  switch (theme % 3) {
/*     */       
/*     */       case 1:
/* 422 */         themeName = "com.lbs.laf.xp.LookAndFeel";
/*     */         break;
/*     */       case 2:
/* 425 */         themeName = "com.lbs.laf.mac.LookAndFeel";
/*     */         break;
/*     */       default:
/* 428 */         themeName = "javax.swing.plaf.metal.MetalLookAndFeel";
/*     */         break;
/*     */     } 
/*     */     
/*     */     try {
/* 433 */       UIManager.setLookAndFeel(themeName);
/* 434 */       SwingUtilities.updateComponentTreeUI(this);
/* 435 */       ArrayList<Component> list = JLbsOpenWindowListing.getOpenDialogs();
/* 436 */       for (int i = 0; i < list.size(); i++) {
/* 437 */         SwingUtilities.updateComponentTreeUI(list.get(i));
/*     */       }
/* 439 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsNumericEdit getEdit() {
/* 450 */     return this.editNumeric;
/*     */   }
/*     */   
/*     */   class DebugFocusAdapter
/*     */     implements FocusListener
/*     */   {
/*     */     public void focusGained(FocusEvent arg0) {
/* 457 */       internalProcess("Got Focus:", arg0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void focusLost(FocusEvent arg0) {
/* 462 */       internalProcess("Lost Focus:", arg0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void internalProcess(String prefix, FocusEvent arg0) {
/* 467 */       Component source = arg0.getComponent();
/* 468 */       System.out.print(prefix);
/* 469 */       System.out.println(source.getClass().toString());
/*     */     }
/*     */   }
/*     */   
/*     */   class DebugComboFilter
/*     */     implements ILbsComboFilterListener
/*     */   {
/*     */     public boolean getFilterMask(ILbsComboBox combo, int index, int iTag) {
/* 477 */       return (index > 0);
/*     */     }
/*     */   }
/*     */   
/*     */   class DebugComboEditEvent
/*     */     implements ActionListener
/*     */   {
/*     */     public void actionPerformed(ActionEvent arg0) {
/* 485 */       JLbsMessageBox box = new JLbsMessageBox("Ali");
/* 486 */       box.setModal(false);
/* 487 */       box.show();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   class JLbsMaskedEditInputVerifier
/*     */     extends InputVerifier
/*     */   {
/*     */     public boolean verify(JComponent comp) {
/* 496 */       JLbsMaskedEdit edit = (JLbsMaskedEdit)comp;
/* 497 */       if (edit.getText().compareToIgnoreCase("A") == 0) {
/* 498 */         return false;
/*     */       }
/*     */       try {
/* 501 */         RttiUtil.setProperty(edit, "Text", null);
/*     */       }
/* 503 */       catch (Exception e) {
/*     */         
/* 505 */         System.out.println(e);
/*     */       } 
/*     */       
/* 508 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean shouldYieldFocus(JComponent input) {
/* 513 */       return verify(input);
/*     */     }
/*     */   }
/*     */   
/*     */   class ComboListener
/*     */     implements ItemListener
/*     */   {
/*     */     public void itemStateChanged(ItemEvent e) {
/* 521 */       JLbsComboBox combo = (JLbsComboBox)e.getSource();
/* 522 */       if (combo.isPopupVisible())
/*     */       {
/* 524 */         System.out.println("Combo item changed : " + e);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void buttonStateChanged(JLbsButtonStateChangeEvent e) {
/* 531 */     System.out.println("Group state changed " + e.getGroupBox().getClass() + " oldMask = " + e.getOldMask() + " newMask = " + 
/* 532 */         e.getNewMask() + " buttonTag = " + e.getButtonTag());
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\test\UIComponentTest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */