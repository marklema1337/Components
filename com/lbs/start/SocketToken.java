/*     */ package com.lbs.start;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.net.InetAddress;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketTimeoutException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.security.PrivilegedExceptionAction;
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
/*     */ public class SocketToken
/*     */ {
/*     */   private static ServerSocket ms_Instance;
/*     */   private static final int DEFAULT_PORT_NUMBER = 50000;
/*     */   private static final int DUMMY_PORT_NUMBER = 40000;
/*     */   private static Thread listenThread;
/*  37 */   private static InetAddress ms_Localhost = InetAddress.getLoopbackAddress();
/*     */   
/*     */   public static final int PORT_TOKEN_CREATED = 0;
/*     */   
/*     */   public static final int PORT_OTHER_CREATED = 1;
/*     */   
/*     */   public static final int PORT_EMPTY = 2;
/*     */   private static final int DUMMY_RETRY_COUNT = 100;
/*     */   public static final String PARAM_FIRM = "firm";
/*     */   public static final String PARAM_LANGUAGE = "lang";
/*     */   public static final String PARAM_TOKEN = "token";
/*     */   public static final String PARAM_USER = "user";
/*     */   public static final String PARAM_FRESH_PORT = "freshport";
/*     */   
/*     */   public static int createPortNumber(int userId, int firmNr) {
/*  52 */     int port = 50000 + firmNr * 100;
/*  53 */     port += userId;
/*  54 */     return port;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isAlreadyOpenedByUs(int port) {
/*  59 */     if (ms_Instance != null)
/*  60 */       return true; 
/*  61 */     if (checkPortInUse(port) == 0)
/*  62 */       return true; 
/*  63 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int checkPortInUse(int port) {
/*  68 */     System.out.println("checkPortInUse invoked " + port);
/*     */     
/*  70 */     Socket s = null;
/*  71 */     ObjectOutputStream out = null;
/*  72 */     ObjectInputStream in = null;
/*  73 */     String message = null;
/*     */ 
/*     */ 
/*     */     
/*  77 */     try { s = new Socket(ms_Localhost, port);
/*  78 */       s.setSoTimeout(3000);
/*  79 */       System.out.println("Connected to localhost in port " + port);
/*  80 */       out = new ObjectOutputStream(s.getOutputStream());
/*  81 */       out.flush();
/*     */ 
/*     */       
/*     */       try {
/*  85 */         in = new ObjectInputStream(s.getInputStream());
/*  86 */         message = (String)in.readObject();
/*  87 */         System.out.println("server>" + message);
/*  88 */         out.writeObject("LBS_Hello");
/*  89 */         out.flush();
/*  90 */         message = (String)in.readObject();
/*  91 */         if (message != null && message.equals("LBS_Hello"))
/*     */         {
/*  93 */           System.out.println("PORT_TOKEN_CREATED");
/*  94 */           return 0;
/*     */         }
/*     */       
/*  97 */       } catch (SocketTimeoutException classNot) {
/*     */         
/*  99 */         System.err.println("data received in unknown format");
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
/*     */       try {
/* 113 */         if (in != null)
/* 114 */           in.close(); 
/* 115 */         if (out != null) {
/* 116 */           out.close();
/*     */         }
/* 118 */       } catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */       
/* 122 */       if (s != null)
/*     */ 
/*     */         
/*     */         try { 
/* 126 */           s.close(); }
/*     */         
/* 128 */         catch (IOException e)
/*     */         
/* 130 */         { e.printStackTrace(); }   } catch (Exception e) { System.out.println("PORT_EMPTY"); return 2; } finally { try { if (in != null) in.close();  if (out != null) out.close();  } catch (IOException iOException) {} if (s != null) try { s.close(); } catch (IOException e) { e.printStackTrace(); }
/*     */       
/*     */        }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean startListen(int userId, int firmNr, ITokenProcessor processor, String token) {
/* 142 */     if (ms_Instance != null) {
/* 143 */       return true;
/*     */     }
/*     */     try {
/* 146 */       int port = createPortNumber(userId, firmNr);
/* 147 */       int state = checkPortInUse(port);
/* 148 */       if (state != 2) {
/*     */         
/* 150 */         port = createDummyPortNumber(userId, firmNr);
/* 151 */         if (port == 0)
/*     */         {
/* 153 */           return false;
/*     */         }
/*     */ 
/*     */         
/* 157 */         initListen(port, processor);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 162 */         initListen(port, processor);
/*     */       } 
/* 164 */       writePortToTemp(getPortFileName(userId, firmNr), port);
/* 165 */       if (token != null && token.length() > 0) {
/* 166 */         processor.processToken(token);
/*     */       }
/* 168 */     } catch (Exception e) {
/*     */       
/* 170 */       e.printStackTrace();
/*     */     } 
/* 172 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int createDummyPortNumber(int userId, int firmNr) {
/* 177 */     int port = 40000 + firmNr * 100;
/* 178 */     port += userId;
/* 179 */     for (int i = 0; i < 100; i++) {
/*     */       
/* 181 */       if (checkPortInUse(port) == 2) {
/* 182 */         return port;
/*     */       }
/* 184 */       port++;
/*     */     } 
/* 186 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void initListen(final int port, final ITokenProcessor processor) throws IOException {
/*     */     try {
/* 194 */       ms_Instance = AccessController.<ServerSocket>doPrivileged(new PrivilegedExceptionAction<ServerSocket>()
/*     */           {
/*     */             public ServerSocket run() throws IOException
/*     */             {
/* 198 */               return new ServerSocket(port);
/*     */             }
/*     */           });
/*     */     }
/* 202 */     catch (PrivilegedActionException e) {
/*     */       
/* 204 */       System.err.println(e.getMessage());
/*     */     } 
/*     */     
/* 207 */     System.out.println("START_LISTEN Connected to localhost in port " + port);
/*     */     
/* 209 */     processor.portListenStarted(port);
/*     */     
/* 211 */     listenThread = new Thread(new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 215 */             Socket connection = null;
/* 216 */             ObjectOutputStream out = null;
/* 217 */             ObjectInputStream in = null;
/* 218 */             String message = null;
/*     */ 
/*     */             
/*     */             while (true) {
/*     */               try {
/* 223 */                 connection = SocketToken.ms_Instance.accept();
/* 224 */                 System.out.println("Connection received from " + connection.getInetAddress().getHostName() + "Port:" + connection
/* 225 */                     .getPort());
/* 226 */                 out = new ObjectOutputStream(connection.getOutputStream());
/* 227 */                 out.flush();
/* 228 */                 in = new ObjectInputStream(connection.getInputStream());
/* 229 */                 out.writeObject("Connection successful");
/* 230 */                 out.flush();
/* 231 */                 System.out.println("server>- Connection successful");
/*     */ 
/*     */                 
/*     */                 try {
/* 235 */                   message = (String)in.readObject();
/* 236 */                   System.out.println("client>" + message);
/* 237 */                   if (message.equals("LBS_Hello")) {
/*     */                     
/* 239 */                     out.writeObject("LBS_Hello");
/* 240 */                     out.flush();
/*     */                   } else {
/*     */ 
/*     */                     
/*     */                     try {
/*     */                       
/* 246 */                       if (processor != null) {
/* 247 */                         processor.processToken(message);
/*     */                       }
/*     */                     } finally {
/*     */                       
/* 251 */                       out.writeObject("SUCCESS");
/* 252 */                       out.flush();
/*     */                     }
/*     */                   
/*     */                   } 
/* 256 */                 } catch (ClassNotFoundException classnot) {
/*     */                   
/* 258 */                   System.err.println("Data received in unknown format");
/*     */                 }
/*     */               
/* 261 */               } catch (Exception e) {
/*     */                 
/* 263 */                 e.printStackTrace();
/*     */               } finally {
/*     */ 
/*     */                 
/*     */                 try {
/*     */                   
/* 269 */                   if (in != null)
/* 270 */                     in.close(); 
/* 271 */                   if (out != null)
/* 272 */                     out.close(); 
/* 273 */                   if (connection != null) {
/* 274 */                     connection.close();
/*     */                   }
/* 276 */                 } catch (Exception e) {
/*     */                   
/* 278 */                   e.printStackTrace();
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 285 */     listenThread.setDaemon(true);
/* 286 */     listenThread.setName("LbsSocketListener");
/* 287 */     listenThread.setPriority(1);
/* 288 */     listenThread.start();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getPortFileName(int userId, int firmId) {
/* 294 */     return JLbsContextLocator.getClientRootDirectory() + "_TOKEN_" + userId + "_" + firmId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writePortToTemp(String fileName, int port) {
/*     */     try {
/* 301 */       File f = new File(fileName);
/*     */       
/* 303 */       FileWriter fstream = new FileWriter(f);
/* 304 */       BufferedWriter out = new BufferedWriter(fstream);
/* 305 */       out.write(port);
/* 306 */       out.close();
/*     */     }
/* 308 */     catch (Exception e) {
/*     */       
/* 310 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int loadPortFromTemp(String fileName) {
/* 316 */     int result = 0;
/*     */     
/*     */     try {
/* 319 */       File f = new File(fileName);
/* 320 */       if (f.exists())
/*     */       {
/*     */         
/* 323 */         FileReader fstream = new FileReader(f);
/* 324 */         BufferedReader in = new BufferedReader(fstream);
/* 325 */         String strLine = in.readLine();
/* 326 */         in.close();
/* 327 */         if (strLine != null) {
/* 328 */           result = Integer.valueOf(strLine).intValue();
/*     */         }
/*     */       }
/*     */     
/* 332 */     } catch (Exception e) {
/*     */       
/* 334 */       e.printStackTrace();
/*     */     } 
/* 336 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean deletePortFromTemp(String fileName) {
/*     */     try {
/* 343 */       File f = new File(fileName);
/* 344 */       if (f.exists()) {
/* 345 */         return f.delete();
/*     */       }
/* 347 */     } catch (Exception e) {
/*     */       
/* 349 */       e.printStackTrace();
/*     */     } 
/* 351 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendToken(String token, final int port) {
/* 359 */     Socket s = null;
/* 360 */     ObjectOutputStream out = null;
/* 361 */     ObjectInputStream in = null;
/*     */     
/*     */     try {
/* 364 */       long start = System.currentTimeMillis();
/* 365 */       System.out.println("Initiating socket for token '" + token + "'");
/* 366 */       if (ms_Instance == null) {
/*     */         
/* 368 */         System.out.println("Connection to port : " + port + " for token '" + token + "'");
/*     */         
/*     */         try {
/* 371 */           s = AccessController.<Socket>doPrivileged(new PrivilegedExceptionAction<Socket>()
/*     */               {
/*     */                 public Socket run() throws IOException
/*     */                 {
/* 375 */                   return new Socket(SocketToken.ms_Localhost, port);
/*     */                 }
/*     */               });
/*     */         }
/* 379 */         catch (PrivilegedActionException e) {
/*     */           
/* 381 */           System.err.println(e.getMessage());
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 386 */         System.out.println("Connection to local port for token '" + token + "'");
/*     */         
/*     */         try {
/* 389 */           s = AccessController.<Socket>doPrivileged(new PrivilegedExceptionAction<Socket>()
/*     */               {
/*     */                 public Socket run() throws IOException
/*     */                 {
/* 393 */                   return new Socket(SocketToken.ms_Localhost, SocketToken.ms_Instance.getLocalPort());
/*     */                 }
/*     */               });
/*     */         }
/* 397 */         catch (PrivilegedActionException e) {
/*     */           
/* 399 */           System.err.println(e.getMessage());
/*     */         } 
/*     */       } 
/*     */       
/* 403 */       long end = System.currentTimeMillis();
/* 404 */       System.out.println("Start communication for token '" + token + "' in " + (end - start) + " ms");
/* 405 */       start = end;
/* 406 */       if (s != null)
/*     */       {
/* 408 */         out = new ObjectOutputStream(s.getOutputStream());
/* 409 */         out.writeObject(token);
/* 410 */         out.flush();
/*     */         
/* 412 */         System.out.println("Token '" + token + "' send to port:" + port);
/* 413 */         in = new ObjectInputStream(s.getInputStream());
/* 414 */         Object returnMessage = in.readObject();
/* 415 */         System.out.println("Token '" + token + "' send result : " + returnMessage);
/* 416 */         end = System.currentTimeMillis();
/* 417 */         System.out.println("Token '" + token + "' process took : " + (end - start) + " ms");
/*     */       }
/*     */     
/* 420 */     } catch (Exception e) {
/*     */       
/* 422 */       e.printStackTrace();
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 428 */         if (in != null)
/* 429 */           in.close(); 
/* 430 */         if (out != null)
/* 431 */           out.close(); 
/* 432 */         if (s != null) {
/* 433 */           s.close();
/*     */         }
/* 435 */       } catch (Exception e) {
/*     */         
/* 437 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\start\SocketToken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */