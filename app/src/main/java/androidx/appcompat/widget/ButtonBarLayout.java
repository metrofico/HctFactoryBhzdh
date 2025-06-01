package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import androidx.appcompat.R;
import androidx.core.view.ViewCompat;

public class ButtonBarLayout extends LinearLayout {
   private static final int PEEK_BUTTON_DP = 16;
   private boolean mAllowStacking;
   private int mLastWidthSize = -1;
   private boolean mStacked;

   public ButtonBarLayout(Context var1, AttributeSet var2) {
      super(var1, var2);
      TypedArray var3 = var1.obtainStyledAttributes(var2, R.styleable.ButtonBarLayout);
      ViewCompat.saveAttributeDataForStyleable(this, var1, R.styleable.ButtonBarLayout, var2, var3, 0, 0);
      this.mAllowStacking = var3.getBoolean(R.styleable.ButtonBarLayout_allowStacking, true);
      var3.recycle();
      if (this.getOrientation() == 1) {
         this.setStacked(this.mAllowStacking);
      }

   }

   private int getNextVisibleChildIndex(int var1) {
      for(int var2 = this.getChildCount(); var1 < var2; ++var1) {
         if (this.getChildAt(var1).getVisibility() == 0) {
            return var1;
         }
      }

      return -1;
   }

   private boolean isStacked() {
      return this.mStacked;
   }

   private void setStacked(boolean var1) {
      if (this.mStacked != var1 && (var1 == 0 || this.mAllowStacking)) {
         this.mStacked = (boolean)var1;
         this.setOrientation(var1);
         int var2;
         if (var1 != 0) {
            var2 = 8388613;
         } else {
            var2 = 80;
         }

         this.setGravity(var2);
         View var3 = this.findViewById(R.id.spacer);
         if (var3 != null) {
            if (var1 != 0) {
               var1 = 8;
            } else {
               var1 = 4;
            }

            var3.setVisibility(var1);
         }

         for(var1 = this.getChildCount() - 2; var1 >= 0; --var1) {
            this.bringChildToFront(this.getChildAt(var1));
         }
      }

   }

   protected void onMeasure(int var1, int var2) {
      int var3 = MeasureSpec.getSize(var1);
      boolean var7 = this.mAllowStacking;
      byte var6 = 0;
      if (var7) {
         if (var3 > this.mLastWidthSize && this.isStacked()) {
            this.setStacked(false);
         }

         this.mLastWidthSize = var3;
      }

      int var4;
      boolean var10;
      if (!this.isStacked() && MeasureSpec.getMode(var1) == 1073741824) {
         var4 = MeasureSpec.makeMeasureSpec(var3, Integer.MIN_VALUE);
         var10 = true;
      } else {
         var4 = var1;
         var10 = false;
      }

      super.onMeasure(var4, var2);
      boolean var5 = var10;
      if (this.mAllowStacking) {
         var5 = var10;
         if (!this.isStacked()) {
            boolean var11;
            if ((this.getMeasuredWidthAndState() & -16777216) == 16777216) {
               var11 = true;
            } else {
               var11 = false;
            }

            var5 = var10;
            if (var11) {
               this.setStacked(true);
               var5 = true;
            }
         }
      }

      if (var5) {
         super.onMeasure(var1, var2);
      }

      int var12 = this.getNextVisibleChildIndex(0);
      var3 = var6;
      if (var12 >= 0) {
         View var8 = this.getChildAt(var12);
         LinearLayout.LayoutParams var9 = (LinearLayout.LayoutParams)var8.getLayoutParams();
         var4 = this.getPaddingTop() + var8.getMeasuredHeight() + var9.topMargin + var9.bottomMargin + 0;
         if (this.isStacked()) {
            var12 = this.getNextVisibleChildIndex(var12 + 1);
            var3 = var4;
            if (var12 >= 0) {
               var3 = var4 + this.getChildAt(var12).getPaddingTop() + (int)(this.getResources().getDisplayMetrics().density * 16.0F);
            }
         } else {
            var3 = var4 + this.getPaddingBottom();
         }
      }

      if (ViewCompat.getMinimumHeight(this) != var3) {
         this.setMinimumHeight(var3);
         if (var2 == 0) {
            super.onMeasure(var1, var2);
         }
      }

   }

   public void setAllowStacking(boolean var1) {
      if (this.mAllowStacking != var1) {
         this.mAllowStacking = var1;
         if (!var1 && this.isStacked()) {
            this.setStacked(false);
         }

         this.requestLayout();
      }

   }
}
