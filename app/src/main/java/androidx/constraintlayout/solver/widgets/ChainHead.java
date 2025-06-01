package androidx.constraintlayout.solver.widgets;

import java.util.ArrayList;

public class ChainHead {
   private boolean mDefined;
   protected ConstraintWidget mFirst;
   protected ConstraintWidget mFirstMatchConstraintWidget;
   protected ConstraintWidget mFirstVisibleWidget;
   protected boolean mHasComplexMatchWeights;
   protected boolean mHasDefinedWeights;
   protected boolean mHasUndefinedWeights;
   protected ConstraintWidget mHead;
   private boolean mIsRtl;
   protected ConstraintWidget mLast;
   protected ConstraintWidget mLastMatchConstraintWidget;
   protected ConstraintWidget mLastVisibleWidget;
   private int mOrientation;
   protected float mTotalWeight = 0.0F;
   protected ArrayList mWeightedMatchConstraintsWidgets;
   protected int mWidgetsCount;
   protected int mWidgetsMatchCount;

   public ChainHead(ConstraintWidget var1, int var2, boolean var3) {
      this.mFirst = var1;
      this.mOrientation = var2;
      this.mIsRtl = var3;
   }

   private void defineChainProperties() {
      int var3 = this.mOrientation * 2;
      ConstraintWidget var7 = this.mFirst;
      boolean var6 = false;
      ConstraintWidget var8 = var7;

      ConstraintWidget var9;
      for(boolean var2 = false; !var2; var8 = var9) {
         ++this.mWidgetsCount;
         ConstraintWidget[] var10 = var7.mNextChainWidget;
         int var4 = this.mOrientation;
         var9 = null;
         var10[var4] = null;
         var7.mListNextMatchConstraintsWidget[this.mOrientation] = null;
         ConstraintWidget var11;
         if (var7.getVisibility() != 8) {
            if (this.mFirstVisibleWidget == null) {
               this.mFirstVisibleWidget = var7;
            }

            this.mLastVisibleWidget = var7;
            if (var7.mListDimensionBehaviors[this.mOrientation] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && (var7.mResolvedMatchConstraintDefault[this.mOrientation] == 0 || var7.mResolvedMatchConstraintDefault[this.mOrientation] == 3 || var7.mResolvedMatchConstraintDefault[this.mOrientation] == 2)) {
               ++this.mWidgetsMatchCount;
               float var1 = var7.mWeight[this.mOrientation];
               if (var1 > 0.0F) {
                  this.mTotalWeight += var7.mWeight[this.mOrientation];
               }

               if (isMatchConstraintEqualityCandidate(var7, this.mOrientation)) {
                  if (var1 < 0.0F) {
                     this.mHasUndefinedWeights = true;
                  } else {
                     this.mHasDefinedWeights = true;
                  }

                  if (this.mWeightedMatchConstraintsWidgets == null) {
                     this.mWeightedMatchConstraintsWidgets = new ArrayList();
                  }

                  this.mWeightedMatchConstraintsWidgets.add(var7);
               }

               if (this.mFirstMatchConstraintWidget == null) {
                  this.mFirstMatchConstraintWidget = var7;
               }

               var11 = this.mLastMatchConstraintWidget;
               if (var11 != null) {
                  var11.mListNextMatchConstraintsWidget[this.mOrientation] = var7;
               }

               this.mLastMatchConstraintWidget = var7;
            }
         }

         if (var8 != var7) {
            var8.mNextChainWidget[this.mOrientation] = var7;
         }

         ConstraintAnchor var12 = var7.mListAnchors[var3 + 1].mTarget;
         var8 = var9;
         if (var12 != null) {
            var11 = var12.mOwner;
            var8 = var9;
            if (var11.mListAnchors[var3].mTarget != null) {
               if (var11.mListAnchors[var3].mTarget.mOwner != var7) {
                  var8 = var9;
               } else {
                  var8 = var11;
               }
            }
         }

         if (var8 == null) {
            var8 = var7;
            var2 = true;
         }

         var9 = var7;
         var7 = var8;
      }

      this.mLast = var7;
      if (this.mOrientation == 0 && this.mIsRtl) {
         this.mHead = var7;
      } else {
         this.mHead = this.mFirst;
      }

      boolean var5 = var6;
      if (this.mHasDefinedWeights) {
         var5 = var6;
         if (this.mHasUndefinedWeights) {
            var5 = true;
         }
      }

      this.mHasComplexMatchWeights = var5;
   }

   private static boolean isMatchConstraintEqualityCandidate(ConstraintWidget var0, int var1) {
      boolean var2;
      if (var0.getVisibility() == 8 || var0.mListDimensionBehaviors[var1] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || var0.mResolvedMatchConstraintDefault[var1] != 0 && var0.mResolvedMatchConstraintDefault[var1] != 3) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   public void define() {
      if (!this.mDefined) {
         this.defineChainProperties();
      }

      this.mDefined = true;
   }

   public ConstraintWidget getFirst() {
      return this.mFirst;
   }

   public ConstraintWidget getFirstMatchConstraintWidget() {
      return this.mFirstMatchConstraintWidget;
   }

   public ConstraintWidget getFirstVisibleWidget() {
      return this.mFirstVisibleWidget;
   }

   public ConstraintWidget getHead() {
      return this.mHead;
   }

   public ConstraintWidget getLast() {
      return this.mLast;
   }

   public ConstraintWidget getLastMatchConstraintWidget() {
      return this.mLastMatchConstraintWidget;
   }

   public ConstraintWidget getLastVisibleWidget() {
      return this.mLastVisibleWidget;
   }

   public float getTotalWeight() {
      return this.mTotalWeight;
   }
}
