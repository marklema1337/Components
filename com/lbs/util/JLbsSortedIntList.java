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
/*     */ public class JLbsSortedIntList
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int[] m_List;
/*     */   private int m_Length;
/*     */   private transient int m_UpdateCount;
/*     */   
/*     */   public void add(int i) {
/*  23 */     beginUpdate();
/*  24 */     int iListSize = listSize();
/*  25 */     if (iListSize <= this.m_Length) {
/*     */       
/*  27 */       iListSize = (iListSize > 128) ? (iListSize + (iListSize >> 1)) : ((iListSize < 4) ? 8 : (iListSize * 2));
/*  28 */       resize(iListSize);
/*     */     } 
/*  30 */     this.m_List[this.m_Length++] = i;
/*  31 */     endUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(int[] intArray) {
/*  36 */     if (intArray != null)
/*  37 */       for (int i = 0; i < intArray.length; i++) {
/*  38 */         add(intArray[i]);
/*     */       } 
/*     */   }
/*     */   
/*     */   public void addUnique(int i) {
/*  43 */     if (indexOf(i) < 0) {
/*  44 */       add(i);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean removeAt(int index) {
/*  49 */     boolean result = false;
/*  50 */     beginUpdate();
/*  51 */     if (index < this.m_Length && index >= 0) {
/*     */       
/*  53 */       result = true;
/*  54 */       this.m_Length--;
/*  55 */       for (int i = index; i < this.m_Length; i++)
/*  56 */         this.m_List[i] = this.m_List[i + 1]; 
/*  57 */       this.m_List[this.m_Length] = 0;
/*     */     } 
/*     */     
/*  60 */     endUpdate();
/*  61 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(int val) {
/*  66 */     int index = indexOf(val);
/*  67 */     return (index >= 0 && removeAt(index));
/*     */   }
/*     */ 
/*     */   
/*     */   public int get(int index) throws IndexOutOfBoundsException {
/*  72 */     if (index >= 0 && index < this.m_Length)
/*  73 */       return this.m_List[index]; 
/*  74 */     throw new IndexOutOfBoundsException("The given array index exceeds index boundaries!");
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/*  79 */     return this.m_Length;
/*     */   }
/*     */ 
/*     */   
/*     */   public void resize(int iNewSize) {
/*  84 */     if (iNewSize == size())
/*     */       return; 
/*  86 */     if (iNewSize <= 0) {
/*  87 */       this.m_List = null;
/*     */     } else {
/*     */       
/*  90 */       int[] newList = new int[iNewSize];
/*  91 */       int iSize = listSize();
/*  92 */       for (int i = 0; i < iSize; i++)
/*  93 */         newList[i] = this.m_List[i]; 
/*  94 */       this.m_List = newList;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void pack() {
/*  99 */     int iSize = listSize();
/* 100 */     if (iSize > this.m_Length) {
/* 101 */       resize(this.m_Length);
/*     */     }
/*     */   }
/*     */   
/*     */   public int beginUpdate() {
/* 106 */     return ++this.m_UpdateCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public int endUpdate() {
/* 111 */     if (this.m_UpdateCount > 0) {
/*     */       
/* 113 */       if (this.m_UpdateCount == 1)
/* 114 */         sort(); 
/* 115 */       return --this.m_UpdateCount;
/*     */     } 
/* 117 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sort() {
/* 122 */     if (this.m_Length > 1) {
/* 123 */       sort(0, this.m_Length - 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public int indexOf(int item) {
/* 128 */     int start = 0;
/* 129 */     int end = this.m_Length - 1;
/* 130 */     while (start <= end) {
/*     */       
/* 132 */       int mid = start + end >>> 1;
/* 133 */       if (item < this.m_List[mid]) {
/* 134 */         end = mid - 1; continue;
/* 135 */       }  if (item > this.m_List[mid]) {
/* 136 */         start = mid + 1; continue;
/*     */       } 
/* 138 */       return mid;
/*     */     } 
/* 140 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasItem(int item) {
/* 145 */     return (indexOf(item) >= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int listSize() {
/* 150 */     return (this.m_List != null) ? this.m_List.length : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sort(int start, int end) {
/* 157 */     if (end > start) {
/*     */       
/* 159 */       int pivot = partition(start, end);
/* 160 */       sort(start, pivot - 1);
/* 161 */       sort(pivot + 1, end);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int findPivot(int start, int end) {
/* 168 */     if (this.m_List[start] < this.m_List[end + start >>> 2] && 
/* 169 */       this.m_List[end + start >>> 2] < this.m_List[end]) {
/* 170 */       return end + start >>> 2;
/*     */     }
/* 172 */     if (this.m_List[end + start >>> 2] < this.m_List[start] && 
/* 173 */       this.m_List[start] < this.m_List[end]) {
/* 174 */       return start;
/*     */     }
/* 176 */     if (this.m_List[end + start >>> 2] < this.m_List[end] && 
/* 177 */       this.m_List[end] < this.m_List[start])
/* 178 */       return end; 
/* 179 */     return end;
/*     */   }
/*     */ 
/*     */   
/*     */   private void swap(int pos1, int pos2) {
/* 184 */     int temp = this.m_List[pos1];
/* 185 */     this.m_List[pos1] = this.m_List[pos2];
/* 186 */     this.m_List[pos2] = temp;
/*     */   }
/*     */ 
/*     */   
/*     */   private int partition(int start, int end) {
/* 191 */     int pivot = findPivot(start, end);
/* 192 */     int right = end;
/* 193 */     int left = start - 1;
/*     */     
/* 195 */     swap(pivot, end);
/*     */ 
/*     */     
/*     */     while (true) {
/* 199 */       if (this.m_List[++left] > this.m_List[end] || 
/* 200 */         left == end) { do {
/*     */         
/* 202 */         } while (this.m_List[--right] > this.m_List[end] && 
/* 203 */           right != start);
/*     */         
/* 205 */         if (left >= right)
/*     */           break; 
/* 207 */         swap(left, right); }
/*     */     
/* 209 */     }  swap(left, end);
/*     */     
/* 211 */     return left;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsSortedIntList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */