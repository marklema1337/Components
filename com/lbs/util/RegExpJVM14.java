/*     */ package com.lbs.util;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RegExpJVM14
/*     */ {
/*     */   class PatternReplacer
/*     */   {
/*  32 */     private final StringBuilder _inputBuffer = new StringBuilder();
/*  33 */     private final StringBuffer _workBuffer = new StringBuffer();
/*  34 */     private HashMap _replaceMap = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean _caseSensitive = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public synchronized void setCaseSensitive(boolean is) {
/*  49 */       this._caseSensitive = is;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public synchronized void addPattern(String pattern, String replacement) {
/*     */       Pattern p;
/*  61 */       if (!this._caseSensitive) {
/*  62 */         p = Pattern.compile(pattern, 2);
/*     */       } else {
/*  64 */         p = Pattern.compile(pattern);
/*  65 */       }  this._replaceMap.put(p, replacement);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public synchronized String replace(String input) {
/*  76 */       Iterator<Pattern> it = this._replaceMap.keySet().iterator();
/*     */       
/*  78 */       this._inputBuffer.setLength(0);
/*  79 */       this._inputBuffer.append(input);
/*     */       
/*  81 */       this._workBuffer.setLength(0);
/*  82 */       this._workBuffer.ensureCapacity(input.length());
/*     */       
/*  84 */       while (it.hasNext()) {
/*     */         
/*  86 */         Pattern pattern = it.next();
/*  87 */         replaceInto(pattern, (String)this._replaceMap.get(pattern), this._inputBuffer, this._workBuffer);
/*  88 */         this._inputBuffer.setLength(0);
/*  89 */         this._inputBuffer.append(this._workBuffer);
/*     */       } 
/*  91 */       return this._inputBuffer.toString();
/*     */     }
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
/*     */     private void replaceInto(Pattern pattern, String replacement, StringBuilder input, StringBuffer work) {
/* 104 */       work.ensureCapacity(input.length());
/* 105 */       work.setLength(0);
/* 106 */       Matcher m = pattern.matcher(input);
/*     */       
/* 108 */       int end = 0;
/* 109 */       while (m.find()) {
/*     */         
/* 111 */         m.appendReplacement(work, replacement);
/* 112 */         end = m.end();
/*     */       } 
/*     */ 
/*     */       
/* 116 */       for (int i = end, is = input.length(); i < is; i++) {
/* 117 */         work.append(input.charAt(i) + "");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 124 */     System.out.println("Java VM Vendor       : " + System.getProperty("java.vm.vendor"));
/* 125 */     System.out.println("Java VM Version      : " + System.getProperty("java.vm.version"));
/* 126 */     System.out.println("Java VM Name         : " + System.getProperty("java.vm.name"));
/* 127 */     System.out.println("Java VM Class path   : " + System.getProperty("java.class.path"));
/* 128 */     System.out.println("Java VM Library path : " + System.getProperty("java.library.path"));
/*     */ 
/*     */     
/* 131 */     String s = "The quick brown $V(fox) jumped over $V(the) www.hut.fi lazy dog's back.";
/*     */     
/* 133 */     (new RegExpJVM14()).getClass(); PatternReplacer ms = new PatternReplacer();
/* 134 */     ms.addPattern("\\$(V|P|T|F|I|S|D|X|Y|Z)\\(((\\w(\\w|\\d)*)(\\.(\\w(\\w|\\d)*))?)\\)", "$2");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 139 */     System.out.println(ms.replace(s));
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\RegExpJVM14.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */