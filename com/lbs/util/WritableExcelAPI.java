/*      */ package com.lbs.util;
/*      */ 
/*      */ import com.lbs.controls.datedit.JLbsTimeDuration;
/*      */ import com.lbs.controls.pivottable.PivotCellValue;
/*      */ import com.lbs.controls.pivottable.PivotTableInfo;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.math.BigDecimal;
/*      */ import java.security.GeneralSecurityException;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.Locale;
/*      */ import org.apache.poi.common.usermodel.HyperlinkType;
/*      */ import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
/*      */ import org.apache.poi.hssf.usermodel.HSSFRichTextString;
/*      */ import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*      */ import org.apache.poi.hssf.util.HSSFColor;
/*      */ import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
/*      */ import org.apache.poi.poifs.crypt.EncryptionInfo;
/*      */ import org.apache.poi.poifs.crypt.EncryptionMode;
/*      */ import org.apache.poi.poifs.crypt.Encryptor;
/*      */ import org.apache.poi.poifs.filesystem.POIFSFileSystem;
/*      */ import org.apache.poi.ss.usermodel.BorderStyle;
/*      */ import org.apache.poi.ss.usermodel.Cell;
/*      */ import org.apache.poi.ss.usermodel.CellStyle;
/*      */ import org.apache.poi.ss.usermodel.CellType;
/*      */ import org.apache.poi.ss.usermodel.ClientAnchor;
/*      */ import org.apache.poi.ss.usermodel.Comment;
/*      */ import org.apache.poi.ss.usermodel.DataFormat;
/*      */ import org.apache.poi.ss.usermodel.Drawing;
/*      */ import org.apache.poi.ss.usermodel.FillPatternType;
/*      */ import org.apache.poi.ss.usermodel.Font;
/*      */ import org.apache.poi.ss.usermodel.HorizontalAlignment;
/*      */ import org.apache.poi.ss.usermodel.Hyperlink;
/*      */ import org.apache.poi.ss.usermodel.RichTextString;
/*      */ import org.apache.poi.ss.usermodel.Row;
/*      */ import org.apache.poi.ss.usermodel.Sheet;
/*      */ import org.apache.poi.ss.usermodel.VerticalAlignment;
/*      */ import org.apache.poi.ss.usermodel.Workbook;
/*      */ import org.apache.poi.ss.usermodel.WorkbookFactory;
/*      */ import org.apache.poi.ss.util.CellRangeAddress;
/*      */ import org.apache.poi.ss.util.CellReference;
/*      */ import org.apache.poi.ss.util.NumberToTextConverter;
/*      */ import org.apache.poi.xssf.streaming.SXSSFSheet;
/*      */ import org.apache.poi.xssf.streaming.SXSSFWorkbook;
/*      */ import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class WritableExcelAPI
/*      */ {
/*      */   private Workbook workBook;
/*      */   public boolean forcedXlsx = false;
/*      */   private String fileName;
/*      */   private LbsFontCache fontCache;
/*      */   private LbsCellStyleCache cellstyleCache;
/*      */   private String password;
/*   78 */   private HashMap<Object, CellStyle> m_CellStyles = new HashMap<>();
/*      */   
/*   80 */   private HashMap<Object, CellStyle> m_DataFormatCellStyles = new HashMap<>();
/*      */ 
/*      */   
/*      */   public boolean openExcelFile(String filePath, boolean forceOverwrite) {
/*   84 */     return openExcelFile(filePath, forceOverwrite, false);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean openExcelFile(String filePath, boolean forceOverwrite, boolean isForceXlsx, boolean isForWriting) {
/*      */     try {
/*   91 */       File originalFile = new File(filePath);
/*      */       
/*   93 */       if (originalFile.exists() && !forceOverwrite) {
/*      */         
/*   95 */         String fileName = JLbsFileUtil.getFileName(filePath);
/*   96 */         fileName = JLbsFileUtil.ensureFileName(fileName);
/*      */         
/*   98 */         String fileDir = JLbsFileUtil.getFileDir(filePath);
/*   99 */         fileDir = JLbsFileUtil.ensurePath(fileDir);
/*      */         
/*  101 */         String copyFilePath = String.valueOf(fileDir) + "Test_" + Calendar.getInstance().getTimeInMillis() + "_" + fileName;
/*  102 */         File copyFile = new File(copyFilePath);
/*  103 */         this.fileName = copyFile.getPath();
/*  104 */         JLbsFileUtil.copyFile(originalFile, copyFile);
/*      */         
/*  106 */         this.workBook = isForceXlsx ? 
/*  107 */           createWorkbook(copyFile, true) : 
/*  108 */           createWorkbook(copyFile);
/*  109 */         this.fontCache = new LbsFontCache(this.workBook);
/*  110 */         this.cellstyleCache = new LbsCellStyleCache(this.workBook);
/*  111 */         copyFile.delete();
/*      */ 
/*      */       
/*      */       }
/*  115 */       else if (isForWriting) {
/*      */         
/*  117 */         File newFile = new File(originalFile.getPath());
/*  118 */         originalFile.delete();
/*  119 */         this.workBook = isForceXlsx ? 
/*  120 */           createWorkbook(newFile, true) : 
/*  121 */           createWorkbook(newFile);
/*  122 */         this.fontCache = new LbsFontCache(this.workBook);
/*  123 */         this.cellstyleCache = new LbsCellStyleCache(this.workBook);
/*  124 */         this.fileName = newFile.getPath();
/*      */       }
/*      */       else {
/*      */         
/*  128 */         this.workBook = isForceXlsx ? 
/*  129 */           createWorkbook(originalFile, true) : 
/*  130 */           createWorkbook(originalFile);
/*  131 */         this.fontCache = new LbsFontCache(this.workBook);
/*  132 */         this.cellstyleCache = new LbsCellStyleCache(this.workBook);
/*  133 */         this.fileName = originalFile.getPath();
/*      */       } 
/*      */       
/*  136 */       return true;
/*      */     }
/*  138 */     catch (Exception e) {
/*      */       
/*  140 */       this.workBook = null;
/*  141 */       this.fontCache = null;
/*  142 */       this.cellstyleCache = null;
/*  143 */       this.fileName = null;
/*  144 */       e.printStackTrace();
/*      */       
/*  146 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean openExcelFile(String filePath, boolean forceOverwrite, boolean isForceXlsx) {
/*      */     try {
/*  153 */       File originalFile = new File(filePath);
/*      */       
/*  155 */       if (originalFile.exists() && !forceOverwrite) {
/*      */         
/*  157 */         String fileName = JLbsFileUtil.getFileName(filePath);
/*  158 */         fileName = JLbsFileUtil.ensureFileName(fileName);
/*      */         
/*  160 */         String fileDir = JLbsFileUtil.getFileDir(filePath);
/*  161 */         fileDir = JLbsFileUtil.ensurePath(fileDir);
/*      */         
/*  163 */         String copyFilePath = String.valueOf(fileDir) + "Test_" + Calendar.getInstance().getTimeInMillis() + "_" + fileName;
/*  164 */         File copyFile = new File(copyFilePath);
/*  165 */         this.fileName = copyFile.getPath();
/*  166 */         JLbsFileUtil.copyFile(originalFile, copyFile);
/*      */         
/*  168 */         this.workBook = isForceXlsx ? 
/*  169 */           createWorkbook(copyFile, true) : 
/*  170 */           createWorkbook(copyFile);
/*  171 */         this.fontCache = new LbsFontCache(this.workBook);
/*  172 */         this.cellstyleCache = new LbsCellStyleCache(this.workBook);
/*  173 */         copyFile.delete();
/*      */       }
/*      */       else {
/*      */         
/*  177 */         this.workBook = isForceXlsx ? 
/*  178 */           createWorkbook(originalFile, true) : 
/*  179 */           createWorkbook(originalFile);
/*  180 */         this.fontCache = new LbsFontCache(this.workBook);
/*  181 */         this.cellstyleCache = new LbsCellStyleCache(this.workBook);
/*  182 */         this.fileName = originalFile.getPath();
/*      */       } 
/*  184 */       return true;
/*      */     }
/*  186 */     catch (Exception e) {
/*      */       
/*  188 */       this.workBook = null;
/*  189 */       this.fontCache = null;
/*  190 */       this.cellstyleCache = null;
/*  191 */       this.fileName = null;
/*  192 */       e.printStackTrace();
/*      */       
/*  194 */       return false;
/*      */     } 
/*      */   }
/*      */   
/*      */   private Workbook createWorkbook(File file) throws IOException, InvalidFormatException {
/*  199 */     Workbook workbook = null;
/*  200 */     this.forcedXlsx = false;
/*  201 */     if (!file.exists()) {
/*      */       
/*  203 */       String extension = JLbsFileUtil.getExtension(file.getPath());
/*  204 */       if (extension != null && extension.toLowerCase().equals("xlsx"))
/*      */       {
/*  206 */         SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook(new XSSFWorkbook(), 1000);
/*  207 */         this.forcedXlsx = true;
/*      */       }
/*      */       else
/*      */       {
/*  211 */         HSSFWorkbook hSSFWorkbook = new HSSFWorkbook();
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  216 */       workbook = WorkbookFactory.create(new FileInputStream(file));
/*      */     } 
/*  218 */     return workbook;
/*      */   }
/*      */ 
/*      */   
/*      */   private Workbook createWorkbook(File file, boolean isXlsx) throws IOException {
/*  223 */     if (file.exists()) {
/*      */       
/*  225 */       if (isXlsx)
/*      */       {
/*  227 */         FileInputStream in = new FileInputStream(file);
/*  228 */         this.forcedXlsx = true;
/*  229 */         return (Workbook)new SXSSFWorkbook(new XSSFWorkbook(in), 1000);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  234 */     else if (isXlsx) {
/*      */       
/*  236 */       this.forcedXlsx = true;
/*  237 */       return (Workbook)new SXSSFWorkbook(new XSSFWorkbook(), 1000);
/*      */     } 
/*      */     
/*  240 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean openExcelFile(String inputFileName, String outputFileName) {
/*      */     try {
/*  247 */       File originalFile = new File(inputFileName);
/*      */       
/*  249 */       if (originalFile.exists()) {
/*      */         
/*  251 */         String outputDir = JLbsFileUtil.getFileDir(outputFileName);
/*  252 */         JLbsFileUtil.makeDirectory(outputDir);
/*  253 */         File copyFile = new File(outputFileName);
/*  254 */         JLbsFileUtil.copyFile(originalFile, copyFile);
/*  255 */         this.workBook = this.forcedXlsx ? 
/*  256 */           createWorkbook(copyFile, true) : 
/*  257 */           createWorkbook(copyFile);
/*  258 */         this.fontCache = new LbsFontCache(this.workBook);
/*  259 */         this.cellstyleCache = new LbsCellStyleCache(this.workBook);
/*  260 */         this.fileName = copyFile.getPath();
/*      */       }
/*      */       else {
/*      */         
/*  264 */         this.workBook = this.forcedXlsx ? 
/*  265 */           createWorkbook(originalFile, true) : 
/*  266 */           createWorkbook(originalFile);
/*  267 */         this.fontCache = new LbsFontCache(this.workBook);
/*  268 */         this.cellstyleCache = new LbsCellStyleCache(this.workBook);
/*  269 */         this.fileName = originalFile.getPath();
/*      */       } 
/*      */       
/*  272 */       return true;
/*      */     }
/*  274 */     catch (Exception e) {
/*      */       
/*  276 */       this.workBook = null;
/*  277 */       this.fontCache = null;
/*  278 */       this.cellstyleCache = null;
/*  279 */       this.fileName = null;
/*  280 */       e.printStackTrace();
/*      */       
/*  282 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean openExcelFile(InputStream stream) {
/*      */     try {
/*  289 */       byte[] b = new byte[2];
/*  290 */       stream.read(b);
/*  291 */       stream.reset();
/*  292 */       boolean xlsx = (new String(b)).equalsIgnoreCase("PK");
/*      */       
/*  294 */       if (this.forcedXlsx) {
/*  295 */         this.workBook = xlsx ? 
/*  296 */           (Workbook)new SXSSFWorkbook(new XSSFWorkbook(stream), 1000) : 
/*  297 */           (Workbook)new HSSFWorkbook(stream);
/*      */       } else {
/*      */         
/*  300 */         this.workBook = xlsx ? 
/*  301 */           (Workbook)new XSSFWorkbook(stream) : 
/*  302 */           (Workbook)new HSSFWorkbook(stream);
/*      */       } 
/*  304 */       this.fontCache = new LbsFontCache(this.workBook);
/*  305 */       this.cellstyleCache = new LbsCellStyleCache(this.workBook);
/*  306 */       this.fileName = null;
/*  307 */       return true;
/*      */     }
/*  309 */     catch (IOException e) {
/*      */       
/*  311 */       this.workBook = null;
/*  312 */       this.fontCache = null;
/*  313 */       this.cellstyleCache = null;
/*  314 */       this.fileName = null;
/*  315 */       e.printStackTrace();
/*      */       
/*  317 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void closeExcelFile(boolean save, String outputFileName) {
/*      */     try {
/*  324 */       if (this.workBook != null) {
/*      */         
/*  326 */         File outputFile = new File(outputFileName);
/*  327 */         FileOutputStream fos = new FileOutputStream(outputFile);
/*      */         
/*      */         try {
/*  330 */           if (!JLbsStringUtil.isEmpty(this.password)) {
/*      */             
/*  332 */             if (this.workBook instanceof SXSSFWorkbook) {
/*      */               Exception exception2;
/*  334 */               boolean exception = false;
/*  335 */               Exception exception1 = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             }
/*  351 */             else if (this.workBook instanceof HSSFWorkbook) {
/*      */               
/*  353 */               ((HSSFWorkbook)this.workBook).writeProtectWorkbook(this.password, "");
/*  354 */               this.workBook.write(fos);
/*      */             } 
/*      */           } else {
/*      */             
/*  358 */             this.workBook.write(fos);
/*      */           } 
/*      */         } finally {
/*      */           
/*  362 */           if (this.workBook instanceof SXSSFWorkbook)
/*  363 */             ((SXSSFWorkbook)this.workBook).dispose(); 
/*  364 */           this.workBook = null;
/*  365 */           this.fontCache = null;
/*  366 */           this.cellstyleCache = null;
/*  367 */           this.fileName = null;
/*  368 */           fos.flush();
/*  369 */           fos.close();
/*      */         }
/*      */       
/*      */       } 
/*  373 */     } catch (IOException e1) {
/*      */       
/*  375 */       e1.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void closeExcelFile(boolean save, boolean f) {
/*      */     try {
/*  383 */       if (this.workBook != null && save)
/*      */       {
/*  385 */         FileOutputStream fos = new FileOutputStream(this.fileName);
/*      */ 
/*      */         
/*      */         try {
/*  389 */           if (!JLbsStringUtil.isEmpty(this.password)) {
/*      */             
/*  391 */             if (this.workBook instanceof SXSSFWorkbook) {
/*      */               
/*  393 */               Exception exception2, exception1 = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             }
/*  403 */             else if (this.workBook instanceof HSSFWorkbook) {
/*      */               
/*  405 */               ((HSSFWorkbook)this.workBook).writeProtectWorkbook(this.password, "");
/*  406 */               this.workBook.write(fos);
/*      */             } 
/*      */           } else {
/*      */             
/*  410 */             this.workBook.write(fos);
/*      */           } 
/*      */         } finally {
/*      */           
/*  414 */           if (this.workBook instanceof SXSSFWorkbook)
/*  415 */             ((SXSSFWorkbook)this.workBook).dispose(); 
/*  416 */           this.workBook = null;
/*  417 */           this.fontCache = null;
/*  418 */           this.cellstyleCache = null;
/*  419 */           this.fileName = null;
/*  420 */           fos.flush();
/*  421 */           fos.close();
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  427 */     catch (Exception e) {
/*      */       
/*  429 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private OutputStream getEncryptingOutputStream(POIFSFileSystem fileSystem, String password) throws IOException, GeneralSecurityException {
/*  436 */     EncryptionInfo encryptionInfo = new EncryptionInfo(EncryptionMode.standard);
/*  437 */     Encryptor encryptor = encryptionInfo.getEncryptor();
/*  438 */     encryptor.confirmPassword(password);
/*  439 */     return encryptor.getDataStream(fileSystem);
/*      */   }
/*      */ 
/*      */   
/*      */   private Sheet getSheet(int sheetIndex) {
/*  444 */     if (this.workBook == null)
/*  445 */       return null; 
/*  446 */     if (sheetIndex >= this.workBook.getNumberOfSheets())
/*  447 */       for (int i = this.workBook.getNumberOfSheets() - 1; i < sheetIndex; i++)
/*  448 */         this.workBook.createSheet();  
/*  449 */     return this.workBook.getSheetAt(sheetIndex);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addSheet(String sheetTitle, int sheetIndex) {
/*  454 */     if (sheetTitle.length() > 31)
/*      */     {
/*  456 */       sheetTitle = String.valueOf(sheetTitle.substring(0, 26)) + StringUtil.padLeft((new StringBuilder(String.valueOf(sheetIndex))).toString(), '0', 5);
/*      */     }
/*  458 */     if (this.workBook != null) {
/*      */       
/*  460 */       Sheet sheet = this.workBook.createSheet(sheetTitle);
/*  461 */       if (this.workBook.getSheetIndex(sheet) != sheetIndex) {
/*  462 */         this.workBook.setSheetOrder(sheetTitle, sheetIndex);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private Drawing getPatriarch(int sheetIndex) {
/*  468 */     Sheet sheet = getSheet(sheetIndex);
/*  469 */     Drawing hp = sheet.createDrawingPatriarch();
/*  470 */     if (hp == null)
/*  471 */       hp = sheet.createDrawingPatriarch(); 
/*  472 */     return hp;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setColumnWidth(int sheetIndex, int column, int width) {
/*  477 */     Sheet sheet = getSheet(sheetIndex);
/*  478 */     if (sheet != null) {
/*  479 */       sheet.setColumnWidth(column, width);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setGridLinesVisible(int sheetIndex, boolean visible, boolean visibleOnPrint) {
/*  484 */     Sheet sheet = getSheet(sheetIndex);
/*  485 */     sheet.setDisplayGridlines(visible);
/*  486 */     sheet.setPrintGridlines(visibleOnPrint);
/*      */   }
/*      */ 
/*      */   
/*      */   protected Cell getCell(int sheetIndex, int row, int column) {
/*  491 */     return getCell(sheetIndex, row, column, null);
/*      */   }
/*      */ 
/*      */   
/*      */   private CellType getCellType(Integer cellType) {
/*  496 */     switch (cellType.intValue()) {
/*      */       
/*      */       case 0:
/*  499 */         return CellType.NUMERIC;
/*      */       case 1:
/*  501 */         return CellType.STRING;
/*      */       case 2:
/*  503 */         return CellType.FORMULA;
/*      */       case 3:
/*  505 */         return CellType.BLANK;
/*      */       case 4:
/*  507 */         return CellType.BOOLEAN;
/*      */       case 5:
/*  509 */         return CellType.ERROR;
/*      */     } 
/*  511 */     return CellType._NONE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Cell getCell(int sheetIndex, int row, int column, CellType cellType) {
/*  518 */     Sheet sheet = getSheet(sheetIndex);
/*  519 */     if (sheet != null) {
/*      */       
/*      */       try {
/*      */         
/*  523 */         Row rw = sheet.getRow(row);
/*  524 */         if (rw == null)
/*  525 */           rw = sheet.createRow(row); 
/*  526 */         Cell cell = rw.getCell(column);
/*  527 */         if (cell == null && cellType != null)
/*  528 */           return rw.createCell(column, cellType); 
/*  529 */         return cell;
/*      */       }
/*  531 */       catch (Exception e) {
/*      */         
/*  533 */         String message = e.getMessage();
/*  534 */         if (message != null) {
/*      */           
/*  536 */           message = message.toLowerCase(Locale.UK);
/*  537 */           if (message.indexOf("invalid row number") >= 0)
/*  538 */             throw new RuntimeException(message); 
/*      */         } 
/*  540 */         e.printStackTrace();
/*  541 */         return null;
/*      */       } 
/*      */     }
/*      */     
/*  545 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getCellValue(int sheetIndex, int row, int column) {
/*  550 */     Cell cell = getCell(sheetIndex, row, column);
/*  551 */     if (cell == null)
/*  552 */       return ""; 
/*  553 */     if (cell.getCellType() == CellType.STRING)
/*  554 */       return cell.getStringCellValue(); 
/*  555 */     if (cell.getCellType() == CellType.NUMERIC)
/*  556 */       return NumberToTextConverter.toText(cell.getNumericCellValue()); 
/*  557 */     if (cell.getCellType() == CellType.BOOLEAN) {
/*  558 */       return cell.getBooleanCellValue() ? 
/*  559 */         "true" : 
/*  560 */         "false";
/*      */     }
/*  562 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   public Double getCellAsNumber(int sheetIndex, int row, int column) {
/*  567 */     Cell cell = getCell(sheetIndex, row, column);
/*  568 */     if (cell != null) {
/*      */       
/*  570 */       if (cell.getCellType() == CellType.NUMERIC) {
/*  571 */         return Double.valueOf(cell.getNumericCellValue());
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/*  576 */         return Double.valueOf(NumberFormat.getNumberInstance().parse(getCellValue(sheetIndex, row, column)).doubleValue());
/*      */       }
/*  578 */       catch (Exception e) {
/*      */         
/*  580 */         return null;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  585 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public Date getCellAsDate(int sheetIndex, int row, int column) {
/*  590 */     Date result = null;
/*  591 */     Cell cell = getCell(sheetIndex, row, column);
/*  592 */     if (cell != null)
/*      */       
/*      */       try {
/*      */         
/*  596 */         result = cell.getDateCellValue();
/*      */       }
/*  598 */       catch (Exception e) {
/*      */         
/*  600 */         String value = cell.getStringCellValue();
/*  601 */         if (!StringUtil.isEmpty(value)) {
/*      */           
/*  603 */           Calendar calendar = StringUtil.toCalendar(value, (value.length() > 10));
/*  604 */           if (calendar != null) {
/*  605 */             result = calendar.getTime();
/*      */           }
/*      */         } 
/*      */       }  
/*  609 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public Boolean getCellAsBoolean(int sheetIndex, int row, int column) {
/*  614 */     Cell cell = getCell(sheetIndex, row, column);
/*  615 */     if (cell != null) {
/*      */       
/*  617 */       if (cell.getCellType() == CellType.BOOLEAN) {
/*  618 */         return Boolean.valueOf(cell.getBooleanCellValue());
/*      */       }
/*      */       
/*  621 */       String strVal = getCellValue(sheetIndex, row, column);
/*  622 */       if (strVal != null) {
/*      */         
/*  624 */         strVal = strVal.trim();
/*  625 */         if (strVal.equalsIgnoreCase("true") || strVal.equalsIgnoreCase("1"))
/*  626 */           return Boolean.TRUE; 
/*      */       } 
/*  628 */       return Boolean.FALSE;
/*      */     } 
/*      */ 
/*      */     
/*  632 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCellValue(int sheetIndex, int row, int column, String value) {
/*  637 */     setCellValue_(sheetIndex, row, column, value);
/*      */   }
/*      */ 
/*      */   
/*      */   private Cell setCellValue_(int sheetIndex, int row, int column, String value) {
/*  642 */     Cell cell = getCell(sheetIndex, row, column, CellType.STRING);
/*  643 */     cell.setCellValue(value);
/*  644 */     return cell;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCellValue(int sheetIndex, int row, int column, double value) {
/*  649 */     Cell cell = getCell(sheetIndex, row, column, CellType.NUMERIC);
/*  650 */     cell.setCellValue(value);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCellValue(int sheetIndex, int row, int column, double value, int ourFormat, int fractions, boolean forceFractions) {
/*  656 */     Cell cell = getCell(sheetIndex, row, column, CellType.NUMERIC);
/*  657 */     CellStyle newCellStyle = getDefinedDataFormatCellStyle(value, ourFormat, fractions, forceFractions);
/*  658 */     cell.setCellStyle(newCellStyle);
/*  659 */     cell.setCellValue(value);
/*      */   }
/*      */ 
/*      */   
/*      */   private short getDataFormatNumeric(int ourFormat, int fractions, boolean forceFractions) {
/*  664 */     DataFormat df = this.workBook.createDataFormat();
/*  665 */     switch (ourFormat) {
/*      */       
/*      */       case 2:
/*  668 */         return df.getFormat(getFormatStr(fractions, forceFractions, "0", "0.0000", 4));
/*      */       case 3:
/*  670 */         return df.getFormat(getFormatStr(fractions, forceFractions, "#,##0", "#,##0.0000", 4));
/*      */       case 4:
/*  672 */         return df.getFormat(getFormatStr(fractions, forceFractions, "0", "0.00", 2));
/*      */       case 5:
/*  674 */         return df.getFormat(getFormatStr(fractions, forceFractions, "#,##0", "#,##0.00", 2));
/*      */       case 16:
/*  676 */         return df.getFormat(getFormatStr(fractions, forceFractions, "##,##0", "##,##0.00000", 5));
/*      */     } 
/*  678 */     if (!forceFractions) {
/*  679 */       return 1;
/*      */     }
/*  681 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String getFormatStr(int fractions, boolean forceFractions, String format, String defaultFormat, int defaultFractions) {
/*  687 */     if (forceFractions)
/*      */     {
/*  689 */       if (fractions < defaultFractions && fractions > 0) {
/*      */         
/*  691 */         format = String.valueOf(format) + ".";
/*  692 */         format = StringUtil.padRight(format, '0', format.length() + fractions);
/*      */       } else {
/*      */         
/*  695 */         format = defaultFormat;
/*      */       }  } 
/*  697 */     return format;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private CellStyle copyCellStyle(Cell cell, HorizontalAlignment align, Short dataformat, Short bgColor, Short fgColor, FillPatternType pattern, Font font, VerticalAlignment verAlign) {
/*  703 */     CellStyle old = this.workBook.getCellStyleAt((new Short(cell.getCellStyle().getIndex())).intValue());
/*  704 */     CellStyle cs = this.cellstyleCache.getCellStyle(cell.getCellType(), old, align, dataformat, bgColor, fgColor, pattern, font, 
/*  705 */         verAlign);
/*  706 */     cell.setCellStyle(cs);
/*  707 */     return cs;
/*      */   }
/*      */ 
/*      */   
/*      */   private CellStyle getDefinedDataFormatCellStyle(double value, int ourFormat, int fractions, boolean forceFractions) {
/*  712 */     String key = String.valueOf(ourFormat) + "_" + fractions + "_" + forceFractions;
/*  713 */     if (this.m_DataFormatCellStyles.containsKey(key)) {
/*  714 */       return this.m_DataFormatCellStyles.get(key);
/*      */     }
/*      */     
/*  717 */     CellStyle newCellStyle = this.workBook.createCellStyle();
/*  718 */     newCellStyle.setDataFormat(getDataFormatNumeric(ourFormat, fractions, forceFractions));
/*  719 */     this.m_DataFormatCellStyles.put(key, newCellStyle);
/*  720 */     return newCellStyle;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private CellStyle getDefinedCellStyle(Object value) {
/*  727 */     String className = value.getClass().getName();
/*  728 */     if (!JLbsStringUtil.isEmpty(className)) {
/*      */       
/*  730 */       if (this.m_CellStyles.containsKey(className)) {
/*  731 */         return this.m_CellStyles.get(className);
/*      */       }
/*      */       
/*  734 */       if (value instanceof Date || value instanceof Calendar) {
/*      */         
/*  736 */         CellStyle cellStyle = this.workBook.createCellStyle();
/*  737 */         DataFormat df = this.workBook.createDataFormat();
/*  738 */         cellStyle.setDataFormat(df.getFormat("dd/mm/yyyy"));
/*  739 */         this.m_CellStyles.put(className, cellStyle);
/*  740 */         return cellStyle;
/*      */       } 
/*  742 */       if (value instanceof JLbsTimeDuration) {
/*      */         
/*  744 */         CellStyle cellStyle = this.workBook.createCellStyle();
/*  745 */         DataFormat df = this.workBook.createDataFormat();
/*  746 */         cellStyle.setDataFormat(df.getFormat("hh:mm:ss"));
/*  747 */         this.m_CellStyles.put(className, cellStyle);
/*  748 */         return cellStyle;
/*      */       } 
/*  750 */       if (value instanceof Hyperlink) {
/*      */         
/*  752 */         CellStyle hlink_style = this.workBook.createCellStyle();
/*  753 */         Font hlink_font = this.workBook.createFont();
/*  754 */         hlink_font.setUnderline((byte)1);
/*  755 */         hlink_font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
/*  756 */         hlink_style.setFont(hlink_font);
/*  757 */         this.m_CellStyles.put(className, hlink_style);
/*  758 */         return hlink_style;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  763 */     return this.workBook.createCellStyle();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setCellValue(int sheetIndex, int row, int column, Date value) {
/*  773 */     setCellValue(sheetIndex, row, column, value, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCellValue(int sheetIndex, int row, int column, Date value, boolean truncate) {
/*  778 */     Cell cell = getCell(sheetIndex, row, column, CellType.NUMERIC);
/*      */     
/*  780 */     CellStyle cellStyle = getDefinedCellStyle(value);
/*      */ 
/*      */ 
/*      */     
/*  784 */     if (truncate)
/*  785 */       value = JLbsDateUtil.truncateDate(value); 
/*  786 */     cell.setCellValue(value);
/*  787 */     cell.setCellStyle(cellStyle);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setCellValue(int sheetIndex, int row, int column, Calendar value) {
/*  796 */     setCellValue(sheetIndex, row, column, value, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCellValue(int sheetIndex, int row, int column, Calendar value, boolean truncate) {
/*  801 */     Cell cell = getCell(sheetIndex, row, column, CellType.NUMERIC);
/*  802 */     CellStyle cellStyle = getDefinedCellStyle(value);
/*      */ 
/*      */ 
/*      */     
/*  806 */     if (truncate)
/*  807 */       JLbsDateUtil.truncateDate(value); 
/*  808 */     cell.setCellValue(value);
/*  809 */     cell.setCellStyle(cellStyle);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCellValue(int sheetIndex, int row, int column, JLbsTimeDuration value) {
/*  814 */     Cell cell = getCell(sheetIndex, row, column, CellType.NUMERIC);
/*  815 */     CellStyle cellStyle = getDefinedCellStyle(value);
/*      */ 
/*      */     
/*  818 */     Calendar cal = value.toCalendar();
/*      */     
/*  820 */     cell.setCellValue(cal.getTime());
/*  821 */     cell.setCellStyle(cellStyle);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCellComment(int sheetIndex, int row, int column, String comment) {
/*  826 */     setCellComment(sheetIndex, row, column, comment, 8, 10);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCellComment(int sheetIndex, int row, int column, String comment, int width, int height) {
/*  831 */     Cell cell = getCell(sheetIndex, row, column, CellType.STRING);
/*  832 */     Drawing patr = getPatriarch(sheetIndex);
/*  833 */     Comment cmnt = patr.createCellComment(
/*  834 */         (ClientAnchor)new HSSFClientAnchor(0, 0, 0, 0, (short)4, 2, (short)width, height));
/*  835 */     HSSFRichTextString richText = new HSSFRichTextString(comment);
/*  836 */     cmnt.setString((RichTextString)richText);
/*  837 */     cell.setCellComment(cmnt);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCellValue(int sheetIndex, int row, int column, String value, int horizontalAlignment) {
/*  842 */     setCellValue_(sheetIndex, row, column, value, horizontalAlignment);
/*      */   }
/*      */ 
/*      */   
/*      */   private CellStyle setCellValue_(int sheetIndex, int row, int column, String value, int horizontalAlignment) {
/*  847 */     Cell cell = setCellValue_(sheetIndex, row, column, value);
/*  848 */     CellStyle cs = copyCellStyle(cell, getHorizontalAlignment(horizontalAlignment), null, null, null, null, null, null);
/*  849 */     cs.setAlignment(getHorizontalAlignment(horizontalAlignment));
/*  850 */     return cs;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private CellStyle setCellValue_(int sheetIndex, int row, int column, String value, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
/*  856 */     Cell cell = setCellValue_(sheetIndex, row, column, value);
/*  857 */     CellStyle old = this.workBook.getCellStyleAt((new Short(cell.getCellStyle().getIndex())).intValue());
/*  858 */     CellStyle cs = this.cellstyleCache.getCellStyle(cell.getCellType(), old, horizontalAlignment, null, null, null, null, null, 
/*  859 */         verticalAlignment);
/*  860 */     cs.setAlignment(horizontalAlignment);
/*  861 */     return cs;
/*      */   }
/*      */ 
/*      */   
/*      */   public CellStyle setCellValue(int sheetIndex, int row, int column, String value, int horizontalAlignment, int verticalAlignment) {
/*  866 */     CellStyle cs = setCellValue_(sheetIndex, row, column, value, getHorizontalAlignment(horizontalAlignment), 
/*  867 */         getVerticalAlignment(verticalAlignment));
/*  868 */     cs.setVerticalAlignment(getVerticalAlignment(verticalAlignment));
/*  869 */     return cs;
/*      */   }
/*      */ 
/*      */   
/*      */   private HorizontalAlignment getHorizontalAlignment(int alignment) {
/*  874 */     switch (alignment) {
/*      */       
/*      */       case 1:
/*  877 */         return HorizontalAlignment.LEFT;
/*      */       
/*      */       case 2:
/*  880 */         return HorizontalAlignment.RIGHT;
/*      */       
/*      */       case 5:
/*  883 */         return HorizontalAlignment.JUSTIFY;
/*      */       
/*      */       case 4:
/*  886 */         return HorizontalAlignment.FILL;
/*      */       
/*      */       case 3:
/*  889 */         return HorizontalAlignment.CENTER;
/*      */     } 
/*      */ 
/*      */     
/*  893 */     return HorizontalAlignment.GENERAL;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private VerticalAlignment getVerticalAlignment(int alignment) {
/*  899 */     switch (alignment) {
/*      */       
/*      */       case 1:
/*  902 */         return VerticalAlignment.CENTER;
/*      */       
/*      */       case 2:
/*  905 */         return VerticalAlignment.TOP;
/*      */       
/*      */       case 3:
/*  908 */         return VerticalAlignment.JUSTIFY;
/*      */     } 
/*      */ 
/*      */     
/*  912 */     return VerticalAlignment.BOTTOM;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private CellStyle setCellValue(int sheetIndex, int row, int column, String value, int horizontalAlignment, int verticalAlignment, boolean wrap) {
/*  919 */     CellStyle cs = setCellValue(sheetIndex, row, column, value, horizontalAlignment, verticalAlignment);
/*  920 */     cs.setWrapText(wrap);
/*  921 */     return cs;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public CellStyle setCellValue(int sheetIndex, int row, int column, String value, int horizontalAlignment, int verticalAlignment, boolean wrap, boolean bold) {
/*  927 */     CellStyle cs = setCellValue(sheetIndex, row, column, value, horizontalAlignment, verticalAlignment, wrap);
/*  928 */     modifyCellFont(cs, Boolean.valueOf(bold), null, null);
/*  929 */     return cs;
/*      */   }
/*      */ 
/*      */   
/*      */   private void modifyCellFont(CellStyle cs, Boolean bold, Boolean italic, Byte underlineStyle) {
/*  934 */     Font current = this.workBook.getFontAt(cs.getFontIndex());
/*  935 */     Font modified = this.fontCache.getFont(current, bold, italic, underlineStyle);
/*  936 */     if (current != modified) {
/*  937 */       cs.setFont(modified);
/*      */     }
/*      */   }
/*      */   
/*      */   private Font getCellFont(CellStyle cs, Boolean bold, Boolean italic, Byte underlineStyle) {
/*  942 */     Font current = this.workBook.getFontAt(cs.getFontIndex());
/*  943 */     Font modified = this.fontCache.getFont(current, bold, italic, underlineStyle);
/*  944 */     if (current != modified) {
/*  945 */       return modified;
/*      */     }
/*  947 */     return current;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCellHyperlink(int sheetIndex, int row, int column, String visibleText, int destinationSheetIndex, int destinationRow, int destinationColumn) {
/*  953 */     Sheet sheet = getSheet(sheetIndex);
/*  954 */     Sheet destinationSheet = getSheet(destinationSheetIndex);
/*  955 */     if (sheet != null && destinationSheet != null) {
/*      */       
/*      */       try {
/*      */         
/*  959 */         Cell cell = getCell(sheetIndex, row, column, CellType.STRING);
/*  960 */         Hyperlink link = this.workBook.getCreationHelper().createHyperlink(HyperlinkType.DOCUMENT);
/*  961 */         Cell target = getCell(destinationSheetIndex, destinationRow, destinationColumn, CellType.STRING);
/*  962 */         CellReference cr = new CellReference(target);
/*  963 */         link.setAddress("'" + destinationSheet.getSheetName() + "'!" + cr.formatAsString());
/*  964 */         setCellLink(cell, visibleText, link);
/*      */       }
/*  966 */       catch (Exception e) {
/*      */         
/*  968 */         e.printStackTrace();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCellHyperlink(int sheetIndex, String url, int row, int column, String visibleText) {
/*  975 */     Sheet sheet = getSheet(sheetIndex);
/*  976 */     if (sheet != null) {
/*      */       
/*      */       try {
/*      */         
/*  980 */         Cell cell = getCell(sheetIndex, row, column, CellType.STRING);
/*  981 */         Hyperlink link = this.workBook.getCreationHelper().createHyperlink(HyperlinkType.URL);
/*  982 */         link.setAddress(url);
/*  983 */         setCellLink(cell, visibleText, link);
/*      */       }
/*  985 */       catch (Exception e) {
/*      */         
/*  987 */         e.printStackTrace();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void setCellLink(Cell cell, String visibleText, Hyperlink link) {
/*  994 */     cell.setHyperlink(link);
/*  995 */     cell.setCellValue(visibleText);
/*  996 */     CellStyle hlink_style = getDefinedCellStyle(link);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1002 */     cell.setCellStyle(hlink_style);
/*      */   }
/*      */ 
/*      */   
/*      */   private void setCellHorizontalAlignment(Cell cell, int alignment) {
/* 1007 */     if (cell != null) {
/* 1008 */       cell.getCellStyle().setAlignment(getHorizontalAlignment(alignment));
/*      */     }
/*      */   }
/*      */   
/*      */   public void setCellHorizontalAlignment(int sheetIndex, int row, int column, int alignment) {
/* 1013 */     setCellHorizontalAlignment(getCell(sheetIndex, row, column), alignment);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCellFontBoldProp(int sheetIndex, int row, int column, boolean bold) {
/* 1018 */     Cell cell = getCell(sheetIndex, row, column);
/* 1019 */     if (cell != null) {
/* 1020 */       copyCellStyle(cell, null, null, null, null, null, getCellFont(cell.getCellStyle(), Boolean.valueOf(bold), null, null), null);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setCellFontBoldProp(int sheetIndex, int row, int startColumn, int endColumn, boolean bold) {
/* 1025 */     for (int i = startColumn; i <= endColumn; i++) {
/* 1026 */       setCellFontBoldProp(sheetIndex, row, i, bold);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setCellFontItalicProp(int sheetIndex, int row, int column, boolean italic) {
/* 1031 */     Cell cell = getCell(sheetIndex, row, column);
/* 1032 */     if (cell != null) {
/* 1033 */       copyCellStyle(cell, null, null, null, null, null, getCellFont(cell.getCellStyle(), null, Boolean.valueOf(italic), null), null);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setCellFontItalicProp(int sheetIndex, int row, int startColumn, int endColumn, boolean italic) {
/* 1038 */     for (int i = startColumn; i <= endColumn; i++) {
/* 1039 */       setCellFontItalicProp(sheetIndex, row, i, italic);
/*      */     }
/*      */   }
/*      */   
/*      */   private short getColor(int color) {
/* 1044 */     switch (color) {
/*      */       
/*      */       case 7:
/* 1047 */         return HSSFColor.HSSFColorPredefined.GREEN.getIndex();
/*      */       case 8:
/* 1049 */         return HSSFColor.HSSFColorPredefined.YELLOW.getIndex();
/*      */       case 5:
/* 1051 */         return HSSFColor.HSSFColorPredefined.RED.getIndex();
/*      */       case 6:
/* 1053 */         return HSSFColor.HSSFColorPredefined.BLUE.getIndex();
/*      */       case 4:
/* 1055 */         return HSSFColor.HSSFColorPredefined.WHITE.getIndex();
/*      */       case 1:
/* 1057 */         return HSSFColor.HSSFColorPredefined.BLACK.getIndex();
/*      */       case 3:
/* 1059 */         return HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex();
/*      */       case 2:
/* 1061 */         return HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex();
/*      */     } 
/* 1063 */     return HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCellBackgroundColor(int sheetIndex, int row, int column, int color) {
/* 1069 */     Cell cell = getCell(sheetIndex, row, column);
/* 1070 */     if (cell != null) {
/*      */ 
/*      */ 
/*      */       
/* 1074 */       CellStyle cs = copyCellStyle(cell, null, null, Short.valueOf(getColor(color)), null, FillPatternType.SOLID_FOREGROUND, null, null);
/* 1075 */       cs.setFillForegroundColor(getColor(color));
/* 1076 */       cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private BorderStyle getLineStyle(int lineStyle) {
/* 1082 */     switch (lineStyle) {
/*      */       
/*      */       case 9:
/* 1085 */         return BorderStyle.DASH_DOT;
/*      */       case 11:
/* 1087 */         return BorderStyle.DASH_DOT_DOT;
/*      */       case 3:
/* 1089 */         return BorderStyle.DASHED;
/*      */       case 4:
/* 1091 */         return BorderStyle.DOTTED;
/*      */       case 6:
/* 1093 */         return BorderStyle.DOUBLE;
/*      */       case 7:
/* 1095 */         return BorderStyle.HAIR;
/*      */       case 2:
/* 1097 */         return BorderStyle.MEDIUM;
/*      */       case 10:
/* 1099 */         return BorderStyle.MEDIUM_DASH_DOT;
/*      */       case 12:
/* 1101 */         return BorderStyle.MEDIUM_DASH_DOT_DOT;
/*      */       case 8:
/* 1103 */         return BorderStyle.MEDIUM_DASHED;
/*      */       case 0:
/* 1105 */         return BorderStyle.NONE;
/*      */       case 13:
/* 1107 */         return BorderStyle.SLANTED_DASH_DOT;
/*      */       case 5:
/* 1109 */         return BorderStyle.THICK;
/*      */       case 1:
/* 1111 */         return BorderStyle.THIN;
/*      */     } 
/* 1113 */     return BorderStyle.NONE;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCellBorder(int sheetIndex, int row, int column, int border, int lineStyle, int borderColour) {
/* 1119 */     Cell cell = getCell(sheetIndex, row, column);
/* 1120 */     if (cell != null) {
/*      */       
/* 1122 */       CellStyle cs = cell.getCellStyle();
/* 1123 */       BorderStyle ls = getLineStyle(lineStyle);
/* 1124 */       short cl = getColor(borderColour);
/* 1125 */       cs.setBorderTop(ls);
/* 1126 */       cs.setTopBorderColor(cl);
/* 1127 */       cs.setBorderRight(ls);
/* 1128 */       cs.setRightBorderColor(cl);
/* 1129 */       cs.setBorderBottom(ls);
/* 1130 */       cs.setBottomBorderColor(cl);
/* 1131 */       cs.setBorderLeft(ls);
/* 1132 */       cs.setLeftBorderColor(cl);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCellBorder(int sheetIndex, int row, int startColumn, int endColumn, int border, int lineStyle, int borderColour) {
/* 1138 */     for (int i = startColumn; i <= endColumn; i++) {
/* 1139 */       setCellBorder(sheetIndex, row, i, border, lineStyle, borderColour);
/*      */     }
/*      */   }
/*      */   
/*      */   public void groupAndOutlineRows(int sheetIndex, int startRow, int endRow, boolean collapsed) {
/* 1144 */     if (endRow < 0 || startRow < 0 || endRow < startRow)
/*      */       return; 
/* 1146 */     Sheet sheet = getSheet(sheetIndex);
/* 1147 */     if (sheet.getRow(startRow) == null)
/* 1148 */       sheet.createRow(startRow); 
/* 1149 */     if (sheet.getRow(endRow) == null)
/* 1150 */       sheet.createRow(endRow); 
/* 1151 */     sheet.groupRow(startRow, endRow);
/*      */   }
/*      */ 
/*      */   
/*      */   public void mergeCells(int sheetIndex, int row1, int row2, int col1, int col2) {
/* 1156 */     Sheet sheet = getSheet(sheetIndex);
/* 1157 */     sheet.addMergedRegion(new CellRangeAddress(row1, 
/* 1158 */           row2, 
/* 1159 */           col1, 
/* 1160 */           col2));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColumnAutosize(int sheetIndex, int startColumn, int endColumn, boolean autosize) {
/* 1166 */     for (int i = startColumn; i <= endColumn; i++) {
/* 1167 */       setColumnAutosize(sheetIndex, i, autosize);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setColumnAutosize(int sheetIndex, int column, boolean autosize) {
/* 1172 */     Sheet sheet = getSheet(sheetIndex);
/* 1173 */     if (sheet instanceof SXSSFSheet)
/* 1174 */       ((SXSSFSheet)sheet).trackColumnForAutoSizing(column); 
/* 1175 */     sheet.autoSizeColumn(column);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getColumnWidth(int sheetIndex, int column) {
/* 1180 */     Sheet sheet = getSheet(sheetIndex);
/* 1181 */     return sheet.getColumnWidth(column);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCellValue(int sheetIndex, int row, int column, PivotCellValue pcv, boolean bold) {
/* 1188 */     if (pcv.getValue() == null)
/*      */     {
/* 1190 */       pcv.setValue("");
/*      */     }
/*      */     
/* 1193 */     Object value = pcv.getValue();
/*      */     
/* 1195 */     if (value == null || pcv.equals(PivotCellValue.EMPTY())) {
/*      */       
/* 1197 */       setCellValue(sheetIndex, row, column, "");
/*      */     
/*      */     }
/* 1200 */     else if (value instanceof Number) {
/*      */       
/* 1202 */       BigDecimal b = new BigDecimal(((Number)value).doubleValue());
/* 1203 */       setCellValue(sheetIndex, row, column, b.doubleValue());
/*      */     
/*      */     }
/* 1206 */     else if (value instanceof Date || value instanceof Calendar) {
/*      */       
/* 1208 */       String formatted = PivotTableInfo.getFormatter().format(value);
/* 1209 */       setCellValue(sheetIndex, row, column, formatted);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1214 */       setCellValue(sheetIndex, row, column, value.toString());
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 1219 */       if (bold)
/*      */       {
/* 1221 */         setCellFontBoldProp(sheetIndex, row, column, bold);
/* 1222 */         setCellBackgroundColor(sheetIndex, row, column, 2);
/*      */       }
/*      */     
/* 1225 */     } catch (Exception e) {
/*      */       
/* 1227 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void main(String[] args) {
/* 1233 */     double d = 40545.55D;
/* 1234 */     System.out.println(String.valueOf(d));
/* 1235 */     WritableExcelAPI excelAPI = new WritableExcelAPI();
/* 1236 */     excelAPI.openExcelFile("D:\\test.xls", true);
/* 1237 */     String val = excelAPI.getCellValue(0, 0, 0);
/* 1238 */     System.out.println(val);
/*      */     int i;
/* 1240 */     for (i = 0; i < 6; i++)
/* 1241 */       excelAPI.setCellValue(0, i, 1, "ROW " + i); 
/* 1242 */     excelAPI.setCellFontBoldProp(0, 2, 1, true);
/* 1243 */     excelAPI.setCellBackgroundColor(0, 2, 1, 6);
/* 1244 */     excelAPI.setCellBackgroundColor(0, 0, 1, 5);
/*      */     
/* 1246 */     excelAPI.groupAndOutlineRows(0, 30, 70, true);
/* 1247 */     excelAPI.groupAndOutlineRows(0, 2, 25, true);
/*      */ 
/*      */     
/* 1250 */     excelAPI.setCellFontBoldProp(0, 5, 1, true);
/* 1251 */     excelAPI.setCellFontItalicProp(0, 5, 1, true);
/* 1252 */     excelAPI.setCellHyperlink(0, 0, 3, "A1234567890123456789012345678901234567890", 1, 2, 2);
/* 1253 */     excelAPI.setCellHyperlink(0, 0, 3, "A1234567890123456789012345678901234567890", 1, 2, 2);
/*      */     
/* 1255 */     for (i = 0; i < 15000; i++) {
/*      */ 
/*      */       
/* 1258 */       excelAPI.setCellValue(1, i, 0, "Değer : " + i, (i % 3 == 0) ? 
/* 1259 */           2 : 
/* 1260 */           1);
/* 1261 */       excelAPI.setCellFontBoldProp(1, i, 0, (i % 5 == 0));
/* 1262 */       excelAPI.setCellFontItalicProp(1, i, 0, (i % 4 == 0));
/* 1263 */       excelAPI.setCellBackgroundColor(1, i, 0, (i % 25 == 0) ? 
/* 1264 */           5 : (
/* 1265 */           (i % 4 == 0) ? 
/* 1266 */           6 : 
/* 1267 */           7));
/*      */     } 
/*      */ 
/*      */     
/* 1271 */     excelAPI.closeExcelFile(true, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPassword(String password) {
/* 1276 */     this.password = password;
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\WritableExcelAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */