package com.lbs.data.report.params;

import com.lbs.data.query.QueryParams;
import java.io.Serializable;
import java.util.List;

public class LbsGenericReportParams implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public QueryParams Params;
  
  public String QueryName;
  
  public List<GenericReportColumn> Columns;
  
  public String m_ListenerClassName;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\report\params\LbsGenericReportParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */