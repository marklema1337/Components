/*    */ package org.apache.logging.log4j.core.lookup;
/*    */ 
/*    */ import org.apache.logging.log4j.Marker;
/*    */ import org.apache.logging.log4j.MarkerManager;
/*    */ import org.apache.logging.log4j.core.LogEvent;
/*    */ import org.apache.logging.log4j.core.config.plugins.Plugin;
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
/*    */ @Plugin(name = "marker", category = "Lookup")
/*    */ public class MarkerLookup
/*    */   extends AbstractLookup
/*    */ {
/*    */   static final String MARKER = "marker";
/*    */   
/*    */   public String lookup(LogEvent event, String key) {
/* 37 */     Marker marker = (event == null) ? null : event.getMarker();
/* 38 */     return (marker == null) ? null : marker.getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public String lookup(String key) {
/* 43 */     return MarkerManager.exists(key) ? key : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\lookup\MarkerLookup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */