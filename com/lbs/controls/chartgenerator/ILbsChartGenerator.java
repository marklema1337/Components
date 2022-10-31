package com.lbs.controls.chartgenerator;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.Calendar;
import org.jfree.data.time.RegularTimePeriod;

public interface ILbsChartGenerator {
  void generateCharts();
  
  int getChartType();
  
  void setChartType(int paramInt);
  
  boolean is3D();
  
  void set3D(boolean paramBoolean);
  
  int getProperXAxis(int paramInt);
  
  void setXAxis(int paramInt, String paramString);
  
  void setNumSeries(int paramInt);
  
  boolean isHaveLegend();
  
  void setHaveLegend(boolean paramBoolean);
  
  boolean isItemValues();
  
  void setItemValues(boolean paramBoolean);
  
  Object createSeries(int paramInt1, int paramInt2, String paramString);
  
  Object createSeries(int paramInt1, int paramInt2, int paramInt3, String paramString);
  
  boolean addValue(int paramInt, BigDecimal paramBigDecimal);
  
  boolean addPieValue(int paramInt, String paramString, BigDecimal paramBigDecimal);
  
  boolean addXYValue(int paramInt, double paramDouble1, double paramDouble2);
  
  boolean addXYValue(int paramInt, BigDecimal paramBigDecimal1, BigDecimal paramBigDecimal2);
  
  boolean addXYValue(int paramInt, RegularTimePeriod paramRegularTimePeriod, double paramDouble);
  
  boolean addXYValue(int paramInt, Calendar paramCalendar, BigDecimal paramBigDecimal);
  
  boolean addXYValue(int paramInt1, int paramInt2, int paramInt3, BigDecimal paramBigDecimal);
  
  boolean addXYValue(int paramInt, String paramString, BigDecimal paramBigDecimal);
  
  boolean addXYValue(int paramInt1, int paramInt2, BigDecimal paramBigDecimal);
  
  void setYAxis(int paramInt, String paramString);
  
  String getTitle();
  
  void setTitle(String paramString);
  
  void setDlgTitle(String paramString);
  
  void setGaugeRange(double[] paramArrayOfdouble);
  
  void setSectionLabels(String[] paramArrayOfString);
  
  void setSectionMinValues(double[] paramArrayOfdouble);
  
  void setSectionColors(Color[] paramArrayOfColor);
  
  String getLang();
  
  void setLang(String paramString);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\chartgenerator\ILbsChartGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */