/*      */ package com.lbs.reflection;
/*      */ 
/*      */ import com.lbs.console.LbsConsole;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Member;
/*      */ import java.lang.reflect.Method;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ReflectiveInvocation
/*      */   implements RunnableWithResult
/*      */ {
/*      */   protected Object _target;
/*      */   protected Constructor _constructor;
/*      */   protected Method _method;
/*      */   protected Object[] _arguments;
/*      */   protected Object _result;
/*      */   protected Exception _exception;
/*      */   protected static final int OK = 0;
/*      */   protected static final int NOSUCHMETHOD = 1;
/*      */   protected static final int SECURITY = 2;
/*      */   protected static final int ILLEGALARGUMENT = 3;
/*      */   protected static final int NULLPOINTER = 4;
/*      */   
/*      */   public static boolean verifyMethod(Object target, Method method, Object[] arguments) {
/*  356 */     int exception = 0;
/*  357 */     String message = "";
/*      */ 
/*      */     
/*  360 */     if (method == null) {
/*      */       
/*  362 */       exception = 4;
/*  363 */       message = "Must specify method object";
/*      */     }
/*      */     else {
/*      */       
/*  367 */       int modifiers = method.getModifiers();
/*  368 */       int noArgs = getNumberOfArguments(arguments);
/*      */       
/*  370 */       if (!Modifier.isPublic(modifiers)) {
/*      */         
/*  372 */         exception = 2;
/*  373 */         message = "Supplied method not public";
/*      */       }
/*  375 */       else if (target == null && !Modifier.isStatic(modifiers)) {
/*      */         
/*  377 */         exception = 3;
/*  378 */         message = "Require static method with null target";
/*      */       }
/*  380 */       else if ((method.getParameterTypes()).length != noArgs) {
/*      */         
/*  382 */         exception = 3;
/*  383 */         message = "Wrong number of parameters for method";
/*      */       } 
/*      */     } 
/*      */     
/*  387 */     if (exception != 0) {
/*      */       
/*      */       try {
/*      */         
/*  391 */         throwException(exception, message);
/*      */       }
/*  393 */       catch (NoSuchMethodException noSuchMethodException) {}
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  399 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean verifyConstructor(Constructor constructor, Object[] arguments) {
/*  440 */     int exception = 0;
/*  441 */     String message = "";
/*      */ 
/*      */     
/*  444 */     if (constructor == null) {
/*      */       
/*  446 */       exception = 4;
/*  447 */       message = "Must specify constructor object";
/*      */     }
/*      */     else {
/*      */       
/*  451 */       int modifiers = constructor.getModifiers();
/*  452 */       int noArgs = getNumberOfArguments(arguments);
/*      */       
/*  454 */       if (!Modifier.isPublic(modifiers)) {
/*      */         
/*  456 */         exception = 2;
/*  457 */         message = "Supplied constructor not public";
/*      */       }
/*  459 */       else if ((constructor.getParameterTypes()).length != noArgs) {
/*      */         
/*  461 */         exception = 3;
/*  462 */         message = "Wrong number of parameters for constructor";
/*      */       } 
/*      */     } 
/*  465 */     if (exception != 0) {
/*      */       
/*      */       try {
/*      */         
/*  469 */         throwException(exception, message);
/*      */       }
/*  471 */       catch (NoSuchMethodException noSuchMethodException) {}
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  477 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Method findMethod(Object target, String name, Object[] arguments) throws NoSuchMethodException {
/*  508 */     Method method = null;
/*  509 */     int exception = 0;
/*  510 */     String message = "";
/*  511 */     List matches = new ArrayList();
/*      */ 
/*      */     
/*  514 */     if (target == null || name == null) {
/*      */       
/*  516 */       exception = 4;
/*  517 */       message = "Must specify target object and method name";
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  522 */       int noArgs = getNumberOfArguments(arguments);
/*  523 */       findMatchingMethods(target.getClass(), name, noArgs, matches);
/*  524 */       if (!matches.isEmpty()) {
/*      */ 
/*      */         
/*      */         try {
/*  528 */           method = (Method)findBestMatch(arguments, matches);
/*      */         }
/*  530 */         catch (ClassCastException e) {
/*      */           
/*  532 */           method = null;
/*      */         } 
/*      */         
/*  535 */         if (method == null)
/*      */         {
/*  537 */           exception = 1;
/*  538 */           message = "No best match for public method " + name + " for class " + target.getClass().getName();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  543 */         exception = 1;
/*  544 */         message = "Public method " + name + " not found for class " + target.getClass().getName();
/*      */       } 
/*      */     } 
/*      */     
/*  548 */     if (exception != 0)
/*      */     {
/*  550 */       throwException(exception, message);
/*      */     }
/*  552 */     return method;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Method findStaticMethod(Class cls, String name, Object[] arguments) throws NoSuchMethodException {
/*  584 */     Method method = null;
/*  585 */     String message = "";
/*  586 */     int exception = 0;
/*  587 */     List matches = new ArrayList();
/*      */ 
/*      */     
/*  590 */     if (cls == null) {
/*      */       
/*  592 */       message = "Require target class for static method lookup.";
/*  593 */       exception = 4;
/*      */     }
/*  595 */     else if (name == null) {
/*      */       
/*  597 */       message = "Require method name for static method lookup.";
/*  598 */       exception = 4;
/*      */     }
/*      */     else {
/*      */       
/*  602 */       int noArgs = getNumberOfArguments(arguments);
/*  603 */       findMatchingMethods(cls, name, noArgs, matches);
/*  604 */       removeInstanceMethods(matches);
/*  605 */       if (!matches.isEmpty()) {
/*      */ 
/*      */         
/*      */         try {
/*  609 */           method = (Method)findBestMatch(arguments, matches);
/*      */         }
/*  611 */         catch (ClassCastException e) {
/*      */           
/*  613 */           method = null;
/*      */         } 
/*      */         
/*  616 */         if (method == null)
/*      */         {
/*  618 */           exception = 1;
/*  619 */           message = "No best match for public static method " + name + " for class " + cls.getName();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  624 */         exception = 1;
/*  625 */         message = "Public static method " + name + " not found for class " + cls.getName();
/*      */       } 
/*      */     } 
/*  628 */     if (exception != 0)
/*      */     {
/*  630 */       throwException(exception, message);
/*      */     }
/*  632 */     return method;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Constructor findConstructor(String name, Object[] arguments) throws NoSuchMethodException {
/*  662 */     Constructor constructor = null;
/*  663 */     int exception = 0;
/*  664 */     String message = "";
/*  665 */     List matches = new ArrayList();
/*      */ 
/*      */     
/*  668 */     if (name == null) {
/*      */       
/*  670 */       exception = 4;
/*  671 */       message = "Must specify constructor name";
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  676 */       int noArgs = getNumberOfArguments(arguments);
/*  677 */       findMatchingConstructors(name, noArgs, matches);
/*  678 */       if (!matches.isEmpty()) {
/*      */ 
/*      */         
/*      */         try {
/*  682 */           constructor = (Constructor)findBestMatch(arguments, matches);
/*      */         }
/*  684 */         catch (ClassCastException e) {
/*      */           
/*  686 */           constructor = null;
/*      */         } 
/*      */         
/*  689 */         if (constructor == null)
/*      */         {
/*  691 */           exception = 1;
/*  692 */           message = "No best match for public constructor " + name;
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  697 */         exception = 1;
/*  698 */         message = "Public constructor " + name + " not found";
/*      */       } 
/*      */     } 
/*  701 */     if (exception != 0)
/*      */     {
/*  703 */       throwException(exception, message);
/*      */     }
/*  705 */     return constructor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Constructor findConstructor(Class cls, Object[] arguments) throws NoSuchMethodException {
/*  731 */     Constructor constructor = null;
/*  732 */     int exception = 0;
/*  733 */     String message = "";
/*  734 */     List matches = new ArrayList();
/*      */ 
/*      */     
/*  737 */     if (cls == null) {
/*      */       
/*  739 */       exception = 4;
/*  740 */       message = "Must specify class to be instaniated.";
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  745 */       int noArgs = getNumberOfArguments(arguments);
/*  746 */       findMatchingConstructors(cls, noArgs, matches);
/*  747 */       if (!matches.isEmpty()) {
/*      */ 
/*      */         
/*      */         try {
/*  751 */           constructor = (Constructor)findBestMatch(arguments, matches);
/*      */         }
/*  753 */         catch (ClassCastException e) {
/*      */           
/*  755 */           constructor = null;
/*      */         } 
/*      */         
/*  758 */         if (constructor == null)
/*      */         {
/*  760 */           exception = 1;
/*  761 */           message = "No best match for public constructor " + cls.getName();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  766 */         exception = 1;
/*  767 */         message = "Public constructor for class " + cls.getName() + " not found";
/*      */       } 
/*      */     } 
/*  770 */     if (exception != 0)
/*      */     {
/*  772 */       throwException(exception, message);
/*      */     }
/*  774 */     return constructor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ReflectiveInvocation(Object target, String method, Object[] args) throws NoSuchMethodException {
/* 1096 */     this._target = null;
/*      */ 
/*      */     
/* 1099 */     this._constructor = null;
/*      */ 
/*      */     
/* 1102 */     this._method = null;
/*      */ 
/*      */     
/* 1105 */     this._arguments = null; if (target == null) { this._constructor = findConstructor(method, args); } else { this._method = findMethod(target, method, args); }  this._target = target; this._arguments = args; } public ReflectiveInvocation(String constructor, Object[] args) throws NoSuchMethodException { this._target = null; this._constructor = null; this._method = null; this._arguments = null; this._constructor = findConstructor(constructor, args); this._arguments = args; } public ReflectiveInvocation(Class cls, Object[] args) throws NoSuchMethodException { this._target = null; this._constructor = null; this._method = null; this._arguments = null; this._constructor = findConstructor(cls, args); this._arguments = args; } public ReflectiveInvocation(Class cls, String method, Object[] args) throws NoSuchMethodException { this._target = null; this._constructor = null; this._method = null; this._arguments = null; if (cls != null) { this._method = findStaticMethod(cls, method, args); this._target = null; } else { this._constructor = findConstructor(method, args); }  this._arguments = args; } public ReflectiveInvocation(Object target, Method method, Object[] args) { this._target = null; this._constructor = null; this._method = null; this._arguments = null; if (verifyMethod(target, method, args)) { this._target = target; this._method = method; this._arguments = args; }  } public ReflectiveInvocation(Constructor constructor, Object[] args) { this._target = null; this._constructor = null; this._method = null; this._arguments = null;
/*      */     if (verifyConstructor(constructor, args)) {
/*      */       this._constructor = constructor;
/*      */       this._arguments = args;
/*      */     }  }
/*      */   public void run() { try {
/*      */       if (this._method != null) {
/*      */         this._result = this._method.invoke(this._target, this._arguments);
/*      */       } else if (this._constructor != null) {
/*      */         this._result = this._constructor.newInstance(this._arguments);
/*      */       } 
/*      */     } catch (InvocationTargetException e) {
/*      */       LbsConsole logger = LbsConsole.getLogger("Data.Client.ReflectiveInvocation");
/*      */       logger.error("ReflectiveInvocation failure - Exception " + e.getTargetException().toString(), e.getTargetException());
/*      */       this._exception = e;
/*      */     } catch (Exception e) {
/*      */       LbsConsole logger = LbsConsole.getLogger("Data.Client.ReflectiveInvocation");
/*      */       logger.error("ReflectiveInvocation failure - Exception " + e.getClass().getName(), e);
/*      */       this._exception = e;
/*      */     }  } public Object getResult() {
/*      */     return this._result;
/*      */   } public Exception getException() {
/*      */     return this._exception;
/*      */   } public String toString() {
/*      */     StringBuilder temp = new StringBuilder();
/*      */     if (this._target != null)
/*      */       temp.append(this._target.getClass()); 
/*      */     if (this._constructor != null)
/*      */       temp.append(this._constructor.toString()); 
/*      */     if (this._method != null)
/*      */       temp.append(this._method.toString()); 
/*      */     return temp.toString();
/*      */   } protected static int getNumberOfArguments(Object[] arguments) {
/*      */     int numberArgs;
/* 1139 */     if (arguments == null) {
/*      */       
/* 1141 */       numberArgs = 0;
/*      */     }
/*      */     else {
/*      */       
/* 1145 */       numberArgs = arguments.length;
/*      */     } 
/* 1147 */     return numberArgs;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void throwException(int exceptionNumber, String message) throws SecurityException, NoSuchMethodException, IllegalArgumentException {
/* 1158 */     switch (exceptionNumber) {
/*      */       case 0:
/*      */         return;
/*      */ 
/*      */ 
/*      */       
/*      */       case 1:
/* 1165 */         throw new NoSuchMethodException(message);
/*      */       case 2:
/* 1167 */         throw new SecurityException(message);
/*      */       case 3:
/* 1169 */         throw new IllegalArgumentException(message);
/*      */       case 4:
/* 1171 */         throw new NullPointerException(message);
/*      */     } 
/*      */ 
/*      */     
/* 1175 */     throw new InternalError("Unrecognized exception code.");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void findMatchingMethods(Class targetClass, String name, int noArguments, List<RIInfo> matches) {
/* 1190 */     Method[] methodList = targetClass.getMethods();
/* 1191 */     for (int i = 0; i < methodList.length; i++) {
/*      */       
/* 1193 */       if (Modifier.isPublic(methodList[i].getModifiers()) && methodList[i].getName().equals(name) && noArguments == (methodList[i]
/* 1194 */         .getParameterTypes()).length) {
/*      */ 
/*      */         
/* 1197 */         RIInfo item = new RIInfo(methodList[i], methodList[i].getParameterTypes());
/* 1198 */         matches.add(item);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected static void findMatchingConstructors(String name, int noArguments, List matches) {
/* 1205 */     Class<?> cls = null;
/*      */ 
/*      */     
/*      */     try {
/* 1209 */       cls = Class.forName(name);
/* 1210 */       findMatchingConstructors(cls, noArguments, matches);
/*      */     }
/* 1212 */     catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void findMatchingConstructors(Class targetClass, int noArguments, List<RIInfo> matches) {
/* 1235 */     Constructor[] conList = (Constructor[])targetClass.getConstructors();
/* 1236 */     for (int i = 0; i < conList.length; i++) {
/*      */       
/* 1238 */       if (Modifier.isPublic(conList[i].getModifiers()) && noArguments == (conList[i].getParameterTypes()).length) {
/*      */ 
/*      */         
/* 1241 */         RIInfo item = new RIInfo(conList[i], conList[i].getParameterTypes());
/* 1242 */         matches.add(item);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void removeInstanceMethods(List matches) {
/* 1257 */     boolean notDone = true;
/* 1258 */     while (notDone) {
/*      */       
/* 1260 */       notDone = false;
/* 1261 */       for (Object o : matches) {
/*      */         
/* 1263 */         RIInfo item = (RIInfo)o;
/* 1264 */         if (!Modifier.isStatic(item.getMember().getModifiers())) {
/*      */           
/* 1266 */           matches.remove(item);
/* 1267 */           notDone = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static Member findBestMatch(Object[] arguments, List matches) {
/* 1285 */     int noArgs = getNumberOfArguments(arguments);
/* 1286 */     Class[] argTypes = getArgumentTypes(arguments, noArgs);
/* 1287 */     calculateChecksums(matches, argTypes);
/* 1288 */     return findMinimumChecksum(matches);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void calculateChecksums(List matches, Class[] argTypes) {
/* 1303 */     for (Object o : matches) {
/*      */       
/* 1305 */       RIInfo item = (RIInfo)o;
/* 1306 */       int checksum = calculateChecksum(item.getArgumentTypes(), argTypes);
/* 1307 */       item.setChecksum(checksum);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static Member findMinimumChecksum(List matches) {
/* 1318 */     Member bestMember = null;
/* 1319 */     int bestChecksum = Integer.MAX_VALUE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1325 */     for (Object o : matches) {
/*      */       
/* 1327 */       RIInfo item = (RIInfo)o;
/* 1328 */       int itemChecksum = item.getChecksum();
/* 1329 */       if (itemChecksum >= 0 && itemChecksum < bestChecksum) {
/*      */         
/* 1331 */         bestChecksum = itemChecksum;
/* 1332 */         bestMember = item.getMember();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1337 */     boolean found = false;
/* 1338 */     for (Object o : matches) {
/*      */       
/* 1340 */       RIInfo item = (RIInfo)o;
/* 1341 */       int itemChecksum = item.getChecksum();
/* 1342 */       if (itemChecksum == bestChecksum && !found) {
/*      */         
/* 1344 */         found = true; continue;
/*      */       } 
/* 1346 */       if (itemChecksum == bestChecksum && found) {
/*      */         
/* 1348 */         bestMember = null;
/*      */         break;
/*      */       } 
/*      */     } 
/* 1352 */     return bestMember;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static Class[] getArgumentTypes(Object[] arguments, int noArgs) {
/* 1364 */     Class[] types = new Class[noArgs];
/* 1365 */     for (int i = 0; i < noArgs; i++) {
/*      */       
/* 1367 */       if (arguments[i] == null) {
/*      */         
/* 1369 */         types[i] = null;
/*      */       }
/*      */       else {
/*      */         
/* 1373 */         types[i] = arguments[i].getClass();
/*      */       } 
/*      */     } 
/* 1376 */     return types;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static int calculateChecksum(Class[] parameters, Class[] arguments) {
/* 1398 */     int checksum = 0;
/* 1399 */     int noArgs = getNumberOfArguments((Object[])arguments);
/* 1400 */     for (int i = 0; i < noArgs; i++) {
/*      */ 
/*      */ 
/*      */       
/* 1404 */       if (parameters[i].isPrimitive()) {
/*      */         
/* 1406 */         int status = getPrimitiveChecksum(parameters[i], arguments[i]);
/* 1407 */         if (status == -1) {
/*      */           
/* 1409 */           checksum = -1;
/*      */           break;
/*      */         } 
/* 1412 */         checksum += status;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1424 */       else if (arguments[i] != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1430 */         if (parameters[i].isAssignableFrom(arguments[i])) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1435 */           if (parameters[i].isInterface())
/*      */           {
/* 1437 */             checksum++;
/*      */ 
/*      */           
/*      */           }
/* 1441 */           else if (parameters[i].isArray())
/*      */           {
/*      */             
/* 1444 */             int status = getArrayChecksum(parameters[i], arguments[i]);
/* 1445 */             checksum += status;
/*      */ 
/*      */           
/*      */           }
/*      */           else
/*      */           {
/*      */             
/* 1452 */             checksum += distanceFromClass(arguments[i], parameters[i]);
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 1461 */           checksum = -1;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1466 */     return checksum;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static int getPrimitiveChecksum(Class parameter, Class argument) {
/* 1480 */     int status = -1;
/* 1481 */     Class primitive = convertObjectToPrimitive(argument);
/* 1482 */     if (primitive != null) {
/*      */       
/* 1484 */       status = checkPrimitives(primitive, parameter);
/*      */ 
/*      */ 
/*      */       
/* 1488 */       if (status != -1)
/*      */       {
/* 1490 */         status++;
/*      */       }
/*      */     } 
/* 1493 */     return status;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static int getArrayChecksum(Class parameter, Class argument) {
/* 1512 */     int checksum = -1;
/* 1513 */     Class<?> paramComponent = parameter.getComponentType();
/* 1514 */     Class<?> argComponent = argument.getComponentType();
/* 1515 */     if (paramComponent != null && argComponent != null)
/*      */     {
/* 1517 */       if (paramComponent.isInterface()) {
/*      */         
/* 1519 */         checksum = 1;
/*      */ 
/*      */       
/*      */       }
/* 1523 */       else if (!paramComponent.isPrimitive() && !argComponent.isPrimitive()) {
/*      */         
/* 1525 */         checksum = distanceFromClass(argComponent, paramComponent);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1531 */       else if (paramComponent.isPrimitive() && argComponent.isPrimitive()) {
/*      */         
/* 1533 */         if (paramComponent.equals(argComponent))
/*      */         {
/* 1535 */           checksum = 0;
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1545 */     return checksum;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static int distanceFromClass(Class subclass, Class superclass) {
/* 1558 */     int distance = 0;
/*      */     
/* 1560 */     Class tempClass = subclass;
/* 1561 */     while (tempClass != superclass && tempClass != null) {
/*      */       
/* 1563 */       distance++;
/* 1564 */       tempClass = tempClass.getSuperclass();
/*      */     } 
/* 1566 */     return distance;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static int checkPrimitives(Class from, Class to) {
/* 1579 */     int checkSum = 0;
/*      */     
/* 1581 */     if (from != to)
/*      */     {
/* 1583 */       if (isCompatiblePrimitives(from, to)) {
/*      */         
/* 1585 */         checkSum++;
/*      */       }
/*      */       else {
/*      */         
/* 1589 */         checkSum = -1;
/*      */       } 
/*      */     }
/* 1592 */     return checkSum;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static Class convertObjectToPrimitive(Class object) {
/*      */     Class primitive;
/* 1604 */     if (object == null) {
/*      */       
/* 1606 */       primitive = null;
/*      */     }
/* 1608 */     else if (object.getName().equals("java.lang.Boolean")) {
/*      */       
/* 1610 */       primitive = boolean.class;
/*      */     }
/* 1612 */     else if (object.getName().equals("java.lang.Character")) {
/*      */       
/* 1614 */       primitive = char.class;
/*      */     }
/* 1616 */     else if (object.getName().equals("java.lang.Byte")) {
/*      */       
/* 1618 */       primitive = byte.class;
/*      */     }
/* 1620 */     else if (object.getName().equals("java.lang.Short")) {
/*      */       
/* 1622 */       primitive = short.class;
/*      */     }
/* 1624 */     else if (object.getName().equals("java.lang.Integer")) {
/*      */       
/* 1626 */       primitive = int.class;
/*      */     }
/* 1628 */     else if (object.getName().equals("java.lang.Long")) {
/*      */       
/* 1630 */       primitive = long.class;
/*      */     }
/* 1632 */     else if (object.getName().equals("java.lang.Float")) {
/*      */       
/* 1634 */       primitive = float.class;
/*      */     }
/* 1636 */     else if (object.getName().equals("java.lang.Double")) {
/*      */       
/* 1638 */       primitive = double.class;
/*      */     }
/*      */     else {
/*      */       
/* 1642 */       primitive = null;
/*      */     } 
/* 1644 */     return primitive;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static boolean isCompatiblePrimitives(Class<byte> from, Class<boolean> to) {
/* 1657 */     boolean result = false;
/*      */     
/* 1659 */     if (from.isPrimitive() && to.isPrimitive())
/*      */     {
/*      */ 
/*      */       
/* 1663 */       if (to == from) {
/*      */         
/* 1665 */         result = true;
/*      */ 
/*      */       
/*      */       }
/* 1669 */       else if (to != boolean.class) {
/*      */ 
/*      */ 
/*      */         
/* 1673 */         if (from == byte.class) {
/*      */           
/* 1675 */           result = true;
/*      */ 
/*      */         
/*      */         }
/* 1679 */         else if (from == short.class || from == char.class) {
/*      */           
/* 1681 */           if (to != byte.class)
/*      */           {
/* 1683 */             result = true;
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 1688 */         else if (from == int.class) {
/*      */           
/* 1690 */           if (to != byte.class && to != short.class && to != char.class)
/*      */           {
/* 1692 */             result = true;
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 1697 */         else if (from == long.class) {
/*      */           
/* 1699 */           if (to == float.class || to == double.class)
/*      */           {
/* 1701 */             result = true;
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 1706 */         else if (from == float.class) {
/*      */           
/* 1708 */           if (to == double.class)
/*      */           {
/* 1710 */             result = true;
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 1715 */         else if (from == double.class) {
/*      */         
/*      */         } 
/*      */       } 
/*      */     }
/* 1720 */     return result;
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\reflection\ReflectiveInvocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */