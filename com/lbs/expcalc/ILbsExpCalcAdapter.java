package com.lbs.expcalc;

public interface ILbsExpCalcAdapter {
  boolean calculateNumericPar(Object paramObject, String paramString, LbsExpCalcValue paramLbsExpCalcValue);
  
  boolean calculateStringPar(Object paramObject, String paramString, LbsExpCalcValue paramLbsExpCalcValue);
  
  LbsFunctionInfo isExternalFunction(Object paramObject, String paramString);
  
  void calculateFunction(Object paramObject, LbsFunctionInfo paramLbsFunctionInfo, LbsExpCalcValue[] paramArrayOfLbsExpCalcValue, LbsExpCalcValue paramLbsExpCalcValue);
  
  void modifyFunctionInfo(Object paramObject, LbsFunctionInfo paramLbsFunctionInfo);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\expcalc\ILbsExpCalcAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */