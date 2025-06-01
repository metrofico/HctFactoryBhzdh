package com.hzbhd.canbus.entity;

public class PanoramicBtnUpdateEntity {
   private int index;
   private boolean isSelect;

   public PanoramicBtnUpdateEntity(int var1, boolean var2) {
      this.index = var1;
      this.isSelect = var2;
   }

   public int getIndex() {
      return this.index;
   }

   public boolean isSelect() {
      return this.isSelect;
   }
}
