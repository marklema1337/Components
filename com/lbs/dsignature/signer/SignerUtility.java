/*     */ package com.lbs.dsignature.signer;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.control.interfaces.ILbsOptionPane;
/*     */ import com.lbs.platform.interfaces.IApplicationContext;
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import tr.gov.tubitak.uekae.esya.api.certificate.validation.policy.PolicyReader;
/*     */ import tr.gov.tubitak.uekae.esya.api.certificate.validation.policy.ValidationPolicy;
/*     */ import tr.gov.tubitak.uekae.esya.api.common.ESYAException;
/*     */ import tr.gov.tubitak.uekae.esya.api.common.util.LicenseUtil;
/*     */ import tr.gov.tubitak.uekae.esya.api.signature.certval.CertValidationPolicies;
/*     */ import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.card.template.AkisTemplate;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.Context;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.SignatureType;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.XMLSignatureException;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.config.Config;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.config.SignatureProfileValidationConfig;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.config.TimestampConfig;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.config.ValidationConfig;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.validator.AllDataObjectsTimeStampValidator;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.validator.DataObjectFormatValidator;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.validator.IndividualDataObjectsTimeStampValidator;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.validator.SignatureTimeStampValidator;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.validator.SigningCertificateValidator;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.validator.SigningTimeValidator;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.validator.TurkishESigProfileAttributeValidator;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.validator.TurkishESigProfileValidator;
/*     */ import tr.gov.tubitak.uekae.esya.asn.util.AsnIO;
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
/*     */ public class SignerUtility
/*     */ {
/*     */   public static final String LISANS_XML = "lisans.xml";
/*     */   public static final String XMLSIGNATURE_CONFIG_XML = "xmlsignature-config.xml";
/*     */   public static final String CERTVAL_POLICY_XML = "certval-policy.xml";
/*     */   public static final String CERTVAL_POLICY_MALIMUHUR_XML = "certval-policy-malimuhur.xml";
/*     */   public static final String DSIGPROP_DLL = "DLL";
/*     */   public static final String DSIGPROP_SIG_TYPE = "SIGTYPE";
/*     */   public static final String DSIGPROP_CERT_TYPE = "CERTTYPE";
/*     */   public static final String DSIGPROP_TSURL = "TSURL";
/*     */   public static final String DSIGPROP_TSUSER = "TSUSER";
/*     */   public static final String DSIGPROP_TSPASS = "TSPASS";
/*     */   public static final String DSIGPROP_TSDIGEST = "TSDIGEST";
/*     */   public static final String DSIGPROP_SIGNER_ROLE = "SIGNERROLE";
/*     */   public static final String DSIGPROP_TS_SUPPORT = "TSSUPPORT";
/*     */   public static final String DSIGPROP_ARCHIVE_SUPPORT = "ARCHSUPPORT";
/*     */   public static final String DSIGPROP_ROOT_ELEMENT = "ENVELOPEDROOT";
/*     */   public static final String DSIGPROP_SLOT_ID = "SLOTID";
/*     */   public static final String DSIGPROP_CRL_ONLY = "CRLONLY";
/*     */   public static final String DSIGPROP_USE_PROXY = "USEPROXY";
/*     */   public static final String DSIGPROP_PHOST = "PHOST";
/*     */   public static final String DSIGPROP_PPORT = "PPORT";
/*     */   public static final String DSIGPROP_PUSER = "PUSER";
/*     */   public static final String DSIGPROP_PPASS = "PPASS";
/*     */   public static final int DSIG_DIGEST_SHA256 = 1;
/*     */   public static final int DSIG_DIGEST_SHA512 = 2;
/*     */   public static final int DSIG_DIGEST_SHA384 = 3;
/*     */   public static final String CERT_SERIAL = "CERT_SERIAL";
/*     */   public static final String USE_HSM = "USE_HSM";
/*     */   public static final String TOKEN_PIN = "TOKEN_PIN";
/*     */   public static final String CERT_HANDLE = "CERT_HANDLE";
/*     */   public static final String CERT_ALIAS = "CERT_ALIAS";
/*     */   public static final String XADES_ENVELOPING = "enveloping";
/*     */   public static final String XADES_ENVELOPED = "enveloped";
/*     */   public static final String CERT_NES = "nes";
/*     */   public static final String CERT_E_MUHUR = "e-muhur";
/*     */   public static final String SIGNER_ROLE_NON = "non";
/*     */   public static final String TS_SUPPORT_FALSE = "false";
/*     */   public static final String ARCH_SUPPORT_FALSE = "false";
/*  96 */   private static final String[] XML_FILES = new String[] { "xmlsignature-config.xml", "lisans.xml", "certval-policy.xml", 
/*  97 */       "certval-policy-malimuhur.xml" };
/*     */   
/*     */   private static final String MALI_MUHUR_CERTIFICATE = "MaliMuhurCertificate";
/*     */   
/*     */   private static final String SERTIFIKA_DEPOSU_SVT = "SertifikaDeposu.svt";
/*     */   
/* 103 */   private static LbsConsole ms_Console = LbsConsole.getLogger(SignerUtility.class);
/*     */ 
/*     */   
/*     */   public static void prepeareESYAAPI(IApplicationContext context, boolean serverSide, String resourceDirPath) throws ESYAException {
/* 107 */     String lisansXml = null;
/* 108 */     if (!serverSide) {
/*     */       
/* 110 */       String tempDirectory = JLbsFileUtil.getTempDirectory();
/* 111 */       write2TempDir((IClientContext)context, tempDirectory);
/*     */       
/* 113 */       lisansXml = JLbsFileUtil.appendPath(tempDirectory, "lisans.xml");
/*     */     } else {
/*     */       
/* 116 */       lisansXml = JLbsFileUtil.appendPath(resourceDirPath, "lisans.xml");
/*     */     } 
/*     */     try {
/* 119 */       LicenseUtil.setLicenseXml(new FileInputStream(new File(lisansXml)));
/*     */     }
/* 121 */     catch (FileNotFoundException e) {
/*     */       
/* 123 */       ms_Console.error(e);
/*     */     } 
/* 125 */     sertifikaDeposuYukle(context, serverSide);
/*     */     
/* 127 */     AkisTemplate.addATR("3B9F978131FE4580655443D2210831C073F6218081055B");
/* 128 */     AkisTemplate.addATR("3B9F978131FE4580655443E4210831C073F6218081056D");
/* 129 */     AkisTemplate.addATR("3B9F968131FE4580655443D3210831C073F6218081055B");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Context createContext(TimestampConfig tsConfig, boolean serverSide, String resourceDir) throws ESYAException, XMLSignatureException {
/* 136 */     String contextDir = null;
/*     */     
/* 138 */     if (serverSide) {
/* 139 */       contextDir = resourceDir;
/*     */     } else {
/* 141 */       contextDir = JLbsFileUtil.getTempDirectory();
/*     */     } 
/* 143 */     Context signContext = new Context(contextDir);
/* 144 */     String xmlSigConf = JLbsFileUtil.appendPath(contextDir, "xmlsignature-config.xml");
/* 145 */     signContext.setConfig(new Config(xmlSigConf));
/* 146 */     if (tsConfig != null) {
/* 147 */       signContext.getConfig().setTimestampConfig(tsConfig);
/*     */     }
/* 149 */     Map<String, String> validationPolicyFiles = new HashMap<>();
/* 150 */     String certPolXml = JLbsFileUtil.appendPath(contextDir, "certval-policy.xml");
/* 151 */     String certPolMMXml = JLbsFileUtil.appendPath(contextDir, "certval-policy-malimuhur.xml");
/* 152 */     validationPolicyFiles.put("", certPolXml);
/* 153 */     validationPolicyFiles.put("MaliMuhurCertificate", certPolMMXml);
/*     */     
/* 155 */     ValidationPolicy policy1 = PolicyReader.readValidationPolicyFromURL(certPolXml);
/* 156 */     ValidationPolicy policy2 = PolicyReader.readValidationPolicyFromURL(certPolMMXml);
/*     */     
/* 158 */     CertValidationPolicies policies = new CertValidationPolicies();
/* 159 */     policies.register(null, policy1);
/* 160 */     policies.register("MaliMuhurCertificate", policy2);
/*     */     
/* 162 */     List<Class<AllDataObjectsTimeStampValidator>> v = new ArrayList();
/* 163 */     v.add(AllDataObjectsTimeStampValidator.class);
/* 164 */     v.add(DataObjectFormatValidator.class);
/* 165 */     v.add(IndividualDataObjectsTimeStampValidator.class);
/* 166 */     v.add(SigningCertificateValidator.class);
/* 167 */     v.add(SigningTimeValidator.class);
/* 168 */     v.add(TurkishESigProfileAttributeValidator.class);
/* 169 */     v.add(TurkishESigProfileValidator.class);
/* 170 */     if (tsConfig != null)
/* 171 */       v.add(SignatureTimeStampValidator.class); 
/* 172 */     Map<SignatureType, SignatureProfileValidationConfig> c1 = new HashMap<>();
/* 173 */     if (tsConfig == null) {
/* 174 */       c1.put(SignatureType.XAdES_BES, new SignatureProfileValidationConfig(
/* 175 */             SignatureType.XAdES_BES, 
/* 176 */             SignatureType.XAdES_BES, v));
/*     */     } else {
/* 178 */       c1.put(SignatureType.XAdES_T, new SignatureProfileValidationConfig(
/* 179 */             SignatureType.XAdES_T, 
/* 180 */             SignatureType.XAdES_T, v));
/*     */     } 
/* 182 */     ValidationConfig vc = new ValidationConfig(validationPolicyFiles, policies, 0L, 17280000L, 0L, true, false, true, true, true, 
/* 183 */         c1);
/* 184 */     signContext.getConfig().setValidationConfig(vc);
/*     */     
/* 186 */     return signContext;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void write2TempDir(IClientContext contex, String tempDirectory) {
/* 191 */     for (int i = 0; i < XML_FILES.length; i++) {
/*     */       
/* 193 */       String filename = XML_FILES[i];
/*     */ 
/*     */       
/*     */       try {
/* 197 */         byte[] file = contex.getResource(filename, false);
/* 198 */         String tempFile = JLbsFileUtil.appendPath(tempDirectory, filename);
/* 199 */         JLbsFileUtil.write2File(tempFile, file);
/*     */       }
/* 201 */       catch (FileNotFoundException e) {
/*     */         
/* 203 */         ms_Console.error(e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sertifikaDeposuYukle(IApplicationContext contex, boolean serverSide) {
/* 211 */     String sertifikaKlasor = String.valueOf(System.getProperty("user.home")) + "/.sertifikadeposu";
/* 212 */     if (!(new File(sertifikaKlasor)).exists()) {
/*     */       
/* 214 */       boolean basarili = (new File(sertifikaKlasor)).mkdir();
/* 215 */       if (!basarili) {
/*     */         
/* 217 */         String message = JLbsLocalizer.getString("UN", 91235, 2001);
/* 218 */         if (!serverSide)
/* 219 */           ILbsOptionPane.showMessageDialog(null, message, "", 0); 
/* 220 */         ms_Console.error(message);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     try {
/* 226 */       byte[] file = contex.getResource("SertifikaDeposu.svt", false);
/* 227 */       writeBytesToFile(file, String.valueOf(System.getProperty("user.home")) + "/.sertifikadeposu/SertifikaDeposu.svt");
/*     */     }
/* 229 */     catch (IOException e) {
/*     */       
/* 231 */       String msg = JLbsLocalizer.getString("UN", 91235, 2000);
/* 232 */       if (!serverSide)
/* 233 */         ILbsOptionPane.showMessageDialog(null, msg, "", 0); 
/* 234 */       ms_Console.error(msg);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeBytesToFile(byte[] arr, String filePath) throws IOException {
/* 241 */     AsnIO.dosyayaz(arr, filePath);
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] getBytesFromFile(String filePath) throws IOException {
/* 246 */     File file = new File(filePath);
/* 247 */     if (!file.exists())
/*     */     {
/* 249 */       return null;
/*     */     }
/* 251 */     return AsnIO.dosyadanOKU(filePath);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setProxySettings(Properties dsigProps) {
/* 257 */     if (Boolean.parseBoolean(dsigProps.getProperty("USEPROXY"))) {
/*     */       
/* 259 */       String prxHost = dsigProps.getProperty("PHOST");
/* 260 */       String prxPort = dsigProps.getProperty("PPORT");
/* 261 */       if (!JLbsStringUtil.isEmpty(prxHost) && !JLbsStringUtil.isEmpty(prxPort)) {
/*     */         
/* 263 */         System.setProperty("http.proxyHost", prxHost);
/* 264 */         System.setProperty("http.proxyPort", prxPort);
/* 265 */         System.setProperty("https.proxyHost", prxHost);
/* 266 */         System.setProperty("https.proxyPort", prxPort);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setOriginalProxySettings(String httpPrxHost, String httpPrxPort, String httpsPrxHost, String httpsPrxPort) {
/* 274 */     if (!JLbsStringUtil.isEmpty(httpPrxHost))
/* 275 */       System.setProperty("http.proxyHost", httpPrxHost); 
/* 276 */     if (!JLbsStringUtil.isEmpty(httpPrxPort))
/* 277 */       System.setProperty("http.proxyPort", httpPrxPort); 
/* 278 */     if (!JLbsStringUtil.isEmpty(httpsPrxHost))
/* 279 */       System.setProperty("https.proxyHost", httpsPrxHost); 
/* 280 */     if (!JLbsStringUtil.isEmpty(httpsPrxPort)) {
/* 281 */       System.setProperty("https.proxyPort", httpsPrxPort);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static ContentWithDigestData getDigest(byte[] rawDataToBeSigned) throws IOException, NoSuchAlgorithmException {
/* 287 */     MessageDigest digestEngine = MessageDigest.getInstance("SHA-256");
/* 288 */     ByteArrayOutputStream contentBuffer = new ByteArrayOutputStream();
/* 289 */     digestEngine.update(rawDataToBeSigned, 0, rawDataToBeSigned.length);
/* 290 */     contentBuffer.write(rawDataToBeSigned, 0, rawDataToBeSigned.length);
/* 291 */     byte[] contentHash = digestEngine.digest();
/* 292 */     contentBuffer.close();
/*     */     
/* 294 */     ContentWithDigestData data = new ContentWithDigestData();
/* 295 */     data.setContent(contentBuffer.toByteArray());
/* 296 */     data.setContentHash(contentHash);
/*     */     
/* 298 */     return data;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\dsignature\signer\SignerUtility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */