package com.hzbhd.canbus.car_cus._277.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._277.ui_set.VehicleDiagnosisPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import java.util.List;

public class VehicleDiagnosisLeftLvAdapter extends RecyclerView.Adapter {
   private Context mContext;
   private LeftItemClickInterface mLeftItemClickInterface;
   private List mList;

   public VehicleDiagnosisLeftLvAdapter(Context var1, List var2, LeftItemClickInterface var3) {
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
      int var3 = CommUtil.getStrIdByResId(this.mContext, ((VehicleDiagnosisPageUiSet.ListBean)this.mList.get(var2)).getTitleSrn());
      int var4 = CommUtil.getStrIdByResId(this.mContext, ((VehicleDiagnosisPageUiSet.ListBean)this.mList.get(var2)).getValue());
      var1.textView.setText(var3);
      var1.textValue.setText(var4);
      if ("geely_e6_item_4".equals(((VehicleDiagnosisPageUiSet.ListBean)this.mList.get(var2)).getValue())) {
         var1.textValue.setTextColor(-65536);
      } else if ("geely_e6_item_0".equals(((VehicleDiagnosisPageUiSet.ListBean)this.mList.get(var2)).getValue())) {
         var1.textValue.setTextColor(-1);
      }

      if (((VehicleDiagnosisPageUiSet.ListBean)this.mList.get(var2)).isIsSelected()) {
         var1.textView.setBackground((Drawable)null);
      } else {
         var1.textView.setBackground((Drawable)null);
      }

      var1.textView.setOnClickListener(new View.OnClickListener(this, var2) {
         final VehicleDiagnosisLeftLvAdapter this$0;
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
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558425, var1, false));
   }

   public interface LeftItemClickInterface {
      void onLeftItemClick(int var1);
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private TextView textValue;
      private TextView textView;
      final VehicleDiagnosisLeftLvAdapter this$0;

      ViewHolder(VehicleDiagnosisLeftLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.textView = (TextView)var2.findViewById(2131363597);
         this.textValue = (TextView)var2.findViewById(2131363714);
      }
   }
}
