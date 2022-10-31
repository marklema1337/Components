/*    */ package org.apache.logging.log4j.core.lookup;
/*    */ 
/*    */ import java.lang.management.ManagementFactory;
/*    */ import java.util.List;
/*    */ import java.util.Map;
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
/*    */ @Plugin(name = "jvmrunargs", category = "Lookup")
/*    */ public class JmxRuntimeInputArgumentsLookup
/*    */   extends MapLookup
/*    */ {
/*    */   public static final JmxRuntimeInputArgumentsLookup JMX_SINGLETON;
/*    */   
/*    */   static {
/* 36 */     List<String> argsList = ManagementFactory.getRuntimeMXBean().getInputArguments();
/* 37 */     JMX_SINGLETON = new JmxRuntimeInputArgumentsLookup(MapLookup.toMap(argsList));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JmxRuntimeInputArgumentsLookup() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JmxRuntimeInputArgumentsLookup(Map<String, String> map) {
/* 49 */     super(map);
/*    */   }
/*    */ 
/*    */   
/*    */   public String lookup(LogEvent event, String key) {
/* 54 */     return lookup(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public String lookup(String key) {
/* 59 */     if (key == null) {
/* 60 */       return null;
/*    */     }
/* 62 */     Map<String, String> map = getMap();
/* 63 */     return (map == null) ? null : map.get(key);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\lookup\JmxRuntimeInputArgumentsLookup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */