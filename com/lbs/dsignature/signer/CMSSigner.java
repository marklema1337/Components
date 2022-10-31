/*     */ package com.lbs.dsignature.signer;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Properties;
/*     */ import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;
/*     */ import tr.gov.tubitak.uekae.esya.api.certificate.validation.CertificateStatus;
/*     */ import tr.gov.tubitak.uekae.esya.api.certificate.validation.CertificateValidation;
/*     */ import tr.gov.tubitak.uekae.esya.api.certificate.validation.ValidationSystem;
/*     */ import tr.gov.tubitak.uekae.esya.api.certificate.validation.check.certificate.CertificateStatusInfo;
/*     */ import tr.gov.tubitak.uekae.esya.api.certificate.validation.policy.PolicyReader;
/*     */ import tr.gov.tubitak.uekae.esya.api.certificate.validation.policy.ValidationPolicy;
/*     */ import tr.gov.tubitak.uekae.esya.api.cmssignature.ISignable;
/*     */ import tr.gov.tubitak.uekae.esya.api.cmssignature.SignableByteArray;
/*     */ import tr.gov.tubitak.uekae.esya.api.cmssignature.attribute.EParameters;
/*     */ import tr.gov.tubitak.uekae.esya.api.cmssignature.signature.BaseSignedData;
/*     */ import tr.gov.tubitak.uekae.esya.api.cmssignature.signature.ESignatureType;
/*     */ import tr.gov.tubitak.uekae.esya.api.cmssignature.validation.SignedDataValidation;
/*     */ import tr.gov.tubitak.uekae.esya.api.cmssignature.validation.SignedDataValidationResult;
/*     */ import tr.gov.tubitak.uekae.esya.api.cmssignature.validation.SignedData_Status;
/*     */ import tr.gov.tubitak.uekae.esya.api.common.ESYAException;
/*     */ import tr.gov.tubitak.uekae.esya.api.common.crypto.BaseSigner;
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
/*     */ public class CMSSigner
/*     */ {
/*  43 */   private static LbsConsole ms_Console = LbsConsole.getLogger(CMSSigner.class);
/*     */ 
/*     */ 
/*     */   
/*     */   public static ContentWithSignedData createCmsSigned(String sessionCode, ECertificate cert, BaseSigner signer, Properties sigProps) throws Exception {
/*  48 */     if (sessionCode == null || sessionCode.equals("")) {
/*  49 */       throw new Exception("133");
/*     */     }
/*  51 */     String tckn = cert.getSubject().getSerialNumberAttribute();
/*     */     
/*  53 */     if (tckn == null || tckn.equals("")) {
/*  54 */       throw new Exception("145");
/*     */     }
/*  56 */     String rawDataToBeSignedStr = String.valueOf(tckn) + sessionCode;
/*  57 */     byte[] rawDataToBeSigned = rawDataToBeSignedStr.getBytes();
/*     */ 
/*     */     
/*     */     try {
/*  61 */       ContentWithDigestData contentAndDigest = SignerUtility.getDigest(rawDataToBeSigned);
/*     */       
/*  63 */       byte[] content = contentAndDigest.getContent();
/*  64 */       byte[] hash = contentAndDigest.getContentHash();
/*     */       
/*  66 */       BaseSignedData bs = new BaseSignedData();
/*  67 */       SignableByteArray signableByteArray = new SignableByteArray(hash);
/*  68 */       bs.addContent((ISignable)signableByteArray);
/*     */       
/*  70 */       HashMap<String, Object> params = new HashMap<>();
/*     */       
/*  72 */       params.put(EParameters.P_VALIDATE_CERTIFICATE_BEFORE_SIGNING, Boolean.valueOf(false));
/*     */       
/*  74 */       bs.addSigner(ESignatureType.TYPE_BES, cert, signer, null, params);
/*     */       
/*  76 */       ContentWithSignedData contentWithSigned = new ContentWithSignedData();
/*  77 */       contentWithSigned.setTckn(tckn);
/*  78 */       contentWithSigned.setSessionCode(sessionCode);
/*  79 */       contentWithSigned.setSignedData(bs.getEncoded());
/*     */       
/*  81 */       return contentWithSigned;
/*     */     }
/*  83 */     catch (Exception e) {
/*     */       
/*  85 */       ms_Console.error("An error occured while creating signed CMS Data", e);
/*  86 */       throw new Exception("146", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean verifySignedCMSData(byte[] signatureBytes, String contextDir, String certType) {
/*     */     try {
/*  95 */       ValidationPolicy policy = getPolicy(contextDir, certType);
/*     */       
/*  97 */       Hashtable<String, Object> params = new Hashtable<>();
/*  98 */       params.put(EParameters.P_CERT_VALIDATION_POLICY, policy);
/*     */       
/* 100 */       SignedDataValidation sdv = new SignedDataValidation();
/*     */       
/* 102 */       SignedDataValidationResult sdvr = sdv.verify(signatureBytes, params);
/*     */       
/* 104 */       if (sdvr.getSDStatus() != SignedData_Status.ALL_VALID) {
/*     */         
/* 106 */         ms_Console.error(sdv.toString());
/* 107 */         return false;
/*     */       } 
/*     */       
/* 110 */       return true;
/*     */     
/*     */     }
/* 113 */     catch (Exception e) {
/*     */       
/* 115 */       ms_Console.error("An error occured while validating signed CMS Data", e);
/* 116 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean validateCertificate(X509Certificate x509Cert, String contextDir, String certType) throws Exception {
/*     */     try {
/* 124 */       ValidationPolicy policy = getPolicy(contextDir, certType);
/*     */       
/* 126 */       ValidationSystem vs = CertificateValidation.createValidationSystem(policy);
/* 127 */       vs.setBaseValidationTime(Calendar.getInstance());
/*     */       
/* 129 */       ECertificate certificate = new ECertificate(x509Cert.getEncoded());
/*     */       
/* 131 */       CertificateStatusInfo csi = CertificateValidation.validateCertificate(vs, certificate);
/*     */       
/* 133 */       if (csi.getCertificateStatus() != CertificateStatus.VALID) {
/*     */         
/* 135 */         ms_Console.error("Certificate status: " + csi.getCertificateStatus());
/* 136 */         return false;
/*     */       } 
/* 138 */       if (csi.getCertificateStatus() == CertificateStatus.VALID) {
/* 139 */         return true;
/*     */       }
/* 141 */     } catch (Exception e) {
/*     */       
/* 143 */       ms_Console.error("An error occured while validating certificate", e);
/*     */     } 
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static ValidationPolicy getPolicy(String contextDir, String certType) throws Exception, ESYAException {
/* 150 */     String certPolXml = JLbsFileUtil.appendPath(contextDir, "certval-policy.xml");
/* 151 */     String certPolMMXml = JLbsFileUtil.appendPath(contextDir, "certval-policy-malimuhur.xml");
/*     */     
/* 153 */     ValidationPolicy policy = new ValidationPolicy();
/*     */     
/* 155 */     if (JLbsStringUtil.isEmpty(certType))
/* 156 */       throw new Exception("215"); 
/* 157 */     if (certType.equals("nes")) {
/* 158 */       policy = PolicyReader.readValidationPolicy(certPolXml);
/* 159 */     } else if (certType.equals("e-muhur")) {
/* 160 */       policy = PolicyReader.readValidationPolicy(certPolMMXml);
/*     */     } else {
/* 162 */       throw new Exception("215");
/* 163 */     }  return policy;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\dsignature\signer\CMSSigner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */