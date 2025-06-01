package com.hzbhd.canbus.car_cus._290.entity;

public class OnOffBean {
   private boolean clickable = true;
   private int iconNoSelectRes;
   private int iconSelectRes;
   private boolean isSelected;
   private int titleRes;

   public OnOffBean(int var1, int var2, int var3) {
      this.titleRes = var1;
      this.iconSelectRes = var2;
      this.iconNoSelectRes = var3;
   }

   public int getIconNoSelectRes() {
      return this.iconNoSelectRes;
   }

   public int getIconSelectRes() {
      return this.iconSelectRes;
   }

   public int getTitleRes() {
      return this.titleRes;
   }

   public boolean isClickable() {
      return this.clickable;
   }

   public boolean isSelected() {
      return this.isSelected;
   }

   public void setClickable(boolean var1) {
      this.clickable = var1;
   }

   public void setIconNoSelectRes(int var1) {
      this.iconNoSelectRes = var1;
   }

   public void setIconSelectRes(int var1) {
      this.iconSelectRes = var1;
   }

   public void setSelected(boolean var1) {
      this.isSelected = var1;
   }

   public void setTitleRes(int var1) {
      this.titleRes = var1;
   }
}
