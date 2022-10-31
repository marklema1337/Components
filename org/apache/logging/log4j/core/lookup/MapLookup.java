/*     */ package org.apache.logging.log4j.core.lookup;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.logging.log4j.core.LogEvent;
/*     */ import org.apache.logging.log4j.core.config.plugins.Plugin;
/*     */ import org.apache.logging.log4j.message.MapMessage;
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
/*     */ @Plugin(name = "map", category = "Lookup")
/*     */ public class MapLookup
/*     */   implements StrLookup
/*     */ {
/*     */   private final Map<String, String> map;
/*     */   
/*     */   public MapLookup() {
/*  42 */     this.map = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MapLookup(Map<String, String> map) {
/*  52 */     this.map = map;
/*     */   }
/*     */   
/*     */   static Map<String, String> initMap(String[] srcArgs, Map<String, String> destMap) {
/*  56 */     for (int i = 0; i < srcArgs.length; i++) {
/*  57 */       int next = i + 1;
/*  58 */       String value = srcArgs[i];
/*  59 */       destMap.put(Integer.toString(i), value);
/*  60 */       destMap.put(value, (next < srcArgs.length) ? srcArgs[next] : null);
/*     */     } 
/*  62 */     return destMap;
/*     */   }
/*     */   
/*     */   static HashMap<String, String> newMap(int initialCapacity) {
/*  66 */     return new HashMap<>(initialCapacity);
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
/*     */   @Deprecated
/*     */   public static void setMainArguments(String... args) {
/*  97 */     MainMapLookup.setMainArguments(args);
/*     */   }
/*     */   
/*     */   static Map<String, String> toMap(List<String> args) {
/* 101 */     if (args == null) {
/* 102 */       return null;
/*     */     }
/* 104 */     int size = args.size();
/* 105 */     return initMap(args.<String>toArray(new String[size]), newMap(size));
/*     */   }
/*     */   
/*     */   static Map<String, String> toMap(String[] args) {
/* 109 */     if (args == null) {
/* 110 */       return null;
/*     */     }
/* 112 */     return initMap(args, newMap(args.length));
/*     */   }
/*     */   
/*     */   protected Map<String, String> getMap() {
/* 116 */     return this.map;
/*     */   }
/*     */ 
/*     */   
/*     */   public String lookup(LogEvent event, String key) {
/* 121 */     boolean isMapMessage = (event != null && event.getMessage() instanceof MapMessage);
/* 122 */     if (isMapMessage) {
/* 123 */       String obj = ((MapMessage)event.getMessage()).get(key);
/* 124 */       if (obj != null) {
/* 125 */         return obj;
/*     */       }
/*     */     } 
/* 128 */     if (this.map != null) {
/* 129 */       return this.map.get(key);
/*     */     }
/* 131 */     return null;
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
/*     */   public String lookup(String key) {
/* 146 */     if (key == null || this.map == null) {
/* 147 */       return null;
/*     */     }
/* 149 */     return this.map.get(key);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\lookup\MapLookup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */