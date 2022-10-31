package com.lbs.controls.interfaces.orgchart;

import com.lbs.controls.data.orgchart.OMEOPositionNode;
import com.lbs.controls.data.orgchart.OMEOPositionPerson;

public interface ILbsOrgChartDesignerOperator extends ILbsOrgChartDesignerExtra {
  void addPersons(OMEOPositionNode paramOMEOPositionNode);
  
  boolean copyPositionFromDifferentOrgChart(int paramInt);
  
  boolean changeExecutive(OMEOPositionNode paramOMEOPositionNode);
  
  boolean movePosition(int paramInt);
  
  void insertSubPosition(OMEOPositionNode paramOMEOPositionNode);
  
  boolean updatePosition(OMEOPositionNode paramOMEOPositionNode);
  
  boolean deletePosition(OMEOPositionNode paramOMEOPositionNode);
  
  boolean removePerson(OMEOPositionNode paramOMEOPositionNode, OMEOPositionPerson paramOMEOPositionPerson);
  
  boolean openFilters();
  
  OMEOPositionNode getRoot();
  
  boolean nodeIsDeleted(OMEOPositionNode paramOMEOPositionNode);
  
  boolean refreshOrgChart();
  
  boolean movePositionByDrag(int paramInt1, String paramString1, int paramInt2, int paramInt3, int paramInt4, String paramString2);
  
  boolean isParamPositionCode();
  
  void setParamPositionCode(boolean paramBoolean);
  
  boolean isParamPositionDescription();
  
  void setParamPositionDescription(boolean paramBoolean);
  
  boolean isParamStaffStatus();
  
  void setParamStaffStatus(boolean paramBoolean);
  
  boolean isParamEmployeeNumber();
  
  void setParamEmployeeNumber(boolean paramBoolean);
  
  boolean isParamNameSurname();
  
  void setParamNameSurname(boolean paramBoolean);
  
  boolean isParamCompany();
  
  void setParamCompany(boolean paramBoolean);
  
  boolean isParamDivision();
  
  void setParamDivision(boolean paramBoolean);
  
  boolean isParamUnit();
  
  void setParamUnit(boolean paramBoolean);
  
  boolean isParamDepartment();
  
  void setParamDepartment(boolean paramBoolean);
  
  boolean isParamEmployeePhoto();
  
  void setParamEmployeePhoto(boolean paramBoolean);
  
  boolean isParamPerson();
  
  boolean isParamSubUnit();
  
  void setParamSubUnit(boolean paramBoolean);
  
  boolean isParamPositionStatus();
  
  void setParamTitleCode(boolean paramBoolean);
  
  boolean isParamTitleCode();
  
  void setParamTitleDesc(boolean paramBoolean);
  
  boolean isParamTitleDesc();
  
  void setParamPositionStatus(boolean paramBoolean);
  
  void setShowAddPositionInfos(boolean paramBoolean);
  
  void setShowTempAssignInfos(boolean paramBoolean);
  
  void setShowLinkedPositionEdges(boolean paramBoolean);
  
  void setOrgChartGroupBy(boolean paramBoolean);
  
  void setOrgChartGroupByGrade(boolean paramBoolean1, boolean paramBoolean2);
  
  boolean isShowLinkedPositionEdges();
  
  void setShowNonEmptyPositions(boolean paramBoolean);
  
  void setParamPlaceInfoName(boolean paramBoolean);
  
  void setParamPlaceInfoCode(boolean paramBoolean);
  
  boolean isParamPlaceInfoName();
  
  boolean isParamPlaceInfoCode();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\interfaces\orgchart\ILbsOrgChartDesignerOperator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */