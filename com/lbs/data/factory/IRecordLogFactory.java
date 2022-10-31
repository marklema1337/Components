package com.lbs.data.factory;

import com.lbs.data.objects.BusinessObject;

public interface IRecordLogFactory {
  BusinessObject createRecordLogEntity();
  
  Object getDomainNr(INamedVariables paramINamedVariables);
  
  Object getCompanyNr(INamedVariables paramINamedVariables);
  
  Object getPeriodNr(INamedVariables paramINamedVariables);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\factory\IRecordLogFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */