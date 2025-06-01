package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.adapter.MeterColorAdapter;

public class MeterPointerColorView extends RelativeLayout {
   private MeterColorAdapter colorAdapter1;
   private int[] colors1;
   private int[] colors1_byte;
   private RecyclerView mRecyclerView1;

   public MeterPointerColorView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public MeterPointerColorView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public MeterPointerColorView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.colors1_byte = new int[]{16777215, 15400960, 18687, 7400960, 2162632, 52449, 16762368, 13789470, 11591910, 16668912, 8142782, 16753828, 14423100, 16748800, 13529087, 14929751, 65533, 11534144};
      this.colors1 = new int[]{2131232024, 2131232034, 2131232035, 2131232036, 2131232037, 2131232038, 2131232039, 2131232040, 2131232041, 2131232025, 2131232026, 2131232027, 2131232028, 2131232029, 2131232030, 2131232031, 2131232032, 2131232033};
   }

   // $FF: synthetic method
   void lambda$onFinishInflate$0$com_hzbhd_canbus_car_cus__283_view_MeterPointerColorView(View var1, int var2) {
      var2 = this.colors1_byte[var2];
      MessageSender.sendMsg(new byte[]{25, 0, 0, 0, 0, 33, 0, 3, (byte)(var2 >> 16 & 255), (byte)(var2 >> 8 & 255), (byte)(var2 & 255)});
   }

   protected void onFinishInflate() {
      super.onFinishInflate();
      RecyclerView var1 = (RecyclerView)this.findViewById(2131363073);
      this.mRecyclerView1 = var1;
      var1.setLayoutManager(new GridLayoutManager(this.getContext(), 6));
      MeterColorAdapter var2 = new MeterColorAdapter(this.getContext(), this.colors1);
      this.colorAdapter1 = var2;
      this.mRecyclerView1.setAdapter(var2);
      this.colorAdapter1.setOnViewClickListener(new MeterPointerColorView$$ExternalSyntheticLambda0(this));
   }

   public void setSelectedItem() {
      int var1 = 0;

      while(true) {
         int[] var3 = this.colors1_byte;
         if (var1 >= var3.length) {
            return;
         }

         int var2 = var3[var1];
         if (GeneralDzData.colorR == (var2 >> 16 & 255) && GeneralDzData.colorG == (var2 >> 8 & 255) && GeneralDzData.colorB == (var2 & 255)) {
            this.colorAdapter1.setSelectedItem(var1);
            return;
         }

         ++var1;
      }
   }
}
