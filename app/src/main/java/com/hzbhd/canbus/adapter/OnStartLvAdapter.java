package com.hzbhd.canbus.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.entity.OnStartListEntity;
import java.util.List;

public class OnStartLvAdapter extends RecyclerView.Adapter {
   private ItemClickInterface mItemClickInterface;
   private List mList;

   public OnStartLvAdapter(List var1, ItemClickInterface var2) {
      this.mItemClickInterface = var2;
      this.mList = var1;
   }

   public int getItemCount() {
      return this.mList.size();
   }

   public long getItemId(int var1) {
      return (long)var1;
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      String var7 = ((OnStartListEntity)this.mList.get(var2)).getAction();
      var7.hashCode();
      int var4 = var7.hashCode();
      byte var6 = 0;
      int var3 = -1;
      switch (var4) {
         case -1664453177:
            if (var7.equals("phone_more_info")) {
               var3 = 0;
            }
            break;
         case -1000044642:
            if (var7.equals("wireless")) {
               var3 = 1;
            }
            break;
         case 106642798:
            if (var7.equals("phone")) {
               var3 = 2;
            }
            break;
         case 1862666772:
            if (var7.equals("navigation")) {
               var3 = 3;
            }
            break;
         case 1968882350:
            if (var7.equals("bluetooth")) {
               var3 = 4;
            }
      }

      int var5;
      label41: {
         var5 = 2131769487;
         var4 = 2131234156;
         switch (var3) {
            case 0:
            case 2:
               break label41;
            case 1:
               var4 = 2131234158;
               var3 = 2131770863;
               break;
            case 3:
               var4 = 2131234157;
               var3 = 2131769336;
               break;
            case 4:
               var4 = 2131234155;
               var3 = 2131767870;
               break;
            default:
               var4 = 0;
               var5 = var6;
               break label41;
         }

         var5 = var3;
      }

      var1.textView.setText(var5);
      var1.imageView.setImageResource(var4);
      var1.relativeLayout.setOnClickListener(new View.OnClickListener(this, var2) {
         final OnStartLvAdapter this$0;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$position = var2;
         }

         public void onClick(View var1) {
            this.this$0.mItemClickInterface.onItemClick(this.val$position);
         }
      });
   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558766, var1, false));
   }

   public interface ItemClickInterface {
      void onItemClick(int var1);
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private ImageView imageView;
      private RelativeLayout relativeLayout;
      private TextView textView;
      final OnStartLvAdapter this$0;

      ViewHolder(OnStartLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.relativeLayout = (RelativeLayout)var2.findViewById(2131362786);
         this.imageView = (ImageView)var2.findViewById(2131362529);
         this.textView = (TextView)var2.findViewById(2131363578);
      }
   }
}
