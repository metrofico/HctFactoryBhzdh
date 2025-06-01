package com.hzbhd.canbus.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HybirdValueLvAdapter extends RecyclerView.Adapter {
   private List mList;

   public HybirdValueLvAdapter(List var1) {
      this.mList = var1;
   }

   public int getItemCount() {
      return this.mList.size();
   }

   public long getItemId(int var1) {
      return (long)var1;
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      var1.textView.setText((CharSequence)this.mList.get(var2));
   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558764, var1, false));
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private TextView textView;
      final HybirdValueLvAdapter this$0;

      ViewHolder(HybirdValueLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.textView = (TextView)var2.findViewById(2131363631);
      }
   }
}
