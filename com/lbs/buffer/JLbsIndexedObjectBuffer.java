/*     */ package com.lbs.buffer;
/*     */ 
/*     */ import com.lbs.transport.TransportUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsIndexedObjectBuffer
/*     */   extends JLbsMappedBuffer
/*     */ {
/*  16 */   private Hashtable m_Index = new Hashtable<>();
/*  17 */   private ArrayList m_List = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  23 */     return this.m_Index.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Object object) {
/*  28 */     boolean result = add(Integer.valueOf(this.m_Count), object);
/*  29 */     if (result)
/*  30 */       this.m_Count++; 
/*  31 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Object key, Object object) {
/*  36 */     if (key == null) {
/*  37 */       key = "";
/*     */     }
/*     */     try {
/*  40 */       synchronized (this.m_RandFile)
/*     */       {
/*  42 */         checkPosition();
/*  43 */         long pos = this.m_RandFile.getFilePointer();
/*  44 */         boolean result = super.add(object);
/*  45 */         if (result) {
/*     */           
/*  47 */           Long lpos = Long.valueOf(pos);
/*  48 */           this.m_Index.put(key, lpos);
/*  49 */           this.m_List.add(lpos);
/*     */         } 
/*  51 */         return result;
/*     */       }
/*     */     
/*  54 */     } catch (Exception exception) {
/*     */ 
/*     */       
/*  57 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object getObject(int index) {
/*  62 */     if (index >= 0 && index < this.m_List.size()) {
/*     */       
/*  64 */       Object o = this.m_List.get(index);
/*  65 */       long pos = (o instanceof Long) ? (
/*  66 */         (Long)o).longValue() : 
/*  67 */         -1L;
/*  68 */       if (pos >= 0L)
/*  69 */         return readAtPos(pos); 
/*     */     } 
/*  71 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getObject(Object key) {
/*  76 */     if (key == null) {
/*  77 */       key = "";
/*     */     }
/*     */     try {
/*  80 */       Object o = this.m_Index.get(key);
/*  81 */       long pos = (o instanceof Long) ? (
/*  82 */         (Long)o).longValue() : 
/*  83 */         -1L;
/*  84 */       if (pos >= 0L) {
/*  85 */         return readAtPos(pos);
/*     */       }
/*  87 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Object readAtPos(long pos) {
/*     */     try {
/*  97 */       byte[] data = null;
/*  98 */       synchronized (this.m_RandFile) {
/*     */         
/* 100 */         this.m_RandFile.seek(pos);
/* 101 */         int size = this.m_RandFile.readInt();
/* 102 */         if (size > 0) {
/*     */           
/* 104 */           data = new byte[size];
/* 105 */           this.m_RandFile.read(data);
/*     */         } 
/*     */       } 
/* 108 */       if (data != null)
/*     */       {
/* 110 */         Object o = TransportUtil.deserializeObjectPlain(data);
/* 111 */         data = null;
/*     */         
/* 113 */         return o;
/*     */       }
/*     */     
/* 116 */     } catch (Exception e) {
/*     */       
/* 118 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 121 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\buffer\JLbsIndexedObjectBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */