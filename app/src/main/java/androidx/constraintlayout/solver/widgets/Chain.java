package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.ArrayRow;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import java.util.ArrayList;

class Chain {
   private static final boolean DEBUG = false;

   static void applyChainConstraints(ConstraintWidgetContainer var0, LinearSystem var1, int var2) {
      int var5 = 0;
      byte var3;
      int var4;
      ChainHead[] var6;
      if (var2 == 0) {
         var4 = var0.mHorizontalChainsSize;
         var6 = var0.mHorizontalChainsArray;
         var3 = 0;
      } else {
         var3 = 2;
         var4 = var0.mVerticalChainsSize;
         var6 = var0.mVerticalChainsArray;
      }

      for(; var5 < var4; ++var5) {
         ChainHead var7 = var6[var5];
         var7.define();
         if (var0.optimizeFor(4)) {
            if (!Optimizer.applyChainOptimized(var0, var1, var2, var3, var7)) {
               applyChainConstraints(var0, var1, var2, var3, var7);
            }
         } else {
            applyChainConstraints(var0, var1, var2, var3, var7);
         }
      }

   }

   static void applyChainConstraints(ConstraintWidgetContainer var0, LinearSystem var1, int var2, int var3, ChainHead var4) {
      ConstraintWidget var25 = var4.mFirst;
      ConstraintWidget var23 = var4.mLast;
      ConstraintWidget var19 = var4.mFirstVisibleWidget;
      ConstraintWidget var22 = var4.mLastVisibleWidget;
      ConstraintWidget var17 = var4.mHead;
      float var5 = var4.mTotalWeight;
      ConstraintWidget var16 = var4.mFirstMatchConstraintWidget;
      var16 = var4.mLastMatchConstraintWidget;
      boolean var12;
      if (var0.mListDimensionBehaviors[var2] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
         var12 = true;
      } else {
         var12 = false;
      }

      boolean var8;
      int var9;
      boolean var10;
      boolean var13;
      label451: {
         label450: {
            int var11;
            if (var2 == 0) {
               if (var17.mHorizontalChainStyle == 0) {
                  var8 = true;
               } else {
                  var8 = false;
               }

               if (var17.mHorizontalChainStyle == 1) {
                  var9 = 1;
               } else {
                  var9 = 0;
               }

               var10 = var8;
               var11 = var9;
               if (var17.mHorizontalChainStyle == 2) {
                  break label450;
               }
            } else {
               if (var17.mVerticalChainStyle == 0) {
                  var8 = true;
               } else {
                  var8 = false;
               }

               if (var17.mVerticalChainStyle == 1) {
                  var9 = 1;
               } else {
                  var9 = 0;
               }

               var10 = var8;
               var11 = var9;
               if (var17.mVerticalChainStyle == 2) {
                  break label450;
               }
            }

            var13 = false;
            var9 = var11;
            break label451;
         }

         var13 = true;
         var10 = var8;
      }

      ConstraintWidget var18 = var25;
      var8 = false;

      while(true) {
         Object var24 = null;
         SolverVariable var20 = null;
         int var14;
         ConstraintAnchor var21;
         ConstraintAnchor var41;
         if (var8) {
            int var36;
            ConstraintAnchor[] var44;
            if (var22 != null) {
               var44 = var23.mListAnchors;
               var36 = var3 + 1;
               if (var44[var36].mTarget != null) {
                  var41 = var22.mListAnchors[var36];
                  var1.addLowerThan(var41.mSolverVariable, var23.mListAnchors[var36].mTarget.mSolverVariable, -var41.getMargin(), 5);
               }
            }

            ConstraintAnchor[] var28;
            if (var12) {
               var28 = var0.mListAnchors;
               var36 = var3 + 1;
               var1.addGreaterThan(var28[var36].mSolverVariable, var23.mListAnchors[var36].mSolverVariable, var23.mListAnchors[var36].getMargin(), 6);
            }

            ArrayList var29 = var4.mWeightedMatchConstraintsWidgets;
            SolverVariable var27;
            int var38;
            SolverVariable var51;
            if (var29 != null) {
               var9 = var29.size();
               if (var9 > 1) {
                  float var6;
                  if (var4.mHasUndefinedWeights && !var4.mHasComplexMatchWeights) {
                     var6 = (float)var4.mWidgetsMatchCount;
                  } else {
                     var6 = var5;
                  }

                  float var7 = 0.0F;
                  var16 = null;

                  for(var36 = 0; var36 < var9; var7 = var5) {
                     label411: {
                        label461: {
                           var18 = (ConstraintWidget)var29.get(var36);
                           var5 = var18.mWeight[var2];
                           if (var5 < 0.0F) {
                              if (var4.mHasComplexMatchWeights) {
                                 var1.addEquality(var18.mListAnchors[var3 + 1].mSolverVariable, var18.mListAnchors[var3].mSolverVariable, 0, 4);
                                 break label461;
                              }

                              var5 = 1.0F;
                           }

                           if (var5 != 0.0F) {
                              if (var16 != null) {
                                 var20 = var16.mListAnchors[var3].mSolverVariable;
                                 var44 = var16.mListAnchors;
                                 var38 = var3 + 1;
                                 var27 = var44[var38].mSolverVariable;
                                 var51 = var18.mListAnchors[var3].mSolverVariable;
                                 SolverVariable var46 = var18.mListAnchors[var38].mSolverVariable;
                                 ArrayRow var26 = var1.createRow();
                                 var26.createRowEqualMatchDimensions(var7, var6, var5, var20, var27, var51, var46);
                                 var1.addConstraint(var26);
                              }

                              var16 = var18;
                              break label411;
                           }

                           var1.addEquality(var18.mListAnchors[var3 + 1].mSolverVariable, var18.mListAnchors[var3].mSolverVariable, 0, 6);
                        }

                        var5 = var7;
                     }

                     ++var36;
                  }
               }
            }

            SolverVariable var30;
            SolverVariable var33;
            ConstraintAnchor var42;
            ConstraintAnchor var45;
            if (var19 == null || var19 != var22 && !var13) {
               ConstraintWidget var34;
               int var39;
               byte var40;
               SolverVariable var43;
               if (var10 && var19 != null) {
                  if (var4.mWidgetsMatchCount > 0 && var4.mWidgetsCount == var4.mWidgetsMatchCount) {
                     var12 = true;
                  } else {
                     var12 = false;
                  }

                  var34 = var19;

                  for(var18 = var19; var34 != null; var34 = var16) {
                     for(var16 = var34.mNextChainWidget[var2]; var16 != null && var16.getVisibility() == 8; var16 = var16.mNextChainWidget[var2]) {
                     }

                     if (var16 != null || var34 == var22) {
                        ConstraintAnchor var52 = var34.mListAnchors[var3];
                        var27 = var52.mSolverVariable;
                        if (var52.mTarget != null) {
                           var43 = var52.mTarget.mSolverVariable;
                        } else {
                           var43 = null;
                        }

                        if (var18 != var34) {
                           var30 = var18.mListAnchors[var3 + 1].mSolverVariable;
                        } else {
                           var30 = var43;
                           if (var34 == var19) {
                              var30 = var43;
                              if (var18 == var34) {
                                 if (var25.mListAnchors[var3].mTarget != null) {
                                    var30 = var25.mListAnchors[var3].mTarget.mSolverVariable;
                                 } else {
                                    var30 = null;
                                 }
                              }
                           }
                        }

                        var39 = var52.getMargin();
                        ConstraintAnchor[] var48 = var34.mListAnchors;
                        var14 = var3 + 1;
                        var9 = var48[var14].getMargin();
                        if (var16 != null) {
                           var42 = var16.mListAnchors[var3];
                           var51 = var42.mSolverVariable;
                           var20 = var34.mListAnchors[var14].mSolverVariable;
                        } else {
                           ConstraintAnchor var54 = var23.mListAnchors[var14].mTarget;
                           if (var54 != null) {
                              var43 = var54.mSolverVariable;
                           } else {
                              var43 = null;
                           }

                           var20 = var34.mListAnchors[var14].mSolverVariable;
                           var51 = var43;
                           var42 = var54;
                        }

                        var36 = var9;
                        if (var42 != null) {
                           var36 = var9 + var42.getMargin();
                        }

                        var9 = var39;
                        if (var18 != null) {
                           var9 = var39 + var18.mListAnchors[var14].getMargin();
                        }

                        if (var27 != null && var30 != null && var51 != null && var20 != null) {
                           if (var34 == var19) {
                              var9 = var19.mListAnchors[var3].getMargin();
                           }

                           if (var34 == var22) {
                              var36 = var22.mListAnchors[var14].getMargin();
                           }

                           if (var12) {
                              var40 = 6;
                           } else {
                              var40 = 4;
                           }

                           var1.addCentering(var27, var30, var9, 0.5F, var51, var20, var36, var40);
                        }
                     }

                     if (var34.getVisibility() != 8) {
                        var18 = var34;
                     }
                  }
               } else {
                  byte var37 = 8;
                  if (var9 != 0 && var19 != null) {
                     if (var4.mWidgetsMatchCount > 0 && var4.mWidgetsCount == var4.mWidgetsMatchCount) {
                        var9 = 1;
                     } else {
                        var9 = 0;
                     }

                     var34 = var19;

                     ConstraintWidget var31;
                     for(var16 = var19; var34 != null; var34 = var31) {
                        for(var31 = var34.mNextChainWidget[var2]; var31 != null && var31.getVisibility() == var37; var31 = var31.mNextChainWidget[var2]) {
                        }

                        if (var34 != var19 && var34 != var22 && var31 != null) {
                           if (var31 == var22) {
                              var31 = null;
                           }

                           var42 = var34.mListAnchors[var3];
                           SolverVariable var53 = var42.mSolverVariable;
                           SolverVariable var47;
                           if (var42.mTarget != null) {
                              var47 = var42.mTarget.mSolverVariable;
                           }

                           ConstraintAnchor[] var49 = var16.mListAnchors;
                           var14 = var3 + 1;
                           var27 = var49[var14].mSolverVariable;
                           var39 = var42.getMargin();
                           var38 = var34.mListAnchors[var14].getMargin();
                           if (var31 != null) {
                              var45 = var31.mListAnchors[var3];
                              var20 = var45.mSolverVariable;
                              if (var45.mTarget != null) {
                                 var43 = var45.mTarget.mSolverVariable;
                              } else {
                                 var43 = null;
                              }
                           } else {
                              var21 = var34.mListAnchors[var14].mTarget;
                              if (var21 != null) {
                                 var47 = var21.mSolverVariable;
                              } else {
                                 var47 = null;
                              }

                              var43 = var34.mListAnchors[var14].mSolverVariable;
                              var20 = var47;
                              var45 = var21;
                           }

                           var36 = var38;
                           if (var45 != null) {
                              var36 = var38 + var45.getMargin();
                           }

                           var38 = var39;
                           if (var16 != null) {
                              var38 = var39 + var16.mListAnchors[var14].getMargin();
                           }

                           if (var9 != 0) {
                              var40 = 6;
                           } else {
                              var40 = 4;
                           }

                           if (var53 != null && var27 != null && var20 != null && var43 != null) {
                              var1.addCentering(var53, var27, var38, 0.5F, var20, var43, var36, var40);
                           }

                           var37 = 8;
                        }

                        if (var34.getVisibility() == var37) {
                           var34 = var16;
                        }

                        var16 = var34;
                     }

                     ConstraintAnchor var32 = var19.mListAnchors[var3];
                     ConstraintAnchor var35 = var25.mListAnchors[var3].mTarget;
                     var44 = var22.mListAnchors;
                     var2 = var3 + 1;
                     var42 = var44[var2];
                     var41 = var23.mListAnchors[var2].mTarget;
                     if (var35 != null) {
                        if (var19 != var22) {
                           var1.addEquality(var32.mSolverVariable, var35.mSolverVariable, var32.getMargin(), 5);
                        } else if (var41 != null) {
                           var1.addCentering(var32.mSolverVariable, var35.mSolverVariable, var32.getMargin(), 0.5F, var42.mSolverVariable, var41.mSolverVariable, var42.getMargin(), 5);
                        }
                     }

                     if (var41 != null && var19 != var22) {
                        var1.addEquality(var42.mSolverVariable, var41.mSolverVariable, -var42.getMargin(), 5);
                     }
                  }
               }
            } else {
               var41 = var25.mListAnchors[var3];
               var28 = var23.mListAnchors;
               var36 = var3 + 1;
               var45 = var28[var36];
               if (var25.mListAnchors[var3].mTarget != null) {
                  var30 = var25.mListAnchors[var3].mTarget.mSolverVariable;
               } else {
                  var30 = null;
               }

               if (var23.mListAnchors[var36].mTarget != null) {
                  var33 = var23.mListAnchors[var36].mTarget.mSolverVariable;
               } else {
                  var33 = null;
               }

               if (var19 == var22) {
                  var41 = var19.mListAnchors[var3];
                  var45 = var19.mListAnchors[var36];
               }

               if (var30 != null && var33 != null) {
                  if (var2 == 0) {
                     var5 = var17.mHorizontalBiasPercent;
                  } else {
                     var5 = var17.mVerticalBiasPercent;
                  }

                  var36 = var41.getMargin();
                  var2 = var45.getMargin();
                  var1.addCentering(var41.mSolverVariable, var30, var36, var5, var33, var45.mSolverVariable, var2, 5);
               }
            }

            if ((var10 || var9 != 0) && var19 != null) {
               var42 = var19.mListAnchors[var3];
               var28 = var22.mListAnchors;
               var2 = var3 + 1;
               var41 = var28[var2];
               if (var42.mTarget != null) {
                  var33 = var42.mTarget.mSolverVariable;
               } else {
                  var33 = null;
               }

               if (var41.mTarget != null) {
                  var30 = var41.mTarget.mSolverVariable;
               } else {
                  var30 = null;
               }

               if (var23 != var22) {
                  var45 = var23.mListAnchors[var2];
                  var30 = (SolverVariable)var24;
                  if (var45.mTarget != null) {
                     var30 = var45.mTarget.mSolverVariable;
                  }
               }

               if (var19 == var22) {
                  var42 = var19.mListAnchors[var3];
                  var41 = var19.mListAnchors[var2];
               }

               if (var33 != null && var30 != null) {
                  var3 = var42.getMargin();
                  if (var22 == null) {
                     var18 = var23;
                  } else {
                     var18 = var22;
                  }

                  var2 = var18.mListAnchors[var2].getMargin();
                  var1.addCentering(var42.mSolverVariable, var33, var3, 0.5F, var30, var41.mSolverVariable, var2, 5);
               }
            }

            return;
         }

         var41 = var18.mListAnchors[var3];
         if (!var12 && !var13) {
            var9 = 4;
         } else {
            var9 = 1;
         }

         int var15 = var41.getMargin();
         var14 = var15;
         if (var41.mTarget != null) {
            var14 = var15;
            if (var18 != var25) {
               var14 = var15 + var41.mTarget.getMargin();
            }
         }

         if (var13 && var18 != var25 && var18 != var19) {
            var9 = 6;
         } else if (var10 && var12) {
            var9 = 4;
         }

         if (var41.mTarget != null) {
            if (var18 == var19) {
               var1.addGreaterThan(var41.mSolverVariable, var41.mTarget.mSolverVariable, var14, 5);
            } else {
               var1.addGreaterThan(var41.mSolverVariable, var41.mTarget.mSolverVariable, var14, 6);
            }

            var1.addEquality(var41.mSolverVariable, var41.mTarget.mSolverVariable, var14, var9);
         }

         if (var12) {
            if (var18.getVisibility() != 8 && var18.mListDimensionBehaviors[var2] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
               var1.addGreaterThan(var18.mListAnchors[var3 + 1].mSolverVariable, var18.mListAnchors[var3].mSolverVariable, 0, 5);
            }

            var1.addGreaterThan(var18.mListAnchors[var3].mSolverVariable, var0.mListAnchors[var3].mSolverVariable, 0, 6);
         }

         var21 = var18.mListAnchors[var3 + 1].mTarget;
         var16 = var20;
         if (var21 != null) {
            ConstraintWidget var50 = var21.mOwner;
            var16 = var20;
            if (var50.mListAnchors[var3].mTarget != null) {
               if (var50.mListAnchors[var3].mTarget.mOwner != var18) {
                  var16 = var20;
               } else {
                  var16 = var50;
               }
            }
         }

         if (var16 != null) {
            var18 = var16;
         } else {
            var8 = true;
         }
      }
   }
}
