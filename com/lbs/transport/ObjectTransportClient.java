/*    */ package com.lbs.transport;
/*    */ 
/*    */ import com.lbs.channel.IChannelProvider;
/*    */ import com.lbs.channel.ICompressor;
/*    */ import com.lbs.channel.ICryptor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ObjectTransportClient
/*    */   extends TransportClient
/*    */ {
/*    */   public static final int COMPRESS_NONE = 0;
/*    */   public static final int COMPRESS_OUTGOING = 1;
/*    */   public static final int COMPRESS_INCOMING = 2;
/*    */   public static final int COMPRESS_ALL = 3;
/* 22 */   protected ICompressor m_Compressor = new StdCompressor(-1162151169);
/* 23 */   protected int m_CompressType = 3;
/*    */ 
/*    */   
/*    */   public ObjectTransportClient(IChannelProvider channelProv, ISessionBase session, ICryptor localCryptor, ICryptor remoteCryptor) {
/* 27 */     super(channelProv, session, localCryptor, remoteCryptor);
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] postData(String sUri, byte[] sendPrefix, byte[] sendData) {
/* 32 */     if (sendData != null && sendData.length > 0) {
/*    */       
/* 34 */       byte[] data = ((this.m_CompressType & 0x1) == 1) ? 
/* 35 */         this.m_Compressor.compress(sendData, 0, sendData.length) : 
/* 36 */         sendData;
/* 37 */       byte[] returnData = postData(sUri, sendPrefix, data, false);
/* 38 */       if (returnData != null && returnData.length > 0 && (this.m_CompressType & 0x2) == 2) {
/*    */         
/*    */         try {
/* 41 */           returnData = this.m_Compressor.uncompress(returnData, 0, returnData.length);
/*    */         }
/* 43 */         catch (Exception exception) {}
/*    */       }
/*    */       
/* 46 */       return returnData;
/*    */     } 
/*    */     
/* 49 */     return postData(sUri, sendPrefix, sendData, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] postData(String sUri, Object obj, ClassLoader clsLoader) throws Exception {
/* 54 */     if (obj == null)
/* 55 */       return postData(sUri, null, null, false); 
/* 56 */     Object[] objects = new Object[1];
/* 57 */     objects[0] = obj;
/* 58 */     return postData(sUri, objects, clsLoader);
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] postData(String sUri, Object[] objects, ClassLoader clsLoader) throws Exception {
/* 63 */     if (objects == null || objects.length == 0)
/* 64 */       return postData(sUri, (byte[])null, (byte[])null); 
/* 65 */     return postData(sUri, (byte[])null, TransportUtil.serializeObjects(objects, clsLoader));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCompressType() {
/* 70 */     return this.m_CompressType;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCompressType(int i) {
/* 75 */     this.m_CompressType = i;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLocalCryptor(ICryptor localCryptor) {
/* 80 */     this.m_LocalCryptor = localCryptor;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRemoteCryptor(ICryptor remoteCryptor) {
/* 85 */     this.m_RemoteCryptor = remoteCryptor;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TransportClient duplicate() {
/* 91 */     TransportClient cand = new ObjectTransportClient(this.m_ChannelProv, this.m_Session, this.m_LocalCryptor, this.m_RemoteCryptor);
/* 92 */     cand.setRecorder(this.recorder);
/* 93 */     return cand;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\ObjectTransportClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */