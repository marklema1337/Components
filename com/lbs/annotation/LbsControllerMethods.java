package com.lbs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LbsControllerMethods {
  LbsControllerMethod preMethod() default @LbsControllerMethod(utilClass = Object.class, methodName = "");
  
  LbsControllerMethod postMethod() default @LbsControllerMethod(utilClass = Object.class, methodName = "");
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\annotation\LbsControllerMethods.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */