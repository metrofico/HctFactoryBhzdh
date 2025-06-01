package com.hzbhd.canbus.car_cus._283.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MeterColorAdapter extends RecyclerView.Adapter {
   private int[] mColors;
   private Context mContext;
   private OnViewClickListener mOnViewClickListener;
   private int selectedPosition = -1;

   public MeterColorAdapter(Context var1, int[] var2) {
      this.mColors = var2;
      this.mContext = var1;
   }

   public int getItemCount() {
      return this.mColors.length;
   }

   // $FF: synthetic method
   void lambda$onBindViewHolder$0$com_hzbhd_canbus_car_cus__283_adapter_MeterColorAdapter(int var1, View var2) {
      OnViewClickListener var3 = this.mOnViewClickListener;
      if (var3 != null) {
         var3.onClick(var2, var1);
      }

   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      var1.imageView.setImageResource(this.mColors[var2]);
      RelativeLayout var4 = var1.itemLayout;
      boolean var3;
      if (var2 == this.selectedPosition) {
         var3 = true;
      } else {
         var3 = false;
      }

      var4.setSelected(var3);
      var1.itemLayout.setOnClickListener(new MeterColorAdapter$$ExternalSyntheticLambda0(this, var2));
   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(this.mContext).inflate(2131558433, (ViewGroup)null, false));
   }

   public void setOnViewClickListener(OnViewClickListener var1) {
      this.mOnViewClickListener = var1;
   }

   public void setSelectedItem(int var1) {
      this.selectedPosition = var1;
      this.notifyDataSetChanged();
   }

   public interface OnViewClickListener {
      void onClick(View var1, int var2);
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      private ImageView imageView;
      private RelativeLayout itemLayout;
      final MeterColorAdapter this$0;

      public ViewHolder(MeterColorAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.imageView = (ImageView)var2.findViewById(2131362472);
         this.itemLayout = (RelativeLayout)var2.findViewById(2131362522);
      }
   }
}
