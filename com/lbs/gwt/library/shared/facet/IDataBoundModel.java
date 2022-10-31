package com.lbs.gwt.library.shared.facet;

import java.io.Serializable;

public interface IDataBoundModel extends Serializable {
  Serializable getFieldValue(String paramString);
  
  PropertyConstraints getPropertyConstraint(String paramString);
  
  void setFieldValue(String paramString, Serializable paramSerializable);
  
  String[] getFieldNames();
  
  int getPrimaryKey();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\gwt\library\shared\facet\IDataBoundModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */