package androidx.appcompat.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import androidx.appcompat.view.menu.ShowableListMenu;

public abstract class ForwardingListener implements View.OnTouchListener, View.OnAttachStateChangeListener {
   private int mActivePointerId;
   private Runnable mDisallowIntercept;
   private boolean mForwarding;
   private final int mLongPressTimeout;
   private final float mScaledTouchSlop;
   final View mSrc;
   private final int mTapTimeout;
   private final int[] mTmpLocation = new int[2];
   private Runnable mTriggerLongPress;

   public ForwardingListener(View var1) {
      this.mSrc = var1;
      var1.setLongClickable(true);
      var1.addOnAttachStateChangeListener(this);
      this.mScaledTouchSlop = (float)ViewConfiguration.get(var1.getContext()).getScaledTouchSlop();
      int var2 = ViewConfiguration.getTapTimeout();
      this.mTapTimeout = var2;
      this.mLongPressTimeout = (var2 + ViewConfiguration.getLongPressTimeout()) / 2;
   }

   private void clearCallbacks() {
      Runnable var1 = this.mTriggerLongPress;
      if (var1 != null) {
         this.mSrc.removeCallbacks(var1);
      }

      var1 = this.mDisallowIntercept;
      if (var1 != null) {
         this.mSrc.removeCallbacks(var1);
      }

   }

   private boolean onTouchForwarded(MotionEvent var1) {
      View var6 = this.mSrc;
      ShowableListMenu var7 = this.getPopup();
      boolean var4 = false;
      boolean var3 = var4;
      if (var7 != null) {
         if (!var7.isShowing()) {
            var3 = var4;
         } else {
            DropDownListView var10 = (DropDownListView)var7.getListView();
            var3 = var4;
            if (var10 != null) {
               if (!var10.isShown()) {
                  var3 = var4;
               } else {
                  MotionEvent var8 = MotionEvent.obtainNoHistory(var1);
                  this.toGlobalMotionEvent(var6, var8);
                  this.toLocalMotionEvent(var10, var8);
                  boolean var5 = var10.onForwardedEvent(var8, this.mActivePointerId);
                  var8.recycle();
                  int var2 = var1.getActionMasked();
                  boolean var9;
                  if (var2 != 1 && var2 != 3) {
                     var9 = true;
                  } else {
                     var9 = false;
                  }

                  var3 = var4;
                  if (var5) {
                     var3 = var4;
                     if (var9) {
                        var3 = true;
                     }
                  }
               }
            }
         }
      }

      return var3;
   }

   private boolean onTouchObserved(MotionEvent var1) {
      View var3 = this.mSrc;
      if (!var3.isEnabled()) {
         return false;
      } else {
         int var2 = var1.getActionMasked();
         if (var2 != 0) {
            if (var2 != 1) {
               if (var2 == 2) {
                  var2 = var1.findPointerIndex(this.mActivePointerId);
                  if (var2 >= 0 && !pointInView(var3, var1.getX(var2), var1.getY(var2), this.mScaledTouchSlop)) {
                     this.clearCallbacks();
                     var3.getParent().requestDisallowInterceptTouchEvent(true);
                     return true;
                  }

                  return false;
               }

               if (var2 != 3) {
                  return false;
               }
            }

            this.clearCallbacks();
         } else {
            this.mActivePointerId = var1.getPointerId(0);
            if (this.mDisallowIntercept == null) {
               this.mDisallowIntercept = new DisallowIntercept(this);
            }

            var3.postDelayed(this.mDisallowIntercept, (long)this.mTapTimeout);
            if (this.mTriggerLongPress == null) {
               this.mTriggerLongPress = new TriggerLongPress(this);
            }

            var3.postDelayed(this.mTriggerLongPress, (long)this.mLongPressTimeout);
         }

         return false;
      }
   }

   private static boolean pointInView(View var0, float var1, float var2, float var3) {
      float var4 = -var3;
      boolean var5;
      if (var1 >= var4 && var2 >= var4 && var1 < (float)(var0.getRight() - var0.getLeft()) + var3 && var2 < (float)(var0.getBottom() - var0.getTop()) + var3) {
         var5 = true;
      } else {
         var5 = false;
      }

      return var5;
   }

   private boolean toGlobalMotionEvent(View var1, MotionEvent var2) {
      int[] var3 = this.mTmpLocation;
      var1.getLocationOnScreen(var3);
      var2.offsetLocation((float)var3[0], (float)var3[1]);
      return true;
   }

   private boolean toLocalMotionEvent(View var1, MotionEvent var2) {
      int[] var3 = this.mTmpLocation;
      var1.getLocationOnScreen(var3);
      var2.offsetLocation((float)(-var3[0]), (float)(-var3[1]));
      return true;
   }

   public abstract ShowableListMenu getPopup();

   protected boolean onForwardingStarted() {
      ShowableListMenu var1 = this.getPopup();
      if (var1 != null && !var1.isShowing()) {
         var1.show();
      }

      return true;
   }

   protected boolean onForwardingStopped() {
      ShowableListMenu var1 = this.getPopup();
      if (var1 != null && var1.isShowing()) {
         var1.dismiss();
      }

      return true;
   }

   void onLongPress() {
      this.clearCallbacks();
      View var3 = this.mSrc;
      if (var3.isEnabled() && !var3.isLongClickable()) {
         if (!this.onForwardingStarted()) {
            return;
         }

         var3.getParent().requestDisallowInterceptTouchEvent(true);
         long var1 = SystemClock.uptimeMillis();
         MotionEvent var4 = MotionEvent.obtain(var1, var1, 3, 0.0F, 0.0F, 0);
         var3.onTouchEvent(var4);
         var4.recycle();
         this.mForwarding = true;
      }

   }

   public boolean onTouch(View var1, MotionEvent var2) {
      boolean var6 = this.mForwarding;
      boolean var5 = true;
      boolean var3;
      boolean var4;
      if (var6) {
         if (!this.onTouchForwarded(var2) && this.onForwardingStopped()) {
            var3 = false;
         } else {
            var3 = true;
         }
      } else {
         if (this.onTouchObserved(var2) && this.onForwardingStarted()) {
            var4 = true;
         } else {
            var4 = false;
         }

         var3 = var4;
         if (var4) {
            long var7 = SystemClock.uptimeMillis();
            MotionEvent var9 = MotionEvent.obtain(var7, var7, 3, 0.0F, 0.0F, 0);
            this.mSrc.onTouchEvent(var9);
            var9.recycle();
            var3 = var4;
         }
      }

      this.mForwarding = var3;
      var4 = var5;
      if (!var3) {
         if (var6) {
            var4 = var5;
         } else {
            var4 = false;
         }
      }

      return var4;
   }

   public void onViewAttachedToWindow(View var1) {
   }

   public void onViewDetachedFromWindow(View var1) {
      this.mForwarding = false;
      this.mActivePointerId = -1;
      Runnable var2 = this.mDisallowIntercept;
      if (var2 != null) {
         this.mSrc.removeCallbacks(var2);
      }

   }

   private class DisallowIntercept implements Runnable {
      final ForwardingListener this$0;

      DisallowIntercept(ForwardingListener var1) {
         this.this$0 = var1;
      }

      public void run() {
         ViewParent var1 = this.this$0.mSrc.getParent();
         if (var1 != null) {
            var1.requestDisallowInterceptTouchEvent(true);
         }

      }
   }

   private class TriggerLongPress implements Runnable {
      final ForwardingListener this$0;

      TriggerLongPress(ForwardingListener var1) {
         this.this$0 = var1;
      }

      public void run() {
         this.this$0.onLongPress();
      }
   }
}
