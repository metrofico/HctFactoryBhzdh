package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SelectProtocolCompanyLvAdapter extends RecyclerView.Adapter {
   private ItemCallBackInterface mItemCallBackInterface;
   private List mList;
   private String selectItem = "";

   public SelectProtocolCompanyLvAdapter(Context var1, List var2, ItemCallBackInterface var3) {
      this.mList = var2;
      this.mItemCallBackInterface = var3;
   }

   public int getItemCount() {
      return this.mList.size();
   }

   public long getItemId(int var1) {
      return (long)var1;
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      if (this.selectItem.equals(this.mList.get(var2))) {
         var1.llItem.setBackgroundResource(2131234363);
         var1.ivDot.setVisibility(0);
      } else {
         var1.ivDot.setVisibility(4);
         if (var2 % 2 == 0) {
            var1.llItem.setBackgroundResource(2131100015);
         } else {
            var1.llItem.setBackgroundResource(2131100016);
         }
      }

      String var3 = (String)this.mList.get(var2);
      var1.tv.setText(var3);
      var1.llItem.setOnClickListener(new View.OnClickListener(this, var2) {
         final SelectProtocolCompanyLvAdapter this$0;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$position = var2;
         }

         public void onClick(View var1) {
            if (this.this$0.mItemCallBackInterface != null) {
               this.this$0.mItemCallBackInterface.select(this.val$position);
            }

         }
      });
   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558776, var1, false));
   }

   public void setSelectItem(String var1) {
      this.selectItem = var1;
   }

   public interface ItemCallBackInterface {
      void select(int var1);
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private ImageView ivDot;
      private LinearLayout llItem;
      final SelectProtocolCompanyLvAdapter this$0;
      private TextView tv;

      ViewHolder(SelectProtocolCompanyLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.tv = (TextView)var2.findViewById(2131363578);
         this.ivDot = (ImageView)var2.findViewById(2131362562);
         this.llItem = (LinearLayout)var2.findViewById(2131362783);
      }
   }
}
