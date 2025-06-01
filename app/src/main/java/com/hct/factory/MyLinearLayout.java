package com.hct.factory;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {
   public MyLinearLayout(Context var1) {
      super(var1);
   }

   public MyLinearLayout(Context var1, @Nullable AttributeSet var2) {
      super(var1, var2);
   }

   public MyLinearLayout(Context var1, @Nullable AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   public MyLinearLayout(Context var1, AttributeSet var2, int var3, int var4) {
      super(var1, var2, var3, var4);
   }

   public boolean dispatchTouchEvent(MotionEvent var1) {
      this.getParent().requestDisallowInterceptTouchEvent(true);
      return super.dispatchTouchEvent(var1);
   }

   public boolean onTouchEvent(MotionEvent var1) {
      if (var1.getAction() != 0) {
         return false;
      } else {
         this.getParent().requestDisallowInterceptTouchEvent(true);
         return super.onTouchEvent(var1);
      }
   }
}
