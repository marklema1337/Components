/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.data.IParameterMandatoryListener;
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.data.Parameter;
/*     */ import com.lbs.interfaces.ILbsXUIComparable;
/*     */ import com.lbs.interfaces.IParameter;
/*     */ import com.lbs.interfaces.ParameterException;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import com.thoughtworks.xstream.annotations.XStreamOmitField;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class JLbsStringList
/*     */   implements Iterable<JLbsStringListItem>, Serializable, Cloneable, ILbsXUIComparable<JLbsStringList>, IParameter
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("List")
/*  36 */   protected ArrayList<JLbsStringListItem> m_List = new ArrayList<>();
/*     */   @XStreamAlias("UpdateCnt")
/*  38 */   private int m_iUpdateCnt = 0;
/*     */   @XStreamOmitField
/*  40 */   private transient JLbsStringListListener m_Listener = null;
/*     */   @XStreamAlias("ID")
/*  42 */   private int m_ID = 0;
/*     */   @XStreamAlias("Description")
/*  44 */   private String m_Description = null;
/*  45 */   protected static Pattern ms_Pattern = Pattern.compile("\\|");
/*     */ 
/*     */ 
/*     */   
/*     */   private int m_Tag;
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsStringList(JLbsStringList list) {
/*  54 */     addAll(list);
/*  55 */     if (list != null) {
/*  56 */       this.m_ID = list.m_ID;
/*     */     }
/*     */   }
/*     */   
/*     */   public JLbsStringList(String[] list) {
/*  61 */     if (list != null) {
/*     */       
/*  63 */       beginUpdate();
/*  64 */       int iLen = list.length;
/*  65 */       for (int i = 0; i < iLen; i++) {
/*     */         
/*  67 */         if (list[i] != null) {
/*  68 */           this.m_List.add(createItem(list[i].trim()));
/*     */         } else {
/*  70 */           this.m_List.add(createItem(list[i]));
/*     */         } 
/*  72 */       }  endUpdate();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsStringList(String list) {
/*  80 */     this((list != null) ? ms_Pattern.split(list) : null);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList(String list, String delim) {
/*  85 */     this(list.split(delim));
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<JLbsStringListItem> getList() {
/*  90 */     return this.m_List;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChangeListener(JLbsStringListListener listener) {
/*  95 */     this.m_Listener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void beginUpdate() {
/* 100 */     this.m_iUpdateCnt++;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean endUpdate() {
/* 105 */     if (this.m_iUpdateCnt > 0) {
/*     */       
/* 107 */       this.m_iUpdateCnt--;
/* 108 */       if (this.m_iUpdateCnt == 0) {
/*     */         
/* 110 */         if (this.m_Listener != null) {
/*     */           
/*     */           try {
/* 113 */             this.m_Listener.stringListChanged(this);
/*     */           }
/* 115 */           catch (Exception exception) {}
/*     */         }
/*     */         
/* 118 */         return true;
/*     */       } 
/*     */     } 
/* 121 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addAll(JLbsStringList list) {
/* 126 */     if (list != null) {
/*     */       
/* 128 */       beginUpdate();
/* 129 */       int iLen = list.size();
/* 130 */       for (int i = 0; i < iLen; i++)
/* 131 */         this.m_List.add(createItem(list.m_List.get(i))); 
/* 132 */       endUpdate();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int add(String item) {
/* 138 */     return add(this.m_List.size(), item);
/*     */   }
/*     */ 
/*     */   
/*     */   public int addRaw(String item) {
/* 143 */     return add(this.m_List.size(), item, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int add(String item, int iTag) {
/* 148 */     return add(this.m_List.size(), item, iTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public int add(int index, String item) {
/* 153 */     beginUpdate();
/* 154 */     this.m_List.add(index, createItem(item));
/* 155 */     endUpdate();
/* 156 */     return index;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(JLbsStringListItem item) {
/* 161 */     beginUpdate();
/* 162 */     this.m_List.add(item);
/* 163 */     endUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public int addUnique(String item) {
/* 168 */     if (indexOf(item) < 0)
/* 169 */       return addRaw(item); 
/* 170 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int addRaw(int index, String item) {
/* 175 */     return add(index, item, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int add(int index, String item, int iTag) {
/* 180 */     beginUpdate();
/* 181 */     this.m_List.add(index, createItem(item, iTag));
/* 182 */     endUpdate();
/* 183 */     return index;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(String item) {
/* 188 */     return removeAt(indexOf(item));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(int iTag) {
/* 193 */     return removeAt(indexOf(iTag));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeAt(int index) {
/* 198 */     if (index >= 0 && index < this.m_List.size()) {
/*     */       
/* 200 */       this.m_List.remove(index);
/* 201 */       return true;
/*     */     } 
/* 203 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAll() {
/* 208 */     beginUpdate();
/* 209 */     this.m_List.clear();
/* 210 */     endUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/* 215 */     return this.m_List.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValueAt(int index) {
/* 220 */     if (index >= 0 && index < this.m_List.size()) {
/*     */       
/* 222 */       JLbsStringListItem listItem = this.m_List.get(index);
/* 223 */       return listItem.Value;
/*     */     } 
/* 225 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTagAt(int index) {
/* 230 */     if (index >= 0 && index < this.m_List.size()) {
/*     */       
/* 232 */       JLbsStringListItem listItem = this.m_List.get(index);
/* 233 */       return listItem.Tag;
/*     */     } 
/* 235 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringListItem getAt(int index) {
/* 240 */     if (index >= 0 && index < this.m_List.size())
/* 241 */       return createItem(this.m_List.get(index)); 
/* 242 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringListItem getItemAt(int index) {
/* 247 */     if (index >= 0 && index < this.m_List.size())
/* 248 */       return this.m_List.get(index); 
/* 249 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringListItem getItem(int tag) {
/* 254 */     int index = indexOf(tag);
/* 255 */     if (index >= 0 && index < this.m_List.size())
/* 256 */       return this.m_List.get(index); 
/* 257 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValueAtTag(int iTag) {
/* 262 */     int index = indexOf(iTag);
/* 263 */     if (index >= 0)
/* 264 */       return ((JLbsStringListItem)this.m_List.get(index)).Value; 
/* 265 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int indexOf(String item) {
/* 270 */     int iLen = this.m_List.size();
/* 271 */     for (int i = 0; i < iLen; i++) {
/*     */       
/* 273 */       JLbsStringListItem listItem = this.m_List.get(i);
/* 274 */       if (JLbsStringUtil.areEqual(listItem.Value, item) || (listItem.Value != null && listItem.Value.compareTo(item) == 0))
/* 275 */         return i; 
/*     */     } 
/* 277 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int indexOf(int iTag) {
/* 282 */     int iLen = this.m_List.size();
/* 283 */     for (int i = 0; i < iLen; i++) {
/*     */       
/* 285 */       JLbsStringListItem listItem = this.m_List.get(i);
/* 286 */       if (listItem.Tag == iTag)
/* 287 */         return i; 
/*     */     } 
/* 289 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sortByCaption() {
/* 294 */     Collections.sort(this.m_List, new JLbsStringListCaptionComparer());
/*     */   }
/*     */ 
/*     */   
/*     */   public void sortByCaptionAndTag() {
/* 299 */     Collections.sort(this.m_List, new JLbsStringListCaptionAndTagComparer());
/*     */   }
/*     */ 
/*     */   
/*     */   public void sortByTag() {
/* 304 */     Collections.sort(this.m_List, new JLbsStringListTagComparer());
/*     */   }
/*     */ 
/*     */   
/*     */   public void sortByTagAndCaption() {
/* 309 */     Collections.sort(this.m_List, new JLbsStringListTagAndCaptionComparer());
/*     */   }
/*     */   
/*     */   class JLbsStringListCaptionComparer
/*     */     implements Comparator<JLbsStringListItem>
/*     */   {
/*     */     public int compare(JLbsStringListItem o1, JLbsStringListItem o2) {
/* 316 */       return o1.Value.compareToIgnoreCase(o2.Value);
/*     */     }
/*     */   }
/*     */   
/*     */   class JLbsStringListCaptionAndTagComparer
/*     */     implements Comparator<JLbsStringListItem>
/*     */   {
/*     */     public int compare(JLbsStringListItem o1, JLbsStringListItem o2) {
/* 324 */       int result = o1.Value.compareToIgnoreCase(o2.Value);
/* 325 */       if (result == 0)
/* 326 */         return o1.Tag - o2.Tag; 
/* 327 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   class JLbsStringListTagComparer
/*     */     implements Comparator<JLbsStringListItem>
/*     */   {
/*     */     public int compare(JLbsStringListItem o1, JLbsStringListItem o2) {
/* 335 */       return o1.Tag - o2.Tag;
/*     */     }
/*     */   }
/*     */   
/*     */   class JLbsStringListTagAndCaptionComparer
/*     */     implements Comparator<JLbsStringListItem>
/*     */   {
/*     */     public int compare(JLbsStringListItem o1, JLbsStringListItem o2) {
/* 343 */       int result = o1.Tag - o2.Tag;
/* 344 */       if (result == 0)
/* 345 */         return o1.Value.compareToIgnoreCase(o2.Value); 
/* 346 */       return result;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] toArray() {
/* 352 */     String[] result = new String[this.m_List.size()];
/* 353 */     for (int i = 0; i < this.m_List.size(); i++)
/* 354 */       result[i] = ((JLbsStringListItem)this.m_List.get(i)).Value; 
/* 355 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<Integer, String> toHashMap() {
/* 360 */     HashMap<Integer, String> result = new HashMap<>();
/* 361 */     for (int i = 0; i < this.m_List.size(); i++)
/* 362 */       result.put(Integer.valueOf(((JLbsStringListItem)this.m_List.get(i)).Tag), ((JLbsStringListItem)this.m_List.get(i)).Value); 
/* 363 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 369 */     StringBuilder result = new StringBuilder("[");
/* 370 */     if (this.m_List != null) {
/*     */       
/* 372 */       int iSize = this.m_List.size();
/* 373 */       if (iSize > 0) {
/*     */         
/* 375 */         result.append(((JLbsStringListItem)this.m_List.get(0)).toString());
/* 376 */         for (int i = 1; i < iSize; i++)
/* 377 */           result.append("," + ((JLbsStringListItem)this.m_List.get(i)).toString()); 
/*     */       } 
/*     */     } 
/* 380 */     result.append("]");
/* 381 */     return result.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toEditString() {
/* 386 */     StringBuilder result = new StringBuilder();
/* 387 */     if (this.m_List != null) {
/*     */       
/* 389 */       int iSize = this.m_List.size();
/* 390 */       if (iSize > 0) {
/*     */         
/* 392 */         result.append(((JLbsStringListItem)this.m_List.get(0)).toEditString());
/* 393 */         for (int i = 1; i < iSize; i++)
/* 394 */           result.append("\n" + ((JLbsStringListItem)this.m_List.get(i)).toEditString()); 
/*     */       } 
/*     */     } 
/* 397 */     return result.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toSingleLine() {
/* 402 */     StringBuilder result = new StringBuilder();
/* 403 */     if (this.m_List != null) {
/*     */       
/* 405 */       int iSize = this.m_List.size();
/* 406 */       if (iSize > 0) {
/*     */         
/* 408 */         result.append(((JLbsStringListItem)this.m_List.get(0)).toEditString());
/* 409 */         for (int i = 1; i < iSize; i++)
/* 410 */           result.append("|" + ((JLbsStringListItem)this.m_List.get(i)).toEditString()); 
/*     */       } 
/*     */     } 
/* 413 */     return result.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toHTMLString() {
/* 418 */     StringBuilder result = new StringBuilder();
/* 419 */     if (this.m_List != null) {
/*     */       
/* 421 */       int iSize = this.m_List.size();
/* 422 */       if (iSize > 0) {
/*     */         
/* 424 */         result.append(((JLbsStringListItem)this.m_List.get(0)).toEditString());
/* 425 */         for (int i = 1; i < iSize; i++)
/* 426 */           result.append("<br>" + ((JLbsStringListItem)this.m_List.get(i)).toEditString()); 
/*     */       } 
/*     */     } 
/* 429 */     return result.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected JLbsStringListItem createItem(JLbsStringListItem item) {
/* 434 */     return new JLbsStringListItem(item);
/*     */   }
/*     */ 
/*     */   
/*     */   protected JLbsStringListItem createItem(String s) {
/* 439 */     return new JLbsStringListItem(s);
/*     */   }
/*     */ 
/*     */   
/*     */   protected JLbsStringListItem createItem(String s, int tag) {
/* 444 */     return new JLbsStringListItem(s, tag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/*     */     JLbsStringList list;
/*     */     try {
/* 453 */       list = (JLbsStringList)super.clone();
/* 454 */       list.m_List = new ArrayList<>();
/* 455 */       list.addAll(this);
/*     */     }
/* 457 */     catch (CloneNotSupportedException e) {
/*     */       
/* 459 */       list = new JLbsStringList(this);
/*     */     } 
/* 461 */     list.setChangeListener(this.m_Listener);
/* 462 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 470 */     return this.m_Description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/* 478 */     this.m_Description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getID() {
/* 486 */     return this.m_ID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setID(int id) {
/* 494 */     this.m_ID = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<JLbsStringListItem> iterator() {
/* 499 */     return this.m_List.iterator();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSameDefinition(JLbsStringList obj) {
/* 504 */     return (obj != null && obj.m_ID == this.m_ID);
/*     */   }
/*     */ 
/*     */   
/*     */   public void calculateDelta(JLbsStringList obj, ArrayList<String> delta, String prefix) {
/* 509 */     JLbsXUIComparator.calculateDelta((ILbsXUIComparable<?>[])this.m_List.<ILbsXUIComparable>toArray((ILbsXUIComparable[])new JLbsStringListItem[0]), (ILbsXUIComparable<?>[])obj.m_List.<ILbsXUIComparable>toArray((ILbsXUIComparable[])new JLbsStringListItem[0]), 
/* 510 */         getDeltaIdentifier(prefix), obj.getDeltaIdentifier(prefix), delta, "StringListItem");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDeltaIdentifier(String prefix) {
/* 515 */     return String.valueOf(prefix) + " --> " + toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsStringList getThis() {
/* 520 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 525 */     return (size() == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void describeXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 530 */     writer.startObject(getClass().getName(), "");
/* 531 */     describePropertiesXML(writer, localizationService);
/* 532 */     writer.endObject();
/*     */   }
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 537 */     writer.startObjectProperty("List", "List(JLbsStringListItem)", null, false);
/* 538 */     writer.writeInnerListObject(JLbsStringListItem.class, localizationService);
/* 539 */     writer.endObjectProperty();
/* 540 */     writer.writeProperty("UpdateCnt", "int", null, false);
/* 541 */     writer.writeProperty("ID", "int", null, false);
/* 542 */     writer.writeProperty("Decsription", "String", null, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getEmptyPropertyNames(IParameterMandatoryListener mandatoryListener) {
/* 552 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier getParameterIdentifier() {
/* 558 */     return Parameter.getParameterIdentifier(getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTag() {
/* 568 */     return this.m_Tag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTag(int m_Tag) {
/* 578 */     this.m_Tag = m_Tag;
/*     */   }
/*     */   
/*     */   public JLbsStringList() {}
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsStringList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */