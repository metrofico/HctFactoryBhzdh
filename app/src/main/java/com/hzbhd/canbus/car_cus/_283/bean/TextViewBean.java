package com.hzbhd.canbus.car_cus._283.bean;

public class TextViewBean {
   private String text;
   private int textSize;

   public TextViewBean(String var1, int var2) {
      this.text = var1;
      this.textSize = var2;
   }

   public String getText() {
      return this.text;
   }

   public int getTextSize() {
      int var2 = this.textSize;
      int var1 = var2;
      if (var2 < 0) {
         var1 = 0;
      }

      return var1;
   }

   public void setText(String var1) {
      this.text = var1;
   }

   public void setTextSize(int var1) {
      this.textSize = var1;
   }
}
