package com.hzbhd.canbus.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SyncListItemView extends LinearLayout {
   private ImageView mIvLeftIcon;
   private ImageView mIvRightIcon;
   private LinearLayout mLlListItem;
   private OnClickListener mOnClickListener;
   private TextView mTvInfo;

   public SyncListItemView(Context var1) {
      super(var1);
      LayoutInflater.from(var1).inflate(2131558732, this);
      this.findView();
      this.initView();
   }

   private void findView() {
      this.mLlListItem = (LinearLayout)this.findViewById(2131362790);
      this.mIvLeftIcon = (ImageView)this.findViewById(2131362595);
      this.mIvRightIcon = (ImageView)this.findViewById(2131362625);
      this.mTvInfo = (TextView)this.findViewById(2131363631);
   }

   private void initView() {
      this.mLlListItem.setOnClickListener(new View.OnClickListener(this) {
         final SyncListItemView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.mOnClickListener.onItemClick();
         }
      });
   }

   public void setEnabled(boolean var1) {
      this.mLlListItem.setEnabled(var1);
   }

   public void setItem(int var1, String var2, int var3) {
      try {
         this.mIvLeftIcon.setImageResource(var1);
         this.mTvInfo.setText(var2);
         this.mIvRightIcon.setImageResource(var3);
      } catch (Exception var4) {
         this.mIvLeftIcon.setImageResource(2131100046);
         this.mTvInfo.setText("");
         this.mIvRightIcon.setImageResource(2131100046);
      }

   }

   public void setOnClickListener(OnClickListener var1) {
      this.mOnClickListener = var1;
   }

   public void setSelected(boolean var1) {
      this.mLlListItem.setSelected(var1);
   }

   interface OnClickListener {
      void onItemClick();
   }
}
