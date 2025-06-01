package com.hzbhd.canbus.car_cus._283.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._283.bean.TextViewBean;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {
   private List lists;
   private Context mContext;

   public MyAdapter(Context var1, List var2) {
      this.mContext = var1;
      this.lists = var2;
   }

   public int getItemCount() {
      return this.lists.size();
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      var1.textView.setText(((TextViewBean)this.lists.get(var2)).getText());
      var1.textView.setTextSize((float)((TextViewBean)this.lists.get(var2)).getTextSize());
   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(this.mContext).inflate(2131558479, var1, false));
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      private TextView textView;
      final MyAdapter this$0;

      public ViewHolder(MyAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.textView = (TextView)var2.findViewById(2131363519);
      }
   }
}
