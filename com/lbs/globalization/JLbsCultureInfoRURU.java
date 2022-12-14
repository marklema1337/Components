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
/*     */ 
/*     */ public class JLbsCultureInfoRURU
/*     */   extends JLbsDefaultCultureInfo
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  17 */   public static final String[] MONTHNAMES = new String[] { "Недействителен", "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", 
/*  18 */       "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь" };
/*     */   
/*  20 */   public static final String[] DAYNAMES = new String[] { "Недействителен", "Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", 
/*  21 */       "Пятница", "Суббота" };
/*     */   
/*  23 */   public static final String[] SHORTDAYNAMES = new String[] { "Недействителен", "Вос", "Пон", "Втр", "Срд", "Чтв", "Пят", "Суб" };
/*     */   
/*  25 */   protected static final String[] NUMBERNAMES = new String[] { "[0] Ноль", "[1] Один", "[2] Два", "[3] Три", "[4] Четыре", "[5] Пять", 
/*  26 */       "[6] Шесть", "[7] Семь", "[8] Восемь", "[9] Девять", "[10] Десять", "[11] Одинадцать ", "[12] Двенадцать", 
/*  27 */       "[13] Тринадцать", "[14] Четыренадцать", "[15] Пятьнадцать", "[16] Шестнадцать", "[17] Семьнадцать", 
/*  28 */       "[18] Восемьнадцать", "[19] Девитнадцать", "[20] Двадцать", "[30] Тридцать", "[40] Сорок", "[50] Пятьдесят", 
/*  29 */       "[60] Шестьдесят", "[70] Семьдесят", "[80] Восемьдесят", "[90] Девяносто", "[100] Сто" };
/*     */   
/*  31 */   protected static final String[] GROUPNAMES = new String[] { "[0] ~", "[1] ~|Тысяч|", "[2] ~|Миллионов|", "[3] ~|Миллиардов|", 
/*  32 */       "[4] ~|Триллионов|" };
/*     */   
/*  34 */   protected static final String[] BASECOMBINATIONS = new String[] { "[1] |Сто", "[2] ~|#", "[3] ~|и" };
/*     */ 
/*     */   
/*     */   protected String[] getFormatStrings() {
/*  38 */     return DEFAULTFORMATS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMonthFullName(int iMonth) {
/*  46 */     if (iMonth > 0 && iMonth <= 12)
/*  47 */       return MONTHNAMES[iMonth]; 
/*  48 */     return MONTHNAMES[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDayFullName(int iDay) {
/*  56 */     if (iDay > 0 && iDay <= 7)
/*  57 */       return DAYNAMES[iDay]; 
/*  58 */     return DAYNAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDayShortName(int iDay) {
/*  63 */     if (iDay > 0 && iDay <= 7)
/*  64 */       return SHORTDAYNAMES[iDay]; 
/*  65 */     return "XxX";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getNumberNames() {
/*  70 */     return NUMBERNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getGroupNames() {
/*  75 */     return GROUPNAMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getBaseCombinations() {
/*  80 */     return BASECOMBINATIONS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguagePrefix() {
/*  85 */     return "RURU";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCultureResStr(int cultureResID) {
/*  90 */     switch (cultureResID) {
/*     */       
/*     */       case 101:
/*  93 */         return "русский";
/*     */       case 102:
/*  95 */         return "Имя пользователя";
/*     */       case 103:
/*  97 */         return "Пароль";
/*     */       case 119:
/*  99 */         return "Зайти под другим пользователем";
/*     */       case 104:
/* 101 */         return "Фирма";
/*     */       case 105:
/* 103 */         return "Период";
/*     */       case 106:
/* 105 */         return "Язык";
/*     */       case 107:
/* 107 */         return "&OK";
/*     */       case 108:
/* 109 */         return "&Отмена";
/*     */       case 109:
/* 111 */         return "Фирма и Период";
/*     */       case 110:
/* 113 */         return "Второстепенный";
/*     */       case 111:
/* 115 */         return "Включен Caps Lock";
/*     */       case 1000:
/* 117 */         return "Ошибка";
/*     */       case 1001:
/* 119 */         return "Неверный пользователь или пароль!";
/*     */       case 1002:
/* 121 */         return "У Вас нет прав администратора. Пожалуйста,обратитесь к системному администратору";
/*     */       case 1003:
/* 123 */         return "Версия систем таблиц устаревшая. Пожалуйста обновите их.";
/*     */       case 1004:
/* 125 */         return "Вам не разрешено использовать эту программу. Пожалуйста,обратитесь к системному администратору";
/*     */       case 1005:
/* 127 */         return "Установленный период не был найден. Пожалуйста,обратитесь к системному администратору";
/*     */       case 1006:
/* 129 */         return "Активный период не был найден. Пожалуйста,обратитесь к системному администратору";
/*     */       case 1007:
/* 131 */         return "Системные таблицы и приложение имеют разные версии. Пожалуйста,обратитесь к системному администратору";
/*     */       case 1008:
/* 133 */         return "Система таблиц не найден.";
/*     */       case 1009:
/* 135 */         return "Системные таблицы не найдены. Пожалуйста,обратитесь к системному администратору";
/*     */       case 1010:
/* 137 */         return "Пользователь не смог войти в систему. Пожалуйста,обратитесь к системному администратору";
/*     */       case 1011:
/* 139 */         return "Учетная запись пользователя была заблокирована.Пожалуйста,обратитесь к системному администратору";
/*     */       case 1012:
/* 141 */         return "Недействительный IP адрес.Пожалуйста,обратитесь к системному администратору";
/*     */       case 1013:
/* 143 */         return "Время действия вашего пароля истекло. Пожалуйста введите новый пароль!";
/*     */       case 1014:
/* 145 */         return "Потеряно соединение с базой данных. Проверьте конфигурацию базы данных.";
/*     */       case 1015:
/* 147 */         return "Таблицы нуждаются в обновлении.Пожалуйста,обратитесь к системному администратору";
/*     */       case 1036:
/* 149 */         return "Базы данных нуждаются в обновлении.Пожалуйста,обратитесь к системному администратору";
/*     */       case 1035:
/* 151 */         return "Базы данных компании нуждаются в обновлении.Пожалуйста,обратитесь к системному администратору";
/*     */       case 1016:
/* 153 */         return "Пожалуйста определите фирму, которую хотите к логину!";
/*     */       case 1017:
/* 155 */         return "Версия систем таблиц может быть устаревшим. Пожалуйста обновите и попробуйте их заново.";
/*     */       case 1018:
/* 157 */         return "Ошибка при входе";
/*     */       case 1019:
/* 159 */         return "Канал не может быть создан";
/*     */       case 1020:
/* 161 */         return "Не удается подключиться к этому адресу";
/*     */       case 1021:
/* 163 */         return "Не действительный логин для соединения";
/*     */       case 1022:
/* 165 */         return "Ошибка клиента:\n";
/*     */       case 1023:
/* 167 */         return "Ошибка сервера:\n";
/*     */       case 1024:
/* 169 */         return "Произошла ошибка, из-за кода:\n";
/*     */       case 1025:
/* 171 */         return "У Вас нет действительной лицензии, чтобы использовать это приложение \n Пожалуйста,установите  действительную лицензию!";
/*     */       case 1026:
/* 173 */         return "В Вашу лицензию не включен этот язык!";
/*     */       case 1027:
/* 175 */         return "Истек срок действия лиценции!";
/*     */       case 1028:
/* 177 */         return "Ваша лицензия не поддерживает эту операционную систему  для работы в приложении! Пожалуйста,смотрите лицензионное соглашение!";
/*     */       case 1029:
/* 179 */         return "Ваша лицензия не включает эту систему управления базой данных!";
/*     */       case 1039:
/* 181 */         return "Пользовательский предел лицензии достигнут.Вы не можете войти в систему!";
/*     */       case 1030:
/* 183 */         return "Не удалось создать новый сеанс! Пользовательский логин заблокирован!";
/*     */       case 1031:
/* 185 */         return "Нужно обновить Настройки. Пожалуйста, обратитесь к системному администратору.";
/*     */       case 1032:
/* 187 */         return "В настоящее время \"Logo Report Viewer\" работает в другом приложении. Чтоб посмотреть другой отчет  \"Logo Report Viewer\" ,пожалуйста, нажмите на кнопку \"Открыть\"";
/*     */       case 1033:
/* 189 */         return "Ошибка совместимости клиента. Пожалуйста, обратитесь к системному администратору.";
/*     */       case 1034:
/* 191 */         return "Пакет настроек не действителен.Пожалуйста, обратитесь к системному администратору.";
/*     */       case 112:
/* 193 */         return "Забыл свою регистрационную информацию";
/*     */       case 113:
/* 195 */         return "E-Mail адрес";
/*     */       case 114:
/* 197 */         return "Пожалуйста, введите действительный адрес электронной почты";
/*     */       case 115:
/* 199 */         return "Ваш Логин отправлен на ваш адрес электронной почты";
/*     */       case 116:
/* 201 */         return "Введенный майл адрес не зарегистрирован в системе. Пожалуйста, попробуйте еще раз.";
/*     */       case 117:
/* 203 */         return "Произошла непредвиденная ошибка. Пожалуйста, обратитесь к системному администратору.";
/*     */       case 118:
/* 205 */         return "Информация";
/*     */       case 1037:
/* 207 */         return "Информация о другом пользователе недопустима.";
/*     */       case 1038:
/* 209 */         return "Управления процессами базы данных не актуальны.Пожалуйста, обратитесь к системному администратору.";
/*     */       case 120:
/* 211 */         return "Развитой";
/*     */       case 1040:
/* 213 */         return "Секретный вопрос ответил неправильно. Пожалуйста, попробуйте еще раз.";
/*     */       case 1041:
/* 215 */         return "Поле пароля не может быть пустым";
/*     */       case 1064:
/* 217 */         return "В Вашем пакете лицензии нет определения меню. Вы не можете войти в систему.";
/*     */       case 1065:
/* 219 */         return "Номер паспорта в системе не найден!";
/*     */       case 1066:
/* 221 */         return "Электронная подпись не может быть проверена!";
/*     */       case 1067:
/* 223 */         return "Электронная подпись недействителена!";
/*     */       case 1068:
/* 225 */         return "Не найдено ни одной лицензии, подключенной для пользователя!";
/*     */       case 1043:
/* 227 */         return "Ваша лицензия не поддерживает логин для этого типа пользователя. Вы не можете войти в систему!";
/*     */     } 
/* 229 */     return super.getCultureResStr(cultureResID);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrencyIdx() {
/* 234 */     return 51;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\globalization\JLbsCultureInfoRURU.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */