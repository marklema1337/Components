/*     */ package com.lbs.globalization;
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
/*     */ public class JLbsCultureInfoVIVN
/*     */   extends JLbsDefaultCultureInfo
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  16 */   public static final String[] MONTHNAMES = new String[] { "Không Hợp Lệ", "Tháng Một", "Tháng Hai", "Biên Giới", "Tháng Tư", "May", 
/*  17 */       "Tháng Sáu", "Tháng Bảy", "Oai Phong", "Tháng Chín", "Tháng Mười", "Tháng Mười Một", "Tháng Mười Hai" };
/*     */   
/*  19 */   public static final String[] DAYNAMES = new String[] { "Không Hợp Lệ", "Chủ Nhật", "Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", 
/*  20 */       "Thứ Bảy" };
/*     */   
/*  22 */   public static final String[] SHORTDAYNAMES = new String[] { "Khl", "Nhậ", "Hai", "Ba", "Tư", "Năm", "Sáu", "Bảy" };
/*     */   
/*  24 */   protected static final String[] NUMBERNAMES = new String[] { "[0] Không", "[1] Một", "[2] Hai", "[3] Ba", "[4] Bốn", "[5] Năm", "[6] Sáu", 
/*  25 */       "[7] Bảy", "[8] Tám", "[9] Nine", "[10] Mười", "[20] Hai Mươi", "[30] Ba Mươi", "[40] Bốn Mươi", "[50] Năm Mươi", 
/*  26 */       "[60] Sáu Mươi", "[70] Bảy Chục", "[80] Tám Mươi", "[90] Chín Mươi", "[100] Mặt" };
/*     */   
/*  28 */   protected static final String[] GROUPNAMES = new String[] { "[0] ~", "[1] ~|Ngàn|", "[2] ~|Triệu|", "[3] ~|Tỷ|", "[4] ~|Nghìn Tỷ|", 
/*  29 */       "[11001] Ngàn|" };
/*     */   
/*  31 */   protected static final String[] BASECOMBINATIONS = new String[] { "[1] ~|Mặt", "[2] ~|#", "[3] ~|#" };
/*     */ 
/*     */   
/*     */   public String getLanguagePrefix() {
/*  35 */     return ILbsCultureConstants.LANGUAGEPREFIXES[36];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDebitText() {
/*  40 */     return "(N)";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCreditText() {
/*  45 */     return "(P)";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEra(int iEra) {
/*  50 */     return (iEra > 0) ? 
/*  51 */       "BC" : 
/*  52 */       "AD";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] getFormatStrings() {
/*  57 */     return DEFAULTFORMATS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMonthFullName(int iMonth) {
/*  65 */     if (iMonth > 0 && iMonth <= 12)
/*  66 */       return MONTHNAMES[iMonth]; 
/*  67 */     return MONTHNAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayShortName(int iDay) {
/*  72 */     if (iDay > 0 && iDay <= 7)
/*  73 */       return SHORTDAYNAMES[iDay]; 
/*  74 */     return "XxX";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDayFullName(int iDay) {
/*  82 */     if (iDay > 0 && iDay <= 7)
/*  83 */       return DAYNAMES[iDay]; 
/*  84 */     return DAYNAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getNumberNames() {
/*  89 */     return NUMBERNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getGroupNames() {
/*  94 */     return GROUPNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getBaseCombinations() {
/*  99 */     return BASECOMBINATIONS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getYes() {
/* 104 */     return "Vâng";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNo() {
/* 109 */     return "Không";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOK() {
/* 114 */     return "OK";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCancel() {
/* 119 */     return "Hủy Bỏ";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSave() {
/* 124 */     return "Lưu";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTimeZone() {
/* 129 */     return "Múi Giờ";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCultureResStr(int cultureResID) {
/* 134 */     switch (cultureResID) {
/*     */       
/*     */       case 101:
/* 137 */         return "Việt";
/*     */       case 102:
/* 139 */         return "Tên đăng nhập";
/*     */       case 103:
/* 141 */         return "Mật khẩu";
/*     */       case 119:
/* 143 */         return "Đăng nhập như là người sử dụng khác nhau";
/*     */       case 120:
/* 145 */         return "Tiên tiến";
/*     */       case 104:
/* 147 */         return "Công ty";
/*     */       case 105:
/* 149 */         return "Thời gian";
/*     */       case 106:
/* 151 */         return "Ngôn ngư";
/*     */       case 107:
/* 153 */         return "&OK";
/*     */       case 108:
/* 155 */         return "&Hủy bỏ";
/*     */       case 109:
/* 157 */         return "Công ty thời";
/*     */       case 110:
/* 159 */         return "Công ty con";
/*     */       case 111:
/* 161 */         return "Caps Lock đang bật.";
/*     */       
/*     */       case 1000:
/* 164 */         return "Lôi";
/*     */       case 1001:
/* 166 */         return "Tên người dùng và / hoặc mật khẩu. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1002:
/* 168 */         return "Bạn không có quyền quản trị. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1003:
/* 170 */         return "Bảng công ty cần được nâng cấp. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1004:
/* 172 */         return "Bạn không được phép sử dụng công ty này. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1005:
/* 174 */         return "Thời hạn quy định không thể được tìm thấy. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1006:
/* 176 */         return "Thời gian hoạt động không thể được tìm thấy. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1007:
/* 178 */         return "Các bảng hệ thống và ứng dụng của phiên bản khác nhau. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1008:
/* 180 */         return "Các công ty được chỉ định không thể tìm được. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1009:
/* 182 */         return "Các bảng hệ thống không thể tìm được. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1010:
/* 184 */         return "Người dùng không thể đăng nhập. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1011:
/* 186 */         return "Tài khoản người dùng đã bị chặn. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1012:
/* 188 */         return "IP không hợp lệ không. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1013:
/* 190 */         return "Mật khẩu của bạn đã hết hạn. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1014:
/* 192 */         return "Kết nối cơ sở dữ liệu lỗi. Kiểm tra các thiết lập cơ sở dữ liệu.";
/*     */       case 1015:
/* 194 */         return "Bảng giai đoạn này nên được nâng cấp. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1036:
/* 196 */         return "Gây nên giai đoạn này nên được nâng cấp. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1035:
/* 198 */         return "Gây nên Công ty cần phải được nâng cấp. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1016:
/* 200 */         return "Vui lòng chọn công ty bạn muốn làm việc!";
/*     */       case 1017:
/* 202 */         return "Phiên bản của các bảng hệ thống có thể là cũ. Vui lòng cập nhật và thử lại.";
/*     */       case 1018:
/* 204 */         return "Đăng nhập thất bại";
/*     */       case 1019:
/* 206 */         return "Các nhà cung cấp kênh không thể để tạo ra một kênh";
/*     */       case 1020:
/* 208 */         return "Không thể để kết nối với";
/*     */       case 1021:
/* 210 */         return "Địa chỉ không phải là một URI đăng nhập hợp lệ: ";
/*     */       case 1022:
/* 212 */         return "Nội ngoại lệ:\n";
/*     */       case 1023:
/* 214 */         return "Từ xa ngoại lệ:\n";
/*     */       case 1024:
/* 216 */         return "Ngoại lệ lớn lên với mã";
/*     */       case 1025:
/* 218 */         return "Bạn không cần phải cài đặt một giấy phép hợp lệ.\nPlease install a valid licence!";
/*     */       case 1026:
/* 220 */         return "Giấy phép của bạn không chứa ngôn ngữ ứng dụng này!";
/*     */       case 1027:
/* 222 */         return "Giấy phép của bạn đã hết hạn!";
/*     */       case 1028:
/* 224 */         return "Hệ điều hành không được hỗ trợ giấy phép của bạn! Xin vui lòng tham khảo thoả thuận cấp phép cho các hệ thống điều hành được hỗ trợ!";
/*     */       case 1029:
/* 226 */         return "Giấy phép của bạn không chứa hệ thống quản lý cơ sở dữ liệu!";
/*     */       case 1030:
/* 228 */         return "Không thể để tạo ra phiên làm việc mới! Người dùng không thể đăng nhập node này!";
/*     */       case 1031:
/* 230 */         return "Bảng tùy biến cần được nâng cấp. Vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1032:
/* 232 */         return "Báo cáo người xem đang chạy.Vui lòng sử dụng nút mở cửa vào xem báo cáo để xem một báo cáo khác.";
/*     */       case 1033:
/* 234 */         return "Khách hàng khả năng tương thích lỗi. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1034:
/* 236 */         return "Các gói phần mềm tùy chỉnh không hợp lệ. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 112:
/* 238 */         return "Quên mật khẩu của bạn?";
/*     */       case 113:
/* 240 */         return "Địa chỉ E-Mail";
/*     */       case 114:
/* 242 */         return "Không hợp lệ địa chỉ e-mail.";
/*     */       case 115:
/* 244 */         return "Thông tin đăng nhập của bạn đã được gửi đến địa chỉ e-mail của bạn.";
/*     */       case 116:
/* 246 */         return "Địa chỉ mail mà bạn chỉ định không được tìm thấy trên hệ thống hồ sơ của chúng tôi. \nHãy kiểm tra địa chỉ mail mà bạn chỉ định.";
/*     */       case 117:
/* 248 */         return "Ngoại lệ bất ngờ đã xảy ra. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 118:
/* 250 */         return "Thông tin";
/*     */       case 1037:
/* 252 */         return "Không hợp lệ KHÁC tên người dùng và / hoặc mật khẩu. Xin vui lòng liên hệ với quản trị hệ thống của bạn.";
/*     */       case 1040:
/* 254 */         return "Câu hỏi bảo mật được trả lời không chính xác. Vui lòng thử lại.";
/*     */     } 
/* 256 */     return super.getCultureResStr(cultureResID);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguageName(int language) {
/* 261 */     switch (language) {
/*     */       
/*     */       case 34:
/* 264 */         return "Tiếng Albanian";
/*     */       case 32:
/* 266 */         return "Tiếng Ả Rập (JO)";
/*     */       case 24:
/* 268 */         return "Tiếng Ả Rập (SA)";
/*     */       case 38:
/* 270 */         return "Tiếng Ả Rập (EG)";
/*     */       case 29:
/* 272 */         return "Tiếng Azerbaijani";
/*     */       case 28:
/* 274 */         return "Bungari";
/*     */       case 17:
/* 276 */         return "Tiếng Trung Quốc";
/*     */       case 27:
/* 278 */         return "Tiếng Croatia";
/*     */       case 9:
/* 280 */         return "Séc";
/*     */       case 10:
/* 282 */         return "Của Đan Mạch";
/*     */       case 11:
/* 284 */         return "Tiếng Hà Lan";
/*     */       case 2:
/* 286 */         return "Tiếng Anh";
/*     */       case 40:
/* 288 */         return "Tiếng Anh (IN)";
/*     */       case 12:
/* 290 */         return "Dân Eston";
/*     */       case 30:
/* 292 */         return "Farsi";
/*     */       case 13:
/* 294 */         return "Tiếng Phần Lan";
/*     */       case 3:
/* 296 */         return "Tiếng Pháp";
/*     */       case 4:
/* 298 */         return "Tiếng Đức";
/*     */       case 37:
/* 300 */         return "Georgian";
/*     */       case 23:
/* 302 */         return "Tiếng Hy Lạp";
/*     */       case 14:
/* 304 */         return "Tiếng Do Thái";
/*     */       case 15:
/* 306 */         return "Tiếng Hungari";
/*     */       case 16:
/* 308 */         return "Thuộc về đảo Iceland";
/*     */       case 6:
/* 310 */         return "Tiếng ý";
/*     */       case 18:
/* 312 */         return "Tiếng Nhật";
/*     */       case 19:
/* 314 */         return "Tiếng Hàn";
/*     */       case 20:
/* 316 */         return "Người Na Uy";
/*     */       case 8:
/* 318 */         return "Tiếng Ba Lan";
/*     */       case 21:
/* 320 */         return "Tiếng Bồ Đào Nha";
/*     */       case 25:
/* 322 */         return "Rumani";
/*     */       case 5:
/* 324 */         return "Tiếng Nga";
/*     */       case 26:
/* 326 */         return "Tiếng Slovenia";
/*     */       case 7:
/* 328 */         return "Tiếng Tây Ban Nha";
/*     */       case 22:
/* 330 */         return "Người Thụy Điển";
/*     */       case 35:
/* 332 */         return "Thái";
/*     */       case 33:
/* 334 */         return "Turkic";
/*     */       case 31:
/* 336 */         return "Tiếng Thổ Nhĩ Kỳ (RTL)";
/*     */       case 1:
/* 338 */         return "Tiếng Thổ Nhĩ Kỳ";
/*     */       case 36:
/* 340 */         return "Việt";
/*     */     } 
/* 342 */     return "Unknown ngôn ngữ";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrencyIdx() {
/* 347 */     return 148;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCultureInfoVIVN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */