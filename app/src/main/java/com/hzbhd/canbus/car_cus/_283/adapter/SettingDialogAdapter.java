package com.hzbhd.canbus.car_cus._283.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import java.util.List;

public class SettingDialogAdapter extends RecyclerView.Adapter {
   private List lists;
   private Context mContext;
   private OnItemClickListener mOnItemClickListener;

   public SettingDialogAdapter(Context var1, List var2) {
      this.mContext = var1;
      this.lists = var2;
   }

   public int getItemCount() {
      return this.lists.size();
   }

   // $FF: synthetic method
   void lambda$onBindViewHolder$0$com_hzbhd_canbus_car_cus__283_adapter_SettingDialogAdapter(int var1, View var2) {
      OnItemClickListener var3 = this.mOnItemClickListener;
      if (var3 != null) {
         var3.onClick(var1);
      }

   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      var1.itemView.setOnClickListener(new SettingDialogAdapter$$ExternalSyntheticLambda0(this, var2));
      var1.tv_select.setVisibility(((SettingDialogBean)this.lists.get(var2)).getSelectVisible());
      var1.tv_value.setText(((SettingDialogBean)this.lists.get(var2)).getValue() + ((SettingDialogBean)this.lists.get(var2)).getUnit());
      var1.tv_value.setTextColor(this.mContext.getColor(((SettingDialogBean)this.lists.get(var2)).getTextColor()));
      if (this.getItemCount() - 1 == var2) {
         var1.lineView.setVisibility(8);
      }

   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(this.mContext).inflate(2131558436, var1, false));
   }

   public void setOnItemClickListener(OnItemClickListener var1) {
      this.mOnItemClickListener = var1;
   }

   public interface OnItemClickListener {
      void onClick(int var1);
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      private View lineView;
      final SettingDialogAdapter this$0;
      private ImageView tv_select;
      private TextView tv_value;

      public ViewHolder(SettingDialogAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.tv_select = (ImageView)var2.findViewById(2131363699);
         this.tv_value = (TextView)var2.findViewById(2131363714);
         this.lineView = var2.findViewById(2131362757);
      }
   }
}
