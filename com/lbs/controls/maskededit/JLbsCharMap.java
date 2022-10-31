/*    */ package com.lbs.controls.maskededit;
/*    */ 
/*    */ import com.lbs.util.JLbsStringUtil;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JLbsCharMap
/*    */ {
/* 21 */   private HashMap m_CharMap = new HashMap<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public void add(char source, char target) {
/* 26 */     this.m_CharMap.put(new Character(source), new Character(target));
/*    */   }
/*    */ 
/*    */   
/*    */   public char get(char source) {
/* 31 */     Character target = (Character)this.m_CharMap.get(new Character(source));
/*    */     
/* 33 */     if (target != null) {
/* 34 */       return target.charValue();
/*    */     }
/* 36 */     return source;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasMapping(char source) {
/* 41 */     return this.m_CharMap.containsKey(new Character(source));
/*    */   }
/*    */ 
/*    */   
/*    */   public void parse(String charMapStr) {
/* 46 */     if (JLbsStringUtil.isEmpty(charMapStr)) {
/*    */       return;
/*    */     }
/* 49 */     int len = charMapStr.length();
/* 50 */     if (len % 2 != 0) {
/* 51 */       len--;
/*    */     }
/* 53 */     for (int i = 0; i < len; i += 2)
/* 54 */       add(charMapStr.charAt(i), charMapStr.charAt(i + 1)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\maskededit\JLbsCharMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */