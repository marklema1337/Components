/*    */ package com.lbs.data.xstream;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConversionException;
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.ConverterLookup;
/*    */ import com.thoughtworks.xstream.converters.ErrorWriter;
/*    */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*    */ import com.thoughtworks.xstream.core.ReferenceByXPathUnmarshaller;
/*    */ import com.thoughtworks.xstream.core.TreeUnmarshaller;
/*    */ import com.thoughtworks.xstream.core.util.FastStack;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.mapper.Mapper;
/*    */ import java.lang.ref.WeakReference;
/*    */ import java.lang.reflect.Field;
/*    */ import java.lang.reflect.Method;
/*    */ 
/*    */ public class LbsUnmarshaller
/*    */   extends ReferenceByXPathUnmarshaller {
/*    */   private FastStack types_delegate;
/*    */   private WeakReference<Object> parentObject;
/*    */   
/*    */   public LbsUnmarshaller(Object root, HierarchicalStreamReader reader, ConverterLookup converterLookup, Mapper mapper) throws Exception {
/* 23 */     super(root, reader, converterLookup, mapper);
/* 24 */     Field f = TreeUnmarshaller.class.getDeclaredField("types");
/* 25 */     f.setAccessible(true);
/* 26 */     this.types_delegate = (FastStack)f.get(this);
/*    */   }
/*    */   
/*    */   protected Object convert(Object parent, Class type, Converter converter) {
/*    */     try {
/* 31 */       this.types_delegate.push(type);
/* 32 */       this.parentObject = new WeakReference(parent);
/* 33 */       Object result = converter.unmarshal(this.reader, (UnmarshallingContext)this);
/* 34 */       this.types_delegate.popSilently();
/* 35 */       return result;
/* 36 */     } catch (ConversionException conversionException) {
/* 37 */       lbsAddInformationTo(conversionException, type, converter, parent);
/* 38 */       throw conversionException;
/* 39 */     } catch (RuntimeException e) {
/* 40 */       ConversionException conversionException = new ConversionException(e);
/* 41 */       lbsAddInformationTo(conversionException, type, converter, parent);
/* 42 */       throw conversionException;
/*    */     } 
/*    */   }
/*    */   
/*    */   private void lbsAddInformationTo(ConversionException conversionException, Class type, Converter converter, Object parent) {
/*    */     try {
/* 48 */       Method m = TreeUnmarshaller.class.getDeclaredMethod("addInformationTo", new Class[] { ErrorWriter.class, Class.class, Converter.class, Object.class });
/* 49 */       m.setAccessible(true);
/* 50 */       m.invoke(this, new Object[] { conversionException, type, converter, parent });
/* 51 */     } catch (Exception e) {
/* 52 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public Object getParentObject() {
/* 57 */     return (this.parentObject == null) ? null : this.parentObject.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\xstream\LbsUnmarshaller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */