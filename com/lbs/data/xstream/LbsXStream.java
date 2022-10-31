/*     */ package com.lbs.data.xstream;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.data.objects.BusinessObject;
/*     */ import com.thoughtworks.xstream.MarshallingStrategy;
/*     */ import com.thoughtworks.xstream.XStream;
/*     */ import com.thoughtworks.xstream.converters.Converter;
/*     */ import com.thoughtworks.xstream.converters.DataHolder;
/*     */ import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
/*     */ import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
/*     */ import com.thoughtworks.xstream.mapper.Mapper;
/*     */ import java.io.Externalizable;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class LbsXStream
/*     */   extends XStream
/*     */ {
/*     */   public static final String XSTREAM_FLATTEN_JSON = "LBS_XSTREAM_FLATTEN_JSON";
/*     */   public static final String XSTREAM_ROOT_OBJECT = "LBS_XSTREAM_ROOT_OBJECT";
/*  29 */   private static List<ILbsXStreamAliasListener> ms_XMLProcessors = new ArrayList<>();
/*     */   
/*     */   private MarshallingStrategy delegate;
/*     */   
/*     */   static {
/*     */     try {
/*  35 */       Class<?> c = Class.forName("com.lbs.filter.LbsFilterConverter");
/*  36 */       Object o = c.newInstance();
/*  37 */       if (o instanceof ILbsXStreamAliasListener) {
/*  38 */         ms_XMLProcessors.add((ILbsXStreamAliasListener)o);
/*     */       }
/*  40 */     } catch (Throwable e) {
/*     */       
/*  42 */       LbsConsole.getLogger(LbsXStream.class).error(e, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LbsXStream() {
/*  51 */     registerConverter((Converter)new ReflectionConverter(getMapper(), getReflectionProvider())
/*     */         {
/*     */           
/*     */           public boolean canConvert(Class<?> type)
/*     */           {
/*  56 */             return Externalizable.class.isAssignableFrom(type);
/*     */           }
/*     */         }-10);
/*     */   }
/*     */ 
/*     */   
/*     */   public LbsXStream(JettisonMappedXmlDriver jettisonMappedXmlDriver) {
/*  63 */     super((HierarchicalStreamDriver)jettisonMappedXmlDriver);
/*  64 */     registerConverter((Converter)new ReflectionConverter(getMapper(), getReflectionProvider())
/*     */         {
/*     */           
/*     */           public boolean canConvert(Class<?> type)
/*     */           {
/*  69 */             return Externalizable.class.isAssignableFrom(type);
/*     */           }
/*     */         }-10);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMarshallingStrategy(MarshallingStrategy marshallingStrategy) {
/*  77 */     this.delegate = marshallingStrategy;
/*  78 */     super.setMarshallingStrategy((MarshallingStrategy)new LbsMarshallingStrategy(0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setupAliases() {
/*  84 */     super.setupAliases();
/*  85 */     for (ILbsXStreamAliasListener listener : ms_XMLProcessors)
/*     */     {
/*  87 */       listener.registerAliases(this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void marshal(Object obj, HierarchicalStreamWriter writer, DataHolder dataHolder) {
/*  94 */     if (dataHolder == null)
/*  95 */       dataHolder = new LbsDataHolder(); 
/*  96 */     dataHolder.put("LBS_XSTREAM_ROOT_OBJECT", obj);
/*  97 */     this.delegate.marshal(writer, obj, getConverterLookup(), getMapper(), dataHolder);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object unmarshal(HierarchicalStreamReader reader, Object root, DataHolder dataHolder) {
/* 103 */     return super.unmarshal(reader, root, dataHolder);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setupConverters() {
/* 109 */     super.setupConverters();
/* 110 */     registerConverter(new LbsQueryBusinessObjectsConverter(getMapper()), 0);
/* 111 */     registerConverter(new LbsBasicBusinessObjectsConverter(getMapper()), 0);
/* 112 */     registerConverter(new LbsObjectPropertyValuesConverter(getMapper()), 0);
/*     */     
/*     */     try {
/* 115 */       Class<?> filterConverter = Class.forName("com.lbs.filter.LbsFilterConverter");
/* 116 */       Constructor<?> cons = filterConverter.getConstructor(new Class[] { Mapper.class });
/* 117 */       Object converter = cons.newInstance(new Object[] { getMapper() });
/* 118 */       if (converter instanceof Converter) {
/* 119 */         registerConverter((Converter)converter, 0);
/*     */       }
/* 121 */     } catch (Throwable e) {
/*     */       
/* 123 */       LbsConsole.getLogger(getClass()).error(e, e);
/*     */     } 
/*     */     
/*     */     try {
/* 127 */       Class<?> filterConverter = Class.forName("com.lbs.filter.LbsFilterListConverter");
/* 128 */       Constructor<?> cons = filterConverter.getConstructor(new Class[] { Mapper.class });
/* 129 */       Object converter = cons.newInstance(new Object[] { getMapper() });
/* 130 */       if (converter instanceof Converter) {
/* 131 */         registerConverter((Converter)converter, 0);
/*     */       }
/* 133 */     } catch (Throwable e) {
/*     */       
/* 135 */       LbsConsole.getLogger(getClass()).error(e, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static LbsXStream getInstance() {
/* 141 */     LbsXStream xs = new LbsXStream();
/* 142 */     xs.autodetectAnnotations(true);
/* 143 */     return xs;
/*     */   }
/*     */ 
/*     */   
/*     */   public static LbsXStream getInstanceJson() {
/* 148 */     LbsXStream xs = new LbsXStream(new JettisonMappedXmlDriver());
/* 149 */     xs.autodetectAnnotations(true);
/* 150 */     return xs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object fromXML(String xml) {
/* 156 */     Object obj = super.fromXML(xml);
/* 157 */     if (obj instanceof ILbsXStreamListener)
/* 158 */       ((ILbsXStreamListener)obj).afterXStreamDeserialization(); 
/* 159 */     return obj;
/*     */   }
/*     */   
/*     */   public String toJSON(Object bo, boolean flattenMode) {
/* 163 */     if (!flattenMode)
/* 164 */       return toXML(bo); 
/* 165 */     DataHolder dh = new LbsDataHolder();
/* 166 */     dh.put("LBS_XSTREAM_FLATTEN_JSON", Boolean.TRUE);
/* 167 */     Writer sw = new StringWriter();
/* 168 */     HierarchicalStreamWriter writer = (new JettisonMappedXmlDriver()).createWriter(sw);
/*     */     try {
/* 170 */       marshal(bo, writer, dh);
/*     */     } finally {
/* 172 */       writer.flush();
/*     */     } 
/* 174 */     if (flattenMode) {
/* 175 */       if (bo instanceof BusinessObject) {
/* 176 */         String json = sw.toString();
/* 177 */         json = json.substring(4 + bo.getClass().getName().length(), json.length() - 1);
/* 178 */         return json;
/*     */       } 
/* 180 */       return sw.toString();
/*     */     } 
/* 182 */     return sw.toString();
/*     */   }
/*     */   
/*     */   public Object fromXML(Class clazz, String json) {
/* 186 */     Object o = fromXML("{" + clazz.getName() + ":" + json + "}");
/* 187 */     if (o instanceof BusinessObject) {
/* 188 */       BusinessObject bo = (BusinessObject)o;
/* 189 */       if (bo.getUniqueIdentifier().getSimpleKey() != 0) {
/* 190 */         bo._setState(2);
/*     */       } else {
/* 192 */         bo._setState(0);
/*     */       } 
/* 194 */     }  return o;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\xstream\LbsXStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */