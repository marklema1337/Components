/*     */ package com.lbs.data.objects;
/*     */ 
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CopyOnWriteArraySet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleBusinessObjects<T extends SimpleBusinessObject>
/*     */   extends BasicBusinessObjects<T>
/*     */   implements Externalizable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @XStreamAlias("Deleted")
/*     */   protected SimpleBusinessObjectList<T> m_Deleted;
/*  27 */   protected transient CopyOnWriteArraySet<BusinessObjectCollectionListener> m_ChangeListeners = new CopyOnWriteArraySet<>(); @XStreamAlias("ChangeHandled")
/*  28 */   protected int m_ChangeHandled = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleBusinessObjects() {
/*  33 */     this.m_Deleted = new SimpleBusinessObjectList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleBusinessObjectList<T> getDeleted() {
/*  38 */     return this.m_Deleted;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canDelete(T obj) {
/*  43 */     return ((obj._getState() == 2 || obj._getState() == 1) && obj
/*  44 */       .getUniqueIdentifier().getSimpleKey() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void delete(T obj) {
/*     */     try {
/*  51 */       this.m_ChangeHandled++;
/*  52 */       int idx = indexOf(obj);
/*     */       
/*  54 */       if (idx == -1) {
/*     */         return;
/*     */       }
/*  57 */       if (canDelete(obj)) {
/*  58 */         this.m_Deleted.add(obj);
/*     */       }
/*  60 */       remove(idx);
/*     */     }
/*     */     finally {
/*     */       
/*  64 */       this.m_ChangeHandled--;
/*  65 */       doChanged("delete", new Object[] { obj });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void delete(int index) {
/*     */     try {
/*  73 */       this.m_ChangeHandled++;
/*  74 */       SimpleBusinessObject simpleBusinessObject = (SimpleBusinessObject)get(index);
/*     */       
/*  76 */       if (canDelete((T)simpleBusinessObject)) {
/*  77 */         this.m_Deleted.add((T)simpleBusinessObject);
/*     */       }
/*  79 */       remove(index);
/*     */     }
/*     */     finally {
/*     */       
/*  83 */       this.m_ChangeHandled--;
/*  84 */       doChanged("delete", new Object[] { Integer.valueOf(index) });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteAll() {
/*  90 */     while (size() > 0) {
/*  91 */       delete(0);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void doChanged(String methodName, Object[] params) {
/*  96 */     if (this.m_ChangeListeners.size() > 0 && this.m_ChangeHandled == 0)
/*     */     {
/*  98 */       for (IBusinessObjectChangeListener changeListener : this.m_ChangeListeners) {
/*     */         
/* 100 */         BusinessObjectEvent e = new BusinessObjectEvent(this, changeListener.getMemberFullPath(), methodName, params);
/* 101 */         changeListener.changed(e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void reverse() {
/*     */     try {
/* 110 */       this.m_ChangeHandled++;
/* 111 */       super.reverse();
/*     */     }
/*     */     finally {
/*     */       
/* 115 */       this.m_ChangeHandled--;
/* 116 */       doChanged("reverse", (Object[])null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(int fromIndex, int count) {
/*     */     try {
/* 124 */       this.m_ChangeHandled++;
/* 125 */       List<T> subList = subList(fromIndex, fromIndex + count);
/* 126 */       for (SimpleBusinessObject simpleBusinessObject : subList)
/*     */       {
/* 128 */         detachChangeListeners((T)simpleBusinessObject);
/*     */       }
/* 130 */       super.remove(fromIndex, count);
/*     */     }
/*     */     finally {
/*     */       
/* 134 */       this.m_ChangeHandled--;
/* 135 */       doChanged("remove", new Object[] { Integer.valueOf(fromIndex), Integer.valueOf(count) });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public CopyOnWriteArraySet<BusinessObjectCollectionListener> getChangeListeners() {
/* 141 */     return this.m_ChangeListeners;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addChangeListener(IBusinessObjectChangeListener changeListener, String memberName) {
/* 146 */     if (changeListener == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 152 */     if (this.m_ChangeListeners.contains(changeListener)) {
/*     */       return;
/*     */     }
/* 155 */     BusinessObjectCollectionListener collChangeListener = new BusinessObjectCollectionListener(changeListener, memberName);
/* 156 */     collChangeListener.setList(this);
/* 157 */     if (!this.m_ChangeListeners.contains(collChangeListener)) {
/*     */       
/* 159 */       this.m_ChangeListeners.add(collChangeListener);
/*     */       
/* 161 */       for (int i = 0; i < size(); i++) {
/*     */         
/* 163 */         SimpleBusinessObject simpleBusinessObject = (SimpleBusinessObject)get(i);
/* 164 */         if (simpleBusinessObject != null)
/*     */         {
/* 166 */           simpleBusinessObject.addChangeListener(new BusinessObjectItemListener(collChangeListener, "item"));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addChangeListener(IBusinessObjectChangeListener changeListener, String memberName, int level, int maxLevel) {
/* 174 */     if (changeListener == null || level > maxLevel) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 180 */     if (this.m_ChangeListeners.contains(changeListener)) {
/*     */       return;
/*     */     }
/* 183 */     BusinessObjectCollectionListener collChangeListener = new BusinessObjectCollectionListener(changeListener, memberName);
/* 184 */     collChangeListener.setList(this);
/* 185 */     if (!this.m_ChangeListeners.contains(collChangeListener)) {
/*     */       
/* 187 */       this.m_ChangeListeners.add(collChangeListener);
/*     */       
/* 189 */       for (int i = 0; i < size(); i++) {
/*     */         
/* 191 */         SimpleBusinessObject simpleBusinessObject = (SimpleBusinessObject)get(i);
/* 192 */         if (simpleBusinessObject != null)
/*     */         {
/* 194 */           simpleBusinessObject.addChangeListener(new BusinessObjectItemListener(collChangeListener, "item"), level, maxLevel);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeChangeListener(IBusinessObjectChangeListener changeListener, String memberName) {
/* 202 */     if (changeListener == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 208 */     if (!removeListener(changeListener)) {
/*     */       
/* 210 */       BusinessObjectCollectionListener collChangeListener = new BusinessObjectCollectionListener(changeListener, memberName);
/*     */       
/* 212 */       collChangeListener.setList(this);
/* 213 */       removeListener(collChangeListener);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean removeListener(IBusinessObjectChangeListener listenerToRemove) {
/* 220 */     if (this.m_ChangeListeners.contains(listenerToRemove)) {
/*     */       
/* 222 */       this.m_ChangeListeners.remove(listenerToRemove);
/*     */       
/* 224 */       for (int i = 0; i < size(); i++) {
/*     */         
/* 226 */         SimpleBusinessObject simpleBusinessObject = (SimpleBusinessObject)get(i);
/* 227 */         if (simpleBusinessObject != null)
/*     */         {
/* 229 */           simpleBusinessObject.removeChangeListener(new BusinessObjectItemListener(listenerToRemove, "item")); } 
/*     */       } 
/* 231 */       return true;
/*     */     } 
/* 233 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeChangeListener(IBusinessObjectChangeListener changeListener, String memberName, int level, int maxLevel) {
/* 238 */     if (changeListener == null || level > maxLevel) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 244 */     if (!removeListener(level, maxLevel, changeListener)) {
/*     */       
/* 246 */       BusinessObjectCollectionListener collChangeListener = new BusinessObjectCollectionListener(changeListener, memberName);
/*     */       
/* 248 */       collChangeListener.setList(this);
/* 249 */       removeListener(level, maxLevel, collChangeListener);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean removeListener(int level, int maxLevel, IBusinessObjectChangeListener listenerToRemove) {
/* 256 */     if (this.m_ChangeListeners.contains(listenerToRemove)) {
/*     */       
/* 258 */       this.m_ChangeListeners.remove(listenerToRemove);
/*     */       
/* 260 */       for (int i = 0; i < size(); i++) {
/*     */         
/* 262 */         SimpleBusinessObject simpleBusinessObject = (SimpleBusinessObject)get(i);
/* 263 */         if (simpleBusinessObject != null)
/*     */         {
/* 265 */           simpleBusinessObject.removeChangeListener(new BusinessObjectItemListener(listenerToRemove, "item"), level, maxLevel); } 
/*     */       } 
/* 267 */       return true;
/*     */     } 
/* 269 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void addInternal(int index, T element) {
/*     */     try {
/* 276 */       this.m_ChangeHandled++;
/* 277 */       add(index, element);
/*     */     }
/*     */     finally {
/*     */       
/* 281 */       this.m_ChangeHandled--;
/* 282 */       doChanged("add", new Object[] { Integer.valueOf(index), element });
/*     */     } 
/*     */     
/* 285 */     attachChangeListeners(element);
/*     */   }
/*     */ 
/*     */   
/*     */   private void attachChangeListeners(T element) {
/* 290 */     for (BusinessObjectCollectionListener listener : this.m_ChangeListeners)
/*     */     {
/* 292 */       element.addChangeListener(new BusinessObjectItemListener(listener, "item"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void detachChangeListeners(T element) {
/* 298 */     if (element != null) {
/* 299 */       for (BusinessObjectCollectionListener listener : this.m_ChangeListeners)
/*     */       {
/* 301 */         element.removeChangeListener(new BusinessObjectItemListener(listener, "item"));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean remove(Object o) {
/*     */     try {
/* 310 */       this.m_ChangeHandled++;
/* 311 */       detachChangeListeners((T)o);
/* 312 */       return super.remove(o);
/*     */     }
/*     */     finally {
/*     */       
/* 316 */       this.m_ChangeHandled--;
/* 317 */       doChanged("remove", new Object[] { o });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeRange(int fromIndex, int toIndex) {
/*     */     try {
/* 325 */       this.m_ChangeHandled++;
/* 326 */       List<T> subList = subList(fromIndex, toIndex);
/* 327 */       for (SimpleBusinessObject simpleBusinessObject : subList)
/*     */       {
/* 329 */         detachChangeListeners((T)simpleBusinessObject);
/*     */       }
/* 331 */       super.removeRange(fromIndex, toIndex);
/*     */     }
/*     */     finally {
/*     */       
/* 335 */       this.m_ChangeHandled--;
/* 336 */       doChanged("removeRange", new Object[] { Integer.valueOf(fromIndex), Integer.valueOf(toIndex) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized boolean addInternal(T o) {
/*     */     try {
/* 344 */       this.m_ChangeHandled++;
/* 345 */       return super.add(o);
/*     */     }
/*     */     finally {
/*     */       
/* 349 */       this.m_ChangeHandled--;
/* 350 */       doChanged("add", new Object[] { o });
/* 351 */       attachChangeListeners(o);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized boolean add(T o) {
/* 357 */     return addInternal(o);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addI(Object o) {
/* 363 */     addInternal((T)o);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addI(int index, Object o) {
/* 369 */     addInternal(index, (T)o);
/* 370 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addElement(T obj) {
/*     */     try {
/* 377 */       this.m_ChangeHandled++;
/* 378 */       super.addElement(obj);
/*     */     }
/*     */     finally {
/*     */       
/* 382 */       this.m_ChangeHandled--;
/* 383 */       doChanged("addElement", new Object[] { obj });
/*     */     } 
/* 385 */     attachChangeListeners(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized T remove(int index) {
/*     */     try {
/* 392 */       this.m_ChangeHandled++;
/* 393 */       SimpleBusinessObject simpleBusinessObject = (SimpleBusinessObject)super.remove(index);
/* 394 */       detachChangeListeners((T)simpleBusinessObject);
/* 395 */       return (T)simpleBusinessObject;
/*     */     }
/*     */     finally {
/*     */       
/* 399 */       this.m_ChangeHandled--;
/* 400 */       doChanged("remove", new Object[] { Integer.valueOf(index) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean removeElement(Object obj) {
/*     */     try {
/* 409 */       this.m_ChangeHandled++;
/* 410 */       detachChangeListeners((T)obj);
/* 411 */       return super.remove(obj);
/*     */     }
/*     */     finally {
/*     */       
/* 415 */       this.m_ChangeHandled--;
/* 416 */       doChanged("removeElement", new Object[] { obj });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void removeElementAt(int index) {
/*     */     try {
/* 424 */       this.m_ChangeHandled++;
/* 425 */       SimpleBusinessObject simpleBusinessObject = (SimpleBusinessObject)get(index);
/* 426 */       detachChangeListeners((T)simpleBusinessObject);
/* 427 */       super.remove(index);
/*     */     }
/*     */     finally {
/*     */       
/* 431 */       this.m_ChangeHandled--;
/* 432 */       doChanged("removeElementAt", new Object[] { Integer.valueOf(index) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized T set(int index, T element) {
/*     */     try {
/* 440 */       this.m_ChangeHandled++;
/* 441 */       SimpleBusinessObject simpleBusinessObject = (SimpleBusinessObject)super.set(index, element);
/* 442 */       detachChangeListeners((T)simpleBusinessObject);
/* 443 */       return (T)simpleBusinessObject;
/*     */     }
/*     */     finally {
/*     */       
/* 447 */       this.m_ChangeHandled--;
/* 448 */       doChanged("set", new Object[] { Integer.valueOf(index), element });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void setElementAt(T obj, int index) {
/*     */     try {
/* 456 */       this.m_ChangeHandled++;
/* 457 */       detachChangeListeners(get(index));
/* 458 */       super.set(index, obj);
/*     */     }
/*     */     finally {
/*     */       
/* 462 */       this.m_ChangeHandled--;
/* 463 */       doChanged("setElementAt", new Object[] { obj, Integer.valueOf(index) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean addAll(Collection<? extends T> c) {
/*     */     try {
/* 471 */       this.m_ChangeHandled++;
/* 472 */       return super.addAll(c);
/*     */     }
/*     */     finally {
/*     */       
/* 476 */       this.m_ChangeHandled--;
/* 477 */       doChanged("addAll", new Object[] { c });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean addAll(int index, Collection<? extends T> c) {
/*     */     try {
/* 485 */       this.m_ChangeHandled++;
/* 486 */       return super.addAll(index, c);
/*     */     }
/*     */     finally {
/*     */       
/* 490 */       this.m_ChangeHandled--;
/* 491 */       doChanged("addAll", new Object[] { Integer.valueOf(index), c });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void insertElementAt(T obj, int index) {
/*     */     try {
/* 499 */       this.m_ChangeHandled++;
/* 500 */       add(index, obj);
/*     */     }
/*     */     finally {
/*     */       
/* 504 */       this.m_ChangeHandled--;
/* 505 */       doChanged("insertElementAt", new Object[] { obj, Integer.valueOf(index) });
/*     */     } 
/* 507 */     attachChangeListeners(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean removeAll(Collection<?> c) {
/*     */     try {
/* 515 */       this.m_ChangeHandled++;
/* 516 */       for (Object item : c)
/*     */       {
/* 518 */         detachChangeListeners((T)item);
/*     */       }
/* 520 */       return super.removeAll(c);
/*     */     }
/*     */     finally {
/*     */       
/* 524 */       this.m_ChangeHandled--;
/* 525 */       doChanged("removeAll", new Object[] { c });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void removeAllElements() {
/*     */     try {
/* 533 */       this.m_ChangeHandled++;
/* 534 */       for (SimpleBusinessObject simpleBusinessObject : this)
/*     */       {
/* 536 */         detachChangeListeners((T)simpleBusinessObject);
/*     */       }
/* 538 */       super.clear();
/*     */     }
/*     */     finally {
/*     */       
/* 542 */       this.m_ChangeHandled--;
/* 543 */       doChanged("removeAllElements", (Object[])null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/*     */     try {
/* 551 */       this.m_ChangeHandled++;
/* 552 */       for (SimpleBusinessObject simpleBusinessObject : this)
/*     */       {
/* 554 */         detachChangeListeners((T)simpleBusinessObject);
/*     */       }
/* 556 */       super.clear();
/*     */     }
/*     */     finally {
/*     */       
/* 560 */       this.m_ChangeHandled--;
/* 561 */       doChanged("clear", (Object[])null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 567 */     super.writeExternal(out);
/* 568 */     this.m_Deleted.writeExternal(out);
/* 569 */     out.writeInt(this.m_ChangeHandled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 574 */     super.readExternal(in);
/* 575 */     this.m_Deleted.readExternal(in);
/* 576 */     this.m_ChangeHandled = in.readInt();
/*     */     
/* 578 */     this.m_ChangeListeners = new CopyOnWriteArraySet<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Object clone() {
/* 585 */     CopyOnWriteArraySet<BusinessObjectCollectionListener> listeners = this.m_ChangeListeners;
/* 586 */     this.m_ChangeListeners = new CopyOnWriteArraySet<>();
/* 587 */     Object clone = super.clone();
/* 588 */     if (clone instanceof SimpleBusinessObjects && listeners != null) {
/*     */       
/* 590 */       SimpleBusinessObjects list = (SimpleBusinessObjects)clone;
/* 591 */       list.m_ChangeListeners.addAll(listeners);
/*     */     } 
/* 593 */     return clone;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\objects\SimpleBusinessObjects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */