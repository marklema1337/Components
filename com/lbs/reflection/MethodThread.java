/*      */ package com.lbs.reflection;
/*      */ 
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.Method;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MethodThread
/*      */   extends Thread
/*      */ {
/*  226 */   protected RunnableWithResult _targetObject = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  233 */   protected static int _threadNumber = 0;
/*      */   
/*      */   protected static synchronized String nextThreadName() {
/*  236 */     return "MethodThread-" + _threadNumber++;
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
/*      */   protected MethodThread(ThreadGroup group, RunnableWithResult target, String name) {
/*  265 */     super(group, target, name);
/*  266 */     this._targetObject = target;
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
/*      */   public static MethodThread createMethodThread(RunnableWithResult target) {
/*  292 */     return new MethodThread(null, target, 
/*  293 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, RunnableWithResult target) {
/*  324 */     return new MethodThread(group, target, 
/*  325 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(String name, RunnableWithResult target) {
/*  352 */     return new MethodThread(null, target, name);
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, String name, RunnableWithResult target) {
/*  385 */     return new MethodThread(group, target, name);
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, String name, Object target) throws NoSuchMethodException {
/*  415 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, "run", null);
/*      */     
/*  417 */     return new MethodThread(group, threadTarget, name);
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
/*      */   public static MethodThread createMethodThread(String name, Object target) throws NoSuchMethodException {
/*  442 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, "run", null);
/*      */     
/*  444 */     return new MethodThread(null, threadTarget, name);
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, Object target) throws NoSuchMethodException {
/*  472 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, "run", null);
/*      */     
/*  474 */     return new MethodThread(group, threadTarget, 
/*  475 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(Object target) throws NoSuchMethodException {
/*  498 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, "run", null);
/*      */     
/*  500 */     return new MethodThread(null, threadTarget, 
/*  501 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(Object target, String method) throws NoSuchMethodException {
/*  528 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, method, null);
/*      */     
/*  530 */     return new MethodThread(null, threadTarget, 
/*  531 */         nextThreadName());
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
/*      */   public static MethodThread createConstructorThread(String constructor) throws NoSuchMethodException {
/*  551 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(null, constructor, null);
/*      */     
/*  553 */     return new MethodThread(null, threadTarget, 
/*  554 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(Object target, Method method) {
/*  580 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, method, null);
/*      */     
/*  582 */     return new MethodThread(null, threadTarget, 
/*  583 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(Method method) {
/*  603 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(null, method, null);
/*      */     
/*  605 */     return new MethodThread(null, threadTarget, 
/*  606 */         nextThreadName());
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
/*      */   public static MethodThread createConstructorThread(Constructor constructor) {
/*  626 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(constructor, null);
/*      */     
/*  628 */     return new MethodThread(null, threadTarget, 
/*  629 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, Object target, String method) throws NoSuchMethodException {
/*  660 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, method, null);
/*      */     
/*  662 */     return new MethodThread(group, threadTarget, 
/*  663 */         nextThreadName());
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
/*      */   public static MethodThread createConstructorThread(ThreadGroup group, String constructor) throws NoSuchMethodException {
/*  691 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(null, constructor, null);
/*      */     
/*  693 */     return new MethodThread(group, threadTarget, 
/*  694 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, Object target, Method method) {
/*  724 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, method, null);
/*      */     
/*  726 */     return new MethodThread(group, threadTarget, 
/*  727 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, Method method) {
/*  752 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(null, method, null);
/*      */     
/*  754 */     return new MethodThread(group, threadTarget, 
/*  755 */         nextThreadName());
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
/*      */   public static MethodThread createConstructorThread(ThreadGroup group, Constructor constructor) {
/*  779 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(constructor, null);
/*      */     
/*  781 */     return new MethodThread(group, threadTarget, 
/*  782 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(String name, Object target, String method) throws NoSuchMethodException {
/*  811 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, method, null);
/*      */     
/*  813 */     return new MethodThread(null, threadTarget, name);
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
/*      */   public static MethodThread createConstructorThread(String name, String constructor) throws NoSuchMethodException {
/*  839 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(null, constructor, null);
/*      */     
/*  841 */     return new MethodThread(null, threadTarget, name);
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
/*      */   public static MethodThread createMethodThread(String name, Object target, Method method) {
/*  869 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, method, null);
/*      */     
/*  871 */     return new MethodThread(null, threadTarget, name);
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
/*      */   public static MethodThread createMethodThread(String name, Method method) {
/*  893 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(null, method, null);
/*      */     
/*  895 */     return new MethodThread(null, threadTarget, name);
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
/*      */   public static MethodThread createConstructorThread(String name, Constructor constructor) {
/*  918 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(constructor, null);
/*      */     
/*  920 */     return new MethodThread(null, threadTarget, name);
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, String name, Object target, String method) throws NoSuchMethodException {
/*  954 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, method, null);
/*      */     
/*  956 */     return new MethodThread(group, threadTarget, name);
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
/*      */   public static MethodThread createConstructorThread(ThreadGroup group, String name, String constructor) throws NoSuchMethodException {
/*  987 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(null, constructor, null);
/*      */     
/*  989 */     return new MethodThread(group, threadTarget, name);
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, String name, Object target, Method method) {
/* 1021 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, method, null);
/*      */     
/* 1023 */     return new MethodThread(group, threadTarget, name);
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, String name, Method method) {
/* 1050 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(null, method, null);
/*      */     
/* 1052 */     return new MethodThread(group, threadTarget, name);
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
/*      */   public static MethodThread createConstructorThread(ThreadGroup group, String name, Constructor constructor) {
/* 1079 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(constructor, null);
/*      */     
/* 1081 */     return new MethodThread(group, threadTarget, name);
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
/*      */   public static MethodThread createMethodThread(Object target, String method, Object[] args) throws NoSuchMethodException {
/* 1115 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, method, args);
/*      */     
/* 1117 */     return new MethodThread(null, threadTarget, 
/* 1118 */         nextThreadName());
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
/*      */   public static MethodThread createConstructorThread(String constructor, Object[] args) throws NoSuchMethodException {
/* 1145 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(null, constructor, args);
/*      */     
/* 1147 */     return new MethodThread(null, threadTarget, 
/* 1148 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(Object target, Method method, Object[] args) {
/* 1183 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, method, args);
/*      */     
/* 1185 */     return new MethodThread(null, threadTarget, 
/* 1186 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(Method method, Object[] args) {
/* 1215 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(null, method, args);
/*      */     
/* 1217 */     return new MethodThread(null, threadTarget, 
/* 1218 */         nextThreadName());
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
/*      */   public static MethodThread createConstructorThread(Constructor constructor, Object[] args) {
/* 1247 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(constructor, args);
/*      */     
/* 1249 */     return new MethodThread(null, threadTarget, 
/* 1250 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, Object target, String method, Object[] args) throws NoSuchMethodException {
/* 1289 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, method, args);
/*      */     
/* 1291 */     return new MethodThread(group, threadTarget, 
/* 1292 */         nextThreadName());
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
/*      */   public static MethodThread createConstructorThread(ThreadGroup group, String constructor, Object[] args) throws NoSuchMethodException {
/* 1327 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(null, constructor, args);
/*      */     
/* 1329 */     return new MethodThread(group, threadTarget, 
/* 1330 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, Object target, Method method, Object[] args) {
/* 1369 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, method, args);
/*      */     
/* 1371 */     return new MethodThread(group, threadTarget, 
/* 1372 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, Method method, Object[] args) {
/* 1406 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(null, method, args);
/*      */     
/* 1408 */     return new MethodThread(group, threadTarget, 
/* 1409 */         nextThreadName());
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
/*      */   public static MethodThread createConstructorThread(ThreadGroup group, Constructor constructor, Object[] args) {
/* 1442 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(constructor, args);
/*      */     
/* 1444 */     return new MethodThread(group, threadTarget, 
/* 1445 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(String name, Object target, String method, Object[] args) throws NoSuchMethodException {
/* 1481 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, method, args);
/*      */     
/* 1483 */     return new MethodThread(null, threadTarget, name);
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
/*      */   public static MethodThread createConstructorThread(String name, String constructor, Object[] args) throws NoSuchMethodException {
/* 1516 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(null, constructor, args);
/*      */     
/* 1518 */     return new MethodThread(null, threadTarget, name);
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
/*      */   public static MethodThread createMethodThread(String name, Object target, Method method, Object[] args) {
/* 1554 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, method, args);
/*      */     
/* 1556 */     return new MethodThread(null, threadTarget, name);
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
/*      */   public static MethodThread createMethodThread(String name, Method method, Object[] args) {
/* 1588 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(null, method, args);
/*      */     
/* 1590 */     return new MethodThread(null, threadTarget, name);
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
/*      */   public static MethodThread createConstructorThread(String name, Constructor constructor, Object[] args) {
/* 1621 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(constructor, args);
/*      */     
/* 1623 */     return new MethodThread(null, threadTarget, name);
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, String name, Object target, String method, Object[] args) throws NoSuchMethodException {
/* 1664 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, method, args);
/*      */     
/* 1666 */     return new MethodThread(group, threadTarget, name);
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
/*      */   public static MethodThread createConstructorThread(ThreadGroup group, String name, String constructor, Object[] args) throws NoSuchMethodException {
/* 1703 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(null, constructor, args);
/*      */     
/* 1705 */     return new MethodThread(group, threadTarget, name);
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, String name, Object target, Method method, Object[] args) {
/* 1746 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(target, method, args);
/*      */     
/* 1748 */     return new MethodThread(group, threadTarget, name);
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, String name, Method method, Object[] args) {
/* 1784 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(null, method, args);
/*      */     
/* 1786 */     return new MethodThread(group, threadTarget, name);
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
/*      */   public static MethodThread createConstructorThread(ThreadGroup group, String name, Constructor constructor, Object[] args) {
/* 1822 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(constructor, args);
/*      */     
/* 1824 */     return new MethodThread(group, threadTarget, name);
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
/*      */   public static MethodThread createConstructorThread(Class cls) throws NoSuchMethodException {
/* 1843 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(cls, null);
/*      */     
/* 1845 */     return new MethodThread(null, threadTarget, 
/* 1846 */         nextThreadName());
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
/*      */   public static MethodThread createConstructorThread(Class cls, Object[] args) throws NoSuchMethodException {
/* 1872 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(cls, args);
/*      */     
/* 1874 */     return new MethodThread(null, threadTarget, 
/* 1875 */         nextThreadName());
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
/*      */   public static MethodThread createConstructorThread(String name, Class cls) throws NoSuchMethodException {
/* 1896 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(cls, null);
/*      */     
/* 1898 */     return new MethodThread(null, threadTarget, name);
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
/*      */   public static MethodThread createConstructorThread(String name, Class cls, Object[] args) throws NoSuchMethodException {
/* 1925 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(cls, args);
/*      */     
/* 1927 */     return new MethodThread(null, threadTarget, name);
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
/*      */   public static MethodThread createConstructorThread(ThreadGroup group, Class cls) throws NoSuchMethodException {
/* 1951 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(cls, null);
/*      */     
/* 1953 */     return new MethodThread(group, threadTarget, 
/* 1954 */         nextThreadName());
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
/*      */   public static MethodThread createConstructorThread(ThreadGroup group, Class cls, Object[] args) throws NoSuchMethodException {
/* 1985 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(cls, args);
/*      */     
/* 1987 */     return new MethodThread(group, threadTarget, 
/* 1988 */         nextThreadName());
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
/*      */   public static MethodThread createConstructorThread(ThreadGroup group, String name, Class cls) throws NoSuchMethodException {
/* 2014 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(cls, null);
/*      */     
/* 2016 */     return new MethodThread(group, threadTarget, name);
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
/*      */   public static MethodThread createConstructorThread(ThreadGroup group, String name, Class cls, Object[] args) throws NoSuchMethodException {
/* 2048 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(cls, args);
/*      */     
/* 2050 */     return new MethodThread(group, threadTarget, name);
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
/*      */   public static MethodThread createMethodThread(Class cls, String method) throws NoSuchMethodException {
/* 2076 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(cls, method, null);
/*      */     
/* 2078 */     return new MethodThread(null, threadTarget, 
/* 2079 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(Class cls, String method, Object[] args) throws NoSuchMethodException {
/* 2113 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(cls, method, args);
/*      */     
/* 2115 */     return new MethodThread(null, threadTarget, 
/* 2116 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(String name, Class cls, String method) throws NoSuchMethodException {
/* 2145 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(cls, method, null);
/*      */     
/* 2147 */     return new MethodThread(null, threadTarget, name);
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
/*      */   public static MethodThread createMethodThread(String name, Class cls, String method, Object[] args) throws NoSuchMethodException {
/* 2184 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(cls, method, args);
/*      */     
/* 2186 */     return new MethodThread(null, threadTarget, name);
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, Class cls, String method) throws NoSuchMethodException {
/* 2217 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(cls, method, null);
/*      */     
/* 2219 */     return new MethodThread(group, threadTarget, 
/* 2220 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, Class cls, String method, Object[] args) throws NoSuchMethodException {
/* 2259 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(cls, method, args);
/*      */     
/* 2261 */     return new MethodThread(group, threadTarget, 
/* 2262 */         nextThreadName());
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, String name, Class cls, String method) throws NoSuchMethodException {
/* 2296 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(cls, method, null);
/*      */     
/* 2298 */     return new MethodThread(group, threadTarget, name);
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
/*      */   public static MethodThread createMethodThread(ThreadGroup group, String name, Class cls, String method, Object[] args) throws NoSuchMethodException {
/* 2340 */     ReflectiveInvocation threadTarget = new ReflectiveInvocation(cls, method, args);
/*      */     
/* 2342 */     return new MethodThread(group, threadTarget, name);
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
/*      */   public synchronized Object joinWithValue() throws InterruptedException {
/* 2358 */     return joinWithValue(0L);
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
/*      */   public synchronized Object joinWithValue(long millis, int nanos) throws InterruptedException {
/* 2383 */     if (millis < 0L) {
/* 2384 */       throw new IllegalArgumentException("timeout value is negative");
/*      */     }
/*      */     
/* 2387 */     if (nanos < 0 || nanos > 999999) {
/* 2388 */       throw new IllegalArgumentException("nanosecond timeout value out of range");
/*      */     }
/*      */ 
/*      */     
/* 2392 */     if (nanos >= 500000 || (nanos != 0 && millis == 0L)) {
/* 2393 */       millis++;
/*      */     }
/*      */     
/* 2396 */     return joinWithValue(millis);
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
/*      */   public synchronized Object joinWithValue(long millis) throws InterruptedException {
/* 2417 */     join(millis);
/* 2418 */     return this._targetObject.getResult();
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
/*      */   public Exception getException() {
/* 2432 */     return this._targetObject.getException();
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
/*      */   public String toString() {
/* 2444 */     if (getThreadGroup() != null) {
/* 2445 */       return "MethodThread[" + getName() + "," + 
/* 2446 */         getPriority() + "," + 
/* 2447 */         getThreadGroup().getName() + "," + this._targetObject
/* 2448 */         .toString() + "]";
/*      */     }
/* 2450 */     return "MethodThread[" + getName() + "," + 
/* 2451 */       getPriority() + "," + this._targetObject
/* 2452 */       .toString() + "]";
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\reflection\MethodThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */