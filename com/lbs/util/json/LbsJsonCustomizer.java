package com.lbs.util.json;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface LbsJsonCustomizer {
  String[] sameFields();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\json\LbsJsonCustomizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */