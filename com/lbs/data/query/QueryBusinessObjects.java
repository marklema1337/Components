/*    */ package com.lbs.data.query;
/*    */ 
/*    */ import com.lbs.data.objects.BasicBusinessObjects;
/*    */ import com.lbs.util.JLbsListedHashtable;
/*    */ import com.thoughtworks.xstream.annotations.XStreamInclude;
/*    */ import java.io.Externalizable;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInput;
/*    */ import java.io.ObjectOutput;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @XStreamInclude({QueryBusinessObject.class})
/*    */ public class QueryBusinessObjects
/*    */   extends BasicBusinessObjects<QueryBusinessObject>
/*    */   implements Serializable, Externalizable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 27 */   private transient String[] m_ColumnNames = null;
/* 28 */   private JLbsListedHashtable m_AuthModes = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String[] getColumnNames() {
/* 35 */     return this.m_ColumnNames;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setColumnNames(String[] columnNames) {
/* 44 */     this.m_ColumnNames = columnNames;
/*    */   }
/*    */ 
/*    */   
/*    */   public QueryBusinessObject item(int index) {
/* 49 */     return (QueryBusinessObject)itemAt(index);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAuthModes(JLbsListedHashtable authModes) {
/* 54 */     this.m_AuthModes = authModes;
/*    */   }
/*    */ 
/*    */   
/*    */   public JLbsListedHashtable getAuthModes() {
/* 59 */     return this.m_AuthModes;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeExternal(ObjectOutput out) throws IOException {
/* 65 */     super.writeExternal(out);
/* 66 */     out.writeObject(this.m_AuthModes);
/* 67 */     out.writeObject(this.m_ColumnNames);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 73 */     super.readExternal(in);
/* 74 */     this.m_AuthModes = (JLbsListedHashtable)in.readObject();
/* 75 */     this.m_ColumnNames = (String[])in.readObject();
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\query\QueryBusinessObjects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */