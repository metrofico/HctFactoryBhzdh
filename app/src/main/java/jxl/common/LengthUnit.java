package jxl.common;

public class LengthUnit extends BaseUnit {
   public static LengthUnit CENTIMETRES = new LengthUnit();
   public static LengthUnit INCHES = new LengthUnit();
   public static LengthUnit METRES = new LengthUnit();
   public static LengthUnit POINTS = new LengthUnit();
   private static int count;

   private LengthUnit() {
      int var1 = count++;
      super(var1);
   }

   public static int getCount() {
      return count;
   }
}
