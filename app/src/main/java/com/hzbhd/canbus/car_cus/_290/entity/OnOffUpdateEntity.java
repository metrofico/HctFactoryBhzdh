package com.hzbhd.canbus.car_cus._290.entity;

public class OnOffUpdateEntity {
   private int index;
   private boolean isSelect;

   public OnOffUpdateEntity(int var1, boolean var2) {
      this.index = var1;
      this.isSelect = var2;
   }

   public int getIndex() {
      return this.index;
   }

   public boolean isSelect() {
      return this.isSelect;
   }

   public void setSelect(boolean var1) {
      this.isSelect = var1;
   }
}
