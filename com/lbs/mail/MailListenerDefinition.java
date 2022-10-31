/*    */ package com.lbs.mail;
/*    */ 
/*    */ import com.lbs.localization.ILocalizationServices;
/*    */ import com.lbs.localization.JLbsResourceId;
/*    */ import com.lbs.util.JLbsStringListItem;
/*    */ import com.lbs.util.JLbsStringUtil;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MailListenerDefinition
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int m_Id;
/*    */   private String m_Name;
/*    */   private Class<MailContractListener> m_ListenerClass;
/*    */   private JLbsResourceId m_NameResource;
/*    */   
/*    */   public MailListenerDefinition() {}
/*    */   
/*    */   public MailListenerDefinition(int id, String name, Class<MailContractListener> listenerClass, JLbsResourceId nameResource) {
/* 26 */     this.m_Id = id;
/* 27 */     this.m_Name = name;
/* 28 */     this.m_ListenerClass = listenerClass;
/* 29 */     this.m_NameResource = nameResource;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getId() {
/* 34 */     return this.m_Id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 39 */     this.m_Id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 44 */     return this.m_Name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 49 */     this.m_Name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public Class<MailContractListener> getListenerClass() {
/* 54 */     return this.m_ListenerClass;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setListenerClass(Class<MailContractListener> listenerClass) {
/* 59 */     this.m_ListenerClass = listenerClass;
/*    */   }
/*    */ 
/*    */   
/*    */   public MailContractListener getListenerInstance() throws InstantiationException, IllegalAccessException {
/* 64 */     return this.m_ListenerClass.newInstance();
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsResourceId getNameResource() {
/* 69 */     return this.m_NameResource;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNameResource(JLbsResourceId nameResource) {
/* 74 */     this.m_NameResource = nameResource;
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsStringListItem getAsResourceItem(ILocalizationServices localizationService) {
/* 79 */     JLbsStringListItem item = new JLbsStringListItem();
/* 80 */     item.setTag(this.m_Id);
/* 81 */     item.setValue(JLbsStringUtil.getResource(localizationService, this.m_Name, this.m_NameResource));
/* 82 */     return item;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mail\MailListenerDefinition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */