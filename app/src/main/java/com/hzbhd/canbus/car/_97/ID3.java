package com.hzbhd.canbus.car._97;

class ID3 {
   private String charsetName;
   private int head;
   private String id3;
   private int length;
   private String record;

   public ID3(int var1, String var2, int var3) {
      this.head = var1;
      this.id3 = new String();
      this.record = new String();
      this.charsetName = var2;
      this.length = var3;
   }

   public String getCharsetName() {
      return this.charsetName;
   }

   public int getHead() {
      return this.head;
   }

   public String getId3() {
      return this.id3;
   }

   public int getLength() {
      return this.length;
   }

   public boolean isId3Change() {
      if (this.record.equals(this.id3)) {
         return false;
      } else {
         this.record = this.id3;
         return true;
      }
   }

   public void setId3(String var1) {
      this.id3 = var1;
   }
}
