package com.hzbhd.canbus.car_cus._283.choosecan;

public class A6CarChooseBean {
   private boolean isSelect;
   private String name;

   public A6CarChooseBean(boolean var1, String var2) {
      this.isSelect = var1;
      this.name = var2;
   }

   public String getName() {
      return this.name;
   }

   public boolean isSelect() {
      return this.isSelect;
   }

   public void setName(String var1) {
      this.name = var1;
   }

   public void setSelect(boolean var1) {
      this.isSelect = var1;
   }
}
