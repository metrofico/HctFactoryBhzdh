package com.hzbhd.canbus.car_cus._290.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._290.entity.MediaItemBean;
import java.util.List;

public class MediaItenAdapter extends RecyclerView.Adapter {
   private Context mContext;
   private ItemClickInterface mItemClickInterface;
   private List mList;

   public MediaItenAdapter(Context var1, List var2, ItemClickInterface var3) {
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
      var1.textView.setText(((MediaItemBean)this.mList.get(var2)).getTitleRes());
      var1.btnView.setBackground(this.mContext.getDrawable(((MediaItemBean)this.mList.get(var2)).getIconReleaseRes()));
      var1.btnView.setOnClickListener(new View.OnClickListener(this, var2) {
         final MediaItenAdapter this$0;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$position = var2;
         }

         public void onClick(View var1) {
            this.this$0.mItemClickInterface.onItemClick(((MediaItemBean)this.this$0.mList.get(this.val$position)).getTarget());
         }
      });
      var1.btnView.setOnTouchListener(new View.OnTouchListener(this, var1, var2) {
         final MediaItenAdapter this$0;
         final ViewHolder val$holder;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$holder = var2;
            this.val$position = var3;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.val$holder.btnView.setBackground(this.this$0.mContext.getDrawable(((MediaItemBean)this.this$0.mList.get(this.val$position)).getIconPressRes()));
            } else if (var2.getAction() == 1) {
               this.val$holder.btnView.setBackground(this.this$0.mContext.getDrawable(((MediaItemBean)this.this$0.mList.get(this.val$position)).getIconReleaseRes()));
            }

            return false;
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
      private TextView textView;
      final MediaItenAdapter this$0;

      ViewHolder(MediaItenAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.btnView = (ImageButton)var2.findViewById(2131362429);
         this.textView = (TextView)var2.findViewById(2131363634);
      }
   }
}
