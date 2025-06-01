package com.hzbhd.canbus.car_cus._283.bean;

public class TmpsChooseBean {
   private int image;
   private boolean isSelect;
   private String text;

   public TmpsChooseBean(int var1, String var2) {
      this.image = var1;
      this.text = var2;
   }

   public int getImage() {
      return this.image;
   }

   public String getText() {
      return this.text;
   }

   public boolean isSelect() {
      return this.isSelect;
   }

   public void setImage(int var1) {
      this.image = var1;
   }

   public void setSelect(boolean var1) {
      this.isSelect = var1;
   }

   public void setText(String var1) {
      this.text = var1;
   }
}
