/*    */ package com.lbs.controls.tablereport;
/*    */ 
/*    */ import java.awt.datatransfer.DataFlavor;
/*    */ 
/*    */ public class TableReportConstants {
/*    */   public enum SortDirection {
/*  7 */     ASC,
/*  8 */     DESC,
/*  9 */     UNDEFINED;
/*    */   }
/*    */   
/* 12 */   static final DataFlavor headerInfoFlavor = new DataFlavor(TableReportHeaderInfo.class, "Header Info");
/*    */   
/* 14 */   public static String[] STRINGS2 = new String[] { 
/* 15 */       "En Az Bir Toplu İşlem Alanı Seçilmelidir!!!", 
/* 16 */       "Tablo Görünüm", 
/* 17 */       "Tümü...", 
/* 18 */       "Seçiniz...", 
/* 19 */       "Alanlar", 
/* 20 */       "Sayısal Değerler", 
/* 21 */       "Göster", 
/* 22 */       "Grafik Tipi", 
/* 23 */       "Alan Seçilmedi!!!", 
/* 24 */       "Uyarı!!!", 
/* 25 */       "En Az Bir Değer Seçmelisiniz!!!", 
/* 26 */       "Gösterilecek Veri Bulunamadı!!!", 
/* 27 */       "En Az Bir Kayıt Seçmelisiniz", 
/* 28 */       "Excel'e Aktar", 
/* 29 */       "Grafik" };
/*    */ 
/*    */   
/* 32 */   public static String[] STRINGS3 = new String[] { 
/* 33 */       "Sıralamayı İptal Et", 
/* 34 */       "Görünümü Kaydet", 
/* 35 */       "Tümünü Genişlet", 
/* 36 */       "Tümünü Daralt", 
/* 37 */       "Görünüm Kaydedildi...", 
/* 38 */       "Bilgi...", 
/* 39 */       "Görünüm Kaydedilemedi!!!", 
/* 40 */       "Hata!!!", 
/* 41 */       "Hiçbiri", 
/* 42 */       "Görünümü Bu Kullanıcı İçin Kaydet", 
/* 43 */       "Görünümü Herkes İçin Kaydet" };
/*    */ 
/*    */   
/* 46 */   public static String[] STRINGS4 = new String[] {
/* 47 */       "Satır Renklendirme"
/*    */     };
/*    */   
/* 50 */   public static String[] STRINGS5 = new String[] {
/* 51 */       "Kolon", 
/* 52 */       "Bar", 
/* 53 */       "Çizgi", 
/* 54 */       "Alan", 
/* 55 */       "Pasta"
/*    */     };
/*    */   
/*    */   enum ChartType {
/* 59 */     COLUMN_CHART(TableReportConstants.STRINGS5[0]),
/* 60 */     BAR_CHART(TableReportConstants.STRINGS5[1]),
/* 61 */     LINE_CHART(TableReportConstants.STRINGS5[2]),
/* 62 */     AREA_CHART(TableReportConstants.STRINGS5[3]),
/* 63 */     PIE_CHART(TableReportConstants.STRINGS5[4]);
/*    */     
/*    */     private String title;
/*    */     
/*    */     ChartType(String title) {
/* 68 */       this.title = title;
/*    */     }
/*    */     
/*    */     public String getTitle() {
/* 72 */       return this.title;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\tablereport\TableReportConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */