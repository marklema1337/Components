package com.lbs.annotation;

import io.swagger.annotations.ApiParam;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LbsApiFieldProperty {
  String varName() default "";
  
  String exampleValue() default "";
  
  boolean required() default false;
  
  boolean readOnly() default false;
  
  int tag() default -2147483648;
  
  Class<?> gridDtoClass() default LbsApiFieldProperty.class;
  
  String description() default "";
  
  LbsLocalizedDescription[] localizedDescription() default {};
  
  ApiParam apiParam() default @ApiParam;
  
  int componentType() default -1;
  
  int order() default 2147483647;
  
  int checkboxTag() default -1;
  
  boolean regularField() default true;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\annotation\LbsApiFieldProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */