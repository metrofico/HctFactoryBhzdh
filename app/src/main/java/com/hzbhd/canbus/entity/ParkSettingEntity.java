package com.hzbhd.canbus.entity;

import android.view.View;

public class ParkSettingEntity {
   private String title;
   private int type;
   private View view;

   public ParkSettingEntity(String var1, View var2, int var3) {
      this.title = var1;
      this.view = var2;
      this.type = var3;
   }

   public String getTitle() {
      return this.title;
   }

   public int getType() {
      return this.type;
   }

   public View getView() {
      return this.view;
   }

   public void setTitle(String var1) {
      this.title = var1;
   }

   public void setType(int var1) {
      this.type = var1;
   }

   public void setView(View var1) {
      this.view = var1;
   }
}
