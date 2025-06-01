package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.util.CommUtil;
import java.util.ArrayList;

public class PanelKeyLvAdapter extends RecyclerView.Adapter {
   private Context mContext;
   private ItemClickInterface mItemClickInterface;
   private ItemLongClickInterface mItemLongClickInterface;
   private ItemTouchInterface mItemTouchInterface;
   private ArrayList mList;

   public PanelKeyLvAdapter(Context var1, ArrayList var2, ItemClickInterface var3, ItemTouchInterface var4, ItemLongClickInterface var5) {
      this.mItemClickInterface = var3;
      this.mItemTouchInterface = var4;
      this.mItemLongClickInterface = var5;
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
      var1.textView.setText(CommUtil.getStrIdByResId(this.mContext, (String)this.mList.get(var2)));
      var1.imageButton.setOnClickListener(new View.OnClickListener(this, var2) {
         final PanelKeyLvAdapter this$0;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$position = var2;
         }

         public void onClick(View var1) {
            this.this$0.mItemClickInterface.onItemClick(this.val$position);
         }
      });
      var1.imageButton.setOnTouchListener(new View.OnTouchListener(this, var2) {
         final PanelKeyLvAdapter this$0;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$position = var2;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (this.this$0.mItemTouchInterface != null) {
               this.this$0.mItemTouchInterface.onItemTouch(this.val$position, var2);
            }

            return false;
         }
      });
      var1.imageButton.setOnLongClickListener(new View.OnLongClickListener(this, var2) {
         final PanelKeyLvAdapter this$0;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$position = var2;
         }

         public boolean onLongClick(View var1) {
            if (this.this$0.mItemLongClickInterface != null) {
               this.this$0.mItemLongClickInterface.onItemLongClick(this.val$position);
            }

            return false;
         }
      });
   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558769, var1, false));
   }

   public interface ItemClickInterface {
      void onItemClick(int var1);
   }

   public interface ItemLongClickInterface {
      void onItemLongClick(int var1);
   }

   public interface ItemTouchInterface {
      void onItemTouch(int var1, MotionEvent var2);
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private ImageButton imageButton;
      private TextView textView;
      final PanelKeyLvAdapter this$0;

      ViewHolder(PanelKeyLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.imageButton = (ImageButton)var2.findViewById(2131362429);
         this.textView = (TextView)var2.findViewById(2131363634);
      }
   }
}
