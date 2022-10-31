/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.data.xstream.ILbsXStreamListener;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import com.thoughtworks.xstream.annotations.XStreamImplicit;
/*     */ import com.thoughtworks.xstream.annotations.XStreamOmitField;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnabledList
/*     */   implements Serializable, Cloneable, Externalizable, ILbsXStreamListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("AllDisabled")
/*     */   protected boolean m_AllDisabled = false;
/*     */   @XStreamAlias("AllEnabled")
/*     */   protected boolean m_AllEnabled = false;
/*     */   @XStreamAlias("AutoSwitch")
/*     */   protected boolean m_AutoSwitch = false;
/*     */   @XStreamAlias("EnabledItems")
/*     */   @XStreamImplicit(itemFieldName = "EnabledItem")
/*  34 */   protected List<String> m_EnabledItems = null;
/*     */   @XStreamAlias("DisabledItems")
/*     */   @XStreamImplicit(itemFieldName = "DisabledItem")
/*  37 */   protected List<String> m_DisabledItems = null;
/*     */   
/*     */   @XStreamAlias("AllowRegularExpressions")
/*     */   protected boolean m_AllowRegularExpressions = false;
/*     */   @XStreamOmitField
/*  42 */   protected transient List<String> m_ForcedItems = null;
/*     */   
/*     */   @XStreamOmitField
/*  45 */   protected transient List<String> m_SpecialEnabledItems = null;
/*     */   
/*     */   @XStreamOmitField
/*     */   protected boolean m_SpecialAllDisabled = false;
/*     */ 
/*     */   
/*     */   public EnabledList() {
/*  52 */     afterXStreamDeserialization();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void afterXStreamDeserialization() {
/*  58 */     if (this.m_EnabledItems == null)
/*  59 */       this.m_EnabledItems = new ArrayList<>(); 
/*  60 */     if (this.m_DisabledItems == null)
/*  61 */       this.m_DisabledItems = new ArrayList<>(); 
/*  62 */     if (this.m_ForcedItems == null)
/*  63 */       this.m_ForcedItems = new ArrayList<>(); 
/*  64 */     if (this.m_SpecialEnabledItems == null) {
/*  65 */       this.m_SpecialEnabledItems = new ArrayList<>();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setAllowRegularExpressions(boolean allowRegularExpressions) {
/*  70 */     this.m_AllowRegularExpressions = allowRegularExpressions;
/*     */   }
/*     */ 
/*     */   
/*     */   public void enableAll() {
/*  75 */     this.m_AllDisabled = false;
/*  76 */     this.m_AllEnabled = true;
/*     */     
/*  78 */     this.m_DisabledItems.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void disableAll() {
/*  83 */     disableAll(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasItem(String item) {
/*  88 */     if (this.m_EnabledItems.contains(item) || this.m_DisabledItems.contains(item))
/*  89 */       return true; 
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getDisabledItems() {
/*  95 */     return this.m_DisabledItems;
/*     */   }
/*     */ 
/*     */   
/*     */   public void disableAll(boolean special, boolean fromEmulating) {
/* 100 */     this.m_AllDisabled = true;
/* 101 */     if (special)
/* 102 */       this.m_SpecialAllDisabled = true; 
/* 103 */     this.m_AllEnabled = false;
/*     */     
/* 105 */     if (fromEmulating)
/*     */     {
/* 107 */       for (int i = 0; i < this.m_EnabledItems.size(); i++)
/*     */       {
/* 109 */         this.m_DisabledItems.add(this.m_EnabledItems.get(i));
/*     */       }
/*     */     }
/* 112 */     this.m_EnabledItems.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void disableAll(boolean special) {
/* 117 */     disableAll(special, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 122 */     this.m_EnabledItems.clear();
/* 123 */     this.m_DisabledItems.clear();
/* 124 */     this.m_ForcedItems.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void markForced(String itemName) {
/* 129 */     this.m_ForcedItems.add(itemName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void enable(String itemName) {
/* 134 */     enable(itemName, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void enable(String itemName, boolean special) {
/* 139 */     this.m_DisabledItems.remove(itemName);
/* 140 */     if (!this.m_EnabledItems.contains(itemName)) {
/*     */       
/* 142 */       this.m_EnabledItems.add(itemName);
/* 143 */       if (special) {
/* 144 */         this.m_SpecialEnabledItems.add(itemName);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void disable(String itemName) {
/* 150 */     this.m_EnabledItems.remove(itemName);
/*     */     
/* 152 */     if (!this.m_DisabledItems.contains(itemName)) {
/* 153 */       this.m_DisabledItems.add(itemName);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isEnabled(String itemName) {
/* 158 */     if (this.m_AllEnabled)
/* 159 */       return !isSpecificallyDisabled(itemName); 
/* 160 */     return isSpecificallyEnabled(itemName);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSpecificallyEnabled(String itemName) {
/* 165 */     boolean contains = this.m_EnabledItems.contains(itemName);
/* 166 */     return this.m_AllowRegularExpressions ? ((contains || 
/* 167 */       matches(this.m_EnabledItems, itemName))) : contains;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSpecificallyDisabled(String itemName) {
/* 173 */     boolean contains = this.m_DisabledItems.contains(itemName);
/* 174 */     return this.m_AllowRegularExpressions ? ((contains || 
/* 175 */       matches(this.m_DisabledItems, itemName))) : contains;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isForced(String itemName) {
/* 181 */     return this.m_ForcedItems.contains(itemName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void revertSpecialItems() {
/* 186 */     if (this.m_SpecialAllDisabled)
/* 187 */       this.m_AllDisabled = false; 
/* 188 */     for (String itemName : this.m_SpecialEnabledItems)
/*     */     {
/* 190 */       this.m_EnabledItems.remove(itemName);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean matches(List<String> items, String itemName) {
/* 196 */     for (int i = 0; i < items.size(); i++) {
/* 197 */       if (itemName.matches(items.get(i)))
/* 198 */         return true; 
/* 199 */     }  return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void cloneFast(EnabledList enabledList) {
/* 204 */     enabledList.m_AllDisabled = this.m_AllDisabled;
/* 205 */     enabledList.m_SpecialAllDisabled = this.m_SpecialAllDisabled;
/* 206 */     enabledList.m_AllowRegularExpressions = this.m_AllowRegularExpressions;
/* 207 */     enabledList.m_AutoSwitch = this.m_AutoSwitch;
/* 208 */     enabledList.m_AllEnabled = this.m_AllEnabled;
/* 209 */     enabledList.m_EnabledItems = new ArrayList<>();
/* 210 */     if (this.m_EnabledItems != null && this.m_EnabledItems.size() > 0) {
/*     */       
/* 212 */       List<String> enabledItems = enabledList.m_EnabledItems;
/* 213 */       for (String enabledItem : this.m_EnabledItems) {
/*     */         
/* 215 */         if (enabledItem != null)
/* 216 */           enabledItems.add(new String(enabledItem)); 
/*     */       } 
/*     */     } 
/* 219 */     enabledList.m_DisabledItems = new ArrayList<>();
/* 220 */     if (this.m_DisabledItems != null && this.m_DisabledItems.size() > 0) {
/*     */       
/* 222 */       List<String> disabledItems = enabledList.m_DisabledItems;
/* 223 */       for (String disabledItem : this.m_DisabledItems) {
/*     */         
/* 225 */         if (disabledItem != null)
/* 226 */           disabledItems.add(new String(disabledItem)); 
/*     */       } 
/*     */     } 
/* 229 */     enabledList.m_ForcedItems = new ArrayList<>();
/* 230 */     if (this.m_ForcedItems != null && this.m_ForcedItems.size() > 0) {
/*     */       
/* 232 */       List<String> forcedItems = enabledList.m_ForcedItems;
/* 233 */       for (String forcedItem : this.m_ForcedItems) {
/*     */         
/* 235 */         if (forcedItem != null)
/* 236 */           forcedItems.add(new String(forcedItem)); 
/*     */       } 
/*     */     } 
/* 239 */     enabledList.m_SpecialEnabledItems = new ArrayList<>();
/* 240 */     if (this.m_SpecialEnabledItems != null && this.m_SpecialEnabledItems.size() > 0) {
/*     */       
/* 242 */       List<String> specialEnabledItems = enabledList.m_SpecialEnabledItems;
/* 243 */       for (String specialEnabledItem : this.m_SpecialEnabledItems) {
/*     */         
/* 245 */         if (specialEnabledItem != null) {
/* 246 */           specialEnabledItems.add(new String(specialEnabledItem));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 256 */     EnabledList enabledList = new EnabledList();
/* 257 */     cloneFast(enabledList);
/* 258 */     return enabledList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAllDisabled() {
/* 266 */     return this.m_AllDisabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 271 */     ExternalizationUtil.readExternal(this.m_EnabledItems, in);
/* 272 */     ExternalizationUtil.readExternal(this.m_DisabledItems, in);
/*     */     
/* 274 */     this.m_AllDisabled = in.readBoolean();
/* 275 */     this.m_AutoSwitch = in.readBoolean();
/* 276 */     this.m_AllowRegularExpressions = in.readBoolean();
/* 277 */     this.m_AllEnabled = in.readBoolean();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 282 */     ArrayList enabledItems = (ArrayList)this.m_EnabledItems;
/* 283 */     ArrayList disabledItems = (ArrayList)this.m_DisabledItems;
/* 284 */     ExternalizationUtil.writeExternal(enabledItems, out);
/* 285 */     ExternalizationUtil.writeExternal(disabledItems, out);
/*     */     
/* 287 */     out.writeBoolean(this.m_AllDisabled);
/* 288 */     out.writeBoolean(this.m_AutoSwitch);
/* 289 */     out.writeBoolean(this.m_AllowRegularExpressions);
/* 290 */     out.writeBoolean(this.m_AllEnabled);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\EnabledList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */