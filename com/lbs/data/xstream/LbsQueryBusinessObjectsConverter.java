/*    */ package com.lbs.data.xstream;
/*    */ 
/*    */ import com.lbs.data.query.QueryBusinessObjects;
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.collections.CollectionConverter;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import com.thoughtworks.xstream.mapper.Mapper;
/*    */ 
/*    */ public class LbsQueryBusinessObjectsConverter
/*    */   extends CollectionConverter implements Converter {
/*    */   public LbsQueryBusinessObjectsConverter(Mapper mapper) {
/* 13 */     super(mapper);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canConvert(Class<QueryBusinessObjects> type) {
/* 18 */     return (type == QueryBusinessObjects.class);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void writeItem(Object item, MarshallingContext context, HierarchicalStreamWriter writer) {
/* 23 */     boolean simpleMode = ((Boolean)context.get("LBS_XSTREAM_FLATTEN_JSON")).booleanValue();
/* 24 */     if (!simpleMode)
/* 25 */       writer.startNode("QueryBusinessObject"); 
/* 26 */     context.convertAnother(item);
/* 27 */     if (!simpleMode)
/* 28 */       writer.endNode(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\xstream\LbsQueryBusinessObjectsConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */