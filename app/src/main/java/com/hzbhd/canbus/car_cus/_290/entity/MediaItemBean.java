package com.hzbhd.canbus.car_cus._290.entity;

public class MediaItemBean {
   private int iconPressRes;
   private int iconReleaseRes;
   private int target;
   private int titleRes;

   public MediaItemBean(int var1, int var2, int var3, int var4) {
      this.titleRes = var1;
      this.iconPressRes = var2;
      this.iconReleaseRes = var3;
      this.target = var4;
   }

   public int getIconPressRes() {
      return this.iconPressRes;
   }

   public int getIconReleaseRes() {
      return this.iconReleaseRes;
   }

   public int getTarget() {
      return this.target;
   }

   public int getTitleRes() {
      return this.titleRes;
   }
}
