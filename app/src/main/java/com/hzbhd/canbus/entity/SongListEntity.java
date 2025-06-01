package com.hzbhd.canbus.entity;

public class SongListEntity {
   private boolean enable = true;
   private boolean isSelected;
   private String title;

   public SongListEntity(String var1) {
      this.title = var1;
   }

   public String getTitle() {
      return this.title;
   }

   public boolean isEnable() {
      return this.enable;
   }

   public boolean isSelected() {
      return this.isSelected;
   }

   public SongListEntity setEnable(boolean var1) {
      this.enable = var1;
      return this;
   }

   public SongListEntity setSelected(boolean var1) {
      this.isSelected = var1;
      return this;
   }

   public void setTitle(String var1) {
      this.title = var1;
   }
}
