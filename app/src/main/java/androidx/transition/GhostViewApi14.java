package androidx.transition;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;

class GhostViewApi14 extends View implements GhostViewImpl {
   Matrix mCurrentMatrix;
   private int mDeltaX;
   private int mDeltaY;
   private final Matrix mMatrix = new Matrix();
   private final ViewTreeObserver.OnPreDrawListener mOnPreDrawListener = new ViewTreeObserver.OnPreDrawListener(this) {
      final GhostViewApi14 this$0;

      {
         this.this$0 = var1;
      }

      public boolean onPreDraw() {
         GhostViewApi14 var1 = this.this$0;
         var1.mCurrentMatrix = var1.mView.getMatrix();
         ViewCompat.postInvalidateOnAnimation(this.this$0);
         if (this.this$0.mStartParent != null && this.this$0.mStartView != null) {
            this.this$0.mStartParent.endViewTransition(this.this$0.mStartView);
            ViewCompat.postInvalidateOnAnimation(this.this$0.mStartParent);
            this.this$0.mStartParent = null;
            this.this$0.mStartView = null;
         }

         return true;
      }
   };
   int mReferences;
   ViewGroup mStartParent;
   View mStartView;
   final View mView;

   GhostViewApi14(View var1) {
      super(var1.getContext());
      this.mView = var1;
      this.setLayerType(2, (Paint)null);
   }

   static GhostViewImpl addGhost(View var0, ViewGroup var1) {
      GhostViewApi14 var3 = getGhostView(var0);
      GhostViewApi14 var2 = var3;
      if (var3 == null) {
         FrameLayout var4 = findFrameLayout(var1);
         if (var4 == null) {
            return null;
         }

         var2 = new GhostViewApi14(var0);
         var4.addView(var2);
      }

      ++var2.mReferences;
      return var2;
   }

   private static FrameLayout findFrameLayout(ViewGroup var0) {
      while(!(var0 instanceof FrameLayout)) {
         ViewParent var1 = var0.getParent();
         if (!(var1 instanceof ViewGroup)) {
            return null;
         }

         var0 = (ViewGroup)var1;
      }

      return (FrameLayout)var0;
   }

   static GhostViewApi14 getGhostView(View var0) {
      return (GhostViewApi14)var0.getTag(R.id.ghost_view);
   }

   static void removeGhost(View var0) {
      GhostViewApi14 var3 = getGhostView(var0);
      if (var3 != null) {
         int var1 = var3.mReferences - 1;
         var3.mReferences = var1;
         if (var1 <= 0) {
            ViewParent var2 = var3.getParent();
            if (var2 instanceof ViewGroup) {
               ViewGroup var4 = (ViewGroup)var2;
               var4.endViewTransition(var3);
               var4.removeView(var3);
            }
         }
      }

   }

   private static void setGhostView(View var0, GhostViewApi14 var1) {
      var0.setTag(R.id.ghost_view, var1);
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      setGhostView(this.mView, this);
      int[] var2 = new int[2];
      int[] var3 = new int[2];
      this.getLocationOnScreen(var2);
      this.mView.getLocationOnScreen(var3);
      var3[0] = (int)((float)var3[0] - this.mView.getTranslationX());
      int var1 = (int)((float)var3[1] - this.mView.getTranslationY());
      var3[1] = var1;
      this.mDeltaX = var3[0] - var2[0];
      this.mDeltaY = var1 - var2[1];
      this.mView.getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
      this.mView.setVisibility(4);
   }

   protected void onDetachedFromWindow() {
      this.mView.getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
      this.mView.setVisibility(0);
      setGhostView(this.mView, (GhostViewApi14)null);
      super.onDetachedFromWindow();
   }

   protected void onDraw(Canvas var1) {
      this.mMatrix.set(this.mCurrentMatrix);
      this.mMatrix.postTranslate((float)this.mDeltaX, (float)this.mDeltaY);
      var1.setMatrix(this.mMatrix);
      this.mView.draw(var1);
   }

   public void reserveEndViewTransition(ViewGroup var1, View var2) {
      this.mStartParent = var1;
      this.mStartView = var2;
   }

   public void setVisibility(int var1) {
      super.setVisibility(var1);
      View var2 = this.mView;
      byte var3;
      if (var1 == 0) {
         var3 = 4;
      } else {
         var3 = 0;
      }

      var2.setVisibility(var3);
   }
}
