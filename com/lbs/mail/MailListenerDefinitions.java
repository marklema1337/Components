/*    */ package com.lbs.mail;
/*    */ 
/*    */ import com.lbs.localization.ILocalizationServices;
/*    */ import com.lbs.util.JLbsStringList;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class MailListenerDefinitions
/*    */   extends ArrayList<MailListenerDefinition>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public int indexOfById(int listenerId) {
/* 14 */     for (int i = 0; i < size(); i++) {
/*    */       
/* 16 */       MailListenerDefinition item = get(i);
/* 17 */       if (item.getId() == listenerId)
/* 18 */         return i; 
/*    */     } 
/* 20 */     return -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public MailListenerDefinition getById(int listenerId) {
/* 25 */     int idx = indexOfById(listenerId);
/* 26 */     if (idx >= 0)
/* 27 */       return get(idx); 
/* 28 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsStringList getListenerList(ILocalizationServices localizationService) {
/* 33 */     JLbsStringList list = new JLbsStringList();
/* 34 */     for (MailListenerDefinition item : this)
/*    */     {
/* 36 */       list.addItem(item.getAsResourceItem(localizationService));
/*    */     }
/* 38 */     return list;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mail\MailListenerDefinitions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */