package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SelectCanTypeIdAdapter extends RecyclerView.Adapter {
   private int mCurSelectedId;
   private ItemCallBackInterface mItemCallBackInterface;
   private List mList;
   private int mNormalTextColor;
   private int mSelectTextColor;

   public SelectCanTypeIdAdapter(Context var1, List var2, ItemCallBackInterface var3, int var4) {
      this.mList = var2;
      this.mItemCallBackInterface = var3;
      this.mCurSelectedId = var4;
      this.mSelectTextColor = var1.getColor(2131099752);
      this.mNormalTextColor = var1.getColor(2131100061);
   }

   public int getItemCount() {
      return this.mList.size();
   }

   public long getItemId(int var1) {
      return (long)var1;
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      if ((Integer)this.mList.get(var2) == this.mCurSelectedId) {
         var1.tv.setBackgroundResource(2131234654);
         var1.tv.setTextColor(this.mSelectTextColor);
      } else {
         var1.tv.setBackground((Drawable)null);
         var1.tv.setTextColor(this.mNormalTextColor);
      }

      var1.tv.setText(String.valueOf(this.mList.get(var2)));
      var1.tv.setOnClickListener(new View.OnClickListener(this, var2) {
         final SelectCanTypeIdAdapter this$0;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$position = var2;
         }

         public void onClick(View var1) {
            if (this.this$0.mItemCallBackInterface != null) {
               this.this$0.mItemCallBackInterface.select((Integer)this.this$0.mList.get(this.val$position));
            }

         }
      });
   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558773, var1, false));
   }

   public interface ItemCallBackInterface {
      void select(int var1);
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      final SelectCanTypeIdAdapter this$0;
      private TextView tv;

      ViewHolder(SelectCanTypeIdAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.tv = (TextView)var2.findViewById(2131363578);
      }
   }
}
