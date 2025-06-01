package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

public class NoScrollViewPager extends ViewPager {
   public NoScrollViewPager(Context var1) {
      super(var1);
   }

   public NoScrollViewPager(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public boolean onInterceptTouchEvent(MotionEvent var1) {
      return false;
   }

   public boolean onTouchEvent(MotionEvent var1) {
      return false;
   }
}
