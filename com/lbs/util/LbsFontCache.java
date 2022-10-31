/*    */ package com.lbs.util;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.poi.ss.usermodel.Font;
/*    */ import org.apache.poi.ss.usermodel.Workbook;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsFontCache
/*    */ {
/* 12 */   private Map<String, Short> cache = new HashMap<>();
/*    */   private Workbook workBook;
/*    */   
/*    */   public LbsFontCache(Workbook workBook) {
/* 16 */     this.workBook = workBook;
/*    */   }
/*    */   
/*    */   public Font getFont(Font old, Boolean bold, Boolean italic, Byte underlineStyle) {
/* 20 */     String key = generateFontKey(old, bold, italic, underlineStyle);
/* 21 */     Short fontIdx = this.cache.get(key);
/* 22 */     if (fontIdx == null) {
/* 23 */       Font font = this.workBook.createFont();
/* 24 */       copyFont(old, font);
/* 25 */       if (bold != null)
/* 26 */         font.setBold(bold.booleanValue()); 
/* 27 */       if (italic != null)
/* 28 */         font.setItalic(italic.booleanValue()); 
/* 29 */       if (underlineStyle != null)
/* 30 */         font.setUnderline(underlineStyle.byteValue()); 
/* 31 */       this.cache.put(key, Short.valueOf(font.getIndex()));
/* 32 */       return font;
/*    */     } 
/* 34 */     return this.workBook.getFontAt(fontIdx.shortValue());
/*    */   }
/*    */   
/*    */   private String generateFontKey(Font old, Boolean bold, Boolean italic, Byte underlineStyle) {
/* 38 */     StringBuilder k = new StringBuilder(old.getFontName());
/* 39 */     k.append(",").append(old.getCharSet());
/* 40 */     k.append(",").append(old.getFontHeight());
/* 41 */     k.append(",").append(old.getTypeOffset());
/* 42 */     k.append(",").append((bold != null) ? bold.booleanValue() : old.getBold());
/* 43 */     k.append(",").append((italic != null) ? italic.booleanValue() : old.getItalic());
/* 44 */     k.append(",").append((underlineStyle != null) ? underlineStyle.byteValue() : old.getUnderline());
/* 45 */     k.append(",").append(old.getStrikeout());
/* 46 */     k.append(",").append(old.getColor());
/* 47 */     return k.toString();
/*    */   }
/*    */   
/*    */   private void copyFont(Font old, Font font) {
/* 51 */     font.setFontName(old.getFontName());
/* 52 */     font.setFontHeight(old.getFontHeight());
/* 53 */     font.setCharSet(old.getCharSet());
/* 54 */     font.setTypeOffset(old.getTypeOffset());
/* 55 */     font.setColor(old.getColor());
/* 56 */     font.setUnderline(old.getUnderline());
/* 57 */     font.setStrikeout(old.getStrikeout());
/* 58 */     font.setItalic(old.getItalic());
/* 59 */     font.setBold(old.getBold());
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\LbsFontCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */