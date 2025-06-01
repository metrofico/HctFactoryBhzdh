package com.hzbhd.canbus.car._334;

import java.util.List;

public class OriginalDeviceData {
   private final String[] bottomBtn;
   private final List list;
   private final String[] topBtn;

   public OriginalDeviceData(List var1) {
      this(var1, (String[])null, (String[])null);
   }

   public OriginalDeviceData(List var1, String[] var2) {
      this.list = var1;
      this.topBtn = null;
      this.bottomBtn = var2;
   }

   public OriginalDeviceData(List var1, String[] var2, String[] var3) {
      this.list = var1;
      this.topBtn = var3;
      this.bottomBtn = var2;
   }

   public String[] getBottomBtn() {
      return this.bottomBtn;
   }

   public List getItemList() {
      return this.list;
   }

   public String[] getTopBtn() {
      return this.topBtn;
   }
}
