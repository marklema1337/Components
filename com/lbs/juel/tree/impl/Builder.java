/*     */ package com.lbs.juel.tree.impl;
/*     */ 
/*     */ import com.lbs.javax.el.ELContext;
/*     */ import com.lbs.javax.el.ELException;
/*     */ import com.lbs.javax.el.ELResolver;
/*     */ import com.lbs.javax.el.FunctionMapper;
/*     */ import com.lbs.javax.el.VariableMapper;
/*     */ import com.lbs.juel.tree.Bindings;
/*     */ import com.lbs.juel.tree.Node;
/*     */ import com.lbs.juel.tree.NodePrinter;
/*     */ import com.lbs.juel.tree.Tree;
/*     */ import com.lbs.juel.tree.TreeBuilder;
/*     */ import com.lbs.juel.tree.TreeBuilderException;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.EnumSet;
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
/*     */ public class Builder
/*     */   implements TreeBuilder
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected final EnumSet<Feature> features;
/*     */   
/*     */   public enum Feature
/*     */   {
/*  48 */     METHOD_INVOCATIONS,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  55 */     NULL_PROPERTIES,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     VARARGS,
/*     */ 
/*     */ 
/*     */     
/*  67 */     IGNORE_RETURN_TYPE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder() {
/*  78 */     this.features = EnumSet.noneOf(Feature.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public Builder(Feature... features) {
/*  83 */     if (features == null || features.length == 0) {
/*     */       
/*  85 */       this.features = EnumSet.noneOf(Feature.class);
/*     */     }
/*  87 */     else if (features.length == 1) {
/*     */       
/*  89 */       this.features = EnumSet.of(features[0]);
/*     */     }
/*     */     else {
/*     */       
/*  93 */       Feature[] rest = new Feature[features.length - 1];
/*  94 */       for (int i = 1; i < features.length; i++)
/*     */       {
/*  96 */         rest[i - 1] = features[i];
/*     */       }
/*  98 */       this.features = EnumSet.of(features[0], rest);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEnabled(Feature feature) {
/* 107 */     return this.features.contains(feature);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tree build(String expression) throws TreeBuilderException {
/*     */     try {
/* 117 */       return createParser(expression).tree();
/*     */     }
/* 119 */     catch (ScanException e) {
/*     */       
/* 121 */       throw new TreeBuilderException(expression, e.position, e.encountered, e.expected, e.getMessage());
/*     */     }
/* 123 */     catch (ParseException e) {
/*     */       
/* 125 */       throw new TreeBuilderException(expression, e.position, e.encountered, e.expected, e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Parser createParser(String expression) {
/* 131 */     return new Parser(this, expression);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 137 */     if (obj == null || obj.getClass() != getClass())
/*     */     {
/* 139 */       return false;
/*     */     }
/* 141 */     return this.features.equals(((Builder)obj).features);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 147 */     return getClass().hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 157 */     if (args.length != 1) {
/*     */       
/* 159 */       System.err.println("usage: java " + Builder.class.getName() + " <expression string>");
/* 160 */       System.exit(1);
/*     */     } 
/* 162 */     PrintWriter out = new PrintWriter(System.out);
/* 163 */     Tree tree = null;
/*     */     
/*     */     try {
/* 166 */       tree = (new Builder(new Feature[] { Feature.METHOD_INVOCATIONS })).build(args[0]);
/*     */     }
/* 168 */     catch (TreeBuilderException e) {
/*     */       
/* 170 */       System.out.println(e.getMessage());
/* 171 */       System.exit(0);
/*     */     } 
/* 173 */     NodePrinter.dump(out, (Node)tree.getRoot());
/* 174 */     if (!tree.getFunctionNodes().iterator().hasNext() && !tree.getIdentifierNodes().iterator().hasNext()) {
/*     */       
/* 176 */       ELContext context = new ELContext()
/*     */         {
/*     */           
/*     */           public VariableMapper getVariableMapper()
/*     */           {
/* 181 */             return null;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public FunctionMapper getFunctionMapper() {
/* 187 */             return null;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public ELResolver getELResolver() {
/* 193 */             return null;
/*     */           }
/*     */         };
/* 196 */       out.print(">> ");
/*     */       
/*     */       try {
/* 199 */         out.println(tree.getRoot().getValue(new Bindings(null, null), context, null));
/*     */       }
/* 201 */       catch (ELException e) {
/*     */         
/* 203 */         out.println(e.getMessage());
/*     */       } 
/*     */     } 
/* 206 */     out.flush();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */