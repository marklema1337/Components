/*     */ package com.lbs.controls.datedit;
/*     */ 
/*     */ import com.lbs.data.IParameterMandatoryListener;
/*     */ import com.lbs.data.Identifier;
/*     */ import com.lbs.data.Parameter;
/*     */ import com.lbs.interfaces.IParameter;
/*     */ import com.lbs.interfaces.ParameterException;
/*     */ import com.lbs.localization.ILocalizationServices;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import com.lbs.util.XMLDescribeBuffer;
/*     */ import com.thoughtworks.xstream.annotations.XStreamAlias;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.GregorianCalendar;
/*     */ import javax.xml.bind.annotation.XmlElement;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsTimeDuration
/*     */   implements Serializable, IParameter
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final int DURATIONYEAR = 1071;
/*     */   @XStreamAlias("Hour")
/*     */   protected int m_iHour;
/*     */   @XStreamAlias("Minute")
/*     */   protected int m_iMinute;
/*     */   @XStreamAlias("Second")
/*     */   protected int m_iSecond;
/*     */   @XStreamAlias("Milisecond")
/*     */   protected int m_iMilisecond;
/*     */   
/*     */   public static JLbsTimeDuration fromInteger(int value) {
/*  50 */     JLbsTimeDuration result = new JLbsTimeDuration();
/*  51 */     result.m_iMilisecond = value & 0xFF;
/*  52 */     result.m_iSecond = value >> 8 & 0xFF;
/*  53 */     result.m_iMinute = value >> 16 & 0xFF;
/*  54 */     result.m_iHour = value >> 24 & 0xFF;
/*  55 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsTimeDuration() {
/*  63 */     this(0, 0, 0, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  68 */     if (obj instanceof Calendar)
/*  69 */       obj = new JLbsTimeDuration((Calendar)obj); 
/*  70 */     if (obj instanceof JLbsTimeDuration) {
/*     */       
/*  72 */       JLbsTimeDuration time = (JLbsTimeDuration)obj;
/*  73 */       return (time.m_iHour == this.m_iHour && time.m_iMinute == this.m_iMinute && time.m_iSecond == this.m_iSecond && time.m_iMilisecond == this.m_iMilisecond);
/*     */     } 
/*  75 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  80 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsTimeDuration(Calendar cal) {
/*  89 */     if (cal != null) {
/*     */ 
/*     */       
/*  92 */       int year = cal.get(1);
/*  93 */       if (year == 1071) {
/*     */         
/*  95 */         int days = cal.get(5);
/*  96 */         this.m_iHour = days * 24 + cal.get(11);
/*     */       } else {
/*     */         
/*  99 */         this.m_iHour = cal.get(11);
/* 100 */       }  this.m_iMinute = cal.get(12);
/* 101 */       this.m_iSecond = cal.get(13);
/* 102 */       this.m_iMilisecond = cal.get(14);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsTimeDuration(int iHour) {
/* 112 */     this(iHour, 0, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsTimeDuration(int iHour, int iMinute) {
/* 123 */     this(iHour, iMinute, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsTimeDuration(int iHour, int iMinute, int iSeconds) {
/* 135 */     this(iHour, iMinute, iSeconds, 0);
/*     */   }
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
/*     */   public JLbsTimeDuration(int iHour, int iMinute, int iSeconds, int iMiliseconds) {
/* 148 */     this.m_iHour = iHour;
/* 149 */     this.m_iMinute = iMinute;
/* 150 */     this.m_iSecond = iSeconds;
/* 151 */     this.m_iMilisecond = iMiliseconds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlElement(name = "iHour")
/*     */   public int getHour() {
/* 161 */     return this.m_iHour;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlElement(name = "iMinute")
/*     */   public int getMinute() {
/* 171 */     return this.m_iMinute;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlElement(name = "iSecond")
/*     */   public int getSecond() {
/* 181 */     return this.m_iSecond;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlElement(name = "iMilisecond")
/*     */   public int getMilisecond() {
/* 191 */     return this.m_iMilisecond;
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar toCalendar() {
/* 196 */     Calendar result = GregorianCalendar.getInstance();
/* 197 */     result.set(11, this.m_iHour % 24);
/* 198 */     result.set(12, this.m_iMinute);
/* 199 */     result.set(13, this.m_iSecond);
/* 200 */     result.set(14, this.m_iMilisecond);
/* 201 */     if (this.m_iHour >= 24) {
/*     */       
/* 203 */       result.set(1, 1071);
/* 204 */       result.set(2, 0);
/* 205 */       result.set(5, this.m_iHour / 24);
/*     */     } 
/* 207 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public Calendar toCalendar(Calendar result) {
/* 212 */     if (result == null) {
/*     */       
/* 214 */       result = Calendar.getInstance();
/* 215 */       result.clear();
/*     */     } 
/*     */     
/* 218 */     result.set(11, this.m_iHour % 24);
/* 219 */     result.set(12, this.m_iMinute);
/* 220 */     result.set(13, this.m_iSecond);
/* 221 */     result.set(14, this.m_iMilisecond);
/* 222 */     if (this.m_iHour >= 24) {
/*     */       
/* 224 */       result.set(1, 1071);
/* 225 */       result.set(2, 0);
/* 226 */       result.set(5, this.m_iHour / 24);
/*     */     } 
/*     */     
/* 229 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean _isNullTime() {
/* 237 */     return (this.m_iSecond == 0 && this.m_iMinute == 0 && this.m_iHour == 0 && this.m_iMilisecond == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 242 */     StringBuffer buffer = new StringBuffer();
/* 243 */     buffer.append(this.m_iHour);
/* 244 */     buffer.append(':');
/* 245 */     buffer.append(this.m_iMinute);
/* 246 */     buffer.append(':');
/* 247 */     buffer.append(this.m_iSecond);
/* 248 */     buffer.append(':');
/* 249 */     buffer.append(this.m_iMilisecond);
/* 250 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static JLbsTimeDuration fromString(String value) {
/* 255 */     if (value != null) {
/*     */       
/* 257 */       String[] parts = JLbsStringUtil.split(value, ":", true);
/* 258 */       if (parts.length == 4)
/*     */       {
/* 260 */         return new JLbsTimeDuration(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), 
/* 261 */             Integer.parseInt(parts[3]));
/*     */       }
/*     */     } 
/* 264 */     return new JLbsTimeDuration();
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 269 */     this.m_iSecond = 0;
/* 270 */     this.m_iMinute = 0;
/* 271 */     this.m_iHour = 0;
/* 272 */     this.m_iMilisecond = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int toInteger() {
/* 280 */     return this.m_iMilisecond & 0xFF | (this.m_iSecond & 0xFF) << 8 | (this.m_iMinute & 0xFF) << 16 | (this.m_iHour & 0xFF) << 24;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHour(int hour) {
/* 285 */     this.m_iHour = hour;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMinute(int minute) {
/* 290 */     this.m_iMinute = minute;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSecond(int second) {
/* 295 */     this.m_iSecond = second;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMilisecond(int milisecond) {
/* 300 */     this.m_iMilisecond = milisecond;
/*     */   }
/*     */ 
/*     */   
/*     */   public void describeXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 305 */     writer.startObject(getClass().getName(), "");
/* 306 */     describePropertiesXML(writer, localizationService);
/* 307 */     writer.endObject();
/*     */   }
/*     */ 
/*     */   
/*     */   public void describePropertiesXML(XMLDescribeBuffer writer, ILocalizationServices localizationService) {
/* 312 */     writer.writeProperty("Hour", "int", null, false);
/* 313 */     writer.writeProperty("Minute", "int", null, false);
/* 314 */     writer.writeProperty("Second", "int", null, false);
/* 315 */     writer.writeProperty("Milisecond", "int", null, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkMandatoryFields(IParameterMandatoryListener mandatoryListener) throws ParameterException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getEmptyPropertyNames(IParameterMandatoryListener mandatoryListener) {
/* 325 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Identifier getParameterIdentifier() {
/* 331 */     return Parameter.getParameterIdentifier(getClass());
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\datedit\JLbsTimeDuration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */