package com.hzbhd.canbus.car_cus._439.air.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CheckItemView2 extends LinearLayout {
   private ImageView check_bos;
   private TextView item_name;
   private boolean selectTag;
   private View view;

   public CheckItemView2(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CheckItemView2(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public CheckItemView2(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.selectTag = false;
      View var4 = LayoutInflater.from(var1).inflate(2131558536, this, true);
      this.view = var4;
      this.item_name = (TextView)var4.findViewById(2131362523);
      this.check_bos = (ImageView)this.view.findViewById(2131362136);
   }

   public void check(boolean var1) {
      if (var1) {
         this.selectTag = true;
         this.check_bos.setBackgroundResource(2131231256);
      } else {
         this.selectTag = false;
         this.check_bos.setBackgroundResource(2131231257);
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
