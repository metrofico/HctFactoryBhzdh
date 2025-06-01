package com.hzbhd.canbus.car_cus._277.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._277.DiagnosisEntity;
import java.util.List;

public class DiagnosisLvAdapter extends RecyclerView.Adapter {
   private Context mContext;
   private List mList;

   public DiagnosisLvAdapter(Context var1, List var2) {
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
      var1.tvTitle.setText(((DiagnosisEntity)this.mList.get(var2)).getTitle());
   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558423, var1, false));
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private RelativeLayout relativeLayout;
      final DiagnosisLvAdapter this$0;
      private TextView tvTitle;

      ViewHolder(DiagnosisLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.relativeLayout = (RelativeLayout)var2.findViewById(2131362786);
         this.tvTitle = (TextView)var2.findViewById(2131363710);
      }
   }
}
