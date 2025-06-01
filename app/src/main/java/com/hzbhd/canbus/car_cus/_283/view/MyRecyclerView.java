package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerView extends RecyclerView {
   private OnScrollChangeListener mOnScrollChangeListener;
   private float moveY;

   public MyRecyclerView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public MyRecyclerView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public MyRecyclerView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   public boolean onTouchEvent(MotionEvent var1) {
      label31: {
         int var3 = var1.getAction();
         if (var3 != 0) {
            if (var3 == 1) {
               break label31;
            }

            if (var3 != 2) {
               return super.onTouchEvent(var1);
            }
         } else {
            this.moveY = var1.getY();
         }

         if (this.moveY == 0.0F) {
            this.moveY = var1.getY();
         }
      }

      float var2 = this.moveY - var1.getY();
      OnScrollChangeListener var4;
      if (var2 > 0.0F) {
         var4 = this.mOnScrollChangeListener;
         if (var4 != null) {
            var4.scrollDown();
         }
      } else if (var2 < 0.0F) {
         var4 = this.mOnScrollChangeListener;
         if (var4 != null) {
            var4.scrollUp();
         }
      }

      this.moveY = 0.0F;
      return super.onTouchEvent(var1);
   }

   public void setOnScrollChangeListener(OnScrollChangeListener var1) {
      this.mOnScrollChangeListener = var1;
   }

   public interface OnScrollChangeListener {
      void scrollDown();

      void scrollUp();
   }
}
