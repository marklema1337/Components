package com.lbs.annotation;

import io.swagger.annotations.Authorization;
import io.swagger.annotations.Extension;
import io.swagger.annotations.ResponseHeader;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LbsApiMethodProperty {
  String value();
  
  String notes() default "";
  
  String[] tags() default {""};
  
  Class<?> response() default Void.class;
  
  String responseContainer() default "";
  
  String responseReference() default "";
  
  String httpMethod() default "";
  
  @Deprecated
  int position() default 0;
  
  String nickname() default "";
  
  String produces() default "";
  
  String consumes() default "";
  
  String protocols() default "";
  
  Authorization[] authorizations() default {@Authorization("")};
  
  boolean hidden() default false;
  
  ResponseHeader[] responseHeaders() default {@ResponseHeader(name = "", response = Void.class)};
  
  int code() default 200;
  
  Extension[] extensions() default {@Extension(properties = {@io.swagger.annotations.ExtensionProperty(name = "", value = "")})};
  
  boolean ignoreJsonView() default false;
  
  LbsLocalizedDescription[] localizedDescription() default {};
  
  String endpoint() default "";
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\annotation\LbsApiMethodProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */