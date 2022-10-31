/*     */ package com.lbs.juel.tree.impl;
/*     */ 
/*     */ import com.lbs.juel.misc.LocalMessages;
/*     */ import com.lbs.juel.tree.ExpressionNode;
/*     */ import com.lbs.juel.tree.FunctionNode;
/*     */ import com.lbs.juel.tree.IdentifierNode;
/*     */ import com.lbs.juel.tree.Tree;
/*     */ import com.lbs.juel.tree.impl.ast.AstBinary;
/*     */ import com.lbs.juel.tree.impl.ast.AstBoolean;
/*     */ import com.lbs.juel.tree.impl.ast.AstBracket;
/*     */ import com.lbs.juel.tree.impl.ast.AstChoice;
/*     */ import com.lbs.juel.tree.impl.ast.AstComposite;
/*     */ import com.lbs.juel.tree.impl.ast.AstDot;
/*     */ import com.lbs.juel.tree.impl.ast.AstEval;
/*     */ import com.lbs.juel.tree.impl.ast.AstFunction;
/*     */ import com.lbs.juel.tree.impl.ast.AstIdentifier;
/*     */ import com.lbs.juel.tree.impl.ast.AstMethod;
/*     */ import com.lbs.juel.tree.impl.ast.AstNested;
/*     */ import com.lbs.juel.tree.impl.ast.AstNode;
/*     */ import com.lbs.juel.tree.impl.ast.AstNull;
/*     */ import com.lbs.juel.tree.impl.ast.AstNumber;
/*     */ import com.lbs.juel.tree.impl.ast.AstParameters;
/*     */ import com.lbs.juel.tree.impl.ast.AstProperty;
/*     */ import com.lbs.juel.tree.impl.ast.AstString;
/*     */ import com.lbs.juel.tree.impl.ast.AstText;
/*     */ import com.lbs.juel.tree.impl.ast.AstUnary;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ public class Parser
/*     */ {
/*     */   public static class ParseException
/*     */     extends Exception
/*     */   {
/*     */     final int position;
/*     */     final String encountered;
/*     */     final String expected;
/*     */     
/*     */     public ParseException(int position, String encountered, String expected) {
/*  96 */       super(LocalMessages.get("error.parse", new Object[] { Integer.valueOf(position), encountered, expected }));
/*  97 */       this.position = position;
/*  98 */       this.encountered = encountered;
/*  99 */       this.expected = expected;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class LookaheadToken
/*     */   {
/*     */     final Scanner.Token token;
/*     */     
/*     */     final int position;
/*     */ 
/*     */     
/*     */     LookaheadToken(Scanner.Token token, int position) {
/* 113 */       this.token = token;
/* 114 */       this.position = position;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum ExtensionPoint {
/* 119 */     OR, AND, EQ, CMP, ADD, MUL, UNARY, LITERAL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static abstract class ExtensionHandler
/*     */   {
/*     */     private final Parser.ExtensionPoint point;
/*     */ 
/*     */ 
/*     */     
/*     */     public ExtensionHandler(Parser.ExtensionPoint point) {
/* 131 */       this.point = point;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Parser.ExtensionPoint getExtensionPoint() {
/* 139 */       return this.point;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public abstract AstNode createAstNode(AstNode... param1VarArgs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 151 */   private static final String EXPR_FIRST = Scanner.Symbol.IDENTIFIER + "|" + Scanner.Symbol.STRING + "|" + Scanner.Symbol.FLOAT + "|" + Scanner.Symbol.INTEGER + "|" + Scanner.Symbol.TRUE + "|" + Scanner.Symbol.FALSE + 
/* 152 */     "|" + Scanner.Symbol.NULL + "|" + Scanner.Symbol.MINUS + "|" + Scanner.Symbol.NOT + "|" + Scanner.Symbol.EMPTY + "|" + Scanner.Symbol.LPAREN;
/*     */   
/*     */   protected final Builder context;
/*     */   
/*     */   protected final Scanner scanner;
/* 157 */   private List<IdentifierNode> identifiers = Collections.emptyList();
/* 158 */   private List<FunctionNode> functions = Collections.emptyList();
/* 159 */   private List<LookaheadToken> lookahead = Collections.emptyList();
/*     */   
/*     */   private Scanner.Token token;
/*     */   
/*     */   private int position;
/* 164 */   protected Map<Scanner.ExtensionToken, ExtensionHandler> extensions = Collections.emptyMap();
/*     */ 
/*     */   
/*     */   public Parser(Builder context, String input) {
/* 168 */     this.context = context;
/* 169 */     this.scanner = createScanner(input);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Scanner createScanner(String expression) {
/* 174 */     return new Scanner(expression);
/*     */   }
/*     */ 
/*     */   
/*     */   public void putExtensionHandler(Scanner.ExtensionToken token, ExtensionHandler extension) {
/* 179 */     if (this.extensions.isEmpty())
/*     */     {
/* 181 */       this.extensions = new HashMap<>(16);
/*     */     }
/* 183 */     this.extensions.put(token, extension);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ExtensionHandler getExtensionHandler(Scanner.Token token) {
/* 188 */     return this.extensions.get(token);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Number parseInteger(String string) throws ParseException {
/*     */     try {
/* 200 */       return Long.valueOf(string);
/*     */     }
/* 202 */     catch (NumberFormatException e) {
/*     */       
/* 204 */       fail(Scanner.Symbol.INTEGER);
/* 205 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Number parseFloat(String string) throws ParseException {
/*     */     try {
/* 218 */       return Double.valueOf(string);
/*     */     }
/* 220 */     catch (NumberFormatException e) {
/*     */       
/* 222 */       fail(Scanner.Symbol.FLOAT);
/* 223 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected AstBinary createAstBinary(AstNode left, AstNode right, AstBinary.Operator operator) {
/* 229 */     return new AstBinary(left, right, operator);
/*     */   }
/*     */ 
/*     */   
/*     */   protected AstBracket createAstBracket(AstNode base, AstNode property, boolean lvalue, boolean strict) {
/* 234 */     return new AstBracket(base, property, lvalue, strict, this.context.isEnabled(Builder.Feature.IGNORE_RETURN_TYPE));
/*     */   }
/*     */ 
/*     */   
/*     */   protected AstChoice createAstChoice(AstNode question, AstNode yes, AstNode no) {
/* 239 */     return new AstChoice(question, yes, no);
/*     */   }
/*     */ 
/*     */   
/*     */   protected AstComposite createAstComposite(List<AstNode> nodes) {
/* 244 */     return new AstComposite(nodes);
/*     */   }
/*     */ 
/*     */   
/*     */   protected AstDot createAstDot(AstNode base, String property, boolean lvalue) {
/* 249 */     return new AstDot(base, property, lvalue, this.context.isEnabled(Builder.Feature.IGNORE_RETURN_TYPE));
/*     */   }
/*     */ 
/*     */   
/*     */   protected AstFunction createAstFunction(String name, int index, AstParameters params) {
/* 254 */     return new AstFunction(name, index, params, this.context.isEnabled(Builder.Feature.VARARGS));
/*     */   }
/*     */ 
/*     */   
/*     */   protected AstIdentifier createAstIdentifier(String name, int index) {
/* 259 */     return new AstIdentifier(name, index, this.context.isEnabled(Builder.Feature.IGNORE_RETURN_TYPE));
/*     */   }
/*     */ 
/*     */   
/*     */   protected AstMethod createAstMethod(AstProperty property, AstParameters params) {
/* 264 */     return new AstMethod(property, params);
/*     */   }
/*     */ 
/*     */   
/*     */   protected AstUnary createAstUnary(AstNode child, AstUnary.Operator operator) {
/* 269 */     return new AstUnary(child, operator);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final List<FunctionNode> getFunctions() {
/* 274 */     return this.functions;
/*     */   }
/*     */ 
/*     */   
/*     */   protected final List<IdentifierNode> getIdentifiers() {
/* 279 */     return this.identifiers;
/*     */   }
/*     */ 
/*     */   
/*     */   protected final Scanner.Token getToken() {
/* 284 */     return this.token;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fail(String expected) throws ParseException {
/* 292 */     throw new ParseException(this.position, "'" + this.token.getImage() + "'", expected);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fail(Scanner.Symbol expected) throws ParseException {
/* 300 */     fail(expected.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final Scanner.Token lookahead(int index) throws Scanner.ScanException, ParseException {
/* 308 */     if (this.lookahead.isEmpty())
/*     */     {
/* 310 */       this.lookahead = new LinkedList<>();
/*     */     }
/* 312 */     while (index >= this.lookahead.size())
/*     */     {
/* 314 */       this.lookahead.add(new LookaheadToken(this.scanner.next(), this.scanner.getPosition()));
/*     */     }
/* 316 */     return ((LookaheadToken)this.lookahead.get(index)).token;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final Scanner.Token consumeToken() throws Scanner.ScanException, ParseException {
/* 325 */     Scanner.Token result = this.token;
/* 326 */     if (this.lookahead.isEmpty()) {
/*     */       
/* 328 */       this.token = this.scanner.next();
/* 329 */       this.position = this.scanner.getPosition();
/*     */     }
/*     */     else {
/*     */       
/* 333 */       LookaheadToken next = this.lookahead.remove(0);
/* 334 */       this.token = next.token;
/* 335 */       this.position = next.position;
/*     */     } 
/* 337 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final Scanner.Token consumeToken(Scanner.Symbol expected) throws Scanner.ScanException, ParseException {
/* 346 */     if (this.token.getSymbol() != expected)
/*     */     {
/* 348 */       fail(expected);
/*     */     }
/* 350 */     return consumeToken();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tree tree() throws Scanner.ScanException, ParseException {
/*     */     AstText astText;
/* 358 */     consumeToken();
/* 359 */     AstNode t = text();
/* 360 */     if (this.token.getSymbol() == Scanner.Symbol.EOF) {
/*     */       
/* 362 */       if (t == null)
/*     */       {
/* 364 */         astText = new AstText("");
/*     */       }
/* 366 */       return new Tree((ExpressionNode)astText, this.functions, this.identifiers, false);
/*     */     } 
/* 368 */     AstEval e = eval();
/* 369 */     if (this.token.getSymbol() == Scanner.Symbol.EOF && astText == null)
/*     */     {
/* 371 */       return new Tree((ExpressionNode)e, this.functions, this.identifiers, e.isDeferred());
/*     */     }
/* 373 */     ArrayList<AstNode> list = new ArrayList<>();
/* 374 */     if (astText != null)
/*     */     {
/* 376 */       list.add(astText);
/*     */     }
/* 378 */     list.add(e);
/* 379 */     AstNode astNode1 = text();
/* 380 */     if (astNode1 != null)
/*     */     {
/* 382 */       list.add(astNode1);
/*     */     }
/* 384 */     while (this.token.getSymbol() != Scanner.Symbol.EOF) {
/*     */       
/* 386 */       if (e.isDeferred()) {
/*     */         
/* 388 */         list.add(eval(true, true));
/*     */       }
/*     */       else {
/*     */         
/* 392 */         list.add(eval(true, false));
/*     */       } 
/* 394 */       astNode1 = text();
/* 395 */       if (astNode1 != null)
/*     */       {
/* 397 */         list.add(astNode1);
/*     */       }
/*     */     } 
/* 400 */     return new Tree((ExpressionNode)createAstComposite(list), this.functions, this.identifiers, e.isDeferred());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AstNode text() throws Scanner.ScanException, ParseException {
/*     */     AstText astText;
/* 408 */     AstNode v = null;
/* 409 */     if (this.token.getSymbol() == Scanner.Symbol.TEXT) {
/*     */       
/* 411 */       astText = new AstText(this.token.getImage());
/* 412 */       consumeToken();
/*     */     } 
/* 414 */     return (AstNode)astText;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AstEval eval() throws Scanner.ScanException, ParseException {
/* 422 */     AstEval e = eval(false, false);
/* 423 */     if (e == null) {
/*     */       
/* 425 */       e = eval(false, true);
/* 426 */       if (e == null)
/*     */       {
/* 428 */         fail(Scanner.Symbol.START_EVAL_DEFERRED + "|" + Scanner.Symbol.START_EVAL_DYNAMIC);
/*     */       }
/*     */     } 
/* 431 */     return e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AstEval eval(boolean required, boolean deferred) throws Scanner.ScanException, ParseException {
/* 440 */     AstEval v = null;
/* 441 */     Scanner.Symbol start_eval = deferred ? 
/* 442 */       Scanner.Symbol.START_EVAL_DEFERRED : 
/* 443 */       Scanner.Symbol.START_EVAL_DYNAMIC;
/* 444 */     if (this.token.getSymbol() == start_eval) {
/*     */       
/* 446 */       consumeToken();
/* 447 */       v = new AstEval(expr(true), deferred);
/* 448 */       consumeToken(Scanner.Symbol.END_EVAL);
/*     */     }
/* 450 */     else if (required) {
/*     */       
/* 452 */       fail(start_eval);
/*     */     } 
/* 454 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AstNode expr(boolean required) throws Scanner.ScanException, ParseException {
/*     */     AstChoice astChoice;
/* 462 */     AstNode v = or(required);
/* 463 */     if (v == null)
/*     */     {
/* 465 */       return null;
/*     */     }
/* 467 */     if (this.token.getSymbol() == Scanner.Symbol.QUESTION) {
/*     */       
/* 469 */       consumeToken();
/* 470 */       AstNode a = expr(true);
/* 471 */       consumeToken(Scanner.Symbol.COLON);
/* 472 */       AstNode b = expr(true);
/* 473 */       astChoice = createAstChoice(v, a, b);
/*     */     } 
/* 475 */     return (AstNode)astChoice;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AstNode or(boolean required) throws Scanner.ScanException, ParseException {
/* 483 */     AstNode astNode = and(required);
/* 484 */     if (astNode == null)
/*     */     {
/* 486 */       return null;
/*     */     }
/*     */     while (true) {
/*     */       AstBinary astBinary;
/* 490 */       switch (this.token.getSymbol()) {
/*     */         
/*     */         case OR:
/* 493 */           consumeToken();
/* 494 */           astBinary = createAstBinary(astNode, and(true), AstBinary.OR);
/*     */           continue;
/*     */         case EXTENSION:
/* 497 */           if (getExtensionHandler(this.token).getExtensionPoint() == ExtensionPoint.OR) {
/*     */             
/* 499 */             astNode = getExtensionHandler(consumeToken()).createAstNode(new AstNode[] { (AstNode)astBinary, and(true) }); continue;
/*     */           }  break;
/*     */       }  break;
/*     */     } 
/* 503 */     return astNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AstNode and(boolean required) throws Scanner.ScanException, ParseException {
/* 513 */     AstNode astNode = eq(required);
/* 514 */     if (astNode == null)
/*     */     {
/* 516 */       return null;
/*     */     }
/*     */     while (true) {
/*     */       AstBinary astBinary;
/* 520 */       switch (this.token.getSymbol()) {
/*     */         
/*     */         case null:
/* 523 */           consumeToken();
/* 524 */           astBinary = createAstBinary(astNode, eq(true), AstBinary.AND);
/*     */           continue;
/*     */         case EXTENSION:
/* 527 */           if (getExtensionHandler(this.token).getExtensionPoint() == ExtensionPoint.AND) {
/*     */             
/* 529 */             astNode = getExtensionHandler(consumeToken()).createAstNode(new AstNode[] { (AstNode)astBinary, eq(true) }); continue;
/*     */           }  break;
/*     */       }  break;
/*     */     } 
/* 533 */     return astNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AstNode eq(boolean required) throws Scanner.ScanException, ParseException {
/* 543 */     AstNode astNode = cmp(required);
/* 544 */     if (astNode == null)
/*     */     {
/* 546 */       return null;
/*     */     }
/*     */     while (true) {
/*     */       AstBinary astBinary;
/* 550 */       switch (this.token.getSymbol()) {
/*     */         
/*     */         case EQ:
/* 553 */           consumeToken();
/* 554 */           astBinary = createAstBinary(astNode, cmp(true), AstBinary.EQ);
/*     */           continue;
/*     */         case NE:
/* 557 */           consumeToken();
/* 558 */           astBinary = createAstBinary((AstNode)astBinary, cmp(true), AstBinary.NE);
/*     */           continue;
/*     */         case EXTENSION:
/* 561 */           if (getExtensionHandler(this.token).getExtensionPoint() == ExtensionPoint.EQ) {
/*     */             
/* 563 */             astNode = getExtensionHandler(consumeToken()).createAstNode(new AstNode[] { (AstNode)astBinary, cmp(true) }); continue;
/*     */           }  break;
/*     */       }  break;
/*     */     } 
/* 567 */     return astNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AstNode cmp(boolean required) throws Scanner.ScanException, ParseException {
/* 577 */     AstNode astNode = add(required);
/* 578 */     if (astNode == null)
/*     */     {
/* 580 */       return null;
/*     */     }
/*     */     while (true) {
/*     */       AstBinary astBinary;
/* 584 */       switch (this.token.getSymbol()) {
/*     */         
/*     */         case LT:
/* 587 */           consumeToken();
/* 588 */           astBinary = createAstBinary(astNode, add(true), AstBinary.LT);
/*     */           continue;
/*     */         case LE:
/* 591 */           consumeToken();
/* 592 */           astBinary = createAstBinary((AstNode)astBinary, add(true), AstBinary.LE);
/*     */           continue;
/*     */         case GE:
/* 595 */           consumeToken();
/* 596 */           astBinary = createAstBinary((AstNode)astBinary, add(true), AstBinary.GE);
/*     */           continue;
/*     */         case GT:
/* 599 */           consumeToken();
/* 600 */           astBinary = createAstBinary((AstNode)astBinary, add(true), AstBinary.GT);
/*     */           continue;
/*     */         case EXTENSION:
/* 603 */           if (getExtensionHandler(this.token).getExtensionPoint() == ExtensionPoint.CMP) {
/*     */             
/* 605 */             astNode = getExtensionHandler(consumeToken()).createAstNode(new AstNode[] { (AstNode)astBinary, add(true) }); continue;
/*     */           }  break;
/*     */       }  break;
/*     */     } 
/* 609 */     return astNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AstNode add(boolean required) throws Scanner.ScanException, ParseException {
/* 619 */     AstNode astNode = mul(required);
/* 620 */     if (astNode == null)
/*     */     {
/* 622 */       return null;
/*     */     }
/*     */     while (true) {
/*     */       AstBinary astBinary;
/* 626 */       switch (this.token.getSymbol()) {
/*     */         
/*     */         case PLUS:
/* 629 */           consumeToken();
/* 630 */           astBinary = createAstBinary(astNode, mul(true), AstBinary.ADD);
/*     */           continue;
/*     */         case MINUS:
/* 633 */           consumeToken();
/* 634 */           astBinary = createAstBinary((AstNode)astBinary, mul(true), AstBinary.SUB);
/*     */           continue;
/*     */         case EXTENSION:
/* 637 */           if (getExtensionHandler(this.token).getExtensionPoint() == ExtensionPoint.ADD) {
/*     */             
/* 639 */             astNode = getExtensionHandler(consumeToken()).createAstNode(new AstNode[] { (AstNode)astBinary, mul(true) }); continue;
/*     */           }  break;
/*     */       }  break;
/*     */     } 
/* 643 */     return astNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AstNode mul(boolean required) throws Scanner.ScanException, ParseException {
/* 653 */     AstNode astNode = unary(required);
/* 654 */     if (astNode == null)
/*     */     {
/* 656 */       return null;
/*     */     }
/*     */     while (true) {
/*     */       AstBinary astBinary;
/* 660 */       switch (this.token.getSymbol()) {
/*     */         
/*     */         case MUL:
/* 663 */           consumeToken();
/* 664 */           astBinary = createAstBinary(astNode, unary(true), AstBinary.MUL);
/*     */           continue;
/*     */         case DIV:
/* 667 */           consumeToken();
/* 668 */           astBinary = createAstBinary((AstNode)astBinary, unary(true), AstBinary.DIV);
/*     */           continue;
/*     */         case MOD:
/* 671 */           consumeToken();
/* 672 */           astBinary = createAstBinary((AstNode)astBinary, unary(true), AstBinary.MOD);
/*     */           continue;
/*     */         case EXTENSION:
/* 675 */           if (getExtensionHandler(this.token).getExtensionPoint() == ExtensionPoint.MUL) {
/*     */             
/* 677 */             astNode = getExtensionHandler(consumeToken()).createAstNode(new AstNode[] { (AstNode)astBinary, unary(true) }); continue;
/*     */           }  break;
/*     */       }  break;
/*     */     } 
/* 681 */     return astNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AstNode unary(boolean required) throws Scanner.ScanException, ParseException {
/*     */     AstUnary astUnary;
/* 691 */     AstNode astNode1, v = null;
/* 692 */     switch (this.token.getSymbol()) {
/*     */       
/*     */       case NOT:
/* 695 */         consumeToken();
/* 696 */         astUnary = createAstUnary(unary(true), AstUnary.NOT);
/*     */         break;
/*     */       case MINUS:
/* 699 */         consumeToken();
/* 700 */         astUnary = createAstUnary(unary(true), AstUnary.NEG);
/*     */         break;
/*     */       case EMPTY:
/* 703 */         consumeToken();
/* 704 */         astUnary = createAstUnary(unary(true), AstUnary.EMPTY);
/*     */         break;
/*     */       case EXTENSION:
/* 707 */         if (getExtensionHandler(this.token).getExtensionPoint() == ExtensionPoint.UNARY) {
/*     */           
/* 709 */           AstNode astNode = getExtensionHandler(consumeToken()).createAstNode(new AstNode[] { unary(true) });
/*     */           break;
/*     */         } 
/*     */       default:
/* 713 */         astNode1 = value(); break;
/*     */     } 
/* 715 */     if (astNode1 == null && required)
/*     */     {
/* 717 */       fail(EXPR_FIRST);
/*     */     }
/* 719 */     return astNode1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AstNode value() throws Scanner.ScanException, ParseException {
/*     */     AstBracket astBracket;
/* 727 */     boolean lvalue = true;
/* 728 */     AstNode v = nonliteral();
/* 729 */     if (v == null) {
/*     */       
/* 731 */       v = literal();
/* 732 */       if (v == null)
/*     */       {
/* 734 */         return null;
/*     */       }
/* 736 */       lvalue = false;
/*     */     }  while (true) {
/*     */       AstDot astDot1; String name; AstDot dot; AstNode property; boolean strict;
/*     */       AstBracket bracket;
/* 740 */       switch (this.token.getSymbol()) {
/*     */         
/*     */         case DOT:
/* 743 */           consumeToken();
/* 744 */           name = consumeToken(Scanner.Symbol.IDENTIFIER).getImage();
/* 745 */           dot = createAstDot(v, name, lvalue);
/* 746 */           if (this.token.getSymbol() == Scanner.Symbol.LPAREN && this.context.isEnabled(Builder.Feature.METHOD_INVOCATIONS)) {
/*     */             
/* 748 */             AstMethod astMethod = createAstMethod((AstProperty)dot, params());
/*     */             
/*     */             continue;
/*     */           } 
/* 752 */           astDot1 = dot;
/*     */           continue;
/*     */         
/*     */         case LBRACK:
/* 756 */           consumeToken();
/* 757 */           property = expr(true);
/* 758 */           strict = !this.context.isEnabled(Builder.Feature.NULL_PROPERTIES);
/* 759 */           consumeToken(Scanner.Symbol.RBRACK);
/* 760 */           bracket = createAstBracket((AstNode)astDot1, property, lvalue, strict);
/* 761 */           if (this.token.getSymbol() == Scanner.Symbol.LPAREN && this.context.isEnabled(Builder.Feature.METHOD_INVOCATIONS)) {
/*     */             
/* 763 */             AstMethod astMethod = createAstMethod((AstProperty)bracket, params());
/*     */             
/*     */             continue;
/*     */           } 
/* 767 */           astBracket = bracket; continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 771 */     return (AstNode)astBracket;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AstNode nonliteral() throws Scanner.ScanException, ParseException {
/*     */     AstIdentifier astIdentifier;
/*     */     AstNode astNode1;
/*     */     AstNested astNested;
/*     */     String name;
/* 782 */     AstNode v = null;
/* 783 */     switch (this.token.getSymbol()) {
/*     */       
/*     */       case IDENTIFIER:
/* 786 */         name = consumeToken().getImage();
/* 787 */         if (this.token.getSymbol() == Scanner.Symbol.COLON && lookahead(0).getSymbol() == Scanner.Symbol.IDENTIFIER && lookahead(1).getSymbol() == Scanner.Symbol.LPAREN) {
/*     */           
/* 789 */           consumeToken();
/* 790 */           name = String.valueOf(name) + ":" + this.token.getImage();
/* 791 */           consumeToken();
/*     */         } 
/* 793 */         if (this.token.getSymbol() == Scanner.Symbol.LPAREN) {
/*     */           
/* 795 */           AstFunction astFunction = function(name, params());
/*     */           
/*     */           break;
/*     */         } 
/* 799 */         astIdentifier = identifier(name);
/*     */         break;
/*     */       
/*     */       case LPAREN:
/* 803 */         consumeToken();
/* 804 */         astNode1 = expr(true);
/* 805 */         consumeToken(Scanner.Symbol.RPAREN);
/* 806 */         astNested = new AstNested(astNode1);
/*     */         break;
/*     */     } 
/* 809 */     return (AstNode)astNested;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AstParameters params() throws Scanner.ScanException, ParseException {
/* 817 */     consumeToken(Scanner.Symbol.LPAREN);
/* 818 */     List<AstNode> l = Collections.emptyList();
/* 819 */     AstNode v = expr(false);
/* 820 */     if (v != null) {
/*     */       
/* 822 */       l = new ArrayList<>();
/* 823 */       l.add(v);
/* 824 */       while (this.token.getSymbol() == Scanner.Symbol.COMMA) {
/*     */         
/* 826 */         consumeToken();
/* 827 */         l.add(expr(true));
/*     */       } 
/*     */     } 
/* 830 */     consumeToken(Scanner.Symbol.RPAREN);
/* 831 */     return new AstParameters(l);
/*     */   }
/*     */   
/*     */   protected AstNode literal() throws Scanner.ScanException, ParseException {
/*     */     AstBoolean astBoolean;
/*     */     AstString astString;
/*     */     AstNumber astNumber;
/*     */     AstNull astNull;
/* 839 */     AstNode astNode1, v = null;
/* 840 */     switch (this.token.getSymbol()) {
/*     */       
/*     */       case TRUE:
/* 843 */         astBoolean = new AstBoolean(true);
/* 844 */         consumeToken();
/*     */         break;
/*     */       case FALSE:
/* 847 */         astBoolean = new AstBoolean(false);
/* 848 */         consumeToken();
/*     */         break;
/*     */       case STRING:
/* 851 */         astString = new AstString(this.token.getImage());
/* 852 */         consumeToken();
/*     */         break;
/*     */       case INTEGER:
/* 855 */         astNumber = new AstNumber(parseInteger(this.token.getImage()));
/* 856 */         consumeToken();
/*     */         break;
/*     */       case FLOAT:
/* 859 */         astNumber = new AstNumber(parseFloat(this.token.getImage()));
/* 860 */         consumeToken();
/*     */         break;
/*     */       case NULL:
/* 863 */         astNull = new AstNull();
/* 864 */         consumeToken();
/*     */         break;
/*     */       case EXTENSION:
/* 867 */         if (getExtensionHandler(this.token).getExtensionPoint() == ExtensionPoint.LITERAL)
/*     */         {
/* 869 */           astNode1 = getExtensionHandler(consumeToken()).createAstNode(new AstNode[0]);
/*     */         }
/*     */         break;
/*     */     } 
/* 873 */     return astNode1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected final AstFunction function(String name, AstParameters params) {
/* 878 */     if (this.functions.isEmpty())
/*     */     {
/* 880 */       this.functions = new ArrayList<>(4);
/*     */     }
/* 882 */     AstFunction function = createAstFunction(name, this.functions.size(), params);
/* 883 */     this.functions.add(function);
/* 884 */     return function;
/*     */   }
/*     */ 
/*     */   
/*     */   protected final AstIdentifier identifier(String name) {
/* 889 */     if (this.identifiers.isEmpty())
/*     */     {
/* 891 */       this.identifiers = new ArrayList<>(4);
/*     */     }
/* 893 */     AstIdentifier identifier = createAstIdentifier(name, this.identifiers.size());
/* 894 */     this.identifiers.add(identifier);
/* 895 */     return identifier;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\Parser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */