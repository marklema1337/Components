/*    */ package com.lbs.util;
/*    */ 
/*    */ import java.io.FileOutputStream;
/*    */ import javax.xml.transform.Transformer;
/*    */ import javax.xml.transform.TransformerFactory;
/*    */ import javax.xml.transform.stream.StreamResult;
/*    */ import javax.xml.transform.stream.StreamSource;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XSLTUtilImpl
/*    */ {
/*    */   protected void transformXML(String inputXML, String inputXSL, String outputXML) throws Exception {
/* 18 */     TransformerFactory tFactory = TransformerFactory.newInstance();
/*    */     
/* 20 */     Transformer transformer = tFactory.newTransformer(new StreamSource(inputXSL));
/* 21 */     transformer.transform(new StreamSource(inputXML), new StreamResult(new FileOutputStream(outputXML)));
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\XSLTUtilImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */