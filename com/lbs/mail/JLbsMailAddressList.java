/*     */ package com.lbs.mail;
/*     */ 
/*     */ import com.lbs.data.IParameterMandatoryListener;
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.data.Parameter;
/*     */ import com.lbs.interfaces.IParameter;
/*     */ import com.lbs.interfaces.ParameterException;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import com.lbs.util.StringUtil;
/*     */ import com.lbs.util.XMLDescribeBuffer;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsMailAddressList
/*     */   extends ArrayList<JLbsMailAddress>
/*     */   implements IParameter
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public JLbsMailAddressList() {}
/*     */   
/*     */   public JLbsMailAddressList(String list) {
/*  31 */     if (list == null) {
/*     */       return;
/*     */     }
/*  34 */     String[] addresses = list.split(",");
/*  35 */     for (int i = 0; i < addresses.length; i++) {
/*     */       
/*  37 */       String address = addresses[i];
/*  38 */       if (!StringUtil.isEmpty(address)) {
/*  39 */         add(new JLbsMailAddress(address));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void calculateMailAddresses(IClientContext context, JLbsMailInputObjects inputObjects) {
/*  45 */     for (JLbsMailAddress address : this)
/*     */     {
/*  47 */       address.calculateMailAddress(context, inputObjects);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsMailAddress getAddress(int index) {
/*  53 */     if (index >= 0 && index < size())
/*  54 */       return get(index); 
/*  55 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getValidSize() {
/*  60 */     int size = 0;
/*  61 */     for (JLbsMailAddress item : this) {
/*     */       
/*  63 */       if (!StringUtil.isEmpty(item.getMailAddress()))
/*  64 */         size++; 
/*     */     } 
/*  66 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] toAdressArray() {
/*  71 */     String[] result = new String[getValidSize()];
/*  72 */     for (int i = 0; i < result.length; i++) {
/*     */       
/*  74 */       JLbsMailAddress address = get(i);
/*  75 */       result[i] = address.toString();
/*     */     } 
/*  77 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] toSimpleAdressArray() {
/*  85 */     String[] result = new String[getValidSize()];
/*  86 */     for (int i = 0; i < result.length; i++) {
/*     */       
/*  88 */       JLbsMailAddress address = get(i);
/*  89 */       result[i] = address.getMailAddress();
/*     */     } 
/*  91 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void describeXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/*  97 */     writer.startObject(getClass().getName(), "");
/*  98 */     describePropertiesXML(writer, localizationService);
/*  99 */     writer.endObject();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier getParameterIdentifier() {
/* 110 */     return Parameter.getParameterIdentifier(getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getEmptyPropertyNames(IParameterMandatoryListener mandatoryListener) {
/* 121 */     return new ArrayList<>();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mail\JLbsMailAddressList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */