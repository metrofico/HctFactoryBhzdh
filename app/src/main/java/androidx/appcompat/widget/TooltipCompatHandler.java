package androidx.appcompat.widget;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;

class TooltipCompatHandler implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
   private static final long HOVER_HIDE_TIMEOUT_MS = 15000L;
   private static final long HOVER_HIDE_TIMEOUT_SHORT_MS = 3000L;
   private static final long LONG_CLICK_HIDE_TIMEOUT_MS = 2500L;
   private static final String TAG = "TooltipCompatHandler";
   private static TooltipCompatHandler sActiveHandler;
   private static TooltipCompatHandler sPendingHandler;
   private final View mAnchor;
   private int mAnchorX;
   private int mAnchorY;
   private boolean mFromTouch;
   private final Runnable mHideRunnable = new Runnable(this) {
      final TooltipCompatHandler this$0;

      {
         this.this$0 = var1;
      }

      public void run() {
         this.this$0.hide();
      }
   };
   private final int mHoverSlop;
   private TooltipPopup mPopup;
   private final Runnable mShowRunnable = new Runnable(this) {
      final TooltipCompatHandler this$0;

      {
         this.this$0 = var1;
      }

      public void run() {
         this.this$0.show(false);
      }
   };
   private final CharSequence mTooltipText;

   private TooltipCompatHandler(View var1, CharSequence var2) {
      this.mAnchor = var1;
      this.mTooltipText = var2;
      this.mHoverSlop = ViewConfigurationCompat.getScaledHoverSlop(ViewConfiguration.get(var1.getContext()));
      this.clearAnchorPos();
      var1.setOnLongClickListener(this);
      var1.setOnHoverListener(this);
   }

   private void cancelPendingShow() {
      this.mAnchor.removeCallbacks(this.mShowRunnable);
   }

   private void clearAnchorPos() {
      this.mAnchorX = Integer.MAX_VALUE;
      this.mAnchorY = Integer.MAX_VALUE;
   }

   private void scheduleShow() {
      this.mAnchor.postDelayed(this.mShowRunnable, (long)ViewConfiguration.getLongPressTimeout());
   }

   private static void setPendingHandler(TooltipCompatHandler var0) {
      TooltipCompatHandler var1 = sPendingHandler;
      if (var1 != null) {
         var1.cancelPendingShow();
      }

      sPendingHandler = var0;
      if (var0 != null) {
         var0.scheduleShow();
      }

   }

   public static void setTooltipText(View var0, CharSequence var1) {
      TooltipCompatHandler var2 = sPendingHandler;
      if (var2 != null && var2.mAnchor == var0) {
         setPendingHandler((TooltipCompatHandler)null);
      }

      if (TextUtils.isEmpty(var1)) {
         TooltipCompatHandler var3 = sActiveHandler;
         if (var3 != null && var3.mAnchor == var0) {
            var3.hide();
         }

         var0.setOnLongClickListener((View.OnLongClickListener)null);
         var0.setLongClickable(false);
         var0.setOnHoverListener((View.OnHoverListener)null);
      } else {
         new TooltipCompatHandler(var0, var1);
      }

   }

   private boolean updateAnchorPos(MotionEvent var1) {
      int var2 = (int)var1.getX();
      int var3 = (int)var1.getY();
      if (Math.abs(var2 - this.mAnchorX) <= this.mHoverSlop && Math.abs(var3 - this.mAnchorY) <= this.mHoverSlop) {
         return false;
      } else {
         this.mAnchorX = var2;
         this.mAnchorY = var3;
         return true;
      }
   }

   void hide() {
      if (sActiveHandler == this) {
         sActiveHandler = null;
         TooltipPopup var1 = this.mPopup;
         if (var1 != null) {
            var1.hide();
            this.mPopup = null;
            this.clearAnchorPos();
            this.mAnchor.removeOnAttachStateChangeListener(this);
         } else {
            Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
         }
      }

      if (sPendingHandler == this) {
         setPendingHandler((TooltipCompatHandler)null);
      }

      this.mAnchor.removeCallbacks(this.mHideRunnable);
   }

   public boolean onHover(View var1, MotionEvent var2) {
      if (this.mPopup != null && this.mFromTouch) {
         return false;
      } else {
         AccessibilityManager var4 = (AccessibilityManager)this.mAnchor.getContext().getSystemService("accessibility");
         if (var4.isEnabled() && var4.isTouchExplorationEnabled()) {
            return false;
         } else {
            int var3 = var2.getAction();
            if (var3 != 7) {
               if (var3 == 10) {
                  this.clearAnchorPos();
                  this.hide();
               }
            } else if (this.mAnchor.isEnabled() && this.mPopup == null && this.updateAnchorPos(var2)) {
               setPendingHandler(this);
            }

            return false;
         }
      }
   }

   public boolean onLongClick(View var1) {
      this.mAnchorX = var1.getWidth() / 2;
      this.mAnchorY = var1.getHeight() / 2;
      this.show(true);
      return true;
   }

   public void onViewAttachedToWindow(View var1) {
   }

   public void onViewDetachedFromWindow(View var1) {
      this.hide();
   }

   void show(boolean var1) {
      if (ViewCompat.isAttachedToWindow(this.mAnchor)) {
         setPendingHandler((TooltipCompatHandler)null);
         TooltipCompatHandler var5 = sActiveHandler;
         if (var5 != null) {
            var5.hide();
         }

         sActiveHandler = this;
         this.mFromTouch = var1;
         TooltipPopup var6 = new TooltipPopup(this.mAnchor.getContext());
         this.mPopup = var6;
         var6.show(this.mAnchor, this.mAnchorX, this.mAnchorY, this.mFromTouch, this.mTooltipText);
         this.mAnchor.addOnAttachStateChangeListener(this);
         long var3;
         if (this.mFromTouch) {
            var3 = 2500L;
         } else {
            int var2;
            if ((ViewCompat.getWindowSystemUiVisibility(this.mAnchor) & 1) == 1) {
               var3 = 3000L;
               var2 = ViewConfiguration.getLongPressTimeout();
            } else {
               var3 = 15000L;
               var2 = ViewConfiguration.getLongPressTimeout();
            }

            var3 -= (long)var2;
         }

         this.mAnchor.removeCallbacks(this.mHideRunnable);
         this.mAnchor.postDelayed(this.mHideRunnable, var3);
      }
   }
}
