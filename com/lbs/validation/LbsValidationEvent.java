/*    */ package com.lbs.validation;
/*    */ 
/*    */ import com.lbs.platform.interfaces.IApplicationContext;
/*    */ import com.lbs.platform.interfaces.ILbsContainer;
/*    */ import com.lbs.platform.interfaces.ILbsValidationEvent;
/*    */ import java.util.WeakHashMap;
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
/*    */ public class LbsValidationEvent
/*    */   implements ILbsValidationEvent
/*    */ {
/*    */   public IApplicationContext Context;
/*    */   public ILbsContainer Container;
/*    */   public Object Data;
/*    */   public Object Source;
/*    */   public Object TypeInfo;
/* 25 */   public WeakHashMap Parameters = new WeakHashMap<>();
/*    */   
/*    */   public int Mode;
/*    */ 
/*    */   
/*    */   public IApplicationContext getContext() {
/* 31 */     return this.Context;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setContext(IApplicationContext context) {
/* 36 */     this.Context = context;
/*    */   }
/*    */   
/*    */   public ILbsContainer getContainer() {
/* 40 */     return this.Container;
/*    */   }
/*    */   
/*    */   public void setContainer(ILbsContainer container) {
/* 44 */     this.Container = container;
/*    */   }
/*    */   
/*    */   public Object getData() {
/* 48 */     return this.Data;
/*    */   }
/*    */   
/*    */   public void setData(Object data) {
/* 52 */     this.Data = data;
/*    */   }
/*    */   
/*    */   public Object getSource() {
/* 56 */     return this.Source;
/*    */   }
/*    */   
/*    */   public void setSource(Object source) {
/* 60 */     this.Source = source;
/*    */   }
/*    */   
/*    */   public Object getTypeInfo() {
/* 64 */     return this.TypeInfo;
/*    */   }
/*    */   
/*    */   public void setTypeInfo(Object typeInfo) {
/* 68 */     this.TypeInfo = typeInfo;
/*    */   }
/*    */   
/*    */   public int getMode() {
/* 72 */     return this.Mode;
/*    */   }
/*    */   
/*    */   public void setMode(int mode) {
/* 76 */     this.Mode = mode;
/*    */   }
/*    */   
/*    */   public WeakHashMap getParameters() {
/* 80 */     return this.Parameters;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\validation\LbsValidationEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */