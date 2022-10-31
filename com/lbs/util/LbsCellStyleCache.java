/*    */ package com.lbs.util;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.poi.ss.usermodel.CellStyle;
/*    */ import org.apache.poi.ss.usermodel.CellType;
/*    */ import org.apache.poi.ss.usermodel.FillPatternType;
/*    */ import org.apache.poi.ss.usermodel.Font;
/*    */ import org.apache.poi.ss.usermodel.HorizontalAlignment;
/*    */ import org.apache.poi.ss.usermodel.VerticalAlignment;
/*    */ import org.apache.poi.ss.usermodel.Workbook;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsCellStyleCache
/*    */ {
/* 17 */   private Map<String, Short> cache = new HashMap<>();
/*    */   
/*    */   private Workbook workBook;
/*    */   
/*    */   public LbsCellStyleCache(Workbook workBook) {
/* 22 */     this.workBook = workBook;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CellStyle getCellStyle(CellType cellType, CellStyle old, HorizontalAlignment align, Short dataformat, Short bgColor, Short fgColor, FillPatternType pattern, Font font, VerticalAlignment verAlign) {
/* 28 */     String key = generateStyleKey(cellType, old, align, dataformat, bgColor, fgColor, pattern, font, verAlign);
/* 29 */     Short csIdx = this.cache.get(key);
/* 30 */     if (csIdx == null) {
/*    */       
/* 32 */       CellStyle cs = this.workBook.createCellStyle();
/* 33 */       copyCellStyle(old, cs);
/* 34 */       if (align != null)
/* 35 */         cs.setAlignment(align); 
/* 36 */       if (dataformat != null)
/* 37 */         cs.setDataFormat(dataformat.shortValue()); 
/* 38 */       if (bgColor != null)
/* 39 */         cs.setFillBackgroundColor(bgColor.shortValue()); 
/* 40 */       if (fgColor != null)
/* 41 */         cs.setFillForegroundColor(fgColor.shortValue()); 
/* 42 */       if (pattern != null)
/* 43 */         cs.setFillPattern(pattern); 
/* 44 */       if (font != null)
/* 45 */         cs.setFont(font); 
/* 46 */       if (verAlign != null) {
/* 47 */         cs.setVerticalAlignment(verAlign);
/*    */       }
/* 49 */       this.cache.put(key, Short.valueOf(cs.getIndex()));
/* 50 */       return cs;
/*    */     } 
/* 52 */     return this.workBook.getCellStyleAt((new Short(csIdx.shortValue())).intValue());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String generateStyleKey(CellType type, CellStyle old, HorizontalAlignment align, Short dataformat, Short bgColor, Short fgColor, FillPatternType pattern, Font font, VerticalAlignment verAlign) {
/* 59 */     StringBuilder k = new StringBuilder();
/* 60 */     k.append(type);
/* 61 */     k.append(",").append(old.getAlignment());
/* 62 */     k.append(",").append(old.getBorderBottom());
/* 63 */     k.append(",").append(old.getBorderLeft());
/* 64 */     k.append(",").append(old.getVerticalAlignment());
/* 65 */     k.append(",").append((align != null) ? 
/* 66 */         align : 
/* 67 */         old.getAlignment());
/* 68 */     k.append(",").append((dataformat != null) ? 
/* 69 */         dataformat.shortValue() : 
/* 70 */         old.getDataFormat());
/* 71 */     k.append(",").append((bgColor != null) ? 
/* 72 */         bgColor.shortValue() : 
/* 73 */         old.getFillBackgroundColor());
/* 74 */     k.append(",").append((pattern != null) ? 
/* 75 */         pattern : 
/* 76 */         old.getFillPattern());
/* 77 */     k.append(",").append((font != null) ? 
/* 78 */         font.getIndexAsInt() : 
/* 79 */         old.getFontIndexAsInt());
/* 80 */     k.append(",").append((fgColor != null) ? 
/* 81 */         fgColor.shortValue() : 
/* 82 */         old.getFillForegroundColor());
/* 83 */     k.append(",").append((verAlign != null) ? 
/* 84 */         verAlign : 
/* 85 */         old.getVerticalAlignment());
/*    */     
/* 87 */     return k.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   private void copyCellStyle(CellStyle old, CellStyle cs) {
/* 92 */     if (old != null && cs != null)
/* 93 */       cs.cloneStyleFrom(old); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\LbsCellStyleCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */