package com.hzbhd.canbus.car_cus._277.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._277.ui_set.VehicleMonitorPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import java.util.List;

public class VihicleMonitorLeftLvAdapter extends RecyclerView.Adapter {
   private Context mContext;
   private LeftItemClickInterface mLeftItemClickInterface;
   private List mList;

   public VihicleMonitorLeftLvAdapter(Context var1, List var2, LeftItemClickInterface var3) {
      this.mLeftItemClickInterface = var3;
      this.mList = var2;
      this.mContext = var1;
   }

   public int getItemCount() {
      return this.mList.size();
   }

   public long getItemId(int var1) {
      return (long)var1;
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      int var3 = CommUtil.getStrIdByResId(this.mContext, ((VehicleMonitorPageUiSet.ListBean)this.mList.get(var2)).getTitleSrn());
      var1.textView.setText(var3);
      if (((VehicleMonitorPageUiSet.ListBean)this.mList.get(var2)).isIsSelected()) {
         var1.textView.setBackgroundResource(2131232548);
      } else {
         var1.textView.setBackgroundResource(2131232549);
      }

      var1.textView.setOnClickListener(new View.OnClickListener(this, var2) {
         final VihicleMonitorLeftLvAdapter this$0;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$position = var2;
         }

         public void onClick(View var1) {
            this.this$0.mLeftItemClickInterface.onLeftItemClick(this.val$position);
         }
      });
   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      View var5 = LayoutInflater.from(var1.getContext()).inflate(2131558426, var1, false);
      WindowManager var3 = (WindowManager)this.mContext.getSystemService("window");
      DisplayMetrics var4 = new DisplayMetrics();
      var3.getDefaultDisplay().getMetrics(var4);
      var5.setLayoutParams(new ViewGroup.LayoutParams(var4.widthPixels / 3, -2));
      return new ViewHolder(this, var5);
   }

   public interface LeftItemClickInterface {
      void onLeftItemClick(int var1);
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private TextView textView;
      final VihicleMonitorLeftLvAdapter this$0;

      ViewHolder(VihicleMonitorLeftLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.textView = (TextView)var2.findViewById(2131363597);
      }
   }
}
