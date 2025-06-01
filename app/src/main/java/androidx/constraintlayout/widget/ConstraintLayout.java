package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.widgets.Analyzer;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.ResolutionAnchor;
import java.util.ArrayList;
import java.util.HashMap;

public class ConstraintLayout extends ViewGroup {
   static final boolean ALLOWS_EMBEDDED = false;
   private static final boolean CACHE_MEASURED_DIMENSION = false;
   private static final boolean DEBUG = false;
   public static final int DESIGN_INFO_ID = 0;
   private static final String TAG = "ConstraintLayout";
   private static final boolean USE_CONSTRAINTS_HELPER = true;
   public static final String VERSION = "ConstraintLayout-1.1.3";
   SparseArray mChildrenByIds = new SparseArray();
   private ArrayList mConstraintHelpers = new ArrayList(4);
   private ConstraintSet mConstraintSet = null;
   private int mConstraintSetId = -1;
   private HashMap mDesignIds = new HashMap();
   private boolean mDirtyHierarchy = true;
   private int mLastMeasureHeight = -1;
   int mLastMeasureHeightMode = 0;
   int mLastMeasureHeightSize = -1;
   private int mLastMeasureWidth = -1;
   int mLastMeasureWidthMode = 0;
   int mLastMeasureWidthSize = -1;
   ConstraintWidgetContainer mLayoutWidget = new ConstraintWidgetContainer();
   private int mMaxHeight = Integer.MAX_VALUE;
   private int mMaxWidth = Integer.MAX_VALUE;
   private Metrics mMetrics;
   private int mMinHeight = 0;
   private int mMinWidth = 0;
   private int mOptimizationLevel = 7;
   private final ArrayList mVariableDimensionsWidgets = new ArrayList(100);

   public ConstraintLayout(Context var1) {
      super(var1);
      this.init((AttributeSet)null);
   }

   public ConstraintLayout(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.init(var2);
   }

   public ConstraintLayout(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.init(var2);
   }

   private final ConstraintWidget getTargetWidget(int var1) {
      if (var1 == 0) {
         return this.mLayoutWidget;
      } else {
         View var3 = (View)this.mChildrenByIds.get(var1);
         View var2 = var3;
         if (var3 == null) {
            var3 = this.findViewById(var1);
            var2 = var3;
            if (var3 != null) {
               var2 = var3;
               if (var3 != this) {
                  var2 = var3;
                  if (var3.getParent() == this) {
                     this.onViewAdded(var3);
                     var2 = var3;
                  }
               }
            }
         }

         if (var2 == this) {
            return this.mLayoutWidget;
         } else {
            ConstraintWidget var4;
            if (var2 == null) {
               var4 = null;
            } else {
               var4 = ((LayoutParams)var2.getLayoutParams()).widget;
            }

            return var4;
         }
      }
   }

   private void init(AttributeSet var1) {
      this.mLayoutWidget.setCompanionWidget(this);
      this.mChildrenByIds.put(this.getId(), this);
      this.mConstraintSet = null;
      if (var1 != null) {
         TypedArray var7 = this.getContext().obtainStyledAttributes(var1, R.styleable.ConstraintLayout_Layout);
         int var3 = var7.getIndexCount();

         for(int var2 = 0; var2 < var3; ++var2) {
            int var4 = var7.getIndex(var2);
            if (var4 == R.styleable.ConstraintLayout_Layout_android_minWidth) {
               this.mMinWidth = var7.getDimensionPixelOffset(var4, this.mMinWidth);
            } else if (var4 == R.styleable.ConstraintLayout_Layout_android_minHeight) {
               this.mMinHeight = var7.getDimensionPixelOffset(var4, this.mMinHeight);
            } else if (var4 == R.styleable.ConstraintLayout_Layout_android_maxWidth) {
               this.mMaxWidth = var7.getDimensionPixelOffset(var4, this.mMaxWidth);
            } else if (var4 == R.styleable.ConstraintLayout_Layout_android_maxHeight) {
               this.mMaxHeight = var7.getDimensionPixelOffset(var4, this.mMaxHeight);
            } else if (var4 == R.styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
               this.mOptimizationLevel = var7.getInt(var4, this.mOptimizationLevel);
            } else if (var4 == R.styleable.ConstraintLayout_Layout_constraintSet) {
               var4 = var7.getResourceId(var4, 0);

               try {
                  ConstraintSet var5 = new ConstraintSet();
                  this.mConstraintSet = var5;
                  var5.load(this.getContext(), var4);
               } catch (Resources.NotFoundException var6) {
                  this.mConstraintSet = null;
               }

               this.mConstraintSetId = var4;
            }
         }

         var7.recycle();
      }

      this.mLayoutWidget.setOptimizationLevel(this.mOptimizationLevel);
   }

   private void internalMeasureChildren(int var1, int var2) {
      int var10 = this.getPaddingTop() + this.getPaddingBottom();
      int var12 = this.getPaddingLeft() + this.getPaddingRight();
      int var11 = this.getChildCount();

      for(int var7 = 0; var7 < var11; ++var7) {
         View var14 = this.getChildAt(var7);
         if (var14.getVisibility() != 8) {
            LayoutParams var16 = (LayoutParams)var14.getLayoutParams();
            ConstraintWidget var17 = var16.widget;
            if (!var16.isGuideline && !var16.isHelper) {
               var17.setVisibility(var14.getVisibility());
               int var8 = var16.width;
               int var9 = var16.height;
               boolean var3;
               if (var16.horizontalDimensionFixed || var16.verticalDimensionFixed || !var16.horizontalDimensionFixed && var16.matchConstraintDefaultWidth == 1 || var16.width == -1 || !var16.verticalDimensionFixed && (var16.matchConstraintDefaultHeight == 1 || var16.height == -1)) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               boolean var4;
               int var5;
               int var6;
               if (var3) {
                  if (var8 == 0) {
                     var5 = getChildMeasureSpec(var1, var12, -2);
                     var3 = true;
                  } else if (var8 == -1) {
                     var5 = getChildMeasureSpec(var1, var12, -1);
                     var3 = false;
                  } else {
                     if (var8 == -2) {
                        var3 = true;
                     } else {
                        var3 = false;
                     }

                     var5 = getChildMeasureSpec(var1, var12, var8);
                  }

                  if (var9 == 0) {
                     var6 = getChildMeasureSpec(var2, var10, -2);
                     var4 = true;
                  } else if (var9 == -1) {
                     var6 = getChildMeasureSpec(var2, var10, -1);
                     var4 = false;
                  } else {
                     if (var9 == -2) {
                        var4 = true;
                     } else {
                        var4 = false;
                     }

                     var6 = getChildMeasureSpec(var2, var10, var9);
                  }

                  var14.measure(var5, var6);
                  Metrics var15 = this.mMetrics;
                  if (var15 != null) {
                     ++var15.measures;
                  }

                  boolean var13;
                  if (var8 == -2) {
                     var13 = true;
                  } else {
                     var13 = false;
                  }

                  var17.setWidthWrapContent(var13);
                  if (var9 == -2) {
                     var13 = true;
                  } else {
                     var13 = false;
                  }

                  var17.setHeightWrapContent(var13);
                  var6 = var14.getMeasuredWidth();
                  var5 = var14.getMeasuredHeight();
               } else {
                  var3 = false;
                  var4 = false;
                  var5 = var9;
                  var6 = var8;
               }

               var17.setWidth(var6);
               var17.setHeight(var5);
               if (var3) {
                  var17.setWrapWidth(var6);
               }

               if (var4) {
                  var17.setWrapHeight(var5);
               }

               if (var16.needsBaseline) {
                  int var18 = var14.getBaseline();
                  if (var18 != -1) {
                     var17.setBaselineDistance(var18);
                  }
               }
            }
         }
      }

   }

   private void internalMeasureDimensions(int var1, int var2) {
      ConstraintLayout var21 = this;
      int var9 = this.getPaddingTop() + this.getPaddingBottom();
      int var15 = this.getPaddingLeft() + this.getPaddingRight();
      int var10 = this.getChildCount();
      int var3 = 0;

      while(true) {
         long var16 = 1L;
         boolean var4;
         int var7;
         int var8;
         boolean var20;
         ConstraintWidget var23;
         View var24;
         LayoutParams var25;
         if (var3 >= var10) {
            var21.mLayoutWidget.solveGraph();

            for(int var11 = 0; var11 < var10; ++var11) {
               var24 = var21.getChildAt(var11);
               if (var24.getVisibility() != 8) {
                  var25 = (LayoutParams)var24.getLayoutParams();
                  var23 = var25.widget;
                  if (!var25.isGuideline && !var25.isHelper) {
                     var23.setVisibility(var24.getVisibility());
                     var7 = var25.width;
                     var8 = var25.height;
                     if (var7 == 0 || var8 == 0) {
                        ResolutionAnchor var34 = var23.getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
                        ResolutionAnchor var27 = var23.getAnchor(ConstraintAnchor.Type.RIGHT).getResolutionNode();
                        if (var23.getAnchor(ConstraintAnchor.Type.LEFT).getTarget() != null && var23.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget() != null) {
                           var4 = true;
                        } else {
                           var4 = false;
                        }

                        ResolutionAnchor var26 = var23.getAnchor(ConstraintAnchor.Type.TOP).getResolutionNode();
                        ResolutionAnchor var28 = var23.getAnchor(ConstraintAnchor.Type.BOTTOM).getResolutionNode();
                        boolean var13;
                        if (var23.getAnchor(ConstraintAnchor.Type.TOP).getTarget() != null && var23.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget() != null) {
                           var13 = true;
                        } else {
                           var13 = false;
                        }

                        if (var7 == 0 && var8 == 0 && var4 && var13) {
                           var16 = 1L;
                        } else {
                           boolean var32;
                           if (var21.mLayoutWidget.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                              var32 = true;
                           } else {
                              var32 = false;
                           }

                           boolean var29;
                           if (var21.mLayoutWidget.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                              var29 = true;
                           } else {
                              var29 = false;
                           }

                           if (!var32) {
                              var23.getResolutionWidth().invalidate();
                           }

                           if (!var29) {
                              var23.getResolutionHeight().invalidate();
                           }

                           boolean var12;
                           int var14;
                           int var31;
                           label266: {
                              if (var7 == 0) {
                                 if (!var32 || !var23.isSpreadWidth() || !var4 || !var34.isResolved() || !var27.isResolved()) {
                                    var31 = getChildMeasureSpec(var1, var15, -2);
                                    var12 = false;
                                    var4 = true;
                                    var14 = var7;
                                    break label266;
                                 }

                                 var7 = (int)(var27.getResolvedValue() - var34.getResolvedValue());
                                 var23.getResolutionWidth().resolve(var7);
                                 var31 = getChildMeasureSpec(var1, var15, var7);
                              } else {
                                 if (var7 != -1) {
                                    if (var7 == -2) {
                                       var4 = true;
                                    } else {
                                       var4 = false;
                                    }

                                    var31 = getChildMeasureSpec(var1, var15, var7);
                                    var14 = var7;
                                    var12 = var32;
                                    break label266;
                                 }

                                 var31 = getChildMeasureSpec(var1, var15, -1);
                              }

                              var4 = false;
                              var12 = var32;
                              var14 = var7;
                           }

                           label199: {
                              if (var8 == 0) {
                                 if (!var29 || !var23.isSpreadHeight() || !var13 || !var26.isResolved() || !var28.isResolved()) {
                                    var7 = getChildMeasureSpec(var2, var9, -2);
                                    var29 = false;
                                    var32 = true;
                                    break label199;
                                 }

                                 var8 = (int)(var28.getResolvedValue() - var26.getResolvedValue());
                                 var23.getResolutionHeight().resolve(var8);
                                 var7 = getChildMeasureSpec(var2, var9, var8);
                              } else {
                                 if (var8 != -1) {
                                    if (var8 == -2) {
                                       var32 = true;
                                    } else {
                                       var32 = false;
                                    }

                                    var7 = getChildMeasureSpec(var2, var9, var8);
                                    break label199;
                                 }

                                 var7 = getChildMeasureSpec(var2, var9, -1);
                              }

                              var32 = false;
                           }

                           var24.measure(var31, var7);
                           Metrics var33 = this.mMetrics;
                           if (var33 != null) {
                              ++var33.measures;
                           }

                           long var18 = 1L;
                           if (var14 == -2) {
                              var20 = true;
                           } else {
                              var20 = false;
                           }

                           var23.setWidthWrapContent(var20);
                           if (var8 == -2) {
                              var20 = true;
                           } else {
                              var20 = false;
                           }

                           var23.setHeightWrapContent(var20);
                           var7 = var24.getMeasuredWidth();
                           var31 = var24.getMeasuredHeight();
                           var23.setWidth(var7);
                           var23.setHeight(var31);
                           if (var4) {
                              var23.setWrapWidth(var7);
                           }

                           if (var32) {
                              var23.setWrapHeight(var31);
                           }

                           if (var12) {
                              var23.getResolutionWidth().resolve(var7);
                           } else {
                              var23.getResolutionWidth().remove();
                           }

                           if (var29) {
                              var23.getResolutionHeight().resolve(var31);
                           } else {
                              var23.getResolutionHeight().remove();
                           }

                           if (var25.needsBaseline) {
                              var3 = var24.getBaseline();
                              var21 = this;
                              if (var3 != -1) {
                                 var23.setBaselineDistance(var3);
                                 var21 = this;
                              }
                           } else {
                              var21 = this;
                           }
                        }
                     }
                  }
               }
            }

            return;
         }

         var24 = var21.getChildAt(var3);
         if (var24.getVisibility() != 8) {
            var25 = (LayoutParams)var24.getLayoutParams();
            var23 = var25.widget;
            if (!var25.isGuideline && !var25.isHelper) {
               var23.setVisibility(var24.getVisibility());
               int var6 = var25.width;
               var7 = var25.height;
               if (var6 != 0 && var7 != 0) {
                  if (var6 == -2) {
                     var4 = true;
                  } else {
                     var4 = false;
                  }

                  var8 = getChildMeasureSpec(var1, var15, var6);
                  boolean var5;
                  if (var7 == -2) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  var24.measure(var8, getChildMeasureSpec(var2, var9, var7));
                  Metrics var22 = var21.mMetrics;
                  if (var22 != null) {
                     ++var22.measures;
                  }

                  if (var6 == -2) {
                     var20 = true;
                  } else {
                     var20 = false;
                  }

                  var23.setWidthWrapContent(var20);
                  if (var7 == -2) {
                     var20 = true;
                  } else {
                     var20 = false;
                  }

                  var23.setHeightWrapContent(var20);
                  var6 = var24.getMeasuredWidth();
                  var7 = var24.getMeasuredHeight();
                  var23.setWidth(var6);
                  var23.setHeight(var7);
                  if (var4) {
                     var23.setWrapWidth(var6);
                  }

                  if (var5) {
                     var23.setWrapHeight(var7);
                  }

                  if (var25.needsBaseline) {
                     int var30 = var24.getBaseline();
                     if (var30 != -1) {
                        var23.setBaselineDistance(var30);
                     }
                  }

                  if (var25.horizontalDimensionFixed && var25.verticalDimensionFixed) {
                     var23.getResolutionWidth().resolve(var6);
                     var23.getResolutionHeight().resolve(var7);
                  }
               } else {
                  var23.getResolutionWidth().invalidate();
                  var23.getResolutionHeight().invalidate();
               }
            }
         }

         ++var3;
      }
   }

   private void setChildrenConstraints() {
      boolean var12 = this.isInEditMode();
      int var11 = this.getChildCount();
      int var3 = 0;
      int var2;
      int var4;
      View var15;
      if (var12) {
         for(var2 = 0; var2 < var11; ++var2) {
            var15 = this.getChildAt(var2);

            String var14;
            boolean var10001;
            try {
               var14 = this.getResources().getResourceName(var15.getId());
               this.setDesignInformation(0, var14, var15.getId());
               var4 = var14.indexOf(47);
            } catch (Resources.NotFoundException var20) {
               var10001 = false;
               continue;
            }

            String var13 = var14;
            if (var4 != -1) {
               try {
                  var13 = var14.substring(var4 + 1);
               } catch (Resources.NotFoundException var19) {
                  var10001 = false;
                  continue;
               }
            }

            try {
               this.getTargetWidget(var15.getId()).setDebugName(var13);
            } catch (Resources.NotFoundException var18) {
               var10001 = false;
            }
         }
      }

      for(var2 = 0; var2 < var11; ++var2) {
         ConstraintWidget var23 = this.getViewWidget(this.getChildAt(var2));
         if (var23 != null) {
            var23.reset();
         }
      }

      View var24;
      if (this.mConstraintSetId != -1) {
         for(var2 = 0; var2 < var11; ++var2) {
            var24 = this.getChildAt(var2);
            if (var24.getId() == this.mConstraintSetId && var24 instanceof Constraints) {
               this.mConstraintSet = ((Constraints)var24).getConstraintSet();
            }
         }
      }

      ConstraintSet var26 = this.mConstraintSet;
      if (var26 != null) {
         var26.applyToInternal(this);
      }

      this.mLayoutWidget.removeAllChildren();
      var4 = this.mConstraintHelpers.size();
      if (var4 > 0) {
         for(var2 = 0; var2 < var4; ++var2) {
            ((ConstraintHelper)this.mConstraintHelpers.get(var2)).updatePreLayout(this);
         }
      }

      for(var2 = 0; var2 < var11; ++var2) {
         var24 = this.getChildAt(var2);
         if (var24 instanceof Placeholder) {
            ((Placeholder)var24).updatePreLayout(this);
         }
      }

      int var5 = 0;

      byte var21;
      for(byte var22 = (byte)var3; var5 < var11; var22 = var21) {
         var15 = this.getChildAt(var5);
         ConstraintWidget var25 = this.getViewWidget(var15);
         if (var25 == null) {
            var21 = var22;
         } else {
            LayoutParams var28 = (LayoutParams)var15.getLayoutParams();
            var28.validate();
            if (var28.helped) {
               var28.helped = (boolean)var22;
            } else if (var12) {
               try {
                  String var16 = this.getResources().getResourceName(var15.getId());
                  this.setDesignInformation(var22, var16, var15.getId());
                  var16 = var16.substring(var16.indexOf("id/") + 3);
                  this.getTargetWidget(var15.getId()).setDebugName(var16);
               } catch (Resources.NotFoundException var17) {
               }
            }

            var25.setVisibility(var15.getVisibility());
            if (var28.isInPlaceholder) {
               var25.setVisibility(8);
            }

            var25.setCompanionWidget(var15);
            this.mLayoutWidget.add(var25);
            if (!var28.verticalDimensionFixed || !var28.horizontalDimensionFixed) {
               this.mVariableDimensionsWidgets.add(var25);
            }

            float var1;
            if (var28.isGuideline) {
               androidx.constraintlayout.solver.widgets.Guideline var27 = (androidx.constraintlayout.solver.widgets.Guideline)var25;
               var4 = var28.resolvedGuideBegin;
               var3 = var28.resolvedGuideEnd;
               var1 = var28.resolvedGuidePercent;
               if (android.os.Build.VERSION.SDK_INT < 17) {
                  var4 = var28.guideBegin;
                  var3 = var28.guideEnd;
                  var1 = var28.guidePercent;
               }

               if (var1 != -1.0F) {
                  var27.setGuidePercent(var1);
                  var21 = var22;
               } else if (var4 != -1) {
                  var27.setGuideBegin(var4);
                  var21 = var22;
               } else {
                  var21 = var22;
                  if (var3 != -1) {
                     var27.setGuideEnd(var3);
                     var21 = var22;
                  }
               }
            } else {
               label331: {
                  if (var28.leftToLeft == -1 && var28.leftToRight == -1 && var28.rightToLeft == -1 && var28.rightToRight == -1 && var28.startToStart == -1 && var28.startToEnd == -1 && var28.endToStart == -1 && var28.endToEnd == -1 && var28.topToTop == -1 && var28.topToBottom == -1 && var28.bottomToTop == -1 && var28.bottomToBottom == -1 && var28.baselineToBaseline == -1 && var28.editorAbsoluteX == -1 && var28.editorAbsoluteY == -1 && var28.circleConstraint == -1 && var28.width != -1) {
                     var21 = var22;
                     if (var28.height != -1) {
                        break label331;
                     }
                  }

                  int var6 = var28.resolvedLeftToLeft;
                  var4 = var28.resolvedLeftToRight;
                  var2 = var28.resolvedRightToLeft;
                  var3 = var28.resolvedRightToRight;
                  int var8 = var28.resolveGoneLeftMargin;
                  int var7 = var28.resolveGoneRightMargin;
                  var1 = var28.resolvedHorizontalBias;
                  int var9;
                  if (android.os.Build.VERSION.SDK_INT < 17) {
                     var6 = var28.leftToLeft;
                     var4 = var28.leftToRight;
                     int var10 = var28.rightToLeft;
                     var9 = var28.rightToRight;
                     var8 = var28.goneLeftMargin;
                     var7 = var28.goneRightMargin;
                     var1 = var28.horizontalBias;
                     var2 = var6;
                     var3 = var4;
                     if (var6 == -1) {
                        var2 = var6;
                        var3 = var4;
                        if (var4 == -1) {
                           if (var28.startToStart != -1) {
                              var2 = var28.startToStart;
                              var3 = var4;
                           } else {
                              var2 = var6;
                              var3 = var4;
                              if (var28.startToEnd != -1) {
                                 var3 = var28.startToEnd;
                                 var2 = var6;
                              }
                           }
                        }
                     }

                     var6 = var2;
                     var4 = var10;
                     var2 = var9;
                     if (var10 == -1) {
                        var4 = var10;
                        var2 = var9;
                        if (var9 == -1) {
                           if (var28.endToStart != -1) {
                              var4 = var28.endToStart;
                              var2 = var9;
                           } else {
                              var4 = var10;
                              var2 = var9;
                              if (var28.endToEnd != -1) {
                                 var2 = var28.endToEnd;
                                 var4 = var10;
                              }
                           }
                        }
                     }

                     var9 = var4;
                     var4 = var3;
                     var3 = var2;
                  } else {
                     var9 = var2;
                  }

                  ConstraintWidget var29;
                  if (var28.circleConstraint != -1) {
                     var29 = this.getTargetWidget(var28.circleConstraint);
                     if (var29 != null) {
                        var25.connectCircularConstraint(var29, var28.circleAngle, var28.circleRadius);
                     }
                  } else {
                     if (var6 != -1) {
                        var29 = this.getTargetWidget(var6);
                        if (var29 != null) {
                           var25.immediateConnect(ConstraintAnchor.Type.LEFT, var29, ConstraintAnchor.Type.LEFT, var28.leftMargin, var8);
                        }
                     } else if (var4 != -1) {
                        var29 = this.getTargetWidget(var4);
                        if (var29 != null) {
                           var25.immediateConnect(ConstraintAnchor.Type.LEFT, var29, ConstraintAnchor.Type.RIGHT, var28.leftMargin, var8);
                        }
                     }

                     if (var9 != -1) {
                        var29 = this.getTargetWidget(var9);
                        if (var29 != null) {
                           var25.immediateConnect(ConstraintAnchor.Type.RIGHT, var29, ConstraintAnchor.Type.LEFT, var28.rightMargin, var7);
                        }
                     } else if (var3 != -1) {
                        var29 = this.getTargetWidget(var3);
                        if (var29 != null) {
                           var25.immediateConnect(ConstraintAnchor.Type.RIGHT, var29, ConstraintAnchor.Type.RIGHT, var28.rightMargin, var7);
                        }
                     }

                     if (var28.topToTop != -1) {
                        var29 = this.getTargetWidget(var28.topToTop);
                        if (var29 != null) {
                           var25.immediateConnect(ConstraintAnchor.Type.TOP, var29, ConstraintAnchor.Type.TOP, var28.topMargin, var28.goneTopMargin);
                        }
                     } else if (var28.topToBottom != -1) {
                        var29 = this.getTargetWidget(var28.topToBottom);
                        if (var29 != null) {
                           var25.immediateConnect(ConstraintAnchor.Type.TOP, var29, ConstraintAnchor.Type.BOTTOM, var28.topMargin, var28.goneTopMargin);
                        }
                     }

                     if (var28.bottomToTop != -1) {
                        var29 = this.getTargetWidget(var28.bottomToTop);
                        if (var29 != null) {
                           var25.immediateConnect(ConstraintAnchor.Type.BOTTOM, var29, ConstraintAnchor.Type.TOP, var28.bottomMargin, var28.goneBottomMargin);
                        }
                     } else if (var28.bottomToBottom != -1) {
                        var29 = this.getTargetWidget(var28.bottomToBottom);
                        if (var29 != null) {
                           var25.immediateConnect(ConstraintAnchor.Type.BOTTOM, var29, ConstraintAnchor.Type.BOTTOM, var28.bottomMargin, var28.goneBottomMargin);
                        }
                     }

                     if (var28.baselineToBaseline != -1) {
                        View var30 = (View)this.mChildrenByIds.get(var28.baselineToBaseline);
                        var29 = this.getTargetWidget(var28.baselineToBaseline);
                        if (var29 != null && var30 != null && var30.getLayoutParams() instanceof LayoutParams) {
                           LayoutParams var31 = (LayoutParams)var30.getLayoutParams();
                           var28.needsBaseline = true;
                           var31.needsBaseline = true;
                           var25.getAnchor(ConstraintAnchor.Type.BASELINE).connect(var29.getAnchor(ConstraintAnchor.Type.BASELINE), 0, -1, ConstraintAnchor.Strength.STRONG, 0, true);
                           var25.getAnchor(ConstraintAnchor.Type.TOP).reset();
                           var25.getAnchor(ConstraintAnchor.Type.BOTTOM).reset();
                        }
                     }

                     if (var1 >= 0.0F && var1 != 0.5F) {
                        var25.setHorizontalBiasPercent(var1);
                     }

                     if (var28.verticalBias >= 0.0F && var28.verticalBias != 0.5F) {
                        var25.setVerticalBiasPercent(var28.verticalBias);
                     }
                  }

                  if (var12 && (var28.editorAbsoluteX != -1 || var28.editorAbsoluteY != -1)) {
                     var25.setOrigin(var28.editorAbsoluteX, var28.editorAbsoluteY);
                  }

                  if (!var28.horizontalDimensionFixed) {
                     if (var28.width == -1) {
                        var25.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                        var25.getAnchor(ConstraintAnchor.Type.LEFT).mMargin = var28.leftMargin;
                        var25.getAnchor(ConstraintAnchor.Type.RIGHT).mMargin = var28.rightMargin;
                     } else {
                        var25.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                        var25.setWidth(0);
                     }
                  } else {
                     var25.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                     var25.setWidth(var28.width);
                  }

                  if (!var28.verticalDimensionFixed) {
                     if (var28.height == -1) {
                        var25.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                        var25.getAnchor(ConstraintAnchor.Type.TOP).mMargin = var28.topMargin;
                        var25.getAnchor(ConstraintAnchor.Type.BOTTOM).mMargin = var28.bottomMargin;
                     } else {
                        var25.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                        var25.setHeight(0);
                     }
                  } else {
                     var25.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                     var25.setHeight(var28.height);
                  }

                  var21 = 0;
                  if (var28.dimensionRatio != null) {
                     var25.setDimensionRatio(var28.dimensionRatio);
                  }

                  var25.setHorizontalWeight(var28.horizontalWeight);
                  var25.setVerticalWeight(var28.verticalWeight);
                  var25.setHorizontalChainStyle(var28.horizontalChainStyle);
                  var25.setVerticalChainStyle(var28.verticalChainStyle);
                  var25.setHorizontalMatchStyle(var28.matchConstraintDefaultWidth, var28.matchConstraintMinWidth, var28.matchConstraintMaxWidth, var28.matchConstraintPercentWidth);
                  var25.setVerticalMatchStyle(var28.matchConstraintDefaultHeight, var28.matchConstraintMinHeight, var28.matchConstraintMaxHeight, var28.matchConstraintPercentHeight);
               }
            }
         }

         ++var5;
      }

   }

   private void setSelfDimensionBehaviour(int var1, int var2) {
      int var6 = MeasureSpec.getMode(var1);
      var1 = MeasureSpec.getSize(var1);
      int var3 = MeasureSpec.getMode(var2);
      var2 = MeasureSpec.getSize(var2);
      int var4 = this.getPaddingTop();
      int var5 = this.getPaddingBottom();
      int var8 = this.getPaddingLeft();
      int var7 = this.getPaddingRight();
      ConstraintWidget.DimensionBehaviour var9 = ConstraintWidget.DimensionBehaviour.FIXED;
      ConstraintWidget.DimensionBehaviour var10 = ConstraintWidget.DimensionBehaviour.FIXED;
      this.getLayoutParams();
      if (var6 != Integer.MIN_VALUE) {
         label28: {
            if (var6 != 0) {
               if (var6 == 1073741824) {
                  var1 = Math.min(this.mMaxWidth, var1) - (var8 + var7);
                  break label28;
               }
            } else {
               var9 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            }

            var1 = 0;
         }
      } else {
         var9 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
      }

      if (var3 != Integer.MIN_VALUE) {
         label22: {
            if (var3 != 0) {
               if (var3 == 1073741824) {
                  var2 = Math.min(this.mMaxHeight, var2) - (var4 + var5);
                  break label22;
               }
            } else {
               var10 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            }

            var2 = 0;
         }
      } else {
         var10 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
      }

      this.mLayoutWidget.setMinWidth(0);
      this.mLayoutWidget.setMinHeight(0);
      this.mLayoutWidget.setHorizontalDimensionBehaviour(var9);
      this.mLayoutWidget.setWidth(var1);
      this.mLayoutWidget.setVerticalDimensionBehaviour(var10);
      this.mLayoutWidget.setHeight(var2);
      this.mLayoutWidget.setMinWidth(this.mMinWidth - this.getPaddingLeft() - this.getPaddingRight());
      this.mLayoutWidget.setMinHeight(this.mMinHeight - this.getPaddingTop() - this.getPaddingBottom());
   }

   private void updateHierarchy() {
      int var4 = this.getChildCount();
      boolean var3 = false;
      int var2 = 0;

      boolean var1;
      while(true) {
         var1 = var3;
         if (var2 >= var4) {
            break;
         }

         if (this.getChildAt(var2).isLayoutRequested()) {
            var1 = true;
            break;
         }

         ++var2;
      }

      if (var1) {
         this.mVariableDimensionsWidgets.clear();
         this.setChildrenConstraints();
      }

   }

   private void updatePostMeasures() {
      int var3 = this.getChildCount();
      byte var2 = 0;

      int var1;
      for(var1 = 0; var1 < var3; ++var1) {
         View var4 = this.getChildAt(var1);
         if (var4 instanceof Placeholder) {
            ((Placeholder)var4).updatePostMeasure(this);
         }
      }

      var3 = this.mConstraintHelpers.size();
      if (var3 > 0) {
         for(var1 = var2; var1 < var3; ++var1) {
            ((ConstraintHelper)this.mConstraintHelpers.get(var1)).updatePostMeasure(this);
         }
      }

   }

   public void addView(View var1, int var2, ViewGroup.LayoutParams var3) {
      super.addView(var1, var2, var3);
      if (android.os.Build.VERSION.SDK_INT < 14) {
         this.onViewAdded(var1);
      }

   }

   protected boolean checkLayoutParams(ViewGroup.LayoutParams var1) {
      return var1 instanceof LayoutParams;
   }

   public void dispatchDraw(Canvas var1) {
      super.dispatchDraw(var1);
      if (this.isInEditMode()) {
         int var9 = this.getChildCount();
         float var2 = (float)this.getWidth();
         float var6 = (float)this.getHeight();

         for(int var8 = 0; var8 < var9; ++var8) {
            View var14 = this.getChildAt(var8);
            if (var14.getVisibility() != 8) {
               Object var15 = var14.getTag();
               if (var15 != null && var15 instanceof String) {
                  String[] var16 = ((String)var15).split(",");
                  if (var16.length == 4) {
                     int var11 = Integer.parseInt(var16[0]);
                     int var12 = Integer.parseInt(var16[1]);
                     int var13 = Integer.parseInt(var16[2]);
                     int var10 = Integer.parseInt(var16[3]);
                     var11 = (int)((float)var11 / 1080.0F * var2);
                     var12 = (int)((float)var12 / 1920.0F * var6);
                     var13 = (int)((float)var13 / 1080.0F * var2);
                     var10 = (int)((float)var10 / 1920.0F * var6);
                     Paint var17 = new Paint();
                     var17.setColor(-65536);
                     float var4 = (float)var11;
                     float var5 = (float)var12;
                     float var7 = (float)(var11 + var13);
                     var1.drawLine(var4, var5, var7, var5, var17);
                     float var3 = (float)(var12 + var10);
                     var1.drawLine(var7, var5, var7, var3, var17);
                     var1.drawLine(var7, var3, var4, var3, var17);
                     var1.drawLine(var4, var3, var4, var5, var17);
                     var17.setColor(-16711936);
                     var1.drawLine(var4, var5, var7, var3, var17);
                     var1.drawLine(var4, var3, var7, var5, var17);
                  }
               }
            }
         }
      }

   }

   public void fillMetrics(Metrics var1) {
      this.mMetrics = var1;
      this.mLayoutWidget.fillMetrics(var1);
   }

   protected LayoutParams generateDefaultLayoutParams() {
      return new LayoutParams(-2, -2);
   }

   protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams var1) {
      return new LayoutParams(var1);
   }

   public LayoutParams generateLayoutParams(AttributeSet var1) {
      return new LayoutParams(this.getContext(), var1);
   }

   public Object getDesignInformation(int var1, Object var2) {
      if (var1 == 0 && var2 instanceof String) {
         String var4 = (String)var2;
         HashMap var3 = this.mDesignIds;
         if (var3 != null && var3.containsKey(var4)) {
            return this.mDesignIds.get(var4);
         }
      }

      return null;
   }

   public int getMaxHeight() {
      return this.mMaxHeight;
   }

   public int getMaxWidth() {
      return this.mMaxWidth;
   }

   public int getMinHeight() {
      return this.mMinHeight;
   }

   public int getMinWidth() {
      return this.mMinWidth;
   }

   public int getOptimizationLevel() {
      return this.mLayoutWidget.getOptimizationLevel();
   }

   public View getViewById(int var1) {
      return (View)this.mChildrenByIds.get(var1);
   }

   public final ConstraintWidget getViewWidget(View var1) {
      if (var1 == this) {
         return this.mLayoutWidget;
      } else {
         ConstraintWidget var2;
         if (var1 == null) {
            var2 = null;
         } else {
            var2 = ((LayoutParams)var1.getLayoutParams()).widget;
         }

         return var2;
      }
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      var4 = this.getChildCount();
      var1 = this.isInEditMode();
      byte var12 = 0;

      for(var2 = 0; var2 < var4; ++var2) {
         View var9 = this.getChildAt(var2);
         LayoutParams var10 = (LayoutParams)var9.getLayoutParams();
         ConstraintWidget var11 = var10.widget;
         if ((var9.getVisibility() != 8 || var10.isGuideline || var10.isHelper || var1) && !var10.isInPlaceholder) {
            int var8 = var11.getDrawX();
            var5 = var11.getDrawY();
            int var6 = var11.getWidth() + var8;
            int var7 = var11.getHeight() + var5;
            var9.layout(var8, var5, var6, var7);
            if (var9 instanceof Placeholder) {
               var9 = ((Placeholder)var9).getContent();
               if (var9 != null) {
                  var9.setVisibility(0);
                  var9.layout(var8, var5, var6, var7);
               }
            }
         }
      }

      var4 = this.mConstraintHelpers.size();
      if (var4 > 0) {
         for(var2 = var12; var2 < var4; ++var2) {
            ((ConstraintHelper)this.mConstraintHelpers.get(var2)).updatePostLayout(this);
         }
      }

   }

   protected void onMeasure(int var1, int var2) {
      System.currentTimeMillis();
      int var8 = MeasureSpec.getMode(var1);
      int var10 = MeasureSpec.getSize(var1);
      int var6 = MeasureSpec.getMode(var2);
      int var7 = MeasureSpec.getSize(var2);
      int var4 = this.getPaddingLeft();
      int var5 = this.getPaddingTop();
      this.mLayoutWidget.setX(var4);
      this.mLayoutWidget.setY(var5);
      this.mLayoutWidget.setMaxWidth(this.mMaxWidth);
      this.mLayoutWidget.setMaxHeight(this.mMaxHeight);
      ConstraintWidgetContainer var20;
      if (android.os.Build.VERSION.SDK_INT >= 17) {
         var20 = this.mLayoutWidget;
         boolean var19;
         if (this.getLayoutDirection() == 1) {
            var19 = true;
         } else {
            var19 = false;
         }

         var20.setRtl(var19);
      }

      this.setSelfDimensionBehaviour(var1, var2);
      int var14 = this.mLayoutWidget.getWidth();
      int var13 = this.mLayoutWidget.getHeight();
      boolean var3;
      if (this.mDirtyHierarchy) {
         this.mDirtyHierarchy = false;
         this.updateHierarchy();
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var9;
      if ((this.mOptimizationLevel & 8) == 8) {
         var9 = true;
      } else {
         var9 = false;
      }

      if (var9) {
         this.mLayoutWidget.preOptimize();
         this.mLayoutWidget.optimizeForDimensions(var14, var13);
         this.internalMeasureDimensions(var1, var2);
      } else {
         this.internalMeasureChildren(var1, var2);
      }

      this.updatePostMeasures();
      if (this.getChildCount() > 0 && var3) {
         Analyzer.determineGroups(this.mLayoutWidget);
      }

      if (this.mLayoutWidget.mGroupsWrapOptimized) {
         if (this.mLayoutWidget.mHorizontalWrapOptimized && var8 == Integer.MIN_VALUE) {
            if (this.mLayoutWidget.mWrapFixedWidth < var10) {
               var20 = this.mLayoutWidget;
               var20.setWidth(var20.mWrapFixedWidth);
            }

            this.mLayoutWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
         }

         if (this.mLayoutWidget.mVerticalWrapOptimized && var6 == Integer.MIN_VALUE) {
            if (this.mLayoutWidget.mWrapFixedHeight < var7) {
               var20 = this.mLayoutWidget;
               var20.setHeight(var20.mWrapFixedHeight);
            }

            this.mLayoutWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
         }
      }

      int var24;
      if ((this.mOptimizationLevel & 32) == 32) {
         var24 = this.mLayoutWidget.getWidth();
         int var11 = this.mLayoutWidget.getHeight();
         if (this.mLastMeasureWidth != var24 && var8 == 1073741824) {
            Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 0, var24);
         }

         if (this.mLastMeasureHeight != var11 && var6 == 1073741824) {
            Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 1, var11);
         }

         if (this.mLayoutWidget.mHorizontalWrapOptimized && this.mLayoutWidget.mWrapFixedWidth > var10) {
            Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 0, var10);
         }

         if (this.mLayoutWidget.mVerticalWrapOptimized && this.mLayoutWidget.mWrapFixedHeight > var7) {
            Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 1, var7);
         }
      }

      if (this.getChildCount() > 0) {
         this.solveLinearSystem("First pass");
      }

      int var12 = this.mVariableDimensionsWidgets.size();
      var7 = var5 + this.getPaddingBottom();
      int var17 = var4 + this.getPaddingRight();
      if (var12 > 0) {
         boolean var29;
         if (this.mLayoutWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
            var29 = true;
         } else {
            var29 = false;
         }

         boolean var30;
         if (this.mLayoutWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
            var30 = true;
         } else {
            var30 = false;
         }

         var5 = Math.max(this.mLayoutWidget.getWidth(), this.mMinWidth);
         var4 = Math.max(this.mLayoutWidget.getHeight(), this.mMinHeight);
         int var15 = 0;
         boolean var26 = false;

         View var21;
         boolean var27;
         for(var24 = 0; var15 < var12; ++var15) {
            ConstraintWidget var22 = (ConstraintWidget)this.mVariableDimensionsWidgets.get(var15);
            var21 = (View)var22.getCompanionWidget();
            if (var21 != null) {
               LayoutParams var31 = (LayoutParams)var21.getLayoutParams();
               if (!var31.isHelper && !var31.isGuideline) {
                  int var16 = var21.getVisibility();
                  boolean var28 = var26;
                  if (var16 != 8 && (!var9 || !var22.getResolutionWidth().isResolved() || !var22.getResolutionHeight().isResolved())) {
                     if (var31.width == -2 && var31.horizontalDimensionFixed) {
                        var6 = getChildMeasureSpec(var1, var17, var31.width);
                     } else {
                        var6 = MeasureSpec.makeMeasureSpec(var22.getWidth(), 1073741824);
                     }

                     if (var31.height == -2 && var31.verticalDimensionFixed) {
                        var16 = getChildMeasureSpec(var2, var7, var31.height);
                     } else {
                        var16 = MeasureSpec.makeMeasureSpec(var22.getHeight(), 1073741824);
                     }

                     var21.measure(var6, var16);
                     Metrics var23 = this.mMetrics;
                     if (var23 != null) {
                        ++var23.additionalMeasures;
                     }

                     int var18 = var21.getMeasuredWidth();
                     var16 = var21.getMeasuredHeight();
                     var6 = var5;
                     if (var18 != var22.getWidth()) {
                        var22.setWidth(var18);
                        if (var9) {
                           var22.getResolutionWidth().resolve(var18);
                        }

                        var6 = var5;
                        if (var29) {
                           var6 = var5;
                           if (var22.getRight() > var5) {
                              var6 = Math.max(var5, var22.getRight() + var22.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
                           }
                        }

                        var28 = true;
                     }

                     if (var16 != var22.getHeight()) {
                        var22.setHeight(var16);
                        if (var9) {
                           var22.getResolutionHeight().resolve(var16);
                        }

                        var5 = var4;
                        if (var30) {
                           var5 = var4;
                           if (var22.getBottom() > var4) {
                              var5 = Math.max(var4, var22.getBottom() + var22.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
                           }
                        }

                        var4 = var5;
                        var28 = true;
                     }

                     var27 = var28;
                     if (var31.needsBaseline) {
                        var16 = var21.getBaseline();
                        var27 = var28;
                        if (var16 != -1) {
                           var27 = var28;
                           if (var16 != var22.getBaselineDistance()) {
                              var22.setBaselineDistance(var16);
                              var27 = true;
                           }
                        }
                     }

                     if (android.os.Build.VERSION.SDK_INT >= 11) {
                        var24 = combineMeasuredStates(var24, var21.getMeasuredState());
                     }

                     var5 = var6;
                     var26 = var27;
                  }
               }
            }
         }

         if (var26) {
            this.mLayoutWidget.setWidth(var14);
            this.mLayoutWidget.setHeight(var13);
            if (var9) {
               this.mLayoutWidget.solveGraph();
            }

            this.solveLinearSystem("2nd pass");
            if (this.mLayoutWidget.getWidth() < var5) {
               this.mLayoutWidget.setWidth(var5);
               var27 = true;
            } else {
               var27 = false;
            }

            boolean var25;
            if (this.mLayoutWidget.getHeight() < var4) {
               this.mLayoutWidget.setHeight(var4);
               var25 = true;
            } else {
               var25 = var27;
            }

            if (var25) {
               this.solveLinearSystem("3rd pass");
            }
         }

         for(var4 = 0; var4 < var12; ++var4) {
            ConstraintWidget var32 = (ConstraintWidget)this.mVariableDimensionsWidgets.get(var4);
            var21 = (View)var32.getCompanionWidget();
            if (var21 != null && (var21.getMeasuredWidth() != var32.getWidth() || var21.getMeasuredHeight() != var32.getHeight()) && var32.getVisibility() != 8) {
               var21.measure(MeasureSpec.makeMeasureSpec(var32.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(var32.getHeight(), 1073741824));
               Metrics var33 = this.mMetrics;
               if (var33 != null) {
                  ++var33.additionalMeasures;
               }
            }
         }
      } else {
         var24 = 0;
      }

      var5 = this.mLayoutWidget.getWidth() + var17;
      var4 = this.mLayoutWidget.getHeight() + var7;
      if (android.os.Build.VERSION.SDK_INT >= 11) {
         var1 = resolveSizeAndState(var5, var1, var24);
         var24 = resolveSizeAndState(var4, var2, var24 << 16);
         var2 = Math.min(this.mMaxWidth, var1 & 16777215);
         var24 = Math.min(this.mMaxHeight, var24 & 16777215);
         var1 = var2;
         if (this.mLayoutWidget.isWidthMeasuredTooSmall()) {
            var1 = var2 | 16777216;
         }

         var2 = var24;
         if (this.mLayoutWidget.isHeightMeasuredTooSmall()) {
            var2 = var24 | 16777216;
         }

         this.setMeasuredDimension(var1, var2);
         this.mLastMeasureWidth = var1;
         this.mLastMeasureHeight = var2;
      } else {
         this.setMeasuredDimension(var5, var4);
         this.mLastMeasureWidth = var5;
         this.mLastMeasureHeight = var4;
      }

   }

   public void onViewAdded(View var1) {
      if (android.os.Build.VERSION.SDK_INT >= 14) {
         super.onViewAdded(var1);
      }

      ConstraintWidget var2 = this.getViewWidget(var1);
      if (var1 instanceof Guideline && !(var2 instanceof androidx.constraintlayout.solver.widgets.Guideline)) {
         LayoutParams var3 = (LayoutParams)var1.getLayoutParams();
         var3.widget = new androidx.constraintlayout.solver.widgets.Guideline();
         var3.isGuideline = true;
         ((androidx.constraintlayout.solver.widgets.Guideline)var3.widget).setOrientation(var3.orientation);
      }

      if (var1 instanceof ConstraintHelper) {
         ConstraintHelper var4 = (ConstraintHelper)var1;
         var4.validateParams();
         ((LayoutParams)var1.getLayoutParams()).isHelper = true;
         if (!this.mConstraintHelpers.contains(var4)) {
            this.mConstraintHelpers.add(var4);
         }
      }

      this.mChildrenByIds.put(var1.getId(), var1);
      this.mDirtyHierarchy = true;
   }

   public void onViewRemoved(View var1) {
      if (android.os.Build.VERSION.SDK_INT >= 14) {
         super.onViewRemoved(var1);
      }

      this.mChildrenByIds.remove(var1.getId());
      ConstraintWidget var2 = this.getViewWidget(var1);
      this.mLayoutWidget.remove(var2);
      this.mConstraintHelpers.remove(var1);
      this.mVariableDimensionsWidgets.remove(var2);
      this.mDirtyHierarchy = true;
   }

   public void removeView(View var1) {
      super.removeView(var1);
      if (android.os.Build.VERSION.SDK_INT < 14) {
         this.onViewRemoved(var1);
      }

   }

   public void requestLayout() {
      super.requestLayout();
      this.mDirtyHierarchy = true;
      this.mLastMeasureWidth = -1;
      this.mLastMeasureHeight = -1;
      this.mLastMeasureWidthSize = -1;
      this.mLastMeasureHeightSize = -1;
      this.mLastMeasureWidthMode = 0;
      this.mLastMeasureHeightMode = 0;
   }

   public void setConstraintSet(ConstraintSet var1) {
      this.mConstraintSet = var1;
   }

   public void setDesignInformation(int var1, Object var2, Object var3) {
      if (var1 == 0 && var2 instanceof String && var3 instanceof Integer) {
         if (this.mDesignIds == null) {
            this.mDesignIds = new HashMap();
         }

         String var4 = (String)var2;
         var1 = var4.indexOf("/");
         String var5 = var4;
         if (var1 != -1) {
            var5 = var4.substring(var1 + 1);
         }

         var1 = (Integer)var3;
         this.mDesignIds.put(var5, var1);
      }

   }

   public void setId(int var1) {
      this.mChildrenByIds.remove(this.getId());
      super.setId(var1);
      this.mChildrenByIds.put(this.getId(), this);
   }

   public void setMaxHeight(int var1) {
      if (var1 != this.mMaxHeight) {
         this.mMaxHeight = var1;
         this.requestLayout();
      }
   }

   public void setMaxWidth(int var1) {
      if (var1 != this.mMaxWidth) {
         this.mMaxWidth = var1;
         this.requestLayout();
      }
   }

   public void setMinHeight(int var1) {
      if (var1 != this.mMinHeight) {
         this.mMinHeight = var1;
         this.requestLayout();
      }
   }

   public void setMinWidth(int var1) {
      if (var1 != this.mMinWidth) {
         this.mMinWidth = var1;
         this.requestLayout();
      }
   }

   public void setOptimizationLevel(int var1) {
      this.mLayoutWidget.setOptimizationLevel(var1);
   }

   public boolean shouldDelayChildPressedState() {
      return false;
   }

   protected void solveLinearSystem(String var1) {
      this.mLayoutWidget.layout();
      Metrics var2 = this.mMetrics;
      if (var2 != null) {
         ++var2.resolutions;
      }

   }

   public static class LayoutParams extends ViewGroup.MarginLayoutParams {
      public static final int BASELINE = 5;
      public static final int BOTTOM = 4;
      public static final int CHAIN_PACKED = 2;
      public static final int CHAIN_SPREAD = 0;
      public static final int CHAIN_SPREAD_INSIDE = 1;
      public static final int END = 7;
      public static final int HORIZONTAL = 0;
      public static final int LEFT = 1;
      public static final int MATCH_CONSTRAINT = 0;
      public static final int MATCH_CONSTRAINT_PERCENT = 2;
      public static final int MATCH_CONSTRAINT_SPREAD = 0;
      public static final int MATCH_CONSTRAINT_WRAP = 1;
      public static final int PARENT_ID = 0;
      public static final int RIGHT = 2;
      public static final int START = 6;
      public static final int TOP = 3;
      public static final int UNSET = -1;
      public static final int VERTICAL = 1;
      public int baselineToBaseline = -1;
      public int bottomToBottom = -1;
      public int bottomToTop = -1;
      public float circleAngle = 0.0F;
      public int circleConstraint = -1;
      public int circleRadius = 0;
      public boolean constrainedHeight = false;
      public boolean constrainedWidth = false;
      public String dimensionRatio = null;
      int dimensionRatioSide = 1;
      float dimensionRatioValue = 0.0F;
      public int editorAbsoluteX = -1;
      public int editorAbsoluteY = -1;
      public int endToEnd = -1;
      public int endToStart = -1;
      public int goneBottomMargin = -1;
      public int goneEndMargin = -1;
      public int goneLeftMargin = -1;
      public int goneRightMargin = -1;
      public int goneStartMargin = -1;
      public int goneTopMargin = -1;
      public int guideBegin = -1;
      public int guideEnd = -1;
      public float guidePercent = -1.0F;
      public boolean helped = false;
      public float horizontalBias = 0.5F;
      public int horizontalChainStyle = 0;
      boolean horizontalDimensionFixed = true;
      public float horizontalWeight = -1.0F;
      boolean isGuideline = false;
      boolean isHelper = false;
      boolean isInPlaceholder = false;
      public int leftToLeft = -1;
      public int leftToRight = -1;
      public int matchConstraintDefaultHeight = 0;
      public int matchConstraintDefaultWidth = 0;
      public int matchConstraintMaxHeight = 0;
      public int matchConstraintMaxWidth = 0;
      public int matchConstraintMinHeight = 0;
      public int matchConstraintMinWidth = 0;
      public float matchConstraintPercentHeight = 1.0F;
      public float matchConstraintPercentWidth = 1.0F;
      boolean needsBaseline = false;
      public int orientation = -1;
      int resolveGoneLeftMargin = -1;
      int resolveGoneRightMargin = -1;
      int resolvedGuideBegin;
      int resolvedGuideEnd;
      float resolvedGuidePercent;
      float resolvedHorizontalBias = 0.5F;
      int resolvedLeftToLeft = -1;
      int resolvedLeftToRight = -1;
      int resolvedRightToLeft = -1;
      int resolvedRightToRight = -1;
      public int rightToLeft = -1;
      public int rightToRight = -1;
      public int startToEnd = -1;
      public int startToStart = -1;
      public int topToBottom = -1;
      public int topToTop = -1;
      public float verticalBias = 0.5F;
      public int verticalChainStyle = 0;
      boolean verticalDimensionFixed = true;
      public float verticalWeight = -1.0F;
      ConstraintWidget widget = new ConstraintWidget();

      public LayoutParams(int var1, int var2) {
         super(var1, var2);
      }

      public LayoutParams(Context var1, AttributeSet var2) {
         super(var1, var2);
         TypedArray var19 = var1.obtainStyledAttributes(var2, R.styleable.ConstraintLayout_Layout);
         int var7 = var19.getIndexCount();

         for(int var5 = 0; var5 < var7; ++var5) {
            int var6 = var19.getIndex(var5);
            int var8 = ConstraintLayout.LayoutParams.Table.map.get(var6);
            float var3;
            switch (var8) {
               case 1:
                  this.orientation = var19.getInt(var6, this.orientation);
                  continue;
               case 2:
                  var8 = var19.getResourceId(var6, this.circleConstraint);
                  this.circleConstraint = var8;
                  if (var8 == -1) {
                     this.circleConstraint = var19.getInt(var6, -1);
                  }
                  continue;
               case 3:
                  this.circleRadius = var19.getDimensionPixelSize(var6, this.circleRadius);
                  continue;
               case 4:
                  var3 = var19.getFloat(var6, this.circleAngle) % 360.0F;
                  this.circleAngle = var3;
                  if (var3 < 0.0F) {
                     this.circleAngle = (360.0F - var3) % 360.0F;
                  }
                  continue;
               case 5:
                  this.guideBegin = var19.getDimensionPixelOffset(var6, this.guideBegin);
                  continue;
               case 6:
                  this.guideEnd = var19.getDimensionPixelOffset(var6, this.guideEnd);
                  continue;
               case 7:
                  this.guidePercent = var19.getFloat(var6, this.guidePercent);
                  continue;
               case 8:
                  var8 = var19.getResourceId(var6, this.leftToLeft);
                  this.leftToLeft = var8;
                  if (var8 == -1) {
                     this.leftToLeft = var19.getInt(var6, -1);
                  }
                  continue;
               case 9:
                  var8 = var19.getResourceId(var6, this.leftToRight);
                  this.leftToRight = var8;
                  if (var8 == -1) {
                     this.leftToRight = var19.getInt(var6, -1);
                  }
                  continue;
               case 10:
                  var8 = var19.getResourceId(var6, this.rightToLeft);
                  this.rightToLeft = var8;
                  if (var8 == -1) {
                     this.rightToLeft = var19.getInt(var6, -1);
                  }
                  continue;
               case 11:
                  var8 = var19.getResourceId(var6, this.rightToRight);
                  this.rightToRight = var8;
                  if (var8 == -1) {
                     this.rightToRight = var19.getInt(var6, -1);
                  }
                  continue;
               case 12:
                  var8 = var19.getResourceId(var6, this.topToTop);
                  this.topToTop = var8;
                  if (var8 == -1) {
                     this.topToTop = var19.getInt(var6, -1);
                  }
                  continue;
               case 13:
                  var8 = var19.getResourceId(var6, this.topToBottom);
                  this.topToBottom = var8;
                  if (var8 == -1) {
                     this.topToBottom = var19.getInt(var6, -1);
                  }
                  continue;
               case 14:
                  var8 = var19.getResourceId(var6, this.bottomToTop);
                  this.bottomToTop = var8;
                  if (var8 == -1) {
                     this.bottomToTop = var19.getInt(var6, -1);
                  }
                  continue;
               case 15:
                  var8 = var19.getResourceId(var6, this.bottomToBottom);
                  this.bottomToBottom = var8;
                  if (var8 == -1) {
                     this.bottomToBottom = var19.getInt(var6, -1);
                  }
                  continue;
               case 16:
                  var8 = var19.getResourceId(var6, this.baselineToBaseline);
                  this.baselineToBaseline = var8;
                  if (var8 == -1) {
                     this.baselineToBaseline = var19.getInt(var6, -1);
                  }
                  continue;
               case 17:
                  var8 = var19.getResourceId(var6, this.startToEnd);
                  this.startToEnd = var8;
                  if (var8 == -1) {
                     this.startToEnd = var19.getInt(var6, -1);
                  }
                  continue;
               case 18:
                  var8 = var19.getResourceId(var6, this.startToStart);
                  this.startToStart = var8;
                  if (var8 == -1) {
                     this.startToStart = var19.getInt(var6, -1);
                  }
                  continue;
               case 19:
                  var8 = var19.getResourceId(var6, this.endToStart);
                  this.endToStart = var8;
                  if (var8 == -1) {
                     this.endToStart = var19.getInt(var6, -1);
                  }
                  continue;
               case 20:
                  var8 = var19.getResourceId(var6, this.endToEnd);
                  this.endToEnd = var8;
                  if (var8 == -1) {
                     this.endToEnd = var19.getInt(var6, -1);
                  }
                  continue;
               case 21:
                  this.goneLeftMargin = var19.getDimensionPixelSize(var6, this.goneLeftMargin);
                  continue;
               case 22:
                  this.goneTopMargin = var19.getDimensionPixelSize(var6, this.goneTopMargin);
                  continue;
               case 23:
                  this.goneRightMargin = var19.getDimensionPixelSize(var6, this.goneRightMargin);
                  continue;
               case 24:
                  this.goneBottomMargin = var19.getDimensionPixelSize(var6, this.goneBottomMargin);
                  continue;
               case 25:
                  this.goneStartMargin = var19.getDimensionPixelSize(var6, this.goneStartMargin);
                  continue;
               case 26:
                  this.goneEndMargin = var19.getDimensionPixelSize(var6, this.goneEndMargin);
                  continue;
               case 27:
                  this.constrainedWidth = var19.getBoolean(var6, this.constrainedWidth);
                  continue;
               case 28:
                  this.constrainedHeight = var19.getBoolean(var6, this.constrainedHeight);
                  continue;
               case 29:
                  this.horizontalBias = var19.getFloat(var6, this.horizontalBias);
                  continue;
               case 30:
                  this.verticalBias = var19.getFloat(var6, this.verticalBias);
                  continue;
               case 31:
                  var6 = var19.getInt(var6, 0);
                  this.matchConstraintDefaultWidth = var6;
                  if (var6 == 1) {
                     Log.e("ConstraintLayout", "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                  }
                  continue;
               case 32:
                  var6 = var19.getInt(var6, 0);
                  this.matchConstraintDefaultHeight = var6;
                  if (var6 == 1) {
                     Log.e("ConstraintLayout", "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                  }
                  continue;
               case 33:
                  try {
                     this.matchConstraintMinWidth = var19.getDimensionPixelSize(var6, this.matchConstraintMinWidth);
                  } catch (Exception var14) {
                     if (var19.getInt(var6, this.matchConstraintMinWidth) == -2) {
                        this.matchConstraintMinWidth = -2;
                     }
                  }
                  continue;
               case 34:
                  try {
                     this.matchConstraintMaxWidth = var19.getDimensionPixelSize(var6, this.matchConstraintMaxWidth);
                  } catch (Exception var13) {
                     if (var19.getInt(var6, this.matchConstraintMaxWidth) == -2) {
                        this.matchConstraintMaxWidth = -2;
                     }
                  }
                  continue;
               case 35:
                  this.matchConstraintPercentWidth = Math.max(0.0F, var19.getFloat(var6, this.matchConstraintPercentWidth));
                  continue;
               case 36:
                  try {
                     this.matchConstraintMinHeight = var19.getDimensionPixelSize(var6, this.matchConstraintMinHeight);
                  } catch (Exception var12) {
                     if (var19.getInt(var6, this.matchConstraintMinHeight) == -2) {
                        this.matchConstraintMinHeight = -2;
                     }
                  }
                  continue;
               case 37:
                  try {
                     this.matchConstraintMaxHeight = var19.getDimensionPixelSize(var6, this.matchConstraintMaxHeight);
                  } catch (Exception var11) {
                     if (var19.getInt(var6, this.matchConstraintMaxHeight) == -2) {
                        this.matchConstraintMaxHeight = -2;
                     }
                  }
                  continue;
               case 38:
                  this.matchConstraintPercentHeight = Math.max(0.0F, var19.getFloat(var6, this.matchConstraintPercentHeight));
                  continue;
            }

            switch (var8) {
               case 44:
                  String var20 = var19.getString(var6);
                  this.dimensionRatio = var20;
                  this.dimensionRatioValue = Float.NaN;
                  this.dimensionRatioSide = -1;
                  if (var20 == null) {
                     break;
                  }

                  var8 = var20.length();
                  var6 = this.dimensionRatio.indexOf(44);
                  if (var6 > 0 && var6 < var8 - 1) {
                     var20 = this.dimensionRatio.substring(0, var6);
                     if (var20.equalsIgnoreCase("W")) {
                        this.dimensionRatioSide = 0;
                     } else if (var20.equalsIgnoreCase("H")) {
                        this.dimensionRatioSide = 1;
                     }

                     ++var6;
                  } else {
                     var6 = 0;
                  }

                  int var9 = this.dimensionRatio.indexOf(58);
                  boolean var10001;
                  if (var9 >= 0 && var9 < var8 - 1) {
                     String var10 = this.dimensionRatio.substring(var6, var9);
                     var20 = this.dimensionRatio.substring(var9 + 1);
                     if (var10.length() > 0 && var20.length() > 0) {
                        float var4;
                        try {
                           var3 = Float.parseFloat(var10);
                           var4 = Float.parseFloat(var20);
                        } catch (NumberFormatException var17) {
                           var10001 = false;
                           break;
                        }

                        if (var3 > 0.0F && var4 > 0.0F) {
                           try {
                              if (this.dimensionRatioSide == 1) {
                                 this.dimensionRatioValue = Math.abs(var4 / var3);
                                 break;
                              }
                           } catch (NumberFormatException var18) {
                              var10001 = false;
                              break;
                           }

                           try {
                              this.dimensionRatioValue = Math.abs(var3 / var4);
                           } catch (NumberFormatException var15) {
                              var10001 = false;
                           }
                        }
                     }
                  } else {
                     var20 = this.dimensionRatio.substring(var6);
                     if (var20.length() > 0) {
                        try {
                           this.dimensionRatioValue = Float.parseFloat(var20);
                        } catch (NumberFormatException var16) {
                           var10001 = false;
                        }
                     }
                  }
                  break;
               case 45:
                  this.horizontalWeight = var19.getFloat(var6, this.horizontalWeight);
                  break;
               case 46:
                  this.verticalWeight = var19.getFloat(var6, this.verticalWeight);
                  break;
               case 47:
                  this.horizontalChainStyle = var19.getInt(var6, 0);
                  break;
               case 48:
                  this.verticalChainStyle = var19.getInt(var6, 0);
                  break;
               case 49:
                  this.editorAbsoluteX = var19.getDimensionPixelOffset(var6, this.editorAbsoluteX);
                  break;
               case 50:
                  this.editorAbsoluteY = var19.getDimensionPixelOffset(var6, this.editorAbsoluteY);
            }
         }

         var19.recycle();
         this.validate();
      }

      public LayoutParams(ViewGroup.LayoutParams var1) {
         super(var1);
      }

      public LayoutParams(LayoutParams var1) {
         super(var1);
         this.guideBegin = var1.guideBegin;
         this.guideEnd = var1.guideEnd;
         this.guidePercent = var1.guidePercent;
         this.leftToLeft = var1.leftToLeft;
         this.leftToRight = var1.leftToRight;
         this.rightToLeft = var1.rightToLeft;
         this.rightToRight = var1.rightToRight;
         this.topToTop = var1.topToTop;
         this.topToBottom = var1.topToBottom;
         this.bottomToTop = var1.bottomToTop;
         this.bottomToBottom = var1.bottomToBottom;
         this.baselineToBaseline = var1.baselineToBaseline;
         this.circleConstraint = var1.circleConstraint;
         this.circleRadius = var1.circleRadius;
         this.circleAngle = var1.circleAngle;
         this.startToEnd = var1.startToEnd;
         this.startToStart = var1.startToStart;
         this.endToStart = var1.endToStart;
         this.endToEnd = var1.endToEnd;
         this.goneLeftMargin = var1.goneLeftMargin;
         this.goneTopMargin = var1.goneTopMargin;
         this.goneRightMargin = var1.goneRightMargin;
         this.goneBottomMargin = var1.goneBottomMargin;
         this.goneStartMargin = var1.goneStartMargin;
         this.goneEndMargin = var1.goneEndMargin;
         this.horizontalBias = var1.horizontalBias;
         this.verticalBias = var1.verticalBias;
         this.dimensionRatio = var1.dimensionRatio;
         this.dimensionRatioValue = var1.dimensionRatioValue;
         this.dimensionRatioSide = var1.dimensionRatioSide;
         this.horizontalWeight = var1.horizontalWeight;
         this.verticalWeight = var1.verticalWeight;
         this.horizontalChainStyle = var1.horizontalChainStyle;
         this.verticalChainStyle = var1.verticalChainStyle;
         this.constrainedWidth = var1.constrainedWidth;
         this.constrainedHeight = var1.constrainedHeight;
         this.matchConstraintDefaultWidth = var1.matchConstraintDefaultWidth;
         this.matchConstraintDefaultHeight = var1.matchConstraintDefaultHeight;
         this.matchConstraintMinWidth = var1.matchConstraintMinWidth;
         this.matchConstraintMaxWidth = var1.matchConstraintMaxWidth;
         this.matchConstraintMinHeight = var1.matchConstraintMinHeight;
         this.matchConstraintMaxHeight = var1.matchConstraintMaxHeight;
         this.matchConstraintPercentWidth = var1.matchConstraintPercentWidth;
         this.matchConstraintPercentHeight = var1.matchConstraintPercentHeight;
         this.editorAbsoluteX = var1.editorAbsoluteX;
         this.editorAbsoluteY = var1.editorAbsoluteY;
         this.orientation = var1.orientation;
         this.horizontalDimensionFixed = var1.horizontalDimensionFixed;
         this.verticalDimensionFixed = var1.verticalDimensionFixed;
         this.needsBaseline = var1.needsBaseline;
         this.isGuideline = var1.isGuideline;
         this.resolvedLeftToLeft = var1.resolvedLeftToLeft;
         this.resolvedLeftToRight = var1.resolvedLeftToRight;
         this.resolvedRightToLeft = var1.resolvedRightToLeft;
         this.resolvedRightToRight = var1.resolvedRightToRight;
         this.resolveGoneLeftMargin = var1.resolveGoneLeftMargin;
         this.resolveGoneRightMargin = var1.resolveGoneRightMargin;
         this.resolvedHorizontalBias = var1.resolvedHorizontalBias;
         this.widget = var1.widget;
      }

      public void reset() {
         ConstraintWidget var1 = this.widget;
         if (var1 != null) {
            var1.reset();
         }

      }

      public void resolveLayoutDirection(int var1) {
         int var4 = this.leftMargin;
         int var5 = this.rightMargin;
         super.resolveLayoutDirection(var1);
         this.resolvedRightToLeft = -1;
         this.resolvedRightToRight = -1;
         this.resolvedLeftToLeft = -1;
         this.resolvedLeftToRight = -1;
         this.resolveGoneLeftMargin = this.goneLeftMargin;
         this.resolveGoneRightMargin = this.goneRightMargin;
         this.resolvedHorizontalBias = this.horizontalBias;
         this.resolvedGuideBegin = this.guideBegin;
         this.resolvedGuideEnd = this.guideEnd;
         this.resolvedGuidePercent = this.guidePercent;
         var1 = this.getLayoutDirection();
         boolean var3 = false;
         boolean var7;
         if (1 == var1) {
            var7 = true;
         } else {
            var7 = false;
         }

         if (var7) {
            label113: {
               var1 = this.startToEnd;
               if (var1 != -1) {
                  this.resolvedRightToLeft = var1;
               } else {
                  int var6 = this.startToStart;
                  var7 = var3;
                  if (var6 == -1) {
                     break label113;
                  }

                  this.resolvedRightToRight = var6;
               }

               var7 = true;
            }

            int var8 = this.endToStart;
            if (var8 != -1) {
               this.resolvedLeftToRight = var8;
               var7 = true;
            }

            var8 = this.endToEnd;
            if (var8 != -1) {
               this.resolvedLeftToLeft = var8;
               var7 = true;
            }

            var8 = this.goneStartMargin;
            if (var8 != -1) {
               this.resolveGoneRightMargin = var8;
            }

            var8 = this.goneEndMargin;
            if (var8 != -1) {
               this.resolveGoneLeftMargin = var8;
            }

            if (var7) {
               this.resolvedHorizontalBias = 1.0F - this.horizontalBias;
            }

            if (this.isGuideline && this.orientation == 1) {
               float var2 = this.guidePercent;
               if (var2 != -1.0F) {
                  this.resolvedGuidePercent = 1.0F - var2;
                  this.resolvedGuideBegin = -1;
                  this.resolvedGuideEnd = -1;
               } else {
                  var1 = this.guideBegin;
                  if (var1 != -1) {
                     this.resolvedGuideEnd = var1;
                     this.resolvedGuideBegin = -1;
                     this.resolvedGuidePercent = -1.0F;
                  } else {
                     var1 = this.guideEnd;
                     if (var1 != -1) {
                        this.resolvedGuideBegin = var1;
                        this.resolvedGuideEnd = -1;
                        this.resolvedGuidePercent = -1.0F;
                     }
                  }
               }
            }
         } else {
            var1 = this.startToEnd;
            if (var1 != -1) {
               this.resolvedLeftToRight = var1;
            }

            var1 = this.startToStart;
            if (var1 != -1) {
               this.resolvedLeftToLeft = var1;
            }

            var1 = this.endToStart;
            if (var1 != -1) {
               this.resolvedRightToLeft = var1;
            }

            var1 = this.endToEnd;
            if (var1 != -1) {
               this.resolvedRightToRight = var1;
            }

            var1 = this.goneStartMargin;
            if (var1 != -1) {
               this.resolveGoneLeftMargin = var1;
            }

            var1 = this.goneEndMargin;
            if (var1 != -1) {
               this.resolveGoneRightMargin = var1;
            }
         }

         if (this.endToStart == -1 && this.endToEnd == -1 && this.startToStart == -1 && this.startToEnd == -1) {
            var1 = this.rightToLeft;
            if (var1 != -1) {
               this.resolvedRightToLeft = var1;
               if (this.rightMargin <= 0 && var5 > 0) {
                  this.rightMargin = var5;
               }
            } else {
               var1 = this.rightToRight;
               if (var1 != -1) {
                  this.resolvedRightToRight = var1;
                  if (this.rightMargin <= 0 && var5 > 0) {
                     this.rightMargin = var5;
                  }
               }
            }

            var1 = this.leftToLeft;
            if (var1 != -1) {
               this.resolvedLeftToLeft = var1;
               if (this.leftMargin <= 0 && var4 > 0) {
                  this.leftMargin = var4;
               }
            } else {
               var1 = this.leftToRight;
               if (var1 != -1) {
                  this.resolvedLeftToRight = var1;
                  if (this.leftMargin <= 0 && var4 > 0) {
                     this.leftMargin = var4;
                  }
               }
            }
         }

      }

      public void validate() {
         this.isGuideline = false;
         this.horizontalDimensionFixed = true;
         this.verticalDimensionFixed = true;
         if (this.width == -2 && this.constrainedWidth) {
            this.horizontalDimensionFixed = false;
            this.matchConstraintDefaultWidth = 1;
         }

         if (this.height == -2 && this.constrainedHeight) {
            this.verticalDimensionFixed = false;
            this.matchConstraintDefaultHeight = 1;
         }

         if (this.width == 0 || this.width == -1) {
            this.horizontalDimensionFixed = false;
            if (this.width == 0 && this.matchConstraintDefaultWidth == 1) {
               this.width = -2;
               this.constrainedWidth = true;
            }
         }

         if (this.height == 0 || this.height == -1) {
            this.verticalDimensionFixed = false;
            if (this.height == 0 && this.matchConstraintDefaultHeight == 1) {
               this.height = -2;
               this.constrainedHeight = true;
            }
         }

         if (this.guidePercent != -1.0F || this.guideBegin != -1 || this.guideEnd != -1) {
            this.isGuideline = true;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            if (!(this.widget instanceof androidx.constraintlayout.solver.widgets.Guideline)) {
               this.widget = new androidx.constraintlayout.solver.widgets.Guideline();
            }

            ((androidx.constraintlayout.solver.widgets.Guideline)this.widget).setOrientation(this.orientation);
         }

      }

      private static class Table {
         public static final int ANDROID_ORIENTATION = 1;
         public static final int LAYOUT_CONSTRAINED_HEIGHT = 28;
         public static final int LAYOUT_CONSTRAINED_WIDTH = 27;
         public static final int LAYOUT_CONSTRAINT_BASELINE_CREATOR = 43;
         public static final int LAYOUT_CONSTRAINT_BASELINE_TO_BASELINE_OF = 16;
         public static final int LAYOUT_CONSTRAINT_BOTTOM_CREATOR = 42;
         public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_BOTTOM_OF = 15;
         public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_TOP_OF = 14;
         public static final int LAYOUT_CONSTRAINT_CIRCLE = 2;
         public static final int LAYOUT_CONSTRAINT_CIRCLE_ANGLE = 4;
         public static final int LAYOUT_CONSTRAINT_CIRCLE_RADIUS = 3;
         public static final int LAYOUT_CONSTRAINT_DIMENSION_RATIO = 44;
         public static final int LAYOUT_CONSTRAINT_END_TO_END_OF = 20;
         public static final int LAYOUT_CONSTRAINT_END_TO_START_OF = 19;
         public static final int LAYOUT_CONSTRAINT_GUIDE_BEGIN = 5;
         public static final int LAYOUT_CONSTRAINT_GUIDE_END = 6;
         public static final int LAYOUT_CONSTRAINT_GUIDE_PERCENT = 7;
         public static final int LAYOUT_CONSTRAINT_HEIGHT_DEFAULT = 32;
         public static final int LAYOUT_CONSTRAINT_HEIGHT_MAX = 37;
         public static final int LAYOUT_CONSTRAINT_HEIGHT_MIN = 36;
         public static final int LAYOUT_CONSTRAINT_HEIGHT_PERCENT = 38;
         public static final int LAYOUT_CONSTRAINT_HORIZONTAL_BIAS = 29;
         public static final int LAYOUT_CONSTRAINT_HORIZONTAL_CHAINSTYLE = 47;
         public static final int LAYOUT_CONSTRAINT_HORIZONTAL_WEIGHT = 45;
         public static final int LAYOUT_CONSTRAINT_LEFT_CREATOR = 39;
         public static final int LAYOUT_CONSTRAINT_LEFT_TO_LEFT_OF = 8;
         public static final int LAYOUT_CONSTRAINT_LEFT_TO_RIGHT_OF = 9;
         public static final int LAYOUT_CONSTRAINT_RIGHT_CREATOR = 41;
         public static final int LAYOUT_CONSTRAINT_RIGHT_TO_LEFT_OF = 10;
         public static final int LAYOUT_CONSTRAINT_RIGHT_TO_RIGHT_OF = 11;
         public static final int LAYOUT_CONSTRAINT_START_TO_END_OF = 17;
         public static final int LAYOUT_CONSTRAINT_START_TO_START_OF = 18;
         public static final int LAYOUT_CONSTRAINT_TOP_CREATOR = 40;
         public static final int LAYOUT_CONSTRAINT_TOP_TO_BOTTOM_OF = 13;
         public static final int LAYOUT_CONSTRAINT_TOP_TO_TOP_OF = 12;
         public static final int LAYOUT_CONSTRAINT_VERTICAL_BIAS = 30;
         public static final int LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE = 48;
         public static final int LAYOUT_CONSTRAINT_VERTICAL_WEIGHT = 46;
         public static final int LAYOUT_CONSTRAINT_WIDTH_DEFAULT = 31;
         public static final int LAYOUT_CONSTRAINT_WIDTH_MAX = 34;
         public static final int LAYOUT_CONSTRAINT_WIDTH_MIN = 33;
         public static final int LAYOUT_CONSTRAINT_WIDTH_PERCENT = 35;
         public static final int LAYOUT_EDITOR_ABSOLUTEX = 49;
         public static final int LAYOUT_EDITOR_ABSOLUTEY = 50;
         public static final int LAYOUT_GONE_MARGIN_BOTTOM = 24;
         public static final int LAYOUT_GONE_MARGIN_END = 26;
         public static final int LAYOUT_GONE_MARGIN_LEFT = 21;
         public static final int LAYOUT_GONE_MARGIN_RIGHT = 23;
         public static final int LAYOUT_GONE_MARGIN_START = 25;
         public static final int LAYOUT_GONE_MARGIN_TOP = 22;
         public static final int UNUSED = 0;
         public static final SparseIntArray map;

         static {
            SparseIntArray var0 = new SparseIntArray();
            map = var0;
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
            var0.append(R.styleable.ConstraintLayout_Layout_android_orientation, 1);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
            var0.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
         }
      }
   }
}
