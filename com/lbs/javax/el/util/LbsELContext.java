/*    */ package com.lbs.javax.el.util;
/*    */ 
/*    */ import com.lbs.javax.el.CompositeELResolver;
/*    */ import com.lbs.javax.el.ELResolver;
/*    */ import com.lbs.juel.util.SimpleContext;
/*    */ import com.lbs.juel.util.SimpleResolver;
/*    */ import com.lbs.platform.interfaces.IApplicationContext;
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
/*    */ public class LbsELContext
/*    */   extends SimpleContext
/*    */ {
/*    */   public LbsELContext(IApplicationContext context) {
/* 23 */     this(context, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public LbsELContext(IApplicationContext context, ELResolver... resolvers) {
/* 28 */     super(prepareMyResolver(context, resolvers));
/*    */   }
/*    */ 
/*    */   
/*    */   private static ELResolver prepareMyResolver(IApplicationContext context, ELResolver... resolvers) {
/* 33 */     CompositeELResolver myResolver = new CompositeELResolver();
/* 34 */     if (resolvers != null) {
/*    */       byte b; int i; ELResolver[] arrayOfELResolver;
/* 36 */       for (i = (arrayOfELResolver = resolvers).length, b = 0; b < i; ) { ELResolver elResolver = arrayOfELResolver[b];
/*    */         
/* 38 */         if (elResolver != null)
/* 39 */           myResolver.add(elResolver);  b++; }
/*    */     
/*    */     } 
/* 42 */     myResolver.add(new LbsObjectELResolver());
/* 43 */     myResolver.add(new LbsContextELResolver(context));
/* 44 */     myResolver.add((ELResolver)new SimpleResolver(false));
/* 45 */     return (ELResolver)myResolver;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\javax\e\\util\LbsELContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */