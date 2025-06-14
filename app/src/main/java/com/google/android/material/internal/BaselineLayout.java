package com.google.android.material.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class BaselineLayout extends ViewGroup {
   private int baseline = -1;

   public BaselineLayout(Context var1) {
      super(var1, (AttributeSet)null, 0);
   }

   public BaselineLayout(Context var1, AttributeSet var2) {
      super(var1, var2, 0);
   }

   public BaselineLayout(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   public int getBaseline() {
      return this.baseline;
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      int var7 = this.getChildCount();
      int var8 = this.getPaddingLeft();
      int var9 = this.getPaddingRight();
      int var6 = this.getPaddingTop();

      for(var3 = 0; var3 < var7; ++var3) {
         View var13 = this.getChildAt(var3);
         if (var13.getVisibility() != 8) {
            int var12 = var13.getMeasuredWidth();
            int var10 = var13.getMeasuredHeight();
            int var11 = (var4 - var2 - var9 - var8 - var12) / 2 + var8;
            if (this.baseline != -1 && var13.getBaseline() != -1) {
               var5 = this.baseline + var6 - var13.getBaseline();
            } else {
               var5 = var6;
            }

            var13.layout(var11, var5, var12 + var11, var10 + var5);
         }
      }

   }

   protected void onMeasure(int var1, int var2) {
      int var11 = this.getChildCount();
      int var10 = 0;
      int var4 = -1;
      int var3 = -1;
      int var5 = 0;
      int var9 = 0;

      int var6;
      int var8;
      for(var8 = var9; var10 < var11; ++var10) {
         View var13 = this.getChildAt(var10);
         if (var13.getVisibility() != 8) {
            this.measureChild(var13, var1, var2);
            int var12 = var13.getBaseline();
            int var7 = var4;
            var6 = var3;
            if (var12 != -1) {
               var7 = Math.max(var4, var12);
               var6 = Math.max(var3, var13.getMeasuredHeight() - var12);
            }

            var9 = Math.max(var9, var13.getMeasuredWidth());
            var5 = Math.max(var5, var13.getMeasuredHeight());
            var8 = View.combineMeasuredStates(var8, var13.getMeasuredState());
            var3 = var6;
            var4 = var7;
         }
      }

      var6 = var5;
      if (var4 != -1) {
         var6 = Math.max(var5, Math.max(var3, this.getPaddingBottom()) + var4);
         this.baseline = var4;
      }

      var3 = Math.max(var6, this.getSuggestedMinimumHeight());
      this.setMeasuredDimension(View.resolveSizeAndState(Math.max(var9, this.getSuggestedMinimumWidth()), var1, var8), View.resolveSizeAndState(var3, var2, var8 << 16));
   }
}
