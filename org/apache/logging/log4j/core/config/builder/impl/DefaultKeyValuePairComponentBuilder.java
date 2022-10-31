/*    */ package org.apache.logging.log4j.core.config.builder.impl;
/*    */ 
/*    */ import org.apache.logging.log4j.core.config.Configuration;
/*    */ import org.apache.logging.log4j.core.config.builder.api.KeyValuePairComponentBuilder;
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
/*    */ class DefaultKeyValuePairComponentBuilder
/*    */   extends DefaultComponentAndConfigurationBuilder<KeyValuePairComponentBuilder>
/*    */   implements KeyValuePairComponentBuilder
/*    */ {
/*    */   public DefaultKeyValuePairComponentBuilder(DefaultConfigurationBuilder<? extends Configuration> builder, String key, String value) {
/* 30 */     super(builder, "KeyValuePair");
/* 31 */     addAttribute("key", key);
/* 32 */     addAttribute("value", value);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\config\builder\impl\DefaultKeyValuePairComponentBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */