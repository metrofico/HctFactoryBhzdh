package com.hzbhd.canbus.car_cus._273;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
   private int space;

   public SpaceItemDecoration(int var1) {
      this.space = var1;
   }

   public void getItemOffsets(Rect var1, View var2, RecyclerView var3, RecyclerView.State var4) {
      super.getItemOffsets(var1, var2, var3, var4);
      var1.bottom = this.space;
   }
}
