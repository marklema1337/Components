/*    */ package com.lbs.cache;
/*    */ 
/*    */ import org.w3c.dom.Document;
/*    */ import org.w3c.dom.Element;
/*    */ import org.w3c.dom.NamedNodeMap;
/*    */ import org.w3c.dom.Node;
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
/*    */ public class JLbsCacheItem
/*    */   implements ICacheConstants
/*    */ {
/*    */   private Object m_Key;
/*    */   
/*    */   public JLbsCacheItem(Object key) {
/* 24 */     this.m_Key = key;
/*    */   }
/*    */ 
/*    */   
/*    */   public Element exportToXMLElement(Document document) {
/* 29 */     if (document == null) {
/* 30 */       return null;
/*    */     }
/* 32 */     Element itemElement = document.createElement("CacheItem");
/* 33 */     if (this.m_Key != null) {
/* 34 */       itemElement.setAttribute("key", String.valueOf(this.m_Key));
/*    */     } else {
/* 36 */       itemElement.setAttribute("key", "");
/* 37 */     }  return itemElement;
/*    */   }
/*    */ 
/*    */   
/*    */   public void importFromXMLElement(Node cacheItemNode) {
/* 42 */     NamedNodeMap attributeList = cacheItemNode.getAttributes();
/* 43 */     if (attributeList.getNamedItem("key") != null) {
/* 44 */       this.m_Key = attributeList.getNamedItem("key").getNodeValue();
/*    */     } else {
/* 46 */       this.m_Key = null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\cache\JLbsCacheItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */