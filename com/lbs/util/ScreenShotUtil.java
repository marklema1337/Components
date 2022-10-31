/*    */ package com.lbs.util;
/*    */ 
/*    */ import java.awt.AWTException;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.Robot;
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import javax.imageio.ImageIO;
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
/*    */ public class ScreenShotUtil
/*    */ {
/*    */   public static void saveScreenShot(String filePath) throws AWTException, IOException {
/* 27 */     saveScreenShot(filePath, "jpg");
/*    */   }
/*    */ 
/*    */   
/*    */   public static void saveScreenShot(String filePath, String formatName) throws AWTException, IOException {
/* 32 */     BufferedImage image = takeScreenShot();
/* 33 */     ImageIO.write(image, formatName, new File(filePath));
/*    */   }
/*    */ 
/*    */   
/*    */   public static void saveScreenShot(String filePath, int x, int y, int width, int height) throws AWTException, IOException {
/* 38 */     saveScreenShot(filePath, "jpg", x, y, width, height);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void saveScreenShot(String filePath, String formatName, int x, int y, int width, int height) throws AWTException, IOException {
/* 44 */     BufferedImage image = takeScreenShot(x, y, width, height);
/* 45 */     ImageIO.write(image, formatName, new File(filePath));
/*    */   }
/*    */ 
/*    */   
/*    */   public static BufferedImage takeScreenShot() throws AWTException {
/* 50 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/* 51 */     Dimension screenSize = toolkit.getScreenSize();
/* 52 */     Rectangle screenRect = new Rectangle(screenSize);
/* 53 */     Robot robot = new Robot();
/* 54 */     return robot.createScreenCapture(screenRect);
/*    */   }
/*    */ 
/*    */   
/*    */   public static BufferedImage takeScreenShot(int x, int y, int width, int height) throws AWTException {
/* 59 */     Rectangle screenRect = new Rectangle(x, y, width, height);
/* 60 */     Robot robot = new Robot();
/* 61 */     return robot.createScreenCapture(screenRect);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/*    */     try {
/* 68 */       saveScreenShot("D:\\deneme.jpg", "jpg");
/*    */ 
/*    */     
/*    */     }
/* 72 */     catch (AWTException e) {
/*    */       
/* 74 */       e.printStackTrace();
/*    */     }
/* 76 */     catch (IOException e) {
/*    */       
/* 78 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\ScreenShotUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */