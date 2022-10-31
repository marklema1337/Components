/*    */ package com.lbs.controller;
/*    */ 
/*    */ import com.lbs.controller.dto.Model;
/*    */ import com.lbs.data.objects.SimpleBusinessObject;
/*    */ import com.lbs.platform.interfaces.IApplicationContext;
/*    */ 
/*    */ 
/*    */ public abstract class ChildController<M extends Model, B extends SimpleBusinessObject, C extends Controller<?, ?>>
/*    */   extends Controller<M, B>
/*    */ {
/*    */   protected C m_ParentController;
/*    */   
/*    */   public ChildController(IApplicationContext context, M model, C parentController) {
/* 14 */     super(context, model, parentController.getMode());
/* 15 */     this.m_ParentController = parentController;
/*    */   }
/*    */ 
/*    */   
/*    */   public ChildController(IApplicationContext context, B data, Class<? extends M> modelClass, C parentController) {
/* 20 */     super(context, data, modelClass, parentController.getMode());
/* 21 */     this.m_ParentController = parentController;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void firePropertyValueChangeImpl(String propertyName, Object oldValue) {
/* 27 */     super.firePropertyValueChangeImpl(propertyName, oldValue);
/* 28 */     this.m_ParentController.childPropertyValueChanged(this, propertyName, this.m_Model.getPropertyGroupIds(propertyName), oldValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public C getParentController() {
/* 33 */     return this.m_ParentController;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\ChildController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */