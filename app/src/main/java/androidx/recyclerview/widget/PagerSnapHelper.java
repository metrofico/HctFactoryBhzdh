package androidx.recyclerview.widget;

import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;

public class PagerSnapHelper extends SnapHelper {
   private static final int MAX_SCROLL_ON_FLING_DURATION = 100;
   private OrientationHelper mHorizontalHelper;
   private OrientationHelper mVerticalHelper;

   private int distanceToCenter(View var1, OrientationHelper var2) {
      return var2.getDecoratedStart(var1) + var2.getDecoratedMeasurement(var1) / 2 - (var2.getStartAfterPadding() + var2.getTotalSpace() / 2);
   }

   private View findCenterView(RecyclerView.LayoutManager var1, OrientationHelper var2) {
      int var7 = var1.getChildCount();
      View var10 = null;
      if (var7 == 0) {
         return null;
      } else {
         int var9 = var2.getStartAfterPadding();
         int var8 = var2.getTotalSpace() / 2;
         int var4 = Integer.MAX_VALUE;

         int var5;
         for(int var3 = 0; var3 < var7; var4 = var5) {
            View var11 = var1.getChildAt(var3);
            int var6 = Math.abs(var2.getDecoratedStart(var11) + var2.getDecoratedMeasurement(var11) / 2 - (var9 + var8));
            var5 = var4;
            if (var6 < var4) {
               var10 = var11;
               var5 = var6;
            }

            ++var3;
         }

         return var10;
      }
   }

   private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager var1) {
      OrientationHelper var2 = this.mHorizontalHelper;
      if (var2 == null || var2.mLayoutManager != var1) {
         this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(var1);
      }

      return this.mHorizontalHelper;
   }

   private OrientationHelper getOrientationHelper(RecyclerView.LayoutManager var1) {
      if (var1.canScrollVertically()) {
         return this.getVerticalHelper(var1);
      } else {
         return var1.canScrollHorizontally() ? this.getHorizontalHelper(var1) : null;
      }
   }

   private OrientationHelper getVerticalHelper(RecyclerView.LayoutManager var1) {
      OrientationHelper var2 = this.mVerticalHelper;
      if (var2 == null || var2.mLayoutManager != var1) {
         this.mVerticalHelper = OrientationHelper.createVerticalHelper(var1);
      }

      return this.mVerticalHelper;
   }

   private boolean isForwardFling(RecyclerView.LayoutManager var1, int var2, int var3) {
      boolean var6 = var1.canScrollHorizontally();
      boolean var5 = true;
      boolean var4 = true;
      if (var6) {
         if (var2 <= 0) {
            var4 = false;
         }

         return var4;
      } else {
         if (var3 > 0) {
            var4 = var5;
         } else {
            var4 = false;
         }

         return var4;
      }
   }

   private boolean isReverseLayout(RecyclerView.LayoutManager var1) {
      int var2 = var1.getItemCount();
      boolean var5 = var1 instanceof RecyclerView.SmoothScroller.ScrollVectorProvider;
      boolean var4 = false;
      boolean var3 = var4;
      if (var5) {
         PointF var6 = ((RecyclerView.SmoothScroller.ScrollVectorProvider)var1).computeScrollVectorForPosition(var2 - 1);
         var3 = var4;
         if (var6 != null) {
            if (!(var6.x < 0.0F)) {
               var3 = var4;
               if (!(var6.y < 0.0F)) {
                  return var3;
               }
            }

            var3 = true;
         }
      }

      return var3;
   }

   public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager var1, View var2) {
      int[] var3 = new int[2];
      if (var1.canScrollHorizontally()) {
         var3[0] = this.distanceToCenter(var2, this.getHorizontalHelper(var1));
      } else {
         var3[0] = 0;
      }

      if (var1.canScrollVertically()) {
         var3[1] = this.distanceToCenter(var2, this.getVerticalHelper(var1));
      } else {
         var3[1] = 0;
      }

      return var3;
   }

   protected RecyclerView.SmoothScroller createScroller(RecyclerView.LayoutManager var1) {
      return !(var1 instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) ? null : new LinearSmoothScroller(this, this.mRecyclerView.getContext()) {
         final PagerSnapHelper this$0;

         {
            this.this$0 = var1;
         }

         protected float calculateSpeedPerPixel(DisplayMetrics var1) {
            return 100.0F / (float)var1.densityDpi;
         }

         protected int calculateTimeForScrolling(int var1) {
            return Math.min(100, super.calculateTimeForScrolling(var1));
         }

         protected void onTargetFound(View var1, RecyclerView.State var2, RecyclerView.SmoothScroller.Action var3) {
            PagerSnapHelper var8 = this.this$0;
            int[] var7 = var8.calculateDistanceToFinalSnap(var8.mRecyclerView.getLayoutManager(), var1);
            int var5 = var7[0];
            int var4 = var7[1];
            int var6 = this.calculateTimeForDeceleration(Math.max(Math.abs(var5), Math.abs(var4)));
            if (var6 > 0) {
               var3.update(var5, var4, var6, this.mDecelerateInterpolator);
            }

         }
      };
   }

   public View findSnapView(RecyclerView.LayoutManager var1) {
      if (var1.canScrollVertically()) {
         return this.findCenterView(var1, this.getVerticalHelper(var1));
      } else {
         return var1.canScrollHorizontally() ? this.findCenterView(var1, this.getHorizontalHelper(var1)) : null;
      }
   }

   public int findTargetSnapPosition(RecyclerView.LayoutManager var1, int var2, int var3) {
      int var10 = var1.getItemCount();
      if (var10 == 0) {
         return -1;
      } else {
         OrientationHelper var18 = this.getOrientationHelper(var1);
         if (var18 == null) {
            return -1;
         } else {
            int var5 = Integer.MIN_VALUE;
            int var7 = Integer.MAX_VALUE;
            int var11 = var1.getChildCount();
            int var6 = 0;
            View var15 = null;

            View var13;
            View var17;
            for(var13 = null; var6 < var11; var15 = var17) {
               View var16 = var1.getChildAt(var6);
               int var9;
               if (var16 == null) {
                  var9 = var7;
                  var17 = var15;
               } else {
                  int var8 = this.distanceToCenter(var16, var18);
                  int var4 = var5;
                  View var14 = var13;
                  if (var8 <= 0) {
                     var4 = var5;
                     var14 = var13;
                     if (var8 > var5) {
                        var14 = var16;
                        var4 = var8;
                     }
                  }

                  var5 = var4;
                  var9 = var7;
                  var17 = var15;
                  var13 = var14;
                  if (var8 >= 0) {
                     var5 = var4;
                     var9 = var7;
                     var17 = var15;
                     var13 = var14;
                     if (var8 < var7) {
                        var13 = var14;
                        var17 = var16;
                        var9 = var8;
                        var5 = var4;
                     }
                  }
               }

               ++var6;
               var7 = var9;
            }

            boolean var12 = this.isForwardFling(var1, var2, var3);
            if (var12 && var15 != null) {
               return var1.getPosition(var15);
            } else if (!var12 && var13 != null) {
               return var1.getPosition(var13);
            } else {
               if (var12) {
                  var15 = var13;
               }

               if (var15 == null) {
                  return -1;
               } else {
                  var3 = var1.getPosition(var15);
                  byte var19;
                  if (this.isReverseLayout(var1) == var12) {
                     var19 = -1;
                  } else {
                     var19 = 1;
                  }

                  var2 = var3 + var19;
                  if (var2 >= 0 && var2 < var10) {
                     return var2;
                  } else {
                     return -1;
                  }
               }
            }
         }
      }
   }
}
