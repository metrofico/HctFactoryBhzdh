package androidx.drawerlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.View.MeasureSpec;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import java.util.ArrayList;
import java.util.List;

public class DrawerLayout extends ViewGroup {
   private static final boolean ALLOW_EDGE_LOCK = false;
   static final boolean CAN_HIDE_DESCENDANTS;
   private static final boolean CHILDREN_DISALLOW_INTERCEPT = true;
   private static final int DEFAULT_SCRIM_COLOR = -1728053248;
   private static final int DRAWER_ELEVATION = 10;
   static final int[] LAYOUT_ATTRS;
   public static final int LOCK_MODE_LOCKED_CLOSED = 1;
   public static final int LOCK_MODE_LOCKED_OPEN = 2;
   public static final int LOCK_MODE_UNDEFINED = 3;
   public static final int LOCK_MODE_UNLOCKED = 0;
   private static final int MIN_DRAWER_MARGIN = 64;
   private static final int MIN_FLING_VELOCITY = 400;
   private static final int PEEK_DELAY = 160;
   private static final boolean SET_DRAWER_SHADOW_FROM_ELEVATION;
   public static final int STATE_DRAGGING = 1;
   public static final int STATE_IDLE = 0;
   public static final int STATE_SETTLING = 2;
   private static final String TAG = "DrawerLayout";
   private static final int[] THEME_ATTRS;
   private static final float TOUCH_SLOP_SENSITIVITY = 1.0F;
   private final ChildAccessibilityDelegate mChildAccessibilityDelegate;
   private Rect mChildHitRect;
   private Matrix mChildInvertedMatrix;
   private boolean mChildrenCanceledTouch;
   private boolean mDisallowInterceptRequested;
   private boolean mDrawStatusBarBackground;
   private float mDrawerElevation;
   private int mDrawerState;
   private boolean mFirstLayout;
   private boolean mInLayout;
   private float mInitialMotionX;
   private float mInitialMotionY;
   private Object mLastInsets;
   private final ViewDragCallback mLeftCallback;
   private final ViewDragHelper mLeftDragger;
   private DrawerListener mListener;
   private List mListeners;
   private int mLockModeEnd;
   private int mLockModeLeft;
   private int mLockModeRight;
   private int mLockModeStart;
   private int mMinDrawerMargin;
   private final ArrayList mNonDrawerViews;
   private final ViewDragCallback mRightCallback;
   private final ViewDragHelper mRightDragger;
   private int mScrimColor;
   private float mScrimOpacity;
   private Paint mScrimPaint;
   private Drawable mShadowEnd;
   private Drawable mShadowLeft;
   private Drawable mShadowLeftResolved;
   private Drawable mShadowRight;
   private Drawable mShadowRightResolved;
   private Drawable mShadowStart;
   private Drawable mStatusBarBackground;
   private CharSequence mTitleLeft;
   private CharSequence mTitleRight;

   static {
      boolean var1 = true;
      THEME_ATTRS = new int[]{16843828};
      LAYOUT_ATTRS = new int[]{16842931};
      boolean var0;
      if (VERSION.SDK_INT >= 19) {
         var0 = true;
      } else {
         var0 = false;
      }

      CAN_HIDE_DESCENDANTS = var0;
      if (VERSION.SDK_INT >= 21) {
         var0 = var1;
      } else {
         var0 = false;
      }

      SET_DRAWER_SHADOW_FROM_ELEVATION = var0;
   }

   public DrawerLayout(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public DrawerLayout(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public DrawerLayout(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mChildAccessibilityDelegate = new ChildAccessibilityDelegate();
      this.mScrimColor = -1728053248;
      this.mScrimPaint = new Paint();
      this.mFirstLayout = true;
      this.mLockModeLeft = 3;
      this.mLockModeRight = 3;
      this.mLockModeStart = 3;
      this.mLockModeEnd = 3;
      this.mShadowStart = null;
      this.mShadowEnd = null;
      this.mShadowLeft = null;
      this.mShadowRight = null;
      this.setDescendantFocusability(262144);
      float var5 = this.getResources().getDisplayMetrics().density;
      this.mMinDrawerMargin = (int)(64.0F * var5 + 0.5F);
      float var4 = 400.0F * var5;
      ViewDragCallback var6 = new ViewDragCallback(this, 3);
      this.mLeftCallback = var6;
      ViewDragCallback var11 = new ViewDragCallback(this, 5);
      this.mRightCallback = var11;
      ViewDragHelper var7 = ViewDragHelper.create(this, 1.0F, var6);
      this.mLeftDragger = var7;
      var7.setEdgeTrackingEnabled(1);
      var7.setMinVelocity(var4);
      var6.setDragger(var7);
      ViewDragHelper var12 = ViewDragHelper.create(this, 1.0F, var11);
      this.mRightDragger = var12;
      var12.setEdgeTrackingEnabled(2);
      var12.setMinVelocity(var4);
      var11.setDragger(var12);
      this.setFocusableInTouchMode(true);
      ViewCompat.setImportantForAccessibility(this, 1);
      ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate(this));
      this.setMotionEventSplittingEnabled(false);
      if (ViewCompat.getFitsSystemWindows(this)) {
         if (VERSION.SDK_INT >= 21) {
            this.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener(this) {
               final DrawerLayout this$0;

               {
                  this.this$0 = var1;
               }

               public WindowInsets onApplyWindowInsets(View var1, WindowInsets var2) {
                  DrawerLayout var4 = (DrawerLayout)var1;
                  boolean var3;
                  if (var2.getSystemWindowInsetTop() > 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var4.setChildInsets(var2, var3);
                  return var2.consumeSystemWindowInsets();
               }
            });
            this.setSystemUiVisibility(1280);
            TypedArray var10 = var1.obtainStyledAttributes(THEME_ATTRS);

            try {
               this.mStatusBarBackground = var10.getDrawable(0);
            } finally {
               var10.recycle();
            }
         } else {
            this.mStatusBarBackground = null;
         }
      }

      this.mDrawerElevation = var5 * 10.0F;
      this.mNonDrawerViews = new ArrayList();
   }

   private boolean dispatchTransformedGenericPointerEvent(MotionEvent var1, View var2) {
      boolean var5;
      if (!var2.getMatrix().isIdentity()) {
         var1 = this.getTransformedMotionEvent(var1, var2);
         var5 = var2.dispatchGenericMotionEvent(var1);
         var1.recycle();
      } else {
         float var3 = (float)(this.getScrollX() - var2.getLeft());
         float var4 = (float)(this.getScrollY() - var2.getTop());
         var1.offsetLocation(var3, var4);
         var5 = var2.dispatchGenericMotionEvent(var1);
         var1.offsetLocation(-var3, -var4);
      }

      return var5;
   }

   private MotionEvent getTransformedMotionEvent(MotionEvent var1, View var2) {
      float var3 = (float)(this.getScrollX() - var2.getLeft());
      float var4 = (float)(this.getScrollY() - var2.getTop());
      var1 = MotionEvent.obtain(var1);
      var1.offsetLocation(var3, var4);
      Matrix var5 = var2.getMatrix();
      if (!var5.isIdentity()) {
         if (this.mChildInvertedMatrix == null) {
            this.mChildInvertedMatrix = new Matrix();
         }

         var5.invert(this.mChildInvertedMatrix);
         var1.transform(this.mChildInvertedMatrix);
      }

      return var1;
   }

   static String gravityToString(int var0) {
      if ((var0 & 3) == 3) {
         return "LEFT";
      } else {
         return (var0 & 5) == 5 ? "RIGHT" : Integer.toHexString(var0);
      }
   }

   private static boolean hasOpaqueBackground(View var0) {
      Drawable var3 = var0.getBackground();
      boolean var2 = false;
      boolean var1 = var2;
      if (var3 != null) {
         var1 = var2;
         if (var3.getOpacity() == -1) {
            var1 = true;
         }
      }

      return var1;
   }

   private boolean hasPeekingDrawer() {
      int var2 = this.getChildCount();

      for(int var1 = 0; var1 < var2; ++var1) {
         if (((LayoutParams)this.getChildAt(var1).getLayoutParams()).isPeeking) {
            return true;
         }
      }

      return false;
   }

   private boolean hasVisibleDrawer() {
      boolean var1;
      if (this.findVisibleDrawer() != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   static boolean includeChildForAccessibility(View var0) {
      boolean var1;
      if (ViewCompat.getImportantForAccessibility(var0) != 4 && ViewCompat.getImportantForAccessibility(var0) != 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private boolean isInBoundsOfChild(float var1, float var2, View var3) {
      if (this.mChildHitRect == null) {
         this.mChildHitRect = new Rect();
      }

      var3.getHitRect(this.mChildHitRect);
      return this.mChildHitRect.contains((int)var1, (int)var2);
   }

   private boolean mirror(Drawable var1, int var2) {
      if (var1 != null && DrawableCompat.isAutoMirrored(var1)) {
         DrawableCompat.setLayoutDirection(var1, var2);
         return true;
      } else {
         return false;
      }
   }

   private Drawable resolveLeftShadow() {
      int var1 = ViewCompat.getLayoutDirection(this);
      Drawable var2;
      if (var1 == 0) {
         var2 = this.mShadowStart;
         if (var2 != null) {
            this.mirror(var2, var1);
            return this.mShadowStart;
         }
      } else {
         var2 = this.mShadowEnd;
         if (var2 != null) {
            this.mirror(var2, var1);
            return this.mShadowEnd;
         }
      }

      return this.mShadowLeft;
   }

   private Drawable resolveRightShadow() {
      int var1 = ViewCompat.getLayoutDirection(this);
      Drawable var2;
      if (var1 == 0) {
         var2 = this.mShadowEnd;
         if (var2 != null) {
            this.mirror(var2, var1);
            return this.mShadowEnd;
         }
      } else {
         var2 = this.mShadowStart;
         if (var2 != null) {
            this.mirror(var2, var1);
            return this.mShadowStart;
         }
      }

      return this.mShadowRight;
   }

   private void resolveShadowDrawables() {
      if (!SET_DRAWER_SHADOW_FROM_ELEVATION) {
         this.mShadowLeftResolved = this.resolveLeftShadow();
         this.mShadowRightResolved = this.resolveRightShadow();
      }
   }

   private void updateChildrenImportantForAccessibility(View var1, boolean var2) {
      int var4 = this.getChildCount();

      for(int var3 = 0; var3 < var4; ++var3) {
         View var5 = this.getChildAt(var3);
         if ((var2 || this.isDrawerView(var5)) && (!var2 || var5 != var1)) {
            ViewCompat.setImportantForAccessibility(var5, 4);
         } else {
            ViewCompat.setImportantForAccessibility(var5, 1);
         }
      }

   }

   public void addDrawerListener(DrawerListener var1) {
      if (var1 != null) {
         if (this.mListeners == null) {
            this.mListeners = new ArrayList();
         }

         this.mListeners.add(var1);
      }
   }

   public void addFocusables(ArrayList var1, int var2, int var3) {
      if (this.getDescendantFocusability() != 393216) {
         int var7 = this.getChildCount();
         byte var6 = 0;
         int var4 = 0;

         boolean var5;
         View var8;
         for(var5 = false; var4 < var7; ++var4) {
            var8 = this.getChildAt(var4);
            if (this.isDrawerView(var8)) {
               if (this.isDrawerOpen(var8)) {
                  var8.addFocusables(var1, var2, var3);
                  var5 = true;
               }
            } else {
               this.mNonDrawerViews.add(var8);
            }
         }

         if (!var5) {
            int var9 = this.mNonDrawerViews.size();

            for(var4 = var6; var4 < var9; ++var4) {
               var8 = (View)this.mNonDrawerViews.get(var4);
               if (var8.getVisibility() == 0) {
                  var8.addFocusables(var1, var2, var3);
               }
            }
         }

         this.mNonDrawerViews.clear();
      }
   }

   public void addView(View var1, int var2, ViewGroup.LayoutParams var3) {
      super.addView(var1, var2, var3);
      if (this.findOpenDrawer() == null && !this.isDrawerView(var1)) {
         ViewCompat.setImportantForAccessibility(var1, 1);
      } else {
         ViewCompat.setImportantForAccessibility(var1, 4);
      }

      if (!CAN_HIDE_DESCENDANTS) {
         ViewCompat.setAccessibilityDelegate(var1, this.mChildAccessibilityDelegate);
      }

   }

   void cancelChildViewTouch() {
      if (!this.mChildrenCanceledTouch) {
         long var3 = SystemClock.uptimeMillis();
         MotionEvent var5 = MotionEvent.obtain(var3, var3, 3, 0.0F, 0.0F, 0);
         int var2 = this.getChildCount();

         for(int var1 = 0; var1 < var2; ++var1) {
            this.getChildAt(var1).dispatchTouchEvent(var5);
         }

         var5.recycle();
         this.mChildrenCanceledTouch = true;
      }

   }

   boolean checkDrawerViewAbsoluteGravity(View var1, int var2) {
      boolean var3;
      if ((this.getDrawerViewAbsoluteGravity(var1) & var2) == var2) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   protected boolean checkLayoutParams(ViewGroup.LayoutParams var1) {
      boolean var2;
      if (var1 instanceof LayoutParams && super.checkLayoutParams(var1)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public void closeDrawer(int var1) {
      this.closeDrawer(var1, true);
   }

   public void closeDrawer(int var1, boolean var2) {
      View var3 = this.findDrawerWithGravity(var1);
      if (var3 != null) {
         this.closeDrawer(var3, var2);
      } else {
         throw new IllegalArgumentException("No drawer view found with gravity " + gravityToString(var1));
      }
   }

   public void closeDrawer(View var1) {
      this.closeDrawer(var1, true);
   }

   public void closeDrawer(View var1, boolean var2) {
      if (this.isDrawerView(var1)) {
         LayoutParams var3 = (LayoutParams)var1.getLayoutParams();
         if (this.mFirstLayout) {
            var3.onScreen = 0.0F;
            var3.openState = 0;
         } else if (var2) {
            var3.openState |= 4;
            if (this.checkDrawerViewAbsoluteGravity(var1, 3)) {
               this.mLeftDragger.smoothSlideViewTo(var1, -var1.getWidth(), var1.getTop());
            } else {
               this.mRightDragger.smoothSlideViewTo(var1, this.getWidth(), var1.getTop());
            }
         } else {
            this.moveDrawerToOffset(var1, 0.0F);
            this.updateDrawerState(var3.gravity, 0, var1);
            var1.setVisibility(4);
         }

         this.invalidate();
      } else {
         throw new IllegalArgumentException("View " + var1 + " is not a sliding drawer");
      }
   }

   public void closeDrawers() {
      this.closeDrawers(false);
   }

   void closeDrawers(boolean var1) {
      int var5 = this.getChildCount();
      int var2 = 0;

      boolean var3;
      boolean var4;
      for(var3 = false; var2 < var5; var3 = var4) {
         View var8 = this.getChildAt(var2);
         LayoutParams var7 = (LayoutParams)var8.getLayoutParams();
         var4 = var3;
         if (this.isDrawerView(var8)) {
            if (var1 && !var7.isPeeking) {
               var4 = var3;
            } else {
               int var9 = var8.getWidth();
               boolean var6;
               if (this.checkDrawerViewAbsoluteGravity(var8, 3)) {
                  var6 = this.mLeftDragger.smoothSlideViewTo(var8, -var9, var8.getTop());
               } else {
                  var6 = this.mRightDragger.smoothSlideViewTo(var8, this.getWidth(), var8.getTop());
               }

               var4 = var3 | var6;
               var7.isPeeking = false;
            }
         }

         ++var2;
      }

      this.mLeftCallback.removeCallbacks();
      this.mRightCallback.removeCallbacks();
      if (var3) {
         this.invalidate();
      }

   }

   public void computeScroll() {
      int var3 = this.getChildCount();
      float var1 = 0.0F;

      for(int var2 = 0; var2 < var3; ++var2) {
         var1 = Math.max(var1, ((LayoutParams)this.getChildAt(var2).getLayoutParams()).onScreen);
      }

      this.mScrimOpacity = var1;
      boolean var5 = this.mLeftDragger.continueSettling(true);
      boolean var4 = this.mRightDragger.continueSettling(true);
      if (var5 || var4) {
         ViewCompat.postInvalidateOnAnimation(this);
      }

   }

   public boolean dispatchGenericMotionEvent(MotionEvent var1) {
      if ((var1.getSource() & 2) != 0 && var1.getAction() != 10 && !(this.mScrimOpacity <= 0.0F)) {
         int var4 = this.getChildCount();
         if (var4 != 0) {
            float var3 = var1.getX();
            float var2 = var1.getY();
            --var4;

            while(var4 >= 0) {
               View var5 = this.getChildAt(var4);
               if (this.isInBoundsOfChild(var3, var2, var5) && !this.isContentView(var5) && this.dispatchTransformedGenericPointerEvent(var1, var5)) {
                  return true;
               }

               --var4;
            }
         }

         return false;
      } else {
         return super.dispatchGenericMotionEvent(var1);
      }
   }

   void dispatchOnDrawerClosed(View var1) {
      LayoutParams var3 = (LayoutParams)var1.getLayoutParams();
      if ((var3.openState & 1) == 1) {
         var3.openState = 0;
         List var4 = this.mListeners;
         if (var4 != null) {
            for(int var2 = var4.size() - 1; var2 >= 0; --var2) {
               ((DrawerListener)this.mListeners.get(var2)).onDrawerClosed(var1);
            }
         }

         this.updateChildrenImportantForAccessibility(var1, false);
         if (this.hasWindowFocus()) {
            var1 = this.getRootView();
            if (var1 != null) {
               var1.sendAccessibilityEvent(32);
            }
         }
      }

   }

   void dispatchOnDrawerOpened(View var1) {
      LayoutParams var3 = (LayoutParams)var1.getLayoutParams();
      if ((var3.openState & 1) == 0) {
         var3.openState = 1;
         List var4 = this.mListeners;
         if (var4 != null) {
            for(int var2 = var4.size() - 1; var2 >= 0; --var2) {
               ((DrawerListener)this.mListeners.get(var2)).onDrawerOpened(var1);
            }
         }

         this.updateChildrenImportantForAccessibility(var1, true);
         if (this.hasWindowFocus()) {
            this.sendAccessibilityEvent(32);
         }
      }

   }

   void dispatchOnDrawerSlide(View var1, float var2) {
      List var4 = this.mListeners;
      if (var4 != null) {
         for(int var3 = var4.size() - 1; var3 >= 0; --var3) {
            ((DrawerListener)this.mListeners.get(var3)).onDrawerSlide(var1, var2);
         }
      }

   }

   protected boolean drawChild(Canvas var1, View var2, long var3) {
      int var13 = this.getHeight();
      boolean var15 = this.isContentView(var2);
      int var6 = this.getWidth();
      int var12 = var1.save();
      int var7 = 0;
      int var8 = var6;
      int var9;
      if (var15) {
         int var14 = this.getChildCount();
         var8 = 0;

         for(var7 = 0; var8 < var14; var7 = var9) {
            View var17 = this.getChildAt(var8);
            int var10 = var6;
            var9 = var7;
            if (var17 != var2) {
               var10 = var6;
               var9 = var7;
               if (var17.getVisibility() == 0) {
                  var10 = var6;
                  var9 = var7;
                  if (hasOpaqueBackground(var17)) {
                     var10 = var6;
                     var9 = var7;
                     if (this.isDrawerView(var17)) {
                        if (var17.getHeight() < var13) {
                           var10 = var6;
                           var9 = var7;
                        } else {
                           int var11;
                           if (this.checkDrawerViewAbsoluteGravity(var17, 3)) {
                              var11 = var17.getRight();
                              var10 = var6;
                              var9 = var7;
                              if (var11 > var7) {
                                 var9 = var11;
                                 var10 = var6;
                              }
                           } else {
                              var11 = var17.getLeft();
                              var10 = var6;
                              var9 = var7;
                              if (var11 < var6) {
                                 var10 = var11;
                                 var9 = var7;
                              }
                           }
                        }
                     }
                  }
               }
            }

            ++var8;
            var6 = var10;
         }

         var1.clipRect(var7, 0, var6, this.getHeight());
         var8 = var6;
      }

      boolean var16 = super.drawChild(var1, var2, var3);
      var1.restoreToCount(var12);
      float var5 = this.mScrimOpacity;
      if (var5 > 0.0F && var15) {
         var9 = this.mScrimColor;
         var6 = (int)((float)((-16777216 & var9) >>> 24) * var5);
         this.mScrimPaint.setColor(var9 & 16777215 | var6 << 24);
         var1.drawRect((float)var7, 0.0F, (float)var8, (float)this.getHeight(), this.mScrimPaint);
      } else if (this.mShadowLeftResolved != null && this.checkDrawerViewAbsoluteGravity(var2, 3)) {
         var6 = this.mShadowLeftResolved.getIntrinsicWidth();
         var8 = var2.getRight();
         var7 = this.mLeftDragger.getEdgeSize();
         var5 = Math.max(0.0F, Math.min((float)var8 / (float)var7, 1.0F));
         this.mShadowLeftResolved.setBounds(var8, var2.getTop(), var6 + var8, var2.getBottom());
         this.mShadowLeftResolved.setAlpha((int)(var5 * 255.0F));
         this.mShadowLeftResolved.draw(var1);
      } else if (this.mShadowRightResolved != null && this.checkDrawerViewAbsoluteGravity(var2, 5)) {
         var8 = this.mShadowRightResolved.getIntrinsicWidth();
         var7 = var2.getLeft();
         var6 = this.getWidth();
         var9 = this.mRightDragger.getEdgeSize();
         var5 = Math.max(0.0F, Math.min((float)(var6 - var7) / (float)var9, 1.0F));
         this.mShadowRightResolved.setBounds(var7 - var8, var2.getTop(), var7, var2.getBottom());
         this.mShadowRightResolved.setAlpha((int)(var5 * 255.0F));
         this.mShadowRightResolved.draw(var1);
      }

      return var16;
   }

   View findDrawerWithGravity(int var1) {
      int var3 = GravityCompat.getAbsoluteGravity(var1, ViewCompat.getLayoutDirection(this));
      int var2 = this.getChildCount();

      for(var1 = 0; var1 < var2; ++var1) {
         View var4 = this.getChildAt(var1);
         if ((this.getDrawerViewAbsoluteGravity(var4) & 7) == (var3 & 7)) {
            return var4;
         }
      }

      return null;
   }

   View findOpenDrawer() {
      int var2 = this.getChildCount();

      for(int var1 = 0; var1 < var2; ++var1) {
         View var3 = this.getChildAt(var1);
         if ((((LayoutParams)var3.getLayoutParams()).openState & 1) == 1) {
            return var3;
         }
      }

      return null;
   }

   View findVisibleDrawer() {
      int var2 = this.getChildCount();

      for(int var1 = 0; var1 < var2; ++var1) {
         View var3 = this.getChildAt(var1);
         if (this.isDrawerView(var3) && this.isDrawerVisible(var3)) {
            return var3;
         }
      }

      return null;
   }

   protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
      return new LayoutParams(-1, -1);
   }

   public ViewGroup.LayoutParams generateLayoutParams(AttributeSet var1) {
      return new LayoutParams(this.getContext(), var1);
   }

   protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams var1) {
      LayoutParams var2;
      if (var1 instanceof LayoutParams) {
         var2 = new LayoutParams((LayoutParams)var1);
      } else if (var1 instanceof ViewGroup.MarginLayoutParams) {
         var2 = new LayoutParams((ViewGroup.MarginLayoutParams)var1);
      } else {
         var2 = new LayoutParams(var1);
      }

      return var2;
   }

   public float getDrawerElevation() {
      return SET_DRAWER_SHADOW_FROM_ELEVATION ? this.mDrawerElevation : 0.0F;
   }

   public int getDrawerLockMode(int var1) {
      int var2 = ViewCompat.getLayoutDirection(this);
      if (var1 != 3) {
         if (var1 != 5) {
            if (var1 != 8388611) {
               if (var1 == 8388613) {
                  var1 = this.mLockModeEnd;
                  if (var1 != 3) {
                     return var1;
                  }

                  if (var2 == 0) {
                     var1 = this.mLockModeRight;
                  } else {
                     var1 = this.mLockModeLeft;
                  }

                  if (var1 != 3) {
                     return var1;
                  }
               }
            } else {
               var1 = this.mLockModeStart;
               if (var1 != 3) {
                  return var1;
               }

               if (var2 == 0) {
                  var1 = this.mLockModeLeft;
               } else {
                  var1 = this.mLockModeRight;
               }

               if (var1 != 3) {
                  return var1;
               }
            }
         } else {
            var1 = this.mLockModeRight;
            if (var1 != 3) {
               return var1;
            }

            if (var2 == 0) {
               var1 = this.mLockModeEnd;
            } else {
               var1 = this.mLockModeStart;
            }

            if (var1 != 3) {
               return var1;
            }
         }
      } else {
         var1 = this.mLockModeLeft;
         if (var1 != 3) {
            return var1;
         }

         if (var2 == 0) {
            var1 = this.mLockModeStart;
         } else {
            var1 = this.mLockModeEnd;
         }

         if (var1 != 3) {
            return var1;
         }
      }

      return 0;
   }

   public int getDrawerLockMode(View var1) {
      if (this.isDrawerView(var1)) {
         return this.getDrawerLockMode(((LayoutParams)var1.getLayoutParams()).gravity);
      } else {
         throw new IllegalArgumentException("View " + var1 + " is not a drawer");
      }
   }

   public CharSequence getDrawerTitle(int var1) {
      var1 = GravityCompat.getAbsoluteGravity(var1, ViewCompat.getLayoutDirection(this));
      if (var1 == 3) {
         return this.mTitleLeft;
      } else {
         return var1 == 5 ? this.mTitleRight : null;
      }
   }

   int getDrawerViewAbsoluteGravity(View var1) {
      return GravityCompat.getAbsoluteGravity(((LayoutParams)var1.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(this));
   }

   float getDrawerViewOffset(View var1) {
      return ((LayoutParams)var1.getLayoutParams()).onScreen;
   }

   public Drawable getStatusBarBackgroundDrawable() {
      return this.mStatusBarBackground;
   }

   boolean isContentView(View var1) {
      boolean var2;
      if (((LayoutParams)var1.getLayoutParams()).gravity == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public boolean isDrawerOpen(int var1) {
      View var2 = this.findDrawerWithGravity(var1);
      return var2 != null ? this.isDrawerOpen(var2) : false;
   }

   public boolean isDrawerOpen(View var1) {
      if (this.isDrawerView(var1)) {
         int var2 = ((LayoutParams)var1.getLayoutParams()).openState;
         boolean var3 = true;
         if ((var2 & 1) != 1) {
            var3 = false;
         }

         return var3;
      } else {
         throw new IllegalArgumentException("View " + var1 + " is not a drawer");
      }
   }

   boolean isDrawerView(View var1) {
      int var2 = GravityCompat.getAbsoluteGravity(((LayoutParams)var1.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(var1));
      if ((var2 & 3) != 0) {
         return true;
      } else {
         return (var2 & 5) != 0;
      }
   }

   public boolean isDrawerVisible(int var1) {
      View var2 = this.findDrawerWithGravity(var1);
      return var2 != null ? this.isDrawerVisible(var2) : false;
   }

   public boolean isDrawerVisible(View var1) {
      if (this.isDrawerView(var1)) {
         boolean var2;
         if (((LayoutParams)var1.getLayoutParams()).onScreen > 0.0F) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      } else {
         throw new IllegalArgumentException("View " + var1 + " is not a drawer");
      }
   }

   void moveDrawerToOffset(View var1, float var2) {
      float var4 = this.getDrawerViewOffset(var1);
      float var3 = (float)var1.getWidth();
      int var5 = (int)(var4 * var3);
      var5 = (int)(var3 * var2) - var5;
      if (!this.checkDrawerViewAbsoluteGravity(var1, 3)) {
         var5 = -var5;
      }

      var1.offsetLeftAndRight(var5);
      this.setDrawerViewOffset(var1, var2);
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      this.mFirstLayout = true;
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      this.mFirstLayout = true;
   }

   public void onDraw(Canvas var1) {
      super.onDraw(var1);
      if (this.mDrawStatusBarBackground && this.mStatusBarBackground != null) {
         int var2;
         label17: {
            if (VERSION.SDK_INT >= 21) {
               Object var3 = this.mLastInsets;
               if (var3 != null) {
                  var2 = ((WindowInsets)var3).getSystemWindowInsetTop();
                  break label17;
               }
            }

            var2 = 0;
         }

         if (var2 > 0) {
            this.mStatusBarBackground.setBounds(0, 0, this.getWidth(), var2);
            this.mStatusBarBackground.draw(var1);
         }
      }

   }

   public boolean onInterceptTouchEvent(MotionEvent var1) {
      int var4 = var1.getActionMasked();
      boolean var8 = this.mLeftDragger.shouldInterceptTouchEvent(var1);
      boolean var7 = this.mRightDragger.shouldInterceptTouchEvent(var1);
      boolean var6 = true;
      boolean var10;
      if (var4 != 0) {
         label43: {
            if (var4 != 1) {
               if (var4 == 2) {
                  if (this.mLeftDragger.checkTouchSlop(3)) {
                     this.mLeftCallback.removeCallbacks();
                     this.mRightCallback.removeCallbacks();
                  }
                  break label43;
               }

               if (var4 != 3) {
                  break label43;
               }
            }

            this.closeDrawers(true);
            this.mDisallowInterceptRequested = false;
            this.mChildrenCanceledTouch = false;
         }

         var10 = false;
      } else {
         label36: {
            float var2 = var1.getX();
            float var3 = var1.getY();
            this.mInitialMotionX = var2;
            this.mInitialMotionY = var3;
            if (this.mScrimOpacity > 0.0F) {
               View var9 = this.mLeftDragger.findTopChildUnder((int)var2, (int)var3);
               if (var9 != null && this.isContentView(var9)) {
                  var10 = true;
                  break label36;
               }
            }

            var10 = false;
         }

         this.mDisallowInterceptRequested = false;
         this.mChildrenCanceledTouch = false;
      }

      boolean var5 = var6;
      if (!(var8 | var7)) {
         var5 = var6;
         if (!var10) {
            var5 = var6;
            if (!this.hasPeekingDrawer()) {
               if (this.mChildrenCanceledTouch) {
                  var5 = var6;
               } else {
                  var5 = false;
               }
            }
         }
      }

      return var5;
   }

   public boolean onKeyDown(int var1, KeyEvent var2) {
      if (var1 == 4 && this.hasVisibleDrawer()) {
         var2.startTracking();
         return true;
      } else {
         return super.onKeyDown(var1, var2);
      }
   }

   public boolean onKeyUp(int var1, KeyEvent var2) {
      if (var1 == 4) {
         View var4 = this.findVisibleDrawer();
         if (var4 != null && this.getDrawerLockMode(var4) == 0) {
            this.closeDrawers();
         }

         boolean var3;
         if (var4 != null) {
            var3 = true;
         } else {
            var3 = false;
         }

         return var3;
      } else {
         return super.onKeyUp(var1, var2);
      }
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      this.mInLayout = true;
      int var10 = var4 - var2;
      int var11 = this.getChildCount();

      for(var4 = 0; var4 < var11; ++var4) {
         View var16 = this.getChildAt(var4);
         if (var16.getVisibility() != 8) {
            LayoutParams var15 = (LayoutParams)var16.getLayoutParams();
            if (this.isContentView(var16)) {
               var16.layout(var15.leftMargin, var15.topMargin, var15.leftMargin + var16.getMeasuredWidth(), var15.topMargin + var16.getMeasuredHeight());
            } else {
               int var12 = var16.getMeasuredWidth();
               int var13 = var16.getMeasuredHeight();
               float var6;
               int var7;
               if (this.checkDrawerViewAbsoluteGravity(var16, 3)) {
                  var2 = -var12;
                  var6 = (float)var12;
                  var7 = var2 + (int)(var15.onScreen * var6);
                  var6 = (float)(var12 + var7) / var6;
               } else {
                  var6 = (float)var12;
                  var7 = var10 - (int)(var15.onScreen * var6);
                  var6 = (float)(var10 - var7) / var6;
               }

               boolean var8;
               if (var6 != var15.onScreen) {
                  var8 = true;
               } else {
                  var8 = false;
               }

               var2 = var15.gravity & 112;
               if (var2 != 16) {
                  if (var2 != 80) {
                     var16.layout(var7, var15.topMargin, var12 + var7, var15.topMargin + var13);
                  } else {
                     var2 = var5 - var3;
                     var16.layout(var7, var2 - var15.bottomMargin - var16.getMeasuredHeight(), var12 + var7, var2 - var15.bottomMargin);
                  }
               } else {
                  int var14 = var5 - var3;
                  int var9 = (var14 - var13) / 2;
                  if (var9 < var15.topMargin) {
                     var2 = var15.topMargin;
                  } else {
                     var2 = var9;
                     if (var9 + var13 > var14 - var15.bottomMargin) {
                        var2 = var14 - var15.bottomMargin - var13;
                     }
                  }

                  var16.layout(var7, var2, var12 + var7, var13 + var2);
               }

               if (var8) {
                  this.setDrawerViewOffset(var16, var6);
               }

               byte var17;
               if (var15.onScreen > 0.0F) {
                  var17 = 0;
               } else {
                  var17 = 4;
               }

               if (var16.getVisibility() != var17) {
                  var16.setVisibility(var17);
               }
            }
         }
      }

      this.mInLayout = false;
      this.mFirstLayout = false;
   }

   protected void onMeasure(int var1, int var2) {
      int var7;
      int var8;
      int var10;
      label114: {
         var10 = MeasureSpec.getMode(var1);
         int var9 = MeasureSpec.getMode(var2);
         int var5 = MeasureSpec.getSize(var1);
         int var6 = MeasureSpec.getSize(var2);
         if (var10 == 1073741824) {
            var7 = var5;
            var8 = var6;
            if (var9 == 1073741824) {
               break label114;
            }
         }

         if (!this.isInEditMode()) {
            throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
         }

         if (var10 != Integer.MIN_VALUE && var10 == 0) {
            var5 = 300;
         }

         if (var9 == Integer.MIN_VALUE) {
            var7 = var5;
            var8 = var6;
         } else {
            var7 = var5;
            var8 = var6;
            if (var9 == 0) {
               var8 = 300;
               var7 = var5;
            }
         }
      }

      this.setMeasuredDimension(var7, var8);
      boolean var21;
      if (this.mLastInsets != null && ViewCompat.getFitsSystemWindows(this)) {
         var21 = true;
      } else {
         var21 = false;
      }

      int var13 = ViewCompat.getLayoutDirection(this);
      int var12 = this.getChildCount();
      var10 = 0;
      boolean var19 = false;

      for(boolean var20 = var19; var10 < var12; ++var10) {
         View var17 = this.getChildAt(var10);
         if (var17.getVisibility() != 8) {
            LayoutParams var18 = (LayoutParams)var17.getLayoutParams();
            if (var21) {
               int var11 = GravityCompat.getAbsoluteGravity(var18.gravity, var13);
               WindowInsets var15;
               WindowInsets var16;
               if (ViewCompat.getFitsSystemWindows(var17)) {
                  if (VERSION.SDK_INT >= 21) {
                     var16 = (WindowInsets)this.mLastInsets;
                     if (var11 == 3) {
                        var15 = var16.replaceSystemWindowInsets(var16.getSystemWindowInsetLeft(), var16.getSystemWindowInsetTop(), 0, var16.getSystemWindowInsetBottom());
                     } else {
                        var15 = var16;
                        if (var11 == 5) {
                           var15 = var16.replaceSystemWindowInsets(0, var16.getSystemWindowInsetTop(), var16.getSystemWindowInsetRight(), var16.getSystemWindowInsetBottom());
                        }
                     }

                     var17.dispatchApplyWindowInsets(var15);
                  }
               } else if (VERSION.SDK_INT >= 21) {
                  var16 = (WindowInsets)this.mLastInsets;
                  if (var11 == 3) {
                     var15 = var16.replaceSystemWindowInsets(var16.getSystemWindowInsetLeft(), var16.getSystemWindowInsetTop(), 0, var16.getSystemWindowInsetBottom());
                  } else {
                     var15 = var16;
                     if (var11 == 5) {
                        var15 = var16.replaceSystemWindowInsets(0, var16.getSystemWindowInsetTop(), var16.getSystemWindowInsetRight(), var16.getSystemWindowInsetBottom());
                     }
                  }

                  var18.leftMargin = var15.getSystemWindowInsetLeft();
                  var18.topMargin = var15.getSystemWindowInsetTop();
                  var18.rightMargin = var15.getSystemWindowInsetRight();
                  var18.bottomMargin = var15.getSystemWindowInsetBottom();
               }
            }

            if (this.isContentView(var17)) {
               var17.measure(MeasureSpec.makeMeasureSpec(var7 - var18.leftMargin - var18.rightMargin, 1073741824), MeasureSpec.makeMeasureSpec(var8 - var18.topMargin - var18.bottomMargin, 1073741824));
            } else {
               if (!this.isDrawerView(var17)) {
                  throw new IllegalStateException("Child " + var17 + " at index " + var10 + " does not have a valid layout_gravity - must be Gravity.LEFT, " + "Gravity.RIGHT or Gravity.NO_GRAVITY");
               }

               if (SET_DRAWER_SHADOW_FROM_ELEVATION) {
                  float var4 = ViewCompat.getElevation(var17);
                  float var3 = this.mDrawerElevation;
                  if (var4 != var3) {
                     ViewCompat.setElevation(var17, var3);
                  }
               }

               int var14 = this.getDrawerViewAbsoluteGravity(var17) & 7;
               boolean var22;
               if (var14 == 3) {
                  var22 = true;
               } else {
                  var22 = false;
               }

               if (var22 && var19 || !var22 && var20) {
                  throw new IllegalStateException("Child drawer has absolute gravity " + gravityToString(var14) + " but this " + "DrawerLayout" + " already has a " + "drawer view along that edge");
               }

               if (var22) {
                  var19 = true;
               } else {
                  var20 = true;
               }

               var17.measure(getChildMeasureSpec(var1, this.mMinDrawerMargin + var18.leftMargin + var18.rightMargin, var18.width), getChildMeasureSpec(var2, var18.topMargin + var18.bottomMargin, var18.height));
            }
         }
      }

   }

   protected void onRestoreInstanceState(Parcelable var1) {
      if (!(var1 instanceof SavedState)) {
         super.onRestoreInstanceState(var1);
      } else {
         SavedState var3 = (SavedState)var1;
         super.onRestoreInstanceState(var3.getSuperState());
         if (var3.openDrawerGravity != 0) {
            View var2 = this.findDrawerWithGravity(var3.openDrawerGravity);
            if (var2 != null) {
               this.openDrawer(var2);
            }
         }

         if (var3.lockModeLeft != 3) {
            this.setDrawerLockMode(var3.lockModeLeft, 3);
         }

         if (var3.lockModeRight != 3) {
            this.setDrawerLockMode(var3.lockModeRight, 5);
         }

         if (var3.lockModeStart != 3) {
            this.setDrawerLockMode(var3.lockModeStart, 8388611);
         }

         if (var3.lockModeEnd != 3) {
            this.setDrawerLockMode(var3.lockModeEnd, 8388613);
         }

      }
   }

   public void onRtlPropertiesChanged(int var1) {
      this.resolveShadowDrawables();
   }

   protected Parcelable onSaveInstanceState() {
      SavedState var5 = new SavedState(super.onSaveInstanceState());
      int var4 = this.getChildCount();

      for(int var1 = 0; var1 < var4; ++var1) {
         LayoutParams var6 = (LayoutParams)this.getChildAt(var1).getLayoutParams();
         int var2 = var6.openState;
         boolean var3 = true;
         boolean var7;
         if (var2 == 1) {
            var7 = true;
         } else {
            var7 = false;
         }

         if (var6.openState != 2) {
            var3 = false;
         }

         if (var7 || var3) {
            var5.openDrawerGravity = var6.gravity;
            break;
         }
      }

      var5.lockModeLeft = this.mLockModeLeft;
      var5.lockModeRight = this.mLockModeRight;
      var5.lockModeStart = this.mLockModeStart;
      var5.lockModeEnd = this.mLockModeEnd;
      return var5;
   }

   public boolean onTouchEvent(MotionEvent var1) {
      this.mLeftDragger.processTouchEvent(var1);
      this.mRightDragger.processTouchEvent(var1);
      int var4 = var1.getAction() & 255;
      float var2;
      float var3;
      if (var4 != 0) {
         if (var4 != 1) {
            if (var4 == 3) {
               this.closeDrawers(true);
               this.mDisallowInterceptRequested = false;
               this.mChildrenCanceledTouch = false;
            }
         } else {
            boolean var5;
            label27: {
               var3 = var1.getX();
               var2 = var1.getY();
               View var6 = this.mLeftDragger.findTopChildUnder((int)var3, (int)var2);
               if (var6 != null && this.isContentView(var6)) {
                  var3 -= this.mInitialMotionX;
                  var2 -= this.mInitialMotionY;
                  var4 = this.mLeftDragger.getTouchSlop();
                  if (var3 * var3 + var2 * var2 < (float)(var4 * var4)) {
                     var6 = this.findOpenDrawer();
                     if (var6 != null && this.getDrawerLockMode(var6) != 2) {
                        var5 = false;
                        break label27;
                     }
                  }
               }

               var5 = true;
            }

            this.closeDrawers(var5);
            this.mDisallowInterceptRequested = false;
         }
      } else {
         var2 = var1.getX();
         var3 = var1.getY();
         this.mInitialMotionX = var2;
         this.mInitialMotionY = var3;
         this.mDisallowInterceptRequested = false;
         this.mChildrenCanceledTouch = false;
      }

      return true;
   }

   public void openDrawer(int var1) {
      this.openDrawer(var1, true);
   }

   public void openDrawer(int var1, boolean var2) {
      View var3 = this.findDrawerWithGravity(var1);
      if (var3 != null) {
         this.openDrawer(var3, var2);
      } else {
         throw new IllegalArgumentException("No drawer view found with gravity " + gravityToString(var1));
      }
   }

   public void openDrawer(View var1) {
      this.openDrawer(var1, true);
   }

   public void openDrawer(View var1, boolean var2) {
      if (this.isDrawerView(var1)) {
         LayoutParams var3 = (LayoutParams)var1.getLayoutParams();
         if (this.mFirstLayout) {
            var3.onScreen = 1.0F;
            var3.openState = 1;
            this.updateChildrenImportantForAccessibility(var1, true);
         } else if (var2) {
            var3.openState |= 2;
            if (this.checkDrawerViewAbsoluteGravity(var1, 3)) {
               this.mLeftDragger.smoothSlideViewTo(var1, 0, var1.getTop());
            } else {
               this.mRightDragger.smoothSlideViewTo(var1, this.getWidth() - var1.getWidth(), var1.getTop());
            }
         } else {
            this.moveDrawerToOffset(var1, 1.0F);
            this.updateDrawerState(var3.gravity, 0, var1);
            var1.setVisibility(0);
         }

         this.invalidate();
      } else {
         throw new IllegalArgumentException("View " + var1 + " is not a sliding drawer");
      }
   }

   public void removeDrawerListener(DrawerListener var1) {
      if (var1 != null) {
         List var2 = this.mListeners;
         if (var2 != null) {
            var2.remove(var1);
         }
      }
   }

   public void requestDisallowInterceptTouchEvent(boolean var1) {
      super.requestDisallowInterceptTouchEvent(var1);
      this.mDisallowInterceptRequested = var1;
      if (var1) {
         this.closeDrawers(true);
      }

   }

   public void requestLayout() {
      if (!this.mInLayout) {
         super.requestLayout();
      }

   }

   public void setChildInsets(Object var1, boolean var2) {
      this.mLastInsets = var1;
      this.mDrawStatusBarBackground = var2;
      if (!var2 && this.getBackground() == null) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.setWillNotDraw(var2);
      this.requestLayout();
   }

   public void setDrawerElevation(float var1) {
      this.mDrawerElevation = var1;

      for(int var2 = 0; var2 < this.getChildCount(); ++var2) {
         View var3 = this.getChildAt(var2);
         if (this.isDrawerView(var3)) {
            ViewCompat.setElevation(var3, this.mDrawerElevation);
         }
      }

   }

   @Deprecated
   public void setDrawerListener(DrawerListener var1) {
      DrawerListener var2 = this.mListener;
      if (var2 != null) {
         this.removeDrawerListener(var2);
      }

      if (var1 != null) {
         this.addDrawerListener(var1);
      }

      this.mListener = var1;
   }

   public void setDrawerLockMode(int var1) {
      this.setDrawerLockMode(var1, 3);
      this.setDrawerLockMode(var1, 5);
   }

   public void setDrawerLockMode(int var1, int var2) {
      int var3 = GravityCompat.getAbsoluteGravity(var2, ViewCompat.getLayoutDirection(this));
      if (var2 != 3) {
         if (var2 != 5) {
            if (var2 != 8388611) {
               if (var2 == 8388613) {
                  this.mLockModeEnd = var1;
               }
            } else {
               this.mLockModeStart = var1;
            }
         } else {
            this.mLockModeRight = var1;
         }
      } else {
         this.mLockModeLeft = var1;
      }

      if (var1 != 0) {
         ViewDragHelper var4;
         if (var3 == 3) {
            var4 = this.mLeftDragger;
         } else {
            var4 = this.mRightDragger;
         }

         var4.cancel();
      }

      View var5;
      if (var1 != 1) {
         if (var1 == 2) {
            var5 = this.findDrawerWithGravity(var3);
            if (var5 != null) {
               this.openDrawer(var5);
            }
         }
      } else {
         var5 = this.findDrawerWithGravity(var3);
         if (var5 != null) {
            this.closeDrawer(var5);
         }
      }

   }

   public void setDrawerLockMode(int var1, View var2) {
      if (this.isDrawerView(var2)) {
         this.setDrawerLockMode(var1, ((LayoutParams)var2.getLayoutParams()).gravity);
      } else {
         throw new IllegalArgumentException("View " + var2 + " is not a " + "drawer with appropriate layout_gravity");
      }
   }

   public void setDrawerShadow(int var1, int var2) {
      this.setDrawerShadow(ContextCompat.getDrawable(this.getContext(), var1), var2);
   }

   public void setDrawerShadow(Drawable var1, int var2) {
      if (!SET_DRAWER_SHADOW_FROM_ELEVATION) {
         if ((var2 & 8388611) == 8388611) {
            this.mShadowStart = var1;
         } else if ((var2 & 8388613) == 8388613) {
            this.mShadowEnd = var1;
         } else if ((var2 & 3) == 3) {
            this.mShadowLeft = var1;
         } else {
            if ((var2 & 5) != 5) {
               return;
            }

            this.mShadowRight = var1;
         }

         this.resolveShadowDrawables();
         this.invalidate();
      }
   }

   public void setDrawerTitle(int var1, CharSequence var2) {
      var1 = GravityCompat.getAbsoluteGravity(var1, ViewCompat.getLayoutDirection(this));
      if (var1 == 3) {
         this.mTitleLeft = var2;
      } else if (var1 == 5) {
         this.mTitleRight = var2;
      }

   }

   void setDrawerViewOffset(View var1, float var2) {
      LayoutParams var3 = (LayoutParams)var1.getLayoutParams();
      if (var2 != var3.onScreen) {
         var3.onScreen = var2;
         this.dispatchOnDrawerSlide(var1, var2);
      }
   }

   public void setScrimColor(int var1) {
      this.mScrimColor = var1;
      this.invalidate();
   }

   public void setStatusBarBackground(int var1) {
      Drawable var2;
      if (var1 != 0) {
         var2 = ContextCompat.getDrawable(this.getContext(), var1);
      } else {
         var2 = null;
      }

      this.mStatusBarBackground = var2;
      this.invalidate();
   }

   public void setStatusBarBackground(Drawable var1) {
      this.mStatusBarBackground = var1;
      this.invalidate();
   }

   public void setStatusBarBackgroundColor(int var1) {
      this.mStatusBarBackground = new ColorDrawable(var1);
      this.invalidate();
   }

   void updateDrawerState(int var1, int var2, View var3) {
      int var5 = this.mLeftDragger.getViewDragState();
      int var6 = this.mRightDragger.getViewDragState();
      byte var4 = 2;
      byte var8;
      if (var5 != 1 && var6 != 1) {
         var8 = var4;
         if (var5 != 2) {
            if (var6 == 2) {
               var8 = var4;
            } else {
               var8 = 0;
            }
         }
      } else {
         var8 = 1;
      }

      if (var3 != null && var2 == 0) {
         LayoutParams var7 = (LayoutParams)var3.getLayoutParams();
         if (var7.onScreen == 0.0F) {
            this.dispatchOnDrawerClosed(var3);
         } else if (var7.onScreen == 1.0F) {
            this.dispatchOnDrawerOpened(var3);
         }
      }

      if (var8 != this.mDrawerState) {
         this.mDrawerState = var8;
         List var9 = this.mListeners;
         if (var9 != null) {
            for(var2 = var9.size() - 1; var2 >= 0; --var2) {
               ((DrawerListener)this.mListeners.get(var2)).onDrawerStateChanged(var8);
            }
         }
      }

   }

   class AccessibilityDelegate extends AccessibilityDelegateCompat {
      private final Rect mTmpRect;
      final DrawerLayout this$0;

      AccessibilityDelegate(DrawerLayout var1) {
         this.this$0 = var1;
         this.mTmpRect = new Rect();
      }

      private void addChildrenForAccessibility(AccessibilityNodeInfoCompat var1, ViewGroup var2) {
         int var4 = var2.getChildCount();

         for(int var3 = 0; var3 < var4; ++var3) {
            View var5 = var2.getChildAt(var3);
            if (DrawerLayout.includeChildForAccessibility(var5)) {
               var1.addChild(var5);
            }
         }

      }

      private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat var1, AccessibilityNodeInfoCompat var2) {
         Rect var3 = this.mTmpRect;
         var2.getBoundsInParent(var3);
         var1.setBoundsInParent(var3);
         var2.getBoundsInScreen(var3);
         var1.setBoundsInScreen(var3);
         var1.setVisibleToUser(var2.isVisibleToUser());
         var1.setPackageName(var2.getPackageName());
         var1.setClassName(var2.getClassName());
         var1.setContentDescription(var2.getContentDescription());
         var1.setEnabled(var2.isEnabled());
         var1.setClickable(var2.isClickable());
         var1.setFocusable(var2.isFocusable());
         var1.setFocused(var2.isFocused());
         var1.setAccessibilityFocused(var2.isAccessibilityFocused());
         var1.setSelected(var2.isSelected());
         var1.setLongClickable(var2.isLongClickable());
         var1.addAction(var2.getActions());
      }

      public boolean dispatchPopulateAccessibilityEvent(View var1, AccessibilityEvent var2) {
         if (var2.getEventType() == 32) {
            List var4 = var2.getText();
            View var5 = this.this$0.findVisibleDrawer();
            if (var5 != null) {
               int var3 = this.this$0.getDrawerViewAbsoluteGravity(var5);
               CharSequence var6 = this.this$0.getDrawerTitle(var3);
               if (var6 != null) {
                  var4.add(var6);
               }
            }

            return true;
         } else {
            return super.dispatchPopulateAccessibilityEvent(var1, var2);
         }
      }

      public void onInitializeAccessibilityEvent(View var1, AccessibilityEvent var2) {
         super.onInitializeAccessibilityEvent(var1, var2);
         var2.setClassName(DrawerLayout.class.getName());
      }

      public void onInitializeAccessibilityNodeInfo(View var1, AccessibilityNodeInfoCompat var2) {
         if (DrawerLayout.CAN_HIDE_DESCENDANTS) {
            super.onInitializeAccessibilityNodeInfo(var1, var2);
         } else {
            AccessibilityNodeInfoCompat var3 = AccessibilityNodeInfoCompat.obtain(var2);
            super.onInitializeAccessibilityNodeInfo(var1, var3);
            var2.setSource(var1);
            ViewParent var4 = ViewCompat.getParentForAccessibility(var1);
            if (var4 instanceof View) {
               var2.setParent((View)var4);
            }

            this.copyNodeInfoNoChildren(var2, var3);
            var3.recycle();
            this.addChildrenForAccessibility(var2, (ViewGroup)var1);
         }

         var2.setClassName(DrawerLayout.class.getName());
         var2.setFocusable(false);
         var2.setFocused(false);
         var2.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_FOCUS);
         var2.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLEAR_FOCUS);
      }

      public boolean onRequestSendAccessibilityEvent(ViewGroup var1, View var2, AccessibilityEvent var3) {
         return !DrawerLayout.CAN_HIDE_DESCENDANTS && !DrawerLayout.includeChildForAccessibility(var2) ? false : super.onRequestSendAccessibilityEvent(var1, var2, var3);
      }
   }

   static final class ChildAccessibilityDelegate extends AccessibilityDelegateCompat {
      public void onInitializeAccessibilityNodeInfo(View var1, AccessibilityNodeInfoCompat var2) {
         super.onInitializeAccessibilityNodeInfo(var1, var2);
         if (!DrawerLayout.includeChildForAccessibility(var1)) {
            var2.setParent((View)null);
         }

      }
   }

   public interface DrawerListener {
      void onDrawerClosed(View var1);

      void onDrawerOpened(View var1);

      void onDrawerSlide(View var1, float var2);

      void onDrawerStateChanged(int var1);
   }

   public static class LayoutParams extends ViewGroup.MarginLayoutParams {
      private static final int FLAG_IS_CLOSING = 4;
      private static final int FLAG_IS_OPENED = 1;
      private static final int FLAG_IS_OPENING = 2;
      public int gravity;
      boolean isPeeking;
      float onScreen;
      int openState;

      public LayoutParams(int var1, int var2) {
         super(var1, var2);
         this.gravity = 0;
      }

      public LayoutParams(int var1, int var2, int var3) {
         this(var1, var2);
         this.gravity = var3;
      }

      public LayoutParams(Context var1, AttributeSet var2) {
         super(var1, var2);
         this.gravity = 0;
         TypedArray var3 = var1.obtainStyledAttributes(var2, DrawerLayout.LAYOUT_ATTRS);
         this.gravity = var3.getInt(0, 0);
         var3.recycle();
      }

      public LayoutParams(ViewGroup.LayoutParams var1) {
         super(var1);
         this.gravity = 0;
      }

      public LayoutParams(ViewGroup.MarginLayoutParams var1) {
         super(var1);
         this.gravity = 0;
      }

      public LayoutParams(LayoutParams var1) {
         super(var1);
         this.gravity = 0;
         this.gravity = var1.gravity;
      }
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
      int lockModeEnd;
      int lockModeLeft;
      int lockModeRight;
      int lockModeStart;
      int openDrawerGravity = 0;

      public SavedState(Parcel var1, ClassLoader var2) {
         super(var1, var2);
         this.openDrawerGravity = var1.readInt();
         this.lockModeLeft = var1.readInt();
         this.lockModeRight = var1.readInt();
         this.lockModeStart = var1.readInt();
         this.lockModeEnd = var1.readInt();
      }

      public SavedState(Parcelable var1) {
         super(var1);
      }

      public void writeToParcel(Parcel var1, int var2) {
         super.writeToParcel(var1, var2);
         var1.writeInt(this.openDrawerGravity);
         var1.writeInt(this.lockModeLeft);
         var1.writeInt(this.lockModeRight);
         var1.writeInt(this.lockModeStart);
         var1.writeInt(this.lockModeEnd);
      }
   }

   public abstract static class SimpleDrawerListener implements DrawerListener {
      public void onDrawerClosed(View var1) {
      }

      public void onDrawerOpened(View var1) {
      }

      public void onDrawerSlide(View var1, float var2) {
      }

      public void onDrawerStateChanged(int var1) {
      }
   }

   private class ViewDragCallback extends ViewDragHelper.Callback {
      private final int mAbsGravity;
      private ViewDragHelper mDragger;
      private final Runnable mPeekRunnable;
      final DrawerLayout this$0;

      ViewDragCallback(DrawerLayout var1, int var2) {
         this.this$0 = var1;
         this.mPeekRunnable = new Runnable(this) {
            final ViewDragCallback this$1;

            {
               this.this$1 = var1;
            }

            public void run() {
               this.this$1.peekDrawer();
            }
         };
         this.mAbsGravity = var2;
      }

      private void closeOtherDrawer() {
         int var2 = this.mAbsGravity;
         byte var1 = 3;
         if (var2 == 3) {
            var1 = 5;
         }

         View var3 = this.this$0.findDrawerWithGravity(var1);
         if (var3 != null) {
            this.this$0.closeDrawer(var3);
         }

      }

      public int clampViewPositionHorizontal(View var1, int var2, int var3) {
         if (this.this$0.checkDrawerViewAbsoluteGravity(var1, 3)) {
            return Math.max(-var1.getWidth(), Math.min(var2, 0));
         } else {
            var3 = this.this$0.getWidth();
            return Math.max(var3 - var1.getWidth(), Math.min(var2, var3));
         }
      }

      public int clampViewPositionVertical(View var1, int var2, int var3) {
         return var1.getTop();
      }

      public int getViewHorizontalDragRange(View var1) {
         int var2;
         if (this.this$0.isDrawerView(var1)) {
            var2 = var1.getWidth();
         } else {
            var2 = 0;
         }

         return var2;
      }

      public void onEdgeDragStarted(int var1, int var2) {
         View var3;
         if ((var1 & 1) == 1) {
            var3 = this.this$0.findDrawerWithGravity(3);
         } else {
            var3 = this.this$0.findDrawerWithGravity(5);
         }

         if (var3 != null && this.this$0.getDrawerLockMode(var3) == 0) {
            this.mDragger.captureChildView(var3, var2);
         }

      }

      public boolean onEdgeLock(int var1) {
         return false;
      }

      public void onEdgeTouched(int var1, int var2) {
         this.this$0.postDelayed(this.mPeekRunnable, 160L);
      }

      public void onViewCaptured(View var1, int var2) {
         ((LayoutParams)var1.getLayoutParams()).isPeeking = false;
         this.closeOtherDrawer();
      }

      public void onViewDragStateChanged(int var1) {
         this.this$0.updateDrawerState(this.mAbsGravity, var1, this.mDragger.getCapturedView());
      }

      public void onViewPositionChanged(View var1, int var2, int var3, int var4, int var5) {
         var3 = var1.getWidth();
         float var6;
         if (this.this$0.checkDrawerViewAbsoluteGravity(var1, 3)) {
            var6 = (float)(var2 + var3);
         } else {
            var6 = (float)(this.this$0.getWidth() - var2);
         }

         var6 /= (float)var3;
         this.this$0.setDrawerViewOffset(var1, var6);
         byte var7;
         if (var6 == 0.0F) {
            var7 = 4;
         } else {
            var7 = 0;
         }

         var1.setVisibility(var7);
         this.this$0.invalidate();
      }

      public void onViewReleased(View var1, float var2, float var3) {
         var3 = this.this$0.getDrawerViewOffset(var1);
         int var6 = var1.getWidth();
         int var4;
         if (this.this$0.checkDrawerViewAbsoluteGravity(var1, 3)) {
            float var7;
            var4 = (var7 = var2 - 0.0F) == 0.0F ? 0 : (var7 < 0.0F ? -1 : 1);
            if (var4 > 0 || var4 == 0 && var3 > 0.5F) {
               var4 = 0;
            } else {
               var4 = -var6;
            }
         } else {
            label21: {
               int var5 = this.this$0.getWidth();
               if (!(var2 < 0.0F)) {
                  var4 = var5;
                  if (var2 != 0.0F) {
                     break label21;
                  }

                  var4 = var5;
                  if (!(var3 > 0.5F)) {
                     break label21;
                  }
               }

               var4 = var5 - var6;
            }
         }

         this.mDragger.settleCapturedViewAt(var4, var1.getTop());
         this.this$0.invalidate();
      }

      void peekDrawer() {
         int var3 = this.mDragger.getEdgeSize();
         int var1 = this.mAbsGravity;
         int var2 = 0;
         boolean var6;
         if (var1 == 3) {
            var6 = true;
         } else {
            var6 = false;
         }

         View var4;
         if (var6) {
            var4 = this.this$0.findDrawerWithGravity(3);
            if (var4 != null) {
               var2 = -var4.getWidth();
            }

            var2 += var3;
         } else {
            var4 = this.this$0.findDrawerWithGravity(5);
            var2 = this.this$0.getWidth() - var3;
         }

         if (var4 != null && (var6 && var4.getLeft() < var2 || !var6 && var4.getLeft() > var2) && this.this$0.getDrawerLockMode(var4) == 0) {
            LayoutParams var5 = (LayoutParams)var4.getLayoutParams();
            this.mDragger.smoothSlideViewTo(var4, var2, var4.getTop());
            var5.isPeeking = true;
            this.this$0.invalidate();
            this.closeOtherDrawer();
            this.this$0.cancelChildViewTouch();
         }

      }

      public void removeCallbacks() {
         this.this$0.removeCallbacks(this.mPeekRunnable);
      }

      public void setDragger(ViewDragHelper var1) {
         this.mDragger = var1;
      }

      public boolean tryCaptureView(View var1, int var2) {
         boolean var3;
         if (this.this$0.isDrawerView(var1) && this.this$0.checkDrawerViewAbsoluteGravity(var1, this.mAbsGravity) && this.this$0.getDrawerLockMode(var1) == 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         return var3;
      }
   }
}
