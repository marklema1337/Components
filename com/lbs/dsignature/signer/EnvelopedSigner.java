/*     */ package com.lbs.dsignature.signer;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.dsignature.gui.LbsSignDlgProgress;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.security.PublicKey;
/*     */ import java.util.Calendar;
/*     */ import java.util.GregorianCalendar;
/*     */ import javax.xml.datatype.XMLGregorianCalendar;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import org.w3c.dom.Document;
/*     */ import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;
/*     */ import tr.gov.tubitak.uekae.esya.api.common.crypto.BaseSigner;
/*     */ import tr.gov.tubitak.uekae.esya.api.crypto.util.KeyUtil;
/*     */ import tr.gov.tubitak.uekae.esya.api.signature.SignatureType;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.C14nMethod;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.Context;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.DigestMethod;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.SignatureMethod;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.TransformType;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.ValidationResult;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.ValidationResultType;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.XMLSignature;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.config.TimestampConfig;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.model.Transform;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.model.Transforms;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.model.keyinfo.KeyInfoElement;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.model.keyinfo.KeyValue;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.model.xades.ClaimedRole;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.model.xades.SignerRole;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.util.XmlUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnvelopedSigner
/*     */ {
/*  53 */   private static LbsConsole ms_Console = LbsConsole.getLogger(EnvelopedSigner.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean createEnveloped(LbsSignDlgProgress dlgProgress, ECertificate cert, BaseSigner signer, String[] inputFiles, String[] outputFiles, TimestampConfig tsConfig, String signerRole) throws Exception {
/*  63 */     for (int i = 0; i < inputFiles.length; i++) {
/*     */       
/*  65 */       System.gc();
/*     */       
/*  67 */       String dosya = JLbsFileUtil.getFileName(inputFiles[i], false);
/*  68 */       if (dlgProgress != null) {
/*     */         
/*  70 */         StringBuilder sb = new StringBuilder();
/*  71 */         sb.append(JLbsLocalizer.getString("UN", 91235, 6));
/*  72 */         sb.append(" ");
/*  73 */         sb.append(i);
/*  74 */         sb.append(" / ");
/*  75 */         sb.append(inputFiles.length);
/*  76 */         dlgProgress.m_LblMesaj2.setText(sb.toString());
/*  77 */         dlgProgress.m_LblMesaj
/*  78 */           .setText(String.valueOf(JLbsLocalizer.getString("UN", 91235, 1208)) + dosya);
/*  79 */         dlgProgress.repaint();
/*     */       } 
/*     */       
/*  82 */       Document envelopeDoc = null;
/*  83 */       XMLSignature signature = null;
/*     */       
/*  85 */       Context signContext = SignerUtility.createContext(tsConfig, false, null);
/*  86 */       envelopeDoc = readDoc(inputFiles[i]);
/*     */       
/*  88 */       signContext.setDocument(envelopeDoc);
/*     */ 
/*     */       
/*  91 */       signature = new XMLSignature(signContext, false);
/*     */       
/*  93 */       envelopeDoc.getDocumentElement().appendChild(signature.getElement());
/*     */       
/*  95 */       Transforms transforms = new Transforms(signContext);
/*  96 */       transforms.addTransform(new Transform(signContext, TransformType.ENVELOPED.getUrl()));
/*     */ 
/*     */ 
/*     */       
/* 100 */       signature.addDocument("", "text/xml", transforms, DigestMethod.SHA_256, false);
/*     */       
/* 102 */       signature.getSignedInfo().setSignatureMethod(SignatureMethod.RSA_SHA256);
/* 103 */       signature.getSignedInfo().setCanonicalizationMethod(C14nMethod.INCLUSIVE_WITH_COMMENTS);
/*     */       
/* 105 */       signature.addKeyInfo(cert);
/* 106 */       PublicKey pk = KeyUtil.decodePublicKey((new ECertificate(cert.getEncoded())).getSubjectPublicKeyInfo());
/* 107 */       signature.getKeyInfo().add((KeyInfoElement)new KeyValue(signContext, pk));
/*     */       
/* 109 */       signature.getQualifyingProperties().getSignedSignatureProperties().setSigningTime(getTime());
/* 110 */       if (!JLbsStringUtil.isEmpty(signerRole))
/* 111 */         signature.getQualifyingProperties().getSignedSignatureProperties()
/* 112 */           .setSignerRole(new SignerRole(signContext, new ClaimedRole[] { new ClaimedRole(signContext, signerRole) })); 
/* 113 */       signature.sign(signer);
/* 114 */       if (tsConfig != null) {
/* 115 */         signature.upgrade(SignatureType.ES_T);
/*     */       }
/*     */       
/* 118 */       Source source = new DOMSource(envelopeDoc);
/* 119 */       Transformer transformer = TransformerFactory.newInstance().newTransformer();
/*     */       
/* 121 */       File file = null;
/* 122 */       FileOutputStream outputStream = null;
/* 123 */       StreamResult streamResult = null;
/*     */       
/*     */       try {
/* 126 */         file = new File(outputFiles[i]);
/* 127 */         outputStream = new FileOutputStream(file);
/* 128 */         streamResult = new StreamResult(outputStream);
/* 129 */         transformer.transform(source, streamResult);
/*     */       }
/*     */       finally {
/*     */         
/* 133 */         if (outputStream != null) {
/*     */           
/* 135 */           outputStream.flush();
/* 136 */           outputStream.close();
/*     */         } 
/*     */       } 
/* 139 */       ValidationResult result = signature.verify();
/* 140 */       ms_Console.debug(result.toXml());
/* 141 */       envelopeDoc = null;
/* 142 */       signature = null;
/* 143 */       if (result.getType() != ValidationResultType.VALID) {
/*     */         
/* 145 */         ms_Console.error("XAdES signature cannot be verified");
/* 146 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/* 150 */     if (dlgProgress != null) {
/*     */       
/* 152 */       dlgProgress.m_LblMesaj2.setText(String.valueOf(JLbsLocalizer.getString("UN", 91235, 6)) + 
/* 153 */           inputFiles.length + " / " + inputFiles.length);
/* 154 */       dlgProgress.m_LblMesaj.setText(JLbsLocalizer.getString("UN", 91235, 1198));
/* 155 */       dlgProgress.repaint();
/* 156 */       Thread.sleep(2000L);
/*     */     } 
/* 158 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] createEnveloped(ECertificate cert, BaseSigner signer, byte[] inputFile, TimestampConfig tsConfig, String signerRole, String resourceDirPath) throws Exception {
/* 164 */     System.gc();
/*     */     
/* 166 */     byte[] outputFile = null;
/*     */     
/* 168 */     Document envelopeDoc = null;
/* 169 */     XMLSignature signature = null;
/*     */     
/* 171 */     Context signContext = SignerUtility.createContext(tsConfig, true, resourceDirPath);
/* 172 */     envelopeDoc = readDoc(inputFile);
/*     */     
/* 174 */     signContext.setDocument(envelopeDoc);
/*     */ 
/*     */     
/* 177 */     signature = new XMLSignature(signContext, false);
/*     */     
/* 179 */     envelopeDoc.getDocumentElement().appendChild(signature.getElement());
/*     */     
/* 181 */     Transforms transforms = new Transforms(signContext);
/* 182 */     transforms.addTransform(new Transform(signContext, TransformType.ENVELOPED.getUrl()));
/*     */ 
/*     */ 
/*     */     
/* 186 */     signature.addDocument("", "text/xml", transforms, DigestMethod.SHA_256, false);
/*     */     
/* 188 */     signature.getSignedInfo().setSignatureMethod(SignatureMethod.RSA_SHA256);
/* 189 */     signature.getSignedInfo().setCanonicalizationMethod(C14nMethod.INCLUSIVE_WITH_COMMENTS);
/*     */     
/* 191 */     signature.addKeyInfo(cert);
/* 192 */     PublicKey pk = KeyUtil.decodePublicKey((new ECertificate(cert.getEncoded())).getSubjectPublicKeyInfo());
/* 193 */     signature.getKeyInfo().add((KeyInfoElement)new KeyValue(signContext, pk));
/*     */     
/* 195 */     signature.getQualifyingProperties().getSignedSignatureProperties().setSigningTime(getTime());
/* 196 */     if (!JLbsStringUtil.isEmpty(signerRole))
/* 197 */       signature.getQualifyingProperties().getSignedSignatureProperties()
/* 198 */         .setSignerRole(new SignerRole(signContext, new ClaimedRole[] { new ClaimedRole(signContext, signerRole) })); 
/* 199 */     signature.sign(signer);
/* 200 */     if (tsConfig != null) {
/* 201 */       signature.upgrade(SignatureType.ES_T);
/*     */     }
/*     */     
/* 204 */     Source source = new DOMSource(envelopeDoc);
/* 205 */     Transformer transformer = TransformerFactory.newInstance().newTransformer();
/*     */     
/* 207 */     ByteArrayOutputStream outputStream = null;
/* 208 */     StreamResult streamResult = null;
/*     */     
/*     */     try {
/* 211 */       outputStream = new ByteArrayOutputStream();
/* 212 */       streamResult = new StreamResult(outputStream);
/* 213 */       transformer.transform(source, streamResult);
/*     */       
/* 215 */       outputFile = outputStream.toByteArray();
/*     */     }
/*     */     finally {
/*     */       
/* 219 */       if (outputStream != null) {
/*     */         
/* 221 */         outputStream.flush();
/* 222 */         outputStream.close();
/*     */       } 
/*     */     } 
/*     */     
/* 226 */     ValidationResult result = signature.verify();
/* 227 */     ms_Console.debug(result.toXml());
/* 228 */     envelopeDoc = null;
/* 229 */     signature = null;
/* 230 */     if (result.getType() != ValidationResultType.VALID) {
/*     */       
/* 232 */       ms_Console.error("XAdES signature cannot be verified");
/* 233 */       return null;
/*     */     } 
/* 235 */     return outputFile;
/*     */   }
/*     */ 
/*     */   
/*     */   private static XMLGregorianCalendar getTime() {
/* 240 */     Calendar cal = new GregorianCalendar();
/* 241 */     cal.get(13);
/* 242 */     return XmlUtil.createDate(cal);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Document readDoc(String f) throws Exception {
/* 247 */     FileInputStream is = null;
/*     */ 
/*     */     
/*     */     try {
/* 251 */       DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
/* 252 */       dbf.setNamespaceAware(true);
/* 253 */       DocumentBuilder db = dbf.newDocumentBuilder();
/*     */ 
/*     */       
/* 256 */       is = new FileInputStream(f);
/*     */       
/* 258 */       return db.parse(is);
/*     */     }
/*     */     finally {
/*     */       
/* 262 */       if (is != null)
/*     */       {
/* 264 */         is.close();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Document readDoc(byte[] content) throws Exception {
/* 271 */     ByteArrayInputStream is = null;
/*     */ 
/*     */     
/*     */     try {
/* 275 */       DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
/* 276 */       dbf.setNamespaceAware(true);
/* 277 */       DocumentBuilder db = dbf.newDocumentBuilder();
/*     */ 
/*     */       
/* 280 */       is = new ByteArrayInputStream(content);
/*     */       
/* 282 */       return db.parse(is);
/*     */     }
/*     */     finally {
/*     */       
/* 286 */       if (is != null)
/*     */       {
/* 288 */         is.close();
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\dsignature\signer\EnvelopedSigner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */