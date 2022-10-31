/*     */ package com.lbs.ws;
/*     */ 
/*     */ import com.lbs.data.application.ThreadLocalVariables;
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import com.lbs.util.JLbsClientFS;
/*     */ import com.lbs.util.JLbsFileUtil;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Properties;
/*     */ import org.apache.axis2.AxisFault;
/*     */ import org.apache.axis2.client.ServiceClient;
/*     */ import org.apache.axis2.client.Stub;
/*     */ import org.apache.axis2.context.ConfigurationContext;
/*     */ import org.apache.axis2.context.ConfigurationContextFactory;
/*     */ import org.apache.rampart.handler.config.OutflowConfiguration;
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
/*     */ public class LbsWebServiceUtil
/*     */ {
/*     */   public static final String AXIS2_SIGN_PROPERTIES = "Axis2_sign.properties";
/*     */   private static final String XMLDSIG_MORE_RSA_SHA256 = "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256";
/*     */   private static final String WSU_NS = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
/*     */   private static final String SOAP11 = "http://schemas.xmlsoap.org/soap/envelope/";
/*     */   private static final String SOAP12 = "http://www.w3.org/2003/05/soap-envelope";
/*  45 */   private static HashMap<String, ConfigurationContext> ms_ConfigurationContexts = new HashMap<>();
/*  46 */   private static ConfigurationContext ms_CfgConfig = null;
/*     */ 
/*     */   
/*     */   static {
/*     */     try {
/*  51 */       ms_CfgConfig = ConfigurationContextFactory.createConfigurationContextFromFileSystem(JLbsClientFS.getRootPath(), null);
/*     */     }
/*  53 */     catch (AxisFault axisFault) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ConfigurationContext createConfigurationContextOnServer(String resPath) throws LbsWebServiceException {
/*     */     try {
/*  63 */       if (ms_ConfigurationContexts.containsKey(resPath)) {
/*  64 */         return ms_ConfigurationContexts.get(resPath);
/*     */       }
/*  66 */       ConfigurationContext cfgConfig = ConfigurationContextFactory.createConfigurationContextFromFileSystem(
/*  67 */           resPath, null);
/*  68 */       ms_ConfigurationContexts.put(resPath, cfgConfig);
/*  69 */       return cfgConfig;
/*     */     }
/*  71 */     catch (Exception e) {
/*     */       
/*  73 */       throw new LbsWebServiceException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ConfigurationContext createConfigurationContext(IClientContext context, String[] modules) throws LbsWebServiceException {
/*  80 */     return createConfigurationContextRedirect(context, modules, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ConfigurationContext createConfigurationContextRedirect(IClientContext context, String[] modules, boolean redirect) throws LbsWebServiceException {
/*     */     try {
/*  87 */       String rootPath = JLbsClientFS.getRootPath();
/*  88 */       if (modules != null) {
/*     */         
/*  90 */         String dir = JLbsFileUtil.appendPath(rootPath, "modules");
/*  91 */         File d = new File(dir);
/*  92 */         if (!d.exists()) {
/*  93 */           d.mkdir();
/*     */         }
/*  95 */         for (int i = 0; i < modules.length; i++) {
/*     */           
/*  97 */           String module = modules[i];
/*  98 */           checkModuleFile(context, module);
/*     */         } 
/*     */       } 
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
/* 122 */       if (redirect) {
/* 123 */         System.setProperty("org.apache.axiom.om.OMMetaFactory", "org.apache.axiom.om.impl.llom.factory.OMLinkedListMetaFactory");
/*     */       }
/* 125 */       ms_CfgConfig = ConfigurationContextFactory.createConfigurationContextFromFileSystem(rootPath, null);
/* 126 */       return ms_CfgConfig;
/*     */     
/*     */     }
/* 129 */     catch (Exception e) {
/*     */       
/* 131 */       throw new LbsWebServiceException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void engageModules(Stub stub, String[] modules) throws LbsWebServiceException {
/* 137 */     if (stub == null) {
/* 138 */       throw new LbsWebServiceException("Stub is null");
/*     */     }
/* 140 */     ServiceClient sc = stub._getServiceClient();
/* 141 */     if (sc == null) {
/* 142 */       throw new LbsWebServiceException("Service Client is null");
/*     */     }
/* 144 */     for (int i = 0; i < modules.length; i++) {
/*     */       
/* 146 */       String module = modules[i];
/*     */       
/*     */       try {
/* 149 */         sc.engageModule(module);
/*     */       }
/* 151 */       catch (Exception e) {
/*     */         
/* 153 */         throw new LbsWebServiceException(e.getMessage());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void engageAddressingModule(Stub stub) throws LbsWebServiceException {
/* 160 */     if (stub == null) {
/* 161 */       throw new LbsWebServiceException("Stub is null");
/*     */     }
/* 163 */     ServiceClient sc = stub._getServiceClient();
/* 164 */     if (sc == null) {
/* 165 */       throw new LbsWebServiceException("Service Client is null");
/*     */     }
/*     */     
/*     */     try {
/* 169 */       sc.engageModule("addressing");
/*     */     }
/* 171 */     catch (Exception e) {
/*     */       
/* 173 */       throw new LbsWebServiceException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void checkModuleFile(IClientContext context, String fileName) throws LbsWebServiceException {
/* 179 */     if (context == null)
/* 180 */       throw new LbsWebServiceException("context is null"); 
/* 181 */     String ext = "-1.6.0.mar";
/* 182 */     fileName = String.valueOf(fileName) + ext;
/* 183 */     String path = JLbsFileUtil.appendPath(JLbsFileUtil.appendPath(JLbsClientFS.getRootPath(), "modules"), fileName);
/* 184 */     File f = new File(path);
/* 185 */     if (f.exists()) {
/*     */       return;
/*     */     }
/*     */     
/*     */     try {
/* 190 */       byte[] moduleFile = context.getResource("/modules/" + fileName, false);
/* 191 */       if (moduleFile != null) {
/* 192 */         JLbsFileUtil.write2File(path, moduleFile);
/*     */       } else {
/* 194 */         throw new LbsWebServiceException(String.valueOf(fileName) + " not found!");
/*     */       } 
/* 196 */     } catch (FileNotFoundException e) {
/*     */       
/* 198 */       throw new LbsWebServiceException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void engageRampartModule(Stub stub) throws LbsWebServiceException {
/* 204 */     if (stub == null) {
/* 205 */       throw new LbsWebServiceException("Stub is null");
/*     */     }
/* 207 */     ServiceClient sc = stub._getServiceClient();
/* 208 */     if (sc == null) {
/* 209 */       throw new LbsWebServiceException("Service Client is null");
/*     */     }
/*     */     
/*     */     try {
/* 213 */       sc.engageModule("rampart");
/*     */     }
/* 215 */     catch (Exception e) {
/*     */       
/* 217 */       throw new LbsWebServiceException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void engageSandesha2Module(Stub stub) throws LbsWebServiceException {
/* 223 */     if (stub == null) {
/* 224 */       throw new LbsWebServiceException("Stub is null");
/*     */     }
/* 226 */     ServiceClient sc = stub._getServiceClient();
/* 227 */     if (sc == null) {
/* 228 */       throw new LbsWebServiceException("Service Client is null");
/*     */     }
/*     */     
/*     */     try {
/* 232 */       sc.engageModule("sandesha2");
/*     */     }
/* 234 */     catch (Exception e) {
/*     */       
/* 236 */       throw new LbsWebServiceException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addRampartSecurity(Stub stub, String username, String callbackClass) throws LbsWebServiceException {
/* 242 */     if (stub == null) {
/* 243 */       throw new LbsWebServiceException("Stub is null");
/*     */     }
/* 245 */     ServiceClient sc = stub._getServiceClient();
/* 246 */     if (sc == null) {
/* 247 */       throw new LbsWebServiceException("Service Client is null");
/*     */     }
/*     */     
/*     */     try {
/* 251 */       OutflowConfiguration outflowConfig = new OutflowConfiguration();
/* 252 */       outflowConfig.setActionItems("UsernameToken");
/* 253 */       outflowConfig.setUser(username);
/* 254 */       outflowConfig.setPasswordCallbackClass(callbackClass);
/* 255 */       sc.getOptions().setProperty("OutflowSecurity", outflowConfig.getProperty());
/*     */     }
/* 257 */     catch (Exception e) {
/*     */       
/* 259 */       throw new LbsWebServiceException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addRampartSecTSandSign(Stub stub, String callbackClass, String userCertAlias, boolean serverSide) throws LbsWebServiceException {
/* 265 */     if (stub == null) {
/* 266 */       throw new LbsWebServiceException("Stub is null");
/*     */     }
/* 268 */     ServiceClient sc = stub._getServiceClient();
/* 269 */     if (sc == null) {
/* 270 */       throw new LbsWebServiceException("Service Client is null");
/*     */     }
/* 272 */     String sigPropFile = "Axis2_sign.properties";
/* 273 */     if (!serverSide) {
/*     */       
/* 275 */       String tempDirectory = JLbsFileUtil.getTempDirectory();
/* 276 */       sigPropFile = JLbsFileUtil.appendPath(tempDirectory, "Axis2_sign.properties");
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 281 */       OutflowConfiguration ofc = new OutflowConfiguration();
/* 282 */       ofc.setActionItems("Timestamp Signature");
/* 283 */       ofc.setUser(userCertAlias);
/* 284 */       ofc.setPasswordCallbackClass(callbackClass);
/* 285 */       ofc.setSignaturePropFile(sigPropFile);
/* 286 */       ofc.setSignatureKeyIdentifier("DirectReference");
/* 287 */       ofc.setSignatureAlgorithm("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256");
/* 288 */       ofc.setSignatureParts("{Element}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp;{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body;");
/* 289 */       sc.getOptions().setProperty("OutflowSecurity", ofc.getProperty());
/*     */     
/*     */     }
/* 292 */     catch (Exception e) {
/*     */       
/* 294 */       throw new LbsWebServiceException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void prepeareSOAPSignaturePropFile(String moduleDll, String encPIN, String slotID, boolean serverSide, String filePath) throws FileNotFoundException, IOException {
/* 300 */     Properties soapSignatureProps = prepeareSOAPSignatureProps(moduleDll, encPIN, slotID);
/*     */     
/* 302 */     String tempFile = filePath;
/* 303 */     if (!serverSide) {
/*     */       
/* 305 */       String tempDirectory = JLbsFileUtil.getTempDirectory();
/* 306 */       tempFile = JLbsFileUtil.appendPath(tempDirectory, "Axis2_sign.properties");
/*     */     } 
/* 308 */     if (!StringUtil.isEmpty(tempFile)) {
/*     */       
/* 310 */       OutputStream configStream = null;
/*     */       
/*     */       try {
/* 313 */         configStream = new FileOutputStream(tempFile);
/* 314 */         soapSignatureProps.store(configStream, (String)null);
/*     */       }
/*     */       finally {
/*     */         
/* 318 */         if (configStream != null)
/* 319 */           configStream.close(); 
/* 320 */         configStream = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static Properties prepeareSOAPSignatureProps(String moduleDllPath, String encPIN, String slotID) {
/* 327 */     Properties soapSignatureProps = new Properties();
/* 328 */     soapSignatureProps.setProperty("org.apache.ws.security.crypto.merlin.keystore.file", moduleDllPath);
/* 329 */     soapSignatureProps.setProperty("org.apache.ws.security.crypto.merlin.keystore.type", "PKCS11");
/* 330 */     soapSignatureProps.setProperty("org.apache.ws.security.crypto.merlin.keystore.private.password", encPIN);
/* 331 */     soapSignatureProps.setProperty("org.apache.ws.security.crypto.provider", "com.lbs.ws.LbsWSSecurityCrypto");
/* 332 */     soapSignatureProps.setProperty("org.apache.ws.security.crypto.merlin.keystore.provider", "SunPKCS11");
/* 333 */     if (!JLbsStringUtil.isEmpty(slotID))
/* 334 */       soapSignatureProps.setProperty("org.apache.ws.security.crypto.merlin.module.slotid", slotID); 
/* 335 */     return soapSignatureProps;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isService() {
/* 340 */     Object threadLocalValue = ThreadLocalVariables.getThreadLocalValue("isService");
/* 341 */     if (threadLocalValue != null)
/* 342 */       return ((Boolean)threadLocalValue).booleanValue(); 
/* 343 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\ws\LbsWebServiceUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */