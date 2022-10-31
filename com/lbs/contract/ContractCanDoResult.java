/*    */ package com.lbs.contract;
/*    */ 
/*    */ import com.lbs.data.Parameter;
/*    */ import com.lbs.localization.ILocalizationServices;
/*    */ import com.lbs.util.XMLDescribeBuffer;
/*    */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
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
/*    */ public class ContractCanDoResult
/*    */   extends Parameter
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @XStreamAlias("CanExecute")
/*    */   private boolean m_CanExecute = false;
/*    */   @XStreamAlias("Messages")
/* 30 */   private List<ContractMessage> m_Messages = new ArrayList<>();
/*    */   @XStreamAlias("Variables")
/* 32 */   private Map<String, String> m_Variables = new HashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getCanExecute() {
/* 40 */     return this.m_CanExecute;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCanExecute(boolean CanExecute) {
/* 45 */     this.m_CanExecute = CanExecute;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<ContractMessage> getMessages() {
/* 50 */     return this.m_Messages;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMessages(List<ContractMessage> Messages) {
/* 55 */     this.m_Messages = Messages;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, String> getVariables() {
/* 60 */     return this.m_Variables;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setVariables(HashMap<String, String> Variables) {
/* 65 */     this.m_Variables = Variables;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected String getTagDescription(ILocalizationServices localizationService) {
/* 71 */     return getResource(localizationService, "UN", -1, -1, "");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 77 */     super.describePropertiesXML(writer, localizationService);
/* 78 */     writer.writeProperty("CanExecute", "boolean", getResource(localizationService, "", 0, 0, ""), false);
/* 79 */     writer.startObjectProperty("Messages", "List<ContractMessage>", getResource(localizationService, "", 0, 0, ""), false);
/* 80 */     writer.writeInnerListObject(ContractMessage.class, localizationService);
/* 81 */     writer.startObjectProperty("Variables", "Map<String, String>", getResource(localizationService, "", 0, 0, ""), false);
/* 82 */     writer.writeInnerMapObject(String.class, String.class, localizationService);
/* 83 */     writer.endObjectProperty();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\contract\ContractCanDoResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */