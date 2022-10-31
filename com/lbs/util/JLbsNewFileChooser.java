/*     */ package com.lbs.util;
/*     */ 
/*     */ import com.lbs.controls.JLbsSwingUtilities;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.io.File;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.event.DocumentEvent;
/*     */ import javax.swing.event.DocumentListener;
/*     */ import javax.swing.event.ListDataEvent;
/*     */ import javax.swing.event.ListDataListener;
/*     */ import javax.swing.plaf.basic.BasicDirectoryModel;
/*     */ import javax.swing.plaf.basic.BasicFileChooserUI;
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
/*     */ public class JLbsNewFileChooser
/*     */   extends JFileChooser
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   class TypeAheadSelector
/*     */     extends KeyAdapter
/*     */     implements PropertyChangeListener, DocumentListener
/*     */   {
/*     */     private Vector m_Files;
/*     */     private JTextField m_Editor;
/*     */     
/*     */     public TypeAheadSelector() {
/*  68 */       setListDataListener();
/*  69 */       this.m_Editor = JLbsNewFileChooser.this.findEditor(JLbsNewFileChooser.this);
/*  70 */       this.m_Editor.getDocument().addDocumentListener(this);
/*  71 */       this.m_Editor.addKeyListener(this);
/*     */     }
/*     */ 
/*     */     
/*     */     public void keyPressed(KeyEvent e) {
/*  76 */       int code = e.getKeyCode();
/*  77 */       if (e.isControlDown() && (
/*  78 */         code == 10 || code == 79)) {
/*     */         
/*  80 */         File f = JLbsNewFileChooser.this.getSelectedFile();
/*  81 */         if (f != null) {
/*     */           
/*  83 */           this.m_Editor.setText(f.getName());
/*  84 */           e.consume();
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void changedUpdate(DocumentEvent e) {
/*  91 */       changed();
/*     */     }
/*     */ 
/*     */     
/*     */     public void insertUpdate(DocumentEvent e) {
/*  96 */       changed();
/*     */     }
/*     */ 
/*     */     
/*     */     public void removeUpdate(DocumentEvent e) {
/* 101 */       changed();
/*     */     }
/*     */ 
/*     */     
/*     */     private void changed() {
/* 106 */       final String text = this.m_Editor.getText();
/* 107 */       if (text == null || text.length() == 0)
/* 108 */         return;  File file = JLbsNewFileChooser.this.getSelectedFile();
/* 109 */       if (file != null && file.getName().compareToIgnoreCase(text) == 0)
/*     */         return; 
/* 111 */       String key = text.toUpperCase();
/* 112 */       for (int i = 0; i < this.m_Files.size(); i++) {
/*     */         
/* 114 */         final File item = this.m_Files.get(i);
/* 115 */         String name = item.getName().toUpperCase();
/* 116 */         if (name.startsWith(key)) {
/*     */           
/* 118 */           JLbsSwingUtilities.invokeLater(this, new Runnable()
/*     */               {
/*     */                 public void run()
/*     */                 {
/* 122 */                   JLbsNewFileChooser.TypeAheadSelector.this.m_Editor.getDocument().removeDocumentListener(JLbsNewFileChooser.TypeAheadSelector.this);
/* 123 */                   JLbsNewFileChooser.TypeAheadSelector.access$2(JLbsNewFileChooser.TypeAheadSelector.this).setSelectedFile(item);
/* 124 */                   JLbsNewFileChooser.TypeAheadSelector.this.m_Editor.setText(text);
/* 125 */                   JLbsNewFileChooser.TypeAheadSelector.this.m_Editor.getDocument().addDocumentListener(JLbsNewFileChooser.TypeAheadSelector.this);
/*     */                 }
/*     */               });
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent e) {}
/*     */ 
/*     */     
/*     */     private void setListDataListener() {
/* 139 */       final BasicDirectoryModel model = ((BasicFileChooserUI)JLbsNewFileChooser.this.getUI()).getModel();
/* 140 */       model.addListDataListener(new ListDataListener()
/*     */           {
/*     */             public void contentsChanged(ListDataEvent lde)
/*     */             {
/* 144 */               Vector<File> buffer = model.getFiles();
/* 145 */               if (buffer.size() > 0)
/*     */               {
/* 147 */                 JLbsNewFileChooser.TypeAheadSelector.this.m_Files = buffer;
/*     */               }
/*     */             }
/*     */             
/*     */             public void intervalAdded(ListDataEvent lde) {}
/*     */             
/*     */             public void intervalRemoved(ListDataEvent lde) {}
/*     */           });
/*     */     }
/*     */   }
/*     */   
/*     */   public JLbsNewFileChooser() {
/*     */     init();
/*     */   }
/*     */   
/*     */   public JLbsNewFileChooser(String path) {
/*     */     super(path);
/*     */     init();
/*     */   }
/*     */   
/*     */   private void init() {}
/*     */   
/*     */   private JTextField findEditor(Component comp) {
/*     */     if (comp instanceof JTextField)
/*     */       return (JTextField)comp; 
/*     */     if (comp instanceof Container) {
/*     */       Component[] components = ((Container)comp).getComponents();
/*     */       for (int i = 0; i < components.length; i++) {
/*     */         JTextField child = findEditor(components[i]);
/*     */         if (child != null)
/*     */           return child; 
/*     */       } 
/*     */     } 
/*     */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lb\\util\JLbsNewFileChooser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */