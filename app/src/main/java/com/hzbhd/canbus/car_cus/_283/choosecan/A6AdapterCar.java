package com.hzbhd.canbus.car_cus._283.choosecan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class A6AdapterCar extends RecyclerView.Adapter {
   private List lists;
   private Context mContext;
   private OnItemClickListener mOnItemClickListener;

   public A6AdapterCar(Context var1, List var2) {
      this.mContext = var1;
      this.lists = var2;
   }

   public int getItemCount() {
      return this.lists.size();
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      var1.itemLayout.setSelected(((A6CarChooseBean)this.lists.get(var2)).isSelect());
      var1.imageView.setSelected(((A6CarChooseBean)this.lists.get(var2)).isSelect());
      var1.tv_title.setText(((A6CarChooseBean)this.lists.get(var2)).getName());
      var1.itemLayout.setOnClickListener(new View.OnClickListener(this, var2) {
         final A6AdapterCar this$0;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$position = var2;
         }

         public void onClick(View var1) {
            if (this.this$0.mOnItemClickListener != null) {
               this.this$0.mOnItemClickListener.onClick(var1, this.val$position);
            }

         }
      });
   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(this.mContext).inflate(2131558587, var1, false));
   }

   public void setOnItemClickListener(OnItemClickListener var1) {
      this.mOnItemClickListener = var1;
   }

   public interface OnItemClickListener {
      void onClick(View var1, int var2);
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      private ImageView imageView;
      private LinearLayout itemLayout;
      final A6AdapterCar this$0;
      private TextView tv_title;

      public ViewHolder(A6AdapterCar var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.itemLayout = (LinearLayout)var2.findViewById(2131362522);
         this.imageView = (ImageView)var2.findViewById(2131362472);
         this.tv_title = (TextView)var2.findViewById(2131363710);
      }
   }
}
