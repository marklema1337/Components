package com.lbs.ws;

import com.lbs.remoteclient.IClientContext;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.axis2.AxisFault;

public interface ILbsWebServiceOperation {
  boolean doWSOperation(int paramInt, IClientContext paramIClientContext, Properties paramProperties, Object[] paramArrayOfObject) throws AxisFault, LbsWebServiceException, FileNotFoundException, IOException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\ws\ILbsWebServiceOperation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */