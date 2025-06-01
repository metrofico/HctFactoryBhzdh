package jxl.format;

public final class RGB {
   private int blue;
   private int green;
   private int red;

   public RGB(int var1, int var2, int var3) {
      this.red = var1;
      this.green = var2;
      this.blue = var3;
   }

   public int getBlue() {
      return this.blue;
   }

   public int getGreen() {
      return this.green;
   }

   public int getRed() {
      return this.red;
   }
}
