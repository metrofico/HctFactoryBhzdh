package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import java.util.Iterator;
import java.util.List;

public class SettingLeftLvAdapter extends RecyclerView.Adapter {
   private Context mContext;
   private LeftItemClickInterface mLeftItemClickInterface;
   private List mList;

   public SettingLeftLvAdapter(Context var1, List var2, LeftItemClickInterface var3) {
      this.mLeftItemClickInterface = var3;
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
      int var3 = CommUtil.getStrIdByResId(this.mContext, ((SettingPageUiSet.ListBean)this.mList.get(var2)).getTitleSrn());
      var1.textView.setText(var3);
      if (((SettingPageUiSet.ListBean)this.mList.get(var2)).isIsSelected()) {
         var1.textView.setBackgroundResource(2131234175);
      } else if (var2 % 2 == 0) {
         var1.textView.setBackgroundResource(2131100015);
      } else {
         var1.textView.setBackgroundResource(2131100016);
      }

      var1.textView.setOnClickListener(new View.OnClickListener(this, var2) {
         final SettingLeftLvAdapter this$0;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$position = var2;
         }

         public void onClick(View var1) {
            this.this$0.mLeftItemClickInterface.onLeftItemClick(this.val$position);
         }
      });
      var1.setVisibility(false);
      Iterator var4 = ((SettingPageUiSet.ListBean)this.mList.get(var2)).getItemList().iterator();

      while(var4.hasNext()) {
         if (((SettingPageUiSet.ListBean.ItemListBean)var4.next()).isEnable()) {
            var1.setVisibility(true);
            break;
         }
      }

   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558782, var1, false));
   }

   public interface LeftItemClickInterface {
      void onLeftItemClick(int var1);
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private TextView textView;
      final SettingLeftLvAdapter this$0;

      ViewHolder(SettingLeftLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.textView = (TextView)var2.findViewById(2131363597);
      }

      private void setVisibility(boolean var1) {
         RecyclerView.LayoutParams var2 = (RecyclerView.LayoutParams)this.itemView.getLayoutParams();
         if (var1) {
            this.itemView.setVisibility(0);
            var2.height = -2;
            var2.width = -1;
         } else {
            this.itemView.setVisibility(8);
            var2.height = 0;
            var2.width = 0;
         }

         this.itemView.setLayoutParams(var2);
      }
   }
}
