/*     */ package com.lbs.juel;
/*     */ 
/*     */ import com.lbs.javax.el.ELContext;
/*     */ import com.lbs.javax.el.ELException;
/*     */ import com.lbs.javax.el.FunctionMapper;
/*     */ import com.lbs.javax.el.ValueExpression;
/*     */ import com.lbs.javax.el.ValueReference;
/*     */ import com.lbs.javax.el.VariableMapper;
/*     */ import com.lbs.juel.misc.LocalMessages;
/*     */ import com.lbs.juel.misc.TypeConverter;
/*     */ import com.lbs.juel.tree.Bindings;
/*     */ import com.lbs.juel.tree.ExpressionNode;
/*     */ import com.lbs.juel.tree.Node;
/*     */ import com.lbs.juel.tree.NodePrinter;
/*     */ import com.lbs.juel.tree.Tree;
/*     */ import com.lbs.juel.tree.TreeBuilder;
/*     */ import com.lbs.juel.tree.TreeStore;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.PrintWriter;
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
/*     */ public final class TreeValueExpression
/*     */   extends ValueExpression
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final TreeBuilder builder;
/*     */   private final Bindings bindings;
/*     */   private final String expr;
/*     */   private final Class<?> type;
/*     */   private final boolean deferred;
/*     */   private transient ExpressionNode node;
/*     */   private String structure;
/*     */   
/*     */   public TreeValueExpression(TreeStore store, FunctionMapper functions, VariableMapper variables, TypeConverter converter, String expr, Class<?> type) {
/*  73 */     Tree tree = store.get(expr);
/*     */     
/*  75 */     this.builder = store.getBuilder();
/*  76 */     this.bindings = tree.bind(functions, variables, converter);
/*  77 */     this.expr = expr;
/*  78 */     this.type = type;
/*  79 */     this.node = tree.getRoot();
/*  80 */     this.deferred = tree.isDeferred();
/*     */     
/*  82 */     if (type == null)
/*     */     {
/*  84 */       throw new NullPointerException(LocalMessages.get("error.value.notype", new Object[0]));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private String getStructuralId() {
/*  90 */     if (this.structure == null)
/*     */     {
/*  92 */       this.structure = this.node.getStructuralId(this.bindings);
/*     */     }
/*  94 */     return this.structure;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getExpectedType() {
/* 100 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExpressionString() {
/* 106 */     return this.expr;
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
/*     */   
/*     */   public Class<?> getType(ELContext context) throws ELException {
/* 119 */     return this.node.getType(this.bindings, context);
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
/*     */   
/*     */   public Object getValue(ELContext context) throws ELException {
/* 132 */     return this.node.getValue(this.bindings, context, this.type);
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
/*     */ 
/*     */   
/*     */   public boolean isReadOnly(ELContext context) throws ELException {
/* 146 */     return this.node.isReadOnly(this.bindings, context);
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
/*     */   public void setValue(ELContext context, Object value) throws ELException {
/* 158 */     this.node.setValue(this.bindings, context, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLiteralText() {
/* 167 */     return this.node.isLiteralText();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ValueReference getValueReference(ELContext context) {
/* 173 */     return this.node.getValueReference(this.bindings, context);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLeftValue() {
/* 183 */     return this.node.isLeftValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDeferred() {
/* 192 */     return this.deferred;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 209 */     if (obj != null && obj.getClass() == getClass()) {
/*     */       
/* 211 */       TreeValueExpression other = (TreeValueExpression)obj;
/* 212 */       if (!this.builder.equals(other.builder))
/*     */       {
/* 214 */         return false;
/*     */       }
/* 216 */       if (this.type != other.type)
/*     */       {
/* 218 */         return false;
/*     */       }
/* 220 */       return (getStructuralId().equals(other.getStructuralId()) && this.bindings.equals(other.bindings));
/*     */     } 
/* 222 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 228 */     return getStructuralId().hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 234 */     return "TreeValueExpression(" + this.expr + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(PrintWriter writer) {
/* 243 */     NodePrinter.dump(writer, (Node)this.node);
/*     */   }
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 248 */     in.defaultReadObject();
/*     */     
/*     */     try {
/* 251 */       this.node = this.builder.build(this.expr).getRoot();
/*     */     }
/* 253 */     catch (ELException e) {
/*     */       
/* 255 */       throw new IOException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\TreeValueExpression.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */