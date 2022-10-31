/*     */ package com.lbs.unity.main;
/*     */ 
/*     */ import com.lbs.console.LbsConsole;
/*     */ import com.lbs.customization.report.customize.ILbsReportCustomizerHostListener;
/*     */ import com.lbs.customization.report.customize.JLbsCustomReportInfo;
/*     */ import com.lbs.customization.report.customize.JLbsCustomReportListEntry;
/*     */ import com.lbs.customization.report.customize.JLbsCustomReportListEntryArray;
/*     */ import com.lbs.customization.report.customize.JLbsCustomReportListing;
/*     */ import com.lbs.customization.report.customize.ReportCustomizeWindow;
/*     */ import com.lbs.customization.report.customize.dotmatrix.DotMatrixDesignerSettings;
/*     */ import com.lbs.customization.report.design.JLbsReportDesignerWrapper;
/*     */ import com.lbs.customization.report.designer.JLbsReportDesigner;
/*     */ import com.lbs.data.factory.FactoryParams;
/*     */ import com.lbs.platform.interfaces.IApplicationContext;
/*     */ import com.lbs.remoteclient.JLbsClientPanel;
/*     */ import com.lbs.remoteclient.JLbsRunContextParameters;
/*     */ import com.lbs.reporting.ClientReportingServices;
/*     */ import com.lbs.start.JLbsStartup;
/*     */ import com.lbs.transport.UserInfo;
/*     */ import com.lbs.util.JLbsFrame;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.JApplet;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UnityClientReportDesignerMainForm
/*     */   extends JLbsClientPanel
/*     */ {
/*     */   ReportCustomizeWindow m_CustomizeWindow;
/*     */   private JLbsRunContextParameters m_CtxParams;
/*     */   private int m_ReportSavedLRef;
/*     */   private String m_LockId;
/*     */   private JLbsCustomReportListEntry entry;
/*     */   private String m_LastSavedReportName;
/*     */   private String m_ReportName;
/*     */   private String m_ReportLang;
/*     */   private int m_LRef;
/*  47 */   protected int m_SortbyName = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JLbsCustomReportInfo m_ReportInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initMainForm() throws Exception {
/*  58 */     designReport();
/*     */   }
/*     */ 
/*     */   
/*     */   private void designReport() throws Exception {
/*  63 */     UnityClientReportDesigner application = UnityClientReportDesigner.getApplication();
/*  64 */     this.m_LRef = application.getLRef();
/*  65 */     this.m_ReportName = application.getReportName();
/*  66 */     this.m_ReportLang = application.getReportLanguage();
/*     */     
/*  68 */     UserInfo userInfo = this.m_Context.getUserInfo();
/*  69 */     if (userInfo != null)
/*     */     {
/*  71 */       userInfo.selectedLanguage = this.m_ReportLang;
/*     */     }
/*     */     
/*  74 */     JLbsCustomReportInfo result = ClientReportingServices.getCustomizationListEx((IApplicationContext)this.m_Context, this.m_ReportName, this.m_ReportLang, 0);
/*     */ 
/*     */ 
/*     */     
/*  78 */     if (this.m_LRef > 0) {
/*     */       
/*  80 */       JLbsCustomReportListEntryArray array = result.Customizations;
/*  81 */       for (int i = 0; i < array.size(); i++) {
/*     */         
/*  83 */         JLbsCustomReportListEntry entry = array.getEntry(i);
/*  84 */         if (entry != null && entry.LRef == this.m_LRef) {
/*     */           
/*  86 */           this.entry = entry;
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } else {
/*  93 */       this.m_LRef = 0;
/*     */     } 
/*     */     
/*  96 */     this.m_ReportInfo = ClientReportingServices.getCustomReportInfo((IApplicationContext)this.m_Context, this.m_ReportName, this.m_ReportLang, this.m_LRef);
/*  97 */     designReport(true, result);
/*     */   }
/*     */ 
/*     */   
/*     */   private void close() {
/* 102 */     JFrame frame = getParentFrame();
/* 103 */     if (frame != null)
/*     */     {
/* 105 */       frame.dispatchEvent(new WindowEvent(frame, 201));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void sortEntryList(ArrayList<?> list) {
/*     */     try {
/* 113 */       Comparator<?> entryListComparator = new Comparator()
/*     */         {
/*     */           public int compare(Object o1, Object o2)
/*     */           {
/* 117 */             if (o1 instanceof JLbsCustomReportListEntry && o2 instanceof JLbsCustomReportListEntry) {
/*     */               
/* 119 */               JLbsCustomReportListEntry Def1 = (JLbsCustomReportListEntry)o1;
/* 120 */               JLbsCustomReportListEntry Def2 = (JLbsCustomReportListEntry)o2;
/*     */               
/* 122 */               if (UnityClientReportDesignerMainForm.this.m_SortbyName == 1) {
/* 123 */                 return Def1.getName().compareToIgnoreCase(Def2.getName());
/*     */               }
/*     */               
/* 126 */               return Def1.getDate().compareTo(Def2.getDate());
/*     */             } 
/*     */             
/* 129 */             return -1;
/*     */           }
/*     */         };
/*     */       
/* 133 */       Collections.sort(list, entryListComparator);
/*     */     }
/* 135 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JList createListItems(JLbsCustomReportListEntryArray list, JLbsCustomReportListing listing) {
/* 142 */     sortEntryList((ArrayList)list);
/*     */     
/* 144 */     DefaultListModel<JLbsCustomReportListEntry> model = new DefaultListModel();
/* 145 */     if (list != null)
/* 146 */       for (int i = 0; i < list.size(); i++) {
/*     */         
/* 148 */         JLbsCustomReportListEntry entry = list.getEntry(i);
/* 149 */         if (entry != null)
/*     */         {
/* 151 */           model.addElement(entry); } 
/*     */       }  
/* 153 */     return new JList<>(model);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setListingField(JLbsCustomReportListing listing, String fieldName, Object fieldValue) throws Exception {
/* 158 */     Field field = listing.getClass().getDeclaredField(fieldName);
/* 159 */     field.setAccessible(true);
/* 160 */     field.set(listing, fieldValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void designReport(boolean loadCtrls, JLbsCustomReportInfo info) throws Exception {
/* 167 */     JLbsCustomReportListing listing = new JLbsCustomReportListing(true, true, true, false, null, this.m_Context);
/* 168 */     if (this.entry == null) {
/*     */       
/* 170 */       JLbsCustomReportListing.JLbsNewReportDlg dlg = listing.createNewReportDlg();
/* 171 */       int selectedValue = dlg.getReportType();
/* 172 */       DotMatrixDesignerSettings dotMatrixSettings = dlg.getDotMatrixSettings();
/*     */       
/* 174 */       switch (selectedValue) {
/*     */         
/*     */         case 1:
/* 177 */           loadCtrls = true;
/*     */           break;
/*     */         case 3:
/* 180 */           loadCtrls = false;
/*     */           break;
/*     */         default:
/* 183 */           close();
/*     */           break;
/*     */       } 
/* 186 */       this.m_ReportInfo.LayoutXML = JLbsCustomReportListing.addDotMatrixSettingsToDocument(this.m_ReportInfo.LayoutXML, dotMatrixSettings);
/*     */ 
/*     */       
/* 189 */       this.entry = new JLbsCustomReportListEntry();
/* 190 */       this.entry.LRef = this.m_LRef;
/*     */     } 
/*     */     
/* 193 */     JList list = createListItems(info.Customizations, listing);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 199 */     listing.setClientCtx(this.m_Context);
/* 200 */     listing.setList(list);
/* 201 */     listing.setReportName(this.m_ReportName);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 209 */     this.m_Context.loadJAR("CustomReportDesigner.jar", false, true);
/* 210 */     this.m_Context.loadJAR("xercesImpl.jar", false, true);
/*     */     
/* 212 */     JLbsCustomReportInfo reportInfo = this.m_ReportInfo;
/*     */     
/* 214 */     setEnabled(false);
/* 215 */     this.m_ReportSavedLRef = -1;
/* 216 */     this.m_CustomizeWindow = new ReportCustomizeWindow("ERP Report Designer", reportInfo.LayoutXML, this.m_Context);
/*     */ 
/*     */     
/* 219 */     this.m_CustomizeWindow.setFormListener(new LbsReportCustomizerHostListener(listing));
/* 220 */     this.m_CustomizeWindow.setReportResource(reportInfo.RepResource);
/* 221 */     this.m_CustomizeWindow.loadLayout(this.entry, reportInfo.LayoutXML, loadCtrls, (reportInfo != null) ? reportInfo.Fields : null);
/*     */ 
/*     */     
/* 224 */     if (this.m_CtxParams != null) {
/*     */       
/* 226 */       this.m_CustomizeWindow.filterFilterList(this.m_CtxParams.HiddenFilters);
/*     */       
/* 228 */       if (this.m_CtxParams.RunContextListener != null) {
/*     */         
/* 230 */         this.m_CtxParams.RunContextListener.beforeDesign(this.m_CtxParams, this.m_Context);
/* 231 */         this.m_CustomizeWindow.filterFunctions(this.m_CtxParams.FilteredFunctions, this.m_CtxParams.FilteredFunctionSets);
/*     */       } 
/*     */     } 
/*     */     
/* 235 */     JFrame frame = getParentFrame();
/* 236 */     if (frame != null)
/*     */     {
/* 238 */       frame.setVisible(false);
/*     */     }
/*     */     
/* 241 */     setVisible(false);
/* 242 */     UnityClientReportDesigner.getApplication().setVisible(false);
/* 243 */     UnityClientReportDesigner.getApplication().getBaseApplet().setVisible(false);
/*     */     
/* 245 */     this.m_CustomizeWindow.addWindowListener(new DesignWindowListener());
/*     */     
/* 247 */     this.m_CustomizeWindow.setModal(true);
/*     */     
/* 249 */     this.m_CustomizeWindow.show();
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
/*     */   private void releaseReportDefinition() {
/* 263 */     if (this.m_LRef <= 0)
/*     */       return; 
/* 265 */     FactoryParams params = new FactoryParams();
/* 266 */     params.getVariables().put("key", Integer.valueOf(this.m_LRef));
/*     */     
/*     */     try {
/* 269 */       this.m_Context.getObjectFactory().releaseLock(params, "$X(CustReports)-$V(key)", this.m_LockId);
/*     */     }
/* 271 */     catch (Exception e) {
/*     */       
/* 273 */       LbsConsole.getLogger(getClass()).error(e, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void refreshCustomizationListing(int lref) {}
/*     */ 
/*     */   
/*     */   private JFrame getParentFrame() {
/*     */     JLbsFrame jLbsFrame;
/* 284 */     JApplet applet = UnityClientReportDesigner.getApplication().getBaseApplet();
/*     */     
/* 286 */     if (applet instanceof JLbsStartup) {
/*     */       
/* 288 */       JLbsStartup startup = (JLbsStartup)applet;
/* 289 */       JFrame frame = startup.getFrame();
/*     */     }
/*     */     else {
/*     */       
/* 293 */       jLbsFrame = UnityClientReportDesigner.getFrame();
/*     */     } 
/* 295 */     return (JFrame)jLbsFrame;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   class DesignWindowListener
/*     */     extends WindowAdapter
/*     */   {
/*     */     public void windowClosed(WindowEvent e) {
/* 304 */       UnityClientReportDesignerMainForm.this.releaseReportDefinition();
/* 305 */       if (UnityClientReportDesignerMainForm.this.m_ReportSavedLRef > 0) {
/* 306 */         UnityClientReportDesignerMainForm.this.refreshCustomizationListing(UnityClientReportDesignerMainForm.this.m_ReportSavedLRef);
/*     */       }
/* 308 */       UnityClientReportDesignerMainForm.this.close();
/*     */     }
/*     */   }
/*     */   
/*     */   class LbsReportCustomizerHostListener
/*     */     implements ILbsReportCustomizerHostListener
/*     */   {
/*     */     private final JLbsCustomReportListing customReportListing;
/*     */     
/*     */     public LbsReportCustomizerHostListener(JLbsCustomReportListing listing) {
/* 318 */       this.customReportListing = listing;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void selectLastSavedReport() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getReportName() {
/* 330 */       return UnityClientReportDesignerMainForm.this.m_ReportName;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int saveReport(JLbsReportDesigner designer, JLbsReportDesignerWrapper wrapper, String xml, Object ctx, boolean force, boolean preview) {
/* 337 */       return this.customReportListing.saveReport(designer, wrapper, xml, ctx, force, preview);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\unity\main\UnityClientReportDesignerMainForm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */