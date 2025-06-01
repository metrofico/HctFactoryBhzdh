package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import java.util.List;

public class SelectCanTypeLastLvAdapter extends RecyclerView.Adapter {
   private ItemCallBackInterface mItemCallBackInterface;
   private List mList;
   private int mNormalTextColor;
   private int mSelectTextColor;

   public SelectCanTypeLastLvAdapter(Context var1, List var2, ItemCallBackInterface var3) {
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
      if (((CanTypeAllEntity)this.mList.get(var2)).isSelected()) {
         var1.ll.setBackgroundResource(2131234654);
         var1.tv0.setTextColor(this.mSelectTextColor);
         var1.tv1.setTextColor(this.mSelectTextColor);
      } else {
         var1.ll.setBackground((Drawable)null);
         var1.tv0.setTextColor(this.mNormalTextColor);
         var1.tv1.setTextColor(this.mNormalTextColor);
      }

      var1.tv0.setText(((CanTypeAllEntity)this.mList.get(var2)).getCan_type_id() + "");
      var1.tv1.setText(((CanTypeAllEntity)this.mList.get(var2)).getDescription());
      var1.ll.setOnClickListener(new View.OnClickListener(this, var2) {
         final SelectCanTypeLastLvAdapter this$0;
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
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558774, var1, false));
   }

   public interface ItemCallBackInterface {
      void select(int var1);
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private ImageView iv;
      private RelativeLayout ll;
      final SelectCanTypeLastLvAdapter this$0;
      private TextView tv0;
      private TextView tv1;

      ViewHolder(SelectCanTypeLastLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.ll = (RelativeLayout)var2.findViewById(2131362786);
         this.tv0 = (TextView)var2.findViewById(2131363581);
         this.tv1 = (TextView)var2.findViewById(2131363582);
         this.iv = (ImageView)var2.findViewById(2131362625);
      }
   }
}
