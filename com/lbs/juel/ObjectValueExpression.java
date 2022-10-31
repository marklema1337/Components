/*     */ package com.lbs.juel;
/*     */ 
/*     */ import com.lbs.javax.el.ELContext;
/*     */ import com.lbs.javax.el.ELException;
/*     */ import com.lbs.javax.el.ValueExpression;
/*     */ import com.lbs.juel.misc.LocalMessages;
/*     */ import com.lbs.juel.misc.TypeConverter;
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
/*     */ public final class ObjectValueExpression
/*     */   extends ValueExpression
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final TypeConverter converter;
/*     */   private final Object object;
/*     */   private final Class<?> type;
/*     */   
/*     */   public ObjectValueExpression(TypeConverter converter, Object object, Class<?> type) {
/*  47 */     this.converter = converter;
/*  48 */     this.object = object;
/*  49 */     this.type = type;
/*     */     
/*  51 */     if (type == null)
/*     */     {
/*  53 */       throw new NullPointerException(LocalMessages.get("error.value.notype", new Object[0]));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  63 */     if (obj != null && obj.getClass() == getClass()) {
/*     */       
/*  65 */       ObjectValueExpression other = (ObjectValueExpression)obj;
/*  66 */       if (this.type != other.type)
/*     */       {
/*  68 */         return false;
/*     */       }
/*  70 */       return !(this.object != other.object && (this.object == null || !this.object.equals(other.object)));
/*     */     } 
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  78 */     return (this.object == null) ? 
/*  79 */       0 : 
/*  80 */       this.object.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(ELContext context) {
/*  89 */     return this.converter.convert(this.object, this.type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExpressionString() {
/*  98 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLiteralText() {
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getType(ELContext context) {
/* 116 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadOnly(ELContext context) {
/* 125 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(ELContext context, Object value) {
/* 134 */     throw new ELException(LocalMessages.get("error.value.set.rvalue", new Object[] { "<object value expression>" }));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 140 */     return "ValueExpression(" + this.object + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getExpectedType() {
/* 146 */     return this.type;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\ObjectValueExpression.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */