package com.hzbhd.canbus.car_cus._290.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._290.entity.OnOffBean;
import java.util.List;

public class OnOffLvAdapter extends RecyclerView.Adapter {
   private Context mContext;
   private ItemClickInterface mItemClickInterface;
   private List mList;

   public OnOffLvAdapter(Context var1, List var2, ItemClickInterface var3) {
      this.mItemClickInterface = var3;
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
      var1.textView.setText(((OnOffBean)this.mList.get(var2)).getTitleRes());
      if (((OnOffBean)this.mList.get(var2)).isSelected()) {
         var1.btnView.setImageResource(((OnOffBean)this.mList.get(var2)).getIconSelectRes());
      } else {
         var1.btnView.setImageResource(((OnOffBean)this.mList.get(var2)).getIconNoSelectRes());
      }

      var1.btnView.setOnClickListener(new View.OnClickListener(this, var2) {
         final OnOffLvAdapter this$0;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$position = var2;
         }

         public void onClick(View var1) {
            if (((OnOffBean)this.this$0.mList.get(this.val$position)).isClickable()) {
               this.this$0.mItemClickInterface.onItemClick(this.val$position);
            }

         }
      });
   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558409, var1, false));
   }

   public interface ItemClickInterface {
      void onItemClick(int var1);
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private ImageButton btnView;
      private RelativeLayout relativeLayout;
      private TextView textView;
      final OnOffLvAdapter this$0;

      ViewHolder(OnOffLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.btnView = (ImageButton)var2.findViewById(2131362429);
         this.textView = (TextView)var2.findViewById(2131363634);
         this.relativeLayout = (RelativeLayout)var2.findViewById(2131363179);
      }
   }
}
