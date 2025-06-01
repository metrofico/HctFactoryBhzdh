package jxl.biff.drawing;

final class ShapeType {
   public static final ShapeType HOST_CONTROL = new ShapeType(201);
   public static final ShapeType MIN = new ShapeType(0);
   public static final ShapeType PICTURE_FRAME = new ShapeType(75);
   public static final ShapeType TEXT_BOX = new ShapeType(202);
   public static final ShapeType UNKNOWN = new ShapeType(-1);
   private static ShapeType[] types = new ShapeType[0];
   private int value;

   ShapeType(int var1) {
      this.value = var1;
      ShapeType[] var3 = types;
      ShapeType[] var2 = new ShapeType[var3.length + 1];
      types = var2;
      System.arraycopy(var3, 0, var2, 0, var3.length);
      types[var3.length] = this;
   }

   static ShapeType getType(int var0) {
      ShapeType var3 = UNKNOWN;
      int var1 = 0;
      boolean var2 = false;

      while(true) {
         ShapeType[] var4 = types;
         if (var1 >= var4.length || var2) {
            return var3;
         }

         ShapeType var5 = var4[var1];
         if (var5.value == var0) {
            var2 = true;
            var3 = var5;
         }

         ++var1;
      }
   }

   public int getValue() {
      return this.value;
   }
}
