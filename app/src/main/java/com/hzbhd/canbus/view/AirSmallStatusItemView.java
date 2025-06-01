package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class AirSmallStatusItemView extends RelativeLayout {
   private String mAction;
   private ImageView mIv;
   private int mOffResId;
   private OnAirInfoChangeListener mOnAirInfoChangeListener;
   private int mOnResId;

   public AirSmallStatusItemView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public AirSmallStatusItemView(Context var1, String var2, int var3, int var4, OnAirInfoChangeListener var5) {
      super(var1);
      this.initViews(var1, var2, var3, var4, var5);
   }

   private void initViews(Context var1, String var2, int var3, int var4, OnAirInfoChangeListener var5) {
      ImageView var6 = (ImageView)LayoutInflater.from(var1).inflate(2131558856, this).findViewById(2131362578);
      this.mIv = var6;
      this.mAction = var2;
      this.mOffResId = var3;
      this.mOnResId = var4;
      this.mOnAirInfoChangeListener = var5;
      var6.setImageResource(var3);
   }

   public void setImageResource(int var1) {
      this.mIv.setImageResource(var1);
   }

   public void turn() {
      OnAirInfoChangeListener var2 = this.mOnAirInfoChangeListener;
      if (var2 != null) {
         ImageView var3 = this.mIv;
         int var1;
         if (var2.onAirInfoChange()) {
            var1 = this.mOnResId;
         } else {
            var1 = this.mOffResId;
         }

         var3.setImageResource(var1);
      }

   }
}
