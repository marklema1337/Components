/*     */ package com.lbs.controls;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsListBox;
/*     */ import com.lbs.recording.interfaces.ILbsListBoxRecordingEvents;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.datatransfer.StringSelection;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.Vector;
/*     */ import javax.swing.DefaultListSelectionModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.ListModel;
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
/*     */ public class JLbsListBox
/*     */   extends JList
/*     */   implements ActionListener, ILbsListBox, ILbsListBoxRecordingEvents
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int WARNING = 1;
/*     */   public static final int QUESTION = 2;
/*     */   public static final int INFORMATION = 3;
/*     */   public static final int ERROR = 4;
/*  50 */   public JLbsListBoxRenderer m_ListBoxRenderer = null;
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean m_ShowTooltips = true;
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsListBox() {
/*  59 */     setModel(new JLbsListBoxModel());
/*  60 */     this.m_ListBoxRenderer = new JLbsListBoxRenderer();
/*  61 */     setCellRenderer(this.m_ListBoxRenderer);
/*  62 */     KeyStroke copyKey = KeyStroke.getKeyStroke(67, 2, false);
/*  63 */     registerKeyboardAction(this, "copy", copyKey, 0);
/*  64 */     setSelectionModel(new DefaultListSelectionModel()
/*     */         {
/*     */           private static final long serialVersionUID = 1L;
/*     */ 
/*     */           
/*     */           public void setSelectionInterval(int index0, int index1) {
/*  70 */             super.setSelectionInterval(index0, index1);
/*  71 */             JLbsListBox.this.ensureIndexIsVisible(index0);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean clearItems() {
/*  81 */     ListModel<E> model = getModel();
/*  82 */     if (model instanceof JLbsListBoxModel) {
/*     */       
/*  84 */       ((JLbsListBoxModel)model).clear();
/*  85 */       repaint();
/*  86 */       return true;
/*     */     } 
/*  88 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelection(final int index) {
/*  93 */     JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/*  97 */             JLbsListBox.this.setSelectedIndex(index);
/*  98 */             JLbsListBox.this.recordSetSelectedIndex(index);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendDeleteKey() {
/* 105 */     KeyListener[] listeners = getKeyListeners();
/* 106 */     if (listeners != null && listeners.length > 0) {
/*     */       
/* 108 */       KeyEvent event = new KeyEvent(this, 0, 0L, 0, 127, '');
/* 109 */       for (int i = 0; i < listeners.length; i++) {
/* 110 */         listeners[i].keyPressed(event);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int addItem(String item) {
/* 119 */     ListModel<E> model = getModel();
/* 120 */     if (model instanceof JLbsListBoxModel)
/* 121 */       JLbsSwingUtilities.invokeLater(this, new AddItemJob((JLbsListBoxModel)model, new JLbsListBoxItem(item))); 
/* 122 */     return -1;
/*     */   }
/*     */   
/*     */   class AddItemJob
/*     */     implements Runnable
/*     */   {
/*     */     private JLbsListBoxModel model;
/*     */     private JLbsListBoxItem item;
/*     */     
/*     */     public AddItemJob(JLbsListBoxModel model, JLbsListBoxItem item) {
/* 132 */       this.model = model;
/* 133 */       this.item = item;
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/* 138 */       this.model.add(this.item);
/* 139 */       JLbsListBox.this.repaint();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setListData(Vector listData) {
/* 146 */     ListModel<E> model = getModel();
/* 147 */     if (model instanceof JLbsListBoxModel) {
/* 148 */       JLbsSwingUtilities.invokeLater(this, new SetListDataJob((JLbsListBoxModel)model, listData));
/*     */     }
/*     */   }
/*     */   
/*     */   class SetListDataJob
/*     */     implements Runnable
/*     */   {
/*     */     private JLbsListBoxModel model;
/*     */     private Vector listData;
/*     */     
/*     */     public SetListDataJob(JLbsListBoxModel model, Vector listData) {
/* 159 */       this.model = model;
/* 160 */       this.listData = listData;
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/* 165 */       for (int i = 0; i < this.listData.size(); i++) {
/* 166 */         if (this.listData.get(i) instanceof String)
/* 167 */           this.model.add(new JLbsListBoxItem(this.listData.get(i))); 
/* 168 */       }  JLbsListBox.this.repaint();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int addItem(String text, int imageIndex) {
/* 178 */     ListModel<E> model = getModel();
/* 179 */     if (model instanceof JLbsListBoxModel)
/* 180 */       JLbsSwingUtilities.invokeLater(this, new AddItemJob((JLbsListBoxModel)model, new JLbsListBoxItem(text, 
/* 181 */               getFixedImage(imageIndex), imageIndex))); 
/* 182 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int addItem(String text, ImageIcon img) {
/* 187 */     ListModel<E> model = getModel();
/* 188 */     if (model instanceof JLbsListBoxModel)
/* 189 */       JLbsSwingUtilities.invokeLater(this, new AddItemJob((JLbsListBoxModel)model, new JLbsListBoxItem(text, img))); 
/* 190 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidateSafe() {
/* 195 */     Runnable callRevalidate = new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 199 */           JLbsListBox.this.invalidate();
/*     */         }
/*     */       };
/* 202 */     JLbsSwingUtilities.invokeLater(this, callRevalidate);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ImageIcon getFixedImage(int index) {
/* 207 */     if (index >= 1 && index <= 4) {
/*     */       
/*     */       try {
/* 210 */         String[] names = { "Warning", "Question", "Confirmation", "Error" };
/* 211 */         String fileName = "../resource/" + names[index - 1] + ".gif";
/* 212 */         ImageIcon img = JLbsControlHelper.getImageIcon(JLbsListBox.class, fileName);
/* 213 */         return img;
/*     */       }
/* 215 */       catch (Exception exception) {}
/*     */     }
/*     */     
/* 218 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 223 */     if (e.getActionCommand().compareTo("copy") == 0) {
/*     */       
/* 225 */       Object[] values = getSelectedValues();
/* 226 */       StringBuilder buff = new StringBuilder();
/* 227 */       if (values != null)
/* 228 */         for (int i = 0; i < values.length; i++) {
/*     */           
/* 230 */           Object value = values[i];
/* 231 */           if (value instanceof JLbsListBoxItem) {
/* 232 */             buff.append(((JLbsListBoxItem)value).toCopyString());
/*     */           } else {
/* 234 */             buff.append(value.toString());
/* 235 */           }  buff.append("\n");
/*     */         }  
/* 237 */       StringSelection copyData = new StringSelection(buff.toString());
/* 238 */       Toolkit.getDefaultToolkit().getSystemClipboard().setContents(copyData, copyData);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getShowTooltips() {
/* 247 */     return this.m_ShowTooltips;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShowTooltips(boolean value) {
/* 255 */     this.m_ShowTooltips = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceIdentifier() {
/* 260 */     return JLbsComponentHelper.getResourceIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUniqueIdentifier() {
/* 265 */     return JLbsComponentHelper.getUniqueIdentifier((ILbsComponentBase)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void recordSetSelectedIndex(int index) {
/* 270 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "MOUSE_CLICKED");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordActionPerformed(ActionEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordKeyPressed(KeyEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordMouseClicked(MouseEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordRequestFocus() {}
/*     */ 
/*     */   
/*     */   public void setComponentOrientation(ComponentOrientation compOrientation) {
/* 291 */     this.m_ListBoxRenderer.setComponentOrientation(compOrientation);
/* 292 */     this.m_ListBoxRenderer.setHorizontalAlignment((compOrientation == ComponentOrientation.LEFT_TO_RIGHT) ? 
/* 293 */         2 : 
/* 294 */         4);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHaveLayoutManager() {
/* 299 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getParentComponent() {
/* 304 */     return getParent();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\JLbsListBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */