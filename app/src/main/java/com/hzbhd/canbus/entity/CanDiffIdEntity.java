package com.hzbhd.canbus.entity;

public class CanDiffIdEntity {
   private int canDiffId;
   private String content;
   private int eachCanId;

   public CanDiffIdEntity(int var1, int var2, String var3) {
      this.canDiffId = var1;
      this.content = var3;
      this.eachCanId = var2;
   }

   public int getCanDiffId() {
      return this.canDiffId;
   }

   public String getContent() {
      return this.content;
   }

   public int getEachCanId() {
      return this.eachCanId;
   }

   public void setCanDiffId(int var1) {
      this.canDiffId = var1;
   }

   public void setContent(String var1) {
      this.content = var1;
   }

   public void setEachCanId(int var1) {
      this.eachCanId = var1;
   }
}
