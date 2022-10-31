/*    */ package com.lbs.dsignature.signer;
/*    */ 
/*    */ import com.lbs.console.LbsConsole;
/*    */ import com.lbs.util.JLbsFileUtil;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.security.PublicKey;
/*    */ import java.util.Calendar;
/*    */ import java.util.GregorianCalendar;
/*    */ import java.util.UUID;
/*    */ import javax.xml.datatype.XMLGregorianCalendar;
/*    */ import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;
/*    */ import tr.gov.tubitak.uekae.esya.api.common.crypto.BaseSigner;
/*    */ import tr.gov.tubitak.uekae.esya.api.crypto.util.KeyUtil;
/*    */ import tr.gov.tubitak.uekae.esya.api.signature.SignatureType;
/*    */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.C14nMethod;
/*    */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.Context;
/*    */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.SignatureMethod;
/*    */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.ValidationResult;
/*    */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.ValidationResultType;
/*    */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.XMLSignature;
/*    */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.config.TimestampConfig;
/*    */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.model.keyinfo.KeyInfoElement;
/*    */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.model.keyinfo.KeyValue;
/*    */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.util.XmlUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnvelopingSigner
/*    */ {
/* 31 */   private static LbsConsole ms_Console = LbsConsole.getLogger(EnvelopingSigner.class);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static byte[] createEnveloping(ECertificate cert, BaseSigner signer, byte[] inputContent, TimestampConfig tsConfig) throws Exception {
/* 41 */     byte[] outputFile = null;
/*    */     
/* 43 */     XMLSignature signature = null;
/* 44 */     Context signContext = SignerUtility.createContext(tsConfig, false, null);
/* 45 */     signature = new XMLSignature(signContext);
/*    */     
/* 47 */     String tempDirectory = JLbsFileUtil.getTempDirectory();
/* 48 */     String tempFile = JLbsFileUtil.appendPath(tempDirectory, UUID.randomUUID().toString());
/* 49 */     JLbsFileUtil.write2File(tempFile, inputContent);
/*    */     
/* 51 */     signature.addDocument(tempFile, "text/xml", true);
/* 52 */     signature.getSignedInfo().setSignatureMethod(SignatureMethod.RSA_SHA256);
/* 53 */     signature.getObject(1).setEncoding(null);
/* 54 */     signature.getSignedInfo().setCanonicalizationMethod(C14nMethod.INCLUSIVE_WITH_COMMENTS);
/* 55 */     signature.addKeyInfo(cert);
/* 56 */     PublicKey pk = KeyUtil.decodePublicKey((new ECertificate(cert.getEncoded())).getSubjectPublicKeyInfo());
/* 57 */     signature.getKeyInfo().add((KeyInfoElement)new KeyValue(signContext, pk));
/* 58 */     signature.getQualifyingProperties().getSignedSignatureProperties().setSigningTime(getTime());
/*    */     
/* 60 */     signature.sign(signer);
/* 61 */     if (tsConfig != null) {
/* 62 */       signature.upgrade(SignatureType.ES_T);
/*    */     }
/* 64 */     ByteArrayOutputStream outputStream = null;
/*    */     
/*    */     try {
/* 67 */       outputStream = new ByteArrayOutputStream();
/* 68 */       signature.write(outputStream);
/*    */       
/* 70 */       outputFile = outputStream.toByteArray();
/*    */     }
/*    */     finally {
/*    */       
/* 74 */       if (outputStream != null) {
/*    */         
/* 76 */         outputStream.flush();
/* 77 */         outputStream.close();
/*    */       } 
/*    */     } 
/*    */     
/* 81 */     ValidationResult result = signature.verify();
/* 82 */     ms_Console.debug(result.toXml());
/* 83 */     signature = null;
/* 84 */     JLbsFileUtil.deleteFile(tempFile);
/* 85 */     if (result.getType() != ValidationResultType.VALID) {
/*    */       
/* 87 */       ms_Console.error("XAdES signature cannot be verified");
/* 88 */       return null;
/*    */     } 
/* 90 */     return outputFile;
/*    */   }
/*    */ 
/*    */   
/*    */   private static XMLGregorianCalendar getTime() {
/* 95 */     Calendar cal = new GregorianCalendar();
/* 96 */     cal.get(13);
/* 97 */     return XmlUtil.createDate(cal);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\dsignature\signer\EnvelopingSigner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */