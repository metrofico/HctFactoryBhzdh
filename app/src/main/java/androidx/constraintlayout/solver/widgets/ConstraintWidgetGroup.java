package androidx.constraintlayout.solver.widgets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConstraintWidgetGroup {
   public List mConstrainedGroup;
   public final int[] mGroupDimensions = new int[]{-1, -1};
   int mGroupHeight = -1;
   int mGroupWidth = -1;
   public boolean mSkipSolver = false;
   List mStartHorizontalWidgets = new ArrayList();
   List mStartVerticalWidgets = new ArrayList();
   List mUnresolvedWidgets = new ArrayList();
   HashSet mWidgetsToSetHorizontal = new HashSet();
   HashSet mWidgetsToSetVertical = new HashSet();
   List mWidgetsToSolve = new ArrayList();

   ConstraintWidgetGroup(List var1) {
      this.mConstrainedGroup = var1;
   }

   ConstraintWidgetGroup(List var1, boolean var2) {
      this.mConstrainedGroup = var1;
      this.mSkipSolver = var2;
   }

   private void getWidgetsToSolveTraversal(ArrayList var1, ConstraintWidget var2) {
      if (!var2.mGroupsToSolver) {
         var1.add(var2);
         var2.mGroupsToSolver = true;
         if (!var2.isFullyResolved()) {
            boolean var6 = var2 instanceof Helper;
            byte var4 = 0;
            int var3;
            int var5;
            if (var6) {
               Helper var7 = (Helper)var2;
               var5 = var7.mWidgetsCount;

               for(var3 = 0; var3 < var5; ++var3) {
                  this.getWidgetsToSolveTraversal(var1, var7.mWidgets[var3]);
               }
            }

            var5 = var2.mListAnchors.length;

            for(var3 = var4; var3 < var5; ++var3) {
               ConstraintAnchor var8 = var2.mListAnchors[var3].mTarget;
               if (var8 != null) {
                  ConstraintWidget var9 = var8.mOwner;
                  if (var8 != null && var9 != var2.getParent()) {
                     this.getWidgetsToSolveTraversal(var1, var9);
                  }
               }
            }

         }
      }
   }

   private void updateResolvedDimension(ConstraintWidget var1) {
      if (var1.mOptimizerMeasurable) {
         if (var1.isFullyResolved()) {
            return;
         }

         ConstraintAnchor var5 = var1.mRight.mTarget;
         boolean var4 = false;
         boolean var3;
         if (var5 != null) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (var3) {
            var5 = var1.mRight.mTarget;
         } else {
            var5 = var1.mLeft.mTarget;
         }

         int var2;
         label73: {
            if (var5 != null) {
               if (!var5.mOwner.mOptimizerMeasured) {
                  this.updateResolvedDimension(var5.mOwner);
               }

               if (var5.mType == ConstraintAnchor.Type.RIGHT) {
                  var2 = var5.mOwner.mX + var5.mOwner.getWidth();
                  break label73;
               }

               if (var5.mType == ConstraintAnchor.Type.LEFT) {
                  var2 = var5.mOwner.mX;
                  break label73;
               }
            }

            var2 = 0;
         }

         if (var3) {
            var2 -= var1.mRight.getMargin();
         } else {
            var2 += var1.mLeft.getMargin() + var1.getWidth();
         }

         var1.setHorizontalDimension(var2 - var1.getWidth(), var2);
         if (var1.mBaseline.mTarget != null) {
            var5 = var1.mBaseline.mTarget;
            if (!var5.mOwner.mOptimizerMeasured) {
               this.updateResolvedDimension(var5.mOwner);
            }

            var2 = var5.mOwner.mY + var5.mOwner.mBaselineDistance - var1.mBaselineDistance;
            var1.setVerticalDimension(var2, var1.mHeight + var2);
            var1.mOptimizerMeasured = true;
            return;
         }

         if (var1.mBottom.mTarget != null) {
            var4 = true;
         }

         if (var4) {
            var5 = var1.mBottom.mTarget;
         } else {
            var5 = var1.mTop.mTarget;
         }

         int var6 = var2;
         if (var5 != null) {
            if (!var5.mOwner.mOptimizerMeasured) {
               this.updateResolvedDimension(var5.mOwner);
            }

            if (var5.mType == ConstraintAnchor.Type.BOTTOM) {
               var6 = var5.mOwner.mY + var5.mOwner.getHeight();
            } else {
               var6 = var2;
               if (var5.mType == ConstraintAnchor.Type.TOP) {
                  var6 = var5.mOwner.mY;
               }
            }
         }

         if (var4) {
            var2 = var6 - var1.mBottom.getMargin();
         } else {
            var2 = var6 + var1.mTop.getMargin() + var1.getHeight();
         }

         var1.setVerticalDimension(var2 - var1.getHeight(), var2);
         var1.mOptimizerMeasured = true;
      }

   }

   void addWidgetsToSet(ConstraintWidget var1, int var2) {
      if (var2 == 0) {
         this.mWidgetsToSetHorizontal.add(var1);
      } else if (var2 == 1) {
         this.mWidgetsToSetVertical.add(var1);
      }

   }

   public List getStartWidgets(int var1) {
      if (var1 == 0) {
         return this.mStartHorizontalWidgets;
      } else {
         return var1 == 1 ? this.mStartVerticalWidgets : null;
      }
   }

   Set getWidgetsToSet(int var1) {
      if (var1 == 0) {
         return this.mWidgetsToSetHorizontal;
      } else {
         return var1 == 1 ? this.mWidgetsToSetVertical : null;
      }
   }

   List getWidgetsToSolve() {
      if (!this.mWidgetsToSolve.isEmpty()) {
         return this.mWidgetsToSolve;
      } else {
         int var2 = this.mConstrainedGroup.size();

         for(int var1 = 0; var1 < var2; ++var1) {
            ConstraintWidget var3 = (ConstraintWidget)this.mConstrainedGroup.get(var1);
            if (!var3.mOptimizerMeasurable) {
               this.getWidgetsToSolveTraversal((ArrayList)this.mWidgetsToSolve, var3);
            }
         }

         this.mUnresolvedWidgets.clear();
         this.mUnresolvedWidgets.addAll(this.mConstrainedGroup);
         this.mUnresolvedWidgets.removeAll(this.mWidgetsToSolve);
         return this.mWidgetsToSolve;
      }
   }

   void updateUnresolvedWidgets() {
      int var2 = this.mUnresolvedWidgets.size();

      for(int var1 = 0; var1 < var2; ++var1) {
         this.updateResolvedDimension((ConstraintWidget)this.mUnresolvedWidgets.get(var1));
      }

   }
}
