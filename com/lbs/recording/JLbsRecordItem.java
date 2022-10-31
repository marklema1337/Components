/*     */ package com.lbs.recording;
/*     */ 
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.recording.interfaces.ILbsScriptContainer;
/*     */ import java.io.Serializable;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsRecordItem
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JLbsDataPoolItem m_DataPoolItem;
/*     */   private ArrayList m_ExtraDataPoolItems;
/*     */   private String m_Action;
/*     */   private int m_ActionID;
/*     */   private int m_Status;
/*  29 */   private int m_SnapshotIndex = -1;
/*     */   private Object m_Data;
/*     */   private boolean m_RequiresNewThread = false;
/*     */   private boolean m_LastItem;
/*     */   private boolean m_UnitTestLastItem;
/*     */   private boolean m_IsSaveItem = false;
/*  35 */   private int m_ControlLevel = 1;
/*     */   
/*     */   private boolean m_ForceSave = false;
/*  38 */   private transient WeakReference<Object> m_Container = null;
/*  39 */   private transient ILbsComponentBase m_Component = null;
/*     */ 
/*     */   
/*     */   private String m_ComplexOperationType;
/*     */ 
/*     */   
/*     */   public JLbsRecordItem(JLbsDataPoolItem dataPoolItem, String action) {
/*  46 */     this.m_DataPoolItem = dataPoolItem;
/*  47 */     this.m_Action = action;
/*  48 */     this.m_Status = 10;
/*  49 */     this.m_ExtraDataPoolItems = new ArrayList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsRecordItem(JLbsDataPoolItem dataPoolItem, String action, Object data) {
/*  55 */     this.m_DataPoolItem = dataPoolItem;
/*  56 */     this.m_Action = action;
/*  57 */     this.m_Status = 10;
/*  58 */     this.m_Data = data;
/*  59 */     this.m_ExtraDataPoolItems = new ArrayList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsRecordItem(JLbsDataPoolItem dataPoolItem, String action, boolean requiresNewThread) {
/*  65 */     this.m_DataPoolItem = dataPoolItem;
/*  66 */     this.m_Action = action;
/*  67 */     this.m_Status = 10;
/*  68 */     this.m_ExtraDataPoolItems = new ArrayList();
/*  69 */     this.m_RequiresNewThread = requiresNewThread;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsRecordItem(String action, Object data) {
/*  76 */     this.m_Action = action;
/*  77 */     this.m_Data = data;
/*  78 */     this.m_ExtraDataPoolItems = new ArrayList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsRecordItem(String action, Object data, int level) {
/*  85 */     this.m_Action = action;
/*  86 */     this.m_Data = data;
/*  87 */     this.m_ExtraDataPoolItems = new ArrayList();
/*  88 */     this.m_ControlLevel = level;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAction() {
/*  96 */     return this.m_Action;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFilteredAction() {
/* 101 */     if (this.m_Action.indexOf("|") == -1) {
/* 102 */       return this.m_Action;
/*     */     }
/* 104 */     return this.m_Action.substring(this.m_Action.indexOf("|") + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAction(String action) {
/* 112 */     this.m_Action = action;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsDataPoolItem getDataPoolItem() {
/* 120 */     return this.m_DataPoolItem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDataPoolItem(JLbsDataPoolItem dataPoolItem) {
/* 128 */     this.m_DataPoolItem = dataPoolItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 133 */     StringBuilder sb = new StringBuilder();
/* 134 */     sb.append("[");
/* 135 */     sb.append("ActionID=" + getActionID());
/* 136 */     sb.append(", Action=" + getAction());
/* 137 */     if (getDataPoolItem() != null) {
/* 138 */       sb.append(", " + getDataPoolItem().toString());
/* 139 */     } else if (this.m_Data != null) {
/* 140 */       sb.append(", " + this.m_Data);
/* 141 */     }  sb.append(", newThread=" + this.m_RequiresNewThread);
/* 142 */     sb.append("]");
/*     */     
/* 144 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toSimpleString() {
/* 149 */     StringBuilder sb = new StringBuilder();
/* 150 */     sb.append(getAction());
/* 151 */     if (getDataPoolItem() != null) {
/* 152 */       sb.append(", " + getDataPoolItem().getTitle());
/* 153 */     } else if (this.m_Data != null) {
/* 154 */       sb.append(", " + this.m_Data);
/* 155 */     }  return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStatus() {
/* 163 */     return this.m_Status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStatus(int status) {
/* 171 */     this.m_Status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getData() {
/* 176 */     return this.m_Data;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requiresNewThread() {
/* 181 */     return this.m_RequiresNewThread;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRequiresNewThread(boolean requiresNewThread) {
/* 186 */     this.m_RequiresNewThread = requiresNewThread;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getActionID() {
/* 194 */     return this.m_ActionID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActionID(int actionID) {
/* 202 */     this.m_ActionID = actionID;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 207 */     Object clone = super.clone();
/* 208 */     if (clone instanceof JLbsRecordItem)
/*     */     {
/* 210 */       if (this.m_DataPoolItem != null)
/* 211 */         ((JLbsRecordItem)clone).setDataPoolItem((JLbsDataPoolItem)this.m_DataPoolItem.clone()); 
/*     */     }
/* 213 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLastItem() {
/* 218 */     return this.m_LastItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLastItem(boolean lastItem) {
/* 223 */     this.m_LastItem = lastItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equalsIgnoreValue(JLbsRecordItem item) {
/* 228 */     if (item == null)
/* 229 */       return false; 
/* 230 */     if (!this.m_Action.equals(item.getAction()))
/* 231 */       return false; 
/* 232 */     JLbsDataPoolItem dpItem = item.getDataPoolItem();
/* 233 */     if (this.m_DataPoolItem == null)
/* 234 */       return (dpItem == null); 
/* 235 */     if (dpItem == null)
/* 236 */       return false; 
/* 237 */     if (this.m_DataPoolItem.getTag() != dpItem.getTag())
/* 238 */       return false; 
/* 239 */     if (this.m_DataPoolItem.getType() != dpItem.getType())
/* 240 */       return false; 
/* 241 */     if (this.m_DataPoolItem.getParentTag() != dpItem.getParentTag())
/* 242 */       return false; 
/* 243 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLastDataPoolItem() {
/* 249 */     return this.m_UnitTestLastItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLastDataPoolItem(boolean lastDataPoolItem) {
/* 254 */     this.m_UnitTestLastItem = lastDataPoolItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSaveItem() {
/* 259 */     return this.m_IsSaveItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSaveItem(boolean isSaveItem) {
/* 264 */     this.m_IsSaveItem = isSaveItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSnapshotIndex() {
/* 269 */     return this.m_SnapshotIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSnapshotIndex(int snapshotIndex) {
/* 274 */     this.m_SnapshotIndex = snapshotIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addExtraDataPoolItem(JLbsDataPoolItem dataPoolItem) {
/* 280 */     if (this.m_ExtraDataPoolItems != null) {
/* 281 */       this.m_ExtraDataPoolItems.add(dataPoolItem);
/*     */     }
/*     */   }
/*     */   
/*     */   public JLbsDataPoolItem getExtraDataPoolItem(int index) {
/* 286 */     if (this.m_ExtraDataPoolItems != null && this.m_ExtraDataPoolItems.size() > 0 && this.m_ExtraDataPoolItems.get(index) != null && 
/* 287 */       this.m_ExtraDataPoolItems.get(index) instanceof JLbsDataPoolItem) {
/* 288 */       return this.m_ExtraDataPoolItems.get(index);
/*     */     }
/* 290 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExtraDataPoolItemCount() {
/* 295 */     return this.m_ExtraDataPoolItems.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setData(Object data) {
/* 303 */     this.m_Data = data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getControlLevel() {
/* 311 */     return this.m_ControlLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setControlLevel(int controlLevel) {
/* 319 */     this.m_ControlLevel = controlLevel;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isForceSave() {
/* 324 */     return this.m_ForceSave;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForceSave(boolean forceSave) {
/* 329 */     this.m_ForceSave = forceSave;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponent(ILbsComponentBase component) {
/* 334 */     this.m_Component = component;
/*     */   }
/*     */ 
/*     */   
/*     */   public ILbsComponentBase getComponent() {
/* 339 */     return this.m_Component;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getContainer() {
/* 344 */     Object container = (this.m_Container != null) ? 
/* 345 */       this.m_Container.get() : 
/* 346 */       null;
/* 347 */     if (container instanceof ILbsScriptContainer) {
/*     */       
/* 349 */       ILbsScriptContainer sContainer = (ILbsScriptContainer)container;
/* 350 */       return sContainer._getXUIPane();
/*     */     } 
/* 352 */     return container;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setContainer(Object container) {
/* 357 */     this.m_Container = new WeakReference(container);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getComplexOperationType() {
/* 362 */     return this.m_ComplexOperationType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComplexOperationType(String m_ComplexOperationType) {
/* 367 */     this.m_ComplexOperationType = m_ComplexOperationType;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\JLbsRecordItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */