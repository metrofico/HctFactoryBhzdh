package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

public class VerticalViewPager extends ViewPager {
   private boolean mScrollAble = true;

   public VerticalViewPager(Context var1) {
      super(var1);
   }

   public VerticalViewPager(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.setPageTransformer(true, new VerticalTransformer());
   }

   private MotionEvent swapTouchEvent(MotionEvent var1) {
      float var3 = (float)this.getWidth();
      float var2 = (float)this.getHeight();
      var1.setLocation(var1.getY() / var2 * var3, var1.getX() / var3 * var2);
      return var1;
   }

   public boolean onInterceptTouchEvent(MotionEvent var1) {
      return !this.mScrollAble ? false : super.onInterceptTouchEvent(this.swapTouchEvent(MotionEvent.obtain(var1)));
   }

   public boolean onTouchEvent(MotionEvent var1) {
      return !this.mScrollAble ? false : super.onTouchEvent(this.swapTouchEvent(MotionEvent.obtain(var1)));
   }

   public void setScrollEnabled(boolean var1) {
      this.mScrollAble = var1;
   }
}
