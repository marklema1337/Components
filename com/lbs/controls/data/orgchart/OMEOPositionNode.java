/*     */ package com.lbs.controls.data.orgchart;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;
/*     */ import javax.swing.tree.DefaultMutableTreeNode;
/*     */ import javax.swing.tree.TreeNode;
/*     */ 
/*     */ public class OMEOPositionNode
/*     */   extends DefaultMutableTreeNode
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected boolean isVisible = true;
/*     */   public int reference;
/*     */   public String code;
/*     */   public String name;
/*     */   public String positionGroup;
/*     */   public int positionGroupRef;
/*     */   public boolean assistant;
/*     */   public String layout;
/*     */   public boolean vacant;
/*     */   public List<OMEOPositionPerson> personal;
/*     */   public List<Integer> linkedPositions;
/*     */   public String description;
/*     */   public int actStaff;
/*     */   public int maxStaff;
/*     */   public boolean isModified = false;
/*  28 */   public String orderVal = "";
/*  29 */   public int parentRef = 0;
/*  30 */   public int levelNr = 0;
/*  31 */   public int positionLevel = 0;
/*  32 */   public int positionOptimizedLevel = 0;
/*     */   
/*  34 */   public int importance = 0;
/*  35 */   public int positionStatus = 0;
/*  36 */   public String posDefCode = "";
/*  37 */   public String deptLinkCode = "";
/*  38 */   public String envTypeCode = "";
/*  39 */   public String riskTypeCode = "";
/*  40 */   public String effortTypeCode = "";
/*  41 */   public String levelTypeCode = "";
/*  42 */   public String jobDefCode = "";
/*  43 */   public String placeInfoCode = "";
/*     */   public int executivePositionRef;
/*     */   public OMEOPositionPerson delegate;
/*  46 */   public int childEmployeeCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OMEOPositionNode() {
/*  53 */     this.code = "";
/*  54 */     this.name = "";
/*  55 */     this.personal = new ArrayList<>();
/*  56 */     this.linkedPositions = new ArrayList<>();
/*  57 */     this.description = "";
/*  58 */     this.positionGroup = "";
/*  59 */     this.layout = "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void vacate() {
/*  68 */     this.vacant = true;
/*  69 */     this.code = "";
/*  70 */     this.name = "";
/*  71 */     this.description = "";
/*  72 */     this.personal = new ArrayList<>();
/*  73 */     this.linkedPositions = new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void adoptStructuralData(OMEOPositionNode otherPosition) {
/*  83 */     this.layout = otherPosition.layout;
/*  84 */     this.assistant = otherPosition.assistant;
/*  85 */     this.positionGroup = otherPosition.positionGroup;
/*  86 */     this.positionGroupRef = otherPosition.positionGroupRef;
/*     */   }
/*     */ 
/*     */   
/*     */   public TreeNode getChildAt(int index, boolean filterIsActive) {
/*  91 */     if (!filterIsActive)
/*     */     {
/*  93 */       return getChildAt(index);
/*     */     }
/*  95 */     if (this.children == null)
/*     */     {
/*  97 */       throw new ArrayIndexOutOfBoundsException("node has no children");
/*     */     }
/*     */     
/* 100 */     int realIndex = -1;
/* 101 */     int visibleIndex = -1;
/* 102 */     Enumeration<TreeNode> e = this.children.elements();
/* 103 */     while (e.hasMoreElements()) {
/*     */       
/* 105 */       OMEOPositionNode node = (OMEOPositionNode)e.nextElement();
/* 106 */       if (node.isVisible())
/*     */       {
/* 108 */         visibleIndex++;
/*     */       }
/* 110 */       realIndex++;
/* 111 */       if (visibleIndex == index)
/*     */       {
/* 113 */         return this.children.elementAt(realIndex);
/*     */       }
/*     */     } 
/*     */     
/* 117 */     throw new ArrayIndexOutOfBoundsException("index unmatched");
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChildCount(boolean filterIsActive) {
/* 122 */     if (!filterIsActive)
/*     */     {
/* 124 */       return getChildCount();
/*     */     }
/* 126 */     if (this.children == null)
/*     */     {
/* 128 */       return 0;
/*     */     }
/*     */     
/* 131 */     int count = 0;
/* 132 */     Enumeration<TreeNode> e = this.children.elements();
/* 133 */     while (e.hasMoreElements()) {
/*     */       
/* 135 */       OMEOPositionNode node = (OMEOPositionNode)e.nextElement();
/* 136 */       if (node.isVisible())
/*     */       {
/* 138 */         count++;
/*     */       }
/*     */     } 
/*     */     
/* 142 */     return count;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 147 */     this.isVisible = visible;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 152 */     return this.isVisible;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 158 */     OMEOPositionNode newNode = (OMEOPositionNode)super.clone();
/* 159 */     newNode.personal = new ArrayList<>();
/* 160 */     newNode.personal.addAll(this.personal);
/* 161 */     newNode.linkedPositions = new ArrayList<>();
/* 162 */     newNode.linkedPositions.addAll(this.linkedPositions);
/* 163 */     return newNode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 169 */     if (this.code != null && this.name != null)
/* 170 */       return String.valueOf(this.code) + " " + this.name; 
/* 171 */     return super.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\data\orgchart\OMEOPositionNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */