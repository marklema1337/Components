/*    */ package com.lbs.console;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InterfaceAdapter<T>
/*    */   implements JsonSerializer<T>, JsonDeserializer<T>
/*    */ {
/*    */   public JsonElement serialize(T object, Type interfaceType, JsonSerializationContext context) {
/* 21 */     JsonObject wrapper = new JsonObject();
/* 22 */     wrapper.addProperty("type", object.getClass().getName());
/* 23 */     wrapper.add("data", context.serialize(object));
/* 24 */     return (JsonElement)wrapper;
/*    */   }
/*    */ 
/*    */   
/*    */   public T deserialize(JsonElement elem, Type interfaceType, JsonDeserializationContext context) throws JsonParseException {
/* 29 */     JsonObject wrapper = (JsonObject)elem;
/* 30 */     JsonElement typeName = get(wrapper, "type");
/* 31 */     JsonElement data = get(wrapper, "data");
/* 32 */     Type actualType = typeForName(typeName);
/* 33 */     return (T)context.deserialize(data, actualType);
/*    */   }
/*    */   
/*    */   private Type typeForName(JsonElement typeElem) {
/*    */     try {
/* 38 */       return Class.forName(typeElem.getAsString());
/* 39 */     } catch (ClassNotFoundException e) {
/* 40 */       throw new JsonParseException(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   private JsonElement get(JsonObject wrapper, String memberName) {
/* 45 */     JsonElement elem = wrapper.get(memberName);
/* 46 */     if (elem == null) throw new JsonParseException("no '" + memberName + "' member found in what was expected to be an interface wrapper"); 
/* 47 */     return elem;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\console\InterfaceAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */