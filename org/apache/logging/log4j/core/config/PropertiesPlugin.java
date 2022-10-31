/*    */ package org.apache.logging.log4j.core.config;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.logging.log4j.core.config.plugins.Plugin;
/*    */ import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
/*    */ import org.apache.logging.log4j.core.config.plugins.PluginElement;
/*    */ import org.apache.logging.log4j.core.config.plugins.PluginFactory;
/*    */ import org.apache.logging.log4j.core.lookup.Interpolator;
/*    */ import org.apache.logging.log4j.core.lookup.PropertiesLookup;
/*    */ import org.apache.logging.log4j.core.lookup.StrLookup;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Plugin(name = "properties", category = "Core", printObject = true)
/*    */ public final class PropertiesPlugin
/*    */ {
/*    */   @PluginFactory
/*    */   public static StrLookup configureSubstitutor(@PluginElement("Properties") Property[] properties, @PluginConfiguration Configuration config) {
/* 48 */     if (properties == null) {
/* 49 */       return (StrLookup)new Interpolator(config.getProperties());
/*    */     }
/* 51 */     Map<String, String> map = new HashMap<>(config.getProperties());
/*    */     
/* 53 */     for (Property prop : properties) {
/* 54 */       map.put(prop.getName(), prop.getValue());
/*    */     }
/*    */     
/* 57 */     return (StrLookup)new Interpolator((StrLookup)new PropertiesLookup(map), config.getPluginPackages());
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\config\PropertiesPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */