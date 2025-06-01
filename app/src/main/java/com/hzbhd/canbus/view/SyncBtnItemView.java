package com.hzbhd.canbus.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SyncBtnItemView extends RelativeLayout {
   private ImageButton mIb;
   private OnClickListener mOnClickListener;
   private TextView mTv;

   public SyncBtnItemView(Context var1) {
      super(var1);
      this.initViews(var1);
   }

   private void initViews(Context var1) {
      View var2 = LayoutInflater.from(var1).inflate(2131558867, this);
      this.mTv = (TextView)var2.findViewById(2131363634);
      ImageButton var3 = (ImageButton)var2.findViewById(2131362429);
      this.mIb = var3;
      var3.setOnClickListener(new View.OnClickListener(this) {
         final SyncBtnItemView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.mOnClickListener.onclick();
         }
      });
   }

   public void setImageResource(int var1) {
      this.mIb.setImageResource(var1);
   }

   public void setOnClickListener(OnClickListener var1) {
      this.mOnClickListener = var1;
   }

   public void setSelected(boolean var1) {
      this.mIb.setSelected(var1);
   }

   public void setText(String var1) {
      this.mTv.setText(var1);
   }

   public void setTextColor(int var1) {
      this.mTv.setTextColor(var1);
   }

   public void setVisible(boolean var1) {
      if (var1) {
         this.setVisibility(0);
      } else {
         this.setVisibility(4);
      }

   }

   interface OnClickListener {
      void onclick();
   }

   public interface OnItemClickListener {
      void onItemClick(View var1);
   }
}
