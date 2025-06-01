package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.Metrics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstraintWidgetContainer extends WidgetContainer {
   private static final boolean DEBUG = false;
   static final boolean DEBUG_GRAPH = false;
   private static final boolean DEBUG_LAYOUT = false;
   private static final int MAX_ITERATIONS = 8;
   private static final boolean USE_SNAPSHOT = true;
   int mDebugSolverPassCount = 0;
   public boolean mGroupsWrapOptimized = false;
   private boolean mHeightMeasuredTooSmall = false;
   ChainHead[] mHorizontalChainsArray = new ChainHead[4];
   int mHorizontalChainsSize = 0;
   public boolean mHorizontalWrapOptimized = false;
   private boolean mIsRtl = false;
   private int mOptimizationLevel = 7;
   int mPaddingBottom;
   int mPaddingLeft;
   int mPaddingRight;
   int mPaddingTop;
   public boolean mSkipSolver = false;
   private Snapshot mSnapshot;
   protected LinearSystem mSystem = new LinearSystem();
   ChainHead[] mVerticalChainsArray = new ChainHead[4];
   int mVerticalChainsSize = 0;
   public boolean mVerticalWrapOptimized = false;
   public List mWidgetGroups = new ArrayList();
   private boolean mWidthMeasuredTooSmall = false;
   public int mWrapFixedHeight = 0;
   public int mWrapFixedWidth = 0;

   public ConstraintWidgetContainer() {
   }

   public ConstraintWidgetContainer(int var1, int var2) {
      super(var1, var2);
   }

   public ConstraintWidgetContainer(int var1, int var2, int var3, int var4) {
      super(var1, var2, var3, var4);
   }

   private void addHorizontalChain(ConstraintWidget var1) {
      int var2 = this.mHorizontalChainsSize;
      ChainHead[] var3 = this.mHorizontalChainsArray;
      if (var2 + 1 >= var3.length) {
         this.mHorizontalChainsArray = (ChainHead[])Arrays.copyOf(var3, var3.length * 2);
      }

      this.mHorizontalChainsArray[this.mHorizontalChainsSize] = new ChainHead(var1, 0, this.isRtl());
      ++this.mHorizontalChainsSize;
   }

   private void addVerticalChain(ConstraintWidget var1) {
      int var2 = this.mVerticalChainsSize;
      ChainHead[] var3 = this.mVerticalChainsArray;
      if (var2 + 1 >= var3.length) {
         this.mVerticalChainsArray = (ChainHead[])Arrays.copyOf(var3, var3.length * 2);
      }

      this.mVerticalChainsArray[this.mVerticalChainsSize] = new ChainHead(var1, 1, this.isRtl());
      ++this.mVerticalChainsSize;
   }

   private void resetChains() {
      this.mHorizontalChainsSize = 0;
      this.mVerticalChainsSize = 0;
   }

   void addChain(ConstraintWidget var1, int var2) {
      if (var2 == 0) {
         this.addHorizontalChain(var1);
      } else if (var2 == 1) {
         this.addVerticalChain(var1);
      }

   }

   public boolean addChildrenToSolver(LinearSystem var1) {
      this.addToSolver(var1);
      int var3 = this.mChildren.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         ConstraintWidget var5 = (ConstraintWidget)this.mChildren.get(var2);
         if (var5 instanceof ConstraintWidgetContainer) {
            ConstraintWidget.DimensionBehaviour var4 = var5.mListDimensionBehaviors[0];
            ConstraintWidget.DimensionBehaviour var6 = var5.mListDimensionBehaviors[1];
            if (var4 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
               var5.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            }

            if (var6 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
               var5.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            }

            var5.addToSolver(var1);
            if (var4 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
               var5.setHorizontalDimensionBehaviour(var4);
            }

            if (var6 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
               var5.setVerticalDimensionBehaviour(var6);
            }
         } else {
            Optimizer.checkMatchParent(this, var1, var5);
            var5.addToSolver(var1);
         }
      }

      if (this.mHorizontalChainsSize > 0) {
         Chain.applyChainConstraints(this, var1, 0);
      }

      if (this.mVerticalChainsSize > 0) {
         Chain.applyChainConstraints(this, var1, 1);
      }

      return true;
   }

   public void analyze(int var1) {
      super.analyze(var1);
      int var3 = this.mChildren.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         ((ConstraintWidget)this.mChildren.get(var2)).analyze(var1);
      }

   }

   public void fillMetrics(Metrics var1) {
      this.mSystem.fillMetrics(var1);
   }

   public ArrayList getHorizontalGuidelines() {
      ArrayList var3 = new ArrayList();
      int var2 = this.mChildren.size();

      for(int var1 = 0; var1 < var2; ++var1) {
         ConstraintWidget var4 = (ConstraintWidget)this.mChildren.get(var1);
         if (var4 instanceof Guideline) {
            Guideline var5 = (Guideline)var4;
            if (var5.getOrientation() == 0) {
               var3.add(var5);
            }
         }
      }

      return var3;
   }

   public int getOptimizationLevel() {
      return this.mOptimizationLevel;
   }

   public LinearSystem getSystem() {
      return this.mSystem;
   }

   public String getType() {
      return "ConstraintLayout";
   }

   public ArrayList getVerticalGuidelines() {
      ArrayList var3 = new ArrayList();
      int var2 = this.mChildren.size();

      for(int var1 = 0; var1 < var2; ++var1) {
         ConstraintWidget var4 = (ConstraintWidget)this.mChildren.get(var1);
         if (var4 instanceof Guideline) {
            Guideline var5 = (Guideline)var4;
            if (var5.getOrientation() == 1) {
               var3.add(var5);
            }
         }
      }

      return var3;
   }

   public List getWidgetGroups() {
      return this.mWidgetGroups;
   }

   public boolean handlesInternalConstraints() {
      return false;
   }

   public boolean isHeightMeasuredTooSmall() {
      return this.mHeightMeasuredTooSmall;
   }

   public boolean isRtl() {
      return this.mIsRtl;
   }

   public boolean isWidthMeasuredTooSmall() {
      return this.mWidthMeasuredTooSmall;
   }

   public void layout() {
      int var11 = this.mX;
      int var12 = this.mY;
      int var9 = Math.max(0, this.getWidth());
      int var10 = Math.max(0, this.getHeight());
      this.mWidthMeasuredTooSmall = false;
      this.mHeightMeasuredTooSmall = false;
      if (this.mParent != null) {
         if (this.mSnapshot == null) {
            this.mSnapshot = new Snapshot(this);
         }

         this.mSnapshot.updateFrom(this);
         this.setX(this.mPaddingLeft);
         this.setY(this.mPaddingTop);
         this.resetAnchors();
         this.resetSolverVariables(this.mSystem.getCache());
      } else {
         this.mX = 0;
         this.mY = 0;
      }

      if (this.mOptimizationLevel != 0) {
         if (!this.optimizeFor(8)) {
            this.optimizeReset();
         }

         if (!this.optimizeFor(32)) {
            this.optimize();
         }

         this.mSystem.graphOptimizer = true;
      } else {
         this.mSystem.graphOptimizer = false;
      }

      ConstraintWidget.DimensionBehaviour var18 = this.mListDimensionBehaviors[1];
      ConstraintWidget.DimensionBehaviour var19 = this.mListDimensionBehaviors[0];
      this.resetChains();
      if (this.mWidgetGroups.size() == 0) {
         this.mWidgetGroups.clear();
         this.mWidgetGroups.add(0, new ConstraintWidgetGroup(this.mChildren));
      }

      int var1 = this.mWidgetGroups.size();
      ArrayList var20 = this.mChildren;
      boolean var6;
      if (this.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && this.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
         var6 = false;
      } else {
         var6 = true;
      }

      boolean var2 = false;

      int var3;
      for(int var7 = 0; var7 < var1 && !this.mSkipSolver; ++var7) {
         if (!((ConstraintWidgetGroup)this.mWidgetGroups.get(var7)).mSkipSolver) {
            if (this.optimizeFor(32)) {
               if (this.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED && this.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED) {
                  this.mChildren = (ArrayList)((ConstraintWidgetGroup)this.mWidgetGroups.get(var7)).getWidgetsToSolve();
               } else {
                  this.mChildren = (ArrayList)((ConstraintWidgetGroup)this.mWidgetGroups.get(var7)).mConstrainedGroup;
               }
            }

            this.resetChains();
            int var13 = this.mChildren.size();

            ConstraintWidget var17;
            for(var3 = 0; var3 < var13; ++var3) {
               var17 = (ConstraintWidget)this.mChildren.get(var3);
               if (var17 instanceof WidgetContainer) {
                  ((WidgetContainer)var17).layout();
               }
            }

            int var4 = 0;
            boolean var14 = true;
            boolean var28 = var2;
            var3 = var4;

            while(true) {
               while(var14) {
                  ++var3;
                  boolean var15 = var14;

                  label193: {
                     label192: {
                        Exception var33;
                        label276: {
                           label190: {
                              Exception var10000;
                              label277: {
                                 boolean var10001;
                                 try {
                                    this.mSystem.reset();
                                 } catch (Exception var27) {
                                    var10000 = var27;
                                    var10001 = false;
                                    break label277;
                                 }

                                 var15 = var14;

                                 try {
                                    this.resetChains();
                                 } catch (Exception var26) {
                                    var10000 = var26;
                                    var10001 = false;
                                    break label277;
                                 }

                                 var15 = var14;

                                 try {
                                    this.createObjectVariables(this.mSystem);
                                 } catch (Exception var25) {
                                    var10000 = var25;
                                    var10001 = false;
                                    break label277;
                                 }

                                 var4 = 0;

                                 while(true) {
                                    if (var4 < var13) {
                                       label278: {
                                          var15 = var14;

                                          try {
                                             var17 = (ConstraintWidget)this.mChildren.get(var4);
                                          } catch (Exception var23) {
                                             var10000 = var23;
                                             var10001 = false;
                                             break;
                                          }

                                          try {
                                             var17.createObjectVariables(this.mSystem);
                                          } catch (Exception var24) {
                                             var10000 = var24;
                                             var10001 = false;
                                             break label278;
                                          }

                                          ++var4;
                                          continue;
                                       }
                                    } else {
                                       try {
                                          var15 = this.addChildrenToSolver(this.mSystem);
                                          break label190;
                                       } catch (Exception var22) {
                                          var10000 = var22;
                                          var10001 = false;
                                       }
                                    }

                                    var33 = var10000;
                                    break label276;
                                 }
                              }

                              var33 = var10000;
                              var14 = var15;
                              break label276;
                           }

                           if (!var15) {
                              break label192;
                           }

                           try {
                              this.mSystem.minimize();
                              break label192;
                           } catch (Exception var21) {
                              var33 = var21;
                              var14 = var15;
                           }
                        }

                        var33.printStackTrace();
                        System.out.println("EXCEPTION : " + var33);
                        break label193;
                     }

                     var14 = var15;
                  }

                  if (var14) {
                     this.updateChildrenFromSolver(this.mSystem, Optimizer.flags);
                  } else {
                     this.updateFromSolver(this.mSystem);

                     for(var4 = 0; var4 < var13; ++var4) {
                        var17 = (ConstraintWidget)this.mChildren.get(var4);
                        if (var17.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && var17.getWidth() < var17.getWrapWidth()) {
                           Optimizer.flags[2] = true;
                           break;
                        }

                        if (var17.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && var17.getHeight() < var17.getWrapHeight()) {
                           Optimizer.flags[2] = true;
                           break;
                        }
                     }
                  }

                  boolean var32;
                  if (var6 && var3 < 8 && Optimizer.flags[2]) {
                     int var5 = 0;
                     int var8 = 0;

                     for(var4 = 0; var5 < var13; ++var5) {
                        var17 = (ConstraintWidget)this.mChildren.get(var5);
                        var8 = Math.max(var8, var17.mX + var17.getWidth());
                        var4 = Math.max(var4, var17.mY + var17.getHeight());
                     }

                     var5 = var3;
                     var3 = Math.max(this.mMinWidth, var8);
                     var8 = Math.max(this.mMinHeight, var4);
                     if (var19 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && this.getWidth() < var3) {
                        this.setWidth(var3);
                        this.mListDimensionBehaviors[0] = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                        var15 = true;
                        var32 = true;
                     } else {
                        var15 = false;
                        var32 = var28;
                     }

                     var14 = var15;
                     var28 = var32;
                     var3 = var5;
                     if (var18 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        var14 = var15;
                        var28 = var32;
                        var3 = var5;
                        if (this.getHeight() < var8) {
                           this.setHeight(var8);
                           this.mListDimensionBehaviors[1] = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                           var14 = true;
                           var28 = true;
                           var3 = var5;
                        }
                     }
                  } else {
                     var14 = false;
                  }

                  var4 = Math.max(this.mMinWidth, this.getWidth());
                  if (var4 > this.getWidth()) {
                     this.setWidth(var4);
                     this.mListDimensionBehaviors[0] = ConstraintWidget.DimensionBehaviour.FIXED;
                     var14 = true;
                     var28 = true;
                  }

                  var4 = Math.max(this.mMinHeight, this.getHeight());
                  if (var4 > this.getHeight()) {
                     this.setHeight(var4);
                     this.mListDimensionBehaviors[1] = ConstraintWidget.DimensionBehaviour.FIXED;
                     var14 = true;
                     var28 = true;
                  }

                  var15 = var14;
                  boolean var31 = var28;
                  if (!var28) {
                     boolean var16 = var14;
                     var32 = var28;
                     if (this.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        var16 = var14;
                        var32 = var28;
                        if (var9 > 0) {
                           var16 = var14;
                           var32 = var28;
                           if (this.getWidth() > var9) {
                              this.mWidthMeasuredTooSmall = true;
                              this.mListDimensionBehaviors[0] = ConstraintWidget.DimensionBehaviour.FIXED;
                              this.setWidth(var9);
                              var16 = true;
                              var32 = true;
                           }
                        }
                     }

                     var15 = var16;
                     var31 = var32;
                     if (this.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        var15 = var16;
                        var31 = var32;
                        if (var10 > 0) {
                           var15 = var16;
                           var31 = var32;
                           if (this.getHeight() > var10) {
                              this.mHeightMeasuredTooSmall = true;
                              this.mListDimensionBehaviors[1] = ConstraintWidget.DimensionBehaviour.FIXED;
                              this.setHeight(var10);
                              var28 = true;
                              var14 = true;
                              continue;
                           }
                        }
                     }
                  }

                  var14 = var15;
                  var28 = var31;
               }

               ((ConstraintWidgetGroup)this.mWidgetGroups.get(var7)).updateUnresolvedWidgets();
               var2 = var28;
               var1 = var1;
               break;
            }
         }
      }

      ArrayList var34 = (ArrayList)var20;
      this.mChildren = var20;
      if (this.mParent != null) {
         var1 = Math.max(this.mMinWidth, this.getWidth());
         var3 = Math.max(this.mMinHeight, this.getHeight());
         this.mSnapshot.applyTo(this);
         this.setWidth(var1 + this.mPaddingLeft + this.mPaddingRight);
         this.setHeight(var3 + this.mPaddingTop + this.mPaddingBottom);
      } else {
         this.mX = var11;
         this.mY = var12;
      }

      if (var2) {
         this.mListDimensionBehaviors[0] = var19;
         this.mListDimensionBehaviors[1] = var18;
      }

      this.resetSolverVariables(this.mSystem.getCache());
      if (this == this.getRootConstraintContainer()) {
         this.updateDrawPosition();
      }

   }

   public void optimize() {
      if (!this.optimizeFor(8)) {
         this.analyze(this.mOptimizationLevel);
      }

      this.solveGraph();
   }

   public boolean optimizeFor(int var1) {
      boolean var2;
      if ((this.mOptimizationLevel & var1) == var1) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public void optimizeForDimensions(int var1, int var2) {
      if (this.mListDimensionBehaviors[0] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && this.mResolutionWidth != null) {
         this.mResolutionWidth.resolve(var1);
      }

      if (this.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && this.mResolutionHeight != null) {
         this.mResolutionHeight.resolve(var2);
      }

   }

   public void optimizeReset() {
      int var2 = this.mChildren.size();
      this.resetResolutionNodes();

      for(int var1 = 0; var1 < var2; ++var1) {
         ((ConstraintWidget)this.mChildren.get(var1)).resetResolutionNodes();
      }

   }

   public void preOptimize() {
      this.optimizeReset();
      this.analyze(this.mOptimizationLevel);
   }

   public void reset() {
      this.mSystem.reset();
      this.mPaddingLeft = 0;
      this.mPaddingRight = 0;
      this.mPaddingTop = 0;
      this.mPaddingBottom = 0;
      this.mWidgetGroups.clear();
      this.mSkipSolver = false;
      super.reset();
   }

   public void resetGraph() {
      ResolutionAnchor var1 = this.getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
      ResolutionAnchor var2 = this.getAnchor(ConstraintAnchor.Type.TOP).getResolutionNode();
      var1.invalidateAnchors();
      var2.invalidateAnchors();
      var1.resolve((ResolutionAnchor)null, 0.0F);
      var2.resolve((ResolutionAnchor)null, 0.0F);
   }

   public void setOptimizationLevel(int var1) {
      this.mOptimizationLevel = var1;
   }

   public void setPadding(int var1, int var2, int var3, int var4) {
      this.mPaddingLeft = var1;
      this.mPaddingTop = var2;
      this.mPaddingRight = var3;
      this.mPaddingBottom = var4;
   }

   public void setRtl(boolean var1) {
      this.mIsRtl = var1;
   }

   public void solveGraph() {
      ResolutionAnchor var1 = this.getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
      ResolutionAnchor var2 = this.getAnchor(ConstraintAnchor.Type.TOP).getResolutionNode();
      var1.resolve((ResolutionAnchor)null, 0.0F);
      var2.resolve((ResolutionAnchor)null, 0.0F);
   }

   public void updateChildrenFromSolver(LinearSystem var1, boolean[] var2) {
      var2[2] = false;
      this.updateFromSolver(var1);
      int var4 = this.mChildren.size();

      for(int var3 = 0; var3 < var4; ++var3) {
         ConstraintWidget var5 = (ConstraintWidget)this.mChildren.get(var3);
         var5.updateFromSolver(var1);
         if (var5.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && var5.getWidth() < var5.getWrapWidth()) {
            var2[2] = true;
         }

         if (var5.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && var5.getHeight() < var5.getWrapHeight()) {
            var2[2] = true;
         }
      }

   }
}
