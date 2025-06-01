package com.hzbhd.canbus.car_cus._304.bean;

import android.view.View;

public class MainPageEntity {
   private String title;
   private View view;

   public MainPageEntity(String var1, View var2) {
      this.title = var1;
      this.view = var2;
   }

   public String getTitle() {
      return this.title;
   }

   public View getView() {
      return this.view;
   }

   public void setTitle(String var1) {
      this.title = var1;
   }

   public void setView(View var1) {
      this.view = var1;
   }
}
