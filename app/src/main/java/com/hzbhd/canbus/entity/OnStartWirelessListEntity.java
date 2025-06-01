package com.hzbhd.canbus.entity;

public class OnStartWirelessListEntity {
   private String title;
   private String value;

   public OnStartWirelessListEntity(String var1, String var2) {
      this.title = var1;
      this.value = var2;
   }

   public String getTitle() {
      return this.title;
   }

   public String getValue() {
      return this.value;
   }

   public void setTitle(String var1) {
      this.title = var1;
   }

   public void setValue(String var1) {
      this.value = var1;
   }
}
