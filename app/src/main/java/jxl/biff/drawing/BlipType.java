package jxl.biff.drawing;

final class BlipType {
   public static final BlipType DIB = new BlipType(7, "DIB");
   public static final BlipType EMF = new BlipType(2, "EMF");
   public static final BlipType ERROR = new BlipType(0, "Error");
   public static final BlipType FIRST_CLIENT = new BlipType(32, "FIRST");
   public static final BlipType JPEG = new BlipType(5, "JPEG");
   public static final BlipType LAST_CLIENT = new BlipType(255, "LAST");
   public static final BlipType PICT = new BlipType(4, "PICT");
   public static final BlipType PNG = new BlipType(6, "PNG");
   public static final BlipType UNKNOWN = new BlipType(1, "Unknown");
   public static final BlipType WMF = new BlipType(3, "WMF");
   private static BlipType[] types = new BlipType[0];
   private String desc;
   private int value;

   private BlipType(int var1, String var2) {
      this.value = var1;
      this.desc = var2;
      BlipType[] var4 = types;
      BlipType[] var3 = new BlipType[var4.length + 1];
      System.arraycopy(var4, 0, var3, 0, var4.length);
      var3[types.length] = this;
      types = var3;
   }

   public static BlipType getType(int var0) {
      BlipType var3 = UNKNOWN;
      int var1 = 0;

      BlipType var2;
      while(true) {
         BlipType[] var4 = types;
         var2 = var3;
         if (var1 >= var4.length) {
            break;
         }

         var2 = var4[var1];
         if (var2.value == var0) {
            break;
         }

         ++var1;
      }

      return var2;
   }

   public String getDescription() {
      return this.desc;
   }

   public int getValue() {
      return this.value;
   }
}
