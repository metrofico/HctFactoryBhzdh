package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import java.util.List;

public class DriveDataLvItemAdapter extends RecyclerView.Adapter {
   private Context mContext;
   private List mList;

   public DriveDataLvItemAdapter(Context var1, List var2) {
      this.mContext = var1;
      this.mList = var2;
   }

   public int getItemCount() {
      return this.mList.size();
   }

   public long getItemId(int var1) {
      return (long)var1;
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      if (((DriverDataPageUiSet.Page.Item)this.mList.get(var2)).isTitleStr()) {
         var1.tvTitle.setText(((DriverDataPageUiSet.Page.Item)this.mList.get(var2)).getTitleSrn());
      } else {
         var1.tvTitle.setText(CommUtil.getStrIdByResId(this.mContext, ((DriverDataPageUiSet.Page.Item)this.mList.get(var2)).getTitleSrn()));
      }

      String var3 = ((DriverDataPageUiSet.Page.Item)this.mList.get(var2)).getValue();
      if (TextUtils.isEmpty(var3)) {
         var1.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, ((DriverDataPageUiSet.Page.Item)this.mList.get(var2)).getDefaultValueSrn()));
      } else {
         var1.tvValue.setText(var3);
      }

   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558763, var1, false));
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      final DriveDataLvItemAdapter this$0;
      private TextView tvTitle;
      private TextView tvValue;

      ViewHolder(DriveDataLvItemAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.tvTitle = (TextView)var2.findViewById(2131363710);
         this.tvValue = (TextView)var2.findViewById(2131363714);
      }
   }
}
