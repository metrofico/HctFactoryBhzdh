package com.google.android.material.snackbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.internal.ThemeEnforcement;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTransientBottomBar {
   static final int ANIMATION_DURATION = 250;
   static final int ANIMATION_FADE_DURATION = 180;
   public static final int LENGTH_INDEFINITE = -2;
   public static final int LENGTH_LONG = 0;
   public static final int LENGTH_SHORT = -1;
   static final int MSG_DISMISS = 1;
   static final int MSG_SHOW = 0;
   private static final int[] SNACKBAR_STYLE_ATTR;
   private static final boolean USE_OFFSET_API;
   static final Handler handler;
   private final AccessibilityManager accessibilityManager;
   private Behavior behavior;
   private List callbacks;
   private final com.google.android.material.snackbar.ContentViewCallback contentViewCallback;
   private final Context context;
   private int duration;
   final SnackbarManager.Callback managerCallback = new SnackbarManager.Callback(this) {
      final BaseTransientBottomBar this$0;

      {
         this.this$0 = var1;
      }

      public void dismiss(int var1) {
         BaseTransientBottomBar.handler.sendMessage(BaseTransientBottomBar.handler.obtainMessage(1, var1, 0, this.this$0));
      }

      public void show() {
         BaseTransientBottomBar.handler.sendMessage(BaseTransientBottomBar.handler.obtainMessage(0, this.this$0));
      }
   };
   private final ViewGroup targetParent;
   protected final SnackbarBaseLayout view;

   static {
      boolean var0;
      if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT <= 19) {
         var0 = true;
      } else {
         var0 = false;
      }

      USE_OFFSET_API = var0;
      SNACKBAR_STYLE_ATTR = new int[]{R.attr.snackbarStyle};
      handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
         public boolean handleMessage(Message var1) {
            int var2 = var1.what;
            if (var2 != 0) {
               if (var2 != 1) {
                  return false;
               } else {
                  ((BaseTransientBottomBar)var1.obj).hideView(var1.arg1);
                  return true;
               }
            } else {
               ((BaseTransientBottomBar)var1.obj).showView();
               return true;
            }
         }
      });
   }

   protected BaseTransientBottomBar(ViewGroup var1, View var2, com.google.android.material.snackbar.ContentViewCallback var3) {
      if (var1 != null) {
         if (var2 != null) {
            if (var3 != null) {
               this.targetParent = var1;
               this.contentViewCallback = var3;
               Context var5 = var1.getContext();
               this.context = var5;
               ThemeEnforcement.checkAppCompatTheme(var5);
               SnackbarBaseLayout var4 = (SnackbarBaseLayout)LayoutInflater.from(var5).inflate(this.getSnackbarBaseLayoutResId(), var1, false);
               this.view = var4;
               var4.addView(var2);
               ViewCompat.setAccessibilityLiveRegion(var4, 1);
               ViewCompat.setImportantForAccessibility(var4, 1);
               ViewCompat.setFitsSystemWindows(var4, true);
               ViewCompat.setOnApplyWindowInsetsListener(var4, new OnApplyWindowInsetsListener(this) {
                  final BaseTransientBottomBar this$0;

                  {
                     this.this$0 = var1;
                  }

                  public WindowInsetsCompat onApplyWindowInsets(View var1, WindowInsetsCompat var2) {
                     var1.setPadding(var1.getPaddingLeft(), var1.getPaddingTop(), var1.getPaddingRight(), var2.getSystemWindowInsetBottom());
                     return var2;
                  }
               });
               ViewCompat.setAccessibilityDelegate(var4, new AccessibilityDelegateCompat(this) {
                  final BaseTransientBottomBar this$0;

                  {
                     this.this$0 = var1;
                  }

                  public void onInitializeAccessibilityNodeInfo(View var1, AccessibilityNodeInfoCompat var2) {
                     super.onInitializeAccessibilityNodeInfo(var1, var2);
                     var2.addAction(1048576);
                     var2.setDismissable(true);
                  }

                  public boolean performAccessibilityAction(View var1, int var2, Bundle var3) {
                     if (var2 == 1048576) {
                        this.this$0.dismiss();
                        return true;
                     } else {
                        return super.performAccessibilityAction(var1, var2, var3);
                     }
                  }
               });
               this.accessibilityManager = (AccessibilityManager)var5.getSystemService("accessibility");
            } else {
               throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
            }
         } else {
            throw new IllegalArgumentException("Transient bottom bar must have non-null content");
         }
      } else {
         throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
      }
   }

   private void animateViewOut(int var1) {
      ValueAnimator var2 = new ValueAnimator();
      var2.setIntValues(new int[]{0, this.getTranslationYBottom()});
      var2.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
      var2.setDuration(250L);
      var2.addListener(new AnimatorListenerAdapter(this, var1) {
         final BaseTransientBottomBar this$0;
         final int val$event;

         {
            this.this$0 = var1;
            this.val$event = var2;
         }

         public void onAnimationEnd(Animator var1) {
            this.this$0.onViewHidden(this.val$event);
         }

         public void onAnimationStart(Animator var1) {
            this.this$0.contentViewCallback.animateContentOut(0, 180);
         }
      });
      var2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) {
         private int previousAnimatedIntValue;
         final BaseTransientBottomBar this$0;

         {
            this.this$0 = var1;
            this.previousAnimatedIntValue = 0;
         }

         public void onAnimationUpdate(ValueAnimator var1) {
            int var2 = (Integer)var1.getAnimatedValue();
            if (BaseTransientBottomBar.USE_OFFSET_API) {
               ViewCompat.offsetTopAndBottom(this.this$0.view, var2 - this.previousAnimatedIntValue);
            } else {
               this.this$0.view.setTranslationY((float)var2);
            }

            this.previousAnimatedIntValue = var2;
         }
      });
      var2.start();
   }

   private int getTranslationYBottom() {
      int var2 = this.view.getHeight();
      ViewGroup.LayoutParams var3 = this.view.getLayoutParams();
      int var1 = var2;
      if (var3 instanceof ViewGroup.MarginLayoutParams) {
         var1 = var2 + ((ViewGroup.MarginLayoutParams)var3).bottomMargin;
      }

      return var1;
   }

   public BaseTransientBottomBar addCallback(BaseCallback var1) {
      if (var1 == null) {
         return this;
      } else {
         if (this.callbacks == null) {
            this.callbacks = new ArrayList();
         }

         this.callbacks.add(var1);
         return this;
      }
   }

   void animateViewIn() {
      int var1 = this.getTranslationYBottom();
      if (USE_OFFSET_API) {
         ViewCompat.offsetTopAndBottom(this.view, var1);
      } else {
         this.view.setTranslationY((float)var1);
      }

      ValueAnimator var2 = new ValueAnimator();
      var2.setIntValues(new int[]{var1, 0});
      var2.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
      var2.setDuration(250L);
      var2.addListener(new AnimatorListenerAdapter(this) {
         final BaseTransientBottomBar this$0;

         {
            this.this$0 = var1;
         }

         public void onAnimationEnd(Animator var1) {
            this.this$0.onViewShown();
         }

         public void onAnimationStart(Animator var1) {
            this.this$0.contentViewCallback.animateContentIn(70, 180);
         }
      });
      var2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this, var1) {
         private int previousAnimatedIntValue;
         final BaseTransientBottomBar this$0;
         final int val$translationYBottom;

         {
            this.this$0 = var1;
            this.val$translationYBottom = var2;
            this.previousAnimatedIntValue = var2;
         }

         public void onAnimationUpdate(ValueAnimator var1) {
            int var2 = (Integer)var1.getAnimatedValue();
            if (BaseTransientBottomBar.USE_OFFSET_API) {
               ViewCompat.offsetTopAndBottom(this.this$0.view, var2 - this.previousAnimatedIntValue);
            } else {
               this.this$0.view.setTranslationY((float)var2);
            }

            this.previousAnimatedIntValue = var2;
         }
      });
      var2.start();
   }

   public void dismiss() {
      this.dispatchDismiss(3);
   }

   protected void dispatchDismiss(int var1) {
      SnackbarManager.getInstance().dismiss(this.managerCallback, var1);
   }

   public Behavior getBehavior() {
      return this.behavior;
   }

   public Context getContext() {
      return this.context;
   }

   public int getDuration() {
      return this.duration;
   }

   protected SwipeDismissBehavior getNewBehavior() {
      return new Behavior();
   }

   protected int getSnackbarBaseLayoutResId() {
      int var1;
      if (this.hasSnackbarStyleAttr()) {
         var1 = R.layout.mtrl_layout_snackbar;
      } else {
         var1 = R.layout.design_layout_snackbar;
      }

      return var1;
   }

   public View getView() {
      return this.view;
   }

   protected boolean hasSnackbarStyleAttr() {
      TypedArray var3 = this.context.obtainStyledAttributes(SNACKBAR_STYLE_ATTR);
      boolean var2 = false;
      int var1 = var3.getResourceId(0, -1);
      var3.recycle();
      if (var1 != -1) {
         var2 = true;
      }

      return var2;
   }

   final void hideView(int var1) {
      if (this.shouldAnimate() && this.view.getVisibility() == 0) {
         this.animateViewOut(var1);
      } else {
         this.onViewHidden(var1);
      }

   }

   public boolean isShown() {
      return SnackbarManager.getInstance().isCurrent(this.managerCallback);
   }

   public boolean isShownOrQueued() {
      return SnackbarManager.getInstance().isCurrentOrNext(this.managerCallback);
   }

   void onViewHidden(int var1) {
      SnackbarManager.getInstance().onDismissed(this.managerCallback);
      List var3 = this.callbacks;
      if (var3 != null) {
         for(int var2 = var3.size() - 1; var2 >= 0; --var2) {
            ((BaseCallback)this.callbacks.get(var2)).onDismissed(this, var1);
         }
      }

      ViewParent var4 = this.view.getParent();
      if (var4 instanceof ViewGroup) {
         ((ViewGroup)var4).removeView(this.view);
      }

   }

   void onViewShown() {
      SnackbarManager.getInstance().onShown(this.managerCallback);
      List var2 = this.callbacks;
      if (var2 != null) {
         for(int var1 = var2.size() - 1; var1 >= 0; --var1) {
            ((BaseCallback)this.callbacks.get(var1)).onShown(this);
         }
      }

   }

   public BaseTransientBottomBar removeCallback(BaseCallback var1) {
      if (var1 == null) {
         return this;
      } else {
         List var2 = this.callbacks;
         if (var2 == null) {
            return this;
         } else {
            var2.remove(var1);
            return this;
         }
      }
   }

   public BaseTransientBottomBar setBehavior(Behavior var1) {
      this.behavior = var1;
      return this;
   }

   public BaseTransientBottomBar setDuration(int var1) {
      this.duration = var1;
      return this;
   }

   boolean shouldAnimate() {
      AccessibilityManager var2 = this.accessibilityManager;
      boolean var1 = true;
      List var3 = var2.getEnabledAccessibilityServiceList(1);
      if (var3 == null || !var3.isEmpty()) {
         var1 = false;
      }

      return var1;
   }

   public void show() {
      SnackbarManager.getInstance().show(this.getDuration(), this.managerCallback);
   }

   final void showView() {
      if (this.view.getParent() == null) {
         ViewGroup.LayoutParams var1 = this.view.getLayoutParams();
         if (var1 instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.LayoutParams var3 = (CoordinatorLayout.LayoutParams)var1;
            Behavior var2 = this.behavior;
            Object var4 = var2;
            if (var2 == null) {
               var4 = this.getNewBehavior();
            }

            if (var4 instanceof Behavior) {
               ((Behavior)var4).setBaseTransientBottomBar(this);
            }

            ((SwipeDismissBehavior)var4).setListener(new SwipeDismissBehavior.OnDismissListener(this) {
               final BaseTransientBottomBar this$0;

               {
                  this.this$0 = var1;
               }

               public void onDismiss(View var1) {
                  var1.setVisibility(8);
                  this.this$0.dispatchDismiss(0);
               }

               public void onDragStateChanged(int var1) {
                  if (var1 != 0) {
                     if (var1 == 1 || var1 == 2) {
                        SnackbarManager.getInstance().pauseTimeout(this.this$0.managerCallback);
                     }
                  } else {
                     SnackbarManager.getInstance().restoreTimeoutIfPaused(this.this$0.managerCallback);
                  }

               }
            });
            var3.setBehavior((CoordinatorLayout.Behavior)var4);
            var3.insetEdge = 80;
         }

         this.targetParent.addView(this.view);
      }

      this.view.setOnAttachStateChangeListener(new OnAttachStateChangeListener(this) {
         final BaseTransientBottomBar this$0;

         {
            this.this$0 = var1;
         }

         public void onViewAttachedToWindow(View var1) {
         }

         public void onViewDetachedFromWindow(View var1) {
            if (this.this$0.isShownOrQueued()) {
               BaseTransientBottomBar.handler.post(new Runnable(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void run() {
                     this.this$1.this$0.onViewHidden(3);
                  }
               });
            }

         }
      });
      if (ViewCompat.isLaidOut(this.view)) {
         if (this.shouldAnimate()) {
            this.animateViewIn();
         } else {
            this.onViewShown();
         }
      } else {
         this.view.setOnLayoutChangeListener(new OnLayoutChangeListener(this) {
            final BaseTransientBottomBar this$0;

            {
               this.this$0 = var1;
            }

            public void onLayoutChange(View var1, int var2, int var3, int var4, int var5) {
               this.this$0.view.setOnLayoutChangeListener((OnLayoutChangeListener)null);
               if (this.this$0.shouldAnimate()) {
                  this.this$0.animateViewIn();
               } else {
                  this.this$0.onViewShown();
               }

            }
         });
      }

   }

   public abstract static class BaseCallback {
      public static final int DISMISS_EVENT_ACTION = 1;
      public static final int DISMISS_EVENT_CONSECUTIVE = 4;
      public static final int DISMISS_EVENT_MANUAL = 3;
      public static final int DISMISS_EVENT_SWIPE = 0;
      public static final int DISMISS_EVENT_TIMEOUT = 2;

      public void onDismissed(Object var1, int var2) {
      }

      public void onShown(Object var1) {
      }

      @Retention(RetentionPolicy.SOURCE)
      public @interface DismissEvent {
      }
   }

   public static class Behavior extends SwipeDismissBehavior {
      private final BehaviorDelegate delegate = new BehaviorDelegate(this);

      private void setBaseTransientBottomBar(BaseTransientBottomBar var1) {
         this.delegate.setBaseTransientBottomBar(var1);
      }

      public boolean canSwipeDismissView(View var1) {
         return this.delegate.canSwipeDismissView(var1);
      }

      public boolean onInterceptTouchEvent(CoordinatorLayout var1, View var2, MotionEvent var3) {
         this.delegate.onInterceptTouchEvent(var1, var2, var3);
         return super.onInterceptTouchEvent(var1, var2, var3);
      }
   }

   public static class BehaviorDelegate {
      private SnackbarManager.Callback managerCallback;

      public BehaviorDelegate(SwipeDismissBehavior var1) {
         var1.setStartAlphaSwipeDistance(0.1F);
         var1.setEndAlphaSwipeDistance(0.6F);
         var1.setSwipeDirection(0);
      }

      public boolean canSwipeDismissView(View var1) {
         return var1 instanceof SnackbarBaseLayout;
      }

      public void onInterceptTouchEvent(CoordinatorLayout var1, View var2, MotionEvent var3) {
         int var4 = var3.getActionMasked();
         if (var4 != 0) {
            if (var4 == 1 || var4 == 3) {
               SnackbarManager.getInstance().restoreTimeoutIfPaused(this.managerCallback);
            }
         } else if (var1.isPointInChildBounds(var2, (int)var3.getX(), (int)var3.getY())) {
            SnackbarManager.getInstance().pauseTimeout(this.managerCallback);
         }

      }

      public void setBaseTransientBottomBar(BaseTransientBottomBar var1) {
         this.managerCallback = var1.managerCallback;
      }
   }

   @Deprecated
   public interface ContentViewCallback extends com.google.android.material.snackbar.ContentViewCallback {
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface Duration {
   }

   protected interface OnAttachStateChangeListener {
      void onViewAttachedToWindow(View var1);

      void onViewDetachedFromWindow(View var1);
   }

   protected interface OnLayoutChangeListener {
      void onLayoutChange(View var1, int var2, int var3, int var4, int var5);
   }

   protected static class SnackbarBaseLayout extends FrameLayout {
      private final AccessibilityManager accessibilityManager;
      private OnAttachStateChangeListener onAttachStateChangeListener;
      private OnLayoutChangeListener onLayoutChangeListener;
      private final AccessibilityManagerCompat.TouchExplorationStateChangeListener touchExplorationStateChangeListener;

      protected SnackbarBaseLayout(Context var1) {
         this(var1, (AttributeSet)null);
      }

      protected SnackbarBaseLayout(Context var1, AttributeSet var2) {
         super(var1, var2);
         TypedArray var4 = var1.obtainStyledAttributes(var2, R.styleable.SnackbarLayout);
         if (var4.hasValue(R.styleable.SnackbarLayout_elevation)) {
            ViewCompat.setElevation(this, (float)var4.getDimensionPixelSize(R.styleable.SnackbarLayout_elevation, 0));
         }

         var4.recycle();
         AccessibilityManager var3 = (AccessibilityManager)var1.getSystemService("accessibility");
         this.accessibilityManager = var3;
         AccessibilityManagerCompat.TouchExplorationStateChangeListener var5 = new AccessibilityManagerCompat.TouchExplorationStateChangeListener(this) {
            final SnackbarBaseLayout this$0;

            {
               this.this$0 = var1;
            }

            public void onTouchExplorationStateChanged(boolean var1) {
               this.this$0.setClickableOrFocusableBasedOnAccessibility(var1);
            }
         };
         this.touchExplorationStateChangeListener = var5;
         AccessibilityManagerCompat.addTouchExplorationStateChangeListener(var3, var5);
         this.setClickableOrFocusableBasedOnAccessibility(var3.isTouchExplorationEnabled());
      }

      private void setClickableOrFocusableBasedOnAccessibility(boolean var1) {
         this.setClickable(var1 ^ true);
         this.setFocusable(var1);
      }

      protected void onAttachedToWindow() {
         super.onAttachedToWindow();
         OnAttachStateChangeListener var1 = this.onAttachStateChangeListener;
         if (var1 != null) {
            var1.onViewAttachedToWindow(this);
         }

         ViewCompat.requestApplyInsets(this);
      }

      protected void onDetachedFromWindow() {
         super.onDetachedFromWindow();
         OnAttachStateChangeListener var1 = this.onAttachStateChangeListener;
         if (var1 != null) {
            var1.onViewDetachedFromWindow(this);
         }

         AccessibilityManagerCompat.removeTouchExplorationStateChangeListener(this.accessibilityManager, this.touchExplorationStateChangeListener);
      }

      protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
         super.onLayout(var1, var2, var3, var4, var5);
         OnLayoutChangeListener var6 = this.onLayoutChangeListener;
         if (var6 != null) {
            var6.onLayoutChange(this, var2, var3, var4, var5);
         }

      }

      void setOnAttachStateChangeListener(OnAttachStateChangeListener var1) {
         this.onAttachStateChangeListener = var1;
      }

      void setOnLayoutChangeListener(OnLayoutChangeListener var1) {
         this.onLayoutChangeListener = var1;
      }
   }
}
