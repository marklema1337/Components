/*    */ package org.apache.logging.log4j.core.lookup;
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
/*    */ public abstract class AbstractLookup
/*    */   implements StrLookup
/*    */ {
/*    */   public String lookup(String key) {
/* 33 */     return lookup(null, key);
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\lookup\AbstractLookup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */