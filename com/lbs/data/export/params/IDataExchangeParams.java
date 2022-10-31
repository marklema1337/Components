package com.lbs.data.export.params;

import com.lbs.data.factory.ISubstitutionVariables;
import com.lbs.data.objects.SimpleBusinessObject;
import com.lbs.platform.interfaces.ILbsBOXMLSerializer;
import com.lbs.platform.interfaces.ILbsValidationResult;
import java.io.Serializable;
import java.util.Hashtable;

public interface IDataExchangeParams extends Serializable {
  public static final int CALENDAR_TIMEINMILLIS = 0;
  
  public static final int CALENDAR_STRING = 1;
  
  public static final int OPTION_NO_BLOB = 1;
  
  public static final int OPTION_NO_CLOB = 2;
  
  public static final int OPTION_PARAM_TRANSFER = 4;
  
  public static final int OPTION_SINGLE_TRANSACTION = 8;
  
  public static final int OPTION_USE_ENV_VARS = 16;
  
  public static final int OPTION_USE_KEYS = 32;
  
  public static final int OPTION_OVERWRITE = 64;
  
  public static final int OPTION_NO_DEFAULTS = 128;
  
  public static final int OPTION_OLD_XML = 256;
  
  public static final int OPTION_DEFAULTS = 144;
  
  public static final int TYPE_DBF = 0;
  
  public static final int TYPE_XML = 1;
  
  public static final int TYPE_XLS = 2;
  
  public static final int OPERATION_UPDATE = 1;
  
  public static final int OPERATION_DELETE = 2;
  
  public static final int INSERT_INSERT = 0;
  
  public static final int INSERT_MERGE = 1;
  
  public static final int INSERT_OVERWRITE = 2;
  
  public static final int EXPORT_OBJECTS_SELECTED = 0;
  
  public static final int EXPORT_OBJECTS_ALL = 1;
  
  public static final String INITIALIZE_FOR_TRANSFER = "InitializeForTransfer";
  
  public static final String INITIALIZE_FOR_EXPORT = "InitializeForExport";
  
  public static final String FORM_NAME = "FormName";
  
  public static final int OPTION_XML = 1;
  
  public static final int OPTION_XLS = 2;
  
  public static final int OPTION_ALL = 3;
  
  public static final String EXCHANGE_TYPE_STR = "EXCHANGE_TYPE_STR";
  
  public static final String BEFORE_IMPORT_BEFORE_INITIALIZE = "BEFORE_IMPORT_BEFORE_INITIALIZE";
  
  int getExchangeType();
  
  void setExchangeType(int paramInt);
  
  void setBOClassName(String paramString);
  
  String getBOClassName();
  
  void setObjectBOClassName(String paramString);
  
  String getObjectBOClassName();
  
  void setCustGUID(String paramString);
  
  String getCustGUID();
  
  String getCustomObjectName();
  
  void setCustomObjectName(String paramString);
  
  ListDefinition getListDefinition();
  
  void setListDefinition(ListDefinition paramListDefinition);
  
  int getCalendarExchangeOption();
  
  int getExchangeOptions();
  
  void setExchangeOptions(int paramInt);
  
  boolean isOptionSet(int paramInt);
  
  void toggleOption(int paramInt);
  
  void setCalendarExchangeOption(int paramInt);
  
  void setOption(int paramInt, boolean paramBoolean);
  
  IDataExchangeParams cloneParams();
  
  Hashtable<String, ILbsBOXMLSerializer> getExtensionSerializers();
  
  void addExtensionSerializer(String paramString, ILbsBOXMLSerializer paramILbsBOXMLSerializer);
  
  String getFileName();
  
  void setFileName(String paramString);
  
  String getXSLFileName();
  
  void setXSLFileName(String paramString);
  
  int getDesiredObjectState();
  
  void setDesiredObjectState(int paramInt);
  
  int getInsertOption();
  
  void setInsertOption(int paramInt);
  
  byte[] getXSLFileContent();
  
  void setXSLFileContent(byte[] paramArrayOfbyte);
  
  void readXSLFileContent();
  
  int getDomainNr();
  
  void setDomainNr(int paramInt);
  
  int getForbiddenOperations();
  
  void setForbiddenOperations(int paramInt);
  
  ILbsValidationResult initialize(SimpleBusinessObject paramSimpleBusinessObject);
  
  void setVariables(ISubstitutionVariables paramISubstitutionVariables);
  
  ISubstitutionVariables getVariables();
  
  boolean isDescriptive();
  
  void setDescriptive(boolean paramBoolean);
  
  IDataExchangeDescriptor getDescriptor();
  
  void setDescriptor(IDataExchangeDescriptor paramIDataExchangeDescriptor);
  
  void setExcelColumnMap(Hashtable<String, Integer> paramHashtable);
  
  Hashtable<String, Integer> getExcelColumnMap();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\export\params\IDataExchangeParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */