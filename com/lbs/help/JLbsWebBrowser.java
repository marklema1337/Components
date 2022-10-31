/*     */ package com.lbs.help;
/*     */ 
/*     */ import com.lbs.controls.JLbsControlHelper;
/*     */ import com.lbs.controls.groupbox.JLbsGroupBorder;
/*     */ import com.lbs.controls.groupbox.JLbsGroupBox;
/*     */ import com.lbs.controls.maskededit.JLbsTextEdit;
/*     */ import com.lbs.controls.toolbar.ILbsToolbarActionListener;
/*     */ import com.lbs.controls.toolbar.JLbsToolbar;
/*     */ import com.lbs.util.JLbsConstants;
/*     */ import com.lbs.util.JLbsFrame;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JEditorPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.event.HyperlinkEvent;
/*     */ import javax.swing.event.HyperlinkListener;
/*     */ import javax.swing.text.Document;
/*     */ import javax.swing.text.html.HTMLDocument;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsWebBrowser
/*     */   extends JLbsFrame
/*     */   implements HyperlinkListener, ILbsToolbarActionListener, PropertyChangeListener
/*     */ {
/*  42 */   public static String ms_Title = "LBS Web Browser";
/*  43 */   public static String ms_BackCaption = "Back";
/*  44 */   public static String ms_ForwardCaption = "Forward";
/*  45 */   public static String ms_RefreshCaption = "Refresh";
/*  46 */   public static String ms_CloseCaption = "Close";
/*  47 */   public static String ms_LocationCaption = "Location";
/*     */   
/*     */   private String m_BrowserName;
/*     */   
/*     */   private JLbsToolbar m_Toolbar;
/*     */   private JButton m_BackButton;
/*     */   private JButton m_ForwardButton;
/*     */   private JButton m_RefreshButton;
/*     */   private JLbsTextEdit m_Location;
/*     */   private JEditorPane m_EditorPane;
/*  57 */   private ArrayList m_BackList = new ArrayList();
/*  58 */   private ArrayList m_ForwardList = new ArrayList();
/*     */   
/*     */   private String m_CurrentPage;
/*     */   private boolean m_InternalLoading;
/*     */   
/*     */   public JLbsWebBrowser() {
/*  64 */     this((String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLbsWebBrowser(String name) {
/*  69 */     this.m_BrowserName = name;
/*  70 */     setTitle(ms_Title);
/*     */     
/*  72 */     JPanel contPanel = new JPanel();
/*  73 */     contPanel.setLayout(new BorderLayout());
/*  74 */     setContentPane(contPanel);
/*     */     
/*  76 */     this.m_Toolbar = new JLbsToolbar();
/*  77 */     this.m_Toolbar.setToolbarActionListener(this);
/*  78 */     this.m_BackButton = this.m_Toolbar.createToolButton(getImageIcon("back.png"), ms_BackCaption, 100);
/*  79 */     this.m_ForwardButton = this.m_Toolbar.createToolButton(getImageIcon("forward.png"), ms_ForwardCaption, 101);
/*  80 */     this.m_RefreshButton = this.m_Toolbar.createToolButton(getImageIcon("refresh.png"), ms_RefreshCaption, 102);
/*  81 */     this.m_Toolbar.addSeparator();
/*  82 */     this.m_Toolbar.createToolButton(getImageIcon("stop.png"), ms_CloseCaption, 103);
/*  83 */     this.m_Toolbar.addSeparator();
/*  84 */     JLbsGroupBox editPanel = new JLbsGroupBox();
/*  85 */     editPanel.setLayout(new BorderLayout());
/*  86 */     editPanel.setBorder((Border)new JLbsGroupBorder());
/*  87 */     editPanel.setText(ms_LocationCaption);
/*  88 */     this.m_Location = new JLbsTextEdit();
/*  89 */     this.m_Location.setPreferredSize(new Dimension(200, 20));
/*  90 */     this.m_Location.setEditable(false);
/*  91 */     editPanel.add((Component)this.m_Location, "Center");
/*  92 */     this.m_Toolbar.add((Component)editPanel);
/*  93 */     this.m_Toolbar.setFloatable(false);
/*  94 */     contPanel.add((Component)this.m_Toolbar, "North");
/*     */     
/*  96 */     JScrollPane scrollPane = new JScrollPane();
/*  97 */     scrollPane.setAutoscrolls(true);
/*  98 */     this.m_EditorPane = new JEditorPane();
/*  99 */     this.m_EditorPane.setEditable(false);
/* 100 */     this.m_EditorPane.addHyperlinkListener(this);
/* 101 */     this.m_EditorPane.addPropertyChangeListener(this);
/* 102 */     scrollPane.setPreferredSize(new Dimension(480, 400));
/* 103 */     scrollPane.setViewportView(this.m_EditorPane);
/* 104 */     contPanel.add(scrollPane, "Center");
/*     */     
/* 106 */     checkButtonsEnabled();
/* 107 */     setDefaultCloseOperation(2);
/* 108 */     addEscapeCloseShortcut();
/* 109 */     pack();
/* 110 */     centerScreen();
/*     */   }
/*     */ 
/*     */   
/*     */   public void windowOpened(WindowEvent e) {
/* 115 */     super.windowOpened(e);
/* 116 */     if (this.m_BrowserName != null) {
/* 117 */       JLbsBrowserFactory.registerBrowser(this.m_BrowserName, this);
/*     */     }
/*     */   }
/*     */   
/*     */   public void windowClosed(WindowEvent e) {
/* 122 */     if (this.m_BrowserName != null)
/* 123 */       JLbsBrowserFactory.deregisterBrowser(this.m_BrowserName, this); 
/* 124 */     super.windowClosed(e);
/*     */   }
/*     */ 
/*     */   
/*     */   private ImageIcon getImageIcon(String fileName) {
/* 129 */     return JLbsControlHelper.getImageIcon(JLbsWebBrowser.class, fileName);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setPage(URL uRL) {
/* 134 */     if (uRL != null)
/* 135 */       return setPage(uRL.toExternalForm()); 
/* 136 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setPage(String URL) {
/* 141 */     if (this.m_EditorPane != null)
/*     */       
/*     */       try {
/* 144 */         if (JLbsConstants.LOGLEVEL > 4)
/* 145 */           System.out.println("Loading URL : " + URL); 
/* 146 */         this.m_EditorPane.setPage(URL);
/* 147 */         String lastPage = this.m_CurrentPage;
/* 148 */         this.m_CurrentPage = URL;
/*     */         
/* 150 */         this.m_Location.setText(this.m_CurrentPage);
/* 151 */         this.m_Location.setSelectionStart(0);
/* 152 */         this.m_Location.setSelectionEnd(0);
/* 153 */         if (!this.m_InternalLoading)
/* 154 */           addURLToHistory(lastPage); 
/* 155 */         checkButtonsEnabled();
/* 156 */         return true;
/*     */       }
/* 158 */       catch (Exception e) {
/*     */         
/* 160 */         if (JLbsConstants.DEBUG)
/* 161 */           System.out.println(e); 
/*     */       }  
/* 163 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean setPageText(String s) {
/*     */     try {
/* 170 */       this.m_EditorPane.setContentType("text/html");
/* 171 */       this.m_EditorPane.setText(s);
/* 172 */       return true;
/*     */     }
/* 174 */     catch (Exception exception) {
/*     */ 
/*     */       
/* 177 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void hyperlinkUpdate(HyperlinkEvent e) {
/* 182 */     if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
/*     */       
/* 184 */       URL targetURL = e.getURL();
/* 185 */       if (targetURL != null) {
/* 186 */         setPage(targetURL);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void propertyChange(PropertyChangeEvent evt) {
/* 192 */     if (evt.getPropertyName().equals("page")) {
/*     */       
/* 194 */       JEditorPane pane = (JEditorPane)evt.getSource();
/* 195 */       if (pane != null) {
/*     */         
/* 197 */         Document doc = pane.getDocument();
/* 198 */         if (doc instanceof HTMLDocument) {
/*     */           
/* 200 */           HTMLDocument htmlDoc = (HTMLDocument)doc;
/* 201 */           String title = (String)htmlDoc.getProperty("title");
/* 202 */           setPageTitle(title);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(JLbsToolbar toolbar, ActionEvent e, int tag) {
/* 210 */     switch (tag) {
/*     */       
/*     */       case 100:
/* 213 */         if (doBack()) checkButtonsEnabled();  break;
/*     */       case 101:
/* 215 */         if (doForward()) checkButtonsEnabled();  break;
/*     */       case 102:
/* 217 */         refresh(); break;
/*     */       case 103:
/* 219 */         dispose();
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void refresh() {
/* 225 */     if (!JLbsStringUtil.isEmpty(this.m_CurrentPage)) {
/*     */       
/* 227 */       this.m_InternalLoading = true;
/* 228 */       setPage(this.m_CurrentPage);
/* 229 */       this.m_InternalLoading = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean doBack() {
/* 235 */     if (this.m_BackList.size() > 0) {
/*     */       
/* 237 */       this.m_InternalLoading = true;
/* 238 */       int index = this.m_BackList.size() - 1;
/* 239 */       String url = this.m_BackList.get(index);
/* 240 */       boolean loaded = setPage(url);
/* 241 */       this.m_InternalLoading = false;
/* 242 */       if (loaded) {
/*     */         
/* 244 */         this.m_BackList.remove(index);
/* 245 */         this.m_ForwardList.add(url);
/*     */       } 
/* 247 */       return loaded;
/*     */     } 
/* 249 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean doForward() {
/* 254 */     if (this.m_ForwardList.size() > 0) {
/*     */       
/* 256 */       int index = this.m_ForwardList.size() - 1;
/* 257 */       String url = this.m_ForwardList.get(index);
/* 258 */       boolean loaded = setPage(url);
/* 259 */       if (loaded)
/* 260 */         this.m_ForwardList.remove(index); 
/* 261 */       return loaded;
/*     */     } 
/* 263 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setPageTitle(String title) {
/* 268 */     String newTitle = !JLbsStringUtil.isEmpty(title) ? (String.valueOf(ms_Title) + " - " + title) : ms_Title;
/* 269 */     setTitle(newTitle);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void checkButtonsEnabled() {
/* 274 */     if (this.m_BackButton != null)
/* 275 */       this.m_BackButton.setEnabled((this.m_BackList.size() > 0)); 
/* 276 */     if (this.m_ForwardButton != null)
/* 277 */       this.m_ForwardButton.setEnabled((this.m_ForwardList.size() > 0)); 
/* 278 */     if (this.m_RefreshButton != null) {
/* 279 */       this.m_RefreshButton.setEnabled(!JLbsStringUtil.isEmpty(this.m_CurrentPage));
/*     */     }
/*     */   }
/*     */   
/*     */   protected void addURLToHistory(String URL) {
/* 284 */     if (!JLbsStringUtil.isEmpty(URL)) {
/* 285 */       this.m_BackList.add(URL);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 290 */     JLbsWebBrowser browser = new JLbsWebBrowser();
/* 291 */     browser.setDefaultCloseOperation(3);
/* 292 */     browser.show();
/*     */     
/*     */     try {
/* 295 */       browser.setPage("http://www.yahoo.com");
/*     */     
/*     */     }
/* 298 */     catch (Exception exception) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\help\JLbsWebBrowser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */