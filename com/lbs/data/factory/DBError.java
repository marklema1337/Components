/*     */ package com.lbs.data.factory;
/*     */ 
/*     */ import com.lbs.data.database.DBCommandException;
/*     */ import com.lbs.data.expression.IQueryItemGenerator;
/*     */ import com.lbs.data.expression.QueryItemGenerator;
/*     */ import com.lbs.data.query.IQueryCommand;
/*     */ import com.lbs.invoke.RttiUtil;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.localization.LbsLocalizableException;
/*     */ import com.lbs.localization.LbsMessage;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import com.lbs.util.ExceptionUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.sql.SQLException;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DBError
/*     */   extends LbsLocalizableException
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int m_ID;
/*     */   private Hashtable m_ClientProperties;
/*     */   
/*     */   public DBError(int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/*  37 */     super(listID, stringTag, defaultTemplateMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public DBError(int listID, int stringTag, String defaultMessage) {
/*  42 */     super(listID, stringTag, defaultMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public DBError(Throwable cause, int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/*  47 */     super(cause, listID, stringTag, defaultTemplateMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public DBError(Throwable cause, int listID, int stringTag, String defaultMessage) {
/*  52 */     super(cause, listID, stringTag, defaultMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public DBError(int id, int listID, int stringTag, String message) {
/*  57 */     super(listID, stringTag, message);
/*  58 */     setID(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public DBError(String resGroup, int id, int listID, int stringTag, String message) {
/*  63 */     super(resGroup, listID, stringTag, message);
/*  64 */     setID(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public DBError(int id, int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/*  69 */     super(listID, stringTag, defaultTemplateMessage);
/*  70 */     setID(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public DBError(String resGroup, int id, int listID, int stringTag, LbsMessage defaultTemplateMessage) {
/*  75 */     super(resGroup, listID, stringTag, defaultTemplateMessage);
/*  76 */     setID(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DBError(int id, String message) {
/*  85 */     setID(id);
/*  86 */     this.m_DefaultMessage = message;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getID() {
/*  91 */     return this.m_ID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setID(int i) {
/*  96 */     this.m_ID = i; } public void localizeMessage(ILocalizationServices lclService) { Object table;
/*     */     String tableName, segments[];
/*     */     StringBuilder segmentsString;
/*     */     Object o;
/*     */     String queryName, timeOut;
/* 101 */     switch (this.m_ID) {
/*     */       
/*     */       case 31:
/* 104 */         this.m_ListID = -1003;
/* 105 */         this.m_StringTag = 38;
/* 106 */         table = this.m_ClientProperties.get("TableName");
/* 107 */         tableName = "";
/* 108 */         segments = (String[])this.m_ClientProperties.get("IndexSegments");
/* 109 */         segmentsString = new StringBuilder();
/* 110 */         if (segments != null && segments.length > 0)
/*     */         {
/* 112 */           for (int i = 0; i < segments.length; i++) {
/*     */             
/* 114 */             if (i == 0) {
/* 115 */               segmentsString.append(segments[i]);
/*     */             } else {
/* 117 */               segmentsString.append(", " + segments[i]);
/*     */             } 
/*     */           }  } 
/* 120 */         if (table != null)
/*     */         {
/* 122 */           tableName = (String)table;
/*     */         }
/*     */ 
/*     */         
/* 126 */         this
/* 127 */           .m_DefaultTemplateMessage = new LbsMessage("Another record has the same features: '~1' table's '~2' field.", new String[] { tableName, segmentsString.toString() });
/*     */         break;
/*     */       case 12:
/* 130 */         this.m_ListID = -1003;
/* 131 */         this.m_StringTag = 39;
/* 132 */         this.m_DefaultMessage = "Insert, Update and Delete operations are forbidden for periods other than the active one!";
/*     */         break;
/*     */       case 32:
/* 135 */         this.m_ListID = -1003;
/* 136 */         this.m_StringTag = 40;
/* 137 */         this
/*     */ 
/*     */           
/* 140 */           .m_DefaultTemplateMessage = new LbsMessage("There are some records referring to this record. You cannot delete this record without removing those references. Original error message:~1", new String[] { (StringUtil.isEmpty(this.m_DefaultMessage) && getCause() != null) ? getCause().getMessage() : this.m_DefaultMessage });
/*     */         break;
/*     */       
/*     */       case 33:
/* 144 */         this.m_ListID = -1003;
/* 145 */         this.m_StringTag = 70;
/* 146 */         this
/*     */ 
/*     */           
/* 149 */           .m_DefaultTemplateMessage = new LbsMessage("This operation cannot continue since the table in this operation has reached its licensed record count limit. Original error message:~1", new String[] { (StringUtil.isEmpty(this.m_DefaultMessage) && getCause() != null) ? getCause().getMessage() : this.m_DefaultMessage });
/*     */         break;
/*     */       
/*     */       case 52:
/* 153 */         o = this.m_ClientProperties.get("queryName");
/* 154 */         queryName = (o instanceof String) ? (String)o : "?";
/*     */ 
/*     */         
/* 157 */         o = this.m_ClientProperties.get("queryTimeout");
/* 158 */         timeOut = "?";
/* 159 */         if (o != null && RttiUtil.isNumericClass(o.getClass())) {
/*     */           
/* 161 */           double secs = RttiUtil.getNumericValue(o);
/* 162 */           double mins = secs / 60.0D;
/* 163 */           NumberFormat format = DecimalFormat.getInstance();
/* 164 */           format.setMaximumFractionDigits(2);
/* 165 */           timeOut = format.format(mins);
/*     */         } 
/* 167 */         this.m_ListID = -1003;
/* 168 */         this.m_StringTag = 63;
/* 169 */         this.m_DefaultTemplateMessage = new LbsMessage("Query named '~1' is aborted due to time out. Maximum time allowed for a query is '~2' minutes.", new String[] { queryName, timeOut });
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 174 */     super.localizeMessage(lclService); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalizedMessage() {
/* 180 */     if ((getID() == 32 || getID() == 33) && this.m_LocalizedMessage == null)
/* 181 */       localizeMessage(JLbsLocalizer.getLocalizationService()); 
/* 182 */     return super.getLocalizedMessage();
/*     */   }
/*     */   
/*     */   protected static Exception convert2KnownException(Exception e) {
/* 186 */     StringBuilder buffer = new StringBuilder();
/* 187 */     if (e.getCause() != null) {
/*     */       
/* 189 */       buffer.append(e.getCause());
/* 190 */       buffer.append("\n");
/*     */     } 
/* 192 */     buffer.append(e.getClass().getName());
/* 193 */     buffer.append("\n");
/* 194 */     buffer.append(ExceptionUtil.getMessage(e));
/* 195 */     return new Exception(buffer.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public static DBError makeErrorOutOfException(Exception e) {
/* 200 */     if (e instanceof LbsLocalizableException) {
/*     */       
/* 202 */       LbsLocalizableException locEx = (LbsLocalizableException)e;
/* 203 */       int errId = 1;
/* 204 */       if (e instanceof DBError) {
/* 205 */         errId = ((DBError)e).getID();
/*     */       }
/* 207 */       if (locEx.hasSimpleMessage()) {
/* 208 */         return new DBError(locEx.getResourceGroup(), errId, locEx.getListID(), locEx.getStringTag(), locEx
/* 209 */             .getDefaultMessage());
/*     */       }
/* 211 */       return new DBError(locEx.getResourceGroup(), errId, locEx.getListID(), locEx.getStringTag(), locEx
/* 212 */           .getDefaultTemplateMessage());
/*     */     } 
/* 214 */     Exception ex = convert2KnownException(e);
/*     */     
/* 216 */     DBError error = new DBError(ex, -1003, 3, new LbsMessage("Exception: ~1", new String[] { ex.getMessage() }));
/* 217 */     error.setID(1);
/* 218 */     return error;
/*     */   }
/*     */ 
/*     */   
/*     */   public static DBError makeErrorOutOfSQLException(SQLException se) {
/* 223 */     Exception ex = convert2KnownException(se);
/* 224 */     String message = ex.getMessage();
/* 225 */     String sqlState = se.getSQLState();
/* 226 */     if (!StringUtil.isEmpty(sqlState))
/*     */     {
/* 228 */       message = message + ", SQLState : " + sqlState;
/*     */     }
/* 230 */     DBError error = new DBError(ex, -1003, 3, new LbsMessage("Exception: ~1", new String[] { message }));
/*     */     
/* 232 */     error.setID(1);
/* 233 */     return error;
/*     */   }
/*     */ 
/*     */   
/*     */   public Hashtable getClientProperties() {
/* 238 */     if (this.m_ClientProperties == null) {
/* 239 */       this.m_ClientProperties = new Hashtable<>();
/*     */     }
/* 241 */     return this.m_ClientProperties;
/*     */   }
/*     */ 
/*     */   
/*     */   public static DBError makeErrorOutOfSQLException(SQLException se, IQueryCommand paramCommand) {
/* 246 */     Exception ex = convert2KnownException(se);
/* 247 */     String message = ex.getMessage();
/* 248 */     String messages = null;
/*     */     
/* 250 */     int idx = -1;
/*     */     
/*     */     try {
/* 253 */       String commandText = paramCommand.getCommandText((IQueryItemGenerator)new QueryItemGenerator(), null);
/*     */       
/* 255 */       if (commandText.indexOf("INSERT INTO") > -1) {
/*     */         
/* 257 */         idx = commandText.toString().indexOf('(');
/* 258 */         if (idx != -1)
/*     */         {
/* 260 */           messages = paramCommand.getCommandText((IQueryItemGenerator)new QueryItemGenerator(), null).substring(11, idx);
/*     */         }
/*     */       }
/* 263 */       else if (commandText.indexOf("UPDATE") > -1) {
/*     */         
/* 265 */         idx = commandText.toString().indexOf("SET");
/* 266 */         if (idx != -1) {
/*     */ 
/*     */           
/* 269 */           messages = paramCommand.getCommandText((IQueryItemGenerator)new QueryItemGenerator(), null).substring(7, idx);
/* 270 */           idx = messages.indexOf(" ");
/* 271 */           messages = messages.substring(0, idx);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 276 */       String str1 = se.getSQLState();
/* 277 */       if (!StringUtil.isEmpty(str1)) {
/*     */         
/* 279 */         message = message + ", SQLState : " + str1;
/* 280 */         message = message + "'" + messages;
/*     */       } 
/* 282 */       DBError dBError = new DBError(ex, -1003, 3, new LbsMessage("Exception: ~1", new String[] { message }));
/* 283 */       dBError.setID(1);
/*     */     
/*     */     }
/* 286 */     catch (DBCommandException dBCommandException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 291 */     String sqlState = se.getSQLState();
/* 292 */     if (!StringUtil.isEmpty(sqlState)) {
/*     */       
/* 294 */       message = message + ", SQLState : " + sqlState;
/* 295 */       message = message + "'" + messages;
/*     */     } 
/* 297 */     DBError error = new DBError(ex, -1003, 3, new LbsMessage("Exception: ~1", new String[] { message }));
/* 298 */     error.setID(1);
/*     */     
/* 300 */     return error;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\DBError.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */