package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SelectCarCateLvAdapter extends RecyclerView.Adapter {
   private ItemCallBackInterface mItemCallBackInterface;
   private List mList;
   private int mNormalTextColor;
   private int mSelectTextColor;
   private String selectItem = "";

   public SelectCarCateLvAdapter(Context var1, List var2, ItemCallBackInterface var3) {
      this.mList = var2;
      this.mItemCallBackInterface = var3;
      this.mSelectTextColor = var1.getColor(2131100061);
      this.mNormalTextColor = var1.getColor(2131100014);
   }

   public int getItemCount() {
      return this.mList.size();
   }

   public long getItemId(int var1) {
      return (long)var1;
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      if (this.selectItem.equals(this.mList.get(var2))) {
         var1.tv.setBackgroundResource(2131099782);
         var1.tv.setTextColor(this.mSelectTextColor);
      } else {
         var1.tv.setBackgroundResource(2131100016);
         var1.tv.setTextColor(this.mNormalTextColor);
      }

      String var3 = (String)this.mList.get(var2);
      var1.tv.setText(var3);
      var1.lLayout.setOnClickListener(new View.OnClickListener(this, var2) {
         final SelectCarCateLvAdapter this$0;
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
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558775, var1, false));
   }

   public void setSelectItem(String var1) {
      this.selectItem = var1;
   }

   public interface ItemCallBackInterface {
      void select(int var1);
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private LinearLayout lLayout;
      final SelectCarCateLvAdapter this$0;
      private TextView tv;

      ViewHolder(SelectCarCateLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.tv = (TextView)var2.findViewById(2131363578);
         this.lLayout = (LinearLayout)var2.findViewById(2131362773);
      }
   }
}
