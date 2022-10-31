/*    */ package com.lbs.data.xstream;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import java.text.ParseException;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class LbsPropertiesConverter
/*    */   implements Converter
/*    */ {
/*    */   private static boolean serializeNulls = false;
/*    */   
/*    */   public boolean canConvert(Class<Object[]> type) {
/* 17 */     return (type == Object[].class);
/*    */   }
/*    */   
/*    */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/* 21 */     String[] objProps = null;
/* 22 */     Iterator<Object> iter = context.keys();
/* 23 */     while (iter.hasNext()) {
/* 24 */       String key = (String)iter.next();
/* 25 */       if (key.equals("LBS_XSTREAM_ROOT_OBJECT")) {
/* 26 */         Object boCand = context.get(key);
/*    */         try {
/* 28 */           objProps = (String[])boCand.getClass().getDeclaredField("m_PropertyNames").get(null);
/* 29 */         } catch (Exception e) {
/* 30 */           e.printStackTrace();
/*    */         } 
/*    */       } 
/*    */     } 
/* 34 */     if (objProps == null)
/* 35 */       throw new RuntimeException("Couldnt retrieve property names"); 
/* 36 */     Object[] values = (Object[])source;
/*    */     
/* 38 */     if (values.length != objProps.length) {
/*    */       return;
/*    */     }
/*    */     
/* 42 */     for (int i = 0; i < objProps.length; i++) {
/* 43 */       if (values[i] != null || serializeNulls) {
/* 44 */         Object val = LbsXStreamHelper.convertToString(values[i]);
/* 45 */         writer.startNode(objProps[i]);
/* 46 */         if (val instanceof String) {
/* 47 */           writer.setValue((String)val);
/*    */         }
/* 49 */         else if (val instanceof String[]) {
/* 50 */           String[] vals = (String[])val;
/* 51 */           for (String v : vals) {
/* 52 */             writer.startNode("item");
/* 53 */             writer.setValue(v);
/* 54 */             writer.endNode();
/*    */           } 
/*    */         } 
/*    */         
/* 58 */         writer.endNode();
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 64 */     String[] objProps = null;
/* 65 */     Class[] objTypes = null;
/* 66 */     LbsUnmarshaller lum = (LbsUnmarshaller)context;
/* 67 */     Object boCand = lum.getParentObject();
/*    */     try {
/* 69 */       objProps = (String[])boCand.getClass().getDeclaredField("m_PropertyNames").get(null);
/* 70 */       objTypes = (Class[])boCand.getClass().getDeclaredField("m_PropertyTypes").get(null);
/* 71 */     } catch (Exception e) {
/* 72 */       e.printStackTrace();
/*    */     } 
/* 74 */     if (objProps == null)
/* 75 */       throw new RuntimeException("Couldnt retrieve property names"); 
/* 76 */     if (objTypes == null)
/* 77 */       throw new RuntimeException("Couldnt retrieve property types"); 
/* 78 */     Object[] values = new Object[objProps.length];
/* 79 */     while (reader.hasMoreChildren()) {
/* 80 */       reader.moveDown();
/* 81 */       for (int i = 0; i < objProps.length; i++) {
/* 82 */         if (objProps[i].equals(reader.getNodeName())) {
/*    */           try {
/* 84 */             values[i] = LbsXStreamHelper.convertToObject(reader, objTypes[i]);
/* 85 */           } catch (ParseException e) {
/* 86 */             e.printStackTrace();
/*    */           } 
/*    */           break;
/*    */         } 
/*    */       } 
/* 91 */       reader.moveUp();
/*    */     } 
/* 93 */     return values;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\xstream\LbsPropertiesConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */