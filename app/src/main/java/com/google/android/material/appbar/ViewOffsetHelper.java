package com.google.android.material.appbar;

import android.view.View;
import androidx.core.view.ViewCompat;

class ViewOffsetHelper {
   private int layoutLeft;
   private int layoutTop;
   private int offsetLeft;
   private int offsetTop;
   private final View view;

   public ViewOffsetHelper(View var1) {
      this.view = var1;
   }

   private void updateOffsets() {
      View var1 = this.view;
      ViewCompat.offsetTopAndBottom(var1, this.offsetTop - (var1.getTop() - this.layoutTop));
      var1 = this.view;
      ViewCompat.offsetLeftAndRight(var1, this.offsetLeft - (var1.getLeft() - this.layoutLeft));
   }

   public int getLayoutLeft() {
      return this.layoutLeft;
   }

   public int getLayoutTop() {
      return this.layoutTop;
   }

   public int getLeftAndRightOffset() {
      return this.offsetLeft;
   }

   public int getTopAndBottomOffset() {
      return this.offsetTop;
   }

   public void onViewLayout() {
      this.layoutTop = this.view.getTop();
      this.layoutLeft = this.view.getLeft();
      this.updateOffsets();
   }

   public boolean setLeftAndRightOffset(int var1) {
      if (this.offsetLeft != var1) {
         this.offsetLeft = var1;
         this.updateOffsets();
         return true;
      } else {
         return false;
      }
   }

   public boolean setTopAndBottomOffset(int var1) {
      if (this.offsetTop != var1) {
         this.offsetTop = var1;
         this.updateOffsets();
         return true;
      } else {
         return false;
      }
   }
}
