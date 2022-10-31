/*    */ package com.lbs.data.multilang;
/*    */ 
/*    */ import com.lbs.data.objects.BusinessObject;
/*    */ import com.lbs.util.LbsClassInstanceProvider;
/*    */ import java.util.ArrayList;
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
/*    */ public class BusinessObjectModification
/*    */ {
/*    */   public static boolean hasChanged(BusinessObject obj) {
/* 23 */     for (int i = 0; i < (BusinessObjectModificationFieldHolder.getInstance()).ms_BusinessObjectList.size(); i++) {
/*    */ 
/*    */       
/* 26 */       IBusinessObjectModificationListener item = (BusinessObjectModificationFieldHolder.getInstance()).ms_BusinessObjectList.get(i);
/* 27 */       if (item.hasChanged(obj)) {
/* 28 */         return true;
/*    */       }
/*    */     } 
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void clearChangedMultilangValues(BusinessObject obj) {
/* 37 */     for (int i = 0; i < (BusinessObjectModificationFieldHolder.getInstance()).ms_BusinessObjectList.size(); i++) {
/*    */ 
/*    */       
/* 40 */       IBusinessObjectModificationListener item = (BusinessObjectModificationFieldHolder.getInstance()).ms_BusinessObjectList.get(i);
/* 41 */       item.clearChangedValues(obj);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void register(IBusinessObjectModificationListener item) {
/* 48 */     (BusinessObjectModificationFieldHolder.getInstance()).ms_BusinessObjectList.add(item);
/*    */   }
/*    */   
/*    */   public static class BusinessObjectModificationFieldHolder
/*    */   {
/* 53 */     private ArrayList<IBusinessObjectModificationListener> ms_BusinessObjectList = new ArrayList<>();
/*    */ 
/*    */     
/*    */     private static BusinessObjectModificationFieldHolder getInstance() {
/* 57 */       return (BusinessObjectModificationFieldHolder)LbsClassInstanceProvider.getInstanceByClass(BusinessObjectModificationFieldHolder.class);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\multilang\BusinessObjectModification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */