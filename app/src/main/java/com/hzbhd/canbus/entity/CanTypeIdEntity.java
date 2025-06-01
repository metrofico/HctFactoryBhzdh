package com.hzbhd.canbus.entity;

public class CanTypeIdEntity {
   private int id;
   private boolean selected;

   public CanTypeIdEntity(int var1, boolean var2) {
      this.id = var1;
      this.selected = var2;
   }

   public int getId() {
      return this.id;
   }

   public boolean isSelected() {
      return this.selected;
   }

   public void setId(int var1) {
      this.id = var1;
   }

   public void setSelected(boolean var1) {
      this.selected = var1;
   }
}
