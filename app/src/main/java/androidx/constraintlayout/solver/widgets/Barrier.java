package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.SolverVariable;
import java.util.ArrayList;

public class Barrier extends Helper {
   public static final int BOTTOM = 3;
   public static final int LEFT = 0;
   public static final int RIGHT = 1;
   public static final int TOP = 2;
   private boolean mAllowsGoneWidget = true;
   private int mBarrierType = 0;
   private ArrayList mNodes = new ArrayList(4);

   public void addToSolver(LinearSystem var1) {
      this.mListAnchors[0] = this.mLeft;
      this.mListAnchors[2] = this.mTop;
      this.mListAnchors[1] = this.mRight;
      this.mListAnchors[3] = this.mBottom;

      int var2;
      for(var2 = 0; var2 < this.mListAnchors.length; ++var2) {
         this.mListAnchors[var2].mSolverVariable = var1.createObjectVariable(this.mListAnchors[var2]);
      }

      var2 = this.mBarrierType;
      if (var2 >= 0 && var2 < 4) {
         ConstraintAnchor var5 = this.mListAnchors[this.mBarrierType];
         var2 = 0;

         int var3;
         boolean var4;
         label107: {
            while(true) {
               if (var2 >= this.mWidgetsCount) {
                  var4 = false;
                  break label107;
               }

               ConstraintWidget var6 = this.mWidgets[var2];
               if (this.mAllowsGoneWidget || var6.allowedInBarrier()) {
                  var3 = this.mBarrierType;
                  if ((var3 == 0 || var3 == 1) && var6.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                     break;
                  }

                  var3 = this.mBarrierType;
                  if ((var3 == 2 || var3 == 3) && var6.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                     break;
                  }
               }

               ++var2;
            }

            var4 = true;
         }

         label129: {
            var2 = this.mBarrierType;
            if (var2 != 0 && var2 != 1) {
               if (this.getParent().getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                  break label129;
               }
            } else if (this.getParent().getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
               break label129;
            }

            var4 = false;
         }

         for(var2 = 0; var2 < this.mWidgetsCount; ++var2) {
            ConstraintWidget var7 = this.mWidgets[var2];
            if (this.mAllowsGoneWidget || var7.allowedInBarrier()) {
               SolverVariable var8 = var1.createObjectVariable(var7.mListAnchors[this.mBarrierType]);
               var7.mListAnchors[this.mBarrierType].mSolverVariable = var8;
               var3 = this.mBarrierType;
               if (var3 != 0 && var3 != 2) {
                  var1.addGreaterBarrier(var5.mSolverVariable, var8, var4);
               } else {
                  var1.addLowerBarrier(var5.mSolverVariable, var8, var4);
               }
            }
         }

         var2 = this.mBarrierType;
         if (var2 == 0) {
            var1.addEquality(this.mRight.mSolverVariable, this.mLeft.mSolverVariable, 0, 6);
            if (!var4) {
               var1.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 5);
            }
         } else if (var2 == 1) {
            var1.addEquality(this.mLeft.mSolverVariable, this.mRight.mSolverVariable, 0, 6);
            if (!var4) {
               var1.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 5);
            }
         } else if (var2 == 2) {
            var1.addEquality(this.mBottom.mSolverVariable, this.mTop.mSolverVariable, 0, 6);
            if (!var4) {
               var1.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 5);
            }
         } else if (var2 == 3) {
            var1.addEquality(this.mTop.mSolverVariable, this.mBottom.mSolverVariable, 0, 6);
            if (!var4) {
               var1.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 5);
            }
         }
      }

   }

   public boolean allowedInBarrier() {
      return true;
   }

   public boolean allowsGoneWidget() {
      return this.mAllowsGoneWidget;
   }

   public void analyze(int var1) {
      if (this.mParent != null) {
         if (((ConstraintWidgetContainer)this.mParent).optimizeFor(2)) {
            var1 = this.mBarrierType;
            ResolutionAnchor var4;
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        return;
                     }

                     var4 = this.mBottom.getResolutionNode();
                  } else {
                     var4 = this.mTop.getResolutionNode();
                  }
               } else {
                  var4 = this.mRight.getResolutionNode();
               }
            } else {
               var4 = this.mLeft.getResolutionNode();
            }

            var4.setType(5);
            var1 = this.mBarrierType;
            if (var1 != 0 && var1 != 1) {
               this.mLeft.getResolutionNode().resolve((ResolutionAnchor)null, 0.0F);
               this.mRight.getResolutionNode().resolve((ResolutionAnchor)null, 0.0F);
            } else {
               this.mTop.getResolutionNode().resolve((ResolutionAnchor)null, 0.0F);
               this.mBottom.getResolutionNode().resolve((ResolutionAnchor)null, 0.0F);
            }

            this.mNodes.clear();

            for(var1 = 0; var1 < this.mWidgetsCount; ++var1) {
               ConstraintWidget var3 = this.mWidgets[var1];
               if (this.mAllowsGoneWidget || var3.allowedInBarrier()) {
                  int var2 = this.mBarrierType;
                  ResolutionAnchor var5;
                  if (var2 != 0) {
                     if (var2 != 1) {
                        if (var2 != 2) {
                           if (var2 != 3) {
                              var5 = null;
                           } else {
                              var5 = var3.mBottom.getResolutionNode();
                           }
                        } else {
                           var5 = var3.mTop.getResolutionNode();
                        }
                     } else {
                        var5 = var3.mRight.getResolutionNode();
                     }
                  } else {
                     var5 = var3.mLeft.getResolutionNode();
                  }

                  if (var5 != null) {
                     this.mNodes.add(var5);
                     var5.addDependent(var4);
                  }
               }
            }

         }
      }
   }

   public void resetResolutionNodes() {
      super.resetResolutionNodes();
      this.mNodes.clear();
   }

   public void resolve() {
      int var3 = this.mBarrierType;
      float var1 = Float.MAX_VALUE;
      ResolutionAnchor var6;
      if (var3 != 0) {
         label65: {
            if (var3 != 1) {
               if (var3 == 2) {
                  var6 = this.mTop.getResolutionNode();
                  break label65;
               }

               if (var3 != 3) {
                  return;
               }

               var6 = this.mBottom.getResolutionNode();
            } else {
               var6 = this.mRight.getResolutionNode();
            }

            var1 = 0.0F;
         }
      } else {
         var6 = this.mLeft.getResolutionNode();
      }

      int var4 = this.mNodes.size();
      ResolutionAnchor var7 = null;
      var3 = 0;

      float var2;
      for(var2 = var1; var3 < var4; var2 = var1) {
         ResolutionAnchor var8 = (ResolutionAnchor)this.mNodes.get(var3);
         if (var8.state != 1) {
            return;
         }

         int var5 = this.mBarrierType;
         if (var5 != 0 && var5 != 2) {
            var1 = var2;
            if (var8.resolvedOffset > var2) {
               var1 = var8.resolvedOffset;
               var7 = var8.resolvedTarget;
            }
         } else {
            var1 = var2;
            if (var8.resolvedOffset < var2) {
               var1 = var8.resolvedOffset;
               var7 = var8.resolvedTarget;
            }
         }

         ++var3;
      }

      if (LinearSystem.getMetrics() != null) {
         Metrics var9 = LinearSystem.getMetrics();
         ++var9.barrierConnectionResolved;
      }

      var6.resolvedTarget = var7;
      var6.resolvedOffset = var2;
      var6.didResolve();
      var3 = this.mBarrierType;
      if (var3 != 0) {
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 != 3) {
                  return;
               }

               this.mTop.getResolutionNode().resolve(var7, var2);
            } else {
               this.mBottom.getResolutionNode().resolve(var7, var2);
            }
         } else {
            this.mLeft.getResolutionNode().resolve(var7, var2);
         }
      } else {
         this.mRight.getResolutionNode().resolve(var7, var2);
      }

   }

   public void setAllowsGoneWidget(boolean var1) {
      this.mAllowsGoneWidget = var1;
   }

   public void setBarrierType(int var1) {
      this.mBarrierType = var1;
   }
}
