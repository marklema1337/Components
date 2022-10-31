/*     */ package com.lbs.juel.tree;
/*     */ 
/*     */ import com.lbs.javax.el.ValueExpression;
/*     */ import com.lbs.juel.misc.TypeConverter;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
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
/*     */ public class Bindings
/*     */   implements TypeConverter
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  38 */   private static final Method[] NO_FUNCTIONS = new Method[0];
/*  39 */   private static final ValueExpression[] NO_VARIABLES = new ValueExpression[0];
/*     */   
/*     */   private transient Method[] functions;
/*     */   private final ValueExpression[] variables;
/*     */   private final TypeConverter converter;
/*     */   
/*     */   private static class MethodWrapper
/*     */     implements Serializable
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     private transient Method method;
/*     */     
/*     */     private MethodWrapper(Method method) {
/*  52 */       this.method = method;
/*     */     }
/*     */ 
/*     */     
/*     */     private void writeObject(ObjectOutputStream out) throws IOException, ClassNotFoundException {
/*  57 */       out.defaultWriteObject();
/*  58 */       out.writeObject(this.method.getDeclaringClass());
/*  59 */       out.writeObject(this.method.getName());
/*  60 */       out.writeObject(this.method.getParameterTypes());
/*     */     }
/*     */ 
/*     */     
/*     */     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  65 */       in.defaultReadObject();
/*  66 */       Class<?> type = (Class)in.readObject();
/*  67 */       String name = (String)in.readObject();
/*  68 */       Class[] args = (Class[])in.readObject();
/*     */       
/*     */       try {
/*  71 */         this.method = type.getDeclaredMethod(name, args);
/*     */       }
/*  73 */       catch (NoSuchMethodException e) {
/*     */         
/*  75 */         throw new IOException(e.getMessage());
/*     */       } 
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
/*     */   
/*     */   public Bindings(Method[] functions, ValueExpression[] variables) {
/*  89 */     this(functions, variables, TypeConverter.DEFAULT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bindings(Method[] functions, ValueExpression[] variables, TypeConverter converter) {
/*  99 */     this.functions = (functions == null || functions.length == 0) ? 
/* 100 */       NO_FUNCTIONS : 
/* 101 */       functions;
/* 102 */     this.variables = (variables == null || variables.length == 0) ? 
/* 103 */       NO_VARIABLES : 
/* 104 */       variables;
/* 105 */     this.converter = (converter == null) ? 
/* 106 */       TypeConverter.DEFAULT : 
/* 107 */       converter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Method getFunction(int index) {
/* 117 */     return this.functions[index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFunctionBound(int index) {
/* 128 */     return (index >= 0 && index < this.functions.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValueExpression getVariable(int index) {
/* 138 */     return this.variables[index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVariableBound(int index) {
/* 149 */     return (index >= 0 && index < this.variables.length && this.variables[index] != null);
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
/*     */   public <T> T convert(Object value, Class<T> type) {
/* 161 */     return (T)this.converter.convert(value, type);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 167 */     if (obj instanceof Bindings) {
/*     */       
/* 169 */       Bindings other = (Bindings)obj;
/* 170 */       return (Arrays.equals((Object[])this.functions, (Object[])other.functions) && Arrays.equals((Object[])this.variables, (Object[])other.variables) && 
/* 171 */         this.converter.equals(other.converter));
/*     */     } 
/* 173 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 179 */     return Arrays.hashCode((Object[])this.functions) ^ Arrays.hashCode((Object[])this.variables) ^ this.converter.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException, ClassNotFoundException {
/* 184 */     out.defaultWriteObject();
/* 185 */     MethodWrapper[] wrappers = new MethodWrapper[this.functions.length];
/* 186 */     for (int i = 0; i < wrappers.length; i++)
/*     */     {
/* 188 */       wrappers[i] = new MethodWrapper(this.functions[i], null);
/*     */     }
/* 190 */     out.writeObject(wrappers);
/*     */   }
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 195 */     in.defaultReadObject();
/* 196 */     MethodWrapper[] wrappers = (MethodWrapper[])in.readObject();
/* 197 */     if (wrappers.length == 0) {
/*     */       
/* 199 */       this.functions = NO_FUNCTIONS;
/*     */     }
/*     */     else {
/*     */       
/* 203 */       this.functions = new Method[wrappers.length];
/* 204 */       for (int i = 0; i < this.functions.length; i++)
/*     */       {
/* 206 */         this.functions[i] = (wrappers[i]).method;
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\Bindings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */