/*    */ package com.lbs.appobjects;
/*    */ 
/*    */ import com.lbs.console.LbsConsole;
/*    */ import com.lbs.util.FileUtil;
/*    */ import com.lbs.util.JLbsFileUtil;
/*    */ import com.lbs.util.JLbsStringUtil;
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.util.Properties;
/*    */ 
/*    */ 
/*    */ public class GOCustVersion
/*    */ {
/* 13 */   public static String VERSION = "0.0.0.0.0-0.0.0";
/*    */   
/*    */   public static final String VERSION_FILE_NAME = "CustVersion.txt";
/*    */   
/*    */   public static void load(String appPath, String fileName, String guid) {
/* 18 */     fileName = JLbsFileUtil.appendPath(appPath, fileName);
/*    */     
/* 20 */     Properties props = new Properties();
/*    */ 
/*    */     
/*    */     try {
/* 24 */       String propsStr = FileUtil.getFileContents(fileName, false);
/* 25 */       ByteArrayInputStream in = new ByteArrayInputStream(propsStr.getBytes());
/* 26 */       props.load(in);
/*    */       
/* 28 */       String prop = props.getProperty(guid);
/* 29 */       if (!JLbsStringUtil.isEmpty(prop)) {
/* 30 */         VERSION = prop;
/*    */       }
/* 32 */     } catch (Exception e) {
/*    */       
/* 34 */       LbsConsole.getLogger("LbsApplication.Client.GOCustVersion").error(null, e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 40 */     load("D:\\Projects\\new_platform_hedef_merge\\logoERP\\WebContent\\Config", "CustVersion.txt", "{D870302C-B3AE-A589-3DC6-E5DCA4068B9F}");
/* 41 */     System.out.println("Read Version:" + VERSION);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\appobjects\GOCustVersion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */