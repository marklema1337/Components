/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class JLbsSortedDoubleList
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final double EPSILON = 1.0E-6D;
/*     */   private double[] m_List;
/*     */   private int m_Length;
/*     */   private transient int m_UpdateCount;
/*     */   
/*     */   public void add(double i) {
/*  24 */     beginUpdate();
/*  25 */     int iListSize = listSize();
/*  26 */     if (iListSize <= this.m_Length) {
/*     */       
/*  28 */       iListSize = (iListSize > 128) ? (iListSize + (iListSize >> 1)) : ((iListSize < 4) ? 8 : (iListSize * 2));
/*  29 */       resize(iListSize);
/*     */     } 
/*  31 */     this.m_List[this.m_Length++] = i;
/*  32 */     endUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(double[] intArray) {
/*  37 */     if (intArray != null)
/*  38 */       for (int i = 0; i < intArray.length; i++) {
/*  39 */         add(intArray[i]);
/*     */       } 
/*     */   }
/*     */   
/*     */   public void addUnique(double i) {
/*  44 */     if (indexOf(i) < 0) {
/*  45 */       add(i);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean removeAt(int index) {
/*  50 */     boolean result = false;
/*  51 */     beginUpdate();
/*  52 */     if (index < this.m_Length && index >= 0) {
/*     */       
/*  54 */       result = true;
/*  55 */       this.m_Length--;
/*  56 */       for (int i = index; i < this.m_Length; i++)
/*  57 */         this.m_List[i] = this.m_List[i + 1]; 
/*  58 */       this.m_List[this.m_Length] = 0.0D;
/*     */     } 
/*     */     
/*  61 */     endUpdate();
/*  62 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(double val) {
/*  67 */     int index = indexOf(val);
/*  68 */     return (index >= 0 && removeAt(index));
/*     */   }
/*     */ 
/*     */   
/*     */   public double get(int index) throws IndexOutOfBoundsException {
/*  73 */     if (index >= 0 && index < this.m_Length)
/*  74 */       return this.m_List[index]; 
/*  75 */     throw new IndexOutOfBoundsException("The given array index exceeds index boundaries!");
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/*  80 */     return this.m_Length;
/*     */   }
/*     */ 
/*     */   
/*     */   public void resize(int iNewSize) {
/*  85 */     if (iNewSize == size())
/*     */       return; 
/*  87 */     if (iNewSize <= 0) {
/*  88 */       this.m_List = null;
/*     */     } else {
/*     */       
/*  91 */       double[] newList = new double[iNewSize];
/*  92 */       int iSize = listSize();
/*  93 */       for (int i = 0; i < iSize; i++)
/*  94 */         newList[i] = this.m_List[i]; 
/*  95 */       this.m_List = newList;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void pack() {
/* 100 */     int iSize = listSize();
/* 101 */     if (iSize > this.m_Length) {
/* 102 */       resize(this.m_Length);
/*     */     }
/*     */   }
/*     */   
/*     */   public int beginUpdate() {
/* 107 */     return ++this.m_UpdateCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public int endUpdate() {
/* 112 */     if (this.m_UpdateCount > 0) {
/*     */       
/* 114 */       if (this.m_UpdateCount == 1)
/* 115 */         sort(); 
/* 116 */       return --this.m_UpdateCount;
/*     */     } 
/* 118 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sort() {
/* 123 */     if (this.m_Length > 1) {
/* 124 */       sort(0, this.m_Length - 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public int indexOf(double item) {
/* 129 */     int start = 0;
/* 130 */     int end = this.m_Length - 1;
/* 131 */     while (start <= end) {
/*     */       
/* 133 */       int mid = start + end >>> 1;
/* 134 */       double diff = item - this.m_List[mid];
/* 135 */       if (Math.abs(diff) < 1.0E-6D)
/* 136 */         return mid; 
/* 137 */       if (diff < 0.0D) {
/* 138 */         end = mid - 1; continue;
/* 139 */       }  if (diff > 0.0D)
/* 140 */         start = mid + 1; 
/*     */     } 
/* 142 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasItem(double item) {
/* 147 */     return (indexOf(item) >= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int listSize() {
/* 152 */     return (this.m_List != null) ? this.m_List.length : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sort(int start, int end) {
/* 159 */     if (end > start) {
/*     */       
/* 161 */       int pivot = partition(start, end);
/* 162 */       sort(start, pivot - 1);
/* 163 */       sort(pivot + 1, end);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int findPivot(int start, int end) {
/* 170 */     if (this.m_List[start] < this.m_List[end + start >>> 1] && 
/* 171 */       this.m_List[end + start >>> 1] < this.m_List[end]) {
/* 172 */       return end + start >>> 1;
/*     */     }
/* 174 */     if (this.m_List[end + start >>> 1] < this.m_List[start] && 
/* 175 */       this.m_List[start] < this.m_List[end]) {
/* 176 */       return start;
/*     */     }
/* 178 */     if (this.m_List[end + start >>> 1] < this.m_List[end] && 
/* 179 */       this.m_List[end] < this.m_List[start])
/* 180 */       return end; 
/* 181 */     return end;
/*     */   }
/*     */ 
/*     */   
/*     */   private void swap(int pos1, int pos2) {
/* 186 */     double temp = this.m_List[pos1];
/* 187 */     this.m_List[pos1] = this.m_List[pos2];
/* 188 */     this.m_List[pos2] = temp;
/*     */   }
/*     */ 
/*     */   
/*     */   private int partition(int start, int end) {
/* 193 */     int pivot = findPivot(start, end);
/* 194 */     int right = end;
/* 195 */     int left = start - 1;
/*     */     
/* 197 */     swap(pivot, end);
/*     */ 
/*     */     
/*     */     while (true) {
/* 201 */       if (this.m_List[++left] > this.m_List[end] || 
/* 202 */         left == end) { do {
/*     */         
/* 204 */         } while (this.m_List[--right] > this.m_List[end] && 
/* 205 */           right != start);
/*     */         
/* 207 */         if (left >= right)
/*     */           break; 
/* 209 */         swap(left, right); }
/*     */     
/* 211 */     }  swap(left, end);
/*     */     
/* 213 */     return left;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsSortedDoubleList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */