/*      */ package com.lbs.controller;
/*      */ 
/*      */ import com.lbs.console.LbsConsole;
/*      */ import com.lbs.controller.dto.LinkVerifierDescription;
/*      */ import com.lbs.controller.dto.Model;
/*      */ import com.lbs.data.factory.FactoryParams;
/*      */ import com.lbs.data.factory.IFactoryAtomicObjectOperations;
/*      */ import com.lbs.data.factory.ObjectFactoryException;
/*      */ import com.lbs.data.objects.BasicBusinessObject;
/*      */ import com.lbs.data.objects.BusinessObject;
/*      */ import com.lbs.data.objects.BusinessObjectEvent;
/*      */ import com.lbs.data.objects.IBusinessObjectChangeListener;
/*      */ import com.lbs.data.objects.ObjectValueManager;
/*      */ import com.lbs.data.objects.SimpleBusinessObject;
/*      */ import com.lbs.data.query.QueryBusinessObject;
/*      */ import com.lbs.data.query.QueryBusinessObjects;
/*      */ import com.lbs.data.query.QueryFactoryException;
/*      */ import com.lbs.gwt.library.shared.facet.PropertyConstraints;
/*      */ import com.lbs.gwt.library.shared.facet.PropertyState;
/*      */ import com.lbs.invoke.SessionReestablishedException;
/*      */ import com.lbs.invoke.SessionTimeoutException;
/*      */ import com.lbs.localization.JLbsResourceId;
/*      */ import com.lbs.localization.LbsMessage;
/*      */ import com.lbs.message.JLbsMessageDialogResult;
/*      */ import com.lbs.platform.interfaces.IApplicationContext;
/*      */ import com.lbs.platform.interfaces.IDataProvider;
/*      */ import com.lbs.platform.interfaces.ILbsContainer;
/*      */ import com.lbs.platform.interfaces.ILbsObjectValidator;
/*      */ import com.lbs.platform.interfaces.ILbsValidationError;
/*      */ import com.lbs.platform.interfaces.ILbsValidationEvent;
/*      */ import com.lbs.platform.interfaces.ILbsValidationResult;
/*      */ import com.lbs.util.Base64;
/*      */ import com.lbs.util.CompareUtil;
/*      */ import com.lbs.util.ConversionDataLoss;
/*      */ import com.lbs.util.ConversionNotSupportedException;
/*      */ import com.lbs.util.JLbsStringList;
/*      */ import com.lbs.util.JLbsStringUtil;
/*      */ import com.lbs.util.StringUtil;
/*      */ import com.lbs.validation.LbsValidationEvent;
/*      */ import java.io.Serializable;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashSet;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.concurrent.CopyOnWriteArraySet;
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class Controller<M extends Model, B extends SimpleBusinessObject>
/*      */   implements IDataProvider
/*      */ {
/*   55 */   private static final LbsConsole ms_Logger = LbsConsole.getLogger(Controller.class);
/*      */ 
/*      */   
/*      */   private static final class ControllerBOChangeListener
/*      */     implements IBusinessObjectChangeListener
/*      */   {
/*      */     private final WeakReference<Controller<?, ?>> m_ControllerRef;
/*      */     private final String m_ListenerName;
/*      */     
/*      */     public ControllerBOChangeListener(String listenerName, Controller<?, ?> controller) {
/*   65 */       this.m_ListenerName = listenerName;
/*   66 */       this.m_ControllerRef = new WeakReference<>(controller);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String getMemberFullPath() {
/*   72 */       return "";
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void changed(BusinessObjectEvent e) {
/*   78 */       Object owner = e.getSource();
/*   79 */       Controller<?, ?> controller = this.m_ControllerRef.get();
/*   80 */       if (controller == null) {
/*      */         
/*   82 */         if (owner instanceof SimpleBusinessObject) {
/*      */           
/*   84 */           SimpleBusinessObject bo = (SimpleBusinessObject)owner;
/*   85 */           bo.removeChangeListener(this, 0, 5);
/*      */         } 
/*      */         return;
/*      */       } 
/*   89 */       if (controller.m_ValueChangeEventsSuspended)
/*      */         return; 
/*   91 */       String memberName = e.getMemberName();
/*   92 */       Object oldValue = e.getOldValue();
/*   93 */       if (!StringUtil.isEmpty(memberName) && (
/*   94 */         e.getValue() instanceof com.lbs.data.objects.SimpleBusinessObjects || e.getValue() instanceof SimpleBusinessObject || controller
/*   95 */         .hasChanged(oldValue, e.getValue()))) {
/*      */         
/*   97 */         String controllerPropName = controller.getControllerPropertyName(memberName);
/*   98 */         if (!StringUtil.isEmpty(controllerPropName))
/*      */         {
/*  100 */           controller.firePropertyValueChange(controllerPropName, oldValue);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public String getListenerName() {
/*  107 */       return this.m_ListenerName;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean equals(Object obj) {
/*  113 */       if (obj instanceof ControllerBOChangeListener) {
/*      */         
/*  115 */         ControllerBOChangeListener listener = (ControllerBOChangeListener)obj;
/*  116 */         Controller<?, ?> controller = this.m_ControllerRef.get();
/*  117 */         Controller<?, ?> otherController = listener.m_ControllerRef.get();
/*  118 */         return (controller == otherController && StringUtil.equals(this.m_ListenerName, listener.m_ListenerName));
/*      */       } 
/*  120 */       return super.equals(obj);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int hashCode() {
/*  127 */       return super.hashCode();
/*      */     }
/*      */   }
/*      */   
/*  131 */   protected M m_Model = null;
/*  132 */   protected B m_BO = null;
/*  133 */   protected Class<? extends M> m_ModelClass = null;
/*      */   
/*  135 */   protected Hashtable<String, PropertyState> m_States = new Hashtable<>();
/*  136 */   protected List<ControllerListener> m_Listeners = new ArrayList<>();
/*  137 */   protected List<ControllerSaveListener> m_SaveListeners = new ArrayList<>();
/*  138 */   protected List<BasicLinkVerifier> m_LinkVerifiers = new ArrayList<>();
/*      */   
/*      */   private IControllerMandatoryProvider m_MandatoryProvider;
/*      */   
/*      */   protected boolean m_ReadOnly = false;
/*      */   
/*      */   private MessageService m_MessageService;
/*      */   
/*      */   protected WeakReference<IFactoryAtomicObjectOperations> m_DBService;
/*      */   
/*      */   protected IApplicationContext m_Context;
/*      */   
/*      */   protected ControllerMode m_Mode;
/*      */   
/*      */   protected boolean m_ValueChangeEventsSuspended = false;
/*      */   
/*      */   private boolean m_StateChangeEventsSuspended = false;
/*      */   
/*      */   protected String m_SetValue;
/*      */   protected boolean m_HasDummyData;
/*      */   private boolean m_RecordChangeLog = false;
/*      */   private ControllerBusinessObjectChangeListener m_ChangeListener;
/*      */   protected ILbsContainer m_Container;
/*  161 */   protected ArrayList<Exception> m_Exceptions = new ArrayList<>();
/*      */   
/*  163 */   protected Hashtable<String, PropertyValueValidationPair> m_PropertyValidationCache = new Hashtable<>();
/*  164 */   protected Hashtable<String, PropertyValueValidationPair> m_PropertyConfirmationCache = new Hashtable<>();
/*      */   
/*      */   protected boolean m_ValidationOnly = false;
/*      */   
/*      */   private ControllerBOChangeListener m_BOChangeListener;
/*      */   
/*      */   protected boolean m_Validating = false;
/*      */ 
/*      */   
/*      */   public Controller(IApplicationContext context, M model, ControllerMode mode) {
/*  174 */     this.m_Context = context;
/*  175 */     this.m_Model = model;
/*  176 */     this.m_ModelClass = (Class)this.m_Model.getClass();
/*  177 */     setMode(mode);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initForModel() {
/*  182 */     transferModelToBO();
/*  183 */     prepareBOListener();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Controller(IApplicationContext context, B bo, Class<? extends M> modelClass, ControllerMode mode) {
/*  189 */     this.m_Context = context;
/*  190 */     this.m_BO = bo;
/*  191 */     this.m_ModelClass = modelClass;
/*  192 */     setMode(mode);
/*  193 */     if (this.m_Model == null)
/*  194 */       this.m_Model = createEmptyModel(); 
/*  195 */     if (this.m_BO instanceof BusinessObject)
/*  196 */       ((BusinessObject)this.m_BO).createLinkedObjects(5); 
/*  197 */     prepareBOListener();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setContainer(ILbsContainer container) {
/*  202 */     this.m_Container = container;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void handleFailedValidation(OperationResult operationResult) throws InvalidDataException {
/*  207 */     if (operationResult == null || operationResult.getResource() == null) {
/*      */       
/*  209 */       InvalidDataException invalidDataException = new InvalidDataException();
/*  210 */       invalidDataException.setSilent(operationResult.isSilent());
/*  211 */       throw invalidDataException;
/*      */     } 
/*      */     
/*  214 */     JLbsResourceId resource = operationResult.getResource();
/*  215 */     InvalidDataException ex = new InvalidDataException(resource.getResourceGroup(), resource.getListId(), resource.getStringTag(), 
/*  216 */         operationResult.getDefaultMessage());
/*  217 */     ex.setSilent(operationResult.isSilent());
/*  218 */     throw ex;
/*      */   }
/*      */ 
/*      */   
/*      */   private void prepareBOListener() {
/*  223 */     if (this.m_BO == null)
/*      */       return; 
/*  225 */     String listenerName = getClass().getName();
/*  226 */     CopyOnWriteArraySet<IBusinessObjectChangeListener> changeListeners = this.m_BO.getChangeListeners();
/*  227 */     if (changeListeners != null) {
/*      */       
/*  229 */       ArrayList<IBusinessObjectChangeListener> listenersToRemove = new ArrayList<>();
/*  230 */       for (IBusinessObjectChangeListener listener : changeListeners) {
/*      */         
/*  232 */         if (listener instanceof ControllerBOChangeListener)
/*      */         {
/*  234 */           if (StringUtil.equals(listenerName, ((ControllerBOChangeListener)listener).getListenerName()))
/*      */           {
/*  236 */             listenersToRemove.add(listener);
/*      */           }
/*      */         }
/*      */       } 
/*  240 */       for (IBusinessObjectChangeListener listener : listenersToRemove)
/*      */       {
/*  242 */         this.m_BO.removeChangeListener(listener, 0, 3);
/*      */       }
/*      */     } 
/*  245 */     this.m_BOChangeListener = new ControllerBOChangeListener(listenerName, this);
/*  246 */     this.m_BO.addChangeListener(this.m_BOChangeListener, 0, 3);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void prepareChildren() {}
/*      */ 
/*      */   
/*      */   private void setMode(ControllerMode mode) {
/*  255 */     this.m_Mode = mode;
/*  256 */     if (this.m_Mode == ControllerMode.View) {
/*  257 */       this.m_ReadOnly = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public ControllerMode getMode() {
/*  262 */     return this.m_Mode;
/*      */   }
/*      */ 
/*      */   
/*      */   public Hashtable<String, PropertyState> getPropertyStates() {
/*  267 */     return this.m_States;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final B transferModelToBO() {
/*  276 */     if (this.m_BO == null)
/*  277 */       this.m_BO = createEmptyBO(); 
/*  278 */     this.m_Exceptions.clear();
/*  279 */     fillBOFromModel();
/*  280 */     return this.m_BO;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public B getBO() {
/*  288 */     return this.m_BO;
/*      */   }
/*      */ 
/*      */   
/*      */   public final M transferBOToModel() {
/*  293 */     if (this.m_Model == null)
/*  294 */       this.m_Model = createEmptyModel(); 
/*  295 */     fillModelFromBO();
/*  296 */     return this.m_Model;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReadOnly(boolean readOnly) {
/*  303 */     this.m_ReadOnly = readOnly;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addListener(ControllerListener listener) {
/*  308 */     if (listener != null) {
/*  309 */       this.m_Listeners.add(listener);
/*      */     }
/*      */   }
/*      */   
/*      */   public void removeListener(ControllerListener listener) {
/*  314 */     if (listener != null) {
/*  315 */       this.m_Listeners.remove(listener);
/*      */     }
/*      */   }
/*      */   
/*      */   public void addSaveListener(ControllerSaveListener listener) {
/*  320 */     if (listener != null) {
/*  321 */       this.m_SaveListeners.add(listener);
/*      */     }
/*      */   }
/*      */   
/*      */   public void removeSaveListener(ControllerSaveListener listener) {
/*  326 */     if (listener != null) {
/*  327 */       this.m_SaveListeners.remove(listener);
/*      */     }
/*      */   }
/*      */   
/*      */   public void addLinkVerifier(BasicLinkVerifier linkVerifier) {
/*  332 */     this.m_LinkVerifiers.add(linkVerifier);
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeLinkVerifier(BasicLinkVerifier linkVerifier) {
/*  337 */     this.m_LinkVerifiers.remove(linkVerifier);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMessageService(MessageService messageService) {
/*  342 */     this.m_MessageService = messageService;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDBService(WeakReference<IFactoryAtomicObjectOperations> dBService) {
/*  347 */     this.m_DBService = dBService;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String buildDisplayExpression(Serializable source, String[] expressionParts) {
/*  352 */     if (expressionParts == null || source == null)
/*  353 */       return ""; 
/*  354 */     StringBuilder sb = new StringBuilder(); byte b; int i; String[] arrayOfString;
/*  355 */     for (i = (arrayOfString = expressionParts).length, b = 0; b < i; ) { String part = arrayOfString[b];
/*      */       
/*  357 */       if (part != null)
/*      */       {
/*  359 */         if (part.charAt(0) == '$') {
/*      */ 
/*      */           
/*      */           try {
/*  363 */             Object value = ObjectValueManager.getMemberValue(source, part.substring(1), true);
/*  364 */             if (value != null) {
/*  365 */               sb.append((String)ObjectValueManager.convertValue(value, String.class, true));
/*      */             }
/*  367 */           } catch (Exception e) {
/*      */             
/*  369 */             LbsConsole.getLogger(getClass()).error(e, e);
/*      */           } 
/*      */         } else {
/*      */           
/*  373 */           sb.append(part);
/*      */         }  }  b++; }
/*      */     
/*  376 */     return sb.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void executeEventless(ControllerRunnable runnable, boolean suspendValueChangeEvents, boolean suspendStateChangeEvents) throws InvalidDataException, PropertyDisabledException, PropertyInvisibleException {
/*  382 */     this.m_ValueChangeEventsSuspended = suspendValueChangeEvents;
/*  383 */     this.m_StateChangeEventsSuspended = suspendStateChangeEvents;
/*      */     
/*      */     try {
/*  386 */       runnable.run();
/*      */     }
/*      */     finally {
/*      */       
/*  390 */       this.m_ValueChangeEventsSuspended = false;
/*  391 */       this.m_StateChangeEventsSuspended = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void disableProperty(String propertyName) {
/*  397 */     changePropertyState(propertyName, this.m_Model.getPropertyUniqueUIId(propertyName), this.m_Model.getPropertyGroupIds(propertyName), 
/*  398 */         PropertyState.disabled);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void enableProperty(String propertyName) {
/*  403 */     changePropertyState(propertyName, this.m_Model.getPropertyUniqueUIId(propertyName), this.m_Model.getPropertyGroupIds(propertyName), 
/*  404 */         PropertyState.enabled);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void disableGroup(int groupId) {
/*  409 */     changeGroupState(groupId, PropertyState.disabled);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void enableGroup(int groupId) {
/*  414 */     changeGroupState(groupId, PropertyState.enabled);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean isDisabled(String propertyName) {
/*  419 */     return (this.m_States.get(propertyName) == PropertyState.disabled);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void hideProperty(String propertyName) {
/*  424 */     changePropertyState(propertyName, this.m_Model.getPropertyUniqueUIId(propertyName), this.m_Model.getPropertyGroupIds(propertyName), 
/*  425 */         PropertyState.invisible);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void showProperty(String propertyName) {
/*  430 */     changePropertyState(propertyName, this.m_Model.getPropertyUniqueUIId(propertyName), this.m_Model.getPropertyGroupIds(propertyName), 
/*  431 */         PropertyState.enabled);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void hideGroup(int groupId) {
/*  436 */     changeGroupState(groupId, PropertyState.invisible);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void showGroup(int groupId) {
/*  441 */     changeGroupState(groupId, PropertyState.enabled);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean isInvisible(String propertyName) {
/*  446 */     return (this.m_States.get(propertyName) == PropertyState.invisible);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void changeGroupState(int groupId, PropertyState newState) {
/*  451 */     fireGroupStateChange(groupId, newState);
/*      */     
/*  453 */     ArrayList<String> properties = this.m_Model.getGroupProperties(groupId);
/*  454 */     if (properties == null) {
/*      */       return;
/*      */     }
/*  457 */     for (String property : properties)
/*      */     {
/*  459 */       changePropertyState(property, this.m_Model.getPropertyUniqueUIId(property), this.m_Model.getPropertyGroupIds(property), newState);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void changePropertyState(String propertyName, PropertyState newState) {
/*  465 */     changePropertyState(propertyName, this.m_Model.getPropertyUniqueUIId(propertyName), this.m_Model.getPropertyGroupIds(propertyName), 
/*  466 */         newState);
/*      */   }
/*      */ 
/*      */   
/*      */   private void changePropertyState(String propertyName, Integer uniqueUIId, ArrayList<Integer> groupIds, PropertyState newState) {
/*  471 */     this.m_States.put(propertyName, newState);
/*  472 */     PropertyConstraints constraint = this.m_Model.getPropertyConstraint(propertyName);
/*  473 */     if (constraint == null) {
/*      */       
/*  475 */       constraint = new PropertyConstraints();
/*  476 */       this.m_Model.setPropertyConstraint(propertyName, constraint);
/*      */     } 
/*  478 */     constraint.setState(newState);
/*  479 */     fireStateChange(propertyName, uniqueUIId, groupIds, newState);
/*      */   }
/*      */ 
/*      */   
/*      */   private void fireGroupStateChange(int groupId, PropertyState newState) {
/*  484 */     if (!this.m_StateChangeEventsSuspended && this.m_Listeners.size() > 0) {
/*      */       
/*  486 */       GroupStateChangeEvent<?> e = new GroupStateChangeEvent(this, Integer.valueOf(groupId), newState);
/*  487 */       for (ControllerListener listener : this.m_Listeners)
/*      */       {
/*  489 */         listener.groupStateChanged(e);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void fireStateChange(String propertyName, Integer uniqueUIId, ArrayList<Integer> groupIds, PropertyState newState) {
/*  496 */     if (!this.m_StateChangeEventsSuspended && this.m_Listeners.size() > 0) {
/*      */       
/*  498 */       PropertyState oldState = this.m_States.get(propertyName);
/*  499 */       if (oldState == null)
/*  500 */         oldState = PropertyState.enabled; 
/*  501 */       PropertyStateChangeEvent<?> e = new PropertyStateChangeEvent(this, propertyName, uniqueUIId, 
/*  502 */           groupIds, newState, oldState);
/*  503 */       for (ControllerListener listener : this.m_Listeners)
/*      */       {
/*  505 */         listener.propertyStateChanged(e);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void fireGroupPropertyValueChange(int groupId) {
/*  512 */     ArrayList<String> properties = this.m_Model.getGroupProperties(groupId);
/*  513 */     if (properties == null) {
/*      */       return;
/*      */     }
/*  516 */     for (String property : properties)
/*      */     {
/*  518 */       firePropertyValueChange(property, null);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void firePropertyValueChange(String propertyName, Object oldValue) {
/*  524 */     if (!this.m_ValueChangeEventsSuspended && this.m_Model != null) {
/*  525 */       firePropertyValueChangeImpl(propertyName, oldValue);
/*      */     }
/*      */   }
/*      */   
/*      */   protected void firePropertyValueChangeImpl(String propertyName, Object oldValue) {
/*  530 */     if (this.m_Listeners.size() > 0) {
/*      */       
/*  532 */       Integer propertyUniqueUIId = this.m_Model.getPropertyUniqueUIId(propertyName);
/*  533 */       ArrayList<Integer> propertyGroupIds = this.m_Model.getPropertyGroupIds(propertyName);
/*  534 */       if (propertyUniqueUIId == null)
/*  535 */         propertyUniqueUIId = Integer.valueOf(2147483647); 
/*  536 */       PropertyValueChangeEvent<?> e = new PropertyValueChangeEvent(this, propertyName, propertyUniqueUIId.intValue(), 
/*  537 */           propertyGroupIds, oldValue);
/*  538 */       for (ControllerListener listener : this.m_Listeners)
/*      */       {
/*  540 */         listener.propertyValueChanged(e);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isPropertyVisible(String propertyName) {
/*      */     try {
/*  549 */       assertSetProperty(propertyName);
/*      */     }
/*  551 */     catch (PropertyDisabledException propertyDisabledException) {
/*      */ 
/*      */     
/*  554 */     } catch (PropertyInvisibleException e) {
/*      */       
/*  556 */       return false;
/*      */     } 
/*  558 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isPropertyEditable(String propertyName) {
/*      */     try {
/*  565 */       assertSetProperty(propertyName);
/*      */     }
/*  567 */     catch (PropertyDisabledException e) {
/*      */       
/*  569 */       return false;
/*      */     }
/*  571 */     catch (PropertyInvisibleException e) {
/*      */       
/*  573 */       return false;
/*      */     } 
/*  575 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void assertSetProperty(String propertyName) throws PropertyDisabledException, PropertyInvisibleException {
/*  580 */     if (this.m_ReadOnly)
/*  581 */       throw new PropertyDisabledException(); 
/*  582 */     if (isDisabled(propertyName))
/*  583 */       throw new PropertyDisabledException(); 
/*  584 */     if (isInvisible(propertyName)) {
/*  585 */       throw new PropertyInvisibleException();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void checkValue(Object value, Class<?> dataType, String[] includedValues, String[] excludedValues) throws InvalidDataException {
/*  591 */     if (value == null)
/*      */       return; 
/*  593 */     if (excludedValues != null) {
/*      */       byte b; int i; String[] arrayOfString;
/*  595 */       for (i = (arrayOfString = excludedValues).length, b = 0; b < i; ) { String excludedValue = arrayOfString[b];
/*      */         
/*  597 */         Object excValue = ObjectValueManager.convertValue(excludedValue, dataType, true);
/*  598 */         if (value.equals(excValue))
/*  599 */           throw prepareInvalidDataException(value);  b++; }
/*      */     
/*      */     } 
/*  602 */     if (includedValues != null && includedValues.length > 0) {
/*      */       
/*  604 */       boolean found = false; byte b; int i; String[] arrayOfString;
/*  605 */       for (i = (arrayOfString = includedValues).length, b = 0; b < i; ) { String includedValue = arrayOfString[b];
/*      */         
/*  607 */         Object incValue = ObjectValueManager.convertValue(includedValue, dataType, true);
/*  608 */         if (value.equals(incValue)) {
/*      */           
/*  610 */           found = true; break;
/*      */         } 
/*      */         b++; }
/*      */       
/*  614 */       if (!found)
/*      */       {
/*  616 */         throw prepareInvalidDataException(value);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private InvalidDataException prepareInvalidDataException(Object value) {
/*  623 */     return new InvalidDataException(90092, 1, new LbsMessage("Value '~1' cannot be set to this property", 
/*  624 */           new String[] { String.valueOf(value) }));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkResourceListValue(int value, int[] includedValues, int[] excludedValues, int listId, String resourceGroup) throws InvalidDataException {
/*  630 */     if (excludedValues != null)
/*      */     {
/*  632 */       for (int i = 0; i < excludedValues.length; i++) {
/*      */         
/*  634 */         if (value == excludedValues[i])
/*  635 */           throw prepareInvalidDataException(Integer.valueOf(value)); 
/*      */       } 
/*      */     }
/*  638 */     boolean found = false;
/*  639 */     if (includedValues != null && includedValues.length > 0) {
/*      */       byte b; int i; int[] arrayOfInt;
/*  641 */       for (i = (arrayOfInt = includedValues).length, b = 0; b < i; ) { int includedValue = arrayOfInt[b];
/*      */         
/*  643 */         if (value == includedValue) {
/*      */           
/*  645 */           found = true; break;
/*      */         } 
/*      */         b++; }
/*      */     
/*      */     } 
/*  650 */     if (!found) {
/*      */       
/*  652 */       JLbsStringList list = this.m_Context.getLocalizationService().getList(listId, resourceGroup);
/*  653 */       if (list != null) {
/*      */         
/*  655 */         for (int i = 0; i < list.size(); i++) {
/*      */           
/*  657 */           if (value == list.getTagAt(i)) {
/*      */             
/*  659 */             found = true;
/*      */             break;
/*      */           } 
/*      */         } 
/*  663 */         if (!found)
/*      */         {
/*  665 */           throw prepareInvalidDataException(Integer.valueOf(value));
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void childPropertyValueChanged(ChildController<?, ?, ?> childController, String propertyName, ArrayList<Integer> groupIds, Object oldValue) {}
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean hasChanged(Object value1, Object value2) {
/*  678 */     return !CompareUtil.areEqual(value1, value2);
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getInputValueFor(String inputName) {
/*  683 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   public IApplicationContext getContext() {
/*  688 */     return this.m_Context;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPropertyUniqueUIId(String propertyName) {
/*  693 */     if (propertyName == null)
/*  694 */       return 0; 
/*  695 */     Integer value = this.m_Model.getPropertyUniqueUIId(propertyName);
/*  696 */     if (value == null)
/*  697 */       return 0; 
/*  698 */     return value.intValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public String getPropertyNameByUniqueUIId(int uniqueUIId) {
/*  703 */     return this.m_Model.getPropertyNameByUniqueUIId(uniqueUIId);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean beforeChildAdd(ChildController<?, ?, ?> child) {
/*  708 */     ChildAddEvent e = new ChildAddEvent(child);
/*  709 */     for (ControllerListener listener : this.m_Listeners) {
/*      */       
/*  711 */       listener.beforeChildAdd(e);
/*  712 */       if (e.isConsumed())
/*  713 */         return false; 
/*      */     } 
/*  715 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkMandatoryProperties(boolean singleException) throws PropertyMandatoryException {}
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean persist(FactoryParams params) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException, PropertyMandatoryException {
/*  726 */     if (this.m_Exceptions.size() > 0)
/*  727 */       return false; 
/*  728 */     if (params == null)
/*  729 */       params = new FactoryParams(); 
/*  730 */     if (!StringUtil.isEmpty(this.m_SetValue))
/*  731 */       params.getVariables().put("set", this.m_SetValue); 
/*  732 */     checkMandatoryProperties(true);
/*  733 */     beforePersist(params);
/*  734 */     if (this.m_BO instanceof ILbsObjectValidator) {
/*      */       
/*  736 */       LbsValidationEvent event = new LbsValidationEvent();
/*  737 */       event.setContext(this.m_Context);
/*  738 */       event.setData(this.m_BO);
/*  739 */       event.setMode(this.m_Mode.getXUIMode());
/*  740 */       event.setContainer(this.m_Container);
/*  741 */       ILbsValidationResult result = validateBeforePersist(event);
/*  742 */       if (!result.canContinue()) {
/*      */         
/*  744 */         processValidationErrors(result);
/*  745 */         return false;
/*      */       } 
/*      */     } 
/*  748 */     boolean success = false;
/*      */     
/*      */     try {
/*  751 */       success = doPersist(params);
/*      */     }
/*  753 */     catch (ObjectFactoryException e) {
/*      */       
/*  755 */       onSaveException(e);
/*      */     } 
/*  757 */     return success;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean doPersist(FactoryParams params) throws ObjectFactoryException, SessionTimeoutException, SessionReestablishedException {
/*      */     boolean success;
/*  764 */     IFactoryAtomicObjectOperations dbService = null;
/*  765 */     if (this.m_DBService != null)
/*      */     {
/*  767 */       dbService = this.m_DBService.get();
/*      */     }
/*  769 */     if (dbService != null) {
/*  770 */       success = dbService.persist((BasicBusinessObject)this.m_BO, params);
/*      */     } else {
/*  772 */       success = this.m_Context.getObjectFactory().persist((BasicBusinessObject)this.m_BO, params);
/*  773 */     }  return success;
/*      */   }
/*      */ 
/*      */   
/*      */   protected ILbsValidationResult validateBeforePersist(LbsValidationEvent event) {
/*  778 */     ILbsValidationResult result = ((ILbsObjectValidator)this.m_BO).validate((ILbsValidationEvent)event);
/*  779 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void processValidationErrors(ILbsValidationResult result) {
/*  784 */     for (ILbsValidationError error : result) {
/*      */       
/*  786 */       for (ControllerSaveListener listener : this.m_SaveListeners)
/*      */       {
/*  788 */         listener.onValidateError(this, error);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void beforePersist(FactoryParams params) {
/*  795 */     for (ControllerSaveListener listener : this.m_SaveListeners)
/*      */     {
/*  797 */       listener.beforeSave(this, params);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void onSaveException(ObjectFactoryException e) throws ObjectFactoryException {
/*  803 */     for (ControllerSaveListener listener : this.m_SaveListeners)
/*      */     {
/*  805 */       listener.onSaveException(this, (Exception)e);
/*      */     }
/*  807 */     throw e;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSetValue(String setValue) {
/*  812 */     this.m_SetValue = setValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getSetValue() {
/*  817 */     return this.m_SetValue;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean isEmptyValue(Object value) {
/*  822 */     return CompareUtil.isValueEmpty(value, true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setBOProperty(String propertyName, Object value) {
/*      */     try {
/*  829 */       ObjectValueManager.setMemberValue(this.m_BO, propertyName, value, true);
/*      */     }
/*  831 */     catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setModelPropertyFromBO(String modelPropertyName, String boPropertyName) {
/*      */     try {
/*  840 */       ObjectValueManager.setMemberValue(this.m_Model, modelPropertyName, ObjectValueManager.getMemberValue(this.m_BO, boPropertyName), 
/*  841 */           true);
/*      */     }
/*  843 */     catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setBOProperty(String propertyName, Serializable lookupResult, String lookupProperty, String alternateLookupProperty) {
/*      */     try {
/*  853 */       setLangProperty(lookupResult);
/*      */       
/*  855 */       ObjectValueManager.setMemberValue(this.m_BO, propertyName, ObjectValueManager.getMemberValue(lookupResult, lookupProperty), 
/*  856 */           true);
/*  857 */       if (!StringUtil.isEmpty(alternateLookupProperty)) {
/*      */         
/*      */         try {
/*  860 */           ObjectValueManager.setMemberValue(this.m_BO, propertyName, 
/*  861 */               ObjectValueManager.getMemberValue(lookupResult, alternateLookupProperty, true), true);
/*      */         }
/*  863 */         catch (Exception exception) {}
/*      */       
/*      */       }
/*      */     }
/*  867 */     catch (Exception e) {
/*      */       
/*  869 */       if (!StringUtil.isEmpty(alternateLookupProperty)) {
/*      */         
/*      */         try {
/*  872 */           ObjectValueManager.setMemberValue(this.m_BO, propertyName, 
/*  873 */               ObjectValueManager.getMemberValue(lookupResult, alternateLookupProperty, true), true);
/*      */         }
/*  875 */         catch (Exception exception) {}
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setLangProperty(Serializable lookupResult) {
/*      */     try {
/*  885 */       if (lookupResult instanceof QueryBusinessObject)
/*      */       {
/*  887 */         String lang = (String)this.m_BO.getProperties().get("!_éLOGITEM_BO_LANGUAGEé_!");
/*  888 */         ((QueryBusinessObject)lookupResult).getProperties().setValue("!_éLOGITEM_BO_LANGUAGEé_!", lang);
/*      */       }
/*      */     
/*  891 */     } catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IControllerMandatoryProvider getMandatoryProvider() {
/*  898 */     return this.m_MandatoryProvider;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMandatoryProvider(IControllerMandatoryProvider mandatoryProvider) {
/*  904 */     this.m_MandatoryProvider = mandatoryProvider;
/*      */     
/*  906 */     if (mandatoryProvider != null) {
/*      */       
/*  908 */       HashSet<String> mandatoryPropertySet = mandatoryProvider.getMandatoryPropertySet();
/*  909 */       Iterator<String> mandatoryPropertyIterator = mandatoryPropertySet.iterator();
/*  910 */       while (mandatoryPropertyIterator.hasNext()) {
/*      */         
/*  912 */         String mandatoryPropertyName = mandatoryPropertyIterator.next();
/*  913 */         this.m_States.put(mandatoryPropertyName, PropertyState.mandatory);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected OperationResult validate(String linkVerifierName, String propertyToValidate, Object valueToValidate) throws InvalidDataException {
/*  921 */     OperationResult result = null;
/*  922 */     for (int i = this.m_LinkVerifiers.size() - 1; i >= 0; i--) {
/*      */       
/*  924 */       result = ((BasicLinkVerifier)this.m_LinkVerifiers.get(i)).validate(linkVerifierName, propertyToValidate, valueToValidate);
/*  925 */       if (result != null)
/*  926 */         return result; 
/*      */     } 
/*  928 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public LinkVerifierDescription getLinkVerifierDescription(String modelPropertyName) {
/*  933 */     return this.m_Model.getLinkVerifierDescription(modelPropertyName);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHasDummyData(boolean hasDummyData) {
/*  938 */     this.m_HasDummyData = hasDummyData;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHasDummyData() {
/*  943 */     return this.m_HasDummyData;
/*      */   }
/*      */ 
/*      */   
/*      */   public synchronized void startRecordingChangeLog() {
/*  948 */     if (!this.m_RecordChangeLog) {
/*      */       
/*  950 */       this.m_ChangeListener = new ControllerBusinessObjectChangeListener();
/*  951 */       this.m_BO.addChangeListener(this.m_ChangeListener);
/*  952 */       this.m_RecordChangeLog = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public synchronized void rollbackChangeLog() {
/*  958 */     if (this.m_RecordChangeLog) {
/*      */       
/*      */       try {
/*      */         
/*  962 */         Hashtable<String, Object> changeLog = this.m_ChangeListener.getChangeLog();
/*  963 */         Enumeration<String> keys = changeLog.keys();
/*  964 */         while (keys.hasMoreElements()) {
/*      */           
/*  966 */           String key = keys.nextElement();
/*      */           
/*      */           try {
/*  969 */             Object oldValue = changeLog.get(key);
/*  970 */             if (oldValue instanceof ControllerBusinessObjectChangeListener.NullValue)
/*  971 */               oldValue = null; 
/*  972 */             ObjectValueManager.setMemberValue(this.m_BO, key, oldValue, true);
/*      */           }
/*  974 */           catch (Exception e) {
/*      */             
/*  976 */             ms_Logger.error(e, e);
/*      */           }
/*      */         
/*      */         } 
/*      */       } finally {
/*      */         
/*  982 */         finalizeChangeLog();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void finalizeChangeLog() {
/*  989 */     this.m_BO.removeChangeListener(this.m_ChangeListener);
/*  990 */     this.m_ChangeListener.getChangeLog().clear();
/*  991 */     this.m_ChangeListener = null;
/*  992 */     this.m_RecordChangeLog = false;
/*      */   }
/*      */ 
/*      */   
/*      */   public synchronized void commitChangeLog() {
/*  997 */     if (this.m_RecordChangeLog)
/*      */     {
/*  999 */       finalizeChangeLog();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public String getBusinessObjectPropertyName(String controllerPropertyName) {
/* 1005 */     return controllerPropertyName;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getControllerPropertyName(String boPropertyName) {
/* 1010 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPropertyMaxLength(String controllerPropertyName) {
/* 1015 */     if (this.m_BO instanceof BusinessObject) {
/*      */       
/* 1017 */       String boPropertyName = getBusinessObjectPropertyName(controllerPropertyName);
/* 1018 */       if (!StringUtil.isEmpty(boPropertyName)) {
/*      */         
/* 1020 */         int idx = boPropertyName.lastIndexOf('.');
/* 1021 */         if (idx > 0 && idx < boPropertyName.length() - 1) {
/*      */           
/* 1023 */           String linkProp = boPropertyName.substring(0, idx);
/*      */           
/*      */           try {
/* 1026 */             Object value = ObjectValueManager.getMemberValue(this.m_BO, linkProp, true);
/* 1027 */             if (value instanceof BusinessObject)
/*      */             {
/* 1029 */               return ((BusinessObject)value).getPropertySize(boPropertyName.substring(idx + 1));
/*      */             }
/*      */           }
/* 1032 */           catch (Exception e) {
/*      */             
/* 1034 */             ms_Logger.error(e, e);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 1039 */           return ((BusinessObject)this.m_BO).getPropertySize(boPropertyName);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1043 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getModelName() {
/* 1048 */     return StringUtil.getNameFromQualified(this.m_ModelClass.getName());
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addException(Exception e) {
/* 1053 */     this.m_Exceptions.add(e);
/*      */   }
/*      */ 
/*      */   
/*      */   public ArrayList<Exception> getExceptions() {
/* 1058 */     return this.m_Exceptions;
/*      */   }
/*      */ 
/*      */   
/*      */   public void checkValidationExceptions() {
/* 1063 */     if (this.m_Exceptions.size() > 0) {
/* 1064 */       throw new ControllerRuntimeException(this.m_Exceptions);
/*      */     }
/*      */   }
/*      */   
/*      */   public void applyModelChanges(M model) throws ControllerRuntimeException {
/* 1069 */     this.m_Model = model;
/* 1070 */     transferModelToBO();
/*      */   }
/*      */ 
/*      */   
/*      */   public void applyModel(Model model) throws ControllerRuntimeException {
/* 1075 */     applyModelChanges((M)model);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPrimaryKey() {
/* 1080 */     return this.m_Model.getPrimaryKey();
/*      */   }
/*      */ 
/*      */   
/*      */   protected int findChild(List<? extends Controller<?, ?>> childList, int primaryKey) {
/* 1085 */     for (int i = 0; i < childList.size(); i++) {
/*      */       
/* 1087 */       if (((Controller)childList.get(i)).getPrimaryKey() == primaryKey)
/* 1088 */         return i; 
/*      */     } 
/* 1090 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   protected MessageService getMessageService() {
/* 1095 */     if (this.m_MessageService == null)
/* 1096 */       this.m_MessageService = new ControllerMessageService(); 
/* 1097 */     return this.m_MessageService;
/*      */   }
/*      */ 
/*      */   
/*      */   public JLbsMessageDialogResult handleMessage(IApplicationContext context, String messageConstantId, String defaultMessage) {
/* 1102 */     return getMessageService().handleMessage(context, messageConstantId, defaultMessage);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public JLbsMessageDialogResult handleMessage(IApplicationContext context, String messageConstantId, String defaultMessage, String[] messageSubstitutions) {
/* 1108 */     return getMessageService().handleMessage(context, messageConstantId, defaultMessage, messageSubstitutions);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean confirmed(IApplicationContext context, String messageConstantId, String defaultMessage) {
/* 1113 */     return getMessageService().confirmed(context, messageConstantId, defaultMessage);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean confirmed(IApplicationContext context, String messageConstantId, String defaultMessage, String[] messageSubstitutions) {
/* 1119 */     return getMessageService().confirmed(context, messageConstantId, defaultMessage, messageSubstitutions);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addValidationMessage(ValidationMessage message) {
/* 1124 */     getMessageService().addValidationMessage(message);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void putPropertyValidationResult(String propertyName, Object value, OperationResult result) {
/* 1129 */     putOpResult(this.m_PropertyValidationCache, propertyName, value, result);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void putOpResult(Hashtable<String, PropertyValueValidationPair> map, String propertyName, Object value, OperationResult result) {
/* 1135 */     map.put(propertyName, new PropertyValueValidationPair(value, result));
/*      */   }
/*      */ 
/*      */   
/*      */   protected OperationResult getPropertyValidationResult(String propertyName, Object value) {
/* 1140 */     return getOpResult(this.m_PropertyValidationCache, propertyName, value);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void clearPropertyValidationResult(String propertyName) {
/* 1145 */     this.m_PropertyValidationCache.remove(propertyName);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void putPropertyConfirmationResult(String propertyName, Object value, OperationResult result) {
/* 1150 */     putOpResult(this.m_PropertyConfirmationCache, propertyName, value, result);
/*      */   }
/*      */ 
/*      */   
/*      */   protected OperationResult getPropertyConfirmationResult(String propertyName, Object value) {
/* 1155 */     return getOpResult(this.m_PropertyConfirmationCache, propertyName, value);
/*      */   }
/*      */ 
/*      */   
/*      */   private OperationResult getOpResult(Hashtable<String, PropertyValueValidationPair> map, String propertyName, Object value) {
/* 1160 */     PropertyValueValidationPair pair = map.get(propertyName);
/* 1161 */     if (pair == null)
/* 1162 */       return null; 
/* 1163 */     if (areValuesEqual(value, pair))
/* 1164 */       return pair.getResult(); 
/* 1165 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void clearPropertyConfirmationResult(String propertyName) {
/* 1170 */     this.m_PropertyConfirmationCache.remove(propertyName);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean areValuesEqual(Object value, PropertyValueValidationPair pair) {
/* 1175 */     if (pair.getValue() == null)
/* 1176 */       return (value == null); 
/* 1177 */     return CompareUtil.areEqual(pair.getValue(), value);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void verifyPropertyValue(PropertyIdentifier property, Object propertyValue) throws PropertyDisabledException, PropertyInvisibleException, InvalidDataException, PropertyNotAvailableException {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void finalizeController() {
/* 1187 */     if (this.m_BO != null && this.m_BOChangeListener != null)
/* 1188 */       this.m_BO.removeChangeListener(this.m_BOChangeListener, 0, 3); 
/* 1189 */     this.m_BO = null;
/* 1190 */     this.m_Model = null;
/* 1191 */     this.m_BOChangeListener = null;
/* 1192 */     this.m_Container = null;
/* 1193 */     this.m_Context = null;
/* 1194 */     this.m_Listeners.clear();
/* 1195 */     this.m_States.clear();
/* 1196 */     this.m_SaveListeners.clear();
/* 1197 */     this.m_LinkVerifiers.clear();
/* 1198 */     this.m_MandatoryProvider = null;
/* 1199 */     this.m_MessageService = null;
/* 1200 */     this.m_Exceptions.clear();
/* 1201 */     this.m_PropertyConfirmationCache.clear();
/* 1202 */     this.m_PropertyValidationCache.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void finalize() throws Throwable {
/* 1208 */     super.finalize();
/* 1209 */     finalizeController();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public QueryBusinessObjects listLinkRecords(String modelPropertyName, String searchValue, int maxRowCount, String[] propsToReturn) throws QueryFactoryException, SessionReestablishedException, SessionTimeoutException, Exception {
/* 1215 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public QueryBusinessObjects listLinkRecords(String modelPropertyName, Object propValue, int operator) throws QueryFactoryException, SessionReestablishedException, SessionTimeoutException, Exception {
/* 1222 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setPropConstraint(String propertyName, PropertyState state, int maxLength) {
/* 1227 */     if (this.m_Model != null) {
/*      */       
/* 1229 */       PropertyConstraints constraint = this.m_Model.getPropertyConstraint(propertyName);
/* 1230 */       if (constraint == null) {
/*      */         
/* 1232 */         constraint = new PropertyConstraints();
/* 1233 */         this.m_Model.setPropertyConstraint(propertyName, constraint);
/*      */       } 
/* 1235 */       constraint.setState(state);
/* 1236 */       constraint.setMaxLength(maxLength);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected QueryBusinessObjects prepareListResults(QueryBusinessObjects items, String[] propsToReturn, String[] fromList, String[] toList, String displayPropName, String[] displayExpression) throws QueryFactoryException, SessionReestablishedException, SessionTimeoutException, Exception {
/* 1244 */     if (items == null) {
/* 1245 */       return null;
/*      */     }
/* 1247 */     QueryBusinessObjects result = new QueryBusinessObjects();
/*      */     
/* 1249 */     for (QueryBusinessObject item : items) {
/*      */       
/* 1251 */       QueryBusinessObject resultItem = new QueryBusinessObject();
/* 1252 */       if (fromList != null && toList != null && fromList.length == toList.length)
/*      */       {
/* 1254 */         for (int i = 0; i < toList.length; i++) {
/*      */           
/* 1256 */           String from = fromList[i];
/* 1257 */           String to = toList[i];
/*      */           
/*      */           try {
/* 1260 */             Object value = item.getProperties().get(from);
/* 1261 */             resultItem.getProperties().set(to, value);
/*      */           }
/* 1263 */           catch (ConversionNotSupportedException e) {
/*      */             
/* 1265 */             ms_Logger.error(e, (Throwable)e);
/*      */           }
/* 1267 */           catch (ConversionDataLoss e) {
/*      */             
/* 1269 */             ms_Logger.error(e, (Throwable)e);
/*      */           } 
/*      */         } 
/*      */       }
/* 1273 */       if (propsToReturn != null) {
/*      */         byte b; int i; String[] arrayOfString;
/* 1275 */         for (i = (arrayOfString = propsToReturn).length, b = 0; b < i; ) { String prop = arrayOfString[b];
/*      */           
/* 1277 */           if (!resultItem.getProperties().containsProperty(prop))
/*      */             
/*      */             try {
/*      */               
/* 1281 */               resultItem.getProperties().set(prop, item.getProperties().get(prop));
/*      */             }
/* 1283 */             catch (ConversionNotSupportedException e) {
/*      */               
/* 1285 */               ms_Logger.error(e, (Throwable)e);
/*      */             }
/* 1287 */             catch (ConversionDataLoss e) {
/*      */               
/* 1289 */               ms_Logger.error(e, (Throwable)e);
/*      */             }  
/*      */           b++; }
/*      */       
/*      */       } 
/* 1294 */       if (!StringUtil.isEmpty(displayPropName) && displayExpression != null)
/*      */       {
/* 1296 */         resultItem.getProperties().set(displayPropName, buildDisplayExpression((Serializable)item, displayExpression));
/*      */       }
/* 1298 */       result.add(resultItem);
/*      */     } 
/* 1300 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   protected QueryBusinessObjects processForColumnName(QueryBusinessObjects items, String[] fromList, String[] toList) {
/* 1305 */     if (items == null) {
/* 1306 */       return null;
/*      */     }
/* 1308 */     if (fromList == null || toList == null || fromList.length != toList.length) {
/* 1309 */       return items;
/*      */     }
/* 1311 */     for (QueryBusinessObject item : items) {
/*      */       
/* 1313 */       for (int i = 0; i < toList.length; i++) {
/*      */         
/* 1315 */         String from = fromList[i];
/* 1316 */         String to = toList[i];
/*      */         
/*      */         try {
/* 1319 */           Object value = item.getProperties().get(from);
/* 1320 */           item.getProperties().set(to, value);
/*      */         }
/* 1322 */         catch (ConversionNotSupportedException e) {
/*      */           
/* 1324 */           ms_Logger.error(e, (Throwable)e);
/*      */         }
/* 1326 */         catch (ConversionDataLoss e) {
/*      */           
/* 1328 */           ms_Logger.error(e, (Throwable)e);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1332 */     return items;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String asImageStr(byte[] fileByte, String extension) {
/* 1337 */     String identifier = "data:image/" + extension + ";base64,";
/* 1338 */     String base64Str = Base64.encodeBytes(fileByte);
/* 1339 */     if (JLbsStringUtil.isEmpty(base64Str)) {
/* 1340 */       return null;
/*      */     }
/* 1342 */     return String.valueOf(identifier) + base64Str;
/*      */   }
/*      */   
/*      */   protected abstract M createEmptyModel();
/*      */   
/*      */   protected abstract B createEmptyBO();
/*      */   
/*      */   protected abstract void fillBOFromModel();
/*      */   
/*      */   protected abstract void fillModelFromBO();
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controller\Controller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */