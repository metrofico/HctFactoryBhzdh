package com.hzbhd.canbus.car_cus._290.entity;

public class LeftItemBean {
   private int iconRes;
   private boolean isSelected;
   private int titleRes;

   public LeftItemBean(int var1, int var2, boolean var3) {
      this.titleRes = var1;
      this.iconRes = var2;
      this.isSelected = var3;
   }

   public int getIconRes() {
      return this.iconRes;
   }

   public int getTitleRes() {
      return this.titleRes;
   }

   public boolean isSelected() {
      return this.isSelected;
   }

   public void setIconRes(int var1) {
      this.iconRes = var1;
   }

   public void setSelected(boolean var1) {
      this.isSelected = var1;
   }

   public void setTitleRes(int var1) {
      this.titleRes = var1;
   }
}
