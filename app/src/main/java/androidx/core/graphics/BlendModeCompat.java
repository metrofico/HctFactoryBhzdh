package androidx.core.graphics;

public enum BlendModeCompat {
   private static final BlendModeCompat[] $VALUES;
   CLEAR,
   COLOR,
   COLOR_BURN,
   COLOR_DODGE,
   DARKEN,
   DIFFERENCE,
   DST,
   DST_ATOP,
   DST_IN,
   DST_OUT,
   DST_OVER,
   EXCLUSION,
   HARD_LIGHT,
   HUE,
   LIGHTEN,
   LUMINOSITY,
   MODULATE,
   MULTIPLY,
   OVERLAY,
   PLUS,
   SATURATION,
   SCREEN,
   SOFT_LIGHT,
   SRC,
   SRC_ATOP,
   SRC_IN,
   SRC_OUT,
   SRC_OVER,
   XOR;

   static {
      BlendModeCompat var25 = new BlendModeCompat("CLEAR", 0);
      CLEAR = var25;
      BlendModeCompat var5 = new BlendModeCompat("SRC", 1);
      SRC = var5;
      BlendModeCompat var3 = new BlendModeCompat("DST", 2);
      DST = var3;
      BlendModeCompat var14 = new BlendModeCompat("SRC_OVER", 3);
      SRC_OVER = var14;
      BlendModeCompat var1 = new BlendModeCompat("DST_OVER", 4);
      DST_OVER = var1;
      BlendModeCompat var11 = new BlendModeCompat("SRC_IN", 5);
      SRC_IN = var11;
      BlendModeCompat var17 = new BlendModeCompat("DST_IN", 6);
      DST_IN = var17;
      BlendModeCompat var24 = new BlendModeCompat("SRC_OUT", 7);
      SRC_OUT = var24;
      BlendModeCompat var0 = new BlendModeCompat("DST_OUT", 8);
      DST_OUT = var0;
      BlendModeCompat var7 = new BlendModeCompat("SRC_ATOP", 9);
      SRC_ATOP = var7;
      BlendModeCompat var2 = new BlendModeCompat("DST_ATOP", 10);
      DST_ATOP = var2;
      BlendModeCompat var9 = new BlendModeCompat("XOR", 11);
      XOR = var9;
      BlendModeCompat var8 = new BlendModeCompat("PLUS", 12);
      PLUS = var8;
      BlendModeCompat var27 = new BlendModeCompat("MODULATE", 13);
      MODULATE = var27;
      BlendModeCompat var10 = new BlendModeCompat("SCREEN", 14);
      SCREEN = var10;
      BlendModeCompat var18 = new BlendModeCompat("OVERLAY", 15);
      OVERLAY = var18;
      BlendModeCompat var20 = new BlendModeCompat("DARKEN", 16);
      DARKEN = var20;
      BlendModeCompat var16 = new BlendModeCompat("LIGHTEN", 17);
      LIGHTEN = var16;
      BlendModeCompat var13 = new BlendModeCompat("COLOR_DODGE", 18);
      COLOR_DODGE = var13;
      BlendModeCompat var12 = new BlendModeCompat("COLOR_BURN", 19);
      COLOR_BURN = var12;
      BlendModeCompat var21 = new BlendModeCompat("HARD_LIGHT", 20);
      HARD_LIGHT = var21;
      BlendModeCompat var26 = new BlendModeCompat("SOFT_LIGHT", 21);
      SOFT_LIGHT = var26;
      BlendModeCompat var4 = new BlendModeCompat("DIFFERENCE", 22);
      DIFFERENCE = var4;
      BlendModeCompat var28 = new BlendModeCompat("EXCLUSION", 23);
      EXCLUSION = var28;
      BlendModeCompat var19 = new BlendModeCompat("MULTIPLY", 24);
      MULTIPLY = var19;
      BlendModeCompat var15 = new BlendModeCompat("HUE", 25);
      HUE = var15;
      BlendModeCompat var6 = new BlendModeCompat("SATURATION", 26);
      SATURATION = var6;
      BlendModeCompat var23 = new BlendModeCompat("COLOR", 27);
      COLOR = var23;
      BlendModeCompat var22 = new BlendModeCompat("LUMINOSITY", 28);
      LUMINOSITY = var22;
      $VALUES = new BlendModeCompat[]{var25, var5, var3, var14, var1, var11, var17, var24, var0, var7, var2, var9, var8, var27, var10, var18, var20, var16, var13, var12, var21, var26, var4, var28, var19, var15, var6, var23, var22};
   }
}
