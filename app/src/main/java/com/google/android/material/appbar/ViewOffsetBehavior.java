package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

class ViewOffsetBehavior extends CoordinatorLayout.Behavior {
   private int tempLeftRightOffset = 0;
   private int tempTopBottomOffset = 0;
   private ViewOffsetHelper viewOffsetHelper;

   public ViewOffsetBehavior() {
   }

   public ViewOffsetBehavior(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public int getLeftAndRightOffset() {
      ViewOffsetHelper var2 = this.viewOffsetHelper;
      int var1;
      if (var2 != null) {
         var1 = var2.getLeftAndRightOffset();
      } else {
         var1 = 0;
      }

      return var1;
   }

   public int getTopAndBottomOffset() {
      ViewOffsetHelper var2 = this.viewOffsetHelper;
      int var1;
      if (var2 != null) {
         var1 = var2.getTopAndBottomOffset();
      } else {
         var1 = 0;
      }

      return var1;
   }

   protected void layoutChild(CoordinatorLayout var1, View var2, int var3) {
      var1.onLayoutChild(var2, var3);
   }

   public boolean onLayoutChild(CoordinatorLayout var1, View var2, int var3) {
      this.layoutChild(var1, var2, var3);
      if (this.viewOffsetHelper == null) {
         this.viewOffsetHelper = new ViewOffsetHelper(var2);
      }

      this.viewOffsetHelper.onViewLayout();
      var3 = this.tempTopBottomOffset;
      if (var3 != 0) {
         this.viewOffsetHelper.setTopAndBottomOffset(var3);
         this.tempTopBottomOffset = 0;
      }

      var3 = this.tempLeftRightOffset;
      if (var3 != 0) {
         this.viewOffsetHelper.setLeftAndRightOffset(var3);
         this.tempLeftRightOffset = 0;
      }

      return true;
   }

   public boolean setLeftAndRightOffset(int var1) {
      ViewOffsetHelper var2 = this.viewOffsetHelper;
      if (var2 != null) {
         return var2.setLeftAndRightOffset(var1);
      } else {
         this.tempLeftRightOffset = var1;
         return false;
      }
   }

   public boolean setTopAndBottomOffset(int var1) {
      ViewOffsetHelper var2 = this.viewOffsetHelper;
      if (var2 != null) {
         return var2.setTopAndBottomOffset(var1);
      } else {
         this.tempTopBottomOffset = var1;
         return false;
      }
   }
}
