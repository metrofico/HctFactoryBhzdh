package com.hzbhd.canbus.car_cus._283.bean;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;

public class AirSelectBean {
   private Drawable drawable;
   private boolean isSelect;
   private String text;

   public AirSelectBean(String var1, Drawable var2) {
      this.text = var1;
      this.drawable = var2;
   }

   public Drawable getDrawable() {
      return this.drawable;
   }

   public int getDrawablwVisible() {
      return this.drawable == null ? 8 : 0;
   }

   public int getSelectVisible() {
      return this.isSelect ? 0 : 8;
   }

   public String getText() {
      return this.text;
   }

   public int getTextVisible() {
      return TextUtils.isEmpty(this.text) ? 8 : 0;
   }

   public int getViewVisible() {
      if (this.drawable == null) {
         return 8;
      } else {
         return TextUtils.isEmpty(this.text) ? 8 : 0;
      }
   }

   public boolean isSelect() {
      return this.isSelect;
   }

   public void setDrawable(Drawable var1) {
      this.drawable = var1;
   }

   public void setSelect(boolean var1) {
      this.isSelect = var1;
   }

   public void setText(String var1) {
      this.text = var1;
   }
}
