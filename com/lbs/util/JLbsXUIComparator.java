/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.interfaces.ILbsXUIComparable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
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
/*     */ public class JLbsXUIComparator
/*     */ {
/*     */   public static <T> void calculateDelta(ILbsXUIComparable<T> sourceChild, ILbsXUIComparable<T> destChild, String sourcePrefix, String destPrefix, ArrayList<String> delta, String childIdentifier) {
/*  24 */     if (sourceChild == null) {
/*     */       
/*  26 */       if (destChild != null && !destChild.isEmpty()) {
/*  27 */         delta.add(String.valueOf(childIdentifier) + "(" + destChild + ") is null for source definition : (" + sourcePrefix + "), dest is (" + 
/*  28 */             destChild + ")");
/*     */       }
/*  30 */     } else if (!sourceChild.isEmpty()) {
/*     */       
/*  32 */       if (destChild == null) {
/*  33 */         delta.add(String.valueOf(childIdentifier) + " (" + sourceChild + ") is null for destination definition : (" + destPrefix + 
/*  34 */             ") , source is (" + sourceChild + ")");
/*     */       } else {
/*  36 */         sourceChild.calculateDelta(destChild.getThis(), delta, sourcePrefix);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T> void calculateDelta(ILbsXUIComparable[] sourceList, ILbsXUIComparable[] destList, String sourcePrefix, String destPrefix, ArrayList<String> delta, String listIdentifier) {
/*  43 */     if (sourceList == null) {
/*     */       
/*  45 */       if (destList != null && destList.length > 0) {
/*  46 */         delta.add(String.valueOf(listIdentifier) + " list is null for source definition : (" + sourcePrefix + ") , dest list is (" + 
/*  47 */             Arrays.toString((Object[])destList) + ")");
/*     */       }
/*  49 */     } else if (sourceList.length > 0) {
/*     */       
/*  51 */       if (destList == null) {
/*  52 */         delta.add(String.valueOf(listIdentifier) + " list is null for destination definition : (" + destPrefix + ") , source list is (" + 
/*  53 */             Arrays.toString((Object[])sourceList) + ")");
/*     */       }
/*     */       else {
/*     */         
/*  57 */         ArrayList<Integer> destIdx = new ArrayList<>();
/*  58 */         boolean fnd = false; int i;
/*  59 */         for (i = 0; i < sourceList.length; i++) {
/*     */           
/*  61 */           fnd = false;
/*  62 */           ILbsXUIComparable<T> source = sourceList[i];
/*  63 */           if (source != null && !source.isEmpty()) {
/*     */             
/*  65 */             for (int j = 0; j < destList.length; j++) {
/*     */               
/*  67 */               ILbsXUIComparable<T> dest = destList[j];
/*  68 */               if (dest != null && !dest.isEmpty() && !destIdx.contains(Integer.valueOf(j)))
/*     */               {
/*  70 */                 if (source.isSameDefinition(dest.getThis())) {
/*     */                   
/*  72 */                   source.calculateDelta(dest.getThis(), delta, sourcePrefix);
/*  73 */                   destIdx.add(Integer.valueOf(j));
/*  74 */                   fnd = true;
/*     */                   break;
/*     */                 }  } 
/*     */             } 
/*  78 */             if (!fnd)
/*  79 */               delta.add(String.valueOf(listIdentifier) + " (" + source.getDeltaIdentifier(sourcePrefix) + 
/*  80 */                   ") cannot be found on destination definition : (" + destPrefix + ")"); 
/*     */           } 
/*  82 */         }  for (i = 0; i < destList.length; i++) {
/*     */           
/*  84 */           if (!destIdx.contains(Integer.valueOf(i)) && destList[i] != null && !destList[i].isEmpty()) {
/*  85 */             delta.add(String.valueOf(listIdentifier) + " (" + destList[i].getDeltaIdentifier(destPrefix) + 
/*  86 */                 ") cannot be found on source definition : (" + sourcePrefix + ")");
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void calculateDelta(Hashtable<String, ?> srcProperties, Hashtable<String, ?> destProperties, String sourcePrefix, String destPrefix, ArrayList<String> delta) {
/*  95 */     if (srcProperties.size() != destProperties.size())
/*  96 */       delta.add("Different properties sizes : source : (" + sourcePrefix + ") [" + srcProperties.size() + "] , dest : (" + 
/*  97 */           destPrefix + ") [" + destProperties.size() + "]"); 
/*  98 */     Enumeration<String> keys = srcProperties.keys();
/*  99 */     while (keys.hasMoreElements()) {
/*     */       
/* 101 */       String key = keys.nextElement();
/* 102 */       if (!destProperties.containsKey(key))
/*     */       {
/* 104 */         delta.add("Cannot find property '" + key + "' in dest definition : (" + destPrefix + ") , source is (" + 
/* 105 */             sourcePrefix + ")");
/*     */       }
/* 107 */       String srcValue = String.valueOf(srcProperties.get(key));
/* 108 */       String destValue = String.valueOf(destProperties.get(key));
/* 109 */       if (!JLbsStringUtil.equals(srcValue, destValue))
/*     */       {
/* 111 */         delta.add("Different property values : source : (" + sourcePrefix + ") [" + srcValue + "] , dest : (" + destPrefix + 
/* 112 */             ") [" + destValue + "]");
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsXUIComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */