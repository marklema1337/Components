/*     */ package com.lbs.laf.common;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Image;
/*     */ import java.awt.MediaTracker;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import javax.swing.JPanel;
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
/*     */ public class SkinImageLoader
/*     */ {
/*  31 */   private static JPanel ms_Panel = new JPanel();
/*  32 */   private static HashMap ms_Map = new HashMap<>();
/*  33 */   private static HashMap ms_BufferedMap = new HashMap<>();
/*     */   
/*     */   private static GraphicsConfiguration ms_Conf;
/*     */   
/*     */   static {
/*  38 */     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
/*  39 */     ms_Conf = ge.getDefaultScreenDevice().getDefaultConfiguration();
/*     */   }
/*     */ 
/*     */   
/*     */   public static Image getImage(String absolutePath) {
/*     */     Object o;
/*  45 */     synchronized (ms_Map) {
/*     */       
/*  47 */       o = ms_Map.get(absolutePath);
/*     */     } 
/*  49 */     if (o instanceof Image)
/*  50 */       return (Image)o; 
/*  51 */     if (o != null)
/*  52 */       return null; 
/*  53 */     Image img = loadImage(absolutePath);
/*  54 */     synchronized (ms_Map) {
/*     */       
/*  56 */       if (img != null) {
/*  57 */         ms_Map.put(absolutePath, img);
/*     */       } else {
/*  59 */         ms_Map.put(absolutePath, Integer.valueOf(0));
/*     */       } 
/*  61 */     }  return img;
/*     */   }
/*     */ 
/*     */   
/*     */   public static BufferedImage getBufferedImage(String absolutePath) {
/*  66 */     BufferedImage bufImg = (BufferedImage)ms_BufferedMap.get(absolutePath);
/*  67 */     if (bufImg != null)
/*  68 */       return bufImg; 
/*  69 */     Image img = getImage(absolutePath);
/*  70 */     if (img == null)
/*  71 */       return null; 
/*  72 */     if (img instanceof BufferedImage) {
/*  73 */       return (BufferedImage)img;
/*     */     }
/*  75 */     int w = img.getWidth(null);
/*  76 */     int h = img.getHeight(null);
/*     */     
/*  78 */     bufImg = ms_Conf.createCompatibleImage(w, h);
/*  79 */     Graphics g = bufImg.getGraphics();
/*  80 */     g.drawImage(img, 0, 0, w, h, 0, 0, w, h, null);
/*  81 */     ms_BufferedMap.put(absolutePath, bufImg);
/*  82 */     return bufImg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Image loadImage(String absolutePath) {
/*  87 */     InputStream stream = null;
/*     */ 
/*     */     
/*  90 */     try { URL url = null;
/*  91 */       if (absolutePath.startsWith("file:///")) {
/*  92 */         url = new URL(absolutePath);
/*     */       } else {
/*  94 */         url = SkinImageLoader.class.getResource(absolutePath);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  99 */       Image image = null;
/* 100 */       if (url != null)
/* 101 */         image = Toolkit.getDefaultToolkit().createImage(url); 
/* 102 */       if (image == null) {
/*     */         
/* 104 */         stream = SkinImageLoader.class.getResourceAsStream(absolutePath);
/* 105 */         if (stream != null) {
/*     */           
/* 107 */           ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/* 108 */           byte[] buffer = new byte[4096];
/*     */           int read;
/* 110 */           while ((read = stream.read(buffer, 0, buffer.length)) > 0)
/* 111 */             outStream.write(buffer, 0, read); 
/* 112 */           image = Toolkit.getDefaultToolkit().createImage(outStream.toByteArray());
/*     */         } 
/*     */       } 
/* 115 */       if (image != null)
/*     */       {
/* 117 */         MediaTracker mediatracker = new MediaTracker(ms_Panel);
/* 118 */         mediatracker.addImage(image, 0);
/*     */         
/*     */         try {
/* 121 */           mediatracker.waitForID(0);
/*     */         }
/* 123 */         catch (InterruptedException interruptedException) {}
/*     */ 
/*     */         
/* 126 */         return image;
/*     */       }
/*     */        }
/* 129 */     catch (Exception exception)
/*     */     
/*     */     { 
/*     */       try {
/*     */ 
/*     */ 
/*     */         
/* 136 */         if (stream != null) {
/* 137 */           stream.close();
/*     */         }
/* 139 */       } catch (IOException iOException) {} } finally { try { if (stream != null) stream.close();  } catch (IOException iOException) {} }
/*     */ 
/*     */ 
/*     */     
/* 143 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\laf\common\SkinImageLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */