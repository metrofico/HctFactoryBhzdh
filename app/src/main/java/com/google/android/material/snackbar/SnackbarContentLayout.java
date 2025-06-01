package com.google.android.material.snackbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.View.MeasureSpec;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;

public class SnackbarContentLayout extends LinearLayout implements ContentViewCallback {
   private Button actionView;
   private int maxInlineActionWidth;
   private int maxWidth;
   private TextView messageView;

   public SnackbarContentLayout(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public SnackbarContentLayout(Context var1, AttributeSet var2) {
      super(var1, var2);
      TypedArray var3 = var1.obtainStyledAttributes(var2, R.styleable.SnackbarLayout);
      this.maxWidth = var3.getDimensionPixelSize(R.styleable.SnackbarLayout_android_maxWidth, -1);
      this.maxInlineActionWidth = var3.getDimensionPixelSize(R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
      var3.recycle();
   }

   private static void updateTopBottomPadding(View var0, int var1, int var2) {
      if (ViewCompat.isPaddingRelative(var0)) {
         ViewCompat.setPaddingRelative(var0, ViewCompat.getPaddingStart(var0), var1, ViewCompat.getPaddingEnd(var0), var2);
      } else {
         var0.setPadding(var0.getPaddingLeft(), var1, var0.getPaddingRight(), var2);
      }

   }

   private boolean updateViewsWithinLayout(int var1, int var2, int var3) {
      int var4 = this.getOrientation();
      boolean var6 = true;
      boolean var5;
      if (var1 != var4) {
         this.setOrientation(var1);
         var5 = true;
      } else {
         var5 = false;
      }

      if (this.messageView.getPaddingTop() != var2 || this.messageView.getPaddingBottom() != var3) {
         updateTopBottomPadding(this.messageView, var2, var3);
         var5 = var6;
      }

      return var5;
   }

   public void animateContentIn(int var1, int var2) {
      this.messageView.setAlpha(0.0F);
      ViewPropertyAnimator var7 = this.messageView.animate().alpha(1.0F);
      long var3 = (long)var2;
      var7 = var7.setDuration(var3);
      long var5 = (long)var1;
      var7.setStartDelay(var5).start();
      if (this.actionView.getVisibility() == 0) {
         this.actionView.setAlpha(0.0F);
         this.actionView.animate().alpha(1.0F).setDuration(var3).setStartDelay(var5).start();
      }

   }

   public void animateContentOut(int var1, int var2) {
      this.messageView.setAlpha(1.0F);
      ViewPropertyAnimator var7 = this.messageView.animate().alpha(0.0F);
      long var5 = (long)var2;
      var7 = var7.setDuration(var5);
      long var3 = (long)var1;
      var7.setStartDelay(var3).start();
      if (this.actionView.getVisibility() == 0) {
         this.actionView.setAlpha(1.0F);
         this.actionView.animate().alpha(0.0F).setDuration(var5).setStartDelay(var3).start();
      }

   }

   public Button getActionView() {
      return this.actionView;
   }

   public TextView getMessageView() {
      return this.messageView;
   }

   protected void onFinishInflate() {
      super.onFinishInflate();
      this.messageView = (TextView)this.findViewById(R.id.snackbar_text);
      this.actionView = (Button)this.findViewById(R.id.snackbar_action);
   }

   protected void onMeasure(int var1, int var2) {
      super.onMeasure(var1, var2);
      int var3 = var1;
      int var4;
      if (this.maxWidth > 0) {
         var4 = this.getMeasuredWidth();
         int var5 = this.maxWidth;
         var3 = var1;
         if (var4 > var5) {
            var3 = MeasureSpec.makeMeasureSpec(var5, 1073741824);
            super.onMeasure(var3, var2);
         }
      }

      int var6 = this.getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical_2lines);
      var4 = this.getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical);
      var1 = this.messageView.getLayout().getLineCount();
      boolean var8 = false;
      boolean var7;
      if (var1 > 1) {
         var7 = true;
      } else {
         var7 = false;
      }

      label44: {
         if (var7 && this.maxInlineActionWidth > 0 && this.actionView.getMeasuredWidth() > this.maxInlineActionWidth) {
            var7 = var8;
            if (!this.updateViewsWithinLayout(1, var6, var6 - var4)) {
               break label44;
            }
         } else {
            if (var7) {
               var4 = var6;
            }

            var7 = var8;
            if (!this.updateViewsWithinLayout(0, var4, var4)) {
               break label44;
            }
         }

         var7 = true;
      }

      if (var7) {
         super.onMeasure(var3, var2);
      }

   }
}
