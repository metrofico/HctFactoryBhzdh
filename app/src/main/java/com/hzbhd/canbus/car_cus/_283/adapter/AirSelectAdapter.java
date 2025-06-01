package com.hzbhd.canbus.car_cus._283.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._283.bean.AirSelectBean;
import java.util.List;

public class AirSelectAdapter extends RecyclerView.Adapter {
   private List lists;
   private Context mContext;
   private OnItemClickListener mOnItemClickListener;

   public AirSelectAdapter(Context var1, List var2) {
      this.mContext = var1;
      this.lists = var2;
   }

   public int getItemCount() {
      return this.lists.size();
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      var1.view.setVisibility(((AirSelectBean)this.lists.get(var2)).getViewVisible());
      var1.imageView.setVisibility(((AirSelectBean)this.lists.get(var2)).getDrawablwVisible());
      var1.textView.setVisibility(((AirSelectBean)this.lists.get(var2)).getTextVisible());
      var1.selectView.setVisibility(((AirSelectBean)this.lists.get(var2)).getSelectVisible());
      var1.textView.setText(((AirSelectBean)this.lists.get(var2)).getText());
      var1.imageView.setImageDrawable(((AirSelectBean)this.lists.get(var2)).getDrawable());
      var1.itemView.setOnClickListener(new View.OnClickListener(this, var2) {
         final AirSelectAdapter this$0;
         final int val$i;

         {
            this.this$0 = var1;
            this.val$i = var2;
         }

         public void onClick(View var1) {
            if (this.this$0.mOnItemClickListener != null) {
               this.this$0.mOnItemClickListener.click(this.val$i);
            }

         }
      });
      if (this.getItemCount() - 1 == var2) {
         var1.lineView.setVisibility(8);
      }

   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(this.mContext).inflate(2131558437, var1, false));
   }

   public void setOnItemClickListener(OnItemClickListener var1) {
      this.mOnItemClickListener = var1;
   }

   public interface OnItemClickListener {
      void click(int var1);
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      private ImageView imageView;
      private View lineView;
      private ImageView selectView;
      private TextView textView;
      final AirSelectAdapter this$0;
      private View view;

      public ViewHolder(AirSelectAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.selectView = (ImageView)var2.findViewById(2131363306);
         this.imageView = (ImageView)var2.findViewById(2131362472);
         this.textView = (TextView)var2.findViewById(2131363519);
         this.view = var2.findViewById(2131363766);
         this.lineView = var2.findViewById(2131362757);
      }
   }
}
