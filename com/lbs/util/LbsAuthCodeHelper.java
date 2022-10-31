/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.util.Hashtable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsAuthCodeHelper
/*     */ {
/*     */   public static final int ORG_UNIT_AUTH_CODE = -1;
/*     */   public static final int DEPT_AUTH_CODE = -2;
/*     */   public static final int LOC_AUTH_CODE = -4;
/*     */   public static final int REPORT_AUTH_CODE = -11;
/*     */   public static final int ORG_UNIT_SUB_AUTH_CODE = -99;
/*     */   public static final int ASG_ORG_UNIT_AUTH_CODE = 291;
/*     */   public static final int OP_DB_UPDATE = 1;
/*     */   public static final int OP_DB_DELETE = 2;
/*     */   public static final int OP_DB_VIEW = 4;
/*     */   public static final int OP_DB_DUPLICATE = 8;
/*     */   public static final int OP_DB_RESTRICT = 16;
/*     */   public static final int OP_DB_INSERT = 32;
/*     */   public static final int OP_DB_SELECT = 64;
/*     */   public static final String DB_AUTH_SEPARATOR = "!_éé_!";
/*     */   public static final String DB_AUTH_NONE = "!é_é!";
/*     */   public static final String DB_AUTH_DISABLED = "!__ééé__!";
/*     */   public static final String DB_AUTH_CODE_EMPTY = "+";
/*     */   public static final String DB_AUTH_CODE_ALL = "*";
/*     */   public static final int ORG_UNIT_KEYS = -99999;
/*     */   public static final int EMPTY_ORG_UNIT_ID = -9999;
/*     */   public static final String ORG_UNIT_KEY_PARAM = "_!ORG_UNIT_REFS!_";
/*     */   public static final boolean ADD_ORGUNIT_REFS = false;
/*     */   public static final int RES_ADMIN_DBCODE_START = -1000;
/*     */   public static final int AUTHMODE_ALL_MODES = 99999;
/*     */   private static final String MODULE_MM = "MM";
/*     */   private static final String MODULE_AM = "AM";
/*     */   private static final String MODULE_MR = "MR";
/*     */   private static final String MODULE_QM = "QM";
/*     */   private static final String MODULE_DE = "DE";
/*     */   private static final String MODULE_CA = "CA";
/*     */   private static final String MODULE_LOP = "LOP";
/*     */   private static final String MODULE_LOS = "LOS";
/*     */   private static final String MODULE_LOT = "LOT";
/*     */   private static final String MODULE_FTI = "FTI";
/*     */   private static final String MODULE_FTE = "FTE";
/*     */   private static final String MODULE_PM = "PM";
/*     */   private static final String MODULE_PP = "PP";
/*     */   private static final String MODULE_PJ = "PJ";
/*     */   private static final String MODULE_BU = "BU";
/*     */   private static final String MODULE_FI = "FI";
/*     */   private static final String MODULE_GL = "GL";
/*     */   private static final String MODULE_EM = "EM";
/*     */   private static final String MODULE_PR = "PR";
/*     */   private static final String MODULE_LM = "LM";
/*     */   private static final String MODULE_VM = "VM";
/*     */   private static final String MODULE_TM = "TM";
/*     */   private static final String MODULE_AP = "AP";
/*     */   private static final String MODULE_PCP = "PCP";
/*     */   private static final String MODULE_PCO = "PCC";
/*     */   private static final String MODULE_WF = "WF";
/*     */   private static final String MODULE_SS = "SS";
/*     */   private static final String MODULE_SF = "SF";
/*     */   private static final String MODULE_OM = "OM";
/*     */   private static final String MODULE_NO = "NO";
/*     */   private static final String MODULE_CN = "CN";
/*     */   private static final String MODULE_AR = "AR";
/*     */   private static final String MODULE_ES = "ES";
/*     */   private static final String MODULE_DP = "DP";
/*     */   private static final String MODULE_EW = "EW";
/*     */   private static final String MODULE_SM = "SM";
/*     */   private static final String MODULE_BI = "BI";
/*     */   private static final String MODULE_SC = "SC";
/*     */   private static final int MODULE_ID_MM = 10;
/*     */   private static final int MODULE_ID_AM = 12;
/*     */   private static final int MODULE_ID_MR = 44;
/*     */   private static final int MODULE_ID_QM = 14;
/*     */   private static final int MODULE_ID_DE = 22;
/*     */   private static final int MODULE_ID_CA = 28;
/*     */   private static final int MODULE_ID_LOP = 24;
/*     */   private static final int MODULE_ID_LOS = 26;
/*     */   private static final int MODULE_ID_LOT = 23;
/*     */   private static final int MODULE_ID_FTI = 46;
/*     */   private static final int MODULE_ID_FTE = 48;
/*     */   private static final int MODULE_ID_PM = 16;
/*     */   private static final int MODULE_ID_PP = 20;
/*     */   private static final int MODULE_ID_PJ = 36;
/*     */   private static final int MODULE_ID_BU = 40;
/*     */   private static final int MODULE_ID_FI = 32;
/*     */   private static final int MODULE_ID_GL = 30;
/*     */   private static final int MODULE_ID_EM = 100;
/*     */   private static final int MODULE_ID_PR = 102;
/*     */   private static final int MODULE_ID_LM = 104;
/*     */   private static final int MODULE_ID_VM = 106;
/*     */   private static final int MODULE_ID_TM = 108;
/*     */   private static final int MODULE_ID_AP = 110;
/*     */   private static final int MODULE_ID_PCP = 112;
/*     */   private static final int MODULE_ID_PCO = 114;
/*     */   private static final int MODULE_ID_WF = 90;
/*     */   private static final int MODULE_ID_SS = 34;
/*     */   private static final int MODULE_ID_SF = 113;
/*     */   private static final int MODULE_ID_OM = 115;
/*     */   private static final int MODULE_ID_NO = 54;
/*     */   private static final int MODULE_ID_CN = 500;
/*     */   private static final int MODULE_ID_AR = 57;
/*     */   private static final int MODULE_ID_ES = 52;
/*     */   private static final int MODULE_ID_DP = 55;
/*     */   private static final int MODULE_ID_EW = 53;
/*     */   private static final int MODULE_ID_SM = 116;
/*     */   private static final int MODULE_ID_BI = 56;
/*     */   private static final int MODULE_ID_SC = 58;
/*     */   private static final int MODULE_TAG_MM = 10000;
/*     */   private static final int MODULE_TAG_AM = 12000;
/*     */   private static final int MODULE_TAG_MR = 44000;
/*     */   private static final int MODULE_TAG_QM = 14000;
/*     */   private static final int MODULE_TAG_DE = 22000;
/*     */   private static final int MODULE_TAG_CA = 28000;
/*     */   private static final int MODULE_TAG_LOP = 24000;
/*     */   private static final int MODULE_TAG_LOS = 26000;
/*     */   private static final int MODULE_TAG_LOT = 23000;
/*     */   private static final int MODULE_TAG_FTI = 46000;
/*     */   private static final int MODULE_TAG_FTE = 34309;
/*     */   private static final int MODULE_TAG_PM = 16000;
/*     */   private static final int MODULE_TAG_PP = 20000;
/*     */   private static final int MODULE_TAG_PJ = 36000;
/*     */   private static final int MODULE_TAG_BU = 40000;
/*     */   private static final int MODULE_TAG_FI = 32000;
/*     */   private static final int MODULE_TAG_GL = 30000;
/*     */   private static final int MODULE_TAG_EM = 200004;
/*     */   private static final int MODULE_TAG_PR = 200005;
/*     */   private static final int MODULE_TAG_LM = 200007;
/*     */   private static final int MODULE_TAG_VM = 200850;
/*     */   private static final int MODULE_TAG_TM = 200006;
/*     */   private static final int MODULE_TAG_AP = 200009;
/*     */   private static final int MODULE_TAG_PCP = 200066;
/*     */   private static final int MODULE_TAG_PCO = 200010;
/*     */   private static final int MODULE_TAG_WF = 50000;
/*     */   private static final int MODULE_TAG_SS = 34000;
/*     */   private static final int MODULE_TAG_SF = 200014;
/*     */   private static final int MODULE_TAG_OM = 200110;
/*     */   private static final int MODULE_TAG_NO = 54000;
/*     */   private static final int MODULE_TAG_CN = 500000;
/*     */   private static final int MODULE_TAG_AR = 57000;
/*     */   private static final int MODULE_TAG_ES = 52000;
/*     */   private static final int MODULE_TAG_DP = 55000;
/*     */   private static final int MODULE_TAG_EW = 53000;
/*     */   private static final int MODULE_TAG_SM = 203000;
/*     */   private static final int MODULE_TAG_BI = 56000;
/*     */   private static final int MODULE_TAG_SC = 58000;
/* 166 */   public static int[] moduleTagList = new int[] { 10000, 12000, 44000, 14000, 22000, 
/* 167 */       28000, 24000, 26000, 23000, 46000, 34309, 16000, 
/* 168 */       20000, 36000, 40000, 32000, 30000, 52000, 53000, 200004, 
/* 169 */       200005, 200007, 200850, 200006, 200009, 200066, 200010, 
/* 170 */       50000, 200014, 203000, 200110, 54000, 500000, 56000, 55000, 
/* 171 */       57000, 34000, 58000 };
/*     */ 
/*     */   
/*     */   public static void initModulesWithModuleId(Hashtable<Integer, Integer> m_ModuleMapsByModuleId) {
/* 175 */     m_ModuleMapsByModuleId.put(Integer.valueOf(10000), Integer.valueOf(10));
/* 176 */     m_ModuleMapsByModuleId.put(Integer.valueOf(12000), Integer.valueOf(12));
/* 177 */     m_ModuleMapsByModuleId.put(Integer.valueOf(44000), Integer.valueOf(44));
/* 178 */     m_ModuleMapsByModuleId.put(Integer.valueOf(14000), Integer.valueOf(14));
/* 179 */     m_ModuleMapsByModuleId.put(Integer.valueOf(22000), Integer.valueOf(22));
/* 180 */     m_ModuleMapsByModuleId.put(Integer.valueOf(28000), Integer.valueOf(28));
/* 181 */     m_ModuleMapsByModuleId.put(Integer.valueOf(24000), Integer.valueOf(24));
/* 182 */     m_ModuleMapsByModuleId.put(Integer.valueOf(26000), Integer.valueOf(26));
/* 183 */     m_ModuleMapsByModuleId.put(Integer.valueOf(23000), Integer.valueOf(23));
/* 184 */     m_ModuleMapsByModuleId.put(Integer.valueOf(46000), Integer.valueOf(46));
/* 185 */     m_ModuleMapsByModuleId.put(Integer.valueOf(34309), Integer.valueOf(48));
/* 186 */     m_ModuleMapsByModuleId.put(Integer.valueOf(16000), Integer.valueOf(16));
/* 187 */     m_ModuleMapsByModuleId.put(Integer.valueOf(20000), Integer.valueOf(20));
/* 188 */     m_ModuleMapsByModuleId.put(Integer.valueOf(36000), Integer.valueOf(36));
/* 189 */     m_ModuleMapsByModuleId.put(Integer.valueOf(40000), Integer.valueOf(40));
/* 190 */     m_ModuleMapsByModuleId.put(Integer.valueOf(32000), Integer.valueOf(32));
/* 191 */     m_ModuleMapsByModuleId.put(Integer.valueOf(30000), Integer.valueOf(30));
/* 192 */     m_ModuleMapsByModuleId.put(Integer.valueOf(200004), Integer.valueOf(100));
/* 193 */     m_ModuleMapsByModuleId.put(Integer.valueOf(200005), Integer.valueOf(102));
/* 194 */     m_ModuleMapsByModuleId.put(Integer.valueOf(200007), Integer.valueOf(104));
/* 195 */     m_ModuleMapsByModuleId.put(Integer.valueOf(200850), Integer.valueOf(106));
/* 196 */     m_ModuleMapsByModuleId.put(Integer.valueOf(200006), Integer.valueOf(108));
/* 197 */     m_ModuleMapsByModuleId.put(Integer.valueOf(200009), Integer.valueOf(110));
/* 198 */     m_ModuleMapsByModuleId.put(Integer.valueOf(200066), Integer.valueOf(112));
/* 199 */     m_ModuleMapsByModuleId.put(Integer.valueOf(200010), Integer.valueOf(114));
/* 200 */     m_ModuleMapsByModuleId.put(Integer.valueOf(50000), Integer.valueOf(90));
/* 201 */     m_ModuleMapsByModuleId.put(Integer.valueOf(34000), Integer.valueOf(34));
/* 202 */     m_ModuleMapsByModuleId.put(Integer.valueOf(200014), Integer.valueOf(113));
/* 203 */     m_ModuleMapsByModuleId.put(Integer.valueOf(200110), Integer.valueOf(115));
/* 204 */     m_ModuleMapsByModuleId.put(Integer.valueOf(54000), Integer.valueOf(54));
/* 205 */     m_ModuleMapsByModuleId.put(Integer.valueOf(500000), Integer.valueOf(500));
/* 206 */     m_ModuleMapsByModuleId.put(Integer.valueOf(57000), Integer.valueOf(57));
/* 207 */     m_ModuleMapsByModuleId.put(Integer.valueOf(52000), Integer.valueOf(52));
/* 208 */     m_ModuleMapsByModuleId.put(Integer.valueOf(55000), Integer.valueOf(55));
/* 209 */     m_ModuleMapsByModuleId.put(Integer.valueOf(53000), Integer.valueOf(53));
/* 210 */     m_ModuleMapsByModuleId.put(Integer.valueOf(203000), Integer.valueOf(116));
/* 211 */     m_ModuleMapsByModuleId.put(Integer.valueOf(56000), Integer.valueOf(56));
/* 212 */     m_ModuleMapsByModuleId.put(Integer.valueOf(58000), Integer.valueOf(58));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void initModulesWithModuleTag(Hashtable<Integer, Integer> m_ModuleMapsByModuleTag) {
/* 217 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(10), Integer.valueOf(10000));
/* 218 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(12), Integer.valueOf(12000));
/* 219 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(44), Integer.valueOf(44000));
/* 220 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(14), Integer.valueOf(14000));
/* 221 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(22), Integer.valueOf(22000));
/* 222 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(28), Integer.valueOf(28000));
/* 223 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(24), Integer.valueOf(24000));
/* 224 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(26), Integer.valueOf(26000));
/* 225 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(23), Integer.valueOf(23000));
/* 226 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(46), Integer.valueOf(46000));
/* 227 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(48), Integer.valueOf(34309));
/* 228 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(16), Integer.valueOf(16000));
/* 229 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(20), Integer.valueOf(20000));
/* 230 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(36), Integer.valueOf(36000));
/* 231 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(40), Integer.valueOf(40000));
/* 232 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(32), Integer.valueOf(32000));
/* 233 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(30), Integer.valueOf(30000));
/* 234 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(100), Integer.valueOf(200004));
/* 235 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(102), Integer.valueOf(200005));
/* 236 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(104), Integer.valueOf(200007));
/* 237 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(106), Integer.valueOf(200850));
/* 238 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(108), Integer.valueOf(200006));
/* 239 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(110), Integer.valueOf(200009));
/* 240 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(112), Integer.valueOf(200066));
/* 241 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(114), Integer.valueOf(200010));
/* 242 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(90), Integer.valueOf(50000));
/* 243 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(34), Integer.valueOf(34000));
/* 244 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(113), Integer.valueOf(200014));
/* 245 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(115), Integer.valueOf(200110));
/* 246 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(54), Integer.valueOf(54000));
/* 247 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(500), Integer.valueOf(500000));
/* 248 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(57), Integer.valueOf(57000));
/* 249 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(52), Integer.valueOf(52000));
/* 250 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(55), Integer.valueOf(55000));
/* 251 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(53), Integer.valueOf(53000));
/* 252 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(116), Integer.valueOf(203000));
/* 253 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(56), Integer.valueOf(56000));
/* 254 */     m_ModuleMapsByModuleTag.put(Integer.valueOf(58), Integer.valueOf(58000));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void initModulesWithModuleEntId(Hashtable<Integer, String> m_ModuleMapsByModuleId) {
/* 259 */     m_ModuleMapsByModuleId.put(Integer.valueOf(10), "MM");
/* 260 */     m_ModuleMapsByModuleId.put(Integer.valueOf(12), "AM");
/* 261 */     m_ModuleMapsByModuleId.put(Integer.valueOf(44), "MR");
/* 262 */     m_ModuleMapsByModuleId.put(Integer.valueOf(14), "QM");
/* 263 */     m_ModuleMapsByModuleId.put(Integer.valueOf(22), "DE");
/* 264 */     m_ModuleMapsByModuleId.put(Integer.valueOf(28), "CA");
/* 265 */     m_ModuleMapsByModuleId.put(Integer.valueOf(24), "LOP");
/* 266 */     m_ModuleMapsByModuleId.put(Integer.valueOf(26), "LOS");
/* 267 */     m_ModuleMapsByModuleId.put(Integer.valueOf(23), "LOT");
/* 268 */     m_ModuleMapsByModuleId.put(Integer.valueOf(46), "FTI");
/* 269 */     m_ModuleMapsByModuleId.put(Integer.valueOf(48), "FTE");
/* 270 */     m_ModuleMapsByModuleId.put(Integer.valueOf(16), "PM");
/* 271 */     m_ModuleMapsByModuleId.put(Integer.valueOf(20), "PP");
/* 272 */     m_ModuleMapsByModuleId.put(Integer.valueOf(36), "PJ");
/* 273 */     m_ModuleMapsByModuleId.put(Integer.valueOf(40), "BU");
/* 274 */     m_ModuleMapsByModuleId.put(Integer.valueOf(32), "FI");
/* 275 */     m_ModuleMapsByModuleId.put(Integer.valueOf(30), "GL");
/* 276 */     m_ModuleMapsByModuleId.put(Integer.valueOf(100), "EM");
/* 277 */     m_ModuleMapsByModuleId.put(Integer.valueOf(102), "PR");
/* 278 */     m_ModuleMapsByModuleId.put(Integer.valueOf(104), "LM");
/* 279 */     m_ModuleMapsByModuleId.put(Integer.valueOf(106), "VM");
/* 280 */     m_ModuleMapsByModuleId.put(Integer.valueOf(108), "TM");
/* 281 */     m_ModuleMapsByModuleId.put(Integer.valueOf(110), "AP");
/* 282 */     m_ModuleMapsByModuleId.put(Integer.valueOf(112), "PCP");
/* 283 */     m_ModuleMapsByModuleId.put(Integer.valueOf(114), "PCC");
/* 284 */     m_ModuleMapsByModuleId.put(Integer.valueOf(90), "WF");
/* 285 */     m_ModuleMapsByModuleId.put(Integer.valueOf(34), "SS");
/* 286 */     m_ModuleMapsByModuleId.put(Integer.valueOf(113), "SF");
/* 287 */     m_ModuleMapsByModuleId.put(Integer.valueOf(115), "OM");
/* 288 */     m_ModuleMapsByModuleId.put(Integer.valueOf(54), "NO");
/* 289 */     m_ModuleMapsByModuleId.put(Integer.valueOf(500), "CN");
/* 290 */     m_ModuleMapsByModuleId.put(Integer.valueOf(57), "AR");
/* 291 */     m_ModuleMapsByModuleId.put(Integer.valueOf(52), "ES");
/* 292 */     m_ModuleMapsByModuleId.put(Integer.valueOf(53), "EW");
/* 293 */     m_ModuleMapsByModuleId.put(Integer.valueOf(116), "SM");
/* 294 */     m_ModuleMapsByModuleId.put(Integer.valueOf(56), "BI");
/* 295 */     m_ModuleMapsByModuleId.put(Integer.valueOf(58), "SC");
/*     */   }
/*     */ 
/*     */   
/*     */   public static void initModules(Hashtable<Integer, String> m_ModuleMaps) {
/* 300 */     m_ModuleMaps.put(Integer.valueOf(10000), "MM");
/* 301 */     m_ModuleMaps.put(Integer.valueOf(12000), "AM");
/* 302 */     m_ModuleMaps.put(Integer.valueOf(44000), "MR");
/* 303 */     m_ModuleMaps.put(Integer.valueOf(14000), "QM");
/* 304 */     m_ModuleMaps.put(Integer.valueOf(22000), "DE");
/* 305 */     m_ModuleMaps.put(Integer.valueOf(28000), "CA");
/* 306 */     m_ModuleMaps.put(Integer.valueOf(24000), "LOP");
/* 307 */     m_ModuleMaps.put(Integer.valueOf(26000), "LOS");
/* 308 */     m_ModuleMaps.put(Integer.valueOf(23000), "LOT");
/* 309 */     m_ModuleMaps.put(Integer.valueOf(46000), "FTI");
/* 310 */     m_ModuleMaps.put(Integer.valueOf(34309), "FTE");
/* 311 */     m_ModuleMaps.put(Integer.valueOf(16000), "PM");
/* 312 */     m_ModuleMaps.put(Integer.valueOf(20000), "PP");
/* 313 */     m_ModuleMaps.put(Integer.valueOf(36000), "PJ");
/* 314 */     m_ModuleMaps.put(Integer.valueOf(40000), "BU");
/* 315 */     m_ModuleMaps.put(Integer.valueOf(32000), "FI");
/* 316 */     m_ModuleMaps.put(Integer.valueOf(30000), "GL");
/* 317 */     m_ModuleMaps.put(Integer.valueOf(200004), "EM");
/* 318 */     m_ModuleMaps.put(Integer.valueOf(200005), "PR");
/* 319 */     m_ModuleMaps.put(Integer.valueOf(200007), "LM");
/* 320 */     m_ModuleMaps.put(Integer.valueOf(200850), "VM");
/* 321 */     m_ModuleMaps.put(Integer.valueOf(200006), "TM");
/* 322 */     m_ModuleMaps.put(Integer.valueOf(200009), "AP");
/* 323 */     m_ModuleMaps.put(Integer.valueOf(200066), "PCP");
/* 324 */     m_ModuleMaps.put(Integer.valueOf(200010), "PCC");
/* 325 */     m_ModuleMaps.put(Integer.valueOf(50000), "WF");
/* 326 */     m_ModuleMaps.put(Integer.valueOf(34000), "SS");
/* 327 */     m_ModuleMaps.put(Integer.valueOf(200014), "SF");
/* 328 */     m_ModuleMaps.put(Integer.valueOf(200110), "OM");
/* 329 */     m_ModuleMaps.put(Integer.valueOf(54000), "NO");
/* 330 */     m_ModuleMaps.put(Integer.valueOf(500000), "CN");
/* 331 */     m_ModuleMaps.put(Integer.valueOf(57000), "AR");
/* 332 */     m_ModuleMaps.put(Integer.valueOf(52000), "ES");
/* 333 */     m_ModuleMaps.put(Integer.valueOf(55000), "DP");
/* 334 */     m_ModuleMaps.put(Integer.valueOf(53000), "EW");
/* 335 */     m_ModuleMaps.put(Integer.valueOf(203000), "SM");
/* 336 */     m_ModuleMaps.put(Integer.valueOf(56000), "BI");
/* 337 */     m_ModuleMaps.put(Integer.valueOf(58000), "SC");
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasRight(int rights, int right) {
/* 342 */     return ((rights & right) == right);
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsStringList refineDBRights(JLbsStringList dbRights) {
/* 347 */     return refineDBRights(dbRights, false);
/*     */   }
/*     */ 
/*     */   
/*     */   private static JLbsStringList refineDBRights(JLbsStringList dbRights, boolean rightCondition) {
/* 352 */     if (dbRights == null)
/* 353 */       return null; 
/* 354 */     String dataStr = null;
/* 355 */     String[] list = null;
/* 356 */     JLbsStringListItem item = null;
/* 357 */     JLbsStringList result = new JLbsStringList();
/* 358 */     for (int i = 0; i < dbRights.size(); i++) {
/*     */       
/* 360 */       item = dbRights.getItemAt(i);
/* 361 */       int permissions = item.getTag();
/* 362 */       dataStr = item.getValue();
/* 363 */       boolean hasRight = hasRight(permissions, 16);
/* 364 */       if (hasRight == rightCondition) {
/*     */         
/* 366 */         list = JLbsStringUtil.split(dataStr, "!_éé_!");
/* 367 */         if (list != null && list.length == 2) {
/* 368 */           result.add(list[0], Integer.parseInt(list[1]));
/* 369 */         } else if (!rightCondition) {
/* 370 */           result.add(item.getValue(), item.getTag());
/*     */         } 
/*     */       } 
/* 373 */     }  return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsStringList refineExcludedDBRights(JLbsStringList dbRights) {
/* 378 */     return refineDBRights(dbRights, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int setRight(int rights, int right) {
/* 383 */     rights |= right;
/* 384 */     return rights;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int resetRight(int rights, int right) {
/* 389 */     rights &= right ^ 0xFFFFFFFF;
/* 390 */     return rights;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int toggleRight(int options, int option, boolean set) {
/* 395 */     if (set)
/* 396 */       return setRight(options, option); 
/* 397 */     return resetRight(options, option);
/*     */   }
/*     */ 
/*     */   
/*     */   private static String refineAuthCode(String authCode) {
/* 402 */     if (JLbsStringUtil.isEmpty(authCode))
/* 403 */       return authCode; 
/* 404 */     if (authCode.charAt(0) == '@')
/* 405 */       return authCode.substring(1); 
/* 406 */     return authCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasRight(JLbsStringList rightList, int permission, int authCodeID, String authCodeValue) {
/* 411 */     if (rightList != null) {
/*     */       
/* 413 */       JLbsStringList refinedRightList = refineDBRights(rightList);
/*     */       
/* 415 */       if (isDisabledAuthId(refinedRightList, authCodeID)) {
/* 416 */         return true;
/*     */       }
/* 418 */       if (matches(authCodeID, authCodeValue, refinedRightList, rightList, permission, false))
/* 419 */         return true; 
/* 420 */       return false;
/*     */     } 
/* 422 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int hasAuthCode(JLbsStringList rights, String authCode) {
/* 427 */     if (rights == null)
/* 428 */       return -1; 
/* 429 */     if (JLbsStringUtil.isEmpty(authCode)) {
/* 430 */       authCode = "+";
/*     */     }
/*     */     
/* 433 */     for (int i = 0; i < rights.size(); i++) {
/*     */       
/* 435 */       if (JLbsStringUtil.simpleRegExpMatch(rights.getValueAt(i), authCode)) {
/*     */         
/* 437 */         int tag = rights.getTagAt(i);
/* 438 */         if (tag != 16) {
/* 439 */           return tag;
/*     */         }
/*     */       } 
/*     */     } 
/* 443 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasAnyAuth(JLbsStringList rights, int right) {
/* 448 */     if (rights != null)
/* 449 */       for (int i = 0; i < rights.size(); i++) {
/*     */         
/* 451 */         int permissions = rights.getTagAt(i);
/* 452 */         if (hasRight(permissions, right))
/* 453 */           return true; 
/*     */       }  
/* 455 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsStringList getAuthCodesWithRight(JLbsStringList dbRights, int id) {
/* 460 */     if (dbRights == null)
/* 461 */       return null; 
/* 462 */     String dataStr = null;
/* 463 */     String[] list = null;
/* 464 */     JLbsStringListItem item = null;
/* 465 */     JLbsStringListItem curItem = new JLbsStringListItem("*", -999);
/* 466 */     JLbsStringList result = new JLbsStringList();
/* 467 */     int curID = -999;
/* 468 */     for (int i = 0; i < dbRights.size(); i++) {
/*     */       
/* 470 */       item = dbRights.getItemAt(i);
/* 471 */       dataStr = item.getValue();
/* 472 */       list = JLbsStringUtil.split(dataStr, "!_éé_!");
/* 473 */       if (list != null && list.length == 2) {
/*     */         
/* 475 */         curItem.Tag = item.Tag;
/* 476 */         curItem.Value = list[0];
/* 477 */         curID = Integer.parseInt(list[1]);
/*     */       }
/*     */       else {
/*     */         
/* 481 */         curItem.Tag = 0;
/* 482 */         curItem.Value = item.Value;
/* 483 */         curID = item.Tag;
/*     */       } 
/* 485 */       if (curID == id)
/* 486 */         result.add(curItem.Value, curItem.Tag); 
/*     */     } 
/* 488 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsStringList getRefinedAuthCodesWithRight(JLbsStringList dbRights, int id) {
/* 493 */     if (dbRights == null)
/* 494 */       return null; 
/* 495 */     String dataStr = null;
/* 496 */     String[] list = null;
/* 497 */     JLbsStringListItem item = null;
/* 498 */     JLbsStringListItem curItem = new JLbsStringListItem("*", -999);
/* 499 */     JLbsStringList result = new JLbsStringList();
/* 500 */     int curID = -999;
/* 501 */     for (int i = 0; i < dbRights.size(); i++) {
/*     */       
/* 503 */       item = dbRights.getItemAt(i);
/* 504 */       dataStr = item.getValue();
/* 505 */       list = JLbsStringUtil.split(dataStr, "!_éé_!");
/* 506 */       if (list != null && list.length == 2) {
/*     */         
/* 508 */         curItem.Tag = item.Tag;
/* 509 */         curItem.Value = list[0];
/* 510 */         curID = Integer.parseInt(list[1]);
/*     */       }
/*     */       else {
/*     */         
/* 514 */         curItem.Tag = 0;
/* 515 */         curItem.Value = item.Value;
/* 516 */         curID = item.Tag;
/*     */       } 
/* 518 */       if (curID == id)
/* 519 */         result.add(refineAuthCode(curItem.Value), curItem.Tag); 
/*     */     } 
/* 521 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Object getIsNull() {
/*     */     try {
/* 528 */       Class<?> cls = Class.forName("com.lbs.data.query.QueryChainValueNull");
/* 529 */       return cls.newInstance();
/*     */     }
/* 531 */     catch (Exception e) {
/*     */       
/* 533 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILbsQueryChainValue formDBAuthChain(JLbsStringList dbRights, int id) {
/* 539 */     return formDBAuthChain(dbRights, id, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILbsQueryChainValue formDBAuthChain(JLbsStringList dbRights, int id, boolean forceNullAuthCode) {
/* 544 */     JLbsStringList refinedDBRights = refineDBRights(dbRights);
/* 545 */     JLbsStringList excludedDBRights = refineExcludedDBRights(dbRights);
/* 546 */     if (refinedDBRights == null && excludedDBRights == null)
/* 547 */       return null; 
/* 548 */     if (refinedDBRights != null)
/*     */     {
/* 550 */       if (isDisabledAuthId(refinedDBRights, id))
/* 551 */         return null; 
/*     */     }
/* 553 */     ILbsQueryChainValue vals = null;
/*     */     
/*     */     try {
/* 556 */       Class<?> cls = Class.forName("com.lbs.data.query.QueryChainValue");
/* 557 */       vals = (ILbsQueryChainValue)cls.newInstance();
/*     */     }
/* 559 */     catch (Exception e) {
/*     */       
/* 561 */       return null;
/*     */     } 
/*     */     
/* 564 */     if (refinedDBRights != null) {
/* 565 */       fillRights(id, refinedDBRights, vals, false);
/*     */     }
/* 567 */     if (excludedDBRights != null)
/* 568 */       fillRights(id, excludedDBRights, vals, true); 
/* 569 */     return vals;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isDisabledAuthId(JLbsStringList refinedDBRights, int id) {
/* 574 */     String val = refinedDBRights.getValueAtTag(id);
/* 575 */     if (JLbsStringUtil.areEqual(val, "!__ééé__!"))
/* 576 */       return true; 
/* 577 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void fillRights(int id, JLbsStringList refinedDBRights, ILbsQueryChainValue vals, boolean excluded) {
/* 582 */     for (int i = 0; i < refinedDBRights.size(); i++) {
/*     */       
/* 584 */       JLbsStringListItem item = refinedDBRights.getItemAt(i);
/* 585 */       if (item.Tag == id && item.Value != null) {
/*     */         
/* 587 */         String mask = item.Value.replace('*', '%');
/* 588 */         mask = mask.replace('?', '_');
/* 589 */         mask = JLbsStringUtil.remove(mask, '@');
/* 590 */         if (mask.equals("+")) {
/*     */           
/* 592 */           if (!excluded) {
/* 593 */             vals.addValue(getIsNull());
/*     */           
/*     */           }
/*     */         }
/* 597 */         else if (excluded) {
/* 598 */           vals.addExcludedValue(mask);
/*     */         } else {
/* 600 */           vals.addValue(mask);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 605 */     if (!excluded && vals != null && vals.size() == 0) {
/* 606 */       vals.addValue("!é_é!");
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
/*     */   public static String getOrgUnitKeys(JLbsStringList dbRights) {
/* 625 */     if (dbRights == null)
/* 626 */       return null; 
/* 627 */     return dbRights.getValueAtTag(-99999);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getDefaultAuthCode(JLbsStringList dbRights) {
/* 632 */     if (dbRights == null)
/* 633 */       return ""; 
/* 634 */     for (int i = 0; i < dbRights.size(); i++) {
/*     */       
/* 636 */       String code = dbRights.getValueAt(i);
/* 637 */       if (!JLbsStringUtil.isEmpty(code))
/*     */       {
/* 639 */         if (code.charAt(0) == '@')
/* 640 */           return code.substring(1); 
/*     */       }
/*     */     } 
/* 643 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkListingDBAuthKey(JLbsStringList dbRights, int authCodeID, String authCodeValue) {
/* 649 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkDBAuthKey(int permission, JLbsStringList dbRights, int authCodeID, String authCodeValue) {
/* 659 */     if (dbRights == null) {
/* 660 */       return true;
/*     */     }
/* 662 */     JLbsStringList refinedDBRights = refineDBRights(dbRights);
/* 663 */     if (isDisabledAuthId(refinedDBRights, authCodeID))
/* 664 */       return true; 
/* 665 */     JLbsStringList excludedDBRights = refineExcludedDBRights(dbRights);
/*     */     
/* 667 */     if (matches(authCodeID, authCodeValue, refinedDBRights, dbRights, permission, false) && 
/* 668 */       !matches(authCodeID, authCodeValue, excludedDBRights, dbRights, permission, true))
/* 669 */       return true; 
/* 670 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean matches(int authCodeID, String authCodeValue, JLbsStringList refinedDBRights, JLbsStringList dbRights, int permission, boolean isExclusion) {
/* 676 */     if (JLbsStringUtil.isEmpty(authCodeValue))
/* 677 */       authCodeValue = "+"; 
/* 678 */     for (int i = 0; i < refinedDBRights.size(); i++) {
/*     */       
/* 680 */       JLbsStringListItem item = refinedDBRights.getItemAt(i);
/* 681 */       if (item.Tag == authCodeID && item.Value != null && item.Value.length() > 0) {
/*     */         
/* 683 */         String itemValue = item.Value;
/* 684 */         if (JLbsConstants.checkAppCloud()) {
/*     */           
/* 686 */           boolean moreThanTwoSegments = ((authCodeValue.split("\\.")).length > 2);
/* 687 */           if (moreThanTwoSegments)
/*     */           {
/* 689 */             if (itemValue.endsWith("*") && !itemValue.endsWith(".*")) {
/*     */               
/* 691 */               itemValue = itemValue.substring(0, itemValue.length() - 1);
/* 692 */               itemValue = String.valueOf(itemValue) + ".*";
/*     */             } 
/*     */           }
/*     */         } 
/*     */         
/* 697 */         if (JLbsStringUtil.simpleRegExpMatch(refineAuthCode(itemValue), authCodeValue)) {
/*     */           
/* 699 */           if (isExclusion)
/* 700 */             return true; 
/* 701 */           String keyValue = String.valueOf(item.Value) + "!_éé_!" + item.Tag;
/* 702 */           int idx = dbRights.indexOf(keyValue);
/* 703 */           int permissions = 0;
/* 704 */           if (idx != -1) {
/*     */             
/* 706 */             permissions = dbRights.getTagAt(idx);
/* 707 */             boolean hasRight = hasRight(permissions, permission);
/* 708 */             if (hasRight || (!isExclusion && permission == 16 && !hasRight))
/* 709 */               return true; 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 714 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsStringList makeDBRightsCompatible(JLbsStringList dbRights) {
/* 719 */     if (dbRights == null)
/* 720 */       dbRights = new JLbsStringList(); 
/* 721 */     if (dbRights.size() == 0) {
/*     */       
/* 723 */       JLbsStringListItem itemAll = new JLbsStringListItem();
/* 724 */       itemAll.setTag(111);
/* 725 */       itemAll.setValue("*");
/* 726 */       JLbsStringListItem itemPlus = new JLbsStringListItem();
/* 727 */       itemPlus.setTag(111);
/* 728 */       itemPlus.setValue("+");
/* 729 */       dbRights.addItem(itemAll);
/* 730 */       dbRights.addItem(itemPlus);
/*     */     } 
/* 732 */     return dbRights;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean forceOrgunitAuth(boolean processOrgUnitAuth) {
/* 737 */     return (JLbsConstants.FORCE_ORGUNIT_DBAUTH && processOrgUnitAuth);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\LbsAuthCodeHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */