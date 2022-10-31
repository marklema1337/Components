/*    */ package com.lbs.controls.tablereport;
/*    */ 
/*    */ import com.lbs.controls.JLbsPanel;
/*    */ import com.lbs.resource.JLbsLocalizer;
/*    */ import com.lbs.util.JLbsFrame;
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Component;
/*    */ import java.awt.GridBagLayout;
/*    */ import javax.swing.JLabel;
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
/*    */ public class TableReportDlg
/*    */   extends JLbsFrame
/*    */ {
/*    */   private TableReportInfo tableReportInfo;
/*    */   private TableReportPanel reportPanel;
/*    */   private JLbsPanel tablePanel;
/*    */   
/*    */   public TableReportDlg(TableReportInfo tableReportInfo) {
/* 30 */     this.tableReportInfo = tableReportInfo;
/*    */     
/* 32 */     setResizable(true);
/* 33 */     setTitle(TableReportConstants.STRINGS2[1]);
/* 34 */     setDefaultCloseOperation(2);
/* 35 */     setSize(800, 600);
/* 36 */     setLayout(new BorderLayout());
/*    */     
/* 38 */     this.tablePanel = new JLbsPanel();
/* 39 */     this.tablePanel.setLayout(new GridBagLayout());
/*    */     
/* 41 */     createTableReportPanel();
/*    */     
/* 43 */     if (tableReportInfo != null && tableReportInfo.getDataList().size() > 0)
/*    */     {
/* 45 */       this.tablePanel.add(new JLabel(JLbsLocalizer.getString(-1997, 25, true)));
/*    */     }
/*    */     
/* 48 */     if (this.reportPanel != null)
/* 49 */       this.reportPanel.add((Component)this.tablePanel); 
/* 50 */     add(this.reportPanel, "Center");
/* 51 */     JLabel label = new JLabel(String.valueOf(JLbsLocalizer.getString(8804, 1013, true)) + " : " + tableReportInfo.getDataList().size());
/* 52 */     label.setFont(this.tablePanel.getFont());
/* 53 */     add(label, "Last");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void createTableReportPanel() {
/* 59 */     if (this.tableReportInfo == null) {
/*    */       return;
/*    */     }
/* 62 */     this.reportPanel = new TableReportPanel(this.tableReportInfo);
/*    */     
/* 64 */     if (this.tableReportInfo.getDataList().size() == 0) {
/*    */       
/* 66 */       this.tablePanel.add(new JLabel(JLbsLocalizer.getString(-1997, 26, true)));
/* 67 */       add((Component)this.tablePanel, "Center");
/*    */       return;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\TableReportDlg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */