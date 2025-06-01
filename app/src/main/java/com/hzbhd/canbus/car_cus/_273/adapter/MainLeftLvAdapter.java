package com.hzbhd.canbus.car_cus._273.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._273.entity.LeftItemBean;
import java.util.List;

public class MainLeftLvAdapter extends RecyclerView.Adapter {
   private LeftItemClickInterface mLeftItemClickInterface;
   private List mList;

   public MainLeftLvAdapter(Context var1, List var2, LeftItemClickInterface var3) {
      this.mLeftItemClickInterface = var3;
      this.mList = var2;
   }

   public int getItemCount() {
      return this.mList.size();
   }

   public long getItemId(int var1) {
      return (long)var1;
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      if (((LeftItemBean)this.mList.get(var2)).isSelected()) {
         var1.relativeLayout.setBackgroundResource(2131232633);
      } else {
         var1.relativeLayout.setBackgroundResource(2131232632);
      }

      var1.relativeLayout.setOnClickListener(new View.OnClickListener(this, var2) {
         final MainLeftLvAdapter this$0;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$position = var2;
         }

         public void onClick(View var1) {
            this.this$0.mLeftItemClickInterface.onLeftItemClick(this.val$position);
         }
      });
      var1.textView.setText(((LeftItemBean)this.mList.get(var2)).getTitleRes());
      var1.imageView.setImageResource(((LeftItemBean)this.mList.get(var2)).getIconRes());
   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558408, var1, false));
   }

   public interface LeftItemClickInterface {
      void onLeftItemClick(int var1);
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private ImageView imageView;
      private RelativeLayout relativeLayout;
      private TextView textView;
      final MainLeftLvAdapter this$0;

      ViewHolder(MainLeftLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.textView = (TextView)var2.findViewById(2131363578);
         this.imageView = (ImageView)var2.findViewById(2131362529);
         this.relativeLayout = (RelativeLayout)var2.findViewById(2131362251);
      }
   }
}
