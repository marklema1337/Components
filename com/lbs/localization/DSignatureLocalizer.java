/*     */ package com.lbs.localization;
/*     */ 
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import com.lbs.transport.TransportUtil;
/*     */ import com.lbs.util.JLbsClientFS;
/*     */ import com.lbs.util.JLbsStringList;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DSignatureLocalizer
/*     */ {
/*     */   private static final String CACHE_KEY = "DSignatureResources";
/*     */   public static final int RES_PIN_ENTER = 1;
/*     */   public static final int RES_DSIGNATURE_LOGIN = 2;
/*     */   public static final int RES_CERT_SELECT = 3;
/*     */   public static final int RES_CLEAR = 4;
/*     */   public static final int RES_SELECT = 5;
/*     */   public static final int RES_IN_PROGRESS = 6;
/*     */   public static final int RES_CERTS_ON_CARD = 7;
/*     */   public static final int RES_CERT_ISSUER = 8;
/*     */   public static final int RES_CERT_VALIDITY_FROM = 9;
/*     */   public static final int RES_CERT_IS_FOR_SIGNING = 10;
/*     */   public static final int RES_CERT_NOT_FOR_SIGNING = 11;
/*     */   public static final int RES_KEY_USAGE = 12;
/*     */   public static final int RES_EXT_KEY_USAGE = 13;
/*     */   public static final int RES_CERT_SERI_NO = 14;
/*     */   public static final int RES_CERT_SUBJECT = 15;
/*     */   public static final int RES_ERR_PIN_EXPIRED = 16;
/*     */   public static final int RES_ERR_PIN_INVALID = 17;
/*     */   public static final int RES_ERR_PIN_INCORRECT = 18;
/*     */   public static final int RES_ERR_PIN_WRONG_RANGE = 19;
/*     */   public static final int RES_ERR_PIN_LOCKED = 20;
/*     */   public static final int RES_ERR_PIN_ERROR = 21;
/*     */   public static final int RES_ERR_PIN_CANCELED = 22;
/*     */   public static final int RES_ERR_NO_CARD_READER_FOUND = 23;
/*     */   public static final int RES_ERR_NO_SMARTCARD_FOUND = 24;
/*     */   public static final int RES_ERR_NO_CERT_FOUND_ON_CARD = 25;
/*     */   public static final int RES_ERR_CHOOSE_ONE_CERTIFICATE = 26;
/*     */   public static final int RES_ERR_CHOOSE_CERTIFICATE_OWNER = 27;
/*     */   public static final int RES_ERR_MECHANISIM_NOT_SUPPORTED = 28;
/*     */   public static final int RES_ERR_SIGNATURE_CANNOT_BE_GENERATED = 29;
/*     */   public static final int RES_ERR_INVALID_TCKN = 30;
/*     */   public static final int RES_ERR_INVALID_DSIGNATURE = 31;
/*     */   public static final int RES_ERR_INVALID_DIGEST = 32;
/*     */   public static final int RES_ERR_SIGNATURE_CANNOT_BE_VERIFIED = 34;
/*     */   public static final int RES_ERR_CERT_SELECTION_CANCELED = 33;
/*     */   public static final int RES_ERR_PKCS_MODULE_ERROR = 35;
/*     */   public static final int RES_ERR_DSIGNATURE_LOGIN_FAILED = 36;
/*     */   public static final int RES_AUTH_KEY_IDENTIFIER = 37;
/*     */   public static final int RES_CERT_CERT_POLICIES = 38;
/*     */   public static final int RES_CERT_BASIC_CONSTRAINTS = 39;
/*     */   public static final int RES_CERT_CRL_DIST_POINTS = 40;
/*     */   public static final int RES_CERT_AUTH_INFO_ACCESS = 41;
/*     */   public static final int RES_QC_STATEMENT = 42;
/*     */   public static final int RES_SUB_KEY_IDENTIFIER = 43;
/*     */   public static final int RES_SUB_INFO_ACCESS = 44;
/*     */   public static final int RES_CERT_SUBALTNAMES = 45;
/*     */   public static final int RES_CRL_DIST_SYNTAX = 46;
/*     */   public static final int RES_CRL_NUMBER = 47;
/*     */   public static final int RES_DELTA_CRL = 48;
/*     */   public static final int RES_ERROR_EXTENSION = 49;
/*     */   public static final int RES_EXP_CERT_ON_CRL = 50;
/*     */   public static final int RES_FRESH_CRL = 51;
/*     */   public static final int RES_HOLD_INS_CODE = 52;
/*     */   public static final int RES__INFO_ACCESS = 53;
/*     */   public static final int RES_INHIBIT_ANY_POLICY = 54;
/*     */   public static final int RES_INVALIDITY_DATE = 55;
/*     */   public static final int RES_ISSUER_ALT_NAME = 56;
/*     */   public static final int RES_ISSUING_DIST_POINT = 57;
/*     */   public static final int RES_NAME_CONST = 58;
/*     */   public static final int RES_POL_CONST = 59;
/*     */   public static final int RES_POL_MAPS = 60;
/*     */   public static final int RES_PRIV_KEY_USAGE_PERIOD = 61;
/*     */   public static final int RES_REASON_CODE = 62;
/*     */   public static final int RES_SUB_DIR_ATT = 63;
/*     */   public static final int RES_CERT_VALIDITY_TO = 64;
/*     */   public static final int RES_ERR_TOKEN_EXCEPTION = 65;
/*     */   public static final int RES_ERR_PIN_EMPTY = 70;
/*     */   public static final int RES_ERR_SESSION_TIMEOUT = 80;
/*     */   public static final int RES_BUTTONS = 81;
/*     */   public static final int RES_WARN_LAST_PIN_TRY = 82;
/*     */   public static final int RES_PROGRESS_INIT_MODULE = 100;
/*     */   public static final int RES_PROGRESS_COMMUNICATE_CARD = 101;
/*     */   public static final int RES_PROGRESS_CREATE_SESSION = 102;
/*     */   public static final int RES_PROGRESS_PREP_CERTS = 103;
/*     */   public static final int RES_PROGRESS_PREP_ROOT_CERTS = 104;
/*     */   public static final int RES_PROGRESS_SIGN = 105;
/*     */   public static final int RES_PROGRESS_TITLE = 106;
/*     */   public static final int RES_PROGRESS_VERIFY_CHAIN = 107;
/*     */   public static final int RES_PROGRESS_SIGN_INIT = 108;
/*     */   public static final int RES_EXC_WRAPPER_OS = 120;
/*     */   public static final int RES_EXC_WRAPPER_LOAD = 121;
/*     */   public static final int RES_EXC_MODULE_INIT = 122;
/*     */   public static final int RES_EXC_MODULE_NULL = 123;
/*     */   public static final int RES_EXC_CERT_READ = 124;
/*     */   public static final int RES_EXC_CERT_DETAIL_READ = 125;
/*     */   public static final int RES_EXC_SESSION_READ_ONLY = 126;
/*     */   public static final int RES_EXC_SESSION_CLOSE = 127;
/*     */   public static final int RES_EXC_ROOT_CERT_LOAD = 128;
/*     */   public static final int RES_EXC_OSCP_CERT_LOAD = 129;
/*     */   public static final int RES_EXC_OSCP_LIST_EMPTY = 130;
/*     */   public static final int RES_EXC_RESPONDER_LIST_EMPTY = 131;
/*     */   public static final int RES_EXC_CERT_LIST_EMPTY = 132;
/*     */   public static final int RES_EXC_SESSION_CODE_EMPTY = 133;
/*     */   public static final int RES_EXC_CERT_NOT_FOR_REPUDIATION = 134;
/*     */   public static final int RES_EXC_CERT_NOT_FOR_DSIGN = 135;
/*     */   public static final int RES_EXC_CERT_NOT_QUALIFIED = 136;
/*     */   public static final int RES_EXC_OSCP_REQ_CREATE = 137;
/*     */   public static final int RES_EXC_OSCP_CHECK_ERROR = 138;
/*     */   public static final int RES_EXC_OSCP_RESP_NOT_SUCCESS = 139;
/*     */   public static final int RES_EXC_CRL_CHECK = 140;
/*     */   public static final int RES_EXC_CERT_EXPIRED = 141;
/*     */   public static final int RES_EXC_CERT_NOT_YET_VALID = 142;
/*     */   public static final int RES_EXC_CERT_VERIFY_ERROR = 143;
/*     */   public static final int RES_EXC_CERT_CHAIN_VERIFY = 144;
/*     */   public static final int RES_EXC_TCKN_NOT_FOUND = 145;
/*     */   public static final int RES_EXC_SIGN = 146;
/*     */   public static final int RES_EXC_NO_SESSION = 147;
/*     */   public static final int RES_EXC_CANCEL = 148;
/*     */   public static final int RES_EXC_TIMESTAMP_ERROR = 149;
/*     */   public static final int RES_EXC_TS_LIST_EMPTY = 150;
/*     */   public static final int RES_EXC_TSRESPONDER_LIST_EMPTY = 151;
/*     */   public static final int RES_EXC_TS_CERT_LOAD = 152;
/*     */   public static final int RES_EXC_TS_NOT_VERIFIED = 153;
/*     */   public static final int RES_EXC_XMLSIG_CORE_VALIDATION = 154;
/*     */   public static final int RES_EXC_XADES_VALIDATION = 155;
/*     */   public static final int RES_EXC_TSREQ_NOT_GENERATED = 156;
/*     */   public static final int RES_EXC_TSREQ_NOT_SENT = 157;
/*     */   public static final int RES_EXC_CONTROL_TSSETTING = 158;
/*     */   public static final int RES_EXC_ERROR_TSCONNECTION = 159;
/*     */   public static final int RES_EXC_TSDIGEST_NOT_EQUAL = 160;
/*     */   public static final int RES_EXC_TSNONCE_NOT_EQUAL = 161;
/*     */   public static final int RES_EXC_TSCERTIFICATE_NOT_FOR_TS_SIGNING = 162;
/*     */   public static final int RES_EXC_TSSIGNATURE_VERIFY = 163;
/*     */   public static final int RES_EXC_TS_VERIFY_ERROR = 164;
/*     */   public static final int RES_EXC_TS_NOTCONTAIN_CERT = 165;
/*     */   public static final int RES_EXC_TS_NOT_SIGNING_CERT = 166;
/*     */   public static final int RES_EXC_OCSP_REQCERT_NOT_CREATE = 167;
/*     */   public static final int RES_EXC_OCSP_URL_CONNECTION = 168;
/*     */   public static final int RES_EXC_OCSP_SIGN_NOT_VERIFIED = 169;
/*     */   public static final int RES_EXC_OCSP_CERT_REVOKED = 170;
/*     */   public static final int RES_EXC_OCSP_CERT_UNKNOWN = 171;
/*     */   public static final int RES_EXC_OCSP_RESP_NOT_RECOGNIZED = 172;
/*     */   public static final int RES_EXC_OCSP_NONCE_NOT_RETURNED = 173;
/*     */   public static final int RES_EXC_OCSP_NONCE_NOT_EQUAL = 174;
/*     */   public static final int RES_EXC_OCSP_RESP_NOT_GOT = 175;
/*     */   public static final int RES_EXC_OCSP_MORE_RECENT_INFO = 176;
/*     */   public static final int RES_EXC_OCSP_MISSING_THISUPDATE = 177;
/*     */   public static final int RES_EXC_OCSP_RESP_YET_NOT_VALID = 178;
/*     */   public static final int RES_EXC_CRL_NOT_TRUSTED_SIGNER = 179;
/*     */   public static final int RES_EXC_CRL_NOT_VERIFIED = 180;
/*     */   public static final int RES_EXC_CRL_MORE_RECENT_INFO = 181;
/*     */   public static final int RES_EXC_CRL_MISSING_THISUPDATE = 182;
/*     */   public static final int RES_EXC_CRL_RESP_YET_NOT_VALID = 183;
/*     */   public static final int RES_EXC_CRL_CERT_REVOKED = 184;
/*     */   public static final int RES_EXC_CRL_NOT_FOUN_ON_CERT = 185;
/*     */   public static final int RES_EXC_PREPEARER_DEF_NOT_FOUND = 186;
/*     */   public static final int RES_ERROR_CODE = 187;
/*     */   public static final int RES_EXC_CERT_NOT_FOR_E_MUHUR = 188;
/*     */   public static final int RES_OK = 189;
/*     */   public static final int RES_CANCEL = 190;
/*     */   public static final int RES_EXC_SIGNATURE_TIME_NOT_FOUND = 191;
/*     */   public static final int RES_EXC_OCSP_CONNECTION_ERROR = 192;
/*     */   public static final int RES_EXC_XMLSIG_CORE_VALIDATION_SIG = 193;
/*     */   public static final int RES_EXC_XMLSIG_CORE_VALIDATION_REF = 194;
/*     */   public static final int RES_EXC_SIGNATURE_VALUE_CORRUPT = 195;
/*     */   public static final int RES_EXC_TIME_STAMP_PROCESS = 196;
/*     */   public static final int RES_EXC_XMLSIGNATURE_CORRUPT = 197;
/*     */   public static final int RES_EXC_SIG_ELEMENT_NOT_FOUND = 198;
/*     */   public static final int RES_EXC_CERT_NOT_QUALIFIED_CP_USERNOTICE = 199;
/*     */   public static final int RES_EXC_CERT_NOT_QUALIFIED_CP_OID = 200;
/*     */   public static final int RES_EXC_CERT_NOT_QUALIFIED_BTK_QC_OID = 201;
/*     */   public static final int RES_EXC_CERT_NOT_QUALIFIED_ETSI_OID = 202;
/*     */   public static final int RES_EXC_CERT_NOT_QUALIFIED_BTK_PHRASE = 203;
/*     */   public static final int RES_WARN_PIN_RANGE = 204;
/*     */   public static final int RES_EXC_SECURE_CHAIN_NOT_CREATED = 205;
/*     */   public static final int RES_EXC_OCSP_RESP_EXPIRED = 206;
/*     */   public static final int RES_EXC_DIGEST_ALG_BTK_NOT_ACCEPTED = 207;
/*     */   public static final int RES_EXC_SIG_ALG_BTK_NOT_ACCEPTED = 208;
/*     */   public static final int RES_EXC_ROOT_CERT_VERIFY_ERROR = 209;
/*     */   public static final int RES_EXC_CRL_EXPIRED = 210;
/*     */   public static final int RES_EXC_TS_ELEMENT_NOT_FOUND = 211;
/*     */   public static final int RES_EXC_ARCHIVE_ELEMENT_NOT_FOUND = 212;
/*     */   public static final int RES_EXC_ARCH_TS_NOT_VERIFIED = 213;
/*     */   public static final int RES_EXC_CERT_NOT_EMUHUR_CP_OID = 214;
/*     */   public static final int RES_EXC_CONTROL_CERT_TYPE_SETTING = 215;
/*     */   public static final int RES_EXC_OCSPCERTIFICATE_NOT_FOR_OCSP_SIGNING = 216;
/*     */   private static final String RES_FILE_NAME = "624c737fb943e761f9a63441f4f80ff7.res";
/*     */   
/*     */   public static String getResource(IClientContext context, ILbsCultureInfo culture, int tag) {
/* 196 */     return getResource(context, (culture == null) ? 
/* 197 */         "" : 
/* 198 */         culture.getLanguagePrefix(), tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsStringList getResourceList(IClientContext context, ILbsCultureInfo culture, int tag) {
/* 203 */     String list = getResource(context, culture, tag);
/* 204 */     if (!JLbsStringUtil.isEmpty(list)) {
/* 205 */       return new JLbsStringList(list.split("\\|"));
/*     */     }
/* 207 */     return new JLbsStringList();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getResource(IClientContext context, String language, int tag) {
/* 212 */     if (context == null) {
/* 213 */       return "";
/*     */     }
/* 215 */     Object value = context.getVariable("DSignatureResources" + language);
/* 216 */     JLbsStringList list = null;
/* 217 */     if (value instanceof JLbsStringList) {
/*     */       
/* 219 */       list = (JLbsStringList)value;
/*     */     }
/*     */     else {
/*     */       
/* 223 */       if (JLbsClientFS.fileExists(String.valueOf(language) + "624c737fb943e761f9a63441f4f80ff7.res")) {
/*     */         
/*     */         try {
/*     */           
/* 227 */           byte[] data = JLbsClientFS.loadFile(String.valueOf(language) + "624c737fb943e761f9a63441f4f80ff7.res", true);
/* 228 */           Object obj = TransportUtil.deserializeObject(data);
/* 229 */           if (obj instanceof JLbsStringList) {
/*     */             
/* 231 */             context.setVariable("DSignatureResources" + language, obj);
/* 232 */             list = (JLbsStringList)obj;
/* 233 */             if (list != null) {
/* 234 */               return list.getValueAtTag(tag);
/*     */             }
/*     */           } 
/* 237 */         } catch (Exception exception) {}
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 245 */         value = context.getPublicObject("GetDSignatureResources", language, true);
/* 246 */         if (value instanceof JLbsStringList)
/*     */         {
/* 248 */           context.setVariable("DSignatureResources" + language, value);
/* 249 */           list = (JLbsStringList)value;
/* 250 */           byte[] data = TransportUtil.serializeObject(value);
/* 251 */           JLbsClientFS.saveFile(String.valueOf(language) + "624c737fb943e761f9a63441f4f80ff7.res", data, true, false, true);
/*     */         }
/*     */       
/* 254 */       } catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */     
/* 258 */     if (list != null)
/* 259 */       return list.getValueAtTag(tag); 
/* 260 */     return "";
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\DSignatureLocalizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */