/*      */ package com.lbs.controls;
/*      */ 
/*      */ import com.lbs.control.interfaces.ILbsComponent;
/*      */ import com.lbs.control.interfaces.ILbsContainer;
/*      */ import com.lbs.util.JLbsConstants;
/*      */ import com.lbs.util.LbsClassInstanceProvider;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.GradientPaint;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Insets;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.font.FontRenderContext;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.InputStream;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.SwingConstants;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.border.Border;
/*      */ import javax.swing.text.StyleContext;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JLbsControlHelper
/*      */   implements SwingConstants
/*      */ {
/*   59 */   private static int ms_MaxImageIconSize = 100;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_HighLiteRuler = true;
/*      */ 
/*      */   
/*   66 */   protected Font m_RulerFont = new Font(JLbsConstants.APP_FONT, 0, JLbsConstants.APP_FONT_SIZE - 1);
/*   67 */   protected Color m_RulerTickColor = Color.BLACK;
/*      */   
/*      */   protected Rectangle m_HorzRulerRect;
/*      */   
/*      */   protected Rectangle m_VertRulerRect;
/*      */   
/*      */   protected int m_RulerUnit;
/*      */   
/*      */   public static final int JUSTIFY = 8;
/*      */ 
/*      */   
/*      */   public static Rectangle getClientRect(JComponent c) {
/*   79 */     if (c == null)
/*   80 */       return new Rectangle(); 
/*   81 */     Dimension size = c.getSize();
/*   82 */     return new Rectangle(0, 0, size.width, size.height);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Rectangle getClientRectInsideBorder(JComponent c) {
/*   93 */     if (c == null)
/*   94 */       return new Rectangle(); 
/*   95 */     Dimension size = c.getSize();
/*   96 */     Insets inset = c.getInsets();
/*   97 */     if (inset != null)
/*   98 */       return new Rectangle(inset.left, inset.top, size.width - inset.left - inset.right, 
/*   99 */           size.height - inset.top - inset.bottom); 
/*  100 */     return new Rectangle(0, 0, size.width, size.height);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Rectangle drawStringVCentered(JComponent comp, Graphics g, Rectangle rect, String s, int alignment, boolean calcOnly, boolean clip) {
/*  121 */     FontMetrics fontMetrics = g.getFontMetrics();
/*  122 */     int iTextHeight = (comp != null) ? 
/*  123 */       fontMetrics.getHeight() : 
/*  124 */       fontMetrics.getMaxAscent();
/*  125 */     int iTextWidth = (s != null) ? 
/*  126 */       fontMetrics.stringWidth(s) : 
/*  127 */       0;
/*      */     
/*  129 */     int iRectHeight = rect.height;
/*  130 */     int iLeftText = rect.x;
/*  131 */     int iXOffset = 0;
/*  132 */     int iYOffset = 0;
/*  133 */     Border border = (comp != null) ? 
/*  134 */       comp.getBorder() : 
/*  135 */       null;
/*  136 */     Insets inset = (border != null) ? 
/*  137 */       border.getBorderInsets(comp) : (
/*  138 */       (comp != null) ? 
/*  139 */       comp.getInsets() : 
/*  140 */       null);
/*  141 */     if (inset != null) {
/*      */       
/*  143 */       iRectHeight -= inset.top + inset.bottom;
/*  144 */       iYOffset = inset.top - fontMetrics.getDescent();
/*  145 */       if (alignment == 4) {
/*  146 */         iXOffset = inset.right;
/*  147 */       } else if (alignment == 2) {
/*  148 */         iXOffset = inset.left;
/*      */       } 
/*  150 */     }  int iTopText = Math.max(iTextHeight, iRectHeight / 2 + iTextHeight / 2);
/*  151 */     switch (alignment) {
/*      */       
/*      */       case 2:
/*  154 */         iLeftText += iXOffset;
/*      */         break;
/*      */       
/*      */       case 4:
/*  158 */         iLeftText = rect.x + rect.width - iTextWidth - iXOffset;
/*      */         break;
/*      */       case 0:
/*  161 */         iLeftText = rect.x + (rect.width - iTextWidth) / 2;
/*      */         break;
/*      */     } 
/*  164 */     if (!calcOnly && s != null)
/*      */     {
/*  166 */       if (clip) {
/*      */         
/*  168 */         Shape shape = g.getClip();
/*  169 */         Rectangle clipRect = shape.getBounds();
/*  170 */         clipRect = clipRect.intersection(rect);
/*  171 */         g.setClip(clipRect);
/*  172 */         g.drawString(s, iLeftText, rect.y + iTopText + iYOffset);
/*  173 */         g.setClip(shape);
/*      */       } else {
/*      */         
/*  176 */         g.drawString(s, iLeftText, rect.y + iTopText + iYOffset);
/*      */       }  } 
/*  178 */     return new Rectangle(iLeftText, rect.y + iTopText, iTextWidth, iTextHeight);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawWrappedString(Graphics g, JLbsWrappedText s, Color color, Rectangle textRect, int horizontalAlign, int verticalAlign) {
/*  184 */     drawWrappedString(g, null, s, color, textRect, horizontalAlign, verticalAlign);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawWrappedString(Graphics g, FontMetrics fm, JLbsWrappedText s, Color color, Rectangle textRect, int horizontalAlign, int verticalAlign) {
/*  190 */     if (s == null)
/*      */       return; 
/*  192 */     Dimension size = s.getSize();
/*  193 */     if (size == null || size.width == 0 || size.height == 0)
/*      */       return; 
/*  195 */     if (fm == null)
/*      */     {
/*  197 */       fm = g.getFontMetrics();
/*      */     }
/*  199 */     int txtHeight = fm.getMaxAscent();
/*  200 */     int YPoint = textRect.y + txtHeight;
/*  201 */     switch (verticalAlign) {
/*      */       case 1:
/*      */         break;
/*      */       
/*      */       case 3:
/*  206 */         YPoint += textRect.height - size.height;
/*      */         break;
/*      */       
/*      */       default:
/*  210 */         YPoint += (textRect.height - size.height) / 2;
/*      */         break;
/*      */     } 
/*  213 */     g.setColor(color);
/*  214 */     int count = s.getLineCount();
/*  215 */     int index = 0;
/*      */     
/*  217 */     int lineHeight = fm.getHeight();
/*  218 */     while (index < count) {
/*      */       
/*  220 */       String line = s.getLine(index);
/*  221 */       if (horizontalAlign == 8 && count > 0 && index == count - 1)
/*  222 */         horizontalAlign = 2; 
/*  223 */       drawHorzAlignedString(horizontalAlign, g, fm, line, textRect.x, YPoint, textRect.width);
/*  224 */       YPoint += lineHeight;
/*  225 */       index++;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawHorzAlignedString(int align, Graphics g, String s, int x, int y, int width) {
/*  231 */     drawHorzAlignedString(align, g, null, s, x, y, width);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawHorzAlignedString(int align, Graphics g, FontMetrics fm, String s, int x, int y, int width) {
/*  236 */     if (s == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  241 */     int drawx = x;
/*  242 */     int drawy = y;
/*  243 */     String text = s;
/*  244 */     if (align != 2) {
/*      */       
/*  246 */       int sw = (fm == null) ? 
/*  247 */         g.getFontMetrics().stringWidth(s) : 
/*  248 */         fm.stringWidth(s);
/*  249 */       if (align == 0) {
/*  250 */         drawx += (width - sw) / 2;
/*  251 */       } else if (align == 4) {
/*  252 */         drawx = drawx + width - sw;
/*  253 */       } else if (align == 8) {
/*  254 */         text = fullJustify(s, width, fm, width - sw);
/*      */       } 
/*      */     } 
/*  257 */     g.drawString(text, drawx, drawy);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Rectangle drawStringVCentered(JComponent comp, Graphics g, Rectangle rect, String s, int alignment, boolean calcOnly) {
/*  263 */     return drawStringVCentered(comp, g, rect, s, alignment, calcOnly, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Rectangle drawStringVCentered(JComponent comp, Graphics g, Rectangle rect, String s, int alignment) {
/*  268 */     return drawStringVCentered(comp, g, rect, s, alignment, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Container getRootContainer(Component c) {
/*  279 */     if (c == null)
/*  280 */       return null; 
/*  281 */     Component comp = c;
/*  282 */     Container parent = c.getParent();
/*  283 */     while (parent != null) {
/*      */       
/*  285 */       comp = parent;
/*  286 */       if (comp instanceof java.awt.Window)
/*      */         break; 
/*  288 */       parent = comp.getParent();
/*      */     } 
/*  290 */     return (comp instanceof Container) ? 
/*  291 */       (Container)comp : 
/*  292 */       null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Rectangle getRootCoordinates(Component c) {
/*  303 */     if (c == null)
/*  304 */       return null; 
/*  305 */     Rectangle rect = c.getBounds();
/*      */     
/*      */     try {
/*  308 */       Point p = c.getLocationOnScreen();
/*  309 */       rect.x = p.x;
/*  310 */       rect.y = p.y;
/*  311 */       return rect;
/*      */     }
/*  313 */     catch (Exception exception) {
/*      */ 
/*      */       
/*  316 */       Component comp = c;
/*  317 */       Container parent = c.getParent();
/*  318 */       while (parent != null) {
/*      */         
/*  320 */         comp = parent;
/*  321 */         Rectangle rectParent = comp.getBounds();
/*  322 */         rect.x += rectParent.x;
/*  323 */         rect.y += rectParent.y;
/*  324 */         parent = comp.getParent();
/*      */       } 
/*  326 */       return rect;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Point clientToScreen(Component c, Point p) {
/*  331 */     if (JLbsConstants.isRunningServerSide(c))
/*      */     {
/*  333 */       return new Point(0, 0);
/*      */     }
/*  335 */     if (c != null && p != null) {
/*      */       
/*  337 */       Point px = c.getLocationOnScreen();
/*  338 */       return new Point(px.x + p.x, px.y + p.y);
/*      */     } 
/*  340 */     return p;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Point screenToClient(Component c, Point p) {
/*  345 */     if (c != null && p != null) {
/*      */       
/*  347 */       Point px = c.getLocationOnScreen();
/*  348 */       return new Point(p.x - px.x, p.y - px.y);
/*      */     } 
/*  350 */     return p;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isChildComponent(JComponent parent, Component comp) {
/*  355 */     if (parent != null && comp != null) {
/*      */       
/*  357 */       int iCompCount = parent.getComponentCount();
/*  358 */       for (int i = 0; i < iCompCount; i++) {
/*  359 */         if (comp == parent.getComponent(i))
/*  360 */           return true; 
/*      */       } 
/*  362 */     }  return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isChildComponent(ILbsContainer parent, ILbsComponent comp) {
/*  367 */     if (parent != null && comp != null) {
/*      */       
/*  369 */       int iCompCount = parent.getComponentCount();
/*  370 */       for (int i = 0; i < iCompCount; i++) {
/*  371 */         if (comp == parent.getChildAt(i))
/*  372 */           return true; 
/*      */       } 
/*  374 */     }  return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getClassRelativeFilePath(Class cls, String relDir, String fileName) {
/*  379 */     String className = (cls != null) ? 
/*  380 */       cls.getName() : 
/*  381 */       "";
/*  382 */     int iDot = className.lastIndexOf(".");
/*  383 */     String path = (iDot > 0) ? 
/*  384 */       className.substring(0, iDot) : 
/*  385 */       className;
/*  386 */     path = path.replace('.', '/');
/*  387 */     if (fileName != null)
/*  388 */       if (relDir != null && relDir.length() > 0) {
/*  389 */         path = String.valueOf(path) + relDir + "/" + fileName;
/*      */       } else {
/*  391 */         path = String.valueOf(path) + "/" + fileName;
/*  392 */       }   return path;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static ImageIcon getImageIcon(Class cls, String path) {
/*      */     try {
/*  399 */       ImageIcon img = null;
/*  400 */       String key = getName(cls, path);
/*  401 */       ImageIcon icon = (ImageIcon)(JLbsControlHelperFieldHolder.getInstance()).ms_ImageIcons.get(key);
/*  402 */       if (icon != null)
/*      */       {
/*  404 */         return icon;
/*      */       }
/*  406 */       synchronized ((JLbsControlHelperFieldHolder.getInstance()).ms_ImageIcons) {
/*      */         
/*  408 */         if ((JLbsControlHelperFieldHolder.getInstance()).ms_ImageIcons.size() > ms_MaxImageIconSize)
/*      */         {
/*  410 */           (JLbsControlHelperFieldHolder.getInstance()).ms_ImageIcons = (HashMap)new HashMap<>();
/*      */         }
/*      */       } 
/*      */       
/*  414 */       byte[] imageBytes = getImageIconAsByteArray(cls, path);
/*  415 */       if (imageBytes != null) {
/*      */         
/*  417 */         img = new ImageIcon(imageBytes);
/*  418 */         (JLbsControlHelperFieldHolder.getInstance()).ms_ImageIcons.put(key, img);
/*  419 */         return img;
/*      */       } 
/*      */       
/*  422 */       return null;
/*      */     }
/*  424 */     catch (Exception e) {
/*      */       
/*  426 */       System.out.println(e);
/*      */       
/*  428 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static String getName(Class cls, String path) {
/*  433 */     return String.valueOf(cls.getCanonicalName()) + "-" + path;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] getImageIconAsByteArray(Class cls, String path) {
/*      */     try {
/*  440 */       Class clsPath = (cls != null) ? 
/*  441 */         cls : 
/*  442 */         JLbsControlHelper.class;
/*  443 */       path = (path != null && path.length() > 0 && path.charAt(0) != '/') ? 
/*  444 */         getClassRelativeFilePath(cls, null, path) : 
/*  445 */         path;
/*  446 */       ClassLoader clsLoader = clsPath.getClassLoader();
/*      */       
/*  448 */       InputStream stream = clsLoader.getResourceAsStream(path);
/*      */       
/*  450 */       if (stream == null && path != null && path.length() > 0 && path.charAt(0) != '/' && path.contains("..") && 
/*  451 */         path.indexOf("..") > path.indexOf("/")) {
/*      */         
/*  453 */         String tempPathStr = System.getProperty("user.dir");
/*  454 */         File p1 = new File(tempPathStr, path);
/*  455 */         path = p1.getCanonicalPath();
/*  456 */         path = path.substring((new File(tempPathStr)).getCanonicalPath().length() + 1);
/*  457 */         stream = clsLoader.getResourceAsStream(path);
/*      */       } 
/*      */       
/*  460 */       if (stream == null) {
/*      */         
/*  462 */         ClassLoader threadClsLoader = getGlobalClsLoader();
/*  463 */         if (clsLoader != threadClsLoader && threadClsLoader != null)
/*  464 */           stream = threadClsLoader.getResourceAsStream(path); 
/*  465 */         if (stream == null)
/*  466 */           stream = ClassLoader.getSystemClassLoader().getResourceAsStream(path); 
/*      */       } 
/*  468 */       if (stream == null) {
/*      */         
/*  470 */         path = "/" + path;
/*  471 */         stream = clsPath.getClassLoader().getResourceAsStream(path);
/*  472 */         if (stream == null) {
/*  473 */           return null;
/*      */         }
/*      */       } 
/*      */       
/*  477 */       ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/*  478 */       byte[] buffer = new byte[1024];
/*      */       int read;
/*  480 */       while ((read = stream.read(buffer, 0, buffer.length)) > 0)
/*  481 */         outStream.write(buffer, 0, read); 
/*  482 */       stream.close();
/*  483 */       byte[] data = outStream.toByteArray();
/*  484 */       outStream.close();
/*  485 */       return data;
/*      */     }
/*  487 */     catch (Exception e) {
/*      */       
/*  489 */       System.out.println(e);
/*      */       
/*  491 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void drawHeaderRectangle(Graphics g, Rectangle r, boolean border, boolean hasSearchFilter) {
/*  496 */     fillHeaderRectangle(g, r.x, r.y, r.x + r.width, r.y + r.height, border, hasSearchFilter);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawHeaderRectangle(Graphics g, Rectangle r) {
/*  501 */     fillHeaderRectangle(g, r.x, r.y, r.x + r.width, r.y + r.height, !JLbsConstants.DESKTOP_MODE, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawHeaderRectangle(Graphics g, Rectangle r, boolean hasSearchFilter) {
/*  506 */     fillHeaderRectangle(g, r.x, r.y, r.x + r.width, r.y + r.height, true, hasSearchFilter);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void fillHeaderRectangle(Graphics g, int x1, int y1, int x2, int y2, boolean bBorder) {
/*  511 */     fillHeaderRectangle(g, x1, y1, x2, y2, bBorder, UIManager.getColor("control"), false);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void fillHeaderRectangle(Graphics g, int x1, int y1, int x2, int y2, boolean bBorder, boolean hasSeachFilter) {
/*  516 */     fillHeaderRectangle(g, x1, y1, x2, y2, bBorder, UIManager.getColor("control"), hasSeachFilter);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void fillHeaderRectangle(Graphics g, int x1, int y1, int x2, int y2, boolean bBorder, Color color) {
/*  521 */     fillHeaderRectangle(g, x1, y1, x2, y2, bBorder, false);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void fillHeaderRectangle(Graphics g, int x1, int y1, int x2, int y2, boolean bBorder, Color color, boolean hasSearchFilter) {
/*  527 */     if (g instanceof Graphics2D) {
/*      */       
/*  529 */       Graphics2D g2 = (Graphics2D)g;
/*  530 */       GradientPaint gp = new GradientPaint(x1, y1, color.brighter(), x1, y2, color, false);
/*  531 */       Paint oldPaint = g2.getPaint();
/*  532 */       g2.setPaint(gp);
/*  533 */       g2.fillRect(x1, y1, x2 - x1, y2 - y1);
/*  534 */       g2.setPaint(oldPaint);
/*      */       
/*  536 */       if (x1 == 1 && y1 == 1 && hasSearchFilter)
/*      */       {
/*  538 */         g2.setColor(Color.green);
/*  539 */         g2.fillRect(x1, y1, x2 - x1, y2 - y1);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  544 */       g.fillRect(x1, y1, x2 - x1, y2 - y1);
/*  545 */     }  if (bBorder) {
/*      */       
/*  547 */       g.setColor(UIManager.getColor("control").darker());
/*  548 */       g.drawLine(x2 - 1, y1 - 1, x2 - 1, y2 - 1);
/*  549 */       g.drawLine(x2 - 1, y2 - 1, x1 - 1, y2 - 1);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawFocusRect(Graphics g, Rectangle rect, Color c) {
/*  555 */     Color saveColor = g.getColor();
/*  556 */     g.setColor(c);
/*  557 */     int iRight = rect.x + rect.width;
/*  558 */     int iBottom = rect.y + rect.height;
/*  559 */     drawDottedLine(g, rect.x, rect.y, iRight, rect.y);
/*  560 */     drawDottedLine(g, rect.x, iBottom, iRight, iBottom);
/*  561 */     drawDottedLine(g, rect.x, rect.y, rect.x, iBottom);
/*  562 */     drawDottedLine(g, iRight, rect.y, iRight, iBottom);
/*  563 */     g.setColor(saveColor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2, double dashlength, double spacelength) {
/*  571 */     double linelength = Math.sqrt(((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)));
/*  572 */     double xincdashspace = (x2 - x1) / linelength / (dashlength + spacelength);
/*  573 */     double yincdashspace = (y2 - y1) / linelength / (dashlength + spacelength);
/*  574 */     double xincdash = (x2 - x1) / linelength / dashlength;
/*  575 */     double yincdash = (y2 - y1) / linelength / dashlength;
/*  576 */     int counter = 0;
/*  577 */     for (double i = 0.0D; i < linelength - dashlength; i += dashlength + spacelength) {
/*      */       
/*  579 */       g.drawLine((int)(x1 + xincdashspace * counter), (int)(y1 + yincdashspace * counter), 
/*  580 */           (int)(x1 + xincdashspace * counter + xincdash), (int)(y1 + yincdashspace * counter + yincdash));
/*  581 */       counter++;
/*      */     } 
/*  583 */     if ((dashlength + spacelength) * counter <= linelength) {
/*  584 */       g.drawLine((int)(x1 + xincdashspace * counter), (int)(y1 + yincdashspace * counter), x2, y2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final void drawDottedLine(Graphics g, int x1, int y1, int x2, int y2) {
/*  593 */     float deltaX = (x2 - x1);
/*  594 */     float deltaY = (y2 - y1);
/*  595 */     int width = (deltaX > 0.0F) ? 
/*  596 */       (int)deltaX : 
/*  597 */       (int)-deltaX;
/*  598 */     int height = (deltaY > 0.0F) ? 
/*  599 */       (int)deltaY : 
/*  600 */       (int)-deltaY;
/*      */ 
/*      */ 
/*      */     
/*  604 */     int interval = ((Toolkit.getDefaultToolkit().getScreenSize()).width >= 800) ? 
/*  605 */       2 : 
/*  606 */       1;
/*      */     
/*  608 */     if (width > 0) {
/*      */       
/*  610 */       if (width > height) {
/*      */         
/*  612 */         int start = Math.min(x1, x2);
/*  613 */         int end = Math.max(x1, x2);
/*      */         
/*  615 */         float slope = deltaY / deltaX;
/*  616 */         for (int x = start; x <= end; x++) {
/*      */           
/*  618 */           if (x % interval == 0)
/*      */           {
/*  620 */             int y = (int)(slope * (x - x1) + y1);
/*  621 */             g.drawLine(x, y, x, y);
/*      */           }
/*      */         
/*      */         } 
/*      */       } else {
/*      */         
/*  627 */         int start = Math.min(y1, y2);
/*  628 */         int end = Math.max(y1, y2);
/*      */         
/*  630 */         float slope = deltaX / deltaY;
/*  631 */         for (int y = start; y <= end; y++)
/*      */         {
/*  633 */           if (y % interval == 0)
/*      */           {
/*  635 */             int x = (int)(slope * (y - y1) + x1);
/*  636 */             g.drawLine(x, y, x, y);
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/*  644 */       int x = x1;
/*  645 */       int start = Math.min(y1, y2);
/*  646 */       int end = Math.max(y1, y2);
/*  647 */       for (int y = start; y <= end; y++) {
/*      */         
/*  649 */         if (y % interval == 0)
/*      */         {
/*  651 */           g.drawLine(x, y, x, y);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isEmptyBorder(JComponent c, Border border) {
/*  659 */     if (border == null)
/*  660 */       return true; 
/*  661 */     Insets inset = border.getBorderInsets(c);
/*  662 */     return (inset.left == 0 && inset.top == 0 && inset.right == 0 && inset.bottom == 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void updateUIBorder(JComponent c, String borderName) {
/*  667 */     if (c == null || borderName == null)
/*      */       return; 
/*  669 */     Border border = c.getBorder();
/*  670 */     if (border != null && !(border instanceof javax.swing.border.EmptyBorder) && !isEmptyBorder(c, border)) {
/*  671 */       c.setBorder(UIManager.getBorder(borderName));
/*      */     }
/*      */   }
/*      */   
/*      */   public static boolean isColorEqual(Color c1, Color c2, double tolerance) {
/*  676 */     if (c1 != null && c2 != null) {
/*      */       
/*  678 */       int redDiff = c1.getRed() - c2.getRed();
/*  679 */       int bluDiff = c1.getBlue() - c2.getBlue();
/*  680 */       int greDiff = c1.getGreen() - c2.getGreen();
/*  681 */       double diff = Math.sqrt((redDiff * redDiff + bluDiff * bluDiff + greDiff * greDiff));
/*  682 */       return (diff < tolerance);
/*      */     } 
/*  684 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Dimension adjustControlDimension(JComponent comp, Dimension minDim) {
/*  689 */     if (comp == null)
/*  690 */       return null; 
/*  691 */     Dimension dim = comp.getPreferredSize();
/*  692 */     if (minDim != null) {
/*      */       
/*  694 */       dim.width = Math.max(dim.width, minDim.width);
/*  695 */       dim.height = Math.max(dim.height, minDim.height);
/*      */     } 
/*  697 */     return dim;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int getCountOfChar(String s, char c) {
/*  702 */     if (s == null || s.length() == 0)
/*  703 */       return 0; 
/*  704 */     int idx = 0;
/*  705 */     int pos = 0;
/*  706 */     int count = 0;
/*  707 */     while (idx < s.length() && pos >= 0) {
/*      */       
/*  709 */       pos = s.indexOf(c, idx);
/*  710 */       if (pos >= 0) {
/*      */         
/*  712 */         idx = pos + 1;
/*  713 */         count++;
/*      */       } 
/*      */     } 
/*  716 */     return count;
/*      */   }
/*      */   
/*      */   public static Dimension measureString(String s, Graphics g, Font f) {
/*      */     FontMetrics fm;
/*  721 */     if (s == null || s.length() == 0 || f == null) {
/*  722 */       return null;
/*      */     }
/*  724 */     if (g == null) {
/*      */       
/*  726 */       StyleContext context = new StyleContext();
/*  727 */       fm = context.getFontMetrics(f);
/*      */     } else {
/*      */       
/*  730 */       fm = g.getFontMetrics();
/*      */     } 
/*  732 */     int dotCount = getCountOfChar(s, '.');
/*  733 */     float delta = 1.1F;
/*  734 */     int width = fm.stringWidth(s) - Math.round(delta * dotCount);
/*  735 */     return new Dimension(width, fm.getHeight());
/*      */   }
/*      */ 
/*      */   
/*      */   public static Dimension measureStringCorrected(String s, Graphics g, Font f) {
/*  740 */     if (s == null || s.length() == 0 || f == null || g == null) {
/*  741 */       return null;
/*      */     }
/*  743 */     Graphics2D g2 = (Graphics2D)g;
/*  744 */     FontRenderContext frc = g2.getFontRenderContext();
/*  745 */     Rectangle2D bounds = f.getStringBounds(s, frc);
/*      */ 
/*      */     
/*  748 */     return new Dimension((int)bounds.getWidth() + 2, g2.getFontMetrics().getHeight());
/*      */   }
/*      */ 
/*      */   
/*      */   public static ClassLoader getGlobalClsLoader() {
/*  753 */     synchronized (JLbsControlHelper.class) {
/*      */       
/*  755 */       return (JLbsControlHelperFieldHolder.getInstance()).ms_GlobalClsLoader;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setGlobalClsLoader(ClassLoader loader) {
/*  761 */     synchronized (JLbsControlHelper.class) {
/*      */       
/*  763 */       (JLbsControlHelperFieldHolder.getInstance()).ms_GlobalClsLoader = loader;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static FontMetrics getFontMetrics(Font f) {
/*  769 */     StyleContext ctx = new StyleContext();
/*  770 */     return ctx.getFontMetrics(f);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void paintHorizontalRuler(Graphics g, Rectangle rect, boolean bMaskOnly, int scrollX) {
/*  775 */     this.m_HorzRulerRect = new Rectangle(rect);
/*  776 */     rect.y += 3;
/*  777 */     rect.height -= 6;
/*  778 */     g.setColor(UIManager.getColor("window"));
/*  779 */     g.setPaintMode();
/*  780 */     if (!bMaskOnly)
/*  781 */       g.fillRect(rect.x, rect.y, rect.width, rect.height); 
/*  782 */     double increment = (this.m_RulerUnit == 0) ? 
/*  783 */       0.25D : 
/*  784 */       0.125D;
/*  785 */     double tick = Math.floor(JLbsReportMeasureConverter.convertFromPixel(this.m_RulerUnit, scrollX, true) + 0.01D) + increment;
/*  786 */     int longTick = (this.m_RulerUnit == 0) ? 
/*  787 */       2 : 
/*  788 */       4;
/*  789 */     int longTick2 = longTick * 2;
/*  790 */     int tickIndex = 1 + (int)tick * longTick * 2;
/*  791 */     int unitIndex = 1 + (int)tick;
/*  792 */     int[] tickLen = { 1, 2 };
/*  793 */     int midPoint = rect.y + rect.height / 2;
/*  794 */     int endPoint = rect.y + rect.height;
/*  795 */     int rectLimit = rect.x + rect.width;
/*  796 */     if (!bMaskOnly) {
/*      */       
/*  798 */       Shape saveClip = g.getClip();
/*  799 */       g.setClip(rect.x, rect.y, rect.width, rect.height);
/*  800 */       g.setColor(this.m_RulerTickColor);
/*  801 */       g.setFont(this.m_RulerFont);
/*  802 */       g.setXORMode(Color.WHITE);
/*      */ 
/*      */       
/*      */       while (true) {
/*  806 */         int tickX = rect.x + JLbsReportMeasureConverter.convertToPixelX(this.m_RulerUnit, tick) - scrollX;
/*  807 */         if (tickX >= rectLimit)
/*      */           break; 
/*  809 */         if (tickX > rect.x)
/*      */         {
/*  811 */           if (tickIndex % longTick2 == 0) {
/*      */             
/*  813 */             String s = Integer.toString(tickIndex / longTick2);
/*  814 */             Dimension dim = measureString(s, g, this.m_RulerFont);
/*  815 */             int start = tickX - dim.width / 2;
/*  816 */             int limit = Math.min(start + dim.width, rect.x + rect.width);
/*  817 */             drawStringVCentered(null, g, new Rectangle(start, rect.y, limit, endPoint - 4), s, 
/*  818 */                 2, false, false);
/*  819 */             unitIndex++;
/*      */           }
/*      */           else {
/*      */             
/*  823 */             int tickTop = tickLen[(tickIndex % longTick == 0) ? 
/*  824 */                 1 : 
/*  825 */                 0];
/*  826 */             g.drawLine(tickX, midPoint - tickTop, tickX, midPoint + tickTop);
/*      */           } 
/*      */         }
/*  829 */         tickIndex++;
/*  830 */         tick = tickIndex / longTick2;
/*      */       } 
/*      */       
/*  833 */       g.setClip(saveClip);
/*      */     } 
/*  835 */     g.setPaintMode();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void paintVerticalRuler(Graphics g, Rectangle rect, boolean bMaskOnly, int scrollY) {
/*  840 */     this.m_VertRulerRect = new Rectangle(rect);
/*  841 */     rect.x += 3;
/*  842 */     rect.width -= 6;
/*  843 */     Shape saveClip = g.getClip();
/*  844 */     double increment = (this.m_RulerUnit == 0) ? 
/*  845 */       0.25D : 
/*  846 */       0.125D;
/*  847 */     int longTick = (this.m_RulerUnit == 0) ? 
/*  848 */       2 : 
/*  849 */       4;
/*  850 */     int longTick2 = longTick * 2;
/*  851 */     int[] tickLen = { 1, 2 };
/*  852 */     g.setPaintMode();
/*  853 */     Rectangle secRect = rect;
/*  854 */     Rectangle rulerRect = rect.intersection(secRect);
/*  855 */     g.setClip(rulerRect.x, rulerRect.y, rulerRect.width, rulerRect.height);
/*  856 */     if (!bMaskOnly) {
/*      */       
/*  858 */       g.setColor(UIManager.getColor("window"));
/*  859 */       g.fillRect(rulerRect.x, rulerRect.y, rulerRect.width, rulerRect.height);
/*      */     } 
/*  861 */     g.setColor(this.m_RulerTickColor);
/*  862 */     if (!bMaskOnly) {
/*      */       
/*  864 */       g.setXORMode(Color.WHITE);
/*  865 */       int midPoint = rulerRect.x + rulerRect.width / 2;
/*  866 */       int rulerRectLimit = rulerRect.y + rulerRect.height;
/*  867 */       double tick = increment;
/*  868 */       int tickIndex = 1;
/*  869 */       g.setFont(this.m_RulerFont);
/*      */ 
/*      */       
/*      */       while (true) {
/*  873 */         int tickY = secRect.y + JLbsReportMeasureConverter.convertToPixelY(this.m_RulerUnit, tick) - scrollY;
/*  874 */         if (tickY >= rulerRectLimit)
/*      */           break; 
/*  876 */         if (tickY > rulerRect.y)
/*      */         {
/*  878 */           if (tickIndex % longTick2 == 0) {
/*      */             
/*  880 */             String s = Integer.toString(tickIndex / longTick2);
/*  881 */             Dimension dim = measureString(s, g, this.m_RulerFont);
/*  882 */             int startY = tickY + dim.height / 2;
/*  883 */             int startX = rulerRect.x + (rulerRect.width - dim.width + 1) / 2;
/*  884 */             g.drawString(s, startX, startY);
/*      */           }
/*      */           else {
/*      */             
/*  888 */             int tickTop = tickLen[(tickIndex % longTick == 0) ? 
/*  889 */                 1 : 
/*  890 */                 0];
/*  891 */             g.drawLine(midPoint - tickTop, tickY, midPoint + tickTop, tickY);
/*      */           } 
/*      */         }
/*  894 */         tickIndex++;
/*  895 */         tick = tickIndex / longTick2;
/*      */       } 
/*      */       
/*  898 */       g.setPaintMode();
/*      */     } 
/*  900 */     g.setClip(saveClip);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int reverseAlignment(int alignment) {
/*  905 */     switch (alignment) {
/*      */       
/*      */       case 2:
/*  908 */         return 4;
/*      */       
/*      */       case 4:
/*  911 */         return 2;
/*      */     } 
/*      */     
/*  914 */     return alignment;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static String fullJustify(String line, int maxWidth, FontMetrics fm, int diff) {
/*      */     try {
/*  921 */       HashMap<Integer, Integer> list = new HashMap<>();
/*      */       
/*  923 */       String[] words = line.split(" ");
/*      */       
/*  925 */       if (words == null || words.length == 0)
/*      */       {
/*  927 */         return "";
/*      */       }
/*  929 */       int last = 0;
/*      */       
/*  931 */       StringBuilder sb = new StringBuilder();
/*  932 */       int each = 1;
/*  933 */       String emptyWidth = getStringWithLengthAndFilledWithCharacter(each, ' ');
/*  934 */       int emptySize = fm.stringWidth(emptyWidth);
/*      */       
/*  936 */       boolean runagain = false;
/*  937 */       for (int i = last; i < words.length; i++) {
/*      */         
/*  939 */         if (runagain) {
/*      */           
/*  941 */           runagain = false;
/*  942 */           i = 0;
/*      */         } 
/*  944 */         if (i == words.length - 1) {
/*      */           break;
/*      */         }
/*  947 */         if (list.containsKey(Integer.valueOf(i))) {
/*      */           
/*  949 */           if (diff < 2)
/*      */             break; 
/*  951 */           list.put(Integer.valueOf(i), Integer.valueOf(((Integer)list.get(Integer.valueOf(i))).intValue() + each));
/*  952 */           diff -= emptySize;
/*      */         } else {
/*      */           
/*  955 */           list.put(Integer.valueOf(i), Integer.valueOf(each));
/*      */         } 
/*  957 */         if (i == words.length - 2 && diff > 2) {
/*      */           
/*  959 */           runagain = true;
/*  960 */           i = 0;
/*      */         } 
/*      */       } 
/*      */       
/*  964 */       Set<Map.Entry<Integer, Integer>> set = list.entrySet();
/*  965 */       Iterator<Map.Entry<Integer, Integer>> it = set.iterator();
/*      */       
/*  967 */       while (it.hasNext()) {
/*      */         
/*  969 */         Map.Entry e = it.next();
/*  970 */         Integer key = (Integer)e.getKey();
/*  971 */         Integer val = (Integer)e.getValue();
/*      */         
/*  973 */         sb.append(String.valueOf(words[key.intValue()]) + getStringWithLengthAndFilledWithCharacter(val.intValue(), ' '));
/*      */       } 
/*  975 */       sb.append(words[words.length - 1]);
/*      */       
/*  977 */       return sb.toString();
/*      */     
/*      */     }
/*  980 */     catch (Exception e) {
/*      */       
/*  982 */       e.printStackTrace();
/*  983 */       return line;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getStringWithLengthAndFilledWithCharacter(int length, char charToFill) {
/*  989 */     char[] array = new char[length];
/*  990 */     int pos = 0;
/*  991 */     while (pos < length) {
/*      */       
/*  993 */       array[pos] = charToFill;
/*  994 */       pos++;
/*      */     } 
/*  996 */     return new String(array);
/*      */   }
/*      */   
/*      */   public static class JLbsControlHelperFieldHolder
/*      */   {
/* 1001 */     private HashMap<String, ImageIcon> ms_ImageIcons = new HashMap<>();
/*      */     
/*      */     private ClassLoader ms_GlobalClsLoader;
/*      */     
/*      */     public static JLbsControlHelperFieldHolder getInstance() {
/* 1006 */       return (JLbsControlHelperFieldHolder)LbsClassInstanceProvider.getInstanceByClass(JLbsControlHelperFieldHolder.class);
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsControlHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */