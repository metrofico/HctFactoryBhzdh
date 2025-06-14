package androidx.transition;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

class ViewOverlayApi14 implements ViewOverlayImpl {
   protected OverlayViewGroup mOverlayViewGroup;

   private ViewOverlayApi14() {
   }

   ViewOverlayApi14(Context var1, ViewGroup var2, View var3) {
      this.mOverlayViewGroup = new OverlayViewGroup(var1, var2, var3, this);
   }

   static ViewOverlayApi14 createFrom(View var0) {
      ViewGroup var3 = getContentView(var0);
      if (var3 != null) {
         int var2 = var3.getChildCount();

         for(int var1 = 0; var1 < var2; ++var1) {
            View var4 = var3.getChildAt(var1);
            if (var4 instanceof OverlayViewGroup) {
               return ((OverlayViewGroup)var4).mViewOverlay;
            }
         }

         return new ViewGroupOverlayApi14(var3.getContext(), var3, var0);
      } else {
         return null;
      }
   }

   static ViewGroup getContentView(View var0) {
      while(var0 != null) {
         if (((View)var0).getId() == 16908290 && var0 instanceof ViewGroup) {
            return (ViewGroup)var0;
         }

         if (((View)var0).getParent() instanceof ViewGroup) {
            var0 = (ViewGroup)((View)var0).getParent();
         }
      }

      return null;
   }

   public void add(Drawable var1) {
      this.mOverlayViewGroup.add(var1);
   }

   public void clear() {
      this.mOverlayViewGroup.clear();
   }

   ViewGroup getOverlayView() {
      return this.mOverlayViewGroup;
   }

   boolean isEmpty() {
      return this.mOverlayViewGroup.isEmpty();
   }

   public void remove(Drawable var1) {
      this.mOverlayViewGroup.remove(var1);
   }

   static class OverlayViewGroup extends ViewGroup {
      static Method sInvalidateChildInParentFastMethod;
      ArrayList mDrawables = null;
      ViewGroup mHostView;
      View mRequestingView;
      ViewOverlayApi14 mViewOverlay;

      static {
         try {
            sInvalidateChildInParentFastMethod = ViewGroup.class.getDeclaredMethod("invalidateChildInParentFast", Integer.TYPE, Integer.TYPE, Rect.class);
         } catch (NoSuchMethodException var1) {
         }

      }

      OverlayViewGroup(Context var1, ViewGroup var2, View var3, ViewOverlayApi14 var4) {
         super(var1);
         this.mHostView = var2;
         this.mRequestingView = var3;
         this.setRight(var2.getWidth());
         this.setBottom(var2.getHeight());
         var2.addView(this);
         this.mViewOverlay = var4;
      }

      private void getOffset(int[] var1) {
         int[] var3 = new int[2];
         int[] var2 = new int[2];
         this.mHostView.getLocationOnScreen(var3);
         this.mRequestingView.getLocationOnScreen(var2);
         var1[0] = var2[0] - var3[0];
         var1[1] = var2[1] - var3[1];
      }

      public void add(Drawable var1) {
         if (this.mDrawables == null) {
            this.mDrawables = new ArrayList();
         }

         if (!this.mDrawables.contains(var1)) {
            this.mDrawables.add(var1);
            this.invalidate(var1.getBounds());
            var1.setCallback(this);
         }

      }

      public void add(View var1) {
         if (var1.getParent() instanceof ViewGroup) {
            ViewGroup var2 = (ViewGroup)var1.getParent();
            if (var2 != this.mHostView && var2.getParent() != null && ViewCompat.isAttachedToWindow(var2)) {
               int[] var3 = new int[2];
               int[] var4 = new int[2];
               var2.getLocationOnScreen(var3);
               this.mHostView.getLocationOnScreen(var4);
               ViewCompat.offsetLeftAndRight(var1, var3[0] - var4[0]);
               ViewCompat.offsetTopAndBottom(var1, var3[1] - var4[1]);
            }

            var2.removeView(var1);
            if (var1.getParent() != null) {
               var2.removeView(var1);
            }
         }

         super.addView(var1, this.getChildCount() - 1);
      }

      public void clear() {
         this.removeAllViews();
         ArrayList var1 = this.mDrawables;
         if (var1 != null) {
            var1.clear();
         }

      }

      protected void dispatchDraw(Canvas var1) {
         int[] var5 = new int[2];
         int[] var4 = new int[2];
         this.mHostView.getLocationOnScreen(var5);
         this.mRequestingView.getLocationOnScreen(var4);
         int var3 = 0;
         var1.translate((float)(var4[0] - var5[0]), (float)(var4[1] - var5[1]));
         var1.clipRect(new Rect(0, 0, this.mRequestingView.getWidth(), this.mRequestingView.getHeight()));
         super.dispatchDraw(var1);
         ArrayList var6 = this.mDrawables;
         int var2;
         if (var6 == null) {
            var2 = 0;
         } else {
            var2 = var6.size();
         }

         while(var3 < var2) {
            ((Drawable)this.mDrawables.get(var3)).draw(var1);
            ++var3;
         }

      }

      public boolean dispatchTouchEvent(MotionEvent var1) {
         return false;
      }

      public void invalidateChildFast(View var1, Rect var2) {
         if (this.mHostView != null) {
            int var3 = var1.getLeft();
            int var4 = var1.getTop();
            int[] var5 = new int[2];
            this.getOffset(var5);
            var2.offset(var3 + var5[0], var4 + var5[1]);
            this.mHostView.invalidate(var2);
         }

      }

      public ViewParent invalidateChildInParent(int[] var1, Rect var2) {
         if (this.mHostView != null) {
            var2.offset(var1[0], var1[1]);
            if (this.mHostView instanceof ViewGroup) {
               var1[0] = 0;
               var1[1] = 0;
               int[] var3 = new int[2];
               this.getOffset(var3);
               var2.offset(var3[0], var3[1]);
               return super.invalidateChildInParent(var1, var2);
            }

            this.invalidate(var2);
         }

         return null;
      }

      protected ViewParent invalidateChildInParentFast(int var1, int var2, Rect var3) {
         if (this.mHostView instanceof ViewGroup && sInvalidateChildInParentFastMethod != null) {
            try {
               this.getOffset(new int[2]);
               sInvalidateChildInParentFastMethod.invoke(this.mHostView, var1, var2, var3);
            } catch (IllegalAccessException var4) {
               var4.printStackTrace();
            } catch (InvocationTargetException var5) {
               var5.printStackTrace();
            }
         }

         return null;
      }

      public void invalidateDrawable(Drawable var1) {
         this.invalidate(var1.getBounds());
      }

      boolean isEmpty() {
         boolean var1;
         if (this.getChildCount() == 0) {
            ArrayList var2 = this.mDrawables;
            if (var2 == null || var2.size() == 0) {
               var1 = true;
               return var1;
            }
         }

         var1 = false;
         return var1;
      }

      protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      }

      public void remove(Drawable var1) {
         ArrayList var2 = this.mDrawables;
         if (var2 != null) {
            var2.remove(var1);
            this.invalidate(var1.getBounds());
            var1.setCallback((Drawable.Callback)null);
         }

      }

      public void remove(View var1) {
         super.removeView(var1);
         if (this.isEmpty()) {
            this.mHostView.removeView(this);
         }

      }

      protected boolean verifyDrawable(Drawable var1) {
         boolean var2;
         if (!super.verifyDrawable(var1)) {
            ArrayList var3 = this.mDrawables;
            if (var3 == null || !var3.contains(var1)) {
               var2 = false;
               return var2;
            }
         }

         var2 = true;
         return var2;
      }

      static class TouchInterceptor extends View {
         TouchInterceptor(Context var1) {
            super(var1);
         }
      }
   }
}
