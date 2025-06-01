package com.hzbhd.canbus.entity;

public class SyncListUpdateEntity {
   private boolean enable;
   private int index;
   private String info;
   private int leftIconResId;
   private int rightIconResId;
   private boolean selected;

   public SyncListUpdateEntity(int var1) {
      this.index = var1;
   }

   public int getIndex() {
      return this.index;
   }

   public String getInfo() {
      return this.info;
   }

   public int getLeftIconResId() {
      return this.leftIconResId;
   }

   public int getRightIconResId() {
      return this.rightIconResId;
   }

   public boolean isEnable() {
      return this.enable;
   }

   public boolean isSelected() {
      return this.selected;
   }

   public SyncListUpdateEntity setEnable(boolean var1) {
      this.enable = var1;
      return this;
   }

   public SyncListUpdateEntity setInfo(String var1) {
      this.info = var1;
      return this;
   }

   public SyncListUpdateEntity setLeftIconResId(int var1) {
      this.leftIconResId = var1;
      return this;
   }

   public SyncListUpdateEntity setRightIconResId(int var1) {
      this.rightIconResId = var1;
      return this;
   }

   public SyncListUpdateEntity setSelected(boolean var1) {
      this.selected = var1;
      return this;
   }
}
