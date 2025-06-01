package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.entity.WarningEntity;
import java.util.List;

public class WarningLvAdapter extends RecyclerView.Adapter {
   private Context mContext;
   private List mList;

   public WarningLvAdapter(Context var1, List var2) {
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
      var1.tvTitle.setText(((WarningEntity)this.mList.get(var2)).getContent());
   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558785, var1, false));
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private RelativeLayout relativeLayout;
      final WarningLvAdapter this$0;
      private TextView tvTitle;

      ViewHolder(WarningLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.relativeLayout = (RelativeLayout)var2.findViewById(2131362786);
         this.tvTitle = (TextView)var2.findViewById(2131363710);
      }
   }
}
