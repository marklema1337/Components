/*    */ package org.apache.logging.log4j.core.parser;
/*    */ 
/*    */ import com.fasterxml.jackson.databind.ObjectMapper;
/*    */ import org.apache.logging.log4j.core.LogEvent;
/*    */ import org.apache.logging.log4j.core.jackson.Log4jYamlObjectMapper;
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
/*    */ public class YamlLogEventParser
/*    */   extends AbstractJacksonLogEventParser
/*    */ {
/*    */   public YamlLogEventParser() {
/* 28 */     super((ObjectMapper)new Log4jYamlObjectMapper());
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\parser\YamlLogEventParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */