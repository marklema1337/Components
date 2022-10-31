/*    */ package com.lbs.controls.data.orgchart;
/*    */ 
/*    */ import javax.swing.tree.DefaultMutableTreeNode;
/*    */ 
/*    */ public class OMEOPositionPerson
/*    */   extends DefaultMutableTreeNode {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public int reference;
/*    */   public String code;
/*    */   public String name;
/*    */   public byte[] photo;
/*    */   public String firmCode;
/*    */   public String firm;
/*    */   public String locationCode;
/*    */   public String location;
/*    */   public String unitCode;
/*    */   public String unit;
/*    */   public String departmentCode;
/*    */   public String department;
/*    */   public String subUnitCode;
/*    */   public String subUnit;
/*    */   public String titleCode;
/*    */   public String titleDesc;
/*    */   public int gender;
/* 25 */   public int tempAssignRef = 0;
/*    */   public boolean isAdditionalPos = false;
/*    */   public OMEOPositionPerson delegate;
/*    */   public boolean isDelegate = false;
/* 29 */   public double yPosition = 0.0D;
/* 30 */   public double height = 0.0D;
/*    */   public boolean searchResult = false;
/* 32 */   public int executiveManagerRef = 0;
/*    */   
/*    */   public boolean isExecutiveUpperManagerSame = false;
/*    */   public String email;
/*    */   public String cellPhone;
/*    */   public String placeInfoName;
/*    */   public String placeInfoCode;
/*    */   
/*    */   public OMEOPositionPerson() {
/* 41 */     this.code = "";
/* 42 */     this.name = "";
/* 43 */     this.firm = "";
/* 44 */     this.location = "";
/* 45 */     this.unit = "";
/* 46 */     this.department = "";
/* 47 */     this.subUnit = "";
/* 48 */     this.titleCode = "";
/* 49 */     this.titleDesc = "";
/* 50 */     this.placeInfoName = "";
/* 51 */     this.placeInfoCode = "";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String result = "";
/* 58 */     if (this.code != null && this.code.length() > 0)
/* 59 */       result = String.valueOf(result) + this.code; 
/* 60 */     if (this.name != null && this.name.length() > 0)
/* 61 */       result = String.valueOf(result) + " " + this.name; 
/* 62 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 68 */     int prime = 31;
/* 69 */     int result = 1;
/* 70 */     result = 31 * result + (this.isAdditionalPos ? 1231 : 1237);
/* 71 */     result = 31 * result + (this.isDelegate ? 1231 : 1237);
/* 72 */     result = 31 * result + this.reference;
/* 73 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 79 */     if (this == obj)
/* 80 */       return true; 
/* 81 */     if (obj == null)
/* 82 */       return false; 
/* 83 */     if (getClass() != obj.getClass())
/* 84 */       return false; 
/* 85 */     OMEOPositionPerson other = (OMEOPositionPerson)obj;
/* 86 */     if (this.isAdditionalPos != other.isAdditionalPos)
/* 87 */       return false; 
/* 88 */     if (this.isDelegate != other.isDelegate)
/* 89 */       return false; 
/* 90 */     if (this.reference != other.reference)
/* 91 */       return false; 
/* 92 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\data\orgchart\OMEOPositionPerson.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */