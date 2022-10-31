/*    */ package com.lbs.data.objects;
/*    */ 
/*    */ import com.lbs.util.StringUtil;
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
/*    */ public class BusinessSchema
/*    */   implements Serializable, Cloneable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected String m_Name;
/*    */   protected String m_Guid;
/*    */   protected Object m_Info;
/*    */   
/*    */   public String getName() {
/* 25 */     return this.m_Name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 30 */     this.m_Name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getGuid() {
/* 35 */     return this.m_Guid;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setGuid(String string) {
/* 40 */     this.m_Guid = string;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCustom() {
/* 45 */     return !StringUtil.isEmpty(this.m_Guid);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getInfo() {
/* 50 */     return this.m_Info;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setInfo(Object info) {
/* 55 */     this.m_Info = info;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\BusinessSchema.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */