package com.lbs.parameter;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class PropertyValue implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected abstract String getValueStatement(ParameterProperty paramParameterProperty);
  
  protected abstract void prepareImports(ParameterProperty paramParameterProperty, ArrayList<String> paramArrayList);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\parameter\PropertyValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */