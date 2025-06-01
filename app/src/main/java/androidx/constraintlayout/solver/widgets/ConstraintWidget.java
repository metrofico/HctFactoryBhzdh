package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.Cache;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.SolverVariable;
import java.util.ArrayList;

public class ConstraintWidget {
   protected static final int ANCHOR_BASELINE = 4;
   protected static final int ANCHOR_BOTTOM = 3;
   protected static final int ANCHOR_LEFT = 0;
   protected static final int ANCHOR_RIGHT = 1;
   protected static final int ANCHOR_TOP = 2;
   private static final boolean AUTOTAG_CENTER = false;
   public static final int CHAIN_PACKED = 2;
   public static final int CHAIN_SPREAD = 0;
   public static final int CHAIN_SPREAD_INSIDE = 1;
   public static float DEFAULT_BIAS;
   static final int DIMENSION_HORIZONTAL = 0;
   static final int DIMENSION_VERTICAL = 1;
   protected static final int DIRECT = 2;
   public static final int GONE = 8;
   public static final int HORIZONTAL = 0;
   public static final int INVISIBLE = 4;
   public static final int MATCH_CONSTRAINT_PERCENT = 2;
   public static final int MATCH_CONSTRAINT_RATIO = 3;
   public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
   public static final int MATCH_CONSTRAINT_SPREAD = 0;
   public static final int MATCH_CONSTRAINT_WRAP = 1;
   protected static final int SOLVER = 1;
   public static final int UNKNOWN = -1;
   public static final int VERTICAL = 1;
   public static final int VISIBLE = 0;
   private static final int WRAP = -2;
   protected ArrayList mAnchors;
   ConstraintAnchor mBaseline;
   int mBaselineDistance;
   ConstraintWidgetGroup mBelongingGroup;
   ConstraintAnchor mBottom;
   boolean mBottomHasCentered;
   ConstraintAnchor mCenter;
   ConstraintAnchor mCenterX;
   ConstraintAnchor mCenterY;
   private float mCircleConstraintAngle;
   private Object mCompanionWidget;
   private int mContainerItemSkip;
   private String mDebugName;
   protected float mDimensionRatio;
   protected int mDimensionRatioSide;
   int mDistToBottom;
   int mDistToLeft;
   int mDistToRight;
   int mDistToTop;
   private int mDrawHeight;
   private int mDrawWidth;
   private int mDrawX;
   private int mDrawY;
   boolean mGroupsToSolver;
   int mHeight;
   float mHorizontalBiasPercent;
   boolean mHorizontalChainFixedPosition;
   int mHorizontalChainStyle;
   ConstraintWidget mHorizontalNextWidget;
   public int mHorizontalResolution;
   boolean mHorizontalWrapVisited;
   boolean mIsHeightWrapContent;
   boolean mIsWidthWrapContent;
   ConstraintAnchor mLeft;
   boolean mLeftHasCentered;
   protected ConstraintAnchor[] mListAnchors;
   protected DimensionBehaviour[] mListDimensionBehaviors;
   protected ConstraintWidget[] mListNextMatchConstraintsWidget;
   int mMatchConstraintDefaultHeight;
   int mMatchConstraintDefaultWidth;
   int mMatchConstraintMaxHeight;
   int mMatchConstraintMaxWidth;
   int mMatchConstraintMinHeight;
   int mMatchConstraintMinWidth;
   float mMatchConstraintPercentHeight;
   float mMatchConstraintPercentWidth;
   private int[] mMaxDimension;
   protected int mMinHeight;
   protected int mMinWidth;
   protected ConstraintWidget[] mNextChainWidget;
   protected int mOffsetX;
   protected int mOffsetY;
   boolean mOptimizerMeasurable;
   boolean mOptimizerMeasured;
   ConstraintWidget mParent;
   int mRelX;
   int mRelY;
   ResolutionDimension mResolutionHeight;
   ResolutionDimension mResolutionWidth;
   float mResolvedDimensionRatio;
   int mResolvedDimensionRatioSide;
   int[] mResolvedMatchConstraintDefault;
   ConstraintAnchor mRight;
   boolean mRightHasCentered;
   ConstraintAnchor mTop;
   boolean mTopHasCentered;
   private String mType;
   float mVerticalBiasPercent;
   boolean mVerticalChainFixedPosition;
   int mVerticalChainStyle;
   ConstraintWidget mVerticalNextWidget;
   public int mVerticalResolution;
   boolean mVerticalWrapVisited;
   private int mVisibility;
   float[] mWeight;
   int mWidth;
   private int mWrapHeight;
   private int mWrapWidth;
   protected int mX;
   protected int mY;

   public ConstraintWidget() {
      this.mHorizontalResolution = -1;
      this.mVerticalResolution = -1;
      this.mMatchConstraintDefaultWidth = 0;
      this.mMatchConstraintDefaultHeight = 0;
      this.mResolvedMatchConstraintDefault = new int[2];
      this.mMatchConstraintMinWidth = 0;
      this.mMatchConstraintMaxWidth = 0;
      this.mMatchConstraintPercentWidth = 1.0F;
      this.mMatchConstraintMinHeight = 0;
      this.mMatchConstraintMaxHeight = 0;
      this.mMatchConstraintPercentHeight = 1.0F;
      this.mResolvedDimensionRatioSide = -1;
      this.mResolvedDimensionRatio = 1.0F;
      this.mBelongingGroup = null;
      this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
      this.mCircleConstraintAngle = 0.0F;
      this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
      this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
      this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
      this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
      this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
      this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
      this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
      ConstraintAnchor var2 = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
      this.mCenter = var2;
      this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, var2};
      this.mAnchors = new ArrayList();
      this.mListDimensionBehaviors = new DimensionBehaviour[]{ConstraintWidget.DimensionBehaviour.FIXED, ConstraintWidget.DimensionBehaviour.FIXED};
      this.mParent = null;
      this.mWidth = 0;
      this.mHeight = 0;
      this.mDimensionRatio = 0.0F;
      this.mDimensionRatioSide = -1;
      this.mX = 0;
      this.mY = 0;
      this.mRelX = 0;
      this.mRelY = 0;
      this.mDrawX = 0;
      this.mDrawY = 0;
      this.mDrawWidth = 0;
      this.mDrawHeight = 0;
      this.mOffsetX = 0;
      this.mOffsetY = 0;
      this.mBaselineDistance = 0;
      float var1 = DEFAULT_BIAS;
      this.mHorizontalBiasPercent = var1;
      this.mVerticalBiasPercent = var1;
      this.mContainerItemSkip = 0;
      this.mVisibility = 0;
      this.mDebugName = null;
      this.mType = null;
      this.mOptimizerMeasurable = false;
      this.mOptimizerMeasured = false;
      this.mGroupsToSolver = false;
      this.mHorizontalChainStyle = 0;
      this.mVerticalChainStyle = 0;
      this.mWeight = new float[]{-1.0F, -1.0F};
      this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
      this.mNextChainWidget = new ConstraintWidget[]{null, null};
      this.mHorizontalNextWidget = null;
      this.mVerticalNextWidget = null;
      this.addAnchors();
   }

   public ConstraintWidget(int var1, int var2) {
      this(0, 0, var1, var2);
   }

   public ConstraintWidget(int var1, int var2, int var3, int var4) {
      this.mHorizontalResolution = -1;
      this.mVerticalResolution = -1;
      this.mMatchConstraintDefaultWidth = 0;
      this.mMatchConstraintDefaultHeight = 0;
      this.mResolvedMatchConstraintDefault = new int[2];
      this.mMatchConstraintMinWidth = 0;
      this.mMatchConstraintMaxWidth = 0;
      this.mMatchConstraintPercentWidth = 1.0F;
      this.mMatchConstraintMinHeight = 0;
      this.mMatchConstraintMaxHeight = 0;
      this.mMatchConstraintPercentHeight = 1.0F;
      this.mResolvedDimensionRatioSide = -1;
      this.mResolvedDimensionRatio = 1.0F;
      this.mBelongingGroup = null;
      this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
      this.mCircleConstraintAngle = 0.0F;
      this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
      this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
      this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
      this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
      this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
      this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
      this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
      ConstraintAnchor var6 = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
      this.mCenter = var6;
      this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, var6};
      this.mAnchors = new ArrayList();
      this.mListDimensionBehaviors = new DimensionBehaviour[]{ConstraintWidget.DimensionBehaviour.FIXED, ConstraintWidget.DimensionBehaviour.FIXED};
      this.mParent = null;
      this.mDimensionRatio = 0.0F;
      this.mDimensionRatioSide = -1;
      this.mRelX = 0;
      this.mRelY = 0;
      this.mDrawX = 0;
      this.mDrawY = 0;
      this.mDrawWidth = 0;
      this.mDrawHeight = 0;
      this.mOffsetX = 0;
      this.mOffsetY = 0;
      this.mBaselineDistance = 0;
      float var5 = DEFAULT_BIAS;
      this.mHorizontalBiasPercent = var5;
      this.mVerticalBiasPercent = var5;
      this.mContainerItemSkip = 0;
      this.mVisibility = 0;
      this.mDebugName = null;
      this.mType = null;
      this.mOptimizerMeasurable = false;
      this.mOptimizerMeasured = false;
      this.mGroupsToSolver = false;
      this.mHorizontalChainStyle = 0;
      this.mVerticalChainStyle = 0;
      this.mWeight = new float[]{-1.0F, -1.0F};
      this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
      this.mNextChainWidget = new ConstraintWidget[]{null, null};
      this.mHorizontalNextWidget = null;
      this.mVerticalNextWidget = null;
      this.mX = var1;
      this.mY = var2;
      this.mWidth = var3;
      this.mHeight = var4;
      this.addAnchors();
      this.forceUpdateDrawPosition();
   }

   private void addAnchors() {
      this.mAnchors.add(this.mLeft);
      this.mAnchors.add(this.mTop);
      this.mAnchors.add(this.mRight);
      this.mAnchors.add(this.mBottom);
      this.mAnchors.add(this.mCenterX);
      this.mAnchors.add(this.mCenterY);
      this.mAnchors.add(this.mCenter);
      this.mAnchors.add(this.mBaseline);
   }

   private void applyConstraints(LinearSystem var1, boolean var2, SolverVariable var3, SolverVariable var4, DimensionBehaviour var5, boolean var6, ConstraintAnchor var7, ConstraintAnchor var8, int var9, int var10, int var11, int var12, float var13, boolean var14, boolean var15, int var16, int var17, int var18, float var19, boolean var20) {
      SolverVariable var30 = var1.createObjectVariable(var7);
      SolverVariable var27 = var1.createObjectVariable(var8);
      SolverVariable var28 = var1.createObjectVariable(var7.getTarget());
      SolverVariable var29 = var1.createObjectVariable(var8.getTarget());
      if (var1.graphOptimizer && var7.getResolutionNode().state == 1 && var8.getResolutionNode().state == 1) {
         if (LinearSystem.getMetrics() != null) {
            Metrics var31 = LinearSystem.getMetrics();
            ++var31.resolvedWidgets;
         }

         var7.getResolutionNode().addResolvedValue(var1);
         var8.getResolutionNode().addResolvedValue(var1);
         if (!var15 && var2) {
            var1.addGreaterThan(var4, var27, 0, 6);
         }

      } else {
         if (LinearSystem.getMetrics() != null) {
            Metrics var26 = LinearSystem.getMetrics();
            ++var26.nonresolvedWidgets;
         }

         byte var23 = var7.isConnected();
         boolean var25 = var8.isConnected();
         boolean var24 = this.mCenter.isConnected();
         int var21;
         if (var25) {
            var21 = var23 + 1;
         } else {
            var21 = var23;
         }

         int var22 = var21;
         if (var24) {
            var22 = var21 + 1;
         }

         if (var14) {
            var21 = 3;
         } else {
            var21 = var16;
         }

         var16 = null.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[var5.ordinal()];
         boolean var40;
         if (var16 != 1 && var16 != 2 && var16 != 3 && var16 == 4 && var21 != 4) {
            var40 = true;
         } else {
            var40 = false;
         }

         if (this.mVisibility == 8) {
            var10 = 0;
            var40 = false;
         }

         if (var20) {
            if (var23 == 0 && !var25 && !var24) {
               var1.addEquality(var30, var9);
            } else if (var23 != 0 && !var25) {
               var1.addEquality(var30, var28, var7.getMargin(), 6);
            }
         }

         boolean var37;
         boolean var38;
         if (!var40) {
            if (var6) {
               var1.addEquality(var27, var30, 0, 3);
               if (var11 > 0) {
                  var1.addGreaterThan(var27, var30, var11, 6);
               }

               if (var12 < Integer.MAX_VALUE) {
                  var1.addLowerThan(var27, var30, var12, 6);
               }
            } else {
               var1.addEquality(var27, var30, var10, 6);
            }

            var37 = var40;
            var10 = var18;
            var16 = var17;
         } else {
            var9 = var17;
            if (var17 == -2) {
               var9 = var10;
            }

            var12 = var18;
            if (var18 == -2) {
               var12 = var10;
            }

            var17 = var10;
            if (var9 > 0) {
               var1.addGreaterThan(var27, var30, var9, 6);
               var17 = Math.max(var10, var9);
            }

            var18 = var17;
            if (var12 > 0) {
               var1.addLowerThan(var27, var30, var12, 6);
               var18 = Math.min(var17, var12);
            }

            label277: {
               if (var21 == 1) {
                  if (var2) {
                     var1.addEquality(var27, var30, var18, 6);
                  } else if (var15) {
                     var1.addEquality(var27, var30, var18, 4);
                  } else {
                     var1.addEquality(var27, var30, var18, 1);
                  }
               } else if (var21 == 2) {
                  SolverVariable var32;
                  ConstraintWidget var42;
                  SolverVariable var43;
                  if (var7.getType() != ConstraintAnchor.Type.TOP && var7.getType() != ConstraintAnchor.Type.BOTTOM) {
                     var32 = var1.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.LEFT));
                     var42 = this.mParent;
                     var43 = var1.createObjectVariable(var42.getAnchor(ConstraintAnchor.Type.RIGHT));
                  } else {
                     var32 = var1.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.TOP));
                     var42 = this.mParent;
                     var43 = var1.createObjectVariable(var42.getAnchor(ConstraintAnchor.Type.BOTTOM));
                  }

                  var1.addConstraint(var1.createRow().createRowDimensionRatio(var27, var30, var43, var32, var19));
                  var38 = false;
                  break label277;
               }

               var38 = var40;
            }

            var17 = var12;
            if (var38 && var22 != 2 && !var14) {
               var12 = Math.max(var9, var18);
               var10 = var12;
               if (var17 > 0) {
                  var10 = Math.min(var17, var12);
               }

               var1.addEquality(var27, var30, var10, 6);
               var37 = false;
               var10 = var17;
               var16 = var9;
            } else {
               var37 = var38;
               var16 = var9;
               var10 = var12;
            }
         }

         if (var20 && !var15) {
            if (var23 == 0 && !var25 && !var24) {
               if (var2) {
                  var1.addGreaterThan(var4, var27, 0, 5);
               }
            } else if (var23 != 0 && !var25) {
               if (var2) {
                  var1.addGreaterThan(var4, var27, 0, 5);
               }
            } else if (var23 == 0 && var25) {
               var1.addEquality(var27, var29, -var8.getMargin(), 6);
               if (var2) {
                  var1.addGreaterThan(var30, var3, 0, 5);
               }
            } else if (var23 != 0 && var25) {
               boolean var33;
               byte var34;
               boolean var35;
               byte var39;
               if (var37) {
                  if (var2 && var11 == 0) {
                     var1.addGreaterThan(var27, var30, 0, 6);
                  }

                  if (var21 == 0) {
                     byte var36;
                     if (var10 <= 0 && var16 <= 0) {
                        var33 = false;
                        var36 = 6;
                     } else {
                        var36 = 4;
                        var33 = true;
                     }

                     var1.addEquality(var30, var28, var7.getMargin(), var36);
                     var1.addEquality(var27, var29, -var8.getMargin(), var36);
                     if (var10 <= 0 && var16 <= 0) {
                        var38 = false;
                     } else {
                        var38 = true;
                     }

                     byte var41 = 5;
                     var35 = var33;
                     var33 = var38;
                     var39 = var41;
                  } else {
                     label239: {
                        if (var21 == 1) {
                           var39 = 6;
                        } else {
                           if (var21 != 3) {
                              var39 = 5;
                              var33 = false;
                              break label239;
                           }

                           if (!var14 && this.mResolvedDimensionRatioSide != -1 && var10 <= 0) {
                              var34 = 6;
                           } else {
                              var34 = 4;
                           }

                           var1.addEquality(var30, var28, var7.getMargin(), var34);
                           var1.addEquality(var27, var29, -var8.getMargin(), var34);
                           var39 = 5;
                        }

                        var33 = true;
                     }

                     var35 = var33;
                  }
               } else {
                  var39 = 5;
                  var35 = false;
                  var33 = true;
               }

               label214: {
                  if (var33) {
                     var1.addCentering(var30, var28, var7.getMargin(), var13, var29, var27, var8.getMargin(), var39);
                     var14 = var7.mTarget.mOwner instanceof Barrier;
                     var6 = var8.mTarget.mOwner instanceof Barrier;
                     if (var14 && !var6) {
                        var6 = var2;
                        var39 = 6;
                        var34 = 5;
                        var14 = true;
                        break label214;
                     }

                     if (!var14 && var6) {
                        var14 = var2;
                        var39 = 5;
                        var34 = 6;
                        var6 = true;
                        break label214;
                     }
                  }

                  var6 = var2;
                  var14 = var2;
                  var39 = 5;
                  var34 = 5;
               }

               if (var35) {
                  var39 = 6;
                  var34 = 6;
               }

               if (!var37 && var6 || var35) {
                  var1.addGreaterThan(var30, var28, var7.getMargin(), var34);
               }

               if (!var37 && var14 || var35) {
                  var1.addLowerThan(var27, var29, -var8.getMargin(), var39);
               }

               if (var2) {
                  var1.addGreaterThan(var30, var3, 0, 6);
               }
            }

            if (var2) {
               var1.addGreaterThan(var4, var27, 0, 6);
            }

         } else {
            if (var22 < 2 && var2) {
               var1.addGreaterThan(var30, var3, 0, 6);
               var1.addGreaterThan(var4, var27, 0, 6);
            }

         }
      }
   }

   private boolean isChainHead(int var1) {
      var1 *= 2;
      ConstraintAnchor var3 = this.mListAnchors[var1].mTarget;
      boolean var2 = true;
      if (var3 != null) {
         var3 = this.mListAnchors[var1].mTarget.mTarget;
         ConstraintAnchor[] var4 = this.mListAnchors;
         if (var3 != var4[var1]) {
            ++var1;
            if (var4[var1].mTarget != null && this.mListAnchors[var1].mTarget.mTarget == this.mListAnchors[var1]) {
               return var2;
            }
         }
      }

      var2 = false;
      return var2;
   }

   public void addToSolver(LinearSystem var1) {
      SolverVariable var21 = var1.createObjectVariable(this.mLeft);
      SolverVariable var22 = var1.createObjectVariable(this.mRight);
      SolverVariable var19 = var1.createObjectVariable(this.mTop);
      SolverVariable var20 = var1.createObjectVariable(this.mBottom);
      SolverVariable var23 = var1.createObjectVariable(this.mBaseline);
      ConstraintWidget var17 = this.mParent;
      boolean var10;
      boolean var11;
      boolean var12;
      boolean var13;
      boolean var14;
      boolean var15;
      if (var17 != null) {
         if (var17 != null && var17.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
            var10 = true;
         } else {
            var10 = false;
         }

         var17 = this.mParent;
         if (var17 != null && var17.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
            var11 = true;
         } else {
            var11 = false;
         }

         if (this.isChainHead(0)) {
            ((ConstraintWidgetContainer)this.mParent).addChain(this, 0);
            var12 = true;
         } else {
            var12 = this.isInHorizontalChain();
         }

         if (this.isChainHead(1)) {
            ((ConstraintWidgetContainer)this.mParent).addChain(this, 1);
            var13 = true;
         } else {
            var13 = this.isInVerticalChain();
         }

         if (var10 && this.mVisibility != 8 && this.mLeft.mTarget == null && this.mRight.mTarget == null) {
            var1.addGreaterThan(var1.createObjectVariable(this.mParent.mRight), var22, 0, 1);
         }

         if (var11 && this.mVisibility != 8 && this.mTop.mTarget == null && this.mBottom.mTarget == null && this.mBaseline == null) {
            var1.addGreaterThan(var1.createObjectVariable(this.mParent.mBottom), var20, 0, 1);
         }

         var15 = var12;
         var14 = var13;
         var12 = var10;
         var10 = var11;
         var13 = var15;
         var11 = var14;
      } else {
         var14 = false;
         var12 = false;
         var11 = var12;
         var13 = var12;
         var10 = var12;
         var12 = var14;
      }

      int var3 = this.mWidth;
      int var5 = this.mMinWidth;
      int var4 = var3;
      if (var3 < var5) {
         var4 = var5;
      }

      int var6 = this.mHeight;
      var5 = this.mMinHeight;
      var3 = var6;
      if (var6 < var5) {
         var3 = var5;
      }

      if (this.mListDimensionBehaviors[0] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
         var14 = true;
      } else {
         var14 = false;
      }

      if (this.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
         var15 = true;
      } else {
         var15 = false;
      }

      int var7;
      int var8;
      boolean var25;
      label219: {
         label245: {
            this.mResolvedDimensionRatioSide = this.mDimensionRatioSide;
            float var2 = this.mDimensionRatio;
            this.mResolvedDimensionRatio = var2;
            var5 = this.mMatchConstraintDefaultWidth;
            var7 = this.mMatchConstraintDefaultHeight;
            if (var2 > 0.0F && this.mVisibility != 8) {
               label249: {
                  var6 = var5;
                  if (this.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                     var6 = var5;
                     if (var5 == 0) {
                        var6 = 3;
                     }
                  }

                  var5 = var7;
                  if (this.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                     var5 = var7;
                     if (var7 == 0) {
                        var5 = 3;
                     }
                  }

                  if (this.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && var6 == 3 && var5 == 3) {
                     this.setupDimensionRatio(var12, var10, var14, var15);
                  } else {
                     DimensionBehaviour var18;
                     DimensionBehaviour var27;
                     if (this.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && var6 == 3) {
                        this.mResolvedDimensionRatioSide = 0;
                        var4 = (int)(this.mResolvedDimensionRatio * (float)this.mHeight);
                        var27 = this.mListDimensionBehaviors[1];
                        var18 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                        if (var27 == var18) {
                           var8 = var3;
                           boolean var26 = true;
                           var7 = var5;
                           var3 = var4;
                           var4 = var8;
                           var25 = var26;
                           break label219;
                        }

                        var7 = var3;
                        var6 = var5;
                        var5 = 4;
                        var3 = var4;
                        var4 = var7;
                        break label249;
                     }

                     if (this.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && var5 == 3) {
                        this.mResolvedDimensionRatioSide = 1;
                        if (this.mDimensionRatioSide == -1) {
                           this.mResolvedDimensionRatio = 1.0F / this.mResolvedDimensionRatio;
                        }

                        var8 = (int)(this.mResolvedDimensionRatio * (float)this.mWidth);
                        var18 = this.mListDimensionBehaviors[0];
                        var27 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                        int var9 = var6;
                        var3 = var4;
                        var7 = var8;
                        if (var18 == var27) {
                           break label245;
                        }

                        var6 = 4;
                        var4 = var8;
                        var5 = var9;
                        break label249;
                     }
                  }

                  var7 = var3;
                  break label245;
               }
            } else {
               var8 = var4;
               var6 = var7;
               var4 = var3;
               var3 = var8;
            }

            boolean var24 = false;
            var7 = var6;
            var6 = var5;
            var25 = var24;
            break label219;
         }

         var3 = var4;
         var8 = var5;
         var25 = true;
         var4 = var7;
         var7 = var8;
      }

      label188: {
         int[] var28 = this.mResolvedMatchConstraintDefault;
         var28[0] = var6;
         var28[1] = var7;
         if (var25) {
            var8 = this.mResolvedDimensionRatioSide;
            if (var8 == 0 || var8 == -1) {
               var14 = true;
               break label188;
            }
         }

         var14 = false;
      }

      if (this.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && this instanceof ConstraintWidgetContainer) {
         var15 = true;
      } else {
         var15 = false;
      }

      boolean var16 = this.mCenter.isConnected() ^ true;
      ConstraintWidget var29;
      SolverVariable var30;
      SolverVariable var31;
      if (this.mHorizontalResolution != 2) {
         var17 = this.mParent;
         if (var17 != null) {
            var30 = var1.createObjectVariable(var17.mRight);
         } else {
            var30 = null;
         }

         var29 = this.mParent;
         if (var29 != null) {
            var31 = var1.createObjectVariable(var29.mLeft);
         } else {
            var31 = null;
         }

         this.applyConstraints(var1, var12, var31, var30, this.mListDimensionBehaviors[0], var15, this.mLeft, this.mRight, this.mX, var3, this.mMinWidth, this.mMaxDimension[0], this.mHorizontalBiasPercent, var14, var13, var6, this.mMatchConstraintMinWidth, this.mMatchConstraintMaxWidth, this.mMatchConstraintPercentWidth, var16);
      }

      if (this.mVerticalResolution != 2) {
         if (this.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && this instanceof ConstraintWidgetContainer) {
            var12 = true;
         } else {
            var12 = false;
         }

         label166: {
            if (var25) {
               var3 = this.mResolvedDimensionRatioSide;
               if (var3 == 1 || var3 == -1) {
                  var13 = true;
                  break label166;
               }
            }

            var13 = false;
         }

         label158: {
            if (this.mBaselineDistance > 0) {
               if (this.mBaseline.getResolutionNode().state == 1) {
                  this.mBaseline.getResolutionNode().addResolvedValue(var1);
               } else {
                  var1.addEquality(var23, var19, this.getBaselineDistance(), 6);
                  if (this.mBaseline.mTarget != null) {
                     var1.addEquality(var23, var1.createObjectVariable(this.mBaseline.mTarget), 0, 6);
                     var14 = false;
                     break label158;
                  }
               }
            }

            var14 = var16;
         }

         var17 = this.mParent;
         if (var17 != null) {
            var30 = var1.createObjectVariable(var17.mBottom);
         } else {
            var30 = null;
         }

         var29 = this.mParent;
         if (var29 != null) {
            var31 = var1.createObjectVariable(var29.mTop);
         } else {
            var31 = null;
         }

         this.applyConstraints(var1, var10, var31, var30, this.mListDimensionBehaviors[1], var12, this.mTop, this.mBottom, this.mY, var4, this.mMinHeight, this.mMaxDimension[1], this.mVerticalBiasPercent, var13, var11, var7, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight, this.mMatchConstraintPercentHeight, var14);
         if (var25) {
            if (this.mResolvedDimensionRatioSide == 1) {
               var1.addRatio(var20, var19, var22, var21, this.mResolvedDimensionRatio, 6);
            } else {
               var1.addRatio(var22, var21, var20, var19, this.mResolvedDimensionRatio, 6);
            }
         }

         if (this.mCenter.isConnected()) {
            var1.addCenterPoint(this, this.mCenter.getTarget().getOwner(), (float)Math.toRadians((double)(this.mCircleConstraintAngle + 90.0F)), this.mCenter.getMargin());
         }

      }
   }

   public boolean allowedInBarrier() {
      boolean var1;
      if (this.mVisibility != 8) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void analyze(int var1) {
      Optimizer.analyze(var1, this);
   }

   public void connect(ConstraintAnchor.Type var1, ConstraintWidget var2, ConstraintAnchor.Type var3) {
      this.connect(var1, var2, var3, 0, ConstraintAnchor.Strength.STRONG);
   }

   public void connect(ConstraintAnchor.Type var1, ConstraintWidget var2, ConstraintAnchor.Type var3, int var4) {
      this.connect(var1, var2, var3, var4, ConstraintAnchor.Strength.STRONG);
   }

   public void connect(ConstraintAnchor.Type var1, ConstraintWidget var2, ConstraintAnchor.Type var3, int var4, ConstraintAnchor.Strength var5) {
      this.connect(var1, var2, var3, var4, var5, 0);
   }

   public void connect(ConstraintAnchor.Type var1, ConstraintWidget var2, ConstraintAnchor.Type var3, int var4, ConstraintAnchor.Strength var5, int var6) {
      ConstraintAnchor.Type var8 = ConstraintAnchor.Type.CENTER;
      byte var7 = 0;
      ConstraintAnchor var10;
      ConstraintAnchor var12;
      ConstraintAnchor var15;
      if (var1 == var8) {
         if (var3 == ConstraintAnchor.Type.CENTER) {
            ConstraintAnchor var9 = this.getAnchor(ConstraintAnchor.Type.LEFT);
            var10 = this.getAnchor(ConstraintAnchor.Type.RIGHT);
            var12 = this.getAnchor(ConstraintAnchor.Type.TOP);
            var15 = this.getAnchor(ConstraintAnchor.Type.BOTTOM);
            boolean var14 = true;
            boolean var13;
            if ((var9 == null || !var9.isConnected()) && (var10 == null || !var10.isConnected())) {
               this.connect(ConstraintAnchor.Type.LEFT, var2, ConstraintAnchor.Type.LEFT, 0, var5, var6);
               this.connect(ConstraintAnchor.Type.RIGHT, var2, ConstraintAnchor.Type.RIGHT, 0, var5, var6);
               var13 = true;
            } else {
               var13 = false;
            }

            if ((var12 == null || !var12.isConnected()) && (var15 == null || !var15.isConnected())) {
               this.connect(ConstraintAnchor.Type.TOP, var2, ConstraintAnchor.Type.TOP, 0, var5, var6);
               this.connect(ConstraintAnchor.Type.BOTTOM, var2, ConstraintAnchor.Type.BOTTOM, 0, var5, var6);
            } else {
               var14 = false;
            }

            if (var13 && var14) {
               this.getAnchor(ConstraintAnchor.Type.CENTER).connect(var2.getAnchor(ConstraintAnchor.Type.CENTER), 0, var6);
            } else if (var13) {
               this.getAnchor(ConstraintAnchor.Type.CENTER_X).connect(var2.getAnchor(ConstraintAnchor.Type.CENTER_X), 0, var6);
            } else if (var14) {
               this.getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(var2.getAnchor(ConstraintAnchor.Type.CENTER_Y), 0, var6);
            }
         } else if (var3 != ConstraintAnchor.Type.LEFT && var3 != ConstraintAnchor.Type.RIGHT) {
            if (var3 == ConstraintAnchor.Type.TOP || var3 == ConstraintAnchor.Type.BOTTOM) {
               this.connect(ConstraintAnchor.Type.TOP, var2, var3, 0, var5, var6);
               this.connect(ConstraintAnchor.Type.BOTTOM, var2, var3, 0, var5, var6);
               this.getAnchor(ConstraintAnchor.Type.CENTER).connect(var2.getAnchor(var3), 0, var6);
            }
         } else {
            this.connect(ConstraintAnchor.Type.LEFT, var2, var3, 0, var5, var6);
            this.connect(ConstraintAnchor.Type.RIGHT, var2, var3, 0, var5, var6);
            this.getAnchor(ConstraintAnchor.Type.CENTER).connect(var2.getAnchor(var3), 0, var6);
         }
      } else {
         ConstraintAnchor var11;
         if (var1 != ConstraintAnchor.Type.CENTER_X || var3 != ConstraintAnchor.Type.LEFT && var3 != ConstraintAnchor.Type.RIGHT) {
            if (var1 != ConstraintAnchor.Type.CENTER_Y || var3 != ConstraintAnchor.Type.TOP && var3 != ConstraintAnchor.Type.BOTTOM) {
               if (var1 == ConstraintAnchor.Type.CENTER_X && var3 == ConstraintAnchor.Type.CENTER_X) {
                  this.getAnchor(ConstraintAnchor.Type.LEFT).connect(var2.getAnchor(ConstraintAnchor.Type.LEFT), 0, var6);
                  this.getAnchor(ConstraintAnchor.Type.RIGHT).connect(var2.getAnchor(ConstraintAnchor.Type.RIGHT), 0, var6);
                  this.getAnchor(ConstraintAnchor.Type.CENTER_X).connect(var2.getAnchor(var3), 0, var6);
               } else if (var1 == ConstraintAnchor.Type.CENTER_Y && var3 == ConstraintAnchor.Type.CENTER_Y) {
                  this.getAnchor(ConstraintAnchor.Type.TOP).connect(var2.getAnchor(ConstraintAnchor.Type.TOP), 0, var6);
                  this.getAnchor(ConstraintAnchor.Type.BOTTOM).connect(var2.getAnchor(ConstraintAnchor.Type.BOTTOM), 0, var6);
                  this.getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(var2.getAnchor(var3), 0, var6);
               } else {
                  var15 = this.getAnchor(var1);
                  var11 = var2.getAnchor(var3);
                  if (var15.isValidConnection(var11)) {
                     if (var1 == ConstraintAnchor.Type.BASELINE) {
                        var12 = this.getAnchor(ConstraintAnchor.Type.TOP);
                        var10 = this.getAnchor(ConstraintAnchor.Type.BOTTOM);
                        if (var12 != null) {
                           var12.reset();
                        }

                        var4 = var7;
                        if (var10 != null) {
                           var10.reset();
                           var4 = var7;
                        }
                     } else if (var1 != ConstraintAnchor.Type.TOP && var1 != ConstraintAnchor.Type.BOTTOM) {
                        if (var1 == ConstraintAnchor.Type.LEFT || var1 == ConstraintAnchor.Type.RIGHT) {
                           var12 = this.getAnchor(ConstraintAnchor.Type.CENTER);
                           if (var12.getTarget() != var11) {
                              var12.reset();
                           }

                           var12 = this.getAnchor(var1).getOpposite();
                           var10 = this.getAnchor(ConstraintAnchor.Type.CENTER_X);
                           if (var10.isConnected()) {
                              var12.reset();
                              var10.reset();
                           }
                        }
                     } else {
                        var12 = this.getAnchor(ConstraintAnchor.Type.BASELINE);
                        if (var12 != null) {
                           var12.reset();
                        }

                        var12 = this.getAnchor(ConstraintAnchor.Type.CENTER);
                        if (var12.getTarget() != var11) {
                           var12.reset();
                        }

                        var12 = this.getAnchor(var1).getOpposite();
                        var10 = this.getAnchor(ConstraintAnchor.Type.CENTER_Y);
                        if (var10.isConnected()) {
                           var12.reset();
                           var10.reset();
                        }
                     }

                     var15.connect(var11, var4, var5, var6);
                     var11.getOwner().connectedTo(var15.getOwner());
                  }
               }
            } else {
               var10 = var2.getAnchor(var3);
               this.getAnchor(ConstraintAnchor.Type.TOP).connect(var10, 0, var6);
               this.getAnchor(ConstraintAnchor.Type.BOTTOM).connect(var10, 0, var6);
               this.getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(var10, 0, var6);
            }
         } else {
            var10 = this.getAnchor(ConstraintAnchor.Type.LEFT);
            var11 = var2.getAnchor(var3);
            var12 = this.getAnchor(ConstraintAnchor.Type.RIGHT);
            var10.connect(var11, 0, var6);
            var12.connect(var11, 0, var6);
            this.getAnchor(ConstraintAnchor.Type.CENTER_X).connect(var11, 0, var6);
         }
      }

   }

   public void connect(ConstraintAnchor var1, ConstraintAnchor var2, int var3) {
      this.connect(var1, var2, var3, ConstraintAnchor.Strength.STRONG, 0);
   }

   public void connect(ConstraintAnchor var1, ConstraintAnchor var2, int var3, int var4) {
      this.connect(var1, var2, var3, ConstraintAnchor.Strength.STRONG, var4);
   }

   public void connect(ConstraintAnchor var1, ConstraintAnchor var2, int var3, ConstraintAnchor.Strength var4, int var5) {
      if (var1.getOwner() == this) {
         this.connect(var1.getType(), var2.getOwner(), var2.getType(), var3, var4, var5);
      }

   }

   public void connectCircularConstraint(ConstraintWidget var1, float var2, int var3) {
      this.immediateConnect(ConstraintAnchor.Type.CENTER, var1, ConstraintAnchor.Type.CENTER, var3, 0);
      this.mCircleConstraintAngle = var2;
   }

   public void connectedTo(ConstraintWidget var1) {
   }

   public void createObjectVariables(LinearSystem var1) {
      var1.createObjectVariable(this.mLeft);
      var1.createObjectVariable(this.mTop);
      var1.createObjectVariable(this.mRight);
      var1.createObjectVariable(this.mBottom);
      if (this.mBaselineDistance > 0) {
         var1.createObjectVariable(this.mBaseline);
      }

   }

   public void disconnectUnlockedWidget(ConstraintWidget var1) {
      ArrayList var4 = this.getAnchors();
      int var3 = var4.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         ConstraintAnchor var5 = (ConstraintAnchor)var4.get(var2);
         if (var5.isConnected() && var5.getTarget().getOwner() == var1 && var5.getConnectionCreator() == 2) {
            var5.reset();
         }
      }

   }

   public void disconnectWidget(ConstraintWidget var1) {
      ArrayList var5 = this.getAnchors();
      int var3 = var5.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         ConstraintAnchor var4 = (ConstraintAnchor)var5.get(var2);
         if (var4.isConnected() && var4.getTarget().getOwner() == var1) {
            var4.reset();
         }
      }

   }

   public void forceUpdateDrawPosition() {
      int var2 = this.mX;
      int var3 = this.mY;
      int var1 = this.mWidth;
      int var4 = this.mHeight;
      this.mDrawX = var2;
      this.mDrawY = var3;
      this.mDrawWidth = var1 + var2 - var2;
      this.mDrawHeight = var4 + var3 - var3;
   }

   public ConstraintAnchor getAnchor(ConstraintAnchor.Type var1) {
      switch (null.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[var1.ordinal()]) {
         case 1:
            return this.mLeft;
         case 2:
            return this.mTop;
         case 3:
            return this.mRight;
         case 4:
            return this.mBottom;
         case 5:
            return this.mBaseline;
         case 6:
            return this.mCenter;
         case 7:
            return this.mCenterX;
         case 8:
            return this.mCenterY;
         case 9:
            return null;
         default:
            throw new AssertionError(var1.name());
      }
   }

   public ArrayList getAnchors() {
      return this.mAnchors;
   }

   public int getBaselineDistance() {
      return this.mBaselineDistance;
   }

   public float getBiasPercent(int var1) {
      if (var1 == 0) {
         return this.mHorizontalBiasPercent;
      } else {
         return var1 == 1 ? this.mVerticalBiasPercent : -1.0F;
      }
   }

   public int getBottom() {
      return this.getY() + this.mHeight;
   }

   public Object getCompanionWidget() {
      return this.mCompanionWidget;
   }

   public int getContainerItemSkip() {
      return this.mContainerItemSkip;
   }

   public String getDebugName() {
      return this.mDebugName;
   }

   public DimensionBehaviour getDimensionBehaviour(int var1) {
      if (var1 == 0) {
         return this.getHorizontalDimensionBehaviour();
      } else {
         return var1 == 1 ? this.getVerticalDimensionBehaviour() : null;
      }
   }

   public float getDimensionRatio() {
      return this.mDimensionRatio;
   }

   public int getDimensionRatioSide() {
      return this.mDimensionRatioSide;
   }

   public int getDrawBottom() {
      return this.getDrawY() + this.mDrawHeight;
   }

   public int getDrawHeight() {
      return this.mDrawHeight;
   }

   public int getDrawRight() {
      return this.getDrawX() + this.mDrawWidth;
   }

   public int getDrawWidth() {
      return this.mDrawWidth;
   }

   public int getDrawX() {
      return this.mDrawX + this.mOffsetX;
   }

   public int getDrawY() {
      return this.mDrawY + this.mOffsetY;
   }

   public int getHeight() {
      return this.mVisibility == 8 ? 0 : this.mHeight;
   }

   public float getHorizontalBiasPercent() {
      return this.mHorizontalBiasPercent;
   }

   public ConstraintWidget getHorizontalChainControlWidget() {
      boolean var1 = this.isInHorizontalChain();
      ConstraintWidget var2 = null;
      if (var1) {
         var2 = this;
         ConstraintWidget var3 = null;

         while(var3 == null && var2 != null) {
            ConstraintAnchor var4 = var2.getAnchor(ConstraintAnchor.Type.LEFT);
            if (var4 == null) {
               var4 = null;
            } else {
               var4 = var4.getTarget();
            }

            ConstraintWidget var6;
            if (var4 == null) {
               var6 = null;
            } else {
               var6 = var4.getOwner();
            }

            if (var6 == this.getParent()) {
               return var2;
            }

            ConstraintAnchor var5;
            if (var6 == null) {
               var5 = null;
            } else {
               var5 = var6.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget();
            }

            if (var5 != null && var5.getOwner() != var2) {
               var3 = var2;
            } else {
               var2 = var6;
            }
         }

         var2 = var3;
      }

      return var2;
   }

   public int getHorizontalChainStyle() {
      return this.mHorizontalChainStyle;
   }

   public DimensionBehaviour getHorizontalDimensionBehaviour() {
      return this.mListDimensionBehaviors[0];
   }

   public int getInternalDrawBottom() {
      return this.mDrawY + this.mDrawHeight;
   }

   public int getInternalDrawRight() {
      return this.mDrawX + this.mDrawWidth;
   }

   int getInternalDrawX() {
      return this.mDrawX;
   }

   int getInternalDrawY() {
      return this.mDrawY;
   }

   public int getLeft() {
      return this.getX();
   }

   public int getLength(int var1) {
      if (var1 == 0) {
         return this.getWidth();
      } else {
         return var1 == 1 ? this.getHeight() : 0;
      }
   }

   public int getMaxHeight() {
      return this.mMaxDimension[1];
   }

   public int getMaxWidth() {
      return this.mMaxDimension[0];
   }

   public int getMinHeight() {
      return this.mMinHeight;
   }

   public int getMinWidth() {
      return this.mMinWidth;
   }

   public int getOptimizerWrapHeight() {
      int var1 = this.mHeight;
      int var2 = var1;
      if (this.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
         if (this.mMatchConstraintDefaultHeight == 1) {
            var1 = Math.max(this.mMatchConstraintMinHeight, var1);
         } else {
            var1 = this.mMatchConstraintMinHeight;
            if (var1 > 0) {
               this.mHeight = var1;
            } else {
               var1 = 0;
            }
         }

         int var3 = this.mMatchConstraintMaxHeight;
         var2 = var1;
         if (var3 > 0) {
            var2 = var1;
            if (var3 < var1) {
               var2 = var3;
            }
         }
      }

      return var2;
   }

   public int getOptimizerWrapWidth() {
      int var1 = this.mWidth;
      int var2 = var1;
      if (this.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
         if (this.mMatchConstraintDefaultWidth == 1) {
            var1 = Math.max(this.mMatchConstraintMinWidth, var1);
         } else {
            var1 = this.mMatchConstraintMinWidth;
            if (var1 > 0) {
               this.mWidth = var1;
            } else {
               var1 = 0;
            }
         }

         int var3 = this.mMatchConstraintMaxWidth;
         var2 = var1;
         if (var3 > 0) {
            var2 = var1;
            if (var3 < var1) {
               var2 = var3;
            }
         }
      }

      return var2;
   }

   public ConstraintWidget getParent() {
      return this.mParent;
   }

   int getRelativePositioning(int var1) {
      if (var1 == 0) {
         return this.mRelX;
      } else {
         return var1 == 1 ? this.mRelY : 0;
      }
   }

   public ResolutionDimension getResolutionHeight() {
      if (this.mResolutionHeight == null) {
         this.mResolutionHeight = new ResolutionDimension();
      }

      return this.mResolutionHeight;
   }

   public ResolutionDimension getResolutionWidth() {
      if (this.mResolutionWidth == null) {
         this.mResolutionWidth = new ResolutionDimension();
      }

      return this.mResolutionWidth;
   }

   public int getRight() {
      return this.getX() + this.mWidth;
   }

   public WidgetContainer getRootWidgetContainer() {
      ConstraintWidget var1;
      for(var1 = this; var1.getParent() != null; var1 = var1.getParent()) {
      }

      return var1 instanceof WidgetContainer ? (WidgetContainer)var1 : null;
   }

   protected int getRootX() {
      return this.mX + this.mOffsetX;
   }

   protected int getRootY() {
      return this.mY + this.mOffsetY;
   }

   public int getTop() {
      return this.getY();
   }

   public String getType() {
      return this.mType;
   }

   public float getVerticalBiasPercent() {
      return this.mVerticalBiasPercent;
   }

   public ConstraintWidget getVerticalChainControlWidget() {
      boolean var1 = this.isInVerticalChain();
      ConstraintWidget var2 = null;
      if (var1) {
         var2 = this;
         ConstraintWidget var3 = null;

         while(var3 == null && var2 != null) {
            ConstraintAnchor var4 = var2.getAnchor(ConstraintAnchor.Type.TOP);
            if (var4 == null) {
               var4 = null;
            } else {
               var4 = var4.getTarget();
            }

            ConstraintWidget var6;
            if (var4 == null) {
               var6 = null;
            } else {
               var6 = var4.getOwner();
            }

            if (var6 == this.getParent()) {
               return var2;
            }

            ConstraintAnchor var5;
            if (var6 == null) {
               var5 = null;
            } else {
               var5 = var6.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget();
            }

            if (var5 != null && var5.getOwner() != var2) {
               var3 = var2;
            } else {
               var2 = var6;
            }
         }

         var2 = var3;
      }

      return var2;
   }

   public int getVerticalChainStyle() {
      return this.mVerticalChainStyle;
   }

   public DimensionBehaviour getVerticalDimensionBehaviour() {
      return this.mListDimensionBehaviors[1];
   }

   public int getVisibility() {
      return this.mVisibility;
   }

   public int getWidth() {
      return this.mVisibility == 8 ? 0 : this.mWidth;
   }

   public int getWrapHeight() {
      return this.mWrapHeight;
   }

   public int getWrapWidth() {
      return this.mWrapWidth;
   }

   public int getX() {
      return this.mX;
   }

   public int getY() {
      return this.mY;
   }

   public boolean hasAncestor(ConstraintWidget var1) {
      ConstraintWidget var3 = this.getParent();
      if (var3 == var1) {
         return true;
      } else {
         ConstraintWidget var2 = var3;
         if (var3 == var1.getParent()) {
            return false;
         } else {
            while(var2 != null) {
               if (var2 == var1) {
                  return true;
               }

               if (var2 == var1.getParent()) {
                  return true;
               }

               var2 = var2.getParent();
            }

            return false;
         }
      }
   }

   public boolean hasBaseline() {
      boolean var1;
      if (this.mBaselineDistance > 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void immediateConnect(ConstraintAnchor.Type var1, ConstraintWidget var2, ConstraintAnchor.Type var3, int var4, int var5) {
      this.getAnchor(var1).connect(var2.getAnchor(var3), var4, var5, ConstraintAnchor.Strength.STRONG, 0, true);
   }

   public boolean isFullyResolved() {
      return this.mLeft.getResolutionNode().state == 1 && this.mRight.getResolutionNode().state == 1 && this.mTop.getResolutionNode().state == 1 && this.mBottom.getResolutionNode().state == 1;
   }

   public boolean isHeightWrapContent() {
      return this.mIsHeightWrapContent;
   }

   public boolean isInHorizontalChain() {
      return this.mLeft.mTarget != null && this.mLeft.mTarget.mTarget == this.mLeft || this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight;
   }

   public boolean isInVerticalChain() {
      return this.mTop.mTarget != null && this.mTop.mTarget.mTarget == this.mTop || this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom;
   }

   public boolean isInsideConstraintLayout() {
      ConstraintWidget var2 = this.getParent();
      ConstraintWidget var1 = var2;
      if (var2 == null) {
         return false;
      } else {
         while(var1 != null) {
            if (var1 instanceof ConstraintWidgetContainer) {
               return true;
            }

            var1 = var1.getParent();
         }

         return false;
      }
   }

   public boolean isRoot() {
      boolean var1;
      if (this.mParent == null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isRootContainer() {
      boolean var1;
      if (this instanceof ConstraintWidgetContainer) {
         ConstraintWidget var2 = this.mParent;
         if (var2 == null || !(var2 instanceof ConstraintWidgetContainer)) {
            var1 = true;
            return var1;
         }
      }

      var1 = false;
      return var1;
   }

   public boolean isSpreadHeight() {
      int var1 = this.mMatchConstraintDefaultHeight;
      boolean var2 = true;
      if (var1 != 0 || this.mDimensionRatio != 0.0F || this.mMatchConstraintMinHeight != 0 || this.mMatchConstraintMaxHeight != 0 || this.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
         var2 = false;
      }

      return var2;
   }

   public boolean isSpreadWidth() {
      int var1 = this.mMatchConstraintDefaultWidth;
      boolean var3 = false;
      boolean var2 = var3;
      if (var1 == 0) {
         var2 = var3;
         if (this.mDimensionRatio == 0.0F) {
            var2 = var3;
            if (this.mMatchConstraintMinWidth == 0) {
               var2 = var3;
               if (this.mMatchConstraintMaxWidth == 0) {
                  var2 = var3;
                  if (this.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                     var2 = true;
                  }
               }
            }
         }
      }

      return var2;
   }

   public boolean isWidthWrapContent() {
      return this.mIsWidthWrapContent;
   }

   public void reset() {
      this.mLeft.reset();
      this.mTop.reset();
      this.mRight.reset();
      this.mBottom.reset();
      this.mBaseline.reset();
      this.mCenterX.reset();
      this.mCenterY.reset();
      this.mCenter.reset();
      this.mParent = null;
      this.mCircleConstraintAngle = 0.0F;
      this.mWidth = 0;
      this.mHeight = 0;
      this.mDimensionRatio = 0.0F;
      this.mDimensionRatioSide = -1;
      this.mX = 0;
      this.mY = 0;
      this.mDrawX = 0;
      this.mDrawY = 0;
      this.mDrawWidth = 0;
      this.mDrawHeight = 0;
      this.mOffsetX = 0;
      this.mOffsetY = 0;
      this.mBaselineDistance = 0;
      this.mMinWidth = 0;
      this.mMinHeight = 0;
      this.mWrapWidth = 0;
      this.mWrapHeight = 0;
      float var1 = DEFAULT_BIAS;
      this.mHorizontalBiasPercent = var1;
      this.mVerticalBiasPercent = var1;
      this.mListDimensionBehaviors[0] = ConstraintWidget.DimensionBehaviour.FIXED;
      this.mListDimensionBehaviors[1] = ConstraintWidget.DimensionBehaviour.FIXED;
      this.mCompanionWidget = null;
      this.mContainerItemSkip = 0;
      this.mVisibility = 0;
      this.mType = null;
      this.mHorizontalWrapVisited = false;
      this.mVerticalWrapVisited = false;
      this.mHorizontalChainStyle = 0;
      this.mVerticalChainStyle = 0;
      this.mHorizontalChainFixedPosition = false;
      this.mVerticalChainFixedPosition = false;
      float[] var2 = this.mWeight;
      var2[0] = -1.0F;
      var2[1] = -1.0F;
      this.mHorizontalResolution = -1;
      this.mVerticalResolution = -1;
      int[] var3 = this.mMaxDimension;
      var3[0] = Integer.MAX_VALUE;
      var3[1] = Integer.MAX_VALUE;
      this.mMatchConstraintDefaultWidth = 0;
      this.mMatchConstraintDefaultHeight = 0;
      this.mMatchConstraintPercentWidth = 1.0F;
      this.mMatchConstraintPercentHeight = 1.0F;
      this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
      this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
      this.mMatchConstraintMinWidth = 0;
      this.mMatchConstraintMinHeight = 0;
      this.mResolvedDimensionRatioSide = -1;
      this.mResolvedDimensionRatio = 1.0F;
      ResolutionDimension var4 = this.mResolutionWidth;
      if (var4 != null) {
         var4.reset();
      }

      var4 = this.mResolutionHeight;
      if (var4 != null) {
         var4.reset();
      }

      this.mBelongingGroup = null;
      this.mOptimizerMeasurable = false;
      this.mOptimizerMeasured = false;
      this.mGroupsToSolver = false;
   }

   public void resetAllConstraints() {
      this.resetAnchors();
      this.setVerticalBiasPercent(DEFAULT_BIAS);
      this.setHorizontalBiasPercent(DEFAULT_BIAS);
      if (!(this instanceof ConstraintWidgetContainer)) {
         if (this.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.getWidth() == this.getWrapWidth()) {
               this.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
            } else if (this.getWidth() > this.getMinWidth()) {
               this.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            }
         }

         if (this.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.getHeight() == this.getWrapHeight()) {
               this.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
            } else if (this.getHeight() > this.getMinHeight()) {
               this.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            }
         }

      }
   }

   public void resetAnchor(ConstraintAnchor var1) {
      if (this.getParent() == null || !(this.getParent() instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer)this.getParent()).handlesInternalConstraints()) {
         ConstraintAnchor var5 = this.getAnchor(ConstraintAnchor.Type.LEFT);
         ConstraintAnchor var2 = this.getAnchor(ConstraintAnchor.Type.RIGHT);
         ConstraintAnchor var4 = this.getAnchor(ConstraintAnchor.Type.TOP);
         ConstraintAnchor var6 = this.getAnchor(ConstraintAnchor.Type.BOTTOM);
         ConstraintAnchor var8 = this.getAnchor(ConstraintAnchor.Type.CENTER);
         ConstraintAnchor var7 = this.getAnchor(ConstraintAnchor.Type.CENTER_X);
         ConstraintAnchor var3 = this.getAnchor(ConstraintAnchor.Type.CENTER_Y);
         if (var1 == var8) {
            if (var5.isConnected() && var2.isConnected() && var5.getTarget() == var2.getTarget()) {
               var5.reset();
               var2.reset();
            }

            if (var4.isConnected() && var6.isConnected() && var4.getTarget() == var6.getTarget()) {
               var4.reset();
               var6.reset();
            }

            this.mHorizontalBiasPercent = 0.5F;
            this.mVerticalBiasPercent = 0.5F;
         } else if (var1 == var7) {
            if (var5.isConnected() && var2.isConnected() && var5.getTarget().getOwner() == var2.getTarget().getOwner()) {
               var5.reset();
               var2.reset();
            }

            this.mHorizontalBiasPercent = 0.5F;
         } else if (var1 == var3) {
            if (var4.isConnected() && var6.isConnected() && var4.getTarget().getOwner() == var6.getTarget().getOwner()) {
               var4.reset();
               var6.reset();
            }

            this.mVerticalBiasPercent = 0.5F;
         } else if (var1 != var5 && var1 != var2) {
            if ((var1 == var4 || var1 == var6) && var4.isConnected() && var4.getTarget() == var6.getTarget()) {
               var8.reset();
            }
         } else if (var5.isConnected() && var5.getTarget() == var2.getTarget()) {
            var8.reset();
         }

         var1.reset();
      }
   }

   public void resetAnchors() {
      ConstraintWidget var3 = this.getParent();
      if (var3 == null || !(var3 instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer)this.getParent()).handlesInternalConstraints()) {
         int var1 = 0;

         for(int var2 = this.mAnchors.size(); var1 < var2; ++var1) {
            ((ConstraintAnchor)this.mAnchors.get(var1)).reset();
         }

      }
   }

   public void resetAnchors(int var1) {
      ConstraintWidget var4 = this.getParent();
      if (var4 == null || !(var4 instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer)this.getParent()).handlesInternalConstraints()) {
         int var2 = 0;

         for(int var3 = this.mAnchors.size(); var2 < var3; ++var2) {
            ConstraintAnchor var5 = (ConstraintAnchor)this.mAnchors.get(var2);
            if (var1 == var5.getConnectionCreator()) {
               if (var5.isVerticalAnchor()) {
                  this.setVerticalBiasPercent(DEFAULT_BIAS);
               } else {
                  this.setHorizontalBiasPercent(DEFAULT_BIAS);
               }

               var5.reset();
            }
         }

      }
   }

   public void resetResolutionNodes() {
      for(int var1 = 0; var1 < 6; ++var1) {
         this.mListAnchors[var1].getResolutionNode().reset();
      }

   }

   public void resetSolverVariables(Cache var1) {
      this.mLeft.resetSolverVariable(var1);
      this.mTop.resetSolverVariable(var1);
      this.mRight.resetSolverVariable(var1);
      this.mBottom.resetSolverVariable(var1);
      this.mBaseline.resetSolverVariable(var1);
      this.mCenter.resetSolverVariable(var1);
      this.mCenterX.resetSolverVariable(var1);
      this.mCenterY.resetSolverVariable(var1);
   }

   public void resolve() {
   }

   public void setBaselineDistance(int var1) {
      this.mBaselineDistance = var1;
   }

   public void setCompanionWidget(Object var1) {
      this.mCompanionWidget = var1;
   }

   public void setContainerItemSkip(int var1) {
      if (var1 >= 0) {
         this.mContainerItemSkip = var1;
      } else {
         this.mContainerItemSkip = 0;
      }

   }

   public void setDebugName(String var1) {
      this.mDebugName = var1;
   }

   public void setDebugSolverName(LinearSystem var1, String var2) {
      this.mDebugName = var2;
      SolverVariable var5 = var1.createObjectVariable(this.mLeft);
      SolverVariable var4 = var1.createObjectVariable(this.mTop);
      SolverVariable var6 = var1.createObjectVariable(this.mRight);
      SolverVariable var3 = var1.createObjectVariable(this.mBottom);
      var5.setName(var2 + ".left");
      var4.setName(var2 + ".top");
      var6.setName(var2 + ".right");
      var3.setName(var2 + ".bottom");
      if (this.mBaselineDistance > 0) {
         var1.createObjectVariable(this.mBaseline).setName(var2 + ".baseline");
      }

   }

   public void setDimension(int var1, int var2) {
      this.mWidth = var1;
      int var3 = this.mMinWidth;
      if (var1 < var3) {
         this.mWidth = var3;
      }

      this.mHeight = var2;
      var1 = this.mMinHeight;
      if (var2 < var1) {
         this.mHeight = var1;
      }

   }

   public void setDimensionRatio(float var1, int var2) {
      this.mDimensionRatio = var1;
      this.mDimensionRatioSide = var2;
   }

   public void setDimensionRatio(String var1) {
      if (var1 != null && var1.length() != 0) {
         int var6 = -1;
         int var8 = var1.length();
         int var9 = var1.indexOf(44);
         byte var7 = 0;
         byte var4 = (byte)var6;
         int var5 = var7;
         String var10;
         if (var9 > 0) {
            var4 = (byte)var6;
            var5 = var7;
            if (var9 < var8 - 1) {
               var10 = var1.substring(0, var9);
               if (var10.equalsIgnoreCase("W")) {
                  var4 = 0;
               } else {
                  var4 = (byte)var6;
                  if (var10.equalsIgnoreCase("H")) {
                     var4 = 1;
                  }
               }

               var5 = var9 + 1;
            }
         }

         float var2;
         label88: {
            var6 = var1.indexOf(58);
            boolean var10001;
            if (var6 >= 0 && var6 < var8 - 1) {
               var10 = var1.substring(var5, var6);
               var1 = var1.substring(var6 + 1);
               if (var10.length() > 0 && var1.length() > 0) {
                  label89: {
                     float var3;
                     try {
                        var3 = Float.parseFloat(var10);
                        var2 = Float.parseFloat(var1);
                     } catch (NumberFormatException var14) {
                        var10001 = false;
                        break label89;
                     }

                     if (var3 > 0.0F && var2 > 0.0F) {
                        if (var4 == 1) {
                           try {
                              var2 = Math.abs(var2 / var3);
                              break label88;
                           } catch (NumberFormatException var13) {
                              var10001 = false;
                           }
                        } else {
                           try {
                              var2 = Math.abs(var3 / var2);
                              break label88;
                           } catch (NumberFormatException var11) {
                              var10001 = false;
                           }
                        }
                     }
                  }
               }
            } else {
               var1 = var1.substring(var5);
               if (var1.length() > 0) {
                  try {
                     var2 = Float.parseFloat(var1);
                     break label88;
                  } catch (NumberFormatException var12) {
                     var10001 = false;
                  }
               }
            }

            var2 = 0.0F;
         }

         if (var2 > 0.0F) {
            this.mDimensionRatio = var2;
            this.mDimensionRatioSide = var4;
         }

      } else {
         this.mDimensionRatio = 0.0F;
      }
   }

   public void setDrawHeight(int var1) {
      this.mDrawHeight = var1;
   }

   public void setDrawOrigin(int var1, int var2) {
      var1 -= this.mOffsetX;
      this.mDrawX = var1;
      var2 -= this.mOffsetY;
      this.mDrawY = var2;
      this.mX = var1;
      this.mY = var2;
   }

   public void setDrawWidth(int var1) {
      this.mDrawWidth = var1;
   }

   public void setDrawX(int var1) {
      var1 -= this.mOffsetX;
      this.mDrawX = var1;
      this.mX = var1;
   }

   public void setDrawY(int var1) {
      var1 -= this.mOffsetY;
      this.mDrawY = var1;
      this.mY = var1;
   }

   public void setFrame(int var1, int var2, int var3) {
      if (var3 == 0) {
         this.setHorizontalDimension(var1, var2);
      } else if (var3 == 1) {
         this.setVerticalDimension(var1, var2);
      }

      this.mOptimizerMeasured = true;
   }

   public void setFrame(int var1, int var2, int var3, int var4) {
      int var5 = var3 - var1;
      var3 = var4 - var2;
      this.mX = var1;
      this.mY = var2;
      if (this.mVisibility == 8) {
         this.mWidth = 0;
         this.mHeight = 0;
      } else {
         var1 = var5;
         if (this.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.FIXED) {
            var2 = this.mWidth;
            var1 = var5;
            if (var5 < var2) {
               var1 = var2;
            }
         }

         var2 = var3;
         if (this.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.FIXED) {
            var4 = this.mHeight;
            var2 = var3;
            if (var3 < var4) {
               var2 = var4;
            }
         }

         this.mWidth = var1;
         this.mHeight = var2;
         var3 = this.mMinHeight;
         if (var2 < var3) {
            this.mHeight = var3;
         }

         var2 = this.mMinWidth;
         if (var1 < var2) {
            this.mWidth = var2;
         }

         this.mOptimizerMeasured = true;
      }
   }

   public void setGoneMargin(ConstraintAnchor.Type var1, int var2) {
      int var3 = null.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[var1.ordinal()];
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 3) {
               if (var3 == 4) {
                  this.mBottom.mGoneMargin = var2;
               }
            } else {
               this.mRight.mGoneMargin = var2;
            }
         } else {
            this.mTop.mGoneMargin = var2;
         }
      } else {
         this.mLeft.mGoneMargin = var2;
      }

   }

   public void setHeight(int var1) {
      this.mHeight = var1;
      int var2 = this.mMinHeight;
      if (var1 < var2) {
         this.mHeight = var2;
      }

   }

   public void setHeightWrapContent(boolean var1) {
      this.mIsHeightWrapContent = var1;
   }

   public void setHorizontalBiasPercent(float var1) {
      this.mHorizontalBiasPercent = var1;
   }

   public void setHorizontalChainStyle(int var1) {
      this.mHorizontalChainStyle = var1;
   }

   public void setHorizontalDimension(int var1, int var2) {
      this.mX = var1;
      var2 -= var1;
      this.mWidth = var2;
      var1 = this.mMinWidth;
      if (var2 < var1) {
         this.mWidth = var1;
      }

   }

   public void setHorizontalDimensionBehaviour(DimensionBehaviour var1) {
      this.mListDimensionBehaviors[0] = var1;
      if (var1 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
         this.setWidth(this.mWrapWidth);
      }

   }

   public void setHorizontalMatchStyle(int var1, int var2, int var3, float var4) {
      this.mMatchConstraintDefaultWidth = var1;
      this.mMatchConstraintMinWidth = var2;
      this.mMatchConstraintMaxWidth = var3;
      this.mMatchConstraintPercentWidth = var4;
      if (var4 < 1.0F && var1 == 0) {
         this.mMatchConstraintDefaultWidth = 2;
      }

   }

   public void setHorizontalWeight(float var1) {
      this.mWeight[0] = var1;
   }

   public void setLength(int var1, int var2) {
      if (var2 == 0) {
         this.setWidth(var1);
      } else if (var2 == 1) {
         this.setHeight(var1);
      }

   }

   public void setMaxHeight(int var1) {
      this.mMaxDimension[1] = var1;
   }

   public void setMaxWidth(int var1) {
      this.mMaxDimension[0] = var1;
   }

   public void setMinHeight(int var1) {
      if (var1 < 0) {
         this.mMinHeight = 0;
      } else {
         this.mMinHeight = var1;
      }

   }

   public void setMinWidth(int var1) {
      if (var1 < 0) {
         this.mMinWidth = 0;
      } else {
         this.mMinWidth = var1;
      }

   }

   public void setOffset(int var1, int var2) {
      this.mOffsetX = var1;
      this.mOffsetY = var2;
   }

   public void setOrigin(int var1, int var2) {
      this.mX = var1;
      this.mY = var2;
   }

   public void setParent(ConstraintWidget var1) {
      this.mParent = var1;
   }

   void setRelativePositioning(int var1, int var2) {
      if (var2 == 0) {
         this.mRelX = var1;
      } else if (var2 == 1) {
         this.mRelY = var1;
      }

   }

   public void setType(String var1) {
      this.mType = var1;
   }

   public void setVerticalBiasPercent(float var1) {
      this.mVerticalBiasPercent = var1;
   }

   public void setVerticalChainStyle(int var1) {
      this.mVerticalChainStyle = var1;
   }

   public void setVerticalDimension(int var1, int var2) {
      this.mY = var1;
      var1 = var2 - var1;
      this.mHeight = var1;
      var2 = this.mMinHeight;
      if (var1 < var2) {
         this.mHeight = var2;
      }

   }

   public void setVerticalDimensionBehaviour(DimensionBehaviour var1) {
      this.mListDimensionBehaviors[1] = var1;
      if (var1 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
         this.setHeight(this.mWrapHeight);
      }

   }

   public void setVerticalMatchStyle(int var1, int var2, int var3, float var4) {
      this.mMatchConstraintDefaultHeight = var1;
      this.mMatchConstraintMinHeight = var2;
      this.mMatchConstraintMaxHeight = var3;
      this.mMatchConstraintPercentHeight = var4;
      if (var4 < 1.0F && var1 == 0) {
         this.mMatchConstraintDefaultHeight = 2;
      }

   }

   public void setVerticalWeight(float var1) {
      this.mWeight[1] = var1;
   }

   public void setVisibility(int var1) {
      this.mVisibility = var1;
   }

   public void setWidth(int var1) {
      this.mWidth = var1;
      int var2 = this.mMinWidth;
      if (var1 < var2) {
         this.mWidth = var2;
      }

   }

   public void setWidthWrapContent(boolean var1) {
      this.mIsWidthWrapContent = var1;
   }

   public void setWrapHeight(int var1) {
      this.mWrapHeight = var1;
   }

   public void setWrapWidth(int var1) {
      this.mWrapWidth = var1;
   }

   public void setX(int var1) {
      this.mX = var1;
   }

   public void setY(int var1) {
      this.mY = var1;
   }

   public void setupDimensionRatio(boolean var1, boolean var2, boolean var3, boolean var4) {
      if (this.mResolvedDimensionRatioSide == -1) {
         if (var3 && !var4) {
            this.mResolvedDimensionRatioSide = 0;
         } else if (!var3 && var4) {
            this.mResolvedDimensionRatioSide = 1;
            if (this.mDimensionRatioSide == -1) {
               this.mResolvedDimensionRatio = 1.0F / this.mResolvedDimensionRatio;
            }
         }
      }

      if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
         this.mResolvedDimensionRatioSide = 1;
      } else if (this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
         this.mResolvedDimensionRatioSide = 0;
      }

      if (this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
         if (this.mTop.isConnected() && this.mBottom.isConnected()) {
            this.mResolvedDimensionRatioSide = 0;
         } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
            this.mResolvedDimensionRatio = 1.0F / this.mResolvedDimensionRatio;
            this.mResolvedDimensionRatioSide = 1;
         }
      }

      if (this.mResolvedDimensionRatioSide == -1) {
         if (var1 && !var2) {
            this.mResolvedDimensionRatioSide = 0;
         } else if (!var1 && var2) {
            this.mResolvedDimensionRatio = 1.0F / this.mResolvedDimensionRatio;
            this.mResolvedDimensionRatioSide = 1;
         }
      }

      if (this.mResolvedDimensionRatioSide == -1) {
         int var5 = this.mMatchConstraintMinWidth;
         if (var5 > 0 && this.mMatchConstraintMinHeight == 0) {
            this.mResolvedDimensionRatioSide = 0;
         } else if (var5 == 0 && this.mMatchConstraintMinHeight > 0) {
            this.mResolvedDimensionRatio = 1.0F / this.mResolvedDimensionRatio;
            this.mResolvedDimensionRatioSide = 1;
         }
      }

      if (this.mResolvedDimensionRatioSide == -1 && var1 && var2) {
         this.mResolvedDimensionRatio = 1.0F / this.mResolvedDimensionRatio;
         this.mResolvedDimensionRatioSide = 1;
      }

   }

   public String toString() {
      StringBuilder var3 = new StringBuilder();
      String var1 = this.mType;
      String var2 = "";
      if (var1 != null) {
         var1 = "type: " + this.mType + " ";
      } else {
         var1 = "";
      }

      var3 = var3.append(var1);
      var1 = var2;
      if (this.mDebugName != null) {
         var1 = "id: " + this.mDebugName + " ";
      }

      return var3.append(var1).append("(").append(this.mX).append(", ").append(this.mY).append(") - (").append(this.mWidth).append(" x ").append(this.mHeight).append(") wrap: (").append(this.mWrapWidth).append(" x ").append(this.mWrapHeight).append(")").toString();
   }

   public void updateDrawPosition() {
      int var2 = this.mX;
      int var4 = this.mY;
      int var3 = this.mWidth;
      int var1 = this.mHeight;
      this.mDrawX = var2;
      this.mDrawY = var4;
      this.mDrawWidth = var3 + var2 - var2;
      this.mDrawHeight = var1 + var4 - var4;
   }

   public void updateFromSolver(LinearSystem var1) {
      int var2;
      int var3;
      int var5;
      int var6;
      label27: {
         var2 = var1.getObjectVariableValue(this.mLeft);
         var5 = var1.getObjectVariableValue(this.mTop);
         var6 = var1.getObjectVariableValue(this.mRight);
         int var4 = var1.getObjectVariableValue(this.mBottom);
         if (var6 - var2 >= 0 && var4 - var5 >= 0 && var2 != Integer.MIN_VALUE && var2 != Integer.MAX_VALUE && var5 != Integer.MIN_VALUE && var5 != Integer.MAX_VALUE && var6 != Integer.MIN_VALUE && var6 != Integer.MAX_VALUE && var4 != Integer.MIN_VALUE) {
            var3 = var4;
            if (var4 != Integer.MAX_VALUE) {
               break label27;
            }
         }

         var3 = 0;
         byte var7 = 0;
         var6 = var7;
         var5 = var7;
         var2 = var7;
      }

      this.setFrame(var2, var5, var6, var3);
   }

   public void updateResolutionNodes() {
      for(int var1 = 0; var1 < 6; ++var1) {
         this.mListAnchors[var1].getResolutionNode().update();
      }

   }

   public static enum ContentAlignment {
      private static final ContentAlignment[] $VALUES;
      BEGIN,
      BOTTOM,
      END,
      LEFT,
      MIDDLE,
      RIGHT,
      TOP,
      VERTICAL_MIDDLE;

      static {
         ContentAlignment var4 = new ContentAlignment("BEGIN", 0);
         BEGIN = var4;
         ContentAlignment var6 = new ContentAlignment("MIDDLE", 1);
         MIDDLE = var6;
         ContentAlignment var2 = new ContentAlignment("END", 2);
         END = var2;
         ContentAlignment var0 = new ContentAlignment("TOP", 3);
         TOP = var0;
         ContentAlignment var3 = new ContentAlignment("VERTICAL_MIDDLE", 4);
         VERTICAL_MIDDLE = var3;
         ContentAlignment var7 = new ContentAlignment("BOTTOM", 5);
         BOTTOM = var7;
         ContentAlignment var5 = new ContentAlignment("LEFT", 6);
         LEFT = var5;
         ContentAlignment var1 = new ContentAlignment("RIGHT", 7);
         RIGHT = var1;
         $VALUES = new ContentAlignment[]{var4, var6, var2, var0, var3, var7, var5, var1};
      }
   }

   public static enum DimensionBehaviour {
      private static final DimensionBehaviour[] $VALUES;
      FIXED,
      MATCH_CONSTRAINT,
      MATCH_PARENT,
      WRAP_CONTENT;

      static {
         DimensionBehaviour var1 = new DimensionBehaviour("FIXED", 0);
         FIXED = var1;
         DimensionBehaviour var2 = new DimensionBehaviour("WRAP_CONTENT", 1);
         WRAP_CONTENT = var2;
         DimensionBehaviour var0 = new DimensionBehaviour("MATCH_CONSTRAINT", 2);
         MATCH_CONSTRAINT = var0;
         DimensionBehaviour var3 = new DimensionBehaviour("MATCH_PARENT", 3);
         MATCH_PARENT = var3;
         $VALUES = new DimensionBehaviour[]{var1, var2, var0, var3};
      }
   }
}
