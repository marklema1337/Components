/*     */ package com.lbs.console;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
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
/*     */ 
/*     */ 
/*     */ public class LbsOutputStream
/*     */   extends OutputStream
/*     */ {
/*     */   protected boolean hasBeenClosed = false;
/*     */   protected byte[] buf;
/*     */   protected int count;
/*     */   private int bufLength;
/*     */   public static final int DEFAULT_BUFFER_LENGTH = 2048;
/*     */   protected LbsConsole logger;
/*     */   protected LbsLevel level;
/*     */   
/*     */   private LbsOutputStream() {}
/*     */   
/*     */   public LbsOutputStream(LbsConsole log, LbsLevel level) throws IllegalArgumentException {
/*  84 */     this();
/*  85 */     if (log == null)
/*     */     {
/*  87 */       throw new IllegalArgumentException("cat == null");
/*     */     }
/*  89 */     if (level == null)
/*     */     {
/*  91 */       throw new IllegalArgumentException("priority == null");
/*     */     }
/*  93 */     this.level = level;
/*  94 */     this.logger = log;
/*  95 */     this.bufLength = 2048;
/*  96 */     this.buf = new byte[2048];
/*  97 */     this.count = 0;
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
/*     */   public void close() {
/* 110 */     flush();
/* 111 */     this.hasBeenClosed = true;
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
/*     */   public void write(int b) throws IOException {
/* 129 */     if (this.hasBeenClosed)
/*     */     {
/* 131 */       throw new IOException("The stream has been closed.");
/*     */     }
/*     */     
/* 134 */     if (this.count == this.bufLength) {
/*     */ 
/*     */       
/* 137 */       int newBufLength = this.bufLength + 2048;
/* 138 */       byte[] newBuf = new byte[newBufLength];
/* 139 */       System.arraycopy(this.buf, 0, newBuf, 0, this.bufLength);
/* 140 */       this.buf = newBuf;
/* 141 */       this.bufLength = newBufLength;
/*     */     } 
/* 143 */     this.buf[this.count] = (byte)b;
/* 144 */     this.count++;
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
/*     */   public void flush() {
/* 157 */     if (this.count == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     if (this.count == 1 && (char)this.buf[0] == '\n') {
/*     */       
/* 166 */       reset();
/*     */       
/*     */       return;
/*     */     } 
/* 170 */     if (this.count == 1 && (char)this.buf[0] == '\r') {
/*     */       
/* 172 */       reset();
/*     */       
/*     */       return;
/*     */     } 
/* 176 */     if (this.count == 2 && (char)this.buf[0] == '\r' && (char)this.buf[1] == '\n') {
/*     */       
/* 178 */       reset();
/*     */       return;
/*     */     } 
/* 181 */     byte[] theBytes = new byte[this.count];
/* 182 */     System.arraycopy(this.buf, 0, theBytes, 0, this.count);
/* 183 */     this.logger.log(this.level, new String(theBytes));
/* 184 */     reset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void reset() {
/* 191 */     this.count = 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\LbsOutputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */