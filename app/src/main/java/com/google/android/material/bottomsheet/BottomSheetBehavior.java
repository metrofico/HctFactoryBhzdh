package com.google.android.material.bottomsheet;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class BottomSheetBehavior extends CoordinatorLayout.Behavior {
   private static final float HIDE_FRICTION = 0.1F;
   private static final float HIDE_THRESHOLD = 0.5F;
   public static final int PEEK_HEIGHT_AUTO = -1;
   public static final int STATE_COLLAPSED = 4;
   public static final int STATE_DRAGGING = 1;
   public static final int STATE_EXPANDED = 3;
   public static final int STATE_HALF_EXPANDED = 6;
   public static final int STATE_HIDDEN = 5;
   public static final int STATE_SETTLING = 2;
   int activePointerId;
   private BottomSheetCallback callback;
   int collapsedOffset;
   private final ViewDragHelper.Callback dragCallback = new ViewDragHelper.Callback(this) {
      final BottomSheetBehavior this$0;

      {
         this.this$0 = var1;
      }

      public int clampViewPositionHorizontal(View var1, int var2, int var3) {
         return var1.getLeft();
      }

      public int clampViewPositionVertical(View var1, int var2, int var3) {
         int var4 = this.this$0.getExpandedOffset();
         if (this.this$0.hideable) {
            var3 = this.this$0.parentHeight;
         } else {
            var3 = this.this$0.collapsedOffset;
         }

         return MathUtils.clamp(var2, var4, var3);
      }

      public int getViewVerticalDragRange(View var1) {
         return this.this$0.hideable ? this.this$0.parentHeight : this.this$0.collapsedOffset;
      }

      public void onViewDragStateChanged(int var1) {
         if (var1 == 1) {
            this.this$0.setStateInternal(1);
         }

      }

      public void onViewPositionChanged(View var1, int var2, int var3, int var4, int var5) {
         this.this$0.dispatchOnSlide(var3);
      }

      public void onViewReleased(View var1, float var2, float var3) {
         int var5;
         byte var7;
         label71: {
            int var4;
            int var6;
            label64: {
               label63: {
                  var4 = 0;
                  var5 = 4;
                  if (var3 < 0.0F) {
                     if (this.this$0.fitToContents) {
                        var5 = this.this$0.fitToContentsOffset;
                        var7 = 3;
                        break label71;
                     }

                     if (var1.getTop() > this.this$0.halfExpandedOffset) {
                        var4 = this.this$0.halfExpandedOffset;
                        break label63;
                     }
                  } else {
                     if (this.this$0.hideable && this.this$0.shouldHide(var1, var3) && (var1.getTop() > this.this$0.collapsedOffset || Math.abs(var2) < Math.abs(var3))) {
                        var5 = this.this$0.parentHeight;
                        var7 = 5;
                        break label71;
                     }

                     if (var3 != 0.0F && !(Math.abs(var2) > Math.abs(var3))) {
                        var6 = this.this$0.collapsedOffset;
                        var7 = (byte)var5;
                        var5 = var6;
                        break label71;
                     }

                     var6 = var1.getTop();
                     if (this.this$0.fitToContents) {
                        if (Math.abs(var6 - this.this$0.fitToContentsOffset) >= Math.abs(var6 - this.this$0.collapsedOffset)) {
                           var4 = this.this$0.collapsedOffset;
                           break label64;
                        }

                        var4 = this.this$0.fitToContentsOffset;
                     } else {
                        if (var6 >= this.this$0.halfExpandedOffset) {
                           if (Math.abs(var6 - this.this$0.halfExpandedOffset) >= Math.abs(var6 - this.this$0.collapsedOffset)) {
                              var4 = this.this$0.collapsedOffset;
                              break label64;
                           }

                           var4 = this.this$0.halfExpandedOffset;
                           break label63;
                        }

                        if (var6 >= Math.abs(var6 - this.this$0.collapsedOffset)) {
                           var4 = this.this$0.halfExpandedOffset;
                           break label63;
                        }
                     }
                  }

                  var5 = 3;
                  break label64;
               }

               var5 = 6;
            }

            var6 = var4;
            var7 = (byte)var5;
            var5 = var6;
         }

         if (this.this$0.viewDragHelper.settleCapturedViewAt(var1.getLeft(), var5)) {
            this.this$0.setStateInternal(2);
            ViewCompat.postOnAnimation(var1, this.this$0.new SettleRunnable(this.this$0, var1, var7));
         } else {
            this.this$0.setStateInternal(var7);
         }

      }

      public boolean tryCaptureView(View var1, int var2) {
         int var3 = this.this$0.state;
         boolean var4 = true;
         if (var3 == 1) {
            return false;
         } else if (this.this$0.touchingScrollingChild) {
            return false;
         } else {
            if (this.this$0.state == 3 && this.this$0.activePointerId == var2) {
               View var5 = (View)this.this$0.nestedScrollingChildRef.get();
               if (var5 != null && var5.canScrollVertically(-1)) {
                  return false;
               }
            }

            if (this.this$0.viewRef == null || this.this$0.viewRef.get() != var1) {
               var4 = false;
            }

            return var4;
         }
      }
   };
   private boolean fitToContents = true;
   int fitToContentsOffset;
   int halfExpandedOffset;
   boolean hideable;
   private boolean ignoreEvents;
   private Map importantForAccessibilityMap;
   private int initialY;
   private int lastNestedScrollDy;
   private int lastPeekHeight;
   private float maximumVelocity;
   private boolean nestedScrolled;
   WeakReference nestedScrollingChildRef;
   int parentHeight;
   private int peekHeight;
   private boolean peekHeightAuto;
   private int peekHeightMin;
   private boolean skipCollapsed;
   int state = 4;
   boolean touchingScrollingChild;
   private VelocityTracker velocityTracker;
   ViewDragHelper viewDragHelper;
   WeakReference viewRef;

   public BottomSheetBehavior() {
   }

   public BottomSheetBehavior(Context var1, AttributeSet var2) {
      super(var1, var2);
      TypedArray var4 = var1.obtainStyledAttributes(var2, R.styleable.BottomSheetBehavior_Layout);
      TypedValue var3 = var4.peekValue(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
      if (var3 != null && var3.data == -1) {
         this.setPeekHeight(var3.data);
      } else {
         this.setPeekHeight(var4.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
      }

      this.setHideable(var4.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
      this.setFitToContents(var4.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true));
      this.setSkipCollapsed(var4.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
      var4.recycle();
      this.maximumVelocity = (float)ViewConfiguration.get(var1).getScaledMaximumFlingVelocity();
   }

   private void calculateCollapsedOffset() {
      if (this.fitToContents) {
         this.collapsedOffset = Math.max(this.parentHeight - this.lastPeekHeight, this.fitToContentsOffset);
      } else {
         this.collapsedOffset = this.parentHeight - this.lastPeekHeight;
      }

   }

   public static BottomSheetBehavior from(View var0) {
      ViewGroup.LayoutParams var1 = var0.getLayoutParams();
      if (var1 instanceof CoordinatorLayout.LayoutParams) {
         CoordinatorLayout.Behavior var2 = ((CoordinatorLayout.LayoutParams)var1).getBehavior();
         if (var2 instanceof BottomSheetBehavior) {
            return (BottomSheetBehavior)var2;
         } else {
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
         }
      } else {
         throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
      }
   }

   private int getExpandedOffset() {
      int var1;
      if (this.fitToContents) {
         var1 = this.fitToContentsOffset;
      } else {
         var1 = 0;
      }

      return var1;
   }

   private float getYVelocity() {
      VelocityTracker var1 = this.velocityTracker;
      if (var1 == null) {
         return 0.0F;
      } else {
         var1.computeCurrentVelocity(1000, this.maximumVelocity);
         return this.velocityTracker.getYVelocity(this.activePointerId);
      }
   }

   private void reset() {
      this.activePointerId = -1;
      VelocityTracker var1 = this.velocityTracker;
      if (var1 != null) {
         var1.recycle();
         this.velocityTracker = null;
      }

   }

   private void updateImportantForAccessibility(boolean var1) {
      WeakReference var4 = this.viewRef;
      if (var4 != null) {
         ViewParent var7 = ((View)var4.get()).getParent();
         if (var7 instanceof CoordinatorLayout) {
            CoordinatorLayout var5 = (CoordinatorLayout)var7;
            int var3 = var5.getChildCount();
            if (VERSION.SDK_INT >= 16 && var1) {
               if (this.importantForAccessibilityMap != null) {
                  return;
               }

               this.importantForAccessibilityMap = new HashMap(var3);
            }

            for(int var2 = 0; var2 < var3; ++var2) {
               View var8 = var5.getChildAt(var2);
               if (var8 != this.viewRef.get()) {
                  if (!var1) {
                     Map var6 = this.importantForAccessibilityMap;
                     if (var6 != null && var6.containsKey(var8)) {
                        ViewCompat.setImportantForAccessibility(var8, (Integer)this.importantForAccessibilityMap.get(var8));
                     }
                  } else {
                     if (VERSION.SDK_INT >= 16) {
                        this.importantForAccessibilityMap.put(var8, var8.getImportantForAccessibility());
                     }

                     ViewCompat.setImportantForAccessibility(var8, 4);
                  }
               }
            }

            if (!var1) {
               this.importantForAccessibilityMap = null;
            }

         }
      }
   }

   void dispatchOnSlide(int var1) {
      View var4 = (View)this.viewRef.get();
      if (var4 != null) {
         BottomSheetCallback var3 = this.callback;
         if (var3 != null) {
            int var2 = this.collapsedOffset;
            if (var1 > var2) {
               var3.onSlide(var4, (float)(var2 - var1) / (float)(this.parentHeight - var2));
            } else {
               var3.onSlide(var4, (float)(var2 - var1) / (float)(var2 - this.getExpandedOffset()));
            }
         }
      }

   }

   View findScrollingChild(View var1) {
      if (ViewCompat.isNestedScrollingEnabled(var1)) {
         return var1;
      } else {
         if (var1 instanceof ViewGroup) {
            ViewGroup var5 = (ViewGroup)var1;
            int var2 = 0;

            for(int var3 = var5.getChildCount(); var2 < var3; ++var2) {
               View var4 = this.findScrollingChild(var5.getChildAt(var2));
               if (var4 != null) {
                  return var4;
               }
            }
         }

         return null;
      }
   }

   public final int getPeekHeight() {
      int var1;
      if (this.peekHeightAuto) {
         var1 = -1;
      } else {
         var1 = this.peekHeight;
      }

      return var1;
   }

   int getPeekHeightMin() {
      return this.peekHeightMin;
   }

   public boolean getSkipCollapsed() {
      return this.skipCollapsed;
   }

   public final int getState() {
      return this.state;
   }

   public boolean isFitToContents() {
      return this.fitToContents;
   }

   public boolean isHideable() {
      return this.hideable;
   }

   public boolean onInterceptTouchEvent(CoordinatorLayout var1, View var2, MotionEvent var3) {
      boolean var6 = var2.isShown();
      boolean var7 = false;
      if (!var6) {
         this.ignoreEvents = true;
         return false;
      } else {
         int var4 = var3.getActionMasked();
         if (var4 == 0) {
            this.reset();
         }

         if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
         }

         this.velocityTracker.addMovement(var3);
         Object var9 = null;
         WeakReference var8;
         if (var4 != 0) {
            if (var4 == 1 || var4 == 3) {
               this.touchingScrollingChild = false;
               this.activePointerId = -1;
               if (this.ignoreEvents) {
                  this.ignoreEvents = false;
                  return false;
               }
            }
         } else {
            int var5 = (int)var3.getX();
            this.initialY = (int)var3.getY();
            var8 = this.nestedScrollingChildRef;
            View var11;
            if (var8 != null) {
               var11 = (View)var8.get();
            } else {
               var11 = null;
            }

            if (var11 != null && var1.isPointInChildBounds(var11, var5, this.initialY)) {
               this.activePointerId = var3.getPointerId(var3.getActionIndex());
               this.touchingScrollingChild = true;
            }

            if (this.activePointerId == -1 && !var1.isPointInChildBounds(var2, var5, this.initialY)) {
               var6 = true;
            } else {
               var6 = false;
            }

            this.ignoreEvents = var6;
         }

         if (!this.ignoreEvents) {
            ViewDragHelper var10 = this.viewDragHelper;
            if (var10 != null && var10.shouldInterceptTouchEvent(var3)) {
               return true;
            }
         }

         var8 = this.nestedScrollingChildRef;
         var2 = (View)var9;
         if (var8 != null) {
            var2 = (View)var8.get();
         }

         var6 = var7;
         if (var4 == 2) {
            var6 = var7;
            if (var2 != null) {
               var6 = var7;
               if (!this.ignoreEvents) {
                  var6 = var7;
                  if (this.state != 1) {
                     var6 = var7;
                     if (!var1.isPointInChildBounds(var2, (int)var3.getX(), (int)var3.getY())) {
                        var6 = var7;
                        if (this.viewDragHelper != null) {
                           var6 = var7;
                           if (Math.abs((float)this.initialY - var3.getY()) > (float)this.viewDragHelper.getTouchSlop()) {
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
   }

   public boolean onLayoutChild(CoordinatorLayout var1, View var2, int var3) {
      if (ViewCompat.getFitsSystemWindows(var1) && !ViewCompat.getFitsSystemWindows(var2)) {
         var2.setFitsSystemWindows(true);
      }

      int var4 = var2.getTop();
      var1.onLayoutChild(var2, var3);
      this.parentHeight = var1.getHeight();
      if (this.peekHeightAuto) {
         if (this.peekHeightMin == 0) {
            this.peekHeightMin = var1.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
         }

         this.lastPeekHeight = Math.max(this.peekHeightMin, this.parentHeight - var1.getWidth() * 9 / 16);
      } else {
         this.lastPeekHeight = this.peekHeight;
      }

      this.fitToContentsOffset = Math.max(0, this.parentHeight - var2.getHeight());
      this.halfExpandedOffset = this.parentHeight / 2;
      this.calculateCollapsedOffset();
      var3 = this.state;
      if (var3 == 3) {
         ViewCompat.offsetTopAndBottom(var2, this.getExpandedOffset());
      } else if (var3 == 6) {
         ViewCompat.offsetTopAndBottom(var2, this.halfExpandedOffset);
      } else if (this.hideable && var3 == 5) {
         ViewCompat.offsetTopAndBottom(var2, this.parentHeight);
      } else if (var3 == 4) {
         ViewCompat.offsetTopAndBottom(var2, this.collapsedOffset);
      } else if (var3 == 1 || var3 == 2) {
         ViewCompat.offsetTopAndBottom(var2, var4 - var2.getTop());
      }

      if (this.viewDragHelper == null) {
         this.viewDragHelper = ViewDragHelper.create(var1, this.dragCallback);
      }

      this.viewRef = new WeakReference(var2);
      this.nestedScrollingChildRef = new WeakReference(this.findScrollingChild(var2));
      return true;
   }

   public boolean onNestedPreFling(CoordinatorLayout var1, View var2, View var3, float var4, float var5) {
      boolean var6;
      if (var3 != this.nestedScrollingChildRef.get() || this.state == 3 && !super.onNestedPreFling(var1, var2, var3, var4, var5)) {
         var6 = false;
      } else {
         var6 = true;
      }

      return var6;
   }

   public void onNestedPreScroll(CoordinatorLayout var1, View var2, View var3, int var4, int var5, int[] var6, int var7) {
      if (var7 != 1) {
         if (var3 == (View)this.nestedScrollingChildRef.get()) {
            var4 = var2.getTop();
            int var8 = var4 - var5;
            if (var5 > 0) {
               if (var8 < this.getExpandedOffset()) {
                  var4 -= this.getExpandedOffset();
                  var6[1] = var4;
                  ViewCompat.offsetTopAndBottom(var2, -var4);
                  this.setStateInternal(3);
               } else {
                  var6[1] = var5;
                  ViewCompat.offsetTopAndBottom(var2, -var5);
                  this.setStateInternal(1);
               }
            } else if (var5 < 0 && !var3.canScrollVertically(-1)) {
               var7 = this.collapsedOffset;
               if (var8 > var7 && !this.hideable) {
                  var4 -= var7;
                  var6[1] = var4;
                  ViewCompat.offsetTopAndBottom(var2, -var4);
                  this.setStateInternal(4);
               } else {
                  var6[1] = var5;
                  ViewCompat.offsetTopAndBottom(var2, -var5);
                  this.setStateInternal(1);
               }
            }

            this.dispatchOnSlide(var2.getTop());
            this.lastNestedScrollDy = var5;
            this.nestedScrolled = true;
         }
      }
   }

   public void onRestoreInstanceState(CoordinatorLayout var1, View var2, Parcelable var3) {
      SavedState var4 = (SavedState)var3;
      super.onRestoreInstanceState(var1, var2, var4.getSuperState());
      if (var4.state != 1 && var4.state != 2) {
         this.state = var4.state;
      } else {
         this.state = 4;
      }

   }

   public Parcelable onSaveInstanceState(CoordinatorLayout var1, View var2) {
      return new SavedState(super.onSaveInstanceState(var1, var2), this.state);
   }

   public boolean onStartNestedScroll(CoordinatorLayout var1, View var2, View var3, View var4, int var5, int var6) {
      boolean var7 = false;
      this.lastNestedScrollDy = 0;
      this.nestedScrolled = false;
      if ((var5 & 2) != 0) {
         var7 = true;
      }

      return var7;
   }

   public void onStopNestedScroll(CoordinatorLayout var1, View var2, View var3, int var4) {
      var4 = var2.getTop();
      int var6 = this.getExpandedOffset();
      byte var5 = 3;
      if (var4 == var6) {
         this.setStateInternal(3);
      } else {
         if (var3 == this.nestedScrollingChildRef.get() && this.nestedScrolled) {
            if (this.lastNestedScrollDy > 0) {
               var4 = this.getExpandedOffset();
            } else if (this.hideable && this.shouldHide(var2, this.getYVelocity())) {
               var4 = this.parentHeight;
               var5 = 5;
            } else {
               label69: {
                  label52: {
                     if (this.lastNestedScrollDy == 0) {
                        var6 = var2.getTop();
                        if (this.fitToContents) {
                           if (Math.abs(var6 - this.fitToContentsOffset) < Math.abs(var6 - this.collapsedOffset)) {
                              var4 = this.fitToContentsOffset;
                              break label69;
                           }

                           var4 = this.collapsedOffset;
                        } else {
                           var4 = this.halfExpandedOffset;
                           if (var6 < var4) {
                              if (var6 < Math.abs(var6 - this.collapsedOffset)) {
                                 var4 = 0;
                                 break label69;
                              }

                              var4 = this.halfExpandedOffset;
                              break label52;
                           }

                           if (Math.abs(var6 - var4) < Math.abs(var6 - this.collapsedOffset)) {
                              var4 = this.halfExpandedOffset;
                              break label52;
                           }

                           var4 = this.collapsedOffset;
                        }
                     } else {
                        var4 = this.collapsedOffset;
                     }

                     var5 = 4;
                     break label69;
                  }

                  var5 = 6;
               }
            }

            if (this.viewDragHelper.smoothSlideViewTo(var2, var2.getLeft(), var4)) {
               this.setStateInternal(2);
               ViewCompat.postOnAnimation(var2, new SettleRunnable(this, var2, var5));
            } else {
               this.setStateInternal(var5);
            }

            this.nestedScrolled = false;
         }

      }
   }

   public boolean onTouchEvent(CoordinatorLayout var1, View var2, MotionEvent var3) {
      if (!var2.isShown()) {
         return false;
      } else {
         int var4 = var3.getActionMasked();
         if (this.state == 1 && var4 == 0) {
            return true;
         } else {
            ViewDragHelper var5 = this.viewDragHelper;
            if (var5 != null) {
               var5.processTouchEvent(var3);
            }

            if (var4 == 0) {
               this.reset();
            }

            if (this.velocityTracker == null) {
               this.velocityTracker = VelocityTracker.obtain();
            }

            this.velocityTracker.addMovement(var3);
            if (var4 == 2 && !this.ignoreEvents && Math.abs((float)this.initialY - var3.getY()) > (float)this.viewDragHelper.getTouchSlop()) {
               this.viewDragHelper.captureChildView(var2, var3.getPointerId(var3.getActionIndex()));
            }

            return this.ignoreEvents ^ true;
         }
      }
   }

   public void setBottomSheetCallback(BottomSheetCallback var1) {
      this.callback = var1;
   }

   public void setFitToContents(boolean var1) {
      if (this.fitToContents != var1) {
         this.fitToContents = var1;
         if (this.viewRef != null) {
            this.calculateCollapsedOffset();
         }

         int var2;
         if (this.fitToContents && this.state == 6) {
            var2 = 3;
         } else {
            var2 = this.state;
         }

         this.setStateInternal(var2);
      }
   }

   public void setHideable(boolean var1) {
      this.hideable = var1;
   }

   public final void setPeekHeight(int var1) {
      boolean var4;
      label36: {
         boolean var2 = true;
         if (var1 == -1) {
            if (!this.peekHeightAuto) {
               this.peekHeightAuto = true;
               var4 = var2;
               break label36;
            }
         } else if (this.peekHeightAuto || this.peekHeight != var1) {
            this.peekHeightAuto = false;
            this.peekHeight = Math.max(0, var1);
            this.collapsedOffset = this.parentHeight - var1;
            var4 = var2;
            break label36;
         }

         var4 = false;
      }

      if (var4 && this.state == 4) {
         WeakReference var3 = this.viewRef;
         if (var3 != null) {
            View var5 = (View)var3.get();
            if (var5 != null) {
               var5.requestLayout();
            }
         }
      }

   }

   public void setSkipCollapsed(boolean var1) {
      this.skipCollapsed = var1;
   }

   public final void setState(int var1) {
      if (var1 != this.state) {
         WeakReference var2 = this.viewRef;
         if (var2 == null) {
            if (var1 == 4 || var1 == 3 || var1 == 6 || this.hideable && var1 == 5) {
               this.state = var1;
            }

         } else {
            View var3 = (View)var2.get();
            if (var3 != null) {
               ViewParent var4 = var3.getParent();
               if (var4 != null && var4.isLayoutRequested() && ViewCompat.isAttachedToWindow(var3)) {
                  var3.post(new Runnable(this, var3, var1) {
                     final BottomSheetBehavior this$0;
                     final View val$child;
                     final int val$finalState;

                     {
                        this.this$0 = var1;
                        this.val$child = var2;
                        this.val$finalState = var3;
                     }

                     public void run() {
                        this.this$0.startSettlingAnimation(this.val$child, this.val$finalState);
                     }
                  });
               } else {
                  this.startSettlingAnimation(var3, var1);
               }

            }
         }
      }
   }

   void setStateInternal(int var1) {
      if (this.state != var1) {
         this.state = var1;
         if (var1 != 6 && var1 != 3) {
            if (var1 == 5 || var1 == 4) {
               this.updateImportantForAccessibility(false);
            }
         } else {
            this.updateImportantForAccessibility(true);
         }

         View var3 = (View)this.viewRef.get();
         if (var3 != null) {
            BottomSheetCallback var2 = this.callback;
            if (var2 != null) {
               var2.onStateChanged(var3, var1);
            }
         }

      }
   }

   boolean shouldHide(View var1, float var2) {
      boolean var4 = this.skipCollapsed;
      boolean var3 = true;
      if (var4) {
         return true;
      } else if (var1.getTop() < this.collapsedOffset) {
         return false;
      } else {
         if (!(Math.abs((float)var1.getTop() + var2 * 0.1F - (float)this.collapsedOffset) / (float)this.peekHeight > 0.5F)) {
            var3 = false;
         }

         return var3;
      }
   }

   void startSettlingAnimation(View var1, int var2) {
      int var3;
      if (var2 == 4) {
         var3 = this.collapsedOffset;
      } else if (var2 == 6) {
         label26: {
            int var4 = this.halfExpandedOffset;
            if (this.fitToContents) {
               var3 = this.fitToContentsOffset;
               if (var4 <= var3) {
                  var2 = 3;
                  break label26;
               }
            }

            var3 = var4;
         }
      } else if (var2 == 3) {
         var3 = this.getExpandedOffset();
      } else {
         if (!this.hideable || var2 != 5) {
            throw new IllegalArgumentException("Illegal state argument: " + var2);
         }

         var3 = this.parentHeight;
      }

      if (this.viewDragHelper.smoothSlideViewTo(var1, var1.getLeft(), var3)) {
         this.setStateInternal(2);
         ViewCompat.postOnAnimation(var1, new SettleRunnable(this, var1, var2));
      } else {
         this.setStateInternal(var2);
      }

   }

   public abstract static class BottomSheetCallback {
      public abstract void onSlide(View var1, float var2);

      public abstract void onStateChanged(View var1, int var2);
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
      final int state;

      public SavedState(Parcel var1) {
         this(var1, (ClassLoader)null);
      }

      public SavedState(Parcel var1, ClassLoader var2) {
         super(var1, var2);
         this.state = var1.readInt();
      }

      public SavedState(Parcelable var1, int var2) {
         super(var1);
         this.state = var2;
      }

      public void writeToParcel(Parcel var1, int var2) {
         super.writeToParcel(var1, var2);
         var1.writeInt(this.state);
      }
   }

   private class SettleRunnable implements Runnable {
      private final int targetState;
      final BottomSheetBehavior this$0;
      private final View view;

      SettleRunnable(BottomSheetBehavior var1, View var2, int var3) {
         this.this$0 = var1;
         this.view = var2;
         this.targetState = var3;
      }

      public void run() {
         if (this.this$0.viewDragHelper != null && this.this$0.viewDragHelper.continueSettling(true)) {
            ViewCompat.postOnAnimation(this.view, this);
         } else {
            this.this$0.setStateInternal(this.targetState);
         }

      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface State {
   }
}
