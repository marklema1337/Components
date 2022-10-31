/*     */ package com.lbs.controls.tablereport;
/*     */ 
/*     */ import com.lbs.laf.common.SkinnedComboBoxButton;
/*     */ import com.lbs.laf.mac.ComboBoxUI;
/*     */ import com.lbs.laf.mac.DefaultTheme;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.Vector;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.CellRendererPane;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.plaf.metal.MetalComboBoxIcon;
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
/*     */ public class ComboCheckBox
/*     */   extends JPanel
/*     */ {
/*     */   private JLabel label;
/*     */   private SkinnedComboBoxButton button;
/*     */   private JPopupMenu menu;
/*     */   private FilterList list;
/*     */   
/*     */   public ComboCheckBox() {
/*  48 */     initGUI();
/*     */   }
/*     */ 
/*     */   
/*     */   private void initGUI() {
/*  53 */     setBorder(BorderFactory.createLineBorder(Color.black));
/*  54 */     setLayout(new BorderLayout());
/*  55 */     setSize(100, 22);
/*  56 */     setPreferredSize(getSize());
/*     */     
/*  58 */     this.label = new JLabel();
/*  59 */     this.label.setOpaque(true);
/*  60 */     this.label.setBackground(Color.white);
/*  61 */     this.label.addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mousePressed(MouseEvent e)
/*     */           {
/*  65 */             ComboCheckBox.this.menu.show(ComboCheckBox.this, 0, ComboCheckBox.this.getHeight());
/*     */           }
/*     */         });
/*     */     
/*  69 */     this.button = createArrowButton();
/*  70 */     this.button.setPreferredSize(new Dimension(22, 22));
/*  71 */     this.button.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  75 */             ComboCheckBox.this.menu.show(ComboCheckBox.this, 0, ComboCheckBox.this.getHeight());
/*     */           }
/*     */         });
/*     */     
/*  79 */     add(this.label, "Center");
/*  80 */     add((Component)this.button, "East");
/*     */     
/*  82 */     this.menu = new JPopupMenu();
/*  83 */     this.menu.setLayout(new BorderLayout());
/*  84 */     DefaultListModel<E> model = new DefaultListModel();
/*  85 */     this.list = new FilterList();
/*  86 */     this.list.setVisibleRowCount(8);
/*  87 */     this.list.setModel(model);
/*  88 */     JScrollPane sp = new JScrollPane(this.list);
/*  89 */     sp.setHorizontalScrollBarPolicy(31);
/*  90 */     Dimension dim = sp.getPreferredSize();
/*  91 */     dim.width = getWidth();
/*  92 */     sp.setPreferredSize(dim);
/*  93 */     this.menu.add(sp, "Center");
/*     */   }
/*     */ 
/*     */   
/*     */   private SkinnedComboBoxButton createArrowButton() {
/*  98 */     JComboBox combo = new JComboBox();
/*  99 */     SkinnedComboBoxButton button = new SkinnedComboBoxButton(combo, new MetalComboBoxIcon(), false, new CellRendererPane(), 
/* 100 */         new JList());
/* 101 */     button.setMargin(new Insets(0, 0, 0, 0));
/* 102 */     button.m_SkinArrow = DefaultTheme.getSkinImage(ComboBoxUI.class, "comboboxarrow.png", 4, 0);
/*     */     
/* 104 */     button.m_SkinButton = DefaultTheme.getSkinImage(ComboBoxUI.class, JLbsConstants.DESKTOP_MODE ? 
/* 105 */         "lookupbuttondesktop.png" : 
/* 106 */         "lookupbutton.png", 9, 0);
/* 107 */     button.m_SkinCombo = DefaultTheme.getSkinImage(ComboBoxUI.class, "combobox.png", 4, 0, 0, 21, 0);
/* 108 */     button.m_SkinIcon = DefaultTheme.getImageIcon(ComboBoxUI.class, "comboboxfocus.png");
/* 109 */     button.m_ButtonWidth = 21;
/* 110 */     button.m_ButtonBorder = 2;
/*     */     
/* 112 */     return button;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasSelectedField() {
/* 117 */     return this.list.hasSelectedValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector<Integer> getSelectedIndexes() {
/* 122 */     Vector<Integer> vector = new Vector<>();
/*     */     
/* 124 */     DefaultListModel<FilterListCellValue> model = (DefaultListModel)this.list.getModel();
/*     */     
/* 126 */     for (int index = 1; index < model.getSize(); index++) {
/*     */       
/* 128 */       FilterListCellValue flcv = model.getElementAt(index);
/* 129 */       if (flcv.isIncluded() && flcv.getValue() instanceof PairValue) {
/*     */         
/* 131 */         PairValue pairValue = (PairValue)flcv.getValue();
/* 132 */         vector.add((Integer)pairValue.getValue());
/*     */       } 
/*     */     } 
/*     */     
/* 136 */     return vector;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(FilterListCellValue flcv) {
/* 141 */     DefaultListModel<FilterListCellValue> model = (DefaultListModel)this.list.getModel();
/* 142 */     if (model.getSize() == 0)
/*     */     {
/* 144 */       this.label.setText(flcv.getValue().toString());
/*     */     }
/*     */     
/* 147 */     model.addElement(flcv);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAllItems() {
/* 152 */     DefaultListModel model = (DefaultListModel)this.list.getModel();
/* 153 */     model.removeAllElements();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\ComboCheckBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */