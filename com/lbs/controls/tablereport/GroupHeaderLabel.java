/*     */ package com.lbs.controls.tablereport;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.Vector;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.event.PopupMenuEvent;
/*     */ import javax.swing.event.PopupMenuListener;
/*     */ 
/*     */ public class GroupHeaderLabel
/*     */   extends JPanel
/*     */ {
/*     */   private JLabel label;
/*     */   private JPanel controlPanel;
/*     */   private SortDirectionButton btnSort;
/*     */   private JCheckBox filterCheck;
/*     */   private FilterList filterList;
/*     */   private TableReportHeaderInfo headerInfo;
/*     */   private JPopupMenu menu;
/*     */   
/*     */   public GroupHeaderLabel(TableReportHeaderInfo info) {
/*  33 */     this.headerInfo = info;
/*  34 */     initGUI();
/*  35 */     init();
/*     */   }
/*     */   
/*     */   private void init() {
/*  39 */     if ((this.label.getSize()).width < 50) {
/*  40 */       this.label.setSize(50, 0);
/*  41 */       this.label.setPreferredSize(this.label.getSize());
/*     */     } 
/*     */     
/*  44 */     GroupLabelDragSource dsGroupLabel = new GroupLabelDragSource(this);
/*     */   }
/*     */   
/*     */   public void toggle() {
/*  48 */     this.btnSort.doClick();
/*     */   }
/*     */   
/*     */   public void clearFilter() {
/*  52 */     this.filterList.clearFilter();
/*  53 */     this.filterCheck.setSelected(false);
/*     */   }
/*     */   
/*     */   public void setSelected(boolean selected) {
/*  57 */     this.filterCheck.setSelected(selected);
/*     */   }
/*     */   
/*     */   public void restoreFilter() {
/*  61 */     this.filterList.restoreFilter();
/*     */     
/*  63 */     boolean selected = (this.headerInfo.getFilteredValues().size() > 0);
/*  64 */     this.filterCheck.setSelected(selected);
/*     */   }
/*     */   
/*     */   public Vector<FilterListCellValue> getExclusionList() {
/*  68 */     return this.filterList.getExclusionList();
/*     */   }
/*     */   
/*     */   public void initPopupMenu(boolean contains) {
/*  72 */     if (this.menu == null) {
/*  73 */       this.menu = new JPopupMenu();
/*  74 */       this.filterList = new FilterList(this.headerInfo);
/*  75 */       this.filterList.addAll(this.headerInfo.getDistinctValues(), !contains);
/*  76 */       JScrollPane sp = new JScrollPane(this.filterList);
/*     */       
/*  78 */       this.menu.add(sp);
/*  79 */       this.menu.setSize(200, 200);
/*     */       
/*  81 */       this.menu.addPopupMenuListener(new PopupMenuListener() {
/*     */             public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
/*  83 */               GroupHeaderLabel.this.filterList.saveCurrentState();
/*     */             }
/*     */             
/*     */             public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
/*     */               try {
/*  88 */                 if (GroupHeaderLabel.this.filterList.hasChanged()) {
/*  89 */                   GroupHeaderLabel.this.filterList.refreshHeaderInfo();
/*     */                   
/*  91 */                   if (GroupHeaderLabel.this.filterList.hasSelectedValue()) {
/*  92 */                     GroupHeaderLabel.this.firePropertyChange("filter.changed", (Object)null, GroupHeaderLabel.this.headerInfo);
/*  93 */                     GroupHeaderLabel.this.filterCheck.setSelected(GroupHeaderLabel.this.hasFilteredValue());
/*     */                   } else {
/*     */                     
/*  96 */                     GroupHeaderLabel.this.filterCheck.setSelected(false);
/*  97 */                     JOptionPane.showMessageDialog(null, TableReportConstants.STRINGS2[12]);
/*     */                   } 
/*     */                 } 
/* 100 */               } catch (Exception ex) {
/* 101 */                 ex.printStackTrace();
/*     */               } 
/*     */             }
/*     */ 
/*     */             
/*     */             public void popupMenuCanceled(PopupMenuEvent e) {}
/*     */           });
/*     */     } 
/*     */   }
/*     */   
/*     */   public void showFilter(boolean show) {
/* 112 */     this.filterCheck.setVisible(show);
/*     */   }
/*     */   
/*     */   private boolean hasFilteredValue() {
/*     */     int i;
/* 117 */     boolean control = false;
/*     */     
/* 119 */     for (int index = 1; index < this.headerInfo.getDistinctValues().size(); index++) {
/* 120 */       FilterListCellValue flcv = this.headerInfo.getDistinctValues().get(index);
/* 121 */       i = control | (flcv.isIncluded() ? 0 : 1);
/*     */     } 
/*     */     
/* 124 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 129 */     int width = (this.label.getPreferredSize()).width + (this.controlPanel.getPreferredSize()).width + 5;
/* 130 */     int height = 22;
/*     */     
/* 132 */     return new Dimension(width, height);
/*     */   }
/*     */   
/*     */   private void initGUI() {
/* 136 */     setBorder(BorderFactory.createLineBorder(Color.black));
/* 137 */     setLayout(new BorderLayout());
/*     */     
/* 139 */     this.label = new JLabel(this.headerInfo.getTitle());
/* 140 */     this.label.setSize(this.label.getPreferredSize());
/*     */     
/* 142 */     this.controlPanel = new JPanel();
/* 143 */     this.controlPanel.setSize(42, 22);
/* 144 */     this.controlPanel.setPreferredSize(this.controlPanel.getSize());
/* 145 */     this.controlPanel.setLayout(new GridLayout(1, 2, 0, 0));
/*     */     
/* 147 */     this.btnSort = new SortDirectionButton(this.headerInfo);
/* 148 */     this.btnSort.setSize(22, 22);
/*     */     
/* 150 */     this.filterCheck = new JCheckBox();
/* 151 */     this.filterCheck = new JCheckBox("");
/* 152 */     this.filterCheck.setBorder((Border)null);
/* 153 */     this.filterCheck.setOpaque(false);
/* 154 */     this.filterCheck.setSelected(hasFilteredValue());
/*     */     
/* 156 */     this.filterCheck.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent e) {
/* 158 */             GroupHeaderLabel.this.filterCheck.setSelected(GroupHeaderLabel.this.hasFilteredValue());
/* 159 */             GroupHeaderLabel.this.menu.show(GroupHeaderLabel.this.filterCheck, 0, GroupHeaderLabel.this.getHeight());
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 164 */     this.controlPanel.add(this.btnSort);
/* 165 */     this.controlPanel.add(this.filterCheck);
/*     */     
/* 167 */     add(this.label, "Center");
/* 168 */     add(this.controlPanel, "East");
/*     */   }
/*     */   
/*     */   public TableReportHeaderInfo getHeaderInfo() {
/* 172 */     return this.headerInfo;
/*     */   }
/*     */   
/*     */   public SortDirectionButton getBtnSort() {
/* 176 */     return this.btnSort;
/*     */   }
/*     */   
/*     */   public JPopupMenu getMenu() {
/* 180 */     return this.menu;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\GroupHeaderLabel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */