/*     */ package com.lbs.controls.chartgenerator;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.jfree.data.general.Series;
/*     */ import org.jfree.data.general.SeriesException;
/*     */ 
/*     */ public class LbsStringSeries
/*     */   extends Series {
/*     */   protected List data;
/*  12 */   private int maximumItemCount = Integer.MAX_VALUE;
/*     */   
/*     */   private boolean autoSort;
/*     */   private boolean allowDuplicateXValues;
/*     */   
/*     */   public LbsStringSeries(Comparable key, boolean autoSort, boolean allowDuplicateXValues) {
/*  18 */     super(key);
/*  19 */     this.data = new ArrayList();
/*  20 */     this.autoSort = autoSort;
/*  21 */     this.allowDuplicateXValues = allowDuplicateXValues;
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsStringSeries(Comparable key) {
/*  26 */     this(key, false, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemCount() {
/*  32 */     return this.data.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public List getItems() {
/*  37 */     return Collections.unmodifiableList(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaximumItemCount() {
/*  42 */     return this.maximumItemCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximumItemCount(int maximum) {
/*  47 */     this.maximumItemCount = maximum;
/*  48 */     int remove = this.data.size() - maximum;
/*  49 */     if (remove > 0) {
/*     */       
/*  51 */       this.data.subList(0, remove).clear();
/*  52 */       fireSeriesChanged();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsStringSeriesDataItem getDataItem(int index) {
/*  58 */     return this.data.get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public int indexOf(String x) {
/*  63 */     if (this.autoSort)
/*     */     {
/*  65 */       return Collections.binarySearch(this.data, new LbsStringSeriesDataItem(x, null));
/*     */     }
/*     */ 
/*     */     
/*  69 */     for (int i = 0; i < this.data.size(); i++) {
/*     */       
/*  71 */       LbsStringSeriesDataItem item = this.data.get(i);
/*  72 */       if (item.getX().equals(x))
/*     */       {
/*  74 */         return i;
/*     */       }
/*     */     } 
/*  77 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(String x, double y) {
/*  83 */     add(x, Double.valueOf(y), true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(String x, Double y, boolean notify) {
/*  88 */     LbsStringSeriesDataItem item = new LbsStringSeriesDataItem(x, y);
/*  89 */     add(item, notify);
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(LbsStringSeriesDataItem item, boolean notify) {
/*  94 */     if (item == null)
/*     */     {
/*  96 */       throw new IllegalArgumentException("Null 'item' argument.");
/*     */     }
/*  98 */     if (this.autoSort) {
/*     */       
/* 100 */       int index = Collections.binarySearch(this.data, item);
/* 101 */       if (index < 0) {
/*     */         
/* 103 */         this.data.add(-index - 1, item);
/*     */ 
/*     */       
/*     */       }
/* 107 */       else if (this.allowDuplicateXValues) {
/*     */         
/* 109 */         int size = this.data.size();
/* 110 */         while (index < size && item.compareTo(this.data.get(index)) == 0)
/*     */         {
/* 112 */           index++;
/*     */         }
/* 114 */         if (index < this.data.size())
/*     */         {
/* 116 */           this.data.add(index, item);
/*     */         }
/*     */         else
/*     */         {
/* 120 */           this.data.add(item);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 125 */         throw new SeriesException("X-value already exists.");
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 131 */       if (!this.allowDuplicateXValues) {
/*     */         
/* 133 */         int index = indexOf(item.getX());
/* 134 */         if (index >= 0)
/*     */         {
/* 136 */           throw new SeriesException("X-value already exists.");
/*     */         }
/*     */       } 
/* 139 */       this.data.add(item);
/*     */     } 
/*     */     
/* 142 */     if (getItemCount() > this.maximumItemCount)
/*     */     {
/* 144 */       this.data.remove(0);
/*     */     }
/*     */     
/* 147 */     if (notify)
/*     */     {
/* 149 */       fireSeriesChanged();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\chartgenerator\LbsStringSeries.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */