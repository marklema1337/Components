/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.localization.LbsLocalizableException;
/*     */ import java.io.InputStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.MathContext;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Date;
/*     */ import org.apache.poi.ss.usermodel.Cell;
/*     */ import org.apache.poi.ss.usermodel.CellType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExcelAPI
/*     */ {
/*     */   private WritableExcelAPI excel;
/*  26 */   private int activeSheetNr = 0;
/*     */   
/*     */   private static char DECIMAL_SEPERATOR;
/*     */   private static char GROUPING_SEPERATOR;
/*     */   private static DecimalFormat decimalFormat;
/*  31 */   private static int[] ms_NUMERIC_ERROR_PAIRS = new int[] { 20095, 3 };
/*     */ 
/*     */   
/*     */   static {
/*  35 */     decimalFormat = new DecimalFormat();
/*  36 */     DECIMAL_SEPERATOR = decimalFormat.getDecimalFormatSymbols().getDecimalSeparator();
/*  37 */     GROUPING_SEPERATOR = decimalFormat.getDecimalFormatSymbols().getGroupingSeparator();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean openExcelFile(String file, boolean visible, boolean readOnly) {
/*  42 */     this.excel = new WritableExcelAPI();
/*  43 */     return this.excel.openExcelFile(file, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean openExcelFile(InputStream stream) {
/*  48 */     this.excel = new WritableExcelAPI();
/*  49 */     return this.excel.openExcelFile(stream);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeExcelFile(boolean save, boolean f) {
/*     */     try {
/*  56 */       this.excel.closeExcelFile(save, f);
/*  57 */       this.excel = null;
/*     */     }
/*  59 */     catch (Exception e) {
/*     */       
/*  61 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setCellValue(int row, int column, String type, String value) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCellValue(int row, int column) {
/*  78 */     if (this.excel != null)
/*  79 */       return this.excel.getCellValue(this.activeSheetNr, row, column); 
/*  80 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public Double getCellValueAsDouble(int row, int column) {
/*  85 */     if (this.excel != null)
/*  86 */       return this.excel.getCellAsNumber(this.activeSheetNr, row, column); 
/*  87 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getCellValueAsDate(int row, int column) throws ParseException {
/*  92 */     if (this.excel != null)
/*  93 */       return this.excel.getCellAsDate(this.activeSheetNr, row, column); 
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer getCellValueAsInteger(int row, int column) {
/*  99 */     Double d = getCellValueAsDouble(row, column);
/* 100 */     if (d != null)
/* 101 */       return Integer.valueOf(d.intValue()); 
/* 102 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Boolean getCellValueAsBoolean(int row, int column) {
/* 107 */     if (this.excel != null)
/* 108 */       return this.excel.getCellAsBoolean(this.activeSheetNr, row, column); 
/* 109 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal getCellValueAsBigDecimal(int row, int column) throws LbsLocalizableException {
/* 114 */     return getCellValueAsBigDecimal(row, column, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal getCellValueAsBigDecimal(int row, int column, boolean hardParse) throws LbsLocalizableException {
/* 119 */     if (this.excel == null) {
/* 120 */       return null;
/*     */     }
/* 122 */     Cell cell = this.excel.getCell(this.activeSheetNr, row, column);
/* 123 */     if (cell == null) {
/* 124 */       return new BigDecimal(0);
/*     */     }
/* 126 */     if (cell.getCellType() == CellType.NUMERIC) {
/* 127 */       return new BigDecimal(cell.getNumericCellValue());
/*     */     }
/* 129 */     String cellText = getCellValue(row, column);
/* 130 */     if (JLbsStringUtil.isEmpty(cellText)) {
/* 131 */       return new BigDecimal(0);
/*     */     }
/*     */     try {
/* 134 */       if (hardParse) {
/*     */         
/* 136 */         ParsePosition pos = new ParsePosition(0);
/* 137 */         Number number1 = (Number)NumberFormat.getNumberInstance().parseObject(cellText, pos);
/* 138 */         if (pos.getIndex() < cellText.length())
/*     */         {
/* 140 */           throw new LbsLocalizableException(ms_NUMERIC_ERROR_PAIRS[0], ms_NUMERIC_ERROR_PAIRS[1], "Not a number");
/*     */         }
/* 142 */         return new BigDecimal(number1.doubleValue(), MathContext.DECIMAL64);
/*     */       } 
/* 144 */       Number number = decimalFormat.parse(cellText);
/* 145 */       return new BigDecimal(number.doubleValue(), MathContext.DECIMAL64);
/*     */     }
/* 147 */     catch (ParseException e) {
/*     */       
/* 149 */       int decimalIndex = cellText.lastIndexOf(DECIMAL_SEPERATOR);
/* 150 */       String valueBeforeSeperator = cellText;
/* 151 */       String valueAfterSeperator = "0";
/* 152 */       if (decimalIndex != -1) {
/*     */         
/* 154 */         valueBeforeSeperator = cellText.substring(0, decimalIndex);
/* 155 */         valueAfterSeperator = cellText.substring(decimalIndex + 1);
/*     */       } 
/* 157 */       String numbers = "Ee+-1234567890";
/* 158 */       StringBuilder bufferFilteredValue = new StringBuilder();
/* 159 */       char[] valueChars = valueBeforeSeperator.toCharArray();
/* 160 */       for (int i = 0; i < valueChars.length; i++) {
/*     */         
/* 162 */         if (numbers.indexOf(valueChars[i]) != -1)
/* 163 */           bufferFilteredValue.append(valueChars[i]); 
/*     */       } 
/* 165 */       String filteredValue = String.valueOf(bufferFilteredValue.toString()) + DECIMAL_SEPERATOR + valueAfterSeperator;
/*     */       
/*     */       try {
/* 168 */         Number number = NumberFormat.getNumberInstance().parse(filteredValue);
/* 169 */         return new BigDecimal(number.doubleValue(), MathContext.DECIMAL64);
/*     */       }
/* 171 */       catch (Exception e2) {
/*     */         
/* 173 */         return new BigDecimal(filteredValue);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getActiveSheetNr() {
/* 180 */     return this.activeSheetNr;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActiveSheetNr(int activeSheetNr) {
/* 185 */     this.activeSheetNr = activeSheetNr;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCellType(Cell cell) {
/* 190 */     if (cell.getCellType() == CellType.BOOLEAN) {
/* 191 */       return 1;
/*     */     }
/* 193 */     if (cell.getCellType() == CellType.STRING) {
/* 194 */       return 4;
/*     */     }
/* 196 */     if (cell.getCellType() == CellType.NUMERIC) {
/* 197 */       return 5;
/*     */     }
/* 199 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCellType(int row, int column) {
/* 204 */     if (this.excel != null) {
/*     */       
/*     */       try {
/*     */         
/* 208 */         Cell cell = this.excel.getCell(this.activeSheetNr, row, column);
/* 209 */         return getCellType(cell);
/*     */       }
/* 211 */       catch (Exception e) {
/*     */         
/* 213 */         return 3;
/*     */       } 
/*     */     }
/* 216 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 221 */     ExcelAPI excelAPI = new ExcelAPI();
/* 222 */     excelAPI.openExcelFile("D:\\Test\\sil.xlsx", false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 232 */     excelAPI.closeExcelFile(false, false);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ExcelAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */