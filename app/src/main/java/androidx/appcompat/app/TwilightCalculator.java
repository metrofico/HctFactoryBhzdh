package androidx.appcompat.app;

class TwilightCalculator {
   private static final float ALTIDUTE_CORRECTION_CIVIL_TWILIGHT = -0.10471976F;
   private static final float C1 = 0.0334196F;
   private static final float C2 = 3.49066E-4F;
   private static final float C3 = 5.236E-6F;
   public static final int DAY = 0;
   private static final float DEGREES_TO_RADIANS = 0.017453292F;
   private static final float J0 = 9.0E-4F;
   public static final int NIGHT = 1;
   private static final float OBLIQUITY = 0.4092797F;
   private static final long UTC_2000 = 946728000000L;
   private static TwilightCalculator sInstance;
   public int state;
   public long sunrise;
   public long sunset;

   static TwilightCalculator getInstance() {
      if (sInstance == null) {
         sInstance = new TwilightCalculator();
      }

      return sInstance;
   }

   public void calculateTwilight(long var1, double var3, double var5) {
      float var12 = (float)(var1 - 946728000000L) / 8.64E7F;
      float var11 = 0.01720197F * var12 + 6.24006F;
      double var9 = (double)var11;
      double var7 = Math.sin(var9) * 0.03341960161924362 + var9 + Math.sin((double)(2.0F * var11)) * 3.4906598739326E-4 + Math.sin((double)(var11 * 3.0F)) * 5.236000106378924E-6 + 1.796593063 + Math.PI;
      var5 = -var5 / 360.0;
      var5 = (double)((float)Math.round((double)(var12 - 9.0E-4F) - var5) + 9.0E-4F) + var5 + Math.sin(var9) * 0.0053 + Math.sin(2.0 * var7) * -0.0069;
      var7 = Math.asin(Math.sin(var7) * Math.sin(0.4092797040939331));
      var3 = 0.01745329238474369 * var3;
      var3 = (Math.sin(-0.10471975803375244) - Math.sin(var3) * Math.sin(var7)) / (Math.cos(var3) * Math.cos(var7));
      if (var3 >= 1.0) {
         this.state = 1;
         this.sunset = -1L;
         this.sunrise = -1L;
      } else if (var3 <= -1.0) {
         this.state = 0;
         this.sunset = -1L;
         this.sunrise = -1L;
      } else {
         var3 = (double)((float)(Math.acos(var3) / 6.283185307179586));
         this.sunset = Math.round((var5 + var3) * 8.64E7) + 946728000000L;
         long var13 = Math.round((var5 - var3) * 8.64E7) + 946728000000L;
         this.sunrise = var13;
         if (var13 < var1 && this.sunset > var1) {
            this.state = 0;
         } else {
            this.state = 1;
         }

      }
   }
}
