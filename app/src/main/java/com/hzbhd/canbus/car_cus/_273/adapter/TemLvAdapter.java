package com.hzbhd.canbus.car_cus._273.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class TemLvAdapter extends RecyclerView.Adapter {
   private int[] mArray;
   private Context mContext;

   public TemLvAdapter(Context var1, int[] var2) {
      this.mArray = var2;
      this.mContext = var1;
   }

   public int getItemCount() {
      return this.mArray.length;
   }

   public long getItemId(int var1) {
      return (long)var1;
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      var1.textView.setText(String.valueOf(this.mArray[var2]));
      if (var2 == 3) {
         var1.textView.setBackgroundResource(2131232707);
      } else {
         var1.textView.setBackground((Drawable)null);
      }

   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558410, var1, false));
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private TextView textView;
      final TemLvAdapter this$0;

      ViewHolder(TemLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.textView = (TextView)var2.findViewById(2131363578);
      }
   }
}
