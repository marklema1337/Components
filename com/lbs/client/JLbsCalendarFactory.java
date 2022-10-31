/*    */ package com.lbs.client;
/*    */ 
/*    */ import com.lbs.console.LbsConsole;
/*    */ import com.lbs.platform.interfaces.IApplicationContext;
/*    */ import com.lbs.remoteclient.IClientContext;
/*    */ import java.lang.reflect.Method;
/*    */ 
/*    */ public class JLbsCalendarFactory
/*    */ {
/*    */   public enum CalendarType
/*    */   {
/* 12 */     Outlook, GoogleCalendar;
/*    */   }
/*    */ 
/*    */   
/*    */   public static ILbsCalendarService getCalendarService(IApplicationContext context, CalendarType type) {
/* 17 */     return getCalendarService(context, type, Boolean.valueOf(true));
/*    */   }
/*    */ 
/*    */   
/*    */   public static ILbsCalendarService getCalendarService(IApplicationContext context, CalendarType type, Boolean useImpersonation) {
/* 22 */     return getCalendarService(context, type, Integer.valueOf(0), useImpersonation);
/*    */   }
/*    */ 
/*    */   
/*    */   public static ILbsCalendarService getCalendarService(IApplicationContext context, CalendarType type, Integer userNr) {
/* 27 */     return getCalendarService(context, type, userNr, Boolean.valueOf(true));
/*    */   }
/*    */ 
/*    */   
/*    */   public static ILbsCalendarService getCalendarService(IApplicationContext context, CalendarType type, Integer userNr, Boolean useImpersonation) {
/* 32 */     Object service = null;
/*    */     
/*    */     try {
/* 35 */       if (type == CalendarType.Outlook) {
/*    */         
/* 37 */         if (context instanceof IClientContext) {
/* 38 */           ((IClientContext)context).loadJAR("ews.jar;joda-time.jar", false, true);
/*    */         }
/* 40 */         Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass("com.lbs.client.JLbsOutlookService");
/* 41 */         service = clazz.newInstance();
/* 42 */         Method method = clazz.getMethod("initialize", new Class[] { IApplicationContext.class, Integer.class, Boolean.class });
/* 43 */         Object init = method.invoke(service, new Object[] { context, userNr, useImpersonation });
/* 44 */         if (!((Boolean)init).booleanValue()) {
/* 45 */           return null;
/*    */         }
/* 47 */       } else if (type == CalendarType.GoogleCalendar) {
/*    */         
/* 49 */         Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass("com.lbs.client.JLbsGoogleCalendarService");
/* 50 */         service = clazz.newInstance();
/*    */       }
/*    */     
/* 53 */     } catch (Exception e) {
/*    */       
/* 55 */       LbsConsole.getLogger("JLbsCalendarFactory").error(e);
/* 56 */       return null;
/*    */     } 
/*    */     
/* 59 */     if (service != null)
/* 60 */       return (ILbsCalendarService)service; 
/* 61 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\client\JLbsCalendarFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */