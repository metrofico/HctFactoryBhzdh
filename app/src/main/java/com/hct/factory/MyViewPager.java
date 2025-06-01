package com.hct.factory;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {
   private boolean noScroll = false;

   public MyViewPager(Context var1) {
      super(var1);
   }

   public MyViewPager(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public boolean isNoScroll() {
      return this.noScroll;
   }

   public boolean onInterceptTouchEvent(MotionEvent var1) {
      return this.isNoScroll() ? false : super.onInterceptTouchEvent(var1);
   }

   public boolean onTouchEvent(MotionEvent var1) {
      return this.isNoScroll() ? false : super.onTouchEvent(var1);
   }

   public void setNoScroll(boolean var1) {
      this.noScroll = var1;
   }
}
