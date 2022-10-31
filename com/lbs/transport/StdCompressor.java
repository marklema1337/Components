/*     */ package com.lbs.transport;
/*     */ 
/*     */ import com.lbs.channel.ICompressor;
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.invoke.RttiUtil;
/*     */ import com.lbs.util.JLbsTransportMode;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.zip.DeflaterOutputStream;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import java.util.zip.InflaterInputStream;
/*     */ import java.util.zip.ZipInputStream;
/*     */ import java.util.zip.ZipOutputStream;
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
/*     */ public class StdCompressor
/*     */   implements ICompressor
/*     */ {
/*     */   public static final int ZIP = -1162151170;
/*     */   public static final int GZIP = -1162151169;
/*     */   private int m_iCompType;
/*     */   
/*     */   public StdCompressor() {
/*  39 */     this(-1162151169);
/*     */   }
/*     */ 
/*     */   
/*     */   public StdCompressor(int iCompType) {
/*  44 */     this.m_iCompType = iCompType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSignature() {
/*  52 */     switch (this.m_iCompType) {
/*     */       
/*     */       case -1162151170:
/*  55 */         return -1162151170;
/*     */     } 
/*  57 */     return -1162151169;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] compress(byte[] data, int index, int length) {
/*  66 */     if (!JLbsTransportMode.COMPRESSION_ON) {
/*  67 */       return data;
/*     */     }
/*     */     try {
/*  70 */       ByteArrayOutputStream stream = new ByteArrayOutputStream(length);
/*  71 */       DeflaterOutputStream defStream = getDeflatorStream(stream);
/*  72 */       if (defStream != null)
/*     */       {
/*  74 */         defStream.write(data, index, length);
/*  75 */         defStream.finish();
/*  76 */         byte[] result = stream.toByteArray();
/*  77 */         defStream.close();
/*  78 */         defStream = null;
/*  79 */         return result;
/*     */       }
/*     */     
/*  82 */     } catch (Exception e) {
/*     */       
/*  84 */       LbsConsole.getLogger("Transport.StdCompressor").error(e.getMessage(), e);
/*     */     } 
/*     */     
/*  87 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] uncompress(byte[] data, int index, int length) throws IOException {
/*  95 */     if (data != null && length > 0) {
/*     */       
/*  97 */       ByteArrayInputStream stream = new ByteArrayInputStream(data, index, length);
/*  98 */       InflaterInputStream infStream = getInflatorStream(stream, Math.min(length * 3, 2048));
/*  99 */       byte[] result = (infStream != null) ? 
/* 100 */         TransportUtil.readStream(infStream, length * 3) : 
/* 101 */         data;
/* 102 */       if (infStream != null)
/* 103 */         infStream.close(); 
/* 104 */       infStream = null;
/* 105 */       return (result != null) ? 
/* 106 */         result : 
/* 107 */         RttiUtil.copyByteArray(data, index, length);
/*     */     } 
/* 109 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private DeflaterOutputStream getDeflatorStream(OutputStream stream) {
/*     */     try {
/* 116 */       switch (this.m_iCompType) {
/*     */         
/*     */         case -1162151169:
/* 119 */           return new GZIPOutputStream(stream);
/*     */       } 
/* 121 */       return new ZipOutputStream(stream);
/*     */     
/*     */     }
/* 124 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 127 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private InflaterInputStream getInflatorStream(InputStream stream, int size) {
/*     */     try {
/* 134 */       switch (this.m_iCompType) {
/*     */         
/*     */         case -1162151169:
/* 137 */           return new GZIPInputStream(stream, size);
/*     */       } 
/* 139 */       return new ZipInputStream(stream);
/*     */     
/*     */     }
/* 142 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 145 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public File uncompressToTemp(InputStream is) throws IOException {
/* 150 */     File result = null;
/* 151 */     File file = File.createTempFile("uzp", null);
/* 152 */     FileOutputStream os = null;
/*     */     
/*     */     try {
/* 155 */       os = new FileOutputStream(file);
/* 156 */       InflaterInputStream infStream = getInflatorStream(is, 8096);
/* 157 */       byte[] buffer = new byte[8096];
/*     */       int read;
/* 159 */       while ((read = infStream.read(buffer, 0, buffer.length)) > 0)
/* 160 */         os.write(buffer, 0, read); 
/* 161 */       infStream.close();
/* 162 */       result = file;
/*     */     }
/*     */     finally {
/*     */       
/* 166 */       if (os != null)
/* 167 */         os.close(); 
/* 168 */       if (file != null && result == null && !file.delete())
/* 169 */         file.deleteOnExit(); 
/*     */     } 
/* 171 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\StdCompressor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */