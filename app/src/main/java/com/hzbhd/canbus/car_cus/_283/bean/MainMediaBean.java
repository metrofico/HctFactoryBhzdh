package com.hzbhd.canbus.car_cus._283.bean;

import android.content.ComponentName;

public class MainMediaBean {
   private int image;
   private ComponentName mComponentName;
   private String text;

   public MainMediaBean(int var1, String var2, ComponentName var3) {
      this.image = var1;
      this.text = var2;
      this.mComponentName = var3;
   }

   public int getImage() {
      return this.image;
   }

   public String getText() {
      return this.text;
   }

   public ComponentName getmComponentName() {
      return this.mComponentName;
   }

   public void setImage(int var1) {
      this.image = var1;
   }

   public void setText(String var1) {
      this.text = var1;
   }

   public void setmComponentName(ComponentName var1) {
      this.mComponentName = var1;
   }
}
