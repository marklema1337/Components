/*      */ package info.clearthought.layout;
/*      */ 
/*      */ import com.lbs.controls.JLbsButton;
/*      */ import com.lbs.util.JLbsConstants;
/*      */ import java.awt.Component;
/*      */ import java.awt.ComponentOrientation;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Insets;
/*      */ import java.awt.LayoutManager2;
/*      */ import java.io.Serializable;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TableLayout
/*      */   implements LayoutManager2, Serializable, TableLayoutConstants
/*      */ {
/*  281 */   protected static final double[][] defaultSize = new double[][] { {}, {} };
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int C = 0;
/*      */ 
/*      */   
/*      */   public static final int R = 1;
/*      */ 
/*      */   
/*      */   public static final double STANDART_SIZE = 10.0D;
/*      */ 
/*      */   
/*      */   protected static boolean checkForComponentOrientationSupport = true;
/*      */ 
/*      */   
/*      */   protected static Method methodGetComponentOrientation;
/*      */ 
/*      */   
/*  300 */   protected double[][] crSpec = new double[2][];
/*      */ 
/*      */   
/*  303 */   protected int[][] crSize = new int[2][];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  308 */   protected int[][] crOffset = new int[2][];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected List<Entry> list;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean dirty;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int oldWidth;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int oldHeight;
/*      */ 
/*      */ 
/*      */   
/*      */   protected int hGap;
/*      */ 
/*      */ 
/*      */   
/*      */   protected int vGap;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TableLayout() {
/*  342 */     init(defaultSize[0], defaultSize[1]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TableLayout(double[][] size) {
/*  357 */     if (size != null && size.length == 2) {
/*  358 */       init(size[0], size[1]);
/*      */     } else {
/*  360 */       throw new IllegalArgumentException("Parameter size should be an array, a[2], where a[0] is the is an array of column widths and a[1] is an array or row heights.");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TableLayout(double[] col, double[] row) {
/*  373 */     init(col, row);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void init(double[] col, double[] row) {
/*  386 */     if (col == null) {
/*  387 */       throw new IllegalArgumentException("Parameter col cannot be null");
/*      */     }
/*  389 */     if (row == null) {
/*  390 */       throw new IllegalArgumentException("Parameter row cannot be null");
/*      */     }
/*      */     
/*  393 */     this.crSpec[0] = new double[col.length];
/*  394 */     this.crSpec[1] = new double[row.length];
/*      */ 
/*      */     
/*  397 */     System.arraycopy(col, 0, this.crSpec[0], 0, (this.crSpec[0]).length);
/*  398 */     System.arraycopy(row, 0, this.crSpec[1], 0, (this.crSpec[1]).length);
/*      */     
/*      */     int counter;
/*  401 */     for (counter = 0; counter < (this.crSpec[0]).length; counter++) {
/*      */       
/*  403 */       if (this.crSpec[0][counter] < 0.0D && this.crSpec[0][counter] != -1.0D && this.crSpec[0][counter] != -2.0D && 
/*  404 */         this.crSpec[0][counter] != -3.0D) {
/*      */         
/*  406 */         this.crSpec[0][counter] = 0.0D;
/*      */       }
/*  408 */       else if (this.crSpec[0][counter] == 6.0D) {
/*  409 */         this.crSpec[0][counter] = 10.0D;
/*      */       } 
/*  411 */     }  for (counter = 0; counter < (this.crSpec[1]).length; counter++) {
/*      */       
/*  413 */       if (this.crSpec[1][counter] < 0.0D && this.crSpec[1][counter] != -1.0D && this.crSpec[1][counter] != -2.0D && 
/*  414 */         this.crSpec[1][counter] != -3.0D) {
/*      */         
/*  416 */         this.crSpec[1][counter] = 0.0D;
/*      */       }
/*  418 */       else if (this.crSpec[1][counter] == 6.0D) {
/*  419 */         this.crSpec[1][counter] = 10.0D;
/*      */       } 
/*      */     } 
/*      */     
/*  423 */     this.list = new ArrayList<>();
/*      */ 
/*      */     
/*  426 */     this.dirty = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TableLayoutConstraints getConstraints(Component component) {
/*  445 */     for (Entry entry : this.list) {
/*      */       
/*  447 */       if (entry.component == component)
/*  448 */         return new TableLayoutConstraints(entry.cr1[0], entry.cr1[1], entry.cr2[0], entry.cr2[1], entry.alignment[0], 
/*  449 */             entry.alignment[1]); 
/*      */     } 
/*  451 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConstraints(Component component, TableLayoutConstraints constraint) {
/*  464 */     if (component == null)
/*  465 */       throw new IllegalArgumentException("Parameter component cannot be null."); 
/*  466 */     if (constraint == null) {
/*  467 */       throw new IllegalArgumentException("Parameter constraint cannot be null.");
/*      */     }
/*  469 */     ListIterator<Entry> iterator = this.list.listIterator(0);
/*      */     
/*  471 */     while (iterator.hasNext()) {
/*      */       
/*  473 */       Entry entry = iterator.next();
/*      */       
/*  475 */       if (entry.component == component) {
/*  476 */         iterator.set(new Entry(component, constraint));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColumn(double[] column) {
/*  506 */     setCr(0, column);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRow(double[] row) {
/*  535 */     setCr(1, row);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setCr(int z, double[] size) {
/*  548 */     this.crSpec[z] = new double[size.length];
/*  549 */     System.arraycopy(size, 0, this.crSpec[z], 0, (this.crSpec[z]).length);
/*      */ 
/*      */     
/*  552 */     for (int counter = 0; counter < (this.crSpec[z]).length; counter++) {
/*      */       
/*  554 */       if (this.crSpec[z][counter] < 0.0D && this.crSpec[z][counter] != -1.0D && this.crSpec[z][counter] != -2.0D && 
/*  555 */         this.crSpec[z][counter] != -3.0D) {
/*      */         
/*  557 */         this.crSpec[z][counter] = 0.0D;
/*      */       }
/*  559 */       else if (this.crSpec[z][counter] == 6.0D) {
/*  560 */         this.crSpec[z][counter] = 10.0D;
/*      */       } 
/*      */     } 
/*  563 */     this.dirty = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColumn(int i, double size) {
/*  594 */     setCr(0, i, size);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRow(int i, double size) {
/*  625 */     setCr(1, i, size);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setCr(int z, int i, double size) {
/*  639 */     if (size < 0.0D && size != -1.0D && size != -2.0D && size != -3.0D) {
/*      */       
/*  641 */       size = 0.0D;
/*      */     }
/*  643 */     else if (size == 6.0D) {
/*  644 */       size = 10.0D;
/*      */     } 
/*      */     
/*  647 */     this.crSpec[z][i] = size;
/*      */ 
/*      */     
/*  650 */     this.dirty = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double[] getColumn() {
/*  664 */     double[] column = new double[(this.crSpec[0]).length];
/*  665 */     System.arraycopy(this.crSpec[0], 0, column, 0, column.length);
/*      */     
/*  667 */     return column;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double[] getRow() {
/*  681 */     double[] row = new double[(this.crSpec[1]).length];
/*  682 */     System.arraycopy(this.crSpec[1], 0, row, 0, row.length);
/*      */     
/*  684 */     return row;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getColumn(int i) {
/*  700 */     return this.crSpec[0][i];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getRow(int i) {
/*  716 */     return this.crSpec[1][i];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumColumn() {
/*  727 */     return (this.crSpec[0]).length;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumRow() {
/*  738 */     return (this.crSpec[1]).length;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHGap() {
/*  749 */     return this.hGap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVGap() {
/*  760 */     return this.vGap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHGap(int hGap) {
/*  771 */     if (hGap >= 0) {
/*  772 */       this.hGap = hGap;
/*      */     } else {
/*  774 */       throw new IllegalArgumentException("Parameter hGap must be non-negative.");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVGap(int vGap) {
/*  785 */     if (vGap >= 0) {
/*  786 */       this.vGap = vGap;
/*      */     } else {
/*  788 */       throw new IllegalArgumentException("Parameter vGap must be non-negative.");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void insertColumn(int i, double size) {
/*  809 */     insertCr(0, i, size);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void insertRow(int i, double size) {
/*  826 */     insertCr(1, i, size);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void insertCr(int z, int i, double size) {
/*  840 */     if (i < 0 || i > (this.crSpec[z]).length) {
/*  841 */       throw new IllegalArgumentException("Parameter i is invalid.  i = " + i + ".  Valid range is [0, " + (this.crSpec[z]).length + 
/*  842 */           "].");
/*      */     }
/*      */     
/*  845 */     if (size < 0.0D && size != -1.0D && size != -2.0D && size != -3.0D) {
/*      */       
/*  847 */       size = 0.0D;
/*      */     }
/*  849 */     else if (size == 6.0D) {
/*  850 */       size = 10.0D;
/*      */     } 
/*      */ 
/*      */     
/*  854 */     double[] cr = new double[(this.crSpec[z]).length + 1];
/*  855 */     System.arraycopy(this.crSpec[z], 0, cr, 0, i);
/*  856 */     System.arraycopy(this.crSpec[z], i, cr, i + 1, (this.crSpec[z]).length - i);
/*      */ 
/*      */     
/*  859 */     cr[i] = size;
/*  860 */     this.crSpec[z] = cr;
/*      */     
/*  862 */     for (Entry entry : this.list) {
/*      */ 
/*      */       
/*  865 */       if (entry.cr1[z] >= i)
/*      */       {
/*  867 */         entry.cr1[z] = entry.cr1[z] + 1;
/*      */       }
/*      */       
/*  870 */       if (entry.cr2[z] >= i)
/*      */       {
/*  872 */         entry.cr2[z] = entry.cr2[z] + 1;
/*      */       }
/*      */     } 
/*      */     
/*  876 */     this.dirty = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void deleteColumn(int i) {
/*  892 */     deleteCr(0, i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void deleteRow(int i) {
/*  909 */     deleteCr(1, i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void deleteCr(int z, int i) {
/*  922 */     if (i < 0 || i >= (this.crSpec[z]).length) {
/*  923 */       throw new IllegalArgumentException("Parameter i is invalid.  i = " + i + ".  Valid range is [0, " + ((
/*  924 */           this.crSpec[z]).length - 1) + "].");
/*      */     }
/*      */     
/*  927 */     double[] cr = new double[(this.crSpec[z]).length - 1];
/*  928 */     System.arraycopy(this.crSpec[z], 0, cr, 0, i);
/*  929 */     System.arraycopy(this.crSpec[z], i + 1, cr, i, (this.crSpec[z]).length - i - 1);
/*      */ 
/*      */     
/*  932 */     this.crSpec[z] = cr;
/*      */ 
/*      */     
/*  935 */     ListIterator<Entry> iterator = this.list.listIterator(0);
/*      */     
/*  937 */     while (iterator.hasNext()) {
/*      */ 
/*      */       
/*  940 */       Entry entry = iterator.next();
/*      */ 
/*      */       
/*  943 */       if (entry.cr1[z] > i)
/*      */       {
/*  945 */         entry.cr1[z] = entry.cr1[z] - 1;
/*      */       }
/*      */       
/*  948 */       if (entry.cr2[z] > i)
/*      */       {
/*  950 */         entry.cr2[z] = entry.cr2[z] - 1;
/*      */       }
/*      */     } 
/*      */     
/*  954 */     this.dirty = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  972 */     String value = "TableLayout {{";
/*  973 */     StringBuilder stringBuilder = new StringBuilder();
/*      */     
/*  975 */     if ((this.crSpec[0]).length > 0) {
/*      */       
/*  977 */       for (int counter = 0; counter < (this.crSpec[0]).length - 1; counter++) {
/*  978 */         stringBuilder.append(String.valueOf(this.crSpec[0][counter]) + ", ");
/*      */       }
/*  980 */       value = String.valueOf(value) + stringBuilder.toString();
/*  981 */       value = String.valueOf(value) + this.crSpec[0][(this.crSpec[0]).length - 1] + "}, {";
/*      */     } else {
/*      */       
/*  984 */       value = String.valueOf(value) + "}, {";
/*      */     } 
/*  986 */     if ((this.crSpec[1]).length > 0) {
/*      */       
/*  988 */       stringBuilder = new StringBuilder();
/*  989 */       for (int counter = 0; counter < (this.crSpec[1]).length - 1; counter++) {
/*  990 */         stringBuilder.append(String.valueOf(this.crSpec[1][counter]) + ", ");
/*      */       }
/*  992 */       value = String.valueOf(value) + stringBuilder.toString();
/*  993 */       value = String.valueOf(value) + this.crSpec[1][(this.crSpec[1]).length - 1] + "}}";
/*      */     } else {
/*      */       
/*  996 */       value = String.valueOf(value) + "}}";
/*      */     } 
/*  998 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getInvalidEntry() {
/* 1016 */     LinkedList<Object> listInvalid = new LinkedList();
/*      */ 
/*      */     
/*      */     try {
/* 1020 */       ListIterator<Entry> iterator = this.list.listIterator(0);
/*      */       
/* 1022 */       while (iterator.hasNext())
/*      */       {
/* 1024 */         Entry entry = iterator.next();
/*      */         
/* 1026 */         if (entry.cr1[1] < 0 || entry.cr1[0] < 0 || entry.cr2[1] >= (this.crSpec[1]).length || 
/* 1027 */           entry.cr2[0] >= (this.crSpec[0]).length)
/*      */         {
/* 1029 */           listInvalid.add(entry.copy());
/*      */         }
/*      */       }
/*      */     
/* 1033 */     } catch (CloneNotSupportedException error) {
/*      */       
/* 1035 */       throw new RuntimeException("Unexpected CloneNotSupportedException");
/*      */     } 
/*      */     
/* 1038 */     return listInvalid;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getOverlappingEntry() {
/* 1053 */     LinkedList<Object> listOverlapping = new LinkedList();
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1058 */       int numEntry = this.list.size();
/*      */ 
/*      */       
/* 1061 */       if (numEntry == 0) {
/* 1062 */         return listOverlapping;
/*      */       }
/*      */       
/* 1065 */       Entry[] entry = this.list.<Entry>toArray(new Entry[numEntry]);
/*      */ 
/*      */       
/* 1068 */       for (int knowUnique = 1; knowUnique < numEntry; knowUnique++) {
/* 1069 */         for (int checking = knowUnique - 1; checking >= 0; checking--) {
/* 1070 */           if (((entry[checking]).cr1[0] >= (entry[knowUnique]).cr1[0] && 
/* 1071 */             (entry[checking]).cr1[0] <= (entry[knowUnique]).cr2[0] && 
/* 1072 */             (entry[checking]).cr1[1] >= (entry[knowUnique]).cr1[1] && (entry[checking]).cr1[1] <= (entry[knowUnique]).cr2[1]) || (
/* 1073 */             (entry[checking]).cr2[0] >= (entry[knowUnique]).cr1[0] && 
/* 1074 */             (entry[checking]).cr2[0] <= (entry[knowUnique]).cr2[0] && 
/* 1075 */             (entry[checking]).cr2[1] >= (entry[knowUnique]).cr1[1] && (entry[checking]).cr2[1] <= (entry[knowUnique]).cr2[1]))
/*      */           {
/* 1077 */             listOverlapping.add(entry[checking].copy()); } 
/*      */         } 
/*      */       } 
/* 1080 */     } catch (CloneNotSupportedException error) {
/*      */       
/* 1082 */       throw new RuntimeException("Unexpected CloneNotSupportedException");
/*      */     } 
/*      */     
/* 1085 */     return listOverlapping;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void calculateSize(Container container) {
/* 1104 */     Insets inset = container.getInsets();
/*      */ 
/*      */     
/* 1107 */     Dimension d = container.getSize();
/* 1108 */     int availableWidth = d.width - inset.left - inset.right;
/* 1109 */     int availableHeight = d.height - inset.top - inset.bottom;
/*      */ 
/*      */     
/* 1112 */     if ((this.crSpec[0]).length > 0) {
/* 1113 */       availableWidth -= this.hGap * ((this.crSpec[0]).length - 1);
/*      */     }
/* 1115 */     if ((this.crSpec[1]).length > 0)
/*      */     {
/* 1117 */       if (JLbsConstants.DESKTOP_MODE && this.vGap != 0) {
/*      */         
/* 1119 */         int count = 0;
/* 1120 */         for (int i = 0; i < (this.crSpec[1]).length; i++) {
/*      */           
/* 1122 */           if (this.crSpec[1][i] != 0.0D) {
/* 1123 */             count++;
/*      */           }
/*      */         } 
/* 1126 */         availableHeight -= this.vGap * (count - 1);
/*      */       }
/*      */       else {
/*      */         
/* 1130 */         availableHeight -= this.vGap * ((this.crSpec[1]).length - 1);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1135 */     this.crSize[0] = new int[(this.crSpec[0]).length];
/* 1136 */     this.crSize[1] = new int[(this.crSpec[1]).length];
/*      */ 
/*      */     
/* 1139 */     availableWidth = assignAbsoluteSize(0, availableWidth);
/* 1140 */     availableHeight = assignAbsoluteSize(1, availableHeight);
/*      */ 
/*      */     
/* 1143 */     HashMap<Integer, Dimension> minimums = new HashMap<>();
/* 1144 */     HashMap<Integer, Dimension> preferreds = new HashMap<>();
/* 1145 */     availableWidth = assignPrefMinSize(0, availableWidth, -3.0D, minimums);
/* 1146 */     availableWidth = assignPrefMinSize(0, availableWidth, -2.0D, preferreds);
/* 1147 */     availableHeight = assignPrefMinSize(1, availableHeight, -3.0D, minimums);
/* 1148 */     availableHeight = assignPrefMinSize(1, availableHeight, -2.0D, preferreds);
/*      */ 
/*      */     
/* 1151 */     availableWidth = assignRelativeSize(0, availableWidth);
/* 1152 */     availableHeight = assignRelativeSize(1, availableHeight);
/*      */ 
/*      */     
/* 1155 */     assignFillSize(0, availableWidth);
/* 1156 */     assignFillSize(1, availableHeight);
/*      */ 
/*      */     
/* 1159 */     calculateOffset(0, inset);
/* 1160 */     calculateOffset(1, inset);
/*      */ 
/*      */ 
/*      */     
/* 1164 */     this.dirty = false;
/* 1165 */     this.oldWidth = d.width;
/* 1166 */     this.oldHeight = d.height;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int assignAbsoluteSize(int z, int availableSize) {
/* 1181 */     int numCr = (this.crSpec[z]).length;
/*      */     
/* 1183 */     for (int counter = 0; counter < numCr; counter++) {
/* 1184 */       if (this.crSpec[z][counter] >= 1.0D || this.crSpec[z][counter] == 0.0D) {
/*      */         
/* 1186 */         this.crSize[z][counter] = (int)(this.crSpec[z][counter] + 0.5D);
/* 1187 */         availableSize -= this.crSize[z][counter];
/*      */       } 
/*      */     } 
/* 1190 */     return availableSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int assignRelativeSize(int z, int availableSize) {
/* 1205 */     int relativeSize = (availableSize < 0) ? 
/* 1206 */       0 : 
/* 1207 */       availableSize;
/* 1208 */     int numCr = (this.crSpec[z]).length;
/*      */     
/* 1210 */     for (int counter = 0; counter < numCr; counter++) {
/* 1211 */       if (this.crSpec[z][counter] > 0.0D && this.crSpec[z][counter] < 1.0D) {
/*      */         
/* 1213 */         this.crSize[z][counter] = (int)(this.crSpec[z][counter] * relativeSize + 0.5D);
/*      */         
/* 1215 */         availableSize -= this.crSize[z][counter];
/*      */       } 
/*      */     } 
/* 1218 */     return availableSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void assignFillSize(int z, int availableSize) {
/* 1231 */     if (availableSize <= 0) {
/*      */       return;
/*      */     }
/*      */     
/* 1235 */     int numFillSize = 0;
/* 1236 */     int numCr = (this.crSpec[z]).length;
/*      */     
/* 1238 */     for (int counter = 0; counter < numCr; counter++) {
/* 1239 */       if (this.crSpec[z][counter] == -1.0D) {
/* 1240 */         numFillSize++;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1247 */     int slackSize = availableSize;
/*      */     
/*      */     int i;
/* 1250 */     for (i = 0; i < numCr; i++) {
/* 1251 */       if (this.crSpec[z][i] == -1.0D) {
/*      */         
/* 1253 */         this.crSize[z][i] = availableSize / numFillSize;
/* 1254 */         slackSize -= this.crSize[z][i];
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1259 */     for (i = numCr - 1; i >= 0 && slackSize > 0; i--) {
/*      */       
/* 1261 */       if (this.crSpec[z][i] == -1.0D) {
/*      */         
/* 1263 */         this.crSize[z][i] = this.crSize[z][i] + 1;
/* 1264 */         slackSize--;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void calculateOffset(int z, Insets inset) {
/* 1277 */     int numCr = (this.crSpec[z]).length;
/*      */     
/* 1279 */     this.crOffset[z] = new int[numCr + 1];
/* 1280 */     this.crOffset[z][0] = (z == 0) ? 
/* 1281 */       inset.left : 
/* 1282 */       inset.top;
/*      */     
/* 1284 */     for (int counter = 0; counter < numCr; counter++) {
/* 1285 */       this.crOffset[z][counter + 1] = this.crOffset[z][counter] + this.crSize[z][counter];
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Dimension getSize(HashMap<Integer, Dimension> sizes, Entry entry, int id, double typeOfSize) {
/* 1306 */     Dimension dim = sizes.get(Integer.valueOf(id));
/* 1307 */     if (dim == null) {
/*      */       
/* 1309 */       if (typeOfSize == -2.0D) {
/* 1310 */         dim = entry.component.getPreferredSize();
/*      */       } else {
/* 1312 */         dim = entry.component.getMinimumSize();
/* 1313 */       }  sizes.put(Integer.valueOf(id), dim);
/*      */     } 
/* 1315 */     return dim;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected int assignPrefMinSize(int z, int availableSize, double typeOfSize, HashMap<Integer, Dimension> sizes) {
/* 1321 */     int numCr = (this.crSpec[z]).length;
/*      */ 
/*      */     
/* 1324 */     for (int counter = 0; counter < numCr; counter++) {
/*      */       
/* 1326 */       if (this.crSpec[z][counter] == typeOfSize) {
/*      */ 
/*      */         
/* 1329 */         int maxSize = 0;
/*      */         
/* 1331 */         int i = 0;
/* 1332 */         label59: for (Entry entry : this.list) {
/*      */           
/* 1334 */           i++;
/*      */           
/* 1336 */           if (entry.cr1[z] < 0 || entry.cr2[z] >= numCr) {
/*      */             continue;
/*      */           }
/*      */ 
/*      */           
/* 1341 */           if (entry.cr1[z] <= counter && entry.cr2[z] >= counter) {
/*      */ 
/*      */             
/* 1344 */             Dimension p = getSize(sizes, entry, i, typeOfSize);
/*      */             
/* 1346 */             int size = (p == null) ? 
/* 1347 */               0 : (
/* 1348 */               (z == 0) ? 
/* 1349 */               p.width : 
/* 1350 */               p.height);
/* 1351 */             int numAdjustable = 0;
/*      */ 
/*      */             
/* 1354 */             if (typeOfSize == -2.0D) {
/*      */               
/* 1356 */               for (int entryCr = entry.cr1[z]; entryCr <= entry.cr2[z]; entryCr++) {
/*      */ 
/*      */ 
/*      */                 
/* 1360 */                 if (this.crSpec[z][entryCr] >= 0.0D || this.crSpec[z][entryCr] == -3.0D) {
/*      */                   
/* 1362 */                   size -= this.crSize[z][entryCr];
/*      */                 
/*      */                 }
/* 1365 */                 else if (this.crSpec[z][entryCr] == -2.0D) {
/* 1366 */                   numAdjustable++;
/*      */ 
/*      */                 
/*      */                 }
/* 1370 */                 else if (this.crSpec[z][entryCr] == -1.0D) {
/*      */                   
/*      */                   continue label59;
/*      */                 } 
/*      */               } 
/*      */             } else {
/* 1376 */               for (int entryCr = entry.cr1[z]; entryCr <= entry.cr2[z]; entryCr++) {
/*      */ 
/*      */ 
/*      */                 
/* 1380 */                 if (this.crSpec[z][entryCr] >= 0.0D) {
/* 1381 */                   size -= this.crSize[z][entryCr];
/*      */                 }
/* 1383 */                 else if (this.crSpec[z][entryCr] == -2.0D || this.crSpec[z][entryCr] == -3.0D) {
/*      */                   
/* 1385 */                   numAdjustable++;
/*      */ 
/*      */ 
/*      */                 
/*      */                 }
/* 1390 */                 else if (this.crSpec[z][entryCr] == -1.0D) {
/*      */                   continue label59;
/*      */                 } 
/*      */               } 
/*      */             } 
/* 1395 */             size = (int)Math.ceil(size / numAdjustable);
/*      */ 
/*      */             
/* 1398 */             if (maxSize < size) {
/* 1399 */               maxSize = size;
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/* 1404 */         this.crSize[z][counter] = maxSize;
/*      */ 
/*      */         
/* 1407 */         availableSize -= maxSize;
/*      */       } 
/*      */     } 
/* 1410 */     return availableSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void layoutContainer(Container container) {
/* 1430 */     Dimension d = container.getSize();
/*      */     
/* 1432 */     if (this.dirty || d.width != this.oldWidth || d.height != this.oldHeight) {
/* 1433 */       calculateSize(container);
/*      */     }
/*      */     
/* 1436 */     ComponentOrientation co = getComponentOrientation(container);
/* 1437 */     boolean isRightToLeft = (co != null && !co.isLeftToRight());
/* 1438 */     Insets insets = container.getInsets();
/*      */ 
/*      */     
/* 1441 */     Component[] component = container.getComponents();
/*      */ 
/*      */     
/* 1444 */     for (int counter = 0; counter < component.length; counter++) {
/*      */ 
/*      */       
/*      */       try {
/*      */         
/* 1449 */         ListIterator<Entry> iterator = this.list.listIterator(0);
/* 1450 */         Entry entry = null;
/*      */         
/* 1452 */         while (iterator.hasNext()) {
/*      */           
/* 1454 */           entry = iterator.next();
/*      */           
/* 1456 */           if (entry.component == component[counter]) {
/*      */             break;
/*      */           }
/* 1459 */           entry = null;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1464 */         if (entry == null) {
/*      */           
/* 1466 */           component[counter].setBounds(0, 0, 0, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1477 */           int preferredWidth = 0;
/* 1478 */           int preferredHeight = 0;
/*      */           
/* 1480 */           if (entry.alignment[0] != 2 || entry.alignment[1] != 2) {
/*      */             
/* 1482 */             Dimension preferredSize = component[counter].getPreferredSize();
/*      */             
/* 1484 */             preferredWidth = preferredSize.width;
/* 1485 */             preferredHeight = preferredSize.height;
/*      */             
/* 1487 */             if (JLbsConstants.DESKTOP_MODE && component[counter] instanceof JLbsButton && preferredWidth < 58) {
/*      */               
/* 1489 */               JLbsButton button = (JLbsButton)component[counter];
/* 1490 */               if (button.getClientProperty("JButton.isFormButton") != null) {
/*      */                 
/* 1492 */                 button.setPreferredSize(new Dimension(preferredWidth = 58, preferredHeight));
/* 1493 */                 button.setMinimumSize(new Dimension(preferredWidth, preferredHeight));
/*      */               } 
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1500 */           int[] value = calculateSizeAndOffset(entry, preferredWidth, true);
/* 1501 */           int x = value[0];
/* 1502 */           int w = value[1];
/* 1503 */           value = calculateSizeAndOffset(entry, preferredHeight, false);
/* 1504 */           int y = value[0];
/* 1505 */           int h = value[1];
/*      */ 
/*      */           
/* 1508 */           if (isRightToLeft) {
/* 1509 */             x = d.width - x - w + insets.left - insets.right;
/*      */           }
/*      */           
/* 1512 */           component[counter].setBounds(x, y, w, h);
/*      */         } 
/* 1514 */       } catch (Exception error) {
/*      */ 
/*      */ 
/*      */         
/* 1518 */         component[counter].setBounds(0, 0, 0, 0);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ComponentOrientation getComponentOrientation(Container container) {
/* 1539 */     ComponentOrientation co = null;
/*      */ 
/*      */     
/*      */     try {
/* 1543 */       if (checkForComponentOrientationSupport) {
/*      */         
/* 1545 */         methodGetComponentOrientation = Class.forName("java.awt.Container").getMethod("getComponentOrientation", 
/* 1546 */             new Class[0]);
/*      */         
/* 1548 */         checkForComponentOrientationSupport = false;
/*      */       } 
/*      */       
/* 1551 */       if (methodGetComponentOrientation != null)
/*      */       {
/* 1553 */         co = (ComponentOrientation)methodGetComponentOrientation.invoke(container, new Object[0]);
/*      */       }
/*      */     }
/* 1556 */     catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */     
/* 1560 */     return co;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int[] calculateSizeAndOffset(Entry entry, int preferredSize, boolean isColumn) {
/* 1579 */     int size, offset, crOffset[] = isColumn ? 
/* 1580 */       this.crOffset[0] : 
/* 1581 */       this.crOffset[1];
/* 1582 */     int entryAlignment = isColumn ? 
/* 1583 */       entry.alignment[0] : 
/* 1584 */       entry.alignment[1];
/*      */ 
/*      */     
/* 1587 */     int cellSetSize = isColumn ? (
/* 1588 */       crOffset[entry.cr2[0] + 1] - crOffset[entry.cr1[0]]) : (
/* 1589 */       crOffset[entry.cr2[1] + 1] - crOffset[entry.cr1[1]]);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1594 */     if (entryAlignment == 2 || cellSetSize < preferredSize) {
/* 1595 */       size = cellSetSize;
/*      */     } else {
/* 1597 */       size = preferredSize;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1602 */     if (isColumn && entryAlignment == 4) {
/* 1603 */       entryAlignment = 0;
/*      */     }
/* 1605 */     if (isColumn && entryAlignment == 5) {
/* 1606 */       entryAlignment = 3;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1611 */     switch (entryAlignment) {
/*      */       
/*      */       case 0:
/* 1614 */         offset = crOffset[isColumn ? 
/* 1615 */             entry.cr1[0] : 
/* 1616 */             entry.cr1[1]];
/*      */         break;
/*      */       
/*      */       case 3:
/* 1620 */         offset = crOffset[(isColumn ? 
/* 1621 */             entry.cr2[0] : 
/* 1622 */             entry.cr2[1]) + 1] - size;
/*      */         break;
/*      */       
/*      */       case 1:
/* 1626 */         offset = crOffset[isColumn ? 
/* 1627 */             entry.cr1[0] : 
/* 1628 */             entry.cr1[1]] + (cellSetSize - size >> 1);
/*      */         break;
/*      */       
/*      */       case 2:
/* 1632 */         offset = crOffset[isColumn ? 
/* 1633 */             entry.cr1[0] : 
/* 1634 */             entry.cr1[1]];
/*      */         break;
/*      */       
/*      */       default:
/* 1638 */         offset = 0;
/*      */         break;
/*      */     } 
/*      */     
/* 1642 */     if (isColumn) {
/*      */       
/* 1644 */       offset += this.hGap * entry.cr1[0];
/* 1645 */       int cumlativeGap = this.hGap * (entry.cr2[0] - entry.cr1[0]);
/*      */       
/* 1647 */       switch (entryAlignment) {
/*      */         
/*      */         case 3:
/* 1650 */           offset += cumlativeGap;
/*      */           break;
/*      */         
/*      */         case 1:
/* 1654 */           offset += cumlativeGap >> 1;
/*      */           break;
/*      */         
/*      */         case 2:
/* 1658 */           size += cumlativeGap;
/*      */           break;
/*      */       } 
/*      */ 
/*      */     
/*      */     } else {
/* 1664 */       int cumlativeGap = 0;
/* 1665 */       if (JLbsConstants.DESKTOP_MODE && this.vGap != 0) {
/*      */         
/* 1667 */         int row1 = entry.cr1[1];
/* 1668 */         int row2 = entry.cr2[1];
/* 1669 */         int count1 = 0;
/* 1670 */         int count2 = 0;
/* 1671 */         for (int i = 0; i < row2; i++) {
/*      */           
/* 1673 */           if (this.crSpec[1][i] == 0.0D) {
/*      */             
/* 1675 */             count2++;
/* 1676 */             if (row1 > i) {
/* 1677 */               count1++;
/*      */             }
/*      */           } 
/*      */         } 
/* 1681 */         row2 -= count2;
/* 1682 */         row1 -= count1;
/* 1683 */         offset += this.vGap * row1;
/* 1684 */         cumlativeGap = this.vGap * (row2 - row1);
/*      */       }
/*      */       else {
/*      */         
/* 1688 */         offset += this.vGap * entry.cr1[1];
/* 1689 */         cumlativeGap = this.vGap * (entry.cr2[1] - entry.cr1[1]);
/*      */       } 
/*      */       
/* 1692 */       switch (entryAlignment) {
/*      */         
/*      */         case 3:
/* 1695 */           offset += cumlativeGap;
/*      */           break;
/*      */         
/*      */         case 1:
/* 1699 */           offset += cumlativeGap >> 1;
/*      */           break;
/*      */         
/*      */         case 2:
/* 1703 */           size += cumlativeGap;
/*      */           break;
/*      */       } 
/*      */ 
/*      */     
/*      */     } 
/* 1709 */     int[] value = { offset, size };
/* 1710 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Dimension preferredLayoutSize(Container container) {
/* 1732 */     return calculateLayoutSize(container, -2.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Dimension minimumLayoutSize(Container container) {
/* 1754 */     return calculateLayoutSize(container, -3.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Dimension calculateLayoutSize(Container container, double typeOfSize) {
/* 1770 */     Entry[] entryList = this.list.<Entry>toArray(new Entry[this.list.size()]);
/* 1771 */     int numEntry = entryList.length;
/* 1772 */     Dimension[] prefMinSize = new Dimension[numEntry];
/*      */     
/* 1774 */     for (int i = 0; i < numEntry; i++) {
/*      */       
/* 1776 */       Component entry = (entryList[i]).component;
/* 1777 */       prefMinSize[i] = (typeOfSize == -2.0D) ? entry.getPreferredSize() : entry.getMinimumSize();
/*      */     } 
/*      */     
/* 1780 */     HashMap<Integer, Dimension> minimumSizes = new HashMap<>();
/* 1781 */     HashMap<Integer, Dimension> preferredSizes = new HashMap<>();
/* 1782 */     int width = calculateLayoutSize(container, 0, typeOfSize, entryList, prefMinSize, minimumSizes, preferredSizes);
/* 1783 */     int height = calculateLayoutSize(container, 1, typeOfSize, entryList, prefMinSize, minimumSizes, preferredSizes);
/*      */     
/* 1785 */     Insets inset = container.getInsets();
/* 1786 */     width += inset.left + inset.right;
/* 1787 */     height += inset.top + inset.bottom;
/*      */     
/* 1789 */     if (!container.isVisible()) {
/*      */       
/* 1791 */       width = 0;
/* 1792 */       height = 0;
/* 1793 */       removeLayoutComponent(container);
/*      */     } 
/*      */     
/* 1796 */     return new Dimension(width, height);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int calculateLayoutSize(Container container, int z, double typeOfSize, Entry[] entryList, Dimension[] prefMinSize, HashMap<Integer, Dimension> minimumSizes, HashMap<Integer, Dimension> preferredSizes) {
/* 1818 */     int scaledSize = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1823 */     int numCr = (this.crSpec[z]).length;
/*      */ 
/*      */ 
/*      */     
/* 1827 */     double fillSizeRatio = 1.0D;
/* 1828 */     int numFillSize = 0;
/*      */     int counter;
/* 1830 */     for (counter = 0; counter < numCr; counter++) {
/* 1831 */       if (this.crSpec[z][counter] > 0.0D && this.crSpec[z][counter] < 1.0D) {
/* 1832 */         fillSizeRatio -= this.crSpec[z][counter];
/* 1833 */       } else if (this.crSpec[z][counter] == -1.0D) {
/* 1834 */         numFillSize++;
/*      */       } 
/*      */     } 
/* 1837 */     if (numFillSize > 1) {
/* 1838 */       fillSizeRatio /= numFillSize;
/*      */     }
/*      */     
/* 1841 */     if (fillSizeRatio < 0.0D) {
/* 1842 */       fillSizeRatio = 0.0D;
/*      */     }
/*      */     
/* 1845 */     this.crSize[z] = new int[numCr];
/*      */ 
/*      */ 
/*      */     
/* 1849 */     assignAbsoluteSize(z, 0);
/*      */ 
/*      */ 
/*      */     
/* 1853 */     assignPrefMinSize(z, 0, -3.0D, minimumSizes);
/* 1854 */     assignPrefMinSize(z, 0, -2.0D, preferredSizes);
/*      */     
/* 1856 */     int[] crPrefMin = new int[numCr];
/*      */     
/* 1858 */     for (counter = 0; counter < numCr; counter++) {
/* 1859 */       if (this.crSpec[z][counter] == -2.0D || this.crSpec[z][counter] == -3.0D)
/*      */       {
/* 1861 */         crPrefMin[counter] = this.crSize[z][counter];
/*      */       }
/*      */     } 
/*      */     
/* 1865 */     int numColumn = (this.crSpec[0]).length;
/* 1866 */     int numRow = (this.crSpec[1]).length;
/* 1867 */     int numEntry = entryList.length;
/*      */     
/* 1869 */     for (int entryCounter = 0; entryCounter < numEntry; entryCounter++) {
/*      */ 
/*      */       
/* 1872 */       Entry entry = entryList[entryCounter];
/*      */ 
/*      */       
/* 1875 */       if (entry.cr1[0] >= 0 && entry.cr1[0] < numColumn && entry.cr2[0] < numColumn && entry.cr1[1] >= 0 && 
/* 1876 */         entry.cr1[1] < numRow && entry.cr2[1] < numRow) {
/*      */         int temp;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1883 */         Dimension size = prefMinSize[entryCounter];
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1888 */         int scalableSize = (z == 0) ? 
/* 1889 */           size.width : 
/* 1890 */           size.height;
/*      */         
/* 1892 */         for (counter = entry.cr1[z]; counter <= entry.cr2[z]; counter++) {
/* 1893 */           if (this.crSpec[z][counter] >= 1.0D) {
/* 1894 */             scalableSize = (int)(scalableSize - this.crSpec[z][counter]);
/* 1895 */           } else if (this.crSpec[z][counter] == -2.0D || this.crSpec[z][counter] == -3.0D) {
/*      */             
/* 1897 */             scalableSize -= crPrefMin[counter];
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1904 */         double relativeSize = 0.0D;
/*      */         
/* 1906 */         for (counter = entry.cr1[z]; counter <= entry.cr2[z]; counter++) {
/*      */ 
/*      */           
/* 1909 */           if (this.crSpec[z][counter] > 0.0D && this.crSpec[z][counter] < 1.0D) {
/*      */             
/* 1911 */             relativeSize += this.crSpec[z][counter];
/*      */           }
/* 1913 */           else if (this.crSpec[z][counter] == -1.0D && fillSizeRatio != 0.0D) {
/*      */             
/* 1915 */             relativeSize += fillSizeRatio;
/*      */           } 
/*      */         } 
/*      */         
/* 1919 */         if (relativeSize == 0.0D) {
/* 1920 */           temp = 0;
/*      */         } else {
/* 1922 */           temp = (int)(scalableSize / relativeSize + 0.5D);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1927 */         if (scaledSize < temp) {
/* 1928 */           scaledSize = temp;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1933 */     int totalSize = scaledSize;
/*      */     
/* 1935 */     for (counter = 0; counter < numCr; counter++) {
/*      */       
/* 1937 */       if (this.crSpec[z][counter] >= 1.0D) {
/* 1938 */         totalSize += (int)(this.crSpec[z][counter] + 0.5D);
/*      */       }
/* 1940 */       else if (this.crSpec[z][counter] == -2.0D || this.crSpec[z][counter] == -3.0D) {
/*      */ 
/*      */         
/* 1943 */         totalSize += crPrefMin[counter];
/*      */       } 
/*      */     } 
/*      */     
/* 1947 */     if (numCr > 0)
/*      */     {
/* 1949 */       if (JLbsConstants.DESKTOP_MODE && this.vGap != 0) {
/*      */         
/* 1951 */         if (z == 0) {
/* 1952 */           totalSize += this.hGap * (numCr - 1);
/*      */         } else {
/*      */           
/* 1955 */           int count = 0;
/* 1956 */           for (int i = 0; i < (this.crSpec[1]).length; i++) {
/*      */             
/* 1958 */             if (this.crSpec[1][i] != 0.0D) {
/* 1959 */               count++;
/*      */             }
/*      */           } 
/* 1962 */           totalSize += this.vGap * (count - 1);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1967 */         totalSize += ((z == 0) ? 
/* 1968 */           this.hGap : 
/* 1969 */           this.vGap) * (numCr - 1);
/*      */       } 
/*      */     }
/*      */     
/* 1973 */     return totalSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addLayoutComponent(String name, Component component) {
/* 1985 */     addLayoutComponent(component, name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addLayoutComponent(Component component, Object constraint) {
/* 2001 */     if (constraint instanceof String) {
/*      */ 
/*      */       
/* 2004 */       constraint = new TableLayoutConstraints((String)constraint);
/*      */ 
/*      */       
/* 2007 */       this.list.add(new Entry(component, (TableLayoutConstraints)constraint));
/*      */ 
/*      */       
/* 2010 */       this.dirty = true;
/*      */     }
/* 2012 */     else if (constraint instanceof TableLayoutConstraints) {
/*      */ 
/*      */       
/* 2015 */       this.list.add(new Entry(component, (TableLayoutConstraints)constraint));
/*      */ 
/*      */       
/* 2018 */       this.dirty = true;
/*      */     } else {
/* 2020 */       if (constraint == null) {
/* 2021 */         throw new IllegalArgumentException("No constraint for the component");
/*      */       }
/* 2023 */       throw new IllegalArgumentException("Cannot accept a constraint of class " + constraint.getClass());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeLayoutComponent(Component component) {
/* 2035 */     ListIterator<Entry> iterator = this.list.listIterator(0);
/*      */     
/* 2037 */     while (iterator.hasNext()) {
/*      */       
/* 2039 */       Entry entry = iterator.next();
/*      */       
/* 2041 */       if (entry.component == component) {
/* 2042 */         iterator.remove();
/*      */       }
/*      */     } 
/*      */     
/* 2046 */     this.dirty = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Dimension maximumLayoutSize(Container target) {
/* 2062 */     return new Dimension(2147483647, 2147483647);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getLayoutAlignmentX(Container parent) {
/* 2076 */     return 0.5F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getLayoutAlignmentY(Container parent) {
/* 2090 */     return 0.5F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void invalidateLayout(Container target) {
/* 2100 */     this.dirty = true;
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\info\clearthought\layout\TableLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */