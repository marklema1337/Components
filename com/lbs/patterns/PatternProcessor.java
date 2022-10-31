/*     */ package com.lbs.patterns;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.util.SetUtil;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PatternProcessor
/*     */ {
/*     */   public static final String LIST_PATTERN = "\\{(((\\w|\\d|\\.)*)(,((\\w|\\d)*))?)\\}";
/*     */   public static final int OPTION_NONE = 0;
/*     */   public static final int OPTION_LEAVE_MISSING = 1;
/*     */   public static final int OPTION_SINGLE_PASS = 2;
/*     */   public static final int OPTION_NON_RECURSIVE = 4;
/*     */   
/*     */   private static void processMatch(String s, Matcher matcher, StringBuilder sb, IPatternMatchProcessor processor, int options) throws PatternProcessorException {
/*  45 */     String parts[], part0 = matcher.group(2);
/*  46 */     String part1 = matcher.group(5);
/*  47 */     if (part1 == null) {
/*     */       
/*  49 */       parts = new String[1];
/*  50 */       parts[0] = part0;
/*     */     }
/*     */     else {
/*     */       
/*  54 */       parts = new String[2];
/*  55 */       parts[0] = part0;
/*  56 */       parts[1] = part1;
/*     */     } 
/*     */ 
/*     */     
/*  60 */     PatternMatch match = new PatternMatch(parts, matcher.group(1));
/*     */     
/*  62 */     String processed = null;
/*  63 */     if (SetUtil.isOptionSet(options, 4)) {
/*  64 */       processed = match.m_Match;
/*     */     } else {
/*  66 */       processed = processPattern(match.m_Match, processor);
/*     */     } 
/*  68 */     processed = processor.process(match);
/*  69 */     sb.append(processed);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String processPattern(String s, IPatternMatchProcessor processor) throws PatternProcessorException {
/*  74 */     return processPattern(s, processor, "\\{(((\\w|\\d|\\.)*)(,((\\w|\\d)*))?)\\}", 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String processPattern(String s, IPatternMatchProcessor processor, String regExp, int options) throws PatternProcessorException {
/*  80 */     StringBuilder sb = new StringBuilder();
/*     */     
/*  82 */     int curIndex = 0;
/*  83 */     boolean matched = true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     Pattern regExp2 = Pattern.compile(regExp);
/*     */ 
/*     */     
/*     */     while (true) {
/*  92 */       while (matched) {
/*     */ 
/*     */         
/*  95 */         Matcher matcher = regExp2.matcher(s);
/*  96 */         matched = matcher.find(curIndex);
/*  97 */         if (!matched && curIndex == 0) {
/*  98 */           return s;
/*     */         }
/* 100 */         if (matched) {
/*     */ 
/*     */           
/* 103 */           int matchStart = matcher.start(0);
/*     */           
/* 105 */           int matchEnd = matcher.end(0);
/*     */           
/* 107 */           sb.append(s.substring(curIndex, matchStart));
/* 108 */           curIndex = matchEnd;
/*     */           
/* 110 */           processMatch(s, matcher, sb, processor, options);
/*     */         } 
/*     */       } 
/*     */       
/* 114 */       sb.append(s.substring(curIndex));
/* 115 */       s = sb.toString();
/* 116 */       sb.setLength(0);
/*     */       
/* 118 */       if (!SetUtil.isOptionSet(options, 2)) {
/*     */         
/* 120 */         curIndex = 0;
/* 121 */         matched = true; continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 125 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PatternMatches getMatches(String pattern) {
/* 132 */     return getMatches(pattern, "\\{(((\\w|\\d|\\.)*)(,((\\w|\\d)*))?)\\}");
/*     */   }
/*     */ 
/*     */   
/*     */   public static PatternMatches getMatches(String pattern, String regExp) {
/* 137 */     return getMatches(pattern, regExp, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static PatternMatches getMatches(String pattern, String regExp, int options) {
/* 142 */     PatternMatchCollector collector = new PatternMatchCollector();
/*     */ 
/*     */     
/*     */     try {
/* 146 */       processPattern(pattern, collector, regExp, options);
/*     */     }
/* 148 */     catch (PatternProcessorException e) {
/*     */       
/* 150 */       LbsConsole.getLogger("Data.Client.PatternProcessor").error("getMatches", e);
/*     */     } 
/*     */ 
/*     */     
/* 154 */     return collector.m_Matches;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String substituteMatches(String pattern, PatternSubstitutions substitutions, int options) {
/* 159 */     return substituteMatches(pattern, substitutions, "\\{(((\\w|\\d|\\.)*)(,((\\w|\\d)*))?)\\}", options);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String substituteMatches(String pattern, PatternSubstitutions substitutions) {
/* 164 */     return substituteMatches(pattern, substitutions, "\\{(((\\w|\\d|\\.)*)(,((\\w|\\d)*))?)\\}");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String substituteMatches(String pattern, PatternSubstitutions substitutions, String regExp) {
/* 169 */     return substituteMatches(pattern, substitutions, regExp, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String substituteMatches(String pattern, PatternSubstitutions substitutions, String regExp, int options) {
/* 174 */     PatternMatchSubstituter substProcessor = new PatternMatchSubstituter();
/* 175 */     substProcessor.setSubstitutions(substitutions);
/*     */ 
/*     */     
/*     */     try {
/* 179 */       pattern = processPattern(pattern, substProcessor, regExp, options);
/*     */     }
/* 181 */     catch (PatternProcessorException e) {
/*     */       
/* 183 */       LbsConsole.getLogger("Data.Client.PatternProcessor").error("substituteMatches", e);
/*     */     } 
/*     */ 
/*     */     
/* 187 */     return pattern;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 192 */     String p = "{1,abcs}.Yilin {2}.Ayinin {3}.Gunu";
/*     */     
/* 194 */     PatternMatches matches = getMatches(p);
/*     */     
/* 196 */     System.out.println(matches);
/*     */     
/* 198 */     PatternSubstitutions substs = new PatternSubstitutions();
/* 199 */     substs.add(new PatternSubstitution("1", "5"));
/* 200 */     substs.add(new PatternSubstitution("2", "{30}"));
/* 201 */     substs.add(new PatternSubstitution("3", "25"));
/*     */     
/* 203 */     String spat = substituteMatches(p, substs, 6);
/*     */     
/* 205 */     System.out.println(spat);
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\patterns\PatternProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */