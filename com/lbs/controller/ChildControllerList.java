/*     */ package com.lbs.controller;
/*     */ 
/*     */ import com.lbs.control.interfaces.IRowAddListener;
/*     */ import com.lbs.control.interfaces.IRowOperationsHandler;
/*     */ import com.lbs.control.interfaces.RowAddEvent;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChildControllerList<E extends ChildController<?, ?, ?>>
/*     */   implements List<E>, Serializable, IRowOperationsHandler
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private IControllerWithChildren m_ParentController;
/*     */   private String m_ChildPropertyName;
/*     */   private List<E> m_List;
/*     */   
/*     */   public ChildControllerList(IControllerWithChildren parentController, String childPropertyName, List<E> list) {
/*  25 */     this.m_ParentController = parentController;
/*  26 */     this.m_ChildPropertyName = childPropertyName;
/*  27 */     this.m_List = list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean add(E e) {
/*  33 */     return this.m_List.add(e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(int index, E element) {
/*  39 */     this.m_List.add(index, element);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addAll(Collection<? extends E> c) {
/*  46 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addAll(int index, Collection<? extends E> c) {
/*  52 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/*  58 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<E> iterator() {
/*  64 */     final Iterator<E> iterator = this.m_List.iterator();
/*  65 */     return new Iterator<E>()
/*     */       {
/*     */ 
/*     */         
/*     */         public boolean hasNext()
/*     */         {
/*  71 */           return iterator.hasNext();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public E next() {
/*  77 */           return (E)iterator.next();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void remove() {
/*  83 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<E> listIterator() {
/*  91 */     return listIterator(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<E> listIterator(int index) {
/*  97 */     final ListIterator<E> iterator = this.m_List.listIterator(index);
/*  98 */     return new ListIterator<E>()
/*     */       {
/*     */         
/*     */         public boolean hasNext()
/*     */         {
/* 103 */           return iterator.hasNext();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public E next() {
/* 109 */           return (E)iterator.next();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public boolean hasPrevious() {
/* 115 */           return iterator.hasPrevious();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public E previous() {
/* 121 */           return (E)iterator.previous();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public int nextIndex() {
/* 127 */           return iterator.nextIndex();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public int previousIndex() {
/* 133 */           return iterator.previousIndex();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void remove() {
/* 139 */           throw new UnsupportedOperationException();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void set(E e) {
/* 145 */           throw new UnsupportedOperationException();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void add(E e) {
/* 151 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public E remove(int index) {
/* 159 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean remove(Object o) {
/* 165 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/* 171 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean retainAll(Collection<?> c) {
/* 177 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public E set(int index, E element) {
/* 183 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<E> subList(int fromIndex, int toIndex) {
/* 189 */     return Collections.unmodifiableList(this.m_List.subList(fromIndex, toIndex));
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/* 194 */     return this.m_List.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 199 */     return this.m_List.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Object o) {
/* 204 */     return this.m_List.contains(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/* 209 */     return this.m_List.toArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T[] toArray(Object[] a) {
/* 214 */     return this.m_List.toArray((T[])a);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/* 219 */     return this.m_List.containsAll(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 224 */     return this.m_List.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 231 */     return this.m_List.equals(obj);
/*     */   }
/*     */ 
/*     */   
/*     */   public E get(int index) {
/* 236 */     return this.m_List.get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public int indexOf(Object o) {
/* 241 */     return this.m_List.indexOf(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public int lastIndexOf(Object o) {
/* 246 */     return this.m_List.lastIndexOf(o);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object addRowObject(int idx, IRowAddListener listener) throws Exception {
/* 252 */     ControllerListener controllerListener = new MyControllerListener(listener);
/* 253 */     this.m_ParentController.addListener(controllerListener);
/*     */     
/*     */     try {
/* 256 */       return this.m_ParentController.insertChildController(this.m_ChildPropertyName, idx);
/*     */     }
/*     */     finally {
/*     */       
/* 260 */       this.m_ParentController.removeListener(controllerListener);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object addRowObject(IRowAddListener listener) throws Exception {
/* 267 */     ControllerListener controllerListener = new MyControllerListener(listener);
/* 268 */     this.m_ParentController.addListener(controllerListener);
/*     */     
/*     */     try {
/* 271 */       return this.m_ParentController.insertChildController(this.m_ChildPropertyName);
/*     */     }
/*     */     finally {
/*     */       
/* 275 */       this.m_ParentController.removeListener(controllerListener);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteRowObject(int idx) throws Exception {
/* 282 */     this.m_ParentController.deleteChildController(this.m_ChildPropertyName, idx);
/*     */   }
/*     */ 
/*     */   
/*     */   private static class MyControllerListener
/*     */     implements ControllerListener
/*     */   {
/*     */     private IRowAddListener m_Listener;
/*     */     
/*     */     public MyControllerListener(IRowAddListener listener) {
/* 292 */       this.m_Listener = listener;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void groupStateChanged(GroupStateChangeEvent<?> e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void propertyStateChanged(PropertyStateChangeEvent<?> e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void propertyValueChanged(PropertyValueChangeEvent<?> e) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void beforeChildAdd(ChildAddEvent e) {
/* 313 */       if (this.m_Listener != null) {
/*     */         
/* 315 */         RowAddEvent event = new RowAddEvent(e.getChildToAdd());
/* 316 */         this.m_Listener.beforeRowObjectInsert(event);
/* 317 */         if (event.isConsumed())
/* 318 */           e.consume(); 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\ChildControllerList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */