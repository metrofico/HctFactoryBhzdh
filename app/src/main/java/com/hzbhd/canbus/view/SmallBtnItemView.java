package com.hzbhd.canbus.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SmallBtnItemView extends LinearLayout {
   private boolean mIsOn;
   private OnItemClickListener mOnItemClickListener;
   private TextView mTv;

   public SmallBtnItemView(Context var1, int var2) {
      super(var1);
      this.initViews(var1, var2);
   }

   public SmallBtnItemView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   private void initViews(Context var1, int var2) {
      TextView var3 = (TextView)LayoutInflater.from(var1).inflate(2131558854, this).findViewById(2131361993);
      this.mTv = var3;
      var3.setText(var1.getString(var2));
      this.mTv.setOnClickListener(new View.OnClickListener(this) {
         final SmallBtnItemView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.mOnItemClickListener != null) {
               this.this$0.mOnItemClickListener.onClick();
            }

         }
      });
   }

   public boolean ismIsOn() {
      return this.mIsOn;
   }

   public void setOnItemClickListener(OnItemClickListener var1) {
      this.mOnItemClickListener = var1;
   }

   public void turn(boolean var1) {
      if (var1) {
         this.mTv.setTextColor(this.getResources().getColor(17170443, (Resources.Theme)null));
         this.mTv.setBackgroundResource(2131234060);
      } else {
         this.mTv.setTextColor(this.getResources().getColor(17170444, (Resources.Theme)null));
         this.mTv.setBackgroundResource(2131234059);
      }

   }

   public interface OnItemClickListener {
      void onClick();
   }
}
