/*    */ package org.apache.logging.log4j.core.lookup;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.Map;
/*    */ import org.apache.logging.log4j.core.LogEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class PropertiesLookup
/*    */   implements StrLookup
/*    */ {
/*    */   private final Map<String, String> properties;
/*    */   
/*    */   public PropertiesLookup(Map<String, String> properties) {
/* 38 */     this
/* 39 */       .properties = (properties == null) ? Collections.<String, String>emptyMap() : properties;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String lookup(LogEvent event, String key) {
/* 47 */     return lookup(key);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String lookup(String key) {
/* 61 */     return (key == null) ? null : this.properties.get(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 66 */     return "PropertiesLookup{properties=" + this.properties + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\lookup\PropertiesLookup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */