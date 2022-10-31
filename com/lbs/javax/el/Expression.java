package com.lbs.javax.el;

import java.io.Serializable;

public abstract class Expression implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public abstract boolean equals(Object paramObject);
  
  public abstract String getExpressionString();
  
  public abstract int hashCode();
  
  public abstract boolean isLiteralText();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\javax\el\Expression.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */