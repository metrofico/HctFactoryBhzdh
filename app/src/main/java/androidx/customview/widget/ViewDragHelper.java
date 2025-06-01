package androidx.customview.widget;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.core.view.ViewCompat;
import java.util.Arrays;

public class ViewDragHelper {
   private static final int BASE_SETTLE_DURATION = 256;
   public static final int DIRECTION_ALL = 3;
   public static final int DIRECTION_HORIZONTAL = 1;
   public static final int DIRECTION_VERTICAL = 2;
   public static final int EDGE_ALL = 15;
   public static final int EDGE_BOTTOM = 8;
   public static final int EDGE_LEFT = 1;
   public static final int EDGE_RIGHT = 2;
   private static final int EDGE_SIZE = 20;
   public static final int EDGE_TOP = 4;
   public static final int INVALID_POINTER = -1;
   private static final int MAX_SETTLE_DURATION = 600;
   public static final int STATE_DRAGGING = 1;
   public static final int STATE_IDLE = 0;
   public static final int STATE_SETTLING = 2;
   private static final String TAG = "ViewDragHelper";
   private static final Interpolator sInterpolator = new Interpolator() {
      public float getInterpolation(float var1) {
         --var1;
         return var1 * var1 * var1 * var1 * var1 + 1.0F;
      }
   };
   private int mActivePointerId = -1;
   private final Callback mCallback;
   private View mCapturedView;
   private int mDragState;
   private int[] mEdgeDragsInProgress;
   private int[] mEdgeDragsLocked;
   private int mEdgeSize;
   private int[] mInitialEdgesTouched;
   private float[] mInitialMotionX;
   private float[] mInitialMotionY;
   private float[] mLastMotionX;
   private float[] mLastMotionY;
   private float mMaxVelocity;
   private float mMinVelocity;
   private final ViewGroup mParentView;
   private int mPointersDown;
   private boolean mReleaseInProgress;
   private OverScroller mScroller;
   private final Runnable mSetIdleRunnable = new Runnable(this) {
      final ViewDragHelper this$0;

      {
         this.this$0 = var1;
      }

      public void run() {
         this.this$0.setDragState(0);
      }
   };
   private int mTouchSlop;
   private int mTrackingEdges;
   private VelocityTracker mVelocityTracker;

   private ViewDragHelper(Context var1, ViewGroup var2, Callback var3) {
      if (var2 != null) {
         if (var3 != null) {
            this.mParentView = var2;
            this.mCallback = var3;
            ViewConfiguration var4 = ViewConfiguration.get(var1);
            this.mEdgeSize = (int)(var1.getResources().getDisplayMetrics().density * 20.0F + 0.5F);
            this.mTouchSlop = var4.getScaledTouchSlop();
            this.mMaxVelocity = (float)var4.getScaledMaximumFlingVelocity();
            this.mMinVelocity = (float)var4.getScaledMinimumFlingVelocity();
            this.mScroller = new OverScroller(var1, sInterpolator);
         } else {
            throw new IllegalArgumentException("Callback may not be null");
         }
      } else {
         throw new IllegalArgumentException("Parent view may not be null");
      }
   }

   private boolean checkNewEdgeDrag(float var1, float var2, int var3, int var4) {
      var1 = Math.abs(var1);
      var2 = Math.abs(var2);
      int var5 = this.mInitialEdgesTouched[var3];
      boolean var7 = false;
      boolean var6 = var7;
      if ((var5 & var4) == var4) {
         var6 = var7;
         if ((this.mTrackingEdges & var4) != 0) {
            var6 = var7;
            if ((this.mEdgeDragsLocked[var3] & var4) != var4) {
               var6 = var7;
               if ((this.mEdgeDragsInProgress[var3] & var4) != var4) {
                  var5 = this.mTouchSlop;
                  if (var1 <= (float)var5 && var2 <= (float)var5) {
                     var6 = var7;
                  } else {
                     if (var1 < var2 * 0.5F && this.mCallback.onEdgeLock(var4)) {
                        int[] var8 = this.mEdgeDragsLocked;
                        var8[var3] |= var4;
                        return false;
                     }

                     var6 = var7;
                     if ((this.mEdgeDragsInProgress[var3] & var4) == 0) {
                        var6 = var7;
                        if (var1 > (float)this.mTouchSlop) {
                           var6 = true;
                        }
                     }
                  }
               }
            }
         }
      }

      return var6;
   }

   private boolean checkTouchSlop(View var1, float var2, float var3) {
      boolean var8 = false;
      boolean var7 = false;
      boolean var6 = false;
      if (var1 == null) {
         return false;
      } else {
         boolean var4;
         if (this.mCallback.getViewHorizontalDragRange(var1) > 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         boolean var5;
         if (this.mCallback.getViewVerticalDragRange(var1) > 0) {
            var5 = true;
         } else {
            var5 = false;
         }

         if (var4 && var5) {
            int var9 = this.mTouchSlop;
            if (var2 * var2 + var3 * var3 > (float)(var9 * var9)) {
               var6 = true;
            }

            return var6;
         } else if (var4) {
            var6 = var8;
            if (Math.abs(var2) > (float)this.mTouchSlop) {
               var6 = true;
            }

            return var6;
         } else {
            var6 = var7;
            if (var5) {
               var6 = var7;
               if (Math.abs(var3) > (float)this.mTouchSlop) {
                  var6 = true;
               }
            }

            return var6;
         }
      }
   }

   private float clampMag(float var1, float var2, float var3) {
      float var4 = Math.abs(var1);
      if (var4 < var2) {
         return 0.0F;
      } else if (var4 > var3) {
         if (!(var1 > 0.0F)) {
            var3 = -var3;
         }

         return var3;
      } else {
         return var1;
      }
   }

   private int clampMag(int var1, int var2, int var3) {
      int var4 = Math.abs(var1);
      if (var4 < var2) {
         return 0;
      } else if (var4 > var3) {
         if (var1 <= 0) {
            var3 = -var3;
         }

         return var3;
      } else {
         return var1;
      }
   }

   private void clearMotionHistory() {
      float[] var1 = this.mInitialMotionX;
      if (var1 != null) {
         Arrays.fill(var1, 0.0F);
         Arrays.fill(this.mInitialMotionY, 0.0F);
         Arrays.fill(this.mLastMotionX, 0.0F);
         Arrays.fill(this.mLastMotionY, 0.0F);
         Arrays.fill(this.mInitialEdgesTouched, 0);
         Arrays.fill(this.mEdgeDragsInProgress, 0);
         Arrays.fill(this.mEdgeDragsLocked, 0);
         this.mPointersDown = 0;
      }
   }

   private void clearMotionHistory(int var1) {
      if (this.mInitialMotionX != null && this.isPointerDown(var1)) {
         this.mInitialMotionX[var1] = 0.0F;
         this.mInitialMotionY[var1] = 0.0F;
         this.mLastMotionX[var1] = 0.0F;
         this.mLastMotionY[var1] = 0.0F;
         this.mInitialEdgesTouched[var1] = 0;
         this.mEdgeDragsInProgress[var1] = 0;
         this.mEdgeDragsLocked[var1] = 0;
         this.mPointersDown &= ~(1 << var1);
      }

   }

   private int computeAxisDuration(int var1, int var2, int var3) {
      if (var1 == 0) {
         return 0;
      } else {
         int var6 = this.mParentView.getWidth();
         int var7 = var6 / 2;
         float var5 = Math.min(1.0F, (float)Math.abs(var1) / (float)var6);
         float var4 = (float)var7;
         var5 = this.distanceInfluenceForSnapDuration(var5);
         var2 = Math.abs(var2);
         if (var2 > 0) {
            var1 = Math.round(Math.abs((var4 + var5 * var4) / (float)var2) * 1000.0F) * 4;
         } else {
            var1 = (int)(((float)Math.abs(var1) / (float)var3 + 1.0F) * 256.0F);
         }

         return Math.min(var1, 600);
      }
   }

   private int computeSettleDuration(View var1, int var2, int var3, int var4, int var5) {
      int var9 = this.clampMag(var4, (int)this.mMinVelocity, (int)this.mMaxVelocity);
      var4 = this.clampMag(var5, (int)this.mMinVelocity, (int)this.mMaxVelocity);
      int var10 = Math.abs(var2);
      int var14 = Math.abs(var3);
      int var12 = Math.abs(var9);
      int var11 = Math.abs(var4);
      int var13 = var12 + var11;
      var5 = var10 + var14;
      float var6;
      float var7;
      if (var9 != 0) {
         var6 = (float)var12;
         var7 = (float)var13;
      } else {
         var6 = (float)var10;
         var7 = (float)var5;
      }

      float var8 = var6 / var7;
      if (var4 != 0) {
         var6 = (float)var11;
         var7 = (float)var13;
      } else {
         var6 = (float)var14;
         var7 = (float)var5;
      }

      var6 /= var7;
      var2 = this.computeAxisDuration(var2, var9, this.mCallback.getViewHorizontalDragRange(var1));
      var3 = this.computeAxisDuration(var3, var4, this.mCallback.getViewVerticalDragRange(var1));
      return (int)((float)var2 * var8 + (float)var3 * var6);
   }

   public static ViewDragHelper create(ViewGroup var0, float var1, Callback var2) {
      ViewDragHelper var3 = create(var0, var2);
      var3.mTouchSlop = (int)((float)var3.mTouchSlop * (1.0F / var1));
      return var3;
   }

   public static ViewDragHelper create(ViewGroup var0, Callback var1) {
      return new ViewDragHelper(var0.getContext(), var0, var1);
   }

   private void dispatchViewReleased(float var1, float var2) {
      this.mReleaseInProgress = true;
      this.mCallback.onViewReleased(this.mCapturedView, var1, var2);
      this.mReleaseInProgress = false;
      if (this.mDragState == 1) {
         this.setDragState(0);
      }

   }

   private float distanceInfluenceForSnapDuration(float var1) {
      return (float)Math.sin((double)((var1 - 0.5F) * 0.47123894F));
   }

   private void dragTo(int var1, int var2, int var3, int var4) {
      int var6 = this.mCapturedView.getLeft();
      int var7 = this.mCapturedView.getTop();
      int var5 = var1;
      if (var3 != 0) {
         var5 = this.mCallback.clampViewPositionHorizontal(this.mCapturedView, var1, var3);
         ViewCompat.offsetLeftAndRight(this.mCapturedView, var5 - var6);
      }

      var1 = var2;
      if (var4 != 0) {
         var1 = this.mCallback.clampViewPositionVertical(this.mCapturedView, var2, var4);
         ViewCompat.offsetTopAndBottom(this.mCapturedView, var1 - var7);
      }

      if (var3 != 0 || var4 != 0) {
         this.mCallback.onViewPositionChanged(this.mCapturedView, var5, var1, var5 - var6, var1 - var7);
      }

   }

   private void ensureMotionHistorySizeForId(int var1) {
      float[] var9 = this.mInitialMotionX;
      if (var9 == null || var9.length <= var1) {
         ++var1;
         float[] var7 = new float[var1];
         float[] var3 = new float[var1];
         float[] var4 = new float[var1];
         float[] var8 = new float[var1];
         int[] var6 = new int[var1];
         int[] var2 = new int[var1];
         int[] var5 = new int[var1];
         if (var9 != null) {
            System.arraycopy(var9, 0, var7, 0, var9.length);
            var9 = this.mInitialMotionY;
            System.arraycopy(var9, 0, var3, 0, var9.length);
            var9 = this.mLastMotionX;
            System.arraycopy(var9, 0, var4, 0, var9.length);
            var9 = this.mLastMotionY;
            System.arraycopy(var9, 0, var8, 0, var9.length);
            int[] var10 = this.mInitialEdgesTouched;
            System.arraycopy(var10, 0, var6, 0, var10.length);
            var10 = this.mEdgeDragsInProgress;
            System.arraycopy(var10, 0, var2, 0, var10.length);
            var10 = this.mEdgeDragsLocked;
            System.arraycopy(var10, 0, var5, 0, var10.length);
         }

         this.mInitialMotionX = var7;
         this.mInitialMotionY = var3;
         this.mLastMotionX = var4;
         this.mLastMotionY = var8;
         this.mInitialEdgesTouched = var6;
         this.mEdgeDragsInProgress = var2;
         this.mEdgeDragsLocked = var5;
      }

   }

   private boolean forceSettleCapturedViewAt(int var1, int var2, int var3, int var4) {
      int var5 = this.mCapturedView.getLeft();
      int var6 = this.mCapturedView.getTop();
      var1 -= var5;
      var2 -= var6;
      if (var1 == 0 && var2 == 0) {
         this.mScroller.abortAnimation();
         this.setDragState(0);
         return false;
      } else {
         var3 = this.computeSettleDuration(this.mCapturedView, var1, var2, var3, var4);
         this.mScroller.startScroll(var5, var6, var1, var2, var3);
         this.setDragState(2);
         return true;
      }
   }

   private int getEdgesTouched(int var1, int var2) {
      int var4;
      if (var1 < this.mParentView.getLeft() + this.mEdgeSize) {
         var4 = 1;
      } else {
         var4 = 0;
      }

      int var3 = var4;
      if (var2 < this.mParentView.getTop() + this.mEdgeSize) {
         var3 = var4 | 4;
      }

      var4 = var3;
      if (var1 > this.mParentView.getRight() - this.mEdgeSize) {
         var4 = var3 | 2;
      }

      var1 = var4;
      if (var2 > this.mParentView.getBottom() - this.mEdgeSize) {
         var1 = var4 | 8;
      }

      return var1;
   }

   private boolean isValidPointerForActionMove(int var1) {
      if (!this.isPointerDown(var1)) {
         Log.e("ViewDragHelper", "Ignoring pointerId=" + var1 + " because ACTION_DOWN was not received " + "for this pointer before ACTION_MOVE. It likely happened because " + " ViewDragHelper did not receive all the events in the event stream.");
         return false;
      } else {
         return true;
      }
   }

   private void releaseViewForPointerUp() {
      this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
      this.dispatchViewReleased(this.clampMag(this.mVelocityTracker.getXVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity), this.clampMag(this.mVelocityTracker.getYVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity));
   }

   private void reportNewEdgeDrags(float var1, float var2, int var3) {
      int var4 = this.checkNewEdgeDrag(var1, var2, var3, 1);
      int var5 = var4;
      if (this.checkNewEdgeDrag(var2, var1, var3, 4)) {
         var5 = var4 | 4;
      }

      var4 = var5;
      if (this.checkNewEdgeDrag(var1, var2, var3, 2)) {
         var4 = var5 | 2;
      }

      var5 = var4;
      if (this.checkNewEdgeDrag(var2, var1, var3, 8)) {
         var5 = var4 | 8;
      }

      if (var5 != 0) {
         int[] var6 = this.mEdgeDragsInProgress;
         var6[var3] |= var5;
         this.mCallback.onEdgeDragStarted(var5, var3);
      }

   }

   private void saveInitialMotion(float var1, float var2, int var3) {
      this.ensureMotionHistorySizeForId(var3);
      float[] var4 = this.mInitialMotionX;
      this.mLastMotionX[var3] = var1;
      var4[var3] = var1;
      var4 = this.mInitialMotionY;
      this.mLastMotionY[var3] = var2;
      var4[var3] = var2;
      this.mInitialEdgesTouched[var3] = this.getEdgesTouched((int)var1, (int)var2);
      this.mPointersDown |= 1 << var3;
   }

   private void saveLastMotion(MotionEvent var1) {
      int var5 = var1.getPointerCount();

      for(int var4 = 0; var4 < var5; ++var4) {
         int var6 = var1.getPointerId(var4);
         if (this.isValidPointerForActionMove(var6)) {
            float var2 = var1.getX(var4);
            float var3 = var1.getY(var4);
            this.mLastMotionX[var6] = var2;
            this.mLastMotionY[var6] = var3;
         }
      }

   }

   public void abort() {
      this.cancel();
      if (this.mDragState == 2) {
         int var1 = this.mScroller.getCurrX();
         int var3 = this.mScroller.getCurrY();
         this.mScroller.abortAnimation();
         int var2 = this.mScroller.getCurrX();
         int var4 = this.mScroller.getCurrY();
         this.mCallback.onViewPositionChanged(this.mCapturedView, var2, var4, var2 - var1, var4 - var3);
      }

      this.setDragState(0);
   }

   protected boolean canScroll(View var1, boolean var2, int var3, int var4, int var5, int var6) {
      boolean var13 = var1 instanceof ViewGroup;
      boolean var12 = true;
      if (var13) {
         ViewGroup var14 = (ViewGroup)var1;
         int var9 = var1.getScrollX();
         int var8 = var1.getScrollY();

         for(int var7 = var14.getChildCount() - 1; var7 >= 0; --var7) {
            View var15 = var14.getChildAt(var7);
            int var11 = var5 + var9;
            if (var11 >= var15.getLeft() && var11 < var15.getRight()) {
               int var10 = var6 + var8;
               if (var10 >= var15.getTop() && var10 < var15.getBottom() && this.canScroll(var15, true, var3, var4, var11 - var15.getLeft(), var10 - var15.getTop())) {
                  return true;
               }
            }
         }
      }

      if (var2) {
         var2 = var12;
         if (var1.canScrollHorizontally(-var3)) {
            return var2;
         }

         if (var1.canScrollVertically(-var4)) {
            var2 = var12;
            return var2;
         }
      }

      var2 = false;
      return var2;
   }

   public void cancel() {
      this.mActivePointerId = -1;
      this.clearMotionHistory();
      VelocityTracker var1 = this.mVelocityTracker;
      if (var1 != null) {
         var1.recycle();
         this.mVelocityTracker = null;
      }

   }

   public void captureChildView(View var1, int var2) {
      if (var1.getParent() == this.mParentView) {
         this.mCapturedView = var1;
         this.mActivePointerId = var2;
         this.mCallback.onViewCaptured(var1, var2);
         this.setDragState(1);
      } else {
         throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.mParentView + ")");
      }
   }

   public boolean checkTouchSlop(int var1) {
      int var3 = this.mInitialMotionX.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         if (this.checkTouchSlop(var1, var2)) {
            return true;
         }
      }

      return false;
   }

   public boolean checkTouchSlop(int var1, int var2) {
      boolean var9 = this.isPointerDown(var2);
      boolean var8 = false;
      boolean var7 = false;
      boolean var6 = false;
      if (!var9) {
         return false;
      } else {
         boolean var5;
         if ((var1 & 1) == 1) {
            var5 = true;
         } else {
            var5 = false;
         }

         boolean var10;
         if ((var1 & 2) == 2) {
            var10 = true;
         } else {
            var10 = false;
         }

         float var3 = this.mLastMotionX[var2] - this.mInitialMotionX[var2];
         float var4 = this.mLastMotionY[var2] - this.mInitialMotionY[var2];
         if (var5 && var10) {
            var1 = this.mTouchSlop;
            if (var3 * var3 + var4 * var4 > (float)(var1 * var1)) {
               var6 = true;
            }

            return var6;
         } else if (var5) {
            var6 = var8;
            if (Math.abs(var3) > (float)this.mTouchSlop) {
               var6 = true;
            }

            return var6;
         } else {
            var6 = var7;
            if (var10) {
               var6 = var7;
               if (Math.abs(var4) > (float)this.mTouchSlop) {
                  var6 = true;
               }
            }

            return var6;
         }
      }
   }

   public boolean continueSettling(boolean var1) {
      int var2 = this.mDragState;
      boolean var7 = false;
      if (var2 == 2) {
         boolean var8 = this.mScroller.computeScrollOffset();
         int var4 = this.mScroller.getCurrX();
         int var5 = this.mScroller.getCurrY();
         int var3 = var4 - this.mCapturedView.getLeft();
         var2 = var5 - this.mCapturedView.getTop();
         if (var3 != 0) {
            ViewCompat.offsetLeftAndRight(this.mCapturedView, var3);
         }

         if (var2 != 0) {
            ViewCompat.offsetTopAndBottom(this.mCapturedView, var2);
         }

         if (var3 != 0 || var2 != 0) {
            this.mCallback.onViewPositionChanged(this.mCapturedView, var4, var5, var3, var2);
         }

         boolean var6 = var8;
         if (var8) {
            var6 = var8;
            if (var4 == this.mScroller.getFinalX()) {
               var6 = var8;
               if (var5 == this.mScroller.getFinalY()) {
                  this.mScroller.abortAnimation();
                  var6 = false;
               }
            }
         }

         if (!var6) {
            if (var1) {
               this.mParentView.post(this.mSetIdleRunnable);
            } else {
               this.setDragState(0);
            }
         }
      }

      var1 = var7;
      if (this.mDragState == 2) {
         var1 = true;
      }

      return var1;
   }

   public View findTopChildUnder(int var1, int var2) {
      for(int var3 = this.mParentView.getChildCount() - 1; var3 >= 0; --var3) {
         View var4 = this.mParentView.getChildAt(this.mCallback.getOrderedChildIndex(var3));
         if (var1 >= var4.getLeft() && var1 < var4.getRight() && var2 >= var4.getTop() && var2 < var4.getBottom()) {
            return var4;
         }
      }

      return null;
   }

   public void flingCapturedView(int var1, int var2, int var3, int var4) {
      if (this.mReleaseInProgress) {
         this.mScroller.fling(this.mCapturedView.getLeft(), this.mCapturedView.getTop(), (int)this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int)this.mVelocityTracker.getYVelocity(this.mActivePointerId), var1, var3, var2, var4);
         this.setDragState(2);
      } else {
         throw new IllegalStateException("Cannot flingCapturedView outside of a call to Callback#onViewReleased");
      }
   }

   public int getActivePointerId() {
      return this.mActivePointerId;
   }

   public View getCapturedView() {
      return this.mCapturedView;
   }

   public int getEdgeSize() {
      return this.mEdgeSize;
   }

   public float getMinVelocity() {
      return this.mMinVelocity;
   }

   public int getTouchSlop() {
      return this.mTouchSlop;
   }

   public int getViewDragState() {
      return this.mDragState;
   }

   public boolean isCapturedViewUnder(int var1, int var2) {
      return this.isViewUnder(this.mCapturedView, var1, var2);
   }

   public boolean isEdgeTouched(int var1) {
      int var3 = this.mInitialEdgesTouched.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         if (this.isEdgeTouched(var1, var2)) {
            return true;
         }
      }

      return false;
   }

   public boolean isEdgeTouched(int var1, int var2) {
      boolean var3;
      if (this.isPointerDown(var2) && (var1 & this.mInitialEdgesTouched[var2]) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public boolean isPointerDown(int var1) {
      int var2 = this.mPointersDown;
      boolean var3 = true;
      if ((1 << var1 & var2) == 0) {
         var3 = false;
      }

      return var3;
   }

   public boolean isViewUnder(View var1, int var2, int var3) {
      boolean var5 = false;
      if (var1 == null) {
         return false;
      } else {
         boolean var4 = var5;
         if (var2 >= var1.getLeft()) {
            var4 = var5;
            if (var2 < var1.getRight()) {
               var4 = var5;
               if (var3 >= var1.getTop()) {
                  var4 = var5;
                  if (var3 < var1.getBottom()) {
                     var4 = true;
                  }
               }
            }
         }

         return var4;
      }
   }

   public void processTouchEvent(MotionEvent var1) {
      int var9 = var1.getActionMasked();
      int var8 = var1.getActionIndex();
      if (var9 == 0) {
         this.cancel();
      }

      if (this.mVelocityTracker == null) {
         this.mVelocityTracker = VelocityTracker.obtain();
      }

      this.mVelocityTracker.addMovement(var1);
      int var7 = 0;
      int var6 = 0;
      float var2;
      float var3;
      if (var9 != 0) {
         if (var9 != 1) {
            View var10;
            if (var9 != 2) {
               if (var9 != 3) {
                  if (var9 != 5) {
                     if (var9 == 6) {
                        var7 = var1.getPointerId(var8);
                        if (this.mDragState == 1 && var7 == this.mActivePointerId) {
                           var8 = var1.getPointerCount();

                           while(true) {
                              if (var6 >= var8) {
                                 var6 = -1;
                                 break;
                              }

                              var9 = var1.getPointerId(var6);
                              if (var9 != this.mActivePointerId) {
                                 var3 = var1.getX(var6);
                                 var2 = var1.getY(var6);
                                 var10 = this.findTopChildUnder((int)var3, (int)var2);
                                 View var11 = this.mCapturedView;
                                 if (var10 == var11 && this.tryCaptureViewForDrag(var11, var9)) {
                                    var6 = this.mActivePointerId;
                                    break;
                                 }
                              }

                              ++var6;
                           }

                           if (var6 == -1) {
                              this.releaseViewForPointerUp();
                           }
                        }

                        this.clearMotionHistory(var7);
                     }
                  } else {
                     var6 = var1.getPointerId(var8);
                     var3 = var1.getX(var8);
                     var2 = var1.getY(var8);
                     this.saveInitialMotion(var3, var2, var6);
                     if (this.mDragState == 0) {
                        this.tryCaptureViewForDrag(this.findTopChildUnder((int)var3, (int)var2), var6);
                        var8 = this.mInitialEdgesTouched[var6];
                        var7 = this.mTrackingEdges;
                        if ((var8 & var7) != 0) {
                           this.mCallback.onEdgeTouched(var8 & var7, var6);
                        }
                     } else if (this.isCapturedViewUnder((int)var3, (int)var2)) {
                        this.tryCaptureViewForDrag(this.mCapturedView, var6);
                     }
                  }
               } else {
                  if (this.mDragState == 1) {
                     this.dispatchViewReleased(0.0F, 0.0F);
                  }

                  this.cancel();
               }
            } else if (this.mDragState == 1) {
               if (this.isValidPointerForActionMove(this.mActivePointerId)) {
                  var6 = var1.findPointerIndex(this.mActivePointerId);
                  var2 = var1.getX(var6);
                  var3 = var1.getY(var6);
                  float[] var13 = this.mLastMotionX;
                  var7 = this.mActivePointerId;
                  var6 = (int)(var2 - var13[var7]);
                  var7 = (int)(var3 - this.mLastMotionY[var7]);
                  this.dragTo(this.mCapturedView.getLeft() + var6, this.mCapturedView.getTop() + var7, var6, var7);
                  this.saveLastMotion(var1);
               }
            } else {
               var8 = var1.getPointerCount();

               for(var6 = var7; var6 < var8; ++var6) {
                  var7 = var1.getPointerId(var6);
                  if (this.isValidPointerForActionMove(var7)) {
                     var3 = var1.getX(var6);
                     float var5 = var1.getY(var6);
                     var2 = var3 - this.mInitialMotionX[var7];
                     float var4 = var5 - this.mInitialMotionY[var7];
                     this.reportNewEdgeDrags(var2, var4, var7);
                     if (this.mDragState == 1) {
                        break;
                     }

                     var10 = this.findTopChildUnder((int)var3, (int)var5);
                     if (this.checkTouchSlop(var10, var2, var4) && this.tryCaptureViewForDrag(var10, var7)) {
                        break;
                     }
                  }
               }

               this.saveLastMotion(var1);
            }
         } else {
            if (this.mDragState == 1) {
               this.releaseViewForPointerUp();
            }

            this.cancel();
         }
      } else {
         var3 = var1.getX();
         var2 = var1.getY();
         var6 = var1.getPointerId(0);
         View var12 = this.findTopChildUnder((int)var3, (int)var2);
         this.saveInitialMotion(var3, var2, var6);
         this.tryCaptureViewForDrag(var12, var6);
         var7 = this.mInitialEdgesTouched[var6];
         var8 = this.mTrackingEdges;
         if ((var7 & var8) != 0) {
            this.mCallback.onEdgeTouched(var7 & var8, var6);
         }
      }

   }

   void setDragState(int var1) {
      this.mParentView.removeCallbacks(this.mSetIdleRunnable);
      if (this.mDragState != var1) {
         this.mDragState = var1;
         this.mCallback.onViewDragStateChanged(var1);
         if (this.mDragState == 0) {
            this.mCapturedView = null;
         }
      }

   }

   public void setEdgeTrackingEnabled(int var1) {
      this.mTrackingEdges = var1;
   }

   public void setMinVelocity(float var1) {
      this.mMinVelocity = var1;
   }

   public boolean settleCapturedViewAt(int var1, int var2) {
      if (this.mReleaseInProgress) {
         return this.forceSettleCapturedViewAt(var1, var2, (int)this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int)this.mVelocityTracker.getYVelocity(this.mActivePointerId));
      } else {
         throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
      }
   }

   public boolean shouldInterceptTouchEvent(MotionEvent var1) {
      int var6 = var1.getActionMasked();
      int var7 = var1.getActionIndex();
      if (var6 == 0) {
         this.cancel();
      }

      if (this.mVelocityTracker == null) {
         this.mVelocityTracker = VelocityTracker.obtain();
      }

      this.mVelocityTracker.addMovement(var1);
      float var2;
      float var3;
      int var8;
      View var18;
      if (var6 != 0) {
         label108: {
            if (var6 != 1) {
               if (var6 == 2) {
                  if (this.mInitialMotionX != null && this.mInitialMotionY != null) {
                     var8 = var1.getPointerCount();

                     for(var6 = 0; var6 < var8; ++var6) {
                        int var9 = var1.getPointerId(var6);
                        if (this.isValidPointerForActionMove(var9)) {
                           float var5 = var1.getX(var6);
                           var3 = var1.getY(var6);
                           float var4 = var5 - this.mInitialMotionX[var9];
                           var2 = var3 - this.mInitialMotionY[var9];
                           View var17 = this.findTopChildUnder((int)var5, (int)var3);
                           boolean var19;
                           if (var17 != null && this.checkTouchSlop(var17, var4, var2)) {
                              var19 = true;
                           } else {
                              var19 = false;
                           }

                           if (var19) {
                              int var10 = var17.getLeft();
                              int var11 = (int)var4;
                              int var12 = this.mCallback.clampViewPositionHorizontal(var17, var10 + var11, var11);
                              var11 = var17.getTop();
                              int var13 = (int)var2;
                              int var15 = this.mCallback.clampViewPositionVertical(var17, var11 + var13, var13);
                              var13 = this.mCallback.getViewHorizontalDragRange(var17);
                              int var14 = this.mCallback.getViewVerticalDragRange(var17);
                              if ((var13 == 0 || var13 > 0 && var12 == var10) && (var14 == 0 || var14 > 0 && var15 == var11)) {
                                 break;
                              }
                           }

                           this.reportNewEdgeDrags(var4, var2, var9);
                           if (this.mDragState == 1 || var19 && this.tryCaptureViewForDrag(var17, var9)) {
                              break;
                           }
                        }
                     }

                     this.saveLastMotion(var1);
                  }
                  break label108;
               }

               if (var6 != 3) {
                  if (var6 != 5) {
                     if (var6 == 6) {
                        this.clearMotionHistory(var1.getPointerId(var7));
                     }
                  } else {
                     var6 = var1.getPointerId(var7);
                     var3 = var1.getX(var7);
                     var2 = var1.getY(var7);
                     this.saveInitialMotion(var3, var2, var6);
                     var7 = this.mDragState;
                     if (var7 == 0) {
                        var8 = this.mInitialEdgesTouched[var6];
                        var7 = this.mTrackingEdges;
                        if ((var8 & var7) != 0) {
                           this.mCallback.onEdgeTouched(var8 & var7, var6);
                        }
                     } else if (var7 == 2) {
                        var18 = this.findTopChildUnder((int)var3, (int)var2);
                        if (var18 == this.mCapturedView) {
                           this.tryCaptureViewForDrag(var18, var6);
                        }
                     }
                  }
                  break label108;
               }
            }

            this.cancel();
         }
      } else {
         var2 = var1.getX();
         var3 = var1.getY();
         var7 = var1.getPointerId(0);
         this.saveInitialMotion(var2, var3, var7);
         var18 = this.findTopChildUnder((int)var2, (int)var3);
         if (var18 == this.mCapturedView && this.mDragState == 2) {
            this.tryCaptureViewForDrag(var18, var7);
         }

         var6 = this.mInitialEdgesTouched[var7];
         var8 = this.mTrackingEdges;
         if ((var6 & var8) != 0) {
            this.mCallback.onEdgeTouched(var6 & var8, var7);
         }
      }

      boolean var16 = false;
      if (this.mDragState == 1) {
         var16 = true;
      }

      return var16;
   }

   public boolean smoothSlideViewTo(View var1, int var2, int var3) {
      this.mCapturedView = var1;
      this.mActivePointerId = -1;
      boolean var4 = this.forceSettleCapturedViewAt(var2, var3, 0, 0);
      if (!var4 && this.mDragState == 0 && this.mCapturedView != null) {
         this.mCapturedView = null;
      }

      return var4;
   }

   boolean tryCaptureViewForDrag(View var1, int var2) {
      if (var1 == this.mCapturedView && this.mActivePointerId == var2) {
         return true;
      } else if (var1 != null && this.mCallback.tryCaptureView(var1, var2)) {
         this.mActivePointerId = var2;
         this.captureChildView(var1, var2);
         return true;
      } else {
         return false;
      }
   }

   public abstract static class Callback {
      public int clampViewPositionHorizontal(View var1, int var2, int var3) {
         return 0;
      }

      public int clampViewPositionVertical(View var1, int var2, int var3) {
         return 0;
      }

      public int getOrderedChildIndex(int var1) {
         return var1;
      }

      public int getViewHorizontalDragRange(View var1) {
         return 0;
      }

      public int getViewVerticalDragRange(View var1) {
         return 0;
      }

      public void onEdgeDragStarted(int var1, int var2) {
      }

      public boolean onEdgeLock(int var1) {
         return false;
      }

      public void onEdgeTouched(int var1, int var2) {
      }

      public void onViewCaptured(View var1, int var2) {
      }

      public void onViewDragStateChanged(int var1) {
      }

      public void onViewPositionChanged(View var1, int var2, int var3, int var4, int var5) {
      }

      public void onViewReleased(View var1, float var2, float var3) {
      }

      public abstract boolean tryCaptureView(View var1, int var2);
   }
}
