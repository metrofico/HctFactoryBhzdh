package androidx.swiperefreshlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.ListView;
import androidx.core.content.ContextCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import androidx.core.widget.ListViewCompat;

public class SwipeRefreshLayout extends ViewGroup implements NestedScrollingParent, NestedScrollingChild {
   private static final int ALPHA_ANIMATION_DURATION = 300;
   private static final int ANIMATE_TO_START_DURATION = 200;
   private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
   private static final int CIRCLE_BG_LIGHT = -328966;
   static final int CIRCLE_DIAMETER = 40;
   static final int CIRCLE_DIAMETER_LARGE = 56;
   private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0F;
   public static final int DEFAULT = 1;
   private static final int DEFAULT_CIRCLE_TARGET = 64;
   public static final int DEFAULT_SLINGSHOT_DISTANCE = -1;
   private static final float DRAG_RATE = 0.5F;
   private static final int INVALID_POINTER = -1;
   public static final int LARGE = 0;
   private static final int[] LAYOUT_ATTRS = new int[]{16842766};
   private static final String LOG_TAG = "SwipeRefreshLayout";
   private static final int MAX_ALPHA = 255;
   private static final float MAX_PROGRESS_ANGLE = 0.8F;
   private static final int SCALE_DOWN_DURATION = 150;
   private static final int STARTING_PROGRESS_ALPHA = 76;
   private int mActivePointerId;
   private Animation mAlphaMaxAnimation;
   private Animation mAlphaStartAnimation;
   private final Animation mAnimateToCorrectPosition;
   private final Animation mAnimateToStartPosition;
   private OnChildScrollUpCallback mChildScrollUpCallback;
   private int mCircleDiameter;
   CircleImageView mCircleView;
   private int mCircleViewIndex;
   int mCurrentTargetOffsetTop;
   int mCustomSlingshotDistance;
   private final DecelerateInterpolator mDecelerateInterpolator;
   protected int mFrom;
   private float mInitialDownY;
   private float mInitialMotionY;
   private boolean mIsBeingDragged;
   OnRefreshListener mListener;
   private int mMediumAnimationDuration;
   private boolean mNestedScrollInProgress;
   private final NestedScrollingChildHelper mNestedScrollingChildHelper;
   private final NestedScrollingParentHelper mNestedScrollingParentHelper;
   boolean mNotify;
   protected int mOriginalOffsetTop;
   private final int[] mParentOffsetInWindow;
   private final int[] mParentScrollConsumed;
   CircularProgressDrawable mProgress;
   private Animation.AnimationListener mRefreshListener;
   boolean mRefreshing;
   private boolean mReturningToStart;
   boolean mScale;
   private Animation mScaleAnimation;
   private Animation mScaleDownAnimation;
   private Animation mScaleDownToStartAnimation;
   int mSpinnerOffsetEnd;
   float mStartingScale;
   private View mTarget;
   private float mTotalDragDistance;
   private float mTotalUnconsumed;
   private int mTouchSlop;
   boolean mUsingCustomStart;

   public SwipeRefreshLayout(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public SwipeRefreshLayout(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mRefreshing = false;
      this.mTotalDragDistance = -1.0F;
      this.mParentScrollConsumed = new int[2];
      this.mParentOffsetInWindow = new int[2];
      this.mActivePointerId = -1;
      this.mCircleViewIndex = -1;
      this.mRefreshListener = new Animation.AnimationListener(this) {
         final SwipeRefreshLayout this$0;

         {
            this.this$0 = var1;
         }

         public void onAnimationEnd(Animation var1) {
            if (this.this$0.mRefreshing) {
               this.this$0.mProgress.setAlpha(255);
               this.this$0.mProgress.start();
               if (this.this$0.mNotify && this.this$0.mListener != null) {
                  this.this$0.mListener.onRefresh();
               }

               SwipeRefreshLayout var2 = this.this$0;
               var2.mCurrentTargetOffsetTop = var2.mCircleView.getTop();
            } else {
               this.this$0.reset();
            }

         }

         public void onAnimationRepeat(Animation var1) {
         }

         public void onAnimationStart(Animation var1) {
         }
      };
      this.mAnimateToCorrectPosition = new Animation(this) {
         final SwipeRefreshLayout this$0;

         {
            this.this$0 = var1;
         }

         public void applyTransformation(float var1, Transformation var2) {
            int var3;
            if (!this.this$0.mUsingCustomStart) {
               var3 = this.this$0.mSpinnerOffsetEnd - Math.abs(this.this$0.mOriginalOffsetTop);
            } else {
               var3 = this.this$0.mSpinnerOffsetEnd;
            }

            int var4 = this.this$0.mFrom;
            var3 = (int)((float)(var3 - this.this$0.mFrom) * var1);
            int var5 = this.this$0.mCircleView.getTop();
            this.this$0.setTargetOffsetTopAndBottom(var4 + var3 - var5);
            this.this$0.mProgress.setArrowScale(1.0F - var1);
         }
      };
      this.mAnimateToStartPosition = new Animation(this) {
         final SwipeRefreshLayout this$0;

         {
            this.this$0 = var1;
         }

         public void applyTransformation(float var1, Transformation var2) {
            this.this$0.moveToStart(var1);
         }
      };
      this.mTouchSlop = ViewConfiguration.get(var1).getScaledTouchSlop();
      this.mMediumAnimationDuration = this.getResources().getInteger(17694721);
      this.setWillNotDraw(false);
      this.mDecelerateInterpolator = new DecelerateInterpolator(2.0F);
      DisplayMetrics var4 = this.getResources().getDisplayMetrics();
      this.mCircleDiameter = (int)(var4.density * 40.0F);
      this.createProgressView();
      this.setChildrenDrawingOrderEnabled(true);
      int var3 = (int)(var4.density * 64.0F);
      this.mSpinnerOffsetEnd = var3;
      this.mTotalDragDistance = (float)var3;
      this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
      this.mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
      this.setNestedScrollingEnabled(true);
      var3 = -this.mCircleDiameter;
      this.mCurrentTargetOffsetTop = var3;
      this.mOriginalOffsetTop = var3;
      this.moveToStart(1.0F);
      TypedArray var5 = var1.obtainStyledAttributes(var2, LAYOUT_ATTRS);
      this.setEnabled(var5.getBoolean(0, true));
      var5.recycle();
   }

   private void animateOffsetToCorrectPosition(int var1, Animation.AnimationListener var2) {
      this.mFrom = var1;
      this.mAnimateToCorrectPosition.reset();
      this.mAnimateToCorrectPosition.setDuration(200L);
      this.mAnimateToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
      if (var2 != null) {
         this.mCircleView.setAnimationListener(var2);
      }

      this.mCircleView.clearAnimation();
      this.mCircleView.startAnimation(this.mAnimateToCorrectPosition);
   }

   private void animateOffsetToStartPosition(int var1, Animation.AnimationListener var2) {
      if (this.mScale) {
         this.startScaleDownReturnToStartAnimation(var1, var2);
      } else {
         this.mFrom = var1;
         this.mAnimateToStartPosition.reset();
         this.mAnimateToStartPosition.setDuration(200L);
         this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
         if (var2 != null) {
            this.mCircleView.setAnimationListener(var2);
         }

         this.mCircleView.clearAnimation();
         this.mCircleView.startAnimation(this.mAnimateToStartPosition);
      }

   }

   private void createProgressView() {
      this.mCircleView = new CircleImageView(this.getContext(), -328966);
      CircularProgressDrawable var1 = new CircularProgressDrawable(this.getContext());
      this.mProgress = var1;
      var1.setStyle(1);
      this.mCircleView.setImageDrawable(this.mProgress);
      this.mCircleView.setVisibility(8);
      this.addView(this.mCircleView);
   }

   private void ensureTarget() {
      if (this.mTarget == null) {
         for(int var1 = 0; var1 < this.getChildCount(); ++var1) {
            View var2 = this.getChildAt(var1);
            if (!var2.equals(this.mCircleView)) {
               this.mTarget = var2;
               break;
            }
         }
      }

   }

   private void finishSpinner(float var1) {
      if (var1 > this.mTotalDragDistance) {
         this.setRefreshing(true, true);
      } else {
         this.mRefreshing = false;
         this.mProgress.setStartEndTrim(0.0F, 0.0F);
         Animation.AnimationListener var2 = null;
         if (!this.mScale) {
            var2 = new Animation.AnimationListener(this) {
               final SwipeRefreshLayout this$0;

               {
                  this.this$0 = var1;
               }

               public void onAnimationEnd(Animation var1) {
                  if (!this.this$0.mScale) {
                     this.this$0.startScaleDownAnimation((Animation.AnimationListener)null);
                  }

               }

               public void onAnimationRepeat(Animation var1) {
               }

               public void onAnimationStart(Animation var1) {
               }
            };
         }

         this.animateOffsetToStartPosition(this.mCurrentTargetOffsetTop, var2);
         this.mProgress.setArrowEnabled(false);
      }

   }

   private boolean isAnimationRunning(Animation var1) {
      boolean var2;
      if (var1 != null && var1.hasStarted() && !var1.hasEnded()) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private void moveSpinner(float var1) {
      this.mProgress.setArrowEnabled(true);
      float var6 = Math.min(1.0F, Math.abs(var1 / this.mTotalDragDistance));
      float var5 = (float)Math.max((double)var6 - 0.4, 0.0) * 5.0F / 3.0F;
      float var8 = Math.abs(var1);
      float var7 = this.mTotalDragDistance;
      int var9 = this.mCustomSlingshotDistance;
      if (var9 <= 0) {
         if (this.mUsingCustomStart) {
            var9 = this.mSpinnerOffsetEnd - this.mOriginalOffsetTop;
         } else {
            var9 = this.mSpinnerOffsetEnd;
         }
      }

      float var4 = (float)var9;
      double var2 = (double)(Math.max(0.0F, Math.min(var8 - var7, var4 * 2.0F) / var4) / 4.0F);
      var7 = (float)(var2 - Math.pow(var2, 2.0)) * 2.0F;
      int var10 = this.mOriginalOffsetTop;
      var9 = (int)(var4 * var6 + var4 * var7 * 2.0F);
      if (this.mCircleView.getVisibility() != 0) {
         this.mCircleView.setVisibility(0);
      }

      if (!this.mScale) {
         this.mCircleView.setScaleX(1.0F);
         this.mCircleView.setScaleY(1.0F);
      }

      if (this.mScale) {
         this.setAnimationProgress(Math.min(1.0F, var1 / this.mTotalDragDistance));
      }

      if (var1 < this.mTotalDragDistance) {
         if (this.mProgress.getAlpha() > 76 && !this.isAnimationRunning(this.mAlphaStartAnimation)) {
            this.startProgressAlphaStartAnimation();
         }
      } else if (this.mProgress.getAlpha() < 255 && !this.isAnimationRunning(this.mAlphaMaxAnimation)) {
         this.startProgressAlphaMaxAnimation();
      }

      this.mProgress.setStartEndTrim(0.0F, Math.min(0.8F, var5 * 0.8F));
      this.mProgress.setArrowScale(Math.min(1.0F, var5));
      this.mProgress.setProgressRotation((var5 * 0.4F - 0.25F + var7 * 2.0F) * 0.5F);
      this.setTargetOffsetTopAndBottom(var10 + var9 - this.mCurrentTargetOffsetTop);
   }

   private void onSecondaryPointerUp(MotionEvent var1) {
      int var2 = var1.getActionIndex();
      if (var1.getPointerId(var2) == this.mActivePointerId) {
         byte var3;
         if (var2 == 0) {
            var3 = 1;
         } else {
            var3 = 0;
         }

         this.mActivePointerId = var1.getPointerId(var3);
      }

   }

   private void setColorViewAlpha(int var1) {
      this.mCircleView.getBackground().setAlpha(var1);
      this.mProgress.setAlpha(var1);
   }

   private void setRefreshing(boolean var1, boolean var2) {
      if (this.mRefreshing != var1) {
         this.mNotify = var2;
         this.ensureTarget();
         this.mRefreshing = var1;
         if (var1) {
            this.animateOffsetToCorrectPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
         } else {
            this.startScaleDownAnimation(this.mRefreshListener);
         }
      }

   }

   private Animation startAlphaAnimation(int var1, int var2) {
      Animation var3 = new Animation(this, var1, var2) {
         final SwipeRefreshLayout this$0;
         final int val$endingAlpha;
         final int val$startingAlpha;

         {
            this.this$0 = var1;
            this.val$startingAlpha = var2;
            this.val$endingAlpha = var3;
         }

         public void applyTransformation(float var1, Transformation var2) {
            CircularProgressDrawable var4 = this.this$0.mProgress;
            int var3 = this.val$startingAlpha;
            var4.setAlpha((int)((float)var3 + (float)(this.val$endingAlpha - var3) * var1));
         }
      };
      var3.setDuration(300L);
      this.mCircleView.setAnimationListener((Animation.AnimationListener)null);
      this.mCircleView.clearAnimation();
      this.mCircleView.startAnimation(var3);
      return var3;
   }

   private void startDragging(float var1) {
      float var2 = this.mInitialDownY;
      int var3 = this.mTouchSlop;
      if (var1 - var2 > (float)var3 && !this.mIsBeingDragged) {
         this.mInitialMotionY = var2 + (float)var3;
         this.mIsBeingDragged = true;
         this.mProgress.setAlpha(76);
      }

   }

   private void startProgressAlphaMaxAnimation() {
      this.mAlphaMaxAnimation = this.startAlphaAnimation(this.mProgress.getAlpha(), 255);
   }

   private void startProgressAlphaStartAnimation() {
      this.mAlphaStartAnimation = this.startAlphaAnimation(this.mProgress.getAlpha(), 76);
   }

   private void startScaleDownReturnToStartAnimation(int var1, Animation.AnimationListener var2) {
      this.mFrom = var1;
      this.mStartingScale = this.mCircleView.getScaleX();
      Animation var3 = new Animation(this) {
         final SwipeRefreshLayout this$0;

         {
            this.this$0 = var1;
         }

         public void applyTransformation(float var1, Transformation var2) {
            float var4 = this.this$0.mStartingScale;
            float var3 = -this.this$0.mStartingScale;
            this.this$0.setAnimationProgress(var4 + var3 * var1);
            this.this$0.moveToStart(var1);
         }
      };
      this.mScaleDownToStartAnimation = var3;
      var3.setDuration(150L);
      if (var2 != null) {
         this.mCircleView.setAnimationListener(var2);
      }

      this.mCircleView.clearAnimation();
      this.mCircleView.startAnimation(this.mScaleDownToStartAnimation);
   }

   private void startScaleUpAnimation(Animation.AnimationListener var1) {
      this.mCircleView.setVisibility(0);
      this.mProgress.setAlpha(255);
      Animation var2 = new Animation(this) {
         final SwipeRefreshLayout this$0;

         {
            this.this$0 = var1;
         }

         public void applyTransformation(float var1, Transformation var2) {
            this.this$0.setAnimationProgress(var1);
         }
      };
      this.mScaleAnimation = var2;
      var2.setDuration((long)this.mMediumAnimationDuration);
      if (var1 != null) {
         this.mCircleView.setAnimationListener(var1);
      }

      this.mCircleView.clearAnimation();
      this.mCircleView.startAnimation(this.mScaleAnimation);
   }

   public boolean canChildScrollUp() {
      OnChildScrollUpCallback var1 = this.mChildScrollUpCallback;
      if (var1 != null) {
         return var1.canChildScrollUp(this, this.mTarget);
      } else {
         View var2 = this.mTarget;
         return var2 instanceof ListView ? ListViewCompat.canScrollList((ListView)var2, -1) : var2.canScrollVertically(-1);
      }
   }

   public boolean dispatchNestedFling(float var1, float var2, boolean var3) {
      return this.mNestedScrollingChildHelper.dispatchNestedFling(var1, var2, var3);
   }

   public boolean dispatchNestedPreFling(float var1, float var2) {
      return this.mNestedScrollingChildHelper.dispatchNestedPreFling(var1, var2);
   }

   public boolean dispatchNestedPreScroll(int var1, int var2, int[] var3, int[] var4) {
      return this.mNestedScrollingChildHelper.dispatchNestedPreScroll(var1, var2, var3, var4);
   }

   public boolean dispatchNestedScroll(int var1, int var2, int var3, int var4, int[] var5) {
      return this.mNestedScrollingChildHelper.dispatchNestedScroll(var1, var2, var3, var4, var5);
   }

   protected int getChildDrawingOrder(int var1, int var2) {
      int var3 = this.mCircleViewIndex;
      if (var3 < 0) {
         return var2;
      } else if (var2 == var1 - 1) {
         return var3;
      } else {
         var1 = var2;
         if (var2 >= var3) {
            var1 = var2 + 1;
         }

         return var1;
      }
   }

   public int getNestedScrollAxes() {
      return this.mNestedScrollingParentHelper.getNestedScrollAxes();
   }

   public int getProgressCircleDiameter() {
      return this.mCircleDiameter;
   }

   public int getProgressViewEndOffset() {
      return this.mSpinnerOffsetEnd;
   }

   public int getProgressViewStartOffset() {
      return this.mOriginalOffsetTop;
   }

   public boolean hasNestedScrollingParent() {
      return this.mNestedScrollingChildHelper.hasNestedScrollingParent();
   }

   public boolean isNestedScrollingEnabled() {
      return this.mNestedScrollingChildHelper.isNestedScrollingEnabled();
   }

   public boolean isRefreshing() {
      return this.mRefreshing;
   }

   void moveToStart(float var1) {
      int var2 = this.mFrom;
      this.setTargetOffsetTopAndBottom(var2 + (int)((float)(this.mOriginalOffsetTop - var2) * var1) - this.mCircleView.getTop());
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      this.reset();
   }

   public boolean onInterceptTouchEvent(MotionEvent var1) {
      this.ensureTarget();
      int var2 = var1.getActionMasked();
      if (this.mReturningToStart && var2 == 0) {
         this.mReturningToStart = false;
      }

      if (this.isEnabled() && !this.mReturningToStart && !this.canChildScrollUp() && !this.mRefreshing && !this.mNestedScrollInProgress) {
         if (var2 != 0) {
            if (var2 != 1) {
               if (var2 == 2) {
                  var2 = this.mActivePointerId;
                  if (var2 == -1) {
                     Log.e(LOG_TAG, "Got ACTION_MOVE event but don't have an active pointer id.");
                     return false;
                  }

                  var2 = var1.findPointerIndex(var2);
                  if (var2 < 0) {
                     return false;
                  }

                  this.startDragging(var1.getY(var2));
                  return this.mIsBeingDragged;
               }

               if (var2 != 3) {
                  if (var2 == 6) {
                     this.onSecondaryPointerUp(var1);
                  }

                  return this.mIsBeingDragged;
               }
            }

            this.mIsBeingDragged = false;
            this.mActivePointerId = -1;
         } else {
            this.setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCircleView.getTop());
            var2 = var1.getPointerId(0);
            this.mActivePointerId = var2;
            this.mIsBeingDragged = false;
            var2 = var1.findPointerIndex(var2);
            if (var2 < 0) {
               return false;
            }

            this.mInitialDownY = var1.getY(var2);
         }

         return this.mIsBeingDragged;
      } else {
         return false;
      }
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      var3 = this.getMeasuredWidth();
      var2 = this.getMeasuredHeight();
      if (this.getChildCount() != 0) {
         if (this.mTarget == null) {
            this.ensureTarget();
         }

         View var6 = this.mTarget;
         if (var6 != null) {
            var5 = this.getPaddingLeft();
            var4 = this.getPaddingTop();
            var6.layout(var5, var4, var3 - this.getPaddingLeft() - this.getPaddingRight() + var5, var2 - this.getPaddingTop() - this.getPaddingBottom() + var4);
            var4 = this.mCircleView.getMeasuredWidth();
            var2 = this.mCircleView.getMeasuredHeight();
            CircleImageView var7 = this.mCircleView;
            var3 /= 2;
            var5 = var4 / 2;
            var4 = this.mCurrentTargetOffsetTop;
            var7.layout(var3 - var5, var4, var3 + var5, var2 + var4);
         }
      }
   }

   public void onMeasure(int var1, int var2) {
      super.onMeasure(var1, var2);
      if (this.mTarget == null) {
         this.ensureTarget();
      }

      View var3 = this.mTarget;
      if (var3 != null) {
         var3.measure(MeasureSpec.makeMeasureSpec(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight(), 1073741824), MeasureSpec.makeMeasureSpec(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom(), 1073741824));
         this.mCircleView.measure(MeasureSpec.makeMeasureSpec(this.mCircleDiameter, 1073741824), MeasureSpec.makeMeasureSpec(this.mCircleDiameter, 1073741824));
         this.mCircleViewIndex = -1;

         for(var1 = 0; var1 < this.getChildCount(); ++var1) {
            if (this.getChildAt(var1) == this.mCircleView) {
               this.mCircleViewIndex = var1;
               break;
            }
         }

      }
   }

   public boolean onNestedFling(View var1, float var2, float var3, boolean var4) {
      return this.dispatchNestedFling(var2, var3, var4);
   }

   public boolean onNestedPreFling(View var1, float var2, float var3) {
      return this.dispatchNestedPreFling(var2, var3);
   }

   public void onNestedPreScroll(View var1, int var2, int var3, int[] var4) {
      if (var3 > 0) {
         float var5 = this.mTotalUnconsumed;
         if (var5 > 0.0F) {
            float var6 = (float)var3;
            if (var6 > var5) {
               var4[1] = var3 - (int)var5;
               this.mTotalUnconsumed = 0.0F;
            } else {
               this.mTotalUnconsumed = var5 - var6;
               var4[1] = var3;
            }

            this.moveSpinner(this.mTotalUnconsumed);
         }
      }

      if (this.mUsingCustomStart && var3 > 0 && this.mTotalUnconsumed == 0.0F && Math.abs(var3 - var4[1]) > 0) {
         this.mCircleView.setVisibility(8);
      }

      int[] var7 = this.mParentScrollConsumed;
      if (this.dispatchNestedPreScroll(var2 - var4[0], var3 - var4[1], var7, (int[])null)) {
         var4[0] += var7[0];
         var4[1] += var7[1];
      }

   }

   public void onNestedScroll(View var1, int var2, int var3, int var4, int var5) {
      this.dispatchNestedScroll(var2, var3, var4, var5, this.mParentOffsetInWindow);
      var2 = var5 + this.mParentOffsetInWindow[1];
      if (var2 < 0 && !this.canChildScrollUp()) {
         float var6 = this.mTotalUnconsumed + (float)Math.abs(var2);
         this.mTotalUnconsumed = var6;
         this.moveSpinner(var6);
      }

   }

   public void onNestedScrollAccepted(View var1, View var2, int var3) {
      this.mNestedScrollingParentHelper.onNestedScrollAccepted(var1, var2, var3);
      this.startNestedScroll(var3 & 2);
      this.mTotalUnconsumed = 0.0F;
      this.mNestedScrollInProgress = true;
   }

   public boolean onStartNestedScroll(View var1, View var2, int var3) {
      boolean var4;
      if (this.isEnabled() && !this.mReturningToStart && !this.mRefreshing && (var3 & 2) != 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      return var4;
   }

   public void onStopNestedScroll(View var1) {
      this.mNestedScrollingParentHelper.onStopNestedScroll(var1);
      this.mNestedScrollInProgress = false;
      float var2 = this.mTotalUnconsumed;
      if (var2 > 0.0F) {
         this.finishSpinner(var2);
         this.mTotalUnconsumed = 0.0F;
      }

      this.stopNestedScroll();
   }

   public boolean onTouchEvent(MotionEvent var1) {
      int var4 = var1.getActionMasked();
      if (this.mReturningToStart && var4 == 0) {
         this.mReturningToStart = false;
      }

      if (this.isEnabled() && !this.mReturningToStart && !this.canChildScrollUp() && !this.mRefreshing && !this.mNestedScrollInProgress) {
         if (var4 != 0) {
            float var2;
            if (var4 == 1) {
               var4 = var1.findPointerIndex(this.mActivePointerId);
               if (var4 < 0) {
                  Log.e(LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
                  return false;
               }

               if (this.mIsBeingDragged) {
                  float var3 = var1.getY(var4);
                  var2 = this.mInitialMotionY;
                  this.mIsBeingDragged = false;
                  this.finishSpinner((var3 - var2) * 0.5F);
               }

               this.mActivePointerId = -1;
               return false;
            }

            if (var4 != 2) {
               if (var4 == 3) {
                  return false;
               }

               if (var4 != 5) {
                  if (var4 == 6) {
                     this.onSecondaryPointerUp(var1);
                  }
               } else {
                  var4 = var1.getActionIndex();
                  if (var4 < 0) {
                     Log.e(LOG_TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                     return false;
                  }

                  this.mActivePointerId = var1.getPointerId(var4);
               }
            } else {
               var4 = var1.findPointerIndex(this.mActivePointerId);
               if (var4 < 0) {
                  Log.e(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                  return false;
               }

               var2 = var1.getY(var4);
               this.startDragging(var2);
               if (this.mIsBeingDragged) {
                  var2 = (var2 - this.mInitialMotionY) * 0.5F;
                  if (!(var2 > 0.0F)) {
                     return false;
                  }

                  this.moveSpinner(var2);
               }
            }
         } else {
            this.mActivePointerId = var1.getPointerId(0);
            this.mIsBeingDragged = false;
         }

         return true;
      } else {
         return false;
      }
   }

   public void requestDisallowInterceptTouchEvent(boolean var1) {
      if (VERSION.SDK_INT >= 21 || !(this.mTarget instanceof AbsListView)) {
         View var2 = this.mTarget;
         if (var2 == null || ViewCompat.isNestedScrollingEnabled(var2)) {
            super.requestDisallowInterceptTouchEvent(var1);
         }
      }

   }

   void reset() {
      this.mCircleView.clearAnimation();
      this.mProgress.stop();
      this.mCircleView.setVisibility(8);
      this.setColorViewAlpha(255);
      if (this.mScale) {
         this.setAnimationProgress(0.0F);
      } else {
         this.setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCurrentTargetOffsetTop);
      }

      this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
   }

   void setAnimationProgress(float var1) {
      this.mCircleView.setScaleX(var1);
      this.mCircleView.setScaleY(var1);
   }

   @Deprecated
   public void setColorScheme(int... var1) {
      this.setColorSchemeResources(var1);
   }

   public void setColorSchemeColors(int... var1) {
      this.ensureTarget();
      this.mProgress.setColorSchemeColors(var1);
   }

   public void setColorSchemeResources(int... var1) {
      Context var3 = this.getContext();
      int[] var4 = new int[var1.length];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var4[var2] = ContextCompat.getColor(var3, var1[var2]);
      }

      this.setColorSchemeColors(var4);
   }

   public void setDistanceToTriggerSync(int var1) {
      this.mTotalDragDistance = (float)var1;
   }

   public void setEnabled(boolean var1) {
      super.setEnabled(var1);
      if (!var1) {
         this.reset();
      }

   }

   public void setNestedScrollingEnabled(boolean var1) {
      this.mNestedScrollingChildHelper.setNestedScrollingEnabled(var1);
   }

   public void setOnChildScrollUpCallback(OnChildScrollUpCallback var1) {
      this.mChildScrollUpCallback = var1;
   }

   public void setOnRefreshListener(OnRefreshListener var1) {
      this.mListener = var1;
   }

   @Deprecated
   public void setProgressBackgroundColor(int var1) {
      this.setProgressBackgroundColorSchemeResource(var1);
   }

   public void setProgressBackgroundColorSchemeColor(int var1) {
      this.mCircleView.setBackgroundColor(var1);
   }

   public void setProgressBackgroundColorSchemeResource(int var1) {
      this.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(this.getContext(), var1));
   }

   public void setProgressViewEndTarget(boolean var1, int var2) {
      this.mSpinnerOffsetEnd = var2;
      this.mScale = var1;
      this.mCircleView.invalidate();
   }

   public void setProgressViewOffset(boolean var1, int var2, int var3) {
      this.mScale = var1;
      this.mOriginalOffsetTop = var2;
      this.mSpinnerOffsetEnd = var3;
      this.mUsingCustomStart = true;
      this.reset();
      this.mRefreshing = false;
   }

   public void setRefreshing(boolean var1) {
      if (var1 && this.mRefreshing != var1) {
         this.mRefreshing = var1;
         int var2;
         if (!this.mUsingCustomStart) {
            var2 = this.mSpinnerOffsetEnd + this.mOriginalOffsetTop;
         } else {
            var2 = this.mSpinnerOffsetEnd;
         }

         this.setTargetOffsetTopAndBottom(var2 - this.mCurrentTargetOffsetTop);
         this.mNotify = false;
         this.startScaleUpAnimation(this.mRefreshListener);
      } else {
         this.setRefreshing(var1, false);
      }

   }

   public void setSize(int var1) {
      if (var1 == 0 || var1 == 1) {
         DisplayMetrics var2 = this.getResources().getDisplayMetrics();
         if (var1 == 0) {
            this.mCircleDiameter = (int)(var2.density * 56.0F);
         } else {
            this.mCircleDiameter = (int)(var2.density * 40.0F);
         }

         this.mCircleView.setImageDrawable((Drawable)null);
         this.mProgress.setStyle(var1);
         this.mCircleView.setImageDrawable(this.mProgress);
      }
   }

   public void setSlingshotDistance(int var1) {
      this.mCustomSlingshotDistance = var1;
   }

   void setTargetOffsetTopAndBottom(int var1) {
      this.mCircleView.bringToFront();
      ViewCompat.offsetTopAndBottom(this.mCircleView, var1);
      this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
   }

   public boolean startNestedScroll(int var1) {
      return this.mNestedScrollingChildHelper.startNestedScroll(var1);
   }

   void startScaleDownAnimation(Animation.AnimationListener var1) {
      Animation var2 = new Animation(this) {
         final SwipeRefreshLayout this$0;

         {
            this.this$0 = var1;
         }

         public void applyTransformation(float var1, Transformation var2) {
            this.this$0.setAnimationProgress(1.0F - var1);
         }
      };
      this.mScaleDownAnimation = var2;
      var2.setDuration(150L);
      this.mCircleView.setAnimationListener(var1);
      this.mCircleView.clearAnimation();
      this.mCircleView.startAnimation(this.mScaleDownAnimation);
   }

   public void stopNestedScroll() {
      this.mNestedScrollingChildHelper.stopNestedScroll();
   }

   public interface OnChildScrollUpCallback {
      boolean canChildScrollUp(SwipeRefreshLayout var1, View var2);
   }

   public interface OnRefreshListener {
      void onRefresh();
   }
}
