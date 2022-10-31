/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.recording.JLbsDataPoolItem;
/*     */ import com.lbs.recording.JLbsRecordItem;
/*     */ import com.lbs.recording.interfaces.ILbsEventRecorder;
/*     */ import java.util.StringTokenizer;
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
/*     */ public class JLbsEventRecorderHelper
/*     */ {
/*     */   public static JLbsDataPoolItem prepareDataPoolItemForGrid(ILbsComponentBase comp, String cellValue, int row, int column) {
/*  24 */     return new JLbsDataPoolItem(JLbsComponentHelper.getTag(comp), JLbsComponentHelper.getParentTag(comp), 
/*  25 */         JLbsComponentHelper.getType(comp), cellValue, JLbsComponentHelper.getTitleForColumn(comp, row, column), 
/*  26 */         JLbsComponentHelper.getPossibleValues(comp));
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsDataPoolItem prepareDataPoolItem(ILbsComponentBase comp) {
/*  31 */     return new JLbsDataPoolItem(JLbsComponentHelper.getTag(comp), JLbsComponentHelper.getParentTag(comp), 
/*  32 */         JLbsComponentHelper.getType(comp), JLbsComponentHelper.getValue(comp), JLbsComponentHelper.getTitle(comp), 
/*  33 */         JLbsComponentHelper.getPossibleValues(comp));
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsDataPoolItem prepareDataPoolItem(ILbsComponentBase comp, String cellValue) {
/*  38 */     return new JLbsDataPoolItem(JLbsComponentHelper.getTag(comp), JLbsComponentHelper.getParentTag(comp), 
/*  39 */         JLbsComponentHelper.getType(comp), cellValue, JLbsComponentHelper.getTitle(comp), 
/*  40 */         JLbsComponentHelper.getPossibleValues(comp));
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsDataPoolItem prepareDataPoolItem(ILbsComponentBase comp, Object value) {
/*  45 */     return prepareDataPoolItem(comp, value, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsDataPoolItem prepareDataPoolItem(ILbsComponentBase comp, Object value, String title) {
/*  50 */     if (title == null || title.equals(""))
/*  51 */       title = JLbsComponentHelper.getTitle(comp); 
/*  52 */     return new JLbsDataPoolItem(JLbsComponentHelper.getTag(comp), JLbsComponentHelper.getParentTag(comp), 
/*  53 */         JLbsComponentHelper.getType(comp), value, title, JLbsComponentHelper.getPossibleValues(comp));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JLbsRecordItem prepareRecordItem(ILbsComponentBase comp, String actionCmd, String cellValue) {
/*  60 */     if (actionCmd.startsWith("GRID_CELL_EDIT")) {
/*     */       
/*  62 */       StringTokenizer tokenizer = new StringTokenizer(actionCmd, "|");
/*  63 */       String[] params = new String[tokenizer.countTokens()];
/*     */       
/*  65 */       for (int i = 0; i < params.length; i++) {
/*  66 */         params[i] = tokenizer.nextToken();
/*     */       }
/*  68 */       int row = Integer.parseInt(params[1]);
/*  69 */       int column = Integer.parseInt(params[2]);
/*     */       
/*  71 */       return new JLbsRecordItem(prepareDataPoolItemForGrid(comp, cellValue, row, column), actionCmd);
/*     */     } 
/*     */     
/*  74 */     return new JLbsRecordItem(prepareDataPoolItem(comp, cellValue), actionCmd);
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsRecordItem prepareRecordItem(ILbsComponentBase comp, String actionCmd) {
/*  79 */     return new JLbsRecordItem(prepareDataPoolItem(comp), actionCmd);
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsRecordItem prepareRecordItem(ILbsComponentBase comp, String actionCmd, Object value) {
/*  84 */     return new JLbsRecordItem(prepareDataPoolItem(comp, value), actionCmd);
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsRecordItem prepareRecordItem(ILbsComponentBase comp, String actionCmd, Object value, String title) {
/*  89 */     return new JLbsRecordItem(prepareDataPoolItem(comp, value, title), actionCmd);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static JLbsRecordItem prepareRecordItem(ILbsComponentBase comp, String actionCmd, Object value, String title, Object recordItemData) {
/*  95 */     return new JLbsRecordItem(prepareDataPoolItem(comp, value, title), actionCmd, recordItemData);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addRecordItem(ILbsComponentBase comp, String actionCmd, String cellValue) {
/* 100 */     ILbsEventRecorder eventRecorder = JLbsComponentHelper.getEventRecorder();
/* 101 */     if (eventRecorder != null) {
/* 102 */       eventRecorder.addRecordItem(prepareRecordItem(comp, actionCmd, cellValue), comp);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addRecordItem(ILbsComponentBase comp, String actionCmd) {
/* 111 */     ILbsEventRecorder eventRecorder = JLbsComponentHelper.getEventRecorder();
/* 112 */     if (eventRecorder != null) {
/* 113 */       eventRecorder.addRecordItem(prepareRecordItem(comp, actionCmd), comp);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addRecordItem(ILbsComponentBase comp, String actionCmd, Object value) {
/* 122 */     ILbsEventRecorder eventRecorder = JLbsComponentHelper.getEventRecorder();
/* 123 */     if (eventRecorder != null) {
/* 124 */       eventRecorder.addRecordItem(prepareRecordItem(comp, actionCmd, value), comp);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void addRecordItem(ILbsComponentBase comp, String actionCmd, Object value, String title) {
/* 129 */     ILbsEventRecorder eventRecorder = JLbsComponentHelper.getEventRecorder();
/* 130 */     if (eventRecorder != null) {
/* 131 */       eventRecorder.addRecordItem(prepareRecordItem(comp, actionCmd, value, title), comp);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void addRecordItem(ILbsComponentBase comp, String actionCmd, Object value, String title, Object recordItemData) {
/* 136 */     ILbsEventRecorder eventRecorder = JLbsComponentHelper.getEventRecorder();
/* 137 */     if (eventRecorder != null) {
/* 138 */       eventRecorder.addRecordItem(prepareRecordItem(comp, actionCmd, value, title, recordItemData), comp);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void addRecordItem(JLbsRecordItem recordItem) {
/* 143 */     ILbsEventRecorder eventRecorder = JLbsComponentHelper.getEventRecorder();
/* 144 */     if (eventRecorder != null) {
/* 145 */       eventRecorder.addRecordItem(recordItem, null);
/*     */     }
/*     */   }
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
/*     */   
/*     */   public static void generteEmulatingController() {
/* 166 */     ILbsEventRecorder eventRecorder = JLbsComponentHelper.getEventRecorder();
/* 167 */     if (eventRecorder != null) {
/* 168 */       eventRecorder.generateEmulating();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void recordTest() {
/* 173 */     ILbsEventRecorder eventRecorder = JLbsComponentHelper.getEventRecorder();
/* 174 */     if (eventRecorder != null) {
/* 175 */       eventRecorder.recordTest();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setSavePropOfLastItem() {
/* 180 */     ILbsEventRecorder eventRecorder = JLbsComponentHelper.getEventRecorder();
/* 181 */     if (eventRecorder != null) {
/* 182 */       eventRecorder.setLastItemAsSaveOperation();
/*     */     }
/*     */   }
/*     */   
/*     */   public static String buildActionCommand(Object param) {
/* 187 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addXUIPane(Object xuiPane, boolean isLookup) {
/* 192 */     ILbsEventRecorder eventRecorder = JLbsComponentHelper.getEventRecorder();
/* 193 */     if (eventRecorder != null) {
/*     */       
/* 195 */       eventRecorder.setLookup(isLookup);
/* 196 */       eventRecorder.setJustInTime(true);
/* 197 */       eventRecorder.setEmulatingPane(xuiPane);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsEventRecorderHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */