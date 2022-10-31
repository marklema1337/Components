/*    */ package com.lbs.transport;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInput;
/*    */ import java.io.ObjectOutput;
/*    */ import java.io.Serializable;
/*    */ import java.util.Iterator;
/*    */ import java.util.Vector;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LbsServerEventList
/*    */   extends Vector
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public LbsServerEvent getEvent(int index) {
/* 25 */     return (LbsServerEvent)get(index);
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized boolean add(Object o) {
/* 30 */     if (!(o instanceof LbsServerEvent))
/* 31 */       return false; 
/* 32 */     return super.add(o);
/*    */   }
/*    */ 
/*    */   
/*    */   public void add(int index, Object element) {
/* 37 */     if (!(element instanceof LbsServerEvent))
/*    */       return; 
/* 39 */     super.add(index, element);
/*    */   }
/*    */ 
/*    */   
/*    */   public static LbsServerEventList readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 44 */     int size = in.readInt();
/* 45 */     if (size == -1) {
/* 46 */       return null;
/*    */     }
/* 48 */     LbsServerEventList list = new LbsServerEventList();
/* 49 */     for (int i = 0; i < size; i++) {
/*    */       
/* 51 */       LbsServerEvent event = new LbsServerEvent();
/* 52 */       event.readExternal(in);
/* 53 */       list.add(event);
/*    */     } 
/*    */     
/* 56 */     return list;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void writeExternal(LbsServerEventList list, ObjectOutput out) throws IOException {
/* 61 */     if (list == null) {
/*    */       
/* 63 */       out.writeInt(-1);
/*    */       
/*    */       return;
/*    */     } 
/* 67 */     out.writeInt(list.size());
/* 68 */     for (Iterator<E> i = list.iterator(); i.hasNext(); ) {
/*    */       
/* 70 */       LbsServerEvent event = (LbsServerEvent)i.next();
/* 71 */       event.writeExternal(out);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\LbsServerEventList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */