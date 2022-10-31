/*     */ package com.lbs.localization;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import org.apache.lucene.analysis.Analyzer;
/*     */ import org.apache.lucene.analysis.standard.StandardAnalyzer;
/*     */ import org.apache.lucene.document.Document;
/*     */ import org.apache.lucene.document.Field;
/*     */ import org.apache.lucene.document.IntPoint;
/*     */ import org.apache.lucene.document.LongPoint;
/*     */ import org.apache.lucene.document.StoredField;
/*     */ import org.apache.lucene.document.StringField;
/*     */ import org.apache.lucene.document.TextField;
/*     */ import org.apache.lucene.index.DirectoryReader;
/*     */ import org.apache.lucene.index.IndexReader;
/*     */ import org.apache.lucene.index.IndexWriter;
/*     */ import org.apache.lucene.index.IndexWriterConfig;
/*     */ import org.apache.lucene.index.IndexableField;
/*     */ import org.apache.lucene.index.LeafReaderContext;
/*     */ import org.apache.lucene.index.Term;
/*     */ import org.apache.lucene.queryparser.classic.ParseException;
/*     */ import org.apache.lucene.queryparser.classic.QueryParser;
/*     */ import org.apache.lucene.search.BooleanClause;
/*     */ import org.apache.lucene.search.BooleanQuery;
/*     */ import org.apache.lucene.search.Collector;
/*     */ import org.apache.lucene.search.IndexSearcher;
/*     */ import org.apache.lucene.search.LeafCollector;
/*     */ import org.apache.lucene.search.Query;
/*     */ import org.apache.lucene.search.Scorable;
/*     */ import org.apache.lucene.search.ScoreDoc;
/*     */ import org.apache.lucene.search.ScoreMode;
/*     */ import org.apache.lucene.search.TermQuery;
/*     */ import org.apache.lucene.search.TopDocs;
/*     */ import org.apache.lucene.search.TopScoreDocCollector;
/*     */ import org.apache.lucene.store.Directory;
/*     */ import org.apache.lucene.store.FSDirectory;
/*     */ import org.apache.lucene.util.BytesRefBuilder;
/*     */ import org.apache.lucene.util.Version;
/*     */ 
/*     */ public class LuceneConnection
/*     */ {
/*  48 */   private static final Version LUCENE_VERSION = Version.LUCENE_8_4_0;
/*     */   
/*     */   private static final String PRIMARYKEY_FIELD = "#_pk_#";
/*     */   
/*     */   public static final String CATALOG_FIELD = "#_ctlg_#";
/*     */   
/*     */   private final Hashtable<String, IndexWriter> writers;
/*     */   
/*     */   private final Hashtable<String, IndexSearcher> searchers;
/*     */   private final String folder;
/*     */   private final String index;
/*     */   
/*     */   public String getFolder() {
/*  61 */     return this.folder;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIndex() {
/*  66 */     return this.index;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPath() {
/*  71 */     return String.valueOf(this.folder) + "/" + this.index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LuceneConnection(String folder, String index) {
/*  77 */     this.folder = folder;
/*  78 */     this.index = index;
/*  79 */     this.writers = new Hashtable<>();
/*  80 */     this.searchers = new Hashtable<>();
/*     */   }
/*     */ 
/*     */   
/*     */   public IndexWriter getWriter(String group) throws IOException {
/*  85 */     IndexWriter writer = this.writers.get(group);
/*  86 */     if (writer == null) {
/*     */       
/*  88 */       IndexWriterConfig iwc = new IndexWriterConfig((Analyzer)new StandardAnalyzer());
/*  89 */       File f = new File(String.valueOf(this.folder) + "/" + this.index + "/" + group.toString());
/*  90 */       f.mkdirs();
/*  91 */       FSDirectory fSDirectory = FSDirectory.open(f.toPath());
/*  92 */       writer = new IndexWriter((Directory)fSDirectory, iwc);
/*  93 */       this.writers.put(group, writer);
/*     */     } 
/*  95 */     return writer;
/*     */   }
/*     */ 
/*     */   
/*     */   public IndexSearcher getSearcher(String group) throws IOException {
/* 100 */     IndexSearcher searcher = this.searchers.get(group);
/* 101 */     if (searcher == null) {
/*     */       
/* 103 */       File f = new File(String.valueOf(this.folder) + "/" + this.index + "/" + group.toString());
/* 104 */       if (!f.exists())
/* 105 */         return null; 
/* 106 */       FSDirectory fSDirectory = FSDirectory.open(f.toPath());
/* 107 */       DirectoryReader reader = DirectoryReader.open((Directory)fSDirectory);
/* 108 */       searcher = new IndexSearcher((IndexReader)reader);
/* 109 */       this.searchers.put(group, searcher);
/*     */     } 
/* 111 */     return searcher;
/*     */   }
/*     */ 
/*     */   
/*     */   private Query createSingleFieldQuery(Enum catalog, Enum field, Serializable value) {
/* 116 */     TermQuery termQuery1, contentFilter = new TermQuery(new Term("#_ctlg_#", catalog.toString()));
/* 117 */     Term term = null;
/* 118 */     Query filter = null;
/* 119 */     if (value instanceof Integer) {
/*     */       
/* 121 */       BytesRefBuilder brb = new BytesRefBuilder();
/* 122 */       brb.grow(4);
/* 123 */       brb.setLength(4);
/* 124 */       filter = IntPoint.newExactQuery(field.toString(), ((Integer)value).intValue());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 130 */     else if (value instanceof Long) {
/*     */       
/* 132 */       BytesRefBuilder brb = new BytesRefBuilder();
/* 133 */       brb.grow(8);
/* 134 */       brb.setLength(8);
/* 135 */       filter = LongPoint.newExactQuery(field.toString(), ((Long)value).intValue());
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 142 */       term = new Term(field.toString(), value.toString());
/* 143 */       termQuery1 = new TermQuery(term);
/*     */     } 
/*     */     
/* 146 */     BooleanQuery query = (new BooleanQuery.Builder()).add((Query)contentFilter, BooleanClause.Occur.MUST)
/* 147 */       .add((Query)termQuery1, BooleanClause.Occur.MUST).build();
/*     */ 
/*     */     
/* 150 */     return (Query)query;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Document> searchDocuments(String group, Enum catalog, StringBuilder queryString) throws IOException, ParseException {
/* 155 */     queryString.append((queryString.length() > 0) ? 
/* 156 */         " AND " : 
/* 157 */         "").append("#_ctlg_#").append(":").append(catalog.toString());
/* 158 */     QueryParser parser = new QueryParser("#_ctlg_#", (Analyzer)new StandardAnalyzer());
/* 159 */     Query query = parser.parse(queryString.toString());
/* 160 */     return executeQuery(group, query);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteDocuments(String group, Enum catalog, Enum field, Serializable value) throws IOException {
/* 165 */     Query query = createSingleFieldQuery(catalog, field, value);
/* 166 */     getWriter(group).deleteDocuments(new Query[] { query });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Document> findDocuments(String group, Enum catalog, Enum field, Serializable value) throws IOException, ParseException {
/* 172 */     Query query = createSingleFieldQuery(catalog, field, value);
/* 173 */     return executeQuery(group, query);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Document> executeQuery(String group, Query query) throws IOException {
/* 178 */     TopScoreDocCollector coll = TopScoreDocCollector.create(10000, 2147483647);
/* 179 */     IndexSearcher searcher = getSearcher(group);
/* 180 */     if (searcher == null)
/* 181 */       return null; 
/* 182 */     searcher.search(query, (Collector)coll);
/* 183 */     if (coll.getTotalHits() == 0)
/* 184 */       return null; 
/* 185 */     TopDocs docs = coll.topDocs();
/* 186 */     List<Document> list = new ArrayList<>(); byte b; int i; ScoreDoc[] arrayOfScoreDoc;
/* 187 */     for (i = (arrayOfScoreDoc = docs.scoreDocs).length, b = 0; b < i; ) { ScoreDoc scoreDoc = arrayOfScoreDoc[b];
/* 188 */       list.add(getSearcher(group).doc(scoreDoc.doc)); b++; }
/* 189 */      return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public int executeCountQuery(String group, Query query) throws IOException {
/* 194 */     final ThreadLocal<Integer> weak = new ThreadLocal<>();
/* 195 */     weak.set(Integer.valueOf(-1));
/* 196 */     IndexSearcher searcher = getSearcher(group);
/* 197 */     searcher.search(query, new Collector()
/*     */         {
/* 199 */           int count = 0;
/*     */ 
/*     */ 
/*     */           
/*     */           public LeafCollector getLeafCollector(LeafReaderContext context) throws IOException {
/* 204 */             return new LeafCollector()
/*     */               {
/*     */                 public void setScorer(Scorable scorer) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 public void collect(int doc) throws IOException {
/* 215 */                   weak.set(Integer.valueOf(++LuceneConnection.null.this.count));
/*     */                 }
/*     */               };
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public ScoreMode scoreMode() {
/* 224 */             return null;
/*     */           }
/*     */         });
/* 227 */     Integer found = weak.get();
/* 228 */     weak.set(null);
/* 229 */     return found.intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer executeMinQuery(String group, Query query, final Enum minFieldName) throws IOException {
/* 234 */     final ThreadLocal<Integer> weak = new ThreadLocal<>();
/* 235 */     weak.set(Integer.valueOf(2147483647));
/* 236 */     final IndexSearcher searcher = getSearcher(group);
/* 237 */     searcher.search(query, new Collector()
/*     */         {
/*     */           
/*     */           public LeafCollector getLeafCollector(LeafReaderContext context) throws IOException
/*     */           {
/* 242 */             return new LeafCollector()
/*     */               {
/*     */ 
/*     */                 
/*     */                 public void collect(int doc) throws IOException
/*     */                 {
/* 248 */                   Document document = searcher.doc(doc);
/* 249 */                   weak.set(Integer.valueOf(Math.min(((Integer)weak.get()).intValue(), document.getField(minFieldName.toString()).numericValue().intValue())));
/*     */                 }
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 public void setScorer(Scorable scorer) throws IOException {}
/*     */               };
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public ScoreMode scoreMode() {
/* 262 */             return ScoreMode.COMPLETE;
/*     */           }
/*     */         });
/* 265 */     Integer found = weak.get();
/* 266 */     weak.set(null);
/* 267 */     return found;
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer executeMaxQuery(String group, Query query, final Enum minFieldName) throws IOException {
/* 272 */     final ThreadLocal<Integer> weak = new ThreadLocal<>();
/* 273 */     weak.set(Integer.valueOf(-2147483648));
/* 274 */     final IndexSearcher searcher = getSearcher(group);
/* 275 */     searcher.search(query, new Collector()
/*     */         {
/*     */           
/*     */           public LeafCollector getLeafCollector(LeafReaderContext context) throws IOException
/*     */           {
/* 280 */             return new LeafCollector()
/*     */               {
/*     */                 public void setScorer(Scorable scorer) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 public void collect(int doc) throws IOException {
/* 291 */                   Document document = searcher.doc(doc);
/* 292 */                   weak.set(Integer.valueOf(Math.max(((Integer)weak.get()).intValue(), document.getField(minFieldName.toString()).numericValue().intValue())));
/*     */                 }
/*     */               };
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public ScoreMode scoreMode() {
/* 300 */             return ScoreMode.COMPLETE;
/*     */           }
/*     */         });
/* 303 */     Integer found = weak.get();
/* 304 */     weak.set(null);
/* 305 */     return found;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws IOException, ParseException {
/* 310 */     LuceneConnection conn = new LuceneConnection("F:/apache-tomcat-7.0.47/webapps/logo/resources/Database", "ENUS");
/* 311 */     List<Document> list = conn.findDocuments("UN", ILocalizationConstants.Catalogs.resources, 
/* 312 */         ILocalizationConstants.ResourceFields.resourceNumber, Integer.valueOf(1070990));
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
/* 351 */     if (list == null)
/*     */       return; 
/* 353 */     for (Document doc : list)
/*     */     {
/* 355 */       System.out.println(doc);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Document createDocument(Enum catalog) {
/* 361 */     Document doc = new Document();
/* 362 */     StringField field = new StringField("#_ctlg_#", catalog.toString(), Field.Store.YES);
/* 363 */     doc.add((IndexableField)field);
/* 364 */     return doc;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addFieldInt(Document doc, Enum name, int value) {
/* 369 */     IntPoint field = new IntPoint(name.toString(), new int[] { value });
/* 370 */     StoredField stored = new StoredField(name.toString(), value);
/* 371 */     addField(doc, (IndexableField)field);
/* 372 */     addField(doc, (IndexableField)stored);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addFieldTimestamp(Document doc, Enum name, Timestamp value) {
/* 377 */     LongPoint field = new LongPoint(name.toString(), new long[] { value.getTime() });
/* 378 */     StoredField stored = new StoredField(name.toString(), value.getTime());
/* 379 */     addField(doc, (IndexableField)field);
/* 380 */     addField(doc, (IndexableField)stored);
/*     */   }
/*     */ 
/*     */   
/*     */   private void addField(Document doc, IndexableField field) {
/* 385 */     doc.add(field);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addFieldString(Document doc, Enum name, String value) {
/* 390 */     StringField field = new StringField(name.toString(), value, Field.Store.YES);
/* 391 */     addField(doc, (IndexableField)field);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addFieldText(Document doc, Enum name, String value) {
/* 396 */     TextField field = new TextField(name.toString(), value, Field.Store.YES);
/* 397 */     addField(doc, (IndexableField)field);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addFieldByteArray(Document doc, Enum name, byte[] serialized) {
/* 402 */     StoredField field = new StoredField(name.toString(), serialized);
/* 403 */     addField(doc, (IndexableField)field);
/*     */   }
/*     */ 
/*     */   
/*     */   public String add(String group, Document doc) throws IOException {
/* 408 */     String pk = UUID.randomUUID().toString();
/* 409 */     StringField pkfield = new StringField("#_pk_#", pk, Field.Store.YES);
/* 410 */     doc.add((IndexableField)pkfield);
/* 411 */     getWriter(group).addDocument((Iterable)doc);
/* 412 */     return pk;
/*     */   }
/*     */ 
/*     */   
/*     */   public void rollback() throws IOException {
/* 417 */     for (String group : this.writers.keySet())
/* 418 */       ((IndexWriter)this.writers.get(group)).rollback(); 
/* 419 */     this.searchers.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void commit() throws IOException {
/* 424 */     for (String group : this.writers.keySet())
/* 425 */       ((IndexWriter)this.writers.get(group)).commit(); 
/* 426 */     this.searchers.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 431 */     for (String group : this.writers.keySet())
/* 432 */       ((IndexWriter)this.writers.get(group)).close(); 
/* 433 */     this.writers.clear();
/* 434 */     for (String group : this.searchers.keySet())
/* 435 */       ((IndexSearcher)this.searchers.get(group)).getIndexReader().close(); 
/* 436 */     this.searchers.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\localization\LuceneConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */