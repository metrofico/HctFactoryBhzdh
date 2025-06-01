package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.Metrics;

public class Optimizer {
   static final int FLAG_CHAIN_DANGLING = 1;
   static final int FLAG_RECOMPUTE_BOUNDS = 2;
   static final int FLAG_USE_OPTIMIZE = 0;
   public static final int OPTIMIZATION_BARRIER = 2;
   public static final int OPTIMIZATION_CHAIN = 4;
   public static final int OPTIMIZATION_DIMENSIONS = 8;
   public static final int OPTIMIZATION_DIRECT = 1;
   public static final int OPTIMIZATION_GROUPS = 32;
   public static final int OPTIMIZATION_NONE = 0;
   public static final int OPTIMIZATION_RATIO = 16;
   public static final int OPTIMIZATION_STANDARD = 7;
   static boolean[] flags = new boolean[3];

   static void analyze(int var0, ConstraintWidget var1) {
      var1.updateResolutionNodes();
      ResolutionAnchor var5 = var1.mLeft.getResolutionNode();
      ResolutionAnchor var3 = var1.mTop.getResolutionNode();
      ResolutionAnchor var4 = var1.mRight.getResolutionNode();
      ResolutionAnchor var6 = var1.mBottom.getResolutionNode();
      boolean var7;
      if ((var0 & 8) == 8) {
         var7 = true;
      } else {
         var7 = false;
      }

      boolean var2;
      if (var1.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(var1, 0)) {
         var2 = true;
      } else {
         var2 = false;
      }

      int var8;
      if (var5.type != 4 && var4.type != 4) {
         if (var1.mListDimensionBehaviors[0] != ConstraintWidget.DimensionBehaviour.FIXED && (!var2 || var1.getVisibility() != 8)) {
            if (var2) {
               var8 = var1.getWidth();
               var5.setType(1);
               var4.setType(1);
               if (var1.mLeft.mTarget == null && var1.mRight.mTarget == null) {
                  if (var7) {
                     var4.dependsOn(var5, 1, var1.getResolutionWidth());
                  } else {
                     var4.dependsOn(var5, var8);
                  }
               } else if (var1.mLeft.mTarget != null && var1.mRight.mTarget == null) {
                  if (var7) {
                     var4.dependsOn(var5, 1, var1.getResolutionWidth());
                  } else {
                     var4.dependsOn(var5, var8);
                  }
               } else if (var1.mLeft.mTarget == null && var1.mRight.mTarget != null) {
                  if (var7) {
                     var5.dependsOn(var4, -1, var1.getResolutionWidth());
                  } else {
                     var5.dependsOn(var4, -var8);
                  }
               } else if (var1.mLeft.mTarget != null && var1.mRight.mTarget != null) {
                  if (var7) {
                     var1.getResolutionWidth().addDependent(var5);
                     var1.getResolutionWidth().addDependent(var4);
                  }

                  if (var1.mDimensionRatio == 0.0F) {
                     var5.setType(3);
                     var4.setType(3);
                     var5.setOpposite(var4, 0.0F);
                     var4.setOpposite(var5, 0.0F);
                  } else {
                     var5.setType(2);
                     var4.setType(2);
                     var5.setOpposite(var4, (float)(-var8));
                     var4.setOpposite(var5, (float)var8);
                     var1.setWidth(var8);
                  }
               }
            }
         } else if (var1.mLeft.mTarget == null && var1.mRight.mTarget == null) {
            var5.setType(1);
            var4.setType(1);
            if (var7) {
               var4.dependsOn(var5, 1, var1.getResolutionWidth());
            } else {
               var4.dependsOn(var5, var1.getWidth());
            }
         } else if (var1.mLeft.mTarget != null && var1.mRight.mTarget == null) {
            var5.setType(1);
            var4.setType(1);
            if (var7) {
               var4.dependsOn(var5, 1, var1.getResolutionWidth());
            } else {
               var4.dependsOn(var5, var1.getWidth());
            }
         } else if (var1.mLeft.mTarget == null && var1.mRight.mTarget != null) {
            var5.setType(1);
            var4.setType(1);
            var5.dependsOn(var4, -var1.getWidth());
            if (var7) {
               var5.dependsOn(var4, -1, var1.getResolutionWidth());
            } else {
               var5.dependsOn(var4, -var1.getWidth());
            }
         } else if (var1.mLeft.mTarget != null && var1.mRight.mTarget != null) {
            var5.setType(2);
            var4.setType(2);
            if (var7) {
               var1.getResolutionWidth().addDependent(var5);
               var1.getResolutionWidth().addDependent(var4);
               var5.setOpposite(var4, -1, var1.getResolutionWidth());
               var4.setOpposite(var5, 1, var1.getResolutionWidth());
            } else {
               var5.setOpposite(var4, (float)(-var1.getWidth()));
               var4.setOpposite(var5, (float)var1.getWidth());
            }
         }
      }

      if (var1.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(var1, 1)) {
         var2 = true;
      } else {
         var2 = false;
      }

      if (var3.type != 4 && var6.type != 4) {
         if (var1.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.FIXED && (!var2 || var1.getVisibility() != 8)) {
            if (var2) {
               var8 = var1.getHeight();
               var3.setType(1);
               var6.setType(1);
               if (var1.mTop.mTarget == null && var1.mBottom.mTarget == null) {
                  if (var7) {
                     var6.dependsOn(var3, 1, var1.getResolutionHeight());
                  } else {
                     var6.dependsOn(var3, var8);
                  }
               } else if (var1.mTop.mTarget != null && var1.mBottom.mTarget == null) {
                  if (var7) {
                     var6.dependsOn(var3, 1, var1.getResolutionHeight());
                  } else {
                     var6.dependsOn(var3, var8);
                  }
               } else if (var1.mTop.mTarget == null && var1.mBottom.mTarget != null) {
                  if (var7) {
                     var3.dependsOn(var6, -1, var1.getResolutionHeight());
                  } else {
                     var3.dependsOn(var6, -var8);
                  }
               } else if (var1.mTop.mTarget != null && var1.mBottom.mTarget != null) {
                  if (var7) {
                     var1.getResolutionHeight().addDependent(var3);
                     var1.getResolutionWidth().addDependent(var6);
                  }

                  if (var1.mDimensionRatio == 0.0F) {
                     var3.setType(3);
                     var6.setType(3);
                     var3.setOpposite(var6, 0.0F);
                     var6.setOpposite(var3, 0.0F);
                  } else {
                     var3.setType(2);
                     var6.setType(2);
                     var3.setOpposite(var6, (float)(-var8));
                     var6.setOpposite(var3, (float)var8);
                     var1.setHeight(var8);
                     if (var1.mBaselineDistance > 0) {
                        var1.mBaseline.getResolutionNode().dependsOn(1, var3, var1.mBaselineDistance);
                     }
                  }
               }
            }
         } else if (var1.mTop.mTarget == null && var1.mBottom.mTarget == null) {
            var3.setType(1);
            var6.setType(1);
            if (var7) {
               var6.dependsOn(var3, 1, var1.getResolutionHeight());
            } else {
               var6.dependsOn(var3, var1.getHeight());
            }

            if (var1.mBaseline.mTarget != null) {
               var1.mBaseline.getResolutionNode().setType(1);
               var3.dependsOn(1, var1.mBaseline.getResolutionNode(), -var1.mBaselineDistance);
            }
         } else if (var1.mTop.mTarget != null && var1.mBottom.mTarget == null) {
            var3.setType(1);
            var6.setType(1);
            if (var7) {
               var6.dependsOn(var3, 1, var1.getResolutionHeight());
            } else {
               var6.dependsOn(var3, var1.getHeight());
            }

            if (var1.mBaselineDistance > 0) {
               var1.mBaseline.getResolutionNode().dependsOn(1, var3, var1.mBaselineDistance);
            }
         } else if (var1.mTop.mTarget == null && var1.mBottom.mTarget != null) {
            var3.setType(1);
            var6.setType(1);
            if (var7) {
               var3.dependsOn(var6, -1, var1.getResolutionHeight());
            } else {
               var3.dependsOn(var6, -var1.getHeight());
            }

            if (var1.mBaselineDistance > 0) {
               var1.mBaseline.getResolutionNode().dependsOn(1, var3, var1.mBaselineDistance);
            }
         } else if (var1.mTop.mTarget != null && var1.mBottom.mTarget != null) {
            var3.setType(2);
            var6.setType(2);
            if (var7) {
               var3.setOpposite(var6, -1, var1.getResolutionHeight());
               var6.setOpposite(var3, 1, var1.getResolutionHeight());
               var1.getResolutionHeight().addDependent(var3);
               var1.getResolutionWidth().addDependent(var6);
            } else {
               var3.setOpposite(var6, (float)(-var1.getHeight()));
               var6.setOpposite(var3, (float)var1.getHeight());
            }

            if (var1.mBaselineDistance > 0) {
               var1.mBaseline.getResolutionNode().dependsOn(1, var3, var1.mBaselineDistance);
            }
         }
      }

   }

   static boolean applyChainOptimized(ConstraintWidgetContainer var0, LinearSystem var1, int var2, int var3, ChainHead var4) {
      float var11;
      boolean var12;
      boolean var13;
      boolean var14;
      ConstraintWidget var19;
      ConstraintWidget var20;
      ConstraintWidget var22;
      ConstraintWidget var23;
      ConstraintWidget var32;
      label314: {
         boolean var15;
         label313: {
            var19 = var4.mFirst;
            var20 = var4.mLast;
            var22 = var4.mFirstVisibleWidget;
            var23 = var4.mLastVisibleWidget;
            ConstraintWidget var21 = var4.mHead;
            var11 = var4.mTotalWeight;
            ConstraintWidget var24 = var4.mFirstMatchConstraintWidget;
            var32 = var4.mLastMatchConstraintWidget;
            ConstraintWidget.DimensionBehaviour var10000 = var0.mListDimensionBehaviors[var2];
            ConstraintWidget.DimensionBehaviour var25 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (var2 == 0) {
               if (var21.mHorizontalChainStyle == 0) {
                  var12 = true;
               } else {
                  var12 = false;
               }

               if (var21.mHorizontalChainStyle == 1) {
                  var13 = true;
               } else {
                  var13 = false;
               }

               var15 = var12;
               var14 = var13;
               if (var21.mHorizontalChainStyle == 2) {
                  var14 = var13;
                  var15 = var12;
                  break label313;
               }
            } else {
               if (var21.mVerticalChainStyle == 0) {
                  var12 = true;
               } else {
                  var12 = false;
               }

               if (var21.mVerticalChainStyle == 1) {
                  var13 = true;
               } else {
                  var13 = false;
               }

               var15 = var12;
               var14 = var13;
               if (var21.mVerticalChainStyle == 2) {
                  var15 = var12;
                  var14 = var13;
                  break label313;
               }
            }

            var12 = false;
            var13 = var15;
            break label314;
         }

         var12 = true;
         var13 = var15;
      }

      var32 = var19;
      int var17 = 0;
      boolean var16 = false;
      int var38 = 0;
      float var9 = 0.0F;
      float var8 = 0.0F;

      float var5;
      float var6;
      ConstraintWidget var27;
      while(!var16) {
         int var18 = var38;
         var5 = var9;
         var6 = var8;
         if (var32.getVisibility() != 8) {
            var18 = var38 + 1;
            if (var2 == 0) {
               var38 = var32.getWidth();
            } else {
               var38 = var32.getHeight();
            }

            var5 = var9 + (float)var38;
            var6 = var5;
            if (var32 != var22) {
               var6 = var5 + (float)var32.mListAnchors[var3].getMargin();
            }

            var5 = var6;
            if (var32 != var23) {
               var5 = var6 + (float)var32.mListAnchors[var3 + 1].getMargin();
            }

            var6 = var8 + (float)var32.mListAnchors[var3].getMargin() + (float)var32.mListAnchors[var3 + 1].getMargin();
         }

         ConstraintAnchor var36 = var32.mListAnchors[var3];
         var38 = var17;
         if (var32.getVisibility() != 8) {
            var38 = var17;
            if (var32.mListDimensionBehaviors[var2] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
               var38 = var17 + 1;
               if (var2 == 0) {
                  if (var32.mMatchConstraintDefaultWidth != 0) {
                     return false;
                  }

                  if (var32.mMatchConstraintMinWidth != 0 || var32.mMatchConstraintMaxWidth != 0) {
                     return false;
                  }
               } else {
                  if (var32.mMatchConstraintDefaultHeight != 0) {
                     return false;
                  }

                  if (var32.mMatchConstraintMinHeight != 0 || var32.mMatchConstraintMaxHeight != 0) {
                     return false;
                  }
               }

               if (var32.mDimensionRatio != 0.0F) {
                  return false;
               }
            }
         }

         label276: {
            ConstraintAnchor var26 = var32.mListAnchors[var3 + 1].mTarget;
            if (var26 != null) {
               var27 = var26.mOwner;
               if (var27.mListAnchors[var3].mTarget != null && var27.mListAnchors[var3].mTarget.mOwner == var32) {
                  break label276;
               }
            }

            var27 = null;
         }

         if (var27 != null) {
            var17 = var38;
            var32 = var27;
            var38 = var18;
            var9 = var5;
            var8 = var6;
         } else {
            var16 = true;
            var17 = var38;
            var38 = var18;
            var9 = var5;
            var8 = var6;
         }
      }

      ResolutionAnchor var40 = var19.mListAnchors[var3].getResolutionNode();
      ConstraintAnchor[] var28 = var20.mListAnchors;
      int var37 = var3 + 1;
      ResolutionAnchor var29 = var28[var37].getResolutionNode();
      if (var40.target != null && var29.target != null) {
         if (var40.target.state == 1 && var29.target.state == 1) {
            if (var17 > 0 && var17 != var38) {
               return false;
            } else {
               if (!var12 && !var13 && !var14) {
                  var5 = 0.0F;
               } else {
                  if (var22 != null) {
                     var6 = (float)var22.mListAnchors[var3].getMargin();
                  } else {
                     var6 = 0.0F;
                  }

                  var5 = var6;
                  if (var23 != null) {
                     var5 = var6 + (float)var23.mListAnchors[var37].getMargin();
                  }
               }

               float var7 = var40.target.resolvedOffset;
               var6 = var29.target.resolvedOffset;
               if (var7 < var6) {
                  var6 -= var7;
               } else {
                  var6 = var7 - var6;
               }

               float var10 = var6 - var9;
               Metrics var34;
               ResolutionAnchor var41;
               if (var17 > 0 && var17 == var38) {
                  if (var32.getParent() != null && var32.getParent().mListDimensionBehaviors[var2] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                     return false;
                  } else {
                     var6 = var10 + var9 - var8;

                     for(var27 = var19; var27 != null; var7 = var5) {
                        if (LinearSystem.sMetrics != null) {
                           var34 = LinearSystem.sMetrics;
                           --var34.nonresolvedWidgets;
                           var34 = LinearSystem.sMetrics;
                           ++var34.resolvedWidgets;
                           var34 = LinearSystem.sMetrics;
                           ++var34.chainConnectionResolved;
                        }

                        label329: {
                           var32 = var27.mNextChainWidget[var2];
                           if (var32 == null) {
                              var5 = var7;
                              if (var27 != var20) {
                                 break label329;
                              }
                           }

                           var5 = var6 / (float)var17;
                           if (var11 > 0.0F) {
                              if (var27.mWeight[var2] == -1.0F) {
                                 var5 = 0.0F;
                              } else {
                                 var5 = var27.mWeight[var2] * var6 / var11;
                              }
                           }

                           if (var27.getVisibility() == 8) {
                              var5 = 0.0F;
                           }

                           var7 += (float)var27.mListAnchors[var3].getMargin();
                           var27.mListAnchors[var3].getResolutionNode().resolve(var40.resolvedTarget, var7);
                           var41 = var27.mListAnchors[var37].getResolutionNode();
                           ResolutionAnchor var39 = var40.resolvedTarget;
                           var5 += var7;
                           var41.resolve(var39, var5);
                           var27.mListAnchors[var3].getResolutionNode().addResolvedValue(var1);
                           var27.mListAnchors[var37].getResolutionNode().addResolvedValue(var1);
                           var5 += (float)var27.mListAnchors[var37].getMargin();
                        }

                        var27 = var32;
                     }

                     return true;
                  }
               } else {
                  if (var10 < 0.0F) {
                     var12 = true;
                     var13 = false;
                     var14 = false;
                  }

                  int var35;
                  if (var12) {
                     Object var30 = var19;
                     var5 = var7 + (var10 - var5) * var19.getBiasPercent(var2);

                     while(true) {
                        Object var33;
                        do {
                           var33 = var30;
                           if (var30 == null) {
                              return true;
                           }

                           if (LinearSystem.sMetrics != null) {
                              Metrics var31 = LinearSystem.sMetrics;
                              --var31.nonresolvedWidgets;
                              var31 = LinearSystem.sMetrics;
                              ++var31.resolvedWidgets;
                              var30 = LinearSystem.sMetrics;
                              ++((Metrics)var30).chainConnectionResolved;
                           }

                           var19 = ((ConstraintWidget)var30).mNextChainWidget[var2];
                           if (var19 != null) {
                              break;
                           }

                           var30 = var19;
                        } while(var33 != var20);

                        if (var2 == 0) {
                           var35 = ((ConstraintWidget)var30).getWidth();
                        } else {
                           var35 = ((ConstraintWidget)var30).getHeight();
                        }

                        var6 = (float)var35;
                        var5 += (float)((ConstraintWidget)var30).mListAnchors[var3].getMargin();
                        ((ConstraintWidget)var30).mListAnchors[var3].getResolutionNode().resolve(var40.resolvedTarget, var5);
                        var41 = ((ConstraintWidget)var30).mListAnchors[var37].getResolutionNode();
                        var29 = var40.resolvedTarget;
                        var5 += var6;
                        var41.resolve(var29, var5);
                        ((ConstraintWidget)var33).mListAnchors[var3].getResolutionNode().addResolvedValue(var1);
                        ((ConstraintWidget)var33).mListAnchors[var37].getResolutionNode().addResolvedValue(var1);
                        var5 += (float)((ConstraintWidget)var33).mListAnchors[var37].getMargin();
                        var30 = var19;
                     }
                  } else if (var13 || var14) {
                     label234: {
                        if (!var13) {
                           var6 = var10;
                           if (!var14) {
                              break label234;
                           }
                        }

                        var6 = var10 - var5;
                     }

                     var8 = var6 / (float)(var38 + 1);
                     if (var14) {
                        if (var38 > 1) {
                           var5 = (float)(var38 - 1);
                        } else {
                           var5 = 2.0F;
                        }

                        var8 = var6 / var5;
                     }

                     if (var19.getVisibility() != 8) {
                        var5 = var7 + var8;
                     } else {
                        var5 = var7;
                     }

                     var6 = var5;
                     if (var14) {
                        var6 = var5;
                        if (var38 > 1) {
                           var6 = (float)var22.mListAnchors[var3].getMargin() + var7;
                        }
                     }

                     var27 = var19;
                     var5 = var6;
                     if (var13) {
                        var27 = var19;
                        var5 = var6;
                        if (var22 != null) {
                           var5 = var6 + (float)var22.mListAnchors[var3].getMargin();
                           var27 = var19;
                        }
                     }

                     for(; var27 != null; var27 = var32) {
                        if (LinearSystem.sMetrics != null) {
                           var34 = LinearSystem.sMetrics;
                           --var34.nonresolvedWidgets;
                           var34 = LinearSystem.sMetrics;
                           ++var34.resolvedWidgets;
                           var34 = LinearSystem.sMetrics;
                           ++var34.chainConnectionResolved;
                        }

                        label334: {
                           var32 = var27.mNextChainWidget[var2];
                           if (var32 == null) {
                              var6 = var5;
                              if (var27 != var20) {
                                 break label334;
                              }
                           }

                           if (var2 == 0) {
                              var35 = var27.getWidth();
                           } else {
                              var35 = var27.getHeight();
                           }

                           var7 = (float)var35;
                           var6 = var5;
                           if (var27 != var22) {
                              var6 = var5 + (float)var27.mListAnchors[var3].getMargin();
                           }

                           var27.mListAnchors[var3].getResolutionNode().resolve(var40.resolvedTarget, var6);
                           var27.mListAnchors[var37].getResolutionNode().resolve(var40.resolvedTarget, var6 + var7);
                           var27.mListAnchors[var3].getResolutionNode().addResolvedValue(var1);
                           var27.mListAnchors[var37].getResolutionNode().addResolvedValue(var1);
                           var7 = var6 + var7 + (float)var27.mListAnchors[var37].getMargin();
                           var6 = var7;
                           if (var32 != null) {
                              var5 = var7;
                              if (var32.getVisibility() != 8) {
                                 var5 = var7 + var8;
                              }
                              continue;
                           }
                        }

                        var5 = var6;
                     }
                  }

                  return true;
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   static void checkMatchParent(ConstraintWidgetContainer var0, LinearSystem var1, ConstraintWidget var2) {
      int var3;
      int var4;
      if (var0.mListDimensionBehaviors[0] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && var2.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
         var3 = var2.mLeft.mMargin;
         var4 = var0.getWidth() - var2.mRight.mMargin;
         var2.mLeft.mSolverVariable = var1.createObjectVariable(var2.mLeft);
         var2.mRight.mSolverVariable = var1.createObjectVariable(var2.mRight);
         var1.addEquality(var2.mLeft.mSolverVariable, var3);
         var1.addEquality(var2.mRight.mSolverVariable, var4);
         var2.mHorizontalResolution = 2;
         var2.setHorizontalDimension(var3, var4);
      }

      if (var0.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && var2.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
         var3 = var2.mTop.mMargin;
         var4 = var0.getHeight() - var2.mBottom.mMargin;
         var2.mTop.mSolverVariable = var1.createObjectVariable(var2.mTop);
         var2.mBottom.mSolverVariable = var1.createObjectVariable(var2.mBottom);
         var1.addEquality(var2.mTop.mSolverVariable, var3);
         var1.addEquality(var2.mBottom.mSolverVariable, var4);
         if (var2.mBaselineDistance > 0 || var2.getVisibility() == 8) {
            var2.mBaseline.mSolverVariable = var1.createObjectVariable(var2.mBaseline);
            var1.addEquality(var2.mBaseline.mSolverVariable, var2.mBaselineDistance + var3);
         }

         var2.mVerticalResolution = 2;
         var2.setVerticalDimension(var3, var4);
      }

   }

   private static boolean optimizableMatchConstraint(ConstraintWidget var0, int var1) {
      if (var0.mListDimensionBehaviors[var1] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
         return false;
      } else {
         float var2 = var0.mDimensionRatio;
         byte var3 = 1;
         if (var2 != 0.0F) {
            ConstraintWidget.DimensionBehaviour[] var4 = var0.mListDimensionBehaviors;
            byte var6;
            if (var1 == 0) {
               var6 = var3;
            } else {
               var6 = 0;
            }

            ConstraintWidget.DimensionBehaviour var10000 = var4[var6];
            ConstraintWidget.DimensionBehaviour var5 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            return false;
         } else {
            if (var1 == 0) {
               if (var0.mMatchConstraintDefaultWidth != 0) {
                  return false;
               }

               if (var0.mMatchConstraintMinWidth != 0 || var0.mMatchConstraintMaxWidth != 0) {
                  return false;
               }
            } else {
               if (var0.mMatchConstraintDefaultHeight != 0) {
                  return false;
               }

               if (var0.mMatchConstraintMinHeight != 0 || var0.mMatchConstraintMaxHeight != 0) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   static void setOptimizedWidget(ConstraintWidget var0, int var1, int var2) {
      int var3 = var1 * 2;
      int var4 = var3 + 1;
      var0.mListAnchors[var3].getResolutionNode().resolvedTarget = var0.getParent().mLeft.getResolutionNode();
      var0.mListAnchors[var3].getResolutionNode().resolvedOffset = (float)var2;
      var0.mListAnchors[var3].getResolutionNode().state = 1;
      var0.mListAnchors[var4].getResolutionNode().resolvedTarget = var0.mListAnchors[var3].getResolutionNode();
      var0.mListAnchors[var4].getResolutionNode().resolvedOffset = (float)var0.getLength(var1);
      var0.mListAnchors[var4].getResolutionNode().state = 1;
   }
}
