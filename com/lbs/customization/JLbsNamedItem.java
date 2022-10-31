/*     */ package com.lbs.customization;
/*     */ 
/*     */ import com.lbs.interfaces.IXMLDescriber;
/*     */ import com.lbs.invoke.InvalidPropertyValueException;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.util.XMLDescribeBuffer;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsNamedItem
/*     */   implements IHasOwner, Serializable, IXMLDescriber
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  20 */   private int m_Id = -1;
/*     */   
/*     */   private String m_Name;
/*     */   
/*     */   private JLbsNamedItemList m_Owner;
/*     */ 
/*     */   
/*     */   public JLbsNamedItem() {}
/*     */   
/*     */   public JLbsNamedItem(JLbsNamedItemList owner) {
/*  30 */     this.m_Owner = owner;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsNamedItem(JLbsNamedItem src) {
/*  35 */     if (src != null) {
/*     */       
/*  37 */       this.m_Owner = src.m_Owner;
/*  38 */       assign(src);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void assign(JLbsNamedItem src) {
/*  44 */     if (src != null) {
/*     */       
/*  46 */       this.m_Id = src.m_Id;
/*  47 */       this.m_Name = src.m_Name;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  53 */     return this.m_Id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  58 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(int i) throws InvalidPropertyValueException {
/*  63 */     if (this.m_Id != i) {
/*     */       
/*  65 */       if (this.m_Owner != null && !this.m_Owner.checkIdUnique(this, i))
/*  66 */         throw new InvalidPropertyValueException("The id '" + i + " is already in use!"); 
/*  67 */       this.m_Id = i;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setName(String string) throws InvalidPropertyValueException {
/*  73 */     string = normalizeName(string);
/*  74 */     if (string != null && (this.m_Name == null || string.compareTo(this.m_Name) != 0)) {
/*     */       
/*  76 */       if (this.m_Owner != null && !this.m_Owner.checkNameUnique(this, string))
/*  77 */         throw new InvalidPropertyValueException("The name '" + string + " is already in use!"); 
/*  78 */       this.m_Name = string;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNameDirect(String string) {
/*  84 */     this.m_Name = string;
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsNamedItemList _getOwner() {
/*  89 */     return this.m_Owner;
/*     */   }
/*     */ 
/*     */   
/*     */   public void _setOwner(JLbsNamedItemList list) throws InvalidPropertyValueException {
/*  94 */     this.m_Owner = list;
/*  95 */     if (list != null) {
/*     */       
/*  97 */       setId((getId() >= 0) ? getId() : list.getUniqueId());
/*  98 */       setName((getName() != null) ? getName() : list.getUniqueName(list.getPrefix()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean _setOwnerSafe(JLbsNamedItemList list) {
/*     */     try {
/* 106 */       _setOwner(list);
/* 107 */       return true;
/*     */     }
/* 109 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 112 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object getOwner() {
/* 117 */     return _getOwner();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(Object owner) {
/* 122 */     if (owner instanceof JLbsNamedItemList) {
/* 123 */       _setOwnerSafe((JLbsNamedItemList)owner);
/*     */     }
/*     */   }
/*     */   
/*     */   protected String normalizeName(String s) {
/* 128 */     if (s != null && s.length() > 0) {
/*     */       
/* 130 */       StringBuilder varName = new StringBuilder(s);
/* 131 */       if (!Character.isLetter(varName.charAt(0)))
/* 132 */         varName.setCharAt(0, 'N'); 
/* 133 */       for (int i = 1; i < varName.length(); i++) {
/* 134 */         if (!Character.isLetterOrDigit(varName.charAt(i)))
/* 135 */           varName.setCharAt(i, '_'); 
/* 136 */       }  return varName.toString();
/*     */     } 
/* 138 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 143 */     return this.m_Name;
/*     */   }
/*     */ 
/*     */   
/*     */   public void describeXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 148 */     writer.startObject(getClass().getName(), "");
/* 149 */     describePropertiesXML(writer, localizationService);
/* 150 */     writer.endObject();
/*     */   }
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 155 */     writer.writeProperty("Id", "int", null, false);
/* 156 */     writer.writeProperty("Name", "String", null, false);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\customization\JLbsNamedItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */