package com.lbs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LbsControllerMethod {
  Class<?> utilClass();
  
  String methodName();
  
  boolean needMenu() default false;
  
  boolean needController() default false;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\annotation\LbsControllerMethod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */