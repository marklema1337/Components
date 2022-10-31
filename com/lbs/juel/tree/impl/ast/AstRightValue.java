/*    */ package com.lbs.juel.tree.impl.ast;
/*    */ 
/*    */ import com.lbs.javax.el.ELContext;
/*    */ import com.lbs.javax.el.ELException;
/*    */ import com.lbs.javax.el.MethodInfo;
/*    */ import com.lbs.javax.el.ValueReference;
/*    */ import com.lbs.juel.misc.LocalMessages;
/*    */ import com.lbs.juel.tree.Bindings;
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
/*    */ public abstract class AstRightValue
/*    */   extends AstNode
/*    */ {
/*    */   public final boolean isLiteralText() {
/* 35 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final Class<?> getType(Bindings bindings, ELContext context) {
/* 43 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final boolean isReadOnly(Bindings bindings, ELContext context) {
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final void setValue(Bindings bindings, ELContext context, Object value) {
/* 59 */     throw new ELException(LocalMessages.get("error.value.set.rvalue", new Object[] { getStructuralId(bindings) }));
/*    */   }
/*    */ 
/*    */   
/*    */   public final MethodInfo getMethodInfo(Bindings bindings, ELContext context, Class<?> returnType, Class[] paramTypes) {
/* 64 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public final Object invoke(Bindings bindings, ELContext context, Class<?> returnType, Class[] paramTypes, Object[] paramValues) {
/* 70 */     throw new ELException(LocalMessages.get("error.method.invalid", new Object[] { getStructuralId(bindings) }));
/*    */   }
/*    */ 
/*    */   
/*    */   public final boolean isLeftValue() {
/* 75 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isMethodInvocation() {
/* 80 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public final ValueReference getValueReference(Bindings bindings, ELContext context) {
/* 85 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\impl\ast\AstRightValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */