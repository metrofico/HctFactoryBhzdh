package com.hzbhd.canbus.car_cus._283.bean;

import android.text.TextUtils;

public class SettingDialogBean {
   private boolean isSelect;
   private String unit;
   private String value;

   public SettingDialogBean(String var1) {
      this.value = var1;
   }

   public SettingDialogBean(String var1, String var2) {
      this.value = var1;
      this.unit = var2;
   }

   public int getSelectVisible() {
      return this.isSelect ? 0 : 4;
   }

   public int getTextColor() {
      return this.isSelect ? 2131099648 : 2131100061;
   }

   public String getUnit() {
      return TextUtils.isEmpty(this.unit) ? "" : this.unit;
   }

   public String getValue() {
      return this.value;
   }

   public boolean isSelect() {
      return this.isSelect;
   }

   public void setSelect(boolean var1) {
      this.isSelect = var1;
   }

   public void setValue(String var1) {
      this.value = var1;
   }
}
