package com.lbs.juel.tree;

import com.lbs.javax.el.ELContext;
import com.lbs.javax.el.MethodInfo;
import com.lbs.javax.el.ValueReference;

public interface ExpressionNode extends Node {
  boolean isLiteralText();
  
  boolean isLeftValue();
  
  boolean isMethodInvocation();
  
  Object getValue(Bindings paramBindings, ELContext paramELContext, Class<?> paramClass);
  
  ValueReference getValueReference(Bindings paramBindings, ELContext paramELContext);
  
  Class<?> getType(Bindings paramBindings, ELContext paramELContext);
  
  boolean isReadOnly(Bindings paramBindings, ELContext paramELContext);
  
  void setValue(Bindings paramBindings, ELContext paramELContext, Object paramObject);
  
  MethodInfo getMethodInfo(Bindings paramBindings, ELContext paramELContext, Class<?> paramClass, Class<?>[] paramArrayOfClass);
  
  Object invoke(Bindings paramBindings, ELContext paramELContext, Class<?> paramClass, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject);
  
  String getStructuralId(Bindings paramBindings);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\juel\tree\ExpressionNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */