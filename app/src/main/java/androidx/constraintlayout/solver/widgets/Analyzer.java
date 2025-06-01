package androidx.constraintlayout.solver.widgets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Analyzer {
   private Analyzer() {
   }

   public static void determineGroups(ConstraintWidgetContainer var0) {
      if ((var0.getOptimizationLevel() & 32) != 32) {
         singleGroup(var0);
      } else {
         var0.mSkipSolver = true;
         var0.mGroupsWrapOptimized = false;
         var0.mHorizontalWrapOptimized = false;
         var0.mVerticalWrapOptimized = false;
         ArrayList var8 = var0.mChildren;
         List var6 = var0.mWidgetGroups;
         boolean var1;
         if (var0.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
            var1 = true;
         } else {
            var1 = false;
         }

         boolean var2;
         if (var0.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
            var2 = true;
         } else {
            var2 = false;
         }

         boolean var5;
         if (!var1 && !var2) {
            var5 = false;
         } else {
            var5 = true;
         }

         var6.clear();
         Iterator var9 = var8.iterator();

         ConstraintWidget var7;
         while(var9.hasNext()) {
            var7 = (ConstraintWidget)var9.next();
            var7.mBelongingGroup = null;
            var7.mGroupsToSolver = false;
            var7.resetResolutionNodes();
         }

         Iterator var11 = var8.iterator();

         while(var11.hasNext()) {
            var7 = (ConstraintWidget)var11.next();
            if (var7.mBelongingGroup == null && !determineGroups(var7, var6, var5)) {
               singleGroup(var0);
               var0.mSkipSolver = false;
               return;
            }
         }

         Iterator var10 = var6.iterator();
         int var4 = 0;

         int var3;
         ConstraintWidgetGroup var12;
         for(var3 = 0; var10.hasNext(); var3 = Math.max(var3, getMaxDimension(var12, 1))) {
            var12 = (ConstraintWidgetGroup)var10.next();
            var4 = Math.max(var4, getMaxDimension(var12, 0));
         }

         if (var1) {
            var0.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            var0.setWidth(var4);
            var0.mGroupsWrapOptimized = true;
            var0.mHorizontalWrapOptimized = true;
            var0.mWrapFixedWidth = var4;
         }

         if (var2) {
            var0.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            var0.setHeight(var3);
            var0.mGroupsWrapOptimized = true;
            var0.mVerticalWrapOptimized = true;
            var0.mWrapFixedHeight = var3;
         }

         setPosition(var6, 0, var0.getWidth());
         setPosition(var6, 1, var0.getHeight());
      }
   }

   private static boolean determineGroups(ConstraintWidget var0, List var1, boolean var2) {
      ConstraintWidgetGroup var3 = new ConstraintWidgetGroup(new ArrayList(), true);
      var1.add(var3);
      return traverse(var0, var3, var1, var2);
   }

   private static int getMaxDimension(ConstraintWidgetGroup var0, int var1) {
      int var5 = var1 * 2;
      List var10 = var0.getStartWidgets(var1);
      int var4 = var10.size();
      int var3 = 0;

      int var2;
      for(var2 = 0; var3 < var4; ++var3) {
         ConstraintWidget var8 = (ConstraintWidget)var10.get(var3);
         ConstraintAnchor[] var9 = var8.mListAnchors;
         int var6 = var5 + 1;
         boolean var7;
         if (var9[var6].mTarget == null || var8.mListAnchors[var5].mTarget != null && var8.mListAnchors[var6].mTarget != null) {
            var7 = true;
         } else {
            var7 = false;
         }

         var2 = Math.max(var2, getMaxDimensionTraversal(var8, var1, var7, 0));
      }

      var0.mGroupDimensions[var1] = var2;
      return var2;
   }

   private static int getMaxDimensionTraversal(ConstraintWidget var0, int var1, boolean var2, int var3) {
      boolean var15 = var0.mOptimizerMeasurable;
      int var11 = 0;
      if (!var15) {
         return 0;
      } else {
         boolean var6;
         if (var0.mBaseline.mTarget != null && var1 == 1) {
            var6 = true;
         } else {
            var6 = false;
         }

         int var4;
         int var5;
         int var8;
         int var9;
         if (var2) {
            var8 = var0.getBaselineDistance();
            var9 = var0.getHeight() - var0.getBaselineDistance();
            var5 = var1 * 2;
            var4 = var5 + 1;
         } else {
            var8 = var0.getHeight() - var0.getBaselineDistance();
            var9 = var0.getBaselineDistance();
            var4 = var1 * 2;
            var5 = var4 + 1;
         }

         int var7;
         int var10;
         if (var0.mListAnchors[var4].mTarget != null && var0.mListAnchors[var5].mTarget == null) {
            var10 = -1;
            var7 = var5;
            var5 = var4;
            var4 = var7;
            var7 = var10;
         } else {
            var7 = 1;
         }

         if (var6) {
            var3 -= var8;
         }

         int var13 = var0.mListAnchors[var5].getMargin() * var7 + getParentBiasOffset(var0, var1);
         var10 = var3 + var13;
         if (var1 == 0) {
            var3 = var0.getWidth();
         } else {
            var3 = var0.getHeight();
         }

         int var14 = var3 * var7;
         Iterator var16 = var0.mListAnchors[var5].getResolutionNode().dependents.iterator();

         for(var3 = var11; var16.hasNext(); var3 = Math.max(var3, getMaxDimensionTraversal(((ResolutionAnchor)((ResolutionNode)var16.next())).myAnchor.mOwner, var1, var2, var10))) {
         }

         var16 = var0.mListAnchors[var4].getResolutionNode().dependents.iterator();

         for(var11 = 0; var16.hasNext(); var11 = Math.max(var11, getMaxDimensionTraversal(((ResolutionAnchor)((ResolutionNode)var16.next())).myAnchor.mOwner, var1, var2, var14 + var10))) {
         }

         int var12;
         if (var6) {
            var12 = var3 - var8;
            var11 += var9;
         } else {
            if (var1 == 0) {
               var12 = var0.getWidth();
            } else {
               var12 = var0.getHeight();
            }

            var11 += var12 * var7;
            var12 = var3;
         }

         if (var1 == 1) {
            var16 = var0.mBaseline.getResolutionNode().dependents.iterator();
            var3 = 0;

            while(var16.hasNext()) {
               ResolutionAnchor var17 = (ResolutionAnchor)((ResolutionNode)var16.next());
               if (var7 == 1) {
                  var3 = Math.max(var3, getMaxDimensionTraversal(var17.myAnchor.mOwner, var1, var2, var8 + var10));
               } else {
                  var3 = Math.max(var3, getMaxDimensionTraversal(var17.myAnchor.mOwner, var1, var2, var9 * var7 + var10));
               }
            }

            if (var0.mBaseline.getResolutionNode().dependents.size() > 0 && !var6) {
               if (var7 == 1) {
                  var3 += var8;
               } else {
                  var3 -= var9;
               }
            }
         } else {
            var3 = 0;
         }

         var9 = Math.max(var12, Math.max(var11, var3));
         int var18 = var14 + var10;
         var8 = var10;
         var3 = var18;
         if (var7 == -1) {
            var3 = var10;
            var8 = var18;
         }

         if (var2) {
            Optimizer.setOptimizedWidget(var0, var1, var8);
            var0.setFrame(var8, var3, var1);
         } else {
            var0.mBelongingGroup.addWidgetsToSet(var0, var1);
            var0.setRelativePositioning(var8, var1);
         }

         if (var0.getDimensionBehaviour(var1) == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && var0.mDimensionRatio != 0.0F) {
            var0.mBelongingGroup.addWidgetsToSet(var0, var1);
         }

         if (var0.mListAnchors[var5].mTarget != null && var0.mListAnchors[var4].mTarget != null) {
            ConstraintWidget var19 = var0.getParent();
            if (var0.mListAnchors[var5].mTarget.mOwner == var19 && var0.mListAnchors[var4].mTarget.mOwner == var19) {
               var0.mBelongingGroup.addWidgetsToSet(var0, var1);
            }
         }

         return var13 + var9;
      }
   }

   private static int getParentBiasOffset(ConstraintWidget var0, int var1) {
      int var3 = var1 * 2;
      ConstraintAnchor var5 = var0.mListAnchors[var3];
      ConstraintAnchor var4 = var0.mListAnchors[var3 + 1];
      if (var5.mTarget != null && var5.mTarget.mOwner == var0.mParent && var4.mTarget != null && var4.mTarget.mOwner == var0.mParent) {
         var3 = var0.mParent.getLength(var1);
         float var2;
         if (var1 == 0) {
            var2 = var0.mHorizontalBiasPercent;
         } else {
            var2 = var0.mVerticalBiasPercent;
         }

         var1 = var0.getLength(var1);
         return (int)((float)(var3 - var5.getMargin() - var4.getMargin() - var1) * var2);
      } else {
         return 0;
      }
   }

   private static void invalidate(ConstraintWidgetContainer var0, ConstraintWidget var1, ConstraintWidgetGroup var2) {
      var2.mSkipSolver = false;
      var0.mSkipSolver = false;
      var1.mOptimizerMeasurable = false;
   }

   private static int resolveDimensionRatio(ConstraintWidget var0) {
      float var1;
      int var2;
      if (var0.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
         if (var0.mDimensionRatioSide == 0) {
            var1 = (float)var0.getHeight() * var0.mDimensionRatio;
         } else {
            var1 = (float)var0.getHeight() / var0.mDimensionRatio;
         }

         var2 = (int)var1;
         var0.setWidth(var2);
      } else if (var0.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
         if (var0.mDimensionRatioSide == 1) {
            var1 = (float)var0.getWidth() * var0.mDimensionRatio;
         } else {
            var1 = (float)var0.getWidth() / var0.mDimensionRatio;
         }

         var2 = (int)var1;
         var0.setHeight(var2);
      } else {
         var2 = -1;
      }

      return var2;
   }

   private static void setConnection(ConstraintAnchor var0) {
      ResolutionAnchor var1 = var0.getResolutionNode();
      if (var0.mTarget != null && var0.mTarget.mTarget != var0) {
         var0.mTarget.getResolutionNode().addDependent(var1);
      }

   }

   public static void setPosition(List var0, int var1, int var2) {
      int var4 = var0.size();

      for(int var3 = 0; var3 < var4; ++var3) {
         Iterator var6 = ((ConstraintWidgetGroup)var0.get(var3)).getWidgetsToSet(var1).iterator();

         while(var6.hasNext()) {
            ConstraintWidget var5 = (ConstraintWidget)var6.next();
            if (var5.mOptimizerMeasurable) {
               updateSizeDependentWidgets(var5, var1, var2);
            }
         }
      }

   }

   private static void singleGroup(ConstraintWidgetContainer var0) {
      var0.mWidgetGroups.clear();
      var0.mWidgetGroups.add(0, new ConstraintWidgetGroup(var0.mChildren));
   }

   private static boolean traverse(ConstraintWidget var0, ConstraintWidgetGroup var1, List var2, boolean var3) {
      if (var0 == null) {
         return true;
      } else {
         var0.mOptimizerMeasured = false;
         ConstraintWidgetContainer var6 = (ConstraintWidgetContainer)var0.getParent();
         if (var0.mBelongingGroup == null) {
            var0.mOptimizerMeasurable = true;
            var1.mConstrainedGroup.add(var0);
            var0.mBelongingGroup = var1;
            if (var0.mLeft.mTarget == null && var0.mRight.mTarget == null && var0.mTop.mTarget == null && var0.mBottom.mTarget == null && var0.mBaseline.mTarget == null && var0.mCenter.mTarget == null) {
               invalidate(var6, var0, var1);
               if (var3) {
                  return false;
               }
            }

            ConstraintWidget.DimensionBehaviour var7;
            if (var0.mTop.mTarget != null && var0.mBottom.mTarget != null) {
               var6.getVerticalDimensionBehaviour();
               var7 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
               if (var3) {
                  invalidate(var6, var0, var1);
                  return false;
               }

               if (var0.mTop.mTarget.mOwner != var0.getParent() || var0.mBottom.mTarget.mOwner != var0.getParent()) {
                  invalidate(var6, var0, var1);
               }
            }

            if (var0.mLeft.mTarget != null && var0.mRight.mTarget != null) {
               var6.getHorizontalDimensionBehaviour();
               var7 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
               if (var3) {
                  invalidate(var6, var0, var1);
                  return false;
               }

               if (var0.mLeft.mTarget.mOwner != var0.getParent() || var0.mRight.mTarget.mOwner != var0.getParent()) {
                  invalidate(var6, var0, var1);
               }
            }

            boolean var4;
            if (var0.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
               var4 = true;
            } else {
               var4 = false;
            }

            boolean var5;
            if (var0.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
               var5 = true;
            } else {
               var5 = false;
            }

            if (var4 ^ var5 && var0.mDimensionRatio != 0.0F) {
               resolveDimensionRatio(var0);
            } else if (var0.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || var0.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
               invalidate(var6, var0, var1);
               if (var3) {
                  return false;
               }
            }

            if ((var0.mLeft.mTarget == null && var0.mRight.mTarget == null || var0.mLeft.mTarget != null && var0.mLeft.mTarget.mOwner == var0.mParent && var0.mRight.mTarget == null || var0.mRight.mTarget != null && var0.mRight.mTarget.mOwner == var0.mParent && var0.mLeft.mTarget == null || var0.mLeft.mTarget != null && var0.mLeft.mTarget.mOwner == var0.mParent && var0.mRight.mTarget != null && var0.mRight.mTarget.mOwner == var0.mParent) && var0.mCenter.mTarget == null && !(var0 instanceof Guideline) && !(var0 instanceof Helper)) {
               var1.mStartHorizontalWidgets.add(var0);
            }

            if ((var0.mTop.mTarget == null && var0.mBottom.mTarget == null || var0.mTop.mTarget != null && var0.mTop.mTarget.mOwner == var0.mParent && var0.mBottom.mTarget == null || var0.mBottom.mTarget != null && var0.mBottom.mTarget.mOwner == var0.mParent && var0.mTop.mTarget == null || var0.mTop.mTarget != null && var0.mTop.mTarget.mOwner == var0.mParent && var0.mBottom.mTarget != null && var0.mBottom.mTarget.mOwner == var0.mParent) && var0.mCenter.mTarget == null && var0.mBaseline.mTarget == null && !(var0 instanceof Guideline) && !(var0 instanceof Helper)) {
               var1.mStartVerticalWidgets.add(var0);
            }

            int var9;
            if (var0 instanceof Helper) {
               invalidate(var6, var0, var1);
               if (var3) {
                  return false;
               }

               Helper var11 = (Helper)var0;

               for(var9 = 0; var9 < var11.mWidgetsCount; ++var9) {
                  if (!traverse(var11.mWidgets[var9], var1, var2, var3)) {
                     return false;
                  }
               }
            }

            int var10 = var0.mListAnchors.length;

            for(var9 = 0; var9 < var10; ++var9) {
               ConstraintAnchor var12 = var0.mListAnchors[var9];
               if (var12.mTarget != null && var12.mTarget.mOwner != var0.getParent()) {
                  if (var12.mType == ConstraintAnchor.Type.CENTER) {
                     invalidate(var6, var0, var1);
                     if (var3) {
                        return false;
                     }
                  } else {
                     setConnection(var12);
                  }

                  if (!traverse(var12.mTarget.mOwner, var1, var2, var3)) {
                     return false;
                  }
               }
            }

            return true;
         } else {
            if (var0.mBelongingGroup != var1) {
               var1.mConstrainedGroup.addAll(var0.mBelongingGroup.mConstrainedGroup);
               var1.mStartHorizontalWidgets.addAll(var0.mBelongingGroup.mStartHorizontalWidgets);
               var1.mStartVerticalWidgets.addAll(var0.mBelongingGroup.mStartVerticalWidgets);
               if (!var0.mBelongingGroup.mSkipSolver) {
                  var1.mSkipSolver = false;
               }

               var2.remove(var0.mBelongingGroup);

               for(Iterator var8 = var0.mBelongingGroup.mConstrainedGroup.iterator(); var8.hasNext(); ((ConstraintWidget)var8.next()).mBelongingGroup = var1) {
               }
            }

            return true;
         }
      }
   }

   private static void updateSizeDependentWidgets(ConstraintWidget var0, int var1, int var2) {
      int var4 = var1 * 2;
      ConstraintAnchor var6 = var0.mListAnchors[var4];
      ConstraintAnchor var5 = var0.mListAnchors[var4 + 1];
      boolean var3;
      if (var6.mTarget != null && var5.mTarget != null) {
         var3 = true;
      } else {
         var3 = false;
      }

      if (var3) {
         Optimizer.setOptimizedWidget(var0, var1, getParentBiasOffset(var0, var1) + var6.getMargin());
      } else {
         int var7;
         if (var0.mDimensionRatio != 0.0F && var0.getDimensionBehaviour(var1) == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            var2 = resolveDimensionRatio(var0);
            var7 = (int)var0.mListAnchors[var4].getResolutionNode().resolvedOffset;
            var5.getResolutionNode().resolvedTarget = var6.getResolutionNode();
            var5.getResolutionNode().resolvedOffset = (float)var2;
            var5.getResolutionNode().state = 1;
            var0.setFrame(var7, var7 + var2, var1);
         } else {
            var2 -= var0.getRelativePositioning(var1);
            var7 = var2 - var0.getLength(var1);
            var0.setFrame(var7, var2, var1);
            Optimizer.setOptimizedWidget(var0, var1, var7);
         }
      }
   }
}
