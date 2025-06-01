package com.hzbhd.canbus.entity;

public class SyncSoftKeyUpdateEntity {
   private int imageResId;
   private int index;
   private boolean isSelected;
   private boolean isVisible = true;
   private String name;

   public SyncSoftKeyUpdateEntity(int var1) {
      this.index = var1;
   }

   public int getImageResId() {
      return this.imageResId;
   }

   public int getIndex() {
      return this.index;
   }

   public String getName() {
      return this.name;
   }

   public boolean isSelected() {
      return this.isSelected;
   }

   public boolean isVisible() {
      return this.isVisible;
   }

   public SyncSoftKeyUpdateEntity setImageResId(int var1) {
      this.imageResId = var1;
      return this;
   }

   public SyncSoftKeyUpdateEntity setName(String var1) {
      this.name = var1;
      return this;
   }

   public SyncSoftKeyUpdateEntity setSelected(boolean var1) {
      this.isSelected = var1;
      return this;
   }

   public SyncSoftKeyUpdateEntity setVisible(boolean var1) {
      this.isVisible = var1;
      return this;
   }
}
