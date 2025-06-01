package com.hzbhd.canbus.entity;

public class WarningEntity {
   private String content;
   private int level;

   public WarningEntity(String var1) {
      this.content = var1;
   }

   public String getContent() {
      return this.content;
   }

   public int getLevel() {
      return this.level;
   }

   public void setContent(String var1) {
      this.content = var1;
   }

   public void setLevel(int var1) {
      this.level = var1;
   }
}
