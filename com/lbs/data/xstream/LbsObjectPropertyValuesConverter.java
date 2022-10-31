/*    */ package com.lbs.data.xstream;
/*    */ 
/*    */ import com.lbs.data.objects.ObjectPropertyValues;
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.collections.MapConverter;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import com.thoughtworks.xstream.mapper.Mapper;
/*    */ import java.text.ParseException;
/*    */ import java.util.Enumeration;
/*    */ 
/*    */ public class LbsObjectPropertyValuesConverter
/*    */   extends MapConverter
/*    */   implements Converter {
/*    */   public LbsObjectPropertyValuesConverter(Mapper mapper) {
/* 18 */     super(mapper);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canConvert(Class<ObjectPropertyValues> type) {
/* 23 */     return (type == ObjectPropertyValues.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/* 28 */     ObjectPropertyValues values = (ObjectPropertyValues)source;
/* 29 */     Enumeration<String> enums = values.properties();
/* 30 */     while (enums.hasMoreElements()) {
/* 31 */       String key = enums.nextElement();
/*    */       try {
/* 33 */         if (values.get(key) != null) {
/* 34 */           writer.startNode(key);
/* 35 */           writer.setValue((String)LbsXStreamHelper.convertToString(values.get(key)));
/* 36 */           writer.endNode();
/*    */         } 
/* 38 */       } catch (Exception e) {
/* 39 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 46 */     ObjectPropertyValues values = new ObjectPropertyValues();
/* 47 */     while (reader.hasMoreChildren()) {
/* 48 */       reader.moveDown();
/*    */       try {
/* 50 */         values.set(reader.getNodeName(), LbsXStreamHelper.convertToObject(reader, int.class));
/* 51 */       } catch (ParseException e) {
/* 52 */         e.printStackTrace();
/*    */       } 
/* 54 */       reader.moveUp();
/*    */     } 
/* 56 */     return values;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\xstream\LbsObjectPropertyValuesConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */