/*     */ package com.lbs.controls.tablereport;
/*     */ 
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import info.clearthought.layout.TableLayout;
/*     */ import info.clearthought.layout.TableLayoutConstraints;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import java.util.Vector;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JScrollPane;
/*     */ import org.jfree.chart.ChartFactory;
/*     */ import org.jfree.chart.ChartPanel;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.CategoryLabelPositions;
/*     */ import org.jfree.chart.axis.NumberAxis;
/*     */ import org.jfree.chart.labels.CategorySeriesLabelGenerator;
/*     */ import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PiePlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.renderer.category.BarRenderer;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.category.DefaultCategoryDataset;
/*     */ import org.jfree.data.general.AbstractDataset;
/*     */ import org.jfree.data.general.DefaultPieDataset;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ import org.jfree.util.Rotation;
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
/*     */ public class GraphicPanel
/*     */   extends JPanel
/*     */ {
/*     */   private TableReportTreeTableModel treeTableModel;
/*     */   private TableReportInfo reportInfo;
/*     */   private JPanel topPanel;
/*     */   private ChartPanel chartPanel;
/*     */   private JComboBox cmbFields;
/*     */   private ComboCheckBox cmbNumericFields;
/*     */   private JButton btnPaintGraphics;
/*     */   private JComboBox cmbChartTypes;
/*     */   private JPopupMenu menuNumericFields;
/*     */   private FilterList numericFieldsList;
/*     */   
/*  69 */   private transient MouseWheelListener zoomChartListener = new MouseWheelListener() {
/*     */       public void mouseWheelMoved(MouseWheelEvent event) {
/*  71 */         if (event.getScrollType() != 0) {
/*     */           return;
/*     */         }
/*     */         
/*  75 */         boolean increase = (event.getWheelRotation() < 0);
/*  76 */         GraphicPanel.this.zoom(increase);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   public GraphicPanel(TableReportInfo reportInfo, TableReportTreeTableModel treeTableModel) {
/*  82 */     this.reportInfo = reportInfo;
/*  83 */     this.treeTableModel = treeTableModel;
/*     */     
/*  85 */     initGUI();
/*  86 */     init();
/*     */   }
/*     */   
/*     */   private void init() {
/*  90 */     fillFields();
/*  91 */     fillNumericFields();
/*  92 */     fillChartTypes();
/*     */   }
/*     */   
/*     */   private void fillFields() {
/*  96 */     this.cmbFields.removeAllItems();
/*     */     
/*  98 */     this.cmbFields.addItem(new PairValue(TableReportConstants.STRINGS2[3], ""));
/*     */     
/* 100 */     for (int index = 1; index < this.reportInfo.getHeaderNames().size(); index++) {
/* 101 */       PairValue pairValue = new PairValue(this.reportInfo.getHeaderNames().get(index), Integer.valueOf(index));
/* 102 */       this.cmbFields.addItem(pairValue);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void fillNumericFields() {
/* 107 */     this.cmbNumericFields.removeAllItems();
/* 108 */     this.cmbNumericFields.addItem(new FilterListCellValue(TableReportConstants.STRINGS2[2], false));
/*     */     
/* 110 */     for (int index = 1; index < this.reportInfo.getHeaderNames().size(); index++) {
/* 111 */       if (this.treeTableModel.isNumber(index)) {
/* 112 */         PairValue pairValue = new PairValue(this.reportInfo.getHeaderNames().get(index), Integer.valueOf(index));
/* 113 */         FilterListCellValue flcv = new FilterListCellValue(pairValue, false);
/* 114 */         this.cmbNumericFields.addItem(flcv);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void fillChartTypes() {
/* 120 */     this.cmbChartTypes.removeAllItems(); byte b; int i;
/*     */     TableReportConstants.ChartType[] arrayOfChartType;
/* 122 */     for (i = (arrayOfChartType = TableReportConstants.ChartType.values()).length, b = 0; b < i; ) { TableReportConstants.ChartType chartType = arrayOfChartType[b];
/* 123 */       PairValue pairValue = new PairValue(chartType.getTitle(), chartType);
/* 124 */       this.cmbChartTypes.addItem(pairValue);
/*     */       b++; }
/*     */     
/* 127 */     this.cmbChartTypes.addItemListener(new ItemListener() {
/*     */           public void itemStateChanged(ItemEvent event) {
/* 129 */             if (event.getStateChange() != 2) {
/*     */               return;
/*     */             }
/*     */             
/* 133 */             if (!GraphicPanel.this.controlDrawChart()) {
/*     */               return;
/*     */             }
/*     */             
/* 137 */             GraphicPanel.this.drawChart();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void initGUI() {
/* 143 */     setLayout(new BorderLayout());
/*     */     
/* 145 */     createTopPanel();
/* 146 */     add(this.topPanel, "North");
/*     */   }
/*     */   
/*     */   private void createTopPanel() {
/* 150 */     this.topPanel = new JPanel();
/* 151 */     this.topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
/* 152 */     this.topPanel.setPreferredSize(new Dimension(0, 48));
/*     */     
/* 154 */     double[][] sizes = {
/* 155 */         { 100.0D, 100.0D, -1.0D, -1.0D
/* 156 */         }, { -1.0D, -1.0D }
/*     */       };
/* 158 */     TableLayout layout = new TableLayout(sizes);
/* 159 */     layout.setHGap(2);
/* 160 */     layout.setVGap(2);
/* 161 */     this.topPanel.setLayout((LayoutManager)layout);
/*     */     
/* 163 */     TableLayoutConstraints c = new TableLayoutConstraints(0, 0, 0, 0);
/* 164 */     this.topPanel.add(createTitleLabel(TableReportConstants.STRINGS2[4]), c);
/*     */     
/* 166 */     this.cmbFields = new JComboBox();
/* 167 */     c = new TableLayoutConstraints(0, 1, 0, 1);
/* 168 */     this.topPanel.add(this.cmbFields, c);
/*     */     
/* 170 */     c = new TableLayoutConstraints(1, 0, 1, 0);
/* 171 */     this.topPanel.add(createTitleLabel(TableReportConstants.STRINGS2[5]), c);
/*     */     
/* 173 */     this.menuNumericFields = new JPopupMenu();
/* 174 */     this.menuNumericFields.add(new JLabel());
/* 175 */     this.numericFieldsList = new FilterList();
/* 176 */     JScrollPane sp = new JScrollPane(this.numericFieldsList);
/* 177 */     this.menuNumericFields.add(sp);
/*     */     
/* 179 */     this.cmbNumericFields = new ComboCheckBox();
/* 180 */     c = new TableLayoutConstraints(1, 1, 1, 1);
/* 181 */     this.topPanel.add(this.cmbNumericFields, c);
/*     */     
/* 183 */     this.btnPaintGraphics = new JButton(TableReportConstants.STRINGS2[6]);
/* 184 */     this.btnPaintGraphics.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent e) {
/* 186 */             if (!GraphicPanel.this.controlDrawChart()) {
/*     */               return;
/*     */             }
/*     */             
/* 190 */             GraphicPanel.this.drawChart();
/*     */           }
/*     */         });
/* 193 */     this.btnPaintGraphics.setActionCommand("render");
/* 194 */     this.btnPaintGraphics.setPreferredSize(new Dimension(80, 0));
/* 195 */     c = new TableLayoutConstraints(2, 0, 2, 1, 0, 2);
/* 196 */     this.topPanel.add(this.btnPaintGraphics, c);
/*     */     
/* 198 */     c = new TableLayoutConstraints(3, 0, 3, 0, 3, 2);
/* 199 */     this.topPanel.add(createTitleLabel(TableReportConstants.STRINGS2[7]), c);
/*     */     
/* 201 */     this.cmbChartTypes = new JComboBox();
/* 202 */     this.cmbChartTypes.setPreferredSize(new Dimension(125, 0));
/* 203 */     c = new TableLayoutConstraints(3, 1, 3, 1, 3, 2);
/* 204 */     this.topPanel.add(this.cmbChartTypes, c);
/*     */   }
/*     */   
/*     */   private JLabel createTitleLabel(String title) {
/* 208 */     JLabel titleLabel = new JLabel();
/* 209 */     titleLabel.setPreferredSize(new Dimension(125, 0));
/* 210 */     titleLabel.setOpaque(true);
/* 211 */     titleLabel.setBackground(Color.lightGray);
/* 212 */     titleLabel.setForeground(Color.black);
/* 213 */     titleLabel.setText(title);
/* 214 */     titleLabel.setBorder(BorderFactory.createLineBorder(Color.black));
/*     */     
/* 216 */     return titleLabel;
/*     */   }
/*     */   
/*     */   private boolean controlDrawChart() {
/* 220 */     boolean control = true;
/*     */     
/* 222 */     int i = control & ((this.cmbFields.getSelectedIndex() > 0) ? 1 : 0);
/* 223 */     if (i == 0) {
/* 224 */       JOptionPane.showMessageDialog(null, TableReportConstants.STRINGS2[8], TableReportConstants.STRINGS2[9], 2);
/* 225 */       return false;
/*     */     } 
/*     */     
/* 228 */     boolean bool1 = i & this.cmbNumericFields.hasSelectedField();
/* 229 */     if (!bool1) {
/* 230 */       JOptionPane.showMessageDialog(null, TableReportConstants.STRINGS2[10], TableReportConstants.STRINGS2[9], 2);
/* 231 */       return false;
/*     */     } 
/*     */     
/* 234 */     return true;
/*     */   }
/*     */   
/*     */   private JFreeChart createPieChart(DefaultPieDataset dataSet) {
/* 238 */     JFreeChart chart = ChartFactory.createPieChart("", (PieDataset)dataSet, true, true, false);
/*     */     
/* 240 */     PiePlot plot = (PiePlot)chart.getPlot();
/* 241 */     plot.setDirection(Rotation.CLOCKWISE);
/* 242 */     plot.setForegroundAlpha(0.5F);
/* 243 */     plot.setNoDataMessage(TableReportConstants.STRINGS2[11]);
/*     */     
/* 245 */     return chart;
/*     */   }
/*     */   
/*     */   private JFreeChart createBarChart(DefaultCategoryDataset dataSet, PlotOrientation orientation) {
/* 249 */     JFreeChart chart = ChartFactory.createBarChart("", "", "", (CategoryDataset)dataSet, orientation, true, true, false);
/* 250 */     CategoryPlot plot = (CategoryPlot)chart.getPlot();
/* 251 */     plot.setDomainGridlinesVisible(false);
/* 252 */     plot.setRangeCrosshairVisible(true);
/* 253 */     plot.setRangeCrosshairPaint(Color.blue);
/* 254 */     NumberAxis axis = (NumberAxis)plot.getRangeAxis();
/* 255 */     axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
/* 256 */     BarRenderer localBarRenderer = (BarRenderer)plot.getRenderer();
/* 257 */     localBarRenderer.setDrawBarOutline(false);
/* 258 */     GradientPaint grp1 = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, new Color(0, 0, 64));
/* 259 */     GradientPaint grp2 = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, new Color(0, 64, 0));
/* 260 */     GradientPaint grp3 = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, new Color(64, 0, 0));
/* 261 */     localBarRenderer.setSeriesPaint(0, grp1);
/* 262 */     localBarRenderer.setSeriesPaint(1, grp2);
/* 263 */     localBarRenderer.setSeriesPaint(2, grp3);
/* 264 */     localBarRenderer.setLegendItemToolTipGenerator((CategorySeriesLabelGenerator)new StandardCategorySeriesLabelGenerator("Tooltip: {1}"));
/* 265 */     CategoryAxis localCategoryAxis = plot.getDomainAxis();
/* 266 */     localCategoryAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.5235987755982988D));
/*     */     
/* 268 */     return chart;
/*     */   }
/*     */   
/*     */   private JFreeChart createLineChart(DefaultCategoryDataset dataSet, PlotOrientation orientation) {
/* 272 */     JFreeChart chart = ChartFactory.createLineChart("", "", "", (CategoryDataset)dataSet, orientation, true, true, false);
/*     */     
/* 274 */     CategoryPlot plot = (CategoryPlot)chart.getPlot();
/* 275 */     plot.setBackgroundPaint(Color.lightGray);
/* 276 */     plot.setRangeGridlinePaint(Color.white);
/*     */     
/* 278 */     NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
/* 279 */     rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
/* 280 */     rangeAxis.setAutoRangeIncludesZero(true);
/*     */     
/* 282 */     return chart;
/*     */   }
/*     */   
/*     */   private JFreeChart createAreaChart(DefaultCategoryDataset dataSet) {
/* 286 */     JFreeChart chart = ChartFactory.createAreaChart("", "", "", (CategoryDataset)dataSet, PlotOrientation.VERTICAL, true, true, false);
/*     */     
/* 288 */     CategoryPlot plot = chart.getCategoryPlot();
/* 289 */     plot.setForegroundAlpha(0.5F);
/*     */     
/* 291 */     plot.setBackgroundPaint(Color.lightGray);
/* 292 */     plot.setDomainGridlinesVisible(true);
/* 293 */     plot.setDomainGridlinePaint(Color.white);
/* 294 */     plot.setRangeGridlinesVisible(true);
/* 295 */     plot.setRangeGridlinePaint(Color.white);
/*     */     
/* 297 */     CategoryAxis domainAxis = plot.getDomainAxis();
/* 298 */     domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
/* 299 */     domainAxis.setLowerMargin(0.0D);
/* 300 */     domainAxis.setUpperMargin(0.0D);
/*     */     
/* 302 */     NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
/* 303 */     rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
/* 304 */     rangeAxis.setLabelAngle(0.0D);
/*     */     
/* 306 */     return chart;
/*     */   }
/*     */   
/*     */   private JFreeChart createChart(AbstractDataset dataSet, TableReportConstants.ChartType chartType) {
/* 310 */     JFreeChart chart = null;
/*     */     
/* 312 */     switch (chartType) {
/*     */       case COLUMN_CHART:
/* 314 */         chart = createBarChart((DefaultCategoryDataset)dataSet, PlotOrientation.VERTICAL);
/*     */         break;
/*     */       
/*     */       case BAR_CHART:
/* 318 */         chart = createBarChart((DefaultCategoryDataset)dataSet, PlotOrientation.HORIZONTAL);
/*     */         break;
/*     */       
/*     */       case PIE_CHART:
/* 322 */         chart = createPieChart((DefaultPieDataset)dataSet);
/*     */         break;
/*     */       
/*     */       case LINE_CHART:
/* 326 */         chart = createLineChart((DefaultCategoryDataset)dataSet, PlotOrientation.VERTICAL);
/*     */         break;
/*     */       
/*     */       case null:
/* 330 */         chart = createAreaChart((DefaultCategoryDataset)dataSet);
/*     */         break;
/*     */     } 
/* 333 */     if (chart != null)
/*     */     {
/* 335 */       chart.setTitle(this.reportInfo.getTitle());
/*     */     }
/*     */     
/* 338 */     return chart;
/*     */   }
/*     */   private AbstractDataset createDataSet(TableReportConstants.ChartType chartType) {
/*     */     DefaultPieDataset defaultPieDataset;
/* 342 */     AbstractDataset ret = null;
/*     */     
/* 344 */     if (chartType == TableReportConstants.ChartType.COLUMN_CHART || chartType == TableReportConstants.ChartType.BAR_CHART || 
/* 345 */       chartType == TableReportConstants.ChartType.LINE_CHART || chartType == TableReportConstants.ChartType.AREA_CHART) {
/* 346 */       DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
/* 347 */       Integer rowIndex = (Integer)((PairValue)this.cmbFields.getSelectedItem()).getValue();
/*     */       
/* 349 */       for (int index = 0; index < this.cmbNumericFields.getSelectedIndexes().size(); index++) {
/* 350 */         int columnIndex = ((Integer)this.cmbNumericFields.getSelectedIndexes().get(index)).intValue();
/* 351 */         String headerName = this.reportInfo.getHeaderNames().get(columnIndex);
/*     */         
/* 353 */         Vector<Object> domainValues = this.treeTableModel.getObjectList(null, rowIndex.intValue());
/* 354 */         Vector<Object> rangeValues = this.treeTableModel.getObjectList(null, columnIndex);
/*     */         
/* 356 */         for (int i = 0; i < domainValues.size(); i++) {
/* 357 */           String columnKey = TableReportInfo.getFormatter().format(domainValues.get(i));
/* 358 */           dataSet.addValue((Number)rangeValues.get(i), headerName, columnKey);
/*     */         } 
/*     */       } 
/*     */       
/* 362 */       DefaultCategoryDataset defaultCategoryDataset1 = dataSet;
/* 363 */     } else if (chartType == TableReportConstants.ChartType.PIE_CHART) {
/* 364 */       DefaultPieDataset dataSet = new DefaultPieDataset();
/* 365 */       Integer rowIndex = (Integer)((PairValue)this.cmbFields.getSelectedItem()).getValue();
/*     */       
/* 367 */       for (int index = 0; index < this.cmbNumericFields.getSelectedIndexes().size(); index++) {
/* 368 */         int columnIndex = ((Integer)this.cmbNumericFields.getSelectedIndexes().get(index)).intValue();
/*     */         
/* 370 */         Vector<Object> domainValues = this.treeTableModel.getObjectList(null, rowIndex.intValue());
/* 371 */         Vector<Object> rangeValues = this.treeTableModel.getObjectList(null, columnIndex);
/*     */         
/* 373 */         for (int i = 0; i < domainValues.size(); i++) {
/* 374 */           String columnKey = TableReportInfo.getFormatter().format(domainValues.get(i));
/* 375 */           dataSet.setValue(columnKey, (Number)rangeValues.get(i));
/*     */         } 
/*     */       } 
/*     */       
/* 379 */       defaultPieDataset = dataSet;
/*     */     } 
/*     */     
/* 382 */     return (AbstractDataset)defaultPieDataset;
/*     */   }
/*     */   
/*     */   private void drawChart() {
/* 386 */     JLbsComponentHelper.loadJFreeChartLib(this.reportInfo.getContext());
/*     */     
/* 388 */     PairValue pairValue = (PairValue)this.cmbChartTypes.getSelectedItem();
/* 389 */     TableReportConstants.ChartType chartType = (TableReportConstants.ChartType)pairValue.getValue();
/*     */     
/* 391 */     JFreeChart chart = createChart(createDataSet(chartType), chartType);
/*     */     
/* 393 */     if (this.chartPanel != null) {
/* 394 */       this.chartPanel.setChart(chart);
/*     */     } else {
/* 396 */       this.chartPanel = new ChartPanel(chart);
/* 397 */       this.chartPanel.addMouseWheelListener(this.zoomChartListener);
/*     */     } 
/*     */     
/* 400 */     add((Component)this.chartPanel, "Center");
/* 401 */     revalidate();
/*     */   }
/*     */   
/*     */   private void zoom(boolean increase) {
/* 405 */     int width = this.chartPanel.getMaximumDrawWidth() - this.chartPanel.getMinimumDrawWidth();
/* 406 */     int height = this.chartPanel.getMaximumDrawHeight() - this.chartPanel.getMinimumDrawHeight();
/*     */     
/* 408 */     if (increase) {
/* 409 */       this.chartPanel.zoomInBoth(width / 2.0D, height / 2.0D);
/*     */     } else {
/* 411 */       this.chartPanel.zoomOutBoth(width / 2.0D, height / 2.0D);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\GraphicPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */