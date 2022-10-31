/*     */ package com.lbs.dsignature.gui;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.control.interfaces.ILbsOptionPane;
/*     */ import com.lbs.dsignature.signer.CMSSigner;
/*     */ import com.lbs.dsignature.signer.ContentWithSignedData;
/*     */ import com.lbs.dsignature.signer.EnvelopedSigner;
/*     */ import com.lbs.dsignature.signer.EnvelopingSigner;
/*     */ import com.lbs.globalization.ILbsCultureInfo;
/*     */ import com.lbs.localization.DSignatureLocalizer;
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsDialog;
/*     */ import com.lbs.ws.LbsWebServiceException;
/*     */ import com.lbs.ws.LbsWebServiceOperation;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.math.BigInteger;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import java.util.Timer;
/*     */ import java.util.UUID;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import tr.gov.tubitak.bilgem.esya.certselector.CertInfo;
/*     */ import tr.gov.tubitak.bilgem.esya.certselector.ECertTreePinPanel;
/*     */ import tr.gov.tubitak.bilgem.esya.certselector.ReaderInfo;
/*     */ import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;
/*     */ import tr.gov.tubitak.uekae.esya.api.common.crypto.Algorithms;
/*     */ import tr.gov.tubitak.uekae.esya.api.common.crypto.BaseSigner;
/*     */ import tr.gov.tubitak.uekae.esya.api.common.util.bag.Pair;
/*     */ import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.BaseSmartCard;
/*     */ import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.CardType;
/*     */ import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartCard;
/*     */ import tr.gov.tubitak.uekae.esya.api.xmlsignature.config.TimestampConfig;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsSignDialog
/*     */   extends JLbsDialog
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  55 */   private static LbsConsole ms_Console = LbsConsole.getLogger(LbsSignDialog.class);
/*     */   
/*  57 */   public ECertTreePinPanel m_CertTreePinPanel = new ECertTreePinPanel();
/*  58 */   private final JButton m_BttnSign = new JButton();
/*  59 */   private final JButton m_BttnCancel = new JButton();
/*     */   
/*     */   private boolean m_Canceled;
/*     */   
/*     */   private boolean m_Ok;
/*     */   
/*     */   private ContentWithSignedData m_contentWithSigned;
/*     */   private byte[] m_signed;
/*     */   private IClientContext m_Context;
/*     */   private ILbsCultureInfo m_SelectedCulture;
/*     */   public static final int DOC_SIGN = 0;
/*     */   public static final int LOGIN = -1;
/*     */   public static final int WF = -2;
/*  72 */   public LbsSignDialogTimer m_DialogTimer = null;
/*     */ 
/*     */   
/*     */   public Timer m_Timer;
/*     */   
/*     */   private boolean signWithPrevSettings = false;
/*     */ 
/*     */   
/*     */   public LbsSignDialog(IClientContext clientContext, ILbsCultureInfo culture, int processType, String[] inputFiles, String[] outputFiles, String[] paketIDs, byte[] inputContent, String destFolder, TimestampConfig tsConfig, LbsWebServiceOperation wsop, Properties dsigProps) {
/*  81 */     this.m_Context = clientContext;
/*  82 */     this.m_SelectedCulture = culture;
/*  83 */     guiInit(processType, inputFiles, outputFiles, paketIDs, inputContent, destFolder, tsConfig, wsop, dsigProps);
/*  84 */     eventInit();
/*     */     
/*     */     try {
/*  87 */       if (JLbsConstants.DESKTOP_MODE) {
/*  88 */         UIManager.setLookAndFeel("com.lbs.laf.mac.DesktopLookAndFeel");
/*     */       } else {
/*  90 */         UIManager.setLookAndFeel("com.lbs.laf.mac.LookAndFeel");
/*  91 */       }  SwingUtilities.updateComponentTreeUI((Component)this);
/*     */     }
/*  93 */     catch (Exception exception) {}
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
/*     */   private void guiInit(final int processType, final String[] inputFiles, final String[] outputFiles, final String[] paketIDs, final byte[] inputContent, final String destFolder, final TimestampConfig tsConfig, final LbsWebServiceOperation wsop, final Properties dsigProps) {
/* 105 */     getContentPane().setLayout(new GridBagLayout());
/*     */     
/* 107 */     this.m_BttnSign.setText(DSignatureLocalizer.getResource(this.m_Context, this.m_SelectedCulture, 2002));
/* 108 */     this.m_BttnSign.setMinimumSize(new Dimension(100, 30));
/* 109 */     getContentPane().add(
/* 110 */         this.m_BttnSign, 
/* 111 */         new GridBagConstraints(2, 3, 1, 1, 1.0D, 0.0D, 13, 0, 
/* 112 */           new Insets(0, 5, 30, 5), 0, 0));
/*     */     
/* 114 */     this.m_BttnSign.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 119 */             int sonuc = ILbsOptionPane.showConfirmDialog(null, 
/* 120 */                 DSignatureLocalizer.getResource(LbsSignDialog.this.m_Context, LbsSignDialog.this.m_SelectedCulture, 72), "", 0, 
/* 121 */                 3);
/* 122 */             if (sonuc == 0) {
/*     */               
/* 124 */               LbsSignDialog.this.m_DialogTimer.cancel();
/* 125 */               LbsSignDialog.this.sign(processType, inputFiles, outputFiles, paketIDs, inputContent, destFolder, tsConfig, wsop, dsigProps);
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 130 */     this.m_BttnCancel.setText(DSignatureLocalizer.getResource(this.m_Context, this.m_SelectedCulture, 190));
/* 131 */     this.m_BttnCancel.setMinimumSize(new Dimension(100, 30));
/*     */ 
/*     */     
/* 134 */     if (this.m_Context.getVariable("EDEFTER_ONEPIN") != null && (
/* 135 */       (Integer)this.m_Context.getVariable("EDEFTER_ONEPIN")).intValue() == 1 && 
/* 136 */       this.m_Context.getVariable(JLbsConstants.EDEFTER_PINANDCERTSELECTION) != null && 
/* 137 */       this.m_Context.getVariable(JLbsConstants.EDEFTER_PINANDCERTSELECTION) instanceof ECertTreePinPanel) {
/*     */       
/* 139 */       this.signWithPrevSettings = true;
/* 140 */       eventInit();
/* 141 */       this.m_BttnSign.doClick();
/*     */     }
/*     */     else {
/*     */       
/* 145 */       getContentPane().add(
/* 146 */           (Component)this.m_CertTreePinPanel, 
/* 147 */           new GridBagConstraints(1, 1, 4, 1, 1.0D, 0.0D, 10, 2, new Insets(0, 
/* 148 */               0, 5, 0), 0, 0));
/*     */       
/* 150 */       getContentPane().add(
/* 151 */           this.m_BttnCancel, 
/* 152 */           new GridBagConstraints(4, 3, 1, 1, 1.0D, 0.0D, 17, 0, 
/* 153 */             new Insets(0, 5, 30, 0), 0, 0));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void eventInit() {
/* 160 */     addWindowListener(new WindowAdapter()
/*     */         {
/*     */           
/*     */           public void windowClosing(WindowEvent we)
/*     */           {
/* 165 */             LbsSignDialog.this.m_DialogTimer.cancel();
/* 166 */             LbsSignDialog.this.m_Canceled = true;
/*     */           }
/*     */         });
/*     */     
/* 170 */     this.m_BttnCancel.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 175 */             LbsSignDialog.this.m_DialogTimer.cancel();
/* 176 */             LbsSignDialog.this.m_Canceled = true;
/* 177 */             LbsSignDialog.this.dispose();
/*     */           }
/*     */         });
/*     */     
/* 181 */     startTimer();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startTimer() {
/* 187 */     this.m_Timer = new Timer();
/* 188 */     this.m_DialogTimer = new LbsSignDialogTimer(this.m_Timer, this);
/* 189 */     int firstStart = 1000;
/* 190 */     int period = 1000;
/* 191 */     this.m_Timer.schedule(this.m_DialogTimer, 1000L, 1000L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopTimer() {
/* 196 */     if (this.m_Timer != null)
/*     */     {
/* 198 */       this.m_Timer.cancel();
/*     */     }
/* 200 */     if (this.m_DialogTimer != null)
/*     */     {
/* 202 */       this.m_DialogTimer.cancel();
/*     */     }
/* 204 */     this.m_DialogTimer = null;
/* 205 */     this.m_Timer = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void sign(final int processType, final String[] inputFiles, final String[] outputFiles, final String[] paketIDs, final byte[] inputContent, final String destFolder, final TimestampConfig tsConfig, final LbsWebServiceOperation wsop, final Properties dsigProps) {
/* 212 */     stopTimer();
/* 213 */     Thread signThread = new Thread(new Runnable()
/*     */         {
/*     */ 
/*     */           
/*     */           public void run()
/*     */           {
/* 219 */             if (LbsSignDialog.this.signWithPrevSettings)
/* 220 */               LbsSignDialog.this.m_CertTreePinPanel = (ECertTreePinPanel)LbsSignDialog.this.m_Context.getVariable(JLbsConstants.EDEFTER_PINANDCERTSELECTION); 
/* 221 */             CertInfo cert = LbsSignDialog.this.getSelectedCertificate();
/* 222 */             if (cert == null) {
/*     */               
/* 224 */               LbsSignDialog.this.startTimer();
/*     */               
/* 226 */               ILbsOptionPane.showMessageDialog(null, DSignatureLocalizer.getResource(LbsSignDialog.this.m_Context, LbsSignDialog.this.m_SelectedCulture, 3), 
/* 227 */                   getClass().getSimpleName(), 0);
/*     */               return;
/*     */             } 
/* 230 */             LbsSignDlgProgress dlgProgress = null;
/*     */ 
/*     */             
/* 233 */             try { String PIN = LbsSignDialog.this.m_CertTreePinPanel.getPin();
/* 234 */               if (PIN == null || PIN.length() == 0) {
/*     */                 
/* 236 */                 LbsSignDialog.this.startTimer();
/* 237 */                 ILbsOptionPane.showMessageDialog(null, JLbsLocalizer.getString("UN", 
/* 238 */                       91235, 1), getClass().getSimpleName(), 0);
/*     */                 
/*     */                 return;
/*     */               } 
/* 242 */               dlgProgress = new LbsSignDlgProgress(DSignatureLocalizer.getResource(LbsSignDialog.this.m_Context, LbsSignDialog.this.m_SelectedCulture, 6), 
/* 243 */                   (Dialog)LbsSignDialog.this);
/* 244 */               dlgProgress.threadBaslat();
/* 245 */               Pair<ReaderInfo, CertInfo> selectionInfo = LbsSignDialog.this.m_CertTreePinPanel.getSelectionInfo();
/* 246 */               BaseSmartCard sc = ((ReaderInfo)selectionInfo.getObject1()).getBasicSmartCard().getBaseSmartCard();
/* 247 */               long slotNo = ((ReaderInfo)selectionInfo.getObject1()).getBasicSmartCard().getSlotNo().longValue();
/* 248 */               CardType cardType = ((ReaderInfo)selectionInfo.getObject1()).getmCardType();
/* 249 */               sc.login(PIN);
/* 250 */               X509Certificate x509Cert = cert.geteCertViewerCert().getX509Cert();
/* 251 */               BaseSigner signer = sc.getSigner(x509Cert, Algorithms.SIGNATURE_RSA_SHA256);
/* 252 */               ECertificate eCert = new ECertificate(x509Cert.getEncoded());
/* 253 */               BigInteger serialNo = x509Cert.getSerialNumber();
/*     */               
/* 255 */               String signerRole = dsigProps.getProperty("SIGNERROLE");
/*     */               
/* 257 */               if (processType == 0) {
/*     */                 
/* 259 */                 LbsSignDialog.this.m_Ok = EnvelopedSigner.createEnveloped(dlgProgress, eCert, signer, inputFiles, outputFiles, tsConfig, 
/* 260 */                     signerRole);
/*     */               }
/* 262 */               else if (processType == -1) {
/*     */                 
/* 264 */                 String sessionCode = UUID.randomUUID().toString();
/* 265 */                 LbsSignDialog.this.m_contentWithSigned = CMSSigner.createCmsSigned(sessionCode, eCert, signer, dsigProps);
/*     */               }
/* 267 */               else if (processType == -2) {
/*     */                 
/* 269 */                 LbsSignDialog.this.m_signed = EnvelopingSigner.createEnveloping(eCert, signer, inputContent, tsConfig);
/*     */               }
/*     */               else {
/*     */                 
/* 273 */                 if (sc != null) {
/*     */                   
/* 275 */                   sc.logout();
/* 276 */                   sc.closeSession();
/*     */                 } 
/*     */                 
/* 279 */                 String libName = String.valueOf(cardType.getLibName()) + ".dll";
/*     */                 
/* 281 */                 String userCertAlias = null;
/* 282 */                 SmartCard s = new SmartCard(cardType);
/* 283 */                 long[] slotList = s.getSlotList();
/* 284 */                 for (int i = 0; i < slotList.length; i++) {
/*     */                   
/* 286 */                   if (slotList[i] == slotNo) {
/*     */                     
/* 288 */                     dsigProps.setProperty("SLOTID", i);
/*     */                     
/*     */                     break;
/*     */                   } 
/*     */                 } 
/* 293 */                 long session = s.openSession(slotNo);
/* 294 */                 s.login(session, PIN);
/* 295 */                 String[] labels = s.getSignatureKeyLabels(session); byte b; int j; String[] arrayOfString1;
/* 296 */                 label107: for (j = (arrayOfString1 = labels).length, b = 0; b < j; ) { String label = arrayOfString1[b];
/*     */                   
/* 298 */                   List<byte[]> readCertificates = s.readCertificate(session, label);
/* 299 */                   for (int k = 0; k < readCertificates.size(); k++) {
/*     */                     
/* 301 */                     byte[] readCert = readCertificates.get(k);
/* 302 */                     ECertificate rCert = new ECertificate(readCert);
/* 303 */                     if (rCert.getSerialNumber().equals(serialNo)) {
/*     */                       
/* 305 */                       userCertAlias = label;
/*     */                       break label107;
/*     */                     } 
/*     */                   } 
/*     */                   b++; }
/*     */                 
/* 311 */                 if (s != null) {
/*     */                   
/* 313 */                   s.logout(session);
/* 314 */                   s.closeSession(session);
/*     */                 } 
/*     */                 
/* 317 */                 Object[] params = { inputFiles, outputFiles, paketIDs, destFolder };
/*     */                 
/* 319 */                 dsigProps.setProperty("CERT_ALIAS", userCertAlias);
/* 320 */                 dsigProps.setProperty("TOKEN_PIN", PIN);
/*     */ 
/*     */                 
/* 323 */                 LbsSignDialog.this.m_Ok = wsop.doWSOperation(processType, LbsSignDialog.this.m_Context, dsigProps, params);
/*     */               } 
/*     */               
/* 326 */               dlgProgress.threadBitir();
/*     */                }
/*     */             
/* 329 */             catch (Exception e)
/*     */             
/* 331 */             { LbsSignDialog.ms_Console.error(e, e);
/* 332 */               ILbsOptionPane.showMessageDialog(null, e.getMessage(), getClass().getSimpleName(), 0);
/* 333 */               LbsSignDialog.this.m_CertTreePinPanel.clearFields();
/* 334 */               LbsSignDialog.this.startTimer();
/* 335 */               if (dlgProgress != null)
/*     */               {
/* 337 */                 dlgProgress.threadBitir();
/*     */               }
/* 339 */               LbsSignDialog.this.m_Ok = false; }
/*     */             
/* 341 */             catch (LbsWebServiceException e)
/*     */             
/* 343 */             { LbsSignDialog.ms_Console.error(e, (Throwable)e);
/* 344 */               ILbsOptionPane.showMessageDialog(null, e.getMessage(), getClass().getSimpleName(), 0);
/* 345 */               LbsSignDialog.this.m_CertTreePinPanel.clearFields();
/* 346 */               LbsSignDialog.this.startTimer();
/* 347 */               if (dlgProgress != null)
/*     */               {
/* 349 */                 dlgProgress.threadBitir();
/*     */               }
/* 351 */               LbsSignDialog.this.m_Ok = false; }
/*     */             
/* 353 */             catch (OutOfMemoryError e)
/*     */             
/* 355 */             { LbsSignDialog.ms_Console.error(e, e);
/* 356 */               ILbsOptionPane.showMessageDialog(null, DSignatureLocalizer.getResource(LbsSignDialog.this.m_Context, LbsSignDialog.this.m_SelectedCulture, 217), 
/* 357 */                   getClass().getSimpleName(), 0);
/* 358 */               LbsSignDialog.this.m_CertTreePinPanel.clearFields();
/* 359 */               LbsSignDialog.this.startTimer();
/* 360 */               if (dlgProgress != null) {
/*     */                 
/* 362 */                 dlgProgress.threadBitir();
/* 363 */                 LbsSignDialog.this.dispose();
/*     */               } 
/* 365 */               LbsSignDialog.this.m_Ok = false; }
/*     */             
/*     */             finally
/*     */             
/* 369 */             { if (((Integer)LbsSignDialog.this.m_Context.getVariable("EDEFTER_ONEPIN")).intValue() == 1)
/*     */               {
/* 371 */                 LbsSignDialog.this.m_Context.setVariable(JLbsConstants.EDEFTER_PINANDCERTSELECTION, LbsSignDialog.this.m_Ok ? LbsSignDialog.this.m_CertTreePinPanel : null); }  }  if (((Integer)LbsSignDialog.this.m_Context.getVariable("EDEFTER_ONEPIN")).intValue() == 1) LbsSignDialog.this.m_Context.setVariable(JLbsConstants.EDEFTER_PINANDCERTSELECTION, LbsSignDialog.this.m_Ok ? LbsSignDialog.this.m_CertTreePinPanel : null);
/*     */           
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 378 */     signThread.start();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCanceled() {
/* 383 */     return this.m_Canceled;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOK() {
/* 388 */     return this.m_Ok;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getSigned() {
/* 393 */     return this.m_signed;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContentWithSignedData getContentWithSigned() {
/* 398 */     return this.m_contentWithSigned;
/*     */   }
/*     */ 
/*     */   
/*     */   public CertInfo getSelectedCertificate() {
/* 403 */     Pair<ReaderInfo, CertInfo> selectionInfo = this.m_CertTreePinPanel.getSelectionInfo();
/* 404 */     return (CertInfo)selectionInfo.getObject2();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\dsignature\gui\LbsSignDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */