package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.R;

public class ScrimInsetsFrameLayout extends FrameLayout {
   Drawable insetForeground;
   Rect insets;
   private Rect tempRect;

   public ScrimInsetsFrameLayout(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public ScrimInsetsFrameLayout(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public ScrimInsetsFrameLayout(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.tempRect = new Rect();
      TypedArray var4 = ThemeEnforcement.obtainStyledAttributes(var1, var2, R.styleable.ScrimInsetsFrameLayout, var3, R.style.Widget_Design_ScrimInsetsFrameLayout);
      this.insetForeground = var4.getDrawable(R.styleable.ScrimInsetsFrameLayout_insetForeground);
      var4.recycle();
      this.setWillNotDraw(true);
      ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener(this) {
         final ScrimInsetsFrameLayout this$0;

         {
            this.this$0 = var1;
         }

         public WindowInsetsCompat onApplyWindowInsets(View var1, WindowInsetsCompat var2) {
            if (this.this$0.insets == null) {
               this.this$0.insets = new Rect();
            }

            this.this$0.insets.set(var2.getSystemWindowInsetLeft(), var2.getSystemWindowInsetTop(), var2.getSystemWindowInsetRight(), var2.getSystemWindowInsetBottom());
            this.this$0.onInsetsChanged(var2);
            ScrimInsetsFrameLayout var4 = this.this$0;
            boolean var3;
            if (var2.hasSystemWindowInsets() && this.this$0.insetForeground != null) {
               var3 = false;
            } else {
               var3 = true;
            }

            var4.setWillNotDraw(var3);
            ViewCompat.postInvalidateOnAnimation(this.this$0);
            return var2.consumeSystemWindowInsets();
         }
      });
   }

   public void draw(Canvas var1) {
      super.draw(var1);
      int var2 = this.getWidth();
      int var4 = this.getHeight();
      if (this.insets != null && this.insetForeground != null) {
         int var3 = var1.save();
         var1.translate((float)this.getScrollX(), (float)this.getScrollY());
         this.tempRect.set(0, 0, var2, this.insets.top);
         this.insetForeground.setBounds(this.tempRect);
         this.insetForeground.draw(var1);
         this.tempRect.set(0, var4 - this.insets.bottom, var2, var4);
         this.insetForeground.setBounds(this.tempRect);
         this.insetForeground.draw(var1);
         this.tempRect.set(0, this.insets.top, this.insets.left, var4 - this.insets.bottom);
         this.insetForeground.setBounds(this.tempRect);
         this.insetForeground.draw(var1);
         this.tempRect.set(var2 - this.insets.right, this.insets.top, var2, var4 - this.insets.bottom);
         this.insetForeground.setBounds(this.tempRect);
         this.insetForeground.draw(var1);
         var1.restoreToCount(var3);
      }

   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      Drawable var1 = this.insetForeground;
      if (var1 != null) {
         var1.setCallback(this);
      }

   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      Drawable var1 = this.insetForeground;
      if (var1 != null) {
         var1.setCallback((Drawable.Callback)null);
      }

   }

   protected void onInsetsChanged(WindowInsetsCompat var1) {
   }
}
