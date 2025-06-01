package com.hzbhd.canbus.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class VerticalProgressBar extends ProgressBar {
   public VerticalProgressBar(Context var1) {
      super(var1);
   }

   public VerticalProgressBar(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public VerticalProgressBar(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   protected void onDraw(Canvas var1) {
      synchronized(this){}

      try {
         var1.rotate(-90.0F);
         var1.translate((float)(-this.getHeight()), 0.0F);
         super.onDraw(var1);
      } finally {
         ;
      }

   }

   protected void onMeasure(int var1, int var2) {
      synchronized(this){}

      try {
         super.onMeasure(var2, var1);
         this.setMeasuredDimension(this.getMeasuredHeight(), this.getMeasuredWidth());
      } finally {
         ;
      }

   }

   protected void onSizeChanged(int var1, int var2, int var3, int var4) {
      super.onSizeChanged(var2, var1, var3, var4);
   }
}
