package com.hzbhd.canbus.car_cus._304;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

public class MyLinearLayoutManager extends LinearLayoutManager {
   private final float MILLISECONDS_PER_INCH = 5.0F;

   public MyLinearLayoutManager(Context var1) {
      super(var1);
   }

   public void smoothScrollToPosition(RecyclerView var1, RecyclerView.State var2, int var3) {
      LinearSmoothScroller var4 = new LinearSmoothScroller(this, var1.getContext()) {
         final MyLinearLayoutManager this$0;

         {
            this.this$0 = var1;
         }

         protected float calculateSpeedPerPixel(DisplayMetrics var1) {
            return 5.0F / var1.density;
         }

         public PointF computeScrollVectorForPosition(int var1) {
            return this.this$0.computeScrollVectorForPosition(var1);
         }
      };
      var4.setTargetPosition(var3);
      this.startSmoothScroll(var4);
   }
}
