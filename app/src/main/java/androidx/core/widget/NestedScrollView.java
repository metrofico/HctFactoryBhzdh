package androidx.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View.MeasureSpec;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import androidx.core.R;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.NestedScrollingChild3;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ScrollingView;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityRecordCompat;
import java.util.ArrayList;

public class NestedScrollView extends FrameLayout implements NestedScrollingParent3, NestedScrollingChild3, ScrollingView {
   private static final AccessibilityDelegate ACCESSIBILITY_DELEGATE = new AccessibilityDelegate();
   static final int ANIMATED_SCROLL_GAP = 250;
   private static final int DEFAULT_SMOOTH_SCROLL_DURATION = 250;
   private static final int INVALID_POINTER = -1;
   static final float MAX_SCROLL_FACTOR = 0.5F;
   private static final int[] SCROLLVIEW_STYLEABLE = new int[]{16843130};
   private static final String TAG = "NestedScrollView";
   private int mActivePointerId;
   private final NestedScrollingChildHelper mChildHelper;
   private View mChildToScrollTo;
   public EdgeEffect mEdgeGlowBottom;
   public EdgeEffect mEdgeGlowTop;
   private boolean mFillViewport;
   private boolean mIsBeingDragged;
   private boolean mIsLaidOut;
   private boolean mIsLayoutDirty;
   private int mLastMotionY;
   private long mLastScroll;
   private int mLastScrollerY;
   private int mMaximumVelocity;
   private int mMinimumVelocity;
   private int mNestedYOffset;
   private OnScrollChangeListener mOnScrollChangeListener;
   private final NestedScrollingParentHelper mParentHelper;
   private SavedState mSavedState;
   private final int[] mScrollConsumed;
   private final int[] mScrollOffset;
   private OverScroller mScroller;
   private boolean mSmoothScrollingEnabled;
   private final Rect mTempRect;
   private int mTouchSlop;
   private VelocityTracker mVelocityTracker;
   private float mVerticalScrollFactor;

   public NestedScrollView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public NestedScrollView(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.nestedScrollViewStyle);
   }

   public NestedScrollView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mTempRect = new Rect();
      this.mIsLayoutDirty = true;
      this.mIsLaidOut = false;
      this.mChildToScrollTo = null;
      this.mIsBeingDragged = false;
      this.mSmoothScrollingEnabled = true;
      this.mActivePointerId = -1;
      this.mScrollOffset = new int[2];
      this.mScrollConsumed = new int[2];
      this.mEdgeGlowTop = EdgeEffectCompat.create(var1, var2);
      this.mEdgeGlowBottom = EdgeEffectCompat.create(var1, var2);
      this.initScrollView();
      TypedArray var4 = var1.obtainStyledAttributes(var2, SCROLLVIEW_STYLEABLE, var3, 0);
      this.setFillViewport(var4.getBoolean(0, false));
      var4.recycle();
      this.mParentHelper = new NestedScrollingParentHelper(this);
      this.mChildHelper = new NestedScrollingChildHelper(this);
      this.setNestedScrollingEnabled(true);
      ViewCompat.setAccessibilityDelegate(this, ACCESSIBILITY_DELEGATE);
   }

   private void abortAnimatedScroll() {
      this.mScroller.abortAnimation();
      this.stopNestedScroll(1);
   }

   private boolean canScroll() {
      int var1 = this.getChildCount();
      boolean var3 = false;
      boolean var2 = var3;
      if (var1 > 0) {
         View var4 = this.getChildAt(0);
         FrameLayout.LayoutParams var5 = (FrameLayout.LayoutParams)var4.getLayoutParams();
         var2 = var3;
         if (var4.getHeight() + var5.topMargin + var5.bottomMargin > this.getHeight() - this.getPaddingTop() - this.getPaddingBottom()) {
            var2 = true;
         }
      }

      return var2;
   }

   private static int clamp(int var0, int var1, int var2) {
      if (var1 < var2 && var0 >= 0) {
         return var1 + var0 > var2 ? var2 - var1 : var0;
      } else {
         return 0;
      }
   }

   private void doScrollY(int var1) {
      if (var1 != 0) {
         if (this.mSmoothScrollingEnabled) {
            this.smoothScrollBy(0, var1);
         } else {
            this.scrollBy(0, var1);
         }
      }

   }

   private boolean edgeEffectFling(int var1) {
      boolean var2;
      if (EdgeEffectCompat.getDistance(this.mEdgeGlowTop) != 0.0F) {
         this.mEdgeGlowTop.onAbsorb(var1);
      } else {
         if (EdgeEffectCompat.getDistance(this.mEdgeGlowBottom) == 0.0F) {
            var2 = false;
            return var2;
         }

         this.mEdgeGlowBottom.onAbsorb(-var1);
      }

      var2 = true;
      return var2;
   }

   private void endDrag() {
      this.mIsBeingDragged = false;
      this.recycleVelocityTracker();
      this.stopNestedScroll(0);
      this.mEdgeGlowTop.onRelease();
      this.mEdgeGlowBottom.onRelease();
   }

   private View findFocusableViewInBounds(boolean var1, int var2, int var3) {
      ArrayList var14 = this.getFocusables(2);
      int var9 = var14.size();
      View var13 = null;
      int var6 = 0;

      boolean var5;
      for(boolean var7 = false; var6 < var9; var7 = var5) {
         View var12 = (View)var14.get(var6);
         int var10 = var12.getTop();
         int var8 = var12.getBottom();
         View var11 = var13;
         var5 = var7;
         if (var2 < var8) {
            var11 = var13;
            var5 = var7;
            if (var10 < var3) {
               boolean var4;
               if (var2 < var10 && var8 < var3) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               if (var13 == null) {
                  var11 = var12;
                  var5 = var4;
               } else {
                  label77: {
                     boolean var15;
                     if (var1 && var10 < var13.getTop() || !var1 && var8 > var13.getBottom()) {
                        var15 = true;
                     } else {
                        var15 = false;
                     }

                     if (var7) {
                        var11 = var13;
                        var5 = var7;
                        if (!var4) {
                           break label77;
                        }

                        var11 = var13;
                        var5 = var7;
                        if (!var15) {
                           break label77;
                        }
                     } else {
                        if (var4) {
                           var11 = var12;
                           var5 = true;
                           break label77;
                        }

                        var11 = var13;
                        var5 = var7;
                        if (!var15) {
                           break label77;
                        }
                     }

                     var11 = var12;
                     var5 = var7;
                  }
               }
            }
         }

         ++var6;
         var13 = var11;
      }

      return var13;
   }

   private float getVerticalScrollFactorCompat() {
      if (this.mVerticalScrollFactor == 0.0F) {
         TypedValue var2 = new TypedValue();
         Context var1 = this.getContext();
         if (!var1.getTheme().resolveAttribute(16842829, var2, true)) {
            throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
         }

         this.mVerticalScrollFactor = var2.getDimension(var1.getResources().getDisplayMetrics());
      }

      return this.mVerticalScrollFactor;
   }

   private boolean inChild(int var1, int var2) {
      int var3 = this.getChildCount();
      boolean var5 = false;
      boolean var4 = var5;
      if (var3 > 0) {
         var3 = this.getScrollY();
         View var6 = this.getChildAt(0);
         var4 = var5;
         if (var2 >= var6.getTop() - var3) {
            var4 = var5;
            if (var2 < var6.getBottom() - var3) {
               var4 = var5;
               if (var1 >= var6.getLeft()) {
                  var4 = var5;
                  if (var1 < var6.getRight()) {
                     var4 = true;
                  }
               }
            }
         }
      }

      return var4;
   }

   private void initOrResetVelocityTracker() {
      VelocityTracker var1 = this.mVelocityTracker;
      if (var1 == null) {
         this.mVelocityTracker = VelocityTracker.obtain();
      } else {
         var1.clear();
      }

   }

   private void initScrollView() {
      this.mScroller = new OverScroller(this.getContext());
      this.setFocusable(true);
      this.setDescendantFocusability(262144);
      this.setWillNotDraw(false);
      ViewConfiguration var1 = ViewConfiguration.get(this.getContext());
      this.mTouchSlop = var1.getScaledTouchSlop();
      this.mMinimumVelocity = var1.getScaledMinimumFlingVelocity();
      this.mMaximumVelocity = var1.getScaledMaximumFlingVelocity();
   }

   private void initVelocityTrackerIfNotExists() {
      if (this.mVelocityTracker == null) {
         this.mVelocityTracker = VelocityTracker.obtain();
      }

   }

   private boolean isOffScreen(View var1) {
      return this.isWithinDeltaOfScreen(var1, 0, this.getHeight()) ^ true;
   }

   private static boolean isViewDescendantOf(View var0, View var1) {
      boolean var2 = true;
      if (var0 == var1) {
         return true;
      } else {
         ViewParent var3 = var0.getParent();
         if (!(var3 instanceof ViewGroup) || !isViewDescendantOf((View)var3, var1)) {
            var2 = false;
         }

         return var2;
      }
   }

   private boolean isWithinDeltaOfScreen(View var1, int var2, int var3) {
      var1.getDrawingRect(this.mTempRect);
      this.offsetDescendantRectToMyCoords(var1, this.mTempRect);
      boolean var4;
      if (this.mTempRect.bottom + var2 >= this.getScrollY() && this.mTempRect.top - var2 <= this.getScrollY() + var3) {
         var4 = true;
      } else {
         var4 = false;
      }

      return var4;
   }

   private void onNestedScrollInternal(int var1, int var2, int[] var3) {
      int var4 = this.getScrollY();
      this.scrollBy(0, var1);
      var4 = this.getScrollY() - var4;
      if (var3 != null) {
         var3[1] += var4;
      }

      this.mChildHelper.dispatchNestedScroll(0, var4, 0, var1 - var4, (int[])null, var2, var3);
   }

   private void onSecondaryPointerUp(MotionEvent var1) {
      int var2 = var1.getActionIndex();
      if (var1.getPointerId(var2) == this.mActivePointerId) {
         byte var4;
         if (var2 == 0) {
            var4 = 1;
         } else {
            var4 = 0;
         }

         this.mLastMotionY = (int)var1.getY(var4);
         this.mActivePointerId = var1.getPointerId(var4);
         VelocityTracker var3 = this.mVelocityTracker;
         if (var3 != null) {
            var3.clear();
         }
      }

   }

   private void recycleVelocityTracker() {
      VelocityTracker var1 = this.mVelocityTracker;
      if (var1 != null) {
         var1.recycle();
         this.mVelocityTracker = null;
      }

   }

   private int releaseVerticalGlow(int var1, float var2) {
      float var3 = var2 / (float)this.getWidth();
      float var4 = (float)var1 / (float)this.getHeight();
      float var5 = EdgeEffectCompat.getDistance(this.mEdgeGlowTop);
      var2 = 0.0F;
      if (var5 != 0.0F) {
         var3 = -EdgeEffectCompat.onPullDistance(this.mEdgeGlowTop, -var4, var3);
         var2 = var3;
         if (EdgeEffectCompat.getDistance(this.mEdgeGlowTop) == 0.0F) {
            this.mEdgeGlowTop.onRelease();
            var2 = var3;
         }
      } else if (EdgeEffectCompat.getDistance(this.mEdgeGlowBottom) != 0.0F) {
         var3 = EdgeEffectCompat.onPullDistance(this.mEdgeGlowBottom, var4, 1.0F - var3);
         var2 = var3;
         if (EdgeEffectCompat.getDistance(this.mEdgeGlowBottom) == 0.0F) {
            this.mEdgeGlowBottom.onRelease();
            var2 = var3;
         }
      }

      var1 = Math.round(var2 * (float)this.getHeight());
      if (var1 != 0) {
         this.invalidate();
      }

      return var1;
   }

   private void runAnimatedScroll(boolean var1) {
      if (var1) {
         this.startNestedScroll(2, 1);
      } else {
         this.stopNestedScroll(1);
      }

      this.mLastScrollerY = this.getScrollY();
      ViewCompat.postInvalidateOnAnimation(this);
   }

   private boolean scrollAndFocus(int var1, int var2, int var3) {
      int var5 = this.getHeight();
      int var4 = this.getScrollY();
      var5 += var4;
      boolean var7 = false;
      boolean var6;
      if (var1 == 33) {
         var6 = true;
      } else {
         var6 = false;
      }

      View var9 = this.findFocusableViewInBounds(var6, var2, var3);
      Object var8 = var9;
      if (var9 == null) {
         var8 = this;
      }

      if (var2 >= var4 && var3 <= var5) {
         var6 = var7;
      } else {
         if (var6) {
            var2 -= var4;
         } else {
            var2 = var3 - var5;
         }

         this.doScrollY(var2);
         var6 = true;
      }

      if (var8 != this.findFocus()) {
         ((View)var8).requestFocus(var1);
      }

      return var6;
   }

   private void scrollToChild(View var1) {
      var1.getDrawingRect(this.mTempRect);
      this.offsetDescendantRectToMyCoords(var1, this.mTempRect);
      int var2 = this.computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
      if (var2 != 0) {
         this.scrollBy(0, var2);
      }

   }

   private boolean scrollToChildRect(Rect var1, boolean var2) {
      int var3 = this.computeScrollDeltaToGetChildRectOnScreen(var1);
      boolean var4;
      if (var3 != 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      if (var4) {
         if (var2) {
            this.scrollBy(0, var3);
         } else {
            this.smoothScrollBy(0, var3);
         }
      }

      return var4;
   }

   private void smoothScrollBy(int var1, int var2, int var3, boolean var4) {
      if (this.getChildCount() != 0) {
         if (AnimationUtils.currentAnimationTimeMillis() - this.mLastScroll > 250L) {
            View var11 = this.getChildAt(0);
            FrameLayout.LayoutParams var12 = (FrameLayout.LayoutParams)var11.getLayoutParams();
            int var5 = var11.getHeight();
            int var8 = var12.topMargin;
            int var7 = var12.bottomMargin;
            int var10 = this.getHeight();
            int var6 = this.getPaddingTop();
            int var9 = this.getPaddingBottom();
            var1 = this.getScrollY();
            var2 = Math.max(0, Math.min(var2 + var1, Math.max(0, var5 + var8 + var7 - (var10 - var6 - var9))));
            this.mScroller.startScroll(this.getScrollX(), var1, 0, var2 - var1, var3);
            this.runAnimatedScroll(var4);
         } else {
            if (!this.mScroller.isFinished()) {
               this.abortAnimatedScroll();
            }

            this.scrollBy(var1, var2);
         }

         this.mLastScroll = AnimationUtils.currentAnimationTimeMillis();
      }
   }

   private boolean stopGlowAnimations(MotionEvent var1) {
      float var2 = EdgeEffectCompat.getDistance(this.mEdgeGlowTop);
      boolean var4 = true;
      boolean var3;
      if (var2 != 0.0F) {
         EdgeEffectCompat.onPullDistance(this.mEdgeGlowTop, 0.0F, var1.getY() / (float)this.getHeight());
         var3 = true;
      } else {
         var3 = false;
      }

      if (EdgeEffectCompat.getDistance(this.mEdgeGlowBottom) != 0.0F) {
         EdgeEffectCompat.onPullDistance(this.mEdgeGlowBottom, 0.0F, 1.0F - var1.getY() / (float)this.getHeight());
         var3 = var4;
      }

      return var3;
   }

   public void addView(View var1) {
      if (this.getChildCount() <= 0) {
         super.addView(var1);
      } else {
         throw new IllegalStateException("ScrollView can host only one direct child");
      }
   }

   public void addView(View var1, int var2) {
      if (this.getChildCount() <= 0) {
         super.addView(var1, var2);
      } else {
         throw new IllegalStateException("ScrollView can host only one direct child");
      }
   }

   public void addView(View var1, int var2, ViewGroup.LayoutParams var3) {
      if (this.getChildCount() <= 0) {
         super.addView(var1, var2, var3);
      } else {
         throw new IllegalStateException("ScrollView can host only one direct child");
      }
   }

   public void addView(View var1, ViewGroup.LayoutParams var2) {
      if (this.getChildCount() <= 0) {
         super.addView(var1, var2);
      } else {
         throw new IllegalStateException("ScrollView can host only one direct child");
      }
   }

   public boolean arrowScroll(int var1) {
      View var5 = this.findFocus();
      View var4 = var5;
      if (var5 == this) {
         var4 = null;
      }

      var5 = FocusFinder.getInstance().findNextFocus(this, var4, var1);
      int var3 = this.getMaxScrollAmount();
      if (var5 != null && this.isWithinDeltaOfScreen(var5, var3, this.getHeight())) {
         var5.getDrawingRect(this.mTempRect);
         this.offsetDescendantRectToMyCoords(var5, this.mTempRect);
         this.doScrollY(this.computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
         var5.requestFocus(var1);
      } else {
         int var2;
         if (var1 == 33 && this.getScrollY() < var3) {
            var2 = this.getScrollY();
         } else {
            var2 = var3;
            if (var1 == 130) {
               var2 = var3;
               if (this.getChildCount() > 0) {
                  var5 = this.getChildAt(0);
                  FrameLayout.LayoutParams var6 = (FrameLayout.LayoutParams)var5.getLayoutParams();
                  var2 = Math.min(var5.getBottom() + var6.bottomMargin - (this.getScrollY() + this.getHeight() - this.getPaddingBottom()), var3);
               }
            }
         }

         if (var2 == 0) {
            return false;
         }

         if (var1 != 130) {
            var2 = -var2;
         }

         this.doScrollY(var2);
      }

      if (var4 != null && var4.isFocused() && this.isOffScreen(var4)) {
         var1 = this.getDescendantFocusability();
         this.setDescendantFocusability(131072);
         this.requestFocus();
         this.setDescendantFocusability(var1);
      }

      return true;
   }

   public int computeHorizontalScrollExtent() {
      return super.computeHorizontalScrollExtent();
   }

   public int computeHorizontalScrollOffset() {
      return super.computeHorizontalScrollOffset();
   }

   public int computeHorizontalScrollRange() {
      return super.computeHorizontalScrollRange();
   }

   public void computeScroll() {
      if (!this.mScroller.isFinished()) {
         this.mScroller.computeScrollOffset();
         int var2 = this.mScroller.getCurrY();
         int var1 = var2 - this.mLastScrollerY;
         this.mLastScrollerY = var2;
         int[] var6 = this.mScrollConsumed;
         boolean var3 = false;
         var6[1] = 0;
         this.dispatchNestedPreScroll(0, var1, var6, (int[])null, 1);
         var2 = var1 - this.mScrollConsumed[1];
         int var4 = this.getScrollRange();
         var1 = var2;
         if (var2 != 0) {
            var1 = this.getScrollY();
            this.overScrollByCompat(0, var2, this.getScrollX(), var1, 0, var4, 0, 0, false);
            var1 = this.getScrollY() - var1;
            var2 -= var1;
            var6 = this.mScrollConsumed;
            var6[1] = 0;
            this.dispatchNestedScroll(0, var1, 0, var2, this.mScrollOffset, 1, var6);
            var1 = var2 - this.mScrollConsumed[1];
         }

         if (var1 != 0) {
            boolean var7;
            label37: {
               int var5 = this.getOverScrollMode();
               if (var5 != 0) {
                  var7 = var3;
                  if (var5 != 1) {
                     break label37;
                  }

                  var7 = var3;
                  if (var4 <= 0) {
                     break label37;
                  }
               }

               var7 = true;
            }

            if (var7) {
               if (var1 < 0) {
                  if (this.mEdgeGlowTop.isFinished()) {
                     this.mEdgeGlowTop.onAbsorb((int)this.mScroller.getCurrVelocity());
                  }
               } else if (this.mEdgeGlowBottom.isFinished()) {
                  this.mEdgeGlowBottom.onAbsorb((int)this.mScroller.getCurrVelocity());
               }
            }

            this.abortAnimatedScroll();
         }

         if (!this.mScroller.isFinished()) {
            ViewCompat.postInvalidateOnAnimation(this);
         } else {
            this.stopNestedScroll(1);
         }

      }
   }

   protected int computeScrollDeltaToGetChildRectOnScreen(Rect var1) {
      int var2 = this.getChildCount();
      byte var6 = 0;
      if (var2 == 0) {
         return 0;
      } else {
         int var7 = this.getHeight();
         var2 = this.getScrollY();
         int var5 = var2 + var7;
         int var4 = this.getVerticalFadingEdgeLength();
         int var3 = var2;
         if (var1.top > 0) {
            var3 = var2 + var4;
         }

         View var9 = this.getChildAt(0);
         FrameLayout.LayoutParams var8 = (FrameLayout.LayoutParams)var9.getLayoutParams();
         if (var1.bottom < var9.getHeight() + var8.topMargin + var8.bottomMargin) {
            var4 = var5 - var4;
         } else {
            var4 = var5;
         }

         if (var1.bottom > var4 && var1.top > var3) {
            if (var1.height() > var7) {
               var2 = var1.top - var3;
            } else {
               var2 = var1.bottom - var4;
            }

            var2 = Math.min(var2 + 0, var9.getBottom() + var8.bottomMargin - var5);
         } else {
            var2 = var6;
            if (var1.top < var3) {
               var2 = var6;
               if (var1.bottom < var4) {
                  if (var1.height() > var7) {
                     var2 = 0 - (var4 - var1.bottom);
                  } else {
                     var2 = 0 - (var3 - var1.top);
                  }

                  var2 = Math.max(var2, -this.getScrollY());
               }
            }
         }

         return var2;
      }
   }

   public int computeVerticalScrollExtent() {
      return super.computeVerticalScrollExtent();
   }

   public int computeVerticalScrollOffset() {
      return Math.max(0, super.computeVerticalScrollOffset());
   }

   public int computeVerticalScrollRange() {
      int var2 = this.getChildCount();
      int var1 = this.getHeight() - this.getPaddingBottom() - this.getPaddingTop();
      if (var2 == 0) {
         return var1;
      } else {
         View var6 = this.getChildAt(0);
         FrameLayout.LayoutParams var5 = (FrameLayout.LayoutParams)var6.getLayoutParams();
         var2 = var6.getBottom() + var5.bottomMargin;
         int var3 = this.getScrollY();
         int var4 = Math.max(0, var2 - var1);
         if (var3 < 0) {
            var1 = var2 - var3;
         } else {
            var1 = var2;
            if (var3 > var4) {
               var1 = var2 + (var3 - var4);
            }
         }

         return var1;
      }
   }

   public boolean dispatchKeyEvent(KeyEvent var1) {
      boolean var2;
      if (!super.dispatchKeyEvent(var1) && !this.executeKeyEvent(var1)) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   public boolean dispatchNestedFling(float var1, float var2, boolean var3) {
      return this.mChildHelper.dispatchNestedFling(var1, var2, var3);
   }

   public boolean dispatchNestedPreFling(float var1, float var2) {
      return this.mChildHelper.dispatchNestedPreFling(var1, var2);
   }

   public boolean dispatchNestedPreScroll(int var1, int var2, int[] var3, int[] var4) {
      return this.dispatchNestedPreScroll(var1, var2, var3, var4, 0);
   }

   public boolean dispatchNestedPreScroll(int var1, int var2, int[] var3, int[] var4, int var5) {
      return this.mChildHelper.dispatchNestedPreScroll(var1, var2, var3, var4, var5);
   }

   public void dispatchNestedScroll(int var1, int var2, int var3, int var4, int[] var5, int var6, int[] var7) {
      this.mChildHelper.dispatchNestedScroll(var1, var2, var3, var4, var5, var6, var7);
   }

   public boolean dispatchNestedScroll(int var1, int var2, int var3, int var4, int[] var5) {
      return this.mChildHelper.dispatchNestedScroll(var1, var2, var3, var4, var5);
   }

   public boolean dispatchNestedScroll(int var1, int var2, int var3, int var4, int[] var5, int var6) {
      return this.mChildHelper.dispatchNestedScroll(var1, var2, var3, var4, var5, var6);
   }

   public void draw(Canvas var1) {
      super.draw(var1);
      int var9 = this.getScrollY();
      boolean var11 = this.mEdgeGlowTop.isFinished();
      byte var6 = 0;
      int var2;
      int var3;
      int var4;
      int var5;
      int var7;
      int var8;
      int var10;
      if (!var11) {
         var10 = var1.save();
         var2 = this.getWidth();
         var8 = this.getHeight();
         var7 = Math.min(0, var9);
         if (VERSION.SDK_INT >= 21 && !this.getClipToPadding()) {
            var3 = 0;
         } else {
            var2 -= this.getPaddingLeft() + this.getPaddingRight();
            var3 = this.getPaddingLeft() + 0;
         }

         var5 = var8;
         var4 = var7;
         if (VERSION.SDK_INT >= 21) {
            var5 = var8;
            var4 = var7;
            if (this.getClipToPadding()) {
               var5 = var8 - (this.getPaddingTop() + this.getPaddingBottom());
               var4 = var7 + this.getPaddingTop();
            }
         }

         var1.translate((float)var3, (float)var4);
         this.mEdgeGlowTop.setSize(var2, var5);
         if (this.mEdgeGlowTop.draw(var1)) {
            ViewCompat.postInvalidateOnAnimation(this);
         }

         var1.restoreToCount(var10);
      }

      if (!this.mEdgeGlowBottom.isFinished()) {
         label40: {
            var10 = var1.save();
            var4 = this.getWidth();
            var7 = this.getHeight();
            var8 = Math.max(this.getScrollRange(), var9) + var7;
            if (VERSION.SDK_INT >= 21) {
               var3 = var6;
               var2 = var4;
               if (!this.getClipToPadding()) {
                  break label40;
               }
            }

            var2 = var4 - (this.getPaddingLeft() + this.getPaddingRight());
            var3 = 0 + this.getPaddingLeft();
         }

         var5 = var8;
         var4 = var7;
         if (VERSION.SDK_INT >= 21) {
            var5 = var8;
            var4 = var7;
            if (this.getClipToPadding()) {
               var4 = var7 - (this.getPaddingTop() + this.getPaddingBottom());
               var5 = var8 - this.getPaddingBottom();
            }
         }

         var1.translate((float)(var3 - var2), (float)var5);
         var1.rotate(180.0F, (float)var2, 0.0F);
         this.mEdgeGlowBottom.setSize(var2, var4);
         if (this.mEdgeGlowBottom.draw(var1)) {
            ViewCompat.postInvalidateOnAnimation(this);
         }

         var1.restoreToCount(var10);
      }

   }

   public boolean executeKeyEvent(KeyEvent var1) {
      this.mTempRect.setEmpty();
      boolean var4 = this.canScroll();
      boolean var5 = false;
      boolean var6 = false;
      short var2 = 130;
      if (!var4) {
         var4 = var6;
         if (this.isFocused()) {
            var4 = var6;
            if (var1.getKeyCode() != 4) {
               View var7 = this.findFocus();
               View var8 = var7;
               if (var7 == this) {
                  var8 = null;
               }

               var8 = FocusFinder.getInstance().findNextFocus(this, var8, 130);
               var4 = var6;
               if (var8 != null) {
                  var4 = var6;
                  if (var8 != this) {
                     var4 = var6;
                     if (var8.requestFocus(130)) {
                        var4 = true;
                     }
                  }
               }
            }
         }

         return var4;
      } else {
         var4 = var5;
         if (var1.getAction() == 0) {
            int var3 = var1.getKeyCode();
            if (var3 != 19) {
               if (var3 != 20) {
                  if (var3 != 62) {
                     var4 = var5;
                  } else {
                     if (var1.isShiftPressed()) {
                        var2 = 33;
                     }

                     this.pageScroll(var2);
                     var4 = var5;
                  }
               } else if (!var1.isAltPressed()) {
                  var4 = this.arrowScroll(130);
               } else {
                  var4 = this.fullScroll(130);
               }
            } else if (!var1.isAltPressed()) {
               var4 = this.arrowScroll(33);
            } else {
               var4 = this.fullScroll(33);
            }
         }

         return var4;
      }
   }

   public void fling(int var1) {
      if (this.getChildCount() > 0) {
         this.mScroller.fling(this.getScrollX(), this.getScrollY(), 0, var1, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
         this.runAnimatedScroll(true);
      }

   }

   public boolean fullScroll(int var1) {
      boolean var2;
      if (var1 == 130) {
         var2 = true;
      } else {
         var2 = false;
      }

      int var3 = this.getHeight();
      this.mTempRect.top = 0;
      this.mTempRect.bottom = var3;
      if (var2) {
         int var6 = this.getChildCount();
         if (var6 > 0) {
            View var4 = this.getChildAt(var6 - 1);
            FrameLayout.LayoutParams var5 = (FrameLayout.LayoutParams)var4.getLayoutParams();
            this.mTempRect.bottom = var4.getBottom() + var5.bottomMargin + this.getPaddingBottom();
            Rect var7 = this.mTempRect;
            var7.top = var7.bottom - var3;
         }
      }

      return this.scrollAndFocus(var1, this.mTempRect.top, this.mTempRect.bottom);
   }

   protected float getBottomFadingEdgeStrength() {
      if (this.getChildCount() == 0) {
         return 0.0F;
      } else {
         View var5 = this.getChildAt(0);
         FrameLayout.LayoutParams var4 = (FrameLayout.LayoutParams)var5.getLayoutParams();
         int var1 = this.getVerticalFadingEdgeLength();
         int var2 = this.getHeight();
         int var3 = this.getPaddingBottom();
         var2 = var5.getBottom() + var4.bottomMargin - this.getScrollY() - (var2 - var3);
         return var2 < var1 ? (float)var2 / (float)var1 : 1.0F;
      }
   }

   public int getMaxScrollAmount() {
      return (int)((float)this.getHeight() * 0.5F);
   }

   public int getNestedScrollAxes() {
      return this.mParentHelper.getNestedScrollAxes();
   }

   int getScrollRange() {
      int var2 = this.getChildCount();
      int var1 = 0;
      if (var2 > 0) {
         View var4 = this.getChildAt(0);
         FrameLayout.LayoutParams var3 = (FrameLayout.LayoutParams)var4.getLayoutParams();
         var1 = Math.max(0, var4.getHeight() + var3.topMargin + var3.bottomMargin - (this.getHeight() - this.getPaddingTop() - this.getPaddingBottom()));
      }

      return var1;
   }

   protected float getTopFadingEdgeStrength() {
      if (this.getChildCount() == 0) {
         return 0.0F;
      } else {
         int var2 = this.getVerticalFadingEdgeLength();
         int var1 = this.getScrollY();
         return var1 < var2 ? (float)var1 / (float)var2 : 1.0F;
      }
   }

   public boolean hasNestedScrollingParent() {
      return this.hasNestedScrollingParent(0);
   }

   public boolean hasNestedScrollingParent(int var1) {
      return this.mChildHelper.hasNestedScrollingParent(var1);
   }

   public boolean isFillViewport() {
      return this.mFillViewport;
   }

   public boolean isNestedScrollingEnabled() {
      return this.mChildHelper.isNestedScrollingEnabled();
   }

   public boolean isSmoothScrollingEnabled() {
      return this.mSmoothScrollingEnabled;
   }

   protected void measureChild(View var1, int var2, int var3) {
      ViewGroup.LayoutParams var4 = var1.getLayoutParams();
      var1.measure(getChildMeasureSpec(var2, this.getPaddingLeft() + this.getPaddingRight(), var4.width), MeasureSpec.makeMeasureSpec(0, 0));
   }

   protected void measureChildWithMargins(View var1, int var2, int var3, int var4, int var5) {
      ViewGroup.MarginLayoutParams var6 = (ViewGroup.MarginLayoutParams)var1.getLayoutParams();
      var1.measure(getChildMeasureSpec(var2, this.getPaddingLeft() + this.getPaddingRight() + var6.leftMargin + var6.rightMargin + var3, var6.width), MeasureSpec.makeMeasureSpec(var6.topMargin + var6.bottomMargin, 0));
   }

   public void onAttachedToWindow() {
      super.onAttachedToWindow();
      this.mIsLaidOut = false;
   }

   public boolean onGenericMotionEvent(MotionEvent var1) {
      if ((var1.getSource() & 2) != 0 && var1.getAction() == 8 && !this.mIsBeingDragged) {
         float var2 = var1.getAxisValue(9);
         if (var2 != 0.0F) {
            int var4 = (int)(var2 * this.getVerticalScrollFactorCompat());
            int var3 = this.getScrollRange();
            int var5 = this.getScrollY();
            var4 = var5 - var4;
            if (var4 < 0) {
               var3 = 0;
            } else if (var4 <= var3) {
               var3 = var4;
            }

            if (var3 != var5) {
               super.scrollTo(this.getScrollX(), var3);
               return true;
            }
         }
      }

      return false;
   }

   public boolean onInterceptTouchEvent(MotionEvent var1) {
      int var2 = var1.getAction();
      boolean var6 = true;
      boolean var5 = true;
      if (var2 == 2 && this.mIsBeingDragged) {
         return true;
      } else {
         var2 &= 255;
         if (var2 != 0) {
            if (var2 != 1) {
               if (var2 == 2) {
                  int var3 = this.mActivePointerId;
                  if (var3 != -1) {
                     var2 = var1.findPointerIndex(var3);
                     if (var2 == -1) {
                        Log.e("NestedScrollView", "Invalid pointerId=" + var3 + " in onInterceptTouchEvent");
                     } else {
                        var2 = (int)var1.getY(var2);
                        if (Math.abs(var2 - this.mLastMotionY) > this.mTouchSlop && (2 & this.getNestedScrollAxes()) == 0) {
                           this.mIsBeingDragged = true;
                           this.mLastMotionY = var2;
                           this.initVelocityTrackerIfNotExists();
                           this.mVelocityTracker.addMovement(var1);
                           this.mNestedYOffset = 0;
                           ViewParent var7 = this.getParent();
                           if (var7 != null) {
                              var7.requestDisallowInterceptTouchEvent(true);
                           }

                           return this.mIsBeingDragged;
                        }
                     }

                     return this.mIsBeingDragged;
                  }

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
            this.recycleVelocityTracker();
            if (this.mScroller.springBack(this.getScrollX(), this.getScrollY(), 0, 0, 0, this.getScrollRange())) {
               ViewCompat.postInvalidateOnAnimation(this);
            }

            this.stopNestedScroll(0);
         } else {
            var2 = (int)var1.getY();
            boolean var4;
            if (!this.inChild((int)var1.getX(), var2)) {
               var4 = var5;
               if (!this.stopGlowAnimations(var1)) {
                  if (!this.mScroller.isFinished()) {
                     var4 = var5;
                  } else {
                     var4 = false;
                  }
               }

               this.mIsBeingDragged = var4;
               this.recycleVelocityTracker();
            } else {
               this.mLastMotionY = var2;
               this.mActivePointerId = var1.getPointerId(0);
               this.initOrResetVelocityTracker();
               this.mVelocityTracker.addMovement(var1);
               this.mScroller.computeScrollOffset();
               var4 = var6;
               if (!this.stopGlowAnimations(var1)) {
                  if (!this.mScroller.isFinished()) {
                     var4 = var6;
                  } else {
                     var4 = false;
                  }
               }

               this.mIsBeingDragged = var4;
               this.startNestedScroll(2, 0);
            }
         }

         return this.mIsBeingDragged;
      }
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      super.onLayout(var1, var2, var3, var4, var5);
      var2 = 0;
      this.mIsLayoutDirty = false;
      View var8 = this.mChildToScrollTo;
      if (var8 != null && isViewDescendantOf(var8, this)) {
         this.scrollToChild(this.mChildToScrollTo);
      }

      this.mChildToScrollTo = null;
      if (!this.mIsLaidOut) {
         if (this.mSavedState != null) {
            this.scrollTo(this.getScrollX(), this.mSavedState.scrollPosition);
            this.mSavedState = null;
         }

         if (this.getChildCount() > 0) {
            View var9 = this.getChildAt(0);
            FrameLayout.LayoutParams var10 = (FrameLayout.LayoutParams)var9.getLayoutParams();
            var2 = var9.getMeasuredHeight() + var10.topMargin + var10.bottomMargin;
         }

         int var7 = this.getPaddingTop();
         int var6 = this.getPaddingBottom();
         var4 = this.getScrollY();
         var2 = clamp(var4, var5 - var3 - var7 - var6, var2);
         if (var2 != var4) {
            this.scrollTo(this.getScrollX(), var2);
         }
      }

      this.scrollTo(this.getScrollX(), this.getScrollY());
      this.mIsLaidOut = true;
   }

   protected void onMeasure(int var1, int var2) {
      super.onMeasure(var1, var2);
      if (this.mFillViewport) {
         if (MeasureSpec.getMode(var2) != 0) {
            if (this.getChildCount() > 0) {
               View var5 = this.getChildAt(0);
               FrameLayout.LayoutParams var4 = (FrameLayout.LayoutParams)var5.getLayoutParams();
               var2 = var5.getMeasuredHeight();
               int var3 = this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom() - var4.topMargin - var4.bottomMargin;
               if (var2 < var3) {
                  var5.measure(getChildMeasureSpec(var1, this.getPaddingLeft() + this.getPaddingRight() + var4.leftMargin + var4.rightMargin, var4.width), MeasureSpec.makeMeasureSpec(var3, 1073741824));
               }
            }

         }
      }
   }

   public boolean onNestedFling(View var1, float var2, float var3, boolean var4) {
      if (!var4) {
         this.dispatchNestedFling(0.0F, var3, true);
         this.fling((int)var3);
         return true;
      } else {
         return false;
      }
   }

   public boolean onNestedPreFling(View var1, float var2, float var3) {
      return this.dispatchNestedPreFling(var2, var3);
   }

   public void onNestedPreScroll(View var1, int var2, int var3, int[] var4) {
      this.onNestedPreScroll(var1, var2, var3, var4, 0);
   }

   public void onNestedPreScroll(View var1, int var2, int var3, int[] var4, int var5) {
      this.dispatchNestedPreScroll(var2, var3, var4, (int[])null, var5);
   }

   public void onNestedScroll(View var1, int var2, int var3, int var4, int var5) {
      this.onNestedScrollInternal(var5, 0, (int[])null);
   }

   public void onNestedScroll(View var1, int var2, int var3, int var4, int var5, int var6) {
      this.onNestedScrollInternal(var5, var6, (int[])null);
   }

   public void onNestedScroll(View var1, int var2, int var3, int var4, int var5, int var6, int[] var7) {
      this.onNestedScrollInternal(var5, var6, var7);
   }

   public void onNestedScrollAccepted(View var1, View var2, int var3) {
      this.onNestedScrollAccepted(var1, var2, var3, 0);
   }

   public void onNestedScrollAccepted(View var1, View var2, int var3, int var4) {
      this.mParentHelper.onNestedScrollAccepted(var1, var2, var3, var4);
      this.startNestedScroll(2, var4);
   }

   protected void onOverScrolled(int var1, int var2, boolean var3, boolean var4) {
      super.scrollTo(var1, var2);
   }

   protected boolean onRequestFocusInDescendants(int var1, Rect var2) {
      int var3;
      if (var1 == 2) {
         var3 = 130;
      } else {
         var3 = var1;
         if (var1 == 1) {
            var3 = 33;
         }
      }

      View var4;
      if (var2 == null) {
         var4 = FocusFinder.getInstance().findNextFocus(this, (View)null, var3);
      } else {
         var4 = FocusFinder.getInstance().findNextFocusFromRect(this, var2, var3);
      }

      if (var4 == null) {
         return false;
      } else {
         return this.isOffScreen(var4) ? false : var4.requestFocus(var3, var2);
      }
   }

   protected void onRestoreInstanceState(Parcelable var1) {
      if (!(var1 instanceof SavedState)) {
         super.onRestoreInstanceState(var1);
      } else {
         SavedState var2 = (SavedState)var1;
         super.onRestoreInstanceState(var2.getSuperState());
         this.mSavedState = var2;
         this.requestLayout();
      }
   }

   protected Parcelable onSaveInstanceState() {
      SavedState var1 = new SavedState(super.onSaveInstanceState());
      var1.scrollPosition = this.getScrollY();
      return var1;
   }

   protected void onScrollChanged(int var1, int var2, int var3, int var4) {
      super.onScrollChanged(var1, var2, var3, var4);
      OnScrollChangeListener var5 = this.mOnScrollChangeListener;
      if (var5 != null) {
         var5.onScrollChange(this, var1, var2, var3, var4);
      }

   }

   protected void onSizeChanged(int var1, int var2, int var3, int var4) {
      super.onSizeChanged(var1, var2, var3, var4);
      View var5 = this.findFocus();
      if (var5 != null && this != var5 && this.isWithinDeltaOfScreen(var5, 0, var4)) {
         var5.getDrawingRect(this.mTempRect);
         this.offsetDescendantRectToMyCoords(var5, this.mTempRect);
         this.doScrollY(this.computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
      }

   }

   public boolean onStartNestedScroll(View var1, View var2, int var3) {
      return this.onStartNestedScroll(var1, var2, var3, 0);
   }

   public boolean onStartNestedScroll(View var1, View var2, int var3, int var4) {
      boolean var5;
      if ((var3 & 2) != 0) {
         var5 = true;
      } else {
         var5 = false;
      }

      return var5;
   }

   public void onStopNestedScroll(View var1) {
      this.onStopNestedScroll(var1, 0);
   }

   public void onStopNestedScroll(View var1, int var2) {
      this.mParentHelper.onStopNestedScroll(var1, var2);
      this.stopNestedScroll(var2);
   }

   public boolean onTouchEvent(MotionEvent var1) {
      this.initVelocityTrackerIfNotExists();
      int var3 = var1.getActionMasked();
      boolean var6 = false;
      if (var3 == 0) {
         this.mNestedYOffset = 0;
      }

      MotionEvent var12 = MotionEvent.obtain(var1);
      var12.offsetLocation(0.0F, (float)this.mNestedYOffset);
      ViewParent var13;
      VelocityTracker var14;
      if (var3 != 0) {
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 != 3) {
                  if (var3 != 5) {
                     if (var3 == 6) {
                        this.onSecondaryPointerUp(var1);
                        this.mLastMotionY = (int)var1.getY(var1.findPointerIndex(this.mActivePointerId));
                     }
                  } else {
                     var3 = var1.getActionIndex();
                     this.mLastMotionY = (int)var1.getY(var3);
                     this.mActivePointerId = var1.getPointerId(var3);
                  }
               } else {
                  if (this.mIsBeingDragged && this.getChildCount() > 0 && this.mScroller.springBack(this.getScrollX(), this.getScrollY(), 0, 0, 0, this.getScrollRange())) {
                     ViewCompat.postInvalidateOnAnimation(this);
                  }

                  this.mActivePointerId = -1;
                  this.endDrag();
               }
            } else {
               int var7 = var1.findPointerIndex(this.mActivePointerId);
               if (var7 == -1) {
                  Log.e("NestedScrollView", "Invalid pointerId=" + this.mActivePointerId + " in onTouchEvent");
               } else {
                  int var5 = (int)var1.getY(var7);
                  var3 = this.mLastMotionY - var5;
                  int var4 = var3 - this.releaseVerticalGlow(var3, var1.getX(var7));
                  var3 = var4;
                  if (!this.mIsBeingDragged) {
                     var3 = var4;
                     if (Math.abs(var4) > this.mTouchSlop) {
                        var13 = this.getParent();
                        if (var13 != null) {
                           var13.requestDisallowInterceptTouchEvent(true);
                        }

                        this.mIsBeingDragged = true;
                        if (var4 > 0) {
                           var3 = var4 - this.mTouchSlop;
                        } else {
                           var3 = var4 + this.mTouchSlop;
                        }
                     }
                  }

                  if (this.mIsBeingDragged) {
                     var4 = var3;
                     if (this.dispatchNestedPreScroll(0, var3, this.mScrollConsumed, this.mScrollOffset, 0)) {
                        var4 = var3 - this.mScrollConsumed[1];
                        this.mNestedYOffset += this.mScrollOffset[1];
                     }

                     this.mLastMotionY = var5 - this.mScrollOffset[1];
                     int var9 = this.getScrollY();
                     int var8 = this.getScrollRange();
                     var3 = this.getOverScrollMode();
                     boolean var15;
                     if (var3 == 0 || var3 == 1 && var8 > 0) {
                        var15 = true;
                     } else {
                        var15 = false;
                     }

                     boolean var16;
                     if (this.overScrollByCompat(0, var4, 0, this.getScrollY(), 0, var8, 0, 0, true) && !this.hasNestedScrollingParent(0)) {
                        var16 = true;
                     } else {
                        var16 = false;
                     }

                     int var10 = this.getScrollY() - var9;
                     int[] var17 = this.mScrollConsumed;
                     var17[1] = 0;
                     this.dispatchNestedScroll(0, var10, 0, var4 - var10, this.mScrollOffset, 0, var17);
                     var10 = this.mLastMotionY;
                     int var11 = this.mScrollOffset[1];
                     this.mLastMotionY = var10 - var11;
                     this.mNestedYOffset += var11;
                     if (var15) {
                        var4 -= this.mScrollConsumed[1];
                        var5 = var9 + var4;
                        if (var5 < 0) {
                           EdgeEffectCompat.onPullDistance(this.mEdgeGlowTop, (float)(-var4) / (float)this.getHeight(), var1.getX(var7) / (float)this.getWidth());
                           if (!this.mEdgeGlowBottom.isFinished()) {
                              this.mEdgeGlowBottom.onRelease();
                           }
                        } else if (var5 > var8) {
                           EdgeEffectCompat.onPullDistance(this.mEdgeGlowBottom, (float)var4 / (float)this.getHeight(), 1.0F - var1.getX(var7) / (float)this.getWidth());
                           if (!this.mEdgeGlowTop.isFinished()) {
                              this.mEdgeGlowTop.onRelease();
                           }
                        }

                        if (!this.mEdgeGlowTop.isFinished() || !this.mEdgeGlowBottom.isFinished()) {
                           ViewCompat.postInvalidateOnAnimation(this);
                           var16 = var6;
                        }
                     }

                     if (var16) {
                        this.mVelocityTracker.clear();
                     }
                  }
               }
            }
         } else {
            var14 = this.mVelocityTracker;
            var14.computeCurrentVelocity(1000, (float)this.mMaximumVelocity);
            var3 = (int)var14.getYVelocity(this.mActivePointerId);
            if (Math.abs(var3) >= this.mMinimumVelocity) {
               if (!this.edgeEffectFling(var3)) {
                  var3 = -var3;
                  float var2 = (float)var3;
                  if (!this.dispatchNestedPreFling(0.0F, var2)) {
                     this.dispatchNestedFling(0.0F, var2, true);
                     this.fling(var3);
                  }
               }
            } else if (this.mScroller.springBack(this.getScrollX(), this.getScrollY(), 0, 0, 0, this.getScrollRange())) {
               ViewCompat.postInvalidateOnAnimation(this);
            }

            this.mActivePointerId = -1;
            this.endDrag();
         }
      } else {
         if (this.getChildCount() == 0) {
            return false;
         }

         if (this.mIsBeingDragged) {
            var13 = this.getParent();
            if (var13 != null) {
               var13.requestDisallowInterceptTouchEvent(true);
            }
         }

         if (!this.mScroller.isFinished()) {
            this.abortAnimatedScroll();
         }

         this.mLastMotionY = (int)var1.getY();
         this.mActivePointerId = var1.getPointerId(0);
         this.startNestedScroll(2, 0);
      }

      var14 = this.mVelocityTracker;
      if (var14 != null) {
         var14.addMovement(var12);
      }

      var12.recycle();
      return true;
   }

   boolean overScrollByCompat(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9) {
      int var12 = this.getOverScrollMode();
      int var11 = this.computeHorizontalScrollRange();
      int var10 = this.computeHorizontalScrollExtent();
      boolean var14 = false;
      boolean var16;
      if (var11 > var10) {
         var16 = true;
      } else {
         var16 = false;
      }

      boolean var15;
      if (this.computeVerticalScrollRange() > this.computeVerticalScrollExtent()) {
         var15 = true;
      } else {
         var15 = false;
      }

      if (var12 == 0 || var12 == 1 && var16) {
         var16 = true;
      } else {
         var16 = false;
      }

      if (var12 == 0 || var12 == 1 && var15) {
         var15 = true;
      } else {
         var15 = false;
      }

      var3 += var1;
      if (!var16) {
         var1 = 0;
      } else {
         var1 = var7;
      }

      var4 += var2;
      if (!var15) {
         var2 = 0;
      } else {
         var2 = var8;
      }

      var7 = -var1;
      var1 += var5;
      var5 = -var2;
      var2 += var6;
      if (var3 > var1) {
         var9 = true;
      } else if (var3 < var7) {
         var9 = true;
         var1 = var7;
      } else {
         var9 = false;
         var1 = var3;
      }

      boolean var13;
      if (var4 > var2) {
         var13 = true;
      } else if (var4 < var5) {
         var13 = true;
         var2 = var5;
      } else {
         var13 = false;
         var2 = var4;
      }

      if (var13 && !this.hasNestedScrollingParent(1)) {
         this.mScroller.springBack(var1, var2, 0, 0, 0, this.getScrollRange());
      }

      this.onOverScrolled(var1, var2, var9, var13);
      if (!var9) {
         var9 = var14;
         if (!var13) {
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   public boolean pageScroll(int var1) {
      boolean var2;
      if (var1 == 130) {
         var2 = true;
      } else {
         var2 = false;
      }

      int var3 = this.getHeight();
      if (var2) {
         this.mTempRect.top = this.getScrollY() + var3;
         int var6 = this.getChildCount();
         if (var6 > 0) {
            View var4 = this.getChildAt(var6 - 1);
            FrameLayout.LayoutParams var5 = (FrameLayout.LayoutParams)var4.getLayoutParams();
            var6 = var4.getBottom() + var5.bottomMargin + this.getPaddingBottom();
            if (this.mTempRect.top + var3 > var6) {
               this.mTempRect.top = var6 - var3;
            }
         }
      } else {
         this.mTempRect.top = this.getScrollY() - var3;
         if (this.mTempRect.top < 0) {
            this.mTempRect.top = 0;
         }
      }

      Rect var7 = this.mTempRect;
      var7.bottom = var7.top + var3;
      return this.scrollAndFocus(var1, this.mTempRect.top, this.mTempRect.bottom);
   }

   public void requestChildFocus(View var1, View var2) {
      if (!this.mIsLayoutDirty) {
         this.scrollToChild(var2);
      } else {
         this.mChildToScrollTo = var2;
      }

      super.requestChildFocus(var1, var2);
   }

   public boolean requestChildRectangleOnScreen(View var1, Rect var2, boolean var3) {
      var2.offset(var1.getLeft() - var1.getScrollX(), var1.getTop() - var1.getScrollY());
      return this.scrollToChildRect(var2, var3);
   }

   public void requestDisallowInterceptTouchEvent(boolean var1) {
      if (var1) {
         this.recycleVelocityTracker();
      }

      super.requestDisallowInterceptTouchEvent(var1);
   }

   public void requestLayout() {
      this.mIsLayoutDirty = true;
      super.requestLayout();
   }

   public void scrollTo(int var1, int var2) {
      if (this.getChildCount() > 0) {
         View var15 = this.getChildAt(0);
         FrameLayout.LayoutParams var16 = (FrameLayout.LayoutParams)var15.getLayoutParams();
         int var10 = this.getWidth();
         int var12 = this.getPaddingLeft();
         int var9 = this.getPaddingRight();
         int var13 = var15.getWidth();
         int var11 = var16.leftMargin;
         int var14 = var16.rightMargin;
         int var6 = this.getHeight();
         int var8 = this.getPaddingTop();
         int var4 = this.getPaddingBottom();
         int var5 = var15.getHeight();
         int var3 = var16.topMargin;
         int var7 = var16.bottomMargin;
         var1 = clamp(var1, var10 - var12 - var9, var13 + var11 + var14);
         var2 = clamp(var2, var6 - var8 - var4, var5 + var3 + var7);
         if (var1 != this.getScrollX() || var2 != this.getScrollY()) {
            super.scrollTo(var1, var2);
         }
      }

   }

   public void setFillViewport(boolean var1) {
      if (var1 != this.mFillViewport) {
         this.mFillViewport = var1;
         this.requestLayout();
      }

   }

   public void setNestedScrollingEnabled(boolean var1) {
      this.mChildHelper.setNestedScrollingEnabled(var1);
   }

   public void setOnScrollChangeListener(OnScrollChangeListener var1) {
      this.mOnScrollChangeListener = var1;
   }

   public void setSmoothScrollingEnabled(boolean var1) {
      this.mSmoothScrollingEnabled = var1;
   }

   public boolean shouldDelayChildPressedState() {
      return true;
   }

   public final void smoothScrollBy(int var1, int var2) {
      this.smoothScrollBy(var1, var2, 250, false);
   }

   public final void smoothScrollBy(int var1, int var2, int var3) {
      this.smoothScrollBy(var1, var2, var3, false);
   }

   public final void smoothScrollTo(int var1, int var2) {
      this.smoothScrollTo(var1, var2, 250, false);
   }

   public final void smoothScrollTo(int var1, int var2, int var3) {
      this.smoothScrollTo(var1, var2, var3, false);
   }

   void smoothScrollTo(int var1, int var2, int var3, boolean var4) {
      this.smoothScrollBy(var1 - this.getScrollX(), var2 - this.getScrollY(), var3, var4);
   }

   void smoothScrollTo(int var1, int var2, boolean var3) {
      this.smoothScrollTo(var1, var2, 250, var3);
   }

   public boolean startNestedScroll(int var1) {
      return this.startNestedScroll(var1, 0);
   }

   public boolean startNestedScroll(int var1, int var2) {
      return this.mChildHelper.startNestedScroll(var1, var2);
   }

   public void stopNestedScroll() {
      this.stopNestedScroll(0);
   }

   public void stopNestedScroll(int var1) {
      this.mChildHelper.stopNestedScroll(var1);
   }

   static class AccessibilityDelegate extends AccessibilityDelegateCompat {
      public void onInitializeAccessibilityEvent(View var1, AccessibilityEvent var2) {
         super.onInitializeAccessibilityEvent(var1, var2);
         NestedScrollView var4 = (NestedScrollView)var1;
         var2.setClassName(ScrollView.class.getName());
         boolean var3;
         if (var4.getScrollRange() > 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var2.setScrollable(var3);
         var2.setScrollX(var4.getScrollX());
         var2.setScrollY(var4.getScrollY());
         AccessibilityRecordCompat.setMaxScrollX(var2, var4.getScrollX());
         AccessibilityRecordCompat.setMaxScrollY(var2, var4.getScrollRange());
      }

      public void onInitializeAccessibilityNodeInfo(View var1, AccessibilityNodeInfoCompat var2) {
         super.onInitializeAccessibilityNodeInfo(var1, var2);
         NestedScrollView var4 = (NestedScrollView)var1;
         var2.setClassName(ScrollView.class.getName());
         if (var4.isEnabled()) {
            int var3 = var4.getScrollRange();
            if (var3 > 0) {
               var2.setScrollable(true);
               if (var4.getScrollY() > 0) {
                  var2.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                  var2.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_UP);
               }

               if (var4.getScrollY() < var3) {
                  var2.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                  var2.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_DOWN);
               }
            }
         }

      }

      public boolean performAccessibilityAction(View var1, int var2, Bundle var3) {
         if (super.performAccessibilityAction(var1, var2, var3)) {
            return true;
         } else {
            NestedScrollView var6 = (NestedScrollView)var1;
            if (!var6.isEnabled()) {
               return false;
            } else {
               int var4;
               int var5;
               if (var2 != 4096) {
                  if (var2 == 8192 || var2 == 16908344) {
                     var2 = var6.getHeight();
                     var4 = var6.getPaddingBottom();
                     var5 = var6.getPaddingTop();
                     var2 = Math.max(var6.getScrollY() - (var2 - var4 - var5), 0);
                     if (var2 != var6.getScrollY()) {
                        var6.smoothScrollTo(0, var2, true);
                        return true;
                     } else {
                        return false;
                     }
                  }

                  if (var2 != 16908346) {
                     return false;
                  }
               }

               var5 = var6.getHeight();
               var2 = var6.getPaddingBottom();
               var4 = var6.getPaddingTop();
               var2 = Math.min(var6.getScrollY() + (var5 - var2 - var4), var6.getScrollRange());
               if (var2 != var6.getScrollY()) {
                  var6.smoothScrollTo(0, var2, true);
                  return true;
               } else {
                  return false;
               }
            }
         }
      }
   }

   public interface OnScrollChangeListener {
      void onScrollChange(NestedScrollView var1, int var2, int var3, int var4, int var5);
   }

   static class SavedState extends View.BaseSavedState {
      public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
         public SavedState createFromParcel(Parcel var1) {
            return new SavedState(var1);
         }

         public SavedState[] newArray(int var1) {
            return new SavedState[var1];
         }
      };
      public int scrollPosition;

      SavedState(Parcel var1) {
         super(var1);
         this.scrollPosition = var1.readInt();
      }

      SavedState(Parcelable var1) {
         super(var1);
      }

      public String toString() {
         return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.scrollPosition + "}";
      }

      public void writeToParcel(Parcel var1, int var2) {
         super.writeToParcel(var1, var2);
         var1.writeInt(this.scrollPosition);
      }
   }
}
