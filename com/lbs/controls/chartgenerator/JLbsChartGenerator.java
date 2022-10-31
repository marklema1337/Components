/*     */ package com.lbs.controls.chartgenerator;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsComponent;
/*     */ import com.lbs.controls.JLbsComponentHelper;
/*     */ import com.lbs.util.JLbsDialog;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Calendar;
/*     */ import javax.swing.JPanel;
/*     */ import org.jfree.chart.ChartMouseEvent;
/*     */ import org.jfree.chart.ChartMouseListener;
/*     */ import org.jfree.chart.ChartPanel;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.axis.Axis;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.CategoryAxis3D;
/*     */ import org.jfree.chart.axis.CategoryLabelPositions;
/*     */ import org.jfree.chart.axis.DateAxis;
/*     */ import org.jfree.chart.axis.NumberAxis;
/*     */ import org.jfree.chart.axis.NumberAxis3D;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*     */ import org.jfree.chart.labels.CategoryToolTipGenerator;
/*     */ import org.jfree.chart.labels.ItemLabelAnchor;
/*     */ import org.jfree.chart.labels.ItemLabelPosition;
/*     */ import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
/*     */ import org.jfree.chart.labels.XYItemLabelGenerator;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.DialShape;
/*     */ import org.jfree.chart.plot.MeterInterval;
/*     */ import org.jfree.chart.plot.MeterPlot;
/*     */ import org.jfree.chart.plot.PiePlot3D;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.renderer.category.AreaRenderer;
/*     */ import org.jfree.chart.renderer.category.BarRenderer;
/*     */ import org.jfree.chart.renderer.category.BarRenderer3D;
/*     */ import org.jfree.chart.renderer.category.CategoryItemRenderer;
/*     */ import org.jfree.chart.renderer.category.LineAndShapeRenderer;
/*     */ import org.jfree.chart.renderer.category.LineRenderer3D;
/*     */ import org.jfree.chart.renderer.xy.XYItemRenderer;
/*     */ import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.category.DefaultCategoryDataset;
/*     */ import org.jfree.data.general.DefaultPieDataset;
/*     */ import org.jfree.data.general.DefaultValueDataset;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ import org.jfree.data.general.ValueDataset;
/*     */ import org.jfree.data.time.Day;
/*     */ import org.jfree.data.time.Month;
/*     */ import org.jfree.data.time.RegularTimePeriod;
/*     */ import org.jfree.data.time.TimeSeries;
/*     */ import org.jfree.data.time.TimeSeriesCollection;
/*     */ import org.jfree.data.time.TimeSeriesDataItem;
/*     */ import org.jfree.data.time.Year;
/*     */ import org.jfree.data.xy.XYDataItem;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.data.xy.XYSeries;
/*     */ import org.jfree.data.xy.XYSeriesCollection;
/*     */ import org.jfree.ui.TextAnchor;
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
/*     */ public class JLbsChartGenerator
/*     */   implements ChartMouseListener, ILbsChartGenerator
/*     */ {
/*     */   public static final int CHART_LINE = 0;
/*     */   public static final int CHART_BAR = 1;
/*     */   public static final int CHART_PIE = 2;
/*     */   public static final int CHART_AREA = 3;
/*     */   public static final int CHART_GAUGE = 4;
/*     */   public static final int AXIS_VALUE = 0;
/*     */   public static final int AXIS_DATE = 1;
/*     */   public static final int AXIS_CATEGORY = 2;
/*     */   public static final int SERIES_XY = 0;
/*     */   public static final int SERIES_TIME = 1;
/*     */   public static final int SERIES_TASK = 2;
/*     */   public static final int SERIES_PIEDATASET = 3;
/*     */   public static final int SERIES_VALUEDATASET = 4;
/*     */   public static final int SERIES_STRING = 5;
/*     */   public static final int TV_DAY = 0;
/*     */   public static final int TV_MONTH = 1;
/*     */   public static final int TV_YEAR = 2;
/*     */   private Container m_Parent;
/*     */   private String m_Title;
/*     */   private JPanel m_ChartContainer;
/* 109 */   private int m_ChartType = 0;
/*     */   
/*     */   private boolean m_3D;
/*     */   private Object[] m_Series;
/*     */   private String[] m_SeriesTitles;
/*     */   private int m_XAxis;
/*     */   private int m_YAxis;
/*     */   private String m_XAxisLabel;
/*     */   private String m_YAxisLabel;
/*     */   private String m_DlgTitle;
/*     */   private boolean m_HaveLegend = true;
/*     */   private boolean m_ItemValues = false;
/* 121 */   private double[] m_GaugeRange = new double[] { 0.0D, 100.0D };
/*     */   private String[] m_SectionLabels;
/*     */   private double[] m_SectionMinValues;
/* 124 */   private Color[] m_SectionColors = new Color[] { Color.lightGray };
/* 125 */   private String m_Lang = "";
/*     */ 
/*     */   
/*     */   public JLbsChartGenerator(ILbsComponent chartParent, Object context) {
/* 129 */     if (chartParent instanceof Container) {
/*     */       
/* 131 */       this.m_Parent = (Container)chartParent;
/* 132 */       this.m_Parent.setLayout(new BorderLayout());
/*     */     } 
/* 134 */     this.m_ChartType = 0;
/* 135 */     this.m_Series = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void generateCharts() {
/* 140 */     if (this.m_Parent == null) {
/*     */       return;
/*     */     }
/* 143 */     if (this.m_ChartContainer == null) {
/*     */       
/* 145 */       this.m_ChartContainer = new JPanel();
/* 146 */       this.m_ChartContainer.setLayout(new GridLayout(0, 1));
/* 147 */       this.m_Parent.add(this.m_ChartContainer);
/*     */     } 
/* 149 */     this.m_ChartContainer.removeAll();
/*     */     
/* 151 */     Axis xAxis = createAxis(this.m_XAxis, this.m_XAxisLabel);
/* 152 */     Axis yAxis = createAxis(this.m_YAxis, this.m_YAxisLabel);
/*     */     
/* 154 */     switch (this.m_ChartType) {
/*     */       
/*     */       case 0:
/* 157 */         if (xAxis instanceof ValueAxis && yAxis instanceof ValueAxis) {
/*     */           
/* 159 */           XYPlot linePlot = createLinePlot((ValueAxis)xAxis, (ValueAxis)yAxis, this.m_ItemValues);
/* 160 */           this.m_ChartContainer.add(createChart((Plot)linePlot, null)); break;
/*     */         } 
/* 162 */         if (xAxis instanceof CategoryAxis && yAxis instanceof ValueAxis) {
/*     */           
/* 164 */           Plot linePlot = createLinePlotForString((CategoryAxis)xAxis, (ValueAxis)yAxis, true, this.m_ItemValues, createCategoryDataset());
/* 165 */           this.m_ChartContainer.add(createChart(linePlot, null));
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 169 */         if (xAxis instanceof CategoryAxis && yAxis instanceof ValueAxis) {
/*     */           
/* 171 */           Plot barPlot = createBarchart((CategoryAxis)xAxis, (ValueAxis)yAxis, true, this.m_ItemValues, createCategoryDataset());
/* 172 */           this.m_ChartContainer.add(createChart(barPlot, null));
/*     */         } 
/*     */         break;
/*     */       case 3:
/* 176 */         if (xAxis instanceof CategoryAxis && yAxis instanceof ValueAxis) {
/*     */           
/* 178 */           Plot areaPlot = createAreachart((CategoryAxis)xAxis, (ValueAxis)yAxis, true, createCategoryDataset());
/* 179 */           this.m_ChartContainer.add(createChart(areaPlot, null));
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 2:
/* 184 */         if (this.m_Series != null && this.m_Series.length > 0)
/* 185 */           for (int i = 0; i < this.m_Series.length; i++) {
/*     */             
/* 187 */             Plot piePlot = createPiechart(createPieDataset(i));
/* 188 */             this.m_ChartContainer.add(createChart(piePlot, this.m_SeriesTitles[i]));
/*     */           }  
/*     */         break;
/*     */       case 4:
/* 192 */         if (this.m_Series != null && this.m_Series.length > 0) {
/* 193 */           for (int i = 0; i < this.m_Series.length; i++) {
/*     */             
/* 195 */             MeterPlot meterPlot = createGaugeChart(createValueDataset(i), this.m_GaugeRange, this.m_SectionLabels, 
/* 196 */                 this.m_SectionMinValues, this.m_SectionColors);
/* 197 */             this.m_ChartContainer.add(createChart((Plot)meterPlot, this.m_SeriesTitles[i]));
/*     */           } 
/*     */         }
/*     */         break;
/*     */     } 
/* 202 */     this.m_ChartContainer.validate();
/* 203 */     this.m_Parent.validate();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChartType() {
/* 208 */     return this.m_ChartType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChartType(int chartType) {
/* 213 */     this.m_ChartType = chartType;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean is3D() {
/* 218 */     return this.m_3D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void set3D(boolean name) {
/* 223 */     this.m_3D = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getProperXAxis(int defaultX) {
/* 228 */     switch (this.m_ChartType) {
/*     */       
/*     */       case 1:
/*     */       case 3:
/* 232 */         return 2;
/*     */     } 
/*     */     
/* 235 */     return defaultX;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setXAxis(int type, String caption) {
/* 240 */     this.m_XAxis = type;
/* 241 */     this.m_XAxisLabel = caption;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumSeries(int nSeries) {
/* 246 */     this.m_Series = new Object[nSeries];
/* 247 */     this.m_SeriesTitles = new String[nSeries];
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHaveLegend() {
/* 252 */     return this.m_HaveLegend;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHaveLegend(boolean haveLegend) {
/* 257 */     this.m_HaveLegend = haveLegend;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemValues() {
/* 262 */     return this.m_ItemValues;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemValues(boolean mItemValues) {
/* 267 */     this.m_ItemValues = mItemValues;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object createSeries(int xSeries, int type, String title) {
/* 272 */     return createSeries(xSeries, type, 0, title);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object createSeries(int xSeries, int type, int timeValue, String title) {
/* 277 */     if (this.m_Series == null || this.m_Series.length <= xSeries) {
/* 278 */       return null;
/*     */     }
/* 280 */     Object s = null;
/* 281 */     switch (type) {
/*     */       
/*     */       case 0:
/* 284 */         s = new XYSeries(title);
/*     */         break;
/*     */       case 1:
/* 287 */         s = new TimeSeries(title);
/*     */         break;
/*     */       case 3:
/* 290 */         s = new DefaultPieDataset();
/*     */         break;
/*     */       case 4:
/* 293 */         s = new DefaultValueDataset();
/*     */         break;
/*     */       case 5:
/* 296 */         s = new LbsStringSeries(title);
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 301 */     this.m_Series[xSeries] = s;
/* 302 */     this.m_SeriesTitles[xSeries] = title;
/* 303 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addValue(int xSeries, BigDecimal value) {
/* 308 */     if (this.m_Series == null || this.m_Series.length <= xSeries) {
/* 309 */       return false;
/*     */     }
/* 311 */     if (this.m_Series[xSeries] instanceof DefaultValueDataset) {
/*     */       
/* 313 */       DefaultValueDataset s = (DefaultValueDataset)this.m_Series[xSeries];
/* 314 */       s.setValue(value);
/*     */     } 
/*     */     
/* 317 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addPieValue(int xSeries, String key, BigDecimal value) {
/* 322 */     if (this.m_Series == null || this.m_Series.length <= xSeries) {
/* 323 */       return false;
/*     */     }
/* 325 */     if (this.m_Series[xSeries] instanceof DefaultPieDataset) {
/*     */       
/* 327 */       DefaultPieDataset s = (DefaultPieDataset)this.m_Series[xSeries];
/* 328 */       s.setValue(key, value.doubleValue());
/*     */     } 
/*     */     
/* 331 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addXYValue(int xSeries, double xValue, double yValue) {
/* 336 */     if (this.m_Series == null || this.m_Series.length <= xSeries) {
/* 337 */       return false;
/*     */     }
/* 339 */     if (this.m_Series[xSeries] instanceof XYSeries) {
/*     */       
/* 341 */       XYSeries s = (XYSeries)this.m_Series[xSeries];
/* 342 */       s.add(xValue, yValue);
/*     */     } 
/*     */     
/* 345 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addXYValue(int xSeries, BigDecimal xValue, BigDecimal yValue) {
/* 350 */     return addXYValue(xSeries, xValue.doubleValue(), yValue.doubleValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addXYValue(int xSeries, RegularTimePeriod xValue, double yValue) {
/* 355 */     if (this.m_Series == null || this.m_Series.length <= xSeries) {
/* 356 */       return false;
/*     */     }
/* 358 */     if (this.m_Series[xSeries] instanceof TimeSeries) {
/*     */       
/* 360 */       TimeSeries s = (TimeSeries)this.m_Series[xSeries];
/* 361 */       s.add(xValue, yValue);
/*     */     } 
/*     */     
/* 364 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addXYValue(int xSeries, String xValue, double yValue) {
/* 370 */     if (this.m_Series == null || this.m_Series.length <= xSeries) {
/* 371 */       return false;
/*     */     }
/* 373 */     if (this.m_Series[xSeries] instanceof LbsStringSeries) {
/*     */       
/* 375 */       LbsStringSeries s = (LbsStringSeries)this.m_Series[xSeries];
/* 376 */       s.add(xValue, yValue);
/*     */     } 
/*     */     
/* 379 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addXYValue(int xSeries, Calendar xValue, BigDecimal yValue) {
/* 386 */     return addXYValue(xSeries, (RegularTimePeriod)new Day(xValue.getTime()), yValue.doubleValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addXYValue(int xSeries, String xValue, BigDecimal yValue) {
/* 391 */     return addXYValue(xSeries, new String(xValue), yValue.doubleValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addXYValue(int xSeries, int year, int month, BigDecimal yValue) {
/* 396 */     return addXYValue(xSeries, (RegularTimePeriod)new Month(month, year), yValue.doubleValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addXYValue(int xSeries, int year, BigDecimal yValue) {
/* 401 */     return addXYValue(xSeries, (RegularTimePeriod)new Year(year), yValue.doubleValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setYAxis(int type, String caption) {
/* 406 */     this.m_YAxis = type;
/* 407 */     this.m_YAxisLabel = caption;
/*     */   }
/*     */ 
/*     */   
/*     */   private Axis createAxis(int type, String caption) {
/* 412 */     switch (type) {
/*     */       
/*     */       case 0:
/* 415 */         return this.m_3D ? (Axis)new NumberAxis3D(caption) : (Axis)new NumberAxis(caption);
/*     */       case 1:
/* 417 */         return (Axis)new DateAxis(caption);
/*     */       case 2:
/* 419 */         return this.m_3D ? (Axis)new CategoryAxis3D(caption) : (Axis)new CategoryAxis(caption);
/*     */     } 
/*     */     
/* 422 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private Component createChart(Plot plot, String title) {
/* 427 */     JFreeChart chart = new JFreeChart((title != null) ? title : this.m_Title, JFreeChart.DEFAULT_TITLE_FONT, plot, this.m_HaveLegend);
/* 428 */     if (!JLbsStringUtil.isEmpty(this.m_Lang))
/* 429 */       JLbsChartPanel.ms_Lang = this.m_Lang; 
/* 430 */     JLbsChartPanel chartpanel = new JLbsChartPanel(chart);
/* 431 */     chartpanel.addChartMouseListener(this);
/* 432 */     return (Component)chartpanel;
/*     */   }
/*     */ 
/*     */   
/*     */   private Plot createBarchart(CategoryAxis xAxis, ValueAxis yAxis, boolean tooltips, boolean itemValues, CategoryDataset dataset) {
/* 437 */     CategoryItemRenderer renderer = this.m_3D ? (CategoryItemRenderer)new BarRenderer3D() : (CategoryItemRenderer)new BarRenderer();
/*     */     
/* 439 */     if (tooltips)
/* 440 */       renderer.setBaseToolTipGenerator((CategoryToolTipGenerator)new StandardCategoryToolTipGenerator()); 
/* 441 */     CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);
/* 442 */     renderer.setSeriesPositiveItemLabelPosition(0, new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, -1.5707963267948966D));
/* 443 */     plot.setOrientation(PlotOrientation.VERTICAL);
/* 444 */     plot.setForegroundAlpha(0.75F);
/* 445 */     if (itemValues) {
/*     */       
/* 447 */       CategoryItemLabelGenerator generator = new CategoryItemLabelGenerator()
/*     */         {
/*     */ 
/*     */ 
/*     */           
/*     */           public String generateRowLabel(CategoryDataset arg0, int arg1)
/*     */           {
/* 454 */             return null;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public String generateLabel(CategoryDataset dataset, int series, int category) {
/* 460 */             String result = null;
/* 461 */             Number value = dataset.getValue(series, category);
/* 462 */             if (value != null) {
/*     */               
/* 464 */               double v = value.doubleValue();
/* 465 */               if (v > 0.0D)
/*     */               {
/* 467 */                 result = value.toString();
/*     */               }
/*     */             } 
/* 470 */             return result;
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public String generateColumnLabel(CategoryDataset arg0, int arg1) {
/* 477 */             return null;
/*     */           }
/*     */         };
/*     */       
/* 481 */       renderer.setSeriesItemLabelGenerator(0, generator);
/* 482 */       renderer.setSeriesItemLabelsVisible(0, true);
/*     */     } 
/*     */     
/* 485 */     CategoryAxis domainAxis = plot.getDomainAxis();
/* 486 */     domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
/* 487 */     return (Plot)plot;
/*     */   }
/*     */ 
/*     */   
/*     */   private Plot createAreachart(CategoryAxis xAxis, ValueAxis yAxis, boolean tooltips, CategoryDataset dataset) {
/* 492 */     xAxis.setCategoryMargin(0.0D);
/* 493 */     AreaRenderer renderer = new AreaRenderer();
/* 494 */     if (tooltips) {
/* 495 */       renderer.setBaseToolTipGenerator((CategoryToolTipGenerator)new StandardCategoryToolTipGenerator());
/*     */     }
/* 497 */     CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, (CategoryItemRenderer)renderer);
/* 498 */     plot.setOrientation(PlotOrientation.VERTICAL);
/* 499 */     plot.setForegroundAlpha(0.5F);
/* 500 */     return (Plot)plot;
/*     */   }
/*     */ 
/*     */   
/*     */   private XYPlot createLinePlot(ValueAxis xAxis, ValueAxis yAxis, boolean itemValues) {
/* 505 */     XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
/* 506 */     renderer.setBaseShapesVisible(this.m_3D);
/* 507 */     renderer.setBaseShapesFilled(this.m_3D);
/* 508 */     renderer.setItemLabelAnchorOffset(-2.0D);
/* 509 */     if (itemValues) {
/*     */       
/* 511 */       XYItemLabelGenerator generator = new XYItemLabelGenerator()
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public String generateLabel(XYDataset dataset, int series, int category)
/*     */           {
/* 520 */             String result = null;
/* 521 */             Number value = Double.valueOf(dataset.getYValue(series, category));
/* 522 */             if (value != null) {
/*     */               
/* 524 */               double v = value.doubleValue();
/* 525 */               if (v > 0.0D) {
/* 526 */                 result = value.toString();
/*     */               }
/*     */             } 
/* 529 */             return result;
/*     */           }
/*     */         };
/*     */       
/* 533 */       renderer.setSeriesItemLabelGenerator(0, generator);
/* 534 */       renderer.setSeriesItemLabelsVisible(0, true);
/*     */     } 
/* 536 */     XYPlot xyplot = new XYPlot(createDataset(), xAxis, yAxis, (XYItemRenderer)renderer);
/* 537 */     ValueAxis domainAxis = xyplot.getDomainAxis();
/* 538 */     domainAxis.setVerticalTickLabels(true);
/* 539 */     return xyplot;
/*     */   }
/*     */ 
/*     */   
/*     */   private Plot createLinePlotForString(CategoryAxis xAxis, ValueAxis yAxis, boolean tooltips, boolean itemValues, CategoryDataset dataset) {
/* 544 */     CategoryItemRenderer renderer = this.m_3D ? (CategoryItemRenderer)new LineRenderer3D() : (CategoryItemRenderer)new LineAndShapeRenderer();
/*     */     
/* 546 */     if (tooltips)
/* 547 */       renderer.setBaseToolTipGenerator((CategoryToolTipGenerator)new StandardCategoryToolTipGenerator()); 
/* 548 */     CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);
/* 549 */     renderer.setSeriesPositiveItemLabelPosition(0, new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, -1.5707963267948966D));
/* 550 */     plot.setOrientation(PlotOrientation.VERTICAL);
/* 551 */     plot.setForegroundAlpha(0.75F);
/* 552 */     if (itemValues) {
/*     */       
/* 554 */       CategoryItemLabelGenerator generator = new CategoryItemLabelGenerator()
/*     */         {
/*     */ 
/*     */ 
/*     */           
/*     */           public String generateRowLabel(CategoryDataset arg0, int arg1)
/*     */           {
/* 561 */             return null;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public String generateLabel(CategoryDataset dataset, int series, int category) {
/* 567 */             String result = null;
/* 568 */             Number value = dataset.getValue(series, category);
/* 569 */             if (value != null) {
/*     */               
/* 571 */               double v = value.doubleValue();
/* 572 */               if (v > 0.0D)
/*     */               {
/* 574 */                 result = value.toString();
/*     */               }
/*     */             } 
/* 577 */             return result;
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public String generateColumnLabel(CategoryDataset arg0, int arg1) {
/* 584 */             return null;
/*     */           }
/*     */         };
/*     */       
/* 588 */       renderer.setSeriesItemLabelGenerator(0, generator);
/* 589 */       renderer.setSeriesItemLabelsVisible(0, true);
/*     */     } 
/*     */     
/* 592 */     CategoryAxis domainAxis = plot.getDomainAxis();
/* 593 */     domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
/* 594 */     return (Plot)plot;
/*     */   }
/*     */ 
/*     */   
/*     */   private Plot createPiechart(PieDataset dataset) {
/* 599 */     PiePlot3D pie = new PiePlot3D(dataset);
/* 600 */     pie.setForegroundAlpha(0.5F);
/* 601 */     pie.setDepthFactor(0.0D);
/* 602 */     pie.setLabelOutlinePaint(Color.LIGHT_GRAY);
/* 603 */     pie.setLabelFont(new Font("Arial Narrow", 0, 15));
/*     */     
/* 605 */     pie.setIgnoreZeroValues(true);
/* 606 */     pie.setAutoPopulateSectionOutlinePaint(true);
/* 607 */     pie.setMaximumLabelWidth(0.3D);
/*     */     
/* 609 */     return (Plot)pie;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private MeterPlot createGaugeChart(ValueDataset dataset, double[] range, String[] sectionLabels, double[] sectionMinValues, Color[] sectionColors) {
/* 615 */     MeterPlot plot = new MeterPlot(dataset);
/* 616 */     plot.setDialShape(DialShape.CHORD);
/* 617 */     plot.setDialOutlinePaint(Color.white);
/* 618 */     plot.setDialBackgroundPaint(Color.white);
/* 619 */     plot.setNeedlePaint(Color.black);
/* 620 */     plot.setValuePaint(Color.black);
/* 621 */     plot.setUnits("");
/* 622 */     plot.setRange(new Range(range[0], range[1]));
/* 623 */     for (int i = 0; i < sectionMinValues.length; i++) {
/*     */       
/* 625 */       double sectionMaxValue = (i + 1 == sectionMinValues.length) ? range[1] : sectionMinValues[i + 1];
/* 626 */       Color sectionColor = (sectionColors != null && sectionColors[i] != null) ? sectionColors[i] : Color.lightGray;
/* 627 */       plot.addInterval(new MeterInterval(sectionLabels[i], new Range(sectionMinValues[i], sectionMaxValue), null, null, 
/* 628 */             sectionColor));
/*     */     } 
/*     */     
/* 631 */     return plot;
/*     */   }
/*     */ 
/*     */   
/*     */   private XYDataset createDataset() {
/* 636 */     if (this.m_Series != null && this.m_Series.length > 0) {
/*     */       
/* 638 */       Object s = this.m_Series[0];
/* 639 */       if (s instanceof TimeSeries) {
/*     */         
/* 641 */         TimeSeriesCollection coll = new TimeSeriesCollection();
/* 642 */         for (int i = 0; i < this.m_Series.length; i++)
/* 643 */           coll.addSeries((TimeSeries)this.m_Series[i]); 
/* 644 */         return (XYDataset)coll;
/*     */       } 
/* 646 */       if (s instanceof XYSeries) {
/*     */         
/* 648 */         XYSeriesCollection coll = new XYSeriesCollection();
/* 649 */         for (int i = 0; i < this.m_Series.length; i++)
/* 650 */           coll.addSeries((XYSeries)this.m_Series[i]); 
/* 651 */         return (XYDataset)coll;
/*     */       } 
/*     */     } 
/*     */     
/* 655 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private ValueDataset createValueDataset(int seriesX) {
/* 660 */     if (this.m_Series != null && this.m_Series.length > 0) {
/*     */       
/* 662 */       Object s = this.m_Series[seriesX];
/* 663 */       if (s instanceof DefaultValueDataset) {
/*     */         
/* 665 */         DefaultValueDataset result = (DefaultValueDataset)s;
/* 666 */         return (ValueDataset)result;
/*     */       } 
/*     */     } 
/*     */     
/* 670 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 675 */     return this.m_Title;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTitle(String title) {
/* 680 */     this.m_Title = title;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDlgTitle(String title) {
/* 685 */     this.m_DlgTitle = title;
/*     */   }
/*     */ 
/*     */   
/*     */   private PieDataset createPieDataset(int seriesX) {
/* 690 */     if (this.m_Series == null || this.m_Series.length <= seriesX)
/* 691 */       return null; 
/* 692 */     DefaultPieDataset result = new DefaultPieDataset();
/* 693 */     Object s = this.m_Series[seriesX];
/* 694 */     if (s instanceof TimeSeries) {
/*     */       
/* 696 */       TimeSeries ts = (TimeSeries)s;
/* 697 */       for (int i = 0; i < ts.getItemCount(); i++)
/*     */       {
/* 699 */         TimeSeriesDataItem time = ts.getDataItem(i);
/* 700 */         result.setValue((Comparable)time.getPeriod(), time.getValue());
/*     */       }
/*     */     
/* 703 */     } else if (s instanceof XYSeries) {
/*     */       
/* 705 */       XYSeries xs = (XYSeries)s;
/* 706 */       for (int i = 0; i < xs.getItemCount(); i++) {
/*     */         
/* 708 */         XYDataItem xy = xs.getDataItem(i);
/* 709 */         Number x = xy.getX();
/* 710 */         if (x instanceof Double) {
/* 711 */           result.setValue((Double)x, xy.getY());
/*     */         }
/*     */       } 
/* 714 */     } else if (s instanceof DefaultPieDataset) {
/*     */       
/* 716 */       DefaultPieDataset ps = (DefaultPieDataset)s;
/* 717 */       for (int i = 0; i < ps.getItemCount(); i++)
/*     */       {
/* 719 */         result.setValue(ps.getKey(i), ps.getValue(i));
/*     */       }
/*     */     } 
/*     */     
/* 723 */     return (PieDataset)result;
/*     */   }
/*     */ 
/*     */   
/*     */   private CategoryDataset createCategoryDataset() {
/* 728 */     if (this.m_Series == null || this.m_Series.length == 0)
/* 729 */       return null; 
/* 730 */     DefaultCategoryDataset result = new DefaultCategoryDataset();
/* 731 */     for (int i = 0; i < this.m_Series.length; i++) {
/*     */       
/* 733 */       Object s = this.m_Series[i];
/* 734 */       if (s instanceof TimeSeries) {
/* 735 */         createCategoryDataset(result, (TimeSeries)s, this.m_SeriesTitles[i]);
/* 736 */       } else if (s instanceof XYSeries) {
/* 737 */         createCategoryDataset(result, (XYSeries)s, this.m_SeriesTitles[i], this.m_SectionLabels);
/* 738 */       } else if (s instanceof LbsStringSeries) {
/* 739 */         createCategoryDataset(result, (LbsStringSeries)s, this.m_SeriesTitles[i]);
/*     */       } 
/*     */     } 
/* 742 */     return (CategoryDataset)result;
/*     */   }
/*     */ 
/*     */   
/*     */   private void createCategoryDataset(DefaultCategoryDataset result, TimeSeries series, String caption) {
/* 747 */     if (series != null) {
/* 748 */       for (int i = 0; i < series.getItemCount(); i++) {
/*     */         
/* 750 */         TimeSeriesDataItem time = series.getDataItem(i);
/* 751 */         result.addValue(time.getValue(), caption, (Comparable)time.getPeriod());
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void createCategoryDataset(DefaultCategoryDataset result, XYSeries series, String caption, String[] columnCaptions) {
/* 757 */     if (series != null)
/* 758 */       for (int i = 0; i < series.getItemCount(); i++) {
/*     */         
/* 760 */         XYDataItem xy = series.getDataItem(i);
/* 761 */         Number x = xy.getX();
/* 762 */         if (columnCaptions != null) {
/* 763 */           result.addValue(xy.getY(), caption, columnCaptions[i]);
/* 764 */         } else if (x instanceof Double) {
/* 765 */           result.addValue(xy.getY(), caption, (Double)x);
/*     */         } 
/*     */       }  
/*     */   }
/*     */   
/*     */   private void createCategoryDataset(DefaultCategoryDataset result, LbsStringSeries series, String caption) {
/* 771 */     if (series != null) {
/* 772 */       for (int i = 0; i < series.getItemCount(); i++) {
/*     */         
/* 774 */         LbsStringSeriesDataItem string = series.getDataItem(i);
/* 775 */         result.addValue(string.getY(), caption, string.getX());
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGaugeRange(double[] mGaugeRange) {
/* 782 */     this.m_GaugeRange = mGaugeRange;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSectionLabels(String[] mSectionLabels) {
/* 787 */     this.m_SectionLabels = mSectionLabels;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSectionMinValues(double[] mSectionMinValues) {
/* 792 */     this.m_SectionMinValues = mSectionMinValues;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSectionColors(Color[] mSectionColors) {
/* 797 */     this.m_SectionColors = mSectionColors;
/*     */   }
/*     */ 
/*     */   
/*     */   public void chartMouseClicked(ChartMouseEvent e) {
/* 802 */     MouseEvent me = e.getTrigger();
/* 803 */     if (me.getModifiers() == 16)
/*     */     {
/* 805 */       if (me.getClickCount() == 2) {
/*     */         
/*     */         try {
/*     */           
/* 809 */           me.consume();
/*     */           
/* 811 */           ChartPanel chartPanel = (ChartPanel)me.getSource();
/* 812 */           ChartPanel newPanel = new ChartPanel((JFreeChart)chartPanel.getChart().clone());
/* 813 */           JPanel dlgPanel = new JPanel();
/* 814 */           dlgPanel.add((Component)newPanel);
/*     */           
/* 816 */           JLbsDialog dlg = new JLbsDialog();
/* 817 */           dlg.setDefaultCloseOperation(2);
/* 818 */           dlg.setModal(false);
/* 819 */           dlg.addEscapeCloseShortcut();
/* 820 */           dlg.setContentPane(dlgPanel);
/* 821 */           dlg.pack();
/* 822 */           dlg.centerScreen();
/* 823 */           dlg.show();
/* 824 */           dlg.setTitle(this.m_DlgTitle);
/*     */         }
/* 826 */         catch (CloneNotSupportedException ex) {
/*     */           
/* 828 */           JLbsComponentHelper.getLogger(getClass().getName()).error(ex);
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void chartMouseMoved(ChartMouseEvent event) {}
/*     */ 
/*     */   
/*     */   public String getLang() {
/* 840 */     return this.m_Lang;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLang(String lang) {
/* 845 */     this.m_Lang = lang;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\chartgenerator\JLbsChartGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */