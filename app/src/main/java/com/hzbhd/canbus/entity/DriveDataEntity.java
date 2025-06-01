package com.hzbhd.canbus.entity;

import java.util.Arrays;
import java.util.List;

public class DriveDataEntity {
   private String headTitleStrRes;
   private List itemList;

   public DriveDataEntity(String var1, DriveDataItem[] var2) {
      this.headTitleStrRes = var1;
      this.itemList = Arrays.asList(var2);
   }

   public String getHeadTitleStrRes() {
      return this.headTitleStrRes;
   }

   public List getItemList() {
      return this.itemList;
   }

   public void setHeadTitleStrRes(String var1) {
      this.headTitleStrRes = var1;
   }

   public void setItemList(List var1) {
      this.itemList = var1;
   }
}
