/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.LbsClassInstanceProvider;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Toolkit;
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
/*     */ public class JLbsReportMeasureConverter
/*     */ {
/*     */   protected static final double ms_Inch2Cm = 2.54D;
/*     */   protected static final double ms_Rounder = 10000.0D;
/*     */   public static final int CM = 0;
/*     */   public static final int INCH = 1;
/*     */   public static final int DOT = 2;
/*  29 */   protected static ThreadLocal<Integer> ms_CharsPerInch = new ThreadLocal<>();
/*  30 */   protected static ThreadLocal<Integer> ms_LinesPerInch = new ThreadLocal<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getScreenResolution() {
/*     */     try {
/*  36 */       if (!GraphicsEnvironment.isHeadless())
/*  37 */         return Toolkit.getDefaultToolkit().getScreenResolution(); 
/*  38 */       if (JLbsConstants.isRunningServerSide(null))
/*     */       {
/*  40 */         if (!LbsClassInstanceProvider.isRemoteCall())
/*     */         {
/*     */           
/*  43 */           return 96.0D;
/*     */         }
/*     */       }
/*     */     }
/*  47 */     catch (Exception e) {
/*     */       
/*  49 */       if (JLbsConstants.DEBUG && JLbsConstants.LOGLEVEL >= 7)
/*  50 */         System.out.println("JLbsReportMeasureConverter : Exception --> " + e.getMessage()); 
/*     */     } 
/*  52 */     return 72.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getInch2Cm(double inch) {
/*  57 */     return inch * 2.54D;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getCm2Inch(double cm) {
/*  62 */     return cm / 2.54D;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getInch2Dot(double inch, boolean horizontal) {
/*  67 */     return inch * (horizontal ? 
/*  68 */       getCharsPerInch() : 
/*  69 */       getLinesPerInch());
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getDot2Inch(double dot, boolean horizontal) {
/*  74 */     return dot / (horizontal ? 
/*  75 */       getCharsPerInch() : 
/*  76 */       getLinesPerInch());
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getCm2Dot(double cm, boolean horizontal) {
/*  81 */     return getInch2Dot(getCm2Inch(cm), horizontal);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getDot2Cm(double dot, boolean horizontal) {
/*  86 */     return getInch2Cm(getDot2Inch(dot, horizontal));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getCm2PixelX(double cm) {
/*  91 */     return getCm2PixelX(cm, getScreenResolution());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getCm2PixelX(double cm, double resolution) {
/*  96 */     return (int)Math.round(resolution * cm / 2.54D);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getCm2PixelY(double cm) {
/* 101 */     return getCm2PixelY(cm, getScreenResolution());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getCm2PixelY(double cm, double resolution) {
/* 106 */     return (int)Math.round(resolution * cm / 2.54D);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getPixel2CmX(int pixel) {
/* 111 */     return pixel * 2.54D / getScreenResolution();
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getPixel2CmY(int pixel) {
/* 116 */     return pixel * 2.54D / getScreenResolution();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDot2PixelX(double dot) {
/* 121 */     return (int)(getPixelXForDot(getScreenResolution()) * dot);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDot2PixelX(double dot, double resolution) {
/* 126 */     return (int)(getPixelXForDot(resolution) * dot);
/*     */   }
/*     */ 
/*     */   
/*     */   private static long getPixelXForDot(double resolution) {
/* 131 */     return Math.round(resolution / getCharsPerInch());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDot2PixelY(double dot) {
/* 136 */     return (int)(getPixelYForDot(getScreenResolution()) * dot);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDot2PixelY(double dot, double resolution) {
/* 141 */     return (int)(getPixelYForDot(resolution) * dot);
/*     */   }
/*     */ 
/*     */   
/*     */   private static long getPixelYForDot(double resolution) {
/* 146 */     return Math.round(resolution / getLinesPerInch());
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getPixel2DotX(int pixel) {
/* 151 */     return getPixel2DotX(pixel, getScreenResolution());
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getPixel2DotX(int pixel, double resolution) {
/* 156 */     return pixel / getPixelXForDot(resolution);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getPixel2DotY(int pixel) {
/* 161 */     return pixel / getPixelYForDot(getScreenResolution());
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getPixel2DotY(int pixel, double resolution) {
/* 166 */     return pixel / getPixelYForDot(resolution);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getInch2PixelX(double inch) {
/* 171 */     return getInch2PixelX(inch, getScreenResolution());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getInch2PixelX(double inch, double resolution) {
/* 176 */     return (int)Math.round(resolution * inch);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getInch2PixelY(double inch) {
/* 181 */     return getInch2PixelY(inch, getScreenResolution());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getInch2PixelY(double inch, double resolution) {
/* 186 */     return (int)Math.round(resolution * inch);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getPixel2InchX(int pixel) {
/* 191 */     return pixel / getScreenResolution();
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getPixel2InchY(int pixel) {
/* 196 */     return pixel / getScreenResolution();
/*     */   }
/*     */ 
/*     */   
/*     */   public static double convertFrom(int from, int to, double value, boolean horizontal) {
/* 201 */     if (from == to)
/* 202 */       return value; 
/* 203 */     switch (from) {
/*     */       
/*     */       case 0:
/* 206 */         switch (to) {
/*     */           
/*     */           case 1:
/* 209 */             return round(getCm2Inch(value));
/*     */           case 2:
/* 211 */             return round(getCm2Dot(value, horizontal));
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 215 */         switch (to) {
/*     */           
/*     */           case 0:
/* 218 */             return round(getInch2Cm(value));
/*     */           case 2:
/* 220 */             return round(getInch2Dot(value, horizontal));
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 224 */         switch (to) {
/*     */           
/*     */           case 0:
/* 227 */             return round(getDot2Cm(value, horizontal));
/*     */           case 1:
/* 229 */             return round(getDot2Inch(value, horizontal));
/*     */         } 
/*     */         break;
/*     */     } 
/* 233 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   private static double round(double valueToRound) {
/* 238 */     return (int)Math.round(valueToRound * 10000.0D) / 10000.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double convertFromPixel(int to, int value, boolean horizontal) {
/* 243 */     if (to == 0)
/* 244 */       return (int)Math.round(getPixel2CmX(value) * 10000.0D) / 10000.0D; 
/* 245 */     if (to == 1) {
/* 246 */       return (int)Math.round(getPixel2InchX(value) * 10000.0D) / 10000.0D;
/*     */     }
/* 248 */     return (int)Math.round((horizontal ? 
/* 249 */         getPixel2DotX(value) : 
/* 250 */         getPixel2DotY(value)) * 10000.0D) / 10000.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int convertToPixelX(int measure, double value) {
/* 255 */     return convertToPixelX(measure, value, getScreenResolution());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int convertToPixelX(int measure, double value, double resolution) {
/* 260 */     if (measure == 0)
/* 261 */       return getCm2PixelX(value, resolution); 
/* 262 */     if (measure == 1) {
/* 263 */       return getInch2PixelX(value, resolution);
/*     */     }
/* 265 */     return getDot2PixelX(value, resolution);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int convertToPixelY(int measure, double value) {
/* 271 */     return convertToPixelY(measure, value, getScreenResolution());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int convertToPixelY(int measure, double value, double resolution) {
/* 276 */     if (measure == 0)
/* 277 */       return getCm2PixelY(value, resolution); 
/* 278 */     if (measure == 1) {
/* 279 */       return getInch2PixelY(value, resolution);
/*     */     }
/* 281 */     return getDot2PixelY(value, resolution);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Dimension ConvertToScreenSize(int measure, double x, double y) {
/* 286 */     int resX = (measure == 0) ? 
/* 287 */       getCm2PixelX(x) : (
/* 288 */       (measure == 1) ? 
/* 289 */       getInch2PixelX(x) : 
/* 290 */       getDot2PixelX(x));
/* 291 */     int resY = (measure == 0) ? 
/* 292 */       getCm2PixelY(y) : (
/* 293 */       (measure == 1) ? 
/* 294 */       getInch2PixelX(y) : 
/* 295 */       getDot2PixelY(y));
/* 296 */     return new Dimension(resX, resY);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setCharsPerInch(int charsPerInch) {
/* 301 */     ms_CharsPerInch.set(Integer.valueOf(charsPerInch));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getCharsPerInch() {
/* 306 */     Integer value = ms_CharsPerInch.get();
/* 307 */     if (value != null)
/* 308 */       return value.intValue(); 
/* 309 */     return 12;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setLinesPerInch(int linesPerInch) {
/* 314 */     ms_LinesPerInch.set(Integer.valueOf(linesPerInch));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getLinesPerInch() {
/* 319 */     Integer value = ms_LinesPerInch.get();
/* 320 */     if (value != null)
/* 321 */       return value.intValue(); 
/* 322 */     return 6;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsReportMeasureConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */