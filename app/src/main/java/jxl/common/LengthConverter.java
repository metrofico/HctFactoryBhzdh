package jxl.common;

public class LengthConverter {
   private static double[][] factors;

   static {
      int var1 = LengthUnit.getCount();
      int var0 = LengthUnit.getCount();
      double[][] var2 = new double[var1][var0];
      factors = var2;
      var2[LengthUnit.POINTS.getIndex()][LengthUnit.POINTS.getIndex()] = 1.0;
      factors[LengthUnit.METRES.getIndex()][LengthUnit.METRES.getIndex()] = 1.0;
      factors[LengthUnit.CENTIMETRES.getIndex()][LengthUnit.CENTIMETRES.getIndex()] = 1.0;
      factors[LengthUnit.INCHES.getIndex()][LengthUnit.INCHES.getIndex()] = 1.0;
      factors[LengthUnit.POINTS.getIndex()][LengthUnit.METRES.getIndex()] = 3.5277777778E-4;
      factors[LengthUnit.POINTS.getIndex()][LengthUnit.CENTIMETRES.getIndex()] = 0.035277777778;
      factors[LengthUnit.POINTS.getIndex()][LengthUnit.INCHES.getIndex()] = 0.013888888889;
      factors[LengthUnit.METRES.getIndex()][LengthUnit.POINTS.getIndex()] = 2877.84;
      factors[LengthUnit.METRES.getIndex()][LengthUnit.CENTIMETRES.getIndex()] = 100.0;
      factors[LengthUnit.METRES.getIndex()][LengthUnit.INCHES.getIndex()] = 39.37;
      factors[LengthUnit.CENTIMETRES.getIndex()][LengthUnit.POINTS.getIndex()] = 28.34643;
      factors[LengthUnit.CENTIMETRES.getIndex()][LengthUnit.METRES.getIndex()] = 0.01;
      factors[LengthUnit.CENTIMETRES.getIndex()][LengthUnit.INCHES.getIndex()] = 0.3937;
      factors[LengthUnit.INCHES.getIndex()][LengthUnit.POINTS.getIndex()] = 72.0;
      factors[LengthUnit.INCHES.getIndex()][LengthUnit.METRES.getIndex()] = 0.0254;
      factors[LengthUnit.INCHES.getIndex()][LengthUnit.CENTIMETRES.getIndex()] = 2.54;
   }

   public static double getConversionFactor(LengthUnit var0, LengthUnit var1) {
      return factors[var0.getIndex()][var1.getIndex()];
   }
}
