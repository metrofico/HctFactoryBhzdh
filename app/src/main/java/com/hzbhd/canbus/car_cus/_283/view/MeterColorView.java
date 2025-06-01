package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.adapter.MeterColorAdapter;

public class MeterColorView extends RelativeLayout {
   private int[] colors1;
   private int[] colors1_byte;
   private int[] colors2;
   private MyRecyclerView mRecyclerView1;
   private RecyclerView mRecyclerView2;
   private View view1;
   private View view2;

   public MeterColorView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public MeterColorView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public MeterColorView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.colors1_byte = new int[]{16777215, 15400960, 18687, 7400960, 2162632, 52449, 16762368, 13789470, 11591910, 16668912, 8142782, 16753828, 14423100, 16748800, 13529087, 14929751, 65533, 11534144};
      this.colors1 = new int[]{2131232024, 2131232034, 2131232035, 2131232036, 2131232037, 2131232038, 2131232039, 2131232040, 2131232041, 2131232025, 2131232026, 2131232027, 2131232028, 2131232029, 2131232030, 2131232031, 2131232032, 2131232033};
      this.colors2 = new int[]{2131231981, 2131231992, 2131232003, 2131232005, 2131232006, 2131232007, 2131232008, 2131232009, 2131232010, 2131231982, 2131231983, 2131231984, 2131231985, 2131231986, 2131231987, 2131231988, 2131231989, 2131231990, 2131231991, 2131231993, 2131231994, 2131231995, 2131231996, 2131231997, 2131231998, 2131231999, 2131232000, 2131232001, 2131232002, 2131232004};
   }

   // $FF: synthetic method
   static void lambda$onFinishInflate$1(View var0, int var1) {
      MessageSender.sendMsg(new byte[]{22, 109, 12, (byte)(var1 + 3 + 1)});
   }

   // $FF: synthetic method
   void lambda$onFinishInflate$0$com_hzbhd_canbus_car_cus__283_view_MeterColorView(View var1, int var2) {
      var2 = this.colors1_byte[var2];
      MessageSender.sendMsg(new byte[]{25, 0, 0, 0, 0, 33, 0, 3, (byte)(var2 >> 16 & 255), (byte)(var2 >> 8 & 255), (byte)(var2 & 255)});
   }

   protected void onFinishInflate() {
      super.onFinishInflate();
      this.mRecyclerView1 = (MyRecyclerView)this.findViewById(2131363073);
      this.mRecyclerView2 = (RecyclerView)this.findViewById(2131363074);
      this.view1 = this.findViewById(2131363767);
      this.view2 = this.findViewById(2131363768);
      this.mRecyclerView1.setLayoutManager(new GridLayoutManager(this.getContext(), 6));
      MeterColorAdapter var1 = new MeterColorAdapter(this.getContext(), this.colors1);
      this.mRecyclerView1.setAdapter(var1);
      var1.setOnViewClickListener(new MeterColorView$$ExternalSyntheticLambda0(this));
      this.mRecyclerView1.setOnScrollChangeListener(new MyRecyclerView.OnScrollChangeListener(this) {
         final MeterColorView this$0;

         {
            this.this$0 = var1;
         }

         public void scrollDown() {
            this.this$0.mRecyclerView1.smoothScrollToPosition(this.this$0.colors1.length - 1);
            this.this$0.view1.setBackgroundResource(2131231977);
            this.this$0.view2.setBackgroundResource(2131231976);
         }

         public void scrollUp() {
            this.this$0.mRecyclerView1.smoothScrollToPosition(0);
            this.this$0.view1.setBackgroundResource(2131231976);
            this.this$0.view2.setBackgroundResource(2131231977);
         }
      });
      this.mRecyclerView2.setLayoutManager(new GridLayoutManager(this.getContext(), 6));
      var1 = new MeterColorAdapter(this.getContext(), this.colors2);
      this.mRecyclerView2.setAdapter(var1);
      var1.setOnViewClickListener(new MeterColorView$$ExternalSyntheticLambda1());
   }
}
