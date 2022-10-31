/*     */ package com.lbs.rmi;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.rmi.util.Assert;
/*     */ import com.lbs.transport.RemoteMethodRequest;
/*     */ import com.lbs.transport.RemoteMethodResponse;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.rmi.RemoteException;
/*     */ import java.util.logging.Level;
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
/*     */ public abstract class AbstractHttpInvokerRequestExecutor
/*     */ {
/*     */   public static final String CONTENT_TYPE_SERIALIZED_OBJECT = "application/x-java-serialized-object";
/*     */   protected static final String HTTP_METHOD_POST = "POST";
/*     */   protected static final String HTTP_HEADER_ACCEPT_LANGUAGE = "Accept-Language";
/*     */   protected static final String HTTP_HEADER_ACCEPT_ENCODING = "Accept-Encoding";
/*     */   protected static final String HTTP_HEADER_CONTENT_ENCODING = "Content-Encoding";
/*     */   protected static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
/*     */   protected static final String HTTP_HEADER_CONTENT_LENGTH = "Content-Length";
/*     */   protected static final String ENCODING_GZIP = "gzip";
/*     */   private static final int SERIALIZED_INVOCATION_BYTE_ARRAY_INITIAL_SIZE = 1024;
/*  45 */   protected static final LbsConsole logger = LbsConsole.getLogger(AbstractHttpInvokerRequestExecutor.class);
/*     */   
/*  47 */   private String contentType = "application/x-java-serialized-object";
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean acceptGzipEncoding = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContentType(String contentType) {
/*  57 */     Assert.notNull(contentType, "'contentType' must not be null");
/*  58 */     this.contentType = contentType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContentType() {
/*  66 */     return this.contentType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAcceptGzipEncoding(boolean acceptGzipEncoding) {
/*  77 */     this.acceptGzipEncoding = acceptGzipEncoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAcceptGzipEncoding() {
/*  86 */     return this.acceptGzipEncoding;
/*     */   }
/*     */ 
/*     */   
/*     */   public final RemoteMethodResponse executeRequest(RemoteMethodRequest invocation) throws Exception {
/*  91 */     ByteArrayOutputStream baos = getByteArrayOutputStream(invocation);
/*  92 */     if (logger.isDebugEnabled())
/*     */     {
/*  94 */       logger.log(Level.FINE, "AbstractHttpInvokerRequestExecutor", "executeRequest", 
/*  95 */           "Sending HTTP invoker request [" + getServiceUrl() + "?" + invocation.TargetServlet + "." + 
/*  96 */           invocation.MethodName + "], with size " + baos.size());
/*     */     }
/*  98 */     return doExecuteRequest(baos, invocation);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract String getServiceUrl();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ByteArrayOutputStream getByteArrayOutputStream(RemoteMethodRequest invocation) throws IOException {
/* 111 */     ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
/* 112 */     writeRemoteInvocation(invocation, baos);
/* 113 */     return baos;
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
/*     */   protected void writeRemoteInvocation(RemoteMethodRequest invocation, OutputStream os) throws IOException {
/* 131 */     ObjectOutputStream oos = createObjectOutputStream(decorateOutputStream(os));
/*     */     
/*     */     try {
/* 134 */       doWriteRemoteInvocation(invocation, oos);
/* 135 */       oos.flush();
/*     */     }
/*     */     finally {
/*     */       
/* 139 */       oos.close();
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
/*     */   protected OutputStream decorateOutputStream(OutputStream os) throws IOException {
/* 153 */     return os;
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
/*     */   protected void doWriteRemoteInvocation(RemoteMethodRequest invocation, ObjectOutputStream oos) throws IOException {
/* 169 */     oos.writeObject(invocation);
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
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract RemoteMethodResponse doExecuteRequest(ByteArrayOutputStream paramByteArrayOutputStream, RemoteMethodRequest paramRemoteMethodRequest) throws Exception;
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
/*     */   protected RemoteMethodResponse readRemoteInvocationResult(InputStream is, HttpURLConnection con) throws Exception {
/* 208 */     ObjectInputStream ois = createObjectInputStream(decorateInputStream(is, con));
/*     */     
/*     */     try {
/* 211 */       return doReadRemoteInvocationResult(ois);
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 217 */         ois.close();
/*     */       }
/* 219 */       catch (Exception exception) {}
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
/*     */   protected InputStream decorateInputStream(InputStream is, HttpURLConnection con) throws IOException {
/* 236 */     return is;
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
/*     */   protected ObjectInputStream createObjectInputStream(InputStream is) throws IOException {
/* 249 */     return new ObjectInputStream(is);
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
/*     */   protected ObjectOutputStream createObjectOutputStream(OutputStream os) throws IOException {
/* 262 */     return new ObjectOutputStream(os);
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
/*     */ 
/*     */   
/*     */   protected RemoteMethodResponse doReadRemoteInvocationResult(ObjectInputStream ois) throws IOException, ClassNotFoundException, Exception {
/* 282 */     Object obj = ois.readObject();
/* 283 */     if (!(obj instanceof RemoteMethodResponse))
/*     */     {
/* 285 */       throw new RemoteException(
/* 286 */           "Deserialized object needs to be assignable to type [" + RemoteMethodResponse.class.getName() + "]: " + obj);
/*     */     }
/* 288 */     return (RemoteMethodResponse)obj;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\rmi\AbstractHttpInvokerRequestExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */