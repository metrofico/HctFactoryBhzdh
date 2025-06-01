package com.hzbhd.canbus.car_cus._467.air.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.comm.ScreenConfig;

public class CheckItemView extends LinearLayout {
   private ImageView check_bos;
   private TextView item_name;
   private boolean selectTag;
   private View view;

   public CheckItemView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CheckItemView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public CheckItemView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.selectTag = false;
      if (ScreenConfig.screenWidth == 1024 && ScreenConfig.screenHeight == 600) {
         this.view = LayoutInflater.from(var1).inflate(2131558566, this, true);
      } else {
         this.view = LayoutInflater.from(var1).inflate(2131558568, this, true);
      }

      this.item_name = (TextView)this.view.findViewById(2131362523);
      this.check_bos = (ImageView)this.view.findViewById(2131362136);
   }

   public void check(boolean var1) {
      if (var1) {
         this.selectTag = true;
         this.check_bos.setBackgroundResource(2131231265);
      } else {
         this.selectTag = false;
         this.check_bos.setBackgroundResource(2131231270);
      }

   }

   public boolean isCheck() {
      return this.selectTag;
   }

   public void setTextColor(int var1) {
      this.item_name.setTextColor(var1);
   }

   public void setTitle(String var1) {
      this.item_name.setText(var1);
   }
}
