/*     */ package com.lbs.client;
/*     */ 
/*     */ import com.lbs.appobjects.GOVersion;
/*     */ import com.lbs.platform.client.FileSystemUtil;
/*     */ import com.lbs.remoteclient.IClientContext;
/*     */ import com.lbs.start.JLbsContextLocator;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.sun.management.OperatingSystemMXBean;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.lang.management.RuntimeMXBean;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Properties;
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
/*     */ public class JLbsMinimumRequirements
/*     */ {
/*     */   public static boolean PROCESS_MIN_SYS_REQS = true;
/*     */   
/*     */   public static boolean processMinSystemRequirements(IClientContext context) {
/*  36 */     if (!PROCESS_MIN_SYS_REQS) {
/*  37 */       return true;
/*     */     }
/*     */     
/*     */     try {
/*  41 */       Properties minProps = JLbsContextLocator.getProductVersionProperties();
/*     */       
/*  43 */       if (minProps != null) {
/*     */         
/*  45 */         ArrayList<String> messages = new ArrayList<>();
/*  46 */         boolean warn = GOVersion.getBooleanProp(minProps, "Min.Warn");
/*  47 */         boolean redirect = GOVersion.getBooleanProp(minProps, "Min.Redirect");
/*     */         
/*  49 */         String reqUrl = minProps.getProperty("Min.Url");
/*     */         
/*  51 */         Locale loc = Locale.getDefault(Locale.Category.FORMAT);
/*  52 */         String lang = loc.getLanguage();
/*     */         
/*  54 */         String oSystem = minProps.getProperty("Min.Os");
/*  55 */         if (!JLbsStringUtil.isEmpty(oSystem)) {
/*     */           
/*  57 */           String osName = FileSystemUtil.getOsName();
/*     */           
/*  59 */           String osName_lc = osName.toLowerCase(Locale.UK);
/*  60 */           String oSystem_lc = oSystem.toLowerCase(Locale.UK);
/*  61 */           switch (osName_lc) {
/*     */             
/*     */             case "unix":
/*  64 */               if ("linux".toLowerCase(Locale.UK).indexOf(oSystem_lc) == -1) {
/*     */                 
/*  66 */                 if (lang.equalsIgnoreCase("tr")) {
/*  67 */                   messages.add("Gerekli işletim sistemi: " + oSystem + ". Bulunan işletim sistemi: " + osName); break;
/*     */                 } 
/*  69 */                 messages.add("Operating system must be: " + oSystem + ". Detected operating system: " + osName);
/*     */               } 
/*     */               break;
/*     */             
/*     */             default:
/*  74 */               if (osName_lc.indexOf(oSystem_lc) == -1) {
/*     */                 
/*  76 */                 if (lang.equalsIgnoreCase("tr")) {
/*  77 */                   messages.add("Gerekli işletim sistemi: " + oSystem + ". Bulunan işletim sistemi: " + osName); break;
/*     */                 } 
/*  79 */                 messages.add("Operating system must be: " + oSystem + ". Detected operating system: " + osName);
/*     */               } 
/*     */               break;
/*     */           } 
/*     */         } 
/*  84 */         String osVersion = minProps.getProperty("Min.OsVersion");
/*  85 */         if (!JLbsStringUtil.isEmpty(osVersion)) {
/*     */           
/*  87 */           HashMap<String, Integer> windowsVersions = new HashMap<>();
/*  88 */           windowsVersions.put("2000", Integer.valueOf(1));
/*  89 */           windowsVersions.put("XP", Integer.valueOf(2));
/*  90 */           windowsVersions.put("2003", Integer.valueOf(3));
/*  91 */           windowsVersions.put("Vista", Integer.valueOf(4));
/*  92 */           windowsVersions.put("2008", Integer.valueOf(5));
/*  93 */           windowsVersions.put("7", Integer.valueOf(6));
/*  94 */           windowsVersions.put("2012", Integer.valueOf(7));
/*  95 */           windowsVersions.put("8", Integer.valueOf(8));
/*  96 */           windowsVersions.put("8.1", Integer.valueOf(9));
/*  97 */           windowsVersions.put("10", Integer.valueOf(10));
/*     */           
/*  99 */           HashMap<String, Integer> linuxVersions = new HashMap<>();
/* 100 */           linuxVersions.put("Ubuntu", Integer.valueOf(1));
/* 101 */           linuxVersions.put("CentOS", Integer.valueOf(2));
/*     */           
/* 103 */           int thisOsVersionIndex = -1;
/* 104 */           String thisOsVersion = "";
/*     */           
/* 106 */           if (!JLbsStringUtil.isEmpty(oSystem)) {
/*     */             String osname; int osVersionWinIndex; Object[] windowsVersionKeys; int i, osVersionLinIndex; Object[] linuxVersionKeys; String osver, cmd[]; int j;
/* 108 */             String oSystem_lc = oSystem.toLowerCase(Locale.UK);
/* 109 */             switch (oSystem_lc) {
/*     */               
/*     */               case "windows":
/* 112 */                 osname = System.getProperty("os.name");
/* 113 */                 osVersionWinIndex = ((Integer)windowsVersions.get(osVersion)).intValue();
/* 114 */                 windowsVersionKeys = windowsVersions.keySet().toArray();
/* 115 */                 for (i = 0; i < windowsVersionKeys.length; i++) {
/*     */                   
/* 117 */                   String element = (String)windowsVersionKeys[i];
/* 118 */                   if (osname.contains(element)) {
/*     */                     
/* 120 */                     int index = ((Integer)windowsVersions.get(element)).intValue();
/* 121 */                     if (index > thisOsVersionIndex) {
/*     */                       
/* 123 */                       thisOsVersionIndex = index;
/* 124 */                       thisOsVersion = element;
/*     */                     } 
/*     */                   } 
/*     */                 } 
/* 128 */                 if (thisOsVersionIndex != -1 && thisOsVersionIndex < osVersionWinIndex) {
/*     */                   
/* 130 */                   if (lang.equalsIgnoreCase("tr")) {
/* 131 */                     messages.add("Gerekli en düşük işletim sistemi versiyonu: " + osVersion + ". Bulunan işletim sistemi versiyonu: " + thisOsVersion);
/*     */                     break;
/*     */                   } 
/* 134 */                   messages.add("Operating system version must be at least: " + osVersion + ". Detected operation system version: " + thisOsVersion);
/*     */                 } 
/*     */                 break;
/*     */ 
/*     */               
/*     */               case "linux":
/* 140 */                 osVersionLinIndex = ((Integer)linuxVersions.get(osVersion)).intValue();
/* 141 */                 linuxVersionKeys = linuxVersions.keySet().toArray();
/*     */                 
/* 143 */                 osver = "";
/* 144 */                 cmd = new String[] { "/bin/sh", "-c", "cat /etc/*-release" };
/*     */                 
/*     */                 try {
/* 147 */                   Process p = Runtime.getRuntime().exec(cmd);
/* 148 */                   BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
/* 149 */                   String line = "";
/* 150 */                   while ((line = bri.readLine()) != null) {
/*     */                     
/* 152 */                     String lineLower = line.toLowerCase(Locale.UK);
/* 153 */                     if (lineLower.contains("ubuntu"))
/* 154 */                       osver = "Ubuntu"; 
/* 155 */                     if (lineLower.contains("centos"))
/* 156 */                       osver = "CentOS"; 
/*     */                   } 
/* 158 */                   bri.close();
/*     */                 }
/* 160 */                 catch (IOException e) {
/*     */                   
/* 162 */                   e.printStackTrace();
/*     */                 } 
/*     */                 
/* 165 */                 for (j = 0; j < linuxVersionKeys.length; j++) {
/*     */                   
/* 167 */                   String element = (String)linuxVersionKeys[j];
/* 168 */                   if (osver.contains(element)) {
/*     */                     
/* 170 */                     int index = ((Integer)linuxVersions.get(element)).intValue();
/* 171 */                     if (element.equalsIgnoreCase(osver)) {
/*     */                       
/* 173 */                       thisOsVersionIndex = index;
/* 174 */                       thisOsVersion = element;
/*     */                     } 
/*     */                   } 
/*     */                 } 
/* 178 */                 if (thisOsVersionIndex != -1 && thisOsVersionIndex != osVersionLinIndex) {
/*     */                   
/* 180 */                   if (lang.equalsIgnoreCase("tr")) {
/* 181 */                     messages.add("Gerekli işletim sistemi versiyonu: " + osVersion + ". Bulunan işletim sistemi versiyonu: " + thisOsVersion);
/*     */                     break;
/*     */                   } 
/* 184 */                   messages.add("Operating system version must be: " + osVersion + ". Detected operation system version: " + thisOsVersion);
/*     */                 } 
/*     */                 break;
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           } 
/*     */         } 
/* 194 */         String osArch = minProps.getProperty("Min.OsArchitecture");
/* 195 */         if (!JLbsStringUtil.isEmpty(osArch))
/*     */         {
/* 197 */           if ((osArch.indexOf("64") != -1 && !FileSystemUtil.is64Bit()) || (osArch
/* 198 */             .indexOf("32") != -1 && !FileSystemUtil.is32Bit())) {
/*     */             
/* 200 */             String osArchBit = "32 bits";
/* 201 */             String osArchSelf = System.getProperty("os.arch");
/* 202 */             if (osArchSelf.indexOf("64") != -1) {
/* 203 */               osArchBit = "64 bits";
/*     */             }
/* 205 */             if (lang.equalsIgnoreCase("tr")) {
/* 206 */               messages.add("Gerekli işletim sistemi mimarisi: " + osArch + ". Bulunan işletim sistemi mimarisi: " + osArchBit);
/*     */             } else {
/*     */               
/* 209 */               messages.add("Operating system architecture must be: " + osArch + ". Detected operating system architecture: " + osArchBit);
/*     */             } 
/*     */           } 
/*     */         }
/* 213 */         int heapSize = GOVersion.getIntProp(minProps, "Min.HeapSize");
/* 214 */         if (heapSize > 0) {
/*     */           
/* 216 */           RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
/* 217 */           List<String> arguments = runtimeMxBean.getInputArguments();
/*     */           
/* 219 */           int xmx = 600;
/*     */           
/* 221 */           for (String arg : arguments) {
/*     */             
/* 223 */             String heap = arg.toLowerCase(Locale.UK);
/* 224 */             if (heap.startsWith("-xmx") && arg.length() >= 5) {
/*     */               
/* 226 */               if (heap.endsWith("m"))
/* 227 */                 xmx = Integer.parseInt(arg.substring(4, heap.lastIndexOf("m"))); 
/* 228 */               if (heap.endsWith("g")) {
/* 229 */                 xmx = Integer.parseInt(arg.substring(4, heap.lastIndexOf("g"))) * 1024;
/*     */               }
/*     */             } 
/*     */           } 
/* 233 */           if (xmx < heapSize)
/*     */           {
/* 235 */             if (lang.equalsIgnoreCase("tr")) {
/* 236 */               messages.add("Gerekli en düşük JVM max heap boyutu: " + heapSize + " mb. Bulunan JVM max heap boyutu: " + xmx + " mb.");
/*     */             } else {
/*     */               
/* 239 */               messages.add("JVM max heap size must be at least: " + heapSize + " mb. Detected JVM max heap size: " + xmx + " mb.");
/*     */             } 
/*     */           }
/*     */         } 
/* 243 */         String jvmVersion = minProps.getProperty("Min.JvmVersion");
/* 244 */         if (!JLbsStringUtil.isEmpty(jvmVersion)) {
/*     */           
/* 246 */           String specVersion = ManagementFactory.getRuntimeMXBean().getSpecVersion();
/* 247 */           if (specVersion.compareTo(jvmVersion) < 0)
/*     */           {
/* 249 */             if (lang.equalsIgnoreCase("tr")) {
/* 250 */               messages.add("Gerekli en düşük JVM versiyonu: " + jvmVersion + ". Bulunan JVM versiyonu: " + specVersion);
/*     */             } else {
/*     */               
/* 253 */               messages.add("Minimum JVM version must be: " + jvmVersion + ". Detected JVM version: " + specVersion);
/*     */             }  } 
/*     */         } 
/* 256 */         String jvmArch = minProps.getProperty("Min.JvmArchitecture");
/* 257 */         if (!JLbsStringUtil.isEmpty(jvmArch)) {
/*     */           
/* 259 */           String vmname = ManagementFactory.getRuntimeMXBean().getVmName();
/* 260 */           if ((jvmArch.indexOf("64") != -1 && vmname.indexOf("64") == -1) || (jvmArch
/* 261 */             .indexOf("32") != -1 && vmname.indexOf("64") != -1)) {
/*     */             
/* 263 */             String jvmArchBit = "32 bits";
/* 264 */             if (vmname.indexOf("64") != -1) {
/* 265 */               jvmArchBit = "64 bits";
/*     */             }
/* 267 */             if (lang.equalsIgnoreCase("tr")) {
/* 268 */               messages.add("Gerekli JVM mimarisi: " + jvmArch + ". Bulunan JVM mimarisi: " + jvmArchBit);
/*     */             } else {
/* 270 */               messages.add("JVM architecture must be: " + jvmArch + ". Detected JVM architecture: " + jvmArchBit);
/*     */             } 
/*     */           } 
/* 273 */         }  int diskSpace = GOVersion.getIntProp(minProps, "Min.DiskSpace");
/* 274 */         if (diskSpace > 0) {
/*     */           
/* 276 */           long diskSize = (new File("/")).getTotalSpace();
/* 277 */           long disk = diskSize / 1000000000L;
/* 278 */           if (disk < diskSpace)
/*     */           {
/* 280 */             if (lang.equalsIgnoreCase("tr")) {
/* 281 */               messages.add("Gerekli disk boyutu: " + diskSpace + " gb. Bulunan disk boyutu: " + disk + " gb.");
/*     */             } else {
/* 283 */               messages.add("Disk space must be at least: " + diskSpace + " gb. Detected disk space: " + disk + " gb.");
/*     */             } 
/*     */           }
/*     */         } 
/* 287 */         int ram = GOVersion.getIntProp(minProps, "Min.Ram");
/* 288 */         if (ram > 0) {
/*     */ 
/*     */           
/* 291 */           long memorySize = ((OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
/*     */           
/* 293 */           long memSize = memorySize / 1000000000L;
/* 294 */           if (memSize < ram)
/*     */           {
/* 296 */             if (lang.equalsIgnoreCase("tr")) {
/* 297 */               messages.add("Gerekli en düşük hafıza boyutu: " + ram + " gb. Bulunan hafıza boyutu: " + memSize + " gb.");
/*     */             } else {
/*     */               
/* 300 */               messages.add("Memory must be at least: " + ram + " gb. Detected memory: " + memSize + " gb");
/*     */             } 
/*     */           }
/*     */         } 
/* 304 */         if (messages.size() > 0)
/*     */         {
/* 306 */           takeActionForMinRequirements(warn, redirect, reqUrl, messages, context);
/* 307 */           return false;
/*     */         }
/*     */       
/*     */       } 
/* 311 */     } catch (Exception e) {
/*     */       
/* 313 */       e.printStackTrace();
/*     */     } 
/* 315 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void takeActionForMinRequirements(boolean warn, boolean redirect, String reqUrl, ArrayList<String> messages, IClientContext context) {
/* 321 */     if (messages != null && messages.size() > 0) {
/*     */       
/* 323 */       Locale loc = Locale.getDefault(Locale.Category.FORMAT);
/* 324 */       String lang = loc.getLanguage();
/*     */       
/* 326 */       StringBuffer bf = new StringBuffer();
/* 327 */       if (lang.equalsIgnoreCase("tr")) {
/* 328 */         bf.append("İstemci Konfigürasyon Gereklilik Sorunları: \n\n");
/*     */       } else {
/* 330 */         bf.append("Client Minimum Configuration Requirement Errors: \n\n");
/* 331 */       }  for (int i = 0; i < messages.size(); i++) {
/*     */         
/* 333 */         String element = messages.get(i);
/* 334 */         bf.append(element);
/* 335 */         bf.append("\n");
/*     */       } 
/* 337 */       String errorTitle = "Error";
/* 338 */       if (lang.equalsIgnoreCase("tr")) {
/* 339 */         errorTitle = "Hata";
/*     */       }
/* 341 */       context.showMessage(errorTitle, bf.toString());
/*     */       
/* 343 */       if (redirect && !JLbsStringUtil.isEmpty(reqUrl)) {
/*     */         
/* 345 */         if (!reqUrl.startsWith("http")) {
/* 346 */           context.openURL("http://" + reqUrl, null);
/*     */         } else {
/* 348 */           context.openURL(reqUrl, null);
/*     */         } 
/* 350 */       } else if (warn) {
/* 351 */         context.openURL(context.getRootUri() + "MinSystemWarning.html", null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\client\JLbsMinimumRequirements.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */