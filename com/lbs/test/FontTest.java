/*     */ package com.lbs.test;
/*     */ 
/*     */ import info.clearthought.layout.TableLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.event.DocumentEvent;
/*     */ import javax.swing.event.DocumentListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FontTest
/*     */ {
/*     */   public static JPanel createContents() {
/*  25 */     JPanel contents = new JPanel();
/*  26 */     double[][] size = { { -2.0D, -1.0D
/*  27 */         }, { -2.0D, -2.0D, -2.0D, 
/*  28 */           -2.0D, -1.0D } };
/*     */     
/*  30 */     contents.setBorder(new EmptyBorder(8, 8, 8, 8));
/*  31 */     TableLayout layout = new TableLayout(size);
/*  32 */     layout.setHGap(6);
/*  33 */     contents.setLayout((LayoutManager)layout);
/*  34 */     contents.add(createLabel("Font Name"), "0, 0");
/*  35 */     contents.add(createLabel("Font Size"), "0, 1");
/*  36 */     contents.add(createLabel("Font Style"), "0, 2");
/*  37 */     contents.add(createLabel("Text"), "0, 3");
/*     */     
/*  39 */     final JComboBox fontName = createFontNameCombo();
/*  40 */     final JComboBox fontSize = createFontSizeCombo();
/*  41 */     final JComboBox fontStyle = createFontStyleCombo();
/*  42 */     final JTextField text = createTextEditor();
/*  43 */     contents.add(fontName, "1, 0");
/*  44 */     contents.add(fontSize, "1, 1");
/*  45 */     contents.add(fontStyle, "1, 2");
/*  46 */     contents.add(text, "1, 3");
/*     */     
/*  48 */     final FontTestPanel panel = new FontTestPanel();
/*  49 */     contents.add(panel, "0, 4, 1, 4");
/*     */     
/*  51 */     ActionListener listener = new ActionListener()
/*     */       {
/*     */         
/*     */         public void actionPerformed(ActionEvent e)
/*     */         {
/*     */           try {
/*  57 */             String fName = fontName.getSelectedItem().toString();
/*  58 */             int fSize = Integer.parseInt(fontSize.getSelectedItem().toString());
/*  59 */             int fStyle = 0;
/*  60 */             switch (fontStyle.getSelectedIndex()) {
/*     */               
/*     */               case 1:
/*  63 */                 fStyle = 1;
/*     */                 break;
/*     */               case 2:
/*  66 */                 fStyle = 2;
/*     */                 break;
/*     */               case 3:
/*  69 */                 fStyle = 3;
/*     */                 break;
/*     */             } 
/*  72 */             panel.setFont(new Font(fName, fStyle, fSize));
/*     */           }
/*  74 */           catch (Exception ex) {
/*     */             
/*  76 */             ex.printStackTrace();
/*     */           } 
/*     */         }
/*     */       };
/*     */     
/*  81 */     fontName.addActionListener(listener);
/*  82 */     fontSize.addActionListener(listener);
/*  83 */     fontStyle.addActionListener(listener);
/*  84 */     text.getDocument().addDocumentListener(new DocumentListener()
/*     */         {
/*     */           public void changedUpdate(DocumentEvent e)
/*     */           {
/*  88 */             panel.setText(text.getText());
/*     */           }
/*     */           
/*     */           public void insertUpdate(DocumentEvent e) {
/*  92 */             panel.setText(text.getText());
/*     */           }
/*     */           
/*     */           public void removeUpdate(DocumentEvent e) {
/*  96 */             panel.setText(text.getText());
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 101 */     panel.setText(text.getText());
/* 102 */     listener.actionPerformed(null);
/* 103 */     return contents;
/*     */   }
/*     */ 
/*     */   
/*     */   private static JTextField createTextEditor() {
/* 108 */     return new JTextField("The quick jump lazy dog");
/*     */   }
/*     */ 
/*     */   
/*     */   private static JComboBox createFontNameCombo() {
/* 113 */     String[] fl = GraphicsEnvironment.getLocalGraphicsEnvironment()
/* 114 */       .getAvailableFontFamilyNames();
/* 115 */     JComboBox<String> combo = new JComboBox<>(fl);
/* 116 */     return combo;
/*     */   }
/*     */ 
/*     */   
/*     */   private static JComboBox createFontSizeCombo() {
/* 121 */     int[] sizes = { 6, 7, 8, 10, 11, 12, 14, 16, 18, 20, 24, 28, 32, 36, 48, 72 };
/* 122 */     JComboBox<String> combo = new JComboBox();
/* 123 */     for (int i = 0; i < sizes.length; i++)
/*     */     {
/* 125 */       combo.addItem(Integer.toString(sizes[i]));
/*     */     }
/* 127 */     combo.setSelectedIndex(3);
/* 128 */     return combo;
/*     */   }
/*     */ 
/*     */   
/*     */   private static JComboBox createFontStyleCombo() {
/* 133 */     JComboBox<String> combo = new JComboBox<>(new String[] { "Normal", "Bold", "Italic", "Bod-Italic" });
/* 134 */     return combo;
/*     */   }
/*     */ 
/*     */   
/*     */   private static JLabel createLabel(String caption) {
/* 139 */     return new JLabel(caption);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 145 */     JDialog dlg = new JDialog();
/* 146 */     dlg.setDefaultCloseOperation(2);
/* 147 */     dlg.addWindowListener(new WindowAdapter()
/*     */         {
/*     */           public void windowClosed(WindowEvent e)
/*     */           {
/* 151 */             System.exit(0);
/*     */           }
/*     */         });
/* 154 */     dlg.setContentPane(createContents());
/* 155 */     dlg.pack();
/* 156 */     dlg.show();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\test\FontTest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */