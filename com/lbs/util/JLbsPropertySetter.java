/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.invoke.RttiUtil;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
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
/*     */ public class JLbsPropertySetter
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected String m_ClassName;
/*     */   protected String m_FieldName;
/*     */   protected String m_FieldClassName;
/*     */   protected String m_Value;
/*  26 */   protected int m_Type = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int TYPE_SERVER = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int TYPE_CLIENT = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsPropertySetter(String className, String fieldName, String fieldClassName, String value) {
/*  39 */     this.m_ClassName = className;
/*  40 */     this.m_FieldName = fieldName;
/*  41 */     this.m_FieldClassName = fieldClassName;
/*  42 */     this.m_Value = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsPropertySetter(String className, String fieldName, String fieldClassName, String value, int type) {
/*  47 */     this(className, fieldName, fieldClassName, value);
/*  48 */     this.m_Type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFieldName() {
/*  56 */     return this.m_FieldName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFieldName(String fieldName) {
/*  64 */     this.m_FieldName = fieldName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClassName() {
/*  72 */     return this.m_ClassName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClassName(String className) {
/*  80 */     this.m_ClassName = className;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFieldClassName() {
/*  88 */     return this.m_FieldClassName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFieldClassName(String fieldClassName) {
/*  96 */     this.m_FieldClassName = fieldClassName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValue() {
/* 104 */     return this.m_Value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(String value) {
/* 112 */     this.m_Value = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean apply() {
/* 117 */     Class clazz = null;
/* 118 */     Object value = null;
/*     */     
/*     */     try {
/* 121 */       clazz = RttiUtil.getClass(this.m_ClassName);
/*     */       
/* 123 */       if (clazz != null) {
/*     */         
/* 125 */         Class<?> fieldClazz = RttiUtil.getClass(this.m_FieldClassName);
/*     */         
/*     */         try {
/* 128 */           Field field = clazz.getDeclaredField(this.m_FieldName);
/* 129 */           if (fieldClazz.equals(Enum.class))
/* 130 */             fieldClazz = field.getType(); 
/* 131 */           value = createObjectFromStringValue(fieldClazz, this.m_Value);
/* 132 */           field.set(null, value);
/* 133 */           System.out.println("The value of the " + this.m_FieldName + " variable in the " + this.m_ClassName + " class is set to " + this.m_Value + ".");
/*     */           
/* 135 */           return true;
/*     */         }
/* 137 */         catch (IllegalArgumentException e) {
/*     */           
/* 139 */           e.printStackTrace();
/*     */         }
/* 141 */         catch (IllegalAccessException e) {
/*     */           
/* 143 */           e.printStackTrace();
/*     */         }
/* 145 */         catch (NoSuchFieldException e) {
/*     */           
/*     */           try
/*     */           {
/* 149 */             value = createObjectFromStringValue(fieldClazz, this.m_Value);
/* 150 */             RttiUtil.runObjectInvokePath(clazz.newInstance(), this.m_FieldName, "set", new Object[] { value });
/*     */             
/* 152 */             System.out.println("The 'set" + this.m_FieldName + "' method of the " + this.m_ClassName + " class is invoked with parameter value '" + value + "'");
/*     */             
/* 154 */             return true;
/*     */           }
/* 156 */           catch (Exception re)
/*     */           {
/* 158 */             re.printStackTrace();
/*     */           }
/*     */         
/*     */         }
/* 162 */         catch (NullPointerException e) {
/*     */           
/* 164 */           e.printStackTrace();
/*     */         }
/*     */       
/*     */       } 
/* 168 */     } catch (SecurityException e) {
/*     */       
/* 170 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 173 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private Object createObjectFromStringValue(Class<?> objType, String value) {
/* 178 */     if (RttiUtil.isSubClassOf(objType, Number.class)) {
/*     */       
/*     */       try {
/*     */         
/* 182 */         Constructor cons = objType.getConstructor(new Class[] { String.class });
/* 183 */         return cons.newInstance(new Object[] { value });
/*     */       }
/* 185 */       catch (Exception e) {
/*     */         
/* 187 */         return null;
/*     */       } 
/*     */     }
/* 190 */     if (objType.equals(String.class))
/* 191 */       return value; 
/* 192 */     if (objType.equals(Boolean.class) && (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")))
/* 193 */       return new Boolean(value); 
/* 194 */     if (Enum.class.isAssignableFrom(objType)) {
/* 195 */       return Enum.valueOf(objType, value);
/*     */     }
/* 197 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getType() {
/* 202 */     return this.m_Type;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(int type) {
/* 207 */     this.m_Type = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public String createHashKeyValue() {
/* 212 */     return this.m_ClassName + "_" + this.m_FieldName;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 217 */     if (obj instanceof JLbsPropertySetter) {
/*     */       
/* 219 */       JLbsPropertySetter prop = (JLbsPropertySetter)obj;
/* 220 */       if (JLbsStringUtil.areEqual(prop.m_ClassName, this.m_ClassName) && JLbsStringUtil.areEqual(prop.m_FieldName, this.m_FieldName) && 
/* 221 */         JLbsStringUtil.areEqual(prop.m_Value, this.m_Value))
/* 222 */         return true; 
/*     */     } 
/* 224 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 230 */     return super.hashCode();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsPropertySetter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */