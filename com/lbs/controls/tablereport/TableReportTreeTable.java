/*     */ package com.lbs.controls.tablereport;
/*     */ 
/*     */ import com.lbs.controls.pivottable.PivotCellValue;
/*     */ import java.awt.Component;
/*     */ import java.awt.MouseInfo;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.table.DefaultTableCellRenderer;
/*     */ import javax.swing.table.TableCellRenderer;
/*     */ import javax.swing.tree.DefaultTreeCellRenderer;
/*     */ import javax.swing.tree.TreePath;
/*     */ import org.jdesktop.swingx.JXTreeTable;
/*     */ import org.jdesktop.swingx.table.TableColumnExt;
/*     */ import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
/*     */ import org.jdesktop.swingx.treetable.MutableTreeTableNode;
/*     */ import org.jdesktop.swingx.treetable.TreeTableModel;
/*     */ import org.jdesktop.swingx.treetable.TreeTableNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TableReportTreeTable
/*     */   extends JXTreeTable
/*     */   implements ActionListener
/*     */ {
/*     */   private TableReportTreeTableModel model;
/*     */   private Vector<Boolean> columnVisibilities;
/*     */   private TableReportInfo reportInfo;
/*     */   private HashMap<Integer, TableReportHeaderInfo> headerInfos;
/*     */   private JPopupMenu tableHeaderMenu;
/*     */   
/*     */   private class TableReportTreeCellRenderer
/*     */     extends DefaultTreeCellRenderer
/*     */   {
/*     */     private TableReportTreeCellRenderer() {}
/*     */     
/*     */     public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
/*  54 */       super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
/*     */       
/*  56 */       String text = null;
/*  57 */       if (!leaf) {
/*     */         
/*  59 */         Object userObject = ((DefaultMutableTreeTableNode)value).getUserObject();
/*     */         
/*  61 */         if (userObject instanceof RowObject) {
/*     */           
/*  63 */           RowObject rowObject = (RowObject)userObject;
/*  64 */           PairValue pairValue = (PairValue)rowObject.getRowData()[0];
/*  65 */           text = String.valueOf(pairValue.getName()) + " : " + TableReportInfo.getFormatter().format(pairValue.getValue());
/*     */         } 
/*     */       } 
/*     */       
/*  69 */       setText(text);
/*  70 */       setFont(getFont().deriveFont(1));
/*     */       
/*  72 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private class HeaderCellRenderer
/*     */     extends DefaultTableCellRenderer
/*     */   {
/*     */     private TableReportHeaderInfo headerInfo;
/*     */     
/*     */     public HeaderCellRenderer(TableReportHeaderInfo headerInfo) {
/*  83 */       this.headerInfo = headerInfo;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
/*  89 */       TableHeaderLabel label = new TableHeaderLabel(this.headerInfo);
/*     */       
/*  91 */       return label;
/*     */     }
/*     */   }
/*     */   
/*     */   private class TableReportCellRenderer
/*     */     extends DefaultTableCellRenderer {
/*     */     private TableReportCellRenderer() {}
/*     */     
/*     */     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
/* 100 */       super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
/*     */       
/* 102 */       JLabel label = new JLabel();
/* 103 */       label.setOpaque(true);
/* 104 */       label.setText(TableReportInfo.getFormatter().format(value));
/*     */       
/* 106 */       int modelIndex = TableReportTreeTable.this.convertColumnIndexToModel(column);
/* 107 */       int horizontalAlignment = ((Integer)TableReportTreeTable.this.reportInfo.getHorizontalAlignments().get(modelIndex)).intValue();
/* 108 */       label.setHorizontalAlignment(horizontalAlignment);
/*     */       
/* 110 */       TreePath treePath = TableReportTreeTable.this.getPathForRow(row);
/* 111 */       DefaultMutableTreeTableNode node = (DefaultMutableTreeTableNode)treePath.getLastPathComponent();
/* 112 */       if (!node.isLeaf()) {
/*     */         
/* 114 */         label.setFont(label.getFont().deriveFont(1));
/* 115 */         if (horizontalAlignment == 2) {
/*     */           
/* 117 */           label.setHorizontalAlignment(4);
/*     */         }
/*     */         else {
/*     */           
/* 121 */           label.setHorizontalAlignment(2);
/*     */         } 
/*     */       } 
/*     */       
/* 125 */       return label;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
/* 131 */     Component c = super.prepareRenderer(renderer, row, column);
/* 132 */     if (!(c instanceof JLabel))
/*     */     {
/* 134 */       return c;
/*     */     }
/*     */     
/* 137 */     JLabel label = (JLabel)c;
/* 138 */     TreePath treePath = getPathForRow(row);
/* 139 */     DefaultMutableTreeTableNode node = (DefaultMutableTreeTableNode)treePath.getLastPathComponent();
/*     */     
/* 141 */     int modelColumnIndex = convertColumnIndexToModel(column);
/*     */     
/* 143 */     RowObject rowObject = (RowObject)node.getUserObject();
/* 144 */     HashMap<Integer, CellDisplay> display = rowObject.getDisplay();
/*     */     
/* 146 */     if (getSelectedRow() != row)
/*     */     {
/* 148 */       if (display.containsKey(Integer.valueOf(modelColumnIndex))) {
/*     */         
/* 150 */         CellDisplay cellDisplay = display.get(Integer.valueOf(modelColumnIndex));
/* 151 */         label.setForeground(cellDisplay.getForeground());
/* 152 */         label.setBackground(cellDisplay.getBackground());
/*     */       } 
/*     */     }
/*     */     
/* 156 */     return label;
/*     */   }
/*     */ 
/*     */   
/*     */   public TableReportTreeTable(TableReportTreeTableModel model, TableReportInfo reportInfo) {
/* 161 */     super((TreeTableModel)model);
/*     */     
/* 163 */     this.model = model;
/* 164 */     this.reportInfo = reportInfo;
/*     */     
/* 166 */     this.columnVisibilities = new Vector<>();
/* 167 */     setColumnRenderers();
/* 168 */     setTreeCellRenderer(new TableReportTreeCellRenderer(null));
/* 169 */     createHeaderRenderers();
/* 170 */     createTableHeaderMenu();
/*     */ 
/*     */ 
/*     */     
/* 174 */     getTableHeader().addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mouseClicked(MouseEvent e)
/*     */           {
/* 178 */             if (e.getButton() != 1) {
/*     */               return;
/*     */             }
/*     */ 
/*     */             
/* 183 */             int viewIndex = TableReportTreeTable.this.getTableHeader().getTable().columnAtPoint(e.getPoint());
/* 184 */             int modelIndex = TableReportTreeTable.this.convertColumnIndexToModel(viewIndex);
/*     */             
/* 186 */             TableReportHeaderInfo headerInfo = (TableReportHeaderInfo)TableReportTreeTable.this.headerInfos.get(Integer.valueOf(modelIndex));
/*     */             
/* 188 */             if (TableReportTreeTable.this.getGroupHeaderIndexes().contains(headerInfo)) {
/*     */               
/* 190 */               TableReportTreeTable.this.firePropertyChange("toggle.group.sort", null, headerInfo);
/*     */             }
/*     */             else {
/*     */               
/* 194 */               headerInfo.toggle();
/* 195 */               TableReportTreeTable.this.sort((DefaultMutableTreeTableNode)null);
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 200 */     addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mousePressed(MouseEvent event)
/*     */           {
/* 204 */             if (event.getClickCount() == 2) {
/*     */               
/* 206 */               int row = TableReportTreeTable.this.getSelectedRow();
/*     */               
/* 208 */               if (TableReportTreeTable.this.isExpanded(row)) {
/*     */                 
/* 210 */                 TableReportTreeTable.this.collapseRow(row);
/*     */               
/*     */               }
/*     */               else {
/*     */                 
/* 215 */                 TableReportTreeTable.this.expandRow(TableReportTreeTable.this.getSelectedRow());
/*     */               } 
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 221 */     addKeyListener(new KeyAdapter()
/*     */         {
/*     */           public void keyPressed(KeyEvent event)
/*     */           {
/* 225 */             if (TableReportTreeTable.this.getSelectedRow() != -1) {
/*     */               
/* 227 */               int row = TableReportTreeTable.this.getSelectedRow();
/*     */               
/* 229 */               if (event.getKeyCode() == 37) {
/*     */                 
/* 231 */                 if (TableReportTreeTable.this.isExpanded(row))
/*     */                 {
/* 233 */                   TableReportTreeTable.this.collapseRow(row);
/*     */                 }
/*     */                 else
/*     */                 {
/* 237 */                   TreePath treePath = TableReportTreeTable.this.getPathForRow(row).getParentPath();
/*     */                   
/* 239 */                   if (treePath != null && treePath.getParentPath() != null)
/*     */                   {
/* 241 */                     TableReportTreeTable.this.collapsePath(treePath);
/*     */                   }
/*     */                 }
/*     */               
/*     */               }
/* 246 */               else if (event.getKeyCode() == 39) {
/*     */                 
/* 248 */                 if (!TableReportTreeTable.this.isExpanded(row))
/*     */                 {
/* 250 */                   TableReportTreeTable.this.expandRow(row);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private void createTableHeaderMenu() {
/* 260 */     this.tableHeaderMenu = new JPopupMenu();
/* 261 */     JMenuItem itemCancelOrder = this.tableHeaderMenu.add(TableReportConstants.STRINGS3[0]);
/* 262 */     itemCancelOrder.setActionCommand("cancel_order");
/* 263 */     itemCancelOrder.addActionListener(this);
/*     */     
/* 265 */     getTableHeader().setComponentPopupMenu(this.tableHeaderMenu);
/*     */   }
/*     */ 
/*     */   
/*     */   public void sort(DefaultMutableTreeTableNode node) {
/* 270 */     if (node == null)
/*     */     {
/* 272 */       node = (DefaultMutableTreeTableNode)this.model.getRoot();
/*     */     }
/*     */     
/* 275 */     if (!node.isLeaf()) {
/*     */       
/* 277 */       boolean control = true; int index;
/* 278 */       for (index = 0; index < node.getChildCount(); index++) {
/*     */         
/* 280 */         DefaultMutableTreeTableNode childNode = (DefaultMutableTreeTableNode)node.getChildAt(index);
/* 281 */         control &= childNode.isLeaf();
/*     */       } 
/*     */       
/* 284 */       if (control) {
/*     */         
/* 286 */         Vector<DefaultMutableTreeTableNode> nodes = new Vector<>();
/* 287 */         Vector<RowObject> vector = new Vector<>();
/*     */         int i;
/* 289 */         for (i = node.getChildCount() - 1; i >= 0; i--) {
/*     */           
/* 291 */           DefaultMutableTreeTableNode childNode = (DefaultMutableTreeTableNode)node.getChildAt(i);
/* 292 */           RowObject rowObject = (RowObject)childNode.getUserObject();
/* 293 */           rowObject.setId(Integer.valueOf(i));
/*     */           
/* 295 */           nodes.insertElementAt(childNode, 0);
/* 296 */           vector.insertElementAt(rowObject, 0);
/*     */         } 
/*     */         
/* 299 */         RowObject.setHeaders(getGroupHeaderIndexes());
/* 300 */         RowObject.setHeaderInfos(this.headerInfos);
/* 301 */         Collections.sort(vector);
/*     */         
/* 303 */         for (i = 0; i < vector.size(); i++)
/*     */         {
/* 305 */           RowObject rowObject = vector.get(i);
/* 306 */           DefaultMutableTreeTableNode childNode = nodes.get(rowObject.getId().intValue());
/*     */           
/* 308 */           this.model.removeNodeFromParent((MutableTreeTableNode)childNode);
/* 309 */           this.model.insertNodeInto((MutableTreeTableNode)childNode, (MutableTreeTableNode)node, node.getChildCount());
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 314 */         for (index = 0; index < node.getChildCount(); index++) {
/*     */           
/* 316 */           DefaultMutableTreeTableNode childNode = (DefaultMutableTreeTableNode)node.getChildAt(index);
/*     */           
/* 318 */           if (!childNode.isLeaf())
/*     */           {
/* 320 */             sort(childNode);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void createHeaderRenderers() {
/* 329 */     SwingUtilities.invokeLater(new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 333 */             if (TableReportTreeTable.this.headerInfos == null)
/*     */             {
/* 335 */               TableReportTreeTable.this.headerInfos = (HashMap)new HashMap<>();
/*     */             }
/*     */             
/* 338 */             for (int viewIndex = 0; viewIndex < TableReportTreeTable.this.getColumnCount(); viewIndex++) {
/*     */               
/* 340 */               int modelIndex = TableReportTreeTable.this.convertColumnIndexToModel(viewIndex);
/* 341 */               String headerName = TableReportTreeTable.this.reportInfo.getHeaderNames().get(modelIndex);
/* 342 */               TableReportHeaderInfo headerInfo = null;
/*     */               
/* 344 */               if (TableReportTreeTable.this.headerInfos.get(Integer.valueOf(modelIndex)) == null) {
/*     */                 
/* 346 */                 headerInfo = TableReportTreeTable.this.createHeaderInfo(headerName, modelIndex);
/* 347 */                 headerInfo.setSortDirection(TableReportConstants.SortDirection.UNDEFINED);
/* 348 */                 TableReportTreeTable.this.headerInfos.put(Integer.valueOf(modelIndex), headerInfo);
/*     */               
/*     */               }
/*     */               else {
/*     */                 
/* 353 */                 headerInfo = (TableReportHeaderInfo)TableReportTreeTable.this.headerInfos.get(Integer.valueOf(modelIndex));
/*     */               } 
/*     */               
/* 356 */               TableReportTreeTable.HeaderCellRenderer headerRenderer = new TableReportTreeTable.HeaderCellRenderer(headerInfo);
/* 357 */               TableReportTreeTable.this.getColumnExt(viewIndex).setHeaderRenderer(headerRenderer);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAggregateValues(DefaultMutableTreeTableNode root, HashMap<Integer, Vector<AggregateValue>> aggregateValues) {
/* 365 */     if (root == null)
/*     */     {
/* 367 */       root = (DefaultMutableTreeTableNode)this.model.getRoot();
/*     */     }
/*     */     
/* 370 */     for (int index = 0; index < root.getChildCount(); index++) {
/*     */       
/* 372 */       DefaultMutableTreeTableNode node = (DefaultMutableTreeTableNode)root.getChildAt(index);
/*     */       
/* 374 */       if (!node.isLeaf()) {
/*     */         
/* 376 */         evaluateAggregateValues(node, aggregateValues);
/* 377 */         updateAggregateValues(node, aggregateValues);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void evaluateAggregateValues(DefaultMutableTreeTableNode root, HashMap<Integer, Vector<AggregateValue>> aggregateValues) {
/* 384 */     if (root == null)
/*     */     {
/* 386 */       root = (DefaultMutableTreeTableNode)this.model.getRoot();
/*     */     }
/*     */     
/* 389 */     Vector<RowObject> rows = getRows(root);
/* 390 */     Number count = Integer.valueOf(rows.size());
/*     */     
/* 392 */     for (Map.Entry<Integer, Vector<AggregateValue>> columnIndex : aggregateValues.entrySet()) {
/*     */       
/* 394 */       Number min = null, max = null, avg = null, sum = null;
/*     */       
/* 396 */       for (int index = 0; index < rows.size(); index++) {
/*     */         
/* 398 */         RowObject ro = rows.get(index);
/* 399 */         Object object = ro.getRowData()[((Integer)columnIndex.getKey()).intValue()];
/*     */         
/* 401 */         if (object != null)
/*     */         {
/*     */ 
/*     */ 
/*     */           
/* 406 */           if (object instanceof Number) {
/*     */             
/* 408 */             Number number = (Number)object;
/*     */             
/* 410 */             if (sum == null) {
/*     */               
/* 412 */               sum = number;
/*     */             }
/*     */             else {
/*     */               
/* 416 */               sum = Double.valueOf(sum.doubleValue() + number.doubleValue());
/*     */             } 
/*     */             
/* 419 */             if (min == null) {
/*     */               
/* 421 */               min = number;
/*     */             }
/* 423 */             else if (min.doubleValue() > number.doubleValue()) {
/*     */               
/* 425 */               min = number;
/*     */             } 
/*     */             
/* 428 */             if (max == null) {
/*     */               
/* 430 */               max = number;
/*     */             }
/* 432 */             else if (max.doubleValue() < number.doubleValue()) {
/*     */               
/* 434 */               max = number;
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/* 439 */       if (sum != null)
/*     */       {
/* 441 */         avg = Double.valueOf(sum.doubleValue() / rows.size());
/*     */       }
/*     */       
/* 444 */       for (AggregateValue av : aggregateValues.get(columnIndex.getKey())) {
/*     */         
/* 446 */         switch (av.getType()) {
/*     */           
/*     */           case null:
/* 449 */             av.setValue(avg);
/*     */ 
/*     */           
/*     */           case COUNT:
/* 453 */             av.setValue(count);
/*     */ 
/*     */           
/*     */           case MAX:
/* 457 */             av.setValue(max);
/*     */ 
/*     */           
/*     */           case MIN:
/* 461 */             av.setValue(min);
/*     */ 
/*     */           
/*     */           case SUM:
/* 465 */             av.setValue(sum);
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/*     */     } 
/* 471 */     if (!(root.getUserObject() instanceof RowObject)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 476 */     Object[] rowData = ((RowObject)root.getUserObject()).getRowData();
/*     */     
/* 478 */     for (Map.Entry<Integer, Vector<AggregateValue>> columnIndex : aggregateValues.entrySet()) {
/*     */       
/* 480 */       Vector<AggregateValue> columnValues = columnIndex.getValue();
/* 481 */       StringBuffer buffer = new StringBuffer();
/*     */       
/* 483 */       for (int index = 0; index < columnValues.size(); index++) {
/*     */         
/* 485 */         buffer.append(((AggregateValue)columnValues.get(index)).toString());
/*     */         
/* 487 */         if (index < columnValues.size() - 1)
/*     */         {
/* 489 */           buffer.append(",");
/*     */         }
/*     */       } 
/*     */       
/* 493 */       rowData[((Integer)columnIndex.getKey()).intValue()] = buffer.toString();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private TableReportHeaderInfo createHeaderInfo(String title, int columnIndex) {
/* 499 */     TableReportHeaderInfo headerInfo = new TableReportHeaderInfo(Integer.valueOf(columnIndex), TableReportConstants.SortDirection.ASC);
/* 500 */     headerInfo.setTitle(title);
/*     */     
/* 502 */     return headerInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumnRenderers() {
/* 507 */     setDefaultRenderer(Date.class, new TableReportCellRenderer(null));
/* 508 */     setDefaultRenderer(Calendar.class, new TableReportCellRenderer(null));
/* 509 */     setDefaultRenderer(String.class, new TableReportCellRenderer(null));
/* 510 */     setDefaultRenderer(Number.class, new TableReportCellRenderer(null));
/*     */   }
/*     */ 
/*     */   
/*     */   public void group() {
/* 515 */     group((HashMap<Integer, TableReportHeaderInfo>)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void group(HashMap<Integer, TableReportHeaderInfo> headerInfos) {
/* 520 */     this.model.group(headerInfos);
/* 521 */     collapseAll();
/*     */     
/* 523 */     firePropertyChange("recalculate.aggregate.values", null, null);
/* 524 */     packAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeGroupHeader(Integer columnIndex, boolean visible) {
/* 529 */     this.model.removeGroupHeaderIndex(columnIndex);
/*     */     
/* 531 */     adjustTreeColumnVisibility();
/* 532 */     saveColumnVisibility();
/* 533 */     this.columnVisibilities.set(columnIndex.intValue(), Boolean.valueOf(visible));
/* 534 */     restoreColumnVisibility();
/*     */     
/* 536 */     collapseAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeGroupHeader(TableReportHeaderInfo info) {
/* 541 */     removeGroupHeader(info.getColumnIndex(), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAggregateValues(HashMap<Integer, Vector<AggregateValue>> aggregateValues) {
/* 546 */     updateAggregateValues((DefaultMutableTreeTableNode)null, aggregateValues);
/* 547 */     evaluateAggregateValues((DefaultMutableTreeTableNode)null, aggregateValues);
/* 548 */     repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNumber(int columnIndex) {
/* 553 */     Vector<Object> vector = getObjectList((DefaultMutableTreeTableNode)null, columnIndex);
/* 554 */     boolean number = true;
/*     */     
/* 556 */     for (int index = 0; index < vector.size(); index++) {
/*     */       
/* 558 */       Object object = vector.get(index);
/* 559 */       if (object != null) {
/*     */         
/* 561 */         number &= object instanceof Number;
/* 562 */         if (!number)
/*     */         {
/* 564 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 569 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector<RowObject> getRows(DefaultMutableTreeTableNode root) {
/* 574 */     Vector<RowObject> rows = new Vector<>();
/* 575 */     DefaultMutableTreeTableNode rootNode = root;
/*     */     
/* 577 */     if (rootNode == null)
/*     */     {
/* 579 */       rootNode = (DefaultMutableTreeTableNode)this.model.getRoot();
/*     */     }
/*     */     
/* 582 */     if (!rootNode.isLeaf()) {
/*     */       
/* 584 */       for (int index = 0; index < rootNode.getChildCount(); index++)
/*     */       {
/* 586 */         DefaultMutableTreeTableNode node = (DefaultMutableTreeTableNode)rootNode.getChildAt(index);
/* 587 */         rows.addAll(getRows(node));
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 592 */       RowObject rowObject = (RowObject)rootNode.getUserObject();
/* 593 */       rows.add(rowObject);
/*     */     } 
/*     */     
/* 596 */     return rows;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector<Object> getObjectList(DefaultMutableTreeTableNode root, int columnIndex) {
/* 601 */     Vector<Object> vector = new Vector();
/* 602 */     DefaultMutableTreeTableNode rootNode = root;
/*     */     
/* 604 */     if (rootNode == null)
/*     */     {
/* 606 */       rootNode = (DefaultMutableTreeTableNode)this.model.getRoot();
/*     */     }
/*     */     
/* 609 */     if (!rootNode.isLeaf()) {
/*     */       
/* 611 */       for (int index = 0; index < rootNode.getChildCount(); index++)
/*     */       {
/* 613 */         DefaultMutableTreeTableNode node = (DefaultMutableTreeTableNode)rootNode.getChildAt(index);
/* 614 */         vector.addAll(getObjectList(node, columnIndex));
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 620 */       RowObject rowObject = (RowObject)rootNode.getUserObject();
/* 621 */       if ((rowObject.getRowData()).length > columnIndex) {
/*     */         
/* 623 */         Object object = rowObject.getRowData()[columnIndex];
/*     */         
/* 625 */         if (object != null) {
/*     */           
/* 627 */           if (object instanceof PivotCellValue) {
/*     */             
/* 629 */             PivotCellValue pcv = (PivotCellValue)object;
/* 630 */             object = pcv.getValue();
/*     */           } 
/* 632 */           vector.add(object);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 637 */     return vector;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addGroupHeader(TableReportHeaderInfo info) {
/* 642 */     this.model.addGroupHeaderIndex(info);
/*     */     
/* 644 */     adjustTreeColumnVisibility();
/* 645 */     saveColumnVisibility();
/* 646 */     this.columnVisibilities.set(info.getColumnIndex().intValue(), Boolean.valueOf(false));
/* 647 */     restoreColumnVisibility();
/*     */     
/* 649 */     collapseAll();
/*     */   }
/*     */ 
/*     */   
/*     */   private void adjustTreeColumnVisibility() {
/* 654 */     if (this.model.getGroupHeaderIndexes().size() == 0) {
/*     */       
/* 656 */       getColumnExt("").setVisible(false);
/*     */     }
/*     */     else {
/*     */       
/* 660 */       getColumnExt("").setVisible(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearNodes() {
/* 666 */     this.model.clearNodes();
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector<TableReportHeaderInfo> getGroupHeaderIndexes() {
/* 671 */     return this.model.getGroupHeaderIndexes();
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeSortDirection(TableReportHeaderInfo info) {
/* 676 */     for (TableReportHeaderInfo storedInfo : this.model.getGroupHeaderIndexes()) {
/*     */       
/* 678 */       if (storedInfo.equals(info)) {
/*     */         
/* 680 */         storedInfo.setSortDirection(info.getSortDirection());
/*     */         
/* 682 */         if (this.headerInfos.get(info.getColumnIndex()) != null)
/*     */         {
/* 684 */           ((TableReportHeaderInfo)this.headerInfos.get(info.getColumnIndex())).setSortDirection(info.getSortDirection());
/*     */         }
/*     */         
/* 687 */         this.model.group();
/* 688 */         collapseAll();
/* 689 */         restoreColumnVisibility();
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void collapseAll() {
/* 697 */     super.collapseAll();
/*     */     
/* 699 */     SwingUtilities.invokeLater(new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 703 */             DefaultMutableTreeTableNode rootNode = (DefaultMutableTreeTableNode)TableReportTreeTable.this.model.getRoot();
/* 704 */             TreePath path = new TreePath((Object[])TableReportTreeTable.this.model.getPathToRoot((TreeTableNode)rootNode));
/* 705 */             TableReportTreeTable.this.expandPath(path);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private void saveColumnVisibility() {
/* 712 */     this.columnVisibilities.clear();
/*     */     
/* 714 */     for (int index = 0; index < this.reportInfo.getHeaderNames().size(); index++) {
/*     */       
/* 716 */       String identifier = this.reportInfo.getHeaderNames().get(index);
/* 717 */       this.columnVisibilities.add(Boolean.valueOf(getColumnExt(identifier).isVisible()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void restoreColumnVisibility() {
/* 723 */     SwingUtilities.invokeLater(new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 727 */             for (String identifier : TableReportTreeTable.this.reportInfo.getHeaderNames()) {
/*     */               
/* 729 */               TableColumnExt tc = TableReportTreeTable.this.getColumnExt(identifier);
/* 730 */               TableReportTreeTable.this.getColumnExt(identifier).setVisible(((Boolean)TableReportTreeTable.this.columnVisibilities.get(tc.getModelIndex())).booleanValue());
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 739 */     if (e.getActionCommand().equals("cancel_order")) {
/*     */       
/* 741 */       Point p = MouseInfo.getPointerInfo().getLocation();
/* 742 */       SwingUtilities.convertPointFromScreen(p, (Component)this);
/* 743 */       int viewIndex = getTableHeader().columnAtPoint(p);
/* 744 */       int modelIndex = convertColumnIndexToModel(viewIndex);
/*     */       
/* 746 */       if (this.headerInfos.get(Integer.valueOf(modelIndex)) != null) {
/*     */         
/* 748 */         TableReportHeaderInfo headerInfo = this.headerInfos.get(Integer.valueOf(modelIndex));
/* 749 */         headerInfo.setSortDirection(TableReportConstants.SortDirection.UNDEFINED);
/* 750 */         getTableHeader().repaint();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<Integer, TableReportHeaderInfo> getHeaderInfos() {
/* 757 */     return this.headerInfos;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\TableReportTreeTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */