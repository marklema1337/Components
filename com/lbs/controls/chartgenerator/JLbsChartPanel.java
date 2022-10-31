/*    */ package com.lbs.controls.chartgenerator;
/*    */ 
/*    */ import com.lbs.resource.JLbsLocalizer;
/*    */ import java.awt.Component;
/*    */ import java.util.Locale;
/*    */ import javax.swing.JOptionPane;
/*    */ import javax.swing.JPopupMenu;
/*    */ import org.jfree.chart.ChartPanel;
/*    */ import org.jfree.chart.JFreeChart;
/*    */ import org.jfree.chart.editor.ChartEditor;
/*    */ import org.jfree.chart.editor.ChartEditorManager;
/*    */ import org.jfree.chart.util.ResourceBundleWrapper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsChartPanel
/*    */   extends ChartPanel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 30 */   public static String ms_Lang = "";
/*    */ 
/*    */   
/*    */   public JLbsChartPanel(JFreeChart chart) {
/* 34 */     super(chart);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected JPopupMenu createPopupMenu(boolean properties, boolean copy, boolean save, boolean print, boolean zoom) {
/* 40 */     if (ms_Lang != null)
/*    */     { String str;
/* 42 */       switch ((str = ms_Lang).hashCode()) { case 2583932: if (!str.equals("TRTR")) {
/*    */             break;
/*    */           }
/* 45 */           localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.LocalizationBundle", 
/* 46 */               new Locale("tr", "TR"));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 54 */           return super.createPopupMenu(properties, copy, save, print, zoom); }  localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.LocalizationBundle", new Locale("en", "US")); }  return super.createPopupMenu(properties, copy, save, print, zoom);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void doEditChartProperties() {
/* 60 */     String ok = JLbsLocalizer.getLocalizationService().getItem(ms_Lang, -20000, 13);
/* 61 */     String cancel = JLbsLocalizer.getLocalizationService().getItem(ms_Lang, -20000, 12);
/* 62 */     ChartEditor editor = ChartEditorManager.getChartEditor(getChart());
/* 63 */     int result = JOptionPane.showOptionDialog((Component)this, editor, 
/* 64 */         localizationResources.getString("Chart_Properties"), 
/* 65 */         2, -1, null, new Object[] { ok, cancel }, cancel);
/* 66 */     if (result == 0)
/* 67 */       editor.updateChart(getChart()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\chartgenerator\JLbsChartPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */