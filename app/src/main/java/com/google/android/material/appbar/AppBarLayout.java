package com.google.android.material.appbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.util.ObjectsCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ThemeEnforcement;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@CoordinatorLayout.DefaultBehavior(Behavior.class)
public class AppBarLayout extends LinearLayout {
   private static final int INVALID_SCROLL_RANGE = -1;
   static final int PENDING_ACTION_ANIMATE_ENABLED = 4;
   static final int PENDING_ACTION_COLLAPSED = 2;
   static final int PENDING_ACTION_EXPANDED = 1;
   static final int PENDING_ACTION_FORCE = 8;
   static final int PENDING_ACTION_NONE = 0;
   private int downPreScrollRange;
   private int downScrollRange;
   private boolean haveChildWithInterpolator;
   private WindowInsetsCompat lastInsets;
   private boolean liftOnScroll;
   private boolean liftable;
   private boolean liftableOverride;
   private boolean lifted;
   private List listeners;
   private int pendingAction;
   private int[] tmpStatesArray;
   private int totalScrollRange;

   public AppBarLayout(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public AppBarLayout(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.totalScrollRange = -1;
      this.downPreScrollRange = -1;
      this.downScrollRange = -1;
      this.pendingAction = 0;
      this.setOrientation(1);
      if (VERSION.SDK_INT >= 21) {
         ViewUtilsLollipop.setBoundsViewOutlineProvider(this);
         ViewUtilsLollipop.setStateListAnimatorFromAttrs(this, var2, 0, R.style.Widget_Design_AppBarLayout);
      }

      TypedArray var3 = ThemeEnforcement.obtainStyledAttributes(var1, var2, R.styleable.AppBarLayout, 0, R.style.Widget_Design_AppBarLayout);
      ViewCompat.setBackground(this, var3.getDrawable(R.styleable.AppBarLayout_android_background));
      if (var3.hasValue(R.styleable.AppBarLayout_expanded)) {
         this.setExpanded(var3.getBoolean(R.styleable.AppBarLayout_expanded, false), false, false);
      }

      if (VERSION.SDK_INT >= 21 && var3.hasValue(R.styleable.AppBarLayout_elevation)) {
         ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, (float)var3.getDimensionPixelSize(R.styleable.AppBarLayout_elevation, 0));
      }

      if (VERSION.SDK_INT >= 26) {
         if (var3.hasValue(R.styleable.AppBarLayout_android_keyboardNavigationCluster)) {
            this.setKeyboardNavigationCluster(var3.getBoolean(R.styleable.AppBarLayout_android_keyboardNavigationCluster, false));
         }

         if (var3.hasValue(R.styleable.AppBarLayout_android_touchscreenBlocksFocus)) {
            this.setTouchscreenBlocksFocus(var3.getBoolean(R.styleable.AppBarLayout_android_touchscreenBlocksFocus, false));
         }
      }

      this.liftOnScroll = var3.getBoolean(R.styleable.AppBarLayout_liftOnScroll, false);
      var3.recycle();
      ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener(this) {
         final AppBarLayout this$0;

         {
            this.this$0 = var1;
         }

         public WindowInsetsCompat onApplyWindowInsets(View var1, WindowInsetsCompat var2) {
            return this.this$0.onWindowInsetChanged(var2);
         }
      });
   }

   private boolean hasCollapsibleChild() {
      int var2 = this.getChildCount();

      for(int var1 = 0; var1 < var2; ++var1) {
         if (((LayoutParams)this.getChildAt(var1).getLayoutParams()).isCollapsible()) {
            return true;
         }
      }

      return false;
   }

   private void invalidateScrollRanges() {
      this.totalScrollRange = -1;
      this.downPreScrollRange = -1;
      this.downScrollRange = -1;
   }

   private void setExpanded(boolean var1, boolean var2, boolean var3) {
      byte var4;
      if (var1) {
         var4 = 1;
      } else {
         var4 = 2;
      }

      byte var6 = 0;
      byte var5;
      if (var2) {
         var5 = 4;
      } else {
         var5 = 0;
      }

      if (var3) {
         var6 = 8;
      }

      this.pendingAction = var4 | var5 | var6;
      this.requestLayout();
   }

   private boolean setLiftableState(boolean var1) {
      if (this.liftable != var1) {
         this.liftable = var1;
         this.refreshDrawableState();
         return true;
      } else {
         return false;
      }
   }

   public void addOnOffsetChangedListener(BaseOnOffsetChangedListener var1) {
      if (this.listeners == null) {
         this.listeners = new ArrayList();
      }

      if (var1 != null && !this.listeners.contains(var1)) {
         this.listeners.add(var1);
      }

   }

   public void addOnOffsetChangedListener(OnOffsetChangedListener var1) {
      this.addOnOffsetChangedListener((BaseOnOffsetChangedListener)var1);
   }

   protected boolean checkLayoutParams(ViewGroup.LayoutParams var1) {
      return var1 instanceof LayoutParams;
   }

   void dispatchOffsetUpdates(int var1) {
      List var4 = this.listeners;
      if (var4 != null) {
         int var2 = 0;

         for(int var3 = var4.size(); var2 < var3; ++var2) {
            BaseOnOffsetChangedListener var5 = (BaseOnOffsetChangedListener)this.listeners.get(var2);
            if (var5 != null) {
               var5.onOffsetChanged(this, var1);
            }
         }
      }

   }

   protected LayoutParams generateDefaultLayoutParams() {
      return new LayoutParams(-1, -2);
   }

   public LayoutParams generateLayoutParams(AttributeSet var1) {
      return new LayoutParams(this.getContext(), var1);
   }

   protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams var1) {
      if (VERSION.SDK_INT >= 19 && var1 instanceof LinearLayout.LayoutParams) {
         return new LayoutParams((LinearLayout.LayoutParams)var1);
      } else {
         return var1 instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams)var1) : new LayoutParams(var1);
      }
   }

   int getDownNestedPreScrollRange() {
      int var1 = this.downPreScrollRange;
      if (var1 != -1) {
         return var1;
      } else {
         int var2 = this.getChildCount() - 1;

         int var3;
         for(var3 = 0; var2 >= 0; var3 = var1) {
            View var6 = this.getChildAt(var2);
            LayoutParams var5 = (LayoutParams)var6.getLayoutParams();
            int var4 = var6.getMeasuredHeight();
            var1 = var5.scrollFlags;
            if ((var1 & 5) == 5) {
               var3 += var5.topMargin + var5.bottomMargin;
               if ((var1 & 8) != 0) {
                  var1 = var3 + ViewCompat.getMinimumHeight(var6);
               } else {
                  if ((var1 & 2) != 0) {
                     var1 = ViewCompat.getMinimumHeight(var6);
                  } else {
                     var1 = this.getTopInset();
                  }

                  var1 = var3 + (var4 - var1);
               }
            } else {
               var1 = var3;
               if (var3 > 0) {
                  break;
               }
            }

            --var2;
         }

         var1 = Math.max(0, var3);
         this.downPreScrollRange = var1;
         return var1;
      }
   }

   int getDownNestedScrollRange() {
      int var1 = this.downScrollRange;
      if (var1 != -1) {
         return var1;
      } else {
         int var4 = this.getChildCount();
         int var2 = 0;
         var1 = 0;

         int var3;
         while(true) {
            var3 = var1;
            if (var2 >= var4) {
               break;
            }

            View var9 = this.getChildAt(var2);
            LayoutParams var10 = (LayoutParams)var9.getLayoutParams();
            int var8 = var9.getMeasuredHeight();
            int var7 = var10.topMargin;
            int var6 = var10.bottomMargin;
            int var5 = var10.scrollFlags;
            var3 = var1;
            if ((var5 & 1) == 0) {
               break;
            }

            var1 += var8 + var7 + var6;
            if ((var5 & 2) != 0) {
               var3 = var1 - (ViewCompat.getMinimumHeight(var9) + this.getTopInset());
               break;
            }

            ++var2;
         }

         var1 = Math.max(0, var3);
         this.downScrollRange = var1;
         return var1;
      }
   }

   public final int getMinimumHeightForVisibleOverlappingContent() {
      int var2 = this.getTopInset();
      int var1 = ViewCompat.getMinimumHeight(this);
      if (var1 == 0) {
         var1 = this.getChildCount();
         if (var1 >= 1) {
            var1 = ViewCompat.getMinimumHeight(this.getChildAt(var1 - 1));
         } else {
            var1 = 0;
         }

         if (var1 == 0) {
            return this.getHeight() / 3;
         }
      }

      return var1 * 2 + var2;
   }

   int getPendingAction() {
      return this.pendingAction;
   }

   @Deprecated
   public float getTargetElevation() {
      return 0.0F;
   }

   final int getTopInset() {
      WindowInsetsCompat var2 = this.lastInsets;
      int var1;
      if (var2 != null) {
         var1 = var2.getSystemWindowInsetTop();
      } else {
         var1 = 0;
      }

      return var1;
   }

   public final int getTotalScrollRange() {
      int var1 = this.totalScrollRange;
      if (var1 != -1) {
         return var1;
      } else {
         int var4 = this.getChildCount();
         int var2 = 0;
         var1 = 0;

         int var3;
         while(true) {
            var3 = var1;
            if (var2 >= var4) {
               break;
            }

            View var7 = this.getChildAt(var2);
            LayoutParams var8 = (LayoutParams)var7.getLayoutParams();
            int var6 = var7.getMeasuredHeight();
            int var5 = var8.scrollFlags;
            var3 = var1;
            if ((var5 & 1) == 0) {
               break;
            }

            var1 += var6 + var8.topMargin + var8.bottomMargin;
            if ((var5 & 2) != 0) {
               var3 = var1 - ViewCompat.getMinimumHeight(var7);
               break;
            }

            ++var2;
         }

         var1 = Math.max(0, var3 - this.getTopInset());
         this.totalScrollRange = var1;
         return var1;
      }
   }

   int getUpNestedPreScrollRange() {
      return this.getTotalScrollRange();
   }

   boolean hasChildWithInterpolator() {
      return this.haveChildWithInterpolator;
   }

   boolean hasScrollableChildren() {
      boolean var1;
      if (this.getTotalScrollRange() != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isLiftOnScroll() {
      return this.liftOnScroll;
   }

   protected int[] onCreateDrawableState(int var1) {
      if (this.tmpStatesArray == null) {
         this.tmpStatesArray = new int[4];
      }

      int[] var3 = this.tmpStatesArray;
      int[] var2 = super.onCreateDrawableState(var1 + var3.length);
      if (this.liftable) {
         var1 = R.attr.state_liftable;
      } else {
         var1 = -R.attr.state_liftable;
      }

      var3[0] = var1;
      if (this.liftable && this.lifted) {
         var1 = R.attr.state_lifted;
      } else {
         var1 = -R.attr.state_lifted;
      }

      var3[1] = var1;
      if (this.liftable) {
         var1 = R.attr.state_collapsible;
      } else {
         var1 = -R.attr.state_collapsible;
      }

      var3[2] = var1;
      if (this.liftable && this.lifted) {
         var1 = R.attr.state_collapsed;
      } else {
         var1 = -R.attr.state_collapsed;
      }

      var3[3] = var1;
      return mergeDrawableStates(var2, var3);
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      super.onLayout(var1, var2, var3, var4, var5);
      this.invalidateScrollRanges();
      var1 = false;
      this.haveChildWithInterpolator = false;
      var3 = this.getChildCount();

      for(var2 = 0; var2 < var3; ++var2) {
         if (((LayoutParams)this.getChildAt(var2).getLayoutParams()).getScrollInterpolator() != null) {
            this.haveChildWithInterpolator = true;
            break;
         }
      }

      if (!this.liftableOverride) {
         if (this.liftOnScroll || this.hasCollapsibleChild()) {
            var1 = true;
         }

         this.setLiftableState(var1);
      }

   }

   protected void onMeasure(int var1, int var2) {
      super.onMeasure(var1, var2);
      this.invalidateScrollRanges();
   }

   WindowInsetsCompat onWindowInsetChanged(WindowInsetsCompat var1) {
      WindowInsetsCompat var2;
      if (ViewCompat.getFitsSystemWindows(this)) {
         var2 = var1;
      } else {
         var2 = null;
      }

      if (!ObjectsCompat.equals(this.lastInsets, var2)) {
         this.lastInsets = var2;
         this.invalidateScrollRanges();
      }

      return var1;
   }

   public void removeOnOffsetChangedListener(BaseOnOffsetChangedListener var1) {
      List var2 = this.listeners;
      if (var2 != null && var1 != null) {
         var2.remove(var1);
      }

   }

   public void removeOnOffsetChangedListener(OnOffsetChangedListener var1) {
      this.removeOnOffsetChangedListener((BaseOnOffsetChangedListener)var1);
   }

   void resetPendingAction() {
      this.pendingAction = 0;
   }

   public void setExpanded(boolean var1) {
      this.setExpanded(var1, ViewCompat.isLaidOut(this));
   }

   public void setExpanded(boolean var1, boolean var2) {
      this.setExpanded(var1, var2, true);
   }

   public void setLiftOnScroll(boolean var1) {
      this.liftOnScroll = var1;
   }

   public boolean setLiftable(boolean var1) {
      this.liftableOverride = true;
      return this.setLiftableState(var1);
   }

   public boolean setLifted(boolean var1) {
      return this.setLiftedState(var1);
   }

   boolean setLiftedState(boolean var1) {
      if (this.lifted != var1) {
         this.lifted = var1;
         this.refreshDrawableState();
         return true;
      } else {
         return false;
      }
   }

   public void setOrientation(int var1) {
      if (var1 == 1) {
         super.setOrientation(var1);
      } else {
         throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
      }
   }

   @Deprecated
   public void setTargetElevation(float var1) {
      if (VERSION.SDK_INT >= 21) {
         ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, var1);
      }

   }

   protected static class BaseBehavior extends HeaderBehavior {
      private static final int INVALID_POSITION = -1;
      private static final int MAX_OFFSET_ANIMATION_DURATION = 600;
      private WeakReference lastNestedScrollingChildRef;
      private int lastStartedType;
      private ValueAnimator offsetAnimator;
      private int offsetDelta;
      private int offsetToChildIndexOnLayout = -1;
      private boolean offsetToChildIndexOnLayoutIsMinHeight;
      private float offsetToChildIndexOnLayoutPerc;
      private BaseDragCallback onDragCallback;

      public BaseBehavior() {
      }

      public BaseBehavior(Context var1, AttributeSet var2) {
         super(var1, var2);
      }

      private void animateOffsetTo(CoordinatorLayout var1, AppBarLayout var2, int var3, float var4) {
         int var5 = Math.abs(this.getTopBottomOffsetForScrollingSibling() - var3);
         var4 = Math.abs(var4);
         if (var4 > 0.0F) {
            var5 = Math.round((float)var5 / var4 * 1000.0F) * 3;
         } else {
            var5 = (int)(((float)var5 / (float)var2.getHeight() + 1.0F) * 150.0F);
         }

         this.animateOffsetWithDuration(var1, var2, var3, var5);
      }

      private void animateOffsetWithDuration(CoordinatorLayout var1, AppBarLayout var2, int var3, int var4) {
         int var5 = this.getTopBottomOffsetForScrollingSibling();
         if (var5 == var3) {
            ValueAnimator var7 = this.offsetAnimator;
            if (var7 != null && var7.isRunning()) {
               this.offsetAnimator.cancel();
            }

         } else {
            ValueAnimator var6 = this.offsetAnimator;
            if (var6 == null) {
               var6 = new ValueAnimator();
               this.offsetAnimator = var6;
               var6.setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
               this.offsetAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this, var1, var2) {
                  final BaseBehavior this$0;
                  final AppBarLayout val$child;
                  final CoordinatorLayout val$coordinatorLayout;

                  {
                     this.this$0 = var1;
                     this.val$coordinatorLayout = var2;
                     this.val$child = var3;
                  }

                  public void onAnimationUpdate(ValueAnimator var1) {
                     this.this$0.setHeaderTopBottomOffset(this.val$coordinatorLayout, this.val$child, (Integer)var1.getAnimatedValue());
                  }
               });
            } else {
               var6.cancel();
            }

            this.offsetAnimator.setDuration((long)Math.min(var4, 600));
            this.offsetAnimator.setIntValues(new int[]{var5, var3});
            this.offsetAnimator.start();
         }
      }

      private boolean canScrollChildren(CoordinatorLayout var1, AppBarLayout var2, View var3) {
         boolean var4;
         if (var2.hasScrollableChildren() && var1.getHeight() - var3.getHeight() <= var2.getHeight()) {
            var4 = true;
         } else {
            var4 = false;
         }

         return var4;
      }

      private static boolean checkFlag(int var0, int var1) {
         boolean var2;
         if ((var0 & var1) == var1) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }

      private View findFirstScrollingChild(CoordinatorLayout var1) {
         int var3 = var1.getChildCount();

         for(int var2 = 0; var2 < var3; ++var2) {
            View var4 = var1.getChildAt(var2);
            if (var4 instanceof NestedScrollingChild) {
               return var4;
            }
         }

         return null;
      }

      private static View getAppBarChildOnOffset(AppBarLayout var0, int var1) {
         int var3 = Math.abs(var1);
         int var2 = var0.getChildCount();

         for(var1 = 0; var1 < var2; ++var1) {
            View var4 = var0.getChildAt(var1);
            if (var3 >= var4.getTop() && var3 <= var4.getBottom()) {
               return var4;
            }
         }

         return null;
      }

      private int getChildIndexOnOffset(AppBarLayout var1, int var2) {
         int var8 = var1.getChildCount();

         for(int var3 = 0; var3 < var8; ++var3) {
            View var9 = var1.getChildAt(var3);
            int var7 = var9.getTop();
            int var6 = var9.getBottom();
            LayoutParams var10 = (LayoutParams)var9.getLayoutParams();
            int var5 = var7;
            int var4 = var6;
            if (checkFlag(var10.getScrollFlags(), 32)) {
               var5 = var7 - var10.topMargin;
               var4 = var6 + var10.bottomMargin;
            }

            var6 = -var2;
            if (var5 <= var6 && var4 >= var6) {
               return var3;
            }
         }

         return -1;
      }

      private int interpolateOffset(AppBarLayout var1, int var2) {
         int var6 = Math.abs(var2);
         int var7 = var1.getChildCount();
         int var5 = 0;

         for(int var4 = 0; var4 < var7; ++var4) {
            View var10 = var1.getChildAt(var4);
            LayoutParams var9 = (LayoutParams)var10.getLayoutParams();
            Interpolator var8 = var9.getScrollInterpolator();
            if (var6 >= var10.getTop() && var6 <= var10.getBottom()) {
               if (var8 != null) {
                  var7 = var9.getScrollFlags();
                  var4 = var5;
                  if ((var7 & 1) != 0) {
                     var5 = 0 + var10.getHeight() + var9.topMargin + var9.bottomMargin;
                     var4 = var5;
                     if ((var7 & 2) != 0) {
                        var4 = var5 - ViewCompat.getMinimumHeight(var10);
                     }
                  }

                  var5 = var4;
                  if (ViewCompat.getFitsSystemWindows(var10)) {
                     var5 = var4 - var1.getTopInset();
                  }

                  if (var5 > 0) {
                     var4 = var10.getTop();
                     float var3 = (float)var5;
                     var4 = Math.round(var3 * var8.getInterpolation((float)(var6 - var4) / var3));
                     return Integer.signum(var2) * (var10.getTop() + var4);
                  }
               }
               break;
            }
         }

         return var2;
      }

      private boolean shouldJumpElevationState(CoordinatorLayout var1, AppBarLayout var2) {
         List var6 = var1.getDependents(var2);
         int var4 = var6.size();
         boolean var5 = false;

         for(int var3 = 0; var3 < var4; ++var3) {
            CoordinatorLayout.Behavior var7 = ((CoordinatorLayout.LayoutParams)((View)var6.get(var3)).getLayoutParams()).getBehavior();
            if (var7 instanceof ScrollingViewBehavior) {
               if (((ScrollingViewBehavior)var7).getOverlayTop() != 0) {
                  var5 = true;
               }

               return var5;
            }
         }

         return false;
      }

      private void snapToChildIfNeeded(CoordinatorLayout var1, AppBarLayout var2) {
         int var7 = this.getTopBottomOffsetForScrollingSibling();
         int var5 = this.getChildIndexOnOffset(var2, var7);
         if (var5 >= 0) {
            View var9 = var2.getChildAt(var5);
            LayoutParams var10 = (LayoutParams)var9.getLayoutParams();
            int var8 = var10.getScrollFlags();
            if ((var8 & 17) == 17) {
               int var6 = -var9.getTop();
               int var3 = -var9.getBottom();
               int var4 = var3;
               if (var5 == var2.getChildCount() - 1) {
                  var4 = var3 + var2.getTopInset();
               }

               if (checkFlag(var8, 2)) {
                  var3 = var4 + ViewCompat.getMinimumHeight(var9);
                  var5 = var6;
               } else {
                  var5 = var6;
                  var3 = var4;
                  if (checkFlag(var8, 5)) {
                     var3 = ViewCompat.getMinimumHeight(var9) + var4;
                     if (var7 < var3) {
                        var5 = var3;
                        var3 = var4;
                     } else {
                        var5 = var6;
                     }
                  }
               }

               var6 = var5;
               var4 = var3;
               if (checkFlag(var8, 32)) {
                  var6 = var5 + var10.topMargin;
                  var4 = var3 - var10.bottomMargin;
               }

               var3 = var6;
               if (var7 < (var4 + var6) / 2) {
                  var3 = var4;
               }

               this.animateOffsetTo(var1, var2, MathUtils.clamp(var3, -var2.getTotalScrollRange(), 0), 0.0F);
            }
         }

      }

      private void stopNestedScrollIfNeeded(int var1, AppBarLayout var2, View var3, int var4) {
         if (var4 == 1) {
            var4 = this.getTopBottomOffsetForScrollingSibling();
            if (var1 < 0 && var4 == 0 || var1 > 0 && var4 == -var2.getDownNestedScrollRange()) {
               ViewCompat.stopNestedScroll(var3, 1);
            }
         }

      }

      private void updateAppBarLayoutDrawableState(CoordinatorLayout var1, AppBarLayout var2, int var3, int var4, boolean var5) {
         View var11 = getAppBarChildOnOffset(var2, var3);
         if (var11 != null) {
            boolean var8;
            boolean var10;
            label50: {
               label57: {
                  int var6 = ((LayoutParams)var11.getLayoutParams()).getScrollFlags();
                  var10 = true;
                  if ((var6 & 1) != 0) {
                     int var7 = ViewCompat.getMinimumHeight(var11);
                     if (var4 > 0 && (var6 & 12) != 0) {
                        if (-var3 >= var11.getBottom() - var7 - var2.getTopInset()) {
                           break label57;
                        }
                     } else if ((var6 & 2) != 0 && -var3 >= var11.getBottom() - var7 - var2.getTopInset()) {
                        break label57;
                     }
                  }

                  var8 = false;
                  break label50;
               }

               var8 = true;
            }

            boolean var9 = var8;
            if (var2.isLiftOnScroll()) {
               var11 = this.findFirstScrollingChild(var1);
               var9 = var8;
               if (var11 != null) {
                  if (var11.getScrollY() > 0) {
                     var8 = var10;
                  } else {
                     var8 = false;
                  }

                  var9 = var8;
               }
            }

            var8 = var2.setLiftedState(var9);
            if (VERSION.SDK_INT >= 11 && (var5 || var8 && this.shouldJumpElevationState(var1, var2))) {
               var2.jumpDrawablesToCurrentState();
            }
         }

      }

      boolean canDragView(AppBarLayout var1) {
         BaseDragCallback var4 = this.onDragCallback;
         if (var4 != null) {
            return var4.canDrag(var1);
         } else {
            WeakReference var5 = this.lastNestedScrollingChildRef;
            boolean var3 = true;
            boolean var2 = var3;
            if (var5 != null) {
               View var6 = (View)var5.get();
               if (var6 != null && var6.isShown() && !var6.canScrollVertically(-1)) {
                  var2 = var3;
               } else {
                  var2 = false;
               }
            }

            return var2;
         }
      }

      int getMaxDragOffset(AppBarLayout var1) {
         return -var1.getDownNestedScrollRange();
      }

      int getScrollRangeForDragFling(AppBarLayout var1) {
         return var1.getTotalScrollRange();
      }

      int getTopBottomOffsetForScrollingSibling() {
         return this.getTopAndBottomOffset() + this.offsetDelta;
      }

      boolean isOffsetAnimatorRunning() {
         ValueAnimator var2 = this.offsetAnimator;
         boolean var1;
         if (var2 != null && var2.isRunning()) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      void onFlingFinished(CoordinatorLayout var1, AppBarLayout var2) {
         this.snapToChildIfNeeded(var1, var2);
      }

      public boolean onLayoutChild(CoordinatorLayout var1, AppBarLayout var2, int var3) {
         boolean var5 = super.onLayoutChild(var1, var2, var3);
         int var4 = var2.getPendingAction();
         var3 = this.offsetToChildIndexOnLayout;
         if (var3 >= 0 && (var4 & 8) == 0) {
            View var6 = var2.getChildAt(var3);
            var4 = -var6.getBottom();
            if (this.offsetToChildIndexOnLayoutIsMinHeight) {
               var3 = ViewCompat.getMinimumHeight(var6) + var2.getTopInset();
            } else {
               var3 = Math.round((float)var6.getHeight() * this.offsetToChildIndexOnLayoutPerc);
            }

            this.setHeaderTopBottomOffset(var1, var2, var4 + var3);
         } else if (var4 != 0) {
            boolean var7;
            if ((var4 & 4) != 0) {
               var7 = true;
            } else {
               var7 = false;
            }

            if ((var4 & 2) != 0) {
               var4 = -var2.getUpNestedPreScrollRange();
               if (var7) {
                  this.animateOffsetTo(var1, var2, var4, 0.0F);
               } else {
                  this.setHeaderTopBottomOffset(var1, var2, var4);
               }
            } else if ((var4 & 1) != 0) {
               if (var7) {
                  this.animateOffsetTo(var1, var2, 0, 0.0F);
               } else {
                  this.setHeaderTopBottomOffset(var1, var2, 0);
               }
            }
         }

         var2.resetPendingAction();
         this.offsetToChildIndexOnLayout = -1;
         this.setTopAndBottomOffset(MathUtils.clamp(this.getTopAndBottomOffset(), -var2.getTotalScrollRange(), 0));
         this.updateAppBarLayoutDrawableState(var1, var2, this.getTopAndBottomOffset(), 0, true);
         var2.dispatchOffsetUpdates(this.getTopAndBottomOffset());
         return var5;
      }

      public boolean onMeasureChild(CoordinatorLayout var1, AppBarLayout var2, int var3, int var4, int var5, int var6) {
         if (((CoordinatorLayout.LayoutParams)var2.getLayoutParams()).height == -2) {
            var1.onMeasureChild(var2, var3, var4, MeasureSpec.makeMeasureSpec(0, 0), var6);
            return true;
         } else {
            return super.onMeasureChild(var1, var2, var3, var4, var5, var6);
         }
      }

      public void onNestedPreScroll(CoordinatorLayout var1, AppBarLayout var2, View var3, int var4, int var5, int[] var6, int var7) {
         if (var5 != 0) {
            int var8;
            if (var5 < 0) {
               var8 = -var2.getTotalScrollRange();
               var4 = var2.getDownNestedPreScrollRange() + var8;
            } else {
               var8 = -var2.getUpNestedPreScrollRange();
               var4 = 0;
            }

            if (var8 != var4) {
               var6[1] = this.scroll(var1, var2, var5, var8, var4);
               this.stopNestedScrollIfNeeded(var5, var2, var3, var7);
            }
         }

      }

      public void onNestedScroll(CoordinatorLayout var1, AppBarLayout var2, View var3, int var4, int var5, int var6, int var7, int var8) {
         if (var7 < 0) {
            this.scroll(var1, var2, var7, -var2.getDownNestedScrollRange(), 0);
            this.stopNestedScrollIfNeeded(var7, var2, var3, var8);
         }

         if (var2.isLiftOnScroll()) {
            boolean var9;
            if (var3.getScrollY() > 0) {
               var9 = true;
            } else {
               var9 = false;
            }

            var2.setLiftedState(var9);
         }

      }

      public void onRestoreInstanceState(CoordinatorLayout var1, AppBarLayout var2, Parcelable var3) {
         if (var3 instanceof SavedState) {
            SavedState var4 = (SavedState)var3;
            super.onRestoreInstanceState(var1, var2, var4.getSuperState());
            this.offsetToChildIndexOnLayout = var4.firstVisibleChildIndex;
            this.offsetToChildIndexOnLayoutPerc = var4.firstVisibleChildPercentageShown;
            this.offsetToChildIndexOnLayoutIsMinHeight = var4.firstVisibleChildAtMinimumHeight;
         } else {
            super.onRestoreInstanceState(var1, var2, var3);
            this.offsetToChildIndexOnLayout = -1;
         }

      }

      public Parcelable onSaveInstanceState(CoordinatorLayout var1, AppBarLayout var2) {
         Parcelable var8 = super.onSaveInstanceState(var1, var2);
         int var5 = this.getTopAndBottomOffset();
         int var4 = var2.getChildCount();
         boolean var7 = false;

         for(int var3 = 0; var3 < var4; ++var3) {
            View var9 = var2.getChildAt(var3);
            int var6 = var9.getBottom() + var5;
            if (var9.getTop() + var5 <= 0 && var6 >= 0) {
               SavedState var10 = new SavedState(var8);
               var10.firstVisibleChildIndex = var3;
               if (var6 == ViewCompat.getMinimumHeight(var9) + var2.getTopInset()) {
                  var7 = true;
               }

               var10.firstVisibleChildAtMinimumHeight = var7;
               var10.firstVisibleChildPercentageShown = (float)var6 / (float)var9.getHeight();
               return var10;
            }
         }

         return var8;
      }

      public boolean onStartNestedScroll(CoordinatorLayout var1, AppBarLayout var2, View var3, View var4, int var5, int var6) {
         boolean var7;
         if ((var5 & 2) == 0 || !var2.isLiftOnScroll() && !this.canScrollChildren(var1, var2, var3)) {
            var7 = false;
         } else {
            var7 = true;
         }

         if (var7) {
            ValueAnimator var8 = this.offsetAnimator;
            if (var8 != null) {
               var8.cancel();
            }
         }

         this.lastNestedScrollingChildRef = null;
         this.lastStartedType = var6;
         return var7;
      }

      public void onStopNestedScroll(CoordinatorLayout var1, AppBarLayout var2, View var3, int var4) {
         if (this.lastStartedType == 0 || var4 == 1) {
            this.snapToChildIfNeeded(var1, var2);
         }

         this.lastNestedScrollingChildRef = new WeakReference(var3);
      }

      public void setDragCallback(BaseDragCallback var1) {
         this.onDragCallback = var1;
      }

      int setHeaderTopBottomOffset(CoordinatorLayout var1, AppBarLayout var2, int var3, int var4, int var5) {
         int var7 = this.getTopBottomOffsetForScrollingSibling();
         byte var6 = 0;
         if (var4 != 0 && var7 >= var4 && var7 <= var5) {
            var4 = MathUtils.clamp(var3, var4, var5);
            var3 = var6;
            if (var7 != var4) {
               if (var2.hasChildWithInterpolator()) {
                  var3 = this.interpolateOffset(var2, var4);
               } else {
                  var3 = var4;
               }

               boolean var8 = this.setTopAndBottomOffset(var3);
               var5 = var7 - var4;
               this.offsetDelta = var4 - var3;
               if (!var8 && var2.hasChildWithInterpolator()) {
                  var1.dispatchDependentViewsChanged(var2);
               }

               var2.dispatchOffsetUpdates(this.getTopAndBottomOffset());
               byte var9;
               if (var4 < var7) {
                  var9 = -1;
               } else {
                  var9 = 1;
               }

               this.updateAppBarLayoutDrawableState(var1, var2, var4, var9, false);
               var3 = var5;
            }
         } else {
            this.offsetDelta = 0;
            var3 = var6;
         }

         return var3;
      }

      public abstract static class BaseDragCallback {
         public abstract boolean canDrag(AppBarLayout var1);
      }

      protected static class SavedState extends AbsSavedState {
         public static final Parcelable.Creator CREATOR = new Parcelable.ClassLoaderCreator() {
            public SavedState createFromParcel(Parcel var1) {
               return new SavedState(var1, (ClassLoader)null);
            }

            public SavedState createFromParcel(Parcel var1, ClassLoader var2) {
               return new SavedState(var1, var2);
            }

            public SavedState[] newArray(int var1) {
               return new SavedState[var1];
            }
         };
         boolean firstVisibleChildAtMinimumHeight;
         int firstVisibleChildIndex;
         float firstVisibleChildPercentageShown;

         public SavedState(Parcel var1, ClassLoader var2) {
            super(var1, var2);
            this.firstVisibleChildIndex = var1.readInt();
            this.firstVisibleChildPercentageShown = var1.readFloat();
            boolean var3;
            if (var1.readByte() != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            this.firstVisibleChildAtMinimumHeight = var3;
         }

         public SavedState(Parcelable var1) {
            super(var1);
         }

         public void writeToParcel(Parcel var1, int var2) {
            super.writeToParcel(var1, var2);
            var1.writeInt(this.firstVisibleChildIndex);
            var1.writeFloat(this.firstVisibleChildPercentageShown);
            var1.writeByte((byte)this.firstVisibleChildAtMinimumHeight);
         }
      }
   }

   public interface BaseOnOffsetChangedListener {
      void onOffsetChanged(AppBarLayout var1, int var2);
   }

   public static class Behavior extends BaseBehavior {
      public Behavior() {
      }

      public Behavior(Context var1, AttributeSet var2) {
         super(var1, var2);
      }

      public abstract static class DragCallback extends BaseBehavior.BaseDragCallback {
      }
   }

   public static class LayoutParams extends LinearLayout.LayoutParams {
      static final int COLLAPSIBLE_FLAGS = 10;
      static final int FLAG_QUICK_RETURN = 5;
      static final int FLAG_SNAP = 17;
      public static final int SCROLL_FLAG_ENTER_ALWAYS = 4;
      public static final int SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED = 8;
      public static final int SCROLL_FLAG_EXIT_UNTIL_COLLAPSED = 2;
      public static final int SCROLL_FLAG_SCROLL = 1;
      public static final int SCROLL_FLAG_SNAP = 16;
      public static final int SCROLL_FLAG_SNAP_MARGINS = 32;
      int scrollFlags = 1;
      Interpolator scrollInterpolator;

      public LayoutParams(int var1, int var2) {
         super(var1, var2);
      }

      public LayoutParams(int var1, int var2, float var3) {
         super(var1, var2, var3);
      }

      public LayoutParams(Context var1, AttributeSet var2) {
         super(var1, var2);
         TypedArray var3 = var1.obtainStyledAttributes(var2, R.styleable.AppBarLayout_Layout);
         this.scrollFlags = var3.getInt(R.styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
         if (var3.hasValue(R.styleable.AppBarLayout_Layout_layout_scrollInterpolator)) {
            this.scrollInterpolator = android.view.animation.AnimationUtils.loadInterpolator(var1, var3.getResourceId(R.styleable.AppBarLayout_Layout_layout_scrollInterpolator, 0));
         }

         var3.recycle();
      }

      public LayoutParams(ViewGroup.LayoutParams var1) {
         super(var1);
      }

      public LayoutParams(ViewGroup.MarginLayoutParams var1) {
         super(var1);
      }

      public LayoutParams(LinearLayout.LayoutParams var1) {
         super(var1);
      }

      public LayoutParams(LayoutParams var1) {
         super(var1);
         this.scrollFlags = var1.scrollFlags;
         this.scrollInterpolator = var1.scrollInterpolator;
      }

      public int getScrollFlags() {
         return this.scrollFlags;
      }

      public Interpolator getScrollInterpolator() {
         return this.scrollInterpolator;
      }

      boolean isCollapsible() {
         int var1 = this.scrollFlags;
         boolean var2 = true;
         if ((var1 & 1) != 1 || (var1 & 10) == 0) {
            var2 = false;
         }

         return var2;
      }

      public void setScrollFlags(int var1) {
         this.scrollFlags = var1;
      }

      public void setScrollInterpolator(Interpolator var1) {
         this.scrollInterpolator = var1;
      }

      @Retention(RetentionPolicy.SOURCE)
      public @interface ScrollFlags {
      }
   }

   public interface OnOffsetChangedListener extends BaseOnOffsetChangedListener {
      void onOffsetChanged(AppBarLayout var1, int var2);
   }

   public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior {
      public ScrollingViewBehavior() {
      }

      public ScrollingViewBehavior(Context var1, AttributeSet var2) {
         super(var1, var2);
         TypedArray var3 = var1.obtainStyledAttributes(var2, R.styleable.ScrollingViewBehavior_Layout);
         this.setOverlayTop(var3.getDimensionPixelSize(R.styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
         var3.recycle();
      }

      private static int getAppBarLayoutOffset(AppBarLayout var0) {
         CoordinatorLayout.Behavior var1 = ((CoordinatorLayout.LayoutParams)var0.getLayoutParams()).getBehavior();
         return var1 instanceof BaseBehavior ? ((BaseBehavior)var1).getTopBottomOffsetForScrollingSibling() : 0;
      }

      private void offsetChildAsNeeded(View var1, View var2) {
         CoordinatorLayout.Behavior var3 = ((CoordinatorLayout.LayoutParams)var2.getLayoutParams()).getBehavior();
         if (var3 instanceof BaseBehavior) {
            BaseBehavior var4 = (BaseBehavior)var3;
            ViewCompat.offsetTopAndBottom(var1, var2.getBottom() - var1.getTop() + var4.offsetDelta + this.getVerticalLayoutGap() - this.getOverlapPixelsForOffset(var2));
         }

      }

      private void updateLiftedStateIfNeeded(View var1, View var2) {
         if (var2 instanceof AppBarLayout) {
            AppBarLayout var4 = (AppBarLayout)var2;
            if (var4.isLiftOnScroll()) {
               boolean var3;
               if (var1.getScrollY() > 0) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               var4.setLiftedState(var3);
            }
         }

      }

      AppBarLayout findFirstDependency(List var1) {
         int var3 = var1.size();

         for(int var2 = 0; var2 < var3; ++var2) {
            View var4 = (View)var1.get(var2);
            if (var4 instanceof AppBarLayout) {
               return (AppBarLayout)var4;
            }
         }

         return null;
      }

      float getOverlapRatioForOffset(View var1) {
         if (var1 instanceof AppBarLayout) {
            AppBarLayout var5 = (AppBarLayout)var1;
            int var3 = var5.getTotalScrollRange();
            int var4 = var5.getDownNestedPreScrollRange();
            int var2 = getAppBarLayoutOffset(var5);
            if (var4 != 0 && var3 + var2 <= var4) {
               return 0.0F;
            }

            var3 -= var4;
            if (var3 != 0) {
               return (float)var2 / (float)var3 + 1.0F;
            }
         }

         return 0.0F;
      }

      int getScrollRange(View var1) {
         return var1 instanceof AppBarLayout ? ((AppBarLayout)var1).getTotalScrollRange() : super.getScrollRange(var1);
      }

      public boolean layoutDependsOn(CoordinatorLayout var1, View var2, View var3) {
         return var3 instanceof AppBarLayout;
      }

      public boolean onDependentViewChanged(CoordinatorLayout var1, View var2, View var3) {
         this.offsetChildAsNeeded(var2, var3);
         this.updateLiftedStateIfNeeded(var2, var3);
         return false;
      }

      public boolean onRequestChildRectangleOnScreen(CoordinatorLayout var1, View var2, Rect var3, boolean var4) {
         AppBarLayout var5 = this.findFirstDependency(var1.getDependencies(var2));
         if (var5 != null) {
            var3.offset(var2.getLeft(), var2.getTop());
            Rect var6 = this.tempRect1;
            var6.set(0, 0, var1.getWidth(), var1.getHeight());
            if (!var6.contains(var3)) {
               var5.setExpanded(false, var4 ^ true);
               return true;
            }
         }

         return false;
      }
   }
}
