/*     */ package com.lbs.smtp;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.console.LbsLevel;
/*     */ import com.lbs.crypto.JLbsBase64;
/*     */ import com.lbs.mail.JLbsMailAddress;
/*     */ import com.lbs.mail.JLbsMailAddressList;
/*     */ import com.lbs.mail.JLbsMailBinaryContent;
/*     */ import com.lbs.mail.JLbsMailContent;
/*     */ import com.lbs.mail.JLbsMailHTMLContent;
/*     */ import com.lbs.mail.JLbsMailMessage;
/*     */ import com.lbs.mail.JLbsMailMultipartContent;
/*     */ import com.lbs.util.JLbsConvertUtil;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.InetAddress;
/*     */ import java.net.Socket;
/*     */ import java.net.UnknownHostException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsSMTP
/*     */ {
/*     */   private final BufferedReader m_InStream;
/*     */   private final DataOutputStream m_OutStream;
/*     */   private Socket m_Socket;
/*     */   private final InetAddress m_Address;
/*     */   private String m_LastResp;
/*  40 */   private static final transient LbsConsole ms_Logger = LbsConsole.getLogger("Transport.JLbsSMTP");
/*     */ 
/*     */   
/*     */   public JLbsSMTP(String hostName) throws NullPointerException, IOException, UnknownHostException {
/*  44 */     if (hostName == null)
/*  45 */       throw new NullPointerException("JLbsSMTP"); 
/*  46 */     this.m_Address = InetAddress.getByName(hostName);
/*  47 */     this.m_Socket = new Socket(this.m_Address, 25);
/*  48 */     this.m_InStream = new BufferedReader(new InputStreamReader(this.m_Socket.getInputStream()));
/*  49 */     this.m_OutStream = new DataOutputStream(this.m_Socket.getOutputStream());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cmdHelo(String address) throws JLbsSMTPCommandException {
/*  54 */     boolean result = false;
/*     */     
/*     */     try {
/*  57 */       result = sendCmdString("EHLO " + address, true);
/*  58 */       skipInputBuffer();
/*     */     }
/*  60 */     catch (JLbsSMTPCommandException jLbsSMTPCommandException) {}
/*     */ 
/*     */     
/*  63 */     if (!result)
/*     */       
/*     */       try {
/*  66 */         result = sendCmdString("HELO " + address, true);
/*  67 */         skipInputBuffer();
/*     */       }
/*  69 */       catch (JLbsSMTPCommandException e) {
/*     */         
/*  71 */         skipInputBuffer();
/*  72 */         throw e;
/*     */       }  
/*  74 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cmdAuth(String user, String pass) throws JLbsSMTPCommandException {
/*  79 */     boolean result = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     if (!result) {
/*     */       
/*  89 */       result = sendCmdString("AUTH LOGIN", true);
/*  90 */       if (result) {
/*  91 */         result = checkBase64(this.m_LastResp, "Username:");
/*     */       } else {
/*  93 */         ms_Logger.debug("AADM_Err: AUTH LOGIN failed!");
/*     */       } 
/*     */       
/*  96 */       if (result) {
/*  97 */         result = sendCmdString(JLbsBase64.encodeString(user), true);
/*     */       } else {
/*  99 */         ms_Logger.debug("AADM_Err: Encode user failed!");
/*     */       } 
/*     */       
/* 102 */       if (result) {
/* 103 */         result = checkBase64(this.m_LastResp, "Password:");
/*     */       } else {
/* 105 */         ms_Logger.debug("AADM_Err: Cmd user-name failed!");
/*     */       } 
/*     */       
/* 108 */       if (result) {
/* 109 */         result = sendCmdString(JLbsBase64.encodeString(pass), true);
/*     */       } else {
/* 111 */         ms_Logger.debug("AADM_Err: Encode password failed!");
/*     */       } 
/*     */       
/* 114 */       if (!result) {
/* 115 */         ms_Logger.debug("AADM_Err: Cmd password failed!");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 120 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cmdMail(String address) throws JLbsSMTPCommandException {
/* 125 */     return sendCmdString("MAIL FROM:<" + address + ">", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cmdRcpt(String address) throws JLbsSMTPCommandException {
/* 130 */     return sendCmdString("RCPT TO:<" + address + ">", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cmdReset() throws JLbsSMTPCommandException {
/* 135 */     return sendCmdString("RSET", true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cmdQuit() {
/*     */     try {
/* 142 */       return sendCmdString("QUIT", false);
/*     */     }
/* 144 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 147 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void send(JLbsMailMessage message) throws JLbsSMTPCommandException {
/*     */     try {
/* 154 */       cmdReset();
/* 155 */       JLbsMailAddress sender = message.getMailFrom();
/* 156 */       cmdMail(sender.getMailAddress());
/*     */       
/* 158 */       sendRecpList(message.getMailTo());
/* 159 */       sendRecpList(message.getMailToCC());
/* 160 */       sendRecpList(message.getMailToBCC());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 169 */       skipInputBuffer();
/*     */       
/* 171 */       sendCmdString("DATA", true);
/* 172 */       this.m_OutStream.write(message.getMailHeaders().getBytes());
/* 173 */       JLbsMailContent content = message.getMailContent();
/* 174 */       if (content != null)
/* 175 */         this.m_OutStream.writeBytes(content.getEncodedMessage()); 
/* 176 */       this.m_OutStream.writeByte(13);
/* 177 */       this.m_OutStream.writeByte(10);
/* 178 */       sendCmdString(".", true);
/*     */     }
/* 180 */     catch (JLbsSMTPCommandException e) {
/*     */       
/* 182 */       throw e;
/*     */     }
/* 184 */     catch (Exception ioe) {
/*     */       
/* 186 */       throw new JLbsSMTPCommandException("Exception:" + ioe.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void skipInputBuffer() {
/*     */     try {
/* 195 */       Thread.sleep(100L);
/* 196 */       while (this.m_InStream.ready())
/*     */       {
/* 198 */         if (ms_Logger.isEnabledFor2(LbsLevel.TRACE)) {
/* 199 */           ms_Logger.trace("Skip:" + this.m_InStream.readLine());
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 205 */           String line = this.m_InStream.readLine();
/* 206 */           if (!JLbsStringUtil.isEmpty(line))
/* 207 */             line = null; 
/*     */         } 
/* 209 */         Thread.sleep(25L);
/*     */       }
/*     */     
/* 212 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 219 */     if (this.m_Socket == null || this.m_Socket.isClosed()) {
/*     */       
/* 221 */       this.m_Socket = null;
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/* 226 */       cmdQuit();
/* 227 */       this.m_Socket.close();
/* 228 */       this.m_Socket = null;
/*     */     }
/* 230 */     catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void finalize() {
/* 239 */     close();
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkResponse(String resp) throws JLbsSMTPCommandException {
/* 244 */     if (resp == null)
/*     */       return; 
/* 246 */     if (resp.length() > 2) {
/*     */       
/* 248 */       String code = resp.substring(0, 3);
/* 249 */       this.m_LastResp = resp.substring(3).trim();
/* 250 */       int codeVal = JLbsConvertUtil.str2IntDef(code, 0);
/* 251 */       if (codeVal >= 200 && codeVal < 400)
/*     */         return; 
/* 253 */       throw new JLbsSMTPCommandException(this.m_LastResp);
/*     */     } 
/* 255 */     throw new JLbsSMTPCommandException("Command response is invalid:" + resp);
/*     */   }
/*     */ 
/*     */   
/*     */   private void sendRecpList(JLbsMailAddressList recipients) throws JLbsSMTPCommandException {
/* 260 */     if (recipients == null)
/*     */       return; 
/* 262 */     for (int i = 0; i < recipients.size(); i++) {
/*     */       
/* 264 */       JLbsMailAddress rcpt = recipients.getAddress(i);
/* 265 */       cmdRcpt(rcpt.getMailAddress());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean sendCmdString(String cmd, boolean checkResp) throws JLbsSMTPCommandException {
/*     */     try {
/* 273 */       this.m_OutStream.writeBytes(cmd);
/* 274 */       this.m_OutStream.writeByte(13);
/* 275 */       this.m_OutStream.writeByte(10);
/* 276 */       this.m_OutStream.flush();
/* 277 */       String resp = this.m_InStream.readLine();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 283 */       ms_Logger.trace("AADM: command-->" + cmd + "\n" + "AADM: response-->" + resp);
/* 284 */       if (checkResp)
/* 285 */         checkResponse(resp); 
/* 286 */       return true;
/*     */     }
/* 288 */     catch (JLbsSMTPCommandException e) {
/*     */       
/* 290 */       throw e;
/*     */     }
/* 292 */     catch (Exception e) {
/*     */       
/* 294 */       ms_Logger.error(e.getMessage(), e);
/*     */ 
/*     */ 
/*     */       
/* 298 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void waitForInput(int loopCnt) {
/* 303 */     if (this.m_InStream == null) {
/*     */       return;
/*     */     }
/*     */     
/* 307 */     while (loopCnt > 0 && !this.m_InStream.ready()) {
/*     */       try {
/* 309 */         Thread.sleep(100L);
/* 310 */         loopCnt--;
/*     */       
/*     */       }
/* 313 */       catch (Exception exception) {
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean checkBase64(String encoded, String plain) {
/*     */     try {
/* 322 */       String s = null;
/*     */       
/*     */       try {
/* 325 */         s = JLbsBase64.decodeString(encoded.trim());
/*     */       }
/* 327 */       catch (Exception exception) {}
/*     */ 
/*     */       
/* 330 */       if (JLbsStringUtil.areEqual(s, plain))
/* 331 */         return true; 
/* 332 */       if (this.m_InStream == null)
/* 333 */         return false; 
/* 334 */       waitForInput(50);
/* 335 */       while (this.m_InStream.ready()) {
/*     */         
/* 337 */         s = this.m_InStream.readLine();
/* 338 */         if (s != null) {
/*     */           
/* 340 */           if (s.length() <= 4)
/*     */             continue; 
/* 342 */           s = s.substring(4);
/*     */           
/*     */           try {
/* 345 */             s = JLbsBase64.decodeString(s);
/*     */           }
/* 347 */           catch (Exception exception) {}
/*     */         } 
/*     */ 
/*     */         
/* 351 */         if (JLbsStringUtil.areEqual(s, plain)) {
/* 352 */           return true;
/*     */         }
/*     */       } 
/* 355 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 358 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsMailMessage createPlainMessage(String mailto) {
/* 363 */     JLbsMailMessage message = new JLbsMailMessage();
/* 364 */     message.setMailFrom("Gokay Gunaydin", "gokayg@logo.com.tr");
/* 365 */     message.setSubject("Plain Text");
/* 366 */     message.addMailTo("Gokay Gunaydin", mailto);
/* 367 */     message.setMailContentText("Al sana deneme text.\nBu da ikinci satir olsun.");
/* 368 */     return message;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsMailMessage createHTMLMessage(String mailto) {
/* 373 */     JLbsMailMessage message = new JLbsMailMessage();
/* 374 */     message.setMailFrom("ADMIN", "admin@localhost");
/* 375 */     message.setSubject("HTML Text");
/* 376 */     message.addMailTo("Gokay Gunaydin", mailto);
/* 377 */     message.setMailContentHTML("<html><body><b> Al sana text</b><hr>Ikinci satir</body></html>");
/* 378 */     return message;
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] readFile(String path) {
/* 383 */     FileInputStream stream = null;
/* 384 */     ByteArrayOutputStream outStream = null;
/*     */     
/*     */     try {
/* 387 */       stream = new FileInputStream(path);
/* 388 */       outStream = new ByteArrayOutputStream();
/* 389 */       byte[] buffer = new byte[4096];
/*     */       int read;
/* 391 */       while ((read = stream.read(buffer, 0, buffer.length)) > 0)
/* 392 */         outStream.write(buffer, 0, read); 
/* 393 */       stream.close();
/* 394 */       return outStream.toByteArray();
/*     */     }
/* 396 */     catch (Exception exception) {
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/* 403 */       if (outStream != null) {
/*     */         
/*     */         try {
/*     */           
/* 407 */           outStream.close();
/*     */         }
/* 409 */         catch (IOException iOException) {}
/*     */       }
/*     */ 
/*     */       
/* 413 */       if (stream != null) {
/*     */         
/*     */         try {
/*     */           
/* 417 */           stream.close();
/*     */         }
/* 419 */         catch (IOException iOException) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 424 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsMailMessage createBinaryMessage(String mailto, String path) {
/* 429 */     JLbsMailMessage message = new JLbsMailMessage();
/* 430 */     message.setMailFrom("ADMIN", "admin@localhost");
/* 431 */     message.setSubject("Binary Text");
/* 432 */     message.addMailTo("Gokay Gunaydin", mailto);
/*     */     
/*     */     try {
/* 435 */       JLbsMailBinaryContent content = new JLbsMailBinaryContent("image/jpeg");
/* 436 */       content.setData(readFile(path));
/* 437 */       content.setFileName("a.jpg");
/* 438 */       message.setMailContent((JLbsMailContent)content);
/*     */     }
/* 440 */     catch (Exception exception) {}
/*     */ 
/*     */     
/* 443 */     return message;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsMailMessage createCompositeMessage(String mailto, String path) {
/* 448 */     JLbsMailMessage message = new JLbsMailMessage();
/* 449 */     message.setMailFrom("ADMIN", "admin@localhost");
/* 450 */     message.setSubject("Composite Text");
/* 451 */     message.addMailTo("Gokay Gunaydin", mailto);
/*     */     
/*     */     try {
/* 454 */       JLbsMailMultipartContent content = new JLbsMailMultipartContent();
/* 455 */       content.addContent((JLbsMailContent)new JLbsMailHTMLContent("<html><body><b> Al sana text</b><hr>Ikinci satir</body></html>"));
/* 456 */       JLbsMailBinaryContent binContent = new JLbsMailBinaryContent("image/jpeg");
/* 457 */       binContent.setData(readFile(path));
/* 458 */       binContent.setFileName("a.jpg");
/* 459 */       content.addContent((JLbsMailContent)binContent);
/* 460 */       binContent = new JLbsMailBinaryContent("image/jpeg");
/* 461 */       binContent.setData(readFile(path));
/* 462 */       binContent.setFileName("b.jpg");
/* 463 */       content.addContent((JLbsMailContent)binContent);
/* 464 */       message.setMailContent((JLbsMailContent)content);
/*     */     }
/* 466 */     catch (Exception exception) {}
/*     */ 
/*     */     
/* 469 */     return message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/* 477 */       JLbsSMTP smtp = new JLbsSMTP("localhost");
/*     */       
/* 479 */       smtp.cmdHelo("goki-desktop");
/* 480 */       smtp.cmdAuth("deneme@localhost", "1");
/*     */       
/* 482 */       smtp.send(createHTMLMessage("deneme@localhost"));
/*     */ 
/*     */ 
/*     */       
/* 486 */       smtp.cmdQuit();
/*     */     }
/* 488 */     catch (Exception e) {
/*     */       
/* 490 */       System.err.println(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\smtp\JLbsSMTP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */