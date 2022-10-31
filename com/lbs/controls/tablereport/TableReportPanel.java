/*     */ package com.lbs.controls.tablereport;
/*     */ 
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import info.clearthought.layout.TableLayout;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.io.File;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.event.PopupMenuEvent;
/*     */ import javax.swing.event.PopupMenuListener;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import javax.swing.table.TableModel;
/*     */ import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
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
/*     */ public class TableReportPanel
/*     */   extends JTabbedPane
/*     */   implements PropertyChangeListener, ActionListener, ITableReportOperations
/*     */ {
/*     */   private TableReportInfo reportInfo;
/*     */   private JPanel tablePanel;
/*     */   private GraphicPanel graphicPanel;
/*     */   private HeaderGroupPanel groupPanel;
/*     */   private HeaderLabel headerLabel;
/*     */   private TableReportTreeTable treeTable;
/*     */   private JPanel bottomPanel;
/*     */   private FilterPanel filterPanel;
/*     */   private AggregateTable aggregateTable;
/*     */   private JScrollPane aggregateScrollPane;
/*     */   private JPopupMenu aggregateMenu;
/*     */   private JPopupMenu headerMenu;
/*     */   private TableReportTreeTableModel model;
/*     */   private TableLayout filterPanelTableLayout;
/*     */   private Point cursorLocation;
/*     */   private JPopupMenu tableMenu;
/*     */   private HeaderDragSource dsTableHeader;
/*     */   
/*     */   public TableReportPanel(TableReportInfo info) {
/*  70 */     this.reportInfo = info;
/*  71 */     this.model = creteTreeTableModel();
/*  72 */     initGUI();
/*     */   }
/*     */ 
/*     */   
/*     */   private TableReportTreeTable createTreeTable() {
/*  77 */     this.model = creteTreeTableModel();
/*  78 */     this.treeTable = new TableReportTreeTable(this.model, this.reportInfo);
/*  79 */     this.treeTable.getTableHeader().setPreferredSize(new Dimension((this.treeTable.getPreferredSize()).width, 20));
/*  80 */     this.treeTable.setRowHeight(18);
/*  81 */     this.treeTable.setRootVisible(false);
/*  82 */     this.treeTable.setShowGrid(true, true);
/*  83 */     this.treeTable.setShowsRootHandles(true);
/*  84 */     this.treeTable.setColumnControlVisible(true);
/*  85 */     this.treeTable.setHorizontalScrollEnabled(true);
/*  86 */     this.treeTable.setRowSelectionAllowed(true);
/*  87 */     this.treeTable.setSelectionMode(0);
/*     */     
/*  89 */     return this.treeTable;
/*     */   }
/*     */ 
/*     */   
/*     */   private TableReportTreeTableModel creteTreeTableModel() {
/*  94 */     RowObject rootObject = new RowObject(Integer.valueOf(-1), new Object[] { "" });
/*  95 */     DefaultMutableTreeTableNode root = new DefaultMutableTreeTableNode(rootObject);
/*  96 */     TableReportTreeTableModel model = new TableReportTreeTableModel(this.reportInfo.getDataList(), root, this.reportInfo.getHeaderNames());
/*     */     
/*  98 */     return model;
/*     */   }
/*     */ 
/*     */   
/*     */   public void fill() {
/* 103 */     this.model = creteTreeTableModel();
/* 104 */     this.treeTable.setModel((TableModel)this.model);
/*     */   }
/*     */ 
/*     */   
/*     */   private void initPopupMenu() {
/* 109 */     this.tableMenu = new JPopupMenu();
/*     */     
/* 111 */     JMenuItem item = this.tableMenu.add(TableReportConstants.STRINGS2[13]);
/* 112 */     item.setActionCommand("export_to_excel");
/* 113 */     item.addActionListener(this);
/*     */     
/* 115 */     JMenuItem itemRowColor = this.tableMenu.add(TableReportConstants.STRINGS4[0]);
/* 116 */     itemRowColor.setActionCommand("row_color");
/* 117 */     itemRowColor.addActionListener(this);
/*     */     
/* 119 */     this.tableMenu.addSeparator();
/*     */     
/* 121 */     JMenuItem itemSaveUser = this.tableMenu.add(TableReportConstants.STRINGS3[9]);
/* 122 */     itemSaveUser.setActionCommand("save_user");
/* 123 */     itemSaveUser.addActionListener(this);
/*     */     
/* 125 */     JMenuItem itemSaveAll = this.tableMenu.add(TableReportConstants.STRINGS3[10]);
/* 126 */     itemSaveAll.setActionCommand("save_all");
/* 127 */     itemSaveAll.setEnabled(this.reportInfo.isSuperUser());
/* 128 */     itemSaveAll.addActionListener(this);
/*     */     
/* 130 */     this.treeTable.setComponentPopupMenu(this.tableMenu);
/*     */   }
/*     */ 
/*     */   
/*     */   private void createHeaderMenu() {
/* 135 */     this.headerMenu = new JPopupMenu();
/* 136 */     JMenuItem itemExpandAll = this.headerMenu.add(TableReportConstants.STRINGS3[2]);
/* 137 */     itemExpandAll.setActionCommand("expand_all");
/* 138 */     itemExpandAll.addActionListener(this);
/*     */     
/* 140 */     JMenuItem itemCollapseAll = this.headerMenu.add(TableReportConstants.STRINGS3[3]);
/* 141 */     itemCollapseAll.setActionCommand("collapse_all");
/* 142 */     itemCollapseAll.addActionListener(this);
/*     */     
/* 144 */     this.headerLabel.setComponentPopupMenu(this.headerMenu);
/*     */   }
/*     */ 
/*     */   
/*     */   private void createAggregateMenu() {
/* 149 */     this.aggregateMenu = new JPopupMenu();
/* 150 */     JMenuItem itemSum = this.aggregateMenu.add(AggregateValue.AggregateFunctionType.SUM.getName());
/* 151 */     itemSum.setActionCommand(AggregateValue.AggregateFunctionType.SUM.getName());
/* 152 */     itemSum.addActionListener(this);
/*     */     
/* 154 */     JMenuItem itemMin = this.aggregateMenu.add(AggregateValue.AggregateFunctionType.MIN.getName());
/* 155 */     itemMin.setActionCommand(AggregateValue.AggregateFunctionType.MIN.getName());
/* 156 */     itemMin.addActionListener(this);
/*     */     
/* 158 */     JMenuItem itemMax = this.aggregateMenu.add(AggregateValue.AggregateFunctionType.MAX.getName());
/* 159 */     itemMax.setActionCommand(AggregateValue.AggregateFunctionType.MAX.getName());
/* 160 */     itemMax.addActionListener(this);
/*     */     
/* 162 */     JMenuItem itemCount = this.aggregateMenu.add(AggregateValue.AggregateFunctionType.COUNT.getName());
/* 163 */     itemCount.setActionCommand(AggregateValue.AggregateFunctionType.COUNT.getName());
/* 164 */     itemCount.addActionListener(this);
/*     */     
/* 166 */     JMenuItem itemAverage = this.aggregateMenu.add(AggregateValue.AggregateFunctionType.AVG.getName());
/* 167 */     itemAverage.setActionCommand(AggregateValue.AggregateFunctionType.AVG.getName());
/* 168 */     itemAverage.addActionListener(this);
/*     */     
/* 170 */     JMenuItem itemNone = this.aggregateMenu.add(TableReportConstants.STRINGS3[8]);
/* 171 */     itemNone.setActionCommand("none");
/* 172 */     itemNone.addActionListener(this);
/*     */     
/* 174 */     this.aggregateTable.addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mousePressed(MouseEvent mouseEvent)
/*     */           {
/* 178 */             if (mouseEvent.getButton() == 3) {
/*     */               
/* 180 */               TableReportPanel.this.cursorLocation = mouseEvent.getPoint();
/* 181 */               TableReportPanel.this.aggregateMenu.show((Component)TableReportPanel.this.aggregateTable, TableReportPanel.this.cursorLocation.x, TableReportPanel.this.cursorLocation.y);
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 186 */     this.aggregateMenu.addPopupMenuListener(new PopupMenuListener()
/*     */         {
/*     */           public void popupMenuWillBecomeVisible(PopupMenuEvent e)
/*     */           {
/* 190 */             Integer columnModelIndex = TableReportPanel.this.getSelectedColumnModelIndex();
/* 191 */             AggregateValue.AggregateFunctionType[] disabledTypes = { AggregateValue.AggregateFunctionType.AVG, AggregateValue.AggregateFunctionType.MAX, 
/* 192 */                 AggregateValue.AggregateFunctionType.MIN, AggregateValue.AggregateFunctionType.SUM };
/* 193 */             Vector<AggregateValue.AggregateFunctionType> vector = new Vector<>(Arrays.asList(disabledTypes));
/*     */             
/* 195 */             if (columnModelIndex != null) {
/*     */               
/* 197 */               boolean number = TableReportPanel.this.treeTable.isNumber(columnModelIndex.intValue()); byte b; int i; Component[] arrayOfComponent;
/* 198 */               for (i = (arrayOfComponent = TableReportPanel.this.aggregateMenu.getComponents()).length, b = 0; b < i; ) { Component c = arrayOfComponent[b];
/*     */                 
/* 200 */                 if (c instanceof JMenuItem) {
/*     */                   
/* 202 */                   JMenuItem item = (JMenuItem)c;
/* 203 */                   item.setEnabled(true);
/*     */                   
/* 205 */                   if (!item.getActionCommand().equals("none")) {
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 210 */                     AggregateValue.AggregateFunctionType type = AggregateValue.AggregateFunctionType.valueOf(item.getActionCommand());
/* 211 */                     if (vector.contains(type))
/*     */                     {
/* 213 */                       if (!number)
/*     */                       {
/* 215 */                         item.setEnabled(false);
/*     */                       }
/*     */                     }
/*     */                   } 
/*     */                 } 
/*     */                 b++; }
/*     */             
/*     */             } 
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
/*     */ 
/*     */ 
/*     */           
/*     */           public void popupMenuCanceled(PopupMenuEvent e) {}
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void initGUI() {
/* 239 */     setLayout(new BorderLayout());
/* 240 */     SwingUtilities.invokeLater(new Runnable()
/*     */         {
/*     */           
/*     */           public void run()
/*     */           {
/* 245 */             TableReportPanel.this.tablePanel = new JPanel();
/* 246 */             TableReportPanel.this.tablePanel.setLayout(new BorderLayout());
/* 247 */             TableReportPanel.this.tablePanel.add((Component)JLbsTableGridFactory.getTableGridPanel(TableReportPanel.this.reportInfo), "Center");
/* 248 */             TableReportPanel.this.removeAll();
/* 249 */             TableReportPanel.this.addTab("Tablo", TableReportPanel.this.tablePanel);
/* 250 */             TableReportPanel.this.graphicPanel = new GraphicPanel(TableReportPanel.this.reportInfo, TableReportPanel.this.model);
/* 251 */             TableReportPanel.this.addTab(TableReportConstants.STRINGS2[14], TableReportPanel.this.graphicPanel);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private DefaultTableModel createAggregateTableModel() {
/* 263 */     DefaultTableModel model = new DefaultTableModel();
/*     */     
/* 265 */     for (int index = 0; index < this.reportInfo.getHeaderNames().size(); index++)
/*     */     {
/* 267 */       model.addColumn(this.reportInfo.getHeaderNames().get(index));
/*     */     }
/*     */     
/* 270 */     return model;
/*     */   }
/*     */ 
/*     */   
/*     */   public void propertyChange(PropertyChangeEvent evt) {
/* 275 */     if (evt.getPropertyName().equals("drop")) {
/*     */       
/* 277 */       TableReportHeaderInfo headerInfo = (TableReportHeaderInfo)evt.getNewValue();
/*     */       
/* 279 */       if (this.groupPanel.contains(headerInfo)) {
/*     */         
/* 281 */         GroupHeaderLabel groupHeaderLabel = this.groupPanel.getGroupHeaderLabel(headerInfo);
/* 282 */         this.groupPanel.remove(groupHeaderLabel);
/* 283 */         this.groupPanel.add(groupHeaderLabel);
/* 284 */         this.treeTable.addGroupHeader(headerInfo);
/*     */         
/*     */         return;
/*     */       } 
/* 288 */       headerInfo.createDistinctValues(this.reportInfo.getDataList());
/* 289 */       this.treeTable.addGroupHeader(headerInfo);
/* 290 */       this.groupPanel.remove(headerInfo);
/*     */       
/* 292 */       GroupHeaderLabel label = new GroupHeaderLabel(headerInfo);
/* 293 */       label.initPopupMenu(this.groupPanel.contains(headerInfo));
/* 294 */       label.getBtnSort().addPropertyChangeListener(this);
/* 295 */       label.addPropertyChangeListener(this);
/*     */       
/* 297 */       this.groupPanel.add(label);
/* 298 */       this.groupPanel.revalidate();
/*     */       
/* 300 */       this.treeTable.expandAll();
/* 301 */       this.treeTable.createHeaderRenderers();
/* 302 */       recalculateAggregateValues();
/*     */     
/*     */     }
/* 305 */     else if (evt.getPropertyName().equals("sort.changed")) {
/*     */       
/* 307 */       TableReportHeaderInfo info = (TableReportHeaderInfo)evt.getNewValue();
/* 308 */       this.treeTable.changeSortDirection(info);
/* 309 */       this.treeTable.createHeaderRenderers();
/* 310 */       recalculateAggregateValues();
/*     */     
/*     */     }
/* 313 */     else if (evt.getPropertyName().equals("make.column.invisible")) {
/*     */       
/* 315 */       TableReportHeaderInfo info = (TableReportHeaderInfo)evt.getNewValue();
/* 316 */       int viewColumnIndex = this.treeTable.convertColumnIndexToView(info.getColumnIndex().intValue());
/*     */       
/* 318 */       if (viewColumnIndex != -1) {
/*     */         
/* 320 */         this.treeTable.getColumnExt(viewColumnIndex).setVisible(false);
/*     */       }
/*     */       else {
/*     */         
/* 324 */         this.treeTable.removeGroupHeader(info);
/* 325 */         this.groupPanel.remove(info);
/*     */       } 
/* 327 */       this.treeTable.createHeaderRenderers();
/*     */     
/*     */     }
/* 330 */     else if (evt.getPropertyName().equals("group.restored.visible")) {
/*     */       
/* 332 */       TableReportHeaderInfo headerInfo = (TableReportHeaderInfo)evt.getNewValue();
/* 333 */       this.groupPanel.remove(headerInfo);
/* 334 */       this.treeTable.removeGroupHeader(headerInfo.getColumnIndex(), true);
/*     */       
/* 336 */       firePropertyChange("filter.removed", (Object)null, headerInfo);
/*     */     
/*     */     }
/* 339 */     else if (evt.getPropertyName().equals("filter.changed")) {
/*     */       
/* 341 */       TableReportHeaderInfo headerInfo = (TableReportHeaderInfo)evt.getNewValue();
/* 342 */       updateFilterPanel(headerInfo);
/* 343 */       this.treeTable.group();
/* 344 */       this.treeTable.createHeaderRenderers();
/* 345 */       recalculateAggregateValues();
/*     */     
/*     */     }
/* 348 */     else if (evt.getPropertyName().equals("filter.removed")) {
/*     */       
/* 350 */       TableReportHeaderInfo headerInfo = (TableReportHeaderInfo)evt.getNewValue();
/* 351 */       this.filterPanel.remove(headerInfo);
/* 352 */       this.filterPanel.revalidate();
/*     */       
/* 354 */       headerInfo.clearFilter(true);
/* 355 */       this.groupPanel.clearFilter(headerInfo);
/* 356 */       this.treeTable.group();
/* 357 */       this.treeTable.restoreColumnVisibility();
/* 358 */       this.treeTable.createHeaderRenderers();
/* 359 */       recalculateAggregateValues();
/*     */     
/*     */     }
/* 362 */     else if (evt.getPropertyName().equals("filter.disabled")) {
/*     */       
/* 364 */       TableReportHeaderInfo headerInfo = (TableReportHeaderInfo)evt.getNewValue();
/* 365 */       headerInfo.clearFilter(false);
/* 366 */       this.groupPanel.clearFilter(headerInfo);
/* 367 */       this.treeTable.group();
/* 368 */       this.treeTable.createHeaderRenderers();
/* 369 */       recalculateAggregateValues();
/*     */     
/*     */     }
/* 372 */     else if (evt.getPropertyName().equals("filter.enabled")) {
/*     */       
/* 374 */       TableReportHeaderInfo headerInfo = (TableReportHeaderInfo)evt.getNewValue();
/* 375 */       headerInfo.restoreFilter();
/* 376 */       this.groupPanel.restoreFilter(headerInfo);
/* 377 */       this.treeTable.group();
/* 378 */       this.treeTable.createHeaderRenderers();
/* 379 */       recalculateAggregateValues();
/*     */     
/*     */     }
/* 382 */     else if (evt.getPropertyName().equals("toggle.group.sort")) {
/*     */       
/* 384 */       TableReportHeaderInfo headerInfo = (TableReportHeaderInfo)evt.getNewValue();
/* 385 */       GroupHeaderLabel headerLabel = this.groupPanel.getGroupHeaderLabel(headerInfo);
/* 386 */       headerLabel.toggle();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateFilterPanel(TableReportHeaderInfo headerInfo) {
/* 392 */     headerInfo.createFilter();
/*     */     
/* 394 */     if (!headerInfo.hasFilteredValues()) {
/*     */       
/* 396 */       this.filterPanel.remove(headerInfo);
/* 397 */       revalidate();
/*     */       
/*     */       return;
/*     */     } 
/* 401 */     if (this.filterPanel.contains(headerInfo)) {
/*     */       byte b; int i; Component[] arrayOfComponent;
/* 403 */       for (i = (arrayOfComponent = this.filterPanel.getComponents()).length, b = 0; b < i; ) { Component c = arrayOfComponent[b];
/*     */         
/* 405 */         if (c instanceof FilterEntryPanel) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 410 */           FilterEntryPanel filterEntryPanel = (FilterEntryPanel)c;
/* 411 */           if (filterEntryPanel.getHeaderInfo().equals(headerInfo)) {
/*     */             
/* 413 */             filterEntryPanel.setHeaderInfo(headerInfo);
/* 414 */             filterEntryPanel.setIncluded(true);
/* 415 */             filterEntryPanel.updateFilterString();
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */         b++; }
/*     */     
/*     */     } else {
/* 423 */       FilterEntryPanel filterEntryPanel = new FilterEntryPanel(headerInfo);
/* 424 */       filterEntryPanel.addPropertyChangeListener(this);
/* 425 */       this.filterPanel.add(filterEntryPanel);
/* 426 */       revalidate();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Integer getSelectedColumnModelIndex() {
/* 432 */     if (this.cursorLocation == null)
/*     */     {
/* 434 */       return null;
/*     */     }
/*     */     
/* 437 */     this.cursorLocation.y = 0;
/* 438 */     int viewIndex = this.treeTable.columnAtPoint(this.cursorLocation);
/* 439 */     if (viewIndex == -1)
/*     */     {
/* 441 */       return null;
/*     */     }
/*     */     
/* 444 */     int columnIndex = this.treeTable.convertColumnIndexToModel(viewIndex);
/* 445 */     return Integer.valueOf(columnIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(final ActionEvent e) {
/* 450 */     Integer columnModelIndex = getSelectedColumnModelIndex();
/*     */     
/* 452 */     if (e.getActionCommand().equals("export_to_excel")) {
/*     */       
/* 454 */       exportToExcel();
/*     */     
/*     */     }
/* 457 */     else if (e.getActionCommand().equals("expand_all") || e.getActionCommand().equals("collapse_all")) {
/*     */       
/* 459 */       SwingUtilities.invokeLater(new Runnable()
/*     */           {
/*     */             public void run()
/*     */             {
/* 463 */               if (e.getActionCommand().equals("expand_all"))
/*     */               {
/* 465 */                 TableReportPanel.this.treeTable.expandAll();
/*     */               }
/*     */               else
/*     */               {
/* 469 */                 TableReportPanel.this.treeTable.collapseAll();
/*     */               }
/*     */             
/*     */             }
/*     */           });
/*     */     }
/* 475 */     else if (e.getActionCommand().equals("save_user")) {
/*     */       
/* 477 */       savePreferences(false);
/*     */     
/*     */     }
/* 480 */     else if (e.getActionCommand().equals("save_all")) {
/*     */       
/* 482 */       savePreferences(true);
/*     */     
/*     */     }
/* 485 */     else if (e.getActionCommand().equals("row_color")) {
/*     */       
/* 487 */       rowColor();
/*     */     
/*     */     }
/* 490 */     else if (e.getActionCommand().equals("none")) {
/*     */       
/* 492 */       this.aggregateTable.reset(columnModelIndex);
/* 493 */       recalculateAggregateValues();
/* 494 */       updateAggregatePanelSize();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 499 */       AggregateValue.AggregateFunctionType type = AggregateValue.AggregateFunctionType.valueOf(e.getActionCommand());
/*     */       
/* 501 */       AggregateValue aggregateValue = new AggregateValue(type, null);
/* 502 */       this.aggregateTable.addAggregateValue(aggregateValue, columnModelIndex.intValue());
/* 503 */       recalculateAggregateValues();
/* 504 */       updateAggregatePanelSize();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void savePreferences(boolean everyone) {
/* 510 */     TableReportPreferences reportPreferences = createPreferences();
/*     */     
/* 512 */     reportPreferences.setUserNr(null);
/*     */     
/* 514 */     if (everyone)
/*     */     {
/* 516 */       reportPreferences.setUserNr(Integer.valueOf(0));
/*     */     }
/*     */     
/* 519 */     saveTableReportPreferences(reportPreferences);
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateAggregatePanelSize() {
/* 524 */     int rowCount = (this.aggregateTable.getRowCount() > 0) ? 
/* 525 */       this.aggregateTable.getRowCount() : 
/* 526 */       1;
/* 527 */     int rowHeight = this.aggregateTable.getRowHeight();
/* 528 */     this.aggregateScrollPane.setPreferredSize(new Dimension(0, rowCount * rowHeight));
/* 529 */     revalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   private void exportToExcel() {
/* 534 */     JFileChooser chooser = new JFileChooser();
/* 535 */     chooser.setFileSelectionMode(0);
/* 536 */     int result = chooser.showSaveDialog(this);
/*     */     
/* 538 */     if (result != 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 543 */     String filePath = chooser.getSelectedFile().getPath();
/* 544 */     filePath = JLbsFileUtil.changeExtension(filePath, ".xls");
/*     */     
/* 546 */     File file = new File(filePath);
/* 547 */     if (file.exists())
/*     */     {
/* 549 */       file.delete();
/*     */     }
/*     */     
/* 552 */     JLbsComponentHelper.exportTableReportToExcel(this.reportInfo.getContext(), this, file);
/*     */   }
/*     */ 
/*     */   
/*     */   private void recalculateAggregateValues() {
/* 557 */     HashMap<Integer, Vector<AggregateValue>> aggregateValues = this.aggregateTable.getAggregateValues();
/* 558 */     this.treeTable.updateAggregateValues(aggregateValues);
/* 559 */     this.aggregateTable.fillAggregateValues();
/*     */   }
/*     */ 
/*     */   
/*     */   public TableReportInfo getReportInfo() {
/* 564 */     return this.reportInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public TableReportTreeTable getTreeTable() {
/* 569 */     return this.treeTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveTableReportPreferences(TableReportPreferences reportPreferences) {
/* 574 */     Object context = getReportInfo().getContext();
/*     */ 
/*     */     
/*     */     try {
/* 578 */       JLbsComponentHelper.saveTableReportPreferences(context, reportPreferences);
/* 579 */       getReportInfo().setReportPreferences(reportPreferences);
/*     */       
/* 581 */       JOptionPane.showMessageDialog(null, TableReportConstants.STRINGS3[4], TableReportConstants.STRINGS3[5], 
/* 582 */           1);
/*     */     }
/* 584 */     catch (Exception e) {
/*     */       
/* 586 */       e.printStackTrace();
/* 587 */       JOptionPane.showMessageDialog(null, TableReportConstants.STRINGS3[6], TableReportConstants.STRINGS3[7], 
/* 588 */           2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public TableReportPreferences createPreferences() {
/* 594 */     TableReportPreferences reportPreferences = this.reportInfo.getReportPreferences();
/*     */     
/* 596 */     if (reportPreferences == null)
/*     */     {
/* 598 */       reportPreferences = new TableReportPreferences();
/*     */     }
/*     */     
/* 601 */     String queryName = this.reportInfo.getQueryName();
/* 602 */     if (queryName == null)
/*     */     {
/* 604 */       queryName = this.reportInfo.getTitle();
/*     */     }
/*     */     
/* 607 */     reportPreferences.setQueryName(queryName);
/* 608 */     reportPreferences.setColumnOrder(createColumnOrder());
/* 609 */     reportPreferences.setExclusionMap(createExclusionMap());
/* 610 */     reportPreferences.setHeaderInfos(this.treeTable.getHeaderInfos());
/*     */     
/* 612 */     return reportPreferences;
/*     */   }
/*     */ 
/*     */   
/*     */   private HashMap<Integer, Vector<FilterListCellValue>> createExclusionMap() {
/* 617 */     HashMap<Integer, Vector<FilterListCellValue>> exclusionMap = new HashMap<>(); byte b; int i;
/*     */     Component[] arrayOfComponent;
/* 619 */     for (i = (arrayOfComponent = this.groupPanel.getComponents()).length, b = 0; b < i; ) { Component c = arrayOfComponent[b];
/*     */       
/* 621 */       if (c instanceof GroupHeaderLabel) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 626 */         GroupHeaderLabel label = (GroupHeaderLabel)c;
/* 627 */         Integer columnIndex = label.getHeaderInfo().getColumnIndex();
/* 628 */         Vector<FilterListCellValue> list = label.getExclusionList();
/* 629 */         exclusionMap.put(columnIndex, list);
/*     */       }  b++; }
/*     */     
/* 632 */     return exclusionMap;
/*     */   }
/*     */ 
/*     */   
/*     */   private Vector<Integer> createColumnOrder() {
/* 637 */     Vector<Integer> order = new Vector<>();
/* 638 */     TableColumnModel columnModel = this.treeTable.getColumnModel();
/*     */     
/* 640 */     for (int viewIndex = 0; viewIndex < columnModel.getColumnCount(); viewIndex++) {
/*     */       
/* 642 */       int modelIndex = this.treeTable.convertColumnIndexToModel(viewIndex);
/* 643 */       order.add(Integer.valueOf(modelIndex));
/*     */     } 
/*     */     
/* 646 */     return order;
/*     */   }
/*     */ 
/*     */   
/*     */   private void applyReportPreferences() {
/* 651 */     if (this.reportInfo.getReportPreferences() == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 656 */     final TableReportPreferences preferences = this.reportInfo.getReportPreferences();
/*     */ 
/*     */     
/* 659 */     if (preferences != null && preferences.getExclusionMap() != null)
/*     */     {
/* 661 */       for (Iterator<Integer> iterator = preferences.getExclusionMap().keySet().iterator(); iterator.hasNext(); ) { int columnModelIndex = ((Integer)iterator.next()).intValue();
/*     */         
/* 663 */         TableReportHeaderInfo headerInfo = this.dsTableHeader.createHeaderInfo(Integer.valueOf(columnModelIndex));
/* 664 */         firePropertyChange("drop", (Object)null, headerInfo);
/*     */         
/* 666 */         Vector<FilterListCellValue> exclusionList = preferences.getExclusionMap().get(Integer.valueOf(columnModelIndex));
/* 667 */         this.groupPanel.setSelected(headerInfo, (exclusionList.size() > 0));
/* 668 */         headerInfo.applyExclusionList(preferences.getExclusionMap().get(Integer.valueOf(columnModelIndex)));
/* 669 */         firePropertyChange("filter.changed", (Object)null, headerInfo); }
/*     */     
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 675 */     SwingUtilities.invokeLater(new Runnable()
/*     */         {
/*     */           public void run() {
/*     */             int i;
/* 679 */             boolean control = false;
/* 680 */             if (preferences != null && preferences.getExclusionMap() != null)
/*     */             {
/* 682 */               for (Integer columnModelIndex : preferences.getHeaderInfos().keySet()) {
/*     */                 
/* 684 */                 TableReportHeaderInfo headerInfo = preferences.getHeaderInfos().get(columnModelIndex);
/* 685 */                 TableReportConstants.SortDirection sortDirection = headerInfo.getSortDirection();
/*     */                 
/* 687 */                 GroupHeaderLabel headerLabel = TableReportPanel.this.groupPanel.getGroupHeaderLabel(headerInfo);
/* 688 */                 if (headerLabel != null)
/*     */                 {
/* 690 */                   sortDirection = headerLabel.getHeaderInfo().getSortDirection();
/*     */                 }
/*     */                 
/* 693 */                 i = control | ((sortDirection != TableReportConstants.SortDirection.UNDEFINED) ? 1 : 0);
/* 694 */                 ((TableReportHeaderInfo)TableReportPanel.this.treeTable.getHeaderInfos().get(columnModelIndex)).setSortDirection(sortDirection);
/*     */               } 
/*     */             }
/*     */             
/* 698 */             if (i != 0)
/*     */             {
/* 700 */               TableReportPanel.this.treeTable.sort((DefaultMutableTreeTableNode)null);
/*     */             }
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 707 */     SwingUtilities.invokeLater(new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 711 */             TableColumnModel columnModel = TableReportPanel.this.treeTable.getColumnModel();
/* 712 */             if (preferences != null && preferences.getColumnOrder() != null) {
/*     */               
/* 714 */               for (int destViewIndex = 0; destViewIndex < preferences.getColumnOrder().size(); destViewIndex++) {
/*     */                 
/* 716 */                 int sourceViewIndex = TableReportPanel.this.treeTable.convertColumnIndexToView(((Integer)preferences.getColumnOrder().get(destViewIndex)).intValue());
/*     */                 
/* 718 */                 if (sourceViewIndex != -1)
/*     */                 {
/* 720 */                   TableReportPanel.this.treeTable.moveColumn(sourceViewIndex, destViewIndex);
/*     */                 }
/*     */               } 
/*     */               
/* 724 */               for (int viewIndex = preferences.getColumnOrder().size() - 1; viewIndex < columnModel.getColumnCount(); viewIndex++)
/*     */               {
/* 726 */                 TableReportPanel.this.treeTable.getColumnExt(viewIndex).setVisible(false);
/*     */               }
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void rowColor() {
/* 736 */     JLbsComponentHelper.rowColoring(this.reportInfo, true);
/* 737 */     this.treeTable.repaint();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\TableReportPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */