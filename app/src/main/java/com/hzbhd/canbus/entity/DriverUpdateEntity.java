package com.hzbhd.canbus.entity;

public class DriverUpdateEntity {
   private int index;
   private int page;
   private String value;

   public DriverUpdateEntity(int var1, int var2, String var3) {
      this.page = var1;
      this.index = var2;
      this.value = var3;
   }

   public int getIndex() {
      return this.index;
   }

   public int getPage() {
      return this.page;
   }

   public String getValue() {
      return this.value;
   }

   public DriverUpdateEntity setValue(String var1) {
      this.value = var1;
      return this;
   }
}
