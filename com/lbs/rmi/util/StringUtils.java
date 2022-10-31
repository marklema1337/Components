/*      */ package com.lbs.rmi.util;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Properties;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.TreeSet;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class StringUtils
/*      */ {
/*      */   private static final String FOLDER_SEPARATOR = "/";
/*      */   private static final String WINDOWS_FOLDER_SEPARATOR = "\\";
/*      */   private static final String TOP_PATH = "..";
/*      */   private static final String CURRENT_PATH = ".";
/*      */   private static final char EXTENSION_SEPARATOR = '.';
/*      */   
/*      */   public static boolean hasLength(CharSequence str) {
/*   86 */     return (str != null && str.length() > 0);
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
/*      */   public static boolean hasLength(String str) {
/*   98 */     return hasLength(str);
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
/*      */   public static boolean hasText(CharSequence str) {
/*  119 */     if (!hasLength(str))
/*      */     {
/*  121 */       return false;
/*      */     }
/*  123 */     int strLen = str.length();
/*  124 */     for (int i = 0; i < strLen; i++) {
/*      */       
/*  126 */       if (!Character.isWhitespace(str.charAt(i)))
/*      */       {
/*  128 */         return true;
/*      */       }
/*      */     } 
/*  131 */     return false;
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
/*      */   public static boolean hasText(String str) {
/*  145 */     return hasText(str);
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
/*      */   public static boolean containsWhitespace(CharSequence str) {
/*  157 */     if (!hasLength(str))
/*      */     {
/*  159 */       return false;
/*      */     }
/*  161 */     int strLen = str.length();
/*  162 */     for (int i = 0; i < strLen; i++) {
/*      */       
/*  164 */       if (Character.isWhitespace(str.charAt(i)))
/*      */       {
/*  166 */         return true;
/*      */       }
/*      */     } 
/*  169 */     return false;
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
/*      */   public static boolean containsWhitespace(String str) {
/*  181 */     return containsWhitespace(str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String trimWhitespace(String str) {
/*  192 */     if (!hasLength(str))
/*      */     {
/*  194 */       return str;
/*      */     }
/*  196 */     StringBuilder buf = new StringBuilder(str);
/*  197 */     while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0)))
/*      */     {
/*  199 */       buf.deleteCharAt(0);
/*      */     }
/*  201 */     while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1)))
/*      */     {
/*  203 */       buf.deleteCharAt(buf.length() - 1);
/*      */     }
/*  205 */     return buf.toString();
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
/*      */   public static String trimAllWhitespace(String str) {
/*  217 */     if (!hasLength(str))
/*      */     {
/*  219 */       return str;
/*      */     }
/*  221 */     StringBuilder buf = new StringBuilder(str);
/*  222 */     int index = 0;
/*  223 */     while (buf.length() > index) {
/*      */       
/*  225 */       if (Character.isWhitespace(buf.charAt(index))) {
/*      */         
/*  227 */         buf.deleteCharAt(index);
/*      */         
/*      */         continue;
/*      */       } 
/*  231 */       index++;
/*      */     } 
/*      */     
/*  234 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String trimLeadingWhitespace(String str) {
/*  245 */     if (!hasLength(str))
/*      */     {
/*  247 */       return str;
/*      */     }
/*  249 */     StringBuilder buf = new StringBuilder(str);
/*  250 */     while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0)))
/*      */     {
/*  252 */       buf.deleteCharAt(0);
/*      */     }
/*  254 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String trimTrailingWhitespace(String str) {
/*  265 */     if (!hasLength(str))
/*      */     {
/*  267 */       return str;
/*      */     }
/*  269 */     StringBuilder buf = new StringBuilder(str);
/*  270 */     while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1)))
/*      */     {
/*  272 */       buf.deleteCharAt(buf.length() - 1);
/*      */     }
/*  274 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String trimLeadingCharacter(String str, char leadingCharacter) {
/*  285 */     if (!hasLength(str))
/*      */     {
/*  287 */       return str;
/*      */     }
/*  289 */     StringBuilder buf = new StringBuilder(str);
/*  290 */     while (buf.length() > 0 && buf.charAt(0) == leadingCharacter)
/*      */     {
/*  292 */       buf.deleteCharAt(0);
/*      */     }
/*  294 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String trimTrailingCharacter(String str, char trailingCharacter) {
/*  305 */     if (!hasLength(str))
/*      */     {
/*  307 */       return str;
/*      */     }
/*  309 */     StringBuilder buf = new StringBuilder(str);
/*  310 */     while (buf.length() > 0 && buf.charAt(buf.length() - 1) == trailingCharacter)
/*      */     {
/*  312 */       buf.deleteCharAt(buf.length() - 1);
/*      */     }
/*  314 */     return buf.toString();
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
/*      */   public static boolean startsWithIgnoreCase(String str, String prefix) {
/*  326 */     if (str == null || prefix == null)
/*      */     {
/*  328 */       return false;
/*      */     }
/*  330 */     if (str.startsWith(prefix))
/*      */     {
/*  332 */       return true;
/*      */     }
/*  334 */     if (str.length() < prefix.length())
/*      */     {
/*  336 */       return false;
/*      */     }
/*  338 */     String lcStr = str.substring(0, prefix.length()).toLowerCase();
/*  339 */     String lcPrefix = prefix.toLowerCase();
/*  340 */     return lcStr.equals(lcPrefix);
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
/*      */   public static boolean endsWithIgnoreCase(String str, String suffix) {
/*  352 */     if (str == null || suffix == null)
/*      */     {
/*  354 */       return false;
/*      */     }
/*  356 */     if (str.endsWith(suffix))
/*      */     {
/*  358 */       return true;
/*      */     }
/*  360 */     if (str.length() < suffix.length())
/*      */     {
/*  362 */       return false;
/*      */     }
/*      */     
/*  365 */     String lcStr = str.substring(str.length() - suffix.length()).toLowerCase();
/*  366 */     String lcSuffix = suffix.toLowerCase();
/*  367 */     return lcStr.equals(lcSuffix);
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
/*      */   public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
/*  379 */     for (int j = 0; j < substring.length(); j++) {
/*      */       
/*  381 */       int i = index + j;
/*  382 */       if (i >= str.length() || str.charAt(i) != substring.charAt(j))
/*      */       {
/*  384 */         return false;
/*      */       }
/*      */     } 
/*  387 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int countOccurrencesOf(String str, String sub) {
/*  397 */     if (str == null || sub == null || str.length() == 0 || sub.length() == 0)
/*      */     {
/*  399 */       return 0;
/*      */     }
/*  401 */     int count = 0, pos = 0, idx = 0;
/*  402 */     while ((idx = str.indexOf(sub, pos)) != -1) {
/*      */       
/*  404 */       count++;
/*  405 */       pos = idx + sub.length();
/*      */     } 
/*  407 */     return count;
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
/*      */   public static String replace(String inString, String oldPattern, String newPattern) {
/*  420 */     if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null)
/*      */     {
/*  422 */       return inString;
/*      */     }
/*  424 */     StringBuilder sbuf = new StringBuilder();
/*      */     
/*  426 */     int pos = 0;
/*  427 */     int index = inString.indexOf(oldPattern);
/*      */     
/*  429 */     int patLen = oldPattern.length();
/*  430 */     while (index >= 0) {
/*      */       
/*  432 */       sbuf.append(inString.substring(pos, index));
/*  433 */       sbuf.append(newPattern);
/*  434 */       pos = index + patLen;
/*  435 */       index = inString.indexOf(oldPattern, pos);
/*      */     } 
/*  437 */     sbuf.append(inString.substring(pos));
/*      */     
/*  439 */     return sbuf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String delete(String inString, String pattern) {
/*  450 */     return replace(inString, pattern, "");
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
/*      */   public static String deleteAny(String inString, String charsToDelete) {
/*  462 */     if (!hasLength(inString) || !hasLength(charsToDelete))
/*      */     {
/*  464 */       return inString;
/*      */     }
/*  466 */     StringBuilder out = new StringBuilder();
/*  467 */     for (int i = 0; i < inString.length(); i++) {
/*      */       
/*  469 */       char c = inString.charAt(i);
/*  470 */       if (charsToDelete.indexOf(c) == -1)
/*      */       {
/*  472 */         out.append(c);
/*      */       }
/*      */     } 
/*  475 */     return out.toString();
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
/*      */   public static String quote(String str) {
/*  490 */     return (str != null) ? (
/*  491 */       "'" + str + "'") : 
/*  492 */       null;
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
/*      */   public static Object quoteIfString(Object obj) {
/*  504 */     return (obj instanceof String) ? 
/*  505 */       quote((String)obj) : 
/*  506 */       obj;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String unqualify(String qualifiedName) {
/*  516 */     return unqualify(qualifiedName, '.');
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String unqualify(String qualifiedName, char separator) {
/*  527 */     return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
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
/*      */   public static String capitalize(String str) {
/*  539 */     return changeFirstCharacterCase(str, true);
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
/*      */   public static String uncapitalize(String str) {
/*  551 */     return changeFirstCharacterCase(str, false);
/*      */   }
/*      */ 
/*      */   
/*      */   private static String changeFirstCharacterCase(String str, boolean capitalize) {
/*  556 */     if (str == null || str.length() == 0)
/*      */     {
/*  558 */       return str;
/*      */     }
/*  560 */     StringBuilder buf = new StringBuilder(str.length());
/*  561 */     if (capitalize) {
/*      */       
/*  563 */       buf.append(Character.toUpperCase(str.charAt(0)));
/*      */     }
/*      */     else {
/*      */       
/*  567 */       buf.append(Character.toLowerCase(str.charAt(0)));
/*      */     } 
/*  569 */     buf.append(str.substring(1));
/*  570 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getFilename(String path) {
/*  581 */     if (path == null)
/*      */     {
/*  583 */       return null;
/*      */     }
/*  585 */     int separatorIndex = path.lastIndexOf("/");
/*  586 */     return (separatorIndex != -1) ? 
/*  587 */       path.substring(separatorIndex + 1) : 
/*  588 */       path;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getFilenameExtension(String path) {
/*  599 */     if (path == null)
/*      */     {
/*  601 */       return null;
/*      */     }
/*  603 */     int sepIndex = path.lastIndexOf('.');
/*  604 */     return (sepIndex != -1) ? 
/*  605 */       path.substring(sepIndex + 1) : 
/*  606 */       null;
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
/*      */   public static String stripFilenameExtension(String path) {
/*  618 */     if (path == null)
/*      */     {
/*  620 */       return null;
/*      */     }
/*  622 */     int sepIndex = path.lastIndexOf('.');
/*  623 */     return (sepIndex != -1) ? 
/*  624 */       path.substring(0, sepIndex) : 
/*  625 */       path;
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
/*      */   public static String applyRelativePath(String path, String relativePath) {
/*  638 */     int separatorIndex = path.lastIndexOf("/");
/*  639 */     if (separatorIndex != -1) {
/*      */       
/*  641 */       String newPath = path.substring(0, separatorIndex);
/*  642 */       if (!relativePath.startsWith("/"))
/*      */       {
/*  644 */         newPath = String.valueOf(newPath) + "/";
/*      */       }
/*  646 */       return String.valueOf(newPath) + relativePath;
/*      */     } 
/*      */ 
/*      */     
/*  650 */     return relativePath;
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
/*      */   public static String cleanPath(String path) {
/*  664 */     if (path == null)
/*      */     {
/*  666 */       return null;
/*      */     }
/*  668 */     String pathToUse = replace(path, "\\", "/");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  674 */     int prefixIndex = pathToUse.indexOf(":");
/*  675 */     String prefix = "";
/*  676 */     if (prefixIndex != -1) {
/*      */       
/*  678 */       prefix = pathToUse.substring(0, prefixIndex + 1);
/*  679 */       pathToUse = pathToUse.substring(prefixIndex + 1);
/*      */     } 
/*  681 */     if (pathToUse.startsWith("/")) {
/*      */       
/*  683 */       prefix = String.valueOf(prefix) + "/";
/*  684 */       pathToUse = pathToUse.substring(1);
/*      */     } 
/*      */     
/*  687 */     String[] pathArray = delimitedListToStringArray(pathToUse, "/");
/*  688 */     List<String> pathElements = new LinkedList();
/*  689 */     int tops = 0;
/*      */     int i;
/*  691 */     for (i = pathArray.length - 1; i >= 0; i--) {
/*      */       
/*  693 */       String element = pathArray[i];
/*  694 */       if (!".".equals(element))
/*      */       {
/*      */ 
/*      */         
/*  698 */         if ("..".equals(element)) {
/*      */ 
/*      */           
/*  701 */           tops++;
/*      */ 
/*      */         
/*      */         }
/*  705 */         else if (tops > 0) {
/*      */ 
/*      */           
/*  708 */           tops--;
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  713 */           pathElements.add(0, element);
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  719 */     for (i = 0; i < tops; i++)
/*      */     {
/*  721 */       pathElements.add(0, "..");
/*      */     }
/*      */     
/*  724 */     return String.valueOf(prefix) + collectionToDelimitedString(pathElements, "/");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean pathEquals(String path1, String path2) {
/*  735 */     return cleanPath(path1).equals(cleanPath(path2));
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
/*      */   public static Locale parseLocaleString(String localeString) {
/*  748 */     String[] parts = tokenizeToStringArray(localeString, "_ ", false, false);
/*  749 */     String language = (parts.length > 0) ? 
/*  750 */       parts[0] : 
/*  751 */       "";
/*  752 */     String country = (parts.length > 1) ? 
/*  753 */       parts[1] : 
/*  754 */       "";
/*  755 */     String variant = "";
/*  756 */     if (parts.length >= 2) {
/*      */ 
/*      */ 
/*      */       
/*  760 */       int endIndexOfCountryCode = localeString.indexOf(country) + country.length();
/*      */       
/*  762 */       variant = trimLeadingWhitespace(localeString.substring(endIndexOfCountryCode));
/*  763 */       if (variant.startsWith("_"))
/*      */       {
/*  765 */         variant = trimLeadingCharacter(variant, '_');
/*      */       }
/*      */     } 
/*  768 */     return (language.length() > 0) ? 
/*  769 */       new Locale(language, country, variant) : 
/*  770 */       null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toLanguageTag(Locale locale) {
/*  781 */     return String.valueOf(locale.getLanguage()) + (hasText(locale.getCountry()) ? (
/*  782 */       "-" + locale.getCountry()) : 
/*  783 */       "");
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
/*      */   public static String[] addStringToArray(String[] array, String str) {
/*  799 */     if (ObjectUtils.isEmpty((Object[])array))
/*      */     {
/*  801 */       return new String[] { str };
/*      */     }
/*  803 */     String[] newArr = new String[array.length + 1];
/*  804 */     System.arraycopy(array, 0, newArr, 0, array.length);
/*  805 */     newArr[array.length] = str;
/*  806 */     return newArr;
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
/*      */   public static String[] concatenateStringArrays(String[] array1, String[] array2) {
/*  819 */     if (ObjectUtils.isEmpty((Object[])array1))
/*      */     {
/*  821 */       return array2;
/*      */     }
/*  823 */     if (ObjectUtils.isEmpty((Object[])array2))
/*      */     {
/*  825 */       return array1;
/*      */     }
/*  827 */     String[] newArr = new String[array1.length + array2.length];
/*  828 */     System.arraycopy(array1, 0, newArr, 0, array1.length);
/*  829 */     System.arraycopy(array2, 0, newArr, array1.length, array2.length);
/*  830 */     return newArr;
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
/*      */   public static String[] mergeStringArrays(String[] array1, String[] array2) {
/*  845 */     if (ObjectUtils.isEmpty((Object[])array1))
/*      */     {
/*  847 */       return array2;
/*      */     }
/*  849 */     if (ObjectUtils.isEmpty((Object[])array2))
/*      */     {
/*  851 */       return array1;
/*      */     }
/*  853 */     List<String> result = new ArrayList();
/*  854 */     result.addAll(Arrays.asList(array1));
/*  855 */     for (int i = 0; i < array2.length; i++) {
/*      */       
/*  857 */       String str = array2[i];
/*  858 */       if (!result.contains(str))
/*      */       {
/*  860 */         result.add(str);
/*      */       }
/*      */     } 
/*  863 */     return toStringArray(result);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] sortStringArray(String[] array) {
/*  873 */     if (ObjectUtils.isEmpty((Object[])array))
/*      */     {
/*  875 */       return new String[0];
/*      */     }
/*  877 */     Arrays.sort((Object[])array);
/*  878 */     return array;
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
/*      */   public static String[] toStringArray(Collection collection) {
/*  890 */     if (collection == null)
/*      */     {
/*  892 */       return null;
/*      */     }
/*  894 */     return (String[])collection.toArray((Object[])new String[collection.size()]);
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
/*      */   public static String[] toStringArray(Enumeration<?> enumeration) {
/*  906 */     if (enumeration == null)
/*      */     {
/*  908 */       return null;
/*      */     }
/*  910 */     List<?> list = Collections.list(enumeration);
/*  911 */     return list.<String>toArray(new String[list.size()]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] trimArrayElements(String[] array) {
/*  922 */     if (ObjectUtils.isEmpty((Object[])array))
/*      */     {
/*  924 */       return new String[0];
/*      */     }
/*  926 */     String[] result = new String[array.length];
/*  927 */     for (int i = 0; i < array.length; i++) {
/*      */       
/*  929 */       String element = array[i];
/*  930 */       result[i] = (element != null) ? 
/*  931 */         element.trim() : 
/*  932 */         null;
/*      */     } 
/*  934 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] removeDuplicateStrings(String[] array) {
/*  945 */     if (ObjectUtils.isEmpty((Object[])array))
/*      */     {
/*  947 */       return array;
/*      */     }
/*  949 */     Set<String> set = new TreeSet();
/*  950 */     for (int i = 0; i < array.length; i++)
/*      */     {
/*  952 */       set.add(array[i]);
/*      */     }
/*  954 */     return toStringArray(set);
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
/*      */   public static String[] split(String toSplit, String delimiter) {
/*  968 */     if (!hasLength(toSplit) || !hasLength(delimiter))
/*      */     {
/*  970 */       return null;
/*      */     }
/*  972 */     int offset = toSplit.indexOf(delimiter);
/*  973 */     if (offset < 0)
/*      */     {
/*  975 */       return null;
/*      */     }
/*  977 */     String beforeDelimiter = toSplit.substring(0, offset);
/*  978 */     String afterDelimiter = toSplit.substring(offset + delimiter.length());
/*  979 */     return new String[] { beforeDelimiter, afterDelimiter };
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
/*      */   public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter) {
/*  995 */     return splitArrayElementsIntoProperties(array, delimiter, null);
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
/*      */   public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter, String charsToDelete) {
/* 1015 */     if (ObjectUtils.isEmpty((Object[])array))
/*      */     {
/* 1017 */       return null;
/*      */     }
/* 1019 */     Properties result = new Properties();
/* 1020 */     for (int i = 0; i < array.length; i++) {
/*      */       
/* 1022 */       String element = array[i];
/* 1023 */       if (charsToDelete != null)
/*      */       {
/* 1025 */         element = deleteAny(array[i], charsToDelete);
/*      */       }
/* 1027 */       String[] splittedElement = split(element, delimiter);
/* 1028 */       if (splittedElement != null)
/*      */       {
/*      */ 
/*      */         
/* 1032 */         result.setProperty(splittedElement[0].trim(), splittedElement[1].trim()); } 
/*      */     } 
/* 1034 */     return result;
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
/*      */   public static String[] tokenizeToStringArray(String str, String delimiters) {
/* 1054 */     return tokenizeToStringArray(str, delimiters, true, true);
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
/*      */   public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {
/* 1079 */     if (str == null)
/*      */     {
/* 1081 */       return null;
/*      */     }
/* 1083 */     StringTokenizer st = new StringTokenizer(str, delimiters);
/* 1084 */     List<String> tokens = new ArrayList();
/* 1085 */     while (st.hasMoreTokens()) {
/*      */       
/* 1087 */       String token = st.nextToken();
/* 1088 */       if (trimTokens)
/*      */       {
/* 1090 */         token = token.trim();
/*      */       }
/* 1092 */       if (!ignoreEmptyTokens || token.length() > 0)
/*      */       {
/* 1094 */         tokens.add(token);
/*      */       }
/*      */     } 
/* 1097 */     return toStringArray(tokens);
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
/*      */   public static String[] delimitedListToStringArray(String str, String delimiter) {
/* 1113 */     return delimitedListToStringArray(str, delimiter, null);
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
/*      */   public static String[] delimitedListToStringArray(String str, String delimiter, String charsToDelete) {
/* 1131 */     if (str == null)
/*      */     {
/* 1133 */       return new String[0];
/*      */     }
/* 1135 */     if (delimiter == null)
/*      */     {
/* 1137 */       return new String[] { str };
/*      */     }
/* 1139 */     List<String> result = new ArrayList();
/* 1140 */     if ("".equals(delimiter)) {
/*      */       
/* 1142 */       for (int i = 0; i < str.length(); i++)
/*      */       {
/* 1144 */         result.add(deleteAny(str.substring(i, i + 1), charsToDelete));
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1149 */       int pos = 0;
/* 1150 */       int delPos = 0;
/* 1151 */       while ((delPos = str.indexOf(delimiter, pos)) != -1) {
/*      */         
/* 1153 */         result.add(deleteAny(str.substring(pos, delPos), charsToDelete));
/* 1154 */         pos = delPos + delimiter.length();
/*      */       } 
/* 1156 */       if (str.length() > 0 && pos <= str.length())
/*      */       {
/*      */         
/* 1159 */         result.add(deleteAny(str.substring(pos), charsToDelete));
/*      */       }
/*      */     } 
/* 1162 */     return toStringArray(result);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] commaDelimitedListToStringArray(String str) {
/* 1172 */     return delimitedListToStringArray(str, ",");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Set commaDelimitedListToSet(String str) {
/* 1183 */     Set<String> set = new TreeSet();
/* 1184 */     String[] tokens = commaDelimitedListToStringArray(str);
/* 1185 */     for (int i = 0; i < tokens.length; i++)
/*      */     {
/* 1187 */       set.add(tokens[i]);
/*      */     }
/* 1189 */     return set;
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
/*      */   public static String collectionToDelimitedString(Collection coll, String delim, String prefix, String suffix) {
/* 1203 */     if (CollectionUtils.isEmpty(coll))
/*      */     {
/* 1205 */       return "";
/*      */     }
/* 1207 */     StringBuilder sb = new StringBuilder();
/* 1208 */     Iterator it = coll.iterator();
/* 1209 */     while (it.hasNext()) {
/*      */       
/* 1211 */       sb.append(prefix).append(it.next()).append(suffix);
/* 1212 */       if (it.hasNext())
/*      */       {
/* 1214 */         sb.append(delim);
/*      */       }
/*      */     } 
/* 1217 */     return sb.toString();
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
/*      */   public static String collectionToDelimitedString(Collection coll, String delim) {
/* 1229 */     return collectionToDelimitedString(coll, delim, "", "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String collectionToCommaDelimitedString(Collection coll) {
/* 1240 */     return collectionToDelimitedString(coll, ",");
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
/*      */   public static String arrayToDelimitedString(Object[] arr, String delim) {
/* 1252 */     if (ObjectUtils.isEmpty(arr))
/*      */     {
/* 1254 */       return "";
/*      */     }
/* 1256 */     StringBuilder sb = new StringBuilder();
/* 1257 */     for (int i = 0; i < arr.length; i++) {
/*      */       
/* 1259 */       if (i > 0)
/*      */       {
/* 1261 */         sb.append(delim);
/*      */       }
/* 1263 */       sb.append(arr[i]);
/*      */     } 
/* 1265 */     return sb.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String arrayToCommaDelimitedString(Object[] arr) {
/* 1276 */     return arrayToDelimitedString(arr, ",");
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
/*      */   public static String removeEnd(String str, String remove) {
/* 1305 */     if (isEmpty(str) || isEmpty(remove))
/*      */     {
/* 1307 */       return str;
/*      */     }
/* 1309 */     if (str.endsWith(remove))
/*      */     {
/* 1311 */       return str.substring(0, str.length() - remove.length());
/*      */     }
/* 1313 */     return str;
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
/*      */   public static boolean isEmpty(String str) {
/* 1336 */     return !(str != null && str.length() != 0);
/*      */   }
/*      */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\rm\\util\StringUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */