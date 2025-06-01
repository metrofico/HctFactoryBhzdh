package com.hzbhd.canbus.entity;

public class DriveDataItem {
   private String defaultValue;
   private String titleStrRes;

   public DriveDataItem(String var1, String var2) {
      this.titleStrRes = var1;
      this.defaultValue = var2;
   }

   public String getDefaultValue() {
      return this.defaultValue;
   }

   public String getTitleStrRes() {
      return this.titleStrRes;
   }

   public void setDefaultValue(String var1) {
      this.defaultValue = var1;
   }

   public void setTitleStrRes(String var1) {
      this.titleStrRes = var1;
   }
}
