/*     */ package com.lbs.rmi;
/*     */ 
/*     */ import com.lbs.rmi.util.Assert;
/*     */ import com.lbs.transport.RemoteMethodRequest;
/*     */ import com.lbs.transport.RemoteMethodResponse;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.rmi.RemoteException;
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
/*     */ public abstract class RemoteInvocationSerializingExporter
/*     */ {
/*     */   public static final String CONTENT_TYPE_SERIALIZED_OBJECT = "application/x-java-serialized-object";
/*  33 */   private String contentType = "application/x-java-serialized-object";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContentType(String contentType) {
/*  41 */     Assert.notNull(contentType, "'contentType' must not be null");
/*  42 */     this.contentType = contentType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContentType() {
/*  50 */     return this.contentType;
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
/*     */   protected ObjectInputStream createObjectInputStream(InputStream is) throws IOException {
/*  62 */     return new ObjectInputStream(is);
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
/*     */   protected RemoteMethodRequest doReadRemoteInvocation(ObjectInputStream ois) throws IOException, ClassNotFoundException {
/*  80 */     Object obj = ois.readObject();
/*  81 */     if (!(obj instanceof RemoteMethodRequest))
/*     */     {
/*  83 */       throw new RemoteException("Deserialized object needs to be assignable to type [" + RemoteMethodRequest.class.getName() + 
/*  84 */           "]: " + obj);
/*     */     }
/*  86 */     return (RemoteMethodRequest)obj;
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
/*  99 */     return new ObjectOutputStream(os);
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
/*     */   protected void doWriteRemoteInvocationResult(RemoteMethodResponse result, ObjectOutputStream oos) throws IOException {
/* 115 */     oos.writeObject(result);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\rmi\RemoteInvocationSerializingExporter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */