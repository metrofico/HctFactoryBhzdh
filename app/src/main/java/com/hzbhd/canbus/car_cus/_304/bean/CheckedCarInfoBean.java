package com.hzbhd.canbus.car_cus._304.bean;

public class CheckedCarInfoBean {
   private boolean isChecked;
   private int textSize;
   private String title;

   public CheckedCarInfoBean(String var1) {
      this.title = var1;
   }

   public int getTextSize() {
      return this.textSize;
   }

   public String getTitle() {
      return this.title;
   }

   public boolean isChecked() {
      return this.isChecked;
   }

   public void setChecked(boolean var1) {
      this.isChecked = var1;
   }

   public void setTextSize(int var1) {
      this.textSize = var1;
   }

   public void setTitle(String var1) {
      this.title = var1;
   }
}
