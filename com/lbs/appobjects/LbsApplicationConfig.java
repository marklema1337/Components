/*     */ package com.lbs.appobjects;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.localization.LuceneLocalizationServices;
/*     */ import com.lbs.platform.interfaces.IApplicationContext;
/*     */ import com.lbs.platform.interfaces.IDeveloperContext;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.StringUtil;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LbsApplicationConfig
/*     */ {
/*     */   public static final String PROD_J_GUAR = "0350";
/*     */   public static final String PROD_J_EETAH = "0370";
/*     */   public static final String PROD_JUGNU = "0390";
/*     */   public static final String PROD_SAAS = "0601";
/*  36 */   protected static Hashtable<String, ArrayList<String>> ms_ImplementationJars = new Hashtable<>();
/*  37 */   protected static Hashtable<String, String> ms_ClientImplementations = new Hashtable<>();
/*  38 */   protected static Hashtable<String, String> ms_ServerImplementations = new Hashtable<>();
/*  39 */   protected static ArrayList<Integer> ms_ResourceProducts = new ArrayList<>();
/*  40 */   protected static Hashtable<String, Integer> ms_ServerResources = new Hashtable<>();
/*  41 */   protected static Hashtable<String, Integer> ms_ClientResources = new Hashtable<>();
/*     */   protected static String ms_MainFormHelpURL;
/*  43 */   protected static Hashtable<String, String> ms_AppletParameters = new Hashtable<>();
/*     */   
/*  45 */   protected static LbsConsole ms_Logger = LbsConsole.getLogger(LbsApplicationConfig.class);
/*     */ 
/*     */   
/*     */   public static void addAppletParameter(String name, String value) {
/*  49 */     if (name != null && value != null) {
/*  50 */       ms_AppletParameters.put(name, value);
/*     */     }
/*     */   }
/*     */   
/*     */   public static int getAppletParameterAsInt(String id, int defValue) {
/*  55 */     String value = ms_AppletParameters.get(id);
/*  56 */     if (value != null)
/*     */       
/*     */       try {
/*  59 */         return Integer.parseInt(value);
/*     */       }
/*  61 */       catch (NumberFormatException ex) {
/*     */         
/*  63 */         return defValue;
/*     */       }  
/*  65 */     return defValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean getAppletParameterAsBoolean(String id, boolean defValue) {
/*  70 */     String value = ms_AppletParameters.get(id);
/*  71 */     if (value != null && !value.equals("null")) {
/*     */       
/*  73 */       if (value.equals("true"))
/*  74 */         return true; 
/*  75 */       return false;
/*     */     } 
/*  77 */     return defValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getAppletParameter(String id, String def) {
/*  82 */     String value = ms_AppletParameters.get(id);
/*  83 */     if (JLbsStringUtil.isEmpty(value) || value.equals("null")) {
/*  84 */       return def;
/*     */     }
/*  86 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getAppletParameter(String id) {
/*  92 */     String value = ms_AppletParameters.get(id);
/*  93 */     if (JLbsStringUtil.isEmpty(value) && id.equals("INI_TITLE")) {
/*     */       
/*  95 */       String title = "j-platform";
/*     */       
/*     */       try {
/*  98 */         Class<?> appInfoCls = Class.forName("com.lbs.application.info.LbsApplicationInfo");
/*     */         
/* 100 */         Object instance = appInfoCls.newInstance();
/* 101 */         if (instance != null) {
/*     */           
/* 103 */           Field field = appInfoCls.getDeclaredField("INSTALL_PRODUCT_GROUP");
/* 104 */           String grp = (String)field.get(instance);
/* 105 */           if ("0350".equalsIgnoreCase(grp))
/* 106 */             return "j-platform"; 
/* 107 */           if ("0370".equalsIgnoreCase(grp))
/* 108 */             return "j-eetah"; 
/* 109 */           if ("0390".equalsIgnoreCase(grp))
/* 110 */             return "j-platform india"; 
/* 111 */           if ("0601".equalsIgnoreCase(grp)) {
/* 112 */             return "saaserp";
/*     */           }
/*     */         } 
/* 115 */       } catch (Throwable e) {
/*     */         
/* 117 */         LbsConsole.getLogger(LbsApplicationConfig.class).error(e.getMessage(), e);
/*     */       } 
/* 119 */       return title;
/*     */     } 
/*     */     
/* 122 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setAppletParameters(Hashtable<String, String> params) {
/* 127 */     ms_AppletParameters = params;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setClientImplementations(Hashtable<String, String> clientImplementations) {
/* 132 */     ms_ClientImplementations = clientImplementations;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setImplementationJars(Hashtable<String, ArrayList<String>> implementationJars) {
/* 137 */     ms_ImplementationJars = implementationJars;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Hashtable<String, ArrayList<String>> getImplementationJars() {
/* 142 */     return ms_ImplementationJars;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getClientImplementationName(String id) {
/* 147 */     return ms_ClientImplementations.get(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getServerImplementationName(String id) {
/* 152 */     return ms_ServerImplementations.get(id);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> T getClientImplementation(IApplicationContext context, String id, Class[] constructorTypes, Object[] constructorParams, Class<T> expectedClass) {
/* 158 */     return createImpl(context, getClientImplementationName(id), ms_ImplementationJars.get(id), constructorTypes, 
/* 159 */         constructorParams, expectedClass);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> T getServerImplementation(IApplicationContext context, String id, Class[] constructorTypes, Object[] constructorParams, Class<T> expectedClass) {
/* 165 */     return createImpl(context, getServerImplementationName(id), ms_ImplementationJars.get(id), constructorTypes, 
/* 166 */         constructorParams, expectedClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static <T> T createImpl(IApplicationContext context, String implName, ArrayList<String> jars, Class[] constructorTypes, Object[] constructorParams, Class<T> expectedClass) {
/* 173 */     if (StringUtil.isEmpty(implName))
/* 174 */       return null; 
/* 175 */     if (jars != null && context instanceof IDeveloperContext) {
/*     */       
/* 177 */       IDeveloperContext ctx = (IDeveloperContext)context;
/* 178 */       for (int i = 0; i < jars.size(); i++) {
/*     */ 
/*     */         
/*     */         try {
/* 182 */           ctx.loadJAR(jars.get(i), false, true);
/*     */         }
/* 184 */         catch (Exception e) {
/*     */           
/* 186 */           ms_Logger.error(e, e);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*     */     try {
/* 192 */       Class<?> clazz = context.loadClass(implName);
/* 193 */       if (expectedClass.isAssignableFrom(clazz))
/*     */       {
/* 195 */         Class<T> tclazz = (Class)clazz;
/* 196 */         Constructor<T> constructor = tclazz.getConstructor(constructorTypes);
/* 197 */         return constructor.newInstance(constructorParams);
/*     */       }
/*     */     
/* 200 */     } catch (Exception e) {
/*     */       
/* 202 */       ms_Logger.error(e, e);
/*     */     } 
/* 204 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Hashtable<String, String> getClientImplementations() {
/* 209 */     return ms_ClientImplementations;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Hashtable<String, String> getServerImplementations() {
/* 214 */     return ms_ServerImplementations;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getMainFormHelpURL() {
/* 219 */     return ms_MainFormHelpURL;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setMainFormHelpURL(String mainFormHelpURL) {
/* 224 */     ms_MainFormHelpURL = mainFormHelpURL;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ArrayList<Integer> getResourceProducts() {
/* 229 */     return ms_ResourceProducts;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setResourceProducts(ArrayList<Integer> resourceProducts) {
/* 234 */     ms_ResourceProducts = resourceProducts;
/* 235 */     LuceneLocalizationServices.setResourceProducts(resourceProducts);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Hashtable<String, Integer> getServerResources() {
/* 240 */     return ms_ServerResources;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setServerResources(Hashtable<String, Integer> serverResources) {
/* 245 */     ms_ServerResources = serverResources;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getServerResource(String formnameListId) {
/* 250 */     if (!StringUtil.isEmpty(formnameListId))
/*     */     {
/* 252 */       if (ms_ServerResources.containsKey(formnameListId))
/* 253 */         return ((Integer)ms_ServerResources.get(formnameListId)).intValue(); 
/*     */     }
/* 255 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Hashtable<String, Integer> getClientResources() {
/* 260 */     return ms_ClientResources;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setClientResources(Hashtable<String, Integer> clientResources) {
/* 265 */     ms_ClientResources = clientResources;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getClientResource(String formnameListId) {
/* 270 */     if (!StringUtil.isEmpty(formnameListId))
/*     */     {
/* 272 */       if (ms_ClientResources.containsKey(formnameListId))
/* 273 */         return ((Integer)ms_ClientResources.get(formnameListId)).intValue(); 
/*     */     }
/* 275 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getAppletParametersStr() {
/* 280 */     if (ms_AppletParameters != null) {
/*     */       
/* 282 */       Set<Map.Entry<String, String>> appletParams = ms_AppletParameters.entrySet();
/* 283 */       Iterator<Map.Entry<String, String>> it = appletParams.iterator();
/* 284 */       StringBuilder sb = new StringBuilder();
/* 285 */       while (it.hasNext()) {
/*     */         
/* 287 */         Map.Entry<String, String> e = it.next();
/* 288 */         sb.append("<param name=\"");
/* 289 */         sb.append(e.getKey());
/* 290 */         sb.append("\" value=\"");
/* 291 */         sb.append(e.getValue());
/* 292 */         sb.append("\">");
/*     */       } 
/* 294 */       return sb.toString();
/*     */     } 
/* 296 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getJNLPArgumentsStr() {
/* 301 */     if (ms_AppletParameters != null) {
/*     */       
/* 303 */       Set<Map.Entry<String, String>> appletParams = ms_AppletParameters.entrySet();
/* 304 */       Iterator<Map.Entry<String, String>> it = appletParams.iterator();
/* 305 */       StringBuilder sb = new StringBuilder();
/* 306 */       while (it.hasNext()) {
/*     */         
/* 308 */         Map.Entry<String, String> e = it.next();
/* 309 */         sb.append("<argument>");
/* 310 */         sb.append(e.getKey());
/* 311 */         sb.append("=");
/* 312 */         sb.append(e.getValue());
/* 313 */         sb.append("</argument>\n");
/*     */       } 
/* 315 */       return sb.toString();
/*     */     } 
/* 317 */     return "";
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\appobjects\LbsApplicationConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */