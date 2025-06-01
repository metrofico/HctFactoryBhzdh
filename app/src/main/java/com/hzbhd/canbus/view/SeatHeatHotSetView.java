package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;

public class SeatHeatHotSetView extends RelativeLayout {
   private ImageView mIvClickItem;
   private OnAirSeatHeatColdMinPlusClickListener mOnMinAddClickListener;
   private TextView mTvDisplayItem;

   public SeatHeatHotSetView(Context var1) {
      super(var1);
   }

   public SeatHeatHotSetView(Context var1, AttributeSet var2) {
      super(var1, var2);
      View var3 = LayoutInflater.from(var1).inflate(2131558815, this);
      this.mIvClickItem = (ImageView)var3.findViewById(2131362558);
      this.mTvDisplayItem = (TextView)var3.findViewById(2131363613);
      this.mIvClickItem.setOnClickListener(new View.OnClickListener(this) {
         final SeatHeatHotSetView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.mOnMinAddClickListener != null) {
               this.this$0.mOnMinAddClickListener.onClickPlus();
            }

         }
      });
   }

   public SeatHeatHotSetView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   public void setControllable(boolean var1) {
      this.mIvClickItem.setEnabled(var1);
   }

   public void setOnUpDownClickListener(OnAirSeatHeatColdMinPlusClickListener var1) {
      this.mOnMinAddClickListener = var1;
   }

   public void setValue(String var1) {
      this.mTvDisplayItem.setText(var1);
   }
}
