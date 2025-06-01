package com.hzbhd.constant.camera;

public class CameraConstants {
   public static enum MATRIX {
      private static final MATRIX[] $VALUES;
      FMS6502,
      NONE;

      static {
         MATRIX var0 = new MATRIX("NONE", 0);
         NONE = var0;
         MATRIX var1 = new MATRIX("FMS6502", 1);
         FMS6502 = var1;
         $VALUES = new MATRIX[]{var0, var1};
      }
   }

   public static enum MATRIX_VIN {
      private static final MATRIX_VIN[] $VALUES;
      AUXIN,
      CAMERA0,
      CAMERA3,
      DVD,
      OFF,
      TV,
      TVOUT0;

      static {
         MATRIX_VIN var4 = new MATRIX_VIN("OFF", 0);
         OFF = var4;
         MATRIX_VIN var2 = new MATRIX_VIN("TV", 1);
         TV = var2;
         MATRIX_VIN var0 = new MATRIX_VIN("DVD", 2);
         DVD = var0;
         MATRIX_VIN var1 = new MATRIX_VIN("TVOUT0", 3);
         TVOUT0 = var1;
         MATRIX_VIN var6 = new MATRIX_VIN("AUXIN", 4);
         AUXIN = var6;
         MATRIX_VIN var5 = new MATRIX_VIN("CAMERA3", 5);
         CAMERA3 = var5;
         MATRIX_VIN var3 = new MATRIX_VIN("CAMERA0", 6);
         CAMERA0 = var3;
         $VALUES = new MATRIX_VIN[]{var4, var2, var0, var1, var6, var5, var3};
      }
   }

   public static enum MIRROR {
      private static final MIRROR[] $VALUES;
      LEFT_RIGHT,
      NORMAL,
      UP_DOWN;

      static {
         MIRROR var0 = new MIRROR("NORMAL", 0);
         NORMAL = var0;
         MIRROR var2 = new MIRROR("LEFT_RIGHT", 1);
         LEFT_RIGHT = var2;
         MIRROR var1 = new MIRROR("UP_DOWN", 2);
         UP_DOWN = var1;
         $VALUES = new MIRROR[]{var0, var2, var1};
      }
   }

   public static enum ROTATE {
      private static final ROTATE[] $VALUES;
      DEG_0,
      DEG_180,
      DEG_270,
      DEG_90;

      static {
         ROTATE var2 = new ROTATE("DEG_0", 0);
         DEG_0 = var2;
         ROTATE var1 = new ROTATE("DEG_90", 1);
         DEG_90 = var1;
         ROTATE var3 = new ROTATE("DEG_180", 2);
         DEG_180 = var3;
         ROTATE var0 = new ROTATE("DEG_270", 3);
         DEG_270 = var0;
         $VALUES = new ROTATE[]{var2, var1, var3, var0};
      }
   }

   public static enum SENSOR {
      private static final SENSOR[] $VALUES;
      MST701,
      N3,
      N4,
      N5,
      NONE,
      NVP6158C;

      static {
         SENSOR var2 = new SENSOR("NONE", 0);
         NONE = var2;
         SENSOR var0 = new SENSOR("MST701", 1);
         MST701 = var0;
         SENSOR var5 = new SENSOR("NVP6158C", 2);
         NVP6158C = var5;
         SENSOR var1 = new SENSOR("N3", 3);
         N3 = var1;
         SENSOR var4 = new SENSOR("N4", 4);
         N4 = var4;
         SENSOR var3 = new SENSOR("N5", 5);
         N5 = var3;
         $VALUES = new SENSOR[]{var2, var0, var5, var1, var4, var3};
      }
   }
}
