/*    */ package com.lbs.data.xstream;
/*    */ 
/*    */ import com.lbs.data.objects.BasicBusinessObjects;
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.collections.CollectionConverter;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import com.thoughtworks.xstream.mapper.Mapper;
/*    */ 
/*    */ public class LbsBasicBusinessObjectsConverter
/*    */   extends CollectionConverter implements Converter {
/*    */   public LbsBasicBusinessObjectsConverter(Mapper mapper) {
/* 13 */     super(mapper);
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class<BasicBusinessObjects> type) {
/* 17 */     return (type == BasicBusinessObjects.class);
/*    */   }
/*    */   
/*    */   protected void writeItem(Object item, MarshallingContext context, HierarchicalStreamWriter writer) {
/* 21 */     writer.startNode("BusinessObject");
/* 22 */     context.convertAnother(item);
/* 23 */     writer.endNode();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\xstream\LbsBasicBusinessObjectsConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */