/*     */ package com.lbs.data.objects;
/*     */ 
/*     */ import com.lbs.controls.datedit.JLbsDateFormatter;
/*     */ import com.lbs.invoke.RttiUtil;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Map;
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
/*     */ public class BusinessObjectComparator
/*     */ {
/*     */   public static synchronized ArrayList findDifferences(BusinessObject bo1, BusinessObject bo2, String lhsString, String rhsString, boolean flexibleTimeComparison) {
/*  38 */     ArrayList retval = new ArrayList();
/*  39 */     internalFindDifferences(retval, 0, bo1, bo2, null, lhsString, rhsString, flexibleTimeComparison);
/*  40 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static synchronized String createIndentedString(int n) {
/*  46 */     StringBuilder buffer = new StringBuilder();
/*  47 */     for (int i = 0; i < n; i++)
/*     */     {
/*  49 */       buffer.append("  ");
/*     */     }
/*     */     
/*  52 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static synchronized void internalFindDifferences(ArrayList<String> diffs, int depth, BusinessObject bo1, BusinessObject bo2, String fieldName, String lhsString, String rhsString, boolean flexibleTimeComparison) {
/*  58 */     String fieldNameStr = "";
/*  59 */     if (fieldName != null) {
/*  60 */       fieldNameStr = fieldName + ", ";
/*     */     }
/*  62 */     if (bo1 == null && bo2 == null)
/*     */       return; 
/*  64 */     if (bo1 == null && bo2 != null) {
/*     */       
/*  66 */       diffs.add(createIndentedString(depth) + "@" + fieldNameStr + "Cannot compare! " + lhsString + ": null\t" + rhsString + ": " + bo2
/*  67 */           .getClass().getName());
/*     */       return;
/*     */     } 
/*  70 */     if (bo1 != null && bo2 == null) {
/*     */       
/*  72 */       diffs.add(createIndentedString(depth) + "@" + fieldNameStr + "Cannot Compare! " + lhsString + ": " + bo1
/*  73 */           .getClass().getName() + "\t" + rhsString + ": null");
/*     */       return;
/*     */     } 
/*  76 */     if (bo1 != null && !bo1.getClass().equals(bo2.getClass())) {
/*     */       
/*  78 */       diffs.add(createIndentedString(depth) + "@" + fieldNameStr + "Cannot Compare! " + lhsString + ": " + bo1
/*  79 */           .getClass().getName() + "\t" + rhsString + ": " + bo2.getClass().getName());
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  85 */     String header = createIndentedString(depth) + "@" + fieldNameStr + ((bo1 == null) ? "" : bo1.getClass().getName()) + " | ";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  90 */     Map<String, Object> propVals1 = (bo1 == null) ? null : bo1.getPropertyValuesAsMap();
/*     */ 
/*     */     
/*  93 */     Map<String, Object> propVals2 = (bo2 == null) ? null : bo2.getPropertyValuesAsMap();
/*  94 */     if (propVals1 != null && propVals2 != null) {
/*     */       
/*  96 */       if (propVals1.size() != propVals2.size()) {
/*     */         
/*  98 */         diffs.add(header + "Cannot Compare! " + lhsString + ": Length of PropertyValues: " + propVals1.size() + " " + rhsString + ": Length of PropertyValues: " + propVals2
/*  99 */             .size());
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 104 */       for (Map.Entry<String, Object> entry : propVals1.entrySet()) {
/*     */         
/* 106 */         String key = entry.getKey();
/* 107 */         if (propVals1.get(key) == null && propVals2.get(key) != null)
/* 108 */           diffs.add(header + "PropertyValues[" + key + "]: " + lhsString + ": null\t" + rhsString + ": " + 
/* 109 */               getStringRep(propVals2.get(key))); 
/* 110 */         if (propVals1.get(key) != null && propVals2.get(key) == null) {
/* 111 */           diffs.add(header + "PropertyValues[" + key + "]: " + lhsString + ": " + getStringRep(propVals1.get(key)) + "\t" + rhsString + ": null");
/*     */         }
/* 113 */         if (propVals1.get(key) != null && propVals2.get(key) != null && !propVals1.get(key).equals(propVals2.get(key))) {
/*     */           
/* 115 */           boolean addDiff = true;
/* 116 */           if (flexibleTimeComparison)
/*     */           {
/* 118 */             if (propVals1.get(key) instanceof Calendar) {
/*     */               
/* 120 */               Calendar date1 = (Calendar)propVals1.get(key);
/* 121 */               Calendar date2 = (Calendar)propVals2.get(key);
/* 122 */               if (date1.before(date2)) {
/*     */                 
/* 124 */                 Calendar date11 = (Calendar)date1.clone();
/* 125 */                 date11.add(12, 2);
/* 126 */                 if (date11.after(date2)) {
/* 127 */                   addDiff = false;
/*     */                 }
/* 129 */               } else if (date1.after(date2)) {
/*     */                 
/* 131 */                 Calendar date11 = (Calendar)date1.clone();
/* 132 */                 date11.add(12, -2);
/* 133 */                 if (date11.before(date2))
/* 134 */                   addDiff = false; 
/*     */               } 
/*     */             } 
/*     */           }
/* 138 */           if (addDiff) {
/* 139 */             diffs.add(header + "PropertyValues[" + key + "]: " + lhsString + ": " + getStringRep(propVals1.get(key)) + "\t" + rhsString + ": " + 
/* 140 */                 getStringRep(propVals2.get(key)));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 145 */     Method[] methods = bo1.getClass().getMethods();
/* 146 */     for (int i = 0; i < methods.length; i++) {
/*     */       
/* 148 */       if (RttiUtil.isSubClassOf(methods[i].getReturnType(), BusinessObjects.class) && (methods[i]
/* 149 */         .getParameterTypes()).length == 0 && methods[i].getName().startsWith("get")) {
/*     */         
/*     */         try
/*     */         {
/* 153 */           BusinessObjects<BusinessObject> subBos1 = (BusinessObjects)methods[i].invoke(bo1, null);
/* 154 */           BusinessObjects<BusinessObject> subBos2 = (BusinessObjects)methods[i].invoke(bo2, null);
/* 155 */           if (subBos1 != null && subBos2 != null) {
/*     */             
/* 157 */             if (subBos1.size() != subBos2.size()) {
/* 158 */               diffs.add(header + "Cannot Compare " + methods[i].getName().substring(3) + " " + lhsString + ": Size of Collection:" + subBos1
/* 159 */                   .size() + "\t" + rhsString + ": Size of Collection: " + subBos2
/* 160 */                   .size());
/*     */             } else {
/*     */               
/* 163 */               for (int j = 0; j < subBos1.size(); j++)
/*     */               {
/* 165 */                 BusinessObject subBo1 = subBos1.get(j);
/* 166 */                 BusinessObject subBo2 = subBos2.get(j);
/* 167 */                 String tempFieldName = "";
/* 168 */                 if (fieldName != null)
/* 169 */                   tempFieldName = fieldName + "."; 
/* 170 */                 internalFindDifferences(diffs, depth + 1, subBo1, subBo2, tempFieldName + methods[i]
/* 171 */                     .getName().substring(3) + "[" + j + "]", lhsString, rhsString, flexibleTimeComparison);
/*     */               }
/*     */             
/*     */             }
/*     */           
/* 176 */           } else if (subBos1 == null && subBos2 != null) {
/*     */             
/* 178 */             diffs.add(header + "Cannot Compare " + methods[i].getName().substring(3) + " " + lhsString + ": null\t" + rhsString + ": Size of Collection: " + subBos2
/* 179 */                 .size());
/*     */           }
/* 181 */           else if (subBos1 != null && subBos2 == null) {
/*     */             
/* 183 */             diffs.add(header + "Cannot Compare " + methods[i].getName().substring(3) + " " + lhsString + ": Size of Collection:" + subBos1
/* 184 */                 .size() + "\t" + rhsString + ": null");
/*     */           }
/*     */         
/*     */         }
/* 188 */         catch (InvocationTargetException e)
/*     */         {
/* 190 */           e.printStackTrace();
/*     */         }
/* 192 */         catch (IllegalArgumentException e)
/*     */         {
/* 194 */           e.printStackTrace();
/*     */         }
/* 196 */         catch (IllegalAccessException e)
/*     */         {
/* 198 */           e.printStackTrace();
/*     */         }
/*     */       
/*     */       }
/* 202 */       else if (RttiUtil.isSubClassOf(methods[i].getReturnType(), BusinessObject.class) && (methods[i]
/* 203 */         .getParameterTypes()).length == 0 && methods[i].getName().startsWith("get")) {
/*     */ 
/*     */         
/*     */         try {
/* 207 */           BusinessObject subBo1 = (BusinessObject)methods[i].invoke(bo1, null);
/* 208 */           BusinessObject subBo2 = (BusinessObject)methods[i].invoke(bo2, null);
/* 209 */           String tempFieldName = "";
/* 210 */           if (fieldName != null)
/* 211 */             tempFieldName = fieldName + "."; 
/* 212 */           internalFindDifferences(diffs, depth + 1, subBo1, subBo2, tempFieldName + methods[i].getName().substring(3), lhsString, rhsString, flexibleTimeComparison);
/*     */         
/*     */         }
/* 215 */         catch (InvocationTargetException e) {
/*     */           
/* 217 */           e.printStackTrace();
/*     */         }
/* 219 */         catch (IllegalArgumentException e) {
/*     */           
/* 221 */           e.printStackTrace();
/*     */         }
/* 223 */         catch (IllegalAccessException e) {
/*     */           
/* 225 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static String getStringRep(Object obj) {
/* 233 */     if (obj instanceof Calendar) {
/*     */       
/* 235 */       JLbsDateFormatter formatter = new JLbsDateFormatter("dd/MM/yyyy HH:mm:ss");
/* 236 */       return "'" + formatter.format(((Calendar)obj).getTime()) + "'";
/*     */     } 
/* 238 */     return "'" + obj + "'";
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean equals(BusinessObject bo1, BusinessObject bo2) {
/* 243 */     if (bo1 == null && bo2 == null)
/* 244 */       return true; 
/* 245 */     if (bo1 == null && bo2 != null)
/* 246 */       return false; 
/* 247 */     if (bo1 != null && bo2 == null)
/* 248 */       return false; 
/* 249 */     if (bo1 != null && !bo1.getClass().equals(bo2.getClass())) {
/* 250 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 255 */     Map<String, Object> propVals1 = (bo1 == null) ? null : bo1.getPropertyValuesAsMap();
/*     */ 
/*     */     
/* 258 */     Map<String, Object> propVals2 = (bo2 == null) ? null : bo2.getPropertyValuesAsMap();
/*     */     
/* 260 */     if (propVals1 != null && propVals2 != null) {
/*     */       
/* 262 */       if (propVals1.size() != propVals2.size()) {
/* 263 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 273 */       for (Map.Entry<String, Object> key : propVals1.entrySet()) {
/*     */         
/* 275 */         if (propVals1.get(key.getKey()) == null && propVals2.get(key.getKey()) != null)
/* 276 */           return false; 
/* 277 */         if (propVals1.get(key.getKey()) != null && propVals2.get(key.getKey()) == null)
/* 278 */           return false; 
/* 279 */         if (propVals1.get(key.getKey()) != null && propVals2.get(key.getKey()) != null && 
/* 280 */           !propVals1.get(key.getKey()).equals(propVals2.get(key.getKey()))) {
/* 281 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/* 285 */     Method[] methods = bo1.getClass().getMethods();
/* 286 */     for (int i = 0; i < methods.length; i++) {
/*     */       
/* 288 */       if (!RttiUtil.isSubClassOf(methods[i].getReturnType(), BusinessObjects.class) || (methods[i]
/* 289 */         .getParameterTypes()).length != 0 || !methods[i].getName().startsWith("get"))
/*     */       {
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
/* 322 */         if (RttiUtil.isSubClassOf(methods[i].getReturnType(), BusinessObject.class) && (methods[i]
/* 323 */           .getParameterTypes()).length == 0 && methods[i].getName().startsWith("get")) {
/*     */           
/*     */           try {
/*     */             
/* 327 */             BusinessObject subBo1 = (BusinessObject)methods[i].invoke(bo1, null);
/* 328 */             BusinessObject subBo2 = (BusinessObject)methods[i].invoke(bo2, null);
/* 329 */             if (subBo1 != null && subBo2 != null) {
/*     */               
/* 331 */               if (!equals(subBo1, subBo2))
/* 332 */                 return false; 
/*     */             } else {
/* 334 */               if (subBo1 == null && subBo2 != null)
/* 335 */                 return false; 
/* 336 */               if (subBo1 != null && subBo2 == null)
/* 337 */                 return false; 
/*     */             } 
/* 339 */           } catch (InvocationTargetException e) {
/*     */             
/* 341 */             e.printStackTrace();
/*     */           }
/* 343 */           catch (IllegalArgumentException e) {
/*     */             
/* 345 */             e.printStackTrace();
/*     */           }
/* 347 */           catch (IllegalAccessException e) {
/*     */             
/* 349 */             e.printStackTrace();
/*     */           } 
/*     */         }
/*     */       }
/*     */     } 
/* 354 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\BusinessObjectComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */