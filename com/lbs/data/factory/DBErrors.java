/*     */ package com.lbs.data.factory;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.data.IParameterMandatoryListener;
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.data.Parameter;
/*     */ import com.lbs.interfaces.IParameter;
/*     */ import com.lbs.interfaces.ParameterException;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.localization.LbsMessage;
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
/*     */ public class DBErrors
/*     */   extends ArrayList<DBError>
/*     */   implements IParameter
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  27 */   private static transient LbsConsole ms_Logger = null;
/*  28 */   private String m_UserInfoHaveLocked = "";
/*     */ 
/*     */   
/*     */   public DBError item(int index) {
/*  32 */     return get(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DBError addError(int id, String message) {
/*  43 */     DBError error = new DBError(id, message);
/*  44 */     add(error);
/*  45 */     return error;
/*     */   }
/*     */ 
/*     */   
/*     */   public DBError addError(int id, int listID, int stringTag, String message) {
/*  50 */     DBError error = new DBError(id, listID, stringTag, message);
/*  51 */     add(error);
/*  52 */     return error;
/*     */   }
/*     */ 
/*     */   
/*     */   public DBError addError(int id, int listID, int stringTag, LbsMessage message) {
/*  57 */     DBError error = new DBError(id, listID, stringTag, message);
/*  58 */     add(error);
/*  59 */     return error;
/*     */   }
/*     */ 
/*     */   
/*     */   public DBError addError(int id, String resGroup, int listID, int stringTag, String message) {
/*  64 */     DBError error = new DBError(resGroup, id, listID, stringTag, message);
/*  65 */     add(error);
/*  66 */     return error;
/*     */   }
/*     */ 
/*     */   
/*     */   public DBError addError(int id, String resGroup, int listID, int stringTag, LbsMessage message) {
/*  71 */     DBError error = new DBError(resGroup, id, listID, stringTag, message);
/*  72 */     add(error);
/*  73 */     return error;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void localizeErrors(ILocalizationServices lclService) {
/*  79 */     for (int i = 0; i < size(); i++) {
/*     */       
/*  81 */       DBError error = item(i);
/*  82 */       error.localizeMessage(lclService);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void printErrorStackTraces() {
/*  89 */     for (int i = 0; i < size(); i++) {
/*     */       
/*  91 */       DBError error = item(i);
/*  92 */       error.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void load(DBErrors errors) {
/*  99 */     for (int i = 0; i < errors.size(); i++) {
/*     */       
/* 101 */       DBError error = errors.get(i);
/* 102 */       add(error);
/*     */     } 
/* 104 */     errors.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void load(DBErrors errors, String userInfoHaveLocked) {
/* 110 */     for (int i = 0; i < errors.size(); i++) {
/*     */       
/* 112 */       DBError error = errors.get(i);
/* 113 */       add(error);
/*     */     } 
/* 115 */     errors.clear();
/* 116 */     setUserInfoHaveLocked(userInfoHaveLocked);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear(int fromIndex) {
/* 121 */     int len = size();
/* 122 */     if (len > fromIndex) {
/* 123 */       removeRange(fromIndex, len - 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean hasErrors() {
/* 128 */     return (size() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getErrorsString() {
/* 133 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 135 */     if (size() == 0) {
/* 136 */       return "";
/*     */     }
/*     */     
/* 139 */     for (int i = 0; i < size(); i++) {
/*     */       
/* 141 */       DBError error = item(i);
/* 142 */       sb.append(error.getID());
/* 143 */       sb.append(" : ");
/* 144 */       sb.append(error.getLocalizedMessage());
/* 145 */       sb.append("\n");
/*     */     } 
/* 147 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public void showErrors() {
/* 152 */     if (ms_Logger == null)
/*     */     {
/* 154 */       synchronized (DBErrors.class) {
/*     */         
/* 156 */         ms_Logger = LbsConsole.getLogger("Data.Client.DBErrors");
/*     */       } 
/*     */     }
/* 159 */     ms_Logger.error(getErrorsString());
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 164 */     return getErrorsString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsError(int errorID) {
/* 170 */     for (int i = 0; i < size(); i++) {
/*     */       
/* 172 */       DBError error = item(i);
/* 173 */       if (error.getID() == errorID)
/* 174 */         return true; 
/*     */     } 
/* 176 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void describeXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 182 */     writer.startObject(getClass().getName(), "");
/* 183 */     describePropertiesXML(writer, localizationService);
/* 184 */     writer.endObject();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier getParameterIdentifier() {
/* 200 */     return Parameter.getParameterIdentifier(getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getEmptyPropertyNames(IParameterMandatoryListener mandatoryListener) {
/* 206 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUserInfoHaveLocked() {
/* 211 */     return this.m_UserInfoHaveLocked;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUserInfoHaveLocked(String userInfoHaveLocked) {
/* 216 */     this.m_UserInfoHaveLocked = userInfoHaveLocked;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\DBErrors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */