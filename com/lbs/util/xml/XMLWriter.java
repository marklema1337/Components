/*     */ package com.lbs.util.xml;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.io.Writer;
/*     */ import java.util.Arrays;
/*     */ import java.util.NoSuchElementException;
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
/*     */ public class XMLWriter
/*     */   implements XMLEventListener
/*     */ {
/*     */   private Writer m_Writer;
/*     */   private XMLEncoder m_Encoder;
/*     */   private int m_State;
/*     */   private String[] m_ElementStack;
/*     */   private int m_ElementStackSize;
/*     */   private char m_QuotationMark;
/*     */   private boolean m_EscapeAmpersands = true;
/*  31 */   private LineBreak m_LineBreak = LineBreak.NONE;
/*  32 */   private char[] m_LineBreakChars = this.m_LineBreak._lineBreakChars;
/*     */   
/*     */   private String m_Indentation;
/*     */   
/*     */   public static final String DEFAULT_INDENTATION = "";
/*     */   
/*     */   public XMLWriter() {
/*  39 */     this.m_State = 0;
/*  40 */     this.m_ElementStack = new String[16];
/*  41 */     this.m_QuotationMark = '"';
/*  42 */     this.m_EscapeAmpersands = true;
/*  43 */     this.m_LineBreak = LineBreak.NONE;
/*  44 */     this.m_LineBreakChars = this.m_LineBreak._lineBreakChars;
/*  45 */     this.m_Indentation = "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLWriter(Writer out, String encoding) throws IllegalStateException, IllegalArgumentException, UnsupportedEncodingException {
/*  52 */     this();
/*  53 */     reset(out, encoding);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLWriter(Writer out, XMLEncoder encoder) throws IllegalStateException, IllegalArgumentException, UnsupportedEncodingException {
/*  60 */     this();
/*  61 */     reset(out, encoder);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final void checkInvariants() throws Error {
/*  67 */     if (this.m_LineBreak == null)
/*     */     {
/*  69 */       throw new Error("_lineBreak == null");
/*     */     }
/*  71 */     if (this.m_LineBreak == LineBreak.NONE && this.m_Indentation.length() > 0)
/*     */     {
/*  73 */       throw new Error("_lineBreak == LineBreak.NONE && _indentation = \"" + this.m_Indentation + "\".");
/*     */     }
/*  75 */     if (this.m_ElementStack == null)
/*     */     {
/*  77 */       throw new Error("_elementStack (" + Arrays.toString(this.m_ElementStack) + " == null");
/*     */     }
/*  79 */     if (this.m_ElementStackSize < 0)
/*     */     {
/*  81 */       throw new Error("_elementStackSize (" + this.m_ElementStackSize + ") < 0");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private final void writeIndentation() throws IOException {
/*  87 */     if (this.m_Indentation.length() > 0) {
/*     */       
/*  89 */       int count = this.m_ElementStackSize - 1;
/*  90 */       for (int i = 0; i < count; i++)
/*     */       {
/*  92 */         this.m_Writer.write(this.m_Indentation);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final Writer getWriter() {
/*  99 */     return this.m_Writer;
/*     */   }
/*     */ 
/*     */   
/*     */   public final String getEncoding() {
/* 104 */     if (this.m_Encoder == null)
/*     */     {
/* 106 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 110 */     return this.m_Encoder.getEncoding();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 116 */     this.m_Writer = null;
/* 117 */     this.m_Encoder = null;
/* 118 */     this.m_ElementStackSize = 0;
/* 119 */     this.m_State = 0;
/* 120 */     this.m_LineBreak = LineBreak.NONE;
/* 121 */     this.m_LineBreakChars = this.m_LineBreak._lineBreakChars;
/* 122 */     this.m_Indentation = "";
/* 123 */     checkInvariants();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final void reset(Writer out) throws IllegalArgumentException {
/* 129 */     if (out == null)
/*     */     {
/* 131 */       throw new IllegalArgumentException("out == null");
/*     */     }
/* 133 */     this.m_Writer = out;
/* 134 */     this.m_State = 1;
/* 135 */     this.m_ElementStackSize = 0;
/* 136 */     this.m_LineBreak = LineBreak.NONE;
/* 137 */     this.m_LineBreakChars = this.m_LineBreak._lineBreakChars;
/* 138 */     this.m_Indentation = "";
/* 139 */     checkInvariants();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void reset(Writer out, String encoding) throws IllegalArgumentException, UnsupportedEncodingException {
/* 144 */     if (encoding == null)
/* 145 */       throw new IllegalArgumentException("encoding == null"); 
/* 146 */     reset(out);
/* 147 */     this.m_Encoder = XMLEncoder.getEncoder(encoding);
/* 148 */     checkInvariants();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void reset(Writer out, XMLEncoder encoder) throws IllegalArgumentException, UnsupportedEncodingException {
/* 153 */     if (encoder == null)
/* 154 */       throw new IllegalArgumentException("encoder == null"); 
/* 155 */     reset(out);
/* 156 */     this.m_Encoder = encoder;
/* 157 */     checkInvariants();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void setState(int newState, String[] newElementStack) throws IllegalArgumentException {
/* 162 */     if (newState < 0)
/* 163 */       throw new IllegalArgumentException("newState < 0"); 
/* 164 */     if (newState == 4 && newElementStack == null)
/* 165 */       throw new IllegalArgumentException("newState == START_TAG_OPEN && newElementStack == null"); 
/* 166 */     if (newState == 5 && newElementStack == null)
/* 167 */       throw new IllegalArgumentException("newState == WITHIN_ELEMENT && newElementStack == null"); 
/* 168 */     if (newState != 4 && newState != 5 && newElementStack != null) {
/* 169 */       throw new IllegalArgumentException("newState != START_TAG_OPEN && newState != WITHIN_ELEMENT && newElementStack != null");
/*     */     }
/*     */     
/* 172 */     if (newElementStack != null) {
/*     */       
/* 174 */       for (int i = 0; i < newElementStack.length; i++) {
/*     */         
/* 176 */         if (newElementStack[i] == null)
/*     */         {
/* 178 */           throw new IllegalArgumentException("newElementStack[" + i + "] == null");
/*     */         }
/*     */       } 
/*     */       
/* 182 */       if (newElementStack.length > this.m_ElementStack.length) {
/*     */         
/*     */         try {
/*     */           
/* 186 */           this.m_ElementStack = new String[newElementStack.length + 16];
/*     */         }
/* 188 */         catch (OutOfMemoryError error) {
/*     */           
/* 190 */           this.m_ElementStack = new String[newElementStack.length];
/*     */         } 
/*     */       }
/* 193 */       System.arraycopy(newElementStack, 0, this.m_ElementStack, 0, newElementStack.length);
/*     */     } 
/*     */     
/* 196 */     if (newState == 0) {
/* 197 */       reset();
/*     */     } else {
/*     */       
/* 200 */       this.m_State = newState;
/* 201 */       this.m_ElementStackSize = (newElementStack == null) ? 0 : newElementStack.length;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 206 */     checkInvariants();
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getState() {
/* 211 */     return this.m_State;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isEscaping() {
/* 216 */     return this.m_EscapeAmpersands;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void setEscaping(boolean escapeAmpersands) {
/* 221 */     this.m_EscapeAmpersands = escapeAmpersands;
/* 222 */     checkInvariants();
/*     */   }
/*     */ 
/*     */   
/*     */   public final String[] getElementStack() {
/* 227 */     if (this.m_ElementStackSize == 0)
/*     */     {
/* 229 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 233 */     String[] newStack = new String[this.m_ElementStackSize];
/* 234 */     System.arraycopy(this.m_ElementStack, 0, newStack, 0, this.m_ElementStackSize);
/* 235 */     return newStack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getElementStackSize() {
/* 241 */     return this.m_ElementStackSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getElementStackCapacity() {
/* 246 */     return this.m_ElementStack.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setElementStackCapacity(int newCapacity) throws IllegalArgumentException, OutOfMemoryError {
/* 252 */     if (newCapacity < this.m_ElementStack.length) {
/* 253 */       throw new IllegalArgumentException("newCapacity < getElementStackSize()");
/*     */     }
/* 255 */     int currentCapacity = this.m_ElementStack.length;
/* 256 */     if (currentCapacity == newCapacity)
/*     */       return; 
/* 258 */     String[] newStack = new String[newCapacity];
/* 259 */     System.arraycopy(this.m_ElementStack, 0, newStack, 0, this.m_ElementStackSize);
/* 260 */     this.m_ElementStack = newStack;
/* 261 */     checkInvariants();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void setQuotationMark(char c) throws IllegalArgumentException {
/* 266 */     if (c == '\'' || c == '"') {
/* 267 */       this.m_QuotationMark = c;
/*     */     } else {
/* 269 */       throw new IllegalArgumentException("c != '\\'' && c != '\"'");
/* 270 */     }  checkInvariants();
/*     */   }
/*     */ 
/*     */   
/*     */   public final char getQuotationMark() {
/* 275 */     return this.m_QuotationMark;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void setLineBreak(LineBreak lineBreak) {
/* 280 */     this.m_LineBreak = (lineBreak != null) ? lineBreak : LineBreak.NONE;
/*     */ 
/*     */ 
/*     */     
/* 284 */     this.m_LineBreakChars = this.m_LineBreak._lineBreakChars;
/*     */     
/* 286 */     if (this.m_LineBreak == LineBreak.NONE)
/*     */     {
/* 288 */       this.m_Indentation = "";
/*     */     }
/* 290 */     checkInvariants();
/*     */   }
/*     */ 
/*     */   
/*     */   public final LineBreak getLineBreak() {
/* 295 */     return this.m_LineBreak;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setIndentation(String indentation) throws IllegalStateException {
/* 301 */     indentation = (indentation != null) ? indentation : "";
/*     */ 
/*     */ 
/*     */     
/* 305 */     if (this.m_LineBreak == LineBreak.NONE)
/* 306 */       throw new IllegalStateException("getLineBreak() == LineBreak.NONE"); 
/* 307 */     int length = indentation.length();
/* 308 */     for (int i = 0; i < length; i++) {
/*     */       
/* 310 */       char ch = indentation.charAt(i);
/* 311 */       if (ch != ' ' && ch != '\t') {
/*     */         
/* 313 */         String message = "indentation.charAt(" + i + ") = " + ch + ", while only space and tab are allowed.";
/* 314 */         throw new IllegalArgumentException(message);
/*     */       } 
/*     */     } 
/* 317 */     this.m_Indentation = indentation;
/* 318 */     checkInvariants();
/*     */   }
/*     */ 
/*     */   
/*     */   public final String getIndentation() {
/* 323 */     return this.m_Indentation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeStartTag() throws IOException {
/* 328 */     this.m_Writer.write(62);
/*     */   }
/*     */ 
/*     */   
/*     */   public final void declaration() throws IllegalStateException, IOException {
/* 333 */     if (this.m_State != 1)
/* 334 */       throw new IllegalStateException("getState() == " + this.m_State); 
/* 335 */     this.m_State = 8;
/* 336 */     this.m_Encoder.declaration(this.m_Writer);
/* 337 */     this.m_State = 2;
/* 338 */     checkInvariants();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void dtd(String name, String publicID, String systemID) throws IllegalStateException, IllegalArgumentException, InvalidXMLException, IOException {
/* 344 */     if (this.m_State != 1 && this.m_State != 2)
/* 345 */       throw new IllegalStateException("getState() == " + this.m_State); 
/* 346 */     if (name == null)
/* 347 */       throw new IllegalArgumentException("name == null"); 
/* 348 */     if (publicID != null && systemID == null)
/* 349 */       throw new IllegalArgumentException("Found public identifier, but no system identifier."); 
/* 350 */     XMLChecker.checkName(name);
/* 351 */     if (publicID != null)
/* 352 */       XMLChecker.checkPubidLiteral("\"" + publicID + "\""); 
/* 353 */     if (systemID != null)
/* 354 */       XMLChecker.checkSystemLiteral("\"" + systemID + "\""); 
/* 355 */     int oldState = this.m_State;
/* 356 */     this.m_State = 8;
/* 357 */     if (oldState == 2)
/* 358 */       this.m_Writer.write(this.m_LineBreakChars); 
/* 359 */     this.m_Writer.write("<!DOCTYPE ");
/* 360 */     this.m_Writer.write(name);
/* 361 */     if (publicID != null) {
/*     */       
/* 363 */       this.m_Writer.write(" PUBLIC \"");
/* 364 */       this.m_Writer.write(publicID);
/* 365 */       this.m_Writer.write(34);
/* 366 */       this.m_Writer.write(32);
/* 367 */       this.m_Writer.write(34);
/* 368 */       this.m_Writer.write(systemID);
/* 369 */       this.m_Writer.write(34);
/*     */     }
/* 371 */     else if (systemID != null) {
/*     */       
/* 373 */       this.m_Writer.write(" SYSTEM \"");
/* 374 */       this.m_Writer.write(systemID);
/* 375 */       this.m_Writer.write(34);
/*     */     } 
/* 377 */     closeStartTag();
/* 378 */     this.m_State = 3;
/* 379 */     checkInvariants();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void startTag(String type) throws IllegalStateException, IllegalArgumentException, IOException {
/* 384 */     if (this.m_State != 1 && this.m_State != 2 && this.m_State != 3 && this.m_State != 4 && this.m_State != 5)
/*     */     {
/* 386 */       throw new IllegalStateException("getState() == " + this.m_State); } 
/* 387 */     if (type == null)
/* 388 */       throw new IllegalArgumentException("type == null"); 
/* 389 */     int oldState = this.m_State;
/* 390 */     this.m_State = 8;
/* 391 */     if (this.m_ElementStackSize == this.m_ElementStack.length) {
/*     */       String[] newStack;
/*     */ 
/*     */       
/*     */       try {
/* 396 */         newStack = new String[(this.m_ElementStackSize + 1) * 2];
/*     */       }
/* 398 */       catch (OutOfMemoryError error) {
/*     */         
/* 400 */         newStack = new String[this.m_ElementStackSize + 1];
/*     */       } 
/* 402 */       System.arraycopy(this.m_ElementStack, 0, newStack, 0, this.m_ElementStackSize);
/* 403 */       this.m_ElementStack = newStack;
/*     */     } 
/* 405 */     this.m_ElementStack[this.m_ElementStackSize] = type;
/* 406 */     this.m_ElementStackSize++;
/* 407 */     if (oldState == 4)
/* 408 */       this.m_Writer.write(62); 
/* 409 */     if (oldState != 1) {
/*     */       
/* 411 */       this.m_Writer.write(this.m_LineBreakChars);
/* 412 */       writeIndentation();
/*     */     } 
/*     */     
/* 415 */     this.m_Writer.write(60);
/* 416 */     this.m_Writer.write(type);
/* 417 */     this.m_State = 4;
/* 418 */     checkInvariants();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void attribute(String name, String value) throws IllegalStateException, IllegalArgumentException, IOException {
/* 423 */     if (this.m_State != 4)
/* 424 */       throw new IllegalStateException("getState() == " + this.m_State); 
/* 425 */     if (name == null || value == null) {
/*     */       
/* 427 */       if (name == null && value == null)
/* 428 */         throw new IllegalArgumentException("name == null && value == null"); 
/* 429 */       if (name == null) {
/* 430 */         throw new IllegalArgumentException("name == null");
/*     */       }
/* 432 */       throw new IllegalArgumentException("value == null");
/*     */     } 
/* 434 */     this.m_State = 8;
/* 435 */     this.m_Encoder.attribute(this.m_Writer, name, value, this.m_QuotationMark, this.m_EscapeAmpersands);
/* 436 */     this.m_State = 4;
/* 437 */     checkInvariants();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void attribute(String name, boolean value) throws IllegalStateException, IllegalArgumentException, IOException {
/* 442 */     attribute(name, value ? "true" : "false");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void attribute(String name, byte value) throws IllegalStateException, IllegalArgumentException, IOException {
/* 449 */     attribute(name, Byte.toString(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public final void attribute(String name, short value) throws IllegalStateException, IllegalArgumentException, IOException {
/* 454 */     attribute(name, Short.toString(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public final void attribute(String name, int value) throws IllegalStateException, IllegalArgumentException, IOException {
/* 459 */     attribute(name, Integer.toString(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public final void attribute(String name, long value) throws IllegalStateException, IllegalArgumentException, IOException {
/* 464 */     attribute(name, Long.toString(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public final void attribute(String name, float value) throws IllegalStateException, IllegalArgumentException, IOException {
/* 469 */     attribute(name, Float.toString(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public final void attribute(String name, double value) throws IllegalStateException, IllegalArgumentException, IOException {
/* 474 */     attribute(name, Double.toString(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public final void attribute(String name, char c) throws IllegalStateException, IllegalArgumentException, IOException {
/* 479 */     attribute(name, Character.toString(c));
/*     */   }
/*     */ 
/*     */   
/*     */   public final void endTag() throws IllegalStateException, IOException {
/* 484 */     if (this.m_State != 5 && this.m_State != 4)
/*     */     {
/* 486 */       throw new IllegalStateException("getState() == " + this.m_State);
/*     */     }
/* 488 */     int oldState = this.m_State;
/* 489 */     this.m_State = 8;
/* 490 */     String type = this.m_ElementStack[this.m_ElementStackSize - 1];
/* 491 */     if (oldState == 4) {
/*     */       
/* 493 */       this.m_Writer.write(47);
/* 494 */       this.m_Writer.write(62);
/*     */     }
/*     */     else {
/*     */       
/* 498 */       this.m_Writer.write(this.m_LineBreakChars);
/* 499 */       writeIndentation();
/*     */       
/* 501 */       this.m_Writer.write(60);
/* 502 */       this.m_Writer.write(47);
/* 503 */       this.m_Writer.write(type);
/* 504 */       closeStartTag();
/*     */     } 
/* 506 */     this.m_ElementStackSize--;
/* 507 */     internalSetState();
/* 508 */     checkInvariants();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void internalSetState() {
/* 516 */     this.m_State = (this.m_ElementStackSize == 0) ? 6 : 5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void endTag(String type) throws IllegalStateException, NoSuchElementException, IOException {
/* 523 */     if (this.m_State != 5 && this.m_State != 4)
/* 524 */       throw new IllegalStateException("getState() == " + this.m_State); 
/* 525 */     int oldState = this.m_State;
/* 526 */     this.m_State = 8;
/* 527 */     String typeFound = "";
/*     */     
/*     */     do {
/* 530 */       typeFound = this.m_ElementStack[this.m_ElementStackSize - 1];
/* 531 */       if (oldState == 4) {
/*     */         
/* 533 */         this.m_Writer.write(47);
/* 534 */         closeStartTag();
/*     */       }
/*     */       else {
/*     */         
/* 538 */         this.m_Writer.write(this.m_LineBreakChars);
/* 539 */         writeIndentation();
/*     */         
/* 541 */         this.m_Writer.write(60);
/* 542 */         this.m_Writer.write(47);
/* 543 */         this.m_Writer.write(typeFound);
/* 544 */         closeStartTag();
/*     */       } 
/*     */       
/* 547 */       this.m_ElementStackSize--;
/*     */     }
/* 549 */     while (!type.equals(typeFound) && this.m_ElementStackSize > 0);
/* 550 */     if (!type.equals(typeFound))
/* 551 */       throw new NoSuchElementException("No element of type \"" + type + "\" was found on the stack of open elements."); 
/* 552 */     internalSetState();
/* 553 */     checkInvariants();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void emptyTag(String type) throws IllegalStateException, IllegalArgumentException, IOException {
/* 558 */     startTag(type);
/* 559 */     endTag();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void pcdata(String text) throws IllegalStateException, IllegalArgumentException, InvalidXMLException, IOException {
/* 564 */     if (this.m_State != 4 && this.m_State != 5)
/* 565 */       throw new IllegalStateException("getState() == " + this.m_State); 
/* 566 */     if (text == null)
/* 567 */       throw new IllegalArgumentException("text == null"); 
/* 568 */     int oldState = this.m_State;
/* 569 */     this.m_State = 8;
/* 570 */     if (oldState == 4) {
/*     */       
/* 572 */       closeStartTag();
/* 573 */       this.m_Writer.write(this.m_LineBreakChars);
/*     */     } 
/* 575 */     this.m_Encoder.text(this.m_Writer, text, this.m_EscapeAmpersands);
/* 576 */     this.m_State = 5;
/* 577 */     checkInvariants();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void pcdata(char[] ch, int start, int length) throws IllegalStateException, IllegalArgumentException, IndexOutOfBoundsException, InvalidXMLException, IOException {
/* 583 */     if (this.m_State != 4 && this.m_State != 5)
/* 584 */       throw new IllegalStateException("getState() == " + this.m_State); 
/* 585 */     if (ch == null)
/* 586 */       throw new IllegalArgumentException("ch == null"); 
/* 587 */     if (start < 0)
/* 588 */       throw new IllegalArgumentException("start (" + start + ") < 0"); 
/* 589 */     if (start >= ch.length)
/* 590 */       throw new IllegalArgumentException("start (" + start + ") >= ch.length (" + ch.length + ')'); 
/* 591 */     if (length < 0)
/* 592 */       throw new IllegalArgumentException("length < 0"); 
/* 593 */     int oldState = this.m_State;
/* 594 */     this.m_State = 8;
/* 595 */     if (oldState == 4)
/* 596 */       closeStartTag(); 
/* 597 */     this.m_Encoder.text(this.m_Writer, ch, start, length, this.m_EscapeAmpersands);
/* 598 */     this.m_State = 5;
/* 599 */     checkInvariants();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void whitespace(String whitespace) throws IllegalStateException, IllegalArgumentException, InvalidXMLException, IOException {
/* 606 */     if (this.m_State != 1 && this.m_State != 2 && this.m_State != 3 && this.m_State != 4 && this.m_State != 5 && this.m_State != 6)
/*     */     {
/* 608 */       throw new IllegalStateException("getState() == " + this.m_State);
/*     */     }
/* 610 */     if (whitespace == null)
/* 611 */       throw new IllegalArgumentException("whitespace == null"); 
/* 612 */     int oldState = this.m_State;
/* 613 */     this.m_State = 8;
/* 614 */     if (oldState == 4)
/*     */     {
/* 616 */       closeStartTag();
/*     */     }
/* 618 */     this.m_Encoder.whitespace(this.m_Writer, whitespace);
/*     */     
/* 620 */     if (oldState == 1) {
/*     */       
/* 622 */       this.m_State = 2;
/*     */     }
/* 624 */     else if (oldState == 4) {
/*     */       
/* 626 */       this.m_State = 5;
/*     */     }
/*     */     else {
/*     */       
/* 630 */       this.m_State = oldState;
/*     */     } 
/* 632 */     checkInvariants();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void whitespace(char[] ch, int start, int length) throws IllegalStateException, IllegalArgumentException, IndexOutOfBoundsException, InvalidXMLException, IOException {
/* 639 */     if (this.m_State != 1 && this.m_State != 2 && this.m_State != 3 && this.m_State != 4 && this.m_State != 5 && this.m_State != 6)
/*     */     {
/* 641 */       throw new IllegalStateException("getState() == " + this.m_State); } 
/* 642 */     if (ch == null)
/* 643 */       throw new IllegalArgumentException("ch == null"); 
/* 644 */     if (start < 0)
/* 645 */       throw new IllegalArgumentException("start (" + start + ") < 0"); 
/* 646 */     if (start >= ch.length)
/* 647 */       throw new IllegalArgumentException("start (" + start + ") >= ch.length (" + ch.length + ')'); 
/* 648 */     if (length < 0) {
/* 649 */       throw new IllegalArgumentException("length < 0");
/*     */     }
/* 651 */     int oldState = this.m_State;
/* 652 */     this.m_State = 8;
/* 653 */     if (oldState == 4)
/* 654 */       closeStartTag(); 
/* 655 */     this.m_Encoder.whitespace(this.m_Writer, ch, start, length);
/* 656 */     if (oldState == 1) {
/*     */       
/* 658 */       this.m_State = 2;
/*     */     }
/* 660 */     else if (oldState == 4) {
/*     */       
/* 662 */       this.m_State = 5;
/*     */     }
/*     */     else {
/*     */       
/* 666 */       this.m_State = oldState;
/*     */     } 
/* 668 */     checkInvariants();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void comment(String text) throws IllegalStateException, IllegalArgumentException, InvalidXMLException, IOException {
/* 675 */     if (this.m_State != 1 && this.m_State != 2 && this.m_State != 3 && this.m_State != 4 && this.m_State != 5 && this.m_State != 6)
/*     */     {
/*     */       
/* 678 */       throw new IllegalStateException("getState() == " + this.m_State);
/*     */     }
/*     */ 
/*     */     
/* 682 */     if (text == null)
/*     */     {
/* 684 */       throw new IllegalArgumentException("text == null");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 689 */     int oldState = this.m_State;
/* 690 */     this.m_State = 8;
/*     */     
/* 692 */     this.m_ElementStackSize++;
/*     */ 
/*     */     
/* 695 */     if (oldState == 4) {
/*     */       
/* 697 */       this.m_Writer.write(62);
/* 698 */       this.m_Writer.write(this.m_LineBreakChars);
/* 699 */       writeIndentation();
/* 700 */       this.m_Writer.write(60);
/* 701 */       this.m_Writer.write(33);
/* 702 */       this.m_Writer.write(45);
/* 703 */       this.m_Writer.write(45);
/*     */     }
/*     */     else {
/*     */       
/* 707 */       if (oldState != 1) {
/*     */         
/* 709 */         this.m_Writer.write(this.m_LineBreakChars);
/* 710 */         writeIndentation();
/*     */       } 
/* 712 */       this.m_Writer.write(60);
/* 713 */       this.m_Writer.write(33);
/* 714 */       this.m_Writer.write(45);
/* 715 */       this.m_Writer.write(45);
/*     */     } 
/* 717 */     this.m_Encoder.text(this.m_Writer, text, this.m_EscapeAmpersands);
/* 718 */     this.m_Writer.write(45);
/* 719 */     this.m_Writer.write(45);
/* 720 */     this.m_Writer.write(62);
/*     */     
/* 722 */     this.m_ElementStackSize--;
/*     */ 
/*     */     
/* 725 */     if (oldState == 1) {
/*     */       
/* 727 */       this.m_State = 2;
/*     */     }
/* 729 */     else if (oldState == 4) {
/*     */       
/* 731 */       this.m_State = 5;
/*     */     }
/*     */     else {
/*     */       
/* 735 */       this.m_State = oldState;
/*     */     } 
/*     */ 
/*     */     
/* 739 */     checkInvariants();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void pi(String target, String instruction) throws IllegalStateException, IllegalArgumentException, IOException {
/* 744 */     if (this.m_State != 1 && this.m_State != 2 && this.m_State != 3 && this.m_State != 4 && this.m_State != 5 && this.m_State != 6)
/*     */     {
/* 746 */       throw new IllegalStateException("getState() == " + this.m_State); } 
/* 747 */     if (target == null)
/* 748 */       throw new IllegalArgumentException("target == null"); 
/* 749 */     int oldState = this.m_State;
/* 750 */     this.m_State = 8;
/* 751 */     if (oldState == 4)
/* 752 */       closeStartTag(); 
/* 753 */     this.m_Writer.write(60);
/* 754 */     this.m_Writer.write(63);
/* 755 */     this.m_Writer.write(target);
/* 756 */     if (instruction != null) {
/*     */       
/* 758 */       this.m_Writer.write(32);
/* 759 */       this.m_Writer.write(instruction);
/*     */     } 
/* 761 */     this.m_Writer.write(63);
/* 762 */     this.m_Writer.write(62);
/* 763 */     if (oldState == 1) {
/*     */       
/* 765 */       this.m_State = 2;
/*     */     }
/* 767 */     else if (oldState == 4) {
/*     */       
/* 769 */       this.m_State = 5;
/*     */     }
/*     */     else {
/*     */       
/* 773 */       this.m_State = oldState;
/*     */     } 
/* 775 */     checkInvariants();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void cdata(String text) throws IllegalStateException, IllegalArgumentException, IOException {
/* 780 */     if (this.m_State != 4 && this.m_State != 5)
/*     */     {
/* 782 */       throw new IllegalStateException("getState() == " + this.m_State);
/*     */     }
/* 784 */     if (text == null)
/*     */     {
/* 786 */       throw new IllegalArgumentException("text == null");
/*     */     }
/* 788 */     int oldState = this.m_State;
/* 789 */     this.m_State = 8;
/* 790 */     if (oldState == 4)
/* 791 */       closeStartTag(); 
/* 792 */     this.m_Writer.write("<![CDATA[");
/* 793 */     this.m_Writer.write(text);
/* 794 */     this.m_Writer.write(93);
/* 795 */     this.m_Writer.write(93);
/* 796 */     this.m_Writer.write(62);
/*     */     
/* 798 */     this.m_State = 5;
/* 799 */     checkInvariants();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void close() throws IllegalStateException, IOException {
/* 804 */     if (this.m_State != 4 && this.m_State != 5 && this.m_State != 6)
/* 805 */       throw new IllegalStateException("getState() == " + this.m_State); 
/* 806 */     while (this.m_ElementStackSize > 0)
/*     */     {
/* 808 */       endTag();
/*     */     }
/* 810 */     checkInvariants();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void endDocument() throws IllegalStateException, IOException {
/* 815 */     if (this.m_State != 4 && this.m_State != 5 && this.m_State != 6)
/* 816 */       throw new IllegalStateException("getState() == " + this.m_State); 
/* 817 */     close();
/* 818 */     this.m_Writer.flush();
/* 819 */     this.m_Writer.close();
/* 820 */     this.m_State = 7;
/* 821 */     checkInvariants();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\xml\XMLWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */