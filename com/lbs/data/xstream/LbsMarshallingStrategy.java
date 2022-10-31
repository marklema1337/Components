/*    */ package com.lbs.data.xstream;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConverterLookup;
/*    */ import com.thoughtworks.xstream.core.ReferenceByXPathMarshallingStrategy;
/*    */ import com.thoughtworks.xstream.core.TreeUnmarshaller;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.mapper.Mapper;
/*    */ 
/*    */ public class LbsMarshallingStrategy
/*    */   extends ReferenceByXPathMarshallingStrategy {
/*    */   public LbsMarshallingStrategy(int mode) {
/* 12 */     super(mode);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TreeUnmarshaller createUnmarshallingContext(Object root, HierarchicalStreamReader reader, ConverterLookup converterLookup, Mapper mapper) {
/*    */     try {
/* 18 */       return (TreeUnmarshaller)new LbsUnmarshaller(root, reader, converterLookup, mapper);
/* 19 */     } catch (Exception e) {
/* 20 */       e.printStackTrace();
/* 21 */       return super.createUnmarshallingContext(root, reader, converterLookup, mapper);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\xstream\LbsMarshallingStrategy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */