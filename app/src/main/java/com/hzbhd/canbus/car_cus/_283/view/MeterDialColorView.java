package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.adapter.MeterColorAdapter;

public class MeterDialColorView extends RelativeLayout {
   private MeterColorAdapter colorAdapter;
   private int[] colors2;
   private RecyclerView mRecyclerView;

   public MeterDialColorView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public MeterDialColorView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public MeterDialColorView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.colors2 = new int[]{2131231981, 2131231992, 2131232003, 2131232005, 2131232006, 2131232007, 2131232008, 2131232009, 2131232010, 2131231982, 2131231983, 2131231984, 2131231985, 2131231986, 2131231987, 2131231988, 2131231989, 2131231990, 2131231991, 2131231993, 2131231994, 2131231995, 2131231996, 2131231997, 2131231998, 2131231999, 2131232000, 2131232001, 2131232002, 2131232004};
   }

   // $FF: synthetic method
   static void lambda$onFinishInflate$0(View var0, int var1) {
      MessageSender.sendMsg(new byte[]{22, 109, 12, (byte)(var1 + 3 + 1)});
   }

   protected void onFinishInflate() {
      super.onFinishInflate();
      RecyclerView var1 = (RecyclerView)this.findViewById(2131363072);
      this.mRecyclerView = var1;
      var1.setLayoutManager(new GridLayoutManager(this.getContext(), 6));
      MeterColorAdapter var2 = new MeterColorAdapter(this.getContext(), this.colors2);
      this.colorAdapter = var2;
      this.mRecyclerView.setAdapter(var2);
      this.colorAdapter.setOnViewClickListener(new MeterDialColorView$$ExternalSyntheticLambda0());
   }

   public void setSelectedItem(int var1) {
      MeterColorAdapter var2 = this.colorAdapter;
      if (var2 != null) {
         var2.setSelectedItem(var1);
      }

   }
}
