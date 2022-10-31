package com.lbs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LbsLocalizedDescription {
  String description() default "";
  
  String resGroup() default "UN";
  
  int listID() default -2147483648;
  
  int stringTag() default -2147483648;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\annotation\LbsLocalizedDescription.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */