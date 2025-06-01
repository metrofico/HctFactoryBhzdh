package androidx.recyclerview.widget;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

public abstract class SnapHelper extends RecyclerView.OnFlingListener {
   static final float MILLISECONDS_PER_INCH = 100.0F;
   private Scroller mGravityScroller;
   RecyclerView mRecyclerView;
   private final RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener(this) {
      boolean mScrolled;
      final SnapHelper this$0;

      {
         this.this$0 = var1;
         this.mScrolled = false;
      }

      public void onScrollStateChanged(RecyclerView var1, int var2) {
         super.onScrollStateChanged(var1, var2);
         if (var2 == 0 && this.mScrolled) {
            this.mScrolled = false;
            this.this$0.snapToTargetExistingView();
         }

      }

      public void onScrolled(RecyclerView var1, int var2, int var3) {
         if (var2 != 0 || var3 != 0) {
            this.mScrolled = true;
         }

      }
   };

   private void destroyCallbacks() {
      this.mRecyclerView.removeOnScrollListener(this.mScrollListener);
      this.mRecyclerView.setOnFlingListener((RecyclerView.OnFlingListener)null);
   }

   private void setupCallbacks() throws IllegalStateException {
      if (this.mRecyclerView.getOnFlingListener() == null) {
         this.mRecyclerView.addOnScrollListener(this.mScrollListener);
         this.mRecyclerView.setOnFlingListener(this);
      } else {
         throw new IllegalStateException("An instance of OnFlingListener already set.");
      }
   }

   private boolean snapFromFling(RecyclerView.LayoutManager var1, int var2, int var3) {
      if (!(var1 instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
         return false;
      } else {
         RecyclerView.SmoothScroller var4 = this.createScroller(var1);
         if (var4 == null) {
            return false;
         } else {
            var2 = this.findTargetSnapPosition(var1, var2, var3);
            if (var2 == -1) {
               return false;
            } else {
               var4.setTargetPosition(var2);
               var1.startSmoothScroll(var4);
               return true;
            }
         }
      }
   }

   public void attachToRecyclerView(RecyclerView var1) throws IllegalStateException {
      RecyclerView var2 = this.mRecyclerView;
      if (var2 != var1) {
         if (var2 != null) {
            this.destroyCallbacks();
         }

         this.mRecyclerView = var1;
         if (var1 != null) {
            this.setupCallbacks();
            this.mGravityScroller = new Scroller(this.mRecyclerView.getContext(), new DecelerateInterpolator());
            this.snapToTargetExistingView();
         }

      }
   }

   public abstract int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager var1, View var2);

   public int[] calculateScrollDistance(int var1, int var2) {
      this.mGravityScroller.fling(0, 0, var1, var2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
      return new int[]{this.mGravityScroller.getFinalX(), this.mGravityScroller.getFinalY()};
   }

   protected RecyclerView.SmoothScroller createScroller(RecyclerView.LayoutManager var1) {
      return this.createSnapScroller(var1);
   }

   @Deprecated
   protected LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager var1) {
      return !(var1 instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) ? null : new LinearSmoothScroller(this, this.mRecyclerView.getContext()) {
         final SnapHelper this$0;

         {
            this.this$0 = var1;
         }

         protected float calculateSpeedPerPixel(DisplayMetrics var1) {
            return 100.0F / (float)var1.densityDpi;
         }

         protected void onTargetFound(View var1, RecyclerView.State var2, RecyclerView.SmoothScroller.Action var3) {
            if (this.this$0.mRecyclerView != null) {
               SnapHelper var8 = this.this$0;
               int[] var7 = var8.calculateDistanceToFinalSnap(var8.mRecyclerView.getLayoutManager(), var1);
               int var5 = var7[0];
               int var4 = var7[1];
               int var6 = this.calculateTimeForDeceleration(Math.max(Math.abs(var5), Math.abs(var4)));
               if (var6 > 0) {
                  var3.update(var5, var4, var6, this.mDecelerateInterpolator);
               }

            }
         }
      };
   }

   public abstract View findSnapView(RecyclerView.LayoutManager var1);

   public abstract int findTargetSnapPosition(RecyclerView.LayoutManager var1, int var2, int var3);

   public boolean onFling(int var1, int var2) {
      RecyclerView.LayoutManager var6 = this.mRecyclerView.getLayoutManager();
      boolean var5 = false;
      if (var6 == null) {
         return false;
      } else if (this.mRecyclerView.getAdapter() == null) {
         return false;
      } else {
         int var3 = this.mRecyclerView.getMinFlingVelocity();
         boolean var4;
         if (Math.abs(var2) <= var3) {
            var4 = var5;
            if (Math.abs(var1) <= var3) {
               return var4;
            }
         }

         var4 = var5;
         if (this.snapFromFling(var6, var1, var2)) {
            var4 = true;
         }

         return var4;
      }
   }

   void snapToTargetExistingView() {
      RecyclerView var2 = this.mRecyclerView;
      if (var2 != null) {
         RecyclerView.LayoutManager var4 = var2.getLayoutManager();
         if (var4 != null) {
            View var3 = this.findSnapView(var4);
            if (var3 != null) {
               int[] var5 = this.calculateDistanceToFinalSnap(var4, var3);
               int var1 = var5[0];
               if (var1 != 0 || var5[1] != 0) {
                  this.mRecyclerView.smoothScrollBy(var1, var5[1]);
               }

            }
         }
      }
   }
}
