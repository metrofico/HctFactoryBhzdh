package com.hzbhd.canbus.entity;

public class SettingUpdateEntity {
   private boolean enable;
   private boolean isValueStr;
   private int leftListIndex;
   private int progress;
   private int rightListIndex;
   private Object value;

   public SettingUpdateEntity(int var1, int var2) {
      this(var1, var2, (Object)null);
   }

   public SettingUpdateEntity(int var1, int var2, Object var3) {
      this.enable = true;
      this.leftListIndex = var1;
      this.rightListIndex = var2;
      this.value = var3;
   }

   public int getLeftListIndex() {
      return this.leftListIndex;
   }

   public int getProgress() {
      return this.progress;
   }

   public int getRightListIndex() {
      return this.rightListIndex;
   }

   public Object getValue() {
      return this.value;
   }

   public boolean isEnable() {
      return this.enable;
   }

   public boolean isValueStr() {
      return this.isValueStr;
   }

   public SettingUpdateEntity setEnable(boolean var1) {
      this.enable = var1;
      return this;
   }

   public SettingUpdateEntity setProgress(int var1) {
      this.progress = var1;
      return this;
   }

   public SettingUpdateEntity setValue(Object var1) {
      this.value = var1;
      return this;
   }

   public SettingUpdateEntity setValueStr(boolean var1) {
      this.isValueStr = var1;
      return this;
   }
}
