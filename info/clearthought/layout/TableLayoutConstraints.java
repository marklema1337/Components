/*     */ package info.clearthought.layout;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.StringTokenizer;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TableLayoutConstraints
/*     */   implements TableLayoutConstants
/*     */ {
/*     */   public int col1;
/*     */   public int row1;
/*     */   public int col2;
/*     */   public int row2;
/*     */   public int hAlign;
/*     */   public int vAlign;
/*     */   
/*     */   public TableLayoutConstraints() {
/*  77 */     this.col1 = this.row1 = this.col1 = this.col2 = 0;
/*  78 */     this.hAlign = this.vAlign = 2;
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
/*     */   
/*     */   public TableLayoutConstraints(int col, int row) {
/*  92 */     this(col, row, col, row, 2, 2);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public TableLayoutConstraints(int col1, int row1, int col2, int row2) {
/* 108 */     this(col1, row1, col2, row2, 2, 2);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TableLayoutConstraints(int col1, int row1, int col2, int row2, int hAlign, int vAlign) {
/* 127 */     this.col1 = col1;
/* 128 */     this.row1 = row1;
/* 129 */     this.col2 = col2;
/* 130 */     this.row2 = row2;
/*     */     
/* 132 */     if (hAlign == 0 || 
/* 133 */       hAlign == 3 || 
/* 134 */       hAlign == 1 || 
/* 135 */       hAlign == 2 || 
/* 136 */       hAlign == 4 || 
/* 137 */       hAlign == 5) {
/*     */       
/* 139 */       this.hAlign = hAlign;
/*     */     } else {
/*     */       
/* 142 */       this.hAlign = 2;
/*     */     } 
/* 144 */     if (vAlign == 0 || 
/* 145 */       vAlign == 3 || 
/* 146 */       vAlign == 1) {
/*     */       
/* 148 */       this.vAlign = vAlign;
/*     */     } else {
/*     */       
/* 151 */       this.vAlign = 2;
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TableLayoutConstraints(String constraints) {
/* 174 */     this.col1 = 0;
/* 175 */     this.row1 = 0;
/* 176 */     this.col2 = 0;
/* 177 */     this.row2 = 0;
/* 178 */     this.hAlign = 2;
/* 179 */     this.vAlign = 2;
/*     */ 
/*     */     
/* 182 */     StringTokenizer st = new StringTokenizer(constraints, ", ");
/* 183 */     int numToken = st.countTokens();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 188 */     try { if (numToken != 2 && numToken != 4 && numToken != 6) {
/* 189 */         throw new RuntimeException();
/*     */       }
/*     */       
/* 192 */       String tokenA = st.nextToken();
/* 193 */       this.col1 = Integer.valueOf(tokenA).intValue();
/* 194 */       this.col2 = this.col1;
/*     */ 
/*     */       
/* 197 */       String tokenB = st.nextToken();
/* 198 */       this.row1 = Integer.valueOf(tokenB).intValue();
/* 199 */       this.row2 = this.row1;
/*     */ 
/*     */       
/* 202 */       tokenA = st.nextToken();
/* 203 */       tokenB = st.nextToken();
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 208 */         this.col2 = Integer.valueOf(tokenA).intValue();
/* 209 */         this.row2 = Integer.valueOf(tokenB).intValue();
/*     */ 
/*     */         
/* 212 */         tokenA = st.nextToken();
/* 213 */         tokenB = st.nextToken();
/*     */       }
/* 215 */       catch (NumberFormatException error) {
/*     */         
/* 217 */         this.col2 = this.col1;
/* 218 */         this.row2 = this.row1;
/*     */       } 
/*     */ 
/*     */       
/* 222 */       if (tokenA.equalsIgnoreCase("L") || tokenA.equalsIgnoreCase("LEFT")) {
/* 223 */         this.hAlign = 0;
/* 224 */       } else if (tokenA.equalsIgnoreCase("C") || 
/* 225 */         tokenA.equalsIgnoreCase("CENTER")) {
/* 226 */         this.hAlign = 1;
/* 227 */       } else if (tokenA.equalsIgnoreCase("F") || 
/* 228 */         tokenA.equalsIgnoreCase("FULL")) {
/* 229 */         this.hAlign = 2;
/* 230 */       } else if (tokenA.equalsIgnoreCase("R") || 
/* 231 */         tokenA.equalsIgnoreCase("RIGHT")) {
/* 232 */         this.hAlign = 3;
/* 233 */       } else if (tokenA.equalsIgnoreCase("LD") || 
/* 234 */         tokenA.equalsIgnoreCase("LEADING")) {
/* 235 */         this.hAlign = 4;
/* 236 */       } else if (tokenA.equalsIgnoreCase("TL") || 
/* 237 */         tokenA.equalsIgnoreCase("TRAILING")) {
/* 238 */         this.hAlign = 5;
/*     */       } else {
/* 240 */         throw new RuntimeException();
/*     */       } 
/*     */       
/* 243 */       if (tokenB.equalsIgnoreCase("T") || tokenB.equalsIgnoreCase("TOP")) {
/* 244 */         this.vAlign = 0;
/* 245 */       } else if (tokenB.equalsIgnoreCase("C") || 
/* 246 */         tokenB.equalsIgnoreCase("CENTER")) {
/* 247 */         this.vAlign = 1;
/* 248 */       } else if (tokenB.equalsIgnoreCase("F") || 
/* 249 */         tokenB.equalsIgnoreCase("FULL")) {
/* 250 */         this.vAlign = 2;
/* 251 */       } else if (tokenB.equalsIgnoreCase("B") || 
/* 252 */         tokenB.equalsIgnoreCase("BOTTOM")) {
/* 253 */         this.vAlign = 3;
/*     */       } else {
/* 255 */         throw new RuntimeException();
/*     */       }  }
/* 257 */     catch (NoSuchElementException noSuchElementException) {  }
/* 258 */     catch (RuntimeException error)
/*     */     
/* 260 */     { throw new IllegalArgumentException(
/* 261 */           "Expected constraints in one of the following formats:\n  col1, row1\n  col1, row1, col2, row2\n  col1, row1, hAlign, vAlign\n  col1, row1, col2, row2, hAlign, vAlign\nConstraints provided '" + 
/*     */ 
/*     */ 
/*     */           
/* 265 */           constraints + "'"); }
/*     */ 
/*     */ 
/*     */     
/* 269 */     if (this.row2 < this.row1) {
/* 270 */       this.row2 = this.row1;
/*     */     }
/*     */     
/* 273 */     if (this.col2 < this.col1) {
/* 274 */       this.col2 = this.col1;
/*     */     }
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
/*     */   public String toString() {
/* 288 */     StringBuilder buffer = new StringBuilder();
/*     */     
/* 290 */     buffer.append(this.col1);
/* 291 */     buffer.append(", ");
/* 292 */     buffer.append(this.row1);
/* 293 */     buffer.append(", ");
/*     */     
/* 295 */     buffer.append(this.col2);
/* 296 */     buffer.append(", ");
/* 297 */     buffer.append(this.row2);
/* 298 */     buffer.append(", ");
/*     */     
/* 300 */     String[] h = { "left", "center", "full", "right", "leading", 
/* 301 */         "trailing" };
/* 302 */     String[] v = { "top", "center", "full", "bottom" };
/*     */     
/* 304 */     buffer.append(h[this.hAlign]);
/* 305 */     buffer.append(", ");
/* 306 */     buffer.append(v[this.vAlign]);
/*     */     
/* 308 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\info\clearthought\layout\TableLayoutConstraints.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */