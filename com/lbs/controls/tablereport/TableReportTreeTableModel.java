/*     */ package com.lbs.controls.tablereport;
/*     */ 
/*     */ import com.lbs.controls.pivottable.PivotCellValue;
/*     */ import com.lbs.platform.interfaces.ICachedList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Vector;
/*     */ import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
/*     */ import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
/*     */ import org.jdesktop.swingx.treetable.MutableTreeTableNode;
/*     */ import org.jdesktop.swingx.treetable.TreeTableNode;
/*     */ 
/*     */ public class TableReportTreeTableModel
/*     */   extends DefaultTreeTableModel
/*     */ {
/*     */   private ICachedList<RowObject> dataList;
/*     */   private Vector<TableReportHeaderInfo> groupHeaderIndexes;
/*     */   
/*     */   public TableReportTreeTableModel(ICachedList<RowObject> dataList, DefaultMutableTreeTableNode root, Vector<String> headerNames) {
/*  20 */     super((TreeTableNode)root);
/*  21 */     this.dataList = dataList;
/*     */     
/*  23 */     setColumnIdentifiers(headerNames);
/*  24 */     this.groupHeaderIndexes = new Vector<>();
/*     */     
/*  26 */     root.setUserObject("");
/*     */     
/*  28 */     group();
/*     */   }
/*     */ 
/*     */   
/*     */   public Class<?> getColumnClass(int column) {
/*  33 */     if (this.dataList.size() == 0)
/*     */     {
/*  35 */       return Object.class;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  41 */       if ((((RowObject)this.dataList.get(0)).getRowData()).length > column) {
/*     */         
/*  43 */         Object value = ((RowObject)this.dataList.get(0)).getRowData()[column];
/*  44 */         if (value == null)
/*     */         {
/*  46 */           return Object.class;
/*     */         }
/*     */ 
/*     */         
/*  50 */         return value.getClass();
/*     */       } 
/*     */ 
/*     */       
/*  54 */       return String.class;
/*     */     
/*     */     }
/*  57 */     catch (Exception e) {
/*     */       
/*  59 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearNodes() {
/*  66 */     DefaultMutableTreeTableNode rootNode = (DefaultMutableTreeTableNode)this.root;
/*     */     
/*  68 */     int count = rootNode.getChildCount();
/*  69 */     for (int index = count - 1; index >= 0; index--)
/*     */     {
/*  71 */       rootNode.remove(index);
/*     */     }
/*     */     
/*  74 */     this.modelSupport.fireNewRoot();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeGroupHeaderIndex(Integer columnIndex) {
/*  79 */     for (TableReportHeaderInfo info : this.groupHeaderIndexes) {
/*     */       
/*  81 */       if (info.getColumnIndex().equals(columnIndex)) {
/*     */         
/*  83 */         removeGroupHeaderIndex(info);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeGroupHeaderIndex(TableReportHeaderInfo info) {
/*  91 */     if (this.groupHeaderIndexes.contains(info)) {
/*     */       
/*  93 */       this.groupHeaderIndexes.remove(info);
/*  94 */       group();
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addGroupHeaderIndex(TableReportHeaderInfo info) {
/* 101 */     if (this.groupHeaderIndexes.contains(info))
/*     */     {
/* 103 */       this.groupHeaderIndexes.remove(info);
/*     */     }
/*     */     
/* 106 */     this.groupHeaderIndexes.add(info);
/* 107 */     group();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getColumnName(int column) {
/* 112 */     return this.columnIdentifiers.get(column).toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCellEditable(Object node, int column) {
/* 117 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void display(ArrayList<RowObject> list) {
/* 126 */     for (int index = 0; index < list.size(); index++) {
/*     */       
/* 128 */       Object[] rowData = ((RowObject)list.get(index)).getRowData();
/* 129 */       StringBuffer buffer = new StringBuffer("");
/*     */       
/* 131 */       buffer.append("index : " + index + "\t");
/* 132 */       for (int i = 0; i < rowData.length; i++) {
/*     */         
/* 134 */         buffer.append(rowData[i]);
/*     */         
/* 136 */         if (i < rowData.length - 1)
/*     */         {
/* 138 */           buffer.append(", ");
/*     */         }
/*     */       } 
/*     */       
/* 142 */       System.out.println(buffer);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isFiltered(RowObject rowObject) {
/* 148 */     for (TableReportHeaderInfo headerInfo : this.groupHeaderIndexes) {
/*     */       
/* 150 */       if (headerInfo.isFiltered(rowObject))
/*     */       {
/* 152 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 156 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void group() {
/* 161 */     group((HashMap<Integer, TableReportHeaderInfo>)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void group(HashMap<Integer, TableReportHeaderInfo> headerInfos) {
/* 166 */     clearNodes();
/* 167 */     DefaultMutableTreeTableNode rootNode = (DefaultMutableTreeTableNode)this.root;
/* 168 */     RowObject.setHeaderInfos(headerInfos);
/* 169 */     RowObject.setHeaders(this.groupHeaderIndexes);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     RowObject previousRowObject = null;
/* 175 */     DefaultMutableTreeTableNode[] nodes = new DefaultMutableTreeTableNode[this.groupHeaderIndexes.size()];
/*     */     
/* 177 */     for (int index = 0; index < this.dataList.size(); index++) {
/*     */       
/* 179 */       RowObject rowObject = (RowObject)this.dataList.get(index);
/*     */       
/* 181 */       if (!isFiltered(rowObject)) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 186 */         DefaultMutableTreeTableNode node = new DefaultMutableTreeTableNode(rowObject);
/* 187 */         DefaultMutableTreeTableNode parent = rootNode;
/*     */         
/* 189 */         if (previousRowObject == null) {
/*     */           
/* 191 */           if (this.groupHeaderIndexes.size() > 0)
/*     */           {
/* 193 */             for (int i = 0; i < this.groupHeaderIndexes.size(); i++) {
/*     */               
/* 195 */               Integer groupHeaderIndex = ((TableReportHeaderInfo)this.groupHeaderIndexes.get(i)).getColumnIndex();
/* 196 */               Object value = rowObject.getRowData()[groupHeaderIndex.intValue()];
/* 197 */               PairValue pairValue = new PairValue(((TableReportHeaderInfo)this.groupHeaderIndexes.get(i)).getTitle(), value);
/* 198 */               Object[] rowData = new Object[getColumnCount()];
/* 199 */               rowData[0] = pairValue;
/* 200 */               nodes[i] = new DefaultMutableTreeTableNode(new RowObject(null, rowData));
/*     */               
/* 202 */               if (i > 0)
/*     */               {
/* 204 */                 nodes[i - 1].add((MutableTreeTableNode)nodes[i]);
/*     */               }
/*     */             } 
/*     */             
/* 208 */             rootNode.add((MutableTreeTableNode)nodes[0]);
/* 209 */             parent = nodes[this.groupHeaderIndexes.size() - 1];
/*     */           
/*     */           }
/*     */           else
/*     */           {
/* 214 */             parent = rootNode;
/*     */           }
/*     */         
/*     */         }
/* 218 */         else if (previousRowObject.compareSimple(rowObject) == 0) {
/*     */           
/* 220 */           if (this.groupHeaderIndexes.size() > 0)
/*     */           {
/* 222 */             parent = nodes[this.groupHeaderIndexes.size() - 1];
/*     */           }
/*     */           else
/*     */           {
/* 226 */             parent = rootNode;
/*     */           }
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 232 */           boolean control = true;
/* 233 */           for (int i = 0; i < this.groupHeaderIndexes.size(); i++) {
/*     */             
/* 235 */             Integer groupHeaderIndex = ((TableReportHeaderInfo)this.groupHeaderIndexes.get(i)).getColumnIndex();
/* 236 */             Object value = rowObject.getRowData()[groupHeaderIndex.intValue()];
/* 237 */             PairValue pairValue = new PairValue(((TableReportHeaderInfo)this.groupHeaderIndexes.get(i)).getTitle(), value);
/* 238 */             Object[] rowData = new Object[getColumnCount()];
/* 239 */             rowData[0] = pairValue;
/*     */             
/* 241 */             control &= pairValue.equals(nodes[i].getUserObject());
/*     */             
/* 243 */             if (!control) {
/*     */               
/* 245 */               nodes[i] = new DefaultMutableTreeTableNode(new RowObject(null, rowData));
/*     */               
/* 247 */               if (i == 0) {
/*     */                 
/* 249 */                 rootNode.add((MutableTreeTableNode)nodes[0]);
/* 250 */                 parent = nodes[0];
/*     */               
/*     */               }
/*     */               else {
/*     */                 
/* 255 */                 nodes[i - 1].add((MutableTreeTableNode)nodes[i]);
/* 256 */                 parent = nodes[i];
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 262 */         parent.add((MutableTreeTableNode)node);
/* 263 */         previousRowObject = rowObject;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getValueAt(Object node, int column) {
/* 270 */     DefaultMutableTreeTableNode n = (DefaultMutableTreeTableNode)node;
/* 271 */     if (!(n.getUserObject() instanceof RowObject))
/*     */     {
/* 273 */       return "";
/*     */     }
/*     */     
/* 276 */     RowObject rowObject = (RowObject)n.getUserObject();
/* 277 */     if ((rowObject.getRowData()).length > column) {
/* 278 */       return rowObject.getRowData()[column];
/*     */     }
/* 280 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector<TableReportHeaderInfo> getGroupHeaderIndexes() {
/* 285 */     return this.groupHeaderIndexes;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector<Object> getObjectList(DefaultMutableTreeTableNode root, int columnIndex) {
/* 290 */     Vector<Object> vector = new Vector();
/* 291 */     DefaultMutableTreeTableNode rootNode = root;
/*     */     
/* 293 */     if (rootNode == null)
/*     */     {
/* 295 */       rootNode = (DefaultMutableTreeTableNode)getRoot();
/*     */     }
/*     */     
/* 298 */     if (!rootNode.isLeaf()) {
/*     */       
/* 300 */       for (int index = 0; index < rootNode.getChildCount(); index++)
/*     */       {
/* 302 */         DefaultMutableTreeTableNode node = (DefaultMutableTreeTableNode)rootNode.getChildAt(index);
/* 303 */         vector.addAll(getObjectList(node, columnIndex));
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 309 */       RowObject rowObject = (RowObject)rootNode.getUserObject();
/* 310 */       if ((rowObject.getRowData()).length > columnIndex) {
/*     */         
/* 312 */         Object object = rowObject.getRowData()[columnIndex];
/*     */         
/* 314 */         if (object != null) {
/*     */           
/* 316 */           if (object instanceof PivotCellValue) {
/*     */             
/* 318 */             PivotCellValue pcv = (PivotCellValue)object;
/* 319 */             object = pcv.getValue();
/*     */           } 
/* 321 */           vector.add(object);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 326 */     return vector;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNumber(int columnIndex) {
/* 331 */     Vector<Object> vector = getObjectList((DefaultMutableTreeTableNode)null, columnIndex);
/* 332 */     boolean number = true;
/*     */     
/* 334 */     for (int index = 0; index < vector.size(); index++) {
/*     */       
/* 336 */       Object object = vector.get(index);
/* 337 */       if (object != null) {
/*     */         
/* 339 */         number &= object instanceof Number;
/* 340 */         if (!number)
/*     */         {
/* 342 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 347 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\TableReportTreeTableModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */