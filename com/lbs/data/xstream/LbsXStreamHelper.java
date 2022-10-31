/*    */ package com.lbs.data.xstream;
/*    */ 
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
/*    */ import java.util.GregorianCalendar;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class LbsXStreamHelper
/*    */ {
/* 15 */   private static final SimpleDateFormat DATE_FORMAT_SEND = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
/* 16 */   private static final SimpleDateFormat DATE_FORMAT_RECV = new SimpleDateFormat("yyyy-MM-dd");
/*    */   
/*    */   public static Object convertToString(Object object) {
/* 19 */     if (object == null)
/* 20 */       return null; 
/* 21 */     if (object.getClass().isArray()) {
/* 22 */       String[] vals = null;
/* 23 */       if (object instanceof int[]) {
/* 24 */         int[] array = (int[])object;
/* 25 */         vals = new String[array.length];
/* 26 */         for (int i = 0; i < array.length; i++)
/* 27 */           vals[i] = toString(Integer.valueOf(array[i])); 
/*    */       } 
/* 29 */       return vals;
/*    */     } 
/* 31 */     if (object instanceof Calendar) {
/* 32 */       Date date = ((Calendar)object).getTime();
/*    */       
/* 34 */       synchronized (DATE_FORMAT_SEND) {
/*    */         
/* 36 */         return DATE_FORMAT_SEND.format(date);
/*    */       } 
/*    */     } 
/* 39 */     return object.toString();
/*    */   }
/*    */   
/*    */   private static String toString(Object object) {
/* 43 */     return (object != null) ? object.toString() : null;
/*    */   }
/*    */   
/*    */   public static Object convertToObject(HierarchicalStreamReader reader, Class<Calendar> type) throws ParseException {
/* 47 */     if (reader == null)
/* 48 */       return null; 
/* 49 */     if (type == Calendar.class || type == GregorianCalendar.class) {
/* 50 */       GregorianCalendar gcal = new GregorianCalendar();
/*    */       
/* 52 */       synchronized (DATE_FORMAT_RECV) {
/*    */         
/* 54 */         gcal.setTime(DATE_FORMAT_RECV.parse(reader.getValue()));
/*    */       } 
/* 56 */       return gcal;
/*    */     } 
/* 58 */     if (type == Date.class)
/*    */     {
/* 60 */       synchronized (DATE_FORMAT_RECV) {
/*    */         
/* 62 */         return DATE_FORMAT_RECV.parse(reader.getValue());
/*    */       } 
/*    */     }
/* 65 */     if (type == boolean.class)
/* 66 */       return Boolean.valueOf(Boolean.parseBoolean(reader.getValue())); 
/* 67 */     if (type == int.class)
/* 68 */       return Integer.valueOf(Integer.parseInt(reader.getValue())); 
/* 69 */     if (type == int[].class) {
/* 70 */       List<Integer> list = new ArrayList<>();
/* 71 */       while (reader.hasMoreChildren()) {
/* 72 */         reader.moveDown();
/* 73 */         list.add(Integer.valueOf(reader.getValue()));
/* 74 */         reader.moveUp();
/*    */       } 
/* 76 */       return list.toArray(new Integer[0]);
/*    */     } 
/* 78 */     return reader.getValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\xstream\LbsXStreamHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */