package com.lbs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LbsMessageExecutor {
  String preMessage() default "";
  
  String postMessage() default "";
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\annotation\LbsMessageExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */