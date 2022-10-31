/*     */ package com.lbs.globalization;
/*     */ 
/*     */ import java.awt.Font;
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
/*     */ public class JLbsCultureInfoZHCN
/*     */   extends JLbsDefaultCultureInfo
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  18 */   public static final String[] MONTHNAMES = new String[] { "無效", "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月" };
/*     */   
/*  20 */   public static final String[] DAYNAMES = new String[] { "無效", "星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
/*     */   
/*  22 */   public static final String[] SHORTDAYNAMES = new String[] { "無效", "天", "一", "二", "三", "四", "五", "六" };
/*     */   
/*  24 */   protected static final String[] NUMBERNAMES = new String[] { "[0] 零", "[1] 一", "[2] 二", "[3] 三", "[4] 四", "[5] 五", "[6] 六", "[7] 七", "[8] 八", 
/*  25 */       "[9] 九", "[10] 十", "[20] 二十", "[30] 三十", "[40] 四十", "[50] 五十", "[60] 六十", "[70] 七十", "[80] 八十", "[90] 九十", "[100] 百" };
/*     */   
/*  27 */   protected static final String[] GROUPNAMES = new String[] { "[0] ~", "[1] ~|千|", "[2] ~|百萬|", "[3] ~|十億|", "[4] ~|兆|", "[11001] 千|" };
/*     */   
/*  29 */   protected static final String[] BASECOMBINATIONS = new String[] { "[1] ~|百", "[2] ~|#", "[3] ~|#" };
/*     */   
/*  31 */   public static String ms_FontName = "MS Gothic";
/*     */   
/*  33 */   public static int ms_FontStyle = 0;
/*     */   
/*  35 */   public static int ms_FontSize = 10;
/*     */ 
/*     */   
/*     */   public String getDebitText() {
/*  39 */     return "(債)";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCreditText() {
/*  44 */     return "(應)";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getYes() {
/*  49 */     return "是";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNo() {
/*  54 */     return "沒有";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOK() {
/*  59 */     return "行";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCancel() {
/*  64 */     return "取消";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSave() {
/*  69 */     return "保存";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTimeZone() {
/*  74 */     return "時區";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguagePrefix() {
/*  79 */     return ILbsCultureConstants.LANGUAGEPREFIXES[17];
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] getFormatStrings() {
/*  84 */     return DEFAULTFORMATS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMonthFullName(int iMonth) {
/*  92 */     if (iMonth > 0 && iMonth <= 12)
/*  93 */       return MONTHNAMES[iMonth]; 
/*  94 */     return MONTHNAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayShortName(int iDay) {
/*  99 */     if (iDay > 0 && iDay <= 7)
/* 100 */       return SHORTDAYNAMES[iDay]; 
/* 101 */     return "XxX";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDayFullName(int iDay) {
/* 109 */     if (iDay > 0 && iDay <= 7)
/* 110 */       return DAYNAMES[iDay]; 
/* 111 */     return DAYNAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getNumberNames() {
/* 116 */     return NUMBERNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getGroupNames() {
/* 121 */     return GROUPNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getBaseCombinations() {
/* 126 */     return BASECOMBINATIONS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCultureResStr(int cultureResID) {
/* 131 */     switch (cultureResID) {
/*     */       
/*     */       case 101:
/* 134 */         return "中國的";
/*     */       case 102:
/* 136 */         return "用戶名";
/*     */       case 103:
/* 138 */         return "密碼";
/*     */       case 119:
/* 140 */         return "以不同的用戶登錄";
/*     */       case 120:
/* 142 */         return "先进";
/*     */       case 104:
/* 144 */         return "公司";
/*     */       case 105:
/* 146 */         return "歷時";
/*     */       case 106:
/* 148 */         return "語";
/*     */       case 107:
/* 150 */         return "&行";
/*     */       case 108:
/* 152 */         return "&取消";
/*     */       case 109:
/* 154 */         return "本公司及期";
/*     */       case 110:
/* 156 */         return "副";
/*     */       case 111:
/* 158 */         return "Caps Lock鍵。";
/*     */       
/*     */       case 1000:
/* 161 */         return "錯誤";
/*     */       case 1001:
/* 163 */         return "無效的用戶名和/或密碼。請與您的系統管理員聯繫。";
/*     */       case 1002:
/* 165 */         return "您沒有管理權限。請與您的系統管理員聯繫。";
/*     */       case 1003:
/* 167 */         return "公司表應該升級。請與您的系統管理員聯繫。";
/*     */       case 1004:
/* 169 */         return "你不能使用這家公司。請與您的系統管理員聯繫。";
/*     */       case 1005:
/* 171 */         return "找不到指定的期間。請與您的系統管理員聯繫。";
/*     */       case 1006:
/* 173 */         return "活動期間可能不會被發現。請與您的系統管理員聯繫。";
/*     */       case 1007:
/* 175 */         return "系統表和應用程序的不同版本。請與您的系統管理員聯繫。";
/*     */       case 1008:
/* 177 */         return "找不到指定的公司。請與您的系統管理員聯繫。";
/*     */       case 1009:
/* 179 */         return "系統表可能不會被發現。請與您的系統管理員聯繫。";
/*     */       case 1010:
/* 181 */         return "用戶無法登錄，請與您的系統管理員聯繫。";
/*     */       case 1011:
/* 183 */         return "用戶帳戶已被封鎖。請與您的系統管理員聯繫。";
/*     */       case 1012:
/* 185 */         return "無效的IP。請與您的系統管理員聯繫。";
/*     */       case 1013:
/* 187 */         return "您的密碼已過期。請與您的系統管理員聯繫。";
/*     */       case 1014:
/* 189 */         return "數據庫連接錯誤。檢查數據庫設置。";
/*     */       case 1015:
/* 191 */         return "期表應該升級。請與您的系統管理員聯繫。";
/*     */       case 1036:
/* 193 */         return "期間觸發器應該升級。請與您的系統管理員聯繫。";
/*     */       case 1035:
/* 195 */         return "公司觸發器應該升級。請與您的系統管理員聯繫。";
/*     */       case 1016:
/* 197 */         return "請選擇公司，你要工作！";
/*     */       case 1017:
/* 199 */         return "可能是老版本的系統表。請更新，然後再試一次。";
/*     */       case 1018:
/* 201 */         return "登錄失敗";
/*     */       case 1019:
/* 203 */         return "渠道商是無法創建一個通道";
/*     */       case 1020:
/* 205 */         return "無法連接到";
/*     */       case 1021:
/* 207 */         return "是不是一個有效的地址登錄的URI: ";
/*     */       case 1022:
/* 209 */         return "內部異常:\n";
/*     */       case 1023:
/* 211 */         return "遠程異常:\n";
/*     */       case 1024:
/* 213 */         return "引發異常的代碼";
/*     */       case 1025:
/* 215 */         return "您沒有安裝有效的許可證。\n請安裝有效的許可證！";
/*     */       case 1026:
/* 217 */         return "您的許可不包含該應用程序的語言！";
/*     */       case 1027:
/* 219 */         return "您的許可證已過期！";
/*     */       case 1028:
/* 221 */         return "操作系統不支持您的許可！支持的操作系統，請參閱您的許可協議！";
/*     */       case 1029:
/* 223 */         return "您的許可不包含這個數據庫管理系統！";
/*     */       case 1030:
/* 225 */         return "無法創建新的會話！用戶無法登錄這個節點！";
/*     */       case 1031:
/* 227 */         return "定制表應該升級。請與您的系統管理員聯繫。";
/*     */       case 1032:
/* 229 */         return "報表查看器已在運行。請使用報表查看器打開“按鈕來查看一個又一個的報告。";
/*     */       case 1033:
/* 231 */         return "客戶端兼容性錯誤。請與您的系統管理員聯繫。";
/*     */       case 1034:
/* 233 */         return "定制包是無效的。請與您的系統管理員聯繫。";
/*     */       case 112:
/* 235 */         return "忘記密碼？";
/*     */       case 113:
/* 237 */         return "電子郵件地址";
/*     */       case 114:
/* 239 */         return "電子郵件地址無效。";
/*     */       case 115:
/* 241 */         return "您的登錄信息已發送到您的電子郵件地址。";
/*     */       case 116:
/* 243 */         return "沒有找到您所指定的郵件地址，我們的系統記錄。 \n請檢查您所指定的電子郵件地址。";
/*     */       case 117:
/* 245 */         return "發生意外的異常。請與您的系統管理員聯繫。";
/*     */       case 118:
/* 247 */         return "信息";
/*     */       case 1037:
/* 249 */         return "無效不同的用戶名和/或密碼。請與您的系統管理員聯繫。";
/*     */       case 1040:
/* 251 */         return "安全问题的回答不正确。请再试一次。";
/*     */     } 
/* 253 */     return super.getCultureResStr(cultureResID);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguageName(int language) {
/* 258 */     switch (language) {
/*     */       
/*     */       case 34:
/* 261 */         return "阿爾巴尼亞人";
/*     */       case 32:
/* 263 */         return "阿拉伯語 (JO)";
/*     */       case 24:
/* 265 */         return "阿拉伯語 (SA)";
/*     */       case 38:
/* 267 */         return "阿拉伯語 (EG)";
/*     */       case 29:
/* 269 */         return "阿塞拜疆";
/*     */       case 28:
/* 271 */         return "保加利亞語";
/*     */       case 17:
/* 273 */         return "中文";
/*     */       case 27:
/* 275 */         return "克羅地亞語";
/*     */       case 9:
/* 277 */         return "捷克的";
/*     */       case 10:
/* 279 */         return "丹麥文";
/*     */       case 11:
/* 281 */         return "荷蘭人";
/*     */       case 2:
/* 283 */         return "英語";
/*     */       case 40:
/* 285 */         return "英語 (IN)";
/*     */       case 12:
/* 287 */         return "愛沙尼亞語";
/*     */       case 30:
/* 289 */         return "波斯語";
/*     */       case 13:
/* 291 */         return "芬蘭";
/*     */       case 3:
/* 293 */         return "法式";
/*     */       case 4:
/* 295 */         return "德語";
/*     */       case 37:
/* 297 */         return "格魯吉亞";
/*     */       case 23:
/* 299 */         return "希臘語";
/*     */       case 14:
/* 301 */         return "希伯來文";
/*     */       case 15:
/* 303 */         return "匈牙利";
/*     */       case 16:
/* 305 */         return "冰島";
/*     */       case 6:
/* 307 */         return "意大利人";
/*     */       case 18:
/* 309 */         return "日本人";
/*     */       case 19:
/* 311 */         return "朝鮮人";
/*     */       case 20:
/* 313 */         return "挪威人";
/*     */       case 8:
/* 315 */         return "波蘭語";
/*     */       case 21:
/* 317 */         return "葡萄牙語";
/*     */       case 25:
/* 319 */         return "羅馬尼亞";
/*     */       case 5:
/* 321 */         return "俄語";
/*     */       case 26:
/* 323 */         return "斯卡洛文人";
/*     */       case 7:
/* 325 */         return "西班牙人";
/*     */       case 22:
/* 327 */         return "瑞典";
/*     */       case 33:
/* 329 */         return "突厥";
/*     */       case 31:
/* 331 */         return "土耳其語 (RTL)";
/*     */       case 1:
/* 333 */         return "土耳其語";
/*     */     } 
/* 335 */     return "未知軟件語言";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrencyIdx() {
/* 340 */     return 25;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Font getFont() {
/* 346 */     return new Font(ms_FontName, ms_FontStyle, ms_FontSize);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCultureInfoZHCN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */