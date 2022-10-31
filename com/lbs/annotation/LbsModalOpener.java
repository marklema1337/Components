package com.lbs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LbsModalOpener {
  Class<?> utilClass();
  
  String clickMethod();
  
  String utilMethod();
  
  String jfmName();
  
  boolean after() default false;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\annotation\LbsModalOpener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */