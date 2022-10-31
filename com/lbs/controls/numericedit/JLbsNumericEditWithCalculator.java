/*     */ package com.lbs.controls.numericedit;
/*     */ 
/*     */ import com.lbs.control.interfaces.ILbsNumericEditWithCalculator;
/*     */ import com.lbs.control.interfaces.ILbsOptionPane;
/*     */ import com.lbs.controls.ILbsComponentBase;
/*     */ import com.lbs.controls.ILbsLocalizable;
/*     */ import com.lbs.controls.JLbsControlHelper;
/*     */ import com.lbs.controls.JLbsEventRecorderHelper;
/*     */ import com.lbs.controls.menu.JLbsPopupMenu;
/*     */ import com.lbs.recording.interfaces.ILbsNumericEditWithCalculatorRecordingEvents;
/*     */ import com.lbs.resource.JLbsLocalizer;
/*     */ import com.lbs.util.JLbsDialog;
/*     */ import com.lbs.util.JLbsStringUtil;
/*     */ import info.clearthought.layout.TableLayout;
/*     */ import info.clearthought.layout.TableLayoutConstraints;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.Popup;
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
/*     */ public class JLbsNumericEditWithCalculator
/*     */   extends JLbsNumericEdit
/*     */   implements ILbsLocalizable, ILbsNumericEditWithCalculatorRecordingEvents, ILbsNumericEditWithCalculator
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  53 */   public String m_PopupTitle = "Select Operations";
/*     */   
/*     */   private static final int RESID = -20098;
/*     */   
/*  57 */   private static DecimalFormatSymbols ms_Symbols = (new DecimalFormat()).getDecimalFormatSymbols();
/*     */   
/*  59 */   String m_Expression = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsNumericEditWithCalculator() {
/*  67 */     initialize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLbsNumericEditWithCalculator(boolean bReal) {
/*  78 */     super(bReal);
/*  79 */     initialize();
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
/*     */   public JLbsNumericEditWithCalculator(boolean bReal, Number nNumber) {
/*  93 */     super(bReal, nNumber);
/*  94 */     initialize();
/*     */   }
/*     */ 
/*     */   
/*     */   private void initialize() {
/*  99 */     addKeyListener(new JLbsNumericEditKeyListener());
/* 100 */     localize();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean localize() {
/* 105 */     this.m_PopupTitle = JLbsLocalizer.getStringDef("UN", -20098, 1, this.m_PopupTitle);
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void popupCalculator(char e) {
/* 111 */     Rectangle rectScreen = JLbsControlHelper.getRootCoordinates((Component)this);
/* 112 */     Container parentWindow = JLbsControlHelper.getRootContainer((Component)this);
/*     */     
/* 114 */     JLbsPopupMenu.setDefaultLightWeightPopupEnabled(true);
/* 115 */     JDialog dialog = new JDialog();
/* 116 */     dialog.setTitle(this.m_PopupTitle);
/* 117 */     dialog.setResizable(true);
/* 118 */     dialog.setLocation(rectScreen.x - 1, rectScreen.y + rectScreen.height);
/* 119 */     if (parentWindow instanceof JLbsDialog) {
/* 120 */       dialog.setModal(((JLbsDialog)parentWindow).isModal());
/*     */     }
/* 122 */     JLbsCalculatorPanel calculator = new JLbsCalculatorPanel();
/* 123 */     dialog.setSize(calculator.getPreferredSize());
/* 124 */     calculator.setCultureInfo(getCultureInfo());
/* 125 */     calculator.setOriginalText(String.valueOf(getText()) + e);
/* 126 */     calculator.setCalculatorListener(new JLbsNumericEditWithCalculatorPanelListener(dialog, this));
/* 127 */     dialog.addWindowListener(new JLbsNumericEditWithCalculatorDialogAdapter());
/* 128 */     dialog.setContentPane((Container)calculator);
/* 129 */     dialog.setVisible(true);
/* 130 */     calculator.grabFocus();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doCloseCalculator(Object dlg) {
/* 135 */     if (dlg instanceof JDialog) {
/*     */       
/* 137 */       ((JDialog)dlg).hide();
/* 138 */       ((JDialog)dlg).dispose();
/* 139 */       grabFocus();
/*     */     }
/* 141 */     else if (dlg instanceof Popup) {
/*     */       
/* 143 */       ((Popup)dlg).hide();
/* 144 */       grabFocus();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFocusOwner() {
/* 151 */     return super.isFocusOwner();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordSetNumber(Number value) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordRequestFocus() {
/* 161 */     JLbsEventRecorderHelper.addRecordItem((ILbsComponentBase)this, "REQUEST_FOCUS");
/*     */   }
/*     */   
/*     */   class JLbsNumericEditWithCalculatorDialogAdapter
/*     */     extends WindowAdapter
/*     */   {
/*     */     public void windowDeactivated(WindowEvent e) {
/* 168 */       Object obj = e.getSource();
/* 169 */       JLbsNumericEditWithCalculator.this.doCloseCalculator(obj);
/*     */     }
/*     */   }
/*     */   
/*     */   class JLbsNumericEditKeyListener
/*     */     implements KeyListener
/*     */   {
/*     */     public void keyPressed(KeyEvent e) {
/* 177 */       if (e.isConsumed())
/*     */         return; 
/* 179 */       switch (e.getKeyChar()) {
/*     */ 
/*     */         
/*     */         case '*':
/*     */         case '+':
/*     */         case '/':
/* 185 */           if (JLbsNumericEditWithCalculator.this.isEnabled() && JLbsNumericEditWithCalculator.this.isEditable()) {
/*     */             
/* 187 */             JLbsNumericEditWithCalculator.this.popupCalculator(e.getKeyChar());
/* 188 */             e.consume();
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void keyReleased(KeyEvent e) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void keyTyped(KeyEvent e) {}
/*     */   }
/*     */ 
/*     */   
/*     */   class JLbsNumericEditWithCalculatorPanelListener
/*     */     implements ILbsCalculatorPanelListener
/*     */   {
/*     */     protected Object m_Window;
/*     */     
/*     */     protected JLbsNumericEdit m_Edit;
/*     */     
/* 211 */     private int count = 0;
/*     */ 
/*     */     
/*     */     public JLbsNumericEditWithCalculatorPanelListener(Object window, JLbsNumericEdit edit) {
/* 215 */       this.m_Window = window;
/* 216 */       this.m_Edit = edit;
/*     */       
/* 218 */       JLbsNumericEditWithCalculator.ms_Symbols = (new DecimalFormat()).getDecimalFormatSymbols();
/*     */     }
/*     */ 
/*     */     
/*     */     public void expressionSelectionChanged(Object sender, String expression) {
/* 223 */       String exp = this.m_Edit.getText();
/* 224 */       exp = JLbsStringUtil.remove(exp, '.');
/* 225 */       if (this.count == 0) {
/* 226 */         JLbsNumericEditWithCalculator.this.m_Expression = String.valueOf(JLbsNumericEditWithCalculator.this.m_Expression) + exp + expression;
/*     */       } else {
/* 228 */         JLbsNumericEditWithCalculator.this.m_Expression = String.valueOf(JLbsNumericEditWithCalculator.this.m_Expression) + expression;
/* 229 */       }  if (isNumeric(expression)) {
/* 230 */         this.m_Edit.setText(String.valueOf(exp) + expression);
/*     */       } else {
/* 232 */         this.m_Edit.setText("0");
/* 233 */       }  this.count++;
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean isNumeric(String expression) {
/* 238 */       if (expression.equals("0") || expression.equals("1") || expression.equals("2") || expression.equals("3") || 
/* 239 */         expression.equals("4") || expression.equals("5") || expression.equals("6") || expression.equals("7") || 
/* 240 */         expression.equals("8") || expression.equals("9")) {
/* 241 */         return true;
/*     */       }
/* 243 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public void expressionSelected(Object sender, String expression) {
/* 248 */       if (sender instanceof JLbsCalculatorPanel) {
/*     */         
/* 250 */         Number value = parseAndEvaluateExpression(expression);
/* 251 */         if (value != null)
/*     */         {
/* 253 */           if (this.m_Edit != null && 
/* 254 */             this.m_Edit.isEditable() && this.m_Edit.isEnabled())
/*     */           {
/* 256 */             if (value.doubleValue() < 0.0D && !this.m_Edit.isSignAllowed()) {
/*     */               
/* 258 */               String message = JLbsLocalizer.getStringDef("UN", -20098, 10, 
/* 259 */                   "Negative values are not allowed for this field! Value is ");
/* 260 */               message = String.valueOf(message) + value;
/* 261 */               ILbsOptionPane.warn(null, message);
/* 262 */               System.err.println("JLbsNumericEditWithCalculator: " + message);
/*     */             } else {
/*     */               
/* 265 */               this.m_Edit.setNumber(value);
/*     */             }  } 
/*     */         }
/* 268 */         JLbsNumericEditWithCalculator.this.doCloseCalculator(this.m_Window);
/* 269 */         JLbsNumericEditWithCalculator.this.m_Expression = "";
/* 270 */         this.count = 0;
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private Number parseAndEvaluateExpression(String expression) {
/*     */       // Byte code:
/*     */       //   0: invokestatic getExpressionEvaluator : ()Lcom/lbs/interfaces/ILbsExpressionEvaluator;
/*     */       //   3: astore_2
/*     */       //   4: aload_2
/*     */       //   5: ifnonnull -> 13
/*     */       //   8: iconst_0
/*     */       //   9: invokestatic valueOf : (I)Ljava/lang/Integer;
/*     */       //   12: areturn
/*     */       //   13: aload_2
/*     */       //   14: iconst_1
/*     */       //   15: invokeinterface setDoubleDiv : (Z)V
/*     */       //   20: aload_1
/*     */       //   21: ifnull -> 73
/*     */       //   24: aload_1
/*     */       //   25: ldc '-'
/*     */       //   27: ldc '+0-'
/*     */       //   29: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
/*     */       //   32: astore_1
/*     */       //   33: new java/lang/StringBuilder
/*     */       //   36: dup
/*     */       //   37: invokespecial <init> : ()V
/*     */       //   40: invokestatic access$1 : ()Ljava/text/DecimalFormatSymbols;
/*     */       //   43: invokevirtual getDecimalSeparator : ()C
/*     */       //   46: invokevirtual append : (C)Ljava/lang/StringBuilder;
/*     */       //   49: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   52: astore_3
/*     */       //   53: aload_3
/*     */       //   54: ldc '.'
/*     */       //   56: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   59: ifeq -> 65
/*     */       //   62: ldc '\.'
/*     */       //   64: astore_3
/*     */       //   65: aload_1
/*     */       //   66: aload_3
/*     */       //   67: ldc '\.'
/*     */       //   69: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
/*     */       //   72: astore_1
/*     */       //   73: aload_2
/*     */       //   74: aload_1
/*     */       //   75: invokeinterface evaluateExpression : (Ljava/lang/String;)Ljava/lang/Object;
/*     */       //   80: astore_3
/*     */       //   81: aload_3
/*     */       //   82: instanceof java/lang/Long
/*     */       //   85: ifeq -> 93
/*     */       //   88: aload_3
/*     */       //   89: checkcast java/lang/Long
/*     */       //   92: areturn
/*     */       //   93: aload_3
/*     */       //   94: instanceof java/lang/Double
/*     */       //   97: ifeq -> 105
/*     */       //   100: aload_3
/*     */       //   101: checkcast java/lang/Double
/*     */       //   104: areturn
/*     */       //   105: aload_3
/*     */       //   106: instanceof java/lang/Number
/*     */       //   109: ifeq -> 117
/*     */       //   112: aload_3
/*     */       //   113: checkcast java/lang/Number
/*     */       //   116: areturn
/*     */       //   117: iconst_0
/*     */       //   118: invokestatic valueOf : (I)Ljava/lang/Integer;
/*     */       //   121: areturn
/*     */       //   122: astore_3
/*     */       //   123: ldc 'UN'
/*     */       //   125: sipush #-20098
/*     */       //   128: bipush #9
/*     */       //   130: aload_3
/*     */       //   131: invokevirtual getMessage : ()Ljava/lang/String;
/*     */       //   134: invokestatic getStringDef : (Ljava/lang/String;IILjava/lang/String;)Ljava/lang/String;
/*     */       //   137: astore #4
/*     */       //   139: aload #4
/*     */       //   141: ifnonnull -> 148
/*     */       //   144: ldc 'The mathematical expression is incorrect!'
/*     */       //   146: astore #4
/*     */       //   148: aconst_null
/*     */       //   149: aload #4
/*     */       //   151: invokestatic warn : (Ljava/awt/Component;Ljava/lang/String;)V
/*     */       //   154: getstatic java/lang/System.err : Ljava/io/PrintStream;
/*     */       //   157: new java/lang/StringBuilder
/*     */       //   160: dup
/*     */       //   161: ldc 'JLbsNumericEditWithCalculator '
/*     */       //   163: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   166: aload_3
/*     */       //   167: invokevirtual getMessage : ()Ljava/lang/String;
/*     */       //   170: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */       //   173: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   176: invokevirtual println : (Ljava/lang/String;)V
/*     */       //   179: aconst_null
/*     */       //   180: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #277	-> 0
/*     */       //   #278	-> 4
/*     */       //   #279	-> 8
/*     */       //   #280	-> 13
/*     */       //   #283	-> 20
/*     */       //   #285	-> 24
/*     */       //   #286	-> 33
/*     */       //   #287	-> 53
/*     */       //   #288	-> 62
/*     */       //   #289	-> 65
/*     */       //   #291	-> 73
/*     */       //   #292	-> 81
/*     */       //   #293	-> 88
/*     */       //   #294	-> 93
/*     */       //   #295	-> 100
/*     */       //   #296	-> 105
/*     */       //   #297	-> 112
/*     */       //   #299	-> 117
/*     */       //   #301	-> 122
/*     */       //   #303	-> 123
/*     */       //   #304	-> 139
/*     */       //   #305	-> 144
/*     */       //   #306	-> 148
/*     */       //   #307	-> 154
/*     */       //   #309	-> 179
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	181	0	this	Lcom/lbs/controls/numericedit/JLbsNumericEditWithCalculator$JLbsNumericEditWithCalculatorPanelListener;
/*     */       //   0	181	1	expression	Ljava/lang/String;
/*     */       //   4	177	2	evaluator	Lcom/lbs/interfaces/ILbsExpressionEvaluator;
/*     */       //   53	20	3	seperator	Ljava/lang/String;
/*     */       //   81	41	3	obj	Ljava/lang/Object;
/*     */       //   123	56	3	e	Ljava/lang/Exception;
/*     */       //   139	40	4	message	Ljava/lang/String;
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   20	92	122	java/lang/Exception
/*     */       //   93	104	122	java/lang/Exception
/*     */       //   105	116	122	java/lang/Exception
/*     */       //   117	121	122	java/lang/Exception
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void escapeSelected(Object sender) {
/* 314 */       if (sender instanceof JLbsCalculatorPanel) {
/*     */         
/* 316 */         JLbsNumericEditWithCalculator.this.doCloseCalculator(this.m_Window);
/* 317 */         JLbsNumericEditWithCalculator.this.m_Expression = "";
/* 318 */         this.count = 0;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 327 */     JFrame frame = new JFrame();
/* 328 */     frame.setDefaultCloseOperation(2);
/* 329 */     frame.setSize(400, 300);
/* 330 */     frame.setLocation(400, 400);
/* 331 */     TableLayout layout = new TableLayout(new double[] { 10.0D, -2.0D, -2.0D, 30.0D }, new double[] {
/* 332 */           10.0D, -2.0D, -2.0D, 30.0D });
/* 333 */     frame.getContentPane().setLayout((LayoutManager)layout);
/*     */     
/* 335 */     JTextField text = new JTextField();
/* 336 */     text.setPreferredSize(new Dimension(100, 22));
/* 337 */     text.setLocation(10, 10);
/* 338 */     TableLayoutConstraints constraints = new TableLayoutConstraints(1, 1);
/* 339 */     frame.getContentPane().add(text, constraints);
/*     */     
/* 341 */     JLbsNumericEditWithCalculator jLbsNumericEditWithCalculator = new JLbsNumericEditWithCalculator();
/* 342 */     jLbsNumericEditWithCalculator.setPreferredSize(new Dimension(100, 22));
/* 343 */     jLbsNumericEditWithCalculator.setLocation(10, 40);
/* 344 */     constraints = new TableLayoutConstraints(1, 2);
/* 345 */     frame.getContentPane().add((Component)jLbsNumericEditWithCalculator, constraints);
/*     */     
/* 347 */     frame.setVisible(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 354 */     frame.show();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\numericedit\JLbsNumericEditWithCalculator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */