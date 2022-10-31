/*    */ package com.lbs.invoke;
/*    */ 
/*    */ import java.security.AllPermission;
/*    */ import java.security.Permission;
/*    */ import java.security.PermissionCollection;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Enumeration;
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
/*    */ public class RemoteClassPermissions
/*    */   extends PermissionCollection
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 24 */   private ArrayList m_Permissions = new ArrayList();
/*    */ 
/*    */ 
/*    */   
/*    */   public void add(Permission permission) {
/* 29 */     if (this.m_Permissions != null)
/* 30 */       this.m_Permissions.add(permission); 
/*    */   }
/*    */   
/*    */   public synchronized boolean implies(Permission permission) {
/* 34 */     int iSize = (this.m_Permissions != null) ? this.m_Permissions.size() : 0;
/* 35 */     for (int i = 0; i < iSize; i++) {
/* 36 */       if (((Permission)this.m_Permissions.get(i)).implies(permission))
/* 37 */         return true; 
/* 38 */     }  System.out.println("Failed to accquire the permission : " + permission.toString());
/* 39 */     return false;
/*    */   }
/*    */   
/*    */   public Enumeration elements() {
/* 43 */     return new ArrayListEnumaration(this.m_Permissions);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   private static RemoteClassPermissions ms_Default = new RemoteClassPermissions(); static {
/* 50 */     ms_Default.add(new AllPermission());
/*    */   }
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
/*    */   public static RemoteClassPermissions getDefault() {
/* 64 */     return ms_Default;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\invoke\RemoteClassPermissions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */