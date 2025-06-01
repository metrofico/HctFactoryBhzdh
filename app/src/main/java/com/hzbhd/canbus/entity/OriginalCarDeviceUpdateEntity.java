package com.hzbhd.canbus.entity;

public class OriginalCarDeviceUpdateEntity {
   private int index;
   private String value;

   public OriginalCarDeviceUpdateEntity(int var1, String var2) {
      this.value = var2;
      this.index = var1;
   }

   public int getIndex() {
      return this.index;
   }

   public String getValue() {
      return this.value;
   }
}
