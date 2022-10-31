package com.lbs.remoting.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LbsRemotingClass {}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\remoting\annotations\LbsRemotingClass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */