package com.hzbhd.canbus.entity;

public class OnStartWirelessUpdataEntity {
   private int position;
   private String value;

   public OnStartWirelessUpdataEntity(int var1, String var2) {
      this.position = var1;
      this.value = var2;
   }

   public int getPosition() {
      return this.position;
   }

   public String getValue() {
      return this.value;
   }

   public void setPosition(int var1) {
      this.position = var1;
   }

   public void setValue(String var1) {
      this.value = var1;
   }
}
