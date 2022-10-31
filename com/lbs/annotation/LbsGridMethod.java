package com.lbs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LbsGridMethod {
  String rowDelete() default "";
  
  String rowCount() default "";
  
  String rowData() default "";
  
  String beforeRowSplitter() default "";
  
  String append() default "";
  
  String launcher() default "";
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\annotation\LbsGridMethod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */