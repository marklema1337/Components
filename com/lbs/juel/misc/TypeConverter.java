/*    */ package com.lbs.juel.misc;
/*    */ 
/*    */ import com.lbs.javax.el.ELException;
/*    */ import java.io.Serializable;
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
/*    */ public interface TypeConverter
/*    */   extends Serializable
/*    */ {
/* 27 */   public static final TypeConverter DEFAULT = new TypeConverterImpl();
/*    */   
/*    */   <T> T convert(Object paramObject, Class<T> paramClass) throws ELException;
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\misc\TypeConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */