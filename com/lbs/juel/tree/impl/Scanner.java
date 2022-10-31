/*     */ package com.lbs.juel.tree.impl;
/*     */ 
/*     */ import com.lbs.juel.misc.LocalMessages;
/*     */ import java.util.HashMap;
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
/*     */ public class Scanner
/*     */ {
/*     */   public static class ScanException
/*     */     extends Exception
/*     */   {
/*     */     final int position;
/*     */     final String encountered;
/*     */     final String expected;
/*     */     
/*     */     public ScanException(int position, String encountered, String expected) {
/*  41 */       super(LocalMessages.get("error.scan", new Object[] { Integer.valueOf(position), encountered, expected }));
/*  42 */       this.position = position;
/*  43 */       this.encountered = encountered;
/*  44 */       this.expected = expected;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Token
/*     */   {
/*     */     private final Scanner.Symbol symbol;
/*     */     private final String image;
/*     */     private final int length;
/*     */     
/*     */     public Token(Scanner.Symbol symbol, String image) {
/*  56 */       this(symbol, image, image.length());
/*     */     }
/*     */ 
/*     */     
/*     */     public Token(Scanner.Symbol symbol, String image, int length) {
/*  61 */       this.symbol = symbol;
/*  62 */       this.image = image;
/*  63 */       this.length = length;
/*     */     }
/*     */ 
/*     */     
/*     */     public Scanner.Symbol getSymbol() {
/*  68 */       return this.symbol;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getImage() {
/*  73 */       return this.image;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getSize() {
/*  78 */       return this.length;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/*  84 */       return this.symbol.toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ExtensionToken
/*     */     extends Token
/*     */   {
/*     */     public ExtensionToken(String image) {
/*  92 */       super(Scanner.Symbol.EXTENSION, image);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Symbol
/*     */   {
/* 100 */     EOF, PLUS("'+'"), MINUS("'-'"), MUL("'*'"), DIV("'/'|'div'"), MOD("'%'|'mod'"), LPAREN("'('"), RPAREN("')'"), IDENTIFIER, NOT(
/* 101 */       "'!'|'not'"), AND("'&&'|'and'"), OR("'||'|'or'"), EMPTY("'empty'"), INSTANCEOF("'instanceof'"), INTEGER, FLOAT, TRUE(
/* 102 */       "'true'"), FALSE("'false'"), STRING, NULL("'null'"), LE("'<='|'le'"), LT("'<'|'lt'"), GE("'>='|'ge'"), GT(
/* 103 */       "'>'|'gt'"), EQ("'=='|'eq'"), NE("'!='|'ne'"), QUESTION("'?'"), COLON("':'"), TEXT, DOT("'.'"), LBRACK("'['"), RBRACK(
/* 104 */       "']'"), COMMA("','"), START_EVAL_DEFERRED("'#{'"), START_EVAL_DYNAMIC("'${'"), END_EVAL("'}'"), EXTENSION;
/*     */ 
/*     */ 
/*     */     
/*     */     private final String string;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Symbol(String string) {
/* 114 */       this.string = string;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 120 */       return (this.string == null) ? (
/* 121 */         "<" + name() + ">") : 
/* 122 */         this.string;
/*     */     }
/*     */   }
/*     */   
/* 126 */   private static final HashMap<String, Token> KEYMAP = new HashMap<>();
/* 127 */   private static final HashMap<Symbol, Token> FIXMAP = new HashMap<>();
/*     */   private Token token;
/*     */   
/*     */   private static void addFixToken(Token token) {
/* 131 */     FIXMAP.put(token.getSymbol(), token);
/*     */   }
/*     */   private int position; private final String input;
/*     */   
/*     */   private static void addKeyToken(Token token) {
/* 136 */     KEYMAP.put(token.getImage(), token);
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 141 */     addFixToken(new Token(Symbol.PLUS, "+"));
/* 142 */     addFixToken(new Token(Symbol.MINUS, "-"));
/* 143 */     addFixToken(new Token(Symbol.MUL, "*"));
/* 144 */     addFixToken(new Token(Symbol.DIV, "/"));
/* 145 */     addFixToken(new Token(Symbol.MOD, "%"));
/* 146 */     addFixToken(new Token(Symbol.LPAREN, "("));
/* 147 */     addFixToken(new Token(Symbol.RPAREN, ")"));
/* 148 */     addFixToken(new Token(Symbol.NOT, "!"));
/* 149 */     addFixToken(new Token(Symbol.AND, "&&"));
/* 150 */     addFixToken(new Token(Symbol.OR, "||"));
/* 151 */     addFixToken(new Token(Symbol.EQ, "=="));
/* 152 */     addFixToken(new Token(Symbol.NE, "!="));
/* 153 */     addFixToken(new Token(Symbol.LT, "<"));
/* 154 */     addFixToken(new Token(Symbol.LE, "<="));
/* 155 */     addFixToken(new Token(Symbol.GT, ">"));
/* 156 */     addFixToken(new Token(Symbol.GE, ">="));
/* 157 */     addFixToken(new Token(Symbol.QUESTION, "?"));
/* 158 */     addFixToken(new Token(Symbol.COLON, ":"));
/* 159 */     addFixToken(new Token(Symbol.COMMA, ","));
/* 160 */     addFixToken(new Token(Symbol.DOT, "."));
/* 161 */     addFixToken(new Token(Symbol.LBRACK, "["));
/* 162 */     addFixToken(new Token(Symbol.RBRACK, "]"));
/* 163 */     addFixToken(new Token(Symbol.START_EVAL_DEFERRED, "#{"));
/* 164 */     addFixToken(new Token(Symbol.START_EVAL_DYNAMIC, "${"));
/* 165 */     addFixToken(new Token(Symbol.END_EVAL, "}"));
/* 166 */     addFixToken(new Token(Symbol.EOF, null, 0));
/*     */     
/* 168 */     addKeyToken(new Token(Symbol.NULL, "null"));
/* 169 */     addKeyToken(new Token(Symbol.TRUE, "true"));
/* 170 */     addKeyToken(new Token(Symbol.FALSE, "false"));
/* 171 */     addKeyToken(new Token(Symbol.EMPTY, "empty"));
/* 172 */     addKeyToken(new Token(Symbol.DIV, "div"));
/* 173 */     addKeyToken(new Token(Symbol.MOD, "mod"));
/* 174 */     addKeyToken(new Token(Symbol.NOT, "not"));
/* 175 */     addKeyToken(new Token(Symbol.AND, "and"));
/* 176 */     addKeyToken(new Token(Symbol.OR, "or"));
/* 177 */     addKeyToken(new Token(Symbol.LE, "le"));
/* 178 */     addKeyToken(new Token(Symbol.LT, "lt"));
/* 179 */     addKeyToken(new Token(Symbol.EQ, "eq"));
/* 180 */     addKeyToken(new Token(Symbol.NE, "ne"));
/* 181 */     addKeyToken(new Token(Symbol.GE, "ge"));
/* 182 */     addKeyToken(new Token(Symbol.GT, "gt"));
/* 183 */     addKeyToken(new Token(Symbol.INSTANCEOF, "instanceof"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   protected final StringBuilder builder = new StringBuilder();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Scanner(String input) {
/* 198 */     this.input = input;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInput() {
/* 203 */     return this.input;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Token getToken() {
/* 211 */     return this.token;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPosition() {
/* 219 */     return this.position;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isDigit(char c) {
/* 227 */     return (c >= '0' && c <= '9');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Token keyword(String s) {
/* 236 */     return KEYMAP.get(s);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Token fixed(Symbol symbol) {
/* 245 */     return FIXMAP.get(symbol);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Token token(Symbol symbol, String value, int length) {
/* 250 */     return new Token(symbol, value, length);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isEval() {
/* 255 */     return (this.token != null && this.token.getSymbol() != Symbol.TEXT && this.token.getSymbol() != Symbol.END_EVAL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Token nextText() throws ScanException {
/* 263 */     this.builder.setLength(0);
/* 264 */     int i = this.position;
/* 265 */     int l = this.input.length();
/* 266 */     boolean escaped = false;
/* 267 */     while (i < l) {
/*     */       
/* 269 */       char c = this.input.charAt(i);
/* 270 */       switch (c) {
/*     */         
/*     */         case '\\':
/* 273 */           if (escaped) {
/*     */             
/* 275 */             this.builder.append('\\');
/*     */             
/*     */             break;
/*     */           } 
/* 279 */           escaped = true;
/*     */           break;
/*     */         
/*     */         case '#':
/*     */         case '$':
/* 284 */           if (i + 1 < l && this.input.charAt(i + 1) == '{') {
/*     */             
/* 286 */             if (escaped)
/*     */             {
/* 288 */               this.builder.append(c);
/*     */             }
/*     */             else
/*     */             {
/* 292 */               return token(Symbol.TEXT, this.builder.toString(), i - this.position);
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 297 */             if (escaped)
/*     */             {
/* 299 */               this.builder.append('\\');
/*     */             }
/* 301 */             this.builder.append(c);
/*     */           } 
/* 303 */           escaped = false;
/*     */           break;
/*     */         default:
/* 306 */           if (escaped)
/*     */           {
/* 308 */             this.builder.append('\\');
/*     */           }
/* 310 */           this.builder.append(c);
/* 311 */           escaped = false; break;
/*     */       } 
/* 313 */       i++;
/*     */     } 
/* 315 */     if (escaped)
/*     */     {
/* 317 */       this.builder.append('\\');
/*     */     }
/* 319 */     return token(Symbol.TEXT, this.builder.toString(), i - this.position);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Token nextString() throws ScanException {
/* 327 */     this.builder.setLength(0);
/* 328 */     char quote = this.input.charAt(this.position);
/* 329 */     int i = this.position + 1;
/* 330 */     int l = this.input.length();
/* 331 */     while (i < l) {
/*     */       
/* 333 */       char c = this.input.charAt(i++);
/* 334 */       if (c == '\\') {
/*     */         
/* 336 */         if (i == l)
/*     */         {
/* 338 */           throw new ScanException(this.position, "unterminated string", String.valueOf(quote) + " or \\");
/*     */         }
/*     */ 
/*     */         
/* 342 */         c = this.input.charAt(i++);
/* 343 */         if (c == '\\' || c == quote) {
/*     */           
/* 345 */           this.builder.append(c);
/*     */           
/*     */           continue;
/*     */         } 
/* 349 */         throw new ScanException(this.position, "invalid escape sequence \\" + c, "\\" + quote + " or \\\\");
/*     */       } 
/*     */ 
/*     */       
/* 353 */       if (c == quote)
/*     */       {
/* 355 */         return token(Symbol.STRING, this.builder.toString(), i - this.position);
/*     */       }
/*     */ 
/*     */       
/* 359 */       this.builder.append(c);
/*     */     } 
/*     */     
/* 362 */     throw new ScanException(this.position, "unterminated string", String.valueOf(quote));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Token nextNumber() throws ScanException {
/* 370 */     int i = this.position;
/* 371 */     int l = this.input.length();
/* 372 */     while (i < l && isDigit(this.input.charAt(i)))
/*     */     {
/* 374 */       i++;
/*     */     }
/* 376 */     Symbol symbol = Symbol.INTEGER;
/* 377 */     if (i < l && this.input.charAt(i) == '.') {
/*     */       
/* 379 */       i++;
/* 380 */       while (i < l && isDigit(this.input.charAt(i)))
/*     */       {
/* 382 */         i++;
/*     */       }
/* 384 */       symbol = Symbol.FLOAT;
/*     */     } 
/* 386 */     if (i < l && (this.input.charAt(i) == 'e' || this.input.charAt(i) == 'E')) {
/*     */       
/* 388 */       int e = i;
/* 389 */       i++;
/* 390 */       if (i < l && (this.input.charAt(i) == '+' || this.input.charAt(i) == '-'))
/*     */       {
/* 392 */         i++;
/*     */       }
/* 394 */       if (i < l && isDigit(this.input.charAt(i))) {
/*     */         
/* 396 */         i++;
/* 397 */         while (i < l && isDigit(this.input.charAt(i)))
/*     */         {
/* 399 */           i++;
/*     */         }
/* 401 */         symbol = Symbol.FLOAT;
/*     */       }
/*     */       else {
/*     */         
/* 405 */         i = e;
/*     */       } 
/*     */     } 
/* 408 */     return token(symbol, this.input.substring(this.position, i), i - this.position);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Token nextEval() throws ScanException {
/* 416 */     char c1 = this.input.charAt(this.position);
/* 417 */     char c2 = (this.position < this.input.length() - 1) ? 
/* 418 */       this.input.charAt(this.position + 1) : Character
/* 419 */       .MIN_VALUE;
/*     */     
/* 421 */     switch (c1) {
/*     */       
/*     */       case '*':
/* 424 */         return fixed(Symbol.MUL);
/*     */       case '/':
/* 426 */         return fixed(Symbol.DIV);
/*     */       case '%':
/* 428 */         return fixed(Symbol.MOD);
/*     */       case '+':
/* 430 */         return fixed(Symbol.PLUS);
/*     */       case '-':
/* 432 */         return fixed(Symbol.MINUS);
/*     */       case '?':
/* 434 */         return fixed(Symbol.QUESTION);
/*     */       case ':':
/* 436 */         return fixed(Symbol.COLON);
/*     */       case '[':
/* 438 */         return fixed(Symbol.LBRACK);
/*     */       case ']':
/* 440 */         return fixed(Symbol.RBRACK);
/*     */       case '(':
/* 442 */         return fixed(Symbol.LPAREN);
/*     */       case ')':
/* 444 */         return fixed(Symbol.RPAREN);
/*     */       case ',':
/* 446 */         return fixed(Symbol.COMMA);
/*     */       case '.':
/* 448 */         if (!isDigit(c2))
/*     */         {
/* 450 */           return fixed(Symbol.DOT);
/*     */         }
/*     */         break;
/*     */       case '=':
/* 454 */         if (c2 == '=')
/*     */         {
/* 456 */           return fixed(Symbol.EQ);
/*     */         }
/*     */         break;
/*     */       case '&':
/* 460 */         if (c2 == '&')
/*     */         {
/* 462 */           return fixed(Symbol.AND);
/*     */         }
/*     */         break;
/*     */       case '|':
/* 466 */         if (c2 == '|')
/*     */         {
/* 468 */           return fixed(Symbol.OR);
/*     */         }
/*     */         break;
/*     */       case '!':
/* 472 */         if (c2 == '=')
/*     */         {
/* 474 */           return fixed(Symbol.NE);
/*     */         }
/* 476 */         return fixed(Symbol.NOT);
/*     */       case '<':
/* 478 */         if (c2 == '=')
/*     */         {
/* 480 */           return fixed(Symbol.LE);
/*     */         }
/* 482 */         return fixed(Symbol.LT);
/*     */       case '>':
/* 484 */         if (c2 == '=')
/*     */         {
/* 486 */           return fixed(Symbol.GE);
/*     */         }
/* 488 */         return fixed(Symbol.GT);
/*     */       case '"':
/*     */       case '\'':
/* 491 */         return nextString();
/*     */     } 
/*     */     
/* 494 */     if (isDigit(c1) || c1 == '.')
/*     */     {
/* 496 */       return nextNumber();
/*     */     }
/*     */     
/* 499 */     if (Character.isJavaIdentifierStart(c1)) {
/*     */       
/* 501 */       int i = this.position + 1;
/* 502 */       int l = this.input.length();
/* 503 */       while (i < l && Character.isJavaIdentifierPart(this.input.charAt(i)))
/*     */       {
/* 505 */         i++;
/*     */       }
/* 507 */       String name = this.input.substring(this.position, i);
/* 508 */       Token keyword = keyword(name);
/* 509 */       return (keyword == null) ? 
/* 510 */         token(Symbol.IDENTIFIER, name, i - this.position) : 
/* 511 */         keyword;
/*     */     } 
/*     */     
/* 514 */     throw new ScanException(this.position, "invalid character '" + c1 + "'", "expression token");
/*     */   }
/*     */ 
/*     */   
/*     */   protected Token nextToken() throws ScanException {
/* 519 */     if (isEval()) {
/*     */       
/* 521 */       if (this.input.charAt(this.position) == '}')
/*     */       {
/* 523 */         return fixed(Symbol.END_EVAL);
/*     */       }
/* 525 */       return nextEval();
/*     */     } 
/*     */ 
/*     */     
/* 529 */     if (this.position + 1 < this.input.length() && this.input.charAt(this.position + 1) == '{')
/*     */     {
/* 531 */       switch (this.input.charAt(this.position)) {
/*     */         
/*     */         case '#':
/* 534 */           return fixed(Symbol.START_EVAL_DEFERRED);
/*     */         case '$':
/* 536 */           return fixed(Symbol.START_EVAL_DYNAMIC);
/*     */       } 
/*     */     }
/* 539 */     return nextText();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Token next() throws ScanException {
/* 551 */     if (this.token != null)
/*     */     {
/* 553 */       this.position += this.token.getSize();
/*     */     }
/*     */     
/* 556 */     int length = this.input.length();
/*     */     
/* 558 */     if (isEval())
/*     */     {
/* 560 */       while (this.position < length && Character.isWhitespace(this.input.charAt(this.position)))
/*     */       {
/* 562 */         this.position++;
/*     */       }
/*     */     }
/*     */     
/* 566 */     if (this.position == length)
/*     */     {
/* 568 */       return this.token = fixed(Symbol.EOF);
/*     */     }
/*     */     
/* 571 */     return this.token = nextToken();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\Scanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */