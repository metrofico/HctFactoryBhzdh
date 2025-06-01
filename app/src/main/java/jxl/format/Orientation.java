package jxl.format;

public final class Orientation {
   public static Orientation HORIZONTAL = new Orientation(0, "horizontal");
   public static Orientation MINUS_45 = new Orientation(135, "down 45");
   public static Orientation MINUS_90 = new Orientation(180, "down 90");
   public static Orientation PLUS_45 = new Orientation(45, "up 45");
   public static Orientation PLUS_90 = new Orientation(90, "up 90");
   public static Orientation STACKED = new Orientation(255, "stacked");
   public static Orientation VERTICAL = new Orientation(255, "vertical");
   private static Orientation[] orientations = new Orientation[0];
   private String string;
   private int value;

   protected Orientation(int var1, String var2) {
      this.value = var1;
      this.string = var2;
      Orientation[] var4 = orientations;
      Orientation[] var3 = new Orientation[var4.length + 1];
      orientations = var3;
      System.arraycopy(var4, 0, var3, 0, var4.length);
      orientations[var4.length] = this;
   }

   public static Orientation getOrientation(int var0) {
      int var1 = 0;

      while(true) {
         Orientation[] var2 = orientations;
         if (var1 >= var2.length) {
            return HORIZONTAL;
         }

         if (var2[var1].getValue() == var0) {
            return orientations[var1];
         }

         ++var1;
      }
   }

   public String getDescription() {
      return this.string;
   }

   public int getValue() {
      return this.value;
   }
}
