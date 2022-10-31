package com.lbs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LbsRestServiceDefinition {
  String tagName() default "";
  
  int version() default 2;
  
  String description() default "";
  
  LbsLocalizedDescription[] localizedDescription() default {};
  
  String endpoint() default "";
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\annotation\LbsRestServiceDefinition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */