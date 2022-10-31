/*    */ package org.apache.logging.log4j.core.jackson;
/*    */ 
/*    */ import com.fasterxml.jackson.core.JsonParser;
/*    */ import com.fasterxml.jackson.core.JsonProcessingException;
/*    */ import com.fasterxml.jackson.core.type.TypeReference;
/*    */ import com.fasterxml.jackson.databind.DeserializationContext;
/*    */ import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.logging.log4j.spi.MutableThreadContextStack;
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
/*    */ final class MutableThreadContextStackDeserializer
/*    */   extends StdDeserializer<MutableThreadContextStack>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   MutableThreadContextStackDeserializer() {
/* 40 */     super(MutableThreadContextStack.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public MutableThreadContextStack deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
/* 46 */     List<String> list = (List<String>)jp.readValueAs(new TypeReference<List<String>>() {
/*    */         
/*    */         });
/* 49 */     return new MutableThreadContextStack(list);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\jackson\MutableThreadContextStackDeserializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */